import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16987 {
    static int N;
    static int[][] eggs;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        eggs = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                eggs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0, 0);
        System.out.println(answer);
    }

    private static void DFS(int idx, int count) {
        if (idx == N) {
            answer = Math.max(answer, count);
            return;
        }

        if (eggs[idx][0] > 0) {
            for (int i = 0; i < N; i++) {
                if (i == idx || eggs[i][0] <= 0)
                    continue;
                int nextCount = count;
                eggs[i][0] -= eggs[idx][1];
                if (eggs[i][0] <= 0)
                    nextCount++;
                eggs[idx][0] -= eggs[i][1];
                if (eggs[idx][0] <= 0)
                    nextCount++;

                DFS(idx + 1, nextCount);

                eggs[i][0] += eggs[idx][1];
                eggs[idx][0] += eggs[i][1];
            }
        }
        if (idx != 0)
            DFS(idx + 1, count);
    }
}