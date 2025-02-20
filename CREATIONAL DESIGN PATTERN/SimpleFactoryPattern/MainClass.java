package SimpleFactoryPattern;

public class MainClass {

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.getShape("CIRCLE");
        CarFactory carFactory = new CarFactory();
        Car car = carFactory.getCar("Suv");
        shape.draw();
        car.start();
    }
    
}
