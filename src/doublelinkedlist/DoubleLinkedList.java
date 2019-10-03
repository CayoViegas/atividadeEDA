package doublelinkedlist;

import java.util.NoSuchElementException;

public class DoubleLinkedList {
	private Node head;
	private Node tail;
	private int size;

	public DoubleLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public boolean isEmpty() {
		return this.head == null;
	}

	public void addLast(int elemento) {
		if (isEmpty()) {
			this.head = new Node(elemento);
			this.tail = this.head;
		} else {
			this.addLast(this.head, elemento);
		}

		this.size++;
	}

	private void addLast(Node node, int elemento) {
		if (node.next == null) {
			Node newNode = new Node(elemento);
			node.next = newNode;
			newNode.prev = node;
			this.tail = newNode;
			return;
		} else {
			addLast(node.next, elemento);
		}
	}

	public int size() {
		if (isEmpty()) {
			return 0;
		} else {
			return 1 + size(this.head.next);
		}
	}

	private int size(Node node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + size(node.next);
		}
	}

	public int soma() {
		return soma(this.head);
	}

	private int soma(Node node) {
		if (node == null) {
			return 0;
		} else {
			return node.elemento + soma(node.next);
		}
	}

	public boolean isSorted() {
		if (isEmpty()) {
			return true;
		} else {
			return isSorted(this.head);
		}
	}

	private boolean isSorted(Node node) {
		boolean retorno = true;

		if (node.next != null) {
			if (node.elemento > node.next.elemento) {
				retorno = false;
			} else {
				retorno = isSorted(node.next);
			}
		}

		return retorno;
	}

	public void add(int index, int elemento) {
		if (index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException();
		}

		Node newNode = new Node(elemento);

		if (index == 0) {
			this.addFirst(elemento);
		} else if (index == this.size - 1) {
			this.addLast(elemento);
		} else {
			Node aux = this.head;

			for (int i = 0; i < index - 1; i++) {
				aux = aux.next;
			}

			newNode.next = aux.next;
			aux.next = newNode;
			newNode.next.prev = newNode;
			newNode.prev = aux;

			this.size++;
		}
	}

	private void addFirst(int elemento) {
		Node newNode = new Node(elemento);

		if (isEmpty()) {
			this.head = newNode;
			this.tail = newNode;
		} else {
			newNode.next = this.head;
			this.head.prev = newNode;
			this.head = newNode;
		}

		this.size++;
	}

	public int remove(int index) {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) {
			return removeFirst();
		}

		if (index == this.size - 1) {
			return removeLast();
		}

		Node aux = this.head;

		for (int i = 0; i < index; i++) {
			aux = aux.next;
		}

		aux.prev.next = aux.next;
		aux.next.prev = aux.prev;
		this.size--;

		return aux.elemento;
	}

	private int removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		int retorno = this.tail.elemento;

		if (this.head.next == null) {
			this.head = null;
			this.tail = null;
		} else {
			this.tail = this.tail.prev;
			this.tail.next = null;
		}

		this.size--;

		return retorno;
	}

	private int removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		int retorno = this.head.elemento;

		if (this.head.next == null) {
			this.head = null;
			this.tail = null;
		} else {
			this.head = this.head.next;
			this.head.prev = null;
		}

		this.size--;

		return retorno;
	}
}

class Node {
	int elemento;
	Node next;
	Node prev;

	public Node(int elemento) {
		this.elemento = elemento;
		this.next = null;
		this.prev = null;
	}
}
