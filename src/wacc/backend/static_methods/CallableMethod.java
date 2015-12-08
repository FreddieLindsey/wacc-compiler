package wacc.backend.static_methods;

import wacc.ast.ProgramNode;
import wacc.backend.instruction.InstructionBlock;
import wacc.backend.instruction.StringDataMessage;

import static wacc.backend.static_methods.CallableMethods.*;

public enum CallableMethod {

  P_PRINT_STRING,
  P_READ_INT,
  P_PRINT_INT,
  P_PRINT_LN,
  P_DIVIDE_BY_ZERO,
  P_THROW_OVERFLOW_ERROR,
  P_THROW_RUNTIME_ERROR,
  P_FREE_PAIR;

  public InstructionBlock generateCode() {
    switch(this) {
      case P_PRINT_STRING:
        return p_print_string(getMessageLabel());
      case P_PRINT_LN:
        return p_print_ln(getMessageLabel());
      case P_PRINT_INT:
        return p_print_int(getMessageLabel());
      case P_READ_INT:
        return p_read_int(getMessageLabel());
      case P_FREE_PAIR:
        return p_free_pair(getMessageLabel());
      case P_DIVIDE_BY_ZERO:
        return p_check_divide_by_zero(getMessageLabel());
      case P_THROW_OVERFLOW_ERROR:
        return p_throw_overflow_error(getMessageLabel());
      case P_THROW_RUNTIME_ERROR:
        return p_throw_runtime_error(getMessageLabel());
    }
    return null;
  }

  public StringDataMessage message() {
    switch(this) {
      case P_PRINT_STRING:
        return new StringDataMessage(ProgramNode.messages.size() + this.ordinal(),
          "%.*s\0");
      case P_PRINT_LN:
        return new StringDataMessage(ProgramNode.messages.size() + this.ordinal(),
          "\0");
      case P_PRINT_INT:
        return new StringDataMessage(ProgramNode.messages.size() + this.ordinal(),
          "%d\0");
      case P_READ_INT:
        return new StringDataMessage(ProgramNode.messages.size() + this.ordinal(),
          "%d\0");
      case P_FREE_PAIR:
        return new StringDataMessage(ProgramNode.messages.size() + this.ordinal(),
          "NullReferenceError: dereference a null reference\n\0");
      case P_DIVIDE_BY_ZERO:
        return new StringDataMessage(ProgramNode.messages.size() + this.ordinal(),
          "DivideByZeroError: divide or modulo by zero\n\0");
      case P_THROW_OVERFLOW_ERROR:
        return new StringDataMessage(ProgramNode.messages.size() + this.ordinal(),
          "OverflowError: the result is too small/large to store in a 4-byte signed-integer.\n");
//      case P_THROW_RUNTIME_ERROR:
//        return new StringDataMessage(ProgramNode.messages.size() + this.ordinal(),
//          );
    }
    return null;
  }
  
  private String getMessageLabel() {
    return "msg_" + String.valueOf(ProgramNode.messages.size() + this.ordinal());
  }
}
