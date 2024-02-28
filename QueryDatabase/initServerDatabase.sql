CREATE TABLE public.user(
	id int PRIMARY KEY,
	name character varying(40) UNIQUE,
	icon int
);

CREATE TABLE public.dialogs(
	id int PRIMARY KEY,
	author int,
	name character varying(40),
	time_create timestamp,
	CONSTRAINT FK_author FOREIGN KEY (author)
		REFERENCES public.user (id) ON DELETE CASCADE
);

CREATE TABLE public.role(
	id int PRIMARY KEY,
	name character varying(40) UNIQUE
);

CREATE TABLE public.user_dialogs(
	id_dialogs int,
	id_participant int,
	role int,
	PRIMARY KEY(id_dialogs, id_participant),
	CONSTRAINT FK_dialogs FOREIGN KEY (id_dialogs)
		REFERENCES public.dialogs (id) ON DELETE CASCADE,
	CONSTRAINT FK_participant FOREIGN KEY (id_participant)
		REFERENCES public.user (id) ON DELETE CASCADE,
	CONSTRAINT FK_role FOREIGN KEY (role) 
		REFERENCES public.role (id) ON DELETE CASCADE
);

CREATE TABLE public.messages(
	id int PRIMARY KEY,
	id_dialogs int,
	id_author int,
	text_message character varying(300),
	time_message timestamp,
	CONSTRAINT FK_dialog_message FOREIGN KEY (id_dialogs)
		REFERENCES public.dialogs (id) ON DELETE CASCADE,
	CONSTRAINT FK_user FOREIGN KEY (id_author)
		REFERENCES public.user (id) ON DELETE CASCADE
);