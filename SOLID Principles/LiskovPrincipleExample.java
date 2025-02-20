// SOLID Princples Practice

//=================================================================

// S - Single Responsibility


// BAD 

// class Employee {
//     public void calculateSalary() { }
//     public void generateReport() { }
//     public void saveToDatabase() { }
// }

//Ideal

class Employee {
    private String name;
    public void work() { }
}

class SalaryCalculator {
    public void calculateSalary(Employee emp) { }
}

class ReportGenerator {
    public void generateReport(Employee emp) { }
}

class EmployeeRepository {
    public void saveToDatabase(Employee emp) { }
}

//=====================================================================

// Open to Extenion Closed to Modification

//BAD - Modifying PaymentProcessor every time a new payment method is added.

// class PaymentProcessor {
//     public void pay(String method) {
//         if (method.equals("CreditCard")) { /* Process credit card */ }
//         else if (method.equals("PayPal")) { /* Process PayPal */ }
//         else { throw new RuntimeException("Unsupported payment method"); }
//     }
// }

//GOOD

interface PaymentMethod {
    void pay();
}

class CreditCardPayment implements PaymentMethod {
    public void pay() { System.out.println("Paid with Credit Card"); }
}

class PayPalPayment implements PaymentMethod {
    public void pay() { System.out.println("Paid with PayPal"); }
}

class PaymentProcessor {
    public void processPayment(PaymentMethod method) {
        method.pay();
    }
}

//=====================================================================

//Liskov Substitution Method

// If B is a subclass of A then an object of A should be replacable with Object of B

//BAD

// class Bird {
//     public void fly() {
//         System.out.println("This bird is flying!");
//     }
// }

// class Sparrow extends Bird {
//     // Sparrow can fly, so no issue here
// }

// class Penguin extends Bird {
//     @Override
//     public void fly() {
//         throw new UnsupportedOperationException("Penguins cannot fly!");
//     }
// }

// public class LiskovViolationExample {
//     public static void main(String[] args) {
//         Bird myBird = new Sparrow();
//         myBird.fly();  // Works fine ✅

//         myBird = new Penguin();
//         myBird.fly();  // ❌ Throws exception: Violates LSP
//     }
// }

//GOOD

abstract class Bird {
    public abstract void eat();
}

interface Flyable {
    void fly();
}

// Sparrow is a Bird and can Fly
class Sparrow extends Bird implements Flyable {
    @Override
    public void eat() {
        System.out.println("Sparrow is eating.");
    }

    @Override
    public void fly() {
        System.out.println("Sparrow is flying.");
    }
}

// Penguin is a Bird but CANNOT Fly
class Penguin extends Bird {
    @Override
    public void eat() {
        System.out.println("Penguin is eating.");
    }
}

public class LiskovPrincipleExample {
    public static void main(String[] args) {
        Bird sparrow = new Sparrow();
        Bird penguin = new Penguin();

        sparrow.eat();  // Works ✅
        penguin.eat();  // Works ✅

        Flyable flyingBird = new Sparrow();
        flyingBird.fly();  // Works ✅

        
    }
}

//======================================================
// I - Interface Segmented Principle

// Client shouldnt implement unnecessary functions they do not need

//BAD ❌ Violates ISP - Too many unrelated methods in one interface


// interface Worker {
//     void work();
//     void eat();
//     void sleep();
// }

// // Robot does not need eat() or sleep(), but is forced to implement them
// class Robot implements Worker {
//     @Override
//     public void work() {
//         System.out.println("Robot is working.");
//     }

//     @Override
//     public void eat() {
//         throw new UnsupportedOperationException("Robot doesn't eat!"); // 🚨 Bad Design
//     }

//     @Override
//     public void sleep() {
//         throw new UnsupportedOperationException("Robot doesn't sleep!"); // 🚨 Bad Design
//     }
// }

// // HumanWorker needs all methods, so it's fine
// class HumanWorker implements Worker {
//     @Override
//     public void work() {
//         System.out.println("Human is working.");
//     }

//     @Override
//     public void eat() {
//         System.out.println("Human is eating.");
//     }

//     @Override
//     public void sleep() {
//         System.out.println("Human is sleeping.");
//     }
// }

//GOOD 

// Now, each interface has a specific responsibility
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

interface Sleepable {
    void sleep();
}

// Robot only implements Workable, so it's not forced to implement unnecessary methods
class Robot implements Workable {
    @Override
    public void work() {
        System.out.println("Robot is working.");
    }
}

// Human needs to work, eat, and sleep, so it implements all three interfaces
class HumanWorker implements Workable, Eatable, Sleepable {
    @Override
    public void work() {
        System.out.println("Human is working.");
    }

    @Override
    public void eat() {
        System.out.println("Human is eating.");
    }

    @Override
    public void sleep() {
        System.out.println("Human is sleeping.");
    }
}


//===============================================

// D - Dependency Inversion Principle

// classes should depend on interfaces rather than concrete classes

//BAD

// Low-level modules (specific implementations)

// class WiredKeyboard {
//     void type() {
//         System.out.println("Typing on Wired Keyboard...");
//     }
// }

// class WiredMouse {
//     void click() {
//         System.out.println("Clicking with Wired Mouse...");
//     }
// }

// // High-level module (directly depends on low-level modules)

// class MacBook {
//     private WiredKeyboard keyboard;
//     private WiredMouse mouse;

//     public MacBook() {
//         this.keyboard = new WiredKeyboard(); // ❌ Direct dependency
//         this.mouse = new WiredMouse();       // ❌ Direct dependency
//     }

//     void useMac() {
//         keyboard.type();
//         mouse.click();
//     }
// }

// public class DIPViolationExample {
//     public static void main(String[] args) {
//         MacBook mac = new MacBook();
//         mac.useMac();
//     }
// }

//GOOD

// Abstraction (Interfaces)
interface Keyboard {
    void type();
}

interface Mouse {
    void click();
}

// Low-level modules (Implementations)
class WiredKeyboard implements Keyboard {
    public void type() {
        System.out.println("Typing on Wired Keyboard...");
    }
}

class WirelessKeyboard implements Keyboard {
    public void type() {
        System.out.println("Typing on Wireless Keyboard...");
    }
}

class WiredMouse implements Mouse {
    public void click() {
        System.out.println("Clicking with Wired Mouse...");
    }
}

class WirelessMouse implements Mouse {
    public void click() {
        System.out.println("Clicking with Wireless Mouse...");
    }
}

// High-level module (Depends on abstractions, not concrete classes)
class MacBook {
    private Keyboard keyboard;
    private Mouse mouse;

    // Constructor Injection (Dependency Injection)
    public MacBook(Keyboard keyboard, Mouse mouse) {
        this.keyboard = keyboard;
        this.mouse = mouse;
    }

    void useMac() {
        keyboard.type();
        mouse.click();
    }
}

public class DIPExample {
    public static void main(String[] args) {
        Keyboard myKeyboard = new WirelessKeyboard();  // Easily switch to WiredKeyboard
        Mouse myMouse = new WirelessMouse();          // Easily switch to WiredMouse
        
        MacBook mac = new MacBook(myKeyboard, myMouse);
        mac.useMac();
    }
}
