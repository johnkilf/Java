class testConverter{

    //Main method to demonstrate sample usage of TimestampConverter Class
    
	public static void main(String[] args) {

		//mysql timestamp returned as string in following format:
		String returnedDate = "2006-05-22 14:04:05";
		
		//to get it in a nice date format use following method:
		String newString = TimestampConverter.convertToNiceDate(returnedDate);
		System.out.println(newString);
		
		//to get it in a nice date/time format use following method:
		newString = TimestampConverter.convertToNiceDateTime(returnedDate);
		System.out.println(newString);
		
		//to get it as a long (for comparisons)
		long longtime = TimestampConverter.convertToLong(returnedDate);
		System.out.println(longtime);
	}
}
