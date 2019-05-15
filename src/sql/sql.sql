SELECT TABLE_NAME,
       ENGINE
FROM   information_schema.TABLES
WHERE  TABLE_SCHEMA = 'base-skeleton';

ALTER TABLE ROLE ENGINE = InnoDB;
ALTER TABLE SUBMENU ENGINE = InnoDB;
ALTER TABLE USER ENGINE = InnoDB;
ALTER TABLE USERROLE ENGINE = InnoDB;

drop table test;
drop table test2; 

create TABLE test(id int primary KEY, nombre varchar(10)) engine=INNODB;
create TABLE test2(id2 int primary KEY, id int, nombre varchar(10)) engine=INNODB;

create index idx_test1_test2 on test2(id);
alter table test2 add foreign key(id) REFERENCES test(id) ON DELETE RESTRICT ON UPDATE CASCADE;