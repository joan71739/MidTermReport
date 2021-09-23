--穝糤┍產祅table[login]
create table login(
--┍產眀腹砞﹚程20PK
stuserid varchar(20) constraint login_account_pk PRIMARY KEY,
--┍產盞絏砞﹚程20,ぃ眔NULL
stpassword varchar(20) constraint login_pswd_nn NOT NULL,
--穨参絪砞﹚程15
company varchar(15) NOT NULL,

);

--穝糤戈秈login
insert into login
values('aa12345','12345','88888888'),
	  ('bb12345','12345','52446587'),
	  ('cc12345','12345','88888888'),
	  ('dd12345','12345','52446587');
------------------------------------------------
--琩高login
select *
from login

--埃login
drop table login