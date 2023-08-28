import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String[] category = new String[]{"R", "T", "C", "F", "J", "M", "A", "N"};
        Map<String, Integer> scores = new HashMap<>();

        for (String s : category)
            scores.put(s, 0);

        for (int i = 0; i < survey.length; i++) {
            if (choices[i] < 4) {
                String s = String.valueOf(survey[i].charAt(0));
                int score = scores.get(s) + (4 - choices[i]);
                scores.put(s, score);
            }

            if (choices[i] > 4) {
                String s = String.valueOf(survey[i].charAt(1));
                int score = scores.get(s) + (choices[i] - 4);
                scores.put(s, score);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            if (scores.get(category[i * 2]) < scores.get(category[i * 2 + 1]))
                sb.append(category[i * 2 + 1]);
            else
                sb.append(category[i * 2]);
        }

        return sb.toString();
    }
}