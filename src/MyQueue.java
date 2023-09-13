import java.util.Stack;

public class MyQueue{
    Stack<Integer> addingStack;
    Stack<Integer> removingStack;
    MyQueueMode mode;
    private enum MyQueueMode{
        ADDING,
        REMOVING
    }
    public MyQueue(){
        addingStack=new Stack<Integer>();
        removingStack=new Stack<Integer>();
        mode=MyQueueMode.ADDING;
    }
    public void add(Integer value){
        if(mode==MyQueueMode.REMOVING){
            changeToAdding();
        }
        addingStack.push(value);
    }

    private void changeToAdding(){
        while(!removingStack.isEmpty()){
            addingStack.push(removingStack.pop());
        }
        mode=MyQueueMode.ADDING;
    }
    private void changeToRemoving(){
        while(!addingStack.isEmpty()){
            removingStack.push(addingStack.pop());
        }
        mode=MyQueueMode.REMOVING;
    }

    public Integer remove(){
        if(mode==MyQueueMode.ADDING){
            changeToRemoving();
        }
        return removingStack.pop();
    }

    public static void main(String args[]){
        MyQueue mq=new MyQueue();
        mq.add(1);
        mq.add(2);
        mq.add(3);
        System.out.println(mq.remove());//1
        System.out.println(mq.remove());//2
        mq.add(4);
        mq.add(5);
        System.out.println(mq.remove());//3
        System.out.println(mq.remove());//4
    }
}
