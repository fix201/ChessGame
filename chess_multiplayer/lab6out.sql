drop table User;
drop table Contact;
drop table Messages;

CREATE TABLE User
        (username            varchar (25),
        password          varchar (25)
      );
CREATE TABLE Contact
        (username        varchar (25),
         contact_username     varchar (50))
        ;
CREATE TABLE Messages
        (sender_username   varchar (25),
         receiver_username   varchar (50),
         message   varchar (100),
         date_sent    datetime)
        ;
alter table User
 add constraint User_username_pk primary key(username);

alter table Contact
 add constraint Contact_username_contact_username_pk primary key(username,contact_username);

alter table Messages
 add constraint Messages_sender_username_receiver_username_pk primary key(sender_username,receiver_username);

alter table Contact
 add constraint Contact_username_fk foreign key(username)
 references User(username);

alter table Contact
 add constraint Contact_contact_username_fk foreign key(contact_username)
 references User(username);

alter table Messages
add constraint Messages_sender_username_fk foreign key(sender_username)
references User(username);

alter table Messages
add constraint Messages_receiver_username_fk foreign key(receiver_username)
references User(username);

commit;
