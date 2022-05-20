//给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。 
//
// 你可以按任意顺序返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,7,11,15], target = 9
//输出：[0,1]
//解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,4], target = 6
//输出：[1,2]
// 
//
// 示例 3： 
//
// 
//输入：nums = [3,3], target = 6
//输出：[0,1]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 10⁴ 
// -10⁹ <= nums[i] <= 10⁹ 
// -10⁹ <= target <= 10⁹ 
// 只会存在一个有效答案 
// 
//
// 进阶：你可以想出一个时间复杂度小于 O(n²) 的算法吗？ 
// Related Topics 数组 哈希表 👍 14290 👎 0


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1 {
    /**
     * [1]两数之和 : 双哈希
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Test1 {
    public static void main(String[] args) {
        // int[] nums = {-1, -3, 0, 30, -31, 32, 2, 7, 23, 11, 15};
        int[] nums = {-1, -3, 0, 3, -3, 2, 2, 7, 2, 1, 5};
        int target = 7;

        // for (int i = 0; i < nums.length; i++) {
        //     System.out.println(i + ":" + nums[i]);
        // }

        System.out.println(Arrays.toString(nums));

        long start2 = System.nanoTime();
        System.out.print(Arrays.toString(twoSum02(nums, target)));
        System.out.println("--2执行耗时: " + (System.nanoTime() - start2) + " ns");

        long start3 = System.nanoTime();
        System.out.print(Arrays.toString(twoSum03(nums, target)));
        System.out.println("--3执行耗时: " + (System.nanoTime() - start3) + " ns");

        long start4 = System.nanoTime();
        System.out.print(Arrays.toString(twoSum04(nums, target)));
        System.out.println("--4执行耗时: " + (System.nanoTime() - start4) + " ns");

        long start5 = System.nanoTime();
        System.out.print(Arrays.toString(twoSum05(nums, target)));
        System.out.println("--5执行耗时: " + (System.nanoTime() - start5) + " ns");

    }


    /**
     * 暴力法：
     * 暴力法很简单，遍历每个元素 x，并查找是否存在一个值与 (target - x) 相等的目标元素。
     * 两层 for 循环，n * n
     * <p>
     * 复杂度分析：
     * 时间复杂度：O(n^2)
     * 对于每个元素，我们试图通过遍历数组的其余部分来寻找它所对应的目标元素，这将耗费 O(n) 的时间。因此时间复杂度为 O(n^2)。
     * 空间复杂度：O(1)
     */
    public static int[] twoSum02(int[] nums, int target) {
        // 遍历整个数组 n次
        for (int i = 0; i < nums.length; i++) {
            // 当前元素和 后面剩下的元素对比 (n-1)+(n-2)+(n-3)+...+(n-(n-1))
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    /**
     * 两遍哈希表
     * 为了对运行时间复杂度进行优化，我们需要一种更有效的方法来检查数组中是否存在目标元素。如果存在，我们需要找出它的索引。
     * 保持数组中的每个元素与其索引相互对应的最好方法是什么？哈希表。
     * <p>
     * 通过以空间换取速度的方式，我们可以将查找时间从 O(n) 降低到 O(1)。哈希表正是为此目的而构建的，
     * 它支持以“近似“恒定的时间进行快速查找。我用“近似”来描述，是因为一旦出现冲突，查找用时可能会退化到 O(n)。
     * 但只要你仔细地挑选哈希函数，在哈希表中进行查找的用时应当被摊销为 O(1)。
     * <p>
     * 一个简单的实现使用了两次迭代。在第一次迭代中，我们将每个元素的值和它的索引添加到表中。
     * 然后，在第二次迭代中，我们将检查每个元素所对应的目标元素（target - nums[i]）是否存在于表中。
     * 注意，该目标元素不能是 nums[i] 本身！
     * <p>
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 我们把包含有 n 个元素的列表遍历两次。由于哈希表将查找时间缩短到 O(1)，所以时间复杂度为 O(n)。
     * 空间复杂度：O(n)
     * 所需的额外空间取决于哈希表中存储的元素数量，该表中存储了 n 个元素。
     */
    public static int[] twoSum03(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    /**
     * 一遍哈希表
     * 事实证明，我们可以一次完成。在进行迭代并将元素插入到表中的同时，我们还会回过头来检查表中是否已经存在当前元素所对应的目标元素。
     * 如果它存在，那我们已经找到了对应解，并立即将其返回。
     * <p>
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 我们只遍历了包含有 n 个元素的列表一次。在表中进行的每次查找只花费 O(1)的时间。
     * 空间复杂度：O(n)
     * 所需的额外空间取决于哈希表中存储的元素数量，该表最多需要存储 n 个元素。
     */
    public static int[] twoSum04(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    /**
     * 使用 数组 构建一个哈希表，一次循环
     * 本质上是用数组模拟了一个哈希表，缺陷是无法自动扩容
     * 受限于构建的哈希表大小，数值在（-1024,1023）才正常使用且个数不能大于2048个
     * <p>
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间负责度：O(n)
     */
    public static int[] twoSum05(int[] arr, int target) {
        // 构建哈希表大小，2048 = 2^11 = 0000 0000 0000 0000 0000 1000 0000 0000
        int volume = 2048;
        // 位运算（与运算）模板，0000 0000 0000 0000 0111 1111 1111，为了计算下标，防止数组越界
        int bitMode = volume - 1;
        // 哈希表容器（数组下标：表示元素值，数组元素：表示下标代表的元素在原数组中的索引）
        int[] result = new int[volume];

        // 遍历排序数组
        for (int i = 0; i < arr.length; i++) {
            // （目标值-当前数组元素的值）的差值，和模板与运算，得出下标(这一步为了防止数组越界)
            int c = (target - arr[i]) & bitMode;
            // 初始化的数组中，元素值全部为0，如果不为零，则代表的是排序数组中的元素索引+1 的值
            if (result[c] != 0) {
                // 返回数组下标中存的值-1，和当前值
                // System.out.println(Arrays.toString(result));
                return new int[]{result[c] - 1, i};
            }
            // 为了避免索引0和哈希表容器中默认值冲突，将索引值+1存到实际值对应的下标的空间中
            result[arr[i] & bitMode] = i + 1;
            // System.out.println(Arrays.toString(result));
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    /**
     * 自己最初的解法，其实就是暴力循环解法，但是自己写的漏洞百出
     */
    public static int[] twoSumMy(int[] nums, int target) /*throws Exception*/ {
        /* 总结反思：此处本来时想的做个优化，碰到大于结果的数直接跳过，结果应为自己考虑不全面，反而出错了
         * 第一反应：画蛇添足了，也是思维定式吧，没有考虑负数，只考虑了正数
         * 第二反应：不能被 给定的条件 将自己限定或被引导，bug 的外部条件千千万，要进行多方面的考虑，如对立思考
         */
        int[] solution = new int[2];
        out:
        for (int i = 0; i < nums.length; i++) {
            /*
            if (nums[i] > target) {
                continue;
            }
            */
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    solution[0] = i;
                    solution[1] = j;
                    break out;
                }
            }
        }
        /*
        if (solution[0] == solution[1]) {
            throw new Exception("无匹配");
        }
        */
        return solution;
    }
}