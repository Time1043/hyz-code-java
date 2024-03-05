
public class PingPongGame {
    public static String calculateScore11(String matchRecord) {
        int score11W = 0, score11L = 0;
        StringBuffer res11 = new StringBuffer();

        for (char c : matchRecord.toCharArray()) {
            if (c == 'E') {
                if (score11W != 0 || score11L != 0) res11.append(score11W).append(":").append(score11L).append("\n");
                break;
            }
            if (c == 'W') score11W++;
            if (c == 'L') score11L++;


            if (score11W >= 11 || score11L >= 11) {
                res11.append(score11W).append(":").append(score11L).append("\n");
                score11W = 0;
                score11L = 0;
            }
        }
        return res11.toString();
    }

    public static String calculateScore21(String matchRecord) {
        int score21W = 0, score21L = 0;
        StringBuffer res21 = new StringBuffer();

        for (char c : matchRecord.toCharArray()) {
            if (c == 'E') {
                if (score21W != 0 || score21L != 0) res21.append(score21W).append(":").append(score21L).append("\n");
                break;
            }
            if (c == 'W') score21W++;
            if (c == 'L') score21L++;

            if (score21W >= 21 || score21L >= 21) {
                res21.append(score21W).append(":").append(score21L).append("\n");
                score21W = 0;
                score21L = 0;
            }
        }
        return res21.toString();
    }

    public static void main(String[] args) {
        String matchRecord = "WWWWLWWWWLWWWWLWWWWLWWWWLWWWWLE";
        String res11 = calculateScore11(matchRecord);
        String res21 = calculateScore21(matchRecord);
        System.out.println(res11);
        System.out.println(res21);
    }
}
