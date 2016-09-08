CREATE TABLE IF NOT EXISTS player
(
	uuid VARCHAR(36) NOT NULL,
	name VARCHAR(16) NOT NULL,
	language VARCHAR(16) NOT NULL,
	favorite INT,
	PRIMARY KEY (uuid)
);

CREATE TABLE IF NOT EXISTS horse
(
	uuid VARCHAR(36) NOT NULL,
	owner VARCHAR(36) NOT NULL,
	id INT NOT NULL,
	name VARCHAR(36) NOT NULL,
	world VARCHAR(16) NOT NULL,
	x INT NOT NULL,
	y INT NOT NULL,
	z INT NOT NULL,
	locked TINYINT(1) NOT NULL,
	protected TINYINT(1) NOT NULL,
	shared TINYINT(1) NOT NULL,
	PRIMARY KEY (uuid),
	CONSTRAINT fk_horse_player
		FOREIGN KEY (owner)
		REFERENCES player (uuid)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
);