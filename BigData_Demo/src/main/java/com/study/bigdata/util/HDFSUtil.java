package com.study.bigdata.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-03-20 17:11
 * @Description
 **/
public class HDFSUtil {

    public static void del(String path, Configuration conf) throws IOException, InterruptedException, URISyntaxException {
        System.setProperty("HADOOP_USER_NAME", "root");
        // 删除文件（夹）
        // 加载文件系统实例
        FileSystem fs = FileSystem.get(new URI("hdfs://server1:9000/"), conf);
        fs.delete(new Path(path), true);
    }

}
