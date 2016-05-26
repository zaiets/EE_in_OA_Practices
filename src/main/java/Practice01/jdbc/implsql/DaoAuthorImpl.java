package Practice01.jdbc.implsql;

import Practice01.jdbc.ConnectionProvider;
import Practice01.jdbc.IDao;
import Practice01.model.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

import static Practice01.jdbc.sqls.AuthorsSQLs.*;

public class DaoAuthorImpl implements IDao<Author> {

    @Override
    public Author create(Author author) {
        try (PreparedStatement ps = ConnectionProvider.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, author.getFirstName());
            ps.setString(2, author.getLastName());
            ps.setDate(3, author.getBirthday());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                author.setId(rs.getInt(1));
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public Author read(int id) {
        Author author = null;
        try (PreparedStatement ps = ConnectionProvider.getConnection().prepareStatement(SQL_SELECT_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                author = new Author();
                author.setFirstName(rs.getString("FIRST_NAME"));
                author.setLastName(rs.getString("LAST_NAME"));
                author.setBirthday(rs.getDate("BIRTHDAY"));
                author.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public Set<Author> readAll() {
        Set<Author> authors = new LinkedHashSet<>();
        try (Statement st = ConnectionProvider.getConnection().createStatement()) {
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL);
            Author author;
            while (rs.next()) {
                author = new Author();
                author.setFirstName(rs.getString("FIRST_NAME"));
                author.setLastName(rs.getString("LAST_NAME"));
                author.setBirthday(rs.getDate("BIRTHDAY"));
                author.setId(rs.getInt("ID"));
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public Author update(Author author) {
        try (PreparedStatement ps = ConnectionProvider.getConnection().prepareStatement(SQL_UPDATE_BY_ID)) {
            ps.setString(1, author.getFirstName());
            ps.setString(2, author.getLastName());
            ps.setDate(3, author.getBirthday());
            if (ps.executeUpdate() == 1) return author;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Author delete(Author author) {
        try (PreparedStatement ps = ConnectionProvider.getConnection().prepareStatement(SQL_DELETE_BY_ID)) {
            ps.setInt(1, author.getId());
            if (ps.executeUpdate() == 1) return author;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<Author> selectByAgeDiap (int startAge, int endAge) {
        Set<Author> authors = new LinkedHashSet<>();
        try (PreparedStatement ps = ConnectionProvider.getConnection().prepareStatement(SQL_SELECT_BY_AGE_DIAP)) {
            ps.setInt(1, startAge);
            ps.setInt(2, endAge);
            ResultSet rs = ps.executeQuery();
            Author author;
            while (rs.next()) {
                author = new Author();
                author.setFirstName(rs.getString("FIRST_NAME"));
                author.setLastName(rs.getString("LAST_NAME"));
                author.setBirthday(rs.getDate("BIRTHDAY"));
                author.setId(rs.getInt("ID"));
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public Set<Author> selectByBirthdayIn (int[] ages) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(SQL_SELECT_BY_BIRTHDAY_IN);
        sqlBuilder.append('(');
        for (int i: ages) {
            sqlBuilder.append(ages[i]);
            sqlBuilder.append(", ");
        }
        sqlBuilder.append(ages[ages.length-1]);
        sqlBuilder.append(')');
        String sql = sqlBuilder.toString();

        Set<Author> authors = new LinkedHashSet<>();
        try (Statement st = ConnectionProvider.getConnection().createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            Author author;
            while (rs.next()) {
                author = new Author();
                author.setFirstName(rs.getString("FIRST_NAME"));
                author.setLastName(rs.getString("LAST_NAME"));
                author.setBirthday(rs.getDate("BIRTHDAY"));
                author.setId(rs.getInt("ID"));
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }
}
