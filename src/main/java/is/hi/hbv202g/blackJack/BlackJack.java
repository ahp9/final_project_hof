package is.hi.hbv202g.blackJack;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BlackJack implements Observer {
    private Player player;
    private Deck deck;
    private Dealer dealer;

    private static final String NEW_GAME_PROMPT = "Do you want to play another game?";
    private static final String PLAYER_MOVE_PROMPT = "If you want to Stand please press 1. If you want a new Card please press 2.";
    private static final String PLAYER_WON_TEXT = "Congratulations, you won and with:";
    private static final String DEALER_WON_TEXT = "Im sorry, the dealer won with";

    public BlackJack() {
        this.player = new Player();
        this.deck = new Deck();
        this.dealer = new Dealer();
        this.player.attach(this);
        this.dealer.attach(this);
    }

    @Override
    public void update() {
        if(player.getCards().size() > 1 && dealer.getCards().size() > 1){
            System.out.println("Dealer spil: " + dealer.getCards() + ". Samtals: " + dealer.getScore());
            System.out.println("------------------------------");
            System.out.println("Spilin þín:  " + player.getCards() + ". Samtals: " + player.getScore());
        }
    }

    /**
     * Byrjar leik
     */
    public void startGame() {
        for(int i = 0; i < 2; i++){
            playersDraw(player);
            playersDraw(dealer);
        }
        checkGame(false);
    }

    private void playersDraw(Player player){
        Card c = deck.drawCard();
        player.drawCard(c);
    }

    private String findWinner(boolean playerStands){
        if (player.hasTwentyOne() || dealer.isBust()) {
            return PLAYER_WON_TEXT + player.getScore();
        } else if (dealer.hasTwentyOne() || player.isBust()) {
            return DEALER_WON_TEXT + dealer.getScore();
        }
        if (playerStands) {
            if (player.getScore() == dealer.getScore()) {
                return "Það varð jafntefli";
            }
            if (player.whoWins(dealer)) {
                return PLAYER_WON_TEXT + player.getScore();
            } else {
                return DEALER_WON_TEXT + dealer.getScore();
            }
        }

        return null;
    }

    private void gameState(boolean newGame){
        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            System.out.println(newGame ? NEW_GAME_PROMPT : PLAYER_MOVE_PROMPT);
            String nextMove = scanner.next();
            switch (nextMove) {
                case "1":
                    System.out.println("Player stands");
                    stand();
                    break;
                case "2":
                    System.out.println("Player wants a new card");
                    drawNewCard();
                    break;
                case "y":
                    System.out.println("Player wants a new game");
                    newGame();
                    break;
                default:
                    System.out.println("Player is done");
            }
        }
    }

    private void checkGame(boolean playerStands){
        if(findWinner(playerStands) != null){
            System.out.println(findWinner(false));
            gameState(true);
        } else {
            gameState(false);
        }
    }

    private void drawNewCard(){
        playersDraw(player);
        checkGame(false);
    }

    private void stand(){
        while(!(dealer.hasSeventeen())){
            playersDraw(dealer);
        }
        checkGame(true);
    }

    private void newGame(){
        deck = new Deck();
        player.newGame();
        dealer.newGame();
        startGame();
    }


    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.startGame();
    }
}
