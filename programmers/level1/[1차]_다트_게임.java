class Solution {
    public int solution(String dartResult) {
        Game[] games = new Game[3];

        for (int i = 0; i < 3; i++)
            games[i] = new Game();

        int count = -1;

        for (int i = 0; i < dartResult.length(); i++) {
            char c = dartResult.charAt(i);

            if (Character.isDigit(c)) {
                if (i > 0 && c == '0' && dartResult.charAt(i - 1) == '1')
                    games[count].score = 10;
                else
                    games[++count].score = c - '0';
            }

            if (c == '*' || c == '#')
                games[count].option = c;
            else
                games[count].bonus = c;
        }

        for (int i = 0; i < 3; i++) {
            Game game = games[i];

            if (game.bonus == 'D')
                game.score = (int) Math.pow(game.score, 2);

            if (game.bonus == 'T')
                game.score = (int) Math.pow(game.score, 3);

            if (game.option == '*') {
                if (i > 0)
                    games[i - 1].score *= 2;
                game.score *= 2;
            }

            if (game.option == '#')
                game.score *= -1;
        }

        int answer = 0;

        for (Game game : games)
            answer += game.score;

        return answer;
    }

    static class Game {
        int score = 0;
        char bonus = 0;
        char option = 0;
    }
}