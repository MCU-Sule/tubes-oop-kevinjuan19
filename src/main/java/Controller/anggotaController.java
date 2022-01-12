package Controller;

import DAO.AnggotaDAO;
import Model.AnggotaEntity;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class anggotaController {

    @FXML
    private TableView<AnggotaEntity> tableAnggota;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNamaAngota;

    @FXML
    private TableColumn<?, ?> colAlamat;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colUsername;

    private AnggotaDAO aDao = new AnggotaDAO();

    private ObservableList<AnggotaEntity> anggotaList = aDao.showData();


    @FXML
    private void initialize(){
        tableAnggota.setItems(anggotaList);
        colId.setCellValueFactory(new PropertyValueFactory<>("idAnggota"));
        colNamaAngota.setCellValueFactory(new PropertyValueFactory<>("namaAnggota"));
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamatAnggota"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("statusKeanggotaan"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
    }

}
