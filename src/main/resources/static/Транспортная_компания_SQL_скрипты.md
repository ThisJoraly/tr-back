## Генерация таблиц в БД

```sql
/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     09.11.2024 18:28:32                          */
/* Author:         Danil Scheglov                               */
/*==============================================================*/

/*==============================================================*/
/* Table: car                                                   */
/*==============================================================*/
create table car (
   car_id               SERIAL               not null,
   driver_id            INT4                 not null,
   car_number           VARCHAR(20)          not null,
   car_model            VARCHAR(50)          not null,
   car_brand            VARCHAR(50)          not null,
   car_capacity         DECIMAL(10,2)        not null,
   car_mileage          INT4                 not null,
   car_condition        VARCHAR(20)          not null,
   car_last_maintenance_date DATE                 null,
   constraint PK_CAR primary key (car_id)
);

comment on column car.car_condition is
'Исправно
На обслуживании
На ремонте';

/*==============================================================*/
/* Index: car_PK                                                */
/*==============================================================*/
create unique index car_PK on car (
car_id
);

/*==============================================================*/
/* Index: control_FK                                            */
/*==============================================================*/
create  index control_FK on car (
driver_id
);

/*==============================================================*/
/* Index: idx_car_driver                                        */
/*==============================================================*/
create  index idx_car_driver on car (
driver_id
);

/*==============================================================*/
/* Table: cargo                                                 */
/*==============================================================*/
create table cargo (
   cargo_id             SERIAL               not null,
   order_id             INT4                 not null,
   cargo_type           VARCHAR(50)          not null,
   cargo_weight         DECIMAL(10,2)        not null,
   cargo_volume         DECIMAL(10,2)        not null,
   constraint PK_CARGO primary key (cargo_id)
);

/*==============================================================*/
/* Index: cargo_PK                                              */
/*==============================================================*/
create unique index cargo_PK on cargo (
cargo_id
);

/*==============================================================*/
/* Index: contains_FK                                           */
/*==============================================================*/
create  index contains_FK on cargo (
order_id
);

/*==============================================================*/
/* Index: idx_cargo_order                                       */
/*==============================================================*/
create  index idx_cargo_order on cargo (
order_id
);

/*==============================================================*/
/* Table: client                                                */
/*==============================================================*/
create table client (
   client_id            SERIAL               not null,
   client_name          VARCHAR(100)         not null,
   client_phone         VARCHAR(25)          not null,
   client_email         VARCHAR(100)         null,
   client_address       VARCHAR(255)         null,
   client_type          VARCHAR(50)          not null,
   constraint PK_CLIENT primary key (client_id)
);

comment on column client.client_type is
'Физическое лицо
Юридическое лицо';

/*==============================================================*/
/* Index: client_PK                                             */
/*==============================================================*/
create unique index client_PK on client (
client_id
);

/*==============================================================*/
/* Table: dispatcher                                            */
/*==============================================================*/
create table dispatcher (
   dispatcher_id        SERIAL               not null,
   dispatcher_surname   VARCHAR(50)          not null,
   dispatcher_name      VARCHAR(50)          not null,
   dispatcher_patronymic VARCHAR(50)          null,
   dispatcher_phone     VARCHAR(25)          not null,
   dispatcher_email     VARCHAR(100)         null,
   dispatcher_start_сhange DATE                 not null,
   dispatcher_end_change DATE                 not null,
   constraint PK_DISPATCHER primary key (dispatcher_id)
);

/*==============================================================*/
/* Index: dispatcher_PK                                         */
/*==============================================================*/
create unique index dispatcher_PK on dispatcher (
dispatcher_id
);

/*==============================================================*/
/* Table: driver                                                */
/*==============================================================*/
create table driver (
   driver_id            SERIAL               not null,
   driver_surname       VARCHAR(50)          not null,
   driver_name          VARCHAR(50)          not null,
   driver_patronymic    VARCHAR(50)          null,
   driver_phone         VARCHAR(25)          not null,
   driver_experience    INT4                 not null,
   constraint PK_DRIVER primary key (driver_id)
);

/*==============================================================*/
/* Index: driver_PK                                             */
/*==============================================================*/
create unique index driver_PK on driver (
driver_id
);

/*==============================================================*/
/* Table: flight                                                */
/*==============================================================*/
create table flight (
   flight_id            SERIAL               not null,
   driver_id            INT4                 not null,
   dispatcher_id        INT4                 not null,
   car_id               INT4                 not null,
   flight_startpoint    VARCHAR(255)         not null,
   flight_endpoint      VARCHAR(255)         not null,
   flight_distance      DECIMAL(10,2)        not null,
   flight_departure_date DATE                 not null,
   constraint PK_FLIGHT primary key (flight_id)
);

/*==============================================================*/
/* Index: flight_PK                                             */
/*==============================================================*/
create unique index flight_PK on flight (
flight_id
);

/*==============================================================*/
/* Index: performs_FK                                           */
/*==============================================================*/
create  index performs_FK on flight (
driver_id
);

/*==============================================================*/
/* Index: assign_FK                                             */
/*==============================================================*/
create  index assign_FK on flight (
dispatcher_id
);

/*==============================================================*/
/* Index: service_FK                                            */
/*==============================================================*/
create  index service_FK on flight (
car_id
);

/*==============================================================*/
/* Index: idx_flight_driver                                     */
/*==============================================================*/
create  index idx_flight_driver on flight (
driver_id
);

/*==============================================================*/
/* Index: idx_flight_dispatcher                                 */
/*==============================================================*/
create  index idx_flight_dispatcher on flight (
dispatcher_id
);

/*==============================================================*/
/* Index: idx_flight_car                                        */
/*==============================================================*/
create  index idx_flight_car on flight (
car_id
);

/*==============================================================*/
/* Table: maintenance_request                                   */
/*==============================================================*/
create table maintenance_request (
   maintenance_request_id SERIAL               not null,
   car_id               INT4                 not null,
   mechanic_id          INT4                 not null,
   filling_date         DATE                 not null,
   service_type         VARCHAR(50)          not null,
   maintenance_request_status VARCHAR(20)          not null,
   maintenance_request_note TEXT                 null,
   constraint PK_MAINTENANCE_REQUEST primary key (maintenance_request_id)
);

comment on column maintenance_request.service_type is
'Ремонт
Плановое техническое обслуживание';

comment on column maintenance_request.maintenance_request_status is
'В ожидании
В процессе
Завершена';

/*==============================================================*/
/* Index: maintenance_request_PK                                */
/*==============================================================*/
create unique index maintenance_request_PK on maintenance_request (
maintenance_request_id
);

/*==============================================================*/
/* Index: refers_to_FK                                          */
/*==============================================================*/
create  index refers_to_FK on maintenance_request (
car_id
);

/*==============================================================*/
/* Index: creating_FK                                           */
/*==============================================================*/
create  index creating_FK on maintenance_request (
mechanic_id
);

/*==============================================================*/
/* Index: idx_maintenance_car                                   */
/*==============================================================*/
create  index idx_maintenance_car on maintenance_request (
car_id
);

/*==============================================================*/
/* Index: idx_maintenance_mechanic                              */
/*==============================================================*/
create  index idx_maintenance_mechanic on maintenance_request (
mechanic_id
);

/*==============================================================*/
/* Table: mechanic                                              */
/*==============================================================*/
create table mechanic (
   mechanic_id          SERIAL               not null,
   mechanic_surname     VARCHAR(50)          not null,
   mechanic_name        VARCHAR(50)          not null,
   mechanic_patronymic  VARCHAR(50)          null,
   mechanic_phone       VARCHAR(25)          not null,
   mechanic_email       VARCHAR(100)         null,
   constraint PK_MECHANIC primary key (mechanic_id)
);

/*==============================================================*/
/* Index: mechanic_PK                                           */
/*==============================================================*/
create unique index mechanic_PK on mechanic (
mechanic_id
);

/*==============================================================*/
/* Table: operator                                              */
/*==============================================================*/
create table operator (
   operator_id          SERIAL               not null,
   operator_surname     VARCHAR(50)          not null,
   operator_name        VARCHAR(50)          not null,
   operator_patronymic  VARCHAR(50)          null,
   operator_phone       VARCHAR(25)          not null,
   operator_email       VARCHAR(100)         null,
   operator_start_date  DATE                 not null,
   operator_end_date    DATE                 not null,
   constraint PK_OPERATOR primary key (operator_id)
);

/*==============================================================*/
/* Index: operator_PK                                           */
/*==============================================================*/
create unique index operator_PK on operator (
operator_id
);

/*==============================================================*/
/* Table: "order"                                               */
/*==============================================================*/
create table "order" (
   order_id             SERIAL               not null,
   client_id            INT4                 not null,
   operator_id          INT4                 not null,
   flight_id            INT4                 null,
   order_startpoint     VARCHAR(255)         not null,
   order_endpoint       VARCHAR(255)         not null,
   order_dispatch_date  DATE                 not null,
   order_delivery_date  DATE                 not null,
   order_status         VARCHAR(20)          not null,
   constraint PK_ORDER primary key (order_id)
);

comment on column "order".order_status is
'Новый
Подтвержден
Назначен
В процессе
Доставлен
Отменён';

/*==============================================================*/
/* Index: order_PK                                              */
/*==============================================================*/
create unique index order_PK on "order" (
order_id
);

/*==============================================================*/
/* Index: execute_FK                                            */
/*==============================================================*/
create  index execute_FK on "order" (
client_id
);

/*==============================================================*/
/* Index: process_FK                                            */
/*==============================================================*/
create  index process_FK on "order" (
operator_id
);

/*==============================================================*/
/* Index: is_linked_to_FK                                       */
/*==============================================================*/
create  index is_linked_to_FK on "order" (
flight_id
);

/*==============================================================*/
/* Index: idx_order_client                                      */
/*==============================================================*/
create  index idx_order_client on "order" (
client_id
);

/*==============================================================*/
/* Index: idx_order_operator                                    */
/*==============================================================*/
create  index idx_order_operator on "order" (
operator_id
);

/*==============================================================*/
/* Index: idx_order_flight                                      */
/*==============================================================*/
create  index idx_order_flight on "order" (
flight_id
);

-- Ограничение ссылочной целостности

alter table car
   add constraint FK_CAR_CONTROL_DRIVER foreign key (driver_id)
      references driver (driver_id)
      on delete restrict on update restrict;

alter table cargo
   add constraint FK_CARGO_CONTAINS_ORDER foreign key (order_id)
      references "order" (order_id)
      on delete cascade on update cascade;

alter table flight
   add constraint FK_FLIGHT_ASSIGN_DISPATCH foreign key (dispatcher_id)
      references dispatcher (dispatcher_id)
      on delete set null on update cascade;

alter table flight
   add constraint FK_FLIGHT_PERFORMS_DRIVER foreign key (driver_id)
      references driver (driver_id)
      on delete set null on update cascade;

alter table flight
   add constraint FK_FLIGHT_SERVICE_CAR foreign key (car_id)
      references car (car_id)
      on delete set null on update cascade;

alter table maintenance_request
   add constraint FK_MAINTENA_CREATING_MECHANIC foreign key (mechanic_id)
      references mechanic (mechanic_id)
      on delete set null on update cascade;

alter table maintenance_request
   add constraint FK_MAINTENA_REFERS_TO_CAR foreign key (car_id)
      references car (car_id)
      on delete set null on update cascade;

alter table "order"
   add constraint FK_ORDER_EXECUTE_CLIENT foreign key (client_id)
      references client (client_id)
      on delete set null on update cascade;

alter table "order"
   add constraint FK_ORDER_IS_LINKED_FLIGHT foreign key (flight_id)
      references flight (flight_id)
      on delete set null on update cascade;

alter table "order"
   add constraint FK_ORDER_PROCESS_OPERATOR foreign key (operator_id)
      references operator (operator_id)
      on delete set null on update cascade;
```
## Client (не имеет зависимостей)

