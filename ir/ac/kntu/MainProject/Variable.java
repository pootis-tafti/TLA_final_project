package ir.ac.kntu.MainProject;

import java.util.ArrayList;
import java.util.List;

public class Variable extends Symbol{

    private List<Rule> rules;

    public Variable(String name) {
        super(name);
        this.rules = new ArrayList<>();
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void addRule(Rule rule){
        this.rules.add(rule);
    }

    public void addRule(Symbol... symbols){
        this.rules.add(new Rule(symbols));
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Variable other){
            return this.getName().equals(other.getName());
        }
        return super.equals(obj);
    }

    public boolean contains(Rule rule){
        return this.rules.contains(rule);
    }

    public boolean contains(Symbol symbol){
        for (Rule rule : this.rules) {
            if (rule.contains(symbol)) {
                return true;
            }
        }
        return false;
    }

    public void replace(Variable variable){
        for (Rule rule : rules) {
            if (!rule.isCNF()) {
                rule.replace(variable);
            }
        }
    }

    public void remove(Rule rule){
        rules.remove(rule);
    }
}
