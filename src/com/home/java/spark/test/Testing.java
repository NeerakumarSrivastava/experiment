package com.home.java.spark.test;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Testing {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder().appName("UseLead").master("local").getOrCreate();




    }


}
