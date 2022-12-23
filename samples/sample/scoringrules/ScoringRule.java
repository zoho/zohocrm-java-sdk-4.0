//$Id$
package com.zoho.crm.sample.scoringrules;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.scoringrules.APIException;
import com.zoho.crm.api.scoringrules.ActionHandler;
import com.zoho.crm.api.scoringrules.ActionResponse;
import com.zoho.crm.api.scoringrules.ActionWrapper;
import com.zoho.crm.api.scoringrules.BodyWrapper;
import com.zoho.crm.api.scoringrules.Criteria;
import com.zoho.crm.api.scoringrules.EntityResponseWrapper;
import com.zoho.crm.api.scoringrules.EntityScore;
import com.zoho.crm.api.scoringrules.FieldRule;
import com.zoho.crm.api.scoringrules.Info;
import com.zoho.crm.api.scoringrules.Layout;
import com.zoho.crm.api.scoringrules.LayoutRequestWrapper;
import com.zoho.crm.api.scoringrules.ResponseHandler;
import com.zoho.crm.api.scoringrules.ResponseWrapper;
import com.zoho.crm.api.scoringrules.RoleRequestWrapper;
import com.zoho.crm.api.scoringrules.ScoringRulesOperations;
import com.zoho.crm.api.scoringrules.Signal;
import com.zoho.crm.api.scoringrules.SignalRule;
import com.zoho.crm.api.scoringrules.SuccessResponse;
import com.zoho.crm.api.scoringrules.ScoringRulesOperations.DeleteScoringRulesParam;
import com.zoho.crm.api.scoringrules.ScoringRulesOperations.GetEntityScoreRecordsParam;
import com.zoho.crm.api.scoringrules.ScoringRulesOperations.GetScoringRulesParam;
import com.zoho.crm.api.users.MinifiedUser;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Choice;
import com.zoho.crm.api.util.Model;

