import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1647 {

    private static class Node {
        int v;
        int w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        // 1. 변수 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[N + 1];
        List<Node>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, cost));
            graph[end].add(new Node(start, cost));
        }

        // 2. 프림 알고리즘 사용
        int answer = 0;
        int max = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.w));
        pq.add(new Node(1, 0));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (visited[curr.v]) {
                continue;
            }
            visited[curr.v] = true;
            answer += curr.w;
            max = Math.max(max, curr.w);
            for (Node next : graph[curr.v]) {
                pq.add(next);
            }
        }

        // 3. 정답 출력
        answer -= max;
        System.out.println(answer);
    }
}
