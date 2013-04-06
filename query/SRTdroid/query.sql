create table user (
	user_name varchar(75) primary key,
	password varchar(75) not null,
	peran int(1) not null,
	nama varchar(50) not null,
	kelamin varchar(10) not null,
	alamat varchar(100) not null,
	telepon varchar(30)
);

insert into user ('user_name', 'password', 'peran', 'nama', 'kelamin', 'alamat') values ('admin', md5('admin'), 1, 'admin', 'pria', 'Depok');