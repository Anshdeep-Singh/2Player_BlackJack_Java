import java.util.ArrayList;

public class Card {

    private final String[] CARD_NUMBER = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",};
    private final String[] CARD_TYPE = {"Heart", "Diamond", "Spade", "Club"};

    public String[] getCARD_NUMBER() {
        return CARD_NUMBER;
    }

    public String[] getCARD_TYPE() {
        return CARD_TYPE;
    }

    public ArrayList<String> GenerateDeck() {

        ArrayList<String> deck = new ArrayList<String>();
        for (int i = 0; i < getCARD_NUMBER().length; i++) {
            for (int j = 0; j < getCARD_TYPE().length; j++) {
                deck.add(getCARD_NUMBER()[i] + " " + getCARD_TYPE()[j]);
            }
        }

        return deck;
    }

    public String[] GenerateHand(ArrayList<String> deck) {

        String[] hand = new String[2];
        int selectCardNumber;

        for (int i = 0; i < 2; ++i) {
            selectCardNumber = (int) (Math.random() * deck.size());
            hand[i] = deck.get(selectCardNumber);
            deck.remove(deck.get(selectCardNumber));
        }
        return hand;
    }

    public static void DisplayDeck(String[] s) {

        for (String i : s) {
            i = i.replaceAll("11 ", "K ");
            i = i.replaceAll("1 ", "A ");
            i = i.replaceAll("12 ", "Q ");
            i = i.replaceAll("13 ", "J ");
            System.out.print(i + " | ");
        }
    }

    public static void DisplayCard(String i) {

        i = i.replaceAll("11 ", "K ");
        i = i.replaceAll("1 ", "A ");
        i = i.replaceAll("12 ", "Q ");
        i = i.replaceAll("13 ", "J ");
        System.out.print(i);
    }
}
