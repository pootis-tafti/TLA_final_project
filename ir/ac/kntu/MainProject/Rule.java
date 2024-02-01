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
}
