import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

public class TFJob {
    public static enum DocCounters {
        TOTAL_DOCS
    }

    public static class TFMapper extends Mapper<LongWritable, Text, Text, Text> {
        private Text docId = new Text();
        private Text word = new Text();

        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] parts = value.toString().split(",", 2);
            if (parts.length < 2) return;
            String id = parts[0].trim();
            String content = parts[1].trim();
            content = content.replaceAll("[^a-zA-Z ]", "").toLowerCase();
            docId.set(id);
            StringTokenizer tokenizer = new StringTokenizer(content);
            while (tokenizer.hasMoreTokens()) {
                word.set(tokenizer.nextToken());
                context.write(docId, word);
            }
        }
    }

    public static class TFReducer extends Reducer<Text, Text, Text, Text> {
        private Text outKey = new Text();
        private Text outValue = new Text();

        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            Map<String, Integer> termCounts = new HashMap<>();
            int totalWordsInDoc = 0;
            for (Text val : values) {
                String term = val.toString();
                termCounts.put(term, termCounts.getOrDefault(term, 0) + 1);
                totalWordsInDoc++;
            }
            for (Map.Entry<String, Integer> entry : termCounts.entrySet()) {
                String term = entry.getKey();
                int count = entry.getValue();
                double tf = (double) count / totalWordsInDoc;
                outKey.set(term);
                outValue.set(key.toString() + "=" + tf);
                context.write(outKey, outValue);
            }
            context.getCounter(DocCounters.TOTAL_DOCS).increment(1);
        }
    }
}
