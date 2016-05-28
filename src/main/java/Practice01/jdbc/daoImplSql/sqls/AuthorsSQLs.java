package Practice01.jdbc.daoImplSql.sqls;

public final class AuthorsSQLs {
    private AuthorsSQLs(){}

    public static final String SQL_INSERT = "INSERT INTO AUTHORS (FIRST_NAME, LAST_NAME, BIRTHDAY) VALUES (?, ?, ?)";
    public static final String SQL_SELECT_ALL = "SELECT * FROM AUTHORS";
    public static final String SQL_SELECT_BY_ID = "SELECT * FROM AUTHORS WHERE ID = ?";
    public static final String SQL_UPDATE_BY_ID = "UPDATE AUTHORS SET FIRST_NAME = ?, LAST_NAME = ?, BIRTHDAY = ? WHERE ID = ?";
    public static final String SQL_DELETE_BY_ID = "DELETE FROM AUTHORS WHERE ID = ?";


    //Получить список испонителей родившихся в указаном диапазоне
    public static final String SQL_SELECT_BY_AGE_DIAP = "SELECT * FROM AUTHORS WHERE BIRTHDAY BETWEEN ? AND ?";


    //Получить список исполнителей родившихся в указаных датах
    //В конец скрипта необходимо добавить перечисление лет в следующем формате '(2001, 1943, ..., 1954, 1955)'
    public static final String SQL_SELECT_BY_BIRTHDAY_IN = "SELECT * FROM AUTHORS WHERE BIRTHDAY IN ";

}
