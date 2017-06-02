package com.roy.tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Tree {
	private TreeNode root;

	public void insert(TreeNode dataNode) {
		if ( root == null ) {
			root = dataNode;
		}
		else {
			insertRecursive(root, dataNode);
		}
	}
	
	public TreeNode find(Integer data) {
		return findRecursive(root, data);
	}
	
	private void insertRecursive(TreeNode parentNode, TreeNode dataNode) {
		//
	}
	
	private TreeNode findRecursive(TreeNode node, Integer data) {
		return null;
	}
	
	public static void main(String[] args) {
		Tree tree = new Tree();
		
		tree.insert(new TreeNode(50));
		tree.insert(new TreeNode(70));
		tree.insert(new TreeNode(25));
		tree.insert(new TreeNode(44));
		tree.insert(new TreeNode(12));
		tree.insert(new TreeNode(88));
		tree.insert(new TreeNode(58));
		tree.insert(new TreeNode(6));
		tree.insert(new TreeNode(124));
		tree.insert(new TreeNode(33));

		tree.printTopView();
	}
	
    public void printTopView()
    {
        // base case
        if (root == null) {  return;  }
 
        // Creates an empty hashset
        HashSet<Integer> set = new HashSet<>();
 
        // Create a queue and add root to it
        Queue<QItem> Q = new LinkedList<QItem>();
        Q.add(new QItem(root, 0)); // Horizontal distance of root is 0
 
        // Standard BFS or level order traversal loop
        while (!Q.isEmpty())
        {
            // Remove the front item and get its details
            QItem qi = Q.remove();
            int hd = qi.hd;
            TreeNode n = qi.node;
 
            // If this is the first node at its horizontal distance,
            // then this node is in top view
            if (!set.contains(hd))
            {
                set.add(hd);
                System.out.print(n.getData() + " ");
            }
 
            // Enqueue left and right children of current node
            if (n.getLeft() != null)
                Q.add(new QItem(n.getLeft(), hd-1));
            if (n.getRight() != null)
                Q.add(new QItem(n.getRight(), hd+1));
        }
    }
}
