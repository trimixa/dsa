package dsa.utility;

import java.util.*;

public class BinaryTreeGenerator {
    private static final Random random = new Random();

    // TreeNode Class
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

    // RANDOM TREES
    //Generate random binary tree with given depth
    public static TreeNode generateRandomTree(int depth) {
        if (depth == 0) return null;
        return generateRandomTreeHelper(depth);
    }

    private static TreeNode generateRandomTreeHelper(int depth) {
        if (depth == 0 || random.nextDouble() < 0.3) return null; // 30% chance of null
        TreeNode node = new TreeNode(random.nextInt(100));
        node.left = generateRandomTreeHelper(depth - 1);
        node.right = generateRandomTreeHelper(depth - 1);
        return node;
    }

    //COMPLETE BINARY TREE
    //Generate complete binary tree (all levels filled except last)
    public static TreeNode generateCompleteBinaryTree(int nodeCount) {
        if (nodeCount <= 0) return null;
        TreeNode root = new TreeNode(random.nextInt(100));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 1;
        while (!queue.isEmpty() && count < nodeCount) {
            TreeNode current = queue.poll();
            // Add left child
            if (count < nodeCount) {
                current.left = new TreeNode(random.nextInt(100));
                queue.add(current.left);
                count++;
            }
            // Add right child
            if (count < nodeCount) {
                current.right = new TreeNode(random.nextInt(100));
                queue.add(current.right);
                count++;
            }
        }
        return root;
    }

    //PERFECT BINARY TREE
    //Generate perfect binary tree (all levels completely filled)
    public static TreeNode generatePerfectBinaryTree(int depth) {
        if (depth == 0) return null;
        return generatePerfectBinaryTreeHelper(depth);
    }

    private static TreeNode generatePerfectBinaryTreeHelper(int depth) {
        if (depth == 0) return null;
        TreeNode node = new TreeNode(random.nextInt(100));
        if (depth > 1) {
            node.left = generatePerfectBinaryTreeHelper(depth - 1);
            node.right = generatePerfectBinaryTreeHelper(depth - 1);
        }
        return node;
    }

    //BALANCED BINARY TREE
    //Generate balanced binary tree
    public static TreeNode generateBalancedBinaryTree(int depth) {
        if (depth == 0) return null;
        return generateBalancedBinaryTreeHelper(depth);
    }

    private static TreeNode generateBalancedBinaryTreeHelper(int depth) {
        if (depth == 0) return null;
        TreeNode node = new TreeNode(random.nextInt(100));
        if (depth > 1) {
            node.left = generateBalancedBinaryTreeHelper(depth - 1);
            node.right = generateBalancedBinaryTreeHelper(depth - 1);
        }
        return node;
    }

    //SKEWED TREES
    //Generate left-skewed tree (linked list-like)
    public static TreeNode generateLeftSkewedTree(int depth) {
        if (depth == 0) return null;
        TreeNode root = new TreeNode(random.nextInt(100));
        TreeNode current = root;

        for (int i = 1; i < depth; i++) {
            current.left = new TreeNode(random.nextInt(100));
            current = current.left;
        }
        return root;
    }

    //Generate right-skewed tree (linked list-like)
    public static TreeNode generateRightSkewedTree(int depth) {
        if (depth == 0) return null;
        TreeNode root = new TreeNode(random.nextInt(100));
        TreeNode current = root;
        for (int i = 1; i < depth; i++) {
            current.right = new TreeNode(random.nextInt(100));
            current = current.right;
        }
        return root;
    }

    //BST (BINARY SEARCH TREE)
    //Generate random BST
    public static TreeNode generateRandomBST(int nodeCount, int min, int max) {
        if (nodeCount <= 0) return null;
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
        if (val < root.val) {
            root.left = insertBST(root.left, val);
        } else {
            root.right = insertBST(root.right, val);
        }
        return root;
    }

    //FROM LEVEL-ORDER ARRAY
    //Generate tree from level-order array (null represents missing node)
    public static TreeNode generateFromLevelOrder(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) return null;
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode current = queue.poll();
            // Left child
            if (i < values.length && values[i] != null) {
                current.left = new TreeNode(values[i]);
                queue.add(current.left);
            }
            i++;
            // Right child
            if (i < values.length && values[i] != null) {
                current.right = new TreeNode(values[i]);
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }

    // UTILITY METHODS
    //Print tree in level-order
    public static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                System.out.print(current.val + " ");
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
            System.out.println();
        }
    }

    //Print tree in inorder
    public static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    //Count nodes in tree
    public static int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    //Get height of tree
    public static int getHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    // DEMO
    public static void main(String[] args) {
        System.out.println("=== Binary Tree Generator Demo ===\n");
        // Random tree
        System.out.println("Random Tree (depth 3):");
        TreeNode randomTree = generateRandomTree(3);
        printLevelOrder(randomTree);
        // Complete tree
        System.out.println("\nComplete Tree (7 nodes):");
        TreeNode completeTree = generateCompleteBinaryTree(7);
        printLevelOrder(completeTree);
        // Perfect tree
        System.out.println("\nPerfect Tree (depth 3):");
        TreeNode perfectTree = generatePerfectBinaryTree(3);
        printLevelOrder(perfectTree);
        // Left skewed
        System.out.println("\nLeft Skewed Tree (depth 5):");
        TreeNode leftSkewed = generateLeftSkewedTree(5);
        printLevelOrder(leftSkewed);
        // BST
        System.out.println("\nRandom BST (5 nodes):");
        TreeNode bst = generateRandomBST(5, 1, 50);
        printLevelOrder(bst);
        // From level order
        System.out.println("\nFrom Level Order [1, 2, 3, 4, 5]:");
        TreeNode fromArray = generateFromLevelOrder(new Integer[]{1, 2, 3, 4, 5});
        printLevelOrder(fromArray);
        System.out.println("\nHeight: " + getHeight(fromArray));
        System.out.println("Node Count: " + countNodes(fromArray));
    }
}