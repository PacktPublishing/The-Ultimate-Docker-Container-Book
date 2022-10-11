CREATE TABLE IF NOT EXISTS species (
  id INT NOT NULL,
  name varchar(250) NOT NULL,
  description varchar(2500) NULL,
  PRIMARY KEY (id)
);

INSERT INTO species(id, name, description) VALUES(1, 'Elephant', 'Big, massive mammal.');
INSERT INTO species(id, name, description) VALUES(2, 'Lion', 'King of the animals living on land.');
INSERT INTO species(id, name, description) VALUES(3, 'Cheetah', 'Fastest living mammal.');
INSERT INTO species(id, name, description) VALUES(4, 'Eagle', 'King of the air.');

CREATE TABLE IF NOT EXISTS races (
  id INT NOT NULL,
  species_id INT NOT NULL,
  name varchar(250) NOT NULL,
  description varchar(2500) NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_species
    FOREIGN KEY(species_id)
    REFERENCES species(id)
);

INSERT INTO races(id, species_id, name, description) VALUES(1, 1, 'Indian Elephant', 'Elephant living in the Indian sub continent. Has no tusks.');
INSERT INTO races(id, species_id, name, description) VALUES(2, 1, 'African Elephant', 'Elephant living in the African sub continent. Has beautiful tusks.');
INSERT INTO races(id, species_id, name, description) VALUES(3, 2, 'African Lion', 'Lion living in the African sub continent.');
INSERT INTO races(id, species_id, name, description) VALUES(4, 3, 'African Cheetah', 'Cheetah living in the African sub continent.');
INSERT INTO races(id, species_id, name, description) VALUES(5, 4, 'American Eagle', 'Eagle living in the North American sub continent.');

CREATE TABLE IF NOT EXISTS photos (
  id UUID NOT NULL,
  species_id INT NOT NULL,
  race_id INT NOT NULL,
  title varchar(250) NOT NULL,
  extension varchar(10) NOT NULL,
  "url" varchar(2500) NOT NULL,
  "description" varchar(2500) NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_photos_species
    FOREIGN KEY(species_id)
    REFERENCES species(id),
  CONSTRAINT fk_photos_races
    FOREIGN KEY(race_id)
    REFERENCES races(id)
);

CREATE TABLE IF NOT EXISTS comments (
  id UUID NOT NULL,
  photo_id UUID NOT NULL,
  "text" varchar(2500) NOT NULL,
  user_id varchar(50) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_comments_photos
    FOREIGN KEY(photo_id)
    REFERENCES photos(id)
);
