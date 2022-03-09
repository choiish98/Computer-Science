/* 삽입정렬
    k번째 원소를 1부터 k-1까지와 비교해 
    적절한 위치에 끼워넣고 
    그 뒤의 자료를 한 칸씩 뒤로 밀어내는 방식
    O(n) = n(n-1) / 2 = n^2
*/

public class insertionSort {

    public static void insertionSort(int[] arr) {
        for (int index = 1; index < arr.length; index++) {
            int temp = arr[index];
            int aux = index - 1;

            while ((aux >= 0) && (arr[aux] > temp)) {
                arr[aux + 1] = arr[aux];
                aux--;
            }
            arr[aux + 1] = temp;
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
        insertionSort(arr);
        PrintArray(arr);
    }
}