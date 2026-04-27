import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

public class TFIDFJob {
    public static class IDFMapper extends Mapper<LongWritable, Text, Text, Text> {
        private Text term = new Text();
        private Text docIdAndTf = new Text();

        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] parts = value.toString().split("\t");
            if (parts.length < 2) return;
            term.set(parts[0]);
            docIdAndTf.set(parts[1]);
            context.write(term, docIdAndTf);
        }
    }

    public static class IDFReducer extends Reducer<Text, Text, Text, Text> {
        private Text result = new Text();

        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            List<String> cache = new ArrayList<>();
            for (Text val : values) {
                cache.add(val.toString());
            }
            long df = cache.size();
            Configuration conf = context.getConfiguration();
            long totalDocs = conf.getLong("TOTAL_DOCS", 1);
            double idf = Math.log10((double) totalDocs / df);
            for (String val : cache) {
                try {
                    String[] parts = val.split("=");
                    if (parts.length < 2) continue;
                    String docId = parts[0];
                    double tf = Double.parseDouble(parts[1]);
                    double tfIdf = tf * idf;
                    result.set("(" + key.toString() + ", " + docId + ")\t" + tfIdf);
                    context.write(null, result);
                } catch (NumberFormatException e) {
                    // Skip malformed records
                    continue;
                }
            }
        }
    }
}