```sql
INSERT INTO client (client_name, client_phone, client_email, client_address, client_type) VALUES
('ООО "Транспорт-Сервис"', '89001112233', 'info@transservice.ru', 'г. Москва, ул. Ленина, д. 10', 'Юридическое лицо'),
('ИП Иванов', '89002223344', 'ivanov@mail.ru', 'г. Санкт-Петербург, ул. Невская, д. 5', 'Физическое лицо'),
('ООО "Грузовые линии"', '89003334455', 'contact@cargo-lines.ru', 'г. Казань, пр. Победы, д. 100', 'Юридическое лицо'),
('ИП Смирнов', '89004445566', 'smirnov@mail.ru', 'г. Екатеринбург, ул. Малышева, д. 10', 'Физическое лицо'),
('ООО "Сибирь-Логистика"', '89005556677', 'info@siberia-logistics.ru', 'г. Новосибирск, ул. Ленина, д. 50', 'Юридическое лицо'),
('ИП Петров', '89006667788', 'petrov@mail.ru', 'г. Омск, ул. Красная, д. 12', 'Физическое лицо'),
('ООО "УралТранс"', '89007778899', 'contact@uraltrans.ru', 'г. Челябинск, ул. Дзержинского, д. 45', 'Юридическое лицо'),
('ИП Васильев', '89008889900', 'vasiliev@mail.ru', 'г. Ростов-на-Дону, ул. Садовая, д. 2', 'Физическое лицо'),
('ООО "ВостокТранс"', '89009990011', 'info@easttrans.ru', 'г. Владивосток, ул. Суханова, д. 30', 'Юридическое лицо'),
('ИП Николаев', '89001112222', 'nikolaev@mail.ru', 'г. Хабаровск, ул. Ленина, д. 20', 'Физическое лицо'),
('ООО "ЮгТранс"', '89002223333', 'contact@southtrans.ru', 'г. Краснодар, ул. Красная, д. 1', 'Юридическое лицо'),
('ИП Сидоров', '89003334444', 'sidorov@mail.ru', 'г. Сочи, ул. Виноградная, д. 15', 'Физическое лицо'),
('ООО "ЦентрЛогистика"', '89004445555', 'info@centerlogistics.ru', 'г. Москва, ул. Тверская, д. 25', 'Юридическое лицо'),
('ИП Морозов', '89005556666', 'morozov@mail.ru', 'г. Санкт-Петербург, ул. Московская, д. 40', 'Физическое лицо'),
('ООО "Транспорт-Экспресс"', '89006667777', 'contact@transexpress.ru', 'г. Екатеринбург, ул. Восточная, д. 8', 'Юридическое лицо');
```

