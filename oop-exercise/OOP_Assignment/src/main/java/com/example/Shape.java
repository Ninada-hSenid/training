package com.example;

abstract class Shape {
    abstract double area();
    abstract double perimeter();
    abstract void properties();

    void display(){
        System.out.println("Shape:"+this.getClass().getSimpleName());
        System.out.println();
        System.out.println("Properties: ");
        properties();
        System.out.println();
        System.out.println("Area = " + area());
        System.out.println("Perimeter = " + perimeter());
    }
}
