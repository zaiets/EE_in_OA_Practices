package Practice01.jdbc;


import Practice01.exceptions.DatabaseException;
import Practice01.jdbc.implsql.UtilsMySql;
import Practice01.properties.PropertyUtils;
import Practice01.jdbc.sqls.*;

import java.util.Properties;

import static Practice01.jdbc.implsql.InitDB_SQLs.*;

public final class DataBaseInit {
    private DataBaseInit(){}

    public static void initDB() throws DatabaseException {
        String[] actions;
        Properties properties = PropertyUtils.getProperties();
        String value = properties.getProperty("DB");
        switch (value) {
            case "JDBC":
                actions = new String[]{
                        SQL_AUTORS_AUDIOS_DROP,
                        SQL_AUTHORS_DROP,
                        SQL_AUDIOS_DROP,
                        SQL_TABLE_DROP,
                        SQL_TABLE_CREATE,
                        AudiosSQLs.SQL_CREATE,
                        AuthorsSQLs.SQL_CREATE,
                        SQL_AUTORS_AUDIOS_CREATE,
                        };
                break;
            default:
                actions = null;
                break;
        }
        UtilsMySql.executeSqlActions(actions);
    }
}
