CREATE TABLE IF NOT EXISTS BOOKING (
   ID             SERIAL                 NOT NULL,
   ROOM_ID        VARCHAR(50)                 NOT NULL,
   DAY            INT                         NOT NULL,
   MONTH          INT                         NOT NULL,
   YEAR           INT                         NOT NULL,
   FROM_TIME      INT                         NOT NULL,
   TO_TIME        INT                         NOT NULL,
   TITLE          VARCHAR(200)                NOT NULL,
   USER_ID        VARCHAR(50)                 NOT NULL,
   PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS USERS (
id                SERIAL             NOT NULL,
email              TEXT              NOT NULL,
password           VARCHAR(10)       NOT NULL,
first_name         VARCHAR(50)       NOT NULL,
last_name          VARCHAR(50)       NOT NULL,
phone              VARCHAR(20),
faculty            VARCHAR(50),
picture            TEXT,
PRIMARY KEY (id),
CONSTRAINT email_name UNIQUE (email)
);


CREATE TABLE IF NOT EXISTS BOOKING_BY_USER (
booking_id  INT NOT NULL,        
user_id INT NOT NULL,
PRIMARY KEY(booking_id, user_id)
);

ALTER TABLE BOOKING_BY_USER
ADD CONSTRAINT user_id_fkey
FOREIGN KEY (user_id)
REFERENCES USERS(id)
ON UPDATE CASCADE
ON DELETE RESTRICT;

ALTER TABLE BOOKING_BY_USER
ADD CONSTRAINT booking_id_fkey
FOREIGN KEY (booking_id)
REFERENCES BOOKING(id)
ON UPDATE CASCADE
ON DELETE RESTRICT;

--Run the following command to update the structure of the db:
--Locally: cat seeder.sql | psql -d postgres


