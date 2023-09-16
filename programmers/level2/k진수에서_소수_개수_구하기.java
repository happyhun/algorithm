class Solution {
    public int solution(int n, int k) {
        // 1. k진수로 변환
        String str = Integer.toString(n, k);

        // 2. 조건에 맞는 소수 개수 찾기
        int answer = calculatePrimeCount(str);

        return answer;
    }

    private static int calculatePrimeCount(String str) {
        int count = 0;

        String[] arr = str.split("0");
        for (String s : arr) {
            if (isPrime(s))
                count++;
        }

        return count;
    }

    private static boolean isPrime(String str) {
        if (str.isEmpty() || str.equals("1"))
            return false;

        long num = Long.parseLong(str); // int로 하면 overflow 발생 가능!

        for (int i = 2; i < (int) Math.sqrt(num) + 1; i++) {
            if (num % i == 0)
                return false;
        }

        return true;
    }
}