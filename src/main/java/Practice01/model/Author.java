package Practice01.model;

import java.sql.Date;

public class Author {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthday;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (getId() != author.getId()) return false;
        if (getFirstName() != null ? !getFirstName().equals(author.getFirstName()) : author.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(author.getLastName()) : author.getLastName() != null)
            return false;
        return getBirthday() != null ? getBirthday().equals(author.getBirthday()) : author.getBirthday() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getBirthday() != null ? getBirthday().hashCode() : 0);
        return result;
    }
}
