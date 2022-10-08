package com.home.java.spark.streaming;

import org.apache.spark.api.java.function.FlatMapGroupsFunction;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.api.java.function.MapGroupsFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.catalyst.encoders.RowEncoder;
import org.apache.spark.sql.types.StructType;

import java.util.Iterator;

public class SparkStreamingGroupbykey {

    public static void main(String[] args) throws  Exception{
        SparkSession sparkSession = SparkSession.builder()
                .appName("Groupbykey")
                .master("local")
                .getOrCreate();

        StructType structType=new StructType();
        structType=structType.add("name","string");
        structType=structType.add("count","int");
        structType=structType.add("date","timestamp");
        Dataset<Row> dataset=sparkSession
                .readStream()
                .option("timestampFormat","yyyy/mm/dd HH:MM:SS")
                .schema(structType)
                .format("csv")
                .load("/home/neeraj/bigdata/streamingPath/spark/inputfile");

        KeyValueGroupedDataset <String,Row> keyValueGroupedDataset=dataset.groupByKey(new MapFunction<Row, String>() {
            @Override
            public String call(Row row) throws Exception {
                return (String)row.get(0);
            }
        }, Encoders.STRING());

        structType=new StructType();
        structType=structType.add("name","string");
        structType=structType.add("totalcount","int");


        Dataset<Row> dataset1= keyValueGroupedDataset.mapGroups(new MapGroupsFunction<String, Row, Row>() {
            @Override
            public Row call(String s, Iterator<Row> iterator) throws Exception {
                int count=0;
                while (iterator.hasNext())
                {
                    Row row=iterator.next();
                    count+=row.getInt(1);
                }
                Object []obj=new Object[2];
                obj[0]=s;
                obj[1]=count;

                return RowFactory.create(obj);
            }
        },RowEncoder.apply(structType));
        
        

        dataset1
                .writeStream().option("checkpointLocation", "/home/neeraj/Desktop/workspace/JavaExperiment/out/checkpoint/Groupbykey").format("console")
                .start("/home/neeraj/bigdata/output/Groupbykey").awaitTermination();


    }


}
