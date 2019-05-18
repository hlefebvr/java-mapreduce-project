import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class EstimateReducer extends Reducer<LongWritable,MapWritable,LongWritable,MapWritable> {

    public void reduce(LongWritable key, Iterable<MapWritable> values, Context context) throws IOException, InterruptedException {
      MapWritable map = new MapWritable();

      for (MapWritable val : values) {
        int size = val.size();
        for (int i = 0 ; i < size ; i += 1) {
          double x = ((DoubleWritable) val.get(new IntWritable(i))).get();
          if (map.size() < size) {
            map.put(new IntWritable(i), new DoubleWritable(x));
          }
          else {
            double y = ((DoubleWritable) map.get(new IntWritable(i))).get();
            map.put( new IntWritable(i), new DoubleWritable( x + y ));
          }
        }
      }
      
      context.write(key, map);
    }
}
