package alg.data.massive;
import alg.node.Node;


public class UnsortedMassive extends Massive {

	@Override
	public void add(Node item) {
		massive[index] = item;
		checkSizeChange(index);
		index++;
	}
}
