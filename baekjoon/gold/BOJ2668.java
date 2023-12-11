import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ2668 {

    static int[] numbers;
    static boolean[] visited;
    static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        numbers = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            visited[i] = true;
            DFS(numbers[i], i);
        }

        printAnswer();
    }

    private static void printAnswer() {
        System.out.println(answer.size());
        for (int number : answer) {
            System.out.println(number);
        }
    }

    private static void DFS(int curr, int target) {
        if (curr == target) {
            answer.add(curr);
        }
        if (visited[curr]) {
            return;
        }
        visited[curr] = true;
        int next = numbers[curr];
        DFS(next, target);
    }
}