## Operator (не имеет зависимостей)

```sql
INSERT INTO operator (operator_surname, operator_name, operator_patronymic, operator_phone, operator_email, operator_start_date, operator_end_date) VALUES
('Петров', 'Сергей', 'Иванович', '89001110001', 's.petrov@company.ru', '2023-01-01', '2023-12-31'),
('Смирнов', 'Алексей', 'Петрович', '89001110002', 'a.smirnov@company.ru', '2023-02-01', '2023-12-31'),
('Васильев', 'Игорь', 'Алексеевич', '89001110003', 'i.vasilev@company.ru', '2023-03-01', '2023-12-31'),
('Кузнецов', 'Дмитрий', 'Михайлович', '89001110004', 'd.kuznetsov@company.ru', '2023-04-01', '2023-12-31'),
('Федоров', 'Антон', 'Николаевич', '89001110005', 'a.fedorov@company.ru', '2023-05-01', '2023-12-31'),
('Иванов', 'Николай', 'Сергеевич', '89001110006', 'n.ivanov@company.ru', '2023-06-01', '2023-12-31'),
('Сидоров', 'Евгений', 'Владимирович', '89001110007', 'e.sidorov@company.ru', '2023-07-01', '2023-12-31'),
('Морозов', 'Олег', 'Викторович', '89001110008', 'o.morozov@company.ru', '2023-08-01', '2023-12-31'),
('Николаев', 'Владислав', 'Игоревич', '89001110009', 'v.nikolaev@company.ru', '2023-09-01', '2023-12-31'),
('Григорьев', 'Максим', 'Павлович', '89001110010', 'm.grigorev@company.ru', '2023-10-01', '2023-12-31'),
('Куликов', 'Роман', 'Геннадьевич', '89001110011', 'r.kulikov@company.ru', '2023-11-01', '2023-12-31'),
('Алексеев', 'Андрей', 'Евгеньевич', '89001110012', 'a.alexeev@company.ru', '2023-12-01', '2023-12-31'),
('Михайлов', 'Юрий', 'Петрович', '89001110013', 'y.mikhailov@company.ru', '2024-01-01', '2024-12-31'),
('Егоров', 'Даниил', 'Иванович', '89001110014', 'd.egorov@company.ru', '2024-02-01', '2024-12-31'),
('Захаров', 'Артем', 'Алексеевич', '89001110015', 'a.zakharov@company.ru', '2024-03-01', '2024-12-31');
```
## Dispatcher (не имеет зависимостей)

