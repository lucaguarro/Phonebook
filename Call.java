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
	
	public String callInfoToString()
	{
		String text = "";
		for(CallInfo callInfo : callInfos)
		{
			text += callInfo.toString();
		}
		return text;
	}
	
	public void addHistory(boolean outgoing)
	{
		callInfos.add(new CallInfo(outgoing));
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}	
	
}
