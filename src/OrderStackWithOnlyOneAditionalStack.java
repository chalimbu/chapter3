import java.util.Stack;

public class OrderStackWithOnlyOneAditionalStack {
    public static void main(String args[]){
        Stack<Integer> mainStack=new Stack<Integer>();
        mainStack.push(10);
        mainStack.push(9);
        mainStack.push(4);
        mainStack.push(4);
        mainStack=order(mainStack);
        System.out.println("");
    }


    public static Stack<Integer> order(Stack<Integer> mainStack){
        Stack<Integer> auxiliaryStack=new Stack<Integer>();
        while(!mainStack.isEmpty()){
            Integer valueToBeOrdered=mainStack.pop();
            while(!auxiliaryStack.isEmpty()&&!(valueToBeOrdered>=auxiliaryStack.peek())){
                mainStack.push(auxiliaryStack.pop());
            }
            auxiliaryStack.push(valueToBeOrdered);
        }
        while(!auxiliaryStack.isEmpty()){
            mainStack.push(auxiliaryStack.pop());
        }
        return mainStack;
    }
}