```sql
INSERT INTO dispatcher (dispatcher_surname, dispatcher_name, dispatcher_patronymic, dispatcher_phone, dispatcher_email, dispatcher_start_сhange, dispatcher_end_change) VALUES
('Иванов', 'Петр', 'Александрович', '89001230001', 'p.ivanov@company.ru', '2024-11-01', '2024-11-01'),
('Смирнова', 'Елена', 'Ивановна', '89001230002', 'e.smirnova@company.ru', '2024-11-01', '2024-11-01'),
('Кузнецова', 'Ольга', 'Сергеевна', '89001230003', 'o.kuznetsova@company.ru', '2024-11-01', '2024-11-01'),
('Федорова', 'Анна', 'Михайловна', '89001230004', 'a.fedorova@company.ru', '2024-11-01', '2024-11-01'),
('Васильева', 'Мария', 'Дмитриевна', '89001230005', 'm.vasilieva@company.ru', '2024-11-01', '2024-11-01'),
('Сидоров', 'Александр', 'Николаевич', '89001230006', 'a.sidorov@company.ru', '2024-11-01', '2024-11-01'),
('Морозов', 'Олег', 'Владимирович', '89001230007', 'o.morozov@company.ru', '2024-11-01', '2024-11-01'),
('Павлова', 'Дарья', 'Андреевна', '89001230008', 'd.pavlova@company.ru', '2024-11-01', '2024-11-01'),
('Григорьев', 'Сергей', 'Геннадьевич', '89001230009', 's.grigorev@company.ru', '2024-11-01', '2024-11-01'),
('Николаев', 'Игорь', 'Петрович', '89001230010', 'i.nikolaev@company.ru', '2024-11-01', '2024-11-01'),
('Куликова', 'Светлана', 'Анатольевна', '89001230011', 's.kulikova@company.ru', '2024-11-01', '2024-11-01'),
('Михайлова', 'Екатерина', 'Владимировна', '89001230012', 'e.mikhailova@company.ru', '2024-11-01', '2024-11-01'),
('Егорова', 'Алина', 'Олеговна', '89001230013', 'a.egorova@company.ru', '2024-11-01', '2024-11-01'),
('Захарова', 'Ирина', 'Сергеевна', '89001230014', 'i.zakharova@company.ru', '2024-11-01', '2024-11-01'),
('Алексеева', 'Ольга', 'Юрьевна', '89001230015', 'o.alexeeva@company.ru', '2024-11-01', '2024-11-01');

```
## Mechanic (не имеет зависимостей)

