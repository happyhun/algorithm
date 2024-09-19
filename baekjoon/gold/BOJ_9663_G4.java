import java.io.*;
import java.util.*;

public class Main {

	static int[] queenPositions;
	static int N;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		answer = 0;
		queenPositions = new int[N];
		
		solve(0);
		System.out.println(answer);
	}
	
	private static void solve(int count) {
		if (count == N) {
			answer++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			queenPositions[count] = i;
			if (canPut(count)) {
				solve(count + 1);
			}
		}
	}
	
	private static boolean canPut(int count) {
		for (int i = 0; i < count; i++) {
			if (queenPositions[i] == queenPositions[count]) {
				return false;
			}
			if (Math.abs(i - count) == Math.abs(queenPositions[i] - queenPositions[count])) {
				return false;
			}
		}
		
		return true;
	}
}
