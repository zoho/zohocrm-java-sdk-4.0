package com.zoho.crm.api.downloadattachments;

import com.zoho.crm.api.Param;
import com.zoho.crm.api.exception.SDKException;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.CommonAPIHandler;
import com.zoho.crm.api.util.Constants;

public class DownloadAttachmentsOperations
{
	private String module;

	private Long recordId;

	private Long userId;

	private String messageId;

	/**
	 * Creates an instance of DownloadAttachmentsOperations with the given parameters
	 * @param recordId A Long representing the recordId
	 * @param module A String representing the module
	 * @param userId A Long representing the userId
	 * @param messageId A String representing the messageId
	 */
	public DownloadAttachmentsOperations(Long recordId, String module, Long userId, String messageId)
	{
		 this.recordId = recordId;

		 this.module = module;

		 this.userId = userId;

		 this.messageId = messageId;


	}

	/**
	 * The method to get download attachments details
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getDownloadAttachmentsDetails() throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/");

		apiPath = apiPath.concat( this.module.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat( this.recordId.toString());

		apiPath = apiPath.concat("/Emails/actions/download_attachments");

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		handlerInstance.addParam(new Param<Long>("user_id", "com.zoho.crm.api.DownloadAttachments.GetDownloadAttachmentsDetailsParam"),  this.userId);

		handlerInstance.addParam(new Param<String>("message_id", "com.zoho.crm.api.DownloadAttachments.GetDownloadAttachmentsDetailsParam"),  this.messageId);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}
	public static class GetDownloadAttachmentsDetailsParam
	{
	}
}