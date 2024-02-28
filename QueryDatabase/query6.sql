CREATE TABLE public.role(
	id int PRIMARY KEY,
	name character varying(40) UNIQUE
);

ALTER TABLE public.user_dialogs ADD COLUMN role int;
ALTER TABLE public.user_dialogs ADD CONSTRAINT FK_role FOREIGN KEY (role) REFERENCES public.role (id) ON DELETE CASCADE;

INSERT INTO public.role(id, name) values (1, 'Creater'), (2, 'Admin'), (3, 'User');

UPDATE public.user_dialogs SET role = 3 WHERE role IS NULL; 

UPDATE public.user_dialogs SET role = 1 WHERE (id_dialogs, id_participant) IN (SELECT u.id_dialogs, u.id_participant FROM dialogs AS d JOIN user_dialogs AS u ON u.id_dialogs = d.id WHERE d.author = u.id_participant);

CREATE SEQUENCE public.role_sequence
	START WITH 1
	INCREMENT BY 1
	NO MINVALUE 
	NO MAXVALUE
	CACHE 1;
CREATE FUNCTION public.role_autoincrement_function() RETURNS trigger
	LANGUAGE plpgsql
	AS $$ 
	BEGIN 
		New.id=nextval('role_sequence');
		RETURN New;
	END;
	$$;
CREATE TRIGGER role_autoincrement_trigger BEFORE INSERT ON public.role FOR EACH ROW EXECUTE PROCEDURE public.role_autoincrement_function();