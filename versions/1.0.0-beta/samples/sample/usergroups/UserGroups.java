package com.zoho.crm.sample.usergroups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.usergroups.APIException;
import com.zoho.crm.api.usergroups.ActionHandler;
import com.zoho.crm.api.usergroups.ActionResponse;
import com.zoho.crm.api.usergroups.ActionWrapper;
import com.zoho.crm.api.usergroups.BodyWrapper;
import com.zoho.crm.api.usergroups.Info;
import com.zoho.crm.api.usergroups.JobHandler;
import com.zoho.crm.api.usergroups.Jobs;
import com.zoho.crm.api.usergroups.JobsWrapper;
import com.zoho.crm.api.usergroups.ResponseHandler;
import com.zoho.crm.api.usergroups.Source;
import com.zoho.crm.api.usergroups.Sources;
import com.zoho.crm.api.usergroups.SuccessResponse;
import com.zoho.crm.api.usergroups.UserGroupsOperations;
import com.zoho.crm.api.usergroups.UserGroupsOperations.GetStatusParam;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Choice;
import com.zoho.crm.api.util.Model;

public class UserGroups
{
	public static void getGroups() throws Exception
	{
		// Get instance of UserGroups Class
		UserGroupsOperations userGroupsOperations = new UserGroupsOperations();

		APIResponse<ResponseHandler> response = userGroupsOperations.getGroups();

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

					// Get the list of obtained User instances
					List<com.zoho.crm.api.usergroups.Groups> users = responseWrapper.getUserGroups();

					for (com.zoho.crm.api.usergroups.Groups user : users)
					{
						com.zoho.crm.api.users.MinifiedUser createdBy = user.getCreatedBy();

						// Check if createdBy is not null
						if (createdBy != null)
						{
							// Get the Name of the createdBy User
							System.out.println("UserGroups Created By User-Name: " + createdBy.getName());

							// Get the ID of the createdBy User
							System.out.println("UserGroups Created By User-ID: " + createdBy.getId());
						}
						
						com.zoho.crm.api.users.MinifiedUser modifiedBy = user.getModifiedBy();

						// Check if modifiedBy is not null
						if (modifiedBy != null)
						{
							System.out.println("UserGroups Modified By User-Name: " + modifiedBy.getName());

							System.out.println("UserGroups Modified By User-ID: " + modifiedBy.getId());
						}
						
						System.out.println("User ModifiedTime: " + user.getModifiedTime());
						
						System.out.println("User CreatedTime: " + user.getCreatedTime());
						
						System.out.println("UserGroups Description: " + user.getDescription());
						
						System.out.println("UserGroups Id: " + user.getId());
						
						System.out.println("UserGroups Name: " + user.getName());
						
						List<Sources> sources = user.getSources();
						
						if(sources != null)
						{
							sources.forEach(source ->
							{
								System.out.println("UserGroups Sources Type: " + source.getType().getValue());
								
								Source source1 = source.getSource();
								
								if(source1 != null)
								{
									System.out.println("UserGroups Sources Id: " + source1.getId());
								}
								
								System.out.println("UserGroups Sources Subordinates: " + source.getSubordinates());
								
								System.out.println("UserGroups Sources SubTerritories: " + source.getSubTerritories());
							});
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

	public static void createGroup() throws Exception
	{
		UserGroupsOperations userGroupsOperations = new UserGroupsOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper request = new BodyWrapper();

		// List of User instances
		List<com.zoho.crm.api.usergroups.Groups> userList = new ArrayList<com.zoho.crm.api.usergroups.Groups>();

		// Get instance of User Class
		com.zoho.crm.api.usergroups.Groups user1 = new com.zoho.crm.api.usergroups.Groups();

		user1.setName("test group");
		
		user1.setDescription("my group");
		
		List<com.zoho.crm.api.usergroups.Sources> sources = new ArrayList<>();
		
		com.zoho.crm.api.usergroups.Sources source = new com.zoho.crm.api.usergroups.Sources();
		
		source.setType(new Choice<String>("users"));
		
		com.zoho.crm.api.usergroups.Source  source1 = new com.zoho.crm.api.usergroups.Source();

		source1.setId(347706113767065l);
		
		source.setSource(source1);
		
		sources.add(source);
		
		user1.setSources(sources);

		userList.add(user1);

		request.setUserGroups(userList);

		APIResponse<ActionHandler> response = userGroupsOperations.createGroup(request);

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
					List<ActionResponse> actionResponses = responseWrapper.getUserGroups();

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

	public static void getGroup(Long group) throws Exception
	{
		// Get instance of UserGroups Class
		UserGroupsOperations userGroupsOperations = new UserGroupsOperations();

		APIResponse<ResponseHandler> response = userGroupsOperations.getGroup(group);

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

					// Get the list of obtained User instances
					List<com.zoho.crm.api.usergroups.Groups> users = responseWrapper.getUserGroups();

					for (com.zoho.crm.api.usergroups.Groups user : users)
					{
						com.zoho.crm.api.users.MinifiedUser createdBy = user.getCreatedBy();

						// Check if createdBy is not null
						if (createdBy != null)
						{
							// Get the Name of the createdBy User
							System.out.println("UserGroups Created By User-Name: " + createdBy.getName());

							// Get the ID of the createdBy User
							System.out.println("UserGroups Created By User-ID: " + createdBy.getId());
						}
						
						com.zoho.crm.api.users.MinifiedUser modifiedBy = user.getModifiedBy();

						// Check if modifiedBy is not null
						if (modifiedBy != null)
						{
							System.out.println("UserGroups Modified By User-Name: " + modifiedBy.getName());

							System.out.println("UserGroups Modified By User-ID: " + modifiedBy.getId());
						}
						
						System.out.println("User ModifiedTime: " + user.getModifiedTime());
						
						System.out.println("User CreatedTime: " + user.getCreatedTime());
						
						System.out.println("UserGroups Description: " + user.getDescription());
						
						System.out.println("UserGroups Id: " + user.getId());
						
						System.out.println("UserGroups Name: " + user.getName());
						
						List<Sources> sources = user.getSources();
						
						if(sources != null)
						{
							sources.forEach(source ->
							{
								System.out.println("UserGroups Sources Type: " + source.getType().getValue());
								
								Source source1 = source.getSource();
								
								if(source1 != null)
								{
									System.out.println("UserGroups Sources Id: " + source1.getId());
								}
								
								System.out.println("UserGroups Sources Subordinates: " + source.getSubordinates());
								
								System.out.println("UserGroups Sources SubTerritories: " + source.getSubTerritories());
							});
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

	public static void updateGroup(Long groupId) throws Exception
	{
		UserGroupsOperations userGroupsOperations = new UserGroupsOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper request = new BodyWrapper();

		// List of User instances
		List<com.zoho.crm.api.usergroups.Groups> userList = new ArrayList<com.zoho.crm.api.usergroups.Groups>();

		// Get instance of User Class
		com.zoho.crm.api.usergroups.Groups user1 = new com.zoho.crm.api.usergroups.Groups();

		user1.setName("test group");
		
		user1.setDescription("my group");
		
		List<com.zoho.crm.api.usergroups.Sources> sources = new ArrayList<>();
		
		com.zoho.crm.api.usergroups.Sources source = new com.zoho.crm.api.usergroups.Sources();
		
		source.setType(new Choice<String>("users"));
		
		com.zoho.crm.api.usergroups.Source  source1 = new com.zoho.crm.api.usergroups.Source();
		
		source1.setId(347706113767065l);
		
		source.setSource(source1);
		
		sources.add(source);
		
		user1.setSources(sources);

		userList.add(user1);

		request.setUserGroups(userList);

		APIResponse<ActionHandler> response = userGroupsOperations.updateGroup(groupId, request);

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
					List<ActionResponse> actionResponses = responseWrapper.getUserGroups();

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

	public static void deleteGroup(Long groupId) throws Exception
	{
		UserGroupsOperations userGroupsOperations = new UserGroupsOperations();

		APIResponse<ActionHandler> response = userGroupsOperations.deleteGroup(groupId);

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
					List<ActionResponse> actionResponses = responseWrapper.getUserGroups();

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

	public static void getStatus(Long jobId) throws Exception
	{
		UserGroupsOperations userGroupsOperations = new UserGroupsOperations();
		
		ParameterMap paramInstance = new ParameterMap();
		
		paramInstance.add(GetStatusParam.JOB_ID, jobId);
		
		APIResponse<JobHandler> response = userGroupsOperations.getStatus(paramInstance);

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
				JobHandler jobHandler = response.getObject();

				if (jobHandler instanceof JobsWrapper)
				{
					// Get the received ResponseWrapper instance
					JobsWrapper responseWrapper = (JobsWrapper) jobHandler;

					// Get the obtained User instance
					List<com.zoho.crm.api.usergroups.JobResponse> users = responseWrapper.getDeletionJobs();

					for (com.zoho.crm.api.usergroups.JobResponse user : users)
					{
						if(user instanceof Jobs)
						{
							Jobs jobs = (Jobs)user;
							
							System.out.println("Status: " + jobs.getStatus());
						}
					}
				}
				// Check if the request returned an exception
				else if (jobHandler instanceof APIException)
				{
					// Get the received APIException instance
					APIException exception = (APIException) jobHandler;

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
