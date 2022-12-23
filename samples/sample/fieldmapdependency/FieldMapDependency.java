package com.zoho.crm.sample.fieldmapdependency;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.fieldmapdependency.APIException;
import com.zoho.crm.api.fieldmapdependency.ActionHandler;
import com.zoho.crm.api.fieldmapdependency.ActionResponse;
import com.zoho.crm.api.fieldmapdependency.ActionWrapper;
import com.zoho.crm.api.fieldmapdependency.BodyWrapper;
import com.zoho.crm.api.fieldmapdependency.Child;
import com.zoho.crm.api.fieldmapdependency.FieldMapDependencyOperations;
import com.zoho.crm.api.fieldmapdependency.Info;
import com.zoho.crm.api.fieldmapdependency.MapDependency;
import com.zoho.crm.api.fieldmapdependency.Parent;
import com.zoho.crm.api.fieldmapdependency.PickListMapping;
import com.zoho.crm.api.fieldmapdependency.PicklistMap;
import com.zoho.crm.api.fieldmapdependency.ResponseHandler;
import com.zoho.crm.api.fieldmapdependency.SuccessResponse;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class FieldMapDependency
{

	public static void createMapDependency(Long layoutId, String module) throws Exception
	{
		// Get instance of FieldMapDependencyOperations Class
		FieldMapDependencyOperations fieldMapDependencyOperations = new FieldMapDependencyOperations(layoutId, module);

		BodyWrapper bodyWrapper = new BodyWrapper();

		List<MapDependency> mapDependencies = new ArrayList<MapDependency>();

		MapDependency mapdependency = new MapDependency();
		
		Parent parent = new Parent();
		
		parent.setAPIName("Lead_Status");
		
		parent.setId(34770612611l);
		
		mapdependency.setParent(parent);

		Child child = new Child();
		
		child.setAPIName("Pick_List_1");
		
		child.setId(3477061166281l);
		
		mapdependency.setChild(child);
		
		List<PickListMapping> pickListValues = new ArrayList<>();
		
		PickListMapping pickListValue = new PickListMapping();
		
		pickListValue.setDisplayValue("-None-");
		
		pickListValue.setId(34770613409l);
		
		pickListValue.setActualValue("-None-");
		
		List<PicklistMap> picklistMaps = new ArrayList<>();
		
		PicklistMap picklistMap = new PicklistMap();
		
		picklistMap.setId(3477061166285l);
		
		picklistMap.setActualValue("Option 1");
		
		picklistMap.setDisplayValue("Option 1");
		
		picklistMaps.add(picklistMap);
		
		picklistMap = new PicklistMap();
		
		picklistMap.setId(3477061166283l);
		
		picklistMap.setActualValue("-None-");
		
		picklistMap.setDisplayValue("-None-");
		
		picklistMaps.add(picklistMap);
		
		pickListValue.setMaps(picklistMaps);
		
		pickListValues.add(pickListValue);
		
		mapdependency.setPickListValues(pickListValues);
		
		mapDependencies.add(mapdependency);
	
		bodyWrapper.setMapDependency(mapDependencies);

		APIResponse<ActionHandler> response = fieldMapDependencyOperations.createMapDependency(bodyWrapper);

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
					List<ActionResponse> actionResponses = actionWrapper.getMapDependency();

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

	public static void getMapDependencies(Long layoutId, String module) throws Exception
	{
		FieldMapDependencyOperations fieldMapDependencyOperations = new FieldMapDependencyOperations(layoutId, module);
		
		ParameterMap paramInstance = new ParameterMap();

		// Call getMapDependencys method
		APIResponse<ResponseHandler> response = fieldMapDependencyOperations.getMapDependencies(paramInstance);

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

					List<MapDependency> mapDependencies = responseWrapper.getMapDependency();

					for (MapDependency mapDependency : mapDependencies)
					{
						Parent parent = mapDependency.getParent();
						
						if(parent != null)
						{
							System.out.println("MapDependency Parent ID: " + parent.getId());
							
							System.out.println("MapDependency Parent APIName: " + parent.getAPIName());
						}
						
						Child child = mapDependency.getChild();
						
						if(child != null)
						{
							System.out.println("MapDependency Child ID: " + child.getId());
							
							System.out.println("MapDependency Child APIName: " + child.getAPIName());
						}
						
						List<PickListMapping> pickListValues = mapDependency.getPickListValues();
						
						if(pickListValues != null)
						{
							pickListValues.forEach(pickListValue ->
							{
								System.out.println("MapDependency PickListValue ID: " + pickListValue.getId());
								
								System.out.println("MapDependency PickListValue ActualValue: " + pickListValue.getActualValue());
								
								System.out.println("MapDependency PickListValue DisplayValue: " + pickListValue.getDisplayValue());
								
								List<PicklistMap> picklistMaps = pickListValue.getMaps();
								
								if(picklistMaps != null)
								{
									picklistMaps.forEach(picklistMap ->
									{
										System.out.println("MapDependency PickListValue Map ID: " + picklistMap.getId());
										
										System.out.println("MapDependency PickListValue Map ActualValue: " + picklistMap.getActualValue());
										
										System.out.println("MapDependency PickListValue Map DisplayValue: " + picklistMap.getDisplayValue());
									});
								}
							});
						}
						
						System.out.println("MapDependency Internal: " + mapDependency.getInternal());
						
						System.out.println("MapDependency Active: " + mapDependency.getActive());

						System.out.println("MapDependency ID: " + mapDependency.getId());
						
						System.out.println("MapDependency Active: " + mapDependency.getSource());

						System.out.println("MapDependency Category: " + mapDependency.getCategory());
						
						System.out.println("MapDependency Source: " + mapDependency.getSource());
					}
					
					Info info = responseWrapper.getInfo();
					
					// Check if info is not null
					if (info != null)
					{
						if (info.getPerPage() != null)
						{
							// Get the PerPage of the Info
							System.out.println("MapDependency Info PerPage: " + info.getPerPage().toString());
						}

						if (info.getCount() != null)
						{
							// Get the Count of the Info
							System.out.println("MapDependency Info Count: " + info.getCount().toString());
						}

						if (info.getPage() != null)
						{
							// Get the Page of the Info
							System.out.println("MapDependency Info Page: " + info.getPage().toString());
						}

						if (info.getMoreRecords() != null)
						{
							// Get the MoreRecords of the Info
							System.out.println("MapDependency Info MoreRecords: " + info.getMoreRecords().toString());
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

	public static void updateMapDependency(Long layoutId, String module, Long dependencyId) throws Exception
	{
		// Get instance of MapDependencysOperations Class
		FieldMapDependencyOperations fieldMapDependencyOperations = new FieldMapDependencyOperations(layoutId, module);

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper bodyWrapper = new BodyWrapper();

		List<MapDependency> mapDependencies = new ArrayList<MapDependency>();

		MapDependency mapdependency = new MapDependency();
		
		Parent parent = new Parent();
		
		parent.setAPIName("Lead_Status");
		
		parent.setId(36523972611l);
		
		mapdependency.setParent(parent);

		Child child = new Child();
		
		child.setAPIName("Lead_Status");
		
		child.setId(36523972611l);
		
		mapdependency.setChild(child);
		
		List<PickListMapping> pickListValues = new ArrayList<>();
		
		PickListMapping pickListValue = new PickListMapping();
		
		pickListValue.setDisplayValue("-None-");
		
		pickListValue.setId(36523973409l);
		
		pickListValue.setActualValue("-None-");
		
		List<PicklistMap> picklistMaps = new ArrayList<>();
		
		PicklistMap picklistMap = new PicklistMap();
		
		picklistMap.setId(36523973389l);
		
		picklistMap.setActualValue("Cold Call");
		
		picklistMap.setDisplayValue("Cold Call");
		
		picklistMaps.add(picklistMap);
		
		picklistMap = new PicklistMap();
		
		picklistMap.setId(36523973391l);
		
		picklistMap.setActualValue("-None-");
		
		picklistMap.setDisplayValue("-None-");
		
		picklistMaps.add(picklistMap);
		
		pickListValue.setMaps(picklistMaps);
		
		pickListValues.add(pickListValue);
		
		mapdependency.setPickListValues(pickListValues);
		
		mapDependencies.add(mapdependency);
	
		bodyWrapper.setMapDependency(mapDependencies);

		APIResponse<ActionHandler> response = fieldMapDependencyOperations.updateMapDependency(dependencyId, bodyWrapper);

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
					List<ActionResponse> actionResponses = actionWrapper.getMapDependency();

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

	public static void deleteMapDependency(Long layoutId, String module, Long dependencyId) throws Exception
	{
		FieldMapDependencyOperations fieldMapDependencyOperations = new FieldMapDependencyOperations(layoutId, module);

		// Call deleteMapDependency method that takes paramInstance as parameter
		APIResponse<ActionHandler> response = fieldMapDependencyOperations.deleteMapDependency(dependencyId);

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

					// Get the list of obtained MapDependency instances
					List<ActionResponse> actionResponses = actionWrapper.getMapDependency();

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

	public static void getMapDependency(Long layoutId, String module, Long dependencyId) throws Exception
	{
		FieldMapDependencyOperations fieldMapDependencyOperations = new FieldMapDependencyOperations(layoutId, module);

		APIResponse<ResponseHandler> response = fieldMapDependencyOperations.getMapDependency(dependencyId);

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

					List<MapDependency> mapDependencies = responseWrapper.getMapDependency();

					for (MapDependency mapDependency : mapDependencies)
					{
						Parent parent = mapDependency.getParent();
						
						if(parent != null)
						{
							System.out.println("MapDependency Map ID: " + parent.getId());
							
							System.out.println("MapDependency Map APIName: " + parent.getAPIName());
						}
						
						Child child = mapDependency.getChild();
						
						if(child != null)
						{
							System.out.println("MapDependency Child ID: " + child.getId());
							
							System.out.println("MapDependency Child APIName: " + child.getAPIName());
						}
						
						List<PickListMapping> pickListValues = mapDependency.getPickListValues();
						
						if(pickListValues != null)
						{
							pickListValues.forEach(pickListValue ->
							{
								System.out.println("MapDependency PickListValue ID: " + pickListValue.getId());
								
								System.out.println("MapDependency PickListValue ActualValue: " + pickListValue.getActualValue());
								
								System.out.println("MapDependency PickListValue DisplayValue: " + pickListValue.getDisplayValue());
								
								List<PicklistMap> picklistMaps = pickListValue.getMaps();
								
								if(picklistMaps != null)
								{
									picklistMaps.forEach(picklistMap ->
									{
										System.out.println("MapDependency PickListValue Map ID: " + picklistMap.getId());
										
										System.out.println("MapDependency PickListValue Map ActualValue: " + picklistMap.getActualValue());
										
										System.out.println("MapDependency PickListValue Map DisplayValue: " + picklistMap.getDisplayValue());
									});
								}
							});
						}
						
						System.out.println("MapDependency Internal: " + mapDependency.getInternal());
						
						System.out.println("MapDependency Active: " + mapDependency.getActive());

						System.out.println("MapDependency ID: " + mapDependency.getId());
						
						System.out.println("MapDependency Active: " + mapDependency.getSource());

						System.out.println("MapDependency Category: " + mapDependency.getCategory());
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
