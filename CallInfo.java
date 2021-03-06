import java.time.LocalDateTime;

public class CallInfo {
	private String dateTime;
	private boolean outgoing;
	
	public CallInfo(boolean outgoing)
	{
		this.dateTime = LocalDateTime.now().toString();
		this.outgoing = outgoing;
	}
	
	public boolean getOutgoing()
	{
		return outgoing;
	}
	
	/**
	 * Converts the call info into a single line of text containing the date/time and whether
	 * or not it was outgoing
	 * @return String - that line of text
	 */
	public String toString()
	{
		if (outgoing)
		{
			return "Outgoing: " + dateTime + "\n"; 
		}
		else
		{
			return "Incoming: " + dateTime + "\n";
		}
	}
}
