package Practice02.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by Levsha on 30.05.2016.
 */
@Entity
@Table(name = "authors", schema = "practice2")
public class AuthorsEntity {
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Basic
    @Column(name = "LAST_NAME")
    private String lastName;
    @Basic
    @Column(name = "BIRTHDAY")
    private Date birthday;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupsEntity group;
    @ManyToMany
    @JoinTable(name = "authors_audios",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "audio_id"))
    private Set<AudiosEntity> audios;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public GroupsEntity getGroup() {
        return group;
    }

    public void setGroup(GroupsEntity group) {
        this.group = group;
    }

    public Set<AudiosEntity> getAudios() {
        return audios;
    }

    public void setAudios(Set<AudiosEntity> audios) {
        this.audios = audios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorsEntity that = (AuthorsEntity) o;

        if (getId() != that.getId()) return false;
        if (getFirstName() != null ? !getFirstName().equals(that.getFirstName()) : that.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(that.getLastName()) : that.getLastName() != null)
            return false;
        if (getBirthday() != null ? !getBirthday().equals(that.getBirthday()) : that.getBirthday() != null)
            return false;
        return getGroup() != null ? getGroup().equals(that.getGroup()) : that.getGroup() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getBirthday() != null ? getBirthday().hashCode() : 0);
        result = 31 * result + (getGroup() != null ? getGroup().hashCode() : 0);
        return result;
    }
}
