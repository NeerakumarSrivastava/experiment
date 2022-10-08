package com.home.java.letcode.dp;

public class BinaryMaximumPathSum {

    public static void main(String[] args) {


    }


    public int maxPathSum(TreeNode root) {
        return getMaximumBinarPath(root, new Info(Integer.MIN_VALUE, Integer.MIN_VALUE), root).maximumPath;
    }

    public Info getMaximumBinarPath(TreeNode root, Info info, TreeNode mainroot) {
        if (root == null)
            return null;

        if (root.left == null && root.right == null) {
            return new Info(root.val, root.val);

        }

        Info infoleft = getMaximumBinarPath(root.left, info, mainroot);
        Info inforight = getMaximumBinarPath(root.right, info, mainroot);


        int maxPath = Integer.MIN_VALUE;
        int newGeneratedMaxPath = Integer.MIN_VALUE;
        int selectedPath = Integer.MIN_VALUE;
        if (infoleft != null && inforight != null) {
            maxPath = infoleft.maximumPath > inforight.maximumPath ? infoleft.maximumPath : inforight.maximumPath;
            newGeneratedMaxPath = infoleft.currentSelectedPath + root.val + inforight.currentSelectedPath;
            selectedPath = infoleft.currentSelectedPath > inforight.currentSelectedPath
                    ? infoleft.currentSelectedPath + root.val
                    : inforight.currentSelectedPath + root.val;


            int rootcase = (infoleft.currentSelectedPath + root.val) > (root.val + inforight.currentSelectedPath)
                    ? (infoleft.currentSelectedPath + root.val) : (root.val + inforight.currentSelectedPath);
            newGeneratedMaxPath = newGeneratedMaxPath > rootcase ? newGeneratedMaxPath : rootcase;
            newGeneratedMaxPath = root.val > newGeneratedMaxPath ? root.val : newGeneratedMaxPath;


        } else if (inforight != null) {
            maxPath = inforight.maximumPath;
            newGeneratedMaxPath = root.val + inforight.currentSelectedPath;
            selectedPath = inforight.currentSelectedPath + root.val;

            int rootcase = (0 + root.val) > (root.val + inforight.currentSelectedPath)
                    ? (0 + root.val) : (root.val + inforight.currentSelectedPath);
            newGeneratedMaxPath = newGeneratedMaxPath > rootcase ? newGeneratedMaxPath : rootcase;
            newGeneratedMaxPath = root.val > newGeneratedMaxPath ? root.val : newGeneratedMaxPath;


        } else if (infoleft != null) {
            maxPath = infoleft.maximumPath;
            newGeneratedMaxPath = infoleft.currentSelectedPath + root.val;
            selectedPath = infoleft.currentSelectedPath + root.val;

            int rootcase = (infoleft.currentSelectedPath + root.val) > (root.val + 0)
                    ? (infoleft.currentSelectedPath + root.val) : (root.val + 0);
            newGeneratedMaxPath = newGeneratedMaxPath > rootcase ? newGeneratedMaxPath : rootcase;
            newGeneratedMaxPath = root.val > newGeneratedMaxPath ? root.val : newGeneratedMaxPath;

        }
        System.out.println("........................................");
        System.out.println(newGeneratedMaxPath);
        System.out.println(maxPath);
        System.out.println(selectedPath);


        System.out.println(".........................................");


        maxPath = (maxPath) > newGeneratedMaxPath
                ? (maxPath)
                : newGeneratedMaxPath;

        return new Info(selectedPath
                , maxPath);


    }


    static class Info {
        public Info(int currentSelectedPath, int maximumPath) {
            this.currentSelectedPath = currentSelectedPath;
            this.maximumPath = maximumPath;
        }

        public int getCurrentSelectedPath() {
            return currentSelectedPath;
        }

        public int getMaximumPath() {
            return maximumPath;
        }

        private int currentSelectedPath;
        private int maximumPath;


        @Override
        public String toString() {
            return "Info{" +
                    "currentSelectedPath=" + currentSelectedPath +
                    ", maximumPath=" + maximumPath +
                    '}';
        }
    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
