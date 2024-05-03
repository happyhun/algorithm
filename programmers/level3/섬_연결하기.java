import java.util.*;

class Solution {

    static int[][] graph;

    public int solution(int n, int[][] costs) {
        // 그래프 생성
        graph = new int[n][n];
        for (int[] cost : costs) {
            int u = cost[0];
            int v = cost[1];
            int w = cost[2];
            graph[u][v] = w;
            graph[v][u] = w;
        }

        // MST
        int result = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (o1, o2) -> o1.weight - o2.weight
        );
        pq.add(new Node(0, 0));
        boolean[] visited = new boolean[n];

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (visited[curr.num]) {
                continue;
            }
            visited[curr.num] = true;
            result += curr.weight;

            for (int i = 0; i < n; i++) {
                if (visited[i] || graph[curr.num][i] == 0) {
                    continue;
                }
                pq.add(new Node(i, graph[curr.num][i]));
            }
        }

        return result;
    }

    static class Node {
        int num;
        int weight;

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }
}