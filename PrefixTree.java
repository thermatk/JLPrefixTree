public class PrefixTree
{
    private char character;
    private PrefixTree left;
    private PrefixTree right;

    public PrefixTree ()//
    {
        character = CharStdIn.readChar();
        if (character == '*')
        {
            left = new PrefixTree ();
            right = new PrefixTree ();
        }
        else return;
    }

    public void preorder ()
    {
        int k = 0;
        if (character == '*')
        {
            left.preorder();
            right.preorder();
            k++;
        }
        else
        {
            System.out.print(character+" ");
            System.out.println(k);
        }

    }
      public static void main(String[] args)
      {          PrefixTree tree = new PrefixTree();
          tree.preorder();
      }
}