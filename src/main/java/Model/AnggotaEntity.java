package Model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "anggota", schema = "perpus", catalog = "")
public class AnggotaEntity {
    private int idAnggota;
    private String namaAnggota;
    private String alamatAnggota;
    private String statusKeanggotaan;
    private String username;
    private String password;
    private Collection<DaftarpinjamanEntity> daftarpinjamenByIdAnggota;

    public AnggotaEntity(){
    }

    public AnggotaEntity(int id, String name, String username, String password) {
        this.idAnggota = id;
        this.namaAnggota = name;
        this.username = username;
        this.password = password;
    }
    @Override
    public String toString() {
        return namaAnggota;
    }

    @Id
    @Column(name = "idAnggota")
    public int getIdAnggota() {
        return idAnggota;
    }

    public void setIdAnggota(int idAnggota) {
        this.idAnggota = idAnggota;
    }

    @Basic
    @Column(name = "namaAnggota")
    public String getNamaAnggota() {
        return namaAnggota;
    }

    public void setNamaAnggota(String namaAnggota) {
        this.namaAnggota = namaAnggota;
    }

    @Basic
    @Column(name = "alamatAnggota")
    public String getAlamatAnggota() {
        return alamatAnggota;
    }

    public void setAlamatAnggota(String alamatAnggota) {
        this.alamatAnggota = alamatAnggota;
    }

    @Basic
    @Column(name = "statusKeanggotaan")
    public String getStatusKeanggotaan() {
        return statusKeanggotaan;
    }

    public void setStatusKeanggotaan(String statusKeanggotaan) {
        this.statusKeanggotaan = statusKeanggotaan;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnggotaEntity that = (AnggotaEntity) o;
        return idAnggota == that.idAnggota && Objects.equals(namaAnggota, that.namaAnggota) && Objects.equals(alamatAnggota, that.alamatAnggota) && Objects.equals(statusKeanggotaan, that.statusKeanggotaan) && Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnggota, namaAnggota, alamatAnggota, statusKeanggotaan, username, password);
    }
    @OneToMany(mappedBy = "anggotaByIdAnggota")
    public Collection<DaftarpinjamanEntity> getDaftarpinjamenByIdAnggota() {
        return daftarpinjamenByIdAnggota;
    }

    public void setDaftarpinjamenByIdAnggota(Collection<DaftarpinjamanEntity> daftarpinjamenByIdAnggota) {
        this.daftarpinjamenByIdAnggota = daftarpinjamenByIdAnggota;
    }
}
