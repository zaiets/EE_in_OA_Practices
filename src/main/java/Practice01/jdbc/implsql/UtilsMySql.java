package Practice01.jdbc.implsql;

import Practice01.exceptions.DatabaseException;
import Practice01.jdbc.ConnectionProvider;

import java.sql.SQLException;
import java.sql.Statement;

public final class UtilsMySql {

    private UtilsMySql(){}

    /**
     * You can create or delete table using this method
     * @param actions
     */
    public static void executeSqlActions(String [] actions) throws DatabaseException {
        try (Statement st = ConnectionProvider.getConnection().createStatement()) {
            for (String s : actions) {
                st.execute(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("DB error:" + e.getSQLState());
        }
    }
}
