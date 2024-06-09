CREATE TABLE `role`
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    created datetime              NULL default CURRENT_TIMESTAMP,
    name    VARCHAR(255)          NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE user
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created       datetime              NULL,
    name          VARCHAR(255)          NULL,
    email         VARCHAR(255)          NULL,
    hash_password VARCHAR(255)          NULL,
    is_verified   BIT(1)                NULL,
    updated       datetime              NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    user_id  BIGINT NOT NULL,
    roles_id BIGINT NOT NULL
);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES `role` (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES user (id);