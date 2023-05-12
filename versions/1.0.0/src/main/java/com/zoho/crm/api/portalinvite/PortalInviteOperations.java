package com.zoho.crm.api.portalinvite;

import com.zoho.crm.api.Param;
import com.zoho.crm.api.exception.SDKException;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.CommonAPIHandler;
import com.zoho.crm.api.util.Constants;

public class PortalInviteOperations
{
	private String module;

	private Long record;

	private Long userTypeId;

	private String type;

	/**
	 * Creates an instance of PortalInviteOperations with the given parameters
	 * @param record A Long representing the record
	 * @param module A String representing the module
	 * @param userTypeId A Long representing the userTypeId
	 * @param type A String representing the type
	 */
	public PortalInviteOperations(Long record, String module, Long userTypeId, String type)
	{
		 this.record = record;

		 this.module = module;

		 this.userTypeId = userTypeId;

		 this.type = type;


	}

	/**
	 * The method to invite users
	 * @return An instance of APIResponse<ActionHandler>
	 * @throws SDKException
	 */
	public APIResponse<ActionHandler> inviteUsers() throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/");

		apiPath = apiPath.concat( this.module.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat( this.record.toString());

		apiPath = apiPath.concat("/actions/portal_invite");

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_POST);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_ACTION);

		handlerInstance.addParam(new Param<Long>("user_type_id", "com.zoho.crm.api.PortalInvite.InviteUsersParam"),  this.userTypeId);

		handlerInstance.addParam(new Param<String>("type", "com.zoho.crm.api.PortalInvite.InviteUsersParam"),  this.type);

		return handlerInstance.apiCall(ActionHandler.class, "application/json");

	}
	public static class InviteUsersParam
	{
	}
}