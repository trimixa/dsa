package dsa.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LongestSubarrayWithSumK {
    public int longestSubarray(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(i,sum+=nums[i]);
        }
        for(int sumIndex: hashMap.values()){

        }
    }
}
