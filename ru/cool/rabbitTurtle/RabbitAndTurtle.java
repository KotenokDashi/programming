package ru.cool.exercise1;

public class RabbitAndTurtle {
    public static void main(String[] args) {
       AnimalThread rabbit = new AnimalThread("Rabbit", 7);
       AnimalThread turtle = new AnimalThread("Turtle", 4);
       rabbit.setOtherThread(turtle);
       turtle.setOtherThread(rabbit);
    }
}
