package wacc.parseTreeVisitor;

import antlr.BasicParser;
import antlr.BasicParserBaseVisitor;
import org.antlr.v4.runtime.misc.NotNull;
import wacc.ast.*;

import java.util.ArrayList;

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
    if (ctx.pairelem() != null) {
      return visitPairelem(ctx.pairelem());
    } else if (ctx.arrayelem() != null) {
      return visitArrayelem(ctx.arrayelem());
    } else if (ctx.ident() != null) {
      return visitIdent(ctx.ident());
    }
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
    return (ASTNode) new BoolNode(null, ctx.TRUE() != null);
  }

  @Override
  public ASTNode visitExpr(@NotNull BasicParser.ExprContext ctx) {
    if (ctx.binaryOper() != null) {
      BinOpNode b = (BinOpNode) visitBinaryOper(ctx.binaryOper());
      b.setLHS((ExprNode) visitExpr(ctx.expr(0)));
      b.setRHS((ExprNode) visitExpr(ctx.expr(1)));
      return (ASTNode) b;
    } else if (ctx.intliter() != null) {
      return visitIntliter(ctx.intliter());
    } else if (ctx.boolliter() != null) {
      return visitBoolliter(ctx.boolliter());
    } else if (ctx.charliter() != null) {
      return visitCharliter(ctx.charliter());
    } else if (ctx.strliter() != null) {
      return visitStrliter(ctx.strliter());
    } else if (ctx.pairliter() != null) {
      return visitPairliter(ctx.pairliter());
    } else if (ctx.ident() != null) {
      return visitIdent(ctx.ident());
    } else if (ctx.arrayelem() != null) {
      return visitArrayelem(ctx.arrayelem());
    } else if (ctx.unaryoper() != null) {
      UnOpNode u = (UnOpNode) visitUnaryoper(ctx.unaryoper());
      ExprNode e = (ExprNode) visitExpr(ctx.expr(0));
      e.setParent(u);
      u.setExpr(e);
      return (ASTNode) u;
    }
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
    return (ASTNode) new IdentNode(null, ctx.getText());
  }

  @Override
  public ASTNode visitUnaryoper(@NotNull BasicParser.UnaryoperContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitAssignrhs(@NotNull BasicParser.AssignrhsContext ctx) {
    if (ctx.CALL() != null) {
      return (ASTNode) new CallNode(
        null,
        (IdentNode) visitIdent(ctx.ident()),
        (ArgListNode) visitArglist(ctx.arglist()));
    } else if (ctx.NEWPAIR() != null) {
      if (ctx.expr().size() != 2) return null;
      return (ASTNode) new NewPairNode<ExprNode, ExprNode>(
        null,
        (ExprNode) visitExpr(ctx.expr(0)),
        (ExprNode) visitExpr(ctx.expr(1)));
    } else if (ctx.arrayliter() != null) {
      return visitArrayliter(ctx.arrayliter());
    } else if (ctx.pairelem() != null) {
      return visitPairelem(ctx.pairelem());
    } else if (ctx.ident() != null) {
      return visitIdent(ctx.ident());
    } else {
      return visitExpr(ctx.expr(0));
    }
  }

  @Override
  public ASTNode visitFunc(@NotNull BasicParser.FuncContext ctx) {
    FuncNode f = new FuncNode(
      null,
      (TypeNode) visitType(ctx.type()),
      (IdentNode) visitIdent(ctx.ident()),
      (ParamListNode) visitParamlist(ctx.paramlist()),
      (StatNode) visitStat(ctx.stat()));
    f.getType().setParent(f);
    f.getIdent().setParent(f);
    f.getStat().setParent(f);
    for (ParamNode p : f.getParams().getParams()) {
      p.setParent(f);
    }
    return (ASTNode) f;
  }

  @Override
  public ASTNode visitStrliter(@NotNull BasicParser.StrliterContext ctx) {
    return (ASTNode) new StringNode(null, ctx.getText());
  }

  @Override
  public ASTNode visitIntsign(@NotNull BasicParser.IntsignContext ctx) {
    return (ASTNode) ((ctx.SUB() != null) ?
      new IntNode(null, -1) :
      new IntNode(null,  1));
  }

  @Override
  public ASTNode visitBinaryOper(@NotNull BasicParser.BinaryOperContext ctx) {
    if (ctx.MUL() != null) {
      return (ASTNode) new BinOpNode(null, null, BinaryOperator.MUL, null);
    } else if (ctx.DIV() != null) {
      return (ASTNode) new BinOpNode(null, null, BinaryOperator.DIV, null);
    } else if (ctx.MOD() != null) {
      return (ASTNode) new BinOpNode(null, null, BinaryOperator.MOD, null);
    } else if (ctx.ADD() != null) {
      return (ASTNode) new BinOpNode(null, null, BinaryOperator.ADD, null);
    } else if (ctx.SUB() != null) {
      return (ASTNode) new BinOpNode(null, null, BinaryOperator.SUB, null);
    } else if (ctx.G() != null) {
      return (ASTNode) new BinOpNode(null, null, BinaryOperator.GT, null);
    } else if (ctx.GE() != null) {
      return (ASTNode) new BinOpNode(null, null, BinaryOperator.GTE, null);
    } else if (ctx.L() != null) {
      return (ASTNode) new BinOpNode(null, null, BinaryOperator.LT, null);
    } else if (ctx.LE() != null) {
      return (ASTNode) new BinOpNode(null, null, BinaryOperator.LTE, null);
    } else if (ctx.EQ() != null) {
      return (ASTNode) new BinOpNode(null, null, BinaryOperator.EQ, null);
    } else if (ctx.NEQ() != null) {
      return (ASTNode) new BinOpNode(null, null, BinaryOperator.NEQ, null);
    } else if (ctx.AND() != null) {
      return (ASTNode) new BinOpNode(null, null, BinaryOperator.AND, null);
    } else if (ctx.OR() != null) {
      return (ASTNode) new BinOpNode(null, null, BinaryOperator.OR, null);
    }
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
    return (ASTNode) new IntNode(null, Long.valueOf(ctx.getText()));
  }
}
