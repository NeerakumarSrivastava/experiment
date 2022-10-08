package com.home.java.spark.dataframe;

public class Department {
    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    int departmentid;

    public Department(int departmentid, String departmentName) {
        this.departmentid = departmentid;
        this.departmentName = departmentName;
    }

    String departmentName;

}
