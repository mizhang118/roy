package com.roy.tree;

public class TreeNode {
	private Integer data;
	private TreeNode left;
	private TreeNode right;
	
	public TreeNode() {
	}
	
	public TreeNode(Integer d) {
		this.data = d;
	}
	
	public Integer getData() {
		return data;
	}
	public void setData(Integer data) {
		this.data = data;
	}
	public TreeNode getLeft() {
		return left;
	}
	public void setLeft(TreeNode left) {
		this.left = left;
	}
	public TreeNode getRight() {
		return right;
	}
	public void setRight(TreeNode right) {
		this.right = right;
	}
	@Override
	public String toString() {
		return " " + data + " ";
	}
}
