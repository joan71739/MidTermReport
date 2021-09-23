--新增活動資料表
create table Activity(
	-- ID 自動生成，從1000 開始，為 PK
	ID int identity(1000,1) primary key not null,
	-- 活動主題，最多30字
	activityTopic varchar(30) not null,
	-- 活動開始時間，輸入格式'2020-10-10 13:00:00'
	startTime datetime not null,
	-- 活動結束時間，輸入格式'2020-10-10 13:00:00'
	endTime datetime not null,
	-- 活動內容，最多500 字
	activityContent varchar(500),
	-- 活動類型，目前只有1(限時優惠) 、 2(優惠券)
	activityType int not null
)


-- insert 資料
insert into Activity(activityTopic,startTime,endTime,activityContent,activityType)
values('飲料買一送一','2021-10-10 13:00:00','2021-12-22 11:00:00','飲料買一送一，限50元以上品項',1),
('飲料折扣5元','2021-07-20 12:00:00','2021-07-31 12:00:00','飲料折扣5元，不限品項',1),
('冰品優惠券','2021-08-01 01:00:00','2022-08-01 01:00:00','所有冰品85折',2);


-- 查詢資料表
select * from Activity;
exec sp_help Activity;

-- 刪除資料表
drop table Activity

