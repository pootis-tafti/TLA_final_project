package ir.ac.kntu.MainProject;

import java.util.ArrayList;
import java.util.List;

public class Rule {
    private List<Symbol> symbols;

    public Rule() {
        this.symbols = new ArrayList<>();
    }

    public Rule(Symbol... symbols) {
        this.symbols = new ArrayList<>();
        for (Symbol symbol : symbols) {
            this.symbols.add(symbol);
        }
    }

    public List<Symbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<Symbol> symbols) {
        this.symbols = symbols;
    }

    public boolean isCNF(){
        return symbols.size() == 1 && symbols.get(0) instanceof Terminal
            || symbols.size() == 2 && symbols.get(0) instanceof Variable 
            && symbols.get(1) instanceof Variable;
    }

    public boolean contains(Symbol other){
        return this.symbols.contains(other);
    }

    public boolean contains(Rule rule){
        if(rule instanceof Epsilon){
            return this instanceof Epsilon;
        }
        if(rule.getSymbols().size() > this.symbols.size()){
            return false;
        }
        int j = 0;
        for (int i = 0; i < symbols.size(); i++) {
            if(this.symbols.get(i).equals(rule.getSymbols().get(j))){
                j++;
                if(j == rule.getSymbols().size()){
                    return true;
                }
            } else {
                j = 0;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Epsilon){
            return this instanceof Epsilon;
        } else if(obj instanceof Rule other){
            if (this.symbols.size() == other.getSymbols().size()) {
                for (int i = 0; i < this.symbols.size(); i++) {
                    if (this.symbols.get(i).equals(other.getSymbols().get(i))){
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
        return super.equals(obj);
    }

    public Rule generateWithout(Symbol symbol){
        Rule output = new Rule();
        for (int i = 0; i < this.symbols.size(); i++) {
            if( !this.symbols.get(i).equals(symbol)){
                output.getSymbols().add(this.symbols.get(i));
            } 
        }
        return output;
    }

    public void replace(Variable variable){
        Rule replaced = variable.getRules().get(0);
        if (! this.contains(replaced)) {
            return;
        }
        int j = 0;
        for (int i = 0; i < this.symbols.size(); i++) {
            if (this.symbols.get(i).equals(replaced.getSymbols().get(j))) {
                j++;
            } else {
                j = 0;
            }
            if (j == replaced.getSymbols().size()) {
                i -= j - 1;
                this.symbols.add(i, variable);
                for (int k = 0; k < j; k++) {
                    this.symbols.remove(i);
                }
                j = 0;
            }
        }
        return;
    }
}
