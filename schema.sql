CREATE TABLE Person
(
  id        INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  firstName VARCHAR(45)                       NOT NULL,
  lastName  VARCHAR(45)                       NOT NULL,
  dob       DATE DEFAULT NULL,
  address   VARCHAR(60),
  zip       VARCHAR(12),
  city      VARCHAR(45),
  email     VARCHAR(120),
  phone     VARCHAR(20)
);
CREATE INDEX person_firstName_index
  ON Person (firstName);
CREATE INDEX person_lastName_index
  ON Person (lastName);
CREATE TABLE IF NOT EXISTS Patient
(
  id               INTEGER PRIMARY KEY NOT NULL,
  contactId        INTEGER             NOT NULL,
  selfEndangerment INTEGER             NOT NULL,
  dangerToOthers   INTEGER             NOT NULL,
  CONSTRAINT patient_person_id_fk FOREIGN KEY (id) REFERENCES Person (id),
  CONSTRAINT patient_person_id_fk FOREIGN KEY (contactId) REFERENCES Person (id)
);
CREATE TABLE IF NOT EXISTS Staff
(
  id   INTEGER PRIMARY KEY NOT NULL,
  type INTEGER             NOT NULL,
  CONSTRAINT staff_person_fk FOREIGN KEY (id) REFERENCES Person (id)
);
CREATE TABLE IF NOT EXISTS Medication
(
  id           INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  name         VARCHAR(120),
  start        DATETIME,
  stop         DATETIME,
  periode      INTEGER,
  dose         FLOAT                             NOT NULL,
  patientId    INTEGER                           NOT NULL,
  prescriberId INTEGER                           NOT NULL,
  created      DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated      DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CONSTRAINT medication_patient_fk FOREIGN KEY (patientId) REFERENCES Patient (id),
  CONSTRAINT medication_prescriber_fk FOREIGN KEY (prescriberId) REFERENCES Staff (id)
);
CREATE INDEX medication_start_index
  ON Medication (start);
CREATE INDEX medication_stop_index
  ON Medication (stop);
CREATE INDEX medication_patientId_index
  ON Medication (patientId);
CREATE INDEX medication_prescriberId_index
  ON Medication (prescriberId);
CREATE TABLE IF NOT EXISTS JournalEntry
(
  id        INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  content   TEXT                              NOT NULL,
  authorId  INTEGER                           NOT NULL,
  patientId INTEGER                           NOT NULL,
  created   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CONSTRAINT journalEntry_author_fk FOREIGN KEY (authorId) REFERENCES Staff (id),
  CONSTRAINT journalEntry_patient_fk FOREIGN KEY (patientId) REFERENCES Patient (id)
);
CREATE INDEX journalEntry_authorId_index
  ON JournalEntry (authorId);
CREATE INDEX journalEntry_patientId_index
  ON JournalEntry (patientId);
CREATE TABLE IF NOT EXISTS Event
(
  id          INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  start       DATETIME                          NOT NULL,
  stop        DATETIME                          NOT NULL,
  description TEXT,
  title       VARCHAR(120),
  patientId   INTEGER                           NOT NULL,
  therapistId INTEGER                           NOT NULL,
  nextId      INTEGER DEFAULT NULL,
  programId   INTEGER DEFAULT NULL,
  CONSTRAINT event_patient_fk FOREIGN KEY (patientId) REFERENCES Patient (id),
  CONSTRAINT event_staff_id_fk FOREIGN KEY (therapistId) REFERENCES Staff (id),
  CONSTRAINT event_event_id_fk FOREIGN KEY (nextId) REFERENCES Event (id)
);
CREATE INDEX event_start_index
  ON Event (start);
CREATE INDEX event_stop_index
  ON Event (stop);
CREATE INDEX event_title_index
  ON Event (title);
CREATE INDEX event_patientId_index
  ON Event (patientId);
CREATE INDEX event_therapistId_index
  ON Event (therapistId);
CREATE TRIGGER update_medication_trigger
  AFTER UPDATE
  ON Medication
  FOR EACH ROW
BEGIN
  UPDATE Medication
  SET updated = CURRENT_TIMESTAMP
  WHERE id = old.id;
END;
CREATE TABLE hibernate_sequence
(
  next_val bigint
);