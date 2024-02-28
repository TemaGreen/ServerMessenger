SELECT u.id, u.name, u.icon FROM public.user AS u LEFT JOIN public.user_dialogs AS d ON u.id = d.id_participant AND d.id_dialogs = 1 WHERE d.id_participant IS NULL
