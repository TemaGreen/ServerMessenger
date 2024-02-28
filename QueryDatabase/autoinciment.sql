CREATE SEQUENCE public.user_sequence
	START WITH 1
	INCREMENT BY 1
	NO MINVALUE 
	NO MAXVALUE
	CACHE 1;
CREATE SEQUENCE public.dialogs_sequence
	START WITH 1
	INCREMENT BY 1
	NO MINVALUE 
	NO MAXVALUE
	CACHE 1;
CREATE SEQUENCE public.messages_sequence
	START WITH 1
	INCREMENT BY 1
	NO MINVALUE 
	NO MAXVALUE
	CACHE 1;
CREATE SEQUENCE public.role_sequence
	START WITH 1
	INCREMENT BY 1
	NO MINVALUE 
	NO MAXVALUE
	CACHE 1;
CREATE FUNCTION public.user_autoincrement_function() RETURNS trigger
	LANGUAGE plpgsql
	AS $$ 
	BEGIN 
		New.id=nextval('user_sequence');
		RETURN New;
	END;
	$$;
CREATE FUNCTION public.dialogs_autoincrement_function() RETURNS trigger
	LANGUAGE plpgsql
	AS $$ 
	BEGIN 
		New.id=nextval('dialogs_sequence');
		RETURN New;
	END;
	$$;
CREATE FUNCTION public.messages_autoincrement_function() RETURNS trigger
	LANGUAGE plpgsql
	AS $$ 
	BEGIN 
		New.id=nextval('messages_sequence');
		RETURN New;
	END;
	$$;	
CREATE FUNCTION public.role_autoincrement_function() RETURNS trigger
	LANGUAGE plpgsql
	AS $$ 
	BEGIN 
		New.id=nextval('role_sequence');
		RETURN New;
	END;
	$$;
CREATE TRIGGER user_autoincrement_trigger BEFORE INSERT ON public.user FOR EACH ROW EXECUTE PROCEDURE public.user_autoincrement_function();
CREATE TRIGGER dialogs_autoincrement_trigger BEFORE INSERT ON public.dialogs FOR EACH ROW EXECUTE PROCEDURE public.dialogs_autoincrement_function();
CREATE TRIGGER messages_autoincrement_trigger BEFORE INSERT ON public.messages FOR EACH ROW EXECUTE PROCEDURE public.messages_autoincrement_function();
CREATE TRIGGER role_autoincrement_trigger BEFORE INSERT ON public.role FOR EACH ROW EXECUTE PROCEDURE public.role_autoincrement_function();
