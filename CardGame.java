import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

public class CardGame {

    public static void main(String[] args) {
        //player input
        Scanner input = new Scanner(System.in);
        //Two player objects
        Player player1 = new Player();
        Player player2 = new Player();
        //setting initial states
        player1.setPlayerBalance(5);
        player2.setPlayerBalance(5);

        player1.setStatus(false);
        player2.setStatus(false);
        final int BOT_BET = 1;

        String[] deckPlayer1 = new String[2];
        String[] deckPlayer2 = new String[2];

        int turn = 0;
        int exit = 0;


        ArrayList<String> usedCards = new ArrayList<String>();
        ArrayList<String> deck = new ArrayList<String>();
        Card cards = new Card();

        //generating deck
        for (int i = 0; i < cards.getCARD_NUMBER().length; i++) {
            for (int j = 0; j < cards.getCARD_TYPE().length; j++) {
                deck.add(cards.getCARD_NUMBER()[i] + " " + cards.getCARD_TYPE()[j]);
            }
        }

        //Selecting cards for player1
        int selectCardNumber;
        String userChoice;
        String newCard;
        char BotChoice;
        double bet = 0;
        boolean playerWin = false;


        while (exit != 1) {

            if (deck.size() > 3) {

                System.out.println("-------------------------------");
                System.out.print("CARDS LEFT IN DECK -> ");
                System.out.println(deck.size());

                System.out.println("-------------------------------");
                for (int i = 0; i < 2; ++i) {
                    selectCardNumber = (int) (Math.random() * deck.size());
                    deckPlayer1[i] = deck.get(selectCardNumber);
                    deck.remove(deck.get(selectCardNumber));
                }

                //Selecting cards for the bot
                for (int i = 0; i < 2; ++i) {
                    selectCardNumber = (int) (Math.random() * deck.size());
                    deckPlayer2[i] = deck.get(selectCardNumber);
                    deck.remove(deck.get(selectCardNumber));
                }


                System.out.format("Your Balance: %.2f \n", player1.getPlayerBalance());
                System.out.format("Computer Balance: %.2f \n", player2.getPlayerBalance());

                //Game Begins


                System.out.println("-------------------------------");
                if (turn % 2 == 0) {
                    System.out.println("\nIt's your turn; your cards are:");
                    DisplayDeck(deckPlayer1);
                    System.out.print("\nPress X to exit\n(B)et or (F)old? Your choice is ");
                    userChoice = input.nextLine();
                    if (userChoice.equalsIgnoreCase("B")) {
                        System.out.print("Cool! How much do you wanna bet? : $");
                        userChoice = input.nextLine();
                        if (userChoice.equalsIgnoreCase("X")) {
                            exit = 1;
                        } else {
                            bet = Double.parseDouble(userChoice);
                            while (bet < 1 | bet > player1.getPlayerBalance()) {
                                System.out.println("\nMinimum bet should be greater than or equal to 1 and must be less than your current balance");
                                System.out.print("Enter bet: $");
                                bet = input.nextDouble();
                                input.nextLine();
                            }
                            player1.setPlayerBalance(player1.getPlayerBalance() - bet);
                            selectCardNumber = (int) (Math.random() * deck.size());
                            newCard = deck.get(selectCardNumber);
                            deck.remove(newCard);
                            System.out.format("\nYou bet $ %.2f and the new card is %s", bet, newCard);
                            playerWin = WinningCondition(newCard, deckPlayer1);
                            player1.setStatus(playerWin);
                            if (playerWin) {
                                System.out.print("\nYou won!");
                                player1.setPlayerBalance(player1.getPlayerBalance() + 2 * bet);
                            } else {
                                System.out.println("\nYou lose!");
                            }
                            System.out.println();
                        }
                    } else if (userChoice.equalsIgnoreCase("F")) {
                        System.out.println("\nYou choose to fold");
                        player1.setPlayerBalance(player1.getPlayerBalance() - 0.5);
                    } else if (userChoice.equalsIgnoreCase("X")) {
                        exit = 1;
                    }
                    if (player1.getPlayerBalance() <= 0.5) {
                        System.out.println("Your balance is less than the minimum balance required to play the game.");
                        System.out.println("PLAYER 2 WON");
                        exit = 1;
                    }
                } else {
                    System.out.println("It's computers turn, cards are: ");
                    DisplayDeck(deckPlayer2);
                    BotChoice = RobotLogic(deckPlayer2, player2.getPlayerBalance());
                    if (BotChoice == 'B') {
                        player2.setPlayerBalance(player2.getPlayerBalance() - BOT_BET); // because the bot always bets 1
                        selectCardNumber = (int) (Math.random() * deck.size());
                        newCard = deck.get(selectCardNumber);
                        deck.remove(newCard);
                        System.out.format("\nComputer bet $ %d and the new card is -> ", BOT_BET);
                        DisplayCard(newCard);
                        playerWin = WinningCondition(newCard, deckPlayer1);
                        player2.setStatus(playerWin);
                        System.out.println();
                        if (playerWin) {
                            System.out.print("\nYou won!");
                            player2.setPlayerBalance(player2.getPlayerBalance() + 2 * BOT_BET);
                        } else {
                            System.out.println("\nComputer lost!");
                        }
                        System.out.println();

                    } else {
                        System.out.println("\nComputer choose to fold");
                        player2.setPlayerBalance(player2.getPlayerBalance() - 0.5);
                    }
                    if (player2.getPlayerBalance() <= 0.5) {
                        System.out.println("Your balance is less than the minimum balance required to play the game.");
                        System.out.println("PLAYER 1 WON");
                        exit = 1;
                    }
                }

                System.out.println();

                turn++;
            } else {
                System.out.println("\nWe are out of cards!");
                System.out.println("The winner is");
                if (player1.getPlayerBalance() > player2.getPlayerBalance()) {
                    System.out.println("PLAYER 1");
                } else if (player1.getPlayerBalance() < player2.getPlayerBalance()) {
                    System.out.println("PLAYER 2");
                } else {
                    System.out.println("IT'S A DRAW");
                }
                exit = 1;
            }
            System.out.print("------------------------------");
        }
    }

    public static char RobotLogic(String[] deck, double balance) {
        int firstCard;
        int secondCard;

        deck[0] = deck[0].substring(0, 2);
        firstCard = Integer.parseInt(deck[0].strip());
        deck[1] = deck[1].substring(0, 2);
        secondCard = Integer.parseInt(deck[1].strip());

        if (balance >= 1) {
            if (Math.pow(firstCard - secondCard, 2) > 25) {
                return 'B';
            } else {
                return 'F';
            }
        } else {
            return 'F';
        }
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
