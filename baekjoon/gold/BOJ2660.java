import java.io.*;
import java.util.*;

public class BOJ2660 {

    public static void main(String[] args) throws IOException {
        // 1. 변수 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (u == -1 && v == -1) {
                break;
            }
            graph[u].add(v);
            graph[v].add(u);
        }

        // 2. 각 번호에서 각 번호로 가는 최소 거리 구하기
        int INF = 100;
        int[][] dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[i].contains(j)) {
                    dist[i][j] = 1;
                } else if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = INF;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (dist[j][i] + dist[i][k] < dist[j][k]) {
                        dist[j][k] = dist[j][i] + dist[i][k];
                    }
                }
            }
        }

        // 3. 각 번호에서 가장 먼 번호의 거리 구하기
        int[] score = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            score[i] = Arrays.stream(dist[i]).max().getAsInt();
        }

        // 4. 후보의 점수와 후보 구하기
        int min = INF;
        for (int i = 1; i <= n; i++) {
            if (score[i] < min) {
                min = score[i];
            }
        }

        List<Integer> candidates = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (score[i] == min) {
                candidates.add(i);
            }
        }

        // 5. 출력하기
        System.out.printf("%d %d\n", min, candidates.size());
        for (int c : candidates) {
            System.out.printf("%d ", c);
        }
        System.out.println();
    }
}