```sql
INSERT INTO mechanic (mechanic_surname, mechanic_name, mechanic_patronymic, mechanic_phone, mechanic_email) VALUES
('Иванов', 'Андрей', 'Викторович', '89031234567', 'ivanov_mek@example.com'),
('Петров', 'Алексей', 'Сергеевич', '89032345678', 'petrov_mek@example.com'),
('Сидоров', 'Дмитрий', 'Николаевич', '89033456789', 'sidorov_mek@example.com'),
('Михайлов', 'Максим', 'Игоревич', '89034567890', 'mikhailov_mek@example.com'),
('Кузнецов', 'Аркадий', 'Петрович', '89035678901', 'kuznetsov_mek@example.com'),
('Николаев', 'Юрий', 'Станиславович', '89036789012', 'nikolaev_mek@example.com'),
('Фёдоров', 'Иван', 'Геннадиевич', '89037890123', 'fedorov_mek@example.com'),
('Александров', 'Владимир', 'Анатольевич', '89038901234', 'alexandrov_mek@example.com'),
('Лебедев', 'Денис', 'Сергеевич', '89039012345', 'lebedev_mek@example.com'),
('Григорьев', 'Геннадий', 'Павлович', '89040123456', 'grigoryev_mek@example.com'),
('Ковалёв', 'Станислав', 'Петрович', '89041234567', 'kovalov_mek@example.com'),
('Денисов', 'Вячеслав', 'Иванович', '89042345678', 'denisov_mek@example.com'),
('Смирнов', 'Олег', 'Николаевич', '89043456789', 'smirnov_mek@example.com'),
('Фролов', 'Петр', 'Александрович', '89044567890', 'frolov_mek@example.com'),
('Тимофеев', 'Игорь', 'Геннадиевич', '89045678901', 'timofeev_mek@example.com');
```
## Driver (не имеет зависимостей)

