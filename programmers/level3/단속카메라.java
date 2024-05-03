import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        int left = 0;
        int right = 1;
        int count = 0;
        while (left < routes.length) {
            count++;
            while (right < routes.length
                    && routes[left][1] >= routes[right][0]) {
                right++;
            }
            left = right;
            right = left + 1;
        }

        return count;
    }
}