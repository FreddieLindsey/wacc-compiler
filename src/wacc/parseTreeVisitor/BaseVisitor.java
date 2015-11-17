package wacc.parseTreeVisitor;

import antlr.BasicParser;
import antlr.BasicParserBaseVisitor;
import org.antlr.v4.runtime.misc.NotNull;
import wacc.ast.*;
import wacc.ast.function.*;
import wacc.ast.io.*;
import wacc.ast.operator.*;
import wacc.ast.pair.*;
import wacc.ast.type.*;

public class BaseVisitor<ASTNode> extends BasicParserBaseVisitor<ASTNode> {
  @Override
  public ASTNode visitArglist(@NotNull BasicParser.ArglistContext ctx) {
    ArgListNode a = new ArgListNode();
    if (ctx.expr() != null) {
      for (BasicParser.ExprContext e : ctx.expr()) {
        a.addExpr((ExprNode) visitExpr(e));
      }
    }
    return (ASTNode) a;
  }

  @Override
  public ASTNode visitCharliter(@NotNull BasicParser.CharliterContext ctx) {
    if (ctx.CHARAC() != null) {
      if (ctx.CHARAC().toString().length() != 1) {
        return (ASTNode) new CharNode(ctx.CHARAC().getText().charAt(1));
      }
      return (ASTNode) new CharNode(ctx.CHARAC().getText().charAt(0));
    } else {
      return (ASTNode) new CharNode('a'); // TODO: Return actual escaped character
    }
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
    return (ASTNode) new ParamNode(
      ((TypeNode) visitType(ctx.type())).getType(),
      (IdentNode) visitIdent(ctx.ident()));
  }

  @Override
  public ASTNode visitBoolliter(@NotNull BasicParser.BoolliterContext ctx) {
    return (ASTNode) new BoolNode(ctx.TRUE() != null);
  }

  @Override
  public ASTNode visitExpr(@NotNull BasicParser.ExprContext ctx) {
    if (ctx.binaryOper() != null) {
      BinOpNode b = (BinOpNode) visitBinaryOper(ctx.binaryOper());
      b.setLHS((ExprNode) visitExpr(ctx.expr(0)));
      b.setRHS((ExprNode) visitExpr(ctx.expr(1)));
      return (ASTNode) b;
    } else if (ctx.unaryoper() != null) {
      UnOpNode u = (UnOpNode) visitUnaryoper(ctx.unaryoper());
      u.setExpr((ExprNode) visitExpr(ctx.expr(0)));
      return (ASTNode) u;
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
    }
    return visitExpr(ctx.expr(0));
  }

  @Override
  public ASTNode visitArrayelem(@NotNull BasicParser.ArrayelemContext ctx) {
    ArrayElemNode aen = new ArrayElemNode(
      (IdentNode) visitIdent(ctx.ident()));
    for (BasicParser.ExprContext e : ctx.expr()) {
      aen.addExpr((ExprNode) visitExpr(e));
    }
    return (ASTNode) aen;
  }

  @Override
  public ASTNode visitType(@NotNull BasicParser.TypeContext ctx) {
    if (ctx.pairtype() != null) {
      return visitPairtype(ctx.pairtype());
    } else if (ctx.arraytype() != null) {
      return visitArraytype(ctx.arraytype());
    } else {
      return visitBasetype(ctx.basetype());
    }
  }

  @Override
  public ASTNode visitParamlist(@NotNull BasicParser.ParamlistContext ctx) {
    ParamListNode pl = new ParamListNode();
    for (BasicParser.ParamContext p : ctx.param()) {
      pl.addParam((ParamNode) visitParam(p));
    }
    return (ASTNode) pl;
  }

  @Override
  public ASTNode visitArraytype(@NotNull BasicParser.ArraytypeContext ctx) {
    if (ctx.basetype() != null) {
      return visitBasetype(ctx.basetype());
    } else if (ctx.arraytype() != null) {
      return visitArraytype(ctx.arraytype());
    } else {
      return visitPairtype(ctx.pairtype());
    }
  }

  @Override
  public ASTNode visitIdent(@NotNull BasicParser.IdentContext ctx) {
    return (ASTNode) new IdentNode(ctx.getText());
  }

  @Override
  public ASTNode visitUnaryoper(@NotNull BasicParser.UnaryoperContext ctx) {
    if (ctx.SUB() != null) {
      return (ASTNode) new UnOpNode(UnaryOperator.NEG);
    } else if (ctx.CHR() != null) {
      return (ASTNode) new UnOpNode(UnaryOperator.CHR);
    } else if (ctx.LEN() != null) {
      return (ASTNode) new UnOpNode(UnaryOperator.LEN);
    } else if (ctx.NOT() != null) {
      return (ASTNode) new UnOpNode(UnaryOperator.NOT);
    } else if (ctx.ORD() != null) {
      return (ASTNode) new UnOpNode(UnaryOperator.ORD);
    }
    return null;
  }

