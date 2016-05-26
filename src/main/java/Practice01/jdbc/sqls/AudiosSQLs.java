package Practice01.jdbc.sqls;

public final class AudiosSQLs {
    private AudiosSQLs(){}

    public static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS practice1.AUDIOS(ID INT NOT NULL AUTO_INCREMENT, " +
            "TITLE TEXT, DURATION INT, YEAR INT)";
    public static final String SQL_INSERT = "INSERT INTO  practice1.AUDIOS(TITLE, DURATION, YEAR) VALUES (?, ?, ?)";
    public static final String SQL_SELECT_ALL = "SELECT * FROM practice1.AUDIOS";
    public static final String SQL_SELECT_BY_ID = "SELECT * FROM practice1.AUDIOS WHERE ID = ?";
    public static final String SQL_UPDATE_BY_ID = "UPDATE practice1.AUDIOS SET TITLE = ?, DURATION = ?, YEAR = ? WHERE ID = ?";
    public static final String SQL_DELETE_BY_ID = "DELETE FROM practice1.AUDIOS WHERE ID = ?";

    /*
    Получить список аудиозаписей по автору
    */
    //TODO else
    /*
    Получить список аудиозаписей по автору написаный в указаный год
    */
    //TODO else
    /*
    Получить список аудиозаписей в указаный год с информацией об исполнителях (см. H4, L4)
    */
    public static final String SQL_SELECT_BY_YEAR_WITH_AUTHOR = "SELECT AUDIOS.ID, TITLE, FIRST_NAME, LAST_NAME " +
            "FROM AUDIOS JOIN AUTHORS_AUDIOS ON AUDIOS.ID = AUTHORS_AUDIOS.AUDIO_ID " +
            "JOIN AUTHORS ON AUTHORS_AUDIOS.AUTHOR_ID = AUTHORS.id";
    /*
    Получить список аудиозаписей самого старого  исполнителя
     */
    //TODO else
}
