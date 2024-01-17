import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ3190 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        // 맵 생성하기 (사과 = 1)
        int[][] map = new int[N + 1][N + 1];
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
        }

        // 방향 전환 배열 만들기
        int L = Integer.parseInt(br.readLine());
        String[][] rotations = new String[L][2];
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String X = st.nextToken();
            String C = st.nextToken();
            rotations[i][0] = X;
            rotations[i][1] = C;
        }

        // 게임 시작
        int answer = start(map, rotations);
        System.out.println(answer);
    }

    private static int start(int[][] map, String[][] rotations) {
        int time = 0;
        int direction = 0;
        int idx = 0;

        // 오른쪽, 아래, 왼쪽, 위
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        // 뱀 자료구조
        Deque<Pair> snake = new ArrayDeque<>();
        snake.add(new Pair(1, 1));

        while (true) {
            if (idx < rotations.length && time == Integer.parseInt(rotations[idx][0])) {
                // 방향 전환
                if (rotations[idx][1].equals("L")) {
                    direction = (direction + 3) % 4;
                } else {
                    direction = (direction + 1) % 4;
                }
                idx++;
            }

            Pair head = snake.getFirst();
            int nx = head.x + dx[direction];
            int ny = head.y + dy[direction];
            Pair next = new Pair(nx, ny);

            // 종료 조건
            if (nx < 1 || nx >= map.length || ny < 1 || ny >= map.length || snake.contains(next)) {
                return time + 1;
            }

            // 뱀 이동
            snake.addFirst(next);
            if (map[nx][ny] == 1) {
                map[nx][ny] = 0;
            } else {
                snake.removeLast();
            }

            time++;
        }
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            Pair pair = (Pair) obj;
            return x == pair.x && y == pair.y;
        }
    }
}