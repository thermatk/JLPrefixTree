
public class PrefixTree {
    private char character;
	private PrefixTree left;
	private PrefixTree right;
	
	private static int numBits;
	private static int numChars;
	
	private static boolean uncompressed = false;

	public PrefixTree() {
		character = CharStdIn.readChar();
		if (!CharStdIn.isEmpty()) {
			if (character == '*') {
				left = new PrefixTree();
				right = new PrefixTree();
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

	private void uncompress() {
		if (isEnding()) {
			System.out.print(Character.toString(character));
			numChars++;
			return;
			
		}
		if (!CharStdIn.isEmpty()) {
			char currentChar = CharStdIn.readChar();
			
			if (currentChar == '0') {
				numBits++;
				left.uncompress();
			} else if (currentChar == '1') {
				numBits++;
				right.uncompress();
			} else if (numBits != 0) {
				uncompressed = true;
			}
		}
	}
	
	public static void outputTable(PrefixTree tree) {
		System.out.println("char	bits	encoding");
		tree.preorder("");
		System.out.println();
	}
	
	public static void outputUncompress(PrefixTree tree) {
		while (!uncompressed && !CharStdIn.isEmpty()) {
			tree.uncompress();
		}
	}
	
	public static void outputStatistics() {
		System.out.println();
		System.out.println("Number of bits		 = " + numBits);
		System.out.println("Number of characters = " + numChars);
		double compression = (numBits / (double) (numChars * 8)) * 100;
		System.out.println("Compression ratio	 = " + compression);
	}

	public static void main(String[] args) {
		PrefixTree tree = new PrefixTree();
		
		outputTable(tree);
		// TODO: output, fixes&cleanup, comments!, readme
		outputUncompress(tree);
		outputStatistics();		
	}
}