//给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3,1]
//输出：true 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,4]
//输出：false 
//
// 示例 3： 
//
// 
//输入：nums = [1,1,1,3,3,4,3,2,4,2]
//输出：true 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// 
// Related Topics 数组 哈希表 排序 👍 736 👎 0


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution217 {
    /**
     * [217]存在重复元素 : 哈希表验证重复
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> hash = new HashSet<>(nums.length);
        for (int num : nums) {
            // 比 map 少寻址一次
            if (!hash.add(num)) {
                return true;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Test217 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        System.out.println(containsDuplicate3(nums));
    }


    public static boolean myContainsDuplicate(int[] nums) {
        Map<Integer, Integer> hash = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (hash.containsKey(nums[i])) {
                return true;
            }
            hash.put(nums[i], i);
        }
        return false;
    }


    /**
     * 哈希表
     * <p>
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> hash = new HashSet<>(nums.length);
        for (int num : nums) {
            if (!hash.add(num)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsDuplicate2(int[] nums) {
        return IntStream.of(nums).distinct().count() != nums.length;
    }

    /**
     * 大神写法
     */
    public static boolean containsDuplicate3(int[] nums) {
        return sort(nums, 0, nums.length - 1);
    }

    public static boolean sort(int[] nums, int left, int right) {
        if (left == right) {
            return false;
        }

        int mid = left + (right - left) / 2;
        // 分治算法
        if (sort(nums, left, mid) || sort(nums, mid + 1, right)) {
            return true;
        }

        // 不能再分
        if (nums[mid] < nums[mid + 1]) {
            return false;
        }
        // 没看懂, 先留着吧
        else {
            int[] temps = new int[right - left + 1];
            int i = left, j = mid + 1, k = 0;
            while (i <= mid && j <= right) {
                if (nums[i] > nums[j]) {
                    temps[k++] = nums[j++];
                } else if (nums[i] < nums[j]) {
                    temps[k++] = nums[i++];
                } else {
                    return true;
                }
            }
            while (i <= mid) {
                temps[k++] = nums[i++];
            }
            for (k = 0, i = left; i < j; ) {
                nums[i++] = temps[k++];
            }
            return false;
        }
    }
}