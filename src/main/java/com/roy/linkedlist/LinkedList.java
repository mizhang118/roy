package com.roy.linkedlist;

public class LinkedList {
	private Node head = null;
	private Node tail = null;
	
	/**
	 * generally look up the head of the list
	 * @return
	 */
	public Node getHead() {
		return head;
	}
	
	/**
	 * generally look up the tail of the list
	 * @return
	 */
	public Node getTail() {
		return tail;
	}
	
	/**
	 * generally adding a node into the linked list
	 * @param node
	 */
	public void add(Node node) {
		addFirst(node);
	}
	
	/**
	 * generally removing a node from the linked list
	 * @return
	 */
	public Node remove() {
		return removeFirst();
	}
	
	/**
	 * stack API to do push
	 * @param node
	 */
	public void push(Node node) {
		addFirst(node);
	}
	
	/**
	 * stack API to do pop
	 * @return
	 */
	public Node pop() {
		return removeFirst();
	}
	
	/**
	 * stack API to do peek
	 * @return
	 */
	public Node peek() {
		return head;
	}
	
	public void queue(Node node) {
		addFirst(node);
	}
	
	public Node dequeue() {
		return removeLast();
	}
	
	/**
	 * TODO: implement addFirst to add a node into the head
	 * @param node
	 */
	public void addFirst(Node node) {
		if ( node == null ) {
			return;
		}
		
		//handle empty list
		if ( head == null || tail == null ) {
			head = node;
			tail = node;
			return;
		}
		
		//handle one or more elements already exists in the list
		node.setNext(head);
		head.setPrevious(node);
		
		head = node;
	}
	
	/**
	 * TODO: implement addLast to add a node into the tail
	 * @param node
	 */
	public void addLast(Node node) {
		//Do this for non-empty linked lists.
		if (tail != null) {
			tail.setNext(node);
			node.setPrevious(tail);
			
			tail = node;
		}
		//This is for empty linked lists.
		else {
			head = node;
			tail = node;
		}
	}
	
	/**
	 * TODO: implement removeFirst to remove a node from head
	 * @return
	 */
	public Node removeFirst() {
		if ( head == null ) {
			return null;
		}

		Node oldHead = head;
		head = oldHead.getNext();
		
		//handle empty case
		if ( head == null ) {
			tail = null;
		}
		
		return oldHead;
	}
	
	/**
	 * TODO: implement removeLast to remove a node from tail
	 * @return
	 */
	public Node removeLast() {
		Node oldTail = tail;
		tail = oldTail.getPrevious();
		if ( tail == null ) {
			head = null;
		}

		return oldTail;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Node tmp = head;
		builder.append("[");
		int count = 0;
		while ( tmp != null ) {
			if ( count > 0 ) {
				builder.append(",");
			}
			
			builder.append(tmp.toString());
			tmp = tmp.getNext();
			count++;
		}
		builder.append("]");
		
		return builder.toString();
	}
	
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		
		list.addFirst(new Node(121));
		list.addLast(new Node(234));
		list.addFirst(new Node(23));
		list.addFirst(new Node(222));
		list.addLast(new Node(15));
		
		//print result as: [222,23,121,234,15]
		System.out.println(list);
		
		//print result as: 15
		System.out.println(list.dequeue());
		
		//print result as: 222
		System.out.println(list.pop());
		
		list.queue(new Node(99));
		list.push(new Node(44));
		
		// print result as [44,99,23,121,234]
		System.out.println(list);
	}
}
