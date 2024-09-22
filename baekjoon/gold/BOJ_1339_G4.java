import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static String[] words;
	static Map<Character, String> priority;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		words = new String[N];
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}
		priority = new HashMap<>();
		solve();
	}
	
	private static void solve() {
		for (String word : words) {
			word = new StringBuilder(word).reverse().toString();
			for (int i = 0; i < word.length(); i++) {
				Character c = word.charAt(i);
				if (priority.containsKey(c)) {
					char[] visited = priority.get(c).toCharArray();
					int idx = 9 - i;
					while (visited[idx] == '9') {
						visited[idx] = '0';
						idx--;
					}
					visited[idx] = (char) (visited[idx] + 1);
					priority.put(c, new String(visited));
				} else {
					char[] visited = new char[10];
					Arrays.fill(visited, '0');
					visited[9 - i] = (char) (visited[9 - i] + 1);
					priority.put(c, new String(visited));
				}
			}
		}
		
		List<Character> keys = new ArrayList<>(priority.keySet());
		keys.sort((c1, c2) -> priority.get(c2).compareTo(priority.get(c1)));
		
		int num = 9;
		Map<Character, Integer> numMap = new HashMap<>();
		for (Character key : keys) {
//			System.out.println(key + " " + priority.get(key));
			numMap.put(key, num--);
		}
		
		int sum = 0;
		for (String word : words) {
//			System.out.println(word);
			for (int i = 0; i < word.length(); i++) {
//				System.out.println(word.charAt(i) + "->" + numMap.get(word.charAt(i)));
				if (word.charAt(i) < (int) 'A') {
					continue;
				}
				word = word.replace(word.charAt(i), (char) (numMap.get(word.charAt(i)) + '0'));
			}
//			System.out.println(word);
			sum += Integer.parseInt(word);
		}
		
		System.out.println(sum);
	}
}
