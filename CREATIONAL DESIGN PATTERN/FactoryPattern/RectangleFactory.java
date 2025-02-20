package FactoryPattern;

public class RectangleFactory extends ShapeFactory{
    public Shape createShape(){
        return new Rectangle();
    }
}
