package com.zoho.crm.api.holidays;

import com.zoho.crm.api.Header;
import com.zoho.crm.api.HeaderMap;
import com.zoho.crm.api.Param;
import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.exception.SDKException;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.CommonAPIHandler;
import com.zoho.crm.api.util.Constants;

public class HolidaysOperations
{
	/**
	 * The method to create holidays
	 * @param request An instance of Holidays
	 * @param headerInstance An instance of HeaderMap
	 * @return An instance of APIResponse<ActionHandler>
	 * @throws SDKException
	 */
	public APIResponse<ActionHandler> createHolidays(Holidays request, HeaderMap headerInstance) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/settings/holidays");

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_POST);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_CREATE);

		handlerInstance.setContentType("application/json");

		handlerInstance.setRequest(request);

		handlerInstance.setMandatoryChecker(true);

		handlerInstance.setHeader(headerInstance);

		return handlerInstance.apiCall(ActionHandler.class, "application/json");

	}

	/**
	 * The method to update holidays
	 * @param request An instance of Holidays
	 * @param headerInstance An instance of HeaderMap
	 * @return An instance of APIResponse<ActionHandler>
	 * @throws SDKException
	 */
	public APIResponse<ActionHandler> updateHolidays(Holidays request, HeaderMap headerInstance) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/settings/holidays");

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_PUT);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_UPDATE);

		handlerInstance.setContentType("application/json");

		handlerInstance.setRequest(request);

		handlerInstance.setMandatoryChecker(true);

		handlerInstance.setHeader(headerInstance);

		return handlerInstance.apiCall(ActionHandler.class, "application/json");

	}

	/**
	 * The method to get holidays
	 * @param paramInstance An instance of ParameterMap
	 * @param headerInstance An instance of HeaderMap
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getHolidays(ParameterMap paramInstance, HeaderMap headerInstance) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/settings/holidays");

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		handlerInstance.setParam(paramInstance);

		handlerInstance.setHeader(headerInstance);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}

	/**
	 * The method to delete holiday
	 * @param holidayId A Long representing the holidayId
	 * @param headerInstance An instance of HeaderMap
	 * @return An instance of APIResponse<ActionHandler>
	 * @throws SDKException
	 */
	public APIResponse<ActionHandler> deleteHoliday(Long holidayId, HeaderMap headerInstance) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v4/settings/holidays/");

		apiPath = apiPath.concat(holidayId.toString());

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_DELETE);

		handlerInstance.setCategoryMethod(Constants.REQUEST_METHOD_DELETE);

		handlerInstance.setHeader(headerInstance);

		return handlerInstance.apiCall(ActionHandler.class, "application/json");

	}
	public static class CreateHolidaysHeader
	{
		public static final Header<String> X_CRM_ORG = new Header<String>("X-CRM-ORG", "com.zoho.crm.api.Holidays.CreateHolidaysHeader");

	}

	public static class UpdateHolidaysHeader
	{
		public static final Header<String> X_CRM_ORG = new Header<String>("X-CRM-ORG", "com.zoho.crm.api.Holidays.UpdateHolidaysHeader");

	}

	public static class GetHolidaysParam
	{
		public static final Param<Integer> YEAR = new Param<Integer>("year", "com.zoho.crm.api.Holidays.GetHolidaysParam");

		public static final Param<String> TYPE = new Param<String>("type", "com.zoho.crm.api.Holidays.GetHolidaysParam");

		public static final Param<Long> SHIFT_ID = new Param<Long>("shift_id", "com.zoho.crm.api.Holidays.GetHolidaysParam");

	}

	public static class GetHolidaysHeader
	{
		public static final Header<String> X_CRM_ORG = new Header<String>("X-CRM-ORG", "com.zoho.crm.api.Holidays.GetHolidaysHeader");

	}

	public static class DeleteHolidayHeader
	{
		public static final Header<String> X_CRM_ORG = new Header<String>("X-CRM-ORG", "com.zoho.crm.api.Holidays.DeleteHolidayHeader");

	}
}