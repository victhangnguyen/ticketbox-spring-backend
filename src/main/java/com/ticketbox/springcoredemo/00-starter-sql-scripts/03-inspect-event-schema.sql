-- Path: 00-starter-sql-scripts/03-inspect-event-schema.sql
USE ticketbox_db;

-- Xem luoc do bang: tim cot co Key = PRI va Extra = auto_increment
DESCRIBE event;

-- Xem du lieu hien co (3 dong tu Lesson 03)
SELECT * FROM event;
