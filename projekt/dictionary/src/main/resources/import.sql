create table if not EXISTS words (
	id int identity primary key,
	polish_word varchar(100),
	english_word varchar(100)
);