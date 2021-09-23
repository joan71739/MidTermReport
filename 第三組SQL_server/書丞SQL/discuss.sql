--查詢資料表   資料表名稱(forum)
select * from forum;
--刪除資料表
drop table forum;
--新增討論區資料表
create table forum
(
shopName varchar(20) not null,
drink varchar(20) not null,
drinkDiscuss varchar (50)  not null,
drinkstar int not null,
)

-- insert 資料
insert into forum(shopName,drink,drinkDiscuss,drinkstar)
values
('清欣','多多翡翠','加厚很好喝',5),
('可佈可','熟成紅茶','很解油膩還有果香',4),
('可佈可','胭脂紅茶','加了白玉讓我找到去天堂的路了',5),
('可佈可','白玉歐雷','真的不行,可以去隔壁買50嵐了這兩分是給包裝的',2);
