package com.home.java.basics;

import java.io.Serializable;

public class Data implements Serializable
{
    private static final long serialVersionUID = 1L;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    private int a;
    private String b;


    public Data(int a,String b)
    {
        this.a=a;
        this.b=b;
    }

    @Override
    public String toString() {
        return "Data{" +
                "a=" + a +
                ", b='" + b + '\'' +
                '}';
    }
}


