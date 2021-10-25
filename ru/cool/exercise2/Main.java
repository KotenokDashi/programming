package ru.cool.exercise2;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        ChickenEggThread chicken = new ChickenEggThread("курица");
        ChickenEggThread egg = new ChickenEggThread("яйцо");

        
        while(chicken.t.isAlive() || egg.t.isAlive()) {
            if (chicken.t.isAlive() && !egg.t.isAlive())
            {
                chicken.t.join();
                System.out.println("курица победила");
                break;
            }
            else if(egg.t.isAlive() && !chicken.t.isAlive())
            {
                egg.t.join();
                System.out.println("яйцо победило");
                break;
            }
        }


    }
}
