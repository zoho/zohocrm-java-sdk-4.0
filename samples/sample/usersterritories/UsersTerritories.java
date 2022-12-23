package com.zoho.crm.sample.usersterritories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.usersterritories.APIException;
import com.zoho.crm.api.usersterritories.ActionHandler;
import com.zoho.crm.api.usersterritories.ActionResponse;
import com.zoho.crm.api.usersterritories.ActionWrapper;
import com.zoho.crm.api.usersterritories.BodyWrapper;
import com.zoho.crm.api.usersterritories.BulkValidation;
import com.zoho.crm.api.usersterritories.Info;
import com.zoho.crm.api.usersterritories.Manager;
import com.zoho.crm.api.usersterritories.ResponseHandler;
import com.zoho.crm.api.usersterritories.SuccessResponse;
import com.zoho.crm.api.usersterritories.Territory;
import com.zoho.crm.api.usersterritories.TransferActionHandler;
import com.zoho.crm.api.usersterritories.TransferActionResponse;
import com.zoho.crm.api.usersterritories.TransferActionWrapper;
import com.zoho.crm.api.usersterritories.TransferAndDelink;
import com.zoho.crm.api.usersterritories.TransferToUser;
import com.zoho.crm.api.usersterritories.TransferWrapper;
import com.zoho.crm.api.usersterritories.UsersTerritoriesOperations;
import com.zoho.crm.api.usersterritories.Validation;
import com.zoho.crm.api.usersterritories.ValidationGroup;
import com.zoho.crm.api.usersterritories.ValidationHandler;
import com.zoho.crm.api.usersterritories.ValidationWrapper;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class UsersTerritories
{
	public static void getTerritoriesOfUser(Long userId) throws Exception
	{
		// Get instance of UsersTerritoriesOperations Class
		UsersTerritoriesOperations usersTerritoriesOperations = new UsersTerritoriesOperations(userId);

		// Get instance of ParameterMap Class
		ParameterMap paramInstance = new ParameterMap();

//		paramInstance.add(GetTerritoriesOfUserParam.PAGE, 1);
//
//		paramInstance.add(GetTerritoriesOfUserParam.PER_PAGE, 1);

		APIResponse<ResponseHandler> response = usersTerritoriesOperations.getTerritoriesOfUser(paramInstance);

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

				if (responseHandler instanceof BodyWrapper)
				{
					// Get the received BodyWrapper instance
					BodyWrapper responseWrapper = (BodyWrapper) responseHandler;

					// Get the list of obtained Territory instances
					List<Territory> usersTerritory = responseWrapper.getTerritories();

					for (Territory territory : usersTerritory)
					{
						// Get the ID of each User Territory
						System.out.println("User Territory ID: " + territory.getId());

						// Get the manager User instance of each User Territory
						Manager manager = territory.getManager();

						// Check if manager is not null
						if (manager != null)
						{
							// Get the Name of the Manager
							System.out.println("User Territory Manager Name: " + manager.getName());

							// Get the ID of the Manager
							System.out.println("User Territory Manager ID: " + manager.getId());
						}

						// Get the reportingTo Territory instance of each User Territory
						Manager reportingTo = territory.getReportingTo();

						// Check if reportingTo is not null
						if (reportingTo != null)
						{
							// Get the Name of the ReportingTo
							System.out.println("User Territory ReportingTo Name: " + reportingTo.getName());

							// Get the ID of the ReportingTo
							System.out.println("User Territory ReportingTo ID: " + reportingTo.getId());
						}

						System.out.println("User Territory Name: " + territory.getName());
					}

					// Get the Object obtained Info instance
					Info info = responseWrapper.getInfo();

					// Check if info is not null
					if (info != null)
					{
						if (info.getPerPage() != null)
						{
							// Get the PerPage of the Info
							System.out.println("User Info PerPage: " + info.getPerPage().toString());
						}

						if (info.getCount() != null)
						{
							// Get the Count of the Info
							System.out.println("User Info Count: " + info.getCount().toString());
						}

						if (info.getPage() != null)
						{
							// Get the Page of the Info
							System.out.println("User Info Page: " + info.getPage().toString());
						}

						if (info.getMoreRecords() != null)
						{
							// Get the MoreRecords of the Info
							System.out.println("User Info MoreRecords: " + info.getMoreRecords().toString());
						}
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
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	public static void associateTerritoriesToUser(Long userId) throws Exception
	{
		// Get instance of UsersTerritoriesOperations Class
		UsersTerritoriesOperations usersTerritoriesOperations = new UsersTerritoriesOperations(userId);

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper request = new BodyWrapper();

		// List of User instances
		List<Territory> userTerritoryList = new ArrayList<Territory>();

		// Get instance of Territory Class
		Territory territory = new Territory();

		territory.setId(34770613051397l);

		userTerritoryList.add(territory);

		request.setTerritories(userTerritoryList);

		// Call associateTerritoriesToUser method that takes userId and TerritoryBodyWrapper instance as parameter
		APIResponse<ActionHandler> response = usersTerritoriesOperations.associateTerritoriesToUser(request);

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

					// Get the list of obtained TerritoryActionResponse instances
					List<ActionResponse> actionResponses = actionWrapper.getTerritories();

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
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	public static void getSpecificTerritoryOfUser(Long userId, Long territoryId) throws Exception
	{
		// Get instance of UsersTerritoriesOperations Class
		UsersTerritoriesOperations usersTerritoriesOperations = new UsersTerritoriesOperations(userId);

		// Call getTerritoriesOfUser method that takes territoryId as parameters
		APIResponse<ResponseHandler> response = usersTerritoriesOperations.getSpecificTerritoryOfUser(territoryId);

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

				if (responseHandler instanceof BodyWrapper)
				{
					// Get the received BodyWrapper instance
					BodyWrapper responseWrapper = (BodyWrapper) responseHandler;

					// Get the list of obtained Territory instances
					List<Territory> usersTerritory = responseWrapper.getTerritories();

					for (Territory territory : usersTerritory)
					{
						// Get the ID of each User Territory
						System.out.println("User Territory ID: " + territory.getId());

						// Get the manager User instance of each User Territory
						Manager manager = territory.getManager();

						// Check if manager is not null
						if (manager != null)
						{
							// Get the Name of the Manager
							System.out.println("User Territory Manager Name: " + manager.getName());

							// Get the ID of the Manager
							System.out.println("User Territory Manager ID: " + manager.getId());
						}

						// Get the reportingTo Territory instance of each User Territory
						Manager reportingTo = territory.getReportingTo();

						// Check if reportingTo is not null
						if (reportingTo != null)
						{
							// Get the Name of the ReportingTo
							System.out.println("User Territory ReportingTo Name: " + reportingTo.getName());

							// Get the ID of the ReportingTo
							System.out.println("User Territory ReportingTo ID: " + reportingTo.getId());
						}

						System.out.println("User Territory Name: " + territory.getName());
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
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	public static void validateBeforeTransferForAllTerritories(Long userId) throws Exception
	{
		// Get instance of UsersTerritoriesOperations Class
		UsersTerritoriesOperations usersTerritoriesOperations = new UsersTerritoriesOperations(userId);

		// Call getValidateTerritoriesBeforeTransferring method that takes userId as parameters
		APIResponse<ValidationHandler> response = usersTerritoriesOperations.validateBeforeTransferForAllTerritories();

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
				ValidationHandler responseHandler = response.getObject();

				if (responseHandler instanceof ValidationWrapper)
				{
					// Get the received ValidationWrapper instance
					ValidationWrapper responseWrapper = (ValidationWrapper) responseHandler;

					// Get the list of obtained Territory instances
					List<ValidationGroup> usersTerritory = responseWrapper.getValidateBeforeTransfer();

					for (ValidationGroup validationGroup : usersTerritory)
					{
						if(validationGroup instanceof BulkValidation)
						{
							BulkValidation validation = (BulkValidation)validationGroup;

							System.out.println("User Territory Validation Alert : " + validation.getAlert());

							System.out.println("User Territory Validation Assignment : " + validation.getAssignment());

							System.out.println("User Territory Validation Criteria : " + validation.getCriteria());
							
							System.out.println("User Territory Validation Name : " + validation.getName());
							
							System.out.println("User Territory Validation Id : " + validation.getId());
						}
						else if(validationGroup instanceof Validation)
						{
							Validation validation = (Validation)validationGroup;

							System.out.println("User Territory Validation Records : " + validation.getRecords());
							
							System.out.println("User Territory Validation Name : " + validation.getName());
							
							System.out.println("User Territory Validation Id : " + validation.getId());
						}
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
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	public static void validateBeforeTransfer(Long userId, Long territoryId) throws Exception
	{
		// Get instance of UsersTerritoriesOperations Class
		UsersTerritoriesOperations usersTerritoriesOperations = new UsersTerritoriesOperations(userId);

		// Call getValidateTerritoriesBeforeTransferring method that takes territoryId and userId as parameters
		APIResponse<ValidationHandler> response = usersTerritoriesOperations.validateBeforeTransfer(territoryId);

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
				ValidationHandler responseHandler = response.getObject();

				if (responseHandler instanceof ValidationWrapper)
				{
					// Get the received ValidationWrapper instance
					ValidationWrapper responseWrapper = (ValidationWrapper) responseHandler;

					// Get the list of obtained Territory instances
					List<ValidationGroup> usersTerritory = responseWrapper.getValidateBeforeTransfer();

					for (ValidationGroup validationGroup : usersTerritory)
					{
						if(validationGroup instanceof BulkValidation)
						{
							BulkValidation validation = (BulkValidation)validationGroup;

							System.out.println("User Territory Validation Alert : " + validation.getAlert());

							System.out.println("User Territory Validation Assignment : " + validation.getAssignment());

							System.out.println("User Territory Validation Criteria : " + validation.getCriteria());
							
							System.out.println("User Territory Validation Name : " + validation.getName());
							
							System.out.println("User Territory Validation Id : " + validation.getId());
						}
						else if(validationGroup instanceof Validation)
						{
							Validation validation = (Validation)validationGroup;

							System.out.println("User Territory Validation Records : " + validation.getRecords());
							
							System.out.println("User Territory Validation Name : " + validation.getName());
							
							System.out.println("User Territory Validation Id : " + validation.getId());
						}
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
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	public static void delinkAndTransferFromAllTerritories(Long userId) throws Exception
	{
		// Get instance of UsersTerritoriesOperations Class
		UsersTerritoriesOperations usersTerritoriesOperations = new UsersTerritoriesOperations(userId);

		// Get instance of TransferWrapper Class that will contain the request body
		TransferWrapper request = new TransferWrapper();

		List<TransferAndDelink> userTerritoryList = new ArrayList<TransferAndDelink>();

		// Get instance of TransferAndDelink Class
		TransferAndDelink territory = new TransferAndDelink();

		territory.setId(34770613051397l);

		TransferToUser transferToUser = new TransferToUser();

		transferToUser.setId(347706113767065l);

		territory.setTransferToUser(transferToUser);

		userTerritoryList.add(territory);

		request.setTransferAndDelink(userTerritoryList);

		// Call delinkAndTransferFromAllTerritories method that takes userId and TransferBodyWrapper instance as parameter
		APIResponse<TransferActionHandler> response = usersTerritoriesOperations.delinkAndTransferFromAllTerritories(request);

		if (response != null)
		{
			// Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());

			// Check if expected response is received
			if (response.isExpected())
			{
				// Get object from response
				TransferActionHandler actionHandler = response.getObject();

				if (actionHandler instanceof TransferActionWrapper)
				{
					// Get the received TransferActionWrapper instance
					TransferActionWrapper responseWrapper = (TransferActionWrapper) actionHandler;

					// Get the list of obtained TransferActionResponse instances
					List<TransferActionResponse> actionResponses = responseWrapper.getTransferAndDelink();

					for (TransferActionResponse actionResponse : actionResponses)
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
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	public static void delinkAndTransferFromSpecificTerritory(Long userId, Long territoryId) throws Exception
	{
		// Get instance of UsersTerritoriesOperations Class
		UsersTerritoriesOperations usersTerritoriesOperations = new UsersTerritoriesOperations(userId);

		// Get instance of TransferWrapper Class that will contain the request body
		TransferWrapper request = new TransferWrapper();

		List<TransferAndDelink> userTerritoryList = new ArrayList<TransferAndDelink>();

		// Get instance of TransferAndDelink Class
		TransferAndDelink territory = new TransferAndDelink();

		territory.setId(34770613051397l);

		TransferToUser transferToUser = new TransferToUser();

		transferToUser.setId(347706113767065l);

		territory.setTransferToUser(transferToUser);

		userTerritoryList.add(territory);

		request.setTransferAndDelink(userTerritoryList);

		// Call delinkAndTransferFromSpecificTerritory method that takes territoryId, userId and TransferBodyWrapper instance as parameter
		APIResponse<TransferActionHandler> response = usersTerritoriesOperations.delinkAndTransferFromSpecificTerritory(territoryId, request);

		if (response != null)
		{
			// Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());

			// Check if expected response is received
			if (response.isExpected())
			{
				// Get object from response
				TransferActionHandler actionHandler = response.getObject();

				if (actionHandler instanceof TransferActionWrapper)
				{
					// Get the received TransferActionWrapper instance
					TransferActionWrapper responseWrapper = (TransferActionWrapper) actionHandler;

					// Get the list of obtained TransferActionResponse instances
					List<TransferActionResponse> actionResponses = responseWrapper.getTransferAndDelink();

					for (TransferActionResponse actionResponse : actionResponses)
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
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}
}
