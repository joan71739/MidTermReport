/*產品資料表*/
create table prouct(
/*產品id*/
proid numeric(10) identity(1,1) constraint product_proid_pk primary key,
/*產品建立日期*/
pstartdate date default getdate(),
/*店家id*/
storeid numeric(10) constraint prouct_storeid_fk REFERENCES store(storeid),
/*產品名稱*/
proname char(20) constraint prooct_proname_nn not null,
/*產品價格*/
proprice int constraint prooct_proprice_nn not null,
/*產品簡介*/
pinto varchar(200),
/*產品圖片*/
pphoto varbinary(max) )

/* 注意:storeid 必須要在 store 資料表中存在才行 */
insert into prouct(storeid, proname,proprice)
values(1,'熟成紅茶',30),
       (1,'麗春紅茶',30),
	   (1,'雪藏紅茶',55),
	   (1,'春梅冰茶',45),
	   (1,'紅寶歐雷',65)

select*from prouct;

drop table prouct


-------------------------------
/*訂單資料表*/
create table ORD(
/*訂單ID:PRIMARY KEY  :購物車ID若客戶下單自動轉為訂單ID*/
ORDID int identity(1,1) constraint ORD_ORDID_pk primary key ,
/*客戶ID*/
productID int ,
/*商店ID*/
storeID int ,
/*下單時間 自動填入現在時間*/
shoppDate date default getdate(),
/*此訂單的總金額*/
total numeric)

select*from ORD

drop table ORD


----------------------------
/*訂單明細資料表:訂單中每一項商品就為訂單明細資料表的一筆資料*/
create table ORDitem(
/*訂單ID*/
ORDID int ,
/*明細ID*/
OitemID int identity(100,1) ,
/*商品*/
poructName varchar(10) not null ,
/*商品單價*/
actualprice numeric,
/*商品數量*/
QTY numeric,
/*商品總金額*/
itemtot numeric,
/*primary key:訂單ID+明細ID*/
constraint ORDitem_ORDID_itemID_pk primary key(ORDID,OitemID),
/*FOREIGN key 訂單ID對訂單資料表中的訂單ID*/
constraint ORDitem_ORDIDToORD_fk FOREIGN key(ORDID) REFERENCES ORD(ORDID), )

select*from ORDitem


drop table ORDitem