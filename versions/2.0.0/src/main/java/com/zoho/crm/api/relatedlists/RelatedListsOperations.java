package com.zoho.crm.api.relatedlists;

import com.zoho.crm.api.Param;
import com.zoho.crm.api.exception.SDKException;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.CommonAPIHandler;
import com.zoho.crm.api.util.Constants;

public class RelatedListsOperations
{
	private String module;

	private Long layoutId;

	/**
	 * Creates an instance of RelatedListsOperations with the given parameters
	 * @param module A String representing the module
	 * @param layoutId A Long representing the layoutId
	 */
	public RelatedListsOperations(String module, Long layoutId)
	{
		 this.module = module;

		 this.layoutId = layoutId;


	}

	/**
	 * The method to get related lists
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getRelatedLists() throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/settings/related_lists");

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		handlerInstance.addParam(new Param<String>("module", "com.zoho.crm.api.RelatedLists.GetRelatedListsParam"),  this.module);

		handlerInstance.addParam(new Param<Long>("layout_id", "com.zoho.crm.api.RelatedLists.GetRelatedListsParam"),  this.layoutId);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}

	/**
	 * The method to get related list
	 * @param relatedListId A Long representing the relatedListId
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getRelatedList(Long relatedListId) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/settings/related_lists/");

		apiPath = apiPath.concat(relatedListId.toString());

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		handlerInstance.addParam(new Param<String>("module", "com.zoho.crm.api.RelatedLists.GetRelatedListParam"),  this.module);

		handlerInstance.addParam(new Param<Long>("layout_id", "com.zoho.crm.api.RelatedLists.GetRelatedListParam"),  this.layoutId);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}
	public static class GetRelatedListsParam
	{
	}

	public static class GetRelatedListParam
	{
	}
}