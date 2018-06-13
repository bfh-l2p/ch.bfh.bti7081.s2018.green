
rem Delete File
rem :::::::::::::::::
del pmsDB.sqlite

rem Create new DB
rem :::::::::::::::::
..\sqlite3.exe pmsDB.sqlite < schema.sql

rem Fill in Testdata
rem :::::::::::::::::
..\sqlite3.exe pmsDB.sqlite < data.sql