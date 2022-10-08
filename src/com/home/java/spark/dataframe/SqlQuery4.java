package com.home.java.spark.dataframe;

import org.apache.spark.sql.*;
import org.apache.spark.sql.types.StructType;

import java.util.*;
import java.util.function.Consumer;

public class SqlQuery4 {
    public static void main(String[] args) {

        /*
        *
        * Worked on UNION.....
        *
        * */

        SparkSession sparkSession = SparkSession.builder().appName("sqlquery3").master("local").getOrCreate();
        StructType structType = new StructType();
        structType = structType.add("EmpId", "int");
        structType = structType.add("FullName", "string");
        structType = structType.add("ManagerId", "int");
        structType = structType.add("DateOfJoining", "timestamp");
        structType = structType.add("City", "string");
        Dataset<Row> employeeDetails = sparkSession.read().option("timestampformat", "mm/dd/yyyy").schema(structType).csv("/home/neeraj/Desktop/Input/EmployeeDetails");


        structType = new StructType();
        structType = structType.add("EmpId", "int");
        structType = structType.add("Project", "string");
        structType = structType.add("Salary", "int");
        structType = structType.add("Variable", "int");

        Dataset<Row> employeeSalary = sparkSession.read().option("timestampformat", "mm/dd/yyyy").schema(structType).csv("/home/neeraj/Desktop/Input/EmployeeSalary");


        StructType structType1 = employeeDetails.schema();
        StructType structType2 = employeeSalary.schema();

        String field1[] = structType1.fieldNames();
        String field2[] = structType2.fieldNames();

        List<String> list1 = Arrays.asList(field1);
        List<String> list2 = Arrays.asList(field2);
        Set<String> set = new HashSet<String>();
        set.addAll(list1);
        set.addAll(list2);

        Column columns[] = new Column[set.size()];

        int i = 0;
        for (String s : set) {
            if (list1.contains(s)) {
                columns[i] = employeeDetails.col(s);
            } else {
                columns[i] = functions.lit(null).cast(structType2.fields()[structType2.fieldIndex(s)].dataType()).as(s);
            }
            i++;
        }
        employeeDetails = employeeDetails.select(columns);
        columns = new Column[set.size()];
        i=0;
        for (String s : set) {
            if (list2.contains(s)) {
                columns[i] = employeeSalary.col(s);
            } else {
                columns[i] = functions.lit(null).cast(structType1.fields()[structType1.fieldIndex(s)].dataType()).as(s);
            }
            i++;
        }
        employeeSalary = employeeSalary.select(columns);
        employeeDetails=employeeDetails.union(employeeSalary);

        employeeDetails.show();


        // Modify Schema...and perform join...


    }
}
