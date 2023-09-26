class Solution {
    public int solution(int m, int n, String[] board) {
        // 1. map 만들기
        char[][] map = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = board[i].charAt(j);
            }
        }

        int answer = 0;
        boolean isDeleted = true;

        while (isDeleted) {
            isDeleted = false;
            boolean[][] deleted = new boolean[m][n];

            // 2. 조건을 만족하는 지 검사
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (map[i][j] != 0
                            && map[i][j] == map[i + 1][j]
                            && map[i + 1][j] == map[i][j + 1]
                            && map[i][j + 1] == map[i + 1][j + 1]) {

                        deleted[i][j] = true;
                        deleted[i + 1][j] = true;
                        deleted[i][j + 1] = true;
                        deleted[i + 1][j + 1] = true;
                        isDeleted = true;
                    }
                }
            }

            // 3. 조건을 만족한 블록 지우기
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (deleted[i][j] == true) {
                        map[i][j] = 0;
                        answer++;
                    }
                }
            }

            // 4. 블록 아래로 내리기
            for (int i = 0; i < n; i++) {
                for (int j = m - 1; j >= 0; j--) {
                    if (map[j][i] == 0) {
                        for (int k = j - 1; k >= 0; k--) {
                            if (map[k][i] != 0) {
                                map[j][i] = map[k][i];
                                map[k][i] = 0;
                                break;
                            }
                        }
                    }
                }
            }

        }

        return answer;
    }
}