package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class bahasaController {

    @FXML
    private Button btnGanti;
    @FXML
    private ComboBox<String> cbBahasa;

    private ObservableList<String> list = FXCollections.observableArrayList();
    private mainController mc;
    private bahasaController bc;
    private String bahasa;

    public String getBahasa(){
        return bahasa;
    }

    @FXML
    public void initialize(){
        list.add("Indonesia");
        list.add("English");
        cbBahasa.setItems(list);
    }


    public void LoadView (Locale locale){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.class.getResource("main.fxml"));
        loader.setResources(ResourceBundle.getBundle("Bundle",locale));
        try {
            Parent root =loader.load();
            Stage s = (Stage) btnGanti.getScene().getWindow();
            s.setScene(new Scene(root));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void pilihBahasa(ActionEvent actionEvent) {
        if (cbBahasa.getSelectionModel().getSelectedItem().equals("Indonesia")){
            bahasa = "IN";
        }else{
            bahasa = "EN";
        }
        FXMLLoader fxmlLoader = new FXMLLoader();
        Locale locale = new Locale(bahasa);
        fxmlLoader.setLocation(getClass().getResource("main.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundle", locale));
        try {
            Parent root = fxmlLoader.load();
            mainController c = fxmlLoader.getController();
            c.setBahasaController(this);
            Stage s = (Stage) cbBahasa.getScene().getWindow();
            s.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void setBahasa(ActionEvent actionEvent) {
//        if (cbBahasa.getSelectionModel().getSelectedItem().equals("Indonesia")){
//            bahasa = "IN";
//        }else{
//            bahasa = "EN";
//        }
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        Locale locale = new Locale(bahasa);
//        fxmlLoader.setLocation(getClass().getResource("main.fxml"));
//        fxmlLoader.setResources(ResourceBundle.getBundle("bundle", locale));
//        try {
//            Parent root = fxmlLoader.load();
//            mainController c = fxmlLoader.getController();
//            c.setBahasaController(this);
//            Stage s = (Stage) cbBahasa.getScene().getWindow();
//            s.setScene(new Scene(root));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }



    public void setController(mainController mc){
        cbBahasa.getSelectionModel().getSelectedItem();
        this.mc = mc;
    }

}
