package wacc;

import antlr.BasicParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import wacc.ast.ASTTree;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.Files.walkFileTree;
import static org.junit.Assert.*;

public class MainTest {

  private abstract class TestVisitor<Path> implements FileVisitor<Path> {
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
      return null;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
      return null;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
      return null;
    }
  }

  @Test
  public void test_test() {
    assertTrue(Main.test_test());
  }

  @Test
  public void validTests() throws IOException {
    TestVisitor<Path> fv = new TestVisitor<Path>() {
      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (file.toString().endsWith(".wacc")) {
          try {
            BasicParser parser = Main.parseInput(new FileInputStream(String.valueOf(file)));
            ParseTree pt = parser.program();
            ASTTree ast = Main.analyseFile(pt);
          } catch (IOException e) {
            System.out.println("Encountered an error parsing/analysing file: "
              + file.toString() + e);
          }
        }
        return FileVisitResult.CONTINUE;
      }
    };
    walkFileTree(FileSystems.getDefault().getPath("wacc", "valid"), fv);
  }

  @Test
  public void invalidSyntaxTests() throws IOException {
    TestVisitor<Path> fv = new TestVisitor<Path>() {
      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (file.toString().endsWith(".wacc")) {
          try {
            BasicParser parser = Main.parseInput(new FileInputStream(String.valueOf(file)));
            ParseTree pt = parser.program();
            ASTTree ast = Main.analyseFile(pt);
          } catch (IOException e) {
            System.out.println("Encountered an error parsing/analysing file: "
              + file.toString() + e);
          }
        }
        return FileVisitResult.CONTINUE;
      }
    };
    walkFileTree(FileSystems.getDefault().getPath("wacc", "invalid", "syntaxErr"), fv);
  }

  @Test
  public void invalidSemanticTests() throws IOException {
    TestVisitor<Path> fv = new TestVisitor<Path>() {
      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (file.toString().endsWith(".wacc")) {
          try {
            BasicParser parser = Main.parseInput(new FileInputStream(String.valueOf(file)));
            ParseTree pt = parser.program();
            ASTTree ast = Main.analyseFile(pt);
          } catch (IOException e) {
            System.out.println("Encountered an error parsing/analysing file: "
              + file.toString() + e);
          }
        }
        return FileVisitResult.CONTINUE;
      }
    };
    walkFileTree(FileSystems.getDefault().getPath("wacc", "invalid", "semanticErr"), fv);
  }

}
