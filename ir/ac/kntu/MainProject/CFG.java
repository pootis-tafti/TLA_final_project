package ir.ac.kntu.MainProject;

import java.util.ArrayList;
import java.util.List;

public class CFG {
    private Variable startVariable;
    private List<Variable> variables;
    private List<Terminal> language;

    public CFG(String cfg) {
        this.variables = new ArrayList<>();
        this.language = new ArrayList<>();
        parse(cfg);
    }

    public void parse(String cfg){
        for (String line : cfg.split("\n")) {
            String[] variable = line.split("->");
            this.variables.add(new Variable(variable[0].trim()));
        }
        for (String line : cfg.split("\n")) {
            String[] variable = line.split("->");
            for (String rule : variable[2].trim().split("\\|")) {
                Rule adder;
                if(rule.equals("î")){
                    adder = new Epsilon();
                }
                else{
                    adder = new Rule();
                    for (int i = 0; i < rule.length(); i++) {
                        if(this.variables.contains("" + rule.charAt(i))){
                            adder.getSymbols().add(this.variables.get(this.variables.indexOf("" + rule.charAt(i))));
                        } else {
                            Terminal terminal = new Terminal("" + rule.charAt(i))
                            language.add(terminal);
                            adder.getSymbols().add(terminal);
                        }
                    }
                }
                variables.get(variables.indexOf(variable[0].trim())).addRule(adder);
            }
            startVariable = this.variables.get(0);
        }
    }

    public void toCNF(){
        if (isRecursive()) {
            variables.add(0, startVariable);
            startVariable = new Variable("S0");
            startVariable.addRule(variables.get(0));
        }
        // TODO remove epsilon
        // TODO extract terminals
        // TODO shrink variable rules
    }

    @Override
    public String toString() {
        // TODO compelete method
        return null;
    }

    public boolean isRecursive(){
        // TODO compelete method
        return true;
    }
}
