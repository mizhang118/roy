package com.roy.linkedlist;

public class Node {
	private Integer data = null;
	private Node next = null;
	private Node previous = null;
	
	public Node(Integer d) {
		this.data = d;
	}
	
	public Integer getData() {
		return data;
	}
	
	public void setData(Integer data) {
		this.data = data;
	}
	
	public Node getNext() {
		return next;
	}
	
	public void setNext(Node next) {
		this.next = next;
	}
	
	public Node getPrevious() {
		return previous;
	}
	
	public void setPrevious(Node previous) {
		this.previous = previous;
	}
	
	@Override
	public String toString() {
		return "" + data;
	}
}
