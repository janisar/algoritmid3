package alg.data;
import alg.data.massive.Massive;
import alg.data.massive.UnsortedMassive;
import alg.data.massive.exception.MassiveOutOfBoundsException;
import alg.node.Node;



public class PriorityQueue {

	private Massive heap;
	private int size;

	public PriorityQueue() {
		size = 0;
		heap = new UnsortedMassive();
		Node rootNode = new Node(1, Integer.MAX_VALUE);
		rootNode.setBound(Integer.MAX_VALUE);
		heap.add(rootNode);
	}

	public Node dequeue() {
		Node result = heap.get(1);
		heap.put(heap.get(size), 1);
		heap.put(null, size--);
		organiseHeap(1);
		return result;
	}

	public void enqueue(Node element) {
		heap.put(element, ++size);
		int current = size;
		while (bound(heap.get(current)) > bound(heap.get(parentIndex(current)))) {
			swapNums(current, parentIndex(current));
			current = parentIndex(current);
		}
	}

	public boolean isEmpty() {
		return size < 1;
	}

	private boolean isLeaf(int pos) {
		if (size <= 1 && pos <= 1) {
			return true;
		}
		if (pos > (size / 2) && pos <= size) {
			return true;
		}
		return false;
	}

	private int leftChild(int position) {
		return position * 2;
	}

	private void organiseHeap(int pos) {
		if (!isLeaf(pos)) {
			Node rightChild = new Node(0, 0);
			rightChild.setPrice(Integer.MIN_VALUE);
			try {
				rightChild = heap.get(rightChild(pos));
			} catch (MassiveOutOfBoundsException ex) {

			}
			if (bound(heap.get(pos)) < bound(heap.get(leftChild(pos)))
					|| bound(heap.get(pos)) < bound(rightChild)) {
				if (bound(heap.get(leftChild(pos))) > bound(rightChild)) {
					swapNums(pos, leftChild(pos));
					organiseHeap(leftChild(pos));
				} else {
					swapNums(pos, rightChild(pos));
					organiseHeap(rightChild(pos));
				}
			}

		}
	}

	private float bound(Node node) {
		return node.getBound();
	}

	private int parentIndex(int position) {
		return position / 2;
	}

	private int rightChild(int position) {
		int result = (position * 2) + 1;
		if (result > size) {
			return -1;
		} else {
			return result;
		}
	}

	private void swapNums(int currentIndex, int parentIndex) {
		Node temp = heap.get(currentIndex);
		heap.put(heap.get(parentIndex), currentIndex);
		heap.put(temp, parentIndex);
	}
}
