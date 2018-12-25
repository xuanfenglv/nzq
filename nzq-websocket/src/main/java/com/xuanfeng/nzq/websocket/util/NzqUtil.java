package com.xuanfeng.nzq.websocket.util;

/**
 * @description: Nzq 工具类
 * @author: lvxianqing
 * @create: 2018/12/21 15:46
 */

public class NzqUtil {

    public static boolean isWin5(int[][] qipan, int row, int column) {
        if (isRowWin(qipan, row, column) || isColWin(qipan, row, column) || isX1Win(qipan, row, column) || isX2Win(qipan, row, column)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isRowWin(int[][] qipan, int row, int column) {
        int sum = 1;
        int c = qipan[row][column];
        for (int l = 1; l <= getMin2(4, column - 0); l++) {
            if (c == qipan[row][column - l]) {
                sum += 1;
            } else {
                break;
            }
        }
        if (sum == 5) {
            return true;
        }

        for (int r = 1; r <= getMin2(4, 14 - column); r++) {
            if (c == qipan[row][column + r]) {
                sum += 1;
            } else {
                break;
            }
        }
        if (sum > 4) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isColWin(int[][] qipan, int row, int column) {
        int sum = 1;
        int c = qipan[row][column];
        for (int t = 1; t <= getMin2(4, (row - 0)); t++) {
            if (c == qipan[row - t][column]) {
                sum += 1;
            } else {
                break;
            }
        }
        if (sum == 5) {
            return true;
        }

        for (int bb = 1; bb <= getMin2(4, 14 - row); bb++) {
            if (c == qipan[row + bb][column]) {
                sum += 1;
            } else {
                break;
            }
        }
        if (sum > 4) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isX1Win(int[][] qipan, int row, int column) {
        int sum = 1;
        int c = qipan[row][column];
        for (int l = 1; l <= getMin3(4, column - 0, row - 0); l++) { //三元式不加括号，当行列都是1时，等于2
            if (c == qipan[row - l][column - l]) {
                sum += 1;
            } else {
                break;
            }
        }

        if (sum == 5) {
            return true;
        }

        for (int r = 1; r <= getMin3(4, 14 - column, 14 - row); r++) {
            if (c == qipan[row + r][column + r]) {
                sum += 1;
            } else {
                break;
            }
        }

        if (sum > 4) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isX2Win(int[][] qipan, int row, int column) {
        int sum = 1;
        int c = qipan[row][column];
        for (int l = 1; l <= getMin3(4, 14 - column, row - 0); l++) {
            if (c == qipan[row - l][column + l]) {
                sum += 1;
            } else {
                break;
            }
        }

        if (sum == 5) {
            return true;
        }

        for (int r = 1; r <= getMin3(4, column - 0, 14 - row); r++) {
            if (c == qipan[row + r][column - r]) {
                sum += 1;
            } else {
                break;
            }
        }
        if (sum > 4) {
            return true;
        } else {
            return false;
        }
    }

    private static int getMin2(int no1, int no2) {
        return (no1 <= no2 ? no1 : no2);
    }

    private static int getMin3(int no1, int no2, int no3) {
        return getMin2(getMin2(no1, no2), no3);
    }

}
