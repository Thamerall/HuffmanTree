/*
 * Thamer Allahabi
 * Huffman Project
 * CSCI 242
 */
public class HuffTree implements Comparable<HuffTree> {

	private Node_Tree root; // tree root

	// construct tree with two subtrees
	public HuffTree(HuffTree t1, HuffTree t2) {
		root = new Node_Tree();
		root.element = (char)(t1.root.element + t2.root.element);
		root.left = t1.root;
		root.right = t2.root;
		root.weight = t1.root.weight + t2.root.weight;
	}

	// construct tree containing a leaf node
	public HuffTree(int weight, char element) {
		root = new Node_Tree(weight, element);
	}

	// return huffman tree root
	public Node_Tree getRoot()
	{
		return root;
	}

	// Compare trees based on their weights
	public int compareTo(HuffTree t2) {
		if (root.weight < t2.root.weight)
			return 1;
		else if (root.weight == t2.root.weight)
			return 0;
		else
			return -1;
	}

}
