
public class PrefixTree {
    private char character;
	private PrefixTree left;
	private PrefixTree right;

	public PrefixTree() {
		character = CharStdIn.readChar();
		if (!CharStdIn.isEmpty()) {
			if (character == '*') {
				left = new PrefixTree();
				right = new PrefixTree();
			} else {
			}
		}
	}

	public void preorder(String encoding) {
		if (character == '*') {
			left.preorder(encoding + "0");
			right.preorder(encoding + "1");

		} else {
			// TODO: max format numbers
			System.out.printf("%16s%16d%16s\n", character, encoding.length(),
					encoding);
		}

	}

	public boolean isEnding() {
		if (character == '*') {
			return false;
		}
		return true;
	}

	public void uncompress() {
		if (isEnding()) {
			// ending
			// System.out.println("ending");
			System.out.print(Character.toString(character));
			// back to root:
			return;
		}
		if (!CharStdIn.isEmpty()) {
			char currentChar = CharStdIn.readChar();

			if (currentChar == '0') {
				// System.out.println("left");
				left.uncompress();
				//

			} else if (currentChar == '1') {
				//
				// System.out.println("right");
				right.uncompress();

			}

		}
	}

	public static void main(String[] args) {
		PrefixTree tree = new PrefixTree();
		tree.preorder("");
		//
		// System.out.println("back to main");
		
		while (!CharStdIn.isEmpty()) {
			tree.uncompress();
		}
		// TEST!
	}
}