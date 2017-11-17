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