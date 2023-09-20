class Solution {
    public String solution(int n, int t, int m, int p) {
        int[] idxs = new int[t];
        for (int i = 0; i < t; i++) {
            idxs[i] = p + m * i - 1;
        }

        int start = 0;
        StringBuilder sb = new StringBuilder();
        while (sb.length() < idxs[t - 1] + 1) {
            sb.append(Integer.toString(start, n));
            start++;
        }

        char[] result = new char[t];
        for (int i = 0; i < t; i++) {
            result[i] = sb.charAt(idxs[i]);
        }

        return new String(result).toUpperCase();
    }
}