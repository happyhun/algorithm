import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> termMap = new HashMap<>();

        for (String term : terms) {
            String[] temp = term.split(" ");
            termMap.put(temp[0], Integer.valueOf(temp[1]));
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < privacies.length; i++) {
            String[] temp = privacies[i].split(" ");

            String date = temp[0];
            String term = temp[1];

            temp = date.split("\\.");
            int year = Integer.parseInt(temp[0]);
            int month = Integer.parseInt(temp[1]);
            int day = Integer.parseInt(temp[2]);

            month += termMap.get(term);
            while (month > 12) {
                month -= 12;
                year++;
            }

            String expiredDate = String.format("%d.%2d.%2d", year, month, day).replace(" ", "0");
            if (today.compareTo(expiredDate) >= 0)
                result.add(i + 1);
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}