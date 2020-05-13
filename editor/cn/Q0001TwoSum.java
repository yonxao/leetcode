//1.两数之和
//two-sum
//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution0001 {
    // class Solution {
    // public static int[] twoSum(int[] nums, int target) {
    //     Map<Integer, Integer> map = new HashMap<>();
    //     for (int i = 0; i < nums.length; i++) {
    //         int complement = target - nums[i];
    //         if (map.containsKey(complement)) {
    //             return new int[]{map.get(complement), i};
    //         }
    //         map.put(nums[i], i);
    //     }
    //     throw new IllegalArgumentException("No two sum solution");
    // }
    public static int[] twoSum(int[] nums, int target) {
        // 解法理解：本质上是用数组模拟了一个哈希表，但是有缺陷，受限于构建的哈希表大小，数值在（-31,32）才正常使用且个数不能大于64个
        // 100000000000
        int volume = 64;
        // 011111111111
        int bitMode = volume - 1;
        int[] result = new int[volume];

        for (int i = 0; i < nums.length; i++) {
            int c = (target - nums[i]) & bitMode;
            if (result[c] != 0) {
                result[nums[i] & bitMode] = i + 1;
                System.out.println(Arrays.toString(result));
                for (int j = 0; j < result.length; j++) {
                    System.out.println(j + ":" + result[j]);
                }
                return new int[]{result[c] - 1, i};
            }
            result[nums[i] & bitMode] = i + 1;
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class TwoSum {
    public static void main(String[] args) {
        int[] nums = {-1, -3, 0, 30, -31, 32, 2, 7, 23, 11, 15};
        int target = 22;
        // int[] nums = {-1, -2, -3, -4, -5, 1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5,
        //         1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2,
        //         3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5,
        //         1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2,
        //         1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2,
        //         1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2,
        //         1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2,
        //         1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2,
        //         1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2,
        //         3, 4, 5, 65, 7, 8, 98, 9, 54, 5, 54, 345, 2, 3, 4, 5, 1, 2, 2,
        //         1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2,
        //         1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2,
        //         1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2, 3, 4, 5, 1, 2, 2,
        //         34};
        for (int i = 0; i < nums.length; i++) {
            System.out.println(i + ":" + nums[i]);
        }
        // int target = 443;

        // for (int i = 0; i < 5; i++) {
        // Solution solution = new Solution();
        long start1 = System.currentTimeMillis();
        System.out.print(Arrays.toString(Solution0001.twoSum(nums, target)));
        System.out.println("--1执行耗时: " + (System.currentTimeMillis() - start1) + " ms");

        long start2 = System.currentTimeMillis();
        System.out.print(Arrays.toString(Solution000102.twoSum(nums, target)));
        System.out.println("--2执行耗时: " + (System.currentTimeMillis() - start2) + " ms");

        long start3 = System.currentTimeMillis();
        System.out.print(Arrays.toString(Solution000103.twoSum(nums, target)));
        System.out.println("--3执行耗时: " + (System.currentTimeMillis() - start3) + " ms");

        long start4 = System.currentTimeMillis();
        System.out.print(Arrays.toString(Solution000104.twoSum(nums, target)));
        System.out.println("--4执行耗时: " + (System.currentTimeMillis() - start4) + " ms");

        long start5 = System.currentTimeMillis();
        System.out.print(Arrays.toString(Solution000105.twoSum(nums, target)));
        System.out.println("--5执行耗时: " + (System.currentTimeMillis() - start5) + " ms");

        // System.out.println("");
        // }
    }
}

class Solution000102 {
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}

class Solution000103 {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
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
}

class Solution000104 {
    public static int[] twoSum(int[] nums, int target) {
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


class Solution000105 {
    public static int[] twoSum(int[] nums, int target) {
        // 100000000000
        int volume = 2048;
        // 011111111111
        int bitMode = volume - 1;
        int[] result = new int[volume];

        for (int i = 0; i < nums.length; i++) {
            int c = (target - nums[i]) & bitMode;
            if (result[c] != 0) {
                return new int[]{result[c] - 1, i};
            }
            result[nums[i] & bitMode] = i + 1;
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}

class Solution000106My {
    public static int[] twoSum(int[] nums, int target) /*throws Exception*/ {
        int[] solution = new int[2];
        out:
        for (int i = 0; i < nums.length; i++) {
            // 总结反思：此处本来时想的做个优化，碰到大于结果的数直接跳过，结果应为自己考虑不全面，反而出错了
            // 第一反应：画蛇添足了，也是思维定式吧，没有考虑负数，只考虑了正数
            // 第二反应：不能被给定的条件将自己限定或被引导，bug 的外部条件千千万，要进行多方面的考虑，如对立思考
            // if (nums[i] > target) {
            //     continue;
            // }
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    solution[0] = i;
                    solution[1] = j;
                    break out;
                }
            }
        }
       /* if (solution[0] == solution[1]) {
            throw new Exception("无匹配");
        }*/
        return solution;
    }
}