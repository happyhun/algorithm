import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1987 {

    static int R, C;
    static char[][] map;
    static int selected;
    static int max = 1;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = (char) br.read();
            }
            br.read();
        }

        solve();
    }

    private static void solve() {
        DFS(0, 0, 1);
        System.out.println(max);
    }

    private static void DFS(int x, int y, int count) {
        selected |= (1 << (map[x][y] - 'A'));

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isOutOfRange(nx, ny) || ((selected & (1 << (map[nx][ny] - 'A'))) != 0)) {
                continue;
            }
            DFS(nx, ny, count + 1);
            selected &= ~(1 << (map[nx][ny] - 'A'));
        }

        max = Math.max(max, count);
    }

    private static boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= R || y < 0 || y >= C;
    }

}
