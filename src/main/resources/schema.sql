create TABLE IF NOT EXISTS query(
    queryId integer PRIMARY KEY,
    tableName varchar(100),
    query varchar(300));