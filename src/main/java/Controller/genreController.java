package Controller;

import DAO.GenreDAO;
import Model.GenreEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class genreController {

    @FXML
    private TextField txtNamaGenre;

    @FXML
    private Button btnAdd;

    @FXML
    private TableView<GenreEntity> tableGenre;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNamaGenre;

    GenreDAO gDao = new GenreDAO();
    ObservableList<GenreEntity> genreList = gDao.showData();

    @FXML
    private void initialize(){
        tableGenre.setItems(genreList);
        colId.setCellValueFactory(new PropertyValueFactory<>("idgenre"));
        colNamaGenre.setCellValueFactory(new PropertyValueFactory<>("namaGenre"));

    }

    @FXML
    void add(ActionEvent event) {
        String nama = txtNamaGenre.getText();
        GenreEntity genre = new GenreEntity();
        genre.setNamaGenre(nama);
        gDao.addData(genre);
        genreList.clear();
        genreList.addAll(gDao.showData());
        tableGenre.refresh();
        txtNamaGenre.clear();
    }

}
