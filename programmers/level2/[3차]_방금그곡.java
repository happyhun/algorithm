import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        List<Music> musics = new ArrayList<>();
        m = m
                .replace("C#", "V")
                .replace("D#", "W")
                .replace("F#", "X")
                .replace("G#", "Y")
                .replace("A#", "Z");

        for (String musicinfo : musicinfos) {
            musicinfo = musicinfo
                    .replace("C#", "V")
                    .replace("D#", "W")
                    .replace("F#", "X")
                    .replace("G#", "Y")
                    .replace("A#", "Z");

            Music music = createMusic(musicinfo);
            musics.add(music);
        }

        Collections.sort(musics);

        for (Music music : musics) {
            if (isSubString(music.playMelody, m)) {
                return music.title;
            }
        }

        return "(None)";

    }

    private Music createMusic(String str) {
        String[] temp = str.split(",");

        int playTime = getPlayTime(temp[0], temp[1]);

        String title = temp[2];
        String playMelody = getPlayMelody(playTime, temp[3]);

        return new Music(playTime, title, playMelody);
    }

    private int getPlayTime(String s1, String s2) {
        int hour = Integer.parseInt(s2.split(":")[0]) - Integer.parseInt(s1.split(":")[0]);
        int minute = Integer.parseInt(s2.split(":")[1]) - Integer.parseInt(s1.split(":")[1]);

        return hour * 60 + minute;
    }

    private String getPlayMelody(int playTime, String melody) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < playTime; i++) {
            sb.append(melody.charAt(i % melody.length()));
        }

        return sb.toString();
    }

    private boolean isSubString(String playMelody, String melody) {
        if (playMelody.length() < melody.length())
            return false;

        for (int i = 0; i < playMelody.length() - melody.length() + 1; i++) {
            if (melody.equals(playMelody.substring(i, i + melody.length()))
                    && (i + melody.length() == playMelody.length() || playMelody.charAt(i + melody.length()) != '#'))
                return true;
        }

        return false;
    }

    static class Music implements Comparable<Music> {
        int playTime;
        String title;
        String playMelody;

        public Music(int playTime, String title, String playMelody) {
            this.playTime = playTime;
            this.title = title;
            this.playMelody = playMelody;
        }

        @Override
        public int compareTo(Music m) {
            return m.playTime - this.playTime;
        }

        @Override
        public String toString() {
            return String.format("%d %s %s", playTime, title, playMelody);
        }
    }
}