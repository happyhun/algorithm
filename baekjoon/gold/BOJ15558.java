import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ15558 {

    static int N, k;
    static int[][] lines;
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        visited = new int[2][N];
        lines = new int[2][N];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                lines[i][j] = br.read() - '0';
            }
            br.read();
        }

        System.out.println(BFS());
    }

    private static int BFS() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            int line = curr[0];
            int pos = curr[1];

            if (pos >= N)
                return 1;

            int time = visited[line][pos];
            // 한 칸 앞으로 이동
            if (pos + 1 >= N || lines[line][pos + 1] == 1 && visited[line][pos + 1] == 0) {
                queue.add(new int[]{line, pos + 1});
                if (pos + 1 < N)
                    visited[line][pos + 1] = time + 1;
            }
            // 한 칸 뒤로 이동
            if (time < pos - 1 && lines[line][pos - 1] == 1 && visited[line][pos - 1] == 0) {
                queue.add(new int[]{line, pos - 1});
                visited[line][pos - 1] = time + 1;
            }
            // 반대편 k 칸 앞으로 이동
            if (pos + k >= N || lines[(line + 1) % 2][pos + k] == 1 && visited[(line + 1) % 2][pos + k] == 0) {
                queue.add(new int[]{(line + 1) % 2, pos + k});
                if (pos + k < N)
                    visited[(line + 1) % 2][pos + k] = time + 1;
            }
        }

        return 0;
    }
}