INSERT INTO hibernate_sequence (next_val) VALUES (1000);

-- Create emergency contact
INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (1, "Stefan", "Schwab", "Kontaktstrasse 45a", "Köniz", NULL, "f.huber21@gmail.com", "0781316763", "3098");

INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (9, "Christoph", "Blocker", "Lafferystrasse 17", "Hergiswil", NULL, "chris.blocker@gmx.ch", "0311458342", "6052");

INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (11, "Lisa", "Meyer", "Dachweg 56", "Rothrist", NULL, NULL, "0334583240", "4550");

INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (16, "Karl", "Fleischhauer", "Metzgerweg 35", "Olten", NULL, "karl.fleischhauer@bluewin.ch", "0625326788", "6239");

-- Create test patient
INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (2, "Freddy", "Huber", "Bernstrasse 103", "Köniz", NULL, "f.huber21@gmail.com", "0781316763", "3098");
INSERT INTO Patient
        (id, dangerToOthers, contactId, selfEndangerment)
    values
        (2, 1, 16, 1);

INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (4, "Wilfried", "Sauer", "Apfelweg 4", "Riggisberg", NULL, "willy_suurer@gmail.com", "0798426496", "3132");
INSERT INTO Patient
        (id, dangerToOthers, contactId, selfEndangerment)
    values
        (4, 1, 9, 1);

INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (5, "Karl", "Klein", "Höhenweg 3", "Ittigen", NULL, "karl_der_kleine@yahoo.com", "0767445327", "3063");
INSERT INTO Patient
        (id, dangerToOthers, contactId, selfEndangerment)
    values
        (5, 1, 1, 2);

INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (6, "Ruth", "Span", "Spielplatz 26", "Bern", NULL, "rutschi@hotmail.de", "0783223554", "3018");
INSERT INTO Patient
        (id, dangerToOthers, contactId, selfEndangerment)
    values
        (6, 1, 11, 3);

INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (8, "Hugo", "Kampf", "Sackgasse 1", "Madiswil", -240714000000, "hugo@kampf.ch", "0775379247", "4137");
INSERT INTO Patient
        (id, dangerToOthers, contactId, selfEndangerment)
    values
        (8, 3, 16, 2);

INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (10, "Justin", "Wolf", "Riedbachweg 23", "Ried", NULL, "jwolf@hotmail.com", "0794328843", "3205");
INSERT INTO Patient
        (id, dangerToOthers, contactId, selfEndangerment)
    values
        (10, 4, 1, 1);

INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (13, "Wilma", "Streit", "Friedensstrasse 21", "Grenchen", NULL, "wstreit2@gmx.ch", "0334689032", "2530");
INSERT INTO Patient
        (id, dangerToOthers, contactId, selfEndangerment)
    values
        (13, 1, 11, 2);

INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (14, "Myrthe", "Deprix", "Rue de la Gare 4", "Le Landeron", NULL, "deprixmyrthe@sadmail.ch", "0333216548", "2125");
INSERT INTO Patient
        (id, dangerToOthers, contactId, selfEndangerment)
    values
        (14, 2, 9, 4);

INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (17, "Sergej", "Fährlich", "Blumenweg 3", "Spreitenbach", NULL, "hausbesuche@yahoo.com", "0645894321", "8251");
INSERT INTO Patient
        (id, dangerToOthers, contactId, selfEndangerment)
    values
        (17, 1, 11, 3);

-- Create test staff
INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (3, "Caroline", "Malade", "Krankengasse 3", "Bern", NULL, "caroline.malade@klinik.ch", "0798356434", "3007");
INSERT INTO Staff
        (id, type)
    values
        (3, 0);

INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (7, "Maria", "Spritze", "Spitalstrasse 44", "Hindelbank", NULL, "maria.spritze@klinik.ch", "0311234567", "3145");
INSERT INTO Staff
        (id, type)
    values
        (7, 0);

INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (12, "Martin", "Schmitt", "Buchschachenweg 22", "Biel", NULL, "martin.schmitt@klinik.ch", "0344598932", "2501");
INSERT INTO Staff
        (id, type)
    values
        (12, 1);

INSERT INTO Person
        (id, firstName, lastName, address, city, dob, email, phone, zip)
    VALUES
        (15, "Simon", "Dr. Acula", "Zukunftsstrasse 157", "Zürich", NULL, "simon.acula@klinik.ch", "0795432178", "8301");
INSERT INTO Staff
        (id, type)
    values
        (15, 0);

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

-- Create journal entries
INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (1, "Rückfall in alte Muster: Der Patient zeigt wieder Anzeichen von beginnender Depression. ", 1527323400000, 2, 12);

INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (2, "Patient klagt, die Antidepressiva seien zu niedrig dosiert. Erhöhung der Dosis vorläufig abgelehnt.", 1527330600000, 2, 12);

INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (3, "Wurde ausfällig gegenüber Pflege. Körperliche Intervention konnte dank Präsenz von zwei weiteren Pflegenden abgewendet werden.", 1527337980000, 4, 3);

INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (4, "Macht gute Forschritte. Heute zum ersten mal Lust geäussert, etwas zu unternehmen.", 1527409860000, 2, 12);

INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (5, "Das ganze Gesicht strahlte heute beim Töpfern nachdem die getöpferte Schale erfolgreich aus dem Ofen kam.", 1527427860000, 4, 3);

INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (6, "Patientin will trotz schlechtem Zustand heute wieder nach Hause. Sie ist ist sich ihren Problemen noch nicht bewusst.", 1527436260000, 6, 15);

INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (7, "Patient hat sich heute zum ersten Mal richtig geöffnet und über seine Vergangenheit gesprochen.", 1527511860000, 4, 3);

INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (8, "Starke bipolare Störungen erkennbar. Heute geht es der Patientin wieder viel besser.", 1527594660000, 6, 15);

INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (9, "Patient fühlt sich im Zimmer unwohl. Der Mitbewohner triggert immer wieder durch abfällige Bemerkungen zum Werdegang des Patienten", 1527672060000, 2, 12);

INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (10, "Patient zeigt initiative und wirkt klar. Im Gespräch mit den Eltern ist ein Wille da, eine Weiterbildung zu beginnen", 1527672480000, 2, 15);

INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (11, "Die Reittherapie hat die Patientin gut angenommen. Sie beginnt wieder mit uns zu sprechen und zeigt Vertrauen.", 1527704880000, 6, 15);

INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (12, "Patient wirkt apathisch und unklar. Die Situation wurde heute durch das bereits wieder kalte Mittagessen getriggert. Bezug zu Misserfolg im alten Beruf als Koch.", 1527766080000, 2, 12);

INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (13, "Im Gespräch mit den Angehörigen konnte ich die schwierigen Familiären Verhältnisse erkennen. Die Patientin leidet under Kindheisttraumata und neigte stark zu depressiven Aussagen", 1527771480000, 6, 15);

INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (14, "Suizid Versuch, Patientin befindet sich im Tiefenauspital. Verletzung ca. 7cm lang und 3cm tief entlang der Halsschlagader. Pflege fand die Person in ein Tuch eingewickelt.", 1527848880000, 6, 15);

INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (15, "Patient ist um 14:30 nicht wie vereinbahrt aus dem Ausgang Typ B zurück gekommen. Wir haben 14:45 eine Fahnung ausgeschrieben. Person befindet sich vermutlich im Grossraum Bern", 1527856080000, 4, 3);

INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (16, "Verbale ausfälligkeiten während Krisengespräch. Patient verweigert interaktion und zeigt sich allgemein unkoorporativ.", 1527872280000, 2, 12);

INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (17, "Im Malatelier ist es erneut zu gewalttätigen Übergriffen durch den Partner der Patientin gekommen. Unangemeledet erschien Herr W. während den Malstunden.", 1527958680000, 4, 3);

INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (18, "Wie mit Frau Meyer verabredet darf der Patient übers Wochenende nach hause. Auflagen: keine Reisen mit ÖV wegen Suizid Risiko", 1528034280000, 2, 3);

INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (19, "Die Patientin will in eine Psychiatrie in Deutschland verlegt werden. Dort habe sie mehr Kontakt zu verwandent was sie positiv motiviert. KESB erlaubniss ausstehend.", 1528196280000, 6, 15);

INSERT INTO JournalEntry
        (id, content, created, patientId, authorId)
    values
        (20, "Frau Streit war heute sehr gereizt, weil sie der Meinung war das ihre Spaghetti zu kurz waren. Medikamentöse Sedierung empfohlen", 1528300680000, 13, 3);

-- Create sample medication entries
INSERT INTO Medication
        (id, name, start, stop, periode, dose, prescriberId, patientId, created, updated)
    values
        (1, "Ponstan", 1523378040000, 1531240440000, 4, 125, 3, 6, 1523550840000, 1523550840000);

INSERT INTO Medication
        (id, name, start, stop, periode, dose, prescriberId, patientId, created, updated)
    values
        (2, "Amitriptylin", 1523378040000, 1531240440000, 4, 150, 3, 2, 1523550840000, 1523550840000);

INSERT INTO Medication
        (id, name, start, stop, periode, dose, prescriberId, patientId, created, updated)
    values
        (3, "Doxepin", 1523378040000, 1523550840000, 4, 125, 3, 6, 1523550840000, 1523550840000);

INSERT INTO Medication
        (id, name, start, stop, periode, dose, prescriberId, patientId, created, updated)
    values
        (4, "Amitriptylin", 1523378040000, 1531240440000, 4, 150, 3, 4, 1523550840000, 1523550840000);

INSERT INTO Medication
        (id, name, start, stop, periode, dose, prescriberId, patientId, created, updated)
    values
        (5, "Doxepin", 1523378040000, 1523550840000, 4, 150, 3, 4, 1523550840000, 1523550840000);

INSERT INTO Medication
        (id, name, start, stop, periode, dose, prescriberId, patientId, created, updated)
    values
        (6, "Ponstan", 1523378040000, 1523550840000, 4, 125, 3, 2, 1523550840000, 1523550840000);

