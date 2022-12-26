package edu.iastate.cs228.hw3;

import java.util.AbstractSequentialList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Implementation of the list interface based on linked nodes that store
 * multiple items per node. Rules for adding and removing elements ensure that
 * each node (except possibly the last one) is at least half full.
 * 
 * @author taram
 */
public class StoutList<E extends Comparable<? super E>> extends AbstractSequentialList<E> {
	/**
	 * Default number of elements that may be stored in each node.
	 */
	private static final int DEFAULT_NODESIZE = 4;

	/**
	 * Number of elements that can be stored in each node.
	 */
	private final int nodeSize;

	/**
	 * Dummy node for tail.
	 */
	private Node tail;

	/**
	 * Number of elements in the list.
	 */
	private int size;

	/**
	 * Constructs an empty list with the default node size.
	 */
	public StoutList() {
		this(DEFAULT_NODESIZE);
	}

	/**
	 * Constructs an empty list with the given node size.
	 * 
	 * @param nodeSize number of elements that may be stored in each node, must be
	 *                 an even number
	 */
	public StoutList(int nodeSize) {
		if (nodeSize <= 0 || nodeSize % 2 != 0)
			throw new IllegalArgumentException();

		// dummy nodes
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.previous = head;
		this.nodeSize = nodeSize;
	}

	/**
	 * Constructor for grading only. Fully implemented.
	 * 
	 * @param head
	 * @param tail
	 * @param nodeSize
	 * @param size
	 */
	public StoutList(Node head, Node tail, int nodeSize, int size) {
		this.head = head;
		this.tail = tail;
		this.nodeSize = nodeSize;
		this.size = size;
	}

	/**
	 * Dummy node for head. It should be private but set to public here only for
	 * grading purpose. f In practice, you should always make the head of a linked
	 * list a private instance variable.
	 */
	public Node head;

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

	@Override
	public boolean add(E item) {
		if (item == null) {
			throw new NullPointerException();
		}
		Node temp = new Node();
		if (size == 0) {
			head.next = temp;
			temp.previous = head;
			temp.next = tail;
			tail.previous = temp;
			temp.addItem(item);

		} else if (tail.previous.count == nodeSize) {
			temp.addItem(item);
			tail.previous.next = temp;
			temp.previous = tail.previous;
			temp.next = tail;
			tail.previous = temp;
			;
		} else {
			tail.previous.addItem(item);

		}
		size++;

		return true;
	}

	@Override
	public void add(int pos, E item) {

		NodeInfo nodeInfo = find(pos);
		int off = nodeInfo.offset;

		if (pos < 0 || pos > size) {
			throw new IndexOutOfBoundsException("" + pos);
		}
		if (item == null) {
			throw new NullPointerException();
		}
		if (tail.previous == head) {
			add(item);
			return;

		}

		if (nodeInfo.offset == 0) {
			if (nodeInfo.node.previous.count < nodeSize && nodeInfo.node.previous != head) {
				nodeInfo.node.previous.addItem(item);
				size++;
				return;

			} else if (nodeInfo.node == tail && tail.previous.count == nodeSize) {
				add(item);
				size++;

				return;

			}
		}

		Node newNode = nodeInfo.node;

		if (nodeInfo.node.count < nodeSize) {// RULE 2
			nodeInfo.node.addItem(nodeInfo.offset, item);
		}

		else {
			E[] array = newNode.data;
			E[] halfArray = (E[]) new Comparable[nodeSize / 2];

			int count = 0;

			for (int i = nodeSize / 2; i < newNode.count; i++) {
				halfArray[count] = array[i];
				count++;
			}
			int k = nodeSize / 2;
			int temp = newNode.count;

			for (int i = nodeSize / 2; i < temp; i++) {
				newNode.removeItem(k);
			}
			Node tNode = new Node();

			for (int i = 0; i < halfArray.length; i++) {
				tNode.addItem(i, halfArray[i]);
			}
			tNode.previous = newNode;
			tNode.next = newNode.next;
			newNode.next.previous = tNode;
			newNode.next = tNode;
			if (nodeInfo.offset <= 2) {
				newNode.addItem(nodeInfo.offset, item);
				size++;

			} else if (nodeInfo.offset > 2) {
				newNode.next.addItem(nodeInfo.offset - 2, item);

			}

		}
	}

