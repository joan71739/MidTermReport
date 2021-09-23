/*���~��ƪ�*/
create table prouct(
/*���~id*/
proid numeric(10) identity(1,1) constraint product_proid_pk primary key,
/*���~�إߤ��*/
pstartdate date default getdate(),
/*���aid*/
storeid numeric(10) constraint prouct_storeid_fk REFERENCES store(storeid),
/*���~�W��*/
proname char(20) constraint prooct_proname_nn not null,
/*���~����*/
proprice int constraint prooct_proprice_nn not null,
/*���~²��*/
pinto varchar(200),
/*���~�Ϥ�*/
pphoto varbinary(max) )

/* �`�N:storeid �����n�b store ��ƪ��s�b�~�� */
insert into prouct(storeid, proname,proprice)
values(1,'��������',30),
       (1,'�R�K����',30),
	   (1,'���ì���',55),
	   (1,'�K���B��',45),
	   (1,'���_�ڹp',65)

select*from prouct;

drop table prouct


-------------------------------
/*�q���ƪ�*/
create table ORD(
/*�q��ID:PRIMARY KEY  :�ʪ���ID�Y�Ȥ�U��۰��ର�q��ID*/
ORDID int identity(1,1) constraint ORD_ORDID_pk primary key ,
/*�Ȥ�ID*/
productID int ,
/*�ө�ID*/
storeID int ,
/*�U��ɶ� �۰ʶ�J�{�b�ɶ�*/
shoppDate date default getdate(),
/*���q�檺�`���B*/
total numeric)

select*from ORD

drop table ORD


----------------------------
/*�q����Ӹ�ƪ�:�q�椤�C�@���ӫ~�N���q����Ӹ�ƪ��@�����*/
create table ORDitem(
/*�q��ID*/
ORDID int ,
/*����ID*/
OitemID int identity(100,1) ,
/*�ӫ~*/
poructName varchar(10) not null ,
/*�ӫ~���*/
actualprice numeric,
/*�ӫ~�ƶq*/
QTY numeric,
/*�ӫ~�`���B*/
itemtot numeric,
/*primary key:�q��ID+����ID*/
constraint ORDitem_ORDID_itemID_pk primary key(ORDID,OitemID),
/*FOREIGN key �q��ID��q���ƪ����q��ID*/
constraint ORDitem_ORDIDToORD_fk FOREIGN key(ORDID) REFERENCES ORD(ORDID), )

select*from ORDitem


drop table ORDitem