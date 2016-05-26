package Practice01.jdbc.daoImplSql.sqls;

public final class InitDB_SQLs {
    private InitDB_SQLs () {}
    public static final String SQL_AUDIOS_DROP = "DROP TABLE IF EXISTS audios";
    public static final String SQL_AUTHORS_DROP = "DROP TABLE IF EXISTS authors";
    public static final String SQL_AUTORS_AUDIOS_DROP = "DROP TABLE IF EXISTS authors_audios";
    public static final String SQL_AUTORS_AUDIOS_CREATE = "CREATE TABLE IF NOT EXISTS AUTHORS_AUDIOS(AUTHOR_ID INT, " +
            "AUDIO_ID INT, UNIQUE (AUDIO_ID,AUTHOR_ID), FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHORS(id), " +
            "FOREIGN KEY (AUDIO_ID) REFERENCES AUDIOS(ID));";
}
