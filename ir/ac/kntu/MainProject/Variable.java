package ir.ac.kntu.MainProject;

import java.util.ArrayList;
import java.util.List;

public class Variable extends Symbol{

    private List<Rule> rules;

    public Variable(Character name) {
        super(name);
        this.rules = new ArrayList<>();
    }
}
