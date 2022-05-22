//给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,2,3,4,5,6,7], k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右轮转 1 步: [7,1,2,3,4,5,6]
//向右轮转 2 步: [6,7,1,2,3,4,5]
//向右轮转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 
//输入：nums = [-1,-100,3,99], k = 2
//输出：[3,99,-1,-100]
//解释: 
//向右轮转 1 步: [99,-1,-100,3]
//向右轮转 2 步: [3,99,-1,-100] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 0 <= k <= 10⁵ 
// 
//
// 
//
// 进阶： 
//
// 
// 尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。 
// 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？ 
// 
//
// 
// 
//
// 
// 
// Related Topics 数组 数学 双指针 👍 1482 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution189 {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        // 环路 若 k = 0, 环路为 0; 若 n = 0 , 环路为 0;
        int loop = n * k == 0 ? 0 : gcd(n, k);

        // 一个环路, 每次和头做交换, 数据不丢失
        for (int head = 0; head < loop; head++) {
            int current = head;
            do {
                int next = ((current + k) % n);
                // 把头放到 temp 中
                int temp = nums[head];
                // 把 next 放到头中
                nums[head] = nums[next];
                // 把 temp 放到 next 中
                nums[next] = temp;

                current = next;
            } while (current != head);
        }
    }

    private int gcd(int x, int y) {
        return y > 0 ? gcd(y, y % x) : x;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

class Test189 {
    public static void main(String[] args) {
        int[] testcase = {1, 2, 3, 4, 5, 6, 7};
        int[] testcase2 = {-1, -100, 3, 99};
        int[] testcase3 = {1};

        rotate3(testcase, 3);
        rotate3(testcase2, 2);
        rotate3(testcase3, 0);

        System.out.println(Arrays.toString(testcase));
        System.out.println(Arrays.toString(testcase2));
        System.out.println(Arrays.toString(testcase3));

    }


    /**
     * 一步一步替换
     * <p>
     * 时间复杂度 O(k*n)  <br>
     * 空间复杂度 O(1) <br>
     * <p>
     * k 为无穷大时, 超时, 部分用例通过
     * 深度分析: 环状替换的前身
     */
    public static void firstRotate(int[] nums, int k) {
        // [1,2,3,4,5,6,7]
        // [7,1,2,3,4,5,6]
        // [6,7,1,2,3,4,5]
        // [5,6,7,1,2,3,4]
        int length = nums.length;
        k %= length;

        for (int j = 0; j < k; j++) {
            int tail = nums[length - 1];
            for (int i = length - 1; i > 0; i--) {
                nums[i] = nums[(i - 1) % length];
            }
            nums[0] = tail;
        }
    }


    /**
     * 数组翻转
     * <p>
     * 时间复杂度 O(2n) = O(n)       <br>
     * 空间复杂度 O(1)
     */
    public static void rotate1(int[] nums, int k) {
        k %= nums.length;
        // 先将数组翻转
        // 然后翻转前 k 个, 再翻转后 length - k 个
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }


    /**
     * 使用额外数组
     * <p>
     * 时间复杂度 O(2n) = O(n)      <br>
     * 空间复杂度 O(n)
     */
    public static void rotate2(int[] nums, int k) {
        // [1,2,3,4,5,6,7]
        // [5,6,7,1,2,3,4]
        k %= nums.length;
        int length = nums.length;
        // 创建一个额外数组
        int[] newArr = new int[length];
        for (int i = 0; i < length; i++) {
            newArr[(i + k) % length] = nums[i];
        }
        /// for (int i = 0; i < nums.length; i++) {
        ///     nums[i] = newArr[i];
        /// }
        System.arraycopy(newArr, 0, nums, 0, length);
    }


    /**
     * 环状替换
     * <p>
     * 时间复杂度 O(n)  <br>
     * 空间复杂度 O(1)
     * <p>
     * 1 2 3 4 5 6 7 1 2 3 4 5 6 7 1 2 3 4 5 6 7                    <br>
     * 1     4     7     3     6     2     5                        <br>
     * |     |     |     |     |     |     |                        <br>
     * 1 2 3 4 5 6 7 1 2 3 4 5 6 7 1 2 3 4 5 6 7                    <br>
     * <p>
     * 数组长度定义为 n; 移动位数定义为 k;
     * (1 4 7 3 6 2 5 1)定义为环路(圈); 一个环路的元素个数(环路长度)定义为 b; 环路的个数定义为 x;
     * (1 2 3 4 5 6 7 1 2 3 4 5 6 7 1 2 3 4 5 6 7)定义为绕行路程;
     * <p>
     * 绕行路程长度 = b * k = n * x                                                                      <br>
     * 可以看出:<br>
     * 1. 一个绕行路程和多个绕行路程效果一致, 则取最小绕行路程, 而 n 是固定的, 所以相当于取最小的 x;                  <br>
     * 2. 当 k 大于 n 时, k 和 k %= n 效果一致;                                                             <br>
     * 3. 绕行路程长度可以被 n 和 k 整除, 即 绕行路程长度 = n * k, 又取最小绕行路程, 则 绕行路程长度 = lcm(n,k)     <br>
     * 4. 所有环路遍历完正好是整个数组, 则: n = x * b                                                         <br>
     * 所以: 绕行路程长度 = b * k = n * x = n * k = lcm(n,k)
     * <p>
     * x = n / b
     * b = lcm(n,k) / k
     * |nk| = lcm(n,k) * gcd(n,k)
     * 则 : x = n / (lcm(n,k) / k) = nk / lcm(n,k) = nk / (nk/gcd(n,k)) = gcd(n,k)
     * <p>
     * k 为非负数(题目给定), 且 k % n, 即 k <= n
     * n 为非负数(数组长度)
     * 若 k = 0, 环路为 0; 若 n = 0 , 环路为 0;
     */
    public static void rotate3(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        // 环路 若 k = 0, 环路为 0; 若 n = 0 , 环路为 0;
        int loop = n * k == 0 ? 0 : gcd(n, k);

        // 一个环路, 每次和头做交换, 数据不丢失
        for (int head = 0; head < loop; head++) {
            int current = head;
            do {
                int next = ((current + k) % n);

                // 把头放到 temp 中
                int temp = nums[head];
                // 把 next 放到头中
                nums[head] = nums[next];
                // 把 temp 放到 next 中
                nums[next] = temp;

                current = next;
            } while (current != head);
        }
    }

    /**
     * 最大公约数(限定条件非负数)
     */
    public static int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }
}