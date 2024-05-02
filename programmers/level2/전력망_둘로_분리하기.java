import java.util.*;

class Solution {

    static List<Integer>[] graph;

    public int solution(int n, int[][] wires) {
        // 그래프 초기화
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 그래프 생성
        for (int[] wire : wires) {
            int u = wire[0];
            int v = wire[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        // 전선 하나씩 끊어보기
        int answer = Integer.MAX_VALUE;
        for (int[] wire : wires) {
            int result = getDiff(wire);
            answer = Math.min(answer, result);
        }

        return answer;
    }

    private int getDiff(int[] wire) {
        int n = graph.length - 1;
        int size1 = 0;
        int size2 = 0;
        boolean[] visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                continue;
            }
            if (size1 == 0) {
                size1 = BFS(i, wire, visited);
            } else if (size2 == 0) {
                size2 = BFS(i, wire, visited);
            } else {
                break;
            }
        }

        return Math.abs(size1 - size2);
    }

    private int BFS(int v, int[] wire, boolean[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(v);
        visited[v] = true;
        int size = 1;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int next : graph[curr]) {
                if (visited[next]) {
                    continue;
                }
                if (curr == wire[0] && next == wire[1]) {
                    continue;
                }
                if (curr == wire[1] && next == wire[0]) {
                    continue;
                }
                queue.add(next);
                visited[next] = true;
                size++;
            }
        }

        return size;
    }
}