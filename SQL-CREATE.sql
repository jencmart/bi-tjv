
CREATE TABLE producer (
    producer_id      INTEGER NOT NULL,
    nazev            VARCHAR2(50 CHAR) NOT NULL
);

ALTER TABLE producer ADD CONSTRAINT producer_pk PRIMARY KEY ( producer_id );
--------------------------------------------------------------------------------

CREATE TABLE item (
    item_id               INTEGER NOT NULL,
    popis                 VARCHAR2(50 CHAR),
    producer_id           INTEGER NOT NULL
);
ALTER TABLE item ADD CONSTRAINT item_pk PRIMARY KEY ( item_id );
--------------------------------------------------------------------------------

CREATE TABLE customer (
    customer_id           INTEGER NOT NULL,
    jmeno                 VARCHAR2(50 CHAR)
);
ALTER TABLE customer ADD CONSTRAINT customer_pk PRIMARY KEY ( customer_id );
--------------------------------------------------------------------------------


CREATE TABLE item_customer (
    item_customer_id      INTEGER NOT NULL,
    jmeno                 VARCHAR2(50 CHAR),
    item_id               INTEGER NOT NULL,
    customer_id           INTEGER NOT NULL
);
ALTER TABLE item_customer ADD CONSTRAINT item_customer_pk PRIMARY KEY ( item_customer_id );
--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
--------------------------------------------------------------------------------



ALTER TABLE item ADD CONSTRAINT item_producer_fk FOREIGN KEY ( producer_id )
    REFERENCES producer ( producer_id );


ALTER TABLE item_customer ADD CONSTRAINT item_customer_c_fk FOREIGN KEY ( customer_id )
    REFERENCES customer ( customer_id );


ALTER TABLE item_customer ADD CONSTRAINT item_customer_i_fk FOREIGN KEY ( item_id )
    REFERENCES item ( item_id );


--------------------------------------------------------------------------------


CREATE TABLE xxx (
    xxx_id                 INTEGER NOT NULL,
    nadpis                 VARCHAR2(50 CHAR)  ,
    telo                   VARCHAR2(500 CHAR) ,
    item_id            INTEGER NOT NULL
);
ALTER TABLE xxx ADD CONSTRAINT xxx_pk PRIMARY KEY ( xxx_id );
