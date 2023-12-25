public class mineSweep1 {
    private static final int[] DX = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] DY = {-1, 0, 1, -1, 1, -1, 0, 1};

    private static char[][] calculateMines(char[][] field, int n, int m) {
        char[][] res = new char[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (field[i][j] == '*') res[i][j] = '*';
                int mines = 0;  // 初始化周围雷的个数为0
                for (int k = 0; k < 8; k++) {
                    int x = i + DX[k];
                    int y = j + DY[k];
                    if (x >= 0 && x < n && y >= 0 && y < m && field[x][y] == '*') mines++;
                }
                if (field[i][j] != '*') res[i][j] = (char) ('0' + mines);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        char[][] field = {
                {'*', '*', '.', '.'},
                {'.', '.', '.', '.'},
                {'.', '*', '.', '.'},
                {'.', '.', '.', '.'}
        };
        char[][] res = calculateMines(field, field.length, field[0].length);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j]);
            }
            System.out.println();
        }
    }
}
