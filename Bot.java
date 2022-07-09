import java.util.ArrayList;

public class Bot extends Player {

    private final int BOT_BET = 1;

    public int getBOT_BET() {
        return BOT_BET;
    }

    public char BotBet(String[] deck, double balance) {
        int firstCard;
        int secondCard;

        deck[0] = deck[0].substring(0, 2);
        firstCard = Integer.parseInt(deck[0].strip());
        deck[1] = deck[1].substring(0, 2);
        secondCard = Integer.parseInt(deck[1].strip());

        if (balance >= 1) {
            if (Math.pow(firstCard - secondCard, 2) > (int) (Math.random() * 25)) {
                return 'B';
            } else {
                return 'F';
            }
        } else {
            return 'F';
        }
    }

    public int BotPlay(char choice, String[] deckPlayer, ArrayList<String> deck) {
        int selectCardNumber;
        String newCard;
        boolean playerWin;
        if (choice == 'B') {
            setPlayerBalance(getPlayerBalance() - getBOT_BET());
            selectCardNumber = (int) (Math.random() * deck.size());
            newCard = deck.get(selectCardNumber);
            deck.remove(newCard);
            System.out.format("\nComputer bet $ %d and the new card is -> ", getBOT_BET());
            Card.DisplayCard(newCard);
            playerWin = CardGame.WinningCondition(newCard, deckPlayer);
            setStatus(playerWin);
            System.out.println();
            if (playerWin) {
                System.out.print("\nYou won!");
                setPlayerBalance(getPlayerBalance() + 2 * getBOT_BET());
            } else {
                System.out.println("\nComputer lost!");
            }
            System.out.println();
        } else {
            System.out.println("\n\nComputer choose to fold");
            setPlayerBalance(getPlayerBalance() - 0.5);
        }
        if (getPlayerBalance() <= 0.5) {
            System.out.println("Your balance is less than the minimum balance required to play the game.");
            System.out.println("PLAYER 1 WON");
            return 1;
        }

        return 0;
    }

}
