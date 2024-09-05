import java.io.*;
import java.util.*;

public class Solution {

	static int T, N;
	static int[][] map;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static boolean[][] visited;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			answer = 0;

			// 지뢰맵 입력받기
			for (int i = 0; i < N; i++) {
				String temp = br.readLine();
				for (int j = 0; j < N; j++) {
					if (temp.charAt(j) == '*') {
						map[i][j] = -1;
					} else {
						map[i][j] = 0;
					}
				}
			}

			// 숫자 채우기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == -1) {
						continue;
					}
					for (int k = 0; k < 8; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
							continue;
						}
						if (map[nx][ny] == -1) {
							map[i][j]++;
						}
					}
				}
			}

			// printMap();
			// 0을 시작점으로 BFS
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 0 && !visited[i][j]) {
						BFS(i, j);
						answer++;
					}
				}
			}

			// 지뢰가 아니고 방문하지 않은 칸 수 세기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > 0 && !visited[i][j]) {
						answer++;
					}
				}
			}

			// 정답 출력
			System.out.printf("#%d %d\n", t, answer);
		}

	}

	private static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.printf("%d ", map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void BFS(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { x, y });
		visited[x][y] = true;

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			for (int i = 0; i < 8; i++) {
				int nx = curr[0] + dx[i];
				int ny = curr[1] + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] == -1) {
					continue;
				}
				if (map[nx][ny] == 0) {
					queue.add(new int[] {nx, ny});
				}
				visited[nx][ny] = true;
			}
		}
	}
}
