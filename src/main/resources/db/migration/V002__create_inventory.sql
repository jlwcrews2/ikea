create sequence if not exists inventory_seq start with 1 increment by 1;

create table if not exists inventory(
    inventory_id bigint primary key,
    inventory_item_id bigint not null,
    inventory_qty_on_hand int,
    inventory_qty_on_reserve int
);