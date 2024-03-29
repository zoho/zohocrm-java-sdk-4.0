package threading.singleuser;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.api.authenticator.store.DBStore;
import com.zoho.api.authenticator.store.TokenStore;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.record.RecordOperations;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.record.RecordOperations.GetRecordsParam;
import com.zoho.crm.api.ParameterMap;

public class SingleThread extends Thread
{
	String moduleAPIName;

	public SingleThread(String moduleAPIName)
	{
		this.moduleAPIName = moduleAPIName;
	}

	public void run()
	{
		try
		{
			RecordOperations cro = new RecordOperations();
			ParameterMap paramInstance = new ParameterMap();
			List<String> fieldNames = new ArrayList<String>(Arrays.asList("Company", "Email"));
			paramInstance.add(GetRecordsParam.FIELDS, String.join(",", fieldNames));
			@SuppressWarnings("rawtypes")
			APIResponse getResponse = cro.getRecords(this.moduleAPIName, paramInstance, null);
			System.out.println(getResponse.getObject());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception
	{
		Environment env = USDataCenter.PRODUCTION;
		TokenStore tokenstore = new DBStore.Builder().build();
		Token token1 = new OAuthToken.Builder().clientID("1000.xxxxx").clientSecret("xxx").refreshToken("1000.xxx.xxxx").redirectURL("https://www.zoho.com").build();
		new Initializer.Builder().environment(env).token(token1).store(tokenstore).initialize();
		SingleThread stsu = new SingleThread("Leads");
		stsu.start();
	}
}
