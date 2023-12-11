import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6593 {

    static char[][][] map;
    static boolean[][][] visited;
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static int L, R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) {
                break;
            }

            map = new char[L][R][C];
            visited = new boolean[L][R][C];
            int[] start = new int[3];
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    for (int k = 0; k < C; k++) {
                        map[i][j][k] = (char) br.read();
                        if (map[i][j][k] == 'S') {
                            start[0] = i;
                            start[1] = j;
                            start[2] = k;
                        }
                    }
                    br.read();
                }
                br.readLine();
            }

            int answer = BFS(start);
            if (answer == 0) {
                System.out.println("Trapped!");
            } else {
                System.out.printf("Escaped in %d minute(s).\n", answer);
            }
        }
    }

    private static int BFS(int[] start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start[0], start[1], start[2], 0});
        visited[start[0]][start[1]][start[2]] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < 6; i++) {
                int nl = curr[0] + dz[i];
                int nr = curr[1] + dx[i];
                int nc = curr[2] + dy[i];
                if (nl < 0 || nl >= L || nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nl][nr][nc] || map[nl][nr][nc] == '#') {
                    continue;
                }
                if (map[nl][nr][nc] == 'E') {
                    return curr[3] + 1;
                }
                queue.add(new int[]{nl, nr, nc, curr[3] + 1});
                visited[nl][nr][nc] = true;
            }
        }
        return 0;
    }
}
