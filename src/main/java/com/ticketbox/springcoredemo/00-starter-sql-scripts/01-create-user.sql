-- Drop user first if they exist
DROP USER if exists 'ticketbox_user'@'localhost' ;

-- Now create user with prop privileges
CREATE USER 'ticketbox_user'@'localhost' IDENTIFIED BY 'ticketbox_password';

GRANT ALL PRIVILEGES ON * . * TO 'ticketbox_user'@'localhost';
