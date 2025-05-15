package com.example;

public class Circle extends Shape{
    double radius;

    Circle(double radius){
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI*radius*radius;
    }

    @Override
    double perimeter() {
        return Math.PI*radius*2;
    }

    @Override
    void properties(){
        System.out.println("Radius = "+ radius);
    }
}
