public class MainMorseCode {
    public static void main(String[] args) {

        System.out.println("\n?\n" + MorseCode.possibilities("?"));

        System.out.println("\n??\n" + MorseCode.possibilities("??"));

        System.out.println("\n???\n" + MorseCode.possibilities("???"));

        System.out.println("\n????\n" + MorseCode.possibilities("????"));

        System.out.println("\n?????\n" + MorseCode.possibilities("?????"));

        System.out.println("\n??????\n" + MorseCode.possibilities("??????"));
    }
}
