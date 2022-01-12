package Model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "daftarpinjaman", schema = "perpus", catalog = "")
public class DaftarpinjamanEntity {
    private String kodePeminjaman;
    private Date tanggalPinjam;
    private Date tanggalKembali;
    private Date tanggalDikembalikan;
    private int denda;
    private AnggotaEntity anggotaByIdAnggota;
    private BukuEntity bukuByIdBuku;

    @Id
    @Column(name = "kodePeminjaman")
    public String getKodePeminjaman() {
        return kodePeminjaman;
    }

    public void setKodePeminjaman(String kodePeminjaman) {
        this.kodePeminjaman = kodePeminjaman;
    }

    @Basic
    @Column(name = "tanggalPinjam")
    public Date getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(Date tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    @Basic
    @Column(name = "tanggalKembali")
    public Date getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(Date tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    @Basic
    @Column(name = "tanggalDikembalikan")
    public Date getTanggalDikembalikan() {
        return tanggalDikembalikan;
    }

    public void setTanggalDikembalikan(Date tanggalDikembalikan) {
        this.tanggalDikembalikan = tanggalDikembalikan;
    }

    @Basic
    @Column(name = "denda")
    public int getDenda() {
        return denda;
    }

    public void setDenda(int denda) {
        this.denda = denda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DaftarpinjamanEntity that = (DaftarpinjamanEntity) o;
        return denda == that.denda && Objects.equals(kodePeminjaman, that.kodePeminjaman) && Objects.equals(tanggalPinjam, that.tanggalPinjam) && Objects.equals(tanggalKembali, that.tanggalKembali) && Objects.equals(tanggalDikembalikan, that.tanggalDikembalikan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kodePeminjaman, tanggalPinjam, tanggalKembali, tanggalDikembalikan, denda);
    }

    @ManyToOne
    @JoinColumn(name = "idAnggota", referencedColumnName = "idAnggota", nullable = false)
    public AnggotaEntity getAnggotaByIdAnggota() {
        return anggotaByIdAnggota;
    }

    public void setAnggotaByIdAnggota(AnggotaEntity anggotaByIdAnggota) {
        this.anggotaByIdAnggota = anggotaByIdAnggota;
    }

    @ManyToOne
    @JoinColumn(name = "idBuku", referencedColumnName = "idBuku", nullable = false)
    public BukuEntity getBukuByIdBuku() {
        return bukuByIdBuku;
    }

    public void setBukuByIdBuku(BukuEntity bukuByIdBuku) {
        this.bukuByIdBuku = bukuByIdBuku;
    }
}
