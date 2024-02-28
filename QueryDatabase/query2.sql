WITH t1 AS (SELECT d.id, u.name FROM public.dialogs AS d INNER JOIN public.user AS u ON d.author = u.id) 
SELECT t1.name, u2.name FROM public.user_dialogs AS d2 INNER JOIN public.user AS u2 ON d2.id_participant = u2.id 
INNER JOIN t1 ON t1.id = d2.id_dialogs WHERE t1.name = 'User3'