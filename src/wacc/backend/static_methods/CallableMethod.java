package wacc.backend.static_methods;

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

  public InstructionBlock generateCode(String s) {
    switch(this) {
      case P_PRINT_STRING:
        return p_print_ln(s);
      case P_PRINT_LN:
        return p_print_ln(s);
      case P_PRINT_INT:
        return p_print_int(s);
      case P_READ_INT:
        return p_read_int(s);
      case P_FREE_PAIR:
        return p_free_pair(s);
      case P_DIVIDE_BY_ZERO:
        return p_check_divide_by_zero(s);
      case P_THROW_OVERFLOW_ERROR:
        return p_throw_overflow_error(s);
      case P_THROW_RUNTIME_ERROR:
        return p_throw_runtime_error(s);
    }
  }

}
