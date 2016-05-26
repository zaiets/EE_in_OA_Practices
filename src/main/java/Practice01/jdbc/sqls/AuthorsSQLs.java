package Practice01.jdbc.sqls;

public final class AuthorsSQLs {
    private AuthorsSQLs(){}

    public static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS practice1.AUTHORS(ID INT NOT NULL AUTO_INCREMENT, " +
            "FIRST_NAME TEXT, LAST_NAME TEXT, BIRTHDAY DATE)";
    public static final String SQL_INSERT = "INSERT INTO practice1.AUTHORS (FIRST_NAME, LAST_NAME, BIRTHDAY) VALUES (?, ?, ?)";
    public static final String SQL_SELECT_ALL = "SELECT * FROM practice1.AUTHORS";
    public static final String SQL_SELECT_BY_ID = "SELECT * FROM practice1.AUTHORS WHERE ID = ?";
    public static final String SQL_UPDATE_BY_ID = "UPDATE practice1.AUTHORS SET FIRST_NAME = ?, LAST_NAME = ?, BIRTHDAY = ? WHERE ID = ?";
    public static final String SQL_DELETE_BY_ID = "DELETE FROM practice1.AUTHORS WHERE ID = ?";


    /*
    Получить список испонителей родившихся в указаном диапазоне
    */
    public static final String SQL_SELECT_BY_AGE_DIAP = "SELECT * FROM practice1.AUTHORS WHERE BIRTHDAY BETWEEN ? AND ?";
    /*
    Получить список исполнителей родившихся в указаных датах
     */
    public static final String SQL_SELECT_BY_BIRTHDAY_IN = "SELECT * FROM practice1.AUTHORS WHERE BIRTHDAY IN ";

}
