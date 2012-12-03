/*************************************************************************
 *  Compilation:  javac CharStdIn.java
 *  Execution:    java CharStdIn
 *  
 *  Supports reading variables of type char from stdin.
 * 
 *************************************************************************/

 import java.io.IOException;

 public class CharStdIn {
    private static final int EOF = -1;     // end of file
    private static int c = EOF;

    // return EOF if end of file or IO error
    public static boolean isEmpty() {
        if (c == EOF) {
            try { c = System.in.read(); }
            catch(IOException e) { c = EOF; }
        }
        return (c == EOF);
    }


    // return EOF if end of file or IO error
    public static char readChar() {
        if (isEmpty()) {
            System.err.println("Error: tried to read from empty input stream.");
            System.exit(1);
        }
        char ch = (char) c;
        c = EOF;
        return ch;
    }
    


   // echo test client
   public static void main(String[] args) {
       while (!CharStdIn.isEmpty()) {
           System.out.print(CharStdIn.readChar());
       }

   }

}
