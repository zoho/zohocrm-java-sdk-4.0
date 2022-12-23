//$Id$
package com.zoho.crm.sample.changeowner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zoho.crm.api.changeowner.APIException;
import com.zoho.crm.api.changeowner.ActionHandler;
import com.zoho.crm.api.changeowner.ActionResponse;
import com.zoho.crm.api.changeowner.ActionWrapper;
import com.zoho.crm.api.changeowner.BodyWrapper;
import com.zoho.crm.api.changeowner.ChangeOwnerOperations;
import com.zoho.crm.api.changeowner.MassWrapper;
import com.zoho.crm.api.changeowner.Owner;
import com.zoho.crm.api.changeowner.RelatedModule;
import com.zoho.crm.api.changeowner.SuccessResponse;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class ChangeOwner
{
	public static void updateRecordsOwner(String moduleAPIName) throws Exception
	{
		// Get instance of ChangeOwnerOperations Class
		ChangeOwnerOperations changeOwnerOperations = new ChangeOwnerOperations(moduleAPIName);

		// Get instance of BodyWrapper Class that will contain the request body
		MassWrapper bodyWrapper = new MassWrapper();

		// List of record id
		List<Long> Ids = new ArrayList<Long>();

		Ids.add(3477061146121l);

		Ids.add(3477061146112l);

		bodyWrapper.setIds(Ids);

		Owner owner = new Owner();

		owner.setId(34770615791024l);

		bodyWrapper.setOwner(owner);

		bodyWrapper.setNotify(true);

		List<RelatedModule> relatedModules = new ArrayList<RelatedModule>();

		// Get instance of Module Class
		RelatedModule relatedModule = new RelatedModule();

		// Set ID to the Module instance
		relatedModule.setId(3477061146865l);

		// Set name to the Module instance
		relatedModule.setAPIName("Tasks");

		// Add Module instance to the list
		relatedModules.add(relatedModule);

		relatedModule = new RelatedModule();

		// Set ID to the Module instance
		relatedModule.setId(3477061146865l);

		// Set name to the Module instance
		relatedModule.setAPIName("Tasks");

		// Add Module instance to the list
		relatedModules.add(relatedModule);

		// Set the list to relatedModules in BodyWrapper instance
		bodyWrapper.setRelatedModules(relatedModules);

		// Call updateByModule method that takes BodyWrapper instance as parameter
		APIResponse<ActionHandler> response = changeOwnerOperations.massUpdate(bodyWrapper);

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
					// Get the received RecordActionWrapper instance
					ActionWrapper actionWrapper = (ActionWrapper) actionHandler;

					// Get the list of obtained ActionResponse instances
					List<ActionResponse> actionResponses = actionWrapper.getData();

					for (ActionResponse actionResponse : actionResponses)
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

	public static void updateRecordOwner(String moduleAPIName, Long recordId) throws Exception
	{
		// ID of the Record to be updated
		// Long recordId = 5255085067923l;

		// Get instance of ChangeOwnerOperations Class
		ChangeOwnerOperations changeOwnerOperations = new ChangeOwnerOperations(moduleAPIName);

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper bodyWrapper = new BodyWrapper();

		Owner owner = new Owner();

		owner.setId(34770615791024l);

		bodyWrapper.setOwner(owner);

		bodyWrapper.setNotify(true);

		List<RelatedModule> relatedModules = new ArrayList<RelatedModule>();

		// Get instance of Module Class
		RelatedModule relatedModule = new RelatedModule();

		// Set ID to the Module instance
		relatedModule.setId(3477061146865l);

		// Set name to the Module instance
		relatedModule.setAPIName("Tasks");

		// Add Module instance to the list
		relatedModules.add(relatedModule);

		relatedModule = new RelatedModule();

		// Set ID to the Module instance
		relatedModule.setId(3477061146865l);

		// Set name to the Module instance
		relatedModule.setAPIName("Tasks");

		// Add Module instance to the list
		relatedModules.add(relatedModule);

		// Set the list to relatedModules in BodyWrapper instance
		bodyWrapper.setRelatedModules(relatedModules);

		// Call updateRecordOwner method that takes recordId and BodyWrapper instance as parameters
		APIResponse<ActionHandler> response = changeOwnerOperations.singleUpdate(recordId, bodyWrapper);

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
}
