--�d�߸�ƪ�   ��ƪ�W��(forum)
select * from forum;
--�R����ƪ�
drop table forum;
--�s�W�Q�װϸ�ƪ�
create table forum
(
shopName varchar(20) not null,
drink varchar(20) not null,
drinkDiscuss varchar (50)  not null,
drinkstar int not null,
)

-- insert ���
insert into forum(shopName,drink,drinkDiscuss,drinkstar)
values
('�M�Y','�h�h�B�A','�[�p�ܦn��',5),
('�i�G�i','��������','�ܸѪo���٦��G��',4),
('�i�G�i','�گ׬���','�[�F�ե����ڧ��h�Ѱ󪺸��F',5),
('�i�G�i','�եɼڹp','�u������,�i�H�h�j���R50�P�F�o����O���]�˪�',2);
