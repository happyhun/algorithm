import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Deque<Character> stack = new ArrayDeque<>();
        int deleteCount = 0;
        for (int i = 0; i < number.length(); i++) {
            while (deleteCount < k && !stack.isEmpty() && stack.peek() < number.charAt(i)) {
                stack.pop();
                deleteCount++;
            }
            stack.push(number.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().substring(0, number.length() - k).toString();
    }
}