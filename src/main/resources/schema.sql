create TABLE IF NOT EXISTS query(
    query_id integer PRIMARY KEY,
    table_name varchar(100),
    query varchar(300));
create TABLE IF NOT EXISTS single_query(
    query_id integer PRIMARY KEY,
    query varchar(300));