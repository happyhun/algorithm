import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Solution {
 
    static int T, N, M;
    static char[][] map;
    static String[] strNum = { "0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011",
            "0110111", "0001011" };
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new char[N][M];
            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
            }
 
            // 마지막 1찾기
            int idx = -1;
            char[] code = null;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == '1') {
                        idx = j;
                    }
                }
                if (idx != -1) {
                    code = Arrays.copyOfRange(map[i], idx - 55, idx + 1);
                    break;
                }
            }
 
            int[] nCode = new int[8];
            for (int i = 0; i < 8; i++) {
                boolean isAnswer = false;
                for (int k = 0; k < 10; k++) {
                    for (int j = 0; j < 7; j++) {
                        if (code[7 * i + j] != strNum[k].charAt(j)) {
                            break;
                        }
                        if (j == 6) {
                            nCode[i] = k;
                            isAnswer = true;
                        }
                    }
                    if (isAnswer) {
                        break;
                    }
                }
            }
 
            int sum = 0;
            for (int i = 0; i < 8; i++) {
                if (i % 2 == 0) {
                    sum += nCode[i] * 3;
                } else {
                    sum += nCode[i];
                }
            }
            if (sum % 10 == 0) {
                sum = 0;
                for (int i = 0; i < 8; i++) {
                    sum += nCode[i];
                }
                System.out.printf("#%d %d\n", t, sum);
            } else {
                System.out.printf("#%d 0\n", t);
            }
        }
    }
}
