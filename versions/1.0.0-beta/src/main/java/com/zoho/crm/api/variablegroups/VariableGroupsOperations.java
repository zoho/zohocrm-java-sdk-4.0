package com.zoho.crm.api.variablegroups;

import com.zoho.crm.api.exception.SDKException;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.CommonAPIHandler;
import com.zoho.crm.api.util.Constants;

public class VariableGroupsOperations
{
	/**
	 * The method to get variable groups
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getVariableGroups() throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/settings/variable_groups");

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}

	/**
	 * The method to get variable group by id
	 * @param variableGroupId A Long representing the variableGroupId
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getVariableGroupById(Long variableGroupId) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/settings/variable_groups/");

		apiPath = apiPath.concat(variableGroupId.toString());

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}

	/**
	 * The method to get variable group by api name
	 * @param apiName A String representing the apiName
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getVariableGroupByAPIName(String apiName) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/settings/variable_groups/");

		apiPath = apiPath.concat(apiName.toString());

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}
}