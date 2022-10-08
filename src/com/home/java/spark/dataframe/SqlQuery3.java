package com.home.java.spark.dataframe;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.StructType;

public class SqlQuery3 {
    public static void main(String[] args) {
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

        employeeDetails.show();
        employeeSalary.show();


        // Ques.1. Write an SQL query to fetch the EmpId and FullName of all the employees working under Manager with id – ‘986’.

        Dataset<Row> q1 = employeeDetails.where("ManagerId=986");
        q1.show();

        //Ques.2. Write an SQL query to fetch the different projects available from the EmployeeSalary table.

        Dataset<Row> q2 = employeeSalary.select(employeeSalary.col("Project")).distinct();
        q2.show();

        //Ques.3. Write an SQL query to fetch the count of employees working in project ‘P1’.

        Dataset<Row> q3 = employeeDetails
                .join(employeeSalary, employeeDetails.col("EmpId").equalTo(employeeSalary.col("EmpId")), "inner")
                .where("Project='P1'");
        q3.show();


        //Ques.4. Write an SQL query to find the maximum, minimum, and average salary of the employees.

        Dataset<Row> q4 = employeeDetails.join(employeeSalary, employeeDetails.col("EmpId").equalTo(employeeSalary.col("EmpId")), "inner")
                .withColumn("MaxSalary", functions.max("Salary").over()).withColumn("MinSalary", functions.min("Salary").over())
                .withColumn("AvgSalary", functions.avg("Salary").over());
        q4.show();

        //Ques.5. Write an SQL query to find the employee id whose salary lies in the range of 9000 and 15000.

        Dataset<Row> q5 = employeeSalary.where("Salary>9000 and Salary<15000").select("EmpId");
        q5.show();

        //Ques.6. Write an SQL query to fetch those employees who live in Toronto and work under manager with ManagerId – 321.

        Dataset<Row> q6 = employeeDetails.where("City='Toronto' and ManagerId=321").select("EmpId");
        q6.show();

        //Ques.9. Write an SQL query to display the total salary of each employee adding the Salary with Variable value.

        Dataset<Row> q7 = employeeSalary.withColumn("TotalSalary", employeeSalary.col("Salary").plus(employeeSalary.col("Variable")));
        q7.show();

        //Ques.10. Write an SQL query to fetch the employees whose name begins with any two characters, followed by a text “hn” and ending with any sequence of characters.

        Dataset<Row> q8 = employeeDetails.where("FullName like '__hn%'");
        q8.show();

        //Ques.11. Write an SQL query to fetch all the EmpIds which are present in either of the tables – ‘EmployeeDetails’ and ‘EmployeeSalary’.
        Dataset<Row> q9 = employeeDetails.select("EmpId").union(employeeSalary.select("EmpId")).distinct();
        q9.show();



       // Ques.12. Write an SQL query to fetch common records between two tables.
        Dataset<Row> q10 = employeeDetails.select("EmpId").intersect(employeeSalary.select("EmpId"));
        q10.show();

        //Ques.13. Write an SQL query to fetch records that are present in one table but not in another table.

        Dataset<Row> q11 = employeeDetails.select("EmpId").orderBy("EmpId").join(employeeSalary.select("EmpId").orderBy("EmpId"),employeeDetails.col("EmpId").equalTo(employeeSalary.col("EmpId")),"leftanti");
        q11.show();

        //Ques.14. Write an SQL query to fetch the EmpIds that are present in both the tables –   ‘EmployeeDetails’ and ‘EmployeeSalary.

        Dataset<Row> q12 = employeeDetails.select("EmpId").orderBy("EmpId").join(employeeSalary.select("EmpId").orderBy("EmpId"),employeeDetails.col("EmpId").equalTo(employeeSalary.col("EmpId")),"leftanti");
        q12.show();


    }


}
