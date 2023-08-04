public class StackFromArray{

    //ejercisio 3
    public static void main(String args[]){
        StackFromArray sfa=new StackFromArray(15);
        System.out.println("");
    }

    int []internalArray;
    int stackMaxLenght;
    int []stackPointer;
    public StackFromArray(int arraySize){
        internalArray=new int[arraySize];
        stackMaxLenght=arraySize/3;
        stackPointer=new int[3];
    }

    public void push(int stackNumber,int element){
        checkStackNumber(stackNumber);
        int newStackLength=stackPointer[stackNumber]+1;
        if(newStackLength>=stackMaxLenght){
            throw new RuntimeException("out of memory");
        }
        stackPointer[stackNumber]+=1;
        internalArray[stackMaxLenght*stackNumber+stackPointer[stackNumber]]=element;
    }

    public int pop(int stackNumber){
        checkStackNumber(stackNumber);
        int position=stackNumber*stackMaxLenght+stackPointer[stackNumber];
        if(stackPointer[stackNumber]==0&&internalArray[position]==0){
            throw new RuntimeException("stack is empty");
        }
        int currenPeek= internalArray[position];
        internalArray[position]=0;
        stackPointer[stackNumber]-=1;
        return currenPeek;
    }

    public boolean isEmpty(int stackNumber){
        checkStackNumber(stackNumber);
        int position=stackNumber*stackMaxLenght+stackPointer[stackNumber];
        if(stackPointer[stackNumber]==0&&internalArray[position]==0){
            return true;
        }
        return false;
    }

    public int peek(int stackNumber){
        checkStackNumber(stackNumber);
        int position=stackNumber*stackMaxLenght+stackPointer[stackNumber];
        if(stackPointer[stackNumber]==0&&internalArray[position]==0){
            throw new RuntimeException("stack is empty");
        }
        return internalArray[position];
    }

    private void checkStackNumber(int stackNumber){
        if(stackNumber<0||stackNumber>3){ throw new RuntimeException("out of bound stack");}
    }

}
