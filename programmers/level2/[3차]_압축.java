import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(String msg) {
        // 1. 사전 초기화
        List<String> dict = new ArrayList<>();
        dict.add("");
        for (int i = 65; i <= 90; i++)
            dict.add(String.valueOf((char) i));

        // 2. 문자열 검사
        List<Integer> result = new ArrayList<>();
        int start = 0;
        while (start < msg.length()) {
            // 사전에 없는 글자가 나올 때까지 문자열 확대
            int end = start + 1;
            while (end <= msg.length() && dict.contains(msg.substring(start, end)))
                end++;
            // 마지막 문자까지 사전에 있는 문자면 색인을 출력 리스트에 바로 추가
            if (end > msg.length()) {
                result.add(dict.indexOf(msg.substring(start, end - 1)));
                break;
            }
            // 사전에 없는 글자가 나오면 직전 글자의 색인을 출력 리스트에 추가하고 없는 문자를 사전에 등록
            result.add(dict.indexOf(msg.substring(start, end - 1)));
            dict.add(msg.substring(start, end));

            // 검사하지 않은 문자부터 검사 반복
            start = end - 1;
        }


        // 3. 정답 배열 반환
        return result.stream().mapToInt(i -> i).toArray();
    }
}