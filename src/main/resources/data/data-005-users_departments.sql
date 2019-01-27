/* ********** DEPARTAMENTOS DE USUARIOS INICIAIS DO SISTEMA ********** */
INSERT INTO users_departments(user_id, department_id) SELECT 1, 1 WHERE NOT EXISTS (
	SELECT 1 FROM users_departments WHERE user_id=1 AND department_id=1 );