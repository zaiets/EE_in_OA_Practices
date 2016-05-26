package Practice01.jdbc.daoImplSql;

import Practice01.jdbc.ConnectionProvider;
import Practice01.jdbc.IDao;
import Practice01.model.Audio;
import Practice01.model.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static Practice01.jdbc.daoImplSql.sqls.AudiosSQLs.*;

public class DaoAudioImpl implements IDao<Audio> {

    @Override
    public Audio create(Audio audio) {
        try (PreparedStatement ps = ConnectionProvider.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, audio.getTitle());
            ps.setInt(2, audio.getDuration());
            ps.setInt(3, audio.getYear());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                audio.setId(rs.getInt(1));
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return audio;
    }

    @Override
    public Audio read(int id) {
        Audio audio = null;
        try (PreparedStatement ps = ConnectionProvider.getConnection().prepareStatement(SQL_SELECT_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                audio = new Audio();
                audio.setTitle(rs.getString("TITLE"));
                audio.setDuration(rs.getInt("DURATION"));
                audio.setYear(rs.getInt("YEAR"));
                audio.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return audio;
    }

    @Override
    public Set<Audio> readAll() {
        Set<Audio> audios = new HashSet<>();
        try (Statement st = ConnectionProvider.getConnection().createStatement()) {
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL);
            Audio audio;
            while (rs.next()) {
                audio = new Audio();
                audio.setTitle(rs.getString("TITLE"));
                audio.setDuration(rs.getInt("DURATION"));
                audio.setYear(rs.getInt("YEAR"));
                audio.setId(rs.getInt("ID"));
                audios.add(audio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return audios;
    }

    @Override
    public Audio update(Audio audio) {
        try (PreparedStatement ps = ConnectionProvider.getConnection().prepareStatement(SQL_UPDATE_BY_ID)) {
            ps.setString(1, audio.getTitle());
            ps.setInt(2, audio.getDuration());
            ps.setInt(3, audio.getYear());
            if (ps.executeUpdate() == 1) return audio;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Audio delete(Audio audio) {
        try (PreparedStatement ps = ConnectionProvider.getConnection().prepareStatement(SQL_DELETE_BY_ID)) {
            ps.setInt(1, audio.getId());
            if (ps.executeUpdate() == 1) return audio;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<Audio> readByAuthor(Author author) {
        Set<Audio> audios = new HashSet<>();
        try (PreparedStatement ps = ConnectionProvider.getConnection().prepareStatement(SQL_SELECT_BY_AUTHOR)) {
            ps.setInt(1, author.getId());
            ResultSet rs = ps.executeQuery();
            Audio audio;
            while (rs.next()) {
                audio = new Audio();
                audio.setTitle(rs.getString("TITLE"));
                audio.setDuration(rs.getInt("DURATION"));
                audio.setYear(rs.getInt("YEAR"));
                audio.setId(rs.getInt("ID"));
                audios.add(audio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return audios;
    }

    public Set<Audio> readByAuthorAndYear(Author author, int year) {
        Set<Audio> audios = new HashSet<>();
        try (PreparedStatement ps = ConnectionProvider.getConnection().prepareStatement(SQL_SELECT_BY_AUTHOR_WITH_YEAR)) {
            ps.setInt(1, author.getId());
            ps.setInt(2, year);
            ResultSet rs = ps.executeQuery();
            Audio audio;
            while (rs.next()) {
                audio = new Audio();
                audio.setTitle(rs.getString("TITLE"));
                audio.setDuration(rs.getInt("DURATION"));
                audio.setYear(rs.getInt("YEAR"));
                audio.setId(rs.getInt("ID"));
                audios.add(audio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return audios;
    }

    public Map<Audio, List<Author>> selectByYearWithAuthors (int year) {
        Map<Audio, List<Author>> resultMap = new HashMap<>();
        List<Author> currentAuthors;

        try (PreparedStatement ps = ConnectionProvider.getConnection().prepareStatement(SQL_SELECT_BY_YEAR_WITH_AUTHOR)) {
            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Audio audio = new Audio();
                audio.setId(rs.getInt("ID"));
                audio.setTitle(rs.getString("TITLE"));
                audio.setYear(rs.getInt("YEAR"));

                Author author = new Author();
                author.setId(rs.getInt("ID"));
                author.setFirstName(rs.getString("FIRST_NAME"));
                author.setLastName(rs.getString("LAST_NAME"));
                author.setBirthday(rs.getDate("BIRTHDAY"));

                if (!resultMap.containsKey(audio)) {
                    currentAuthors = new ArrayList<>();
                    currentAuthors.add(author);
                    resultMap.put(audio, currentAuthors);
                } else {
                    resultMap.get(audio).add(author);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    public Set<Audio> readByOldestAuthor() {
        Set<Audio> audios = new HashSet<>();
        try (Statement st = ConnectionProvider.getConnection().createStatement()) {
            ResultSet rs = st.executeQuery(SQL_SELECT_BY_OLDEST_AUTHOR);
            Audio audio;
            while (rs.next()) {
                audio = new Audio();
                audio.setTitle(rs.getString("TITLE"));
                audio.setDuration(rs.getInt("DURATION"));
                audio.setYear(rs.getInt("YEAR"));
                audio.setId(rs.getInt("ID"));
                audios.add(audio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return audios;
    }
}
