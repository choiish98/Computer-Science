public class SimpleArrayListTest {
    public static void main(String[] args) {
        SimpleArrayList list = new SimpleArrayList();
        System.out.println(list);
        list.remove(4);
        list.remove(2);
        System.out.println(list);
    }
}

class SimpleArrayList {
    int[] elements;
    int size;

    public SimpleArrayList() {
        this.elements = new int[10];
        this.size = 8;
        for (int i = 0; i < size; i++) {
            elements[i] = 100 - 4 * i;
        }
    }

    public void add(int data) {
        this.elements[this.size] = data;
        this.size++;
    }

    public void insert(int index, int n) {
        this.elements[index] = n;
    }

    public void remove(int index) {
        for (int i = index; i <= size; i++) {
            this.elements[i] = this.elements[i + 1];
        }
        size--;
    }

    public String toString() {
        StringBuffer sbuf = new StringBuffer("list = ");
        for (int i = 0; i < size; i++) {
            sbuf.append(elements[i] + " ");
        }
        return sbuf.toString();
    }
}