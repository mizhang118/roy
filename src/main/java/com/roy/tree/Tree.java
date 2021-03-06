package com.roy.tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Tree {
	private TreeNode root;
	
	public TreeNode getRoot() {
		return this.root;
	}
	
	public void setRoot(TreeNode node) {
		this.root = node;
	}

	public void insert(TreeNode dataNode) {
		if ( root == null ) {
			root = dataNode;
		}
		else {
			insertByLoop(root, dataNode);
		}	
	}
	
	public TreeNode find(Integer data) {
		return findRecursive(root, data);
	}
	
	private void insertRecursive(TreeNode parentNode, TreeNode dataNode) {
		if ( parentNode == null || dataNode == null ) {
			return;
		}
		
		if (dataNode.getData() < parentNode.getData()) {
			if ( parentNode.getLeft() == null ) {
				parentNode.setLeft(dataNode);
			}
			else {
				insertRecursive(parentNode.getLeft(), dataNode);
			}
		}
		else {
			if (parentNode.getRight() == null) {
				parentNode.setRight(dataNode);
			}
			else {
				insertRecursive(parentNode.getRight(), dataNode);
			}
		}
	}
	
	private void insertByLoop(TreeNode parentNode, TreeNode dataNode) {
		if ( dataNode == null ) {
			return;
		}
		
		while ( parentNode != null ) {
		if (dataNode.getData() < parentNode.getData()) {
			if ( parentNode.getLeft() == null ) {
				parentNode.setLeft(dataNode);
				break;
			}
			else {
				parentNode = parentNode.getLeft();
			}
		}
		else {
			if (parentNode.getRight() == null) {
				parentNode.setRight(dataNode);
				break;
			}
			else {
				parentNode = parentNode.getRight();
			}
		}
		}
	}
	
	private TreeNode findRecursive(TreeNode node, Integer data) {
		if ( node == null ) {
			return null;
		}
		
		if (node.getData() == data) {
			return node;
		}
		else if (data < node.getData()) {
			return findRecursive(node.getLeft(), data);
		}
		else if (data > node.getData()) {
			return findRecursive(node.getRight(), data);
		}
		return null;
	}
	
	private TreeNode findByLoop(TreeNode node, Integer data) {
		if (node == null) {
			return null;
		}
		
		while (node != null) {
			if (data < node.getData()) {
				if (node.getLeft() == null) {
					return node;
				}
				else {
					node = node.getLeft();
				}
			}
			else {
				if (node.getRight() == null) {
					return node;
				}
				else {
					node = node.getRight();
				}
			}
		}
		return null;
	}
	/**
	 * https://en.wikipedia.org/wiki/Tree_traversal
	 * 
	 * 4 traverse ways: (1) In-order (2) Pre-order (3) Post-order (4) Breadth-first
	 */
	public void traverseRecursive(TreeNode node) {
		if (node == null) {
			return;
		}
		
		traverseRecursive(node.getLeft());
		traverseRecursive(node.getRight());
		visit(node);
	}
	
	public void traverseBF() {
		LinkedList<TreeNode> list = new LinkedList<TreeNode>();
		if ( root != null ) {
			list.addFirst(root);
		}
		
		while ( !list.isEmpty() ) {
			TreeNode node = list.removeLast();
			
			if ( node.getLeft() != null ) {
				list.addFirst(node.getLeft());
			}
			if ( node.getRight() != null ) {
				list.addFirst(node.getRight());
			}
			
			visit(node);
		}
		
	}
	
	public void visit(TreeNode node) {
		System.out.println(node.getData());
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

		System.out.println("\n");
		
		tree.traverseBF();

		
		
/*
		System.out.println(tree.find(88));
		System.out.println(tree.find(13));
		System.out.println(tree.find(44));
		System.out.println(tree.find(33));
		System.out.println(tree.find(124));
		System.out.println(tree.find(71));
*/		
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