	@Override
	public E remove(int pos) {
		if (pos < 0 || pos > size) {
			throw new IllegalStateException();
		}

		NodeInfo nodeInfo = find(pos);
		Node tempNode = nodeInfo.node;
		E value = tempNode.data[nodeInfo.offset];

		if (tempNode.count == 1 && tempNode.next == tail) {
			tempNode.previous.next = tail;
			tail.previous = tempNode.previous;
		} else if (tempNode.next == tail) {
			tempNode.removeItem(nodeInfo.offset);
			for (int i = nodeInfo.offset; i < tempNode.count - 1; i++) {
				tempNode.data[i] = tempNode.data[i + 1];
			}
			tempNode.data[DEFAULT_NODESIZE - 1] = null;

		} else if (tempNode.count > nodeSize / 2) {
			tempNode.removeItem(nodeInfo.offset);
		} else if (tempNode.count <= 2) {
			tempNode.removeItem(nodeInfo.offset);

			Node suc = tempNode.next;
				if (suc.count > nodeSize / 2) {
					tempNode.addItem(suc.data[0]);
					suc.removeItem(0);

				} else {

					for (int i = 0; i < suc.count; i++) {
						tempNode.addItem(suc.data[i]);
					}

					tempNode.next = suc.next;
					suc.next.previous = tempNode;
				}
				suc = suc.next;

		} else {
			Node suc = tempNode.next;
				if (suc.count > nodeSize / 2) {
					tempNode.addItem(suc.data[0]);
					suc.removeItem(0);

				} else {
					for (int i = 0; i < suc.count; i++) {
						tempNode.addItem(suc.data[i]);
					}
					tempNode.next = suc.next.previous;
					suc.next.previous = suc.previous.next;
				}
				suc = suc.next;
		}
		return value;
	}

	/**
	 * Sort all elements in the stout list in the NON-DECREASING order. You may do
	 * the following. Traverse the list and copy its elements into an array,
	 * deleting every visited node along the way. Then, sort the array by calling
	 * the insertionSort() method. (Note that sorting efficiency is not a concern
	 * for this project.) Finally, copy all elements from the array back to the
	 * stout list, creating new nodes for storage. After sorting, all nodes but
	 * (possibly) the last one must be full of elements.
	 * 
	 * Comparator<E> must have been implemented for calling insertionSort().
	 */
	public void sort() {
		// TODO
		new StoutListIterator();
		E[] sortedList = (E[]) new Comparable[size];
		Node t = head.next;
		int j = 0;

		while (t != tail) {
			for (int i = 0; i < t.count; i++) {
				sortedList[j] = t.data[i];
				j++;
			}
			t = t.next;
		}

		head.next = tail;
		tail.previous = head;

		insertionSort(sortedList, new DataComp());
		size = 0;
		for (int i = 0; i < sortedList.length; i++) {

			add(sortedList[i]);
		}

	}

	/**
	 * Sort all elements in the stout list in the NON-INCREASING order. Call the
	 * bubbleSort() method. After sorting, all but (possibly) the last nodes must be
	 * filled with elements.
	 * 
	 * Comparable<? super E> must be implemented for calling bubbleSort().
	 */
	public void sortReverse() {
		E[] sortList = (E[]) new Comparable[size];

		int index = 0;
		Node temp = head.next;
		while (temp != tail) {
			for (int i = 0; i < temp.count; i++) {
				sortList[index] = temp.data[i];
				index++;
			}
			temp = temp.next;
		}

		head.next = tail;
		tail.previous = head;

		bubbleSort(sortList);
		size = 0;
		for (int i = 0; i < sortList.length; i++) {
			if (sortList[i] != null) {
				add(sortList[i]);

			}
		}
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new StoutListIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return new StoutListIterator();
	}

