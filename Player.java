import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {

    private String playerName;
    private double playerBalance;
    private boolean status;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public double getPlayerBalance() {
        return playerBalance;
    }

    public void setPlayerBalance(double playerBalance) {
        this.playerBalance = playerBalance;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int PlayerPlay(String[] deckPlayer, ArrayList<String> deck) {

        Scanner input = new Scanner(System.in);
        String userChoice;
        String newCard;
        boolean playerWin;
        double bet;
        int selectCardNumber;

        System.out.println("\nIt's your turn; your cards are:");
        Card.DisplayDeck(deckPlayer);
        System.out.print("\nPress X to exit\n(B)et or (F)old? Your choice is ");

        userChoice = input.nextLine();
        if (userChoice.equalsIgnoreCase("B")) {
            System.out.print("Cool! How much do you wanna bet? : $");
            userChoice = input.nextLine();
            if (userChoice.equalsIgnoreCase("X")) {
                return 1;
            } else {
                try {
                    bet = Double.parseDouble(userChoice);
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Invalid value,using default bet -> $1");
                    bet = 1;
                }
                while (bet < 1 | bet > getPlayerBalance()) {
                    System.out.println("\nMinimum bet should be greater than or equal to 1 and must be less than your current balance");
                    System.out.print("Enter bet: $");
                    try {
                        bet = input.nextDouble();
                        input.nextLine();

                    } catch (InputMismatchException | NumberFormatException e) {
                        System.out.println("Invalid value,using default bet -> $1");
                        bet = 1;
                    }
                }
                setPlayerBalance(getPlayerBalance() - bet);
                selectCardNumber = (int) (Math.random() * deck.size());
                newCard = deck.get(selectCardNumber);
                deck.remove(newCard);
                System.out.format("\nYou bet $ %.2f and the new card is ->", bet);
                Card.DisplayCard(newCard);
                playerWin = CardGame.WinningCondition(newCard, deckPlayer);
                setStatus(playerWin);
                if (playerWin) {
                    System.out.print("\nYou won!");
                    setPlayerBalance(getPlayerBalance() + 2 * bet);
                } else {
                    System.out.println("\nYou lose!");
                }
                System.out.println();
            }
        } else if (userChoice.equalsIgnoreCase("F")) {
            System.out.println("\nYou choose to fold");
            setPlayerBalance(getPlayerBalance() - 0.5);
        } else if (userChoice.equalsIgnoreCase("X")) {
            return 1;
        } else {
            System.out.println("\nYou choose to fold");
            setPlayerBalance(getPlayerBalance() - 0.5);
        }
        if (getPlayerBalance() <= 0.5) {
            System.out.println("Your balance is less than the minimum balance required to play the game.");
            System.out.println("PLAYER 2 WON");
            return 1;
        }
        return 0;
    }
}
