// ** 퀵정렬 **
// 배열에서 비교 정렬을 할 때 가장 빠르게 정렬이 가능
// 아무 숫자를 기준으로 잡고
// 기준 숫자보다 작은 수는 왼쪽으로 파티션
// 큰 수는 오른쪽으로 파티션
// * 파티션 방법 *
// start, end가 pivot보다 크고 작은지 비교
// 크거나 작을 때 멈추고 스왑
// start > end일 때 반복 종료
// 파티션 안에서도 다시 파티션
// 시간 복잡도 O(n log n) 최악의 경우: n^2

/**
 * quickSort
 */
public class quickSort {

    public static void quickSort(int[] arr, int left, int right) {
        int i, j, pivot, tmp;
        if (left < right) {
            i = left;
            j = right;
            pivot = arr[(left + right) / 2];
            // 분할 과정
            while (i < j) {
                while (arr[j] > pivot)
                    j--;
                while (i < j && arr[i] < pivot)
                    i++;

                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
            // 정렬 과정
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
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
        quickSort(arr, 0, arr.length - 1);
        PrintArray(arr);
    }
}