import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1025 {

    static int n, m;
    static int[][] arr;
    static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[10][10];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }

        for (int r = 0; r < n; ++r) {
            for (int c = 0; c < m; ++c) {
                for (int dr = -n; dr < n; ++dr) {
                    for (int dc = -m; dc < m; ++dc) {
                        if (dr == 0 && dc == 0)
                            continue;
                        int max = 0;
                        int nr = r;
                        int nc = c;
                        while (nr >= 0 && nr < n && nc >= 0 && nc < m) {
                            max = 10 * max + arr[nr][nc];
                            if (Math.abs(Math.sqrt(max) - (int) Math.sqrt(max)) < 1e-10)
                                answer = Math.max(answer, max);
                            nr += dr;
                            nc += dc;
                        }
                    }
                }
            }
        }


        System.out.println(answer);
    }
}