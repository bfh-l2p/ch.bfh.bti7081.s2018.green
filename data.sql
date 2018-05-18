INSERT INTO hibernate_sequence (next_val) VALUES (100);

INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (1, "emergency", "contact", NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (2, "test", "Patient", NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (3, "test", "Staff", NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO Patient
        (id, dangerToOthers, contactId, selfEndangerment)
    values
        (2, 1, 1, 1);

INSERT INTO Staff
        (id, type)
    values
        (3, 1);

INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (99, "Journaleintrag aus der DB", 1526585549180, 2, 3)
