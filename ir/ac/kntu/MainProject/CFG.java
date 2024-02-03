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
                            Terminal terminal = new Terminal("" + rule.charAt(i));
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
            startVariable = new Variable("S0");
            variables.add(0, startVariable);
        }
        // TODO remove epsilon
        for (Variable variable : variables) {
            if(variable.contains(new Epsilon()) && ! variable.equals(this.startVariable)){
                variable.remove(new Epsilon());
                for (int i = 0; i < variable.getRules().size(); i++) {
                    if (variable.getRules().get(i).contains(variable)) {
                        variable.getRules().add(variable.getRules().get(i).generateWithout(variable));
                    }
                }
            }
        }
        // TODO extract terminals

        int i = 0;
        for (Terminal terminal : language) {
            Variable termianVariable = new Variable("V" + i);
            i++;
            termianVariable.addRule(terminal);
            for (Variable variable : variables) {
                variable.replace(termianVariable);
            }
        }

        // TODO shrink variable rules and remove single variables
        for (Variable variable : variables) {
            for (Rule rule : variable.getRules()) {
                while(rule.getSymbols().size() > 2){
                    Variable shrinkVariable = new Variable("V" + i);
                    shrinkVariable.addRule(rule.getSymbols().get(0),rule.getSymbols().get(1));
                    for (Variable variable1 : variables){
                        variable1.replace(shrinkVariable);
                    }
                }
                if (rule.getSymbols().size() == 1 && rule.getSymbols().get(0) instanceof Variable single){
                    variable.remove(rule);
                    for (Rule rule1: single.getRules()) {
                        variable.addRule(rule1);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        // TODO compelete method
        return null;
    }

    public boolean isRecursive(){
        for (Variable variable : variables) {
            if (variable.contains(new Epsilon())) {
                return true;
            }
        }
        return false;
    }

    public boolean containsEpsilon(){
        for (int i = 1; i < variables.size(); i++) {
            if(variables.get(i).contains(new Epsilon())){
                return true;
            }
        }
        return false;
    }
}
