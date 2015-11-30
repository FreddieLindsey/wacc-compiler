package wacc.ast.io;

import wacc.ast.ExprNode;
import wacc.ast.IdentNode;
import wacc.ast.type.PairTypeNode;
import wacc.ast.type.TypeEnum;
import wacc.ast.type.TypeNode;
import wacc.backend.*;

import java.util.ArrayList;
import java.util.Arrays;

public class BasicStatNode extends StatNode {

  private ExprNode expr;
  private StatTypeEnum st;

  public BasicStatNode(StatTypeEnum st) {
    super();
    this.st = st;
  }

  public void addExpr(ExprNode e) {
    this.expr = e;
    e.setParent(this);
  }

  public StatTypeEnum getType() {
    return st;
  }

  public ExprNode getExpr() {
    return expr;
  }

  @Override
  public boolean isSemanticallyValid() {
    // TODO: Tidy up and comment
    switch (st) {
      case SKIP:
        semanticallyValid = true;
        return semanticallyValid;
      case EXIT:
        semanticallyValid = expr != null
          && expr.type() != null
          && expr.type().getType().equals(TypeEnum.INT);
        break;
      case FREE:
        semanticallyValid = expr != null
          && expr.type() != null
          && expr.type() instanceof PairTypeNode;
        break;
      case READ:
        semanticallyValid = expr != null
          && expr.type() != null
          && (expr.type().getType() == TypeEnum.INT
          || expr.type().getType() == TypeEnum.CHAR);
        break;
      default:
        semanticallyValid = expr != null && expr.type() != null;
    }
    if (!semanticallyValid) return false;

    semanticallyValid = expr.isSemanticallyValid();
    return semanticallyValid;
  }

  @Override
  public TypeNode returnType() {
    if (st == StatTypeEnum.RETURN) {
      if (expr instanceof IdentNode) {
        return symbolTable.lookUp(((IdentNode) expr).getIdent());
      } else {
        return expr.type();
      }
    } else {
      return null;
    }
  }

  @Override
  public boolean returns() {
    return st == StatTypeEnum.RETURN;
  }

  @Override
  public ArrayList<AssemblyInstr> generateCode() {
    ArrayList<AssemblyInstr> instrs = new ArrayList<AssemblyInstr>();

    switch (st) {
      case SKIP: 
        ArrayList<Arg> args = new ArrayList<Arg>();
        args.add(new Register(RegEnum.R0));
        args.add(new Const(ArgEnum.CONST, 0));
        AssemblyInstr a = new AssemblyInstr(
          AssemblyInstrEnum.LDR, 
          null, 
          args);
        instrs.add(a);
                      break;
      case READ: 
      case FREE:
      case RETURN:
      case EXIT:
      case PRINT:
      case PRINTLN:
      default: break;
    }


    return instrs;
  }

}
