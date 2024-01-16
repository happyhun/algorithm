import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1240 {

    static List<int[]>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[u].add(new int[]{v, d});
            graph[v].add(new int[]{u, d});
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            visited = new boolean[N + 1];
            DFS(new int[]{start, 0}, end);
        }
    }

    private static void DFS(int[] curr, int target) {
        if (curr[0] == target) {
            System.out.println(curr[1]);
            return;
        }

        visited[curr[0]] = true;
        for (int[] next : graph[curr[0]]) {
            if (visited[next[0]]) {
                continue;
            }
            DFS(new int[]{next[0], next[1] + curr[1]}, target);
        }
    }
}
