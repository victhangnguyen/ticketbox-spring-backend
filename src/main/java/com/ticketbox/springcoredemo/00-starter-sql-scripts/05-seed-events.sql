-- Path: 00-starter-sql-scripts/05-seed-events.sql
USE ticketbox_db;

-- Them 3 su kien mau (id se la 5, 6, 7 neu bang dang co id 1-4 va bo dem la 5)
INSERT INTO event (event_name, organizer_name, ticket_price) VALUES
  ('Jazz Dem Mua Thu',      'Jazz Club',           280000),
  ('Workshop React Native', 'Code Academy',        450000),
  ('Music Festival 2026',   'TicketBox Organizer', 500000);

SELECT * FROM event;

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 

INSERT INTO event (event_name, organizer_name, ticket_price) VALUES
  ('Liveshow Anh Trai Say Hi', 'VieON Entertainment', 1200000),
  ('Concert Rap Viet Season 4', 'Vie Channel', 1500000),
  ('Dem Nhac Indie - Nhung Thanh Pho Mo Mang', 'Lulita Media', 650000),
  ('Showcase Album Moi HIEUTHUHAI', 'GERDNANG Team', 800000),
  ('Minishow Duong Domic and tlinh', 'Soul Live Project', 450000),
  ('VPop Hits Dem Nhac Lofi', 'Chill Box Studio', 250000),
  ('Concert Vu Bao Tang Cua Nhung Nuoi Tiec', 'Trong Dong Palace', 900000),
  ('Festival Am Nhac Hiphop Dangrangto and MCK', 'SpaceSpeakers Group', 1100000),
  ('Dem Nhac Tre Acoustic Chill', 'Goc Ban Cong', 180000),
  ('Gala Nhac Viet Trending', 'TProduction', 700000);

