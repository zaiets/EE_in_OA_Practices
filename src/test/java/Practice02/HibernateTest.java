package Practice02;

import org.hibernate.Session;
import org.junit.Test;

/**
 * Created by Levsha on 30.05.2016.
 */
public class HibernateTest {

    @Test
    public void test() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            //TODO

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
