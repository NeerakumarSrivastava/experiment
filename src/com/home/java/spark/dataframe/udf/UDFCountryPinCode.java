package com.home.java.spark.dataframe.udf;

import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.api.java.UDF1;

import java.util.HashMap;

public class UDFCountryPinCode implements UDF1<String, Row> {
    private static HashMap<String,String> countryMap=new HashMap<>();
    static
    {
        countryMap.put("Toronto", "Canada");
        countryMap.put("California", "USA");
        countryMap.put("New Delhi", "India");
    }

    private static HashMap<String,Integer> PinMap=new HashMap<>();
    static
    {
        PinMap.put("Toronto", 15428765);
        PinMap.put("California", 45582322);
        PinMap.put("New Delhi", 45663852);
    }

    @Override
    public Row call(String o) throws Exception {

        String country=countryMap.get(o);
        Integer pincode=PinMap.get(o);

        Object []objects=new Object[2];
        objects[0]=country;
        objects[1]=pincode;
        return RowFactory.create(objects);
    }
}
