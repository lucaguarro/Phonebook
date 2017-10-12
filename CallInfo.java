
public class CallInfo {
	private String dateTime;
	private boolean outgoing;
	
	public CallInfo(String dateTime, boolean outgoing)
	{
		this.dateTime = dateTime;
		this.outgoing = outgoing;
	}
	
	public boolean getOutgoing()
	{
		return outgoing;
	}
	
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
