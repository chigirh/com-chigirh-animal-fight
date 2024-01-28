CREATE TABLE IF NOT EXISTS `users`(
    `user_id` VARCHAR(300) NOT NULL,
    `user_name` VARCHAR(300) NOT NULL,
    PRIMARY KEY(`user_id`)
);

CREATE TABLE IF NOT EXISTS `users_sec`(
    `user_id` VARCHAR(300) NOT NULL,
    `password` VARCHAR(300) NOT NULL,
    `is_init` BOOLEAN NOT NULL DEFAULT false,
    `init_code` VARCHAR(300) ,
    PRIMARY KEY(`user_id`)
);
