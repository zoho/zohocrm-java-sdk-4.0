package com.zoho.crm.sample.masschangeowner;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.masschangeowner.APIException;
import com.zoho.crm.api.masschangeowner.ActionHandler;
import com.zoho.crm.api.masschangeowner.ActionWrapper;
import com.zoho.crm.api.masschangeowner.Data;
import com.zoho.crm.api.masschangeowner.MassChangeOwnerOperations;
import com.zoho.crm.api.masschangeowner.MassChangeOwnerOperations.CheckStatusParam;
import com.zoho.crm.api.masschangeowner.Owner;
import com.zoho.crm.api.masschangeowner.ResponseHandler;
import com.zoho.crm.api.masschangeowner.ResponseWrapper;
import com.zoho.crm.api.masschangeowner.Status;
import com.zoho.crm.api.masschangeowner.SuccessResponse;
import com.zoho.crm.api.masschangeowner.Territory;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class MassChangeOwner
{
	public static void changeOwner(String moduleAPIName) throws Exception
	{
		MassChangeOwnerOperations massChangeOwnerOperations = new MassChangeOwnerOperations();

		Data bodyWrapper = new Data();

		bodyWrapper.setCvid(347706115237021l);

		Owner owner = new Owner();

		owner.setId(3477061173021l);

		bodyWrapper.setOwner(owner);

		Territory territory = new Territory();

		territory.setId(365239776223l);

		territory.setIncludeChild(true);

		bodyWrapper.setTerritory(territory);

		APIResponse<ActionHandler> response = massChangeOwnerOperations.changeOwner(moduleAPIName, bodyWrapper);

		if (response != null)
		{
			// Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());

			// Check if expected response is received
			if (response.isExpected())
			{
				// Get object from response
				ActionHandler actionHandler = response.getObject();

				if (actionHandler instanceof ActionWrapper)
				{
					// Get the received ActionWrapper instance
					ActionWrapper actionWrapper = (ActionWrapper) actionHandler;

					// Get the list of obtained ActionResponse instances
					List<SuccessResponse> actionResponses = actionWrapper.getData();

					for (SuccessResponse actionResponse : actionResponses)
					{
						// Check if the request is successful
						if (actionResponse instanceof SuccessResponse)
						{
							// Get the received SuccessResponse instance
							SuccessResponse successResponse = (SuccessResponse) actionResponse;

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
					}
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

	public static void checkStatus(Long jobId, String module) throws Exception
	{
		MassChangeOwnerOperations massChangeOwnerOperations = new MassChangeOwnerOperations();

		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(CheckStatusParam.JOB_ID, jobId);

		APIResponse<ResponseHandler> response = massChangeOwnerOperations.checkStatus(module, paramInstance);

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
						System.out.println("MassChangeOwner TotalCount: " + status1.getTotalCount());

						System.out.println("MassChangeOwner UpdatedCount: " + status1.getUpdatedCount());

						System.out.println("MassChangeOwner NotUpdatedCount: " + status1.getNotUpdatedCount());

						System.out.println("MassChangeOwner FailedCount: " + status1.getFailedCount());

						System.out.println("MassChangeOwner Status: " + status1.getStatus());
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
