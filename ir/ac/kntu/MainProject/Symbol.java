package ir.ac.kntu.MainProject;

public class Symbol {
    private String name;

    public Symbol(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Symbol other) {
            return this.getName().equals(other.getName());
        } else if (obj instanceof String other) {
            return this.getName().equals(other);
        }
        return super.equals(obj);
    }
}
