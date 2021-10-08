CREATE TABLE  IF NOT EXISTS ITEM
(
    id                         bigserial      NOT NULL,
    name                       varchar(50)    NOT NULL DEFAULT '',
    description                varchar(50)    NOT NULL DEFAULT '',
    brand_name                 varchar(50)    NOT NULL DEFAULT '자체제작',
    price                      integer        NOT NULL DEFAULT 0,
    volume                     varchar(50)    NULL DEFAULT '',
    dimension                  varchar(50)    NULL DEFAULT '',
    package_type               varchar(50)    NULL DEFAULT '',
    origin                     varchar(50)    NULL DEFAULT '',
    expiration                 varchar(50)    NULL DEFAULT '',
    price_per_100g             integer        NULL DEFAULT 0,
    allergic_info              varchar(50)    NULL DEFAULT '',
    model_serial_no            varchar(50)    NULL DEFAULT '',
    rating                     integer        NULL DEFAULT 0,
    search_keyword             varchar(50)    NULL DEFAULT '',
    stock                      integer        NULL DEFAULT 0,
    discount_price             integer        NULL DEFAULT 0,
    author                     varchar(50)    NULL DEFAULT '',
    publisher                  varchar(50)    NULL DEFAULT '',
    isbn                       varchar(50)    NULL DEFAULT '',
    published_time             date           NULL DEFAULT Now(),
    is_oneday_eligible         boolean        NULL DEFAULT false,
    is_fresh_eligible          boolean        NULL DEFAULT false,
    status                     varchar(50)    NULL DEFAULT 'ACTIVATED',
    status_update_time         timestamp      NULL DEFAULT Now(),
    created_time               timestamp      NULL DEFAULT Now(),
    CONSTRAINT PK_ITEM PRIMARY KEY (id)
);

CREATE TABLE  IF NOT EXISTS CATEGORY
(
    id                          bigserial      NOT NULL,
    name                        varchar(50)    NOT NULL DEFAULT '',
    category_type               varchar(50)    NULL DEFAULT 'NOT_ASSIGNED',
    depth_level                 varchar(50)    NULL DEFAULT 'DEPTH_ONE',
    is_refundable               boolean        NULL DEFAULT false,
    status                     varchar(50)    NULL DEFAULT 'ACTIVATED',
    status_update_time         timestamp      NULL DEFAULT Now(),
    CONSTRAINT PK_CATEGORY PRIMARY KEY (id)
);