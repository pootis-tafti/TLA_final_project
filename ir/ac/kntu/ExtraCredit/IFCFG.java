package ir.ac.kntu.ExtraCredit;

import java.util.Stack;

import ir.ac.kntu.MainProject.CFG;
import ir.ac.kntu.MainProject.Variable;

public class IFCFG extends CFG{

    public IFCFG(String cfg) {
        super(cfg);
    }

    @Override
    public void parse(String cfg) {
        int variableIndex = 0;
        setStartVariable(new Variable("S"));
        getVariables().add(getStartVariable());
        Stack<String> stack= new Stack<>();
        for (int i = 0; i < cfg.length(); i++) {
            
        }
        super.parse(cfg);
    }
}
