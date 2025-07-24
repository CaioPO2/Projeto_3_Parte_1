/**
 * 
 */
/**
 * 
 */
module ExercicioModulo29 {
	requires transitive junit;
	requires transitive java.sql;
	
	exports exercicio.domain to junit;
	exports exercicio.dao.generic.jdbc to junit;
	exports exercicio.dao.generic.jdbc.dao to junit;
	exports exercicio.test to junit;
}