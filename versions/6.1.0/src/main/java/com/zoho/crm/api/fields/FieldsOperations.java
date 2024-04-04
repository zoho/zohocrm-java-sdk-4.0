package com.zoho.crm.api.fields;

import com.zoho.crm.api.Param;
import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.exception.SDKException;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.CommonAPIHandler;
import com.zoho.crm.api.util.Constants;

public class FieldsOperations
{
	/**
	 * The method to get fields
	 * @param paramInstance An instance of ParameterMap
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getFields(ParameterMap paramInstance) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/settings/fields");

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		handlerInstance.setParam(paramInstance);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}

	/**
	 * The method to get field
	 * @param field A Long representing the field
	 * @param paramInstance An instance of ParameterMap
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getField(Long field, ParameterMap paramInstance) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/settings/fields/");

		apiPath = apiPath.concat(field.toString());

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		handlerInstance.setParam(paramInstance);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}
	public static class GetFieldsParam
	{
		public static final Param<String> MODULE = new Param<String>("module", "com.zoho.crm.api.Fields.GetFieldsParam");

		public static final Param<String> TYPE = new Param<String>("type", "com.zoho.crm.api.Fields.GetFieldsParam");

		public static final Param<String> INCLUDE = new Param<String>("include", "com.zoho.crm.api.Fields.GetFieldsParam");

		public static final Param<String> DATA_TYPE = new Param<String>("data_type", "com.zoho.crm.api.Fields.GetFieldsParam");

		public static final Param<String> FEATURE_NAME = new Param<String>("feature_name", "com.zoho.crm.api.Fields.GetFieldsParam");

		public static final Param<String> COMPONENT = new Param<String>("component", "com.zoho.crm.api.Fields.GetFieldsParam");

		public static final Param<Long> LAYOUT_ID = new Param<Long>("layout_id", "com.zoho.crm.api.Fields.GetFieldsParam");

	}

	public static class GetFieldParam
	{
		public static final Param<String> MODULE = new Param<String>("module", "com.zoho.crm.api.Fields.GetFieldParam");

		public static final Param<String> INCLUDE = new Param<String>("include", "com.zoho.crm.api.Fields.GetFieldParam");

	}
}