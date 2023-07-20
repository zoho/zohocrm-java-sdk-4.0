package com.zoho.crm.api.usertypeusers;

import com.zoho.crm.api.Param;
import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.exception.SDKException;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.CommonAPIHandler;
import com.zoho.crm.api.util.Constants;

public class UserTypeUsersOperations
{
	/**
	 * The method to get users of user type
	 * @param userTypeId A Long representing the userTypeId
	 * @param portalName A String representing the portalName
	 * @param paramInstance An instance of ParameterMap
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getUsersOfUserType(Long userTypeId, String portalName, ParameterMap paramInstance) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/settings/portals/");

		apiPath = apiPath.concat(portalName.toString());

		apiPath = apiPath.concat("/user_type/");

		apiPath = apiPath.concat(userTypeId.toString());

		apiPath = apiPath.concat("/users");

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		handlerInstance.setParam(paramInstance);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}
	public static class GetUsersofUserTypeParam
	{
		public static final Param<String> FILTERS = new Param<String>("filters", "com.zoho.crm.api.UserTypeUsers.GetUsersofUserTypeParam");

		public static final Param<String> TYPE = new Param<String>("type", "com.zoho.crm.api.UserTypeUsers.GetUsersofUserTypeParam");

	}
}