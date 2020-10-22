INSERT INTO town (id, name, min_visibility, max_wind) VALUES
    (1, 'Grozny', 10000, 1.0),
    (2, 'Valmiera', 10000, 0.5),
    (3, 'Piza', 10000, 2.0);

INSERT INTO employee (id, first_name, last_name, email, town_id) VALUES
    (1, 'Ilse', 'Muusi', 'ilse.muusi@towermaster.com', 1),
    (2, 'Robert', 'Arent', 'robert.arent@towermaster.com', 1),
    (3, 'Marcel', 'Baader', 'm.baader@towermaster.com', 2),
    (4, 'Eva', 'Zielinska', 'eva@towermaster.com', 3),
    (5, 'Caspar', 'Ollson', 'caspar@towermaster.com', 2);

