import java.io.*;
import java.util.*;
 
public class Solution {
 
    static int T;
    static int[][] map;
    static Set<String> results;
    static char[] result;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, -1, 0, 1 };
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
            map = new int[4][4];
            results = new HashSet<>();
            for (int i = 0; i < 4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            result = new char[7];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    result[0] = (char) (map[i][j] + '0');
                    permutation(i, j, 1);
                }
            }
 
            System.out.printf("#%d %d\n", t, results.size());
        }
    }
 
    private static void permutation(int x, int y, int depth) {
        if (depth == 7) {
            results.add(String.valueOf(result));
            return;
        }
 
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) {
                continue;
            }
            result[depth] = (char) (map[nx][ny] + '0');
            permutation(nx, ny, depth + 1);
        }
    }
}
