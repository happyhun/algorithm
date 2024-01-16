import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15681 {

    static List<Integer>[] graph;
    static boolean[] visited;
    static int[] sizes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        visited = new boolean[N + 1];
        sizes = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        makeTree(R);

        visited = new boolean[N + 1];
        getSize(R);

        for (int i = 0; i < Q; i++) {
            int start = Integer.parseInt(br.readLine());
            System.out.println(sizes[start]);
        }
    }

    private static void makeTree(int curr) {
        if (visited[curr]) {
            return;
        }

        visited[curr] = true;
        for (int next : graph[curr]) {
            if (visited[next]) {
                continue;
            }
            graph[next].remove(Integer.valueOf(curr));
            makeTree(next);
        }
    }

    private static int getSize(int curr) {
        int size = 1;
        visited[curr] = true;
        for (int next : graph[curr]) {
            if (visited[next]) {
                continue;
            }
            size += getSize(next);
        }
        sizes[curr] = size;
        return size;
    }
}
