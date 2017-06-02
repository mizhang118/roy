package com.roy.linkedlist;

public class OrderedLinkedList {
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
	
	/**
	 * TODO: implement this method to add the node into a right place to generate a ordered linked list
	 */
	public void insert(Node node) {
		Node pointer = null;
		
		//hanlde case of empty list
		if ( head == null || tail == null ) {
			head = node;
			tail = node;
			return;
		}
		
		//handle the case of inserting into head
		if (head.getData() >= node.getData()) {
			node.setNext(head);
			head.setPrevious(node);
			head = node;
			return;
		}
		
		//handle the case of inserting into tail
		if (tail.getData() <= node.getData()) {
			node.setPrevious(tail);
			tail.setNext(node);
			tail = node;
			return;
		}

		//handle the case of inserting into middle
		pointer = head.getNext();
		while ( pointer != null ) {
			if (node.getData() <= pointer.getData()) {
				node.setPrevious(pointer.getPrevious());
				node.setNext(pointer);
				pointer.getPrevious().setNext(node);
				
				//poiter.setPrevous has to be called at last, otherwise, prevous node will be lost
				pointer.setPrevious(node);
			}
			pointer = pointer.getNext();
		}
	}
	
	public static void main(String[] args) {
		OrderedLinkedList list = new OrderedLinkedList();
		
		list.insert(new Node(121));
		list.insert(new Node(234));
		list.insert(new Node(23));
		list.insert(new Node(222));
		list.insert(new Node(15));
		
		//print result as: [15,23,121,222,234]
		System.out.println(list);
	}
}
