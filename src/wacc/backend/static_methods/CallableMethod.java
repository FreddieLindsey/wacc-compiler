package wacc.backend.static_methods;

import wacc.ast.ProgramNode;
import wacc.backend.instruction.InstructionBlock;

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
    String message_number = "msg_" + String.valueOf(ProgramNode.messages.size() + this.ordinal());
    switch(this) {
      case P_PRINT_STRING:
        return p_print_string(message_number);
      case P_PRINT_LN:
        return p_print_ln(message_number);
      case P_PRINT_INT:
        return p_print_int(message_number);
      case P_READ_INT:
        return p_read_int(message_number);
      case P_FREE_PAIR:
        return p_free_pair(message_number);
      case P_DIVIDE_BY_ZERO:
        return p_check_divide_by_zero(message_number);
      case P_THROW_OVERFLOW_ERROR:
        return p_throw_overflow_error(message_number);
      case P_THROW_RUNTIME_ERROR:
        return p_throw_runtime_error(message_number);
    }
    return null;
  }

}
