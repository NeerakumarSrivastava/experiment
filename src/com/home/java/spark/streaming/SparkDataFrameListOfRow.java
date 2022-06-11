package com.home.java.spark.streaming;

import com.home.java.basics.Data;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.parser.CatalystSqlParser;
import org.apache.spark.sql.types.*;

import java.util.ArrayList;
import java.util.List;

public class SparkDataFrameListOfRow {

    public static void main(String[] args) {

        SparkSession sparkSession= SparkSession.builder()
                .master("local")
                .appName("List Of Row").
                getOrCreate();

        List<Row> list=new ArrayList<>();
        Object []obj1=new Object[2];
        obj1[0]=12;
        obj1[1]="My Name";

        Row row=RowFactory.create(obj1);
        list.add(row);
        obj1=new Object[2];
        obj1[0]=124;
        obj1[1]="Neeraj";

        row=RowFactory.create(obj1);
        list.add(row);
        System.out.println(list);


/*
        StructType structType=new StructType();
        structType=structType.add("id","int");
        structType=structType.add("Name","string");
*/




      /*  StructType structType=new StructType();
        structType=structType.add("id", DataTypes.IntegerType);
        structType=structType.add("Name",DataTypes.StringType);
*/

   /*     StructType structType = new StructType(new StructField[]{
                new StructField("id", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("Name", DataTypes.StringType, false, Metadata.empty())
        });

*/

   //  StructType structType=new CatalystSqlParser(sparkSession.sqlContext().conf()).parseTableSchema("id int,name string");

        StructType structType=StructType.fromDDL("id int, name string");

        Dataset<Row> DFrow=sparkSession.createDataFrame(list, structType);



        DFrow.show();












    }


}
