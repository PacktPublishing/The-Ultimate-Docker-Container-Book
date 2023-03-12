-- create table for images

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
INSERT INTO images (description, url) VALUES('cat image', 'images/vulture.png');
INSERT INTO images (description, url) VALUES('cat image', 'images/3-female-lions.png');
INSERT INTO images (description, url) VALUES('cat image', 'images/antelopes.png');
INSERT INTO images (description, url) VALUES('cat image', 'images/birds.png');
INSERT INTO images (description, url) VALUES('cat image', 'images/buffalo.png');
INSERT INTO images (description, url) VALUES('cat image', 'images/cheetah.png');
INSERT INTO images (description, url) VALUES('cat image', 'images/elephants.png');
INSERT INTO images (description, url) VALUES('cat image', 'images/jackal.png');
INSERT INTO images (description, url) VALUES('cat image', 'images/giraffe.png');
INSERT INTO images (description, url) VALUES('cat image', 'images/hippos.png');
INSERT INTO images (description, url) VALUES('cat image', 'images/male-lion.png');
INSERT INTO images (description, url) VALUES('cat image', 'images/zebra.png');