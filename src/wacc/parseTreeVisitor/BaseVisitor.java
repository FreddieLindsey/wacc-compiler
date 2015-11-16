package wacc.parseTreeVisitor;

import antlr.BasicParser;
import antlr.BasicParserBaseVisitor;
import antlr.BasicParserVisitor;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import wacc.ast.*;

import java.util.ArrayList;
import java.util.List;

public class BaseVisitor<ASTNode> extends BasicParserBaseVisitor<ASTNode> {
  @Override
  public ASTNode visitArglist(@NotNull BasicParser.ArglistContext ctx) {
    ArrayList<ExprNode> exprs = new ArrayList<>();
    ArgListNode a = new ArgListNode(null, new ExprNode[]{});
    for (BasicParser.ExprContext e : ctx.expr()) {
      ExprNode expr = (ExprNode) visitExpr(e);
      expr.setParent(a);
      exprs.add(expr);
    }
    a.setExprs((ExprNode[]) exprs.toArray());
    return (ASTNode) a;
  }

  @Override
  public ASTNode visitCharliter(@NotNull BasicParser.CharliterContext ctx) {
    return (ASTNode) new CharNode(null, ctx.CHARAC().getText().charAt(0));
  }

  @Override
  public ASTNode visitAssignlhs(@NotNull BasicParser.AssignlhsContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitParam(@NotNull BasicParser.ParamContext ctx) {
    ParamNode p = new ParamNode(
      null,
      ((TypeNode) visitType(ctx.type())).getType(),
      (IdentNode) visitIdent(ctx.ident()));
    p.getIdent().setParent(p);
    return (ASTNode) p;
  }

  @Override
  public ASTNode visitBoolliter(@NotNull BasicParser.BoolliterContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitExpr(@NotNull BasicParser.ExprContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitArrayelem(@NotNull BasicParser.ArrayelemContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitType(@NotNull BasicParser.TypeContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitParamlist(@NotNull BasicParser.ParamlistContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitArraytype(@NotNull BasicParser.ArraytypeContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitIdent(@NotNull BasicParser.IdentContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitUnaryoper(@NotNull BasicParser.UnaryoperContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitAssignrhs(@NotNull BasicParser.AssignrhsContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitFunc(@NotNull BasicParser.FuncContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitStrliter(@NotNull BasicParser.StrliterContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitIntsign(@NotNull BasicParser.IntsignContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitBinaryOper(@NotNull BasicParser.BinaryOperContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitStat(@NotNull BasicParser.StatContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitArrayliter(@NotNull BasicParser.ArrayliterContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitPairtype(@NotNull BasicParser.PairtypeContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitBasetype(@NotNull BasicParser.BasetypeContext ctx) {
    if (ctx.BOOL() != null) {
      return visitBoolliter((BasicParser.BoolliterContext) ctx.BOOL());
    } else if (ctx.CHAR() != null) {
      return visitCharliter((BasicParser.CharliterContext) ctx.CHAR());
    } else if (ctx.INT() != null) {
      return visitIntliter((BasicParser.IntliterContext) ctx.INT());
    } else if (ctx.STRING() != null) {
      return visitStrliter((BasicParser.StrliterContext) ctx.STRING());
    }
    return null;
  }

  @Override
  public ASTNode visitProgram(@NotNull BasicParser.ProgramContext ctx) {
    ArrayList<FuncNode> funcs = new ArrayList<>();
    ProgramNode prog = new ProgramNode(null);
    for (BasicParser.FuncContext func : ctx.func()) {
      FuncNode f = (FuncNode) visitFunc(func);
      f.setParent(prog);
      prog.add(f);
    }
    prog.setStat((StatNode) visitStat(ctx.stat()));
    return (ASTNode) prog;
  }

  @Override
  public ASTNode visitPairliter(@NotNull BasicParser.PairliterContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitPairelem(@NotNull BasicParser.PairelemContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitPairelemtype(@NotNull BasicParser.PairelemtypeContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitIntliter(@NotNull BasicParser.IntliterContext ctx) {
    return null;
  }

  @Override
  public ASTNode visit(@NotNull ParseTree parseTree) {
    return null;
  }

  @Override
  public ASTNode visitChildren(@NotNull RuleNode ruleNode) {
    return null;
  }

  @Override
  public ASTNode visitTerminal(@NotNull TerminalNode terminalNode) {
    return null;
  }

  @Override
  public ASTNode visitErrorNode(@NotNull ErrorNode errorNode) {
    return null;
  }
}
