/* ********** SYSTEM INITIAL PROFILES ********** */
INSERT INTO profile(description, label) SELECT 'Super Admin', 'SUPER_ADMIN' WHERE NOT EXISTS (
	SELECT 1 FROM profile WHERE description='Super Admin' AND label='SUPER_ADMIN' );
INSERT INTO profile(description, label) SELECT 'Admin', 'ADMIN' WHERE NOT EXISTS (
	SELECT 1 FROM profile WHERE description='Admin' AND label='ADMIN' );
INSERT INTO profile(description, label) SELECT 'User', 'USER' WHERE NOT EXISTS (
	SELECT 1 FROM profile WHERE description='User' AND label='USER' );