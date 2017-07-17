-- For H2 Database
create table registrations (
  id bigserial not null primary key,
  title varchar(512) not null,
  name varchar(512) not null,
  email varchar(512) not null,
  designation varchar(512) not null,
  organisation varchar(512) not null,
  country varchar(512) not null,
  presenting boolean not null,
  displaying boolean not null,
  presentation_title1 varchar(512),
  presentation_title2 varchar(512),
  presentation_title3 varchar(512),
  created_at timestamp not null,
  updated_at timestamp not null
)
