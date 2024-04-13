package is.hi.hbv202g.blackJack;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BlackJack implements Observer {
    private Player player;
    private Deck deck;
    private Dealer dealer;

    public BlackJack() {
        this.player = new Player();
        this.deck = new Deck();
        this.dealer = new Dealer();
        this.player.attach(this);
        this.dealer.attach(this);
    }

    /**
     * Byrjar leik
     */
    public void startGame() {
        for(int i = 0; i < 2; i++){
            playersDraw(player);
            playersDraw(dealer);
        }
        if(findWinner(false) != null){
            System.out.println(findWinner(false));
            gameState(true);
        } else {
            System.out.println(player.getCards());
            System.out.println(dealer.getCards());
            gameState(false);
        }
    }

    private void playersDraw(Player player){
        Card c = deck.drawCard();
        player.drawCard(c);
    }

    private String findWinner(boolean playerStands){
        if (player.hasTwentyOne() || dealer.isBust()) {
            return "Til hamingju, þú vannst og með samtölu: " + player.getScore();
        } else if (dealer.hasTwentyOne() || player.isBust()) {
            return "Því miður vann dealerinn með samtölu: " + dealer.getScore();
        }
        if (playerStands) {
            if (player.getScore() == dealer.getScore()) {
                return "Það varð jafntefli";
            }
            if (player.whoWins(dealer)) {
                return "Til hamingju , þú vannst og með samtölu: " + player.getScore();
            } else {
                return "Því miður vann dealerinn með samtölu: " + dealer.getScore();
            }
        }

        return null;
    }

    private void gameState(boolean newGame){
        try (Scanner s = new Scanner(System.in, StandardCharsets.UTF_8)) {
            if(newGame){
                System.out.println("Viltu spila nyjan leik?");
            } else {
                System.out.println("If you want to Stand pls press 1. If you want a new Card pls press 2.");
            }

            int nextMove = s.nextInt();

            if(nextMove == 1){
                System.out.println("He stands");
            } else if(nextMove == 2){
                System.out.println("Wants a new card");
            } else {
                System.out.println("Eitthvað");
            }
        }
    }

    @Override
    public void update() {
        //Seinna
    }

    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.startGame();
    }
}
