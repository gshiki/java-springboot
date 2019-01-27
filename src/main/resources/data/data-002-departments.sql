/* ********** DEPARTAMENTOS INICIAIS DO SISTEMA ********** */
INSERT INTO department(description, label, symbol) SELECT 'General Department', 'GENERAL_DEPARTMENT', 'GD' WHERE NOT EXISTS (
	SELECT 1 FROM department WHERE description='General Department' AND label='GENERAL_DEPARTMENT' AND symbol='GD' );