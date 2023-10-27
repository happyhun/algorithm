import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ13549 {

    static int[] dist = new int[100001];
    static int[] dx = {-1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int K = Integer.parseInt(temp[1]);

        int answer = BFS(N, K);
        System.out.println(answer);
    }

    private static int BFS(int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
        int teleport = start;
        while (teleport <= 100000) {
            if (teleport == end)
                return 0;
            queue.add(teleport);
            dist[teleport] = 1;
            if (teleport == 0)
                break;
            teleport *= 2;
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (curr == end)
                return dist[curr] - 1;

            for (int i = 0; i < 2; i++) {
                int next = curr + dx[i];
                if (next < 0 || next > 100000 || dist[next] != 0)
                    continue;
                queue.add(next);
                dist[next] = dist[curr] + 1;

                teleport = next * 2;
                while (teleport <= 100000 && dist[teleport] == 0) {
                    if (teleport == end)
                        return dist[next] - 1;
                    queue.add(teleport);
                    dist[teleport] = dist[next];
                    if (teleport == 0)
                        break;
                    teleport *= 2;
                }
            }
        }

        return 0;
    }
}
