import java.util.*;

class Solution {
    public int[] solution(String s) {
        List<String> list = new ArrayList<>();

        int start = 1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ',' && s.charAt(i - 1) == '}') {
                list.add(s.substring(start, i));
                start = i + 1;
            }
        }

        list.add(s.substring(start, s.length() - 1));

        List<Set<String>> sets = new ArrayList<>();

        for (String str : list) {
            String[] arr = str.substring(1, str.length() - 1).split(",");
            sets.add(new HashSet<>(Arrays.asList(arr)));
        }

        sets.sort((s1, s2) -> s1.size() - s2.size());

        int[] answer = new int[sets.size()];

        for (String str : sets.get(0))
            answer[0] = Integer.parseInt(str);

        for (int i = answer.length - 1; i > 0; i--) {
            sets.get(i).removeAll(sets.get(i - 1));
            for (String num : sets.get(i))
                answer[i] = Integer.parseInt(num);
        }

        return answer;
    }
}