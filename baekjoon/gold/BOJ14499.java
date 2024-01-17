import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14499 {

    static int[] curr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] orders = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            orders[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dice = new int[2][4];
        curr = new int[]{x, y};
        for (int order : orders) {
            int result = move(map, order, dice);
            if (result == -1) {
                continue;
            }
            System.out.println(result);
        }
    }

    private static int move(int[][] map, int order, int[][] dice) {
        int[] type = {0, 0, 0, 1, 1};
        int[] dd = {0, 1, 3, 3, 1};
        int[] dx = {0, 0, 0, -1, 1};
        int[] dy = {0, 1, -1, 0, 0};

        // 맵 이동
        int nx = curr[0] + dx[order];
        int ny = curr[1] + dy[order];


        if (nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) {
            return -1;
        }

        // 주사위 변환
        int[] nDice = new int[4];
        for (int i = 0; i < 4; i++) {
            int ni = (i + dd[order]) % 4;
            nDice[ni] = dice[type[order]][i];
        }

        // 지도 및 주사위 업데이트
        if (map[nx][ny] == 0) {
            map[nx][ny] = nDice[3];
        } else {
            nDice[3] = map[nx][ny];
            map[nx][ny] = 0;
        }

        curr[0] = nx;
        curr[1] = ny;
        dice[type[order]] = nDice;
        dice[(type[order] + 1) % 2][1] = nDice[1];
        dice[(type[order] + 1) % 2][3] = nDice[3];

        // 주사위 상단 값 반환
        return nDice[1];
    }
}