```sql
INSERT INTO driver (driver_surname, driver_name, driver_patronymic, driver_phone, driver_experience) VALUES
('Кузнецов', 'Дмитрий', 'Иванович', '89001340001', 5),
('Федоров', 'Антон', 'Петрович', '89001340002', 7),
('Сидоров', 'Евгений', 'Николаевич', '89001340003', 3),
('Морозов', 'Олег', 'Алексеевич', '89001340004', 10),
('Николаев', 'Иван', 'Владимирович', '89001340005', 6),
('Григорьев', 'Максим', 'Дмитриевич', '89001340006', 4),
('Захаров', 'Роман', 'Сергеевич', '89001340007', 8),
('Алексеев', 'Юрий', 'Николаевич', '89001340008', 9),
('Петров', 'Сергей', 'Викторович', '89001340009', 2),
('Иванов', 'Николай', 'Алексеевич', '89001340010', 5),
('Смирнов', 'Алексей', 'Олегович', '89001340011', 3),
('Васильев', 'Артем', 'Владимирович', '89001340012', 7),
('Куликов', 'Денис', 'Геннадьевич', '89001340013', 4),
('Михайлов', 'Егор', 'Игоревич', '89001340014', 6),
('Егоров', 'Александр', 'Юрьевич', '89001340015', 10);
```
## Car (имеет зависимость от driver)

```sql
INSERT INTO car (driver_id, car_number, car_model, car_brand, car_capacity, car_mileage, car_condition, car_last_maintenance_date) VALUES
(1, 'A123BC77', 'Sprinter', 'Mercedes-Benz', 3.5, 120000, 'Исправно', '2024-10-15'),
(2, 'B234CD77', 'Actros', 'Mercedes-Benz', 20.0, 450000, 'На обслуживании', '2024-11-10'),
(3, 'C345DE77', 'Transit', 'Ford', 4.0, 200000, 'На ремонте', '2024-10-20'),
(4, 'D456EF77', 'Ducato', 'Fiat', 3.0, 150000, 'Исправно', '2024-09-01'),
(5, 'E567FG77', 'Daily', 'Iveco', 3.5, 250000, 'На обслуживании', '2024-10-25'),
(6, 'F678GH77', 'Canter', 'Mitsubishi', 5.0, 300000, 'На ремонте', '2024-10-12'),
(7, 'G789HI77', 'Sprinter', 'Mercedes-Benz', 3.5, 180000, 'Исправно', '2024-08-30'),
(8, 'H890IJ77', 'Actros', 'Mercedes-Benz', 20.0, 400000, 'На обслуживании', '2024-09-10'),
(9, 'I901JK77', 'Transit', 'Ford', 4.0, 210000, 'Исправно', '2024-09-20'),
(10, 'J012KL77', 'Ducato', 'Fiat', 3.0, 170000, 'На ремонте', '2024-10-05'),
(11, 'K123LM77', 'Daily', 'Iveco', 3.5, 260000, 'Исправно', '2024-11-15'),
(12, 'L234MN77', 'Canter', 'Mitsubishi', 5.0, 290000, 'На обслуживании', '2024-11-05'),
(13, 'M345NO77', 'Sprinter', 'Mercedes-Benz', 3.5, 195000, 'Исправно', '2024-09-25'),
(14, 'N456OP77', 'Actros', 'Mercedes-Benz', 20.0, 430000, 'На ремонте', '2024-10-22'),
(15, 'O567PQ77', 'Transit', 'Ford', 4.0, 220000, 'Исправно', '2024-09-15');
```
## Flight (имеет зависимость от driver, dispatcher, car)

