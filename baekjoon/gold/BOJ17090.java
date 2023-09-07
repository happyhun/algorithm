import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17090 {

    static char[][] map;
    static boolean[][] visited;
    static int N, M;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N][M];
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = (char) br.read();
            }
            br.read();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'T' || map[i][j] == 'F')
                    continue;
                solve(i, j);
            }
        }

        System.out.println(answer);
    }

    private static boolean solve(int r, int c) {
        if (r < 0 || r >= N || c < 0 || c >= M)
            return true;

        if (visited[r][c]) {
            if (map[r][c] == 'T')
                return true;
            return false;
        }

        visited[r][c] = true;

        switch (map[r][c]) {
            case 'R':
                if (solve(r, c + 1)) {
                    map[r][c] = 'T';
                    answer++;
                    return true;
                }
                map[r][c] = 'F';
                return false;
            case 'L':
                if (solve(r, c - 1)) {
                    map[r][c] = 'T';
                    answer++;
                    return true;
                }
                map[r][c] = 'F';
                return false;
            case 'U':
                if (solve(r - 1, c)) {
                    map[r][c] = 'T';
                    answer++;
                    return true;
                }
                map[r][c] = 'F';
                return false;
            case 'D':
                if (solve(r + 1, c)) {
                    map[r][c] = 'T';
                    answer++;
                    return true;
                }
                map[r][c] = 'F';
                return false;
            case 'T':
                answer++;
                return true;
        }

        return false;
    }
}