create table person(
	no number(2),
	name varchar2(10),
	weight number(4,1),
	birthday date	
)


insert into person values(1, 'ÀÌÁöÀº', 40.3, sysdate)
select * from person