/* ********** USUARIOS INICIAIS DO SISTEMA ********** */
INSERT INTO users(create_date, email, login, password, name, status_id, update_date) SELECT '2019-01-01 00:00:00', 'gshiki@gmail.com', 'superadmin', 'pass', 'Super Admin User', 1, '2019-01-01 00:00:00' WHERE NOT EXISTS (
	SELECT 1 FROM users WHERE login='superadmin' AND name='Super Admin User' );