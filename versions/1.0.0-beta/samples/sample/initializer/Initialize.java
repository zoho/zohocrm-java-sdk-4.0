package com.zoho.crm.sample.initializer;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.api.authenticator.store.DBStore;
import com.zoho.api.authenticator.store.FileStore;

import com.zoho.api.authenticator.store.TokenStore;
import com.zoho.api.logger.Logger;
import com.zoho.api.logger.Logger.Builder;
import com.zoho.api.logger.Logger.Levels;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.RequestProxy;
import com.zoho.crm.api.SDKConfig;
import com.zoho.crm.api.UserSignature;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.dc.USDataCenter;

public class Initialize
{
	public static void main(String[] args) throws Exception
	{
		initialize();
	}

	public static void initialize() throws Exception
	{
		/*
		 * Create an instance of Logger Class that takes two parameters 
		 * level -> Level of the log messages to be logged. Can be configured by typing Levels "." and choose any level from the list displayed. 
		 * filePath -> Absolute file path, where messages need to be logged.
		 */
		Logger logger = new Logger.Builder()
				.filePath("/usr/sdk_log.log")
				.level(Levels.ALL)
				.build();

		// Create an UserSignature instance that takes user Email as parameter
		UserSignature user = new UserSignature("user@zoho.com");

		/*
		 * Configure the environment which is of the pattern Domain.Environment Available Domains: USDataCenter, EUDataCenter, INDataCenter, CNDataCenter, AUDataCenter Available Environments: PRODUCTION, DEVELOPER, SANDBOX
		 */
		Environment environment = USDataCenter.PRODUCTION;

		/*
		 * Create a Token instance 1 -> OAuth client id. 2 -> OAuth client secret. 3 -> REFRESH/GRANT token. 4 -> token type. 5 -> OAuth redirect URL.
		 */
		Token token = new OAuthToken.Builder()
				.clientID("xxxxx")
				.clientSecret("xxxxx")
//				.refreshToken("1000.xxx.xxxx")
				.redirectURL("https://www.zoho.com")
				.grantToken("1000.xxx.xxx")
//				.accessToken("1000.xxxx.xxxx")
				.build();

		/*
		 * Create an instance of TokenStore. 
		 * 1 -> DataBase host name. Default "jdbc:mysql://localhost" 
		 * 2 -> DataBase name. Default "zohooauth" 
		 * 3 -> DataBase user name. Default "root" 
		 * 4 -> DataBase password. Default "" 
		 * 5 -> DataBase port number. Default "3306"
		 */
//		 TokenStore tokenstore = new DBStore();

//		TokenStore tokenstore = new DBStore.Builder().tableName("oauthtoken").build();

		TokenStore tokenstore = new FileStore("/usr/java_sdk_token.txt");

		SDKConfig config = new SDKConfig.Builder()
		.autoRefreshFields(true)
		.pickListValidation(false)
		.build();

		String resourcePath = "/usr";

		/*
		 * Create an instance of RequestProxy 
		 * 1 -> proxyHost 
		 * 2 -> proxyPort 
		 * 3 -> proxyUser 
		 * 4 -> password 
		 * 5 -> userDomain
		 */
//		RequestProxy requestProxy = new RequestProxy.Builder()
//				.host("proxyHost")
//				.port(80)
//				.user("proxyUser")
//				.password("password")
//				.userDomain("userDomain")
//				.build();

		/*
		 * Call static initialize method of Initializer class that takes the arguments 1 -> UserSignature instance 2 -> Environment instance 3 -> Token instance 4 -> TokenStore instance 4 -> autoRefreshFields - true - all the modules' fields will be auto-refreshed in the background whenever there is any change. - false - the fields will not be auto-refreshed in the background. The user can manually delete the file(s) or the specific module's fields using methods from ModuleFieldsHandler 5 -> The path containing the absolute directory path to store user specific JSON files containing module fields information. 7 -> Logger instance 8 -> UserProxy instance
		 */
		new Initializer.Builder()
		.user(user)
		.environment(environment)
		.token(token)
		.SDKConfig(config)
		.store(tokenstore)
		.resourcePath(resourcePath)
		.logger(logger)
		.initialize();

//		token.remove();
	}
}
