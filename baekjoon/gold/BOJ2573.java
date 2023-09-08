import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2573 {

    static int[][] map;
    static int answer = 0;
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            if (getSplitNumber() == 0) {
                System.out.println(0);
                break;
            }

            passOneDay();

            if (getSplitNumber() > 1) {
                System.out.println(answer);
                break;
            }
        }

    }

    private static void passOneDay() {
        int [][] temp = new int[map.length][map[0].length];

        for (int i = 0; i < temp.length; i++) {
            temp[i] = map[i].clone();
        }



        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != 0) {
                    for (int k = 0; k < 4; k++) {
                        if (map[i+dx[k]][j+dy[k]] == 0) {
                            temp[i][j]--;
                        }
                    }
                    if (temp[i][j] < 0)
                        temp[i][j] = 0;
                }
            }
        }

        map = temp;
        answer++;
    }

    private static int getSplitNumber() {
        int result = 0;
        int [][] temp = new int[map.length][map[0].length];

        for (int i = 0; i < temp.length; i++) {
            temp[i] = map[i].clone();
        }


        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                if (temp[i][j] != 0) {
                    DFS(i, j, temp);
                    result++;
                }
            }
        }
        return result;
    }

    private static void DFS(int x, int y, int[][] temp) {
        if (temp[x][y] == 0)
            return;

        temp[x][y] = 0;

        for (int i = 0; i < 4; i++) {
            DFS(x+dx[i], y+dy[i], temp);
        }
    }

}
