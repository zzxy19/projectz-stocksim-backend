import psycopg2

CREATE_TABLE_QUERY = """
  CREATE TABLE company_dev (
    Symbol      varchar(10) PRIMARY KEY,
    Name        varchar(100) NOT NULL,
    IPOyear     integer,
    Sector      varchar(100),
    Industry    varchar(100)
  );
"""

INSERT_QUERY = """
  INSERT INTO company_dev VALUES ("MU", "Micron", 2001, "Tech Sector", "Tech Industry");
"""

SELECT_QUERY = """
  SELECT * FROM company_dev;
"""

DELETE_QUERY = """
  DELETE FROM company_dev WHERE Symbol = "MU"
"""

def doQuery(conn, query) :
  cur = conn.cursor()
  cur.execute(query)
  for row in cur.fetchall():
    print row

hostname='projectz-stocksim-db.chsesjujswlg.us-east-2.rds.amazonaws.com'
username='oneplustwo'
password='xiezihan19'
database='stocksim_zbase'

myConnection = psycopg2.connect(host=hostname, user=username, password=password, dbname=database)
#doQuery(myConnection, CREATE_TABLE_QUERY)
