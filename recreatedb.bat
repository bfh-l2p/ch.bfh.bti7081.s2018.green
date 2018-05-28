del pmsDB.sqlite
sqlite3 -init schema.sql pmsDB.sqlite .exit
sqlite3 pmsDB.sqlite < data.sql
