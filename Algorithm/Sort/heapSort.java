/*  heapSort
    힙 트리나 최소 힙 트리를 구성해 정렬을 하는 방법
    내림차순 정렬을 위해서는 최대 힙을 구성
    오름차순 정렬을 위해서는 최소 힙을 구성

    알고리즘
    n개의 노드에 대한 완전 이진 트리를 구성
    루트 노드부터 부모노드, 왼쪽 자식노드, 오른쪽 자식노드 순
    최대 힙(부모노드가 자식노드보다 큰 트리)을 구성
    가장 큰 수(루트에 위치)를 가장 작은 수와 교환
    2와 3을 반복

    시간 복잡도
    O(n\log n)
*/

public class Heap {
    private int[] element; // element[0] contains length
    private static final int ROOTLOC = 1;
    private static final int DEFAULT = 10;

    public Heap(int size) {
        if (size > DEFAULT) {
            element = new int[size + 1];
            element[0] = 0;
        } else {
            element = new int[DEFAULT + 1];
            element[0] = 0;
        }
    }

    public void add(int newnum) {

        if (element.length <= element[0] + 1) {
            int[] elementTemp = new int[element[0] * 2];
            for (int x = 0; x < element[0]; x++) {
                elementTemp[x] = element[x];
            }
            element = elementTemp;
        }
        element[++element[0]] = newnum;
        upheap();
    }

    public int extractRoot() {
        int extracted = element[1];
        element[1] = element[element[0]--];
        downheap();
        return extracted;
    }

    public void upheap() {
        int locmark = element[0];
        while (locmark >= 1 && element[locmark / 2] > element[locmark]) {
            swap(locmark / 2, locmark);
            locmark /= 2;
        }
    }

    public void downheap() {
        int locmark = 1;
        while (locmark * 2 <= element[0]) {
            if (locmark * 2 + 1 <= element[0]) {
                int small = smaller(locmark * 2, locmark * 2 + 1);
                swap(locmark, small);
                locmark = small;
            } else {
                swap(locmark, locmark * 2);
                locmark *= 2;
            }
        }
    }

    public void swap(int a, int b) {
        int temp = element[a];
        element[a] = element[b];
        element[b] = temp;
    }

    public int smaller(int a, int b) {
        return element[a] < element[b] ? a : b;
    }
}

public class heapSort {
    public static void main(String[] args) {
        System.out.println("hello");
    }
}
