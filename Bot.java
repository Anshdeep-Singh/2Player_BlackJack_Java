public class Bot extends Player{

    private final int BOT_BET = 1;

    public int getBOT_BET() {
        return BOT_BET;
    }

    public char RobotLogic(String[] deck, double balance) {
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

}
