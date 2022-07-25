package model;

public class Shape {
    public String name, color;
    public Shape() {
        this.name = "Null";
        this.color = "Null";
    }
    public Shape(String n, String c) {
        this.name = n;
        this.color = c;
    }
    public void display() {
        System.out.println("Shape name is "+ name);
        System.out.println("Shape color is "+ color);
    }
}
