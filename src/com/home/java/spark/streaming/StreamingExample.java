package com.home.java.spark.streaming;

import org.apache.commons.codec.StringEncoder;
import org.apache.spark.api.java.function.FlatMapGroupsFunction;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.catalyst.encoders.RowEncoder;
import org.apache.spark.sql.streaming.DataStreamReader;
import org.apache.spark.sql.types.StructType;

public class StreamingExample {

    public static void main(String[] args)  throws Exception{

        SparkSession sparkSession = SparkSession
                .builder()
                .appName("myapp")
                .master("local")

                .getOrCreate();




        StructType structType = new StructType();
        structType = structType.add("name", "string");
        structType = structType.add("count", "int");
        structType = structType.add("time", "timestamp");


        Dataset<Row> rowDataset = sparkSession
                .readStream()
                .format("csv")
                .option("timestampFormat","yyyy/mm/dd hh:MM:SS")
                .schema(structType)
                .load("/home/neeraj/bigdata/streamingPath/spark/inputfile");


/*

        KeyValueGroupedDataset <String,Row> keyValueGroupedDataset=rowDataset.groupByKey(new MapFunction<Row, Object>() {
            @Override
            public String call(Row row) throws Exception {
                return (String)row.get(0);
            }
        }, Encoders.STRING());

        rowDataset=  keyValueGroupedDataset.flatMapGroups(new FlatMapGroupsFunction<String, Row, Object>() {
        });

*/


        rowDataset.writeStream().format("console").outputMode("append")
                .option("checkpointLocation", "/home/neeraj/Desktop/workspace/JavaExperiment/out/checkpoint/myapp")
                .start("/home/neeraj/bigdata/output").awaitTermination();


    }


}
