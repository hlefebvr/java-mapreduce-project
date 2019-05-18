import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.chain.ChainMapper;
import org.apache.hadoop.mapreduce.lib.chain.ChainReducer;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class BayesClassifier {
  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "bayesian classifier");

    job.setJarByClass(BayesClassifier.class);

    // ChainMapper.addMapper(job, CollectionMapper.class, LongWritable.class, Text.class, Text.class, Text.class, mapConf1);
    //  ChainReducer.setReducer(job, TotalSalesReducer.class, Text.class, IntWritable.class, Text.class, IntWritable.class, reduceConf);
    ChainMapper.addMapper(job, CategoryMapper.class, LongWritable.class, Text.class, LongWritable.class, MapWritable.class, conf);
    ChainReducer.setReducer(job, EstimateReducer.class, LongWritable.class, MapWritable.class, LongWritable.class, MapWritable.class, conf);
    ChainReducer.addMapper(job, ComputeMapper.class, LongWritable.class, MapWritable.class, LongWritable.class, MapWritable.class, conf);

    // job.setMapperClass(CategoryMapper.class);
    // job.setCombinerClass(EstimateReducer.class);
    // job.setReducerClass(EstimateReducer.class);

    job.setOutputKeyClass(LongWritable.class);
    job.setOutputValueClass(MapWritable.class);
    
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
