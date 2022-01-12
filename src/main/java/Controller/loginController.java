package Controller;

import DAO.AnggotaDAO;
import Model.AnggotaEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class loginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnExit;

    private AnggotaEntity anggota = new AnggotaEntity();
    private AnggotaDAO aDao = new AnggotaDAO();

    public AnggotaEntity getAnggotaEntity() {
        return anggota;
    }

    private String bahasa = "IN";
    private bahasaController bahasaController;
    private Locale l;

    public void setBahasaController(bahasaController bahasaController){
        bahasaController = bahasaController;
        bahasa = bahasaController.getBahasa();
        l = new Locale(bahasa);
    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    double xOffset = 0;
    double yOffset = 0;
    @FXML
    void login(ActionEvent event) throws IOException {
        if(txtUsername.getText().equals("")||txtPassword.getText().equals("")){
            showAlert("Mising Username or Password Field");
        }else{
            anggota.setUsername(txtUsername.getText());
            anggota.setPassword(txtPassword.getText());
            AnggotaEntity newAnggota;
            newAnggota = aDao.login(anggota);

            if (newAnggota != null){
                anggota.setIdAnggota(newAnggota.getIdAnggota());
                anggota.setNamaAnggota(newAnggota.getNamaAnggota());
                anggota.setAlamatAnggota(newAnggota.getAlamatAnggota());
//                FXMLLoader loader = new FXMLLoader();
                Locale locale = new Locale(bahasa);
//                loader.setLocation(getClass().getResource("penerbit.fxml"));
//                loader.setResources(ResourceBundle.getBundle("Bundle",locale));
//                Parent fxml = loader.load();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
                fxmlLoader.setResources(ResourceBundle.getBundle("Bundle",locale));
                Parent root = fxmlLoader.load();
                mainController c = fxmlLoader.getController();
                c.setCLogin(this);

                Scene scene = new Scene(root);

                Stage new_stage = new Stage();
                root.setOnMousePressed(e->{
                    xOffset = e.getSceneX();
                    yOffset = e.getSceneY();
                });

                root.setOnMouseDragged(e -> {
                    new_stage.setX(e.getScreenX() - xOffset);
                    new_stage.setY(e.getScreenY() - yOffset);
                });


                new_stage.initModality(Modality.WINDOW_MODAL);
                new_stage.initStyle(StageStyle.TRANSPARENT);
                txtUsername.getScene().getWindow().hide();
                new_stage.initOwner(txtUsername.getScene().getWindow());
                new_stage.setScene(scene);
                new_stage.show();

            }
        }
    }

    @FXML
    void register(ActionEvent event) throws IOException {
        Stage new_stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
        Parent root = fxmlLoader.load();
        Scene new_scene = new Scene(root);

        root.setOnMousePressed(e->{
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });

        root.setOnMouseDragged(e -> {
            new_stage.setX(e.getScreenX() - xOffset);
            new_stage.setY(e.getScreenY() - yOffset);
        });


        new_stage.setScene(new_scene);
        new_stage.initModality(Modality.WINDOW_MODAL);
        new_stage.initStyle(StageStyle.TRANSPARENT);
        txtUsername.getScene().getWindow().hide();
        new_stage.initOwner(txtUsername.getScene().getWindow());
        new_stage.setScene(new_scene);
        new_stage.show();
    }

    public void showAlert(String kalimat){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Information");
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.show();
        alert.setContentText(kalimat);
    }

}
