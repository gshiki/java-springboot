/* ********** PERFIS DE USUARIOS INICIAIS DO SISTEMA ********** */
INSERT INTO users_profiles(user_id, profile_id) SELECT 1, 1 WHERE NOT EXISTS (
	SELECT 1 FROM users_profiles WHERE user_id=1 AND profile_id=1 );