	private class NodeInfo {
		public Node node;
		public int offset;

		public NodeInfo(Node Node, int offset) {
			this.node = Node;
			this.offset = offset;
		}
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return new StoutListIterator(index);
	}

	/**
	 * Returns a string representation of this list showing the internal structure
	 * of the nodes.
	 */
	public String toStringInternal() {
		return toStringInternal(null);
	}

	/**
	 * Returns a string representation of this list showing the internal structure
	 * of the nodes and the position of the iterator.
	 *
	 * @param iter an iterator for this list
	 */
	public String toStringInternal(ListIterator<E> iter) {
		int count = 0;
		int position = -1;
		if (iter != null) {
			position = iter.nextIndex();
		}

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		Node current = head.next;
		while (current != tail) {
			sb.append('(');
			E data = current.data[0];
			if (data == null) {
				sb.append("-");
			} else {
				if (position == count) {
					sb.append("| ");
					position = -1;
				}
				sb.append(data.toString());
				++count;
			}

			for (int i = 1; i < nodeSize; ++i) {
				sb.append(", ");
				data = current.data[i];
				if (data == null) {
					sb.append("-");
				} else {
					if (position == count) {
						sb.append("| ");
						position = -1;
					}
					sb.append(data.toString());
					++count;

					// iterator at end
					if (position == size && count == size) {
						sb.append(" |");
						position = -1;
					}
				}
			}
			sb.append(')');
			current = current.next;
			if (current != tail)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Node type for this list. Each node holds a maximum of nodeSize elements in an
	 * array. Empty slots are null.
	 */
	private class Node {
		/**
		 * Array of actual data elements.
		 */
		public E[] data = (E[]) new Comparable[nodeSize];

		/**
		 * Link to next node.
		 */
		public Node next;

		/**
		 * Link to previous node;
		 */
		public Node previous;

		/**
		 * Index of the next available offset in this node, also equal to the number of
		 * elements in this node.
		 */
		public int count;

		/**
		 * Adds an item to this node at the first available F. Precondition: count <
		 * nodeSize
		 * 
		 * @param item element to be added
		 */
		void addItem(E item) {
			if (count >= nodeSize) {
				return;
			}
			data[count++] = item;

		}

		/**
		 * Adds an item to this node at the indicated offset, shifting elements to the
		 * right as necessary.
		 * 
		 * Precondition: count < nodeSize
		 * 
		 * @param offset array index at which to put the new element
		 * @param item   element to be added
		 */
		void addItem(int offset, E item) {
			if (count >= nodeSize) {
				return;
			}
			for (int i = count - 1; i >= offset; --i) {
				data[i + 1] = data[i];
			}
			++count;
			data[offset] = item;

		}

		/**
		 * Deletes an element from this node at the indicated offset, shifting elements
		 * left as necessary. Precondition: 0 <= offset < count
		 * 
		 * @param offset
		 */
		void removeItem(int offset) {
			E item = data[offset];
			for (int i = offset + 1; i < nodeSize; ++i) {
				data[i - 1] = data[i];
			}
			data[count - 1] = null;
			--count;
		}
	}

	private class StoutListIterator implements ListIterator<E> {

		private int pos = 0;
		private static final int BEHIND = -1;
		private static final int AHEAD = 1;
		private static final int NONE = 0;
		public E[] nodeList;

		private int direction;
		private Node cursor;
		private int cur;

		/**
		 * Default constructor
		 */
		public StoutListIterator() {
			this(0);
		}

		/**
		 * Constructor finds node at a given position.
		 * 
		 * @param pos
		 */
		public StoutListIterator(int pos) {
			cursor = head;
			direction=NONE;
			if (pos < 0 || pos > size) {
				throw new IndexOutOfBoundsException("" + pos);
			}
			this.pos = pos;
		
			nodeList = (E[]) new Comparable[size];

			Node t = head.next;
			int tIndex = 0;

			while (t != tail) {
				for (int i = 0; i < t.count; i++) {
					nodeList[tIndex] = t.data[i];
					tIndex++;
				}
				t = t.next;

			}

		}

		@Override
		public boolean hasNext() {
			return size > pos;
		}

		@Override
		public E next() {
			// TODO
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			direction = BEHIND;

			return nodeList[pos++];
		}

		@Override
		public void remove() {

			Node t = head.next;
			int tIndex = 0;

			if (direction == AHEAD) {
				StoutList.this.remove(pos);
			//	pos--;
			} else if (direction == BEHIND) {
				StoutList.this.remove(pos-1);
				pos = Math.max(0, pos - 1);
			} else {
				throw new IllegalStateException();
			}
			direction = NONE;
		//	pos--;

		}

		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return pos > 0;
		}

		@Override
		public E previous() {
			// TODO Auto-generated method stub
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			direction = AHEAD;

			pos--;

			return nodeList[pos];
		}

		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			return pos;
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return pos - 1;
		}

		@Override
		public void set(E e) {
			if (e == null) {
				throw new NullPointerException();
			}

			if (direction == BEHIND) {

				NodeInfo nodeCur = find(pos);
				nodeCur.node.data[nodeCur.offset] = e;
				nodeList[pos - 1] = e;
			} else if (direction == AHEAD) {
				NodeInfo nodeCur = find(pos - 1);
				nodeCur.node.data[nodeCur.offset] = e;
				nodeList[pos - 1] = e;
			} else {
				throw new IllegalStateException();

			}
		}

		@Override
		public void add(E e) {
			if (e == null) {
				throw new NullPointerException();
			}
			//Node t = head.next;
		//	int tIndex = 0;

			StoutList.this.add(pos, e);
			pos++;

		}

	}

