package Controller;

import DAO.AnggotaDAO;
import Model.AnggotaEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class registerController {

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtAlamat;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnExit;

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    void login(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = fxmlLoader.load();
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
        txtName.getScene().getWindow().hide();
        new_stage.initOwner(txtName.getScene().getWindow());
        new_stage.setScene(scene);
        new_stage.show();
    }

    @FXML
    void register(ActionEvent event) throws IOException {
        AnggotaEntity anggota =new AnggotaEntity();
        anggota.setNamaAnggota(txtName.getText());
        anggota.setAlamatAnggota(txtAlamat.getText());
        anggota.setUsername(txtUsername.getText());
        anggota.setPassword(txtPassword.getText());
        AnggotaDAO anggotaDAO = new AnggotaDAO();
        anggotaDAO.addData(anggota);
        txtPassword.clear();
        txtName.clear();
        txtUsername.clear();
        txtAlamat.clear();
        Stage new_stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

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
        txtAlamat.getScene().getWindow().hide();
        new_stage.initOwner(txtAlamat.getScene().getWindow());
        new_stage.setScene(scene);
        new_stage.show();

    }

    public void LoadView (Locale locale){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.class.getResource("register.fxml"));
        loader.setResources(ResourceBundle.getBundle("Bundle",locale));
        try {
            Parent root =loader.load();
            Stage s = (Stage) btnExit.getScene().getWindow();
            s.setScene(new Scene(root));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }


}
