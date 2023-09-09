import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16973 {

    static int N, M;
    static int H, W;
    static int[][] map;
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new int[N][M];
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        int sR = Integer.parseInt(st.nextToken()) - 1;
        int sC = Integer.parseInt(st.nextToken()) - 1;
        int fR = Integer.parseInt(st.nextToken()) - 1;
        int fC = Integer.parseInt(st.nextToken()) - 1;

        int answer = BFS(sR, sC, fR, fC);

        System.out.println(answer);
    }

    private static int BFS(int sR, int sC, int fR, int fC) {
        if (sR == fR && sC == fC)
            return 0;

        int[] dx = new int[]{0, 0, -1, 1};
        int[] dy = new int[]{-1, 1, 0, 0};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sR, sC});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] == fR && curr[1] == fC)
                return visited[fR][fC];

            out:
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];

                if (nx < 0 || nx > N - H || ny < 0 || ny > M - W || visited[nx][ny] != 0)
                    continue;

                switch (i) {
                    case 0:
                        for (int j = 0; j < H; j++) {
                            if (map[nx + j][ny] == 1)
                                continue out;
                        }
                        break;
                    case 1:
                        for (int j = 0; j < H; j++) {
                            if (map[nx + j][ny + W - 1] == 1)
                                continue out;
                        }
                        break;
                    case 2:
                        for (int j = 0; j < W; j++) {
                            if (map[nx][ny + j] == 1)
                                continue out;
                        }
                        break;
                    case 3:
                        for (int j = 0; j < W; j++) {
                            if (map[nx + H - 1][ny + j] == 1)
                                continue out;
                        }
                        break;
                }

                queue.add(new int[]{nx, ny});
                visited[nx][ny] = visited[curr[0]][curr[1]] + 1;
            }
        }

        return -1;
    }
}