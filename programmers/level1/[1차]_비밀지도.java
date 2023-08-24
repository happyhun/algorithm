class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for (int i = 0; i < n; i++) {
            int num = arr1[i] | arr2[i];
            String row = Integer.toBinaryString(num).replace("0", " ").replace("1", "#");

            StringBuilder sb = new StringBuilder();
            int count = n - row.length();

            for (int j = 0; j < count; j++)
                sb.append(" ");

            sb.append(row);
            answer[i] = sb.toString();
        }

        return answer;
    }
}