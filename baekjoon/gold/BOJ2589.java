import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2589 {

    static char[][] graph;
    static int[][] dist;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new char[N][M];
        dist = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                graph[i][j] = (char) br.read();
            }
            br.read();
        }

        int max = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 'L') {
                    max = Math.max(max, getMaxDist(i, j));
                }
            }
        }

        System.out.println(max);
    }

    private static int getMaxDist(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        dist[r][c] = 1;
        int max = 0;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || dist[nx][ny] != 0 || graph[nx][ny] != 'L') {
                    continue;
                }
                queue.add(new int[]{nx, ny});
                dist[nx][ny] = dist[curr[0]][curr[1]] + 1;
                max = Math.max(max, dist[nx][ny]);
            }
        }

        dist = new int[N][M];

        return max == 0 ? max : max - 1;
    }
}
