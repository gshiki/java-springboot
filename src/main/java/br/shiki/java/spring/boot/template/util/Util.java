package br.shiki.java.spring.boot.template.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;


/**
 * 
 * @author Shiki
 *
 */
@Component
@PropertySource({
	"classpath:/config/routes.properties"
})
public class Util {
	
	static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	static final SimpleDateFormat SDF_APPLICATION_TIME = new SimpleDateFormat("HH:mm:ss");
	static final SimpleDateFormat SDF_SQL_DATE = new SimpleDateFormat("yyyy-MM-dd");
	static final SimpleDateFormat SDF_SQL_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static final SimpleDateFormat SDF_BR_DATE = new SimpleDateFormat("dd/MM/yyyy");
	static final SimpleDateFormat SDF_BR_TIME = new SimpleDateFormat("HH:mm");
	static final SimpleDateFormat SDF_BR_FULLDATE = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	static final SimpleDateFormat SDF_BR_FULLTIME = new SimpleDateFormat("HH:mm:ss");
	
	static final DecimalFormat DF_DECILMAL = new DecimalFormat("#0.00");
	static final DecimalFormat DF_NUMBER = new DecimalFormat("#0");
	
	/**
	 * DIA: (0?[1-9]|[12][0-9]|3[01])
	 * MES: (0?[1-9]|[12][0-2])
	 * ANO: (\\d{4})
	 * HORA: (0?[0-9]|1[0-9]|2[0-3])
	 * MINUTO: (0?[0-9]|[12345][0-9])
	 * SEGUNDO: (0?[0-9]|[12345][0-9])
	 */
	static Pattern PATTERN_DATE = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])/?(0?[1-9]|[12][0-2])?/?(\\d{4})?\\s?(0?[0-9]|1[0-9]|2[0-3])?:?(0?[0-9]|[12345][0-9])?:?(0?[0-9]|[12345][0-9])?$");
	static Pattern PATTERN_HOUR = Pattern.compile("^(0?[0-9]|1[0-9]|2[0-3])?:?(0?[0-9]|[12345][0-9])?:?(0?[0-9]|[12345][0-9])?$");
	static Pattern PATTERN_TIME = Pattern.compile("^[0-9]+:(0?[0-9]|[12345][0-9]):(0?[0-9]|[12345][0-9])$");
	static Pattern PATTERN_NUMBER = Pattern.compile("^\\d+$");
	static Pattern PATTERN_DECIMAL = Pattern.compile("^\\d+([\\.\\,]\\d+){0,1}$");
	
	static Pattern PATTERN_TIME_FORMAT = Pattern.compile("^(HH|hh):mm(:ss){0,1}$");
	static Pattern PATTERN_DECIMAL_FORMAT = Pattern.compile("^#\\,0{2,5}$");
	static Pattern PATTERN_PERCENTAGE_FORMAT = Pattern.compile("^#\\,0{2,5}%$");
	static Pattern PATTERN_NUMBER_FORMAT = Pattern.compile("^#$");
	
	
	/* ************************************************************* */
    /* 						  CONVERSIONS							 */
	/**
	 * 
	 * @param erros
	 * @return
	 */
    public static Map<String, String> convertErrorsToJSON(List<ObjectError> erros) {
    	Map<String, String> errors = new HashMap<String, String>();
		
		for (ObjectError erro : erros) {
			FieldError fieldError = (FieldError) erro;
			
			errors.put(fieldError.getField(), erro.getDefaultMessage());
		}
        return errors;
    }
    
    /* ************************************************************* */
    /* 						    DATES								 */
    /**
     * 
     * @param date
     * @return
     */
    public static String timeToString(Date date) {
    	return SDF_BR_DATE.format(date);
    }
    
    /**
     * 
     * @param date
     * @return
     */
    public static String fulltimeToString(Date date) {
    	return SDF_BR_FULLTIME.format(date);
    }
    
    /**
     * 
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
    	return SDF_BR_DATE.format(date);
    }
    
    /**
     * 
     * @param stringDate
     * @return
     * @throws ParseException
     */
    public static Date dateToSQL(String stringDate) throws ParseException {
    	if (isValid(stringDate)) {
    		String[] stringParts = stringDate.split("/");
    		String formated = stringParts[2] + "-" + stringParts[1] + "-" + stringParts[0];
    		
    		return SDF_SQL_DATE.parse(formated);
    	}
    	return null;
    }
    
    /**
     * 
     * @param date
     * @param time
     * @return
     * @throws ParseException
     */
    public static Date dateTimeToSQL(String date, String time) throws ParseException {
    	if (isValid(date) && isValid(time)) {
    		String[] stringParts = date.split("/");
    		String formated = stringParts[2] + "-" + stringParts[1] + "-" + stringParts[0];
    		
    		return SDF_SQL_DATETIME.parse(formated + " " + time);
    	}
    	return null;
    }
    
    /* ************************************************************* */
    /* 						  VALIDACOES							 */
    /**
     * 
     */
    public static boolean isValid(String string) {
    	return string != null && !"null".equals(string.toLowerCase()) && !string.isEmpty();
    }
    
    /**
     * 
     * @param number
     * @return
     */
    public static boolean isValid(Long number) {
    	return number != null && isValid(number.toString());
    }
    
    /**
     * 
     * @param decimalNumber
     * @return
     */
    public static boolean isValid(Double decimalNumber) {
    	return decimalNumber != null && isValid(decimalNumber.toString());
    }

}
