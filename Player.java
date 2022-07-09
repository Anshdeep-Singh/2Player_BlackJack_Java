public class Player {

    private String playerName;
    private double playerBalance;
    private boolean status;

    public String getPlayerName(){
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
}
