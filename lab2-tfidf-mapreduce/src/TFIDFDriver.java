import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class TFIDFDriver extends Configured implements Tool {
    public int run(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.printf("Usage: %s [generic options] <input> <output>\n", getClass().getSimpleName());
            ToolRunner.printGenericCommandUsage(System.err);
            return -1;
        }
        String inputPath = args[0];
        String tempPath = args[1] + "_temp";
        String outputPath = args[1];

        // --- JOB 1: TF ---
        Configuration conf1 = getConf();
        Job job1 = Job.getInstance(conf1, "TF Computation");
        job1.setJarByClass(TFJob.class);
        job1.setMapperClass(TFJob.TFMapper.class);
        job1.setReducerClass(TFJob.TFReducer.class);
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job1, new Path(inputPath));
        FileOutputFormat.setOutputPath(job1, new Path(tempPath));
        if (!job1.waitForCompletion(true)) {
            return 1;
        }

        // Retrieve Total Document Count from Job 1
        long totalDocs = job1.getCounters().findCounter(TFJob.DocCounters.TOTAL_DOCS).getValue();

        // --- JOB 2: TF-IDF ---
        Configuration conf2 = getConf();
        conf2.setLong("TOTAL_DOCS", totalDocs);
        Job job2 = Job.getInstance(conf2, "TF-IDF Computation");
        job2.setJarByClass(TFIDFJob.class);
        job2.setMapperClass(TFIDFJob.IDFMapper.class);
        job2.setReducerClass(TFIDFJob.IDFReducer.class);
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job2, new Path(tempPath));
        FileOutputFormat.setOutputPath(job2, new Path(outputPath));
        return job2.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new TFIDFDriver(), args);
        System.exit(exitCode);
    }
}
