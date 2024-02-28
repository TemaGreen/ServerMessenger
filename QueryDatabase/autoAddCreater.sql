CREATE FUNCTION public.auto_dialog_creater_trigger() RETURNS trigger AS $user_dialogs$
	BEGIN 
		INSERT INTO public.user_dialogs(id_dialogs, id_participant, role) values (NEW.id, NEW.author, 1); 
		RETURN NEW;
	END;
	$user_dialogs$ LANGUAGE plpgsql;

CREATE TRIGGER auto_dialog_creater_trigger AFTER INSERT ON public.dialogs FOR EACH ROW EXECUTE PROCEDURE public.auto_dialog_creater_trigger(); 