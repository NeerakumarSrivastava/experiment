package com.home.java.spark;

import com.home.java.spark.dataframe.udf.UDFCountryPinCode;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
/*
* UDF Register in spark session...
*
* */
public class SqlQuery6 {

    public static void main(String[] args) {
        StructType structType1 = new StructType();
        structType1 = structType1.add("Country", "string");
        structType1 = structType1.add("PinCode", "int");
        StructField[]structFields=structType1.fields();


        SparkSession sparkSession= SparkSession.builder().master("local").appName("Sqlquery6").getOrCreate();
        StructType structType = new StructType();
        structType = structType.add("EmpId", "int");
        structType = structType.add("FullName", "string");
        structType = structType.add("ManagerId", "int");
        structType = structType.add("DateOfJoining", "timestamp");
        structType = structType.add("City", "string");

        Dataset<Row> employeeDetails = sparkSession.read().option("timestampformat", "mm/dd/yyyy")
                .schema(structType).csv("/home/neeraj/Desktop/Input/EmployeeDetails");

        sparkSession.udf().register("countryAndPincode", new UDFCountryPinCode(), DataTypes.createStructType(structFields));

        Column []column=new Column[1];
        column[0]=employeeDetails.col("City");

        employeeDetails=employeeDetails.withColumn("countryAndPincode", functions.callUDF("countryAndPincode", column));
        employeeDetails=employeeDetails.withColumn("country", employeeDetails.col("countryAndPincode").getField("Country"))
                .withColumn("PinCode", employeeDetails.col("countryAndPincode").getField("PinCode"));

        employeeDetails.show();







    }


}
