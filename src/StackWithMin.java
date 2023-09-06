import java.util.Stack;

public class StackWithMin extends Stack<Integer> {
    Stack<Integer> stackMin;

    public StackWithMin() {
        stackMin = new Stack<Integer>();
    }

    public Integer push(Integer value) {
        if (value <= min()) {
            stackMin.push(value);
        }
        super.push(value);
        return value;
    }

    public Integer pop() {
        Integer currentPop = super.pop();
        if (currentPop <= min()) {
            stackMin.pop();
        }
        return currentPop;
    }

    public Integer min() {
        if (stackMin.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        return stackMin.peek();
    }

    public static void main(String args[]) {
        StackWithMin stMin = new StackWithMin();
        System.out.println("" + stMin.min());//MAX
        stMin.push(5);
        System.out.println("" + stMin.min());//5
        stMin.push(4);
        System.out.println("" + stMin.min());//4
        stMin.push(3);
        System.out.println("" + stMin.min());//3
        stMin.push(10);
        System.out.println("" + stMin.min());//3
        stMin.pop();
        stMin.pop();
        System.out.println("" + stMin.min());//4
    }
}