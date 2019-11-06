package com.CK;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        new Solution().findPaths(2, 2, 2, 0, 0);
    }
}

class Solution {
    private final static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int findPaths(int m, int n, int N, int i, int j) {
        if (N == 0)
            return 0;
        BigInteger[][][] dp = new BigInteger[m][n][N + 1];
        dfs(m, n, i, j, N, dp);
        BigInteger res = dp[i][j][N].mod(BigInteger.valueOf((long) Math.pow(10.0, 9.0) + 7));
        return res.intValue();
    }

    private BigInteger dfs(int m, int n, int r, int c, int k, BigInteger[][][] dp) {
        if (!posOnBoard(m, n, r, c)) {
            return BigInteger.ONE;
        }

        if (k == 0) {
            dp[r][c][k] = BigInteger.ZERO;
            return dp[r][c][k];
        }

        if (dp[r][c][k] != null) {
            return dp[r][c][k];
        }

        BigInteger sum = BigInteger.ZERO;
        for (int i = 0; i < 4; i++) {
            int nextR = dir[i][0] + r, nextC = dir[i][1] + c;
            sum = sum.add(dfs(m, n, nextR, nextC, k - 1, dp));
        }
        dp[r][c][k] = sum;
        return dp[r][c][k];
    }

    private boolean posOnBoard(int m, int n, int r, int c) {
        return r <= m - 1 && r >= 0 && c <= n - 1 && c >= 0;
    }
}

class Solution {
    int M = 1000000007;

    public int findPaths(int m, int n, int N, int i, int j) {
        Integer[][][] memo = new Integer[m][n][N + 1];
        return findPaths(m, n, N, i, j, memo);
    }

    public int findPaths(int m, int n, int N, int i, int j, Integer[][][] memo) {
        if (i == m || j == n || i < 0 || j < 0)
            return 1;
        if (N == 0)
            return 0;
        if (memo[i][j][N] != null)
            return memo[i][j][N];
        memo[i][j][N] = ((findPaths(m, n, N - 1, i - 1, j, memo) + findPaths(m, n, N - 1, i + 1, j, memo)) % M + (findPaths(m, n, N - 1, i, j - 1, memo) + findPaths(m, n, N - 1, i, j + 1, memo)) % M) % M;
        return memo[i][j][N];
    }
}