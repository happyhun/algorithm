import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] failCounts = new int[N + 2];

        for (int stage : stages) {
            failCounts[stage]++;
        }

        Stage[] stageArray = new Stage[N];
        int totalCounts = stages.length;

        for (int i = 1; i <= N; i++) {
            totalCounts -= failCounts[i - 1];
            stageArray[i - 1] = new Stage(i, failCounts[i], totalCounts);
        }

        Arrays.sort(stageArray, (s1, s2) -> Double.compare(s2.getFailRate(), s1.getFailRate()));

        int[] answer = new int[N];

        for (int i = 0; i < N; i++) {
            answer[i] = stageArray[i].id;
        }

        return answer;
    }

    static class Stage {
        int id;
        int failCount;
        int totalCount;

        public Stage(int id, int failCount, int totalCount) {
            this.id = id;
            this.failCount = failCount;
            this.totalCount = totalCount;
        }

        public double getFailRate() {
            return totalCount == 0 ? 0 : (double) failCount / totalCount;
        }
    }
}