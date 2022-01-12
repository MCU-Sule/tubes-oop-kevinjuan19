package DAO;

import Model.AnggotaEntity;
import Utility.HibernateUtil;
import Utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AnggotaDAO implements daoInterface<AnggotaEntity>{
    @Override
    public int addData(AnggotaEntity data) {
        int result = 0;
        try {
            String query = "INSERT INTO anggota (namaAnggota,alamatAnggota,username,password) VALUES (?,?,?,MD5(?))";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1,data.getNamaAnggota());
            ps.setString(2,data.getAlamatAnggota());
            ps.setString(3,data.getUsername());
            ps.setString(4,data.getPassword());
            result = ps.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return result;
    }

    @Override
    public int delData(AnggotaEntity data) {
        return 0;
    }

    @Override
    public int updateData(AnggotaEntity data) {
        return 0;
    }

    @Override
    public ObservableList<AnggotaEntity> showData() {
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(AnggotaEntity.class);

        query.from(AnggotaEntity.class);

        List<AnggotaEntity> clist = s.createQuery(query).getResultList();
        s.close();

        return FXCollections.observableArrayList(clist);
    }
    public AnggotaEntity login(AnggotaEntity data){
        AnggotaEntity u;
        try {
            Connection con;
            con= JDBCConnection.getConnection();
            String query="SELECT * FROM anggota WHERE username=? AND password=MD5(?)";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,data.getUsername());
            ps.setString(2,data.getPassword());
            ResultSet res = ps.executeQuery();
            res.next();
            if (res.getString("username")==null||res.getString("username")==""){
                u=null;
            }else{
                u=new AnggotaEntity(res.getInt("idAnggota"),res.getString("namaAnggota"),res.getString("username"),
                        res.getString("password"));
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            u=null;
        }
        return u;
    }
}
