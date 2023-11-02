import java.util.LinkedList;
import java.util.Queue;

public class AnimalShelter{

    public abstract class Animal{
        String name;
        public Animal(String name){
            this.name=name;
        }
    }
    public class Cat extends Animal{
        public Cat(String name){
            super(name);
        }
    }
    public class Dog extends Animal{
        public Dog(String name){
            super(name);
        }
    }
    public class WrapperCounter<A>{
        public A wrapping;
        public int counter;
        public WrapperCounter(A wrapping,int counter){
            this.wrapping=wrapping;
            this.counter=counter;
        }
    }

    public Cat createCat(String name){
        return new Cat(name);
    }
    public Dog createDog(String name){
        return new Dog(name);
    }


    Queue<WrapperCounter<Cat>> catsQueue=new LinkedList<WrapperCounter<Cat>>();
    Queue<WrapperCounter<Dog>> dogsQueue=new LinkedList<WrapperCounter<Dog>>();
    int counter=0;


    public void enqueue(Animal animal){
        if(animal instanceof Cat){
            catsQueue.add(new WrapperCounter((Cat)animal,counter));
        }else if (animal instanceof Dog){
            dogsQueue.add(new WrapperCounter((Dog)animal,counter));
        }
        counter++;
    }
    public Animal dequeueAny(){
        if(lowestValueOfQueue(catsQueue)<lowestValueOfQueue(dogsQueue)){
            return catsQueue.remove().wrapping;
        }else{
            return dogsQueue.remove().wrapping;
        }
    }
    private <T> int lowestValueOfQueue(Queue<WrapperCounter<T>> queue){
        if(queue.isEmpty()){
            return Integer.MAX_VALUE;
        }else{
            return queue.peek().counter;
        }
    }

    public Dog dequeueDog(){
        return dogsQueue.remove().wrapping;
    }
    public Cat dequeueCat(){
        return catsQueue.remove().wrapping;
    }

    public static void main(String args[]){
        AnimalShelter as=new AnimalShelter();
        as.enqueue(as.createDog("dog 1"));
        as.enqueue(as.createCat("cat 1"));
        as.enqueue(as.createDog("dog 2"));
        as.enqueue(as.createCat("cat 2"));
        as.enqueue(as.createDog("dog 3"));
        as.enqueue(as.createDog("dog 4"));
        as.enqueue(as.createCat("cat 3"));

        System.out.println(as.dequeueAny().name);
        System.out.println(as.dequeueDog().name);
        System.out.println(as.dequeueDog().name);
        System.out.println(as.dequeueCat().name);
    }
}

