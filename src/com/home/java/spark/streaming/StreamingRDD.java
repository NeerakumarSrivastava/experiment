package com.home.java.spark.streaming;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;


import java.util.ArrayList;
import java.util.List;

public class StreamingRDD {
     public static void main(String[] args) {

        SparkConf sparkConf=new SparkConf();
        sparkConf.setMaster("local");
        sparkConf.setAppName("Spark RDD");
        List<String> list=new ArrayList<>();
        list.add("df1");
        list.add("df2");
        list.add("df3");
        list.add("df4");

        JavaSparkContext javaSparkContext=new JavaSparkContext(sparkConf);
        JavaRDD <String> javaRDD=javaSparkContext.parallelize(list);
        javaRDD.foreach(p-> System.out.println(p));

    }


}
