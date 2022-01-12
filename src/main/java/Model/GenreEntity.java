package Model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "genre", schema = "perpus", catalog = "")
public class GenreEntity {
    private int idgenre;
    private String namaGenre;
    private Collection<BukuEntity> bukusByIdgenre;

    @Override
    public String toString() {
        return namaGenre;
    }

    @Id
    @Column(name = "idgenre")
    public int getIdgenre() {
        return idgenre;
    }

    public void setIdgenre(int idgenre) {
        this.idgenre = idgenre;
    }

    @Basic
    @Column(name = "namaGenre")
    public String getNamaGenre() {
        return namaGenre;
    }

    public void setNamaGenre(String namaGenre) {
        this.namaGenre = namaGenre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreEntity that = (GenreEntity) o;
        return idgenre == that.idgenre && Objects.equals(namaGenre, that.namaGenre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idgenre, namaGenre);
    }

    @OneToMany(mappedBy = "genreByIdGenre")
    public Collection<BukuEntity> getBukusByIdgenre() {
        return bukusByIdgenre;
    }

    public void setBukusByIdgenre(Collection<BukuEntity> bukusByIdgenre) {
        this.bukusByIdgenre = bukusByIdgenre;
    }

}
