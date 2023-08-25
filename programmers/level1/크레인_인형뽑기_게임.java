import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();

        for (int move : moves) {
            int doll = 0;

            for (int i = 0; i < board[0].length; i++) {
                if (board[i][move - 1] == 0)
                    continue;

                doll = board[i][move - 1];
                board[i][move - 1] = 0;
                break;
            }

            if (!stack.isEmpty() && stack.peek() == doll) {
                stack.pop();
                answer += 2;
            } else if (doll > 0){
                stack.push(doll);
            }
        }

        return answer;
    }
}