INSERT INTO public.user(id, name) values 
	(1, 'User1'),
	(2, 'User2');
INSERT INTO public.dialogs(id, author) values
	(1, 1);
INSERT INTO public.user_dialogs(id_dialogs, id_participant) values
	(1, 1),
	(1, 2);
INSERT INTO public.messages(id, id_dialogs, id_author, text_message, time_message) values
	(1, 1, 1, 'Привет!', '2023-02-21 11:30:00'),
	(2, 1, 2, 'Привет', '2023-02-21 11:31:23'),
	(3, 1, 1, 'Как дела?', '2023-02-21 11:31:50'),
	(4, 1, 2, 'Нормально', '2023-02-21 11:32:34'),
	(5, 1, 2, 'А у тебя?', '2023-02-21 11:32:40');