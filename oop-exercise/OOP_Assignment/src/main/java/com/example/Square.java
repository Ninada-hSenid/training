package com.example;

public class Square extends Shape{
    private double side;

    Square(double side) {
        this.side = side;
    }

    @Override
    double area() {
        return side*side;
    }

    @Override
    double perimeter() {
        return side*4;
    }
     @Override
    void properties(){
        System.out.println("Length = "+ side);
     }
}
