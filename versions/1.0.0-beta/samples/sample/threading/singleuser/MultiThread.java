package com.zoho.crm.sample.threading.singleuser;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.api.authenticator.store.FileStore;
import com.zoho.api.authenticator.store.TokenStore;
import com.zoho.api.logger.Logger;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.SDKConfig;
import com.zoho.crm.api.UserSignature;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.record.RecordOperations;
import com.zoho.crm.api.util.APIResponse;

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

			@SuppressWarnings("rawtypes")
			APIResponse getResponse = cro.getRecords(this.moduleAPIName, null, null);

			System.out.println(getResponse.getObject());

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception
	{
		Logger loggerInstance = new Logger.Builder().level(Logger.Levels.ALL).filePath("/usr/sdk.log").build();

		Environment env = USDataCenter.PRODUCTION;

		UserSignature user1 = new UserSignature("abc1@zoho.com");

		TokenStore tokenstore = new FileStore("/usr/java_sdk_token.txt");

		Token token1 = new OAuthToken.Builder().clientID("1000.xxxxx").clientSecret("xxxxx").refreshToken("1000.xxxx.xxxxx").redirectURL("https://www.zoho.com").build();

		String resourcePath = "/usr";

		SDKConfig sdkConfig = new SDKConfig.Builder().autoRefreshFields(false).pickListValidation(true).build();

		new Initializer.Builder().user(user1).environment(env).token(token1).store(tokenstore).SDKConfig(sdkConfig).resourcePath(resourcePath).logger(loggerInstance).initialize();

		MultiThread mtsu = new MultiThread("Deals");

		mtsu.start();

		mtsu = new MultiThread("Leads");

		mtsu.start();
	}
}
