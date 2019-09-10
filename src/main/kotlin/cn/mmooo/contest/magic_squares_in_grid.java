package cn.mmooo.contest;

public class magic_squares_in_grid {
    /**
     * 3 x 3 的幻方是一个填充有从 1 到 9 的不同数字的 3 x 3 矩阵，其中每行，每列以及两条对角线上的各数之和都相等。
     * <p>
     * 给定一个由整数组成的 N × N 矩阵，其中有多少个 3 × 3 的 “幻方” 子矩阵？（每个子矩阵都是连续的）。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * <p>
     * [4,3,8,4],
     * [9,5,1,9],
     * [2,7,6,2],
     * 输出: 1
     * 解释:
     * 下面的子矩阵是一个 3 x 3 的幻方：
     * 438
     * 951
     * 276
     * <p>
     * 而这一个不是：
     * 384
     * 519
     * 762
     * <p>
     * 总的来说，在本示例所给定的矩阵中只有一个 3 x 3 的幻方子矩阵。
     *
     * @param grid
     * @return
     */
    public int numMagicSquaresInside(int[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (checkIsMagicSquares(i, j, grid)) {
                    result++;
                }
            }
        }
        return result;
    }

    private boolean checkIsMagicSquares(int x, int y, int[][] grid) {
        if (x > grid.length - 3) return false;
        if (y > grid[0].length - 3) return false;

        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (grid[i][j] > 9) return false;
                if (grid[i][j] < 1) return false;
            }
        }

        int temp;
        int sum = grid[x][y] + grid[x + 1][y] + grid[x + 2][y];
        for (int i = 0; i < 3; i++) {
            temp = grid[x][y + i] + grid[x + 1][y + i] + grid[x + 2][y + i];
            if (temp != sum) return false;

            temp = grid[x + i][y] + grid[x + i][y + 1] + grid[x + i][y + 2];
            if (temp != sum) return false;

        }

        temp = grid[x][y] + grid[x + 1][y + 1] + grid[x + 2][y + 2];
        if (temp != sum) return false;

        temp = grid[x][y + 2] + grid[x + 1][y + 1] + grid[x + 2][y];
        return temp == sum;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {10, 3, 5}, {1, 6, 11}, {7, 9, 2}
        };
        int i = new magic_squares_in_grid().numMagicSquaresInside(grid);
        System.out.println(i);
    }
}
