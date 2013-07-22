package entities;

import javax.persistence.*;
import java.sql.Blob;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/20/13
 * Time: 5:19 PM
 */
@Entity
@Table(name = "PHOTO", catalog = "", schema = "INTERNETSHOP")
public final class Photo {
    @Column(name = "ID_PHOTO", nullable = false, insertable = true, updatable = true, length = 6, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_Photo")
    @SequenceGenerator(name = "S_Photo", sequenceName = "S_PHOTO", allocationSize = 1)
    private int idPhoto;
    @Column(name = "IMAGE", nullable = false, insertable = true, updatable = true, length = 4000, precision = 0)
    @Lob
    @Basic(fetch = FetchType.EAGER)
    private Blob image;
    @ManyToOne
    @JoinColumn(name = "ID_ITEM", referencedColumnName = "ID_ITEM", nullable = false)
    private Item item;

    public int getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(int idPhoto) {
        this.idPhoto = idPhoto;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (idPhoto != photo.idPhoto) return false;
        if (image != null ? !image.equals(photo.image) : photo.image != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPhoto;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "idPhoto=" + idPhoto +
                ", image=" + image +
                '}';
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
