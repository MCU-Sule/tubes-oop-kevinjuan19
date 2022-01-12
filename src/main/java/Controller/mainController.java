package Controller;

import DAO.AnggotaDAO;
import Model.AnggotaEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class mainController implements Initializable {

    @FXML
    private Button btnHome;

    @FXML
    private Button btnAnggota;

    @FXML
    private Button btnPinjaman;

    @FXML
    private Button btnBuku;

    @FXML
    private Button btnGenre;

    @FXML
    private Button btnPenerbit;

    @FXML
    private Button btnBahasa;

    @FXML
    private Button btnKeluar;

    @FXML
    private Label labNama;

    @FXML
    private Label labHello;

    @FXML
    private StackPane contentArea;

    @FXML
    void keluar(ActionEvent event) {
        System.exit(0);
//        ((Stage)((Button)event.getSource()).getScene().getWindow()).setIconified(true);
    }

    private AnggotaDAO aDao = new AnggotaDAO();
    private ObservableList<AnggotaEntity> anggotaList = aDao.showData();

    public ObservableList<AnggotaEntity> getAnggotaList() {
        return anggotaList;
    }
    private AnggotaEntity anggotaEntity;
    private loginController cLogin;

    private String bahasa = "IN";
    private bahasaController bahasaController;
    private Locale l ;


    public void setBahasaController(bahasaController bahasaController){
        bahasaController = bahasaController;
        bahasa = bahasaController.getBahasa();
        l = new Locale(bahasa);
        labHello.setVisible(false);
        labNama.setVisible(false);
    }

    public void setCLogin(loginController cLogin) {
        this.cLogin = cLogin;
        anggotaEntity = cLogin.getAnggotaEntity();
        labNama.setText(cLogin.getAnggotaEntity().getNamaAnggota());

    }

    @FXML
    private anggotaController anggota;


    @FXML
    void toAnggota(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Locale locale = new Locale(bahasa);
        loader.setLocation(getClass().getResource("anggota.fxml"));
        loader.setResources(ResourceBundle.getBundle("Bundle",locale));
        Parent fxml = loader.load();
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    void toBahasa(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Locale locale = new Locale(bahasa);
        loader.setLocation(getClass().getResource("bahasa.fxml"));
        loader.setResources(ResourceBundle.getBundle("Bundle",locale));
        Parent fxml = loader.load();
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    void toBuku(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Locale locale = new Locale(bahasa);
        loader.setLocation(getClass().getResource("buku.fxml"));
        loader.setResources(ResourceBundle.getBundle("Bundle",locale));
        Parent fxml = loader.load();
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    void toGenre(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Locale locale = new Locale(bahasa);
        loader.setLocation(getClass().getResource("genre.fxml"));
        loader.setResources(ResourceBundle.getBundle("Bundle",locale));
        Parent fxml = loader.load();
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    void toHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Locale locale = new Locale(bahasa);
        loader.setLocation(getClass().getResource("home.fxml"));
        loader.setResources(ResourceBundle.getBundle("Bundle",locale));
        Parent fxml = loader.load();
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    void toPenerbit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Locale locale = new Locale(bahasa);
        loader.setLocation(getClass().getResource("penerbit.fxml"));
        loader.setResources(ResourceBundle.getBundle("Bundle",locale));
        Parent fxml = loader.load();
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    void toPinjaman(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Locale locale = new Locale(bahasa);
        loader.setLocation(getClass().getResource("pinjaman.fxml"));
        loader.setResources(ResourceBundle.getBundle("Bundle",locale));
        Parent fxml = loader.load();
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            try {
                FXMLLoader loader = new FXMLLoader();
                Locale locale = new Locale(bahasa);
                loader.setLocation(getClass().getResource("home.fxml"));
                loader.setResources(ResourceBundle.getBundle("Bundle", locale));
                Parent fxml = loader.load();
                contentArea.getChildren().removeAll();
                contentArea.getChildren().setAll(fxml);

                } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }


//    public void setController (bahasaController bahasa){
//
//    }
}
