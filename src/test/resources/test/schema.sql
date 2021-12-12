CREATE TABLE  IF NOT EXISTS ITEM
(
    id                         BIGSERIAL      NOT NULL,
    name                       VARCHAR(50)    NOT NULL,
    description                VARCHAR(50)    NOT NULL,
    brand_name                 VARCHAR(50)    DEFAULT NULL,
    price                      INTEGER        NOT NULL,
    volume                     VARCHAR(50)    DEFAULT NULL,
    dimension                  VARCHAR(50)    DEFAULT NULL,
    package_type               VARCHAR(50)    DEFAULT NULL,
    origin                     VARCHAR(50)    DEFAULT NULL,
    expiration                 VARCHAR(50)    DEFAULT NULL,
    price_per_100g             INTEGER        DEFAULT NULL,
    allergic_info              VARCHAR(50)    DEFAULT NULL,
    model_serial_no            VARCHAR(50)    DEFAULT NULL,
    rating                     INTEGER        DEFAULT NULL,
    search_keyword             VARCHAR(50)    DEFAULT NULL,
    stock                      INTEGER        DEFAULT NULL,
    discount_price             INTEGER        DEFAULT NULL,
    author                     VARCHAR(50)    DEFAULT NULL,
    publisher                  VARCHAR(50)    DEFAULT NULL,
    isbn                       VARCHAR(50)    DEFAULT NULL,
    published_time             DATE           DEFAULT NULL,
    is_oneday_eligible         BOOLEAN        DEFAULT false,
    is_fresh_eligible          BOOLEAN        DEFAULT false,
    status                     INTEGER        DEFAULT 2001,
    status_update_time         TIMESTAMP      DEFAULT NOW(),
    created_time               TIMESTAMP      DEFAULT NOW(),
    category_id                BIGINT         DEFAULT NULL
);

CREATE TABLE  IF NOT EXISTS CATEGORY
(
    id                         BIGSERIAL      NOT NULL,
    name                       VARCHAR(50)    NOT NULL,
    category_type              VARCHAR(50)    NULL DEFAULT 'NOT_ASSIGNED',
    depth_level                VARCHAR(50)    NULL DEFAULT 'DEPTH_ONE',
    is_refundable              BOOLEAN        NULL DEFAULT FALSE,
    status                     INTEGER        DEFAULT 2001,
    status_update_time         TIMESTAMP      NULL DEFAULT NOW()
);

ALTER TABLE ITEM ADD CONSTRAINT FK_CATEGORY FOREIGN KEY (category_id) REFERENCES CATEGORY (id);

CREATE TABLE IF NOT EXISTS MEMBER (
    id                         BIGSERIAL      NOT NULL,
    user_id                    VARCHAR(50)    NOT NULL,
    password                   VARCHAR(255)   NOT NULL,
    name                       VARCHAR(50)    NOT NULL,
    email                      VARCHAR(50)    NOT NULL,
    phone                      VARCHAR(50)    NOT NULL,
    count                      INTEGER        NULL DEFAULT 0,
    last_login_time            TIMESTAMP      DEFAULT NULL,
    status                     INTEGER        NULL DEFAULT 2001,
    status_update_time         TIMESTAMP      NULL DEFAULT NOW(),
    create_time                TIMESTAMP      NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS DELIVERY (
    id                         BIGSERIAL      NOT NULL,
    waybill_number             VARCHAR(50)    NOT NULL,
    memo                       VARCHAR(255)   NOT NULL,
    create_time                TIMESTAMP      NULL DEFAULT NOW(),
    update_time                TIMESTAMP      NULL DEFAULT NOW(),
    status                     INTEGER        DEFAULT 2001,
    status_update_time         TIMESTAMP      NULL DEFAULT NOW(),
    delivery_policy_id         BIGINT         DEFAULT NULL,
    delivery_driver_id         BIGINT         DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS DELIVERY_DRIVER (
    id                         BIGSERIAL      NOT NULL,
    name                       VARCHAR(50)    NOT NULL,
    phone                      VARCHAR(50)    NOT NULL,
    create_time                TIMESTAMP      NULL DEFAULT NOW(),
    update_time                TIMESTAMP      NULL DEFAULT NOW(),
    status                     INTEGER        DEFAULT 2001,
    status_update_time         TIMESTAMP      NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS DELIVERY_POLICY (
    id                         BIGSERIAL      NOT NULL,
    name                       VARCHAR(50)    NOT NULL,
    company                    VARCHAR(50)    NOT NULL,
    fee_policy                 INTEGER        DEFAULT 1002,
    fee_method                 INTEGER        DEFAULT 7270,
    fee_price                  INTEGER        NOT NULL,
    island_mountainous_fee     INTEGER        NOT NULL,
    status                     INTEGER        DEFAULT 2001,
    status_update_time         TIMESTAMP      NULL DEFAULT NOW()
);

ALTER TABLE DELIVERY ADD CONSTRAINT FK_DELIVERY_POLICY FOREIGN KEY (delivery_policy_id) REFERENCES DELIVERY_POLICY (id);
ALTER TABLE DELIVERY ADD CONSTRAINT FK_DELIVERY_DRIVER FOREIGN KEY (delivery_driver_id) REFERENCES DELIVERY_DRIVER (id);