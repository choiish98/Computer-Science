public class Stack {
    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);
        int removed = stack.pop();
        int topData = stack.peek();
        System.out.printf("pop: %d\n", removed);
        System.out.printf("peek: %d\n", topData);
        System.out.println(stack);
    }
  }
  class MyStack {
    private int[] array;
    private int capacity;
    private int top;
    public MyStack() {
      this.array = new int[5];
      this.capacity = 5;
      this.top = -1;
    }
    public int pop() {
        int data = array[top--];
        return data;
    }
      public int peek() {
        int data = array[top];
        return data;
    }    
    public void push(int data) {
        array[++top] = data;
    }
    @Override
    public String toString() {
      StringBuffer sbuf = new StringBuffer();
      for (int i = capacity - 1; i >= 0; i--) {
        Integer data = (i <= top) ? array[i] : null;
        sbuf.append(String.format("| %4s |%s\n", data, (i == top) ? " <- top " : ""));
      }
      return sbuf.append("--------\n").toString();
    }
  }