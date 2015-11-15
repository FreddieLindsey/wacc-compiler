package wacc;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import wacc.ast.ASTTree;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;

import static java.nio.file.Files.walkFileTree;
import static org.junit.Assert.*;

public class MainTest {

  @Test
  public void test_test() {
    assertTrue(Main.test_test());
  }

  @Test
  public void validTests() throws IOException {
    walkFileTree(FileSystems.getDefault().getPath("wacc", "valid"), new FileVisitor<Path>() {
      @Override
      public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return null;
      }

      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (file.toString().endsWith(".wacc")) {
          try {
            ParseTree pt = Main.parseInput(new FileInputStream(String.valueOf(file)));
            ASTTree ast = Main.analyseFile(pt);
          } catch (IOException e) {
            System.out.println("Encountered an error parsing/analysing file: "
              + file.toString() + e);
          }
        }

        return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return null;
      }

      @Override
      public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return null;
      }
    });
  }

  @Test
  public void invalidSyntaxTests() {

  }

  @Test
  public void invalidSemanticTests() {

  }

}
