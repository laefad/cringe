
create table author (
    id int8 generated by default as identity, 
    birth_date date, 
    death_date date, 
    description varchar(255), 
    middlename varchar(255), 
    name varchar(255) not null, 
    surname varchar(255) not null, 
    primary key (id)
);

create table author_books (
    authors_id int8 not null, 
    books_id int8 not null, 
    primary key (authors_id, books_id)
);

create table book (
    id int8 generated by default as identity, 
    description varchar(255), 
    name varchar(255) not null, 
    publication_date date, 
    primary key (id)
);

create table book_tags (
    book_id int8 not null, 
    tags_id int8 not null, 
    primary key (book_id, tags_id)
);

create table tag (
    id int8 generated by default as identity, 
    name varchar(255) not null, 
    primary key (id)
);

create table tag_list (
    book_id int8 not null, 
    username varchar(255) not null, 
    primary key (book_id, username)
);

create table tag_list_tags (
    tag_list_book_id int8 not null, 
    tag_list_username varchar(255) not null, 
    tags_id int8 not null, 
    primary key (tag_list_book_id, tag_list_username, tags_id)
);

create table user_book_details (
    username varchar(255) not null, 
    primary key (username)
);

create table user_book_details_books (
    users_username varchar(255) not null, 
    books_id int8 not null, 
    primary key (users_username, books_id)
);

alter table if exists tag_list_tags 
    add constraint UK_92683bgw7dtu1faaiy96l9nhq unique (tags_id);

alter table if exists author_books 
    add constraint FKr514ej8rhei197wx3nrvp0qie foreign key (books_id) references book;

alter table if exists author_books 
    add constraint FKemnd34muvgwnr8a8pteuqrkxh foreign key (authors_id) references author;

alter table if exists book_tags 
    add constraint FKsky6wumpk8q486i2lecduct0d foreign key (tags_id) references tag;

alter table if exists book_tags 
    add constraint FKgrb34gudrjkbeew59bty9sh45 foreign key (book_id) references book;

alter table if exists tag_list 
    add constraint FK1cbvbsb1pcki35t7sjywh0cgt foreign key (book_id) references book;

alter table if exists tag_list 
    add constraint FK8rhdw3gy6q3x9qc05o1l2jbr2 foreign key (username) references user_book_details;

alter table if exists tag_list_tags 
    add constraint FKaralexbhu67bfstoenwvw28s3 foreign key (tags_id) references tag;
    
alter table if exists tag_list_tags 
    add constraint FKltneya6w8o3w0rjs2jmld0mr2 foreign key (tag_list_book_id, tag_list_username) references tag_list;

alter table if exists user_book_details_books 
    add constraint FKpdvh6xko5bek0lsavah4x025w foreign key (books_id) references book;

alter table if exists user_book_details_books 
    add constraint FKibqtmpt7y9adol3csafrdnw4i foreign key (users_username) references user_book_details;
