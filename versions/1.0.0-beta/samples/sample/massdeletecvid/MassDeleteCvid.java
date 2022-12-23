package com.zoho.crm.sample.massdeletecvid;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.massdeletecvid.APIException;
import com.zoho.crm.api.massdeletecvid.ActionHandler;
import com.zoho.crm.api.massdeletecvid.ActionResponse;
import com.zoho.crm.api.massdeletecvid.ActionWrapper;
import com.zoho.crm.api.massdeletecvid.BodyWrapper;
import com.zoho.crm.api.massdeletecvid.MassDeleteCvidOperations;
import com.zoho.crm.api.massdeletecvid.MassDeleteCvidOperations.GetMassDeleteStatusParam;
import com.zoho.crm.api.massdeletecvid.MassDeleteScheduled;
import com.zoho.crm.api.massdeletecvid.RecordDeleted;
import com.zoho.crm.api.massdeletecvid.ResponseHandler;
import com.zoho.crm.api.massdeletecvid.ResponseWrapper;
import com.zoho.crm.api.massdeletecvid.Status;
import com.zoho.crm.api.massdeletecvid.Territory;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class MassDeleteCvid
{
	public static void massDeleteByCvid(String moduleAPIName) throws Exception
	{
		MassDeleteCvidOperations massDeleteCvidOperations = new MassDeleteCvidOperations(moduleAPIName);

		BodyWrapper bodyWrapper = new BodyWrapper();

		bodyWrapper.setCvid(347706187511l);

		bodyWrapper.setIds(new ArrayList<Long>(Arrays.asList(347706116634118l)));

		Territory territory = new Territory();

		territory.setId(0l);

		territory.setIncludeChild(true);

		bodyWrapper.setTerritory(territory);

		APIResponse<ActionHandler> response = massDeleteCvidOperations.massDeleteByCvid(bodyWrapper);

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
					List<ActionResponse> actionResponses = actionWrapper.getData();

					for (ActionResponse actionResponse : actionResponses)
					{
						// Check if the request is successful
						if (actionResponse instanceof RecordDeleted)
						{
							// Get the received SuccessResponse instance
							RecordDeleted successResponse = (RecordDeleted) actionResponse;

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
						// Check if the request is successful
						else if (actionResponse instanceof MassDeleteScheduled)
						{
							// Get the received SuccessResponse instance
							MassDeleteScheduled successResponse = (MassDeleteScheduled) actionResponse;

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
						else if (actionResponse instanceof APIException)
						{
							// Get the received APIException instance
							APIException exception = (APIException) actionResponse;

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

	public static void getMassDeleteStatus(Long jobId, String moduleAPIName) throws Exception
	{
		MassDeleteCvidOperations massDeleteCvidOperations = new MassDeleteCvidOperations(moduleAPIName);

		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(GetMassDeleteStatusParam.JOB_ID, jobId);

		APIResponse<ResponseHandler> response = massDeleteCvidOperations.getMassDeleteStatus(paramInstance);

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
						System.out.println("MassDelete TotalCount: " + status1.getTotalCount());

						System.out.println("MassDelete ConvertedCount: " + status1.getDeletedCount());

						System.out.println("MassDelete FailedCount: " + status1.getFailedCount());

						System.out.println("MassDelete Status: " + status1.getStatus());
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
