package com.home.java.spark.dataframe;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.expressions.WindowSpec;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.functions;

public class SqlQueryTest1 {
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

        //Write a query to fetch all employees who also hold the managerial position.
        Dataset<Row> query1 = EmployeeInfodf.join(EmployeePosition, "EmpID").where(functions.col("EmpPosition").isin("Manager"));

        //Write a query to fetch the department-wise count of employees sorted by department’s count in ascending order.
        WindowSpec windowSpec = Window.orderBy(functions.col("EmpID").desc());

        // Dataset<Row> query2=EmployeeInfodf.withColumn("CountDepartmentWise", functions.count("EmpID").over(windowSpec)).orderBy("CountDepartmentWise");
      //  Dataset<Row> query2 = EmployeeInfodf.groupBy("Department").count().orderBy("count");

        //Q18. Write a query to calculate the even and odd records from a table...

      //  EmployeeInfodf = EmployeeInfodf.withColumn("rownum", functions.row_number().over(windowSpec));
       // Dataset<Row> query2Even =EmployeeInfodf.where("rownum%2=0");
      //  Dataset<Row> query2Odd =EmployeeInfodf.where("rownum%2!=0");

        //Q19. Write a SQL query to retrieve employee details from EmployeeInfo table who have a date of joining in the EmployeePosition table.
        WindowSpec windowSpec1 = Window.partitionBy("EmpID").orderBy(functions.col("EmpID").desc());
      //  EmployeeInfodf= EmployeeInfodf.join(EmployeePosition,"EmpID")
        //        .withColumn("rownum", functions.row_number().over(windowSpec1)).where("rownum=1").drop("rownum");


       // Q20. Write a query to retrieve two minimum and maximum salaries from the EmployeePosition table.

        EmployeeInfodf=  EmployeeInfodf.join(EmployeePosition,"EmpID")
                .withColumn("rownum", functions.row_number().over(windowSpec1)).where("rownum=1").drop("rownum")
                .withColumn("RANK", functions.dense_rank().over(windowSpec));

        Dataset<Row> top2=EmployeeInfodf.where("RANK<3");
        Dataset<Row> bottom2=EmployeeInfodf.withColumn("maxValue", functions.max("RANK").over()).where("RANK>(maxValue-2)").drop("maxValue");


        bottom2.show();
        top2.show();
     //   query2Even.show();

    }

}
