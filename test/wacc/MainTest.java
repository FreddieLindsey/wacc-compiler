package wacc;

import antlr.BasicParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.Files.walkFileTree;
import static org.junit.Assert.*;

public class MainTest {

  private final PrintStream curr = System.out;
  private final PrintStream err = System.err;
  private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
  private final PrintStream replace = new PrintStream(baos);
  private boolean errors_syn = false;
  private boolean errors_sem = false;

  @Test
  public void validTests() throws IOException {
    SimpleFileVisitor<Path> fv = new SimpleFileVisitor<Path>() {
      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (file.toString().endsWith(".wacc")) {
          captureOut();
          try {
            BasicParser parser = Main.parseInput(new FileInputStream(String.valueOf(file)));
            ParseTree pt = parser.program();
            resetOut();
            if (!baos.toString().equals("")) {
              errors_syn = true;
              System.err.println("--------------------------------------\n"
                + "Valid file\t" + file.toFile().getName()
                + "\nat location\t" + file.toString());
            }
            ASTTree ast = Main.analyseFile(pt);
            assertTrue(true); // TODO: Design and implement
          } catch (IOException e) {
            resetOut();
            System.out.println("Encountered an error parsing/analysing file: "
              + file.toString() + e);
          }
        }
        return FileVisitResult.CONTINUE;
      }
    };
    walkFileTree(FileSystems.getDefault().getPath("wacc_examples", "valid"), fv);
    if (errors_syn || errors_sem) {
      System.err.println("--------------------------------------\n"
        + "The above files shouldn't have had errors but didn't. Check the grammar!\n\n");
    }
  }

  @Test
  public void invalidSyntaxTests() throws IOException {
    SimpleFileVisitor<Path> fv = new SimpleFileVisitor<Path>() {
      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (file.toString().endsWith(".wacc")) {
          captureOut();
          try {
            BasicParser parser = Main.parseInput(new FileInputStream(String.valueOf(file)));
            ParseTree pt = parser.program();
            resetOut();
            if (baos.toString().equals("")) {
              errors_syn = true;
              System.err.println("--------------------------------------\n"
                + "Invalid file\t" + file.toFile().getName()
                + "\nat location\t" + file.toString());
            }
          } catch (IOException e) {
            resetOut();
            System.out.println("Encountered an error parsing/analysing file: "
              + file.toString() + e);
          }
        }
        return FileVisitResult.CONTINUE;
      }
    };
    walkFileTree(FileSystems.getDefault().getPath("wacc_examples", "invalid", "syntaxErr"), fv);
    if (errors_syn) {
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
          captureOut();
          try {
            BasicParser parser = Main.parseInput(new FileInputStream(String.valueOf(file)));
            ParseTree pt = parser.program();
            resetOut();
            if (!baos.toString().equals("")) {
              errors_syn = true;
              System.err.println("--------------------------------------\n"
                + "Invalid file\t" + file.toFile().getName()
                + "\nat location\t" + file.toString());
            }
            ASTTree ast = Main.analyseFile(pt);
            assertTrue(true); // TODO: Design and implement AST to report semantic errors
          } catch (IOException e) {
            resetOut();
            System.out.println("Encountered an error parsing/analysing file: "
              + file.toString() + e);
          }
        }
        return FileVisitResult.CONTINUE;
      }
    };
    walkFileTree(FileSystems.getDefault().getPath("wacc_examples", "invalid", "semanticErr"), fv);
    if (errors_sem) {
      System.err.println("--------------------------------------\n"
        + "The above files should have had errors but didn't. Check the grammar!\n\n");
    }
  }

  private void captureOut() {
    baos.reset();
    System.setErr(replace);
    System.setOut(replace);
  }

  private void resetOut() {
    System.setErr(err);
    System.setOut(curr);
  }

}
