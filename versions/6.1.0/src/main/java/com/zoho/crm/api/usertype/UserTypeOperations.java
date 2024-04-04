package com.zoho.crm.api.usertype;

import com.zoho.crm.api.exception.SDKException;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.CommonAPIHandler;
import com.zoho.crm.api.util.Constants;

public class UserTypeOperations
{
	private String portalName;

	/**
	 * Creates an instance of UserTypeOperations with the given parameters
	 * @param portalName A String representing the portalName
	 */
	public UserTypeOperations(String portalName)
	{
		 this.portalName = portalName;


	}

	/**
	 * The method to get user types
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getUserTypes() throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/settings/portals/");

		apiPath = apiPath.concat( this.portalName.toString());

		apiPath = apiPath.concat("/user_type");

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}

	/**
	 * The method to get user type
	 * @param userTypeId A Long representing the userTypeId
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getUserType(Long userTypeId) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/settings/portals/");

		apiPath = apiPath.concat( this.portalName.toString());

		apiPath = apiPath.concat("/user_type/");

		apiPath = apiPath.concat(userTypeId.toString());

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}
}