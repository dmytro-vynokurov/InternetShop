package entities;

import javax.persistence.*;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/20/13
 * Time: 5:19 PM
 */
@Entity
@Table(name = "VIDEO", catalog = "", schema = "INTERNETSHOP")
@NamedQuery(name="findVideosOfItem",query = "SELECT v FROM Video v WHERE v.item=:item")
public final class Video {
    @Column(name = "ID_VIDEO", nullable = false, insertable = true, updatable = true, length = 6, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_Video")
    @SequenceGenerator(name = "S_Video", sequenceName = "S_VIDEO", allocationSize = 1)
    private int idVideo;
    @Column(name = "URL", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
    @Basic
    private String url;
    @ManyToOne
    @JoinColumn(name = "ID_ITEM", referencedColumnName = "ID_ITEM", nullable = false)
    private Item item;

    public int getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(int idVideo) {
        this.idVideo = idVideo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Video video = (Video) o;

        if (idVideo != video.idVideo) return false;
        if (url != null ? !url.equals(video.url) : video.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idVideo;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Video{" +
                "url='" + url + '\'' +
                ", idVideo=" + idVideo +
                '}';
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
