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

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;

public class DaoAudioImplTest {
    DaoAuthorImpl authorDao;
    DaoAudioImpl audioDao;
    Author author1;
    Author author2;
    Audio audioExpected;

    @Before
    public void beforeTest() {
        authorDao = new DaoAuthorImpl();
        audioDao = new DaoAudioImpl();

        author1 = new Author();
        author1.setId(1);
        author1.setFirstName("Author");
        author1.setLastName("N1");
        author1.setBirthday(Date.valueOf(LocalDate.of(1980, 1, 1)));
        author2 = new Author();
        author2.setId(2);
        author2.setFirstName("Author");
        author2.setLastName("N2");
        author2.setBirthday(Date.valueOf(LocalDate.of(1981, 2, 2)));

        audioExpected = new Audio();
        audioExpected.setId(1);
        audioExpected.setTitle("Song N1");
        audioExpected.setDuration(1);
        audioExpected.setYear(2001);

        DataBaseInit.initDB();

        //Заполняем связи автор-песня (для тестирования)
        String add_author_audio = "INSERT INTO AUTHORS_AUDIOS (AUTHOR_ID, AUDIO_ID) VALUES (?, ?)";
        String add_audio = "INSERT INTO AUDIOS (TITLE, DURATION, YEAR) VALUES (?, ?, ?)";
        String add_author = "INSERT INTO AUTHORS (FIRST_NAME, LAST_NAME, BIRTHDAY) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionProvider.getConnection()) {
            //Заполнение аудио перед тестом
            try (PreparedStatement ps = connection.prepareStatement(add_audio)) {
                for (int i = 1; i < 4; i++) {
                    ps.setString(1, "Song N".concat(String.valueOf(i)));
                    ps.setInt(2, i);
                    ps.setInt(3, 2000 + i);
                    ps.executeUpdate();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            //Заполнение авторов перед тестом
            try (PreparedStatement ps = connection.prepareStatement(add_author)) {
                for (int i = 1; i < 4; i++) {
                    ps.setString(1, "Author");
                    ps.setString(2, "N".concat(String.valueOf(i)));
                    ps.setDate(3, Date.valueOf(LocalDate.of(1980+i, 1, 1)));
                    ps.executeUpdate();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            //Заполнение связей аудио-авторы перед тестом
            try (PreparedStatement ps = connection.prepareStatement(add_author_audio)) {
                ps.setInt(1, 1);
                ps.setInt(2, 1);
                ps.executeUpdate();
                ps.setInt(1, 2);
                ps.setInt(2, 2);
                ps.executeUpdate();
                ps.setInt(1, 2);
                ps.setInt(2, 1);
                ps.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createTest01 () throws Exception {
        Audio audio = new Audio();
        audio.setTitle("Song N4");
        audio.setDuration(4);
        audio.setYear(2004);
        int expectedId = 4;
        Assert.assertEquals("Audio test 'create' 01", expectedId, audioDao.create(audio).getId());
    }

    @Test (expected = Exception.class)
    public void createTest02() throws Exception {
        audioDao.create(null);
    }

    @Test
    public void readTest01() throws Exception {
        Assert.assertEquals("Audio test 'read' 01", audioExpected, audioDao.read(1));
    }

    @Test
    public void readTest02() throws Exception {
        Assert.assertNull(audioDao.read(10));
    }

    @Test
    public void readTest03() throws Exception {
        Assert.assertNull(audioDao.read(-1));
    }

    @Test
    public void readAllTest01() throws Exception {
        Set<Audio> set = audioDao.readAll();
        Assert.assertEquals("Audio test 'readAll' 01", 3, set.size());
    }

    @Test
    public void readAllTest02() throws Exception {
        Set<Audio> set = audioDao.readAll();
        Assert.assertTrue(set.contains(audioExpected));
    }

    @Test
    public void readAllTest03() throws Exception {
        Set<Audio> set = audioDao.readAll();
        set.iterator().forEachRemaining(o -> Assert.assertTrue(o != null));
    }

    @Test
    public void updateTest01() throws Exception {
        audioExpected.setTitle("TestUpdate");
        Assert.assertEquals("Audio test 'update' 01", audioExpected, audioDao.update(audioExpected));
    }

    @Test
    public void updateTest02() throws Exception {
        audioExpected.setTitle(null);
        Assert.assertEquals("Audio test 'update' 02", audioExpected, audioDao.update(audioExpected));
    }

    @Test (expected = Exception.class)
    public void updateTest03() throws Exception {
        audioDao.update(null);
    }

    @Test
    public void deleteTest01(){
        Audio audio = new Audio();
        audio.setId(3);
        audio.setTitle("Song N3");
        audio.setDuration(3);
        audio.setYear(2003);
        Assert.assertEquals("Audio test 'delete' 01", audio, audioDao.delete(audio));
    }

    @Test
    public void deleteTest02(){
        Audio audio = new Audio();
        audio.setTitle("Song N4");
        audio.setDuration(4);
        audio.setYear(2004);
        audioDao.delete(audio);
        Assert.assertEquals("Audio test 'delete' 02", null, audioDao.delete(audio));
    }

    @Test
    public void readByAuthorTest01() throws Exception {
        Set<Audio> actual = audioDao.readByAuthor(author1);
        int sizeExpected = 1;
        Assert.assertEquals("Audio test 'readByAuthor' 01", sizeExpected, actual.size());
    }

    @Test
    public void readByAuthorTest02() throws Exception {
        Set<Audio> actual = audioDao.readByAuthor(author1);
        Assert.assertTrue(actual.contains(audioExpected));
    }

    @Test
    public void readByAuthorAndYearTest01() throws Exception {
        Set<Audio> actual = audioDao.readByAuthorAndYear(author2, 2002);
        int sizeExpected = 1;
        Assert.assertEquals("Audio test 'readByAuthor' 01", sizeExpected, actual.size());
    }

    @Test
    public void readByAuthorAndYearTest02() throws Exception {
        Set<Audio> actual = audioDao.readByAuthorAndYear(author1, 2001);
        Assert.assertTrue(actual.contains(audioExpected));
    }

    @Test
    public void selectByYearWithAuthorsTest() throws Exception {
        //TODO
        Assert.fail();
    }

    @Test
    public void readByOldestAuthorTest() throws Exception {
        //TODO
        Assert.fail();
    }
}
