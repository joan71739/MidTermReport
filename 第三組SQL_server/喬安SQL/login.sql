--�s�W���a�n�Jtable[login]
create table login(
--���a�b���]�w�̦h20�Ӧr�A��PK
stuserid varchar(20) constraint login_account_pk PRIMARY KEY,
--���a�K�X�]�w�̦h20�Ӧr,���o��NULL
stpassword varchar(20) constraint login_pswd_nn NOT NULL,
--���~�νs�]�w�̦h15�Ӧr
company varchar(15) NOT NULL,

);

--�s�W��ƶi�Jlogin
insert into login
values('aa12345','12345','88888888'),
	  ('bb12345','12345','52446587'),
	  ('cc12345','12345','88888888'),
	  ('dd12345','12345','52446587');
------------------------------------------------
--�d��login
select *
from login

--�R��login
drop table login