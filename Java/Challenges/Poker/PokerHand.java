import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;

public class PokerHand {

    private ArrayList<Card> cards = new ArrayList<>();

    PokerHand(String hand) {
        String[] cards = hand.split(" ");

        for (int i = 0; i < 5; i++) {
            this.cards.add(new Card(cards[i]));
        }

        Collections.sort(this.cards, new Comparator<Card>() {
            @Override
            public int compare(Card lhs, Card rhs) {
                return lhs.getValue() - rhs.getValue();
            }
        });
    }

    public Result compareWith(PokerHand hand) {

        if (this.valueOfHand() > hand.valueOfHand()) {
            return Result.WIN;
        }

        if (this.valueOfHand() < hand.valueOfHand()) {
            return Result.LOSS;
        }

        return this.highCard(hand);
    }

    private int valueOfHand() {

        if (this.isAStraightFlush() != -1) {
            return this.isAStraightFlush();
        }

        if (this.isAFourOfAKind() != -1) {
            return this.isAFourOfAKind();
        }

        if (this.isAFullHouseOrAThreeOfAKind() != -1) {
            return this.isAFullHouseOrAThreeOfAKind();
        }

        if (this.isAFLush() != -1) {
            return this.isAFLush();
        }

        if (this.isAStraight() != -1) {
            return this.isAStraight();
        }

        if (this.isADoublePairOrAPair() != -1) {
            return this.isADoublePairOrAPair();
        }

        return this.cards.get(4).getValue();
    }

    private Result highCard(PokerHand hand) {

        for (int i = this.cards.size() - 1; i >= 0; i--) {
            if (this.cards.get(i).getValue() > hand.getCards().get(i).getValue()) {
                return Result.WIN;
            }

            if (this.cards.get(i).getValue() < hand.getCards().get(i).getValue()) {

                return Result.LOSS;
            }
        }

        return Result.TIE;
    }

    private int isADoublePairOrAPair() {
        int equalValueCards;

        ArrayList<Integer> cardsPositions;
        Integer[] arr = { 0, 1, 2, 3, 4 };
        ArrayList<Integer> allCardsPositions = new ArrayList<Integer>(Arrays.asList(arr));

        for (int i = 0; i < this.cards.size(); i++) {
            equalValueCards = 0;
            cardsPositions = new ArrayList<>();
            cardsPositions.add(i);
            for (int j = 0; j < this.cards.size(); j++) {
                if (j == i) {
                    continue;
                }
                if (this.cards.get(i).getValue() == this.cards.get(j).getValue()) {
                    equalValueCards++;
                    cardsPositions.add(j);
                }
            }
            if (equalValueCards == 1) {
                allCardsPositions.removeAll(cardsPositions);
                int equalValueCards2;
                ArrayList<Integer> cardsPositions2;
                for (int k : allCardsPositions) {
                    equalValueCards2 = 0;
                    cardsPositions2 = new ArrayList<>();
                    cardsPositions2.add(k);
                    for (int n : allCardsPositions) {
                        if (k == n) {
                            continue;
                        }
                        if (this.cards.get(k).getValue() == this.cards.get(n).getValue()) {
                            equalValueCards2++;
                            cardsPositions2.add(n);
                        }
                    }

                    if (equalValueCards2 == 1) {

                        if (this.cards.get(cardsPositions.get(0)).getValue() > this.cards.get(cardsPositions2.get(0))
                                .getValue()) {
                            return 100 * this.cards.get(cardsPositions.get(0)).getValue();
                        } else {
                            return 100 * this.cards.get(cardsPositions2.get(0)).getValue();
                        }
                    }
                }
                return 10 * this.cards.get(i).getValue();
            }
        }
        return -1;
    }

    private int isAStraight() {

        for (int i = 1; i < this.cards.size(); i++) {
            if ((this.cards.get(i).getValue() - this.cards.get(i - 1).getValue()) != 1) {
                return -1;
            }
        }

        return 50000;

    }

    private int isAFLush() {

        for (int i = 1; i < this.cards.size(); i++) {
            if (!this.cards.get(i - 1).getSuit().equals(this.cards.get(i).getSuit())) {
                return -1;
            }
        }

        return 500000;
    }

    private int isAFullHouseOrAThreeOfAKind() {

        int equalValueCards;

        ArrayList<Integer> cardsPositions;
        Integer[] arr = { 0, 1, 2, 3, 4 };
        ArrayList<Integer> allCardsPositions = new ArrayList<Integer>(Arrays.asList(arr));

        for (int i = 0; i < this.cards.size(); i++) {
            equalValueCards = 0;
            cardsPositions = new ArrayList<>();
            cardsPositions.add(i);

            for (int j = 0; j < this.cards.size(); j++) {
                if (j == i) {
                    continue;
                }

                if (this.cards.get(i).getValue() == this.cards.get(j).getValue()) {
                    equalValueCards++;
                    cardsPositions.add(j);
                }

            }
            if (equalValueCards == 2) {
                allCardsPositions.removeAll(cardsPositions);

                if (this.cards.get(allCardsPositions.get(0)).getValue() == this.cards.get(allCardsPositions.get(1))
                        .getValue()) {
                    return (1000000 * this.cards.get(i).getValue());
                } else {
                    return (1000 * this.cards.get(i).getValue());
                }

            }
        }

        return -1;
    }

    private int isAFourOfAKind() {

        int equalValueCards;

        for (int i = 0; i < this.cards.size(); i++) {
            equalValueCards = 0;
            for (int j = 0; j < this.cards.size(); j++) {
                if (j == i) {
                    continue;
                }

                if (this.cards.get(i).getValue() == this.cards.get(j).getValue()) {
                    equalValueCards++;
                }

            }
            if (equalValueCards == 3) {
                return (10000000 * this.cards.get(i).getValue());
            }
        }

        return -1;
    }

    private int isAStraightFlush() {

        for (int i = 1; i < this.cards.size(); i++) {
            if (!this.cards.get(i - 1).getSuit().equals(this.cards.get(i).getSuit())) {
                return -1;
            }
            if ((this.cards.get(i).getValue() - this.cards.get(i - 1).getValue()) != 1) {
                return -1;
            }
        }

        return 100000000 * this.cards.get(4).getValue();
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

}