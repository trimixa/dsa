package dsa.utility;

import java.util.*;

/**
 * Utility class for generating and visualizing various types of Binary Trees.
 * Ideal for testing Data Structures and Algorithms (DSA) solutions locally.
 */
public class BinaryTreeGenerator {
    private static final Random random = new Random();

    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    // ==========================================
    // RANDOM & STRUCTURAL TREES
    // ==========================================

    /**
     * Generates a completely random binary tree with a maximum depth.
     * Nodes have a 30% chance of terminating early to create uneven structures.
     *
     * @param depth Maximum depth of the tree.
     * @return The root of the random binary tree.
     * @throws IllegalArgumentException if depth is negative.
     */
    public static TreeNode generateRandomTree(int depth) {
        if (depth < 0) throw new IllegalArgumentException("Depth cannot be negative.");
        if (depth == 0) return null;
        return generateRandomTreeHelper(depth);
    }

    private static TreeNode generateRandomTreeHelper(int depth) {
        if (depth == 0 || random.nextDouble() < 0.3) return null;
        TreeNode node = new TreeNode(random.nextInt(100));
        node.left = generateRandomTreeHelper(depth - 1);
        node.right = generateRandomTreeHelper(depth - 1);
        return node;
    }

    /**
     * Generates a Complete Binary Tree (all levels filled left-to-right).
     *
     * @param nodeCount Total number of nodes in the tree.
     * @return The root of the complete binary tree.
     * @throws IllegalArgumentException if nodeCount is negative.
     */
    public static TreeNode generateCompleteBinaryTree(int nodeCount) {
        if (nodeCount < 0) throw new IllegalArgumentException("Node count cannot be negative.");
        if (nodeCount == 0) return null;

        TreeNode root = new TreeNode(random.nextInt(100));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 1;

        while (!queue.isEmpty() && count < nodeCount) {
            TreeNode current = queue.poll();

            if (count < nodeCount) {
                current.left = new TreeNode(random.nextInt(100));
                queue.add(current.left);
                count++;
            }
            if (count < nodeCount) {
                current.right = new TreeNode(random.nextInt(100));
                queue.add(current.right);
                count++;
            }
        }
        return root;
    }

    /**
     * Generates a Perfect Binary Tree (all internal nodes have two children, all leaves at same level).
     *
     * @param depth The exact depth of the perfect tree.
     * @return The root of the perfect binary tree.
     * @throws IllegalArgumentException if depth is negative.
     */
    public static TreeNode generatePerfectBinaryTree(int depth) {
        if (depth < 0) throw new IllegalArgumentException("Depth cannot be negative.");
        if (depth == 0) return null;

        TreeNode node = new TreeNode(random.nextInt(100));
        if (depth > 1) {
            node.left = generatePerfectBinaryTree(depth - 1);
            node.right = generatePerfectBinaryTree(depth - 1);
        }
        return node;
    }

    // ==========================================
    // SKEWED TREES (EDGE CASE TESTING)
    // ==========================================

    /**
     * Generates a strictly left-skewed tree (effectively a Linked List).
     * Ideal for testing stack overflow / worst-case O(N) traversal times.
     *
     * @param nodeCount Number of nodes.
     * @return The root of the skewed tree.
     */
    public static TreeNode generateLeftSkewedTree(int nodeCount) {
        if (nodeCount < 0) throw new IllegalArgumentException("Node count cannot be negative.");
        if (nodeCount == 0) return null;

        TreeNode root = new TreeNode(random.nextInt(100));
        TreeNode current = root;
        for (int i = 1; i < nodeCount; i++) {
            current.left = new TreeNode(random.nextInt(100));
            current = current.left;
        }
        return root;
    }

    /**
     * Generates a strictly right-skewed tree.
     *
     * @param nodeCount Number of nodes.
     * @return The root of the skewed tree.
     */
    public static TreeNode generateRightSkewedTree(int nodeCount) {
        if (nodeCount < 0) throw new IllegalArgumentException("Node count cannot be negative.");
        if (nodeCount == 0) return null;

        TreeNode root = new TreeNode(random.nextInt(100));
        TreeNode current = root;
        for (int i = 1; i < nodeCount; i++) {
            current.right = new TreeNode(random.nextInt(100));
            current = current.right;
        }
        return root;
    }

