package Controller;

import DAO.PenerbitDAO;
import Model.GenreEntity;
import Model.PenerbitEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class penerbitController {

    @FXML
    private TextField txtPenerbit;

    @FXML
    private Button btnAdd;

    @FXML
    private TableView<PenerbitEntity> tablePenerbit;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNamaPenerbit;

    private PenerbitDAO pDao = new  PenerbitDAO();
    private ObservableList<PenerbitEntity> penerbitList = pDao.showData();

    @FXML
    public void initialize(){
        tablePenerbit.setItems(penerbitList);
        colId.setCellValueFactory(new PropertyValueFactory<>("idpenerbit"));
        colNamaPenerbit.setCellValueFactory(new PropertyValueFactory<>("penerbit"));

    }

    @FXML
    void add(ActionEvent event) {
        String nama = txtPenerbit.getText();
        PenerbitEntity penerbit = new PenerbitEntity();
        penerbit.setPenerbit(nama);
        pDao.addData(penerbit);
        penerbitList.clear();
        penerbitList.addAll(pDao.showData());
        tablePenerbit.refresh();
        txtPenerbit.clear();
    }

}
