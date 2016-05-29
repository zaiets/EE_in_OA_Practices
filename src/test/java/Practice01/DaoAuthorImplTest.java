package Practice01;


import Practice01.jdbc.ConnectionProvider;
import Practice01.jdbc.DataBaseInit;
import Practice01.jdbc.daoImplSql.DaoAuthorImpl;
import Practice01.model.Author;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;

public class DaoAuthorImplTest {
    DaoAuthorImpl authorDao;
    Author authorExpected;

    @Before
    public void beforeTest() {
        authorDao = new DaoAuthorImpl();

        authorExpected = new Author();
        authorExpected.setId(1);
        authorExpected.setFirstName("Author");
        authorExpected.setLastName("N1");
        authorExpected.setBirthday(Date.valueOf(LocalDate.of(1955, 5, 5)));

        DataBaseInit.initDB();

        //Заполняем связи автор-песня (для тестирования)
        String add_author = "INSERT INTO AUTHORS (FIRST_NAME, LAST_NAME, BIRTHDAY) VALUES (?, ?, ?)";
            //Заполнение авторов перед тестом
            try (PreparedStatement ps = ConnectionProvider.getConnection().prepareStatement(add_author)) {
                for (int i = 1; i < 4; i++) {
                    ps.setString(1, "Author");
                    ps.setString(2, "N".concat(String.valueOf(i)));
                    ps.setDate(3, Date.valueOf(LocalDate.of(1954+i, 5, 5)));
                    ps.executeUpdate();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }

    @Test
    public void createTest01() throws Exception {
        Author author = new Author();
        author.setFirstName("Author");
        author.setLastName("CreateTest");
        author.setBirthday(Date.valueOf(LocalDate.of(1950, 5, 14)));
        int expectedId = 4;
        Assert.assertEquals("Author test 'create' 01", expectedId, authorDao.create(author).getId());
    }

    @Test (expected = Exception.class)
    public void createTest02() throws Exception {
        authorDao.create(null);
    }

    @Test
    public void readTest01() throws Exception {
        Assert.assertEquals("Author test 'read' 01", authorExpected, authorDao.read(1));
    }

    @Test
    public void readTest02() throws Exception {
        Assert.assertNull(authorDao.read(10));
    }

    @Test
    public void readTest03() throws Exception {
        Assert.assertNull(authorDao.read(-1));
    }

    @Test
    public void readAllTest01() throws Exception {
        Set<Author> set = authorDao.readAll();
        Assert.assertEquals("Author test 'readAll' 01", 3, set.size());
    }

    @Test
    public void readAllTest02() throws Exception {
        Set<Author> set = authorDao.readAll();
        Assert.assertTrue(set.contains(authorExpected));
    }

    @Test
    public void readAllTest03() throws Exception {
        Set<Author> set = authorDao.readAll();
        set.iterator().forEachRemaining(o -> Assert.assertTrue(o != null));
    }

    @Test
    public void updateTest01() throws Exception {
        authorExpected.setLastName("TestUpdate");
        Assert.assertEquals("Author test 'update' 01", authorExpected, authorDao.update(authorExpected));
    }

    @Test
    public void updateTest02() throws Exception {
        authorExpected.setLastName(null);
        Assert.assertEquals("Author test 'update' 02", authorExpected, authorDao.update(authorExpected));
    }

    @Test (expected = Exception.class)
    public void updateTest03() throws Exception {
        authorDao.update(null);
    }

    @Test
    public void deleteTest01(){
        Assert.assertEquals("Author test 'delete' 01", authorExpected, authorDao.delete(authorExpected));
    }

    @Test
    public void deleteTest02(){
        authorDao.delete(authorExpected);
        Assert.assertEquals("Author test 'delete' 02", null, authorDao.delete(authorExpected));
    }

    @Test
    public void selectByAgeDiapTest01 () throws Exception {
        Date start = Date.valueOf(LocalDate.of(1953, 1, 1));
        Date end = Date.valueOf(LocalDate.of(1956, 12, 20));
        int expectedSize = 2;
        Assert.assertEquals("Author test 'selectByAgeDiap' 01", expectedSize, authorDao.selectByAgeDiap(start, end).size());
    }

    @Test
    public void selectByBirthdayInTest01 () throws Exception {
        Date [] years = {Date.valueOf(LocalDate.of(1955, 5, 5)), Date.valueOf(LocalDate.of(1956, 5, 5))};
        int expectedSize = 2;
        Assert.assertEquals("Author test 'selectByBirthdayIn' 01", expectedSize, authorDao.selectByBirthdayIn(years).size());
    }
}
