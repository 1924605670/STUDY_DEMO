package com.study.bigdata.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-03-20 14:36
 * @Description
 **/
public class WordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split(" ");
        for (String word : words) {
            context.write(new Text(word),new IntWritable(1));
        }
    }
}
