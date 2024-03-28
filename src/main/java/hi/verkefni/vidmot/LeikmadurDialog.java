package hi.verkefni.vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

public class LeikmadurDialog extends DialogPane {

    @FXML
    private TextField fxLeikmadur;

    /**
     * Smiður LeikmaðurDialog
     */
    public LeikmadurDialog(){
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
    public String[] hvadHeitaLeikmenn(){
        Dialog<ButtonType> d = new Dialog<>();
        d.setDialogPane(this);
        Optional<ButtonType> utkoma = d.showAndWait();
        if (utkoma.isPresent() && (utkoma.get().getButtonData() == ButtonBar.ButtonData.OK_DONE)){
            return new String[]{fxLeikmadur.getText()};
        } else {
            return null;
        }
    }


}
