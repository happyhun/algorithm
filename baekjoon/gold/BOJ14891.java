import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14891 {
    static int[][] gear;
    static int[] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        gear = new int[4][8];

        for (int i = 0; i < 4; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                gear[i][j] = s.charAt(j) - '0';
            }
        }

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int gearNumber = Integer.parseInt(st.nextToken()) - 1;
            int turn = Integer.parseInt(st.nextToken());

            d = new int[4];

            d[gearNumber] = turn;
            checkDir(gearNumber);
            gearTurn();
        }

        int ans = 0;
        if (gear[0][0] == 1) ans += 1;
        if (gear[1][0] == 1) ans += 2;
        if (gear[2][0] == 1) ans += 4;
        if (gear[3][0] == 1) ans += 8;

        System.out.println(ans);
    }

    private static void checkDir(int gearN) {
        for (int i = gearN - 1; i >= 0; i--) {
            if (gear[i][2] != gear[i + 1][6]) {
                d[i] = -d[i + 1];
            } else {
                break;
            }
        }

        for (int i = gearN + 1; i < 4; i++) {
            if (gear[i][6] != gear[i - 1][2]) {
                d[i] = -d[i - 1];
            } else {
                break;
            }
        }
    }

    private static void gearTurn() {
        int temp = 0;

        for (int i = 0; i < 4; i++) {
            if (d[i] == 1) {
                temp = gear[i][7];
                for (int j = 7; j > 0; j--) {
                    gear[i][j] = gear[i][j - 1];
                }
                gear[i][0] = temp;
            }

            if (d[i] == -1) {
                temp = gear[i][0];
                for (int j = 0; j < 7; j++) {
                    gear[i][j] = gear[i][j + 1];
                }
                gear[i][7] = temp;
            }
        }
    }
}