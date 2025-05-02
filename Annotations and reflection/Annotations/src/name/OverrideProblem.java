package name;
class Animal {
    public void makeSound() {
        System.out.println("Some generic animal sound");
    }
}
class Dog extends Animal {  
  @Override
    public void makeSound() {
        System.out.println("Woof! Woof!");
    }
}public class OverrideProblem {
    public static void main(String[] args) {
        Dog myDog = new Dog();         
myDog.makeSound();        }
}

