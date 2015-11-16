package wacc.ast;

import org.antlr.v4.runtime.tree.ParseTree;
import wacc.WACCParserBaseVisitor;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

import java.util.Set;

public class ASTTree {

  private final ParseTree parseTree;
  private Set<String> semanticErrors;
  private ASTNode head;
  private WACCParserBaseVisitor visitor;

  public ASTTree(ParseTree parseTree) {
    this.parseTree = parseTree;
  }

  public ASTTree(BasicParser.ProgramContext p) {
  	visitor = new WACCParserBaseVisitor();
  	head = visitProgram(p);
  }


}
