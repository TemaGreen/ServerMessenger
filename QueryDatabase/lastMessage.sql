Select * From public.messages WHERE time_message = (Select MAX(time_message) From public.messages Where id_dialogs = 1 And id_author = 1) 

