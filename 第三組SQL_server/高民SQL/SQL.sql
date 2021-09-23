--帳號
select * from SQLMemberID;

--一般會員資料
select * from SQLMemberData;

--企業會員資料
select * from SQLMemberDataVendor;

drop table SQLMemberID
drop table SQLMemberData;
drop table SQLMemberDataVendor;

create table SQLMemberID(
--帳號
UserID varchar(50) not null PRIMARY KEY,
--密碼
UserPaws varchar(50) not null,
--類別
ClassMember varchar(10) not null,
--統編
VAT varchar(10))

create table SQLMemberData(
--帳號
UserID varchar(50) not null,
--姓名
Name varchar(50) ,
--生日
Birthday varchar(10) ,
Email varchar(50))

create table SQLMemberDataVendor(
--帳號
UserID varchar(50) not null,
--企業名稱
Vendor varchar(50) ,
--負責人
Principal varchar(10) ,
--地址
Address varchar(50) ,
Email varchar(50) )


exec sp_help SQLMemberID;
--ClassMember 使用者類別 a:一般 b:企業 c:店家 d:員工 fa:一般會員忘記密碼未修改 fb:企業會員忘記密碼未修改
--UserID 使者帳號, UserPaws 使用者密碼, ClassMember 使用者類別, VAT 統一編號
insert into SQLMemberID (UserID, UserPaws, ClassMember) values ('aaa', 'a123', 'a');
insert into SQLMemberID (UserID, UserPaws, ClassMember, VAT) values ('bbb', 'a123', 'b','12345678');
insert into SQLMemberID (UserID, UserPaws, ClassMember, VAT) values ('ccc', 'a123', 'c','12345678');
insert into SQLMemberID (UserID, UserPaws, ClassMember, VAT) values ('ddd', 'a123', 'd','12345678');

--UserID 使者帳號, Name 使者名子, Birthday 生日, Email
insert into SQLMemberData (UserID, Name, Birthday, Email) values ('aaa', 'RRR', '2000-01-01','610323021@gms.ndhu.edu.tw');
insert into SQLMemberData (UserID, Name, Birthday, Email) values ('bbb', 'RRR', '2000-01-01',null);
insert into SQLMemberData (UserID, Name, Birthday, Email) values ('ccc', 'RRR', '2000-01-01','qwe@qwe');
insert into SQLMemberData (UserID, Name, Birthday, Email) values ('ddd', 'RRR', '2000-01-01','qwe@qwe');

--UserID 使者帳號,Vendor 企業名稱,Principal 負責人,Address 地址, Email
insert into SQLMemberDataVendor (UserID, Vendor,Principal ,Address, Email) values ('bbb', 'TT', 'Ted','aaa123ddd','sda@sadad');
insert into SQLMemberDataVendor (UserID, Vendor,Principal ,Address, Email) values ('ccc', 'TT', 'Ted','aaa123ddd','sda@sadad');
insert into SQLMemberDataVendor (UserID, Vendor,Principal ,Address, Email) values ('ddd', 'TT', 'Ted','aaa123ddd','sda@sadad');



update SQLMemberID set UserPaws = 'a123',VAT = '123432323' where UserID = 'aaa'
update SQLMemberData set Name = 'rrrr',Birthday = '2000-01-01',Email = '610323021@gms.ndhu.edu.tw' where UserID = 'aaa'
update SQLMemberDataVendor set Vendor = 'rrrr',Principal = '2000/01/05',Address = 'ssss',Email = '' where UserID = 'bbb'

delete from SQLMemberID where UserID = 'aaa'

select * from SQLMemberID where UsernameID = 'ccc'

select COUNT(UsernameID) from SQLMemberID where UsernameID = 'aaa'

select UsernameID, UserPaws, ClassMember
from SQLMemberID as s


select UsernameID, UserPaws, ClassMember, COUNT(UsernameID) as IDn
from SQLMemberID 
where UsernameID = 'aaa'
group by UsernameID,UserPaws, ClassMember


select COUNT(UsernameID) as IDn
from SQLMemberID 
where UsernameID = 'zzz'
group by UsernameID



