
rem Delete File
rem :::::::::::::::::
del pmsDB.sqlite

rem Create new DB
rem :::::::::::::::::
sqlite3.exe pmsDB.sqlite < schema.sql