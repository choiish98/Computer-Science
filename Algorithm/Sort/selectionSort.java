/* 선택정렬
    일단 1번째부터 끝까지 훑어서 
    가장 작은 게 1번째, 
    2번째부터 끝까지 훑어서 가장 작은 게 2번째
    ……해서 (n-1)번 반복
    O(n) = n(n-1) / 2 = n^2
    버블정렬보다 2배 빠르다
*/

public class selectionSort {

    public static void selectionSort(int[] list) {
        int indexMin, temp;

        for (int i = 0; i < list.length - 1; i++) {
            indexMin = i;
            for (int j = i + 1; j < list.length; j++) {
                if (list[j] < list[indexMin]) {
                    indexMin = j;
                }
            }
            temp = list[indexMin];
            list[indexMin] = list[i];
            list[i] = temp;
        }
    }

    private static void PrintArray(int[] arr) {
        for (int data : arr) {
            System.out.print(data + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 3, 9, 4, 7, 5, 0, 1, 6, 8, 2 };
        PrintArray(arr);
        selectionSort(arr);
        PrintArray(arr);
    }
}
