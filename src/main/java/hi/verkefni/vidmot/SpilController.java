package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.Leikmadur;
import hi.verkefni.vinnsla.SpilV;
import hi.verkefni.vinnsla.Stokkur;
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


    private Leikmadur leikmadur = new Leikmadur();
    private Stokkur stokkur = new Stokkur();
    private Leikmadur dealer = new Leikmadur();


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
        fxSamtalsDealer.setText(dealer.getSamtals() + "");

    }

    /**
     * Á meðan samtala á hendi dealers er minni en 17 þá
     * er dregið nýtt spil fyrir dealer og gefin á hendi
     * dealersins. Athugað hvort dealerinn vann eða ekki.
     */
    @FXML
    protected void komidNogHandler(){
        while (dealer.getSamtals() < 17){
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
        stokkur = new Stokkur();
        leikmadur.nyrLeikur();
        dealer.nyrLeikur();
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
    private Spil nyttSpil(SpilV a){
        Spil aHendi = new Spil();
        aHendi.setSpil(a);
        return aHendi;
    }

    /**
     * Setur spil á hendi leikmannsins og prentar út samtölu hans.
     */
    private void gefaLSpil(){
        SpilV l = stokkur.dragaSpil();
        fxLeikmadurHendi.getChildren().add(nyttSpil(l));
        leikmadur.gefaSpil(l);
        fxSamtalsLeikamdur.setText(leikmadur.getSamtals() + "");
    }

    /**
     * Setur spil á hendi dealersins og prentar út samtölu hans.
     */
    private void gefaDSpil(){
        SpilV d = stokkur.dragaSpil();
        fxDealerHendi.getChildren().add(nyttSpil(d));
        dealer.gefaSpil(d);
        fxSamtalsDealer.setText(dealer.getSamtals() + "");
    }

    /**
     * Tjékkar hvort einhver er búinn að vinna
     *
     * @return skilar streng þess sem er búinn að vinna eða skilar engu ef
     * hvorugur hefur unnið
     */
    private String finnaSigurvegara (){
        if (leikmadur.hvorVann(dealer) == leikmadur){
            return "Til hamingju " + fxNafnLeikmadur.getText() + ", þú vannst og með samtölu: " + leikmadur.getSamtals();
        } else if (leikmadur.hvorVann(dealer) == dealer){
            return "Því miður vann dealerinn með samtölu: " + dealer.getSamtals();
        }
        return null;
    }

    /**
     * Tjékkar hvort dealerinn er búinn að vinna
     *
     * @return skilar streng hvort dealerinng eða leikmaðurinn hefur unnið
     */
    private String vinnurDealer(){
        if (leikmadur.vinnurDealer(dealer)){
            return "Því miður vann dealerinn með samtölu: " + dealer.getSamtals();
        } else {
            return "Til hamingju " + fxNafnLeikmadur.getText() + ", þú vannst og með samtölu: " + leikmadur.getSamtals();
        }
    }

    private boolean sprakk(){
        if (leikmadur.getSamtals() > 21 ||dealer.getSamtals() > 21 ){
            return true;
        }
        return false;
    }

    private String sprakkText(){
        if (leikmadur.getSamtals() > 21){
            return "Dealerinn vinnur, þú sprakkst: " + leikmadur.getSamtals();
        } else {
            return "Til hamingju " + fxNafnLeikmadur.getText() + " þú vanst, dealerinn sprakk: " +
                    dealer.getSamtals();
        }
    }
}