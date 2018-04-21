/*
 * Thamer Allahabi
 * Huffman Project
 * CSCI 242
 */

import java.io.*;
import java.util.Scanner;

public class HuffmanMain {


	public static final int Letter_Num = 128;

	private int[] freqTable = new int[Letter_Num];

	// huffman tree
	private HuffTree huffTree;

	// huffman table
	private String[] huffTable;
    private int writableCount=0;
	public HuffmanMain() {

	}

	// Get characters frequencies
	public void getFrequencies(String filename) {
		// †Read text from input file.

		try {

			// open file
			FileReader fr = new FileReader(new File(filename));
			int ch = fr.read();

			while (ch != -1) {
				int index = ch;
				freqTable[index]++; // Count the character in text

				System.out.print((char) ch);
				ch = fr.read();
			}

			fr.close();
		}


		catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.exit(1);
		}

	}

	// build huffman tree from frequency counts
	public void buildHuffmanTree() {

		// min heap priority tree
		Huffman_Heap<HuffTree> minheap = new Huffman_Heap<>();

		// add characters
		for (int i = 0; i < freqTable.length; i++) {
			if (freqTable[i] > 0)
				minheap.add(new HuffTree(freqTable[i], (char) i));
		}

		// combine sub trees
		while (minheap.getSize() > 1) {
			HuffTree t1 = minheap.remove();

			HuffTree t2 = minheap.remove();

			minheap.add(new HuffTree(t1, t2));
		}

		huffTree = minheap.remove(); // The final tree
	}

	// print huffman tree helper
	void printTree() {

		try {
			PrintWriter pw = new PrintWriter("huffman-image");
			printTree(huffTree.getRoot(), 20, pw);
			pw.close();
		}

		// bad file
		catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
	}

	// print huffman tree helper
	void printTree(Node_Tree node, int level, PrintWriter pw) {
		level++; /* increment level */
		if (node != null) {
			printTree(node.right, level, pw);

			// ident
			for (int i = 0; i < level - 20; i++) {
				pw.print("   ");
				System.out.print("   ");
			}

			// print node
			System.out.println(node.toString());
			pw.println(node.toString());

			printTree(node.left, level, pw);
		}
		level--; /* decrement level */
	}

	// get huffman codes from huffman tree
	public void getCodes(String outfilename) {
		if (huffTree.getRoot() == null)
			return;
		huffTable = new String[2 * 128];

		PrintWriter pw;
		try {
			pw = new PrintWriter(new File(outfilename));
			getCodes(huffTree.getRoot(), pw);
			pw.close();
		}

		// bad file
		catch (FileNotFoundException ex) {

			System.out.println(ex.getMessage());
		}

	}

	// Recursive get codes helper
	private void getCodes(Node_Tree root, PrintWriter pw) {
		if (root.left != null) {
			root.left.code = root.code + "0";
			getCodes(root.left, pw);

			root.right.code = root.code + "1";
			getCodes(root.right, pw);
		} else {
			huffTable[(int) root.element] = root.code;
			System.out.println(root.element + "=" + root.code);
			pw.println(root.element + "=" + root.code);
		}
	}

	// encode message with huffman codes
	public void encode(String infilename, String outfilename) {

		// †Read text from input file.
		// and write huffman codes to output file

		try {

			FileReader fr = new FileReader(new File(infilename));
			PrintWriter pw = new PrintWriter(new File(outfilename));

			int ch = fr.read();
			int length = 0;

			while (ch != -1) {

				// get code for chsaracter
				String codes = huffTable[ch];

				// print code
				System.out.println(codes);
				pw.println(codes);
				length+=codes.length();

				ch = fr.read();
			}

			System.out.println("length = "+length);
			pw.println("length = "+length);
			writableCount=length;
			
			fr.close();
			pw.close();

		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.exit(1);
		}

	}

	public void decode(String infilename, String outfilename) {

		try {

			Scanner fsc = new Scanner(new File(infilename));
            PrintWriter fw = new PrintWriter(new File(outfilename));

			while (fsc.hasNext()) {

				String code = fsc.nextLine();

				// find char for code
				for (int i = 0; i < Letter_Num; i++) {
					if (huffTable[i] != null) {
						if (huffTable[i].equals(code)) {
							System.out.print((char) (i));
							fw.write((char) i);
							break;
						}
					}
				}

			}
            fw.write("length = "+writableCount);
			// close file
			fsc.close();
			fw.close();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.exit(1);
		}

	}

	public static void main(String[] args) {

		// print program title
		System.out.println("Huffman Main");

		// make huffman object
		HuffmanMain huffman = new HuffmanMain();

		// get frequencies
		huffman.getFrequencies("/Users/thamer/IdeaProjects/Huffman/src/input");

		// build huffman tree
		huffman.buildHuffmanTree();

		// print huffman tree
		huffman.printTree();

		// get codes from tree
		huffman.getCodes("/Users/thamer/IdeaProjects/Huffman/src/output");

		// encode
		huffman.encode("/Users/thamer/IdeaProjects/Huffman/src/input", "/Users/thamer/IdeaProjects/Huffman/src/encoded");

		// decode;
		huffman.decode("/Users/thamer/IdeaProjects/Huffman/src/encoded", "/Users/thamer/IdeaProjects/Huffman/src/decoded");

	}

}
 class Node_Tree {

	char element; // leaf node character
	int weight; // weight of the subtree
	Node_Tree left; // left subtree
	Node_Tree right; // right subtree
	String code = ""; // code of this node

	// default tree node
	public Node_Tree() {
	}

	// construct tree node with weight and character
	public Node_Tree(int weight, char element) {
		this.weight = weight;
		this.element = element;
	}

	// tree node info
	public String toString()
	{
		if(element >= ' ' && element < 128)
			return element + ":" + weight;
		else
			return " " + ":" + weight;
	}

}


class Huffman_Heap<E extends Comparable<E>> {

	private java.util.ArrayList<E> list = new java.util.ArrayList<E>();

	// default heap
	public Huffman_Heap() {
	}

	// construct a heap from an array
	public Huffman_Heap(E[] items) {
		for (int i = 0; i < items.length; i++)
			add(items[i]);
	}

	public void add(E newItem) {


		list.add(newItem);

		int currentIndex = list.size() - 1;

		// percolate up
		while (currentIndex > 0) {
			int parentIndex = (currentIndex - 1) / 2;

			// Swap if the current is greater than its parent
			if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0) {
				E temp = list.get(currentIndex);
				list.set(currentIndex, list.get(parentIndex));
				list.set(parentIndex, temp);
			}

			else
				break; // the tree is a heap now

			currentIndex = parentIndex;
		}
	}

	// Remove root from heap
	public E remove() {

		if (list.size() == 0)
			return null;

		E removedItem = list.get(0);
		list.set(0, list.get(list.size() - 1));
		list.remove(list.size() - 1);

		int currentIndex = 0;
		while (currentIndex < list.size()) {
			int leftChildIndex = 2 * currentIndex + 1;
			int rightChildIndex = 2 * currentIndex + 2;

			// Find the maximum
			if (leftChildIndex >= list.size())
				break;
			int maxIndex = leftChildIndex;
			if (rightChildIndex < list.size()) {
				if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0) {
					maxIndex = rightChildIndex;
				}
			}


			if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0) {
				E temp = list.get(maxIndex);
				list.set(maxIndex, list.get(currentIndex));
				list.set(currentIndex, temp);
				currentIndex = maxIndex;
			} else
				break;
		}

		return removedItem;
	}


	public int getSize() {
		return list.size();
	}
}


