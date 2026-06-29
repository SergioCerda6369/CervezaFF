CREATE TABLE quality (
    id_quality INT AUTO_INCREMENT PRIMARY KEY,
    cantidad_ph INT NOT NULL,
    control_quality BOOLEAN NOT NULL
);

INSERT INTO quality (cantidad_ph, control_quality)
VALUES (4, TRUE);