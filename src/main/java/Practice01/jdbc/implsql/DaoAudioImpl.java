package Practice01.jdbc.implsql;

import Practice01.jdbc.ConnectionProvider;
import Practice01.jdbc.IDao;
import Practice01.model.Audio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import static Practice01.jdbc.sqls.AudiosSQLs.*;

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


    //TODO else

    public Set<Audio> selectByYearWithAuthors () {

        /*
        List<Audio> audios = new ArrayList<Audio>();

        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Audio audio = new Audio();
            audio.setId(resultSet.getInt(""ID""));
            audio.setTitle(resultSet.getString(""TITLE""));

            Author author = new Author();
            author.setFirstName(resultSet.getString(""FIRST_NAME""));
            author.setLastName(resultSet.getString(""LAST_NAME""));

            int founded = audios.indexOf(audio);
            if(founded != -1){
                Audio oldOne = audios.get(founded);
                oldOne.addAuthor(author);
            }else{
                audio.addAuthor(author);
                audios.add(audio);
            }
        }
        resultSet.close();
        */
        return null;
    }
}
