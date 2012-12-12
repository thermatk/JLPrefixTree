
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

	private void preorder(String encoding) {
		if (character == '*') {
			left.preorder(encoding + "0");
			right.preorder(encoding + "1");

		} else {
			// TODO: max format numbers
			System.out.printf("%s	%d	%s\n", character, encoding.length(),
					encoding);
		}

	}

	private boolean isEnding() {
		if (character == '*') {
			return false;
		}
		return true;
	}

	public void uncompress() {
		if (isEnding()) {
			System.out.print(Character.toString(character));
			return;
		}
		if (!CharStdIn.isEmpty()) {
			char currentChar = CharStdIn.readChar();

			if (currentChar == '0') {
				left.uncompress();
			} else if (currentChar == '1') {
				right.uncompress();
			}

		}
	}
	
	public static void outputTable(PrefixTree tree) {
		System.out.println("char	bits	encoding");
		tree.preorder("");
		System.out.println();
	}

	public static void main(String[] args) {
		PrefixTree tree = new PrefixTree();
		
		outputTable(tree);
		// TODO: output, fixes&cleanup, comments!, readme
		while (!CharStdIn.isEmpty()) {
			tree.uncompress();
		}
	}
}