package DAO;

import Model.PenerbitEntity;
import Utility.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class PenerbitDAO implements daoInterface<PenerbitEntity>{
    @Override
    public int addData(PenerbitEntity data) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.save(data);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int delData(PenerbitEntity data) {
        return 0;
    }

    @Override
    public int updateData(PenerbitEntity data) {
        return 0;
    }

    @Override
    public ObservableList<PenerbitEntity> showData() {
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(PenerbitEntity.class);

        query.from(PenerbitEntity.class);

        List<PenerbitEntity> clist = s.createQuery(query).getResultList();
        s.close();

        return FXCollections.observableArrayList(clist);
    }
}
