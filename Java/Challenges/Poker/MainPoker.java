public class MainPoker {
    public static void main(String[] args) {

        PokerHand heroHand = new PokerHand("AS AC AD AH 2C");
        PokerHand vilainHand = new PokerHand("8C 9C TC JC QC");

        System.out.print("\nHero Hand:");
        heroHand.getCards().forEach((i) -> System.out.print(" " + i));

        System.out.print("\nVilain Hand:");
        vilainHand.getCards().forEach((i) -> System.out.print(" " + i));

        System.out.println("\nHero Wins? " + heroHand.compareWith(vilainHand));
    }
}