    // ==========================================
    // BINARY SEARCH TREES (BST)
    // ==========================================

    /**
     * Generates a random, unsorted Binary Search Tree.
     * * @param nodeCount Number of unique nodes.
     * @param min Minimum allowable value.
     * @param max Maximum allowable value.
     * @return The root of the BST.
     * @throws IllegalArgumentException if inputs are invalid or impossible to fulfill.
     */
    public static TreeNode generateRandomBST(int nodeCount, int min, int max) {
        if (nodeCount < 0) throw new IllegalArgumentException("Node count cannot be negative.");
        if (min > max) throw new IllegalArgumentException("Min value cannot be greater than Max value.");
        // THE FIX: Prevent infinite loop if user asks for more unique nodes than the range allows
        if (nodeCount > (max - min + 1)) {
            throw new IllegalArgumentException(
                    "Impossible constraint: Requested " + nodeCount + " unique nodes, but range [" + min + "-" + max + "] only holds " + (max - min + 1) + " values."
            );
        }

        TreeNode root = null;
        Set<Integer> used = new HashSet<>();
        for (int i = 0; i < nodeCount; i++) {
            int val;
            do {
                val = random.nextInt(max - min + 1) + min;
            } while (used.contains(val));
            used.add(val);
            root = insertBST(root, val);
        }
        return root;
    }

    private static TreeNode insertBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insertBST(root.left, val);
        else root.right = insertBST(root.right, val);
        return root;
    }

    /**
     * Generates a perfectly Balanced Binary Search Tree (AVL-like).
     *
     * @param nodeCount Number of nodes.
     * @param min Minimum value.
     * @param max Maximum value.
     * @return The root of the balanced BST.
     */
    public static TreeNode generateBalancedBST(int nodeCount, int min, int max) {
        if (nodeCount < 0 || min > max || nodeCount > (max - min + 1)) {
            throw new IllegalArgumentException("Invalid bounds for Balanced BST generation.");
        }

        // 1. Generate unique numbers
        Set<Integer> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size() < nodeCount) {
            uniqueNumbers.add(random.nextInt(max - min + 1) + min);
        }

        // 2. Sort them
        List<Integer> sortedList = new ArrayList<>(uniqueNumbers);
        Collections.sort(sortedList);

        // 3. Build tree from the middle out
        return buildBalancedBSTHelper(sortedList, 0, sortedList.size() - 1);
    }

    private static TreeNode buildBalancedBSTHelper(List<Integer> nodes, int start, int end) {
        if (start > end) return null;

        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(nodes.get(mid));

        node.left = buildBalancedBSTHelper(nodes, start, mid - 1);
        node.right = buildBalancedBSTHelper(nodes, mid + 1, end);

        return node;
    }

    // ==========================================
    // UTILITY BUILDERS & PRINTERS
    // ==========================================

    /**
     * Generates a tree from a LeetCode-style Level-Order array.
     * Null represents a missing child node.
     *
     * @param values The array of Integer values.
     * @return The root of the tree.
     */
    public static TreeNode generateFromLevelOrder(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) return null;

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;

        while (!queue.isEmpty() && i < values.length) {
            TreeNode current = queue.poll();

            if (i < values.length && values[i] != null) {
                current.left = new TreeNode(values[i]);
                queue.add(current.left);
            }
            i++;

            if (i < values.length && values[i] != null) {
                current.right = new TreeNode(values[i]);
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }

    public static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("[]");
            return;
        }
        List<String> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current != null) {
                result.add(String.valueOf(current.val));
                queue.add(current.left);
                queue.add(current.right);
            } else {
                result.add("null");
            }
        }

        // Trim trailing nulls to match LeetCode output style
        while (!result.isEmpty() && result.get(result.size() - 1).equals("null")) {
            result.remove(result.size() - 1);
        }
        System.out.println("[" + String.join(", ", result) + "]");
    }
}