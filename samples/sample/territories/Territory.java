package com.zoho.crm.sample.territories;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.territories.APIException;
import com.zoho.crm.api.territories.BodyWrapper;
import com.zoho.crm.api.territories.Criteria;
import com.zoho.crm.api.territories.GroupCriteria;
import com.zoho.crm.api.territories.Info;
import com.zoho.crm.api.territories.Manager;
import com.zoho.crm.api.territories.ResponseHandler;
import com.zoho.crm.api.territories.SingleCriteria;
import com.zoho.crm.api.territories.TerritoriesOperations;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class Territory
{
	/**
	 * <h3>Get Territories</h3> This method is used to get the list of territories enabled for your organization and print the response.
	 * 
	 * @throws Exception
	 */
	public static void getTerritories() throws Exception
	{
		// Get instance of TerritoriesOperations Class
		TerritoriesOperations territoriesOperations = new TerritoriesOperations();

		ParameterMap paramInstance = new ParameterMap();

		// Call getTerritories method
		APIResponse<ResponseHandler> response = territoriesOperations.getTerritories(paramInstance);

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

					// Get the list of obtained Territory instances
					List<com.zoho.crm.api.territories.Territory> territoryList = responseWrapper.getTerritories();

					for (com.zoho.crm.api.territories.Territory territory : territoryList)
					{
						// Get the CreatedTime of each Territory
						System.out.println("Territory CreatedTime: " + territory.getCreatedTime());

						// Get the permission type of each Territory
						System.out.println("Territory permission type: " + territory.getPermissionType());

						// Get the ModifiedTime of each Territory
						System.out.println("Territory ModifiedTime: " + territory.getModifiedTime());

						// Get the manager User instance of each Territory
						Manager manager = territory.getManager();

						// Check if manager is not null
						if (manager != null)
						{
							// Get the Name of the Manager
							System.out.println("Territory Manager User-Name: " + manager.getName());

							// Get the ID of the Manager
							System.out.println("Territory Manager User-ID: " + manager.getId());
						}

						// Get the accountrule instance of each Territory
						Criteria accountRuleCriteria = territory.getAccountRuleCriteria();

						// Check if criteria is not null
						if (accountRuleCriteria != null)
						{
							printCriteria(accountRuleCriteria);
						}

						// Get the Name of each Territory
						System.out.println("Territory Name: " + territory.getName());

						// Get the modifiedBy User instance of each Territory
						com.zoho.crm.api.users.MinifiedUser modifiedBy = territory.getModifiedBy();

						// Check if modifiedBy is not null
						if (modifiedBy != null)
						{
							// Get the Name of the modifiedBy User
							System.out.println("Territory Modified By User-Name: " + modifiedBy.getName());

							// Get the ID of the modifiedBy User
							System.out.println("Territory Modified By User-ID: " + modifiedBy.getId());
						}

						// Get the deal rule instance of each Territory
						Criteria dealRuleCriteria1 = territory.getDealRuleCriteria();

						// Check if criteria is not null
						if (dealRuleCriteria1 != null)
						{
							printCriteria(dealRuleCriteria1);
						}

						// Get the Description of each Territory
						System.out.println("Territory Description: " + territory.getDescription());

						// Get the ID of each Territory
						System.out.println("Territory ID: " + territory.getId());

						// Get the createdBy User instance of each Territory
						com.zoho.crm.api.users.MinifiedUser createdBy = territory.getCreatedBy();

						// Check if createdBy is not null
						if (modifiedBy != null)
						{
							// Get the Name of the createdBy User
							System.out.println("Territory Created By User-Name: " + createdBy.getName());

							// Get the ID of the createdBy User
							System.out.println("Territory Created By User-ID: " + createdBy.getId());
						}

						// Get the reportingTo Territory instance of each Territory
						com.zoho.crm.api.territories.ReportingTo reportingTo = territory.getReportingTo();

						// Check if reportingTo is not null
						if (reportingTo != null)
						{
							// Get the Name of the createdBy User
							System.out.println("Territory reporting By Territory-Name: " + reportingTo.getName());

							// Get the ID of the createdBy User
							System.out.println("Territory reporting By Territory-ID: " + reportingTo.getId());
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
							System.out.println("Territory Info PerPage: " + info.getPerPage().toString());
						}

						if (info.getCount() != null)
						{
							// Get the Count of the Info
							System.out.println("Territory Info Count: " + info.getCount().toString());
						}

						if (info.getPage() != null)
						{
							// Get the Page of the Info
							System.out.println("Territory Info Page: " + info.getPage().toString());
						}

						if (info.getMoreRecords() != null)
						{
							// Get the MoreRecords of the Info
							System.out.println("Territory Info MoreRecords: " + info.getMoreRecords().toString());
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
	 * <h3>Get Territory</h3> This method is used to get the single territory and print the response.
	 * 
	 * @param territoryId - The ID of the Territory to be obtained
	 * @throws Exception
	 */
	public static void getTerritory(Long territoryId) throws Exception
	{
		// example
		// Long territoryId = 34770613051397l;

		// Get instance of TerritoriesOperations Class
		TerritoriesOperations territoriesOperations = new TerritoriesOperations();

		// Call getTerritory method that takes territoryId as parameter
		APIResponse<ResponseHandler> response = territoriesOperations.getTerritory(territoryId);

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

					// Get the list of obtained Territory instances
					List<com.zoho.crm.api.territories.Territory> territoryList = responseWrapper.getTerritories();

					for (com.zoho.crm.api.territories.Territory territory : territoryList)
					{
						// Get the CreatedTime of each Territory
						System.out.println("Territory CreatedTime: " + territory.getCreatedTime());

						// Get the ModifiedTime of each Territory
						System.out.println("Territory ModifiedTime: " + territory.getModifiedTime());

						// Get the manager User instance of each Territory
						Manager manager = territory.getManager();

						// Check if manager is not null
						if (manager != null)
						{
							// Get the Name of the Manager
							System.out.println("Territory Manager User-Name: " + manager.getName());

							// Get the ID of the Manager
							System.out.println("Territory Manager User-ID: " + manager.getId());
						}

						// Get the accountrule instance of each Territory
						Criteria accountRuleCriteria = territory.getAccountRuleCriteria();

						// Check if criteria is not null
						if (accountRuleCriteria != null)
						{
							printCriteria(accountRuleCriteria);
						}

						// Get the deal rule instance of each Territory
						Criteria dealRuleCriteria = territory.getDealRuleCriteria();

						// Check if criteria is not null
						if (dealRuleCriteria != null)
						{
							printCriteria(dealRuleCriteria);
						}

						// Get the Name of each Territory
						System.out.println("Territory Name: " + territory.getName());

						// Get the modifiedBy User instance of each Territory
						com.zoho.crm.api.users.MinifiedUser modifiedBy = territory.getModifiedBy();

						// Check if modifiedBy is not null
						if (modifiedBy != null)
						{
							// Get the Name of the modifiedBy User
							System.out.println("Territory Modified By User-Name: " + modifiedBy.getName());

							// Get the ID of the modifiedBy User
							System.out.println("Territory Modified By User-ID: " + modifiedBy.getId());
						}

						// Get the Description of each Territory
						System.out.println("Territory Description: " + territory.getDescription());

						// Get the ID of each Territory
						System.out.println("Territory ID: " + territory.getId());

						// Get the createdBy User instance of each Territory
						com.zoho.crm.api.users.MinifiedUser createdBy = territory.getCreatedBy();

						// Check if createdBy is not null
						if (modifiedBy != null)
						{
							// Get the Name of the createdBy User
							System.out.println("Territory Created By User-Name: " + createdBy.getName());

							// Get the ID of the createdBy User
							System.out.println("Territory Created By User-ID: " + createdBy.getId());
						}
						// Get the reportingTo Territory instance of each Territory
						com.zoho.crm.api.territories.ReportingTo reportingTo = territory.getReportingTo();

						// Check if reportingTo is not null
						if (reportingTo != null)
						{
							// Get the Name of the createdBy User
							System.out.println("Territory reporting By Territory-Name: " + reportingTo.getName());

							// Get the ID of the createdBy User
							System.out.println("Territory reporting By Territory-ID: " + reportingTo.getId());
						}
						// Get the permission type of each Territory
						System.out.println("Territory permission type: " + territory.getPermissionType());
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

	private static void printCriteria(Criteria criteria)
	{
		if (criteria instanceof SingleCriteria)
		{
			SingleCriteria singlecriteria = (SingleCriteria) criteria;

			if (singlecriteria.getComparator() != null)
			{
				// Get the Comparator of the Criteria
				System.out.println("CustomView Criteria Comparator: " + singlecriteria.getComparator());
			}

			if (singlecriteria.getField() != null)
			{
				// Get the name of the field
				System.out.println("CustomView Criteria field name: " + singlecriteria.getField().getAPIName());
			}

			if (singlecriteria.getValue() != null)
			{
				// Get the Value of the Criteria
				System.out.println("CustomView Criteria Value: " + singlecriteria.getValue().toString());
			}
		}
		else if (criteria instanceof GroupCriteria)
		{
			GroupCriteria groupcriteria = (GroupCriteria) criteria;

			// Get the List of Criteria instance of each Criteria
			List<Criteria> criteriaGroup = groupcriteria.getGroup();

			if (criteriaGroup != null)
			{
				for (Criteria criteria1 : criteriaGroup)
				{
					printCriteria(criteria1);
				}
			}

			if (groupcriteria.getGroupOperator() != null)
			{
				// Get the Group Operator of the Criteria
				System.out.println("CustomView Criteria Group Operator: " + groupcriteria.getGroupOperator());
			}
		}
	}
}
