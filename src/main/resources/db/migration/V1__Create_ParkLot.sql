create table `parking_lot`(
    `id` int not null auto_increment primary key,
    `name` varchar(255) not null unique,
    `capacity` int not null,
    `location` varchar(255) not null
)