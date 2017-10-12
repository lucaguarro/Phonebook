import java.util.ArrayList;
public class Call 
{

	private int number;
	private ArrayList<CallInfo> callInfos;
	
	public Call(int number, String dateTime, boolean outgoing)
	{
		this.number = number;
		callInfos.add(new CallInfo(dateTime, outgoing));
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
	
}
