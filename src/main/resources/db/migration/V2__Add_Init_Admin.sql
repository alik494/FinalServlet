insert into usr (id,email, username,password,active)
            values (1,'admin@mail.com','admin','admin',true);
insert into user_role (user_id,roles)
            values (1,'USER'),(1,'ADMIN');

            select nextval('hibernate_sequence')


            insert into usr (id,email, username,password,active)

            SELECT nextval('hibernate_sequence') , 'admin2@mail.com' , 'admin2','admin2',true;


            insert into user_role (roles,user_id)
            values ('USER','3')


            select id from usr where email='admin2@mail.com';


