package Model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "buku", schema = "perpus", catalog = "")
public class BukuEntity {
    private int idBuku;
    private String namaBuku;
    private String pengarang;
    private PenerbitEntity penerbitByIdPenerbit;
    private GenreEntity genreByIdGenre;
    private Collection<DaftarpinjamanEntity> daftarpinjamenByIdBuku;

    @Override
    public String toString() {
        return namaBuku;
    }

    @Id
    @Column(name = "idBuku")
    public int getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(int idBuku) {
        this.idBuku = idBuku;
    }

    @Basic
    @Column(name = "namaBuku")
    public String getNamaBuku() {
        return namaBuku;
    }

    public void setNamaBuku(String namaBuku) {
        this.namaBuku = namaBuku;
    }

    @Basic
    @Column(name = "pengarang")
    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BukuEntity that = (BukuEntity) o;
        return idBuku == that.idBuku && Objects.equals(namaBuku, that.namaBuku) && Objects.equals(pengarang, that.pengarang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBuku, namaBuku, pengarang);
    }
    @ManyToOne
    @JoinColumn(name = "idPenerbit", referencedColumnName = "idpenerbit", nullable = false)
    public PenerbitEntity getPenerbitByIdPenerbit() {
        return penerbitByIdPenerbit;
    }

    public void setPenerbitByIdPenerbit(PenerbitEntity penerbitByIdPenerbit) {
        this.penerbitByIdPenerbit = penerbitByIdPenerbit;
    }

    @ManyToOne
    @JoinColumn(name = "idGenre", referencedColumnName = "idgenre", nullable = false)
    public GenreEntity getGenreByIdGenre() {
        return genreByIdGenre;
    }

    public void setGenreByIdGenre(GenreEntity genreByIdGenre) {
        this.genreByIdGenre = genreByIdGenre;
    }

//    @OneToMany(mappedBy = "bukuByIdbuku")
//    public Collection<DaftarpinjamanEntity> getDaftarpinjamenByIdBuku() {
//        return daftarpinjamenByIdBuku;
//    }
//
//    public void setDaftarpinjamenByIdBuku(Collection<DaftarpinjamanEntity> daftarpinjamenByIdBuku) {
//        this.daftarpinjamenByIdBuku = daftarpinjamenByIdBuku;
//    }
}
