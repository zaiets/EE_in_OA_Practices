package Practice01.jdbc.daoImplSql;

import Practice01.jdbc.ConnectionProvider;

import java.sql.SQLException;
import java.sql.Statement;

public final class UtilsMySql {

    private UtilsMySql(){}
    /**
     * You can create or delete table using this method
     */
    public static void executeSqlActions(String [] actions) {
        try (Statement st = ConnectionProvider.getConnection().createStatement()) {
            for (String s : actions) {
                st.execute(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
