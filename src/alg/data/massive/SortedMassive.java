package alg.data.massive;
import alg.node.Node;


public class SortedMassive extends Massive{

	@Override
	public void add(Node item) {
		int index = this.index - 1;

		massive[this.index] = item;
		while (index >= 1 && massive[index].item.compareTo(item.item) >= 0) {
			swapItems(item, index);
			index--;
		}
		checkSizeChange(this.index);
		this.index++;
	}

	private void swapItems(Node item, int index) {
		Node temp = massive[index];
		massive[index] = item;
		massive[index + 1] = temp;
	}
}
