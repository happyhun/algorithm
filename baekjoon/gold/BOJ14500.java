import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500 {

    static int N, M;
    static int max = 0;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
        System.out.println(max);
    }

    private static void solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                DFS(1, map[i][j], i, j); // 4가지 타입 확인
                BFS(i, j); // 1가지 타입 확인
                visited[i][j] = false;
            }
        }
    }

    private static void DFS(int count, int sum, int x, int y) {
        if (count == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isOutOfRange(nx, ny) || visited[nx][ny]) {
                continue;
            }
            visited[nx][ny] = true;
            DFS(count + 1, sum + map[nx][ny], nx, ny);
            visited[nx][ny] = false;
        }
    }

    private static boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }

    private static void BFS(int x, int y) {
        int min = 1000;
        int sum = map[x][y];
        int[] values = new int[4];

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isOutOfRange(nx, ny)) {
                min = 0;
                continue;
            }
            values[i] = map[nx][ny];
            min = Math.min(min, values[i]);
            sum += values[i];
        }

        max = Math.max(max, sum - min);
    }
}