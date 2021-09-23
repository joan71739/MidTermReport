--���a�n�J��s�W���a��T
create table store(
--���aID�A�۰ʷs�W
storeid numeric(10) identity(1,1) constraint store_storeid_pk PRIMARY KEY,
--�إߤ��,�w�]��������
startdate date default getdate(),
--���a�b���AFK,�Plogin�s��
stuserid varchar(20) constraint store_stuserid_fk REFERENCES login(stuserid),
--���a�W�]�w�̦h25�r�A���o��NULL,��UK
title varchar(50) constraint store_title_nn NOT NULL,
--�����A���o��NULL
manager	varchar(10) constraint store_manager_nn NOT NULL,
--�a�}�A���o��NULL
stadd varchar(80) constraint store_stadd_nn NOT NULL,
--�q��
tel varchar(15),
--²��
intro varchar(200),
--���a�ʭ�,
photo varbinary(max),

constraint store_stuserid_uk UNIQUE(stuserid)
)

--�s�W��ƶi�Jstore
insert into store(stuserid,title,manager,stadd,tel,intro)
values('aa12345','�M�s����shop','���M�Y','�򶩥����s�Ϥ��s�G��20��','02-27412555','�򶩲Ĥ@���M�s�A�ʦ~�ѩ�')

insert into store(stuserid,title,manager,stadd,tel,intro)
values('bb12345','�Z����','���M�Y','�򶩥����s�Ϥ��s�@��20��','02-123456789','�q�f�`�n��')

insert into store(stuserid,title,manager,stadd,tel,intro)
values('dd12345','�й�ڦy�s','������','�򶩥����s�ϷR�|��500��','02-123456789','�`��')

insert into store(stuserid,title,manager,stadd,tel,intro)
values('cc12345','�y�s','������','�򶩥����s�ϷR�|��500��','02-123456789','�`��')

------------------------------------------------
--�d��store
select *
from store

--�R��store
drop table store

--�Q�Υ��~�b���d��
select stuserid
from store
where stuserid in (
				select userid
				from login
				where company='32565252'
)