  @Override
  public ASTNode visitAssignrhs(@NotNull BasicParser.AssignrhsContext ctx) {
    if (ctx.CALL() != null) {
      return (ASTNode) new CallNode(
        (IdentNode) visitIdent(ctx.ident()));
    } else if (ctx.NEWPAIR() != null) {
      if (ctx.expr().size() != 2) return null;
      return (ASTNode) new NewPairNode<ExprNode, ExprNode>(
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
      (TypeNode) visitType(ctx.type()),
      (IdentNode) visitIdent(ctx.ident()),
      (StatNode) visitStat(ctx.stat()));
    if (ctx.paramlist() != null) {
      ParamListNode pl = (ParamListNode) visitParamlist(ctx.paramlist());
      for (ParamNode p : pl.getParams()) {
        f.addParam(p);
      }
    }
    return (ASTNode) f;
  }

  @Override
  public ASTNode visitStrliter(@NotNull BasicParser.StrliterContext ctx) {
    return (ASTNode) new StringNode(ctx.getText());
  }

  @Override
  public ASTNode visitIntsign(@NotNull BasicParser.IntsignContext ctx) {
    return (ASTNode) ((ctx.SUB() != null) ?
      new IntNode(-1) :
      new IntNode(1));
  }

  @Override
  public ASTNode visitBinaryOper(@NotNull BasicParser.BinaryOperContext ctx) {
    if (ctx.MUL() != null) {
      return (ASTNode) new BinOpNode(BinaryOperator.MUL);
    } else if (ctx.DIV() != null) {
      return (ASTNode) new BinOpNode(BinaryOperator.DIV);
    } else if (ctx.MOD() != null) {
      return (ASTNode) new BinOpNode(BinaryOperator.MOD);
    } else if (ctx.ADD() != null) {
      return (ASTNode) new BinOpNode(BinaryOperator.ADD);
    } else if (ctx.SUB() != null) {
      return (ASTNode) new BinOpNode(BinaryOperator.SUB);
    } else if (ctx.G() != null) {
      return (ASTNode) new BinOpNode(BinaryOperator.GT);
    } else if (ctx.GE() != null) {
      return (ASTNode) new BinOpNode(BinaryOperator.GTE);
    } else if (ctx.L() != null) {
      return (ASTNode) new BinOpNode(BinaryOperator.LT);
    } else if (ctx.LE() != null) {
      return (ASTNode) new BinOpNode(BinaryOperator.LTE);
    } else if (ctx.EQ() != null) {
      return (ASTNode) new BinOpNode(BinaryOperator.EQ);
    } else if (ctx.NEQ() != null) {
      return (ASTNode) new BinOpNode(BinaryOperator.NEQ);
    } else if (ctx.AND() != null) {
      return (ASTNode) new BinOpNode(BinaryOperator.AND);
    } else if (ctx.OR() != null) {
      return (ASTNode) new BinOpNode(BinaryOperator.OR);
    }
    return null;
  }

  @Override
  public ASTNode visitStat(@NotNull BasicParser.StatContext ctx) {
    if (ctx.READ() != null) {
      BasicStatNode b = new BasicStatNode(StatTypeEnum.READ);
      if (ctx.expr() != null) b.addExpr((ExprNode) visitAssignlhs(ctx.assignlhs()));
      return (ASTNode) b;
    } else if (ctx.FREE() != null) {
      BasicStatNode b = new BasicStatNode(StatTypeEnum.FREE);
      b.addExpr((ExprNode) visitExpr(ctx.expr()));
      return (ASTNode) b;
    } else if (ctx.ASSIGN() != null && ctx.type() != null) {
      return (ASTNode) new NewAssignNode(
        ((TypeNode) visitType(ctx.type())).getType(),
        (IdentNode) visitIdent(ctx.ident()),
        (AssignNode) visitAssignrhs(ctx.assignrhs()));
    } else if (ctx.ASSIGN() != null) {
      return (ASTNode) new ReAssignNode(
        (AssignNode) visitAssignlhs(ctx.assignlhs()),
        (AssignNode) visitAssignrhs(ctx.assignrhs()));
    } else if (ctx.BEGIN() != null) {
      BeginStatNode b = new BeginStatNode();
      b.addStat((StatNode) visitStat(ctx.stat(0)));
      return (ASTNode) b;
    } else if (ctx.RETURN() != null) {
      BasicStatNode b = new BasicStatNode(StatTypeEnum.RETURN);
      b.addExpr((ExprNode) visitExpr(ctx.expr()));
      return (ASTNode) b;
    } else if (ctx.SKIP() != null) {
      BasicStatNode b = new BasicStatNode(StatTypeEnum.SKIP);
      return (ASTNode) b;
    } else if (ctx.EXIT() != null) {
      BasicStatNode b = new BasicStatNode(StatTypeEnum.EXIT);
      b.addExpr((ExprNode) visitExpr(ctx.expr()));
      return (ASTNode) b;
    } else if (ctx.PRINT() != null) {
      BasicStatNode b = new BasicStatNode(StatTypeEnum.PRINT);
      b.addExpr((ExprNode) visitExpr(ctx.expr()));
      return (ASTNode) b;
    } else if (ctx.PRINTLN() != null) {
      BasicStatNode b = new BasicStatNode(StatTypeEnum.PRINTLN);
      b.addExpr((ExprNode) visitExpr(ctx.expr()));
      return (ASTNode) b;
    } else if (ctx.IF() != null) {
      return (ASTNode) new IfStatNode(
        (ExprNode) visitExpr(ctx.expr()),
        (StatNode) visitStat(ctx.stat(0)),
        (StatNode) visitStat(ctx.stat(1)));
    } else if (ctx.WHILE() != null) {
      return (ASTNode) new WhileStatNode(
        (ExprNode) visitExpr(ctx.expr()),
        (StatNode) visitStat(ctx.stat(0)));
    } else if (ctx.SEMI() != null) {
      return (ASTNode) new CompStatNode(
        (StatNode) visitStat(ctx.stat(0)),
        (StatNode) visitStat(ctx.stat(1)));
    }
    return null;
  }

  @Override
  public ASTNode visitArrayliter(@NotNull BasicParser.ArrayliterContext ctx) {
    ArrayLiteralNode aln = new ArrayLiteralNode();
    for (BasicParser.ExprContext e : ctx.expr()) {
      aln.addExpr((ExprNode) visitExpr(e));
    }
    return (ASTNode) aln;
  }

  @Override
  public ASTNode visitPairtype(@NotNull BasicParser.PairtypeContext ctx) {
    TypeNode p1 = new TypeNode((TypeNode) visitPairelemtype(ctx.pairelemtype(0)));
    TypeNode p2 = new TypeNode((TypeNode) visitPairelemtype(ctx.pairelemtype(1)));
    return (ASTNode) new TypeNode(p1, p2);
  }

  @Override
  public ASTNode visitBasetype(@NotNull BasicParser.BasetypeContext ctx) {
    if (ctx.BOOL() != null) {
      return (ASTNode) new TypeNode(TypeEnum.BOOL);
    } else if (ctx.CHAR() != null) {
      return (ASTNode) new TypeNode(TypeEnum.CHAR);
    } else if (ctx.INT() != null) {
      return (ASTNode) new TypeNode(TypeEnum.INT);
    } else if (ctx.STRING() != null) {
      return (ASTNode) new TypeNode(TypeEnum.STRING);
    }
    return null;
  }

  @Override
  public ASTNode visitProgram(@NotNull BasicParser.ProgramContext ctx) {
    ProgramNode prog = new ProgramNode();
    for (BasicParser.FuncContext func : ctx.func()) {
      prog.addFunc((FuncNode) visitFunc(func));
    }
    prog.addStat((StatNode) visitStat(ctx.stat()));
    return (ASTNode) prog;
  }

  @Override
  public ASTNode visitPairliter(@NotNull BasicParser.PairliterContext ctx) {
    return (ASTNode) new PairLiteralNode();
  }

  @Override
  public ASTNode visitPairelem(@NotNull BasicParser.PairelemContext ctx) {
    try {
      return (ASTNode) new PairLookupNode(
        ((IdentNode) visitExpr(ctx.expr())).getIdent(),
        ctx.FST() != null);
    } catch (ClassCastException e) {
      System.exit(200);
      return null;
    }
  }

  @Override
  public ASTNode visitPairelemtype(@NotNull BasicParser.PairelemtypeContext ctx) {
    if (ctx.PAIR() != null) {
      return (ASTNode) new TypeNode(TypeEnum.PAIR);
    } else if (ctx.arraytype() != null) {
      return visitArraytype(ctx.arraytype());
    } else {
      return visitBasetype(ctx.basetype());
    }
  }

  @Override
  public ASTNode visitIntliter(@NotNull BasicParser.IntliterContext ctx) {
    return (ASTNode) new IntNode(Long.valueOf(ctx.getText()));
  }
}
