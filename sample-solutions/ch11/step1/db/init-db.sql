CREATE TABLE images
(
  imageid serial UNIQUE PRIMARY KEY,
  description character varying(10485760) NOT NULL,
  url character varying(255) NOT NULL
);

ALTER TABLE images
  OWNER TO dockeruser;
ALTER ROLE dockeruser CONNECTION LIMIT -1;

-- add image data
INSERT INTO images (description, url) VALUES('vulture on tree', 'images/vulture.png');
INSERT INTO images (description, url) VALUES('female lions', 'images/3-female-lions.png');
INSERT INTO images (description, url) VALUES('antelopes', 'images/antelopes.png');
INSERT INTO images (description, url) VALUES('birds', 'images/birds.png');
INSERT INTO images (description, url) VALUES('buffalo', 'images/buffalo.png');
INSERT INTO images (description, url) VALUES('cheetah', 'images/cheetah.png');
INSERT INTO images (description, url) VALUES('elephants', 'images/elephants.png');
INSERT INTO images (description, url) VALUES('jackal', 'images/jackal.png');
INSERT INTO images (description, url) VALUES('giraffe', 'images/giraffe.png');
INSERT INTO images (description, url) VALUES('hippos', 'images/hippos.png');
INSERT INTO images (description, url) VALUES('male lion', 'images/male-lion.png');
INSERT INTO images (description, url) VALUES('zebra', 'images/zebra.png');