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

public class CategoryMapper
    extends Mapper<LongWritable, Text, LongWritable, MapWritable>{

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] cols = value.toString().split(";");
        LongWritable k = new LongWritable(Long.parseLong(cols[0]));
        MapWritable map = new MapWritable();

        map.put(new IntWritable(0), new DoubleWritable(1));

        int e = 1;
        for (int i = 1 ; i < cols.length ; i += 1) {
            double x = Double.parseDouble(cols[i]);
            map.put(new IntWritable(e), new DoubleWritable(x));
            map.put(new IntWritable(e + 1), new DoubleWritable(x * x));
            e += 2;
        }

        System.out.println("key = " + k.toString());
        System.out.println("value = " + map.toString());

        context.write(k, map);
    }
}