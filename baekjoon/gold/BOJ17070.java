import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17070 {
    static int[][] map;
    static int N;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] pipe = new int[]{0, 1};
        String dir = "가로";

        DFS(pipe, dir);

        System.out.println(answer);
    }

    private static void DFS(int[] pipe, String dir) {
        int r = pipe[0];
        int c = pipe[1];

        if (r == N - 1 && c == N - 1) {
            answer++;
            return;
        }

        int nr, nc;

        if (dir.equals("가로")) {
            // 가로 방향 이동
            nr = pipe[0];
            nc = pipe[1] + 1;
            if (nr < N && nc < N && map[nr][nc] != 1) {
                DFS(new int[]{nr, nc}, "가로");
            }

            // 대각선 방향 이동
            nr = pipe[0] + 1;
            nc = pipe[1] + 1;
            if (nr < N && nc < N && map[r][nc] != 1 && map[nr][nc] != 1 && map[nr][c] != 1) {
                DFS(new int[]{nr, nc}, "대각선");
            }
        }

        if (dir.equals("세로")) {
            // 세로 방향 이동
            nr = pipe[0] + 1;
            nc = pipe[1];
            if (nr < N && nc < N && map[nr][nc] != 1) {
                DFS(new int[]{nr, nc}, "세로");
            }

            // 대각선 방향 이동
            nr = pipe[0] + 1;
            nc = pipe[1] + 1;
            if (nr < N && nc < N && map[r][nc] != 1 && map[nr][nc] != 1 && map[nr][c] != 1) {
                DFS(new int[]{nr, nc}, "대각선");
            }
        }

        if (dir.equals("대각선")) {
            // 가로 방향 이동
            nr = pipe[0];
            nc = pipe[1] + 1;
            if (nr < N && nc < N && map[nr][nc] != 1) {
                DFS(new int[]{nr, nc}, "가로");
            }

            // 세로 방향 이동
            nr = pipe[0] + 1;
            nc = pipe[1];
            if (nr < N && nc < N && map[nr][nc] != 1) {
                DFS(new int[]{nr, nc}, "세로");
            }

            // 대각선 방향 이동
            nr = pipe[0] + 1;
            nc = pipe[1] + 1;
            if (nr < N && nc < N && map[r][nc] != 1 && map[nr][nc] != 1 && map[nr][c] != 1) {
                DFS(new int[]{nr, nc}, "대각선");
            }
        }
    }
}