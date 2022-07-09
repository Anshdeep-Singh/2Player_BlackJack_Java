import java.util.ArrayList;
public class CardGame {
    public static void BlackJack() {

        final int INITIAL_BALANCE = 5;
        int turn = 0;
        int exit = 0;
        char BotChoice;

        Player player1 = new Player();
        Bot bot = new Bot();

        player1.setPlayerBalance(INITIAL_BALANCE);
        bot.setPlayerBalance(INITIAL_BALANCE);

        player1.setStatus(false);
        bot.setStatus(false);

        String[] deckPlayer1;
        String[] deckPlayer2;

        ArrayList<String> deck;
        Card cards = new Card();

        deck = cards.GenerateDeck();

        while (exit != 1) {

            if (deck.size() > 4 ) {

                System.out.println("-------------------------------");
                System.out.print("CARDS LEFT IN DECK -> ");
                System.out.println(deck.size());
                System.out.println("-------------------------------");

                deckPlayer1 = cards.GenerateHand(deck);
                deckPlayer2 = cards.GenerateHand(deck);

                System.out.format("Your Balance    : %.2f \n", player1.getPlayerBalance());
                System.out.format("Computer Balance: %.2f \n", bot.getPlayerBalance());

                System.out.println("-------------------------------");

                if (turn % 2 == 0) {

                    exit = player1.PlayerPlay(deckPlayer1, deck);

                } else {

                    System.out.println("It's computers turn, cards are: ");
                    Card.DisplayDeck(deckPlayer2);
                    BotChoice = bot.BotBet(deckPlayer2, bot.getPlayerBalance());
                    exit = bot.BotPlay(BotChoice, deckPlayer2, deck);
                }

                System.out.println();
                turn++;

            } else {
                exit = DecideWinner(player1.getPlayerBalance(), bot.getPlayerBalance());
            }
            System.out.print("------------------------------");
        }
    }

    public static int DecideWinner(double p1Balance, double p2Balance) {
        System.out.println("\nWe are out of cards!");
        System.out.println("The winner is");

        if (p1Balance > p2Balance) {
            System.out.println("PLAYER 1");
        } else if (p1Balance < p2Balance) {
            System.out.println("PLAYER 2");
        } else {
            System.out.println("IT'S A DRAW");
        }

        return 1;
    }

    public static boolean WinningCondition(String newCard, String[] deck) {

        int firstCard;
        int secondCard;
        int middleCard;

        System.out.println();

        deck[0] = deck[0].substring(0, 2);
        firstCard = Integer.parseInt(deck[0].strip());
        deck[1] = deck[1].substring(0, 2);
        secondCard = Integer.parseInt(deck[1].strip());
        newCard = newCard.substring(0, 2);
        middleCard = Integer.parseInt(newCard.strip());

        if (firstCard > middleCard && secondCard < middleCard) {
            return true;
        } else return secondCard > middleCard && firstCard < middleCard;
    }
}
