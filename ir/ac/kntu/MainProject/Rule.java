package ir.ac.kntu.MainProject;

import java.util.ArrayList;
import java.util.List;

public class Rule {
    private List<Symbol> symbols;

    public Rule() {
        this.symbols = new ArrayList<>();
    }

    public List<Symbol> getSymbols() {
        return symbols;
    }
}
