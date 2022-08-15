CREATE TABLE IF NOT EXISTS query(
    query_id integer PRIMARY KEY,
    table_name varchar(100),
    query varchar(300));
CREATE TABLE IF NOT EXISTS single_query(
    query_id integer PRIMARY KEY,
    query varchar(300));
CREATE TABLE IF NOT EXISTS report(
    report_id integer PRIMARY KEY,
    table_amount integer
);
CREATE TABLE IF NOT EXISTS report_table(
    report_table_id integer auto_increment PRIMARY KEY,
    table_name varchar(200),
    report_id integer,
    CONSTRAINT fk_report_table_report FOREIGN KEY(report_id) REFERENCES report(report_id)
);
CREATE TABLE IF NOT EXISTS report_column(
    report_column_id integer auto_increment PRIMARY KEY,
    title varchar(200),
    type varchar(100),
    report_table_id integer,
    CONSTRAINT report_column_report_table FOREIGN KEY(report_table_id) REFERENCES report_table(report_table_id)
);