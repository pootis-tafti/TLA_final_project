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
}
