package ru.cool.exercise2;

public class Main {
    /*
        Код не работает как надо. Выводит то правильно, то рандомно. Почему - не знаю.
        Не выводит ничего когда два потока одновременно заканчивают работу. Как это предотвратить я не знаю.
        Пытался join() использовать - не помогало.
     */
    public static void main(String[] args) throws InterruptedException {
        ChickenEggThread chicken = new ChickenEggThread("курица");
        ChickenEggThread egg = new ChickenEggThread("яйцо");
//        while(chicken.t.isAlive() || egg.t.isAlive()) {
//            if (chicken.t.isAlive() && !egg.t.isAlive())
//            {
//                System.out.println("курица победила");
//                break;
//            }
//            else if(egg.t.isAlive() && !chicken.t.isAlive())
//            {
//                System.out.println("яйцо победило");
//                break;
//            }
//        }


    }
}
