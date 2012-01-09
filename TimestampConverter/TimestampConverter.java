import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class TimestampConverter{

    //takes a time string with a specified format and converts it to strings
    //or longs that are easier to read/utilize

	static SimpleDateFormat dateFormat;
	
	static{
		dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//if database returns split seconds use this instead:
		//dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	}
	
	public static String convertToNiceDate(String input)
	{
	    //converts input to string of form 22-May-2006
	    
        java.sql.Timestamp timestamp = parseDate(input);

		String newString = DateFormat.getDateInstance(DateFormat.MEDIUM).format(timestamp);
		return newString;

	}

	public static String convertToNiceDateTime(String input)
	{
        //converts input to string of form 22-May-2006 14:04
        
        java.sql.Timestamp timestamp = parseDate(input);

		String newString = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(timestamp);
		return newString;

	}

	public static long convertToLong(String input)
	{
        //converts input to long
        
        java.sql.Timestamp timestamp = parseDate(input);

		long tsLong = timestamp.getTime();
		return tsLong;
	}
	
	private static java.sql.Timestamp parseDate(String input)
	{
	    try{
            java.util.Date parsedDate = dateFormat.parse(input);
		    return new java.sql.Timestamp(parsedDate.getTime());
		}catch(java.text.ParseException e)
		{
			System.out.println("ERROR: Date Parse exception");
			//returns epoch start time if input can't be parsed
			return new java.sql.Timestamp(0);
		}
		
	}
}
