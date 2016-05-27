package Practice01.jdbc;


import Practice01.jdbc.daoImplSql.UtilsMySql;
import Practice01.jdbc.daoImplSql.sqls.AudiosSQLs;
import Practice01.jdbc.daoImplSql.sqls.AuthorsSQLs;
import Practice01.properties.PropertyUtils;

import java.util.Properties;

import static Practice01.jdbc.daoImplSql.sqls.InitDB_SQLs.*;

public final class DataBaseInit {
    private DataBaseInit() {
    }

    public static void initDB() {
        String[] actions;
        Properties properties = PropertyUtils.getProperties();
        String value = properties.getProperty("DB");
        switch (value) {
            case "JDBC":
                actions = new String[]{
                        SQL_AUTORS_AUDIOS_DROP,
                        SQL_AUTHORS_DROP,
                        SQL_AUDIOS_DROP,
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
