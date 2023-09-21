import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < records.length; i++) {
            String[] temp = records[i].split(" ");
            int type = temp[2].equals("IN") ? -1 : 1;
            int minute = calculateMinute(temp[0]);
            map.put(temp[1], map.getOrDefault(temp[1], 0) + minute * type);
        }

        List<String> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList);

        int[] answer = new int[map.size()];
        for (int i = 0; i < keyList.size(); i++) {
            String key = keyList.get(i);
            answer[i] = calculateFee(fees, map.get(key));
        }

        return answer;
    }

    private int calculateMinute(String s) {
        String[] temp = s.split(":");
        return Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]);
    }

    private int calculateFee(int[] fees, int duration) {
        if (duration <= 0)
            duration += 23 * 60 + 59;

        if (duration <= fees[0])
            return fees[1];
        else
            return fees[1] + (int) Math.ceil((duration - fees[0]) / (double) fees[2]) * fees[3];
    }
}