package com.zoho.crm.sample.contactroles;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.crm.api.contactroles.ActionResponse;
import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.contactroles.APIException;
import com.zoho.crm.api.contactroles.ActionHandler;
import com.zoho.crm.api.contactroles.ActionWrapper;
import com.zoho.crm.api.contactroles.BodyWrapper;
import com.zoho.crm.api.contactroles.ContactRole;
import com.zoho.crm.api.contactroles.ContactRolesOperations;
import com.zoho.crm.api.contactroles.ContactRolesOperations.DeleteRolesParam;
import com.zoho.crm.api.contactroles.ResponseHandler;
import com.zoho.crm.api.contactroles.SuccessResponse;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class ContactRoles
{
	/**
	 * <h3>Get Contact Roles</h3> This method is used to get all the Contact Roles and print the response.
	 * 
	 * @throws Exception
	 */
	public static void getContactRoles() throws Exception
	{
		// Get instance of ContactRolesOperations Class
		ContactRolesOperations contactRolesOperations = new ContactRolesOperations();

		// Call getContactRoles method
		APIResponse<ResponseHandler> response = contactRolesOperations.getRoles();

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

					// Get the list of obtained ContactRole instances
					List<ContactRole> contactRoles = responseWrapper.getContactRoles();

					for (ContactRole contactRole : contactRoles)
					{
						// Get the ID of each ContactRole
						System.out.println("ContactRole ID: " + contactRole.getId());

						// Get the name of each ContactRole
						System.out.println("ContactRole Name: " + contactRole.getName());

						// Get the sequence number each ContactRole
						System.out.println("ContactRole SequenceNumber: " + contactRole.getSequenceNumber());
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
			else if (response.getStatusCode() != 204)
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				Field[] fields = clas.getDeclaredFields();

				for (Field field : fields)
				{
					field.setAccessible(true);

					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	/**
	 * <h3>Create Contact Roles</h3> This method is used to create Contact Roles and print the response.
	 * 
	 * @throws Exception
	 */
	public static void createContactRoles() throws Exception
	{
		// Get instance of ContactRolesOperations Class
		ContactRolesOperations contactRolesOperations = new ContactRolesOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper bodyWrapper = new BodyWrapper();

		// List of ContactRole instances
		List<ContactRole> contactRoles = new ArrayList<ContactRole>();

		for (int i = 1; i <= 5; i++)
		{
			// Get instance of ContactRole Class
			ContactRole contactRole = new ContactRole();

			// Set name of the Contact Role
			contactRole.setName("contactRole12123" + i);

			// Set sequence number of the Contact Role
			contactRole.setSequenceNumber(i);

			// Add ContactRole instance to the list
			contactRoles.add(contactRole);
		}

		// Set the list to contactRoles in BodyWrapper instance
		bodyWrapper.setContactRoles(contactRoles);

		// Call createContactRoles method that takes BodyWrapper instance as parameter
		APIResponse<ActionHandler> response = contactRolesOperations.createRoles(bodyWrapper);

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
					List<ActionResponse> actionResponses = actionWrapper.getContactRoles();

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

	/**
	 * <h3>Update Contact Roles</h3> This method is used to update Contact Roles and print the response.
	 * 
	 * @throws Exception
	 */
	public static void updateContactRoles() throws Exception
	{
		// Get instance of ContactRolesOperations Class
		ContactRolesOperations contactRolesOperations = new ContactRolesOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper bodyWrapper = new BodyWrapper();

		// List of ContactRole instances
		List<ContactRole> contactRolesList = new ArrayList<ContactRole>();

		// Get instance of ContactRole Class
		ContactRole cr1 = new ContactRole();

		// Set ID to the ContactRole instance
		cr1.setId(3477061146865l);

		// Set name to the ContactRole instance
		cr1.setName("Editsded1");

		// Add ContactRole instance to the list
		contactRolesList.add(cr1);

		// Get instance of ContactRole Class
		ContactRole cr2 = new ContactRole();

		// Set ID to the ContactRole instance
		cr2.setId(3477061146863l);

		cr2.setSequenceNumber(1);

		// Set name to the ContactRole instance
		cr2.setName("Edisdasted1");

		// Add ContactRole instance to the list
		contactRolesList.add(cr2);

		// Set the list to contactRoles in BodyWrapper instance
		bodyWrapper.setContactRoles(contactRolesList);

		// Call updateContactRoles method that takes BodyWrapper instance as parameter
		APIResponse<ActionHandler> response = contactRolesOperations.updateRoles(bodyWrapper);

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
					List<ActionResponse> actionResponses = actionWrapper.getContactRoles();

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

	
	/**
	 * <h3>Delete Contact Roles</h3> This method is used to delete Contact Roles and print the response.
	 * 
	 * @param contactRoleIds - The List of ContactRole IDs to be deleted.
	 * @throws Exception
	 */
	public static void deleteContactRoles(List<Long> contactRoleIds) throws Exception
	{
		// example
//		ArrayList<Long> contactRoleIds = new ArrayList<Long>(Arrays.asList(347706152081l, 347706152082l));

		// Get instance of ContactRolesOperations Class
		ContactRolesOperations contactRolesOperations = new ContactRolesOperations();

		// Get instance of ParameterMap Class
		ParameterMap paramInstance = new ParameterMap();

		for (Long id : contactRoleIds)
		{
			paramInstance.add(DeleteRolesParam.IDS, id);
		}

		// Call deleteContactRoles method that takes paramInstance as parameter
		APIResponse<ActionHandler> response = contactRolesOperations.deleteRoles(paramInstance);

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

					// Get the list of obtained ContactRole instances
					List<ActionResponse> actionResponses = actionWrapper.getContactRoles();

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

	/**
	 * <h3>Get Contact Role</h3> This method is used to get single Contact Role with ID and print the response.
	 * 
	 * @param contactRoleId - The ID of the ContactRole to be obtained
	 * @throws Exception
	 */
	public static void getContactRole(Long contactRoleId) throws Exception
	{
		// example
		// Long contactRoleId = 347706151774l;

		// Get instance of ContactRolesOperations Class
		ContactRolesOperations contactRolesOperations = new ContactRolesOperations();

		// Call getContactRole method that takes contactRoleId as parameter
		APIResponse<ResponseHandler> response = contactRolesOperations.getRole(contactRoleId);

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

					// Get the list of obtained ContactRole instances
					List<ContactRole> contactRoles = responseWrapper.getContactRoles();

					for (ContactRole contactRole : contactRoles)
					{
						// Get the ID of each ContactRole
						System.out.println("ContactRole ID: " + contactRole.getId());

						// Get the name of each ContactRole
						System.out.println("ContactRole Name: " + contactRole.getName());

						// Get the sequence number each ContactRole
						System.out.println("ContactRole SequenceNumber: " + contactRole.getSequenceNumber());
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

	/**
	 * <h3>Update Contact Role</h3> This method is used to update single Contact Role with ID and print the response.
	 * 
	 * @param contactRoleId The ID of the ContactRole to be updated
	 * @throws Exception
	 */
	public static void updateContactRole(Long contactRoleId) throws Exception
	{
		// ID of the ContactRole to be updated
		// Long contactRoleId = 5255085067923l;

		// Get instance of ContactRolesOperations Class
		ContactRolesOperations contactRolesOperations = new ContactRolesOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper bodyWrapper = new BodyWrapper();

		// List of ContactRole instances
		List<ContactRole> contactRolesList = new ArrayList<ContactRole>();

		// Get instance of ContactRole Class
		ContactRole cr1 = new ContactRole();

		// Set name to the ContactRole instance
		cr1.setName("ContactRole");

		// Set sequence number to the ContactRole instance
		cr1.setSequenceNumber(2);

		// Add ContactRole instance to the list
		contactRolesList.add(cr1);

		// Set the list to contactRoles in BodyWrapper instance
		bodyWrapper.setContactRoles(contactRolesList);

		// Call updateContactRole method that takes contactRoleId and BodyWrapper instance as parameters
		APIResponse<ActionHandler> response = contactRolesOperations.updateRole(contactRoleId, bodyWrapper);

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
					List<ActionResponse> actionResponses = actionWrapper.getContactRoles();

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

	/**
	 * <h3>Delete Contact Role</h3> This method is used to delete single Contact Role with ID and print the response.
	 * 
	 * @param contactRoleId ID of the ContactRole to be deleted
	 * @throws Exception
	 */
	public static void deleteContactRole(Long contactRoleId) throws Exception
	{
		// ID of the ContactRole to be updated
		// Long contactRoleId = 5255085067923l;

		// Get instance of ContactRolesOperations Class
		ContactRolesOperations contactRolesOperations = new ContactRolesOperations();

		// Call deleteContactRole which takes contactRoleId as parameter
		APIResponse<ActionHandler> response = contactRolesOperations.deleteRole(contactRoleId);

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
					List<ActionResponse> actionResponses = actionWrapper.getContactRoles();

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