-- Create sample events
INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (1, "Sprechstunde", "Betreuung durch Psychologe", 2, 12, 1529049600000, 1529053200000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (2, "Sprechstunde", "Betreuung durch Psychologe", 2, 12, 1529654400000, 1529658000000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (3, "Sprechstunde", "Betreuung durch Psychologe", 2, 12, 1529654400000, 1530262800000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (4, "Sprechstunde", "Betreuung durch Psychologe", 2, 12, 1530864000000, 1530867600000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (5, "Sitzung über Angst", "Wie mit Angst umgehen", 2, 12, 1529323200000, 1529326800000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (6, "Maltherapie", "In der Gruppe", 4, 3, 1529049600000, 1529053200000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (7, "Maltherapie", "In der Gruppe", 4, 3, 1529654400000, 1529658000000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (8, "Maltherapie", "In der Gruppe", 4, 3, 1530259200000, 1530262800000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (9, "Maltherapie", "In der Gruppe", 4, 3, 1530864000000, 1530867600000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (10, "Begleiteter Ausgang", "Nur im Hof", 4, 7, 1529323200000, 1529326800000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (11, "Begleiteter Ausgang", "Nur im Hof", 4, 7, 1529409600000, 1529413200000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (12, "Begleiteter Ausgang", "Nur im Hof", 4, 7, 1529496000000, 1529499600000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (13, "Begleiteter Ausgang", "Nur im Hof", 4, 7, 1529582400000, 1529586000000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (14, "Begleiteter Ausgang", "Nur im Hof", 4, 7, 1529668800000, 1529672400000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (15, "Begleiteter Ausgang", "Nur im Hof", 4, 7, 1529755200000, 1529758800000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (16, "Begleiteter Ausgang", "Nur im Hof", 4, 7, 1529841600000, 1529845200000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (17, "Begleiteter Ausgang", "Nur im Hof", 4, 7, 1529928000000, 1529931600000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (18, "Begleiteter Ausgang", "Nur im Hof", 4, 7, 1530014400000, 1530018000000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (19, "Austrittsgespräch", "Im Beisein eine/r engen Angehörige/n", 6, 7, 1529049600000, 1529053200000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (20, "Töpferschnupperkurs", "Der Patient will sich einmal beim Töpfern versuchen", 6, 3, 1529654400000, 1529658000000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (21, "Tierfütterung", "Im Streichelzoo Geisen füttern", 6, 15, 1529409600000, 1529413200000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (22, "Tierfütterung", "Im Streichelzoo Geisen füttern", 6, 15, 1529496000000, 1529499600000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (23, "Tierfütterung", "Im Streichelzoo Geisen füttern", 6, 15, 1529496000000, 1529586000000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (24, "Tierfütterung", "Im Streichelzoo Geisen füttern", 6, 15, 1529582400000, 1529672400000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (25, "Tierfütterung", "Im Streichelzoo Geisen füttern", 6, 15, 1529668800000, 1529758800000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (26, "Tierfütterung", "Im Streichelzoo Geisen füttern", 6, 15, 1529755200000, 1529845200000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (27, "Tierfütterung", "Im Streichelzoo Geisen füttern", 6, 15, 1529841600000, 1529931600000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (28, "Tierfütterung", "Im Streichelzoo Geisen füttern", 6, 15, 1529928000000, 1530018000000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (29, "Tierfütterung", "Im Streichelzoo Geisen füttern", 6, 15, 1530014400000, 1530104400000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (30, "Kriesengespräch", "Im Beisein der Eltern", 6, 12, 1529654400000, 1529658000000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (31, "Stationsmeeting", "Mit Dr. Acula zu besprechen", 10, 15, 1529049600000, 1529053200000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (32, "Stationsmeeting", "Mit Dr. Acula zu besprechen", 10, 15, 1529654400000, 1529658000000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (33, "Stationsmeeting", "Mit Dr. Acula zu besprechen", 10, 15, 1530259200000, 1530262800000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (34, "Stationsmeeting", "Mit Dr. Acula zu besprechen", 10, 15, 1530864000000, 1530867600000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (35, "Stationsmeeting", "Mit Dr. Acula zu besprechen", 10, 15, 1531468800000, 1531472400000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (36, "Blutentnahme", "", 10, 7, 1530025200000, 1530027000000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (37, "Konflikttherapie 2", "Im 2. Stock ( Konferenz Raum) z.H Frau Streit", 10, 12, 1529049600000, 1529053200000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (38, "Umgang mit Kriesensituationen", "Gruppe 2: Streit, Fährlich, Sauer.", 10, 15, 1524724200000, 1524731400000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (39, "Umgang mit Kriesensituationen", "Gruppe 2: Streit, Fährlich, Sauer.", 10, 15, 1527316200000, 1527323400000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (40, "Umgang mit Kriesensituationen", "Gruppe 2: Streit, Fährlich, Sauer.", 10, 15, 1529994600000, 1530001800000);

INSERT INTO Event
        (id, title, description, patientId, therapistId, start, stop)
    values
        (41, "Umgang mit Kriesensituationen", "Gruppe 2: Streit, Fährlich, Sauer.", 10, 15, 1532586600000, 1532593800000);
