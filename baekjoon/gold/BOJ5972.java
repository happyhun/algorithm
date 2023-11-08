import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ5972 {

    public static void main(String[] args) throws IOException {
        // 1. 변수 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<int[]>[] graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new int[]{end, cost});
            graph[end].add(new int[]{start, cost});
        }

        // 2. 최소 비용 구하기
        int[] costs = new int[N + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{1, 0});
        costs[1] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (costs[curr[0]] < curr[1]) {
                continue;
            }
            for (int[] next : graph[curr[0]]) {
                if (costs[next[0]] <= curr[1] + next[1]) {
                    continue;
                }
                costs[next[0]] = costs[curr[0]] + next[1];
                pq.add(new int[]{next[0], costs[next[0]]});
            }
        }

        System.out.println(costs[N]);
    }
}
