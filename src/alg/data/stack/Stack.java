package alg.data.stack;
import alg.node.Node;

public class Stack {

	Node[] m;
	int currentIndex;

	public Stack(Node[] items) {
		m = items;
		currentIndex = 0;
	}

	public void push(Node item) {
		m[currentIndex] = item;
		currentIndex++;
	}

	public Node pop() {
		if (!isEmpty()) {
			Node result = m[currentIndex - 1];
			try {
				m[currentIndex] = null;
			} catch (ArrayIndexOutOfBoundsException e) {

			}
			currentIndex--;
			return result;
		} else {
			System.err.println("Stack '" + this + " 'is empty!");
			return null;
		}
	}

	public boolean isEmpty() {
		return currentIndex == 0;
	}

	public int size() {
		return m.length;
	}
}
