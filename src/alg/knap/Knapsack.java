package alg.knap;
import alg.data.PriorityQueue;
import alg.data.massive.Massive;
import alg.data.stack.Stack;
import alg.node.Node;



public class Knapsack {

	private int maxWeight;
	private Massive items;

	public Knapsack(int i) {
		maxWeight = i;
	}

	public Knapsack(Massive items, int maxWeight) {
		this.items = items;
		this.maxWeight = maxWeight;
	}

	private Node[] getChilds(Node item) {
		Node[] result = new Node[2];
		for (int i = 0; i < items.length(); i++) {
			Node curr = items.get(i);
			if (curr != null) {
				if (curr.equals(item)) {
					if (i + 1 < items.length()) {
						Node next = items.get(i + 1);
						Node left = next.copyLeft(item);
						result[0] = left;
						Node right = next.copyRight(item);
						result[1] = right;
						break;
					}
				}
			}
		}
		return result;
	}

	public Node knapsackDepthFirst(boolean cut) {
		Stack stack = new Stack(new Node[items.length() * 200000]);
		Node v = items.get(0);
		int best = v.getPrice();
		Node bestNode = v;
		stack.push(v);
		while (!stack.isEmpty()) {
			Node item = stack.pop();
			Node[] childs = getChilds(item);
			if (childs != null) {
				for (Node c : childs) { // Visit each child.
					if (promising(c)) {
						if (totalPrice(c) > best) {
							best = totalPrice(c);
							bestNode = c;
						}
						if (cut) {
							if (bound(c) > best) {
								stack.push(c);
							}
						} else if (bound(c) > 0) {
							stack.push(c);
						}
					}
				}
			}
		}
		return bestNode;
	}



	private float bound(Node c) {
		int result = c.getPrice();
		int weight = c.getWeight();

		for (int i = 0; i < items.length(); i++) {
			Node curr = items.get(i);
			if (curr != null) {
				if (curr.item.equals(c.item)) {
					for (int j = i + 1; j < items.length(); j++, i++) {
						weight += items.get(j).getWeight();
						if (weight > maxWeight) {
							result += (maxWeight - (weight - items.get(j).getWeight())) * items.get(j).item.getPw();
							return result;
						} else {
							result += items.get(j).getPrice();
						}
					}
				}
			}
		}
		return result;
	}

	private boolean promising(Node c) {
		if (c == null) {
			return false;
		}
		return c.getWeight() <= maxWeight;
	}

	private int totalPrice(Node c) {
		return c.getPrice();
	}

	public Node knapsackBestFirst() {
		PriorityQueue pq = new PriorityQueue();
		Node v = items.get(1);
		Node root = createRootElement(v);
		pq.enqueue(root);
		Node result = root;
		float best = root.getPrice();
		while (!pq.isEmpty()){
			v = pq.dequeue();
			if (v.getBound() < best) {
				break;
			}
			Node[] childNodes = getChilds(v);
			for (Node c : childNodes) { // Visit each child.
				if (promising(c)) {
					if (c.getPrice() > best) {
						best = c.getPrice();
						result = c;
					}
					if (calculateBound(c) > best) {
						pq.enqueue(c);
					}
				}
			}
		}
		return result;
	}

	private Node createRootElement(Node v) {
		Node root = new Node(0, 0);
		root.setBound(bound(v));
		return root;
	}

	private float calculateBound(Node v) {
		float bound = bound(v);
		v.setBound(bound);
		return bound;
	}

}
