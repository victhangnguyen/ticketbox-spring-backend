-- Path: 00-starter-sql-scripts/04-reset-auto-increment.sql  (phan 3: don sach va dem lai tu 1)
-- CANH BAO: TRUNCATE xoa vinh vien toan bo du lieu. CHI chay tren database thu nghiem.
TRUNCATE TABLE ticketbox_db.event;

SELECT COUNT(*) AS so_dong FROM event;

SELECT AUTO_INCREMENT FROM information_schema.TABLES
WHERE TABLE_SCHEMA = 'ticketbox_db' AND TABLE_NAME = 'event';