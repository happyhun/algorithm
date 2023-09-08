import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7569 {

    static int[][][] box;
    static int M, N, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[H][N][M];

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                String[] temp = br.readLine().split(" ");
                for (int m = 0; m < M; m++) {
                    box[h][n][m] = Integer.parseInt(temp[m]);
                }
            }
        }

        int answer = BFS();

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (box[h][n][m] == 0) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        System.out.println(answer);

    }

    private static int BFS() {
        int answer = 0;

        int[] dh = new int[]{-1, 1, 0, 0, 0, 0};
        int[] dn = new int[]{0, 0, -1, 1, 0, 0};
        int[] dm = new int[]{0, 0, 0, 0, -1, 1};

        Queue<Tomato> queue = new LinkedList<>();
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (box[h][n][m] == 1) {
                        queue.add(new Tomato(h, n, m));
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            Tomato tomato = queue.poll();
            int h = tomato.h;
            int n = tomato.n;
            int m = tomato.m;

            for (int i = 0; i < 6; i++) {
                int nh = h + dh[i];
                int nn = n + dn[i];
                int nm = m + dm[i];
                if (nh < 0 || nn < 0 || nm < 0 || nh == H || nn == N || nm == M)
                    continue;
                if (box[nh][nn][nm] == 0) {
                    queue.add(new Tomato(nh, nn, nm));
                    box[nh][nn][nm] = box[h][n][m] + 1;
                    answer = box[h][n][m];
                }
            }
        }

        return answer;
    }

    private static class Tomato {
        int h;
        int n;
        int m;

        public Tomato(int h, int n, int m) {
            this.h = h;
            this.n = n;
            this.m = m;
        }
    }
}