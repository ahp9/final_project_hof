package is.hi.hbv202g.blackJack;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BlackJack implements Observer {
    private Player player;
    private Deck deck;
    private Dealer dealer;

    private static final String NEW_GAME_PROMPT = "Do you want to play another game? (y/n)";
    private static final String PLAYER_MOVE_PROMPT = "If you want to Stand please press 1. If you want a new Card please press 2.";
    private static final String PLAYER_WON_TEXT = "Congratulations, you won and with: ";
    private static final String DEALER_WON_TEXT = "Im sorry, the dealer won with ";
    private static final String TIE_TEXT = "It's a tie!";

    public BlackJack() {
        this.player = new Player();
        this.deck = new Deck();
        this.dealer = new Dealer();
        this.player.attach(this);
        this.dealer.attach(this);
    }

    /**
     * Uppfærir leikmenn, birtir spil og samtal þeirra
     */
    @Override
    public void update() {
        if(player.getCards().size() > 1 && dealer.getCards().size() > 1){
            System.out.println("Dealer cards: " + dealer.getCards() + ". \nTotal score: " + dealer.getScore());
            System.out.println("------------------------------");
            System.out.println("Your cards:  " + player.getCards() + ". \nTotal score: " + player.getScore());
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

    /**
     * Leikmaður dregur nýtt spil og bætt á hendi
     * @param player Leikmaður / Dealer
     */
    private void playersDraw(Player player){
        Card c = deck.drawCard();
        player.drawCard(c);
    }

    /**
     * Athugar hver vann leikinn ef einhver vann
     * @param playerStands boolean um hvort leikmaður stendur eða ekki
     * @return streng sem inniheldur hver vann annars null
     */
    private String findWinner(boolean playerStands){
        if (player.hasTwentyOne() || dealer.isBust()) {
            return PLAYER_WON_TEXT + player.getScore();
        } else if (dealer.hasTwentyOne() || player.isBust()) {
            return DEALER_WON_TEXT + dealer.getScore();
        }
        if (playerStands) {

            if (player.getScore() == dealer.getScore()) {
                return TIE_TEXT;
            }
            if (player.whoWins(dealer)) {
                return PLAYER_WON_TEXT + player.getScore();
            } else {
                return DEALER_WON_TEXT + dealer.getScore();
            }
        }

        return null;
    }

    /**
     * Setur fram texta eftir því hver staðan er í leiknum og tekur
     * inn scanner input varðandi næstu stöðu leiksins
     * @param newGame boolean um hvort þetta er nýr leikur eða ekki
     */
    private void gameState(boolean newGame){
        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            boolean validInput = false;
            while (!validInput) {
                System.out.println(newGame ? NEW_GAME_PROMPT : PLAYER_MOVE_PROMPT);
                String nextMove = scanner.next();
                System.out.println();
                switch (nextMove) {
                    case "1":
                        System.out.println("Player stands\n ");
                        stand();
                        validInput = true;
                        break;
                    case "2":
                        System.out.println("Player wants a new card\n ");
                        drawNewCard();
                        validInput = true;
                        break;
                    case "y":
                        System.out.println("Player wants a new game\n ");
                        newGame();
                        validInput = true;
                        break;
                    case "n":
                        System.out.println("Player is done\n ");
                        validInput = true;
                        break;
                    default:
                        System.out.println("Invalid input. Please try again.");
                }
            }
        }
    }

    /**
     * Athugar hvort leikmaður hefur unnið eða ekki og setur fram
     * næstu stöðu leiksins
     * @param playerStands boolean um hvort leikmaður stendur eða ekki
     */
    private void checkGame(boolean playerStands){
        if(findWinner(playerStands) != null){
            System.out.println(findWinner(playerStands));
            gameState(true);
        } else {
            gameState(playerStands);
        }
    }

    /**
     * Leikmaður dregur nýtt spil og athugar stöðu leiks
     */
    private void drawNewCard(){
        playersDraw(player);
        checkGame(false);
    }

    /**
     * Leikmaður stendur og athugar stöðu leiks. Dealer dregur
     * spil ef samtals er 17 eða minna
     */
    private void stand(){
        while(!(dealer.hasSeventeen())){
            playersDraw(dealer);
        }
        checkGame(true);
    }

    /**
     * Nýr leikinn
     */
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
