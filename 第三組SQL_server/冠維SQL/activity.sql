--�s�W���ʸ�ƪ�
create table Activity(
	-- ID �۰ʥͦ��A�q1000 �}�l�A�� PK
	ID int identity(1000,1) primary key not null,
	-- ���ʥD�D�A�̦h30�r
	activityTopic varchar(30) not null,
	-- ���ʶ}�l�ɶ��A��J�榡'2020-10-10 13:00:00'
	startTime datetime not null,
	-- ���ʵ����ɶ��A��J�榡'2020-10-10 13:00:00'
	endTime datetime not null,
	-- ���ʤ��e�A�̦h500 �r
	activityContent varchar(500),
	-- ���������A�ثe�u��1(�����u�f) �B 2(�u�f��)
	activityType int not null
)


-- insert ���
insert into Activity(activityTopic,startTime,endTime,activityContent,activityType)
values('���ƶR�@�e�@','2021-10-10 13:00:00','2021-12-22 11:00:00','���ƶR�@�e�@�A��50���H�W�~��',1),
('���Ƨ馩5��','2021-07-20 12:00:00','2021-07-31 12:00:00','���Ƨ馩5���A�����~��',1),
('�B�~�u�f��','2021-08-01 01:00:00','2022-08-01 01:00:00','�Ҧ��B�~85��',2);


-- �d�߸�ƪ�
select * from Activity;
exec sp_help Activity;

-- �R����ƪ�
drop table Activity

