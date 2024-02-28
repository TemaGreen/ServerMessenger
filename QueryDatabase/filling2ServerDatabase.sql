INSERT INTO public.user(name, icon) values 
	('User1',1),
	('User2',2),
	('User3',3),
	('User4',4),
	('User5',5),
	('User6',6),
	('User7',7),
	('User8',8),
	('User9',9),
	('User10',10);

INSERT INTO public.dialogs(author, name, time_create) values
	((SELECT id FROM public.user WHERE name = 'User1'), 'Диалог с User2', '2023-01-21 11:29:50'),
	((SELECT id FROM public.user WHERE name = 'User1'), 'Диалог с User5', '2023-02-15 10:30:00'),
	((SELECT id FROM public.user WHERE name = 'User1'), 'Разговор втроем', '2023-03-04 12:13:04'),
	((Select id FROM public.user WHERE name = 'User3'), 'Диалог с несколькими пользователями', '2023-02-21 17:46:19'),
	((Select id FROM public.user WHERE name = 'User8'), 'Диалог с семьей', '2023-02-21 01:45:23'),
	((Select id FROM public.user WHERE name = 'User10'), 'Диалог User10, User9', '2023-02-21 15:26:09');

INSERT INTO public.role(name) values 
	('Creater'), 
	('Admin'), 
	('User');
	
INSERT INTO public.user_dialogs(id_dialogs, id_participant, role) values
	((SELECT id FROM public.dialogs WHERE name = 'Диалог с User2'), (SELECT id FROM public.user WHERE name = 'User1'), (SELECT id FROM public.role WHERE name = 'Creater')),
	((SELECT id FROM public.dialogs WHERE name = 'Диалог с User2'), (SELECT id FROM public.user WHERE name = 'User2'), (SELECT id FROM public.role WHERE name = 'User')),
	
	((SELECT id FROM public.dialogs WHERE name = 'Диалог с User5'), (SELECT id FROM public.user WHERE name = 'User1'), (SELECT id FROM public.role WHERE name = 'Creater')),
	((SELECT id FROM public.dialogs WHERE name = 'Диалог с User5'), (SELECT id FROM public.user WHERE name = 'User5'), (SELECT id FROM public.role WHERE name = 'User')),
	
	((SELECT id FROM public.dialogs WHERE name = 'Разговор втроем'), (SELECT id FROM public.user WHERE name = 'User1'), (SELECT id FROM public.role WHERE name = 'Creater')),
	((SELECT id FROM public.dialogs WHERE name = 'Разговор втроем'), (SELECT id FROM public.user WHERE name = 'User7'), (SELECT id FROM public.role WHERE name = 'User')),
	((SELECT id FROM public.dialogs WHERE name = 'Разговор втроем'), (SELECT id FROM public.user WHERE name = 'User9'), (SELECT id FROM public.role WHERE name = 'User')),
	
	((SELECT id FROM public.dialogs WHERE name = 'Диалог с несколькими пользователями'), (SELECT id FROM public.user WHERE name = 'User3'), (SELECT id FROM public.role WHERE name = 'Creater')),
	((SELECT id FROM public.dialogs WHERE name = 'Диалог с несколькими пользователями'), (SELECT id FROM public.user WHERE name = 'User1'), (SELECT id FROM public.role WHERE name = 'User')),
	((SELECT id FROM public.dialogs WHERE name = 'Диалог с несколькими пользователями'), (SELECT id FROM public.user WHERE name = 'User2'), (SELECT id FROM public.role WHERE name = 'User')),
	((SELECT id FROM public.dialogs WHERE name = 'Диалог с несколькими пользователями'), (SELECT id FROM public.user WHERE name = 'User4'), (SELECT id FROM public.role WHERE name = 'User')),
	
	((SELECT id FROM public.dialogs WHERE name = 'Диалог с семьей'), (SELECT id FROM public.user WHERE name = 'User8'), (SELECT id FROM public.role WHERE name = 'Creater')),
	((SELECT id FROM public.dialogs WHERE name = 'Диалог с семьей'), (SELECT id FROM public.user WHERE name = 'User1'), (SELECT id FROM public.role WHERE name = 'User')),
	((SELECT id FROM public.dialogs WHERE name = 'Диалог с семьей'), (SELECT id FROM public.user WHERE name = 'User4'), (SELECT id FROM public.role WHERE name = 'User')),
	
	((SELECT id FROM public.dialogs WHERE name = 'Диалог User10, User9'), (SELECT id FROM public.user WHERE name = 'User10'), (SELECT id FROM public.role WHERE name = 'Creater')),
	((SELECT id FROM public.dialogs WHERE name = 'Диалог User10, User9'), (SELECT id FROM public.user WHERE name = 'User9'), (SELECT id FROM public.role WHERE name = 'User'));
	
INSERT INTO public.messages(id_dialogs, id_author, text_message, time_message) values
	((SELECT id FROM public.dialogs WHERE name = 'Диалог с User2'), (SELECT id FROM public.user WHERE name = 'User1'), 'Привет!', '2023-02-21 11:30:00'),
	((SELECT id FROM public.dialogs WHERE name = 'Диалог с User2'), (SELECT id FROM public.user WHERE name = 'User2'), 'Привет', '2023-02-21 11:31:23'),
	((SELECT id FROM public.dialogs WHERE name = 'Диалог с User2'), (SELECT id FROM public.user WHERE name = 'User1'), 'Как дела?', '2023-02-21 11:31:50'),
	((SELECT id FROM public.dialogs WHERE name = 'Диалог с User2'), (SELECT id FROM public.user WHERE name = 'User2'), 'Нормально', '2023-02-21 11:32:34'),
	((SELECT id FROM public.dialogs WHERE name = 'Диалог с User2'), (SELECT id FROM public.user WHERE name = 'User2'), 'А у тебя?', '2023-02-21 11:32:40');