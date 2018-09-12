

create table MARKETUSER
(
  FNAME VARCHAR2(30) not null,
  LNAME VARCHAR2(30),
  PASSWORD VARCHAR2(60) default NULL,
  CREATED_AT TIMESTAMP(6),
  EMAIL VARCHAR2(30) not null
    primary key,
  ROLE VARCHAR2(30) not null
)
/

create table MARKETPRODUCT
(
  PRODUCT_NAME VARCHAR2(30) not null
    primary key,
  PRODUCT_PRICE NUMBER not null,
  PRODUCTION_DATE DATE not null,
  EXPIRED_DATE DATE not null,
  PRODUCTION_COMPANY VARCHAR2(30) not null,
  ADMIN_EMAIL VARCHAR2(30) not null
    constraint M_PRODUCT_M_USER_FK
    references MARKETUSER,
  PRODUCT_QUANTITY NUMBER
)
/

create table MARKETCATEGORY
(
  CAT_ID NUMBER not null
    primary key,
  CAT_NAME VARCHAR2(30) not null,
  EMAIL VARCHAR2(30) not null
    constraint M_CATEGORY_M_EMAIL_FK
    references MARKETUSER
)
/

