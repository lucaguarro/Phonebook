import java.util.ArrayList;
public class Call 
{

	private int number;
	private ArrayList<CallInfo> callInfos;
	
	public Call(int number, boolean outgoing)
	{
		this.number = number;
		callInfos.add(new CallInfo(outgoing));
	}
	
	/**
	 * Generate a string containing all the call info contained in this call object
	 * Does not include who called or whether or not it was outgoing
	 * @return String containing all that call info
	 */
	public String callInfoToString()
	{
		String text = "";
		for(CallInfo callInfo : callInfos)
		{
			text += callInfo.toString();
		}
		return text;
	}
	
	/**
	 * Add a new entry to this call's history
	 * @param outgoing - Whether or not it was outgoing
	 */
	public void addHistory(boolean outgoing)
	{
		callInfos.add(new CallInfo(outgoing));
	}

	/**
	 * @return number - The number who made this call/this call was made to
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number - The number who made this call/this call was made to to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}	
	
}
