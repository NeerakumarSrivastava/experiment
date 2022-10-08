package com.home.java.spark.dataframe;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.expressions.WindowSpec;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.StructType;

public class SqlQuery2 {
    public static void main(String[] args) {

        SparkSession sparkSession = SparkSession.builder().master("local").appName("SqlQueryTest1").getOrCreate();

        // Now Schema on DF

        StructType structType = new StructType();
        structType = structType.add("EmpID", "int");
        structType = structType.add("EmpFname", "string");
        structType = structType.add("EmpLname", "string");
        structType = structType.add("Department", "string");
        structType = structType.add("Project", "string");
        structType = structType.add("Address", "string");
        structType = structType.add("DOB", "timestamp");
        structType = structType.add("Gender", "string");

        Dataset<Row> EmployeeInfodf = sparkSession.read().option("timestampformat", "dd/MM/yyyy").schema(structType).csv("/home/neeraj/Desktop/Input/EmployeeInfo.csv");

        structType = new StructType();
        structType = structType.add("EmpID", "int");
        structType = structType.add("EmpPosition", "string");
        structType = structType.add("DateOfJoining", "timestamp");
        structType = structType.add("Salary", "int");


        Dataset<Row> EmployeePosition = sparkSession.read().option("timestampformat", "dd/MM/yyyy").schema(structType).csv("/home/neeraj/Desktop/Input/EmployeePosition.csv");

        EmployeeInfodf.show();
        EmployeePosition.show();
        //Q21. Write a query to find the Nth highest salary from the table without using TOP/limit keyword.
        // WindowSpec windowSpec = Window.orderBy(functions.col("Salary"));
        //   EmployeeInfodf=EmployeeInfodf.join(EmployeePosition, EmployeePosition.col("EmpID").equalTo(EmployeeInfodf.col("EmpID")), "leftouter");

        //  EmployeeInfodf=EmployeeInfodf.withColumn("Rank",functions.dense_rank().over(windowSpec)).where("Rank<3");

        //Q26. Write a query to display the first and the last record from the EmployeeInfo table.
       /* WindowSpec windowSpec = Window.orderBy(functions.col("EmpID"));

        EmployeeInfodf=EmployeeInfodf.withColumn("RowNumber", functions.row_number().over(windowSpec))
                .withColumn("maxRow",functions.max("RowNumber").over()).where("RowNumber=1 or RowNumber= maxRow");
*/
        //Q28. Write a query to retrieve Departments who have less than 2 employees working in it.
     /*   WindowSpec windowSpec = Window.partitionBy(functions.col("Department"));

        EmployeeInfodf = EmployeeInfodf.withColumn("CountEmpDepartmentwise", functions.count("EmpId").over(windowSpec))
                .where("CountEmpDepartmentwise<2");
     */
        //Q29. Write a query to retrieve EmpPostion along with total salaries paid for each of them.

        WindowSpec windowSpec = Window.partitionBy(functions.col("EmpPosition"));



        EmployeeInfodf = EmployeeInfodf.join(EmployeePosition,EmployeeInfodf.col("EmpID").equalTo(EmployeePosition.col("EmpID")),"inner")
                .groupBy("EmpPosition").sum("Salary");




        EmployeeInfodf.show();

    }

}
