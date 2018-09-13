-- auto-generated definition
create table MARKETUSER
(
  FNAME      VARCHAR2(30) not null,
  LNAME      VARCHAR2(30),
  PASSWORD   VARCHAR2(60) default NULL,
  CREATED_AT TIMESTAMP(6),
  EMAIL      VARCHAR2(30) not null
    primary key,
  ROLE       VARCHAR2(30) not null
)
;



create table MARKETCATEGORY
(
  CAT_ID   NUMBER       not null
    primary key,
  CAT_NAME VARCHAR2(30) not null,
  EMAIL    VARCHAR2(30) not null
    constraint M_CATEGORY_M_EMAIL_FK
    references MARKETUSER
)
;



create table MARKET_PRODUCT
(
  PRODUCT_NAME       VARCHAR2(30) not null
    primary key,
  PRODUCT_PRICE      NUMBER       not null,
  PRODUCTION_DATE    DATE         not null,
  EXPIRED_DATE       DATE         not null,
  PRODUCTION_COMPANY VARCHAR2(30) not null,
  ADMIN_EMAIL        VARCHAR2(30) not null
    constraint MPRODUCT_M_USER_FK
    references MARKETUSER,
  PRODUCT_QUANTITY   NUMBER,
  PRODUCT_IMAGE      BLOB,
  CAT_ID             NUMBER
    constraint MPRODUCT_MCATEGORY_CAT_ID_FK
    references MARKETCATEGORY
);











create table MARKET_TRANSACTIONS
(
  TRANSACTION_ID            NUMBER not null
    primary key,
  TRANSACTION_USER_EMAIL    VARCHAR2(30)
    constraint SYS_C006190
    references MARKETUSER,
  TRANSACTION_PRODUCT_NAME  VARCHAR2(30)
    constraint SYS_C006191
    references MARKETPRODUCT,
  TRANSACTION_SELLING_PRICE NUMBER not null,
  TRANSACTION_QUANTITY      NUMBER not null,
  TRANSACTION_PROFIT        NUMBER not null,
  TRANSACTION_DATE          TIMESTAMP(6)
);








