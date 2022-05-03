import org.w3c.dom.css.CSS2Properties;

public class Stack_array {
    public static void main(String[] args) {
        MyStack_array stack = new MyStack_array();
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
  class MyStack_array {
    private int[] array;
    private int capacity;
    private int top;
    public MyStack_array() {
      this.array = new int[5];
      this.capacity = 5;
      this.top = -1;
    }
    public int pop() {
      if (capacity == 0) System.out.println("null");
        int data = array[top--];
        return data;
    }
      public int peek() {
        if (capacity == 0) System.out.println("null");
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