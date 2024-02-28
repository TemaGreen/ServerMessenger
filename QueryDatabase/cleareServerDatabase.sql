delete from public.user;
delete from public.dialogs;
delete from public.messages;
delete from public.user_dialogs;
delete from public.role;
ALTER SEQUENCE public.dialogs_sequence RESTART WITH 1;
ALTER SEQUENCE public.messages_sequence RESTART WITH 1;
ALTER SEQUENCE public.user_sequence RESTART WITH 1;
ALTER SEQUENCE public.role_sequence RESTART WITH 1;