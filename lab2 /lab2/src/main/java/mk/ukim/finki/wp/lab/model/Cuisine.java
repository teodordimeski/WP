package mk.ukim.finki.wp.lab.model;

public enum Cuisine {
    WESTERN("Western"),
    MEDITERRANEAN("Mediterranean"),
    ASIAN("Asian"),
    MIDDLE_EASTERN("Middle Eastern"),
    FUSION("Fusion"),
    VEGAN("Vegan");

    private final String label;

    Cuisine(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