```sql
INSERT INTO flight (driver_id, dispatcher_id, car_id, flight_startpoint, flight_endpoint, flight_distance, flight_departure_date) VALUES
(1, 1, 1, 'г. Москва', 'г. Санкт-Петербург', 700.00, '2024-11-10'),
(2, 2, 2, 'г. Новосибирск', 'г. Омск', 450.00, '2024-11-11'),
(3, 3, 3, 'г. Екатеринбург', 'г. Казань', 450.00, '2024-11-12'),
(4, 4, 4, 'г. Нижний Новгород', 'г. Воронеж', 370.00, '2024-11-13'),
(5, 5, 5, 'г. Ростов-на-Дону', 'г. Краснодар', 200.00, '2024-11-14'),
(6, 6, 6, 'г. Уфа', 'г. Челябинск', 350.00, '2024-11-15'),
(7, 7, 7, 'г. Тюмень', 'г. Самара', 600.00, '2024-11-16'),
(8, 8, 8, 'г. Краснодар', 'г. Волгоград', 350.00, '2024-11-17'),
(9, 9, 9, 'г. Саратов', 'г. Пенза', 120.00, '2024-11-18'),
(10, 10, 10, 'г. Казань', 'г. Ульяновск', 250.00, '2024-11-19'),
(11, 11, 11, 'г. Челябинск', 'г. Екатеринбург', 200.00, '2024-11-20'),
(12, 12, 12, 'г. Волгоград', 'г. Ростов-на-Дону', 280.00, '2024-11-21'),
(13, 13, 13, 'г. Санкт-Петербург', 'г. Псков', 160.00, '2024-11-22'),
(14, 14, 14, 'г. Воронеж', 'г. Краснодар', 400.00, '2024-11-23'),
(15, 15, 15, 'г. Омск', 'г. Новосибирск', 450.00, '2024-11-24');
```
## Order (имеет зависимость от client, operator, flight)

```sql
INSERT INTO "order" (client_id, operator_id, flight_id, order_startpoint, order_endpoint, order_dispatch_date, order_delivery_date, order_status) VALUES
(1, 1, 1, 'г. Москва', 'г. Санкт-Петербург', '2024-11-10', '2024-11-11', 'Доставлен'),
(2, 2, 2, 'г. Новосибирск', 'г. Омск', '2024-11-11', '2024-11-12', 'Подтвержден'),
(3, 3, 3, 'г. Екатеринбург', 'г. Казань', '2024-11-12', '2024-11-13', 'Назначен'),
(4, 4, 4, 'г. Нижний Новгород', 'г. Воронеж', '2024-11-13', '2024-11-14', 'В процессе'),
(5, 5, 5, 'г. Ростов-на-Дону', 'г. Краснодар', '2024-11-14', '2024-11-15', 'Доставлен'),
(6, 6, 6, 'г. Уфа', 'г. Челябинск', '2024-11-15', '2024-11-16', 'Отменён'),
(7, 7, 7, 'г. Тюмень', 'г. Самара', '2024-11-16', '2024-11-17', 'Подтвержден'),
(8, 8, 8, 'г. Краснодар', 'г. Волгоград', '2024-11-17', '2024-11-18', 'В процессе'),
(9, 9, 9, 'г. Саратов', 'г. Пенза', '2024-11-18', '2024-11-19', 'Назначен'),
(10, 10, 10, 'г. Казань', 'г. Ульяновск', '2024-11-19', '2024-11-20', 'Доставлен'),
(11, 11, 11, 'г. Челябинск', 'г. Екатеринбург', '2024-11-20', '2024-11-21', 'В процессе'),
(12, 12, 12, 'г. Волгоград', 'г. Ростов-на-Дону', '2024-11-21', '2024-11-22', 'Доставлен'),
(13, 13, 13, 'г. Санкт-Петербург', 'г. Псков', '2024-11-22', '2024-11-23', 'Отменён'),
(14, 14, 14, 'г. Воронеж', 'г. Краснодар', '2024-11-23', '2024-11-24', 'Подтвержден'),
(15, 15, 15, 'г. Омск', 'г. Новосибирск', '2024-11-24', '2024-11-25', 'Назначен');
```
## Cargo (имеет зависимость от order)

