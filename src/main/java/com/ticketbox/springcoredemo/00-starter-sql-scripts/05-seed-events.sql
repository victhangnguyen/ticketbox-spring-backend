-- Path: 00-starter-sql-scripts/05-seed-events.sql
USE ticketbox_db;

-- Them 3 su kien mau (id se la 5, 6, 7 neu bang dang co id 1-4 va bo dem la 5)
INSERT INTO event (event_name, organizer_name, ticket_price) VALUES
  ('Jazz Dem Mua Thu',      'Jazz Club',           280000),
  ('Workshop React Native', 'Code Academy',        450000),
  ('Music Festival 2026',   'TicketBox Organizer', 500000);

SELECT * FROM event;
