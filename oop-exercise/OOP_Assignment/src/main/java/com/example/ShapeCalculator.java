package com.example;

import java.util.Scanner;

public class ShapeCalculator {

    public static void calculateShapeProperies(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a shape:");
        System.out.println("1. Square");
        System.out.println("2. Rectangle");
        System.out.println("3. Circle");

        System.out.println("Enter your choice :");
        int choice = scanner.nextInt();

        Shape shape = null;

        switch (choice){
            case 1:
                System.out.println("Enter side length : ");
                double side = scanner.nextDouble();
                shape = new Square(side);
                break;
            case 2:
                System.out.println("Enter side length : ");
                double length = scanner.nextDouble();
                System.out.println("Enter side width : ");
                double width = scanner.nextDouble();
                shape = new Rectangle(length, width);
                break;
            case 3:
                System.out.println("Enter radius : ");
                double radius = scanner.nextDouble();
                shape = new Circle(radius);
                break;
            default:
                System.out.println("Enter valied input");
                return;
        }

        shape.display();
    }

    public static void main(String[] args){
        calculateShapeProperies();
    }
}
