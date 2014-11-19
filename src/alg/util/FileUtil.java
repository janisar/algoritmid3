package alg.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import alg.data.massive.Massive;
import alg.node.Item;
import alg.node.Node;


public class FileUtil {

	private static final String PATH_TO_FILE = "/home/janis/Desktop/algoritmid/";

	@SuppressWarnings("resource")
	public static int getMaxIndex() {
		File file = new File(PATH_TO_FILE + "alg.in");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			line = br.readLine();
			if (line != null) {
				return Integer.valueOf(line);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println("Cannot read file '" + file.getAbsolutePath() + "'");
		} catch (IOException e) {
			System.err.println("Something went wrong while reading '" + file.getAbsolutePath() + "'");
		}
		return 0;
	}

	public static Massive getItems(Massive result, String fileName) {
		File file = new File(PATH_TO_FILE + fileName);
		addRootNode(result);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			while ((line = br.readLine()) != null ) {
				Node item = createNode(line);
				result.add(item);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println("Cannot read file '" + file.getAbsolutePath() + "'");
		} catch (IOException e) {
			System.err.println("Something went wrong while reading '" + file.getAbsolutePath() + "'");
		}
		return result;
	}

	private static void addRootNode(Massive result) {
		result.add(new Node(0, 0));
	}

	public static void printNodeToFile(Node node) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(PATH_TO_FILE + "alg.out", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		writer.println(node.getPrice() + " " + node.getWeight());
		List<Item> items = node.getItems();
		for (Item i : items) {
			writer.println(i.getPrice() + " " + i.getWeight());
		}
		writer.close();
	}

	private static Node createNode(String line) {
		String[] parts = line.split("\\s+");
		int weight = Integer.valueOf(parts[1]);
		int price = Integer.valueOf(parts[0]);
		return new Node(weight, price);
	}
}
