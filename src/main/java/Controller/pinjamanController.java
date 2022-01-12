package Controller;

import DAO.AnggotaDAO;
import DAO.BukuDAO;
import DAO.PinjamanDAO;
import Model.AnggotaEntity;
import Model.BukuEntity;
import Model.DaftarpinjamanEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.time.LocalDate;

public class pinjamanController {

    @FXML
    private TextField txtKodePeminjaman;

    @FXML
    private DatePicker datePinjam;

    @FXML
    private DatePicker dateKembali;

    @FXML
    private DatePicker dateDikembalikan;


    @FXML
    private ComboBox<AnggotaEntity> cbAnggota;

    @FXML
    private ComboBox<BukuEntity> cbBuku;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnKembalikan;

    @FXML
    private Button btnDelete;

    @FXML
    private TableView<DaftarpinjamanEntity> tablePinjaman;

    @FXML
    private TableColumn<?, ?> colKodePeminjaman;

    @FXML
    private TableColumn<?, ?> colUsername;

    @FXML
    private TableColumn<?, ?> colBuku;

    @FXML
    private TableColumn<?, ?> colTglPinjam;

    @FXML
    private TableColumn<?, ?> colTglKembali;

    @FXML
    private TableColumn<?, ?> colTglDikembalikan;

    @FXML
    private TableColumn<?, ?> colDenda;

    private DaftarpinjamanEntity selected;

    private AnggotaDAO aDao = new AnggotaDAO();
    private ObservableList<AnggotaEntity> anggotaList = aDao.showData();

    private BukuDAO bDao = new BukuDAO();
    private ObservableList<BukuEntity> bukuList = bDao.showData();

    private PinjamanDAO pDao = new PinjamanDAO();
    private ObservableList<DaftarpinjamanEntity> pinjamanList = pDao.showData();

    @FXML
    private void initialize(){
        cbAnggota.setItems(anggotaList);
        cbBuku.setItems(bukuList);
        tablePinjaman.setItems(pinjamanList);
        colKodePeminjaman.setCellValueFactory(new PropertyValueFactory<>("kodePeminjaman"));
        colTglPinjam.setCellValueFactory(new PropertyValueFactory<>("tanggalPinjam"));
        colTglKembali.setCellValueFactory(new PropertyValueFactory<>("tanggalKembali"));
        colTglDikembalikan.setCellValueFactory(new PropertyValueFactory<>("tanggalDikembalikan"));
        colDenda.setCellValueFactory(new PropertyValueFactory<>("denda"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("anggotaByIdAnggota"));
        colBuku.setCellValueFactory(new PropertyValueFactory<>("bukuByIdBuku"));
        tablePinjaman.setOnMouseClicked(e-> tableUpdate());
        dateDikembalikan.setDisable(true);
    }

    public void tableUpdate(){
        selected = tablePinjaman.getSelectionModel().getSelectedItem();
        if (selected != null){
            txtKodePeminjaman.setDisable(true);
            datePinjam.setDisable(true);
            dateKembali.setDisable(true);
            cbBuku.setDisable(true);
            cbAnggota.setDisable(true);
            btnAdd.setDisable(true);
            btnKembalikan.setDisable(false);
            btnDelete.setDisable(false);
            dateDikembalikan.setDisable(false);
            txtKodePeminjaman.setText(selected.getKodePeminjaman());
            txtKodePeminjaman.setDisable(true);
            datePinjam.setValue(LocalDate.parse(String.valueOf(selected.getTanggalPinjam())));
            dateKembali.setValue(LocalDate.parse(String.valueOf(selected.getTanggalKembali())));
            if(selected.getTanggalDikembalikan() != null){
                dateDikembalikan.setValue(LocalDate.parse(String.valueOf(selected.getTanggalDikembalikan())));
            }else{
                dateDikembalikan.setValue(null);
            }
            cbAnggota.setValue(selected.getAnggotaByIdAnggota());
            cbBuku.setValue(selected.getBukuByIdBuku());
        }
    }

    public void clearAll(){
        txtKodePeminjaman.clear();
        datePinjam.setValue(null);
        dateKembali.setValue(null);
        dateDikembalikan.setValue(null);
        cbBuku.setValue(null);
        cbAnggota.setValue(null);
        btnAdd.setDisable(false);
        btnKembalikan.setDisable(true);
        btnDelete.setDisable(true);
        txtKodePeminjaman.setDisable(false);
        dateKembali.setDisable(false);
        datePinjam.setDisable(false);
        cbBuku.setDisable(false);
        cbAnggota.setDisable(false);
        dateDikembalikan.setDisable(true);

    }
    @FXML
    void add(ActionEvent event) {
        DaftarpinjamanEntity pinjaman = new DaftarpinjamanEntity();
        String kode = txtKodePeminjaman.getText();
        Date pinjam = Date.valueOf(datePinjam.getValue());
        Date kembali = Date.valueOf(dateKembali.getValue());
//        Date dikembalikan = Date.valueOf(datePinjam.getValue());
        int denda = 0;
        pinjaman.setKodePeminjaman(kode);
        pinjaman.setTanggalPinjam(pinjam);
        pinjaman.setTanggalKembali(kembali);
        pinjaman.setDenda(denda);
        pinjaman.setAnggotaByIdAnggota(cbAnggota.getValue());
        pinjaman.setBukuByIdBuku(cbBuku.getValue());
        pDao.addData(pinjaman);
        pinjamanList.clear();
        pinjamanList.addAll(pDao.showData());
        tablePinjaman.refresh();
        clearAll();
    }

    @FXML
    void delete(ActionEvent event) {
        if(selected!=null){
            pDao.delData(selected);
            pinjamanList.clear();
            pinjamanList.addAll(pDao.showData());
            tablePinjaman.refresh();
            clearAll();
        }
    }

    @FXML
    void edit(ActionEvent event) {
        if(selected!=null){
            selected.setTanggalDikembalikan(Date.valueOf(dateDikembalikan.getValue()));
            Date kembali = selected.getTanggalKembali();
            Date dikembalikan = selected.getTanggalDikembalikan();
            long pengurangan = dikembalikan.getTime() - kembali.getTime();
            long perbedaanHari = (pengurangan/(1000*60*60*24)) % 365;
            int denda = (int)(perbedaanHari*1000);
            if (denda<0){
                denda = 0;
            }
            selected.setDenda(denda);
            pDao.updateData(selected);
            pinjamanList.clear();
            pinjamanList.addAll(pDao.showData());
            tablePinjaman.refresh();
            clearAll();
        }
    }

}
