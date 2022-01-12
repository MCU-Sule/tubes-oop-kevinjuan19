package Model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "penerbit", schema = "perpus", catalog = "")
public class PenerbitEntity {
    private int idpenerbit;
    private String penerbit;
    private Collection<BukuEntity> bukusByIdpenerbit;

    @Override
    public String toString() {
        return penerbit;
    }

    @Id
    @Column(name = "idpenerbit")
    public int getIdpenerbit() {
        return idpenerbit;
    }

    public void setIdpenerbit(int idpenerbit) {
        this.idpenerbit = idpenerbit;
    }

    @Basic
    @Column(name = "penerbit")
    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PenerbitEntity that = (PenerbitEntity) o;
        return idpenerbit == that.idpenerbit && Objects.equals(penerbit, that.penerbit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idpenerbit, penerbit);
    }

    @OneToMany(mappedBy = "penerbitByIdPenerbit")
    public Collection<BukuEntity> getBukusByIdpenerbit() {
        return bukusByIdpenerbit;
    }

    public void setBukusByIdpenerbit(Collection<BukuEntity> bukusByIdpenerbit) {
        this.bukusByIdpenerbit = bukusByIdpenerbit;
    }
}
