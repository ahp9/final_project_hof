package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.Player;
import hi.verkefni.vinnsla.Card;
import hi.verkefni.vinnsla.Dealer;
import hi.verkefni.vinnsla.Deck;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;


public class SpilController implements Initializable {
    @FXML
    public Button fxnyrLeikur;
    @FXML
    public Button fxNyttSpil;
    @FXML
    public Button fxKomidnog;
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


    private Player leikmadur = new Player();
    private Deck stokkur = new Deck();
    private Player dealer = new Dealer();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LeikmadurDialog l = new LeikmadurDialog();
        String [] leikmenn = l.hvadHeitaLeikmenn();
        if (leikmenn != null){
            fxNafnLeikmadur.setText(leikmenn[0] + "");
            for (int i = 0; i < 2; i++){
                gefaLSpil();
                gefaDSpil();
            }
            if (finnaSigurvegara() != null){
                if (sprakk()){
                    fxVinningur.setText(sprakkText());
                    stada2();
                } else {
                    fxVinningur.setText(finnaSigurvegara());
                    stada2();
                }
            }
            stada1();
        } else {
            Platform.exit();
        }

    }

    /**
     * Dregur nýtt spil og gefur leikmanninu. Reiknar út samtölu
     * leikmannsins
     */
    @FXML
    protected void nyttSpilHandler(){
        gefaLSpil();
        if (finnaSigurvegara() != null){
            if (sprakk()){
                fxVinningur.setText(sprakkText());
                stada2();
            } else {
                fxVinningur.setText(finnaSigurvegara());
                stada2();
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
    protected void komidNogHandler(){
        System.out.println();
        while (!((Dealer) dealer).hasSeventeen()){
            System.out.println(((Dealer) dealer).hasSeventeen());
            gefaDSpil();
        }
        if (sprakk()){
            fxVinningur.setText(sprakkText());
            stada2();
        } else {
            fxVinningur.setText(vinnurDealer());
            stada2();
        }
    }

    /**
     * Eyðir öllu frá seinasta leik, núllstillir og býr til
     * nýjan stokk og gefur leikmanninum og dealernum 2 ný spil.
     * Athugar hvort annar þeirra hefur unnið strax
     */
    @FXML
    protected void nyrLeikurHandler (){
        eydaMyndum();
        fxVinningur.setText("");
        stokkur = new Deck();
        leikmadur.newGame();
        dealer.newGame();
        stada1();
        for (int k = 0; k < 2; k++){
            gefaLSpil();
            gefaDSpil();
        }
        if (finnaSigurvegara() != null){
            if (sprakk()){
                fxVinningur.setText(sprakkText());
            } else {
                fxVinningur.setText(finnaSigurvegara());
            }
        }
    }

    /**
     * Óvirkir Nýr Leikur hnappinn á meðan verið er að spila.
     * Nýtt Spil og Komið Nóg er enn virkir
     */
    private void stada1(){
        fxnyrLeikur.setDisable(true);
        fxNyttSpil.setDisable(false);
        fxKomidnog.setDisable(false);
    }

    /**
     * Óvirkjar Nýtt spil og Komið nóg en virkir Nýr leikur.
     * Gerist þegar leikur er búinn.
     */
    private void stada2(){
        fxnyrLeikur.setDisable(false);
        fxNyttSpil.setDisable(true);
        fxKomidnog.setDisable(true);
    }

    /**
     * Eyðir myndum frá fyrrum leik.
     */
    private void eydaMyndum() {
        fxLeikmadurHendi.getChildren().removeAll(fxLeikmadurHendi.getChildren());
        fxDealerHendi.getChildren().removeAll(fxDealerHendi.getChildren());
    }

    /**
     * Tekur inn spilV og breytir því í Spil
     *
     * @param a SpilV sem var dregið úr stokk.
     * @return skilar SpilV umbreyttu í Spil
     */
    private Spil nyttSpil(Card a){
        Spil aHendi = new Spil();
        aHendi.setSpil(a);
        return aHendi;
    }

    /**
     * Setur spil á hendi leikmannsins og prentar út samtölu hans.
     */
    private void gefaLSpil(){
        Card l = stokkur.dragaSpil();
        fxLeikmadurHendi.getChildren().add(nyttSpil(l));
        leikmadur.drawCard(l);
        fxSamtalsLeikamdur.setText(leikmadur.getScore() + "");
    }

    /**
     * Setur spil á hendi dealersins og prentar út samtölu hans.
     */
    private void gefaDSpil(){
        Card d = stokkur.dragaSpil();
        fxDealerHendi.getChildren().add(nyttSpil(d));
        dealer.drawCard(d);
        fxSamtalsDealer.setText(dealer.getScore() + "");
    }

    /**
     * Tjékkar hvort einhver er búinn að vinna
     *
     * @return skilar streng þess sem er búinn að vinna eða skilar engu ef
     * hvorugur hefur unnið
     */
    private String finnaSigurvegara (){
        if (leikmadur.whoWon(dealer) == leikmadur){
            return "Til hamingju " + fxNafnLeikmadur.getText() + ", þú vannst og með samtölu: " + leikmadur.getScore();
        } else if (leikmadur.whoWon(dealer) == dealer){
            return "Því miður vann dealerinn með samtölu: " + dealer.getScore();
        }
        return null;
    }

    /**
     * Tjékkar hvort dealerinn er búinn að vinna
     *
     * @return skilar streng hvort dealerinn eða leikmaðurinn hefur unnið
     */
    private String vinnurDealer(){
        if (leikmadur.dealerWins(dealer)){
            return "Því miður vann dealerinn með samtölu: " + dealer.getScore();
        } else {
            return "Til hamingju " + fxNafnLeikmadur.getText() + ", þú vannst og með samtölu: " + leikmadur.getScore();
        }
    }

    private boolean sprakk(){
        if (leikmadur.getScore() > 21 ||dealer.getScore() > 21 ){
            return true;
        }
        return false;
    }

    private String sprakkText(){
        if (leikmadur.getScore() > 21){
            return "Dealerinn vinnur, þú sprakkst: " + leikmadur.getScore();
        } else {
            return "Til hamingju " + fxNafnLeikmadur.getText() + " þú vanst, dealerinn sprakk: " +
                    dealer.getScore();
        }
    }
}