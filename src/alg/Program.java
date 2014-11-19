package alg;

import alg.data.massive.SortedMassive;
import alg.knap.Knapsack;
import alg.node.Node;
import alg.util.FileUtil;



public class Program {

	private static final int NANO = 1000000000;

	public static void main(String[] args) {
		int maxWeight = FileUtil.getMaxIndex();
		SortedMassive items = (SortedMassive) FileUtil.getItems(new SortedMassive(), "alg.in");
		Knapsack knapsack = new Knapsack(items, maxWeight);

		//Node m = knapsack.knapsackDepthFirst(true);
		//Node n = knapsack.knapsackBestFirst();

		//FileUtil.printNodeToFile(m);

		//compareDepthFirstWithoutCut();
		compareDepthFirstAndBestFirst();
	}

	private static void compareDepthFirstAndBestFirst() {
		int maxWeight = FileUtil.getMaxIndex();
		SortedMassive items = (SortedMassive) FileUtil.getItems(new SortedMassive(), "alg.in");
		double start = System.nanoTime();
		new Knapsack(items, maxWeight).knapsackDepthFirst(true);
		double end = System.nanoTime();
		System.out.print("Depth first: ");
		System.out.println((end - start) / NANO);

		start = System.nanoTime();
		new Knapsack(items, maxWeight).knapsackBestFirst();
		end = System.nanoTime();
		System.out.print("Best first: ");
		System.out.println((end - start) / NANO);
	}

	private static void compareDepthFirstWithoutCut() {
		int maxWeight = FileUtil.getMaxIndex();
		SortedMassive items = (SortedMassive) FileUtil.getItems(new SortedMassive(), "alg.in");
		double start = System.nanoTime();
		new Knapsack(items, maxWeight).knapsackDepthFirst(true);
		double end = System.nanoTime();
		System.out.print("With cut: ");
		System.out.println((end - start) / NANO);

		start = System.nanoTime();
		Node n = new Knapsack(items, maxWeight).knapsackDepthFirst(false);
		end = System.nanoTime();
		System.out.print("Without cut: ");
		System.out.println((end - start) / NANO);
		System.out.println(n.getPrice());
	}
}
