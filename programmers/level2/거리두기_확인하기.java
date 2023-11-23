import java.util.LinkedList;
import java.util.Queue;

class Solution {

    public int[] solution(String[][] places) {
        int[] result = new int[5];
        for (int i = 0; i < 5; i++) {
            char[][] map = makeMap(places[i]);
            result[i] = checkSocialDistance(map);
        }

        return result;
    }

    private char[][] makeMap(String[] place) {
        char[][] map = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = place[i].charAt(j);
            }
        }
        return map;
    }

    private int checkSocialDistance(char[][] map) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == 'P' && violate(i, j, map)) {
                    return 0;
                }
            }
        }
        return 1;
    }

    private boolean violate(int r, int c, char[][] map) {
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, -1, 0, 1};
        boolean[][] visited = new boolean[5][5];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c, 0});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (map[curr[0]][curr[1]] == 'P' && !hasPartition(r, c, curr[0], curr[1], map)) {
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                int nd = curr[2] + 1;
                if (nx < 0 || nx > 4 || ny < 0 || ny > 4 || nd > 2 || visited[nx][ny]) {
                    continue;
                }
                queue.add(new int[]{nx, ny, nd});
                visited[nx][ny] = true;
            }
        }

        return false;
    }

    private boolean hasPartition(int r1, int c1, int r2, int c2, char[][] map) {
        if (r1 == r2 && c1 == c2) {
            return true;
        }
        if (r1 == r2) {
            return Math.abs(c1 - c2) > 1 && map[r1][(c1 + c2) / 2] == 'X';
        }
        if (c1 == c2) {
            return Math.abs(r1 - r2) > 1 && map[(r1 + r2) / 2][c1] == 'X';
        }
        return map[r1][c2] == 'X' && map[r2][c1] == 'X';
    }
}