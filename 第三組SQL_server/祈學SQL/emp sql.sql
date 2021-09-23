create table EMP(
	--員工id自動產生，從1000開始
	id int primary key not null identity(1000,1),
	--員工姓名
	name varchar(20) not null,
	--員工職稱
	job varchar(20) not null,
	--員工入職日期
	hiredate datetime not null,
	--員工薪水
	sal int not null 
)

insert into EMP(name,job,hiredate,sal)
values('Mary','store manager','2019-02-08',60000),
('Tony','manager','2019-02-08',48000),
('Mike','clerk','2019-03-08',30000);

select * from EMP;

drop table EMP;