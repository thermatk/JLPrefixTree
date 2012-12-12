/*************************************************************************
 *  Authors: Руслан Бойцов & Арсений Самсонов
 *  Compilation: javac -cp .:stdlib.jar PrefixTree.java
 *  Execution: java  -cp .:stdlib.jar PrefixTree < input
 * 
 *    По введённым в стандартный ввод в особом формате обходу дерева и сжатому бинарному сообщению
 *	производит вывод таблицы символов, расшифровки и статистики по сжатию.
 *  
 *  % java  -cp .:stdlib.jar PrefixTree
 *  *a**!*dc*rb
 *  0111110010110101001111100100
 *  char	bits	encoding
 *  a	1	0
 *  !	3	100
 *  d	4	1010
 *  c	4	1011
 *  r	3	110
 *  b	3	111
 *  abracadabra!
 *  Number of bits		 = 28
 *  Number of characters = 12
 *  Compression ratio	 = 29.166666666666668
 */

/**
 * @author thermatk
 * @author remkwadriga Основной класс
 */
public class PrefixTree {
	/**
	 * Значение вершины, левая и правая ветки
	 */
	private char character;
	private PrefixTree left;
	private PrefixTree right;

	/**
	 * Статичные перменные для подсчёта статистики
	 */
	private static int numBits;
	private static int numChars;

	/**
	 * Триггер, чтобы выходить из цикла расшифровки (если нет 0 и 1)
	 */
	private static boolean uncompressed = false;

	/**
	 * Конструктор, рекурсивно собирает дерево по вводимому обходу
	 */
	public PrefixTree() {
		// Считать символ в новый PrefixTree
		character = CharStdIn.readChar();
		// Если есть символы на вводе
		if (!CharStdIn.isEmpty()) {
			// если символ '*', дробим на два
			if (character == '*') {
				left = new PrefixTree();
				right = new PrefixTree();
			}
		}
	}

	/**
	 * Рекурсивный обход дерева в прямом порядке
	 * 
	 * @param encoding
	 *            какой путь уже проделан до текущего объекта
	 */
	private void preorder(String encoding) {
		// Если промежуточная вершина, дробить и записать в путь
		if (character == '*') {
			left.preorder(encoding + "0");
			right.preorder(encoding + "1");

		} else {
			// иначе - вывести найденную вершину, длину пути и путь
			System.out.printf("%s	%d	%s\n", character, encoding.length(),
					encoding);
		}

	}

	/**
	 * Является ли вершина конечной?
	 * 
	 * @return да или нет
	 */
	private boolean isEnding() {
		if (character == '*') {
			return false;
		}
		return true;
	}

	/**
	 * Рекурсивно расшифровывает вводимое в стандартный ввод
	 */
	private void uncompress() {
		// Если дошли до свободной вершины - вывести и выйти
		if (isEnding()) {
			System.out.print(Character.toString(character));
			numChars++;
			return;
		}
		// Если ещё есть символы на вводе
		if (!CharStdIn.isEmpty()) {
			// считать
			char currentChar = CharStdIn.readChar();
			if (currentChar == '0') {
				// влево
				numBits++;
				left.uncompress();
			} else if (currentChar == '1') {
				// вправо
				numBits++;
				right.uncompress();
			} else if (numBits != 0) {
				// если уже не начало считывания, но вдруг не 0 или 1, значит
				// закончили
				uncompressed = true;
			}
		}
	}

	/**
	 * Выводит таблицу с результатами прямого обхода
	 * 
	 * @param tree
	 *            Корень дерева
	 */
	public static void outputTable(PrefixTree tree) {
		// Заголовок
		System.out.println("char	bits	encoding");
		// основная функция обхода
		tree.preorder("");
		// просто отступ
		System.out.println();
	}

	/**
	 * Выводит расшифровку
	 * 
	 * @param tree
	 *            Корень дерева
	 */
	public static void outputUncompress(PrefixTree tree) {
		// для каждого символа, пока на вводе есть нули и единицы, идём от корня
		while (!uncompressed && !CharStdIn.isEmpty()) {
			tree.uncompress();
		}
	}

	/**
	 * Вывод статистики
	 */
	public static void outputStatistics() {
		// отступ и вывод статистики
		System.out.println();
		System.out.println("Number of bits = " + numBits);
		System.out.println("Number of characters = " + numChars);
		double compression = (numBits / (double) (numChars * 8)) * 100;
		System.out.println("Compression ratio = " + compression);
	}

	/**
	 * Основная функция
	 * @param args
	 * пустота - наши аргументы
	 */
	public static void main(String[] args) {
		// собираем деревья от корня
		PrefixTree tree = new PrefixTree();

		// вывести прямой обход
		outputTable(tree);
		// вывести расшифровку
		outputUncompress(tree);
		// вывести статистику по сжатию
		outputStatistics();
	}
}