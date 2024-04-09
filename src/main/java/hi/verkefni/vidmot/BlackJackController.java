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
        player.attach(this);
        dealer.attach(this);
        if (playersName != null) {
            fxNafnLeikmadur.setText(playersName + "");

            gameInPlayState();
            gameSetup();
        } else {
            Platform.exit();
        }

    }

    /**
     * Dregur nýtt spil og gefur leikmanninu. Reiknar út samtölu
     * leikmannsins
     */
    @FXML
    protected void drawNewCard() {
        playerDraws();
        if (findWinner() != null) {
            if (player.isBust() || dealer.isBust()) {
                fxVinningur.setText(sprakkText());
                gameFinishedState();
            } else {
                fxVinningur.setText(findWinner());
                gameFinishedState();
            }
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
            dealerDraws();
        }
        if (player.isBust() || dealer.isBust()) {
            fxVinningur.setText(sprakkText());
            gameFinishedState();
        } else {
            fxVinningur.setText(whoWon());
            gameFinishedState();
        }
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
        gameInPlayState();
        gameSetup();
    }

    private void gameSetup() {
        for (int k = 0; k < 2; k++) {
            playerDraws();
            dealerDraws();
        }
        if (findWinner() != null) {
            if (player.isBust() || dealer.isBust()) {
                fxVinningur.setText(sprakkText());
                gameFinishedState();
            } else {
                fxVinningur.setText(findWinner());
                gameFinishedState();
            }
        }
    }

    /**
     * Óvirkir Nýr Leikur hnappinn á meðan verið er að spila.
     * Nýtt Spil og Komið Nóg er enn virkir
     */
    private void gameInPlayState() {
        fxnyrLeikur.setDisable(true);
        fxNyttSpil.setDisable(false);
        fxKomidnog.setDisable(false);
    }

    /**
     * Óvirkjar Nýtt spil og Komið nóg en virkir Nýr leikur.
     * Gerist þegar leikur er búinn.
     */
    private void gameFinishedState() {
        fxnyrLeikur.setDisable(false);
        fxNyttSpil.setDisable(true);
        fxKomidnog.setDisable(true);
    }

    /**
     * Eyðir spilum frá fyrrum leik.
     */
    private void removeCards() {
        fxLeikmadurHendi.getChildren().removeAll(fxLeikmadurHendi.getChildren());
        fxDealerHendi.getChildren().removeAll(fxDealerHendi.getChildren());
    }

    /**
     * Tekur inn spilV og breytir því í Spil
     *
     * @param a SpilV sem var dregið úr stokk.
     * @return skilar SpilV umbreyttu í Spil
     */
    private CardUI newCard(Card s) {
        CardUI aHendi = new CardUI();
        aHendi.setCardUI(s);
        return aHendi;
    }

    /**
     * Setur spil á hendi leikmannsins og prentar út samtölu hans.
     */
    private void playerDraws() {
        Card l = deck.dragaSpil();
        fxLeikmadurHendi.getChildren().add(newCard(l));
        player.drawCard(l);
    }

    /**
     * Setur spil á hendi dealersins og prentar út samtölu hans.
     */
    private void dealerDraws() {
        Card d = deck.dragaSpil();
        fxDealerHendi.getChildren().add(newCard(d));
        dealer.drawCard(d);
    }

    /**
     * Tjékkar hvort einhver er búinn að vinna
     *
     * @return skilar streng þess sem er búinn að vinna eða skilar engu ef
     *         hvorugur hefur unnið
     */
    private String findWinner() {
        if (player.hasTwentyOne() || dealer.isBust()) {
            return "Til hamingju " + fxNafnLeikmadur.getText() + ", þú vannst og með samtölu: " + player.getScore();
        } else if (dealer.hasTwentyOne() || player.isBust()) {
            return "Því miður vann dealerinn með samtölu: " + dealer.getScore();
        }
        return null;
    }

    private String whoWon() {
        if (player.getScore() == dealer.getScore()) {
            return "Draw";
        }
        if (player.whoWins(dealer)) {
            return "Player";
        } else {
            return "Dealer";
        }
    }

    private String sprakkText() {
        if (player.getScore() > 21) {
            return "Dealerinn vinnur, þú sprakkst: " + player.getScore();
        } else {
            return "Til hamingju " + fxNafnLeikmadur.getText() + " þú vannst, dealerinn sprakk: " +
                    dealer.getScore();
        }
    }

    @Override
    public void update() {
        fxSamtalsLeikamdur.setText(player.getScore() + "");
        fxSamtalsDealer.setText(dealer.getScore() + "");
    }
}