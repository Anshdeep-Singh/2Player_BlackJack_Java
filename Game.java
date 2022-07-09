import java.util.Scanner;

public class Game {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String choice;
        do {
            CardGame.BlackJack();
            System.out.print("\nPress (Y) to play the game and (X) to exit...");
            choice = input.nextLine();
        }while(choice.equalsIgnoreCase("Y"));
    }
}
