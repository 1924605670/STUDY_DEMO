package com.study.bigdata.wordcount;

import com.study.bigdata.util.HDFSUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-03-20 14:45
 * @Description
 **/
public class WordCountJob {

    public static void main(String[] args) throws Exception {
        System.setProperty("HADOOP_USER_NAME","root");
        Configuration configuration = new Configuration();

        configuration.set("fs.default.name", "hdfs://server1:9000");
        configuration.set("mapreduce.app-submission.cross-platform", "true");
        configuration.set("mapreduce.framework.name", "yarn");
        configuration.set("mapreduce.cluster.local.dir","/Users/chengzhihua/Documents/soft/hadoop");

        configuration.set("mapred.jar","/Users/chengzhihua/IdeaProjects/STUDY_DEMO/BigData_Demo/target/BigData_Demo-1.0-SNAPSHOT.jar");

        HDFSUtil.del(args[1].substring(args[1].indexOf("9000")),configuration);

        Job job = Job.getInstance(configuration);

        job.setJarByClass(WordCountJob.class);

        job.setMapperClass(WordCountMapper.class);

        job.setReducerClass(WordCountReduce.class);

        job.setMapOutputKeyClass(Text.class);

        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);

        job.setOutputValueClass(IntWritable.class);

//        job.setSortComparatorClass(IntWritableDecreasingComparator.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean flag = job.waitForCompletion(true);
        System.exit(flag ? 0 : 1);

    }

}
