package alg.node;
import java.util.ArrayList;
import java.util.List;

public class Node {

	List<Item> allItems;
	public Item item;
	int weight;
	int price;
	float bound;

	public Node(int weight, int price) {
		allItems = new ArrayList<Item>();
		this.weight = weight;
		this.price = price;
		item = new Item(weight, price);
	}

	public Node(int w, int p, Node node, Item item, boolean selected) {
		this.item = item;
		addItems(node);
		addItem(item, selected);
		calculateParameters();
	}

	private void calculateParameters() {
		int weight = 0;
		int price = 0;
		for (Item i : allItems) {
			weight += i.getWeight();
			price += i.getPrice();
		}
		this.weight = weight;
		this.price = price;
	}

	private void addItems(Node node) {
		allItems = new ArrayList<Item>();
		List<Item> nodeItems = node.getItems();
		for (Item i : nodeItems) {
			if (i.getPrice() != 0 && i.getWeight() != 0) {
				allItems.add(i);
			}
		}
	}

	public void addItem(Item item, boolean selected) {
		if (selected) {
			allItems.add(item);
		}
	}

	public boolean promising(int maxWeight) {
		return maxWeight >= weight;
	}

	public int getPrice() {
		return price;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<Item> getItems() {
		return allItems;
	}

	public void setBound(float bound) {
		this.bound = bound;
	}

	public float getBound() {
		return bound;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Node)) {
			return false;
		}
		Node other = (Node) obj;
		if (other.item.equals(item)) {
			return true;
		}
		return false;
	}

	public void addPreviousItems(Node node) {
		if (node.item != null) {
			addItem(node.item, true);
		}
		if (node.getPrice() != 0 && node.getItems() != null) {
			addItems(node);
		}
	}

	public int getTotalPrice(boolean selected) {
		int result = 0;
		if (selected) {
			result = price;
		}
		for (Item i : allItems) {
			result += i.getPrice();
		}
		return result;
	}

	public int getTotalWeight(boolean selected) {
		int result = 0;
		if (selected) {
			result = weight;
		}
		for (Item i : allItems) {
			result += i.getWeight();
		}
		return result;
	}

	public Node copyLeft(Node node) {
		return getLeftNode(node);
	}

	public Node copyRight(Node node) {
		return getRightNode(node);
	}

	private Node getRightNode(Node node) {
		Node node2 = new Node(0, 0, node, item, false);
		return node2;
	}

	private Node getLeftNode(Node node) {
		Node node2 = new Node(weight, price, node, item, true);
		return node2;
	}
}