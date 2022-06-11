package com.home.java.spark.streaming;


import com.home.java.basics.Data;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StreamingDataFrameList {

    public static void main(String[] args) {
        SparkSession sparkSession= SparkSession
                .builder()
                .appName("myapp")
                .master("local")
                .getOrCreate();

        List<Data> list=new ArrayList<>();
        list.add(new Data(1,"df1"));
        list.add(new Data(2,"df2"));
        list.add(new Data(3,"df3"));
        list.add(new Data(4,"df4"));


        Dataset<Row> row=sparkSession.createDataFrame(list, Data.class);



        row.show();


    }





}
