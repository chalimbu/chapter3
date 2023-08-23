public class MultiStack {
    private class StackInfo{
        public int start,size,capacity;
        public StackInfo(int start,int capacity) {
            this.start = start;
            this.capacity = capacity;
        }

        /* Check if an index on the full array is whithin the stack boundaries.
        The stack can wrap around to the start of the array
         */
        public boolean isWithinStackCapacity(int index){
            /* If outside of bounds of array, return false */
            if(index<0 || index >= values.length){
                return false;
            }
            /* If index wraps around, adjust it*/
            int contingousIndex= index < start ? index + values.length: index;
            int end = start+ capacity;
            return start <= contingousIndex && contingousIndex < end;
        }

        public int lastCapacityIndex(){
            return adjustIndex(start+capacity-1);
        }

        public int lastElementIndex(){
            return adjustIndex(start+size-1);
        }

        public boolean isFull(){return size==capacity;}
        public boolean isEmpty(){return size==0;}

    }

    private StackInfo[] info;
    private int[] values;

    public MultiStack(int numberOffStacks, int defaultSize){
        /* Create metadata for all the stacks */
        info = new  StackInfo[numberOffStacks];
        for(int i=0; i<numberOffStacks;i++){
            info[i]= new StackInfo(defaultSize*i,defaultSize);
        }
        values = new int[numberOffStacks*defaultSize];
    }

    /* Adjust index to be within the range of  0 -> length -1 */
    private int adjustIndex(int index){
        /* Javas's mod operator can return neg values. For example, (-11%5) will return -1, not 4(probably 1)
        (since we're wrapping around the index)
         */
        int max=values.length;
        /*el primer modulo max, me da que este entre negativos del tamaño del index, al sumarle el tamaño real
        * ya lo tengo positivo y solo me queda un modulo max para que quede dentro del tamaño positivo*/
        return ((index%max)+max)%max;
    }

    /* push value onto stack num, shifting/expanding stacks as necesarry. Throws exception if all stack are full*/
    public void push(int stackNum, int value) {
        if(allStacksAreFull()){
            throw new RuntimeException("Full stacks");
        }
        /* If this stack is full, expand it*/
        StackInfo stack=info[stackNum];
        if(stack.isFull()){
            expand(stackNum);
        }
        /*Find hte index of top element in the array +1, and increment the stack pointer*/
        stack.size++;
        values[stack.lastElementIndex()]=value;
    }

    /*return value from stack*/
    public int pop(int stackNum){
        if (stackNum<0 || stackNum>= info.length){
            throw new RuntimeException("Stack out of bounds");
        }
        StackInfo stack=info[stackNum];
        if(stack.isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
        int value=values[stack.lastElementIndex()];
        values[stack.lastElementIndex()]=0;
        stack.size--;
        return value;

    }

    public int peek(int stackNum){
        StackInfo stack = info[stackNum];
        return values[stack.lastElementIndex()];
    }

    public boolean isEmpty(int stackNum){
        StackInfo stack = info[stackNum];
        return stack.isEmpty();
    }


    /*Expand stack by shifting over other stacks*/
    private void expand(int stackNum){
        shift((stackNum+1)%info.length);
        info[stackNum].capacity++;
    }

    /*Shift intems in stack over by one element. If we have available capacity, then we'll end up shrinking the stack
    * by one element. If we don't have available capacity, then we'll need to shift the next stack over
    * too */
    private void shift(int stackNum){
        System.out.println("//// Shifting"+ stackNum);
        StackInfo stack=info[stackNum];

        if(stack.size>= stack.capacity){
            shift((stackNum+1)%info.length);
            stack.capacity++;// claim index tha the next stack lost
        }

        /* Shift all elements in stack over by one*/
        int index= stack.lastCapacityIndex();
        while(stack.isWithinStackCapacity(index)){
            values[index] = values[previousIndex(index)];
            index=previousIndex(index);
        }

        /*Adjust stack data*/
        values[stack.start]=0;//clear item
        stack.start=nextIndex(stack.start);//adjust start
        stack.capacity--;//and capacity
    }


    private int previousIndex(int index){
        return adjustIndex(index-1);
    }

    private int nextIndex(int index){
        return adjustIndex(index+1);
    }

    public boolean allStacksAreFull(){
        return numberOfElements() == values.length;
    }

    public int numberOfElements(){
        int size=0;
        for( StackInfo sd: info){
            size+=sd.size;
        }
        return size;
    }

    public static void main(String args[]){
        MultiStack ms=new MultiStack(3,2);
        ms.push(0,1);
        ms.push(0,2);
        System.out.println("push 1 and 2, pop"+ms.pop(0));
        System.out.println("peek "+ms.peek(0));
        ms.push(1,3);
        ms.push(1,4);
        ms.push(1,5);
        System.out.println("peek after pushing 3,4,5 to 1 "+ms.peek(1));
        System.out.println("is empty for stack that was not touched "+ms.isEmpty(2));
        /*
        * expected output
        * push 1 and 2, pop2
        peek 1
        //// Shifting2
        peek after pushing 3,4,5 to 1 5
        is empty for stack that was not touched true
        * */
    }
}
