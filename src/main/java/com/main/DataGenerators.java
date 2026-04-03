package com.main;

import java.util.*;

public class DataGenerators {

    private static final Random random = new Random();

    // ============================================
    // INTEGER ARRAYS
    // ============================================

    /**
     * Generate random positive integer array
     */
    public static int[] generatePositiveIntArray(int size, int min, int max) {
        validateRange(min, max);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
        return array;
    }

    /**
     * Generate mixed positive and negative integer array
     */
    public static int[] generateMixedIntArray(int size, int min, int max) {
        validateRange(min, max);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            int num = random.nextInt(max - min + 1) + min;
            array[i] = (random.nextBoolean()) ? num : -num;
        }
        return array;
    }

    /**
     * Generate sorted integer array (ascending)
     */
    public static int[] generateSortedIntArray(int size, int min, int max) {
        validateRange(min, max);
        int[] array = generatePositiveIntArray(size, min, max);
        Arrays.sort(array);
        return array;
    }

    /**
     * Generate sorted integer array (descending)
     */
    public static int[] generateReverseSortedIntArray(int size, int min, int max) {
        validateRange(min, max);
        int[] array = generatePositiveIntArray(size, min, max);
        Arrays.sort(array);
        reverse(array);
        return array;
    }

    /**
     * Generate array with duplicates
     */
    public static int[] generateArrayWithDuplicates(int size, int min, int max, double duplicatePercent) {
        validateRange(min, max);
        int[] array = new int[size];
        int duplicateCount = (int) (size * duplicatePercent);

        for (int i = 0; i < size - duplicateCount; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }

        for (int i = size - duplicateCount; i < size; i++) {
            array[i] = array[random.nextInt(size - duplicateCount)];
        }

        shuffle(array);
        return array;
    }

    // ============================================
    // 2D ARRAYS
    // ============================================

    /**
     * Generate 2D integer array
     */
    public static int[][] generate2DIntArray(int rows, int cols, int min, int max) {
        validateRange(min, max);
        int[][] array = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = random.nextInt(max - min + 1) + min;
            }
        }
        return array;
    }

    /**
     * Generate 2D matrix (square)
     */
    public static int[][] generateSquareMatrix(int size, int min, int max) {
        return generate2DIntArray(size, size, min, max);
    }

    // ============================================
    // STRING ARRAYS
    // ============================================

    /**
     * Generate random string array
     */
    public static String[] generateStringArray(int size, int minLength, int maxLength) {
        String[] array = new String[size];
        for (int i = 0; i < size; i++) {
            array[i] = generateRandomString(minLength, maxLength);
        }
        return array;
    }

    /**
     * Generate random string (lowercase letters only)
     */
    private static String generateRandomString(int minLength, int maxLength) {
        int length = random.nextInt(maxLength - minLength + 1) + minLength;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + random.nextInt(26)));
        }
        return sb.toString();
    }

    /**
     * Generate string array with duplicates
     */
    public static String[] generateStringArrayWithDuplicates(int size, int minLength, int maxLength) {
        String[] array = new String[size];
        String[] uniqueStrings = generateStringArray(size / 2, minLength, maxLength);

        for (int i = 0; i < size; i++) {
            array[i] = uniqueStrings[random.nextInt(uniqueStrings.length)];
        }

        shuffle(array);
        return array;
    }

    // ============================================
    // LINKED LIST NODES
    // ============================================

    /**
     * Generate linked list from array
     */
    public static ListNode generateLinkedList(int... values) {
        if (values.length == 0) return null;

        ListNode head = new ListNode(values[0]);
        ListNode current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }

        return head;
    }

    /**
     * Generate random linked list
     */
    public static ListNode generateRandomLinkedList(int size, int min, int max) {
        validateRange(min, max);
        int[] values = generatePositiveIntArray(size, min, max);
        return generateLinkedList(values);
    }

    // ============================================
    // TREE NODES
    // ============================================

    /**
     * Generate binary tree from level-order array
     */
    public static TreeNode generateBinaryTree(Integer... values) {
        if (values.length == 0 || values[0] == null) return null;

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

    // ============================================
    // GRAPH (ADJACENCY LIST)
    // ============================================

    /**
     * Generate random graph as adjacency list
     */
    public static Map<Integer, List<Integer>> generateGraph(int nodes, double edgeProbability) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < nodes; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < nodes; i++) {
            for (int j = i + 1; j < nodes; j++) {
                if (random.nextDouble() < edgeProbability) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        return graph;
    }

    // ============================================
    // UTILITY METHODS
    // ============================================

    private static void validateRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min must be <= Max");
        }
    }

    private static void reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

    private static void shuffle(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private static void shuffle(String[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            String temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    // ============================================
    // HELPER CLASSES
    // ============================================

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

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

    // ============================================
    // DEMO
    // ============================================

    public static void main(String[] args) {
        System.out.println("=== Data Generator Demo ===\n");

        System.out.println("Positive array: " + Arrays.toString(generatePositiveIntArray(5, 1, 10)));
        System.out.println("Mixed array: " + Arrays.toString(generateMixedIntArray(5, 1, 10)));
        System.out.println("Sorted array: " + Arrays.toString(generateSortedIntArray(5, 1, 10)));
        System.out.println("Reverse sorted: " + Arrays.toString(generateReverseSortedIntArray(5, 1, 10)));
        System.out.println("With duplicates: " + Arrays.toString(generateArrayWithDuplicates(5, 1, 10, 0.4)));

        System.out.println("\n2D Array:");
        int[][] matrix = generate2DIntArray(3, 3, 1, 10);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("\nString array: " + Arrays.toString(generateStringArray(3, 3, 5)));
        System.out.println("String with duplicates: " + Arrays.toString(generateStringArrayWithDuplicates(5, 3, 5)));

        System.out.println("\nLinked list generated");
        ListNode head = generateRandomLinkedList(5, 1, 10);

        System.out.println("Binary tree generated");
        TreeNode root = generateBinaryTree(1, 2, 3, 4, 5);

        System.out.println("Graph: " + generateGraph(4, 0.5));
    }
}