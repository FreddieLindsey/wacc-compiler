package wacc.ast;

import java.util.ArrayList;

public class ProgramNode implements ASTNode {

    private ArrayList<FuncNode> funcs;
    private StatNode stat;

    public ProgramNode() {
        this.funcs = new ArrayList<>();
    }

    @Override
    public boolean isSemanticallyValid() {
        return true;
    }

    public void add(FuncNode f) {
        funcs.add(f);
    }

    public ArrayList<FuncNode> getFuncs() {
        return funcs;
    }

    public StatNode getStat() {
        return stat;
    }

    public void setStat(StatNode stat) {
        if (this.stat != null) {
            throw new IllegalArgumentException("You cannot overwrite the final stat of a program");
        }
        this.stat = stat;
    }
}
