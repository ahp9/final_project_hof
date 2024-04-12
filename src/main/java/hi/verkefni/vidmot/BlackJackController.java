package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.Player;
import hi.verkefni.vinnsla.Card;
import hi.verkefni.vinnsla.Dealer;
import hi.verkefni.vinnsla.Deck;
import hi.verkefni.vinnsla.Observer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BlackJackController implements Initializable, Observer {
    @FXML
    private Button fxnyrLeikur;
    @FXML
    private Button fxNyttSpil;
    @FXML
    private Button fxKomidnog;
    @FXML
    private HBox fxDealerHendi;
    @FXML
    private HBox fxLeikmadurHendi;
    @FXML
    private Label fxNafnLeikmadur;
    @FXML
    private Label fxVinningur;
    @FXML
    private Label fxSamtalsLeikamdur;
    @FXML
    private Label fxSamtalsDealer;

    private Player player = new Player();
    private Deck deck = new Deck();
    private Player dealer = new Dealer();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PlayerDialog l = new PlayerDialog();
        String playersName = l.playersName();

        if (playersName != null) {
            player.attach(this);
            dealer.attach(this);

            fxNafnLeikmadur.setText(playersName + "");
            gameSetup();
        } else {
            Platform.exit();
        }

    }

    @Override
    public void update() {
        fxSamtalsLeikamdur.setText(player.getScore() + "");
        fxSamtalsDealer.setText(dealer.getScore() + "");
    }

    private void gameSetup() {
        for (int k = 0; k < 2; k++) {
            playerDraw(player, false);
            playerDraw(dealer, true);
        }
        if (findWinner(false) != null) {
            fxVinningur.setText(findWinner(false));

            gameState(false, true, true);
        } else {
            gameState(true, false, false);
        }
    }

    /**
     * Setur spil á hendi leikmannsins og prentar út samtölu hans.
     */
    private void playerDraw(Player p, boolean isDealer) {
        Card l = deck.drawCard();
        if (isDealer) {
            fxDealerHendi.getChildren().add(newCard(l));
        } else {
            fxLeikmadurHendi.getChildren().add(newCard(l));
        }
        p.drawCard(l);
    }

    /**
     * Dregur nýtt spil og gefur leikmanninu. Reiknar út samtölu
     * leikmannsins
     */
    @FXML
    protected void drawNewCard() {
        playerDraw(player, false);
        if (findWinner(false) != null) {
            fxVinningur.setText(findWinner(false));

            gameState(false, true, true);
        }
        fxSamtalsDealer.setText(dealer.getScore() + "");
    }

    /**
     * Á meðan samtala á hendi dealers er minni en 17 þá
     * er dregið nýtt spil fyrir dealer og gefin á hendi
     * dealersins. Athugað hvort dealerinn vann eða ekki.
     */
    @FXML
    protected void stand() {
        while (!((Dealer) dealer).hasSeventeen()) {
            playerDraw(dealer, true);
        }

        fxVinningur.setText(findWinner(true));
        gameState(false, true, true);

    }

    /**
     * Eyðir öllu frá seinasta leik, núllstillir og býr til
     * nýjan stokk og gefur leikmanninum og dealernum 2 ný spil.
     * Athugar hvort annar þeirra hefur unnið strax
     */
    @FXML
    protected void newGame() {
        removeCards();
        fxVinningur.setText("");
        deck = new Deck();
        player.newGame();
        dealer.newGame();
        gameState(true, false, false);
        gameSetup();
    }

    /**
     * Tekur inn spilV og breytir því í Spil
     *
     * @param a SpilV sem var dregið úr stokk.
     * @return skilar SpilV umbreyttu í Spil
     */
    private CardUI newCard(Card s) {
        CardUI onHand = new CardUI();
        onHand.setCardUI(s);
        return onHand;
    }

    /**
     * Tjékkar hvort einhver er búinn að vinna
     * 
     * @param playerStands segir til hvort leikmaður vill standa
     *                     og finnur sigurvegara útfrá þeirra niðurstöðu.
     * 
     * @return skilar viðeigandi streng eftir því hvor vann eða skilar engu ef
     *         hvorugur hefur unnið
     */
    private String findWinner(boolean playerStands) {
        if (player.hasTwentyOne() || dealer.isBust()) {
            return "Til hamingju " + fxNafnLeikmadur.getText() + ", þú vannst og með samtölu: " + player.getScore();
        } else if (dealer.hasTwentyOne() || player.isBust()) {
            return "Því miður vann dealerinn með samtölu: " + dealer.getScore();
        }
        if (playerStands) {
            if (player.getScore() == dealer.getScore()) {
                return "Það varð jafntefli";
            }
            if (player.whoWins(dealer)) {
                return "Til hamingju " + fxNafnLeikmadur.getText() + ", þú vannst og með samtölu: " + player.getScore();
            } else {
                return "Því miður vann dealerinn með samtölu: " + dealer.getScore();
            }
        }

        return null;
    }

    /**
     * Breytir stöðu hnappa eftir því hver staðan er í leiknum.
     * 
     * @param newGame er nýr leikur
     * @param newCard er hægt að setja nýtt spil
     * @param isDone  er leikmaður kominn með nóg
     */
    private void gameState(boolean newGame, boolean newCard, boolean isDone) {
        fxnyrLeikur.setDisable(newGame);
        fxNyttSpil.setDisable(newCard);
        fxKomidnog.setDisable(isDone);
    }

    /**
     * Eyðir spilum frá fyrrum leik.
     */
    private void removeCards() {
        fxLeikmadurHendi.getChildren().removeAll(fxLeikmadurHendi.getChildren());
        fxDealerHendi.getChildren().removeAll(fxDealerHendi.getChildren());
    }
}