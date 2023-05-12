package com.zoho.crm.api.downloadinlineimages;

import com.zoho.crm.api.Param;
import com.zoho.crm.api.exception.SDKException;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.CommonAPIHandler;
import com.zoho.crm.api.util.Constants;

public class DownloadInlineImagesOperations
{
	private String module;

	private Long recordId;

	private Long userId;

	private String messageId;

	private String id;

	/**
	 * Creates an instance of DownloadInlineImagesOperations with the given parameters
	 * @param recordId A Long representing the recordId
	 * @param module A String representing the module
	 * @param userId A Long representing the userId
	 * @param messageId A String representing the messageId
	 * @param id A String representing the id
	 */
	public DownloadInlineImagesOperations(Long recordId, String module, Long userId, String messageId, String id)
	{
		 this.recordId = recordId;

		 this.module = module;

		 this.userId = userId;

		 this.messageId = messageId;

		 this.id = id;


	}

	/**
	 * The method to get download inline images
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getDownloadInlineImages() throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/");

		apiPath = apiPath.concat( this.module.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat( this.recordId.toString());

		apiPath = apiPath.concat("/Emails/actions/download_inline_images");

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		handlerInstance.addParam(new Param<Long>("user_id", "com.zoho.crm.api.DownloadInlineImages.GetDownloadInlineImagesParam"),  this.userId);

		handlerInstance.addParam(new Param<String>("message_id", "com.zoho.crm.api.DownloadInlineImages.GetDownloadInlineImagesParam"),  this.messageId);

		handlerInstance.addParam(new Param<String>("id", "com.zoho.crm.api.DownloadInlineImages.GetDownloadInlineImagesParam"),  this.id);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}
	public static class GetDownloadInlineImagesParam
	{
	}
}