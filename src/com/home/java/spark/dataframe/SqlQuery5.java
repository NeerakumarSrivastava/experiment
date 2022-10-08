package com.home.java.spark.dataframe;

import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import com.home.java.spark.dataframe.udf.*;

public class SqlQuery5 {

    /*
    * Add Column using udf....
    * Explode use array to ROW .....
    * */
    public static void main(String[] args) throws AnalysisException {

        StructType structType1 = new StructType();
        structType1 = structType1.add("Country", "string");
        structType1 = structType1.add("PinCode", "int");
        StructField []structFields=structType1.fields();

        SparkSession sparkSession = SparkSession.builder().appName("sqlquery3").master("local").getOrCreate();
        sparkSession.sqlContext().udf().register("UDFCountryPinCode", functions.udf(new UDFCountryPinCode() , DataTypes.createStructType(structFields)));

        StructType structType = new StructType();
        structType = structType.add("EmpId", "int");
        structType = structType.add("FullName", "string");
        structType = structType.add("ManagerId", "int");
        structType = structType.add("DateOfJoining", "timestamp");
        structType = structType.add("City", "string");
        Dataset<Row> employeeDetails = sparkSession.read().option("timestampformat", "mm/dd/yyyy").schema(structType).csv("/home/neeraj/Desktop/Input/EmployeeDetails");

        employeeDetails.createTempView("EmpTable");
        employeeDetails=employeeDetails.sqlContext().sql(" select *,c.country,c.pincode from (select *,UDFCountryPinCode(City) as c from EmpTable)");
        employeeDetails=employeeDetails.drop("c");
        employeeDetails.show();




    }


}
