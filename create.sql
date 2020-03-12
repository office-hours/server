create table appointment (appointment_id CHAR(16) FOR BIT DATA not null, created timestamp not null, end_time timestamp not null, start_time timestamp not null, status integer not null, subject integer, updated timestamp not null, student_id CHAR(16) FOR BIT DATA, teacher_id CHAR(16) FOR BIT DATA, primary key (appointment_id))
create table base_user (user_id CHAR(16) FOR BIT DATA not null, created timestamp not null, email varchar(255) not null, nickname varchar(255), oauth varchar(4696) not null, primary key (user_id))
create table policy (policy_id CHAR(16) FOR BIT DATA not null, block_time time not null, created timestamp not null, end_available timestamp not null, start_available timestamp not null, updated timestamp not null, teacher_id CHAR(16) FOR BIT DATA, primary key (policy_id))
create table student (student_id CHAR(16) FOR BIT DATA not null, student_name varchar(255), user_id CHAR(16) FOR BIT DATA, primary key (student_id))
create table teacher (teacher_id CHAR(16) FOR BIT DATA not null, teacher_name varchar(255), user_id CHAR(16) FOR BIT DATA, primary key (teacher_id))
create index IDXkqavgwjmxr5e34lbeg5rtte9e on appointment (status)
create index IDXko8qd6n0wt9wgq61vd2w8r3wb on appointment (start_time)
create index IDXllisg0htxs55iy7cs99j3dct5 on appointment (end_time)
create index IDX7dh64eexoawn8rio1d54x7v4r on appointment (created)
create index IDXtmwxjgd8u97v7076bh5pj0l91 on base_user (created)
alter table base_user add constraint UK_b4hwa8i589s1em8fh6wn5gw4a unique (email)
create unique index UK_32fybp3bsdbupnf499nfeoj1c on base_user (nickname)
alter table base_user add constraint UK_g1pfh2eome2yongg8lclc75k3 unique (oauth)
create index IDX6l90hm2kqu4m9os762cg1yh5d on policy (created)
create unique index UK_mdudq3fkcqow8irt5gcc6cbxj on teacher (teacher_name)
alter table appointment add constraint FKqmgdc0smrfp9823rjugqk97nm foreign key (student_id) references student
alter table appointment add constraint FKmlbf2026pfjlrfnyq72tg3035 foreign key (teacher_id) references teacher
alter table policy add constraint FKggy7dcp7o9ca74llhn9j94pfy foreign key (teacher_id) references teacher
alter table student add constraint FK1jeukvbpttk4j1v8imcjc3npo foreign key (user_id) references base_user
alter table teacher add constraint FKecdl73aya6qa435nabwrb6sti foreign key (user_id) references base_user
create table appointment (appointment_id CHAR(16) FOR BIT DATA not null, created timestamp not null, end_time timestamp not null, start_time timestamp not null, status integer not null, subject integer, updated timestamp not null, student_id CHAR(16) FOR BIT DATA, teacher_id CHAR(16) FOR BIT DATA, primary key (appointment_id))
create table base_user (user_id CHAR(16) FOR BIT DATA not null, created timestamp not null, email varchar(255) not null, nickname varchar(255), oauth varchar(4696) not null, primary key (user_id))
create table policy (policy_id CHAR(16) FOR BIT DATA not null, block_time time not null, created timestamp not null, end_available timestamp not null, start_available timestamp not null, updated timestamp not null, teacher_id CHAR(16) FOR BIT DATA, primary key (policy_id))
create table student (student_id CHAR(16) FOR BIT DATA not null, student_name varchar(255), user_id CHAR(16) FOR BIT DATA, primary key (student_id))
create table teacher (teacher_id CHAR(16) FOR BIT DATA not null, teacher_name varchar(255), user_id CHAR(16) FOR BIT DATA, primary key (teacher_id))
create index IDXkqavgwjmxr5e34lbeg5rtte9e on appointment (status)
create index IDXko8qd6n0wt9wgq61vd2w8r3wb on appointment (start_time)
create index IDXllisg0htxs55iy7cs99j3dct5 on appointment (end_time)
create index IDX7dh64eexoawn8rio1d54x7v4r on appointment (created)
create index IDXtmwxjgd8u97v7076bh5pj0l91 on base_user (created)
alter table base_user add constraint UK_b4hwa8i589s1em8fh6wn5gw4a unique (email)
create unique index UK_32fybp3bsdbupnf499nfeoj1c on base_user (nickname)
alter table base_user add constraint UK_g1pfh2eome2yongg8lclc75k3 unique (oauth)
create index IDX6l90hm2kqu4m9os762cg1yh5d on policy (created)
create unique index UK_mdudq3fkcqow8irt5gcc6cbxj on teacher (teacher_name)
alter table appointment add constraint FKqmgdc0smrfp9823rjugqk97nm foreign key (student_id) references student
alter table appointment add constraint FKmlbf2026pfjlrfnyq72tg3035 foreign key (teacher_id) references teacher
alter table policy add constraint FKggy7dcp7o9ca74llhn9j94pfy foreign key (teacher_id) references teacher
alter table student add constraint FK1jeukvbpttk4j1v8imcjc3npo foreign key (user_id) references base_user
alter table teacher add constraint FKecdl73aya6qa435nabwrb6sti foreign key (user_id) references base_user
