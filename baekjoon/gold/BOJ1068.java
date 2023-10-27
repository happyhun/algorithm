import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1068 {

    static int answer = 0;
    static List<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        int root = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
                continue;
            }
            tree[parent].add(i);
        }

        int target = Integer.parseInt(br.readLine());
        DFS(-1, root, target);

        System.out.println(answer);
    }

    private static void DFS(int parent, int curr, int target) {
        if (curr == target) {
            if (parent != -1 && tree[parent].size() == 1)
                answer++;
            return;
        }

        if (tree[curr].isEmpty()) {
            answer++;
            return;
        }

        for (int next : tree[curr]) {
            DFS(curr, next, target);
        }
    }
}
