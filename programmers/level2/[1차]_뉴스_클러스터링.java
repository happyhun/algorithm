import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        // 1. 다중집합 만들기 (오직 영문자만)
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();

        List<String> A = createMultiSet(str1);
        List<String> B = createMultiSet(str2);

        int nA = A.size();
        int nB = B.size();

        if (nA == 0 && nB == 0)
            return 65536;

        // 2. 교집합의 개수를 구한다.
        int count1 = 0;
        for (String a : A) {
            for (String b : B) {
                if (a.equals(b)) {
                    B.remove(b);
                    count1++;
                    break;
                }
            }
        }

        // 3. 합집합의 개수를 구한다.
        int count2 = nA + nB - count1;

        // 4. 자카드 유사도를 구한다.
        double J = (double) count1 / count2;

        // 5. 정답 조건에 맞게 반환한다.
        int answer = (int) (J * 65536);

        return answer;
    }

    private List<String> createMultiSet(String str) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < str.length() - 1; i++) {
            String sub = str.substring(i, i + 2);
            if (sub.replaceAll("[A-Z]", "").length() == 0)
                result.add(sub);
        }

        return result;
    }
}