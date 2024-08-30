import java.io.*;
import java.util.StringTokenizer;
 
public class Solution {
 
    static int T, N;
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
         
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            int[][] map90 = new int[N][N];
            int[][] map180 = new int[N][N];
            int[][] map270 = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map90[j][N - 1 - i] = map[i][j];
                    map180[N - 1 - i][N - 1 - j] = map[i][j];
                    map270[N - 1 - j][i] = map[i][j];
                }
            }
             
            System.out.println("#" + t);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(map90[i][j]);
                }
                System.out.print(" ");
                for (int j = 0; j < N; j++) {
                    System.out.print(map180[i][j]);
                }
                System.out.print(" ");
                for (int j = 0; j < N; j++) {
                    System.out.print(map270[i][j]);
                }
                System.out.println();
            }
        }
    }
}
