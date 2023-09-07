import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2252 {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] indegree = new int[N + 1];
        LinkedList<Integer>[] graph = new LinkedList[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (graph[a] == null)
                graph[a] = new LinkedList<>();

            graph[a].add(b);
            indegree[b]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
                indegree[i]--;
                System.out.print(i + " ");
            }
        }

        while (!queue.isEmpty()) {
            int num = queue.poll();
            LinkedList<Integer> l = graph[num];

            if (l == null)
                continue;

            for (Integer i : l) {
                indegree[i]--;

                if (indegree[i] == 0) {
                    queue.add(i);
                    System.out.print(i + " ");
                }
            }
        }
    }
}