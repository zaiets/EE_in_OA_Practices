package Practice02.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Levsha on 30.05.2016.
 */
@Entity
@Table(name = "genres", schema = "practice2")
public class GenresEntity {
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "GENRE_TITLE")
    private String genreTitle;
    @OneToMany(mappedBy="genre")
    private Set<AlbumsEntity> albums;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenreTitle() {
        return genreTitle;
    }

    public void setGenreTitle(String genreTitle) {
        this.genreTitle = genreTitle;
    }

    public Set<AlbumsEntity> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<AlbumsEntity> albums) {
        this.albums = albums;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenresEntity that = (GenresEntity) o;

        if (getId() != that.getId()) return false;
        if (getGenreTitle() != null ? !getGenreTitle().equals(that.getGenreTitle()) : that.getGenreTitle() != null)
            return false;
        return getAlbums() != null ? getAlbums().equals(that.getAlbums()) : that.getAlbums() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getGenreTitle() != null ? getGenreTitle().hashCode() : 0);
        result = 31 * result + (getAlbums() != null ? getAlbums().hashCode() : 0);
        return result;
    }
}
