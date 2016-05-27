package Practice01.jdbc.daoImplSql.sqls;

public final class AudiosSQLs {
    private AudiosSQLs(){}

    public static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS AUDIOS (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            "TITLE TEXT, DURATION INT, YEAR INT)";
    public static final String SQL_INSERT = "INSERT INTO AUDIOS(TITLE, DURATION, YEAR) VALUES (?, ?, ?)";
    public static final String SQL_SELECT_ALL = "SELECT * FROM AUDIOS";
    public static final String SQL_SELECT_BY_ID = "SELECT * FROM AUDIOS WHERE ID = ?";
    public static final String SQL_UPDATE_BY_ID = "UPDATE AUDIOS SET TITLE = ?, DURATION = ?, YEAR = ? WHERE ID = ?";
    public static final String SQL_DELETE_BY_ID = "DELETE FROM AUDIOS WHERE ID = ?";


    //Получить список аудиозаписей по автору
    public static final String SQL_SELECT_BY_AUTHOR = "SELECT AUDIOS.ID, AUDIOS.TITLE, AUDIOS.DURATION, AUDIOS.YEAR FROM AUDIOS " +
            "JOIN AUTHORS_AUDIOS ON AUDIOS.ID = AUTHORS_AUDIOS.AUDIO_ID WHERE AUTHORS_AUDIOS.AUTHORS_ID = ?";

    //Получить список аудиозаписей по автору написаный в указаный год
    public static final String SQL_SELECT_BY_AUTHOR_WITH_YEAR = "SELECT AUDIOS.ID, AUDIOS.TITLE, AUDIOS.DURATION, AUDIOS.YEAR " +
            "FROM AUDIOS JOIN AUTHORS_AUDIOS ON AUDIOS.ID = AUTHORS_AUDIOS.AUDIO_ID " +
            "WHERE AUTHORS_AUDIOS.AUTHORS_ID = ? AND AUDIOS.YEAR = ?";

    //Получить список аудиозаписей в указаный год с информацией об исполнителях (см. H4, L4)
    public static final String SQL_SELECT_BY_YEAR_WITH_AUTHOR = "SELECT * " +
            "FROM AUDIOS JOIN AUTHORS_AUDIOS ON AUDIOS.ID = AUTHORS_AUDIOS.AUDIO_ID " +
            "JOIN AUTHORS ON AUTHORS_AUDIOS.AUTHOR_ID = AUTHORS.id WHERE AUDIOS.YEAR = ?";

    //Получить список аудиозаписей самого старого  исполнителя
    public static final String SQL_SELECT_BY_OLDEST_AUTHOR = "SELECT AUDIOS.ID, AUDIOS.TITLE, AUDIOS.DURATION, AUDIOS.YEAR " +
            "FROM AUDIOS JOIN AUTHORS_AUDIOS ON AUDIOS.ID = AUTHORS_AUDIOS.AUDIO_ID " +
            "JOIN AUTHORS ON AUTHORS_AUDIOS.AUTHOR_ID = AUTHORS.id WHERE AUTHORS.BIRTHDAY = (SELECT MAX(BIRTHDAY) AS BIRTHDAY FROM AUTHORS)";
}