```sql
INSERT INTO cargo (order_id, cargo_type, cargo_weight, cargo_volume) VALUES
(1, 'Продукты', 5000.00, 10.00),
(2, 'Техника', 15000.00, 20.00),
(3, 'Мебель', 3000.00, 15.00),
(4, 'Книги', 1200.00, 5.00),
(5, 'Оборудование', 25000.00, 50.00),
(6, 'Продукты', 4000.00, 8.00),
(7, 'Одежда', 1000.00, 3.00),
(8, 'Мебель', 2000.00, 7.00),
(9, 'Косметика', 500.00, 1.00),
(10, 'Техника', 8000.00, 18.00),
(11, 'Мебель', 3000.00, 10.00),
(12, 'Оборудование', 22000.00, 40.00),
(13, 'Продукты', 7000.00, 12.00),
(14, 'Книги', 1500.00, 4.00),
(15, 'Техника', 10000.00, 25.00);
```

## Maintenance_request (имеет зависимость от car, mechanic)

```sql
INSERT INTO maintenance_request (car_id, mechanic_id, filling_date, service_type, maintenance_request_status, maintenance_request_note) VALUES
(1, 1, '2024-11-15', 'Ремонт', 'В процессе', 'Замена тормозных колодок'),
(2, 2, '2024-11-16', 'Плановое техническое обслуживание', 'Завершена', 'Замена масла и фильтров'),
(3, 3, '2024-11-17', 'Ремонт', 'В ожидании', 'Ремонт коробки передач'),
(4, 4, '2024-11-18', 'Плановое техническое обслуживание', 'В процессе', 'Проверка системы охлаждения'),
(5, 5, '2024-11-19', 'Ремонт', 'В процессе', 'Ремонт двигателя'),
(6, 6, '2024-11-20', 'Плановое техническое обслуживание', 'Завершена', 'Замена тормозных дисков'),
(7, 7, '2024-11-21', 'Ремонт', 'В ожидании', 'Покраска кузова'),
(8, 8, '2024-11-22', 'Плановое техническое обслуживание', 'В процессе', 'Замена амортизаторов'),
(9, 9, '2024-11-23', 'Ремонт', 'В ожидании', 'Замена трансмиссии'),
(10, 10, '2024-11-24', 'Плановое техническое обслуживание', 'Завершена', 'Проверка аккумулятора'),
(11, 11, '2024-11-25', 'Ремонт', 'В процессе', 'Ремонт системы кондиционирования'),
(12, 12, '2024-11-26', 'Плановое техническое обслуживание', 'Завершена', 'Проверка тормозной системы'),
(13, 13, '2024-11-27', 'Ремонт', 'В ожидании', 'Ремонт электрики'),
(14, 14, '2024-11-28', 'Плановое техническое обслуживание', 'В процессе', 'Проверка подвески'),
(15, 15, '2024-11-29', 'Ремонт', 'В ожидании', 'Замена рулевой рейки');
```
