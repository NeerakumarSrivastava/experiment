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

public class UseLead {
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
        * When we apply lead and lag function we have to perform operation over windowspec object
        * Window SPec provide following feature like
        * order by
        * partition by
        * rank
        * range operation ..... it would be for aggregation operation... TODO
        * rows between .... TODO 
        *
        * */

        SparkSession sparkSession = SparkSession.builder().appName("UseLead").master("local").getOrCreate();
        Dataset<Row> dataSetEmp = sparkSession.createDataFrame(listEmp, Employee.class);
        WindowSpec windowSpec=Window.partitionBy(dataSetEmp.col("department")).orderBy(dataSetEmp.col("id"));
        dataSetEmp = dataSetEmp.withColumn("LeadId", functions.lag(dataSetEmp.col("name"), 1).over(windowSpec));
        dataSetEmp.show();


    }
}
