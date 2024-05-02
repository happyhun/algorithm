import java.util.*;

class Solution {

    static char[] arr = {'A', 'E', 'I', 'O', 'U'};
    static StringBuilder sb;
    static int answer;
    static int count;

    public int solution(String word) {
        sb = new StringBuilder();
        answer = -1;

        subset(0, word);
        return answer;
    }

    private void subset(int depth, String word) {
        if (depth == arr.length || answer != -1) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            count++;
            if (sb.toString().equals(word)) {
                answer = count;
                return;
            }
            subset(depth + 1, word);
            if (answer != -1) {
                return;
            }
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}