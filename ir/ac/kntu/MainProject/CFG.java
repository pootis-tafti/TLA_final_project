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
            for (String rule : variable[1].trim().split("\\|")) {
                Rule adder;
                if(rule.hashCode() == 6567){
                    adder = new Epsilon();
                }
                else{
                    adder = new Rule();
                    for (int i = 0; i < rule.length(); i++) {
                        if(this.variables.contains(new Variable("" + rule.charAt(i)))){
                            adder.getSymbols().add(this.variables.get(this.variables.indexOf(new Variable("" + rule.charAt(i)))));
                        } else {
                            Terminal terminal = new Terminal("" + rule.charAt(i));
                            if (!language.contains(terminal)) {
                                language.add(terminal);
                            }
                            adder.getSymbols().add(terminal);
                        }
                    }
                }
                variables.get(variables.indexOf(new Symbol(variable[0].trim()))).addRule(adder);
            }
            startVariable = this.variables.get(0);
        }
    }

    public void toCNF(){
        if (isRecursive()) {
            startVariable = new Variable("S0");
            startVariable.addRule(variables.get(0));
            variables.add(0, startVariable);
        }
        //System.out.println(this.toString() + "\n");
        //TODO remove single variables

        for (Variable variable : variables) {
            for (int i = 0; i < variable.getRules().size(); i++) {
                Rule rule = variable.getRules().get(i);
                if (! (rule instanceof Epsilon) && rule.getSymbols().size() == 1 
                        && rule.getSymbols().get(0) instanceof Variable single){
                    variable.remove(rule);
                    if(!single.equals(variable)){
                        for (Rule rule1 : single.getRules()) {
                            if (!variable.contains(rule1)) {
                                variable.getRules().add(rule1);
                            }
                        }
                        
                    }
                }
            }
        }

        //System.out.println(this.toString() + "\n");

        // TODO remove epsilon
        for (Variable variable : variables) {
            if(variable.contains(new Epsilon()) && ! variable.equals(this.startVariable)){
                variable.remove(new Epsilon());
                for (Variable variable1 : variables) {
                    for (int i = 0; i < variable1.getRules().size(); i++) {
                        if (variable1.getRules().get(i).contains(variable)
                                && ! variable1.getRules().contains(variable1.getRules().get(i).generateWithout(variable))
                                && variable1.getRules().get(i).generateWithout(variable).getSymbols().size() != 1) {
                            variable1.getRules().add(variable1.getRules().get(i).generateWithout(variable));
                        }
                    }
                }
            }
        }

        // TODO extract terminals

        //System.out.println(this.toString() + "\n");

        int i = 0;
        for (Terminal terminal : language) {
            Variable termianlVariable = new Variable("V" + i);
            i++;
            termianlVariable.addRule(terminal);
            for (Variable variable : variables) {
                variable.replace(termianlVariable);
            }
            variables.add(termianlVariable);
        }

        //System.out.println(this.toString() + "\n");

        // TODO shrink variable rules

        for (int k = 0; k <  variables.size(); k++) {
            Variable variable = variables.get(k);
            for (int j = 0; j < variable.getRules().size(); j++) {
                Rule rule = variable.getRules().get(j);
                while(rule.getSymbols().size() > 2){
                    Variable shrinkVariable = new Variable("V" + i);
                    i++;
                    shrinkVariable.addRule(rule.getSymbols().get(0), rule.getSymbols().get(1));
                    for (Variable variable1 : variables){
                        variable1.replace(shrinkVariable);
                    }
                    variables.add(shrinkVariable);
                }
            }
        }
    }

    @Override
    public String toString() {
        String output = "";
        for (Variable variable : this.variables) {
            output += variable.getName() + " -> ";
            for (Rule rule : variable.getRules()) {
                if (rule.equals(new Epsilon())) {
                    output += "\u03B5";
                }else {
                    for (Symbol symbol : rule.getSymbols()) {
                        output += symbol.getName();
                    }
                }
                output += '|';
            }
            output = output.substring(0, output.length() - 1) + '\n';
        }
        return output.substring(0, output.length() - 1);
    }

    public boolean isRecursive(){
        for (Variable variable : variables) {
            if (variable.contains(startVariable)) {
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
