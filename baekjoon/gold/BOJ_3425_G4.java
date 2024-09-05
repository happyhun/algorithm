import java.io.*;
import java.util.*;

public class Solution {

	static List<String> ops;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp = br.readLine();

		while (!temp.equals("QUIT")) {
			ops = new ArrayList<>();

			// 명령어 입력받기
			while (!temp.equals("END")) {
				ops.add(temp);
				temp = br.readLine();
			}

			// 숫자 입력받고 실행하기
			int N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				int num = Integer.parseInt(br.readLine());
				solve(num);
			}
			System.out.println();

			// 줄바꿈 없애기
			br.readLine();

			// 다음 명령어 입력받기
			temp = br.readLine();
		}
	}

	private static void solve(int num) {
		Deque<Long> stack = new ArrayDeque<>();
		stack.push((long) num);

		for (String op : ops) {
			switch (op) {
			case "POP":
				if (stack.isEmpty()) {
					System.out.println("ERROR");
					return;
				}
				stack.pop();
				break;
			case "INV":
				if (stack.isEmpty()) {
					System.out.println("ERROR");
					return;
				}
				Long curr = stack.pop() * -1;
				stack.push(curr);
				break;
			case "DUP":
				if (stack.isEmpty()) {
					System.out.println("ERROR");
					return;
				}
				stack.push(stack.peek());
				break;
			case "SWP":
				if (stack.size() < 2) {
					System.out.println("ERROR");
					return;
				}
				long first = stack.pop();
				long second = stack.pop();
				stack.push(first);
				stack.push(second);
				break;
			case "ADD":
				if (stack.size() < 2) {
					System.out.println("ERROR");
					return;
				}
				first = stack.pop();
				second = stack.pop();
				long result = first + second;
				if (Math.abs(result) > 1000000000) {
					System.out.println("ERROR");
					return;
				}
				stack.push(result);
				break;
			case "SUB":
				if (stack.size() < 2) {
					System.out.println("ERROR");
					return;
				}
				first = stack.pop();
				second = stack.pop();
				result = second - first;
				if (Math.abs(result) > 1000000000) {
					System.out.println("ERROR");
					return;
				}
				stack.push(result);
				break;
			case "MUL":
				if (stack.size() < 2) {
					System.out.println("ERROR");
					return;
				}
				first = stack.pop();
				second = stack.pop();
				result = second * first;
				if (Math.abs(result) > 1000000000) {
					System.out.println("ERROR");
					return;
				}
				stack.push(result);
				break;
			case "DIV":
				if (stack.size() < 2 || stack.peek() == 0) {
					System.out.println("ERROR");
					return;
				}
				first = stack.pop();
				second = stack.pop();
				result = Math.abs(second) / Math.abs(first);
				if (Math.abs(result) > 1000000000) {
					System.out.println("ERROR");
					return;
				}
				if ((first < 0 && second > 0) || (first > 0 && second < 0)) {
					result *= -1;
				}
				stack.push(result);
				break;
			case "MOD":
				if (stack.size() < 2 || stack.peek() == 0) {
					System.out.println("ERROR");
					return;
				}
				first = stack.pop();
				second = stack.pop();
				result = Math.abs(second) % Math.abs(first);
				if (Math.abs(result) > 1000000000) {
					System.out.println("ERROR");
					return;
				}
				if (second < 0) {
					result *= -1;
				}
				stack.push(result);
				break;
			default:
				stack.push(Long.parseLong(op.split(" ")[1]));
				break;
			}
		}

		if (stack.size() != 1) {
			System.out.println("ERROR");
		} else {
			System.out.println(stack.pop());
		}
	}
}