public class ScoringRule
{
	public static void getScoringRules() throws Exception
	{
		// Get instance of ScoringRulesOperations Class
		ScoringRulesOperations scoringRulesOperations = new ScoringRulesOperations();

		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(GetScoringRulesParam.MODULE, "Leads");

		// Call getContactRoles method
		APIResponse<ResponseHandler> response = scoringRulesOperations.getScoringRules(paramInstance);

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

					// Get the list of obtained ScoringRule instances
					List<com.zoho.crm.api.scoringrules.ScoringRule> scoringRules = responseWrapper.getScoringRules();

					for (com.zoho.crm.api.scoringrules.ScoringRule scoringRule : scoringRules)
					{
						Layout layout = scoringRule.getLayout();

						if (layout != null)
						{
							System.out.println("ScoringRule Layout ID: " + layout.getId());

							System.out.println("ScoringRule Layout APIName: " + layout.getAPIName());
						}

						// Get the CreatedTime of each ScoringRule
						System.out.println("ScoringRule CreatedTime: " + scoringRule.getCreatedTime());

						// Get the ModifiedTime of each ScoringRule
						System.out.println("ScoringRule ModifiedTime: " + scoringRule.getModifiedTime());

						List<FieldRule> fieldRules = scoringRule.getFieldRules();

						for (FieldRule fieldRule : fieldRules)
						{
							System.out.println("ScoringRule FieldRule Score: " + fieldRule.getScore());

							// Get the Criteria instance of each CustomView
							Criteria criteria = fieldRule.getCriteria();

							// Check if criteria is not null
							if (criteria != null)
							{
								printCriteria(criteria);
							}

							System.out.println("ScoringRule FieldRule Id: " + fieldRule.getId());
						}

						com.zoho.crm.api.modules.Module module = scoringRule.getModule();

						if (module != null)
						{
							System.out.println("ScoringRule Module ID: " + module.getId());

							System.out.println("ScoringRule Module APIName: " + module.getAPIName());
						}

						// Get the Name each ScoringRule
						System.out.println("ScoringRule Name: " + scoringRule.getName());

						MinifiedUser modifiedBy = scoringRule.getModifiedBy();

						if (modifiedBy != null)
						{
							System.out.println("ScoringRule Modified By Name : " + modifiedBy.getName());

							System.out.println("ScoringRule Modified By id : " + modifiedBy.getId());
						}

						System.out.println("ScoringRule Active: " + scoringRule.getActive());

						System.out.println("ScoringRule Description: " + scoringRule.getDescription());

						System.out.println("ScoringRule Id: " + scoringRule.getId());

						List<SignalRule> signalRules = scoringRule.getSignalRules();

						if(signalRules != null)
						{
							for (SignalRule signalRule : signalRules)
							{
								System.out.println("ScoringRule SignalRule Score: " + signalRule.getScore());

								System.out.println("ScoringRule SignalRule Id: " + signalRule.getId());

								Signal signal = signalRule.getSignal();

								if (signal != null)
								{
									System.out.println("ScoringRule SignalRule Signal Namespace: " + signal.getNamespace());

									System.out.println("ScoringRule SignalRule Signal Id: " + signal.getId());
								}
							}
						}

						MinifiedUser createdBy = scoringRule.getCreatedBy();

						if (createdBy != null)
						{
							System.out.println("ScoringRule Created By Name : " + createdBy.getName());

							System.out.println("ScoringRule Created By id : " + createdBy.getId());
						}
					}

					// Get the Object obtained Info instance
					Info info = responseWrapper.getInfo();

					if (info != null)
					{
						if (info.getPerPage() != null)
						{
							// Get the PerPage of the Info
							System.out.println("Info PerPage: " + info.getPerPage().toString());
						}

						if (info.getCount() != null)
						{
							// Get the Count of the Info
							System.out.println("Info Count: " + info.getCount().toString());
						}

						if (info.getPage() != null)
						{
							// Get the Default of the Info
							System.out.println("Info Page: " + info.getPage());
						}

						if (info.getMoreRecords() != null)
						{
							// Get the Default of the Info
							System.out.println("Info MoreRecords: " + info.getMoreRecords().toString());
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
					System.out.println("Message: " + exception.getMessage().getValue());
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

	public static void getScoringRule(Long id) throws Exception
	{
		// Get instance of ScoringRulesOperations Class
		ScoringRulesOperations scoringRulesOperations = new ScoringRulesOperations();

		ParameterMap paramInstance = new ParameterMap();

		// Call getContactRoles method
		APIResponse<ResponseHandler> response = scoringRulesOperations.getScoringRule(id, paramInstance);

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

					// Get the list of obtained ScoringRule instances
					List<com.zoho.crm.api.scoringrules.ScoringRule> scoringRules = responseWrapper.getScoringRules();

					for (com.zoho.crm.api.scoringrules.ScoringRule scoringRule : scoringRules)
					{
						Layout layout = scoringRule.getLayout();

						if (layout != null)
						{
							System.out.println("ScoringRule Layout ID: " + layout.getId());

							System.out.println("ScoringRule Layout APIName: " + layout.getAPIName());
						}

						// Get the CreatedTime of each ScoringRule
						System.out.println("ScoringRule CreatedTime: " + scoringRule.getCreatedTime());

						// Get the ModifiedTime of each ScoringRule
						System.out.println("ScoringRule ModifiedTime: " + scoringRule.getModifiedTime());

						List<FieldRule> fieldRules = scoringRule.getFieldRules();

						for (FieldRule fieldRule : fieldRules)
						{
							System.out.println("ScoringRule FieldRule Score: " + fieldRule.getScore());

							// Get the Criteria instance of each CustomView
							Criteria criteria = fieldRule.getCriteria();

							// Check if criteria is not null
							if (criteria != null)
							{
								printCriteria(criteria);
							}

							System.out.println("ScoringRule FieldRule Id: " + fieldRule.getId());
						}

						com.zoho.crm.api.modules.Module module = scoringRule.getModule();

						if (module != null)
						{
							System.out.println("ScoringRule Module ID: " + module.getId());

							System.out.println("ScoringRule Module APIName: " + module.getAPIName());
						}

						// Get the Name each ScoringRule
						System.out.println("ScoringRule Name: " + scoringRule.getName());

						MinifiedUser modifiedBy = scoringRule.getModifiedBy();

						if (modifiedBy != null)
						{
							System.out.println("ScoringRule Modified By Name : " + modifiedBy.getName());

							System.out.println("ScoringRule Modified By id : " + modifiedBy.getId());
						}

						System.out.println("ScoringRule Active: " + scoringRule.getActive());

						System.out.println("ScoringRule Description: " + scoringRule.getDescription());

						System.out.println("ScoringRule Id: " + scoringRule.getId());

						List<SignalRule> signalRules = scoringRule.getSignalRules();

						if (signalRules != null)
						{
							for (SignalRule signalRule : signalRules)
							{
								System.out.println("ScoringRule SignalRule Score: " + signalRule.getScore());

								System.out.println("ScoringRule SignalRule Id: " + signalRule.getId());

								Signal signal = signalRule.getSignal();

								if (signal != null)
								{
									System.out.println("ScoringRule SignalRule Signal Namespace: " + signal.getNamespace());

									System.out.println("ScoringRule SignalRule Signal Id: " + signal.getId());
								}
							}
						}

						MinifiedUser createdBy = scoringRule.getCreatedBy();

						if (createdBy != null)
						{
							System.out.println("ScoringRule Created By Name : " + createdBy.getName());

							System.out.println("ScoringRule Created By id : " + createdBy.getId());
						}
					}

					// Get the Object obtained Info instance
					Info info = responseWrapper.getInfo();

					if (info != null)
					{
						if (info.getPerPage() != null)
						{
							// Get the PerPage of the Info
							System.out.println("Info PerPage: " + info.getPerPage().toString());
						}

						if (info.getCount() != null)
						{
							// Get the Count of the Info
							System.out.println("Info Count: " + info.getCount().toString());
						}

						if (info.getPage() != null)
						{
							// Get the Default of the Info
							System.out.println("Info Page: " + info.getPage());
						}

						if (info.getMoreRecords() != null)
						{
							// Get the Default of the Info
							System.out.println("Info MoreRecords: " + info.getMoreRecords().toString());
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
					System.out.println("Message: " + exception.getMessage().getValue());
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

	private static void printCriteria(Criteria criteria)
	{
		if (criteria.getComparator() != null)
		{
			// Get the Comparator of the Criteria
			System.out.println("CustomView Criteria Comparator: " + criteria.getComparator().getValue());
		}

		if (criteria.getField() != null)
		{
			// Get the name of the field
			System.out.println("CustomView Criteria field name: " + criteria.getField().getAPIName());
		}

		if (criteria.getValue() != null)
		{
			// Get the Value of the Criteria
			System.out.println("CustomView Criteria Value: " + criteria.getValue().toString());
		}

		// Get the List of Criteria instance of each Criteria
		List<Criteria> criteriaGroup = criteria.getGroup();

		if (criteriaGroup != null)
		{
			for (Criteria criteria1 : criteriaGroup)
			{
				printCriteria(criteria1);
			}
		}

		if (criteria.getGroupOperator() != null)
		{
			// Get the Group Operator of the Criteria
			System.out.println("CustomView Criteria Group Operator: " + criteria.getGroupOperator().getValue());
		}
	}

	public static void createScoringRules() throws Exception
	{
		// Get instance of ScoringRulesOperations Class
		ScoringRulesOperations scoringRulesOperations = new ScoringRulesOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper bodyWrapper = new BodyWrapper();

		// List of ScoringRule instances
		List<com.zoho.crm.api.scoringrules.ScoringRule> scoringRules = new ArrayList<com.zoho.crm.api.scoringrules.ScoringRule>();

		// Get instance of ScoringRule Class
		com.zoho.crm.api.scoringrules.ScoringRule scoringRule = new com.zoho.crm.api.scoringrules.ScoringRule();

		scoringRule.setName("Rule 10");

		scoringRule.setDescription("Rule for Module Leads");

		com.zoho.crm.api.modules.Module module = new com.zoho.crm.api.modules.Module();

		module.setAPIName("Leads");

		module.setId(34770612175l);

		scoringRule.setModule(module);

		Layout layout = new Layout();

		layout.setAPIName("Standard");

		layout.setId(347706191055l);

		scoringRule.setLayout(layout);

		scoringRule.setActive(false);

		List<FieldRule> fieldRules = new ArrayList<FieldRule>();

		FieldRule fieldRule = new FieldRule();

		fieldRule.setScore(10);

		Criteria criteria = new Criteria();

		criteria.setGroupOperator(new Choice<String>("or"));

		List<Criteria> group = new ArrayList<Criteria>();

		Criteria criteria1 = new Criteria();

		com.zoho.crm.api.fields.MinifiedFields field1 = new com.zoho.crm.api.fields.MinifiedFields();

		field1.setAPIName("Company");

		criteria1.setField(field1);

		criteria1.setComparator(new Choice<String>("equal"));

		criteria1.setValue("zoho");

		group.add(criteria1);

		Criteria criteria2 = new Criteria();

		field1 = new com.zoho.crm.api.fields.MinifiedFields();

		field1.setAPIName("Designation");

		criteria2.setField(field1);

		criteria2.setComparator(new Choice<String>("equal"));

		criteria2.setValue("review");

		group.add(criteria2);

		criteria.setGroup(group);

		fieldRule.setCriteria(criteria);

		fieldRules.add(fieldRule);

		scoringRule.setFieldRules(fieldRules);

		scoringRules.add(scoringRule);

		bodyWrapper.setScoringRules(scoringRules);

		// Call createScoringRules method that takes BodyWrapper instance as parameter
		APIResponse<ActionHandler> response = scoringRulesOperations.createScoringRules(bodyWrapper);

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
					List<ActionResponse> actionResponses = actionWrapper.getScoringRules();

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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
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

	public static void updateScoringRules(Long id) throws Exception
	{
		// Get instance of ScoringRulesOperations Class
		ScoringRulesOperations scoringRulesOperations = new ScoringRulesOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper bodyWrapper = new BodyWrapper();

		// List of ScoringRule instances
		List<com.zoho.crm.api.scoringrules.ScoringRule> scoringRules = new ArrayList<com.zoho.crm.api.scoringrules.ScoringRule>();

		// Get instance of ScoringRule Class
		com.zoho.crm.api.scoringrules.ScoringRule scoringRule = new com.zoho.crm.api.scoringrules.ScoringRule();

		scoringRule.setId(id);

		scoringRule.setName("Rule 10");

		scoringRule.setDescription("Rule for Module Leads");

		com.zoho.crm.api.modules.Module module = new com.zoho.crm.api.modules.Module();

		module.setAPIName("Leads");

		module.setId(34770612175l);

		scoringRule.setModule(module);

		Layout layout = new Layout();

		layout.setAPIName("Standard");

		layout.setId(347706191055l);

		scoringRule.setLayout(layout);

		scoringRule.setActive(false);

		List<FieldRule> fieldRules = new ArrayList<FieldRule>();

		FieldRule fieldRule = new FieldRule();

		fieldRule.setScore(10);

		fieldRule.setId(3477061149545l);

//		fieldRule.setDelete(null);

		Criteria criteria = new Criteria();

		criteria.setGroupOperator(new Choice<String>("or"));

		List<Criteria> group = new ArrayList<Criteria>();

		Criteria criteria1 = new Criteria();

		com.zoho.crm.api.fields.MinifiedFields field1 = new com.zoho.crm.api.fields.MinifiedFields();

		field1.setAPIName("Company");

		criteria1.setField(field1);

		criteria1.setComparator(new Choice<String>("equal"));

		criteria1.setValue("zoho");

		group.add(criteria1);

		Criteria criteria2 = new Criteria();

		field1 = new com.zoho.crm.api.fields.MinifiedFields();

		field1.setAPIName("Designation");

		criteria2.setField(field1);

		criteria2.setComparator(new Choice<String>("equal"));

		criteria2.setValue("review");

		group.add(criteria2);

		Criteria criteria3 = new Criteria();

		field1 = new com.zoho.crm.api.fields.MinifiedFields();

		field1.setAPIName("Last_Name");

		criteria3.setField(field1);

		criteria3.setComparator(new Choice<String>("equal"));

		criteria3.setValue("SDK");

		group.add(criteria3);

		criteria.setGroup(group);

		fieldRule.setCriteria(criteria);

		fieldRules.add(fieldRule);

		scoringRule.setFieldRules(fieldRules);

		scoringRules.add(scoringRule);

		bodyWrapper.setScoringRules(scoringRules);

		// Call createScoringRules method that takes BodyWrapper instance as parameter
		APIResponse<ActionHandler> response = scoringRulesOperations.updateScoringRules(bodyWrapper);

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
					List<ActionResponse> actionResponses = actionWrapper.getScoringRules();

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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
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

	public static void updateScoringRule(Long id) throws Exception
	{
		// Get instance of ScoringRulesOperations Class
		ScoringRulesOperations scoringRulesOperations = new ScoringRulesOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper bodyWrapper = new BodyWrapper();

		// List of ScoringRule instances
		List<com.zoho.crm.api.scoringrules.ScoringRule> scoringRules = new ArrayList<com.zoho.crm.api.scoringrules.ScoringRule>();

		// Get instance of ScoringRule Class
		com.zoho.crm.api.scoringrules.ScoringRule scoringRule = new com.zoho.crm.api.scoringrules.ScoringRule();

		scoringRule.setName("Rule 10");

		scoringRule.setDescription("Rule for Module Leads");

		com.zoho.crm.api.modules.Module module = new com.zoho.crm.api.modules.Module();

		module.setAPIName("Leads");

		module.setId(34770612175l);

		scoringRule.setModule(module);

		Layout layout = new Layout();

		layout.setAPIName("Standard");

		layout.setId(347706191055l);

		scoringRule.setLayout(layout);

		scoringRule.setActive(false);

		List<FieldRule> fieldRules = new ArrayList<FieldRule>();

		FieldRule fieldRule = new FieldRule();

		fieldRule.setScore(10);

//		fieldRule.setId(3477061149545l);

//		fieldRule.setDelete(null);

		Criteria criteria = new Criteria();

		criteria.setGroupOperator(new Choice<String>("or"));

		List<Criteria> group = new ArrayList<Criteria>();

		Criteria criteria1 = new Criteria();

		com.zoho.crm.api.fields.MinifiedFields field1 = new com.zoho.crm.api.fields.MinifiedFields();

		field1.setAPIName("Company");

		criteria1.setField(field1);

		criteria1.setComparator(new Choice<String>("equal"));

		criteria1.setValue("zoho");

		group.add(criteria1);

		Criteria criteria2 = new Criteria();

		field1 = new com.zoho.crm.api.fields.MinifiedFields();

		field1.setAPIName("Designation");

		criteria2.setField(field1);

		criteria2.setComparator(new Choice<String>("equal"));

		criteria2.setValue("review");

		group.add(criteria2);

		Criteria criteria3 = new Criteria();

		field1 = new com.zoho.crm.api.fields.MinifiedFields();

		field1.setAPIName("Last_Name");

		criteria3.setField(field1);

		criteria3.setComparator(new Choice<String>("equal"));

		criteria3.setValue("SDK");

		group.add(criteria3);

		criteria.setGroup(group);

		fieldRule.setCriteria(criteria);

		fieldRules.add(fieldRule);

		scoringRule.setFieldRules(fieldRules);

		scoringRules.add(scoringRule);

		bodyWrapper.setScoringRules(scoringRules);

		// Call updateScoringRule method that takes id and BodyWrapper instance as parameter
		APIResponse<ActionHandler> response = scoringRulesOperations.updateScoringRule(id, bodyWrapper);

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
					List<ActionResponse> actionResponses = actionWrapper.getScoringRules();

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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
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

	public static void deleteScoringRules() throws Exception
	{
		// Get instance of ScoringRulesOperations Class
		ScoringRulesOperations scoringRulesOperations = new ScoringRulesOperations();

		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(DeleteScoringRulesParam.IDS, "3477061147333");

		// Call deleteScoringRules method that takes paramInstance as parameter
		APIResponse<ActionHandler> response = scoringRulesOperations.deleteScoringRules(paramInstance);

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
					List<ActionResponse> actionResponses = actionWrapper.getScoringRules();

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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
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

	public static void deleteScoringRule(Long id) throws Exception
	{
		// Get instance of ScoringRulesOperations Class
		ScoringRulesOperations scoringRulesOperations = new ScoringRulesOperations();

		// Call deleteScoringRules method that takes paramInstance as parameter
		APIResponse<ActionHandler> response = scoringRulesOperations.deleteScoringRule(id);

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
					List<ActionResponse> actionResponses = actionWrapper.getScoringRules();

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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
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

	public static void scoringRuleExecutionUsingLayoutId(String moduleAPIName) throws Exception
	{
		// Get instance of ScoringRulesOperations Class
		ScoringRulesOperations scoringRulesOperations = new ScoringRulesOperations();

		// Get instance of LayoutRequestWrapper Class that will contain the request body
		LayoutRequestWrapper bodyWrapper = new LayoutRequestWrapper();

		Layout layout = new Layout();

		layout.setId(347706191055l);

		bodyWrapper.setLayout(layout);

		// Call scoringRuleExecutionUsingLayoutId method that takes moduleAPIName and LayoutRequestWrapper instance as parameter
		APIResponse<ActionHandler> response = scoringRulesOperations.scoringRuleExecutionUsingLayoutId(moduleAPIName, bodyWrapper);

		if (response != null)
		{
			// Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());

			// Check if expected response is received
			if (response.isExpected())
			{
				// Get object from response
				ActionHandler actionResponse = response.getObject();

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
					System.out.println("Message: " + exception.getMessage().getValue());
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

	public static void scoringRuleExecutionUsingRuleIds(String moduleAPIName) throws Exception
	{
		// Get instance of ScoringRulesOperations Class
		ScoringRulesOperations scoringRulesOperations = new ScoringRulesOperations();

		// Get instance of RoleRequestWrapper Class that will contain the request body
		RoleRequestWrapper bodyWrapper = new RoleRequestWrapper();

		List<String> scoringRules = new ArrayList<String>();

		scoringRules.add("347706114954046");

		scoringRules.add("3477061149131");

		bodyWrapper.setScoringRules(scoringRules);

		// Call scoringRuleExecutionUsingRuleIds method that takes moduleAPIName and RoleRequestWrapper instance as parameter
		APIResponse<ActionHandler> response = scoringRulesOperations.scoringRuleExecutionUsingRuleIds(moduleAPIName, bodyWrapper);
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
					List<ActionResponse> actionResponses = actionWrapper.getScoringRules();

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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
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

	public static void activateScoringRule(Long id) throws Exception
	{
		// Get instance of ScoringRulesOperations Class
		ScoringRulesOperations scoringRulesOperations = new ScoringRulesOperations();

		// Call activateScoringRule method that takes paramInstance as parameter
		APIResponse<ActionHandler> response = scoringRulesOperations.activateScoringRule(id);

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
					List<ActionResponse> actionResponses = actionWrapper.getScoringRules();

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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
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

	public static void deactivateScoringRule(Long id) throws Exception
	{
		// Get instance of ScoringRulesOperations Class
		ScoringRulesOperations scoringRulesOperations = new ScoringRulesOperations();

		// Call deactivateScoringRule method that takes paramInstance as parameter
		APIResponse<ActionHandler> response = scoringRulesOperations.deactivateScoringRule(id);

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
					List<ActionResponse> actionResponses = actionWrapper.getScoringRules();

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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
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

	public static void cloneScoringRule(Long id) throws Exception
	{
		// Get instance of ScoringRulesOperations Class
		ScoringRulesOperations scoringRulesOperations = new ScoringRulesOperations();

		// Call cloneScoringRule method that takes id as parameter
		APIResponse<ActionHandler> response = scoringRulesOperations.cloneScoringRule(id);

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
					List<ActionResponse> actionResponses = actionWrapper.getScoringRules();

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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
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

	public static void getEntityScoreRecords() throws Exception
	{
		// Get instance of ScoringRulesOperations Class
		ScoringRulesOperations scoringRulesOperations = new ScoringRulesOperations();

		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(GetEntityScoreRecordsParam.FIELDS, "Positive_Score");

		// Call getContactRoles method
		APIResponse<ResponseHandler> response = scoringRulesOperations.getEntityScoreRecords(paramInstance);

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

				if (responseHandler instanceof EntityResponseWrapper)
				{
					// Get the received EntityResponseWrapper instance
					EntityResponseWrapper responseWrapper = (EntityResponseWrapper) responseHandler;

					// Get the list of obtained ScoringRule instances
					List<EntityScore> entityScores = responseWrapper.getData();

					for (EntityScore entityScore : entityScores)
					{
						System.out.println("EntityScore Id: " + entityScore.getId());

						System.out.println("EntityScore Score: " + entityScore.getScore());
					}

					// Get the Object obtained Info instance
					Info info = responseWrapper.getInfo();

					if (info != null)
					{
						if (info.getPerPage() != null)
						{
							// Get the PerPage of the Info
							System.out.println("Info PerPage: " + info.getPerPage().toString());
						}

						if (info.getCount() != null)
						{
							// Get the Count of the Info
							System.out.println("Info Count: " + info.getCount().toString());
						}

						if (info.getPage() != null)
						{
							// Get the Default of the Info
							System.out.println("Info Page: " + info.getPage());
						}

						if (info.getMoreRecords() != null)
						{
							// Get the Default of the Info
							System.out.println("Info MoreRecords: " + info.getMoreRecords().toString());
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
					System.out.println("Message: " + exception.getMessage().getValue());
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

	public static void getEntityScoreRecord(Long recordId, String moduleAPIName) throws Exception
	{
		// Get instance of ScoringRulesOperations Class
		ScoringRulesOperations scoringRulesOperations = new ScoringRulesOperations();

		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(GetEntityScoreRecordsParam.FIELDS, "Positive_Score");

		// Call getContactRoles method
		APIResponse<ResponseHandler> response = scoringRulesOperations.getEntityScoreRecord(recordId, moduleAPIName, paramInstance);

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

					// Get the list of obtained ScoringRule instances
					List<com.zoho.crm.api.scoringrules.ScoringRule> scoringRules = responseWrapper.getScoringRules();

					for (com.zoho.crm.api.scoringrules.ScoringRule scoringRule : scoringRules)
					{
						Layout layout = scoringRule.getLayout();

						if (layout != null)
						{
							System.out.println("ScoringRule Layout ID: " + layout.getId());

							System.out.println("ScoringRule Layout APIName: " + layout.getAPIName());
						}

						// Get the CreatedTime of each ScoringRule
						System.out.println("ScoringRule CreatedTime: " + scoringRule.getCreatedTime());

						// Get the ModifiedTime of each ScoringRule
						System.out.println("ScoringRule ModifiedTime: " + scoringRule.getModifiedTime());

						List<FieldRule> fieldRules = scoringRule.getFieldRules();

						for (FieldRule fieldRule : fieldRules)
						{
							System.out.println("ScoringRule FieldRule Score: " + fieldRule.getScore());

							// Get the Criteria instance of each CustomView
							Criteria criteria = fieldRule.getCriteria();

							// Check if criteria is not null
							if (criteria != null)
							{
								printCriteria(criteria);
							}

							System.out.println("ScoringRule FieldRule Id: " + fieldRule.getId());
						}

						com.zoho.crm.api.modules.Module module = scoringRule.getModule();

						if (module != null)
						{
							System.out.println("ScoringRule Module ID: " + module.getId());

							System.out.println("ScoringRule Module APIName: " + module.getAPIName());
						}

						// Get the Name each ScoringRule
						System.out.println("ScoringRule Name: " + scoringRule.getName());

						MinifiedUser modifiedBy = scoringRule.getModifiedBy();

						if (modifiedBy != null)
						{
							System.out.println("ScoringRule Modified By Name : " + modifiedBy.getName());

							System.out.println("ScoringRule Modified By id : " + modifiedBy.getId());
						}

						System.out.println("ScoringRule Active: " + scoringRule.getActive());

						System.out.println("ScoringRule Description: " + scoringRule.getDescription());

						System.out.println("ScoringRule Id: " + scoringRule.getId());

						List<SignalRule> signalRules = scoringRule.getSignalRules();

						if (signalRules != null)
						{
							for (SignalRule signalRule : signalRules)
							{
								System.out.println("ScoringRule SignalRule Score: " + signalRule.getScore());

								System.out.println("ScoringRule SignalRule Id: " + signalRule.getId());

								Signal signal = signalRule.getSignal();

								if (signal != null)
								{
									System.out.println("ScoringRule SignalRule Signal Namespace: " + signal.getNamespace());

									System.out.println("ScoringRule SignalRule Signal Id: " + signal.getId());
								}
							}
						}

						MinifiedUser createdBy = scoringRule.getCreatedBy();

						if (createdBy != null)
						{
							System.out.println("ScoringRule Created By Name : " + createdBy.getName());

							System.out.println("ScoringRule Created By id : " + createdBy.getId());
						}
					}

					// Get the Object obtained Info instance
					Info info = responseWrapper.getInfo();

					if (info != null)
					{
						if (info.getPerPage() != null)
						{
							// Get the PerPage of the Info
							System.out.println("Info PerPage: " + info.getPerPage().toString());
						}

						if (info.getCount() != null)
						{
							// Get the Count of the Info
							System.out.println("Info Count: " + info.getCount().toString());
						}

						if (info.getPage() != null)
						{
							// Get the Default of the Info
							System.out.println("Info Page: " + info.getPage());
						}

						if (info.getMoreRecords() != null)
						{
							// Get the Default of the Info
							System.out.println("Info MoreRecords: " + info.getMoreRecords().toString());
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
					System.out.println("Message: " + exception.getMessage().getValue());
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

}
