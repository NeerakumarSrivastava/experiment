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

public class ExplodeAndSplitFunction {

    public static void main(String[] args) {

        List<Employee> listEmp = new ArrayList<>();
        List<String> contacts = new ArrayList<>();
        contacts.add("fsfdsf");
        contacts.add("dfsfs");
        Employee employee1 = new Employee(1, "Neeraj Kumar");
        employee1.setDepartment("rcem");
        employee1.setContactNumbers(contacts);
        Employee employee2 = new Employee(2, "Mrinalini Sinha");
        employee2.setDepartment("rcem");
        employee2.setContactNumbers(contacts);
        Employee employee3 = new Employee(3, "Pankaj Srivastava");
        employee3.setDepartment("college");
        employee3.setContactNumbers(contacts);
        Employee employee4 = new Employee(4, "Prabhat Srivastava");
        employee4.setDepartment("college");
        employee4.setContactNumbers(contacts);
        Employee employee5 = new Employee(5, "Preeti Srivastava");
        employee5.setDepartment("college");
        employee5.setContactNumbers(contacts);
        listEmp.add(employee1);
        listEmp.add(employee2);
        listEmp.add(employee3);
        listEmp.add(employee4);
        listEmp.add(employee5);

        /*
         * Learning :
         *
         *   Explode operation ...
         *   Explode convert one row to more rows....
         *   Split convert one column to more column..
         *   And case sensivity is not there ...
         *
         * */

        SparkSession sparkSession = SparkSession.builder().appName("UseLead").master("local").getOrCreate();
        Dataset<Row> dataSetEmp = sparkSession.createDataFrame(listEmp, Employee.class);

        WindowSpec windowSpec= Window.orderBy(dataSetEmp.col("department"));
        dataSetEmp = dataSetEmp.withColumn("Rank", functions.rank().over(windowSpec));
        dataSetEmp.show();
         dataSetEmp=dataSetEmp.withColumn("First Number",functions.explode(dataSetEmp.col("contactNumbers")));

        dataSetEmp=dataSetEmp.withColumn("First Name",functions.split(dataSetEmp.col("name")," ").getItem(0));
        dataSetEmp=dataSetEmp.withColumn("Surname",functions.split(dataSetEmp.col("name")," ").getItem(1));

         dataSetEmp.show();








    }

}
