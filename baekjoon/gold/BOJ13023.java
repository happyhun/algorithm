import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ13023 {

    static List<Integer>[] graph;
    static boolean[] visited;
    static int N, M, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
            graph[end].add(start);
        }

        answer = 0;
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            DFS(i, 0);
            if (answer == 1) {
                break;
            }
        }

        System.out.println(answer);
    }

    private static void DFS(int curr, int depth) {
        if (depth >= 4) {
            answer = 1;
            return;
        }

        visited[curr] = true;

        for (int next : graph[curr]) {
            if (visited[next]) {
                continue;
            }
            DFS(next, depth + 1);
            visited[next] = false;
        }
    }
}
