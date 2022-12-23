package com.zoho.crm.sample.massconvert;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.massconvert.APIException;
import com.zoho.crm.api.massconvert.ActionHandler;
import com.zoho.crm.api.massconvert.AssignTo;
import com.zoho.crm.api.massconvert.Convert;
import com.zoho.crm.api.massconvert.MassConvertOperations;
import com.zoho.crm.api.massconvert.MassConvertOperations.GetJobStatusParam;
import com.zoho.crm.api.massconvert.MoveAttachmentsTo;
import com.zoho.crm.api.massconvert.RelatedModule;
import com.zoho.crm.api.massconvert.ResponseHandler;
import com.zoho.crm.api.massconvert.ResponseWrapper;
import com.zoho.crm.api.massconvert.Status;
import com.zoho.crm.api.massconvert.SuccessResponse;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Choice;
import com.zoho.crm.api.util.Model;

public class MassConvert
{
	public static void massConvert() throws Exception
	{
		MassConvertOperations massConvertOperations = new MassConvertOperations();

		Convert bodyWrapper = new Convert();
		
		bodyWrapper.setIds(new ArrayList<Long>(Arrays.asList(347706116634119l)));
		
		com.zoho.crm.api.record.Record deals = new com.zoho.crm.api.record.Record();
		
		deals.addFieldValue(com.zoho.crm.api.record.Field.Deals.AMOUNT, 10000d);
		
		deals.addFieldValue(com.zoho.crm.api.record.Field.Deals.DEAL_NAME, "V4SDK");
		
		deals.addFieldValue(com.zoho.crm.api.record.Field.Deals.CLOSING_DATE, LocalDate.of(2022, 12, 13));
		
		deals.addFieldValue(com.zoho.crm.api.record.Field.Deals.PIPELINE, new Choice<String>("Standard(Standard)"));
		
		deals.addFieldValue(com.zoho.crm.api.record.Field.Deals.STAGE, new Choice<String>("Closed Won"));

		bodyWrapper.setDeals(deals);
		
		MoveAttachmentsTo carryovertags = new MoveAttachmentsTo();
		
		carryovertags.setId(36523972179l);
		
		carryovertags.setAPIName("Contacts");
		
		bodyWrapper.setCarryOverTags(new ArrayList<MoveAttachmentsTo>(Arrays.asList(carryovertags)));
		
		List<RelatedModule> related_modules = new ArrayList<>();
		
		RelatedModule relatedmodule = new RelatedModule();
		
		relatedmodule.setAPIName("Tasks");
		
		relatedmodule.setId(36523972193l);
		
		related_modules.add(relatedmodule);
		
		bodyWrapper.setRelatedModules(related_modules);
		
		AssignTo assign_to = new AssignTo();
		
		assign_to.setId(36523972811l);
		
		bodyWrapper.setAssignTo(assign_to);
		
		MoveAttachmentsTo move_attachments_to = new MoveAttachmentsTo();
		
		move_attachments_to.setAPIName("Contacts");
		
		move_attachments_to.setId(36523972179l);
		
		bodyWrapper.setMoveAttachmentsTo(move_attachments_to);

		APIResponse<ActionHandler> response = massConvertOperations.massConvert(bodyWrapper);

		if (response != null)
		{
			// Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());

			// Check if expected response is received
			if (response.isExpected())
			{
				// Get object from response
				ActionHandler actionHandler = response.getObject();

				// Check if the request is successful
				if (actionHandler instanceof SuccessResponse)
				{
					// Get the received SuccessResponse instance
					SuccessResponse successResponse = (SuccessResponse) actionHandler;

					// Get the Status
					System.out.println("Status: " + successResponse.getStatus().getValue());

					// Get the Code
					System.out.println("Code: " + successResponse.getCode().getValue());

					System.out.println("Details: ");

					// Get the details map
					for (Map.Entry<String, Object> entry : successResponse.getDetails().entrySet())
					{
						// Get each value in the map
						System.out.println(entry.getKey() + ": " + entry.getValue());
					}

					// Get the Message
					System.out.println("Message: " + successResponse.getMessage());
				}
				// Check if the request returned an exception
				else if (actionHandler instanceof APIException)
				{
					// Get the received APIException instance
					APIException exception = (APIException) actionHandler;

					// Get the Status
					System.out.println("Status: " + exception.getStatus().getValue());

					// Get the Code
					System.out.println("Code: " + exception.getCode().getValue());

					System.out.println("Details: ");

					// Get the details map
					for (Map.Entry<String, Object> entry : exception.getDetails().entrySet())
					{
						// Get each value in the map
						System.out.println(entry.getKey() + ": " + entry.getValue());
					}

					// Get the Message
					System.out.println("Message: " + exception.getMessage());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				Field[] fields = clas.getDeclaredFields();

				for (Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	public static void getJobStatus(Long jobId) throws Exception
	{
		MassConvertOperations massConvertOperations = new MassConvertOperations();

		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(GetJobStatusParam.JOB_ID, jobId);

		APIResponse<ResponseHandler> response = massConvertOperations.getJobStatus(paramInstance);

		if (response != null)
		{
			// Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());

			if (Arrays.asList(204, 304).contains(response.getStatusCode()))
			{
				System.out.println(response.getStatusCode() == 204 ? "No Content" : "Not Modified");
				return;
			}

			// Check if expected response is received
			if (response.isExpected())
			{
				// Get object from response
				ResponseHandler responseHandler = response.getObject();

				if (responseHandler instanceof ResponseWrapper)
				{
					// Get the received ResponseWrapper instance
					ResponseWrapper responseWrapper = (ResponseWrapper) responseHandler;

					List<Status> status = responseWrapper.getData();

					for (Status status1 : status)
					{
						System.out.println("MassConvert TotalCount: " + status1.getTotalCount());

						System.out.println("MassConvert ConvertedCount: " + status1.getConvertedCount());

						System.out.println("MassConvert NotConvertedCount: " + status1.getNotConvertedCount());

						System.out.println("MassConvert FailedCount: " + status1.getFailedCount());

						System.out.println("MassConvert Status: " + status1.getStatus());
					}
				}
				// Check if the request returned an exception
				else if (responseHandler instanceof APIException)
				{
					// Get the received APIException instance
					APIException exception = (APIException) responseHandler;

					// Get the Status
					System.out.println("Status: " + exception.getStatus().getValue());

					// Get the Code
					System.out.println("Code: " + exception.getCode().getValue());

					System.out.println("Details: ");

					// Get the details map
					for (Map.Entry<String, Object> entry : exception.getDetails().entrySet())
					{
						// Get each value in the map
						System.out.println(entry.getKey() + ": " + entry.getValue());
					}

					// Get the Message
					System.out.println("Message: " + exception.getMessage());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				Field[] fields = clas.getDeclaredFields();

				for (Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}
}
