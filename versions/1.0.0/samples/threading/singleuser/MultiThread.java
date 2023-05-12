package threading.singleuser;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.api.authenticator.store.FileStore;
import com.zoho.api.authenticator.store.TokenStore;
import com.zoho.api.logger.Logger;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.record.RecordOperations;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.record.RecordOperations.GetRecordsParam;
import com.zoho.crm.api.ParameterMap;

public class MultiThread extends Thread
{
	String moduleAPIName;

	public MultiThread(String moduleAPIName)
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
		Logger loggerInstance = new Logger.Builder().level(Logger.Levels.ALL).filePath("/Users/Documents/AutomateSDK/java/sdk.log").build();
		Environment env = USDataCenter.PRODUCTION;
		TokenStore tokenstore = new FileStore("/Users/Documents/AutomateSDK/java/GitLab/java_sdk_token.txt");
		Token token1 = new OAuthToken.Builder().clientID("1000.xxxx").clientSecret("xxxxx").refreshToken("1000.xxx.xxx").redirectURL("https://www.zoho.com").build();
		new Initializer.Builder().environment(env).token(token1).store(tokenstore).logger(loggerInstance).initialize();
		MultiThread mtsu = new MultiThread("Deals");
		mtsu.start();
		mtsu = new MultiThread("Leads");
		mtsu.start();
	}
}
