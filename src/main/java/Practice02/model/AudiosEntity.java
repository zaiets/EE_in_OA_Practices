package Practice02.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Levsha on 30.05.2016.
 */
@Entity
@Table(name = "audios", schema = "practice2")
public class AudiosEntity {
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "TITLE")
    private String title;
    @Basic
    @Column(name = "DURATION")
    private Integer duration;
    @Basic
    @Column(name = "YEAR")
    private Integer year;
    @ManyToOne
    @JoinColumn(name = "ALBUM_ID")
    private AlbumsEntity album;
    @ManyToMany
    @JoinTable(name = "authors_audios",
            joinColumns = @JoinColumn(name = "audio_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<AuthorsEntity> authors;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public AlbumsEntity getAlbum() {
        return album;
    }

    public void setAlbum(AlbumsEntity album) {
        this.album = album;
    }

    public Set<AuthorsEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorsEntity> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AudiosEntity that = (AudiosEntity) o;

        if (getId() != that.getId()) return false;
        if (getTitle() != null ? !getTitle().equals(that.getTitle()) : that.getTitle() != null) return false;
        if (getDuration() != null ? !getDuration().equals(that.getDuration()) : that.getDuration() != null)
            return false;
        if (getYear() != null ? !getYear().equals(that.getYear()) : that.getYear() != null) return false;
        return getAlbum() != null ? getAlbum().equals(that.getAlbum()) : that.getAlbum() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getDuration() != null ? getDuration().hashCode() : 0);
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        result = 31 * result + (getAlbum() != null ? getAlbum().hashCode() : 0);
        return result;
    }
}
