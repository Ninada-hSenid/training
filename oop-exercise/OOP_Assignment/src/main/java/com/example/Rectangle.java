package com.example;

public class Rectangle extends Shape{
    double length;
    double width;

    Rectangle(double length, double width){
        this.length = length;
        this.width = width;
    }

    @Override
    double area() {
        return length*width;
    }

    @Override
    double perimeter() {
        return (length*width)*2;
    }

    @Override
    void properties(){
        System.out.println("Length = "+ length);
        System.out.println("Width = "+width);
    }
}
