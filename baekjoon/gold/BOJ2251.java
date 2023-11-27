import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2251 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] limits = new int[3];
        for (int i = 0; i < 3; i++) {
            limits[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> result = BFS(limits);

        for (int n : result) {
            System.out.printf("%d ", n);
        }
        System.out.println();
    }

    private static List<Integer> BFS(int[] limits) {
        Set<Integer> result = new TreeSet<>();
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[201][201];

        queue.add(new int[]{0, 0, limits[2]});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] == 0) {
                result.add(curr[2]);
            }
            for (int from = 0; from < 3; from++) {
                for (int to = 0; to < 3; to++) {
                    if (from == to) {
                        continue;
                    }
                    int[] next = curr.clone();
                    next[to] += next[from];
                    next[from] = 0;
                    if (next[to] > limits[to]) {
                        next[from] = next[to] - limits[to];
                        next[to] = limits[to];
                    }
                    if (visited[next[0]][next[1]]) {
                        continue;
                    }
                    queue.add(next);
                    visited[next[0]][next[1]] = true;
                }
            }
        }

        return new ArrayList<>(result);
    }
}
