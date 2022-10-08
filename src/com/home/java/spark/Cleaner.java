package com.home.java.spark;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Cleaner {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession
                .builder()
                .appName("myapp")
                .master("local")
                .getOrCreate();
        String inputPath="/home/neeraj/Desktop/Input/file.txt";
       Dataset<Row> dataset= sparkSession.read().text(inputPath);
        JavaRDD<String> javaRDD= dataset.javaRDD().map(new Function<Row, String>() {
           @Override
           public String call(Row row) throws Exception {

               return row.mkString()+"Hello";
           }
       });
        javaRDD.foreach(p-> System.out.println(p));


    }

    public String changing(String data)
    {
        return "Hello"+data;
    }



}
