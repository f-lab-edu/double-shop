CREATE TABLE  IF NOT EXISTS ITEM
(
    id                         bigserial      NOT NULL,
    name                       varchar(50)    NOT NULL,
    description                varchar(50)    NOT NULL,
    brand_name                 varchar(50)    DEFAULT NULL,
    price                      integer        NOT NULL,
    volume                     varchar(50)    DEFAULT NULL,
    dimension                  varchar(50)    DEFAULT NULL,
    package_type               varchar(50)    DEFAULT NULL,
    origin                     varchar(50)    DEFAULT NULL,
    expiration                 varchar(50)    DEFAULT NULL,
    price_per_100g             integer        DEFAULT NULL,
    allergic_info              varchar(50)    DEFAULT NULL,
    model_serial_no            varchar(50)    DEFAULT NULL,
    rating                     integer        DEFAULT NULL,
    search_keyword             varchar(50)    DEFAULT NULL,
    stock                      integer        DEFAULT NULL,
    discount_price             integer        DEFAULT NULL,
    author                     varchar(50)    DEFAULT NULL,
    publisher                  varchar(50)    DEFAULT NULL,
    isbn                       varchar(50)    DEFAULT NULL,
    published_time             date           NULL DEFAULT Now(),
    is_oneday_eligible         boolean        NULL DEFAULT false,
    is_fresh_eligible          boolean        NULL DEFAULT false,
    status                     varchar(50)    NULL DEFAULT 'ACTIVATED',
    status_update_time         timestamp      NULL DEFAULT Now(),
    created_time               timestamp      NULL DEFAULT Now(),
    category_id                bigint         DEFAULT NULL,
    CONSTRAINT PK_ITEM PRIMARY KEY (id)
);

CREATE TABLE  IF NOT EXISTS CATEGORY
(
    id                          bigserial      NOT NULL,
    name                        varchar(50)    NOT NULL,
    category_type               varchar(50)    NULL DEFAULT 'NOT_ASSIGNED',
    depth_level                 varchar(50)    NULL DEFAULT 'DEPTH_ONE',
    is_refundable               boolean        NULL DEFAULT false,
    status                     varchar(50)    NULL DEFAULT 'ACTIVATED',
    status_update_time         timestamp      NULL DEFAULT Now(),
    CONSTRAINT PK_CATEGORY PRIMARY KEY (id)
);

ALTER TABLE ITEM ADD CONSTRAINT FK_CATEGORY FOREIGN KEY (category_id) REFERENCES CATEGORY (id);