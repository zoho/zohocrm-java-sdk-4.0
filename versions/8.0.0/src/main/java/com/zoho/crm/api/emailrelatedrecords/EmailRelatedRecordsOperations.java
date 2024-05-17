package com.zoho.crm.api.emailrelatedrecords;

import com.zoho.crm.api.Param;
import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.exception.SDKException;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.CommonAPIHandler;
import com.zoho.crm.api.util.Constants;

public class EmailRelatedRecordsOperations
{
	private String modulename;

	private Long record;

	private String type;

	private String index;

	private String ownerId;

	/**
	 * Creates an instance of EmailRelatedRecordsOperations with the given parameters
	 * @param record A Long representing the record
	 * @param modulename A String representing the modulename
	 * @param type A String representing the type
	 * @param index A String representing the index
	 * @param ownerId A String representing the ownerId
	 */
	public EmailRelatedRecordsOperations(Long record, String modulename, String type, String index, String ownerId)
	{
		 this.record = record;

		 this.modulename = modulename;

		 this.type = type;

		 this.index = index;

		 this.ownerId = ownerId;


	}

	/**
	 * The method to get related emails
	 * @param paramInstance An instance of ParameterMap
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getRelatedEmails(ParameterMap paramInstance) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/");

		apiPath = apiPath.concat( this.modulename.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat( this.record.toString());

		apiPath = apiPath.concat("/Emails");

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		handlerInstance.addParam(new Param<String>("type", "com.zoho.crm.api.EmailRelatedRecords.GetRelatedEmailsParam"),  this.type);

		handlerInstance.addParam(new Param<String>("index", "com.zoho.crm.api.EmailRelatedRecords.GetRelatedEmailsParam"),  this.index);

		handlerInstance.addParam(new Param<String>("owner_id", "com.zoho.crm.api.EmailRelatedRecords.GetRelatedEmailsParam"),  this.ownerId);

		handlerInstance.setParam(paramInstance);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}

	/**
	 * The method to get related email
	 * @param messageId A String representing the messageId
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getRelatedEmail(String messageId) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/");

		apiPath = apiPath.concat( this.modulename.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat( this.record.toString());

		apiPath = apiPath.concat("/Emails/");

		apiPath = apiPath.concat(messageId.toString());

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		handlerInstance.addParam(new Param<String>("type", "com.zoho.crm.api.EmailRelatedRecords.GetRelatedEmailParam"),  this.type);

		handlerInstance.addParam(new Param<String>("index", "com.zoho.crm.api.EmailRelatedRecords.GetRelatedEmailParam"),  this.index);

		handlerInstance.addParam(new Param<String>("owner_id", "com.zoho.crm.api.EmailRelatedRecords.GetRelatedEmailParam"),  this.ownerId);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}
	public static class GetRelatedEmailsParam
	{
		public static final Param<String> FILTER = new Param<String>("filter", "com.zoho.crm.api.EmailRelatedRecords.GetRelatedEmailsParam");

	}

	public static class GetRelatedEmailParam
	{
	}
}