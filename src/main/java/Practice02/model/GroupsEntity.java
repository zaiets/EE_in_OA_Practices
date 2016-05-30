package Practice02.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Levsha on 30.05.2016.
 */
@Entity
@Table(name = "groups", schema = "practice2", catalog = "")
public class GroupsEntity {
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "GROUP_TITLE")
    private String groupTitle;
    @OneToMany(mappedBy = "group")
    private Set<AuthorsEntity> authors;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
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

        GroupsEntity that = (GroupsEntity) o;

        if (getId() != that.getId()) return false;
        if (getGroupTitle() != null ? !getGroupTitle().equals(that.getGroupTitle()) : that.getGroupTitle() != null)
            return false;
        return getAuthors() != null ? getAuthors().equals(that.getAuthors()) : that.getAuthors() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getGroupTitle() != null ? getGroupTitle().hashCode() : 0);
        result = 31 * result + (getAuthors() != null ? getAuthors().hashCode() : 0);
        return result;
    }
}
