package com.raulmartinezperez.ubiqumproject;

import java.io.Serializable;

public class Product implements Serializable{

    private String tab= "\u0009";
    private int price;
    private int name;
    private int age;
    private int country;
    private int anything;


    public Product() {
    }

    public Product(int price,int name, int age, int country, int anything) {
        this.price = price;
        this.name = name;
        this.age = age;
        this.country = country;
        this.anything = anything;
    }

    public int getPrice() {

        int length = String.valueOf(price).length();

        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public int getAnything() {
        return anything;
    }

    public void setAnything(int anything) {
        this.anything = anything;
    }

    public int getAge() {

        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return price + "€ " + name;
    }
}
