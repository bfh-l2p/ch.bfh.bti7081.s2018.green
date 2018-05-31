INSERT INTO hibernate_sequence (next_val) VALUES (1000);

-- Create emergency contact
INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (1, "emergency", "contact", NULL, NULL, NULL, NULL, NULL, NULL);

-- Create test patient
INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (2, "test", "Patient", NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO Patient
        (id, dangerToOthers, contactId, selfEndangerment)
    values
        (2, 1, 1, 1);

-- Create test staff
INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (3, "test", "Staff", NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO Staff
        (id, type)
    values
        (3, 1);

-- Add sample therapies
INSERT INTO Therapy
        (id, name)
    values
        (1, "Töpfern");
INSERT INTO Therapy
        (id, name)
    values
        (2, "WC-Ente füttern");
INSERT INTO Therapy
        (id, name)
    values
        (3, "Spaziergang");
INSERT INTO Therapy
        (id, name)
    values
        (4, "Streichelzoo besuchen");

-- Create a journal entry
INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (99, "Journaleintrag aus der DB", 1526585549180, 2, 3);

-- Create a sample medication entry
INSERT INTO Medication
        (id, name, start, stop, periode, dose, prescriberId, patientId, created, updated)
    values
        (1, "Ponstan", 1523378040000, 1523550840000, 4, 125, 3, 2, 1523550840000, 1523550840000);

-- Create sample event
INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (1, "test event", "some description", 2, 3, 1523378040000, 1523550840000);
