package wacc.ast;

import wacc.symbolTable.SymbolTable;

public class BeginStatNode extends StatNode {

	private StatNode stat;
	private SymbolTable scope;

	public BeginStatNode() {
    super();
	}

  public void addStat(StatNode s) {
    this.stat = s;
    s.setParent(this);
  }
  
  public void setScope(SymbolTable st) {
    this.scope = st;
  }

	@Override
	public boolean isSemanticallyValid() {
		return stat.isSemanticallyValid();
	}


}
