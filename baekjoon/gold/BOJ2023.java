import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2023 {

    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        // 변수 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 신기한 소수 찾기
        DFS();
    }

    private static boolean isPrime(int num) {
        if (num == 0 || num == 1) {
            return false;
        }

        if (num == 2) {
            return true;
        }

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static void DFS() {
        // 소수가 아니면 종료
        if (sb.length() > 0 && !isPrime(Integer.parseInt(sb.toString()))) {
            return;
        }

        // N 자리의 신기한 소수 출력
        if (sb.length() == N) {
            System.out.println(sb);
            return;
        }

        // 백트래킹 기법 사용
        for (int i = 0; i < 10; i++) {
            sb.append(i);
            DFS();
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}