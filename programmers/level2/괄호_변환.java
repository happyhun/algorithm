import java.util.*;

class Solution {
    public String solution(String p) {
        return recur(p);
    }

    private String recur(String str) {
        // 1
        if (str.isEmpty())
            return "";

        // 2
        String[] temp = makeBalance(str);
        String u = temp[0];
        String v = temp[1];

        // 3
        if (isCorrect(u)) {
            // 3-1
            return u + recur(v);
        }

        // 4
        StringBuilder sb = new StringBuilder();
        // 4-1
        sb.append('(');
        // 4-2
        sb.append(recur(v));
        // 4-3
        sb.append(')');
        // 4-4
        sb.append(process(u));
        // 4-5
        return sb.toString();
    }

    private String[] makeBalance(String str) {
        String u, v;
        int idx = 1;
        int count = 0;

        if (str.charAt(0) == '(')
            count++;
        else
            count--;

        while (count != 0) {
            if (str.charAt(idx) == '(')
                count++;
            else
                count--;

            idx++;
        }

        u = str.substring(0, idx);
        v = idx == str.length() ? "" : str.substring(idx);
        return new String[]{u, v};
    }

    private boolean isCorrect(String str) {
        Stack<Character> stack = new Stack<>();

        stack.push(str.charAt(0));
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == '(')
                stack.push('(');
            else {
                if (stack.peek() == '(')
                    stack.pop();
                else
                    return false;
            }
        }

        return stack.isEmpty() ? true : false;
    }

    private String process(String str) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < str.length() - 1; i++) {
            if (str.charAt(i) == '(')
                sb.append(')');
            else
                sb.append('(');
        }

        return sb.toString();
    }
}