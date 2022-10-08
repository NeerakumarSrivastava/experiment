package com.home.java.spark.dataframe.functions;

import com.home.java.spark.dataframe.Employee;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.expressions.WindowSpec;
import org.apache.spark.sql.functions;

import java.util.ArrayList;
import java.util.List;

public class DenseRankFunction {
    public static void main(String[] args) {

        List<Employee> listEmp = new ArrayList<>();
        Employee employee1 = new Employee(1, "Neeraj");
        employee1.setDepartment("rcem");
        Employee employee2 = new Employee(2, "Mrinalini");
        employee2.setDepartment("rcem");
        Employee employee3 = new Employee(3, "Pankaj");
        employee3.setDepartment("college");
        Employee employee4 = new Employee(4, "Prabhat");
        employee4.setDepartment("college");
        Employee employee5 = new Employee(5, "Preeti");
        employee5.setDepartment("college");
        listEmp.add(employee1);
        listEmp.add(employee2);
        listEmp.add(employee3);
        listEmp.add(employee4);
        listEmp.add(employee5);

        /*
         * Learning :
         *
         *   Rank operation require orderby clause...
         *   Spark function has lots of operation....
         *
         *
         * */

        SparkSession sparkSession = SparkSession.builder().appName("UseLead").master("local").getOrCreate();
        Dataset<Row> dataSetEmp = sparkSession.createDataFrame(listEmp, Employee.class);

        WindowSpec windowSpec= Window.orderBy(dataSetEmp.col("name")).partitionBy(dataSetEmp.col("department"));
        dataSetEmp = dataSetEmp.withColumn("Rank", functions.dense_rank().over(windowSpec));
        dataSetEmp.show();







    }


}
