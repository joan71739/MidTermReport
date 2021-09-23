--店家登入後新增店家資訊
create table store(
--店家ID，自動新增
storeid numeric(10) identity(1,1) constraint store_storeid_pk PRIMARY KEY,
--建立日期,預設日期為當日
startdate date default getdate(),
--店家帳號，FK,與login連結
stuserid varchar(20) constraint store_stuserid_fk REFERENCES login(stuserid),
--店家名設定最多25字，不得為NULL,為UK
title varchar(50) constraint store_title_nn NOT NULL,
--店長，不得為NULL
manager	varchar(10) constraint store_manager_nn NOT NULL,
--地址，不得為NULL
stadd varchar(80) constraint store_stadd_nn NOT NULL,
--電話
tel varchar(15),
--簡介
intro varchar(200),
--店家封面,
photo varbinary(max),

constraint store_stuserid_uk UNIQUE(stuserid)
)

--新增資料進入store
insert into store(stuserid,title,manager,stadd,tel,intro)
values('aa12345','清新飲料shop','陳清欣','基隆市中山區中山二路20巷','02-27412555','基隆第一間清新，百年老店')

insert into store(stuserid,title,manager,stadd,tel,intro)
values('bb12345','武石藍','陳清欣','基隆市中山區中山一路20巷','02-123456789','廟口常駐店')

insert into store(stuserid,title,manager,stadd,tel,intro)
values('dd12345','請對我尖叫','王美華','基隆市中山區愛四路500號','02-123456789','總店')

insert into store(stuserid,title,manager,stadd,tel,intro)
values('cc12345','尖叫','王美華','基隆市中山區愛四路500號','02-123456789','總店')

------------------------------------------------
--查詢store
select *
from store

--刪除store
drop table store

--利用企業帳號查詢
select stuserid
from store
where stuserid in (
				select userid
				from login
				where company='32565252'
)

