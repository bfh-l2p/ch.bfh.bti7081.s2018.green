#!/bin/bash

# remove existing db
rm pmsDB.sqlite

# create new db from schema.sql
sqlite3 -init schema.sql pmsDB.sqlite .exit

# import default data
sqlite3 pmsDB.sqlite < data.sql