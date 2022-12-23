package com.zoho.crm.api.inventorytemplates;

import com.zoho.crm.api.Param;
import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.exception.SDKException;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.CommonAPIHandler;
import com.zoho.crm.api.util.Constants;

public class InventoryTemplatesOperations
{
	/**
	 * The method to get inventory templates
	 * @param paramInstance An instance of ParameterMap
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getInventoryTemplates(ParameterMap paramInstance) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/settings/inventory_templates");

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		handlerInstance.setParam(paramInstance);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}

	/**
	 * The method to get inventory template
	 * @param templateId A Long representing the templateId
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getInventoryTemplate(Long templateId) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/settings/inventory_templates/");

		apiPath = apiPath.concat(templateId.toString());

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}
	public static class GetInventoryTemplatesParam
	{
		public static final Param<String> MODULE = new Param<String>("module", "com.zoho.crm.api.InventoryTemplates.GetInventoryTemplatesParam");

	}
}