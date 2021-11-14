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
    published_time             date           DEFAULT NULL,
    is_oneday_eligible         boolean        DEFAULT false,
    is_fresh_eligible          boolean        DEFAULT false,
    status                     integer        DEFAULT 2001,
    status_update_time         timestamp      DEFAULT Now(),
    created_time               timestamp      DEFAULT Now(),
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
    status                     integer         DEFAULT 2001,
    status_update_time         timestamp      NULL DEFAULT Now(),
    CONSTRAINT PK_CATEGORY PRIMARY KEY (id)
);

ALTER TABLE ITEM ADD CONSTRAINT FK_CATEGORY FOREIGN KEY (category_id) REFERENCES CATEGORY (id);

CREATE TABLE IF NOT EXISTS MEMBER (
    id                          bigserial      NOT NULL,
    user_id                     varchar(50)    NOT NULL,
    password                    varchar(50)    NOT NULL,
    name                        varchar(50)    NOT NULL,
    email                       varchar(50)    NOT NULL,
    phone                       varchar(50)    NOT NULL,
    count                       integer        NULL DEFAULT 0,
    last_login_time             timestamp      DEFAULT NULL,
    status                      integer        NULL DEFAULT 2001,
    status_update_time          timestamp      NULL DEFAULT now()
);