package Practice02.model;

import javax.persistence.*;

/**
 * Created by Levsha on 30.05.2016.
 */
@Entity
@Table(name = "albums", schema = "practice2")
public class AlbumsEntity {
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "ALBUM_TITLE")
    private String albumTitle;
    @ManyToOne
    @JoinColumn(name = "genres_id")
    private GenresEntity genre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public GenresEntity getGenre() {
        return genre;
    }

    public void setGenre(GenresEntity genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlbumsEntity that = (AlbumsEntity) o;

        if (getId() != that.getId()) return false;
        if (getAlbumTitle() != null ? !getAlbumTitle().equals(that.getAlbumTitle()) : that.getAlbumTitle() != null)
            return false;
        return getGenre() != null ? getGenre().equals(that.getGenre()) : that.getGenre() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getAlbumTitle() != null ? getAlbumTitle().hashCode() : 0);
        result = 31 * result + (getGenre() != null ? getGenre().hashCode() : 0);
        return result;
    }
}
