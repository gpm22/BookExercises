public enum PokeBerry {
    CHERI("Cheri Berry", 100.0f),
    CHESTO("Chesto Berry", 250.0f),
    PECHA("Pecha Berry", 500.0f),
    RAWST("Rawst Berry", 178.0f),
    ASPEAR("Aspear Berry", 187.0f),
    LEPPA("Leppa Berry", 102.0f),
    ORAN("Oran Berry", 87.0f),
    PERSIM("Persim Berry", 127.0f),
    LUM("Lum Berry", 243.0f),
    SITRUS("Sitrus Berry", 833.0f);

    private final String label;
    private final float cost;

    /**
     * @param label The plain text name of the PokeBerry
     * @param cost  The PokeBerry's cost
     */
    PokeBerry(String label, float cost) {
        this.label = label;
        this.cost = cost;
    }

    /**
     * @return The plain text name of the PokeBerry
     */
    public String label() {
        return label;
    }

    /**
     * @return The PokeBerry's cost
     */
    public float cost() {
        return cost;
    }
}