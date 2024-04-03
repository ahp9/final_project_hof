package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Spil extends AnchorPane {

    /**
     * Smiður spilsins
     */
    public Spil(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("spil.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception){
            throw new RuntimeException(exception);
        }
    }


    /**
     * Tekur inn gildi og mynd af tegund spilsins og setur á Spilið
     * @param s spil sem var dregið og á að setja á hendi leikmans eða dealers
     */
    public void setSpil(Card s){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 5; j++){
                if (j > 0 && j < 3){
                    ((Label)getChildren().get(j)).setText(s.getValue() + "");
                } else if (j > 2){
                    ((ImageView)getChildren().get(j)).setImage(lesaMyndir(s));
                }
            }
        }
    }

    /**
     * Les inn mynd tegund spilsins
     * @param s Spilið sem dregið var úr stokk
     * @return skilar mynd af tegund spilsins
     */
    private Image lesaMyndir(Card s){
        String sort = "myndir/" + s.getSuit().name().toLowerCase() + ".png";
        return new Image(Spil.class.getResourceAsStream(sort));
    }
}
