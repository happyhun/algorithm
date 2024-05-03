import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        boolean[] escaped = new boolean[people.length];
        int result = 0;
        int idx = 0;

        for (int i = people.length - 1; i >= 0; i--) {
            if (escaped[i]) {
                break;
            }
            escaped[i] = true;
            if (people[i] + people[idx] <= limit) {
                escaped[idx++] = true;
            }
            result++;
        }

        return result;
    }
}