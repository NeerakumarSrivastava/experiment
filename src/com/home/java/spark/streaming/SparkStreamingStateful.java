package com.home.java.spark.streaming;

import org.apache.spark.api.java.function.FlatMapGroupsWithStateFunction;
import org.apache.spark.api.java.function.MapFunction;

import org.apache.spark.sql.*;
import org.apache.spark.sql.catalyst.encoders.RowEncoder;
import org.apache.spark.sql.streaming.GroupState;
import org.apache.spark.sql.streaming.GroupStateTimeout;
import org.apache.spark.sql.streaming.OutputMode;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.types.StructType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SparkStreamingStateful {

    public static void main(String[] args) throws StreamingQueryException {
        SparkSession sparkSession = SparkSession.builder()
                .appName("Stateful")
                .master("local")
                .getOrCreate();
        StructType structType=new StructType();
        structType=structType.add("name","string");
        structType=structType.add("count","int");
        structType=structType.add("timestamp","timestamp");
        Dataset<Row> dataset=sparkSession
                .readStream()
                .option("timestampFormat","yyyy/mm/dd HH:MM:SS")
                .schema(structType)
                .format("csv")
                .load("/home/neeraj/bigdata/streamingPath/spark/inputfile");

        dataset= dataset.withWatermark("timestamp","10 minutes");




        KeyValueGroupedDataset<String,Row> keyValueGroupedDataset=dataset.groupByKey(new MapFunction<Row, String>() {
            @Override
            public String call(Row row) throws Exception {
                return (String)row.get(0);
            }
        }, Encoders.STRING());

        structType=new StructType();
        structType=structType.add("name","string");
        structType=structType.add("totalcount","int");

/*
* Append Mode is not working with mapGroupwithstate
*
* */

        Dataset<Row> dataset1= keyValueGroupedDataset.flatMapGroupsWithState(new FlatMapGroupsWithStateFunction<String, Row, GroupStateInfo, Row>() {

            @Override
            public Iterator<Row> call(String s, Iterator<Row> iterator, GroupState<GroupStateInfo> groupState) throws Exception {

                GroupStateInfo groupStateInfo=null;
                if(groupState.exists())
                    groupStateInfo= groupState.get();
                int count=0;
                while (iterator.hasNext())
                {
                    Row row=iterator.next();
                    count+=row.getInt(1);
                }


                if(groupStateInfo==null)
                {
                    GroupStateInfo groupStateInfo1=new GroupStateInfo();
                    groupStateInfo1.setName(s);
                    groupStateInfo1.setCount(count);
                    groupState.update(groupStateInfo1);
                }
                else
                {

                 count+=groupStateInfo.getCount();

                }
                Object []obj=new Object[2];
                obj[0]=s;
                obj[1]=count;
                List<Row> list=new ArrayList<>();
                list.add(RowFactory.create(obj));

                return list.iterator();
            }
        }, OutputMode.Append(),Encoders.bean(GroupStateInfo.class),RowEncoder.apply(structType), GroupStateTimeout.EventTimeTimeout());


        dataset1
                .writeStream().option("checkpointLocation", "/home/neeraj/Desktop/workspace/JavaExperiment/out/checkpoint/Groupbykey")
                .format("console")
              //  .outputMode("update")
                .start("/home/neeraj/bigdata/output/Groupbykey").awaitTermination();


    }


}
