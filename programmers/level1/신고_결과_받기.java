import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        User[] users = new User[id_list.length];

        for (int i = 0; i < users.length; i++)
            users[i] = new User(id_list[i]);

        for (String r : report) {
            String[] temp = r.split(" ");
            for (User user : users) {
                if (user.name.equals(temp[0]))
                    user.reporting.add(temp[1]);
                if (user.name.equals(temp[1]))
                    user.reported.add(temp[0]);
            }
        }

        List<String> suspendedUsers = new ArrayList<>();
        for (User user : users) {
            if (user.reported.size() >= k)
                suspendedUsers.add(user.name);
        }

        int[] result = new int[users.length];
        for (int i = 0; i < result.length; i++) {
            int count = 0;
            for (String name : suspendedUsers) {
                if (users[i].reporting.contains(name))
                    count++;
            }
            result[i] = count;
        }

        return result;
    }

    static class User {
        String name;
        Set<String> reporting = new HashSet<>();
        Set<String> reported = new HashSet<>();

        public User(String name) {
            this.name = name;
        }
    }
}