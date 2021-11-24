public class Card {
    private int value;
    private String suit;

    Card(String card) {
        String[] values = card.split("");

        value = this.convert(values[0]);

        suit = values[1];
    }

    private int convert(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            if (value.equals("T")) {
                return 10;
            }

            if (value.equals("J")) {
                return 11;
            }

            if (value.equals("Q")) {
                return 12;
            }

            if (value.equals("K")) {
                return 13;
            }

            if (value.equals("A")) {
                return 14;
            }
        }

        return 0;
    }

    private String reconvert(int value) {
        if (value < 10) {
            return "" + value;
        }

        if (value == 10) {
            return "T";
        }

        if (value == 11) {
            return "J";
        }

        if (value == 12) {
            return "Q";
        }

        if (value == 13) {
            return "T";
        }

        if (value == 14) {
            return "A";
        }

        return "";
    }

    public int getValue() {
        return this.value;
    }

    public String getSuit() {
        return this.suit;
    }

    @Override
    public String toString() {
        return this.reconvert(this.value) + this.suit;
    }

}
