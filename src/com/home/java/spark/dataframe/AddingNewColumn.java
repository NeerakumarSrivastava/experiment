package com.home.java.spark.dataframe;

import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.ArrayList;
import java.util.List;

public class AddingNewColumn {

    public static void main(String[] args) throws AnalysisException {

        /*
        * Adding new column using select statement...
        *
        * */

        List<Employee> list=new ArrayList<>();
        Employee employee1=new Employee(1,"Neeraj");
        Employee employee2=new Employee(2,"Mrinalini");
        Employee employee3=new Employee(3,"Pankaj");
        list.add(employee1);
        list.add(employee2);
        list.add(employee3);

        SparkSession sparkSession=SparkSession.builder().master("local").appName("NewCol").getOrCreate();
        Dataset <Row> dataSet=sparkSession.createDataFrame(list,Employee.class);
        dataSet.createTempView("emp");

        dataSet=dataSet.sqlContext().sql("select id, null as department,name from emp");
        dataSet.show();





    }

}
