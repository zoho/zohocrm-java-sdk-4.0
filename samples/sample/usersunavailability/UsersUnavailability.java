//$Id$
package com.zoho.crm.sample.usersunavailability;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.usersunavailability.APIException;
import com.zoho.crm.api.usersunavailability.ActionHandler;
import com.zoho.crm.api.usersunavailability.ActionResponse;
import com.zoho.crm.api.usersunavailability.ActionWrapper;
import com.zoho.crm.api.usersunavailability.BodyWrapper;
import com.zoho.crm.api.usersunavailability.Info;
import com.zoho.crm.api.usersunavailability.ResponseHandler;
import com.zoho.crm.api.usersunavailability.SuccessResponse;
import com.zoho.crm.api.usersunavailability.User;
import com.zoho.crm.api.usersunavailability.UsersUnavailabilityOperations;
import com.zoho.crm.api.usersunavailability.UsersUnavailabilityOperations.GetUsersUnavailabilitesParam;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class UsersUnavailability
{
	public static void getUsersUnavailabilityHours() throws Exception
	{
		// Get instance of UsersUnavailabilityOperations Class
		UsersUnavailabilityOperations usersUnavailabilityOperations = new UsersUnavailabilityOperations();

		// Get instance of ParameterMap Class
		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(GetUsersUnavailabilitesParam.GROUP_IDS, "34770619,3477061912");

		paramInstance.add(GetUsersUnavailabilitesParam.INCLUDE_INNER_DETAILS, "56xxx8");

		paramInstance.add(GetUsersUnavailabilitesParam.ROLE_IDS, "343370619,3403706191");

		paramInstance.add(GetUsersUnavailabilitesParam.TERRITORY_IDS, "343370619,3403706191");

		JSONObject filters = new JSONObject();

		filters.put("group_operator", "or");

		JSONArray group = new JSONArray();

		JSONObject criteria1 = new JSONObject();

		criteria1.put("comparator", "between");

		JSONObject criteria1Field = new JSONObject();

		criteria1Field.put("api_name", "from");

		criteria1.put("field", criteria1Field);

		JSONArray criteria1Value = new JSONArray();

		criteria1Value.put("2021-02-18T19:00:00+05:30");

		criteria1Value.put("2021-02-19T19:00:00+05:30");

		criteria1.put("value", criteria1Value);

		group.put(criteria1);

		JSONObject criteria2 = new JSONObject();

		criteria2.put("comparator", "between");

		JSONObject criteria2Field = new JSONObject();

		criteria2Field.put("api_name", "to");

		criteria2.put("field", criteria2Field);

		JSONArray criteria2Value = new JSONArray();

		criteria2Value.put("2021-02-18T20:00:00+05:30");

		criteria2Value.put("2021-02-19T20:00:00+05:30");

		criteria2.put("value", criteria2Value);

		group.put(criteria2);

		filters.put("group", group);

		System.out.println(filters.toString());

		paramInstance.add(GetUsersUnavailabilitesParam.FILTERS, filters.toString());

		// Call getUsersUnavailabilityHours method that takes ParameterMap instance as parameters
		APIResponse<ResponseHandler> response = usersUnavailabilityOperations.getUsersUnavailabilites(paramInstance);

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
					// Get the received ResponseWrapper instance
					BodyWrapper responseWrapper = (BodyWrapper) responseHandler;

					// Get the list of obtained UsersUnavailability instances
					List<com.zoho.crm.api.usersunavailability.UsersUnavailability> users = responseWrapper.getUsersUnavailability();

					for (com.zoho.crm.api.usersunavailability.UsersUnavailability usersUnavailability : users)
					{
						System.out.println("UsersUnavailability Comments: " + usersUnavailability.getComments());

						System.out.println("UsersUnavailability From: " + usersUnavailability.getFrom());

						System.out.println("UsersUnavailability Id: " + usersUnavailability.getId());

						System.out.println("UsersUnavailability to: " + usersUnavailability.getTo());

						User user = usersUnavailability.getUser();

						if (user != null)
						{
							System.out.println("UsersUnavailability User-Name: " + user.getName());

							System.out.println("UsersUnavailability User-Id: " + user.getId());

							System.out.println("UsersUnavailability User-ZuId: " + user.getZuid());
						}
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

	public static void getUserUnavailabilityHours(Long id) throws Exception
	{
		// Get instance of UsersUnavailabilityOperations Class
		UsersUnavailabilityOperations usersUnavailabilityOperations = new UsersUnavailabilityOperations();

		// Get instance of ParameterMap Class
		ParameterMap paramInstance = new ParameterMap();

		// Call getUserUnavailabilityHours method that takes id and ParameterMap instance as parameters
		APIResponse<ResponseHandler> response = usersUnavailabilityOperations.getUsersUnavailability(id, paramInstance);

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
					// Get the received ResponseWrapper instance
					BodyWrapper responseWrapper = (BodyWrapper) responseHandler;

					// Get the list of obtained UsersUnavailability instances
					List<com.zoho.crm.api.usersunavailability.UsersUnavailability> users = responseWrapper.getUsersUnavailability();

					for (com.zoho.crm.api.usersunavailability.UsersUnavailability usersUnavailability : users)
					{
						System.out.println("UsersUnavailability Comments: " + usersUnavailability.getComments());

						System.out.println("UsersUnavailability From: " + usersUnavailability.getFrom());

						System.out.println("UsersUnavailability Id: " + usersUnavailability.getId());

						System.out.println("UsersUnavailability to: " + usersUnavailability.getTo());

						User user = usersUnavailability.getUser();

						if (user != null)
						{
							System.out.println("UsersUnavailability User-Name: " + user.getName());

							System.out.println("UsersUnavailability User-Id: " + user.getId());

							System.out.println("UsersUnavailability User-ZuId: " + user.getZuid());
						}
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

	public static void usersUnavailabilityHours() throws Exception
	{
		// Get instance of UsersUnavailabilityOperations Class
		UsersUnavailabilityOperations usersOperations = new UsersUnavailabilityOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper request = new BodyWrapper();

		// List of User instances
		List<com.zoho.crm.api.usersunavailability.UsersUnavailability> userList = new ArrayList<com.zoho.crm.api.usersunavailability.UsersUnavailability>();

		// Get instance of User Class
		com.zoho.crm.api.usersunavailability.UsersUnavailability user1 = new com.zoho.crm.api.usersunavailability.UsersUnavailability();

		user1.setComments("Unavailable");

		OffsetDateTime from = OffsetDateTime.of(2022, 04, 02, 11, 00, 01, 00, ZoneOffset.of("+05:30"));

		user1.setFrom(from);

		OffsetDateTime to = OffsetDateTime.of(2022, 05, 02, 11, 00, 01, 00, ZoneOffset.of("+05:30"));

		user1.setTo(to);

		User user = new User();

		user.setId(347706113767065l);

		user1.setUser(user);

		userList.add(user1);

		request.setUsersUnavailability(userList);

		// Call usersUnavailabilityHours method that takes BodyWrapper class instance as parameter
		APIResponse<ActionHandler> response = usersOperations.createUsersUnavailability(request);

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
					ActionWrapper responseWrapper = (ActionWrapper) actionHandler;

					// Get the list of obtained ActionResponse instances
					List<ActionResponse> actionResponses = responseWrapper.getUsersUnavailability();

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

	public static void updateUsersUnavailabilityHours() throws Exception
	{
		// Get instance of UsersUnavailabilityOperations Class
		UsersUnavailabilityOperations usersOperations = new UsersUnavailabilityOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper request = new BodyWrapper();

		// List of User instances
		List<com.zoho.crm.api.usersunavailability.UsersUnavailability> userList = new ArrayList<com.zoho.crm.api.usersunavailability.UsersUnavailability>();

		// Get instance of User Class
		com.zoho.crm.api.usersunavailability.UsersUnavailability user1 = new com.zoho.crm.api.usersunavailability.UsersUnavailability();

		user1.setComments("Unavailable");

		user1.setId(347706114792029l);

		OffsetDateTime from = OffsetDateTime.of(2022, 04, 02, 11, 00, 01, 00, ZoneOffset.of("+05:30"));

		user1.setFrom(from);

		OffsetDateTime to = OffsetDateTime.of(2022, 05, 05, 11, 00, 01, 00, ZoneOffset.of("+05:30"));

		user1.setTo(to);

		User user = new User();

		user.setId(347706113767065l);

		user1.setUser(user);

		userList.add(user1);

		request.setUsersUnavailability(userList);

		// Call updateUsersUnavailabilityHours method that takes BodyWrapper class instance as parameter
		APIResponse<ActionHandler> response = usersOperations.updateUsersUnavailabilites(request);

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
					ActionWrapper responseWrapper = (ActionWrapper) actionHandler;

					// Get the list of obtained ActionResponse instances
					List<ActionResponse> actionResponses = responseWrapper.getUsersUnavailability();

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

	public static void updateUsersUnavailability(Long id) throws Exception
	{
		// Get instance of UsersUnavailabilityOperations Class
		UsersUnavailabilityOperations usersOperations = new UsersUnavailabilityOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper request = new BodyWrapper();

		// List of User instances
		List<com.zoho.crm.api.usersunavailability.UsersUnavailability> userList = new ArrayList<com.zoho.crm.api.usersunavailability.UsersUnavailability>();

		// Get instance of User Class
		com.zoho.crm.api.usersunavailability.UsersUnavailability user1 = new com.zoho.crm.api.usersunavailability.UsersUnavailability();

		user1.setComments("Unavailable");

		OffsetDateTime from = OffsetDateTime.of(2022, 04, 02, 11, 00, 01, 00, ZoneOffset.of("+05:30"));

		user1.setFrom(from);

		OffsetDateTime to = OffsetDateTime.of(2022, 05, 05, 11, 00, 01, 00, ZoneOffset.of("+05:30"));

		user1.setTo(to);

		userList.add(user1);

		request.setUsersUnavailability(userList);

		// Call updateUsersUnavailability method that takes id and BodyWrapper class instance as parameter
		APIResponse<ActionHandler> response = usersOperations.updateUsersUnavailability(id, request);

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
					ActionWrapper responseWrapper = (ActionWrapper) actionHandler;

					// Get the list of obtained ActionResponse instances
					List<ActionResponse> actionResponses = responseWrapper.getUsersUnavailability();

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

	public static void deleteUsersUnavailabilityHour(Long id) throws Exception
	{
		// Get instance of UsersUnavailabilityOperations Class
		UsersUnavailabilityOperations usersOperations = new UsersUnavailabilityOperations();

		// Call deleteUsersUnavailabilityHour method that takes id as parameter
		APIResponse<ActionHandler> response = usersOperations.deleteUsersUnavailabilityHour(id);

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
					ActionWrapper responseWrapper = (ActionWrapper) actionHandler;

					// Get the list of obtained ActionResponse instances
					List<ActionResponse> actionResponses = responseWrapper.getUsersUnavailability();

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

}
