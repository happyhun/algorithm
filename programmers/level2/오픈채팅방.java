import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();

        for (String s : record) {
            String[] temp = s.split(" ");
            if (temp.length > 2) {
                map.put(temp[1], temp[2]);
            }
        }

        List<String> result = new ArrayList<>();
        for (String r : record) {
            String[] temp = r.split(" ");
            switch (temp[0]) {
                case "Enter":
                    result.add(String.format("%s님이 들어왔습니다.", map.get(temp[1])));
                    break;
                case "Leave":
                    result.add(String.format("%s님이 나갔습니다.", map.get(temp[1])));
                    break;
            }
        }

        return result.toArray(new String[0]);
    }
}