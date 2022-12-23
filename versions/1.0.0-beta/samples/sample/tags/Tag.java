package com.zoho.crm.sample.tags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.tags.APIException;
import com.zoho.crm.api.tags.ActionHandler;
import com.zoho.crm.api.tags.ActionResponse;
import com.zoho.crm.api.tags.ActionWrapper;
import com.zoho.crm.api.tags.BodyWrapper;
import com.zoho.crm.api.tags.ConflictWrapper;
import com.zoho.crm.api.tags.ExistingTagRequestWrapper;
import com.zoho.crm.api.tags.Info;
import com.zoho.crm.api.tags.MergeWrapper;
import com.zoho.crm.api.tags.NewTagRequestWrapper;
import com.zoho.crm.api.tags.RecordActionHandler;
import com.zoho.crm.api.tags.RecordActionResponse;
import com.zoho.crm.api.tags.RecordActionWrapper;
import com.zoho.crm.api.tags.RecordSuccessResponse;
import com.zoho.crm.api.tags.ResponseHandler;
import com.zoho.crm.api.tags.ResponseWrapper;
import com.zoho.crm.api.tags.SuccessResponseTag;
import com.zoho.crm.api.tags.TagsDeleted;
import com.zoho.crm.api.tags.TagsOperations;
import com.zoho.crm.api.tags.TagsOperations.CreateTagsParam;
import com.zoho.crm.api.tags.TagsOperations.GetRecordCountForTagParam;
import com.zoho.crm.api.tags.TagsOperations.GetTagsParam;
import com.zoho.crm.api.tags.TagsOperations.RemoveTagsFromMultipleRecordsParam;
import com.zoho.crm.api.tags.TagsOperations.UpdateTagParam;
import com.zoho.crm.api.tags.TagsOperations.UpdateTagsParam;
import com.zoho.crm.api.tags.TagsOperations.AddTagsParam;
import com.zoho.crm.api.tags.TagsOperations.AddTagsToMultipleRecordsParam;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class Tag
{
	/**
	 * <h3>Get Tags</h3> This method is used to get all the tags in a module.
	 * 
	 * @param moduleAPIName - The API Name of the module to get tags.
	 * @throws Exception
	 */
	public static void getTags(String moduleAPIName) throws Exception
	{
		// example
		// String moduleAPIName = "Leads";

		// Get instance of TagsOperations Class
		TagsOperations tagsOperations = new TagsOperations();

		// Get instance of ParameterMap Class
		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(GetTagsParam.MODULE, moduleAPIName);

		// paramInstance.add(GetTagsParam.MY_TAGS, ""); //Displays the names of the tags created by the current user.

		// Call getTags method that takes paramInstance as parameter
		APIResponse<ResponseHandler> response = tagsOperations.getTags(paramInstance);

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

					// Get the obtained ShareRecord instance
					List<com.zoho.crm.api.tags.Tag> tags = responseWrapper.getTags();

					for (com.zoho.crm.api.tags.Tag tag : tags)
					{
						// Get the CreatedTime of each Tag
						System.out.println("Tag CreatedTime: " + tag.getCreatedTime());

						// Get the ModifiedTime of each Tag
						System.out.println("Tag ModifiedTime: " + tag.getModifiedTime());

						// Get the ColorCode of each Tag
						System.out.println("Tag ColorCode: " + tag.getColorCode());

						// Get the Name of each Tag
						System.out.println("Tag Name: " + tag.getName());

						// Get the modifiedBy User instance of each Tag
						com.zoho.crm.api.users.MinifiedUser modifiedBy = tag.getModifiedBy();

						// Check if modifiedBy is not null
						if (modifiedBy != null)
						{
							// Get the ID of the modifiedBy User
							System.out.println("Tag Modified By User-ID: " + modifiedBy.getId());

							// Get the name of the modifiedBy User
							System.out.println("Tag Modified By User-Name: " + modifiedBy.getName());
						}

						// Get the ID of each Tag
						System.out.println("Tag ID: " + tag.getId());

						// Get the createdBy User instance of each Tag
						com.zoho.crm.api.users.MinifiedUser createdBy = tag.getCreatedBy();

						// Check if createdBy is not null
						if (createdBy != null)
						{
							// Get the ID of the createdBy User
							System.out.println("Tag Created By User-ID: " + createdBy.getId());

							// Get the name of the createdBy User
							System.out.println("Tag Created By User-Name: " + createdBy.getName());
						}
					}

					// Get the Object obtained Info instance
					Info info = responseWrapper.getInfo();

					// Check if info is not null
					if (info != null)
					{
						if (info.getCount() != null)
						{
							// Get the Count of the Info
							System.out.println("Tag Info Count: " + info.getCount().toString());
						}

						if (info.getAllowedCount() != null)
						{
							// Get the AllowedCount of the Info
							System.out.println("Tag Info AllowedCount: " + info.getAllowedCount().toString());
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

	/**
	 * <h3>Create Tags</h3> This method is used to create new tags and print the response.
	 * 
	 * @param moduleAPIName - The API Name of the module to create tags.
	 * @throws Exception
	 */
	public static void createTags(String moduleAPIName) throws Exception
	{
		// example
		// String moduleAPIName = "Leads";

		// Get instance of TagsOperations Class
		TagsOperations tagsOperations = new TagsOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper request = new BodyWrapper();

		// Get instance of ParameterMap Class
		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(CreateTagsParam.MODULE, moduleAPIName);

		// List of Tag instances
		List<com.zoho.crm.api.tags.Tag> tagList = new ArrayList<com.zoho.crm.api.tags.Tag>();

		// Get instance of Tag Class
		com.zoho.crm.api.tags.Tag tag1 = new com.zoho.crm.api.tags.Tag();

		tag1.setName("Java SDK v4");

		tagList.add(tag1);

		request.setTags(tagList);

		// Call createTags method that takes BodyWrapper instance and paramInstance as parameter
		APIResponse<ActionHandler> response = tagsOperations.createTags(request, paramInstance);

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
					List<ActionResponse> actionResponses = actionWrapper.getTags();

					for (ActionResponse actionResponse : actionResponses)
					{
						// Check if the request is successful
						if (actionResponse instanceof SuccessResponseTag)
						{
							// Get the received SuccessResponse instance
							SuccessResponseTag successResponse = (SuccessResponseTag) actionResponse;

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

	/**
	 * <h3>Update Tags</h3> This method is used to update multiple tags simultaneously and print the response.
	 * 
	 * @param moduleAPIName - The API Name of the module to update tags.
	 * @throws Exception
	 */
	public static void updateTags(String moduleAPIName) throws Exception
	{
		// example
		// String moduleAPIName = "Leads";

		// Get instance of TagsOperations Class
		TagsOperations tagsOperations = new TagsOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper request = new BodyWrapper();

		// Get instance of ParameterMap Class
		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(UpdateTagsParam.MODULE, moduleAPIName);

		// List of Tag instances
		List<com.zoho.crm.api.tags.Tag> tagList = new ArrayList<com.zoho.crm.api.tags.Tag>();

		// Get instance of Tag Class
		com.zoho.crm.api.tags.Tag tag1 = new com.zoho.crm.api.tags.Tag();

		tag1.setId(3477061116652l);

		tag1.setName("tagNasdsdme123");

		tagList.add(tag1);

		request.setTags(tagList);

		// Call updateTags method that takes BodyWrapper instance and paramInstance as parameter
		APIResponse<ActionHandler> response = tagsOperations.updateTags(request, paramInstance);

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
					List<ActionResponse> actionResponses = actionWrapper.getTags();

					for (ActionResponse actionResponse : actionResponses)
					{
						// Check if the request is successful
						if (actionResponse instanceof SuccessResponseTag)
						{
							// Get the received SuccessResponse instance
							SuccessResponseTag successResponse = (SuccessResponseTag) actionResponse;

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

	/**
	 * <h3>Update Tag</h3> This method is used to update single tag and print the response.
	 * 
	 * @param moduleAPIName - The API Name of the module to update tag.
	 * @param tagId         - The ID of the tag to be obtained.
	 * @throws Exception
	 */
	public static void updateTag(String moduleAPIName, Long tagId) throws Exception
	{
		// example
		// String moduleAPIName = "Leads";
		// Long tagId = 34770615794039;

		// Get instance of TagsOperations Class
		TagsOperations tagsOperations = new TagsOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper request = new BodyWrapper();

		// Get instance of ParameterMap Class
		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(UpdateTagParam.MODULE, moduleAPIName);

		// List of Tag instances
		List<com.zoho.crm.api.tags.Tag> tagList = new ArrayList<com.zoho.crm.api.tags.Tag>();

		// Get instance of Tag Class
		com.zoho.crm.api.tags.Tag tag1 = new com.zoho.crm.api.tags.Tag();

		tag1.setName("Java SDK");

		tagList.add(tag1);

		request.setTags(tagList);

		// Call updateTag method that takes tagId, paramInstance and BodyWrapper instance as parameter
		APIResponse<ActionHandler> response = tagsOperations.updateTag(tagId, request, paramInstance);

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
					List<ActionResponse> actionResponses = actionWrapper.getTags();

					for (ActionResponse actionResponse : actionResponses)
					{
						// Check if the request is successful
						if (actionResponse instanceof SuccessResponseTag)
						{
							// Get the received SuccessResponse instance
							SuccessResponseTag successResponse = (SuccessResponseTag) actionResponse;

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

	/**
	 * <h3>Delete Tag</h3> This method is used to delete a tag from the module and print the response.
	 * 
	 * @param tagId - The ID of the tag to be obtained.
	 * @throws Exception
	 */
	public static void deleteTag(Long tagId) throws Exception
	{
		// example
		// Long tagId = 34770615794039;

		// Get instance of TagsOperations Class
		TagsOperations tagsOperations = new TagsOperations();

		// Call deleteTag method that takes tag id as parameter
		APIResponse<ActionHandler> response = tagsOperations.deleteTag(tagId);

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
					List<ActionResponse> actionResponses = actionWrapper.getTags();

					for (ActionResponse actionResponse : actionResponses)
					{
						// Check if the request is successful
						if (actionResponse instanceof TagsDeleted)
						{
							// Get the received SuccessResponse instance
							TagsDeleted successResponse = (TagsDeleted) actionResponse;

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
							System.out.println("Message: " + successResponse.getMessage().getValue());
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

	/**
	 * <h3>Merge Tag</h3> This method is used to merge tags and put all the records under the two tags into a single tag and print the response.
	 * 
	 * @param tagId      - The ID of the tag to be obtained.
	 * @param conflictId - The ID of the conflict tag to be obtained.
	 * @throws Exception
	 */
	public static void mergeTags(Long tagId, String conflictId) throws Exception
	{
		// example
		// Long tagId = 34770615794039;
		// Long conflictId = 34770615803151;

		// Get instance of TagsOperations Class
		TagsOperations tagsOperations = new TagsOperations();

		// Get instance of MergeWrapper Class that will contain the request body
		MergeWrapper request = new MergeWrapper();

		// List of Tag ConflictWrapper
		List<ConflictWrapper> tags = new ArrayList<ConflictWrapper>();

		// Get instance of ConflictWrapper Class
		ConflictWrapper mergeTag = new ConflictWrapper();

		mergeTag.setConflictId(conflictId);

		tags.add(mergeTag);

		request.setTags(tags);

		// Call mergeTags method that takes tag and MergeWrapper instance id as parameter
		APIResponse<ActionHandler> response = tagsOperations.mergeTags(tagId, request);

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
					List<ActionResponse> actionResponses = actionWrapper.getTags();

					for (ActionResponse actionResponse : actionResponses)
					{
						// Check if the request is successful
						if (actionResponse instanceof SuccessResponseTag)
						{
							// Get the received SuccessResponse instance
							SuccessResponseTag successResponse = (SuccessResponseTag) actionResponse;

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

	/**
	 * <h3>Add Tags To Record</h3> This method is used to add tags to a specific record and print the response.
	 * 
	 * @param moduleAPIName - The API Name of the module to add tag.
	 * @param recordId      - The ID of the record to be obtained.
	 * @param tagNames      - The names of the tags to be added.
	 * @throws Exception
	 */
	public static void addTagsToRecord(String moduleAPIName, long recordId, List<String> tagNames) throws Exception
	{
		// example
		// String moduleAPIName = "Leads";
		// long recordId = 34770615623115L;
		// ArrayList<String> tagNames = new ArrayList<String>(Arrays.asList("addtag1,addtag12"));

		// Get instance of TagsOperations Class
		TagsOperations tagsOperations = new TagsOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		NewTagRequestWrapper request = new NewTagRequestWrapper();

		List<com.zoho.crm.api.tags.Tag> tagList = new ArrayList<com.zoho.crm.api.tags.Tag>();

		// Get instance of Tag Class
		com.zoho.crm.api.tags.Tag tag1 = new com.zoho.crm.api.tags.Tag();

		tag1.setName("tagNam3e3e12345");

		tagList.add(tag1);

		request.setTags(tagList);

		// Get instance of ParameterMap Class
		ParameterMap paramInstance = new ParameterMap();

//		for(String tagName : tagNames)
//		{
//			paramInstance.add(AddTagsToRecordParam.TAG_NAMES, tagName);
//		}

		paramInstance.add(AddTagsParam.OVER_WRITE, "false");

		// Call addTagsToRecord method that takes recordId, moduleAPIName and paramInstance as parameter
		APIResponse<RecordActionHandler> response = tagsOperations.addTags(recordId, moduleAPIName, request, paramInstance);

		if (response != null)
		{
			// Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());

			// Check if expected response is received
			if (response.isExpected())
			{
				// Get object from response
				RecordActionHandler recordActionHandler = response.getObject();

				if (recordActionHandler instanceof RecordActionWrapper)
				{
					// Get the received RecordActionWrapper instance
					RecordActionWrapper recordActionWrapper = (RecordActionWrapper) recordActionHandler;

					// Get the list of obtained RecordActionResponse instances
					List<RecordActionResponse> actionResponses = recordActionWrapper.getData();

					for (RecordActionResponse actionResponse : actionResponses)
					{
						// Check if the request is successful
						if (actionResponse instanceof RecordSuccessResponse)
						{
							// Get the received SuccessResponse instance
							RecordSuccessResponse successResponse = (RecordSuccessResponse) actionResponse;

							// Get the Status
							System.out.println("Status: " + successResponse.getStatus().getValue());

							// Get the Code
							System.out.println("Code: " + successResponse.getCode().getValue());

							System.out.println("Details: ");

							// Get the details map
							for (Map.Entry<String, Object> entry : successResponse.getDetails().entrySet())
							{
								if (entry.getKey().equals("tags"))
								{
									// Get the obtained ShareRecord instance
									@SuppressWarnings("unchecked")
									List<com.zoho.crm.api.tags.RecordDetailTag> tags = (List<com.zoho.crm.api.tags.RecordDetailTag>) entry.getValue();

									for (com.zoho.crm.api.tags.RecordDetailTag tag : tags)
									{
										// Get the ColorCode of each Tag
										System.out.println("Tag ColorCode: " + tag.getColorCode());

										// Get the Name of each Tag
										System.out.println("Tag Name: " + tag.getName());

										// Get the ID of each Tag
										System.out.println("Tag ID: " + tag.getId());
									}
								}
								else
								{
									// Get each value in the map
									System.out.println(entry.getKey() + ": " + entry.getValue());
								}
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
				else if (recordActionHandler instanceof APIException)
				{
					// Check if the request returned an exception
					APIException exception = (APIException) recordActionHandler;

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

	/**
	 * <h3>Remove Tags From Record</h3> This method is used to delete the tag associated with a specific record and print the response.
	 * 
	 * @param moduleAPIName - The API Name of the module to remove tag.
	 * @param recordId      - The ID of the record to be obtained.
	 * @param tagNames      - The names of the tags to be remove.
	 * @throws Exception
	 */
	public static void removeTagsFromRecord(String moduleAPIName, long recordId, List<String> tagNames) throws Exception
	{
		// example
		// String moduleAPIName = "Leads";
		// long recordId = 34770615623115L;
		// ArrayList<String> tagNames = new ArrayList<String>(Arrays.asList("addtag1,addtag12"));
		// Get instance of TagsOperations Class
		TagsOperations tagsOperations = new TagsOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		ExistingTagRequestWrapper request = new ExistingTagRequestWrapper();

		List<com.zoho.crm.api.tags.ExistingTag> tagList = new ArrayList<com.zoho.crm.api.tags.ExistingTag>();

		// Get instance of Tag Class
		com.zoho.crm.api.tags.ExistingTag tag1 = new com.zoho.crm.api.tags.ExistingTag();

		tag1.setName("tagNam3e3e12345");

		tagList.add(tag1);

		request.setTags(tagList);

		// Call removeTagsFromRecord method that takes recordId, moduleAPIName and paramInstance as parameter
		APIResponse<RecordActionHandler> response = tagsOperations.removeTags(recordId, moduleAPIName, request);

		if (response != null)
		{
			// Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());

			// Check if expected response is received
			if (response.isExpected())
			{
				// Get object from response
				RecordActionHandler recordActionHandler = response.getObject();

				if (recordActionHandler instanceof RecordActionWrapper)
				{
					// Get the received RecordActionWrapper instance
					RecordActionWrapper recordActionWrapper = (RecordActionWrapper) recordActionHandler;

					// Get the list of obtained RecordActionResponse instances
					List<RecordActionResponse> actionResponses = recordActionWrapper.getData();

					for (RecordActionResponse actionResponse : actionResponses)
					{
						// Check if the request is successful
						if (actionResponse instanceof RecordSuccessResponse)
						{
							// Get the received SuccessResponse instance
							RecordSuccessResponse successResponse = (RecordSuccessResponse) actionResponse;

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
				else if (recordActionHandler instanceof APIException)
				{
					// Get the received APIException instance
					APIException exception = (APIException) recordActionHandler;

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

	/**
	 * <h3>Add Tags To Multiple Records</h3> This method is used to add tags to multiple records simultaneously and print the response.
	 * 
	 * @param moduleAPIName - The API Name of the module to add tags.
	 * @param recordIds     - The ID of the record to be obtained.
	 * @param tagNames      - The names of the tags to be add.
	 * @throws Exception
	 */
	public static void addTagsToMultipleRecords(String moduleAPIName, List<Long> recordIds, List<String> tagNames) throws Exception
	{
		// example
		// String moduleAPIName = "Leads";
		// ArrayList<Long> recordIds = new ArrayList<String>(Arrays.asList(34770615623115l, 34770616114067l));
		// ArrayList<String> tagNames = new ArrayList<String>(Arrays.asList("addtag1,addtag12"));

		// Get instance of TagsOperations Class
		TagsOperations tagsOperations = new TagsOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		NewTagRequestWrapper request = new NewTagRequestWrapper();

		List<com.zoho.crm.api.tags.Tag> tagList = new ArrayList<com.zoho.crm.api.tags.Tag>();

		// Get instance of Tag Class
		com.zoho.crm.api.tags.Tag tag1 = new com.zoho.crm.api.tags.Tag();

		tag1.setName("tagNam3e3e12345");

		tagList.add(tag1);

		request.setTags(tagList);

		// Get instance of ParameterMap Class
		ParameterMap paramInstance = new ParameterMap();
//		
//		for(String tagName : tagNames)
//		{
//			paramInstance.add(AddTagsToMultipleRecordsParam.TAG_NAMES, tagName);
//		}

		request.setOverWrite(true);

		request.setIds(recordIds);

		paramInstance.add(AddTagsToMultipleRecordsParam.OVER_WRITE, "false");

		// Call addTagsToMultipleRecords method that takes moduleAPIName, paramInstance as parameter
		APIResponse<RecordActionHandler> response = tagsOperations.addTagsToMultipleRecords(moduleAPIName, request, paramInstance);

		if (response != null)
		{
			// Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());

			// Check if expected response is received
			if (response.isExpected())
			{
				// Get object from response
				RecordActionHandler recordActionHandler = response.getObject();

				if (recordActionHandler instanceof RecordActionWrapper)
				{
					// Get the received RecordActionWrapper instance
					RecordActionWrapper recordActionWrapper = (RecordActionWrapper) recordActionHandler;

					// Get the list of obtained RecordActionResponse instances
					List<RecordActionResponse> actionResponses = recordActionWrapper.getData();

					for (RecordActionResponse actionResponse : actionResponses)
					{
						// Check if the request is successful
						if (actionResponse instanceof RecordSuccessResponse)
						{
							// Get the received SuccessResponse instance
							RecordSuccessResponse successResponse = (RecordSuccessResponse) actionResponse;

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

					// Check if locked count is null
					if (recordActionWrapper.getLockedCount() != null)
					{
						System.out.println("Locked Count: " + recordActionWrapper.getLockedCount().toString());
					}

					// Check if success count is null
					if (recordActionWrapper.getSuccessCount() != null)
					{
						System.out.println("Success Count: " + recordActionWrapper.getSuccessCount());
					}

					// Check if wf scheduler is null
					if (recordActionWrapper.getWfScheduler() != null)
					{
						System.out.println("WF Scheduler: " + recordActionWrapper.getWfScheduler());
					}
				}
				// Check if the request returned an exception
				else if (recordActionHandler instanceof APIException)
				{
					// Check if the request returned an exception
					APIException exception = (APIException) recordActionHandler;

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

	/**
	 * <h3>Remove Tags From Multiple Records</h3> This method is used to delete the tags associated with multiple records and print the response.
	 * 
	 * @param moduleAPIName - The API Name of the module to remove tags.
	 * @param recordIds     - The ID of the record to be obtained.
	 * @param tagNames      - The names of the tags to be remove.
	 * @throws Exception
	 */
	public static void removeTagsFromMultipleRecords(String moduleAPIName, List<Long> recordIds, List<String> tagNames) throws Exception
	{
		// example
		// String moduleAPIName = "Leads";
		// ArrayList<Long> recordIds = new ArrayList<Long>(Arrays.asList(34770615623115, 34770616114067));
		// ArrayList<String> tagNames = new ArrayList<String>(Arrays.asList("addtag1,addtag12"));

		// Get instance of TagsOperations Class
		TagsOperations tagsOperations = new TagsOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		ExistingTagRequestWrapper request = new ExistingTagRequestWrapper();

		List<com.zoho.crm.api.tags.ExistingTag> tagList = new ArrayList<com.zoho.crm.api.tags.ExistingTag>();

		// Get instance of Tag Class
		com.zoho.crm.api.tags.ExistingTag tag1 = new com.zoho.crm.api.tags.ExistingTag();

		tag1.setName("tagNam3e3e12345");

		tagList.add(tag1);

		request.setTags(tagList);

		request.setIds(new ArrayList<Long>(Arrays.asList(34770615623115l)));

		ParameterMap paramInstance = new ParameterMap();

//		for(String tagName : tagNames)
//		{
//			paramInstance.add(RemoveTagsFromMultipleRecordsParam.TAG_NAMES, tagName);
//		}

		for (Long recordId : recordIds)
		{
			paramInstance.add(RemoveTagsFromMultipleRecordsParam.IDS, recordId);
		}

		// Call RemoveTagsFromMultipleRecordsParam method that takes moduleAPIName, paramInstance as parameter
		APIResponse<RecordActionHandler> response = tagsOperations.removeTagsFromMultipleRecords(moduleAPIName, request, paramInstance);

		if (response != null)
		{
			// Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());

			// Check if expected response is received
			if (response.isExpected())
			{
				// Get object from response
				RecordActionHandler recordActionHandler = response.getObject();

				if (recordActionHandler instanceof RecordActionWrapper)
				{
					// Get the received RecordActionWrapper instance
					RecordActionWrapper recordActionWrapper = (RecordActionWrapper) recordActionHandler;

					// Get the list of obtained RecordActionResponse instances
					List<RecordActionResponse> actionResponses = recordActionWrapper.getData();

					for (RecordActionResponse actionResponse : actionResponses)
					{
						// Check if the request is successful
						if (actionResponse instanceof RecordSuccessResponse)
						{
							// Get the received SuccessResponse instance
							RecordSuccessResponse successResponse = (RecordSuccessResponse) actionResponse;

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

					// Check if locked count is null
					if (recordActionWrapper.getLockedCount() != null)
					{
						System.out.println("Locked Count: " + recordActionWrapper.getLockedCount().toString());
					}

					// Check if success count is null
					if (recordActionWrapper.getSuccessCount() != null)
					{
						System.out.println("Success Count: " + recordActionWrapper.getSuccessCount());
					}

					// Check if wf scheduler is null
					if (recordActionWrapper.getWfScheduler() != null)
					{
						System.out.println("WF Scheduler: " + recordActionWrapper.getWfScheduler());
					}
				}
				// Check if the request returned an exception
				else if (recordActionHandler instanceof APIException)
				{
					// Get the received APIException instance
					APIException exception = (APIException) recordActionHandler;

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

	/**
	 * <h3>Get Record Count For Tag</h3> This method is used to get the total number of records under a tag and print the response.
	 * 
	 * @param moduleAPIName - The API Name of the module.
	 * @param tagId         - The ID of the tag to be obtained.
	 * @throws Exception
	 */
	public static void getRecordCountForTag(String moduleAPIName, Long tagId) throws Exception
	{
		// example
		// String moduleAPIName = "Leads";
		// Long tagId = 34770615803151l;

		// Get instance of TagsOperations Class
		TagsOperations tagsOperations = new TagsOperations();

		// Get instance of ParameterMap Class
		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(GetRecordCountForTagParam.MODULE, moduleAPIName);

		// Call getRecordCountForTag method that takes tagId and paramInstance as parameter
		APIResponse<ResponseHandler> response = tagsOperations.getRecordCountForTag(tagId, paramInstance);

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
				ResponseHandler countHandler = response.getObject();

				if (countHandler instanceof ResponseWrapper)
				{
					// Get the received CountWrapper instance
					ResponseWrapper countWrapper = (ResponseWrapper) countHandler;

					// Get the Count of Tag
					System.out.println("Tag Count: " + countWrapper.getCount());
				}
				// Check if the request returned an exception
				else if (countHandler instanceof APIException)
				{
					// Get the received APIException instance
					APIException exception = (APIException) countHandler;

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
