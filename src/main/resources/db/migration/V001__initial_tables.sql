
-- Sequences
CREATE SEQUENCE item_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE bom_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE bomline_seq START WITH 1 INCREMENT BY 1;

-- Item
CREATE TABLE item (
                      item_id BIGINT PRIMARY KEY,
                      item_name VARCHAR(255),
                      item_description VARCHAR(255)
);

-- BOM
CREATE TABLE bom (
                     bom_id BIGINT PRIMARY KEY,
                     item_id BIGINT NOT NULL UNIQUE
);

-- BOM Line
CREATE TABLE bom_line (
                          bomline_id BIGINT PRIMARY KEY,
                          bom_id BIGINT,
                          item_id BIGINT,
                          quantity integer
);
