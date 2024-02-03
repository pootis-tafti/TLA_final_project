package ir.ac.kntu.MainProject;

public class Epsilon extends Rule {
    public Epsilon() {
        super();
        setSymbols(null);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Epsilon;
    }

    @Override
    public boolean contains(Rule rule) {
        return rule instanceof Epsilon;
    }

    @Override
    public boolean contains(Symbol other) {
        return false;
    }

    @Override
    public String toString() {
        return "\u03B5";
    }
}
