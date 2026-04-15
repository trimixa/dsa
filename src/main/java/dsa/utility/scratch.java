package dsa.utility;

import java.util.Random;

public class scratch {
    public static final Random random = new Random();

    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static TreeNode generateRandomTree(int depth) {
        if (depth < 0)
            throw new IllegalArgumentException("D E P T H  OF  T R E E   C A N N O T   B E   N E G A T I V E !");
        if (depth == 0) return null;
        return generateRandomTreeHelper(depth);
    }

    public static TreeNode generateRandomTreeHelper(int depth) {

    }

}
