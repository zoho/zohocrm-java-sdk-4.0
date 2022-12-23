package com.zoho.crm.sample.dealcontactroles;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.dealcontactroles.APIException;
import com.zoho.crm.api.dealcontactroles.ActionHandler;
import com.zoho.crm.api.dealcontactroles.ActionResponse;
import com.zoho.crm.api.dealcontactroles.ActionWrapper;
import com.zoho.crm.api.dealcontactroles.BodyWrapper;
import com.zoho.crm.api.dealcontactroles.ContactRole;
import com.zoho.crm.api.dealcontactroles.Data;
import com.zoho.crm.api.dealcontactroles.DealContactRolesOperations;
import com.zoho.crm.api.dealcontactroles.DealContactRolesOperations.GetAssociatedContactRolesParam;
import com.zoho.crm.api.dealcontactroles.Info;
import com.zoho.crm.api.dealcontactroles.ResponseHandler;
import com.zoho.crm.api.dealcontactroles.SuccessResponse;
import com.zoho.crm.api.exception.SDKException;
import com.zoho.crm.api.users.MinifiedUser;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class DealContactRoles
{
	/**
	 * <h3>Get All ContactRoles Of Deal</h3>
	 * 
	 * @param dealId ID of the Deals
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws SDKException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void getAllContactRolesOfDeal(long dealId) throws Exception
	{
		// Get instance of DealContactRolesOperations Class
		DealContactRolesOperations contactRolesOperations = new DealContactRolesOperations();

		// Get instance of ParameterMap Class
		ParameterMap paramInstance = new ParameterMap();

	    paramInstance.add(GetAssociatedContactRolesParam.IDS, 347706114564040l);

//		paramInstance.add(GetAssociatedContactRolesParam.FIELDS, "Id");

		// Call getAssociatedContactRoles method that takes Param instance as parameter
		APIResponse<ResponseHandler> response = contactRolesOperations.getAssociatedContactRoles(dealId, paramInstance);

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
				// Get the object from response
				ResponseHandler responseHandler = response.getObject();

				if (responseHandler instanceof BodyWrapper)
				{
					// Get the received RecordResponseWrapper instance
					BodyWrapper responseWrapper = (BodyWrapper) responseHandler;

					// Get the obtained Record instances
					List<Data> records = responseWrapper.getData();

					for (com.zoho.crm.api.record.Record record : records)
					{
						// Get the ID of each Record
						System.out.println("Record ID: " + record.getId());

						// Get the createdBy User instance of each Record
						MinifiedUser createdBy = record.getCreatedBy();

						// Check if createdBy is not null
						if (createdBy != null)
						{
							// Get the ID of the createdBy User
							System.out.println("Record Created By User-ID: " + createdBy.getId());

							// Get the name of the createdBy User
							System.out.println("Record Created By User-Name: " + createdBy.getName());

							// Get the Email of the createdBy User
							System.out.println("Record Created By User-Email: " + createdBy.getEmail());
						}

						// Get the CreatedTime of each Record
						System.out.println("Record CreatedTime: " + record.getCreatedTime());

						// Get the modifiedBy User instance of each Record
						MinifiedUser modifiedBy = record.getModifiedBy();

						// Check if modifiedBy is not null
						if (modifiedBy != null)
						{
							// Get the ID of the modifiedBy User
							System.out.println("Record Modified By User-ID: " + modifiedBy.getId());

							// Get the name of the modifiedBy User
							System.out.println("Record Modified By User-Name: " + modifiedBy.getName());

							// Get the Email of the modifiedBy User
							System.out.println("Record Modified By User-Email: " + modifiedBy.getEmail());
						}

						// Get the ModifiedTime of each Record
						System.out.println("Record CreatedTime: " + record.getModifiedTime());

						// To get particular field value
						System.out.println("Record Field Value: " + record.getKeyValue("Last_Name"));// FieldApiName

						System.out.println("Record KeyValues:");

						// Get the KeyValue map
						for (Map.Entry<String, Object> entry : record.getKeyValues().entrySet())
						{
							String keyName = entry.getKey();

							Object value = entry.getValue();

							if (value instanceof List)
							{
								System.out.println("Record KeyName : " + keyName);

								List<?> dataList = (List<?>) value;

								for (Object data : dataList)
								{
									if (data instanceof Map)
									{
										System.out.println("Record KeyName : " + keyName + " - Value : ");

										for (Map.Entry<String, Object> mapValue : ((HashMap<String, Object>) data).entrySet())
										{
											System.out.println(mapValue.getKey() + " : " + mapValue.getValue());
										}
									}
									else
									{
										System.out.println(data);
									}
								}
							}
							else if(value instanceof com.zoho.crm.api.dealcontactroles.ContactRole)
							{
								com.zoho.crm.api.dealcontactroles.ContactRole contactrole = (com.zoho.crm.api.dealcontactroles.ContactRole)value;
								
								if(contactrole != null)
								{
									System.out.println("Record ContactRole Name : " + contactrole.getName());
									
									System.out.println("Record ContactRole Id : " + contactrole.getId());
								}
							}
							else if (value instanceof Map)
							{
								System.out.println("Record KeyName : " + keyName + " - Value : ");

								for (Map.Entry<String, Object> mapValue : ((HashMap<String, Object>) value).entrySet())
								{
									System.out.println(mapValue.getKey() + " : " + mapValue.getValue());
								}
							}
							else
							{
								System.out.println("Record KeyName : " + keyName + " - Value : " + value);
							}
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
							System.out.println("Record Info PerPage: " + info.getPerPage().toString());
						}
						
						if (info.getNextPageToken() != null)
						{
							// Get the NextPageToken of the Info
							System.out.println("Record Info PerPage: " + info.getNextPageToken().toString());
						}
						
						if (info.getCount() != null)
						{
							// Get the Count of the Info
							System.out.println("Record Info Count: " + info.getCount().toString());
						}

						if (info.getPage() != null)
						{
							// Get the Page of the Info
							System.out.println("Record Info Page: " + info.getPage().toString());
						}
						
						if (info.getPreviousPageToken() != null)
						{
							// Get the PreviousPageToken of the Info
							System.out.println("Record Info PreviousPageToken: " + info.getPreviousPageToken().toString());
						}
						
						if (info.getPageTokenExpiry() != null)
						{
							// Get the PageTokenExpiry of the Info
							System.out.println("Record Info PageTokenExpiry: " + info.getPageTokenExpiry().toString());
						}
						
						if (info.getMoreRecords() != null)
						{
							// Get the MoreRecords of the Info
							System.out.println("Record Info MoreRecords: " + info.getMoreRecords().toString());
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
				Field[] fields = clas.getDeclaredFields();

				for (Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void getContactRoleOfDeal(long contactId, long dealId) throws IllegalArgumentException, IllegalAccessException, SDKException
	{
		// Get instance of ContactRolesOperations Class
		DealContactRolesOperations contactRolesOperations = new DealContactRolesOperations();

		// Call getContactRoleOfDeal method that takes Param instance as parameter
		APIResponse<ResponseHandler> response = contactRolesOperations.getAssociatedContactRolesSpecificToContact(contactId, dealId);

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
				// Get the object from response
				ResponseHandler responseHandler = response.getObject();

				if (responseHandler instanceof BodyWrapper)
				{
					// Get the received RecordResponseWrapper instance
					BodyWrapper responseWrapper = (BodyWrapper) responseHandler;

					// Get the obtained Record instances
					List<Data> records = responseWrapper.getData();

					for (Data record : records)
					{
						// Get the ID of each Record
						System.out.println("Record ID: " + record.getId());

						// Get the createdBy User instance of each Record
						MinifiedUser createdBy = record.getCreatedBy();

						// Check if createdBy is not null
						if (createdBy != null)
						{
							// Get the ID of the createdBy User
							System.out.println("Record Created By User-ID: " + createdBy.getId());

							// Get the name of the createdBy User
							System.out.println("Record Created By User-Name: " + createdBy.getName());

							// Get the Email of the createdBy User
							System.out.println("Record Created By User-Email: " + createdBy.getEmail());
						}

						// Get the CreatedTime of each Record
						System.out.println("Record CreatedTime: " + record.getCreatedTime());

						// Get the modifiedBy User instance of each Record
						MinifiedUser modifiedBy = record.getModifiedBy();

						// Check if modifiedBy is not null
						if (modifiedBy != null)
						{
							// Get the ID of the modifiedBy User
							System.out.println("Record Modified By User-ID: " + modifiedBy.getId());

							// Get the name of the modifiedBy User
							System.out.println("Record Modified By User-Name: " + modifiedBy.getName());

							// Get the Email of the modifiedBy User
							System.out.println("Record Modified By User-Email: " + modifiedBy.getEmail());
						}

						// Get the ModifiedTime of each Record
						System.out.println("Record CreatedTime: " + record.getModifiedTime());

						// To get particular field value
						System.out.println("Record Field Value: " + record.getKeyValue("Last_Name"));// FieldApiName

						System.out.println("Record KeyValues:");

						// Get the KeyValue map
						for (Map.Entry<String, Object> entry : record.getKeyValues().entrySet())
						{
							String keyName = entry.getKey();

							Object value = entry.getValue();

							if (value instanceof List)
							{
								System.out.println("Record KeyName : " + keyName);

								List<?> dataList = (List<?>) value;

								for (Object data : dataList)
								{
									if (data instanceof Map)
									{
										System.out.println("Record KeyName : " + keyName + " - Value : ");

										for (Map.Entry<String, Object> mapValue : ((HashMap<String, Object>) data).entrySet())
										{
											System.out.println(mapValue.getKey() + " : " + mapValue.getValue());
										}
									}
									else
									{
										System.out.println(data);
									}
								}
							}
							else if(value instanceof com.zoho.crm.api.dealcontactroles.ContactRole)
							{
								com.zoho.crm.api.dealcontactroles.ContactRole contactrole = (com.zoho.crm.api.dealcontactroles.ContactRole)value;
								
								if(contactrole != null)
								{
									System.out.println("Record ContactRole Name : " + contactrole.getName());
									
									System.out.println("Record ContactRole Id : " + contactrole.getId());
								}
							}
							else if (value instanceof Map)
							{
								System.out.println("Record KeyName : " + keyName + " - Value : ");

								for (Map.Entry<String, Object> mapValue : ((HashMap<String, Object>) value).entrySet())
								{
									System.out.println(mapValue.getKey() + " : " + mapValue.getValue());
								}
							}
							else
							{
								System.out.println("Record KeyName : " + keyName + " - Value : " + value);
							}
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
							System.out.println("Record Info Count: " + info.getCount().toString());
						}

						if (info.getMoreRecords() != null)
						{
							// Get the MoreRecords of the Info
							System.out.println("Record Info MoreRecords: " + info.getMoreRecords().toString());
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
				Field[] fields = clas.getDeclaredFields();

				for (Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	public static void addContactRoleToDeal(long contactId, long dealId) throws SDKException
	{
		// Get instance of ContactRolesOperations Class
		DealContactRolesOperations contactRolesOperations = new DealContactRolesOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper bodyWrapper = new BodyWrapper();

		List<Data> data = new ArrayList<>();

		Data data1 = new Data();

		// Get instance of ContactRole Class
		ContactRole contactRole = new ContactRole();
		
		contactRole.setId(347706191858l);

		// Set name of the Contact Role
		contactRole.setName("contactRole1211");
		
		data1.setContactRole(contactRole);

		data.add(data1);

		bodyWrapper.setData(data);

		// Call createContactRoles method that takes BodyWrapper instance as parameter
		APIResponse<ActionHandler> response = contactRolesOperations.associateContactRoleToDeal(contactId, dealId, bodyWrapper);

		if (response != null)
		{
			// Get the status code from response
			System.out.println("Status code" + response.getStatusCode());

			// Get object from response
			ActionHandler actionHandler = response.getObject();

			if (actionHandler instanceof ActionWrapper)
			{
				// Get the received ActionWrapper instance
				ActionWrapper actionWrapper = (ActionWrapper) actionHandler;

				// Get the list of obtained action responses
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

						if (successResponse.getDetails() != null)
						{
							// Get the details map
							for (Map.Entry<String, Object> entry : successResponse.getDetails().entrySet())
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
	}

	public static void removeContactRoleFromDeal(long contactId, long dealId) throws SDKException
	{
		// Get instance of DealContactRolesOperations Class
		DealContactRolesOperations contactRolesOperations = new DealContactRolesOperations();

		// Call createContactRoles method that takes BodyWrapper instance as parameter
		APIResponse<ActionHandler> response = contactRolesOperations.deleteContactRoleRealation(contactId, dealId);

		if (response != null)
		{
			// Get the status code from response
			System.out.println("Status code" + response.getStatusCode());

			// Get object from response
			ActionHandler actionHandler = response.getObject();

			if (actionHandler instanceof ActionWrapper)
			{
				// Get the received ActionWrapper instance
				ActionWrapper actionWrapper = (ActionWrapper) actionHandler;

				// Get the list of obtained action responses
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

						if (successResponse.getDetails() != null)
						{
							// Get the details map
							for (Map.Entry<String, Object> entry : successResponse.getDetails().entrySet())
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
	}
}
