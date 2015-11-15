package wacc;

import antlr.BasicParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import wacc.ast.ASTTree;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.Files.walkFileTree;
import static org.junit.Assert.*;

public class MainTest {

  @Test
  public void validTests() throws IOException {
    SimpleFileVisitor<Path> fv = new SimpleFileVisitor<Path>() {
      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (file.toString().endsWith(".wacc")) {
          try {
            BasicParser parser = Main.parseInput(new FileInputStream(String.valueOf(file)));
            ParseTree pt = parser.program();
            ASTTree ast = Main.analyseFile(pt);
            assertTrue(true);
          } catch (IOException e) {
            System.out.println("Encountered an error parsing/analysing file: "
              + file.toString() + e);
          }
        }
        return FileVisitResult.CONTINUE;
      }
    };
    walkFileTree(FileSystems.getDefault().getPath("wacc_examples", "valid"), fv);
  }

  @Test
  public void invalidSyntaxTests() throws IOException {
    final boolean[] errors = {false};
    SimpleFileVisitor<Path> fv = new SimpleFileVisitor<Path>() {
      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (file.toString().endsWith(".wacc")) {
          PrintStream curr = System.out;
          PrintStream err = System.err;
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          PrintStream replace = new PrintStream(baos);
          System.setErr(replace);
          System.setOut(replace);
          try {
            BasicParser parser = Main.parseInput(new FileInputStream(String.valueOf(file)));
            ParseTree pt = parser.program();
            System.setErr(err);
            System.setOut(curr);
            if (baos.toString().equals("")) {
              errors[0] = true;
              System.err.println("--------------------------------------\n"
                + "Invalid file\t" + file.toFile().getName()
                + "\nat location\t" + file.toString());
            }
          } catch (IOException e) {
            System.setErr(err);
            System.setOut(curr);
            System.out.println("Encountered an error parsing/analysing file: "
              + file.toString() + e);
          }
        }
        return FileVisitResult.CONTINUE;
      }
    };
    walkFileTree(FileSystems.getDefault().getPath("wacc_examples", "invalid", "syntaxErr"), fv);
    if (errors[0]) {
      System.err.println("--------------------------------------\n"
        + "The above files should have had errors but didn't. Check the grammar!\n\n");
    }
  }

  @Test
  public void invalidSemanticTests() throws IOException {
    SimpleFileVisitor<Path> fv = new SimpleFileVisitor<Path>() {
      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (file.toString().endsWith(".wacc")) {
          try {
            BasicParser parser = Main.parseInput(new FileInputStream(String.valueOf(file)));
            ParseTree pt = parser.program();
            ASTTree ast = Main.analyseFile(pt);
            assertTrue(true); // TODO: Design and implement AST to report semantic errors
          } catch (IOException e) {
            System.out.println("Encountered an error parsing/analysing file: "
              + file.toString() + e);
          }
        }
        return FileVisitResult.CONTINUE;
      }
    };
    walkFileTree(FileSystems.getDefault().getPath("wacc_examples", "invalid", "semanticErr"), fv);
  }

}
