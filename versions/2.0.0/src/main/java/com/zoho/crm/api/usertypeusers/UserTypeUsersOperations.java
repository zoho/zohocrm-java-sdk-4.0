package com.zoho.crm.api.usertypeusers;

import com.zoho.crm.api.Param;
import com.zoho.crm.api.exception.SDKException;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.CommonAPIHandler;
import com.zoho.crm.api.util.Constants;

public class UserTypeUsersOperations
{
	private String portalName;

	private Long userTypeId;

	private String filters;

	private String type;

	/**
	 * Creates an instance of UserTypeUsersOperations with the given parameters
	 * @param userTypeId A Long representing the userTypeId
	 * @param portalName A String representing the portalName
	 * @param filters A String representing the filters
	 * @param type A String representing the type
	 */
	public UserTypeUsersOperations(Long userTypeId, String portalName, String filters, String type)
	{
		 this.userTypeId = userTypeId;

		 this.portalName = portalName;

		 this.filters = filters;

		 this.type = type;


	}

	/**
	 * The method to get users of user type
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getUsersOfUserType() throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/settings/portals/");

		apiPath = apiPath.concat( this.portalName.toString());

		apiPath = apiPath.concat("/user_type/");

		apiPath = apiPath.concat( this.userTypeId.toString());

		apiPath = apiPath.concat("/users");

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		handlerInstance.addParam(new Param<String>("filters", "com.zoho.crm.api.UserTypeUsers.GetUsersofUserTypeParam"),  this.filters);

		handlerInstance.addParam(new Param<String>("type", "com.zoho.crm.api.UserTypeUsers.GetUsersofUserTypeParam"),  this.type);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}
	public static class GetUsersofUserTypeParam
	{
	}
}