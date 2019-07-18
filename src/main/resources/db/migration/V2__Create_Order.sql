create table `park_order`(
    `id` int not null auto_increment primary key ,
    `lot_name` varchar(255) not null,
    `car_no` varchar(255) not null ,
    `create_time` bigint not null ,
    `end_time` bigint,
    `status` varchar(255) not null default 'open'
)