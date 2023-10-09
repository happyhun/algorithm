import java.util.*;
import java.util.Map.Entry;

public class Solution {

    static Map<String, Integer> map;
    static int target;

    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();

        // 1. 주문 목록 오름차순 정렬
        for (int i = 0; i < orders.length; i++) {
            char[] charArr = orders[i].toCharArray();
            Arrays.sort(charArr);
            orders[i] = String.valueOf(charArr);
        }

        // 2. course 값에 해당하는 조합 구하기
        for (int i = 0; i < course.length; i++) {
            map = new HashMap<>();
            target = course[i];
            int max = 0;

            // 2-1. 주문한 요리 개수가 코스의 요리 개수 이상이면 조합 구하기
            for (String order : orders) {
                if (target > order.length())
                    continue;
                StringBuilder sb = new StringBuilder();
                combi(order, sb, 0);
            }

            // 2-2. 주문 횟수 최대값 구하기
            for (Entry<String, Integer> entry : map.entrySet()) {
                max = Math.max(max, entry.getValue());
            }

            // 2-3. 2번 이상 주문되고 최대 주문 횟수를 가지는 조합은 정답 리스트에 넣기
            for (Entry<String, Integer> entry : map.entrySet()) {
                if (max >= 2 && entry.getValue() == max)
                    answer.add(entry.getKey());
            }
        }

        // 3. 오름차순 정렬 후 반환
        Collections.sort(answer);
        return answer.toArray(new String[0]);
    }

    private void combi(String str, StringBuilder sb, int idx) {
        if (sb.length() == target) {
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
            return;
        }

        for (int i = idx; i < str.length(); i++) {
            sb.append(str.charAt(i));
            combi(str, sb, i + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
