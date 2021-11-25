import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class MorseCode {

    public static List<String> possibilities(String signals) {

        HashMap<String, String> possibleLettersMap = possibleLettersMap();
        List<String> possibleLetters = new ArrayList<String>();
        List<String> morseCodes = generateMorsePossibilites(signals);

        morseCodes.forEach((i) -> possibleLetters.add(possibleLettersMap.get(i)));

        while (possibleLetters.remove(null));

        return possibleLetters;
    }
	
	 private static HashMap<String, String> possibleLettersMap() {
        HashMap<String, String> possibleLetters = new HashMap<String, String>();
        possibleLetters.put(".", "E");
        possibleLetters.put("-", "T");
        possibleLetters.put("..", "I");
        possibleLetters.put(".-", "A");
        possibleLetters.put("-.", "N");
        possibleLetters.put("--", "M");
        possibleLetters.put("...", "S");
        possibleLetters.put("..-", "U");
        possibleLetters.put(".-.", "R");
        possibleLetters.put(".--", "W");
        possibleLetters.put("-..", "D");
        possibleLetters.put("-.-", "K");
        possibleLetters.put("--.", "G");
        possibleLetters.put("---", "O");
        possibleLetters.put("....", "H");
        possibleLetters.put("...-", "V");
        possibleLetters.put("..-.", "F");
        possibleLetters.put("..--", "Ü");
        possibleLetters.put(".-..", "L");
        possibleLetters.put(".-.-", "Ä");
        possibleLetters.put(".--.", "P");
        possibleLetters.put(".---", "J");
        possibleLetters.put("-...", "B");
        possibleLetters.put("-..-", "X");
        possibleLetters.put("-.-.", "C");
        possibleLetters.put("-.--", "Y");
        possibleLetters.put("--..", "Z");
        possibleLetters.put("--.-", "Q");
        possibleLetters.put("---.", "Ö");
        possibleLetters.put("----", "CH");
        possibleLetters.put(".....", "5");
        possibleLetters.put("....-", "4");
        possibleLetters.put("...-.", "Ŝ");
        possibleLetters.put("...--", "3");
        possibleLetters.put("..-..", "É");
        possibleLetters.put("..--.", "Ð");
        possibleLetters.put("..---", "2");
        possibleLetters.put(".-..-", "È");
        possibleLetters.put(".-.-.", "+");
        possibleLetters.put(".--..", "Þ");
        possibleLetters.put(".--.-", "À");
        possibleLetters.put(".---.", "Ĵ");
        possibleLetters.put(".----", "1");
        possibleLetters.put("-....", "6");
        possibleLetters.put("-...-", "=");
        possibleLetters.put("-..--", "/");
        possibleLetters.put("-.-..", "Ç");
        possibleLetters.put("-.--.", "Ĥ");
        possibleLetters.put("--...", "7");
        possibleLetters.put("--.-.", "Ĝ");
        possibleLetters.put("--.--", "Ñ");
        possibleLetters.put("---..", "8");
        possibleLetters.put("----.", "9");
        possibleLetters.put("-----", "0");
        possibleLetters.put("..--..", "?");
        possibleLetters.put("..--.-", "_");
        possibleLetters.put(".-..-.", "\"");
        possibleLetters.put(".-.-.-", ".");
        possibleLetters.put(".--.-", "@");
        possibleLetters.put(".----.", "'");
        possibleLetters.put("-....-", "-");
        possibleLetters.put("-.-.-.", ";");
        possibleLetters.put("-.-.--", "!");
        possibleLetters.put("-.--.", "()");
        possibleLetters.put("--..--", ",");
        possibleLetters.put("---...", ":");

        return possibleLetters;
    }
	
	private static List<String> generateMorsePossibilites(String signal) {
        List<String> morsePossibilities = new ArrayList<>();

        morsePossibilities = generateMorseCode(signal, morsePossibilities);

        while (morseListContainsInterrogation(morsePossibilities)) {

            List<String> morsePossibilitiesClone = new ArrayList<>(morsePossibilities);

            for (String word : morsePossibilitiesClone) {
                morsePossibilities = generateMorseCode(word, morsePossibilities);
            }

        }

        return morsePossibilities;
    }
	
	private static List<String> generateMorseCode(String signal, List<String> morseList) {
        List<String> morseCodes = new ArrayList<>(morseList);

        if (signal.contains("?")) {
            morseCodes.remove(signal);
        } else {

            if (morseCodes.size() == 0) {
                morseCodes.add(signal);
            }

            return morseCodes;
        }

        String[] signalSplited = signal.split("");

        String morseCode1 = "";
        String morseCode2 = "";

        for (int i = 0; i < signalSplited.length; i++) {
            if (signalSplited[i].equals("?")) {
                for (int j = 0; j < signalSplited.length; j++) {
                    if (i == j) {
                        morseCode1 += ".";
                        morseCode2 += "-";
                        continue;
                    }

                    morseCode1 += signalSplited[j];
                    morseCode2 += signalSplited[j];
                }
                break;
            }
        }

        morseCodes.add(morseCode1);
        morseCodes.add(morseCode2);
        return morseCodes;
    }

    private static boolean morseListContainsInterrogation(List<String> morseList) {

        for (String morseCode : morseList) {
            if (morseCode.contains("?")) {
                return true;
            }
        }

        return false;
    }

}