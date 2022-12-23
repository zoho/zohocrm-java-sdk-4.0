package com.zoho.crm.api.emailrelatedrecords;

import com.zoho.crm.api.Param;
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

	/**
	 * Creates an instance of EmailRelatedRecordsOperations with the given parameters
	 * @param record A Long representing the record
	 * @param modulename A String representing the modulename
	 * @param type A String representing the type
	 * @param index A String representing the index
	 */
	public EmailRelatedRecordsOperations(Long record, String modulename, String type, String index)
	{
		 this.record = record;

		 this.modulename = modulename;

		 this.type = type;

		 this.index = index;


	}

	/**
	 * The method to get related email
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getRelatedEmail() throws SDKException
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

		handlerInstance.addParam(new Param<String>("type", "com.zoho.crm.api.EmailRelatedRecords.GetRelatedEmailParam"),  this.type);

		handlerInstance.addParam(new Param<String>("index", "com.zoho.crm.api.EmailRelatedRecords.GetRelatedEmailParam"),  this.index);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}
	public static class GetRelatedEmailParam
	{
	}
}