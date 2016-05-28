package Practice01;


import Practice01.jdbc.ConnectionProvider;
import Practice01.jdbc.DataBaseInit;
import Practice01.jdbc.daoImplSql.DaoAudioImpl;
import Practice01.jdbc.daoImplSql.DaoAuthorImpl;
import Practice01.model.Audio;
import Practice01.model.Author;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class DaoAuthorImplTest {
    DaoAuthorImpl authorDao;
    DaoAudioImpl audioDao;

    @Before
    public void beforeTest() {
        DataBaseInit.initDB();

        authorDao = new DaoAuthorImpl();
        audioDao = new DaoAudioImpl();
        for (int i = 0; i < 3; i++) {
            Author author = new Author();
            author.setFirstName("Author");
            author.setLastName("N".concat(String.valueOf(i)));
            author.setBirthday(Date.valueOf(LocalDate.of(1955, 5, 5)));
            authorDao.create(author);

            Audio audio = new Audio();
            audio.setTitle("Song N".concat(String.valueOf(i)));
            audio.setDuration(1 + i);
            audio.setYear(1980 + i);
            audioDao.create(audio);
        }
        //Заполняет связи автор-песня (для тестирования)
        String add_author_audio = "INSERT INTO AUTHORS_AUDIOS (AUTHOR_ID, AUDIO_ID) VALUES (?, ?)";
        try (PreparedStatement ps = ConnectionProvider.getConnection().prepareStatement(add_author_audio)) {
            ps.setInt(1, 1);
            ps.setInt(2, 1);
            ps.executeUpdate();
            ps.setInt(1, 1);
            ps.setInt(2, 2);
            ps.executeUpdate();
            ps.setInt(1, 1);
            ps.setInt(2, 3);
            ps.executeUpdate();
            ps.setInt(1, 2);
            ps.setInt(2, 3);
            ps.executeUpdate();
            ps.setInt(1, 3);
            ps.setInt(2, 3);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createTest() throws Exception {
        Author author = new Author();
        author.setFirstName("Author");
        author.setLastName("CreateTest");
        author.setBirthday(Date.valueOf(LocalDate.of(1950, 5, 14)));
        Assert.assertSame("Create author test 01", author, authorDao.create(author));
    }

    @Test
    public void readTest() throws Exception {
        Assert.fail();
    }

    @Test
    public void readAllTest() throws Exception {
        Assert.fail();
    }

    @Test
    public void updateTest() throws Exception {
        Assert.fail();
    }

    @Test
    public void readByAuthorTest() throws Exception {
        Assert.fail();
    }

    @Test
    public void readByAuthorAndYearTest() throws Exception {
        Assert.fail();
    }

    @Test
    public void selectByYearWithAuthorsTest() throws Exception {
        Assert.fail();
    }

    @Test
    public void readByOldestAuthorTest() throws Exception {
        Assert.fail();
    }
}
