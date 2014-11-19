package alg.node;


public class Item implements Comparable<Item> {

	private int weight;
	private int price;
	private float pw;

	public Item(int weight, int price) {
		this.weight = weight;
		this.price = price;
		pw = (float)price / weight;
	}

	public int getPrice() {
		return price;
	}

	public int getWeight() {
		return weight;
	}

	public float getPw() {
		return pw;
	}

	@Override
	public int compareTo(Item arg0) {
		if (pw == arg0.getPw()) {
			return  weight - arg0.getWeight();
		}
		if ((int)(arg0.getPw() - pw) == 0 ) {
			if ((arg0.getPw() - pw) > 0) {
				return 1;
			} else {
				return -1;
			}
		}
		return (int) (arg0.getPw() - pw);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Item)) {
			return false;
		}
		Item other = (Item) obj;
		if (price != other.price) {
			return false;
		}
		if (pw == Float.NaN && other.pw == Float.NaN) {
			if (pw != other.pw) {
				return false;
			}
		}
		if (weight != other.weight) {
			return false;
		}
		return true;
	}

	public Item copy() {
		return new Item(weight, price);
	}

}
