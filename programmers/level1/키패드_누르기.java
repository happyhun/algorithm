class Solution {

    static int left = 10;
    static int right = 12;

    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();

        for (int number : numbers) {
            if (number == 0)
                number = 11;

            switch (number) {
                case 1: case 4: case 7:
                    sb.append("L");
                    left = number;
                    break;
                case 3: case 6: case 9:
                    sb.append("R");
                    right = number;
                    break;
                default:
                    if (getCloseFinger(number, hand).equals("left")) {
                        sb.append("L");
                        left = number;
                    } else {
                        sb.append("R");
                        right = number;
                    }
            }
        }

        return sb.toString();
    }

    private String getCloseFinger(int number, String hand) {
        if (calculateDistance(right, number) == calculateDistance(left, number))
            return hand;

        if (calculateDistance(right, number) < calculateDistance(left, number))
            return "right";

        return "left";
    }

    private int calculateDistance(int curr, int target) {
        return Math.abs(curr - target) / 3 + Math.abs(curr - target) % 3;
    }
}