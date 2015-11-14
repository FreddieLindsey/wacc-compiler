package wacc;

import antlr.BasicLexer;
import antlr.BasicParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ANTLRInputStream in = new ANTLRInputStream(System.in);

        BasicLexer lexer = new BasicLexer(in);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        BasicParser parser = new BasicParser(tokens);

        ParseTree tree = parser.program();

        System.out.println(tree.toStringTree(parser));
    }

    public static boolean test_test() {
      return true;
    }
}
