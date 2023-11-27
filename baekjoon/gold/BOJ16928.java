import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16928 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] map = new int[101];
        for (int i = 1; i <= 100; i++) {
            map[i] = i;
        }

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            map[from] = to;
        }

        int answer = BFS(map);
        System.out.println(answer);
    }

    private static int BFS(int[] map) {
        boolean[] visited = new boolean[map.length];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0});
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (map[curr[0]] != curr[0]) {
                curr[0] = map[curr[0]];
                visited[curr[0]] = true;
            }
            for (int i = 1; i <= 6; i++) {
                int nx = curr[0] + i;
                int nd = curr[1] + 1;
                if (nx == 100) {
                    return nd;
                }
                if (visited[nx] || nx > 100) {
                    continue;
                }
                queue.add(new int[]{nx, nd});
                visited[nx] = true;
            }
        }

        return 0;
    }
}
