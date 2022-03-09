/*  
    Greedy Algorithm
    당장 눈 앞의 최적의 상황만을 쫓는 알고리즘 
    특정한 상황에서 최적의 해를 보장 ex) 거스름 돈 문제, 활동 선택 문제
    정렬과 함께 사용하는 경우가 많다. ex) 크루스칼 (최소비용신장트리)n 
    최적의 해를 보장하지 못 할 때 DP 사용

    예제: 1이 될 때까지
    주어진 N에 대하여 최대한 많이 나누기를 수행
*/

public class greedy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextInt()); // 주어지는 n
        int k = Integer.parseInt(sc.nextInt()); // 나누는 수 k
        int result = 0; // 연산 수행 횟수

        while(true) {
            int target = (n/k) * k; // 나누어 떨어지는 수 만들기
            result += (n - target); // 나누어 떨어지는 수만큼 1씩 빼기
            n = target;

            if(n < k) break; // 더 이상 나눌 수 없을 때 반복 종료

            result += 1; // 1회 증가
            n /= k; // k로 나누기
        }
        
        // 더 이상 나눌 수 없는 수를 1까지 빼기
        result += (n-1);
        System.out.println(result);
    }
}
