package Controller;

import DAO.BukuDAO;
import DAO.GenreDAO;
import DAO.PenerbitDAO;
import Model.BukuEntity;
import Model.GenreEntity;
import Model.PenerbitEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class bukuController {

    @FXML
    private TextField txtId;

    @FXML
    private ComboBox<GenreEntity> cbGenre;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private ComboBox<PenerbitEntity> cbPenerbit;

    @FXML
    private TextField txtNamaBuku;

    @FXML
    private TextField txtPengarang;

    @FXML
    private TableView<BukuEntity> tableBuku;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNamaBuku;

    @FXML
    private TableColumn<?, ?> colPengarang;

    @FXML
    private TableColumn<?, ?> colPenerbit;

    @FXML
    private TableColumn<?, ?> colGenre;


    private BukuEntity selected;

    private GenreDAO gDao = new GenreDAO();
    private ObservableList<GenreEntity> genreList = gDao.showData();

    private PenerbitDAO pDao = new PenerbitDAO();
    private ObservableList<PenerbitEntity> penerbitList = pDao.showData();

    private BukuDAO bDao = new BukuDAO();
    private ObservableList<BukuEntity> bukuList = bDao.showData();

    @FXML
    private void initialize(){
        cbGenre.setItems(genreList);
        cbPenerbit.setItems(penerbitList);
        tableBuku.setItems(bukuList);
        colId.setCellValueFactory(new PropertyValueFactory<>("idBuku"));
        colNamaBuku.setCellValueFactory(new PropertyValueFactory<>("namaBuku"));
        colPengarang.setCellValueFactory(new PropertyValueFactory<>("pengarang"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("penerbitByIdPenerbit"));
        colPenerbit.setCellValueFactory(new PropertyValueFactory<>("genreByIdGenre"));
        tableBuku.setOnMouseClicked(e -> tableUpdate());
        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
    }
    private void tableUpdate(){
        selected = tableBuku.getSelectionModel().getSelectedItem();
        if (selected != null){
            btnAdd.setDisable(true);
            btnEdit.setDisable(false);
            btnDelete.setDisable(false);
            txtId.setText(String.valueOf(selected.getIdBuku()));
            txtId.setDisable(true);

            txtNamaBuku.setText(selected.getNamaBuku());
            txtPengarang.setText(String.valueOf(selected.getPengarang()));
            cbGenre.setValue(selected.getGenreByIdGenre());
            cbPenerbit.setValue(selected.getPenerbitByIdPenerbit());
        }

    }
    public void clearAll(){
        txtId.clear();
        txtPengarang.clear();
        txtNamaBuku.clear();
        cbPenerbit.setValue(null);
        cbGenre.setValue(null);
        btnAdd.setDisable(false);
        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        txtId.setDisable(false);
    }



    @FXML
    void add(ActionEvent event) {
        BukuEntity buku = new BukuEntity();
        int id = Integer.parseInt(txtId.getText());
        String nama = txtNamaBuku.getText();
        String pengarang = txtPengarang.getText();
        buku.setIdBuku(id);
        buku.setNamaBuku(nama);
        buku.setPengarang(pengarang);
        buku.setGenreByIdGenre(cbGenre.getValue());
        buku.setPenerbitByIdPenerbit(cbPenerbit.getValue());
        bDao.addData(buku);
        bukuList.clear();
        bukuList.addAll(bDao.showData());
        tableBuku.refresh();
        clearAll();
    }

    @FXML
    void delete(ActionEvent event) {
        if(selected!= null){
            bDao.delData(selected);
            bukuList.clear();
            bukuList.addAll(bDao.showData());
            tableBuku.refresh();
            clearAll();
            txtId.clear();

        }
    }

    @FXML
    void edit(ActionEvent event) {
        if(selected != null){
            int id = Integer.parseInt(txtId.getText());
            String nama = txtNamaBuku.getText();
            String pengarang = txtPengarang.getText();
            selected.setIdBuku(id);
            selected.setNamaBuku(nama);
            selected.setPengarang(pengarang);
            selected.setGenreByIdGenre(cbGenre.getValue());
            selected.setPenerbitByIdPenerbit(cbPenerbit.getValue());
            bDao.updateData(selected);
            bukuList.clear();
            bukuList.addAll(bDao.showData());
            tableBuku.refresh();
            clearAll();
        }
    }

}
