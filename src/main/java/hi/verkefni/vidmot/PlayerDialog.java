package hi.verkefni.vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

public class PlayerDialog extends DialogPane {

    @FXML
    private TextField fxLeikmadur;

    /**
     * Smiður LeikmaðurDialog
     */
    public PlayerDialog(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("leikmadur-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception){
            throw new RuntimeException(exception);
        }
    }

    /**
     * Aðferð sem birtir dialog sem biður um nafn leikmanns
     * @return nafn leikmanns
     */
    public String playersName(){
        Dialog<ButtonType> d = new Dialog<>();
        d.setDialogPane(this);
        Optional<ButtonType> result = d.showAndWait();
        if (result.isPresent() && (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE)){
            return fxLeikmadur.getText();
        } else {
            return null;
        }
    }


}