	/*
	 * This is a find helper method that locates a item at a specefic place
	 * 
	 * @param position of item
	 * 
	 * @return Node info of that item at that position
	 */
	private NodeInfo find(int pos) {

		Node newNode = head.next;
		int count = newNode.count;
		int offset = 0;

		if (pos == -1) {

			return new NodeInfo(head, offset);
		} else if (pos == size) {
			return new NodeInfo(tail, offset);
		}

		while (newNode != tail) {

			if (newNode.count <= pos) {
				pos = pos - newNode.count;
				newNode = newNode.next;
			} else if (newNode.count >= pos + 1) {
				offset = pos;
				break;
			}
		}
		NodeInfo nodeCur = new NodeInfo(newNode, offset);
		return nodeCur;

	}

	/**
	 * Sort an array arr[] using the insertion sort algorithm in the NON-DECREASING
	 * order.
	 * 
	 * @param arr  array storing elements from the list
	 * @param comp comparator used in sorting
	 */
	public void insertionSort(E[] arr, Comparator<? super E> comp) {
		// TODO
		for (int i = 1; i < arr.length; i++) {
			E firstVal = arr[i];
			int j = i - 1;

			while (j >= 0 && comp.compare(arr[j], firstVal) > 0) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = firstVal;
		}
	}

	/**
	 * Sort arr[] using the bubble sort algorithm in the NON-INCREASING order. For a
	 * description of bubble sort please refer to Section 6.1 in the project
	 * description. You must use the compareTo() method from an implementation of
	 * the Comparable interface by the class E or ? super E.
	 * 
	 * @param arr array holding elements from the list
	 */
	private void bubbleSort(E[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if ((arr[j] != null && arr[j + 1] != null) && (arr[j].compareTo(arr[j + 1]) < 0)) {
					E temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}

		}
	}

	/*
	 * This is a comparator that is used by the sorts
	 */
	class DataComp<E extends Comparable<E>> implements Comparator<E> {
		@Override
		public int compare(E lhs, E rhs) {
			// TODO Auto-generated method stub
			return lhs.compareTo(rhs);
		}

	}

}
