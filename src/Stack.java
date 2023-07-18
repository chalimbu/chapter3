import java.util.EmptyStackException;

public class Stack<T>{

    public static void main(String args[]){
        Stack<Integer> myStack=new Stack<>();
        myStack.push(1);
        myStack.push(2);

    }

    private static class StackElement<T>{
        public T data;
        public StackElement next;
        public StackElement(T data){
            this.data=data;
        }
    }

    private StackElement<T> top=null;

    public void push(T data){
        StackElement se=new StackElement(data);
        se.next=top;
        top=se;
    }
    public T pop(){
        if(top==null){
            throw new RuntimeException("empty stack");
        }
        T data= top.data;
        top=top.next;
        return data;
    }
    public T peek(){
        if(top==null){
            throw new RuntimeException("Empty stack");
        }
        return top.data;
    }

    public boolean isEmpty(){
        return top==null;
    }

}