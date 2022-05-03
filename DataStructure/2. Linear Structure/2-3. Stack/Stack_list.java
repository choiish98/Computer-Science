public public class Stack_list {
    public static void main(String[] args) {
        MyStack_list stack = new MyStack_list();
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

class MyStack_list {
    private int data;
    private MyStack_list next;
    private MyStack_list top;

    public MyStack_list(int data) {
    this.data = data;
    }

    public int pop() {
        if (top == null) System.out.println("null");
        int item = top.data;
        top = top.next;

        return item;
    }

    public void push(int item) {
        MyStack_list t = new MyStack_list(item);
        t.next = top;
        top = t;
    }

    public int peek() {
        if (top == null) System.out.println("null");
        return top.data;
    }
}
