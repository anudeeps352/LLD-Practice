package SimpleFactoryPattern;
public class CarFactory {
    Car getCar (String cartype){
        switch (cartype) {
            case "Suv":
                return new Suv();
                
            
            case "Coupe":
                return new Coupe();
                
            default:
                return null;
        }
    }
}
