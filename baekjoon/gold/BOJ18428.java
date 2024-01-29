import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ18428 {

    static int N;
    static char[][] map;
    static List<Pair> teachers = new ArrayList<>();
    static List<Pair> candidates = new ArrayList<>();
    // 상하좌우 4가지 방향
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        // 변수 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if (map[i][j] == 'T') {
                    // 선생님 리스트에 추가
                    teachers.add(new Pair(i, j));
                }
            }
        }

        // 장애물 설정
        solve();
    }

    private static void solve() {
        makeCandidates();
        setObstacles(0, 0);
        System.out.println("NO");
    }

    private static void makeCandidates() {
        for (Pair teacher : teachers) {
            for (int i = 0; i < 4; i++) {
                // i 방향 탐색 후 S가 있으면 S 직전까지 후보 위치로 지정
                int x = teacher.x;
                int y = teacher.y;
                List<Pair> temp = new ArrayList<>();
                while (true) {
                    x += dx[i];
                    y += dy[i];
                    if (isOutOfRange(x, y) || map[x][y] == 'T') {
                        break;
                    }
                    if (map[x][y] == 'S') {
                        // 후보 위치 리스트에 추가
                        for (Pair pair : temp) {
                            if (map[pair.x][pair.y] != 'C') {
                                candidates.add(pair);
                                map[pair.x][pair.y] = 'C';
                            }
                        }
                        break;
                    }
                    temp.add(new Pair(x, y));
                }
            }
        }
    }

    private static boolean isOutOfRange(int x, int y) {
        return x < 1 || x > N || y < 1 || y > N;
    }

    private static void setObstacles(int idx, int count) {
        if (candidates.size() < 3) {
            for (Pair pair : candidates) {
                map[pair.x][pair.y] = 'O';
            }
            if (isAnswer()) {
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }

        if (count == 3) {
            if (isAnswer()) {
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }

        if (idx >= candidates.size()) {
            return;
        }

        Pair pair = candidates.get(idx);

        map[pair.x][pair.y] = 'O';
        setObstacles(idx + 1, count + 1);

        map[pair.x][pair.y] = 'X';
        setObstacles(idx + 1, count);
    }

    private static boolean isAnswer() {
        for (Pair teacher : teachers) {
            for (int i = 0; i < 4; i++) {
                int x = teacher.x;
                int y = teacher.y;
                while (true) {
                    x += dx[i];
                    y += dy[i];
                    if (isOutOfRange(x, y) || map[x][y] == 'T' || map[x][y] == 'O') {
                        break;
                    }
                    if (map[x][y] == 'S') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "( " + x + ", " + y + " )";
        }
    }
}