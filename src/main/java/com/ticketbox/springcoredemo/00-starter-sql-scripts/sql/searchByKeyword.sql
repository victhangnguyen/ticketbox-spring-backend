SELECT id, event_name, organizer_name
FROM event
WHERE event_name LIKE '%jazz%' OR organizer_name LIKE '%jazz%';
-- +----+------------------+----------------+
-- | id | event_name       | organizer_name |
-- +----+------------------+----------------+
-- |  4 | Dem Nhac Jazz    | Jazz Club      |
-- |  5 | Jazz Dem Mua Thu | Jazz Club      |
-- +----+------------------+----------------+
-- (MySQL mac dinh khong phan biet hoa thuong nen "jazz" khop voi "Jazz")
