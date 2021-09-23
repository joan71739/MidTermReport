--�b��
select * from SQLMemberID;

--�@��|�����
select * from SQLMemberData;

--���~�|�����
select * from SQLMemberDataVendor;

drop table SQLMemberID
drop table SQLMemberData;
drop table SQLMemberDataVendor;

create table SQLMemberID(
--�b��
UserID varchar(50) not null PRIMARY KEY,
--�K�X
UserPaws varchar(50) not null,
--���O
ClassMember varchar(10) not null,
--�νs
VAT varchar(10))

create table SQLMemberData(
--�b��
UserID varchar(50) not null,
--�m�W
Name varchar(50) ,
--�ͤ�
Birthday varchar(10) ,
Email varchar(50))

create table SQLMemberDataVendor(
--�b��
UserID varchar(50) not null,
--���~�W��
Vendor varchar(50) ,
--�t�d�H
Principal varchar(10) ,
--�a�}
Address varchar(50) ,
Email varchar(50) )


exec sp_help SQLMemberID;
--ClassMember �ϥΪ����O a:�@�� b:���~ c:���a d:���u fa:�@��|���ѰO�K�X���ק� fb:���~�|���ѰO�K�X���ק�
--UserID �Ϫ̱b��, UserPaws �ϥΪ̱K�X, ClassMember �ϥΪ����O, VAT �Τ@�s��
insert into SQLMemberID (UserID, UserPaws, ClassMember) values ('aaa', 'a123', 'a');
insert into SQLMemberID (UserID, UserPaws, ClassMember, VAT) values ('bbb', 'a123', 'b','12345678');
insert into SQLMemberID (UserID, UserPaws, ClassMember, VAT) values ('ccc', 'a123', 'c','12345678');
insert into SQLMemberID (UserID, UserPaws, ClassMember, VAT) values ('ddd', 'a123', 'd','12345678');

--UserID �Ϫ̱b��, Name �Ϫ̦W�l, Birthday �ͤ�, Email
insert into SQLMemberData (UserID, Name, Birthday, Email) values ('aaa', 'RRR', '2000-01-01','610323021@gms.ndhu.edu.tw');
insert into SQLMemberData (UserID, Name, Birthday, Email) values ('bbb', 'RRR', '2000-01-01',null);
insert into SQLMemberData (UserID, Name, Birthday, Email) values ('ccc', 'RRR', '2000-01-01','qwe@qwe');
insert into SQLMemberData (UserID, Name, Birthday, Email) values ('ddd', 'RRR', '2000-01-01','qwe@qwe');

--UserID �Ϫ̱b��,Vendor ���~�W��,Principal �t�d�H,Address �a�}, Email
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



