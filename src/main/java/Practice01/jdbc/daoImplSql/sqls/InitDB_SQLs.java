package Practice01.jdbc.daoImplSql.sqls;

public final class InitDB_SQLs {
    private InitDB_SQLs () {}
    //Init DB scripts for Practice01:
    public static final String SQL_AUDIOS_DROP = "DROP TABLE IF EXISTS audios";
    public static final String SQL_AUTHORS_DROP = "DROP TABLE IF EXISTS authors";
    public static final String SQL_AUTORS_AUDIOS_DROP = "DROP TABLE IF EXISTS authors_audios";
    public static final String SQL_AUTORS_AUDIOS_CREATE = "CREATE TABLE IF NOT EXISTS AUTHORS_AUDIOS(AUTHOR_ID INT, " +
            "AUDIO_ID INT, UNIQUE (AUDIO_ID,AUTHOR_ID), FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHORS(id), " +
            "FOREIGN KEY (AUDIO_ID) REFERENCES AUDIOS(ID));";
    public static final String SQL_AUDIOS_CREATE = "CREATE TABLE IF NOT EXISTS AUDIOS (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            "TITLE TEXT, DURATION INT, YEAR INT)";
    public static final String SQL_AUTHORS_CREATE = "CREATE TABLE IF NOT EXISTS AUTHORS (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            "FIRST_NAME TEXT, LAST_NAME TEXT, BIRTHDAY DATE)";


}
