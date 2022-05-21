//给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。 
//
// 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。 
//
// 返回 你能获得的 最大 利润 。 
//
// 
//
// 示例 1： 
//
// 
//输入：prices = [7,1,5,3,6,4]
//输出：7
//解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
//     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
//     总利润为 4 + 3 = 7 。 
//
// 示例 2： 
//
// 
//输入：prices = [1,2,3,4,5]
//输出：4
//解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
//     总利润为 4 。 
//
// 示例 3： 
//
// 
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0 。 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 3 * 10⁴ 
// 0 <= prices[i] <= 10⁴ 
// 
// Related Topics 贪心 数组 动态规划 👍 1706 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution122 {
    /**
     * [122]买卖股票的最佳时机 II : 贪心算法(局部选择->全局最优解)
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int maxProfit(int[] prices) {
        // 总收益(全局)
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            // 如果今天比昨天价高, 累加昨买今卖的收益(局部)
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class Test122 {
    public static void main(String[] args) {
        int[] prices = {1};
        System.out.println(firstMaxProfit3(prices));
    }

    public static int firstMaxProfit(int[] prices) {
        // 如果当天比明天高, 不买卖, 将买入时间设定为明天
        // 如果当天比昨天低, 买入
        // 如果明天比后天高, 卖出
        // 如果明天比后天低, 将卖出时间设定为后天, 买入时间设定为大后天
        int buy = 0;
        int buyAmount = 0;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (buy == 1) {
                profit += (prices[i] - buyAmount);
                buy = 0;
            }
            if (i == prices.length - 1) {
                break;
            }
            if (prices[i] < prices[i + 1]) {
                buy = 1;
                buyAmount = prices[i];
            }
        }
        return profit;
    }

    public static int firstMaxProfit2(int[] prices) {
        // 仓位状态
        int buy = 0;
        // 总收益
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            // 如果(昨天)建仓了, 清仓
            if (buy == 1) {
                profit += prices[i] - prices[i - 1];
                buy = 0;
            }
            // 如果是最后一天, 就不在购买了, 防数组溢出
            if (i == prices.length - 1) {
                break;
            }
            // 如果今天价格比明天低, 建仓
            if (prices[i] < prices[i + 1]) {
                buy = 1;
            }
        }
        return profit;
    }

    public static int firstMaxProfit3(int[] prices) {
        // 总收益
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            // 如果今天比昨天价高, 计算收益
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
}