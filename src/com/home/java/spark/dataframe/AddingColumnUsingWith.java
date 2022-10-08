package com.home.java.spark.dataframe;

import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StringType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddingColumnUsingWith {

    public static void main(String[] args) {

        /*
        * Using WithColumn method...
        *
        * */

        List<Employee> listEmp = new ArrayList<>();
        Employee employee1 = new Employee(1, "Neeraj");
        Employee employee2 = new Employee(2, "Mrinalini");
        Employee employee3 = new Employee(3, "Pankaj");
        listEmp.add(employee1);
        listEmp.add(employee2);
        listEmp.add(employee3);


        List<Department> listDep = new ArrayList<>();
        listDep.add(new Department(2, "rcem"));
        listDep.add(new Department(3, "bdsp"));

        SparkSession sparkSession = SparkSession.builder().appName("AddingColumn").master("local").getOrCreate();
        Dataset<Row> dataSetEmp = sparkSession.createDataFrame(listEmp, Employee.class);
        String[] columnsEmp = dataSetEmp.columns();
        List<String> stringListEmp = Arrays.asList(columnsEmp);



        Dataset<Row> dataSetDep = sparkSession.createDataFrame(listDep, Department.class);
        String[] columnsDep = dataSetDep.columns();

        List<String> merge = new ArrayList<>();
        Arrays.stream(columnsEmp).forEach(p -> merge.add(p));
        Arrays.stream(columnsDep).forEach(p -> merge.add(p));
        System.out.println(merge);

        for (String p : merge) {
         if(!stringListEmp.contains(p))
         {
             dataSetEmp=dataSetEmp.withColumn(p,functions.lit(null).cast(DataTypes.StringType).as(p));
         }
        }
        dataSetEmp.show();



    }


}
