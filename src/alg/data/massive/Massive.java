package alg.data.massive;

import alg.data.massive.exception.MassiveOutOfBoundsException;
import alg.node.Node;


public abstract class Massive {
	int index = 0;
	Node[] massive;

	public Massive() {
		create(1);
	}

	public abstract void add(Node item);

	public void checkSizeChange(int i) {
		if (index >= massive.length - 1) {
			increaseMassiveSize(i);
		} else if (index <= massive.length / 4) {
			decreaseMassiveSize();
		}
	}

	public void create(int n) {
		massive = new Node[n];
	}

	private void decreaseMassiveSize() {
		Node[] newMassive = new Node[massive.length / 2];
		for (int i = 0; i < index; i++) {
			newMassive[i] = massive[i];
		}
		massive = newMassive;
	}

	public Node get(int i) throws MassiveOutOfBoundsException {
		if (i >= index || i < 0) {
			throw new MassiveOutOfBoundsException("No element found at " + i);
		}

		return massive[i];
	}

	private void increaseMassiveSize(int index) {
		Node[] newMassive = new Node[massive.length * 2];
		for (int i = 0; i <= this.index; i++) {
			newMassive[i] = massive[i];
		}
		massive = newMassive;
	}

	public int length() {
		return index;
	}

	public void put(Node x, int i) {
		if (i > index) {
			throw new MassiveOutOfBoundsException("No element found at " + i);
		}
		checkSizeChange(index);
		massive[i] = x;
		index++;
	}

	public Node remove() {
		Node result = massive[--index];
		massive[index] = null;

		checkSizeChange(index);

		return result;
	}
}
