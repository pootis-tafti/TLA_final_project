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
}
