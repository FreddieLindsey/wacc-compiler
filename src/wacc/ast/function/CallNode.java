package wacc.ast.function;

import wacc.ast.ExprNode;
import wacc.ast.IdentNode;
import wacc.ast.assign.AssignNode;
import wacc.ast.type.FuncTypeNode;
import wacc.ast.type.TypeNode;
import wacc.backend.instruction.InstructionBlock;

import java.util.ArrayList;

public class CallNode extends AssignNode {

  private IdentNode ident;
  private ArgListNode args;

  public CallNode(IdentNode ident, ArgListNode args) {
    super();
    this.ident = ident;
    this.args = args;
    ident.setParent(this);
    args.setParent(this);
  }

  public IdentNode getIdent() {
    return this.ident;
  }

  public ArgListNode getArgs() {
    return this.args;
  }

  @Override
  public TypeNode type() {
    // We only care about the return type of the function being called,
    // which is included in the IdentNode assigned to the object
    return ident.type();
  }

  @Override
  public boolean isSemanticallyValid() {
    // Check the function identifier is valid
    if (!ident.isSemanticallyValid()) return false;

    // Check to see if the function has been defined
    TypeNode funcType = symbolTable.lookUpType(ident.getIdent());
    if (funcType == null) return false;

    // Check to ensure it's a function
    if (!(funcType instanceof FuncTypeNode)) return false;
    FuncTypeNode func = (FuncTypeNode) funcType;

    // Check the argsList is valid
    if (!args.isSemanticallyValid()) return false;

    ArrayList<TypeNode> paramTypes = func.getParamsTypes();
    ArrayList<ExprNode> argTypes = args.getExprs();

    // Check params/args are same size
    if (paramTypes.size() != argTypes.size()) return false;

    // Check all params/args are of same type
    for (int i = 0; i < paramTypes.size(); i++) {
      TypeNode paramType = paramTypes.get(i);
      TypeNode argType = argTypes.get(i).type();
      if (argType == null || !paramType.equals(argType)) return false;
    }

    semanticallyValid = true;
    return semanticallyValid;
  }

  @Override
  public boolean validLeft() {
    return false;
  }

  @Override
  public boolean validRight() {
    return true;
  }

  public InstructionBlock generateCode() {
    InstructionBlock i = new InstructionBlock();

//     According to wiki, this is the ARM calling convention:
//     In the prologue, push r4 to r11 to the stack, and push the return address in r14, to the stack. (This can be done with a single STM instruction).
//     copy any passed arguments (in r0 to r3) to the local scratch registers (r4 to r11).
//     allocate other local variables to the remaining local scratch registers (r4 to r11).
//     do calculations and call other subroutines as necessary using BL, assuming r0 to r3, r12 and r14 will not be preserved.
//     put the result in r0
//     In the epilogue, pull r4 to r11 from the stack, and pull the return address to the program counter r15. (This can be done with a single LDM instruction).

    return i;
  }

}
