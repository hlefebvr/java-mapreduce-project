import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ComputeMapper
    extends Mapper<LongWritable, MapWritable, LongWritable, MapWritable>{

    public void map(LongWritable key, MapWritable value, Context context) throws IOException, InterruptedException {

        MapWritable map = new MapWritable();

        double n = ((DoubleWritable) value.get(new IntWritable(0))).get();
        
        map.put(new IntWritable(0), new DoubleWritable(n));

        System.out.println("Recieved : " + value.toString());
        for (int i = 1 ; i < value.size() ; i += 2) {
            double s_xi = ((DoubleWritable) value.get(new IntWritable(i))).get();
            double s_xi2 = ((DoubleWritable) value.get(new IntWritable(i+1))).get();
            System.out.println(" n = " + n);
            System.out.println(" s_xi = " + s_xi);
            System.out.println(" mean = " + (s_xi / n));
            map.put(new IntWritable(i), new DoubleWritable(s_xi / n));
            map.put(new IntWritable(i+1), new DoubleWritable(s_xi2 / n - (s_xi / n) * (s_xi / n) ));
        }

        context.write(key, map);
    }
}