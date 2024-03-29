package blueprint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.blueprints.APIException;
import com.zoho.crm.api.blueprints.ActionHandler;
import com.zoho.crm.api.blueprints.BlueprintsOperations;
import com.zoho.crm.api.blueprints.BodyWrapper;
import com.zoho.crm.api.blueprints.Escalation;
import com.zoho.crm.api.blueprints.NextTransition;
import com.zoho.crm.api.blueprints.ProcessInfo;
import com.zoho.crm.api.blueprints.SuccessResponse;
import com.zoho.crm.api.blueprints.Transition;
import com.zoho.crm.api.blueprints.ValidationError;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.record.Record;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class UpdateBlueprint
{
	public static void updateBlueprint(String moduleAPIName, Long recordId, Long transitionId) throws Exception
	{
		BlueprintsOperations bluePrintOperations = new BlueprintsOperations(recordId, moduleAPIName);
		BodyWrapper bodyWrapper = new BodyWrapper();
		List<com.zoho.crm.api.blueprints.Blueprint> bluePrintList = new ArrayList<com.zoho.crm.api.blueprints.Blueprint>();
		com.zoho.crm.api.blueprints.Blueprint bluePrint = new com.zoho.crm.api.blueprints.Blueprint();
		bluePrint.setTransitionId(transitionId);
		Record data = new Record();
		HashMap<String, Object> lookup = new HashMap<String, Object>();
		lookup.put("Phone", "8940372937");
		lookup.put("id", "8940372937");
		data.addKeyValue("Phone", "8940372937");
		data.addKeyValue("Notes", "Updated via blueprint");
		HashMap<String, Object> attachments = new HashMap<String, Object>();
		ArrayList<String> fileIds = new ArrayList<String>();
		fileIds.add("blojtd2d13b5f044e4041a3315793fb21ef");
		attachments.put("file_id", fileIds);
		attachments.put("link_url", "ww.zoho.com");
//		data.addKeyValue("Attachments", attachments);
		ArrayList<HashMap<String, Object>> listings = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> interested_listings = new HashMap<String, Object>();
		interested_listings.put("id", 36523971978005L);
		listings.add(interested_listings);
		interested_listings.put("id", 36523971978016L);
		listings.add(interested_listings);
//		data.addKeyValue("Listings", listings);
		ArrayList<HashMap<String, Object>> multiuser = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> multi_user = new HashMap<String, Object>();
		multi_user.put("name", "givenname");
		multi_user.put("id", 3652397186017L);
		multiuser.add(multi_user);
//		data.addKeyValue("Multi_user", multiuser);
		ProcessInfo processinfo = new ProcessInfo();
		processinfo.setAPIName("apiname");
		processinfo.setColumnName("columnname");
		processinfo.setContinuous(false);
		processinfo.setFieldId("3212112");
		processinfo.setFieldLabel("fieldlabel");
		processinfo.setFieldName("field_name");
		processinfo.setId(329993200132223L);
		processinfo.setIsContinuous(false);
		processinfo.setName("name");
		Escalation escalation = new Escalation();
		escalation.setDays(1);
		escalation.setStatus("overdue");
		processinfo.setEscalation(escalation);
//		bluePrint.setProcessInfo(processinfo);
		List<Transition> transitions = new ArrayList<Transition>();
		Transition transition = new Transition();
		transition.setType("manual");
		List<NextTransition> nextTransitions = new ArrayList<NextTransition>();
		NextTransition nexttransition = new NextTransition();
		nexttransition.setId(36523973921103L);
		nexttransition.setName("call later");
		nexttransition.setType("manual");
		nexttransition.setCriteriaMatched(false);
		nextTransitions.add(nexttransition);
		transition.setNextTransitions(nextTransitions);
		transitions.add(transition);
//		bluePrint.setTransitions(transitions);
		ArrayList<HashMap<String, Object>> checkLists = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> checkListItem = new HashMap<String, Object>();
		checkListItem.put("list 1", true);
		checkLists.add(checkListItem);
		checkListItem = new HashMap<String, Object>();
		checkListItem.put("list 2", true);
		checkLists.add(checkListItem);
		checkListItem = new HashMap<String, Object>();
		checkListItem.put("list 3", true);
		checkLists.add(checkListItem);
//		data.addKeyValue("CheckLists", checkLists);
		HashMap<String, Object> tasks = new HashMap<String, Object>();
		tasks.put("Subject", "Event");
//		data.addKeyValue("Tasks", tasks);
		bluePrint.setData(data);
		bluePrintList.add(bluePrint);
		bodyWrapper.setBlueprint(bluePrintList);
		APIResponse<ActionHandler> response = bluePrintOperations.updateBlueprint(bodyWrapper);
		if (response != null)
		{
			System.out.println("Status Code: " + response.getStatusCode());
			if (response.isExpected())
			{
				ActionHandler actionResponse = response.getObject();
				if (actionResponse instanceof SuccessResponse)
				{
					SuccessResponse successResponse = (SuccessResponse) actionResponse;
					System.out.println("Status: " + successResponse.getStatus().getValue());
					System.out.println("Code: " + successResponse.getCode().getValue());
					System.out.println("Details: ");
					if (successResponse.getDetails() != null)
					{
						for (Map.Entry<String, Object> entry : successResponse.getDetails().entrySet())
						{
							System.out.println(entry.getKey() + ": " + entry.getValue());
						}
					}
					System.out.println("Message: " + successResponse.getMessage().getValue());
				}
				else if (actionResponse instanceof APIException)
				{
					APIException exception = (APIException) actionResponse;
					System.out.println("Status: " + exception.getStatus().getValue());
					System.out.println("Code: " + exception.getCode().getValue());
					System.out.println("Details: ");
					for (Map.Entry<String, Object> entry : exception.getDetails().entrySet())
					{
						System.out.println(entry.getKey() + ": ");
						if (entry.getValue() instanceof List)
						{
							@SuppressWarnings("unchecked")
							List<ValidationError> validationError = (List<ValidationError>) entry.getValue();
							for (ValidationError error : validationError)
							{
								System.out.println("Field APIName " + error.getAPIName() + " : " + error.getMessage());
								System.out.println("Field index " + error.getIndex());
								System.out.println("Field info message " + error.getInfoMessage());
								System.out.println("Field parent api name " + error.getParentAPIName());
							}
						}
						else
						{
							System.out.println(entry.getValue().toString());
						}
					}
					System.out.println("Message: " + exception.getMessage());
				}
			}
			else
			{
				Model responseObject = response.getModel();
				Class<? extends Model> clas = responseObject.getClass();
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();
				for (java.lang.reflect.Field field : fields)
				{
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	public static void main(String[] args)
	{
		try
		{
			Environment environment = USDataCenter.PRODUCTION;
			Token token = new OAuthToken.Builder().clientID("Client_Id").clientSecret("Client_Secret").refreshToken("Refresh_Token").redirectURL("Redirect_URL").build();
			new Initializer.Builder().environment(environment).token(token).initialize();
			String moduleAPIName = "Leads";
			Long recordId = 34770614381002l;
			Long transitionId = 3477061173096l;
			updateBlueprint(moduleAPIName, recordId, transitionId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
