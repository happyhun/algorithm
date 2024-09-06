import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Photo[] photos = new Photo[N];
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int no = Integer.parseInt(st.nextToken());
			boolean posted = false;
			for (int j = 0; j < N; j++) {
				if (photos[j] != null && photos[j].no == no) {
					photos[j].vote++;
					posted = true;
					break;
				}
			}
			if (posted) {
				continue;
			}
			Photo photo = new Photo(no, 1, i);
			posted = false;
			for (int j = 0; j < N; j++) {
				if (photos[j] == null) {
					photos[j] = photo;
					posted = true;
					break;
				}
			}
			if (posted) {
				continue;
			}
			int idx = 0;
			int minVote = Integer.MAX_VALUE;
			int minTime = Integer.MAX_VALUE;
			for (int j = 0; j < N; j++) {
				if ((photos[j].vote < minVote) || (photos[j].vote == minVote && photos[j].time < minTime)) {
					idx = j;
					minVote = photos[j].vote;
					minTime = photos[j].time;
				}
			}
			photos[idx] = photo;
		}
		
		List<Integer> result = new ArrayList<>();
		for (int j = 0; j < N; j++) {
			if (photos[j] == null) {
				continue;
			}
			result.add(photos[j].no);
		}
		result.sort((o1, o2) -> o1 - o2);
		for (int no : result) {
			System.out.print(no + " ");
		}
	}

	private static class Photo {
		int no;
		int vote;
		int time;

		public Photo(int no, int vote, int time) {
			this.no = no;
			this.vote = vote;
			this.time = time;
		}
		
		public String toString() {
			return String.format("%d %d %d", no, vote, time);
		}
	}
}
