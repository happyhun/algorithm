import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1759 {

    static int L;
    static String[] letters;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        letters = new String[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            letters[i] = st.nextToken();
        }

        Arrays.sort(letters);

        DFS(0, new StringBuilder());
    }

    private static void DFS(int idx, StringBuilder sb) {
        if (sb.length() == L) {
            if (isValid(sb)) {
                System.out.println(sb);
            }
            return;
        }

        if (idx >= letters.length) {
            return;
        }

        sb.append(letters[idx]);
        DFS(idx + 1, sb);
        sb.deleteCharAt(sb.length() - 1);
        DFS(idx + 1, sb);
    }

    private static boolean isValid(StringBuilder sb) {
        int mo = 0, ja = 0;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                mo++;
            } else {
                ja++;
            }
        }

        return (mo >= 1) && (ja >= 2);
    }
}
