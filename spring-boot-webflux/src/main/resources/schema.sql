DROP TABLE IF EXISTS spring.user;
DROP TABLE IF EXISTS spring.post;

CREATE TABLE spring.user (
                              id         bigint auto_increment primary key,
                              name       varchar(256) not null,
                              age        int(10) not null,
                              created_at timestamp default CURRENT_TIMESTAMP,
                              updated_at timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
);

CREATE TABLE spring.post (
                              id         bigint auto_increment primary key,
                              title    varchar(500),
                              content    varchar(500),
                              author_id  bigint not null,
                              created_at timestamp default CURRENT_TIMESTAMP,
                              updated_at timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
);

ALTER TABLE spring.post
    ADD FOREIGN KEY (`author_id`) REFERENCES user (`id`);