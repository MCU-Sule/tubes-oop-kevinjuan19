package DAO;

import Model.GenreEntity;
import Utility.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class GenreDAO implements daoInterface<GenreEntity>{
    @Override
    public int addData(GenreEntity data) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.save(data);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int delData(GenreEntity data) {
        return 0;
    }

    @Override
    public int updateData(GenreEntity data) {
        return 0;
    }

    @Override
    public ObservableList<GenreEntity> showData() {
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(GenreEntity.class);

        query.from(GenreEntity.class);

        List<GenreEntity> clist = s.createQuery(query).getResultList();
        s.close();

        return FXCollections.observableArrayList(clist);
    }
}
