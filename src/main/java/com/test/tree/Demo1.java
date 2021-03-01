package com.test.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author scc
 * @date 2020/9/16 14:29
 */
public class Demo1 {

    /**
     * 二叉树节点
     */
    private static class TreeNode {

        int data;

        private TreeNode leftChild;

        private TreeNode rightChild;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    /**
     * 链表转红黑树
     *
     * @param inputList
     * @return
     */
    private static TreeNode createBinaryTree(LinkedList<Integer> inputList) {
        TreeNode node = null;
        if (inputList == null || inputList.isEmpty()) {
            return null;
        }
        Integer data = inputList.removeFirst();
        if (data != null) {
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(inputList);
            node.rightChild = createBinaryTree(inputList);
        }
        return node;
    }

    /**
     * 前序遍历
     */
    private static void pre(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.data);
        pre(root.leftChild);
        pre(root.rightChild);
    }

    /**
     * 中序遍历
     */
    private static void mid(TreeNode root) {
        if (root == null) {
            return;
        }
        mid(root.leftChild);
        System.out.println(root.data);
        mid(root.rightChild);
    }

    /**
     * 后序遍历
     */
    private static void post(TreeNode root) {
        if (root == null) {
            return;
        }
        post(root.leftChild);
        post(root.rightChild);
        System.out.println(root.data);
    }

    public static void level(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.data);
            if (node.leftChild != null) {
                queue.offer(node.leftChild);
            }
            if (node.rightChild != null) {
                queue.offer(node.rightChild);
            }
        }
    }

    public static void main(String[] args) {
        // 深度优先遍历（前中后）适合用递归或者栈
        // 广度优先遍历（层序）适合用队列
        LinkedList<Integer> inputList = new LinkedList<Integer>(Arrays
                .asList(new Integer[]{3, 2, 9, null, null, 10, null, null, 8, null, 4}));
        TreeNode root = createBinaryTree(inputList);
        System.out.println("前序遍历");
        pre(root);
        System.out.println("中序遍历");
        mid(root);
        System.out.println("后序遍历");
        post(root);
        System.out.println("层序遍历");
        level(root);
    }
}
