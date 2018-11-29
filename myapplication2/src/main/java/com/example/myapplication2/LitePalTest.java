package com.example.myapplication2;

import org.litepal.crud.DataSupport;

public class LitePalTest extends DataSupport {
    private  String name;
    private  Double   price;
    public  void setName(String s){
        name   =   s;
    }
    public  void setPrice(double f){
        price =    f;
    }
    public  String  getName(){
            return name;

    }
    public  Double getPrice(){
            return price;

    }
}
