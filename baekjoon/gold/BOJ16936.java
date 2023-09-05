import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ16936 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] nums = new long[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }

        List<Long> list = new ArrayList<>();
        list.add(nums[0]);

        while (true) {
            long lastNum = list.get(list.size() - 1);
            if (lastNum % 3 == 0 && contains(nums, lastNum / 3))
                list.add(lastNum / 3);
            else if (contains(nums, lastNum * 2))
                list.add(lastNum * 2);
            else
                break;
        }

        while (true) {
            long firstNum = list.get(0);
            if (firstNum % 2 == 0 && contains(nums, firstNum / 2))
                list.add(0, firstNum / 2);
            else if (contains(nums, firstNum * 3))
                list.add(0, firstNum * 3);
            else
                break;
        }

        for (long n : list) {
            System.out.printf("%d ", n);
        }
    }

    private static boolean contains(long[] nums, long n) {
        for (long num : nums) {
            if (num == n)
                return true;
        }
        return false;
    }
}