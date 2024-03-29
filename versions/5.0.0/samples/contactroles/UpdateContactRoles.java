package contactroles;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.contactroles.APIException;
import com.zoho.crm.api.contactroles.ActionHandler;
import com.zoho.crm.api.contactroles.ActionResponse;
import com.zoho.crm.api.contactroles.ActionWrapper;
import com.zoho.crm.api.contactroles.BodyWrapper;
import com.zoho.crm.api.contactroles.ContactRole;
import com.zoho.crm.api.contactroles.ContactRolesOperations;
import com.zoho.crm.api.contactroles.SuccessResponse;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class UpdateContactRoles
{
	public static void updateContactRoles() throws Exception
	{
		ContactRolesOperations contactRolesOperations = new ContactRolesOperations();
		BodyWrapper bodyWrapper = new BodyWrapper();
		List<ContactRole> contactRolesList = new ArrayList<ContactRole>();
		ContactRole cr1 = new ContactRole();
		cr1.setId(347706114686005l);
		cr1.setName("Editsded1");
		contactRolesList.add(cr1);
		ContactRole cr2 = new ContactRole();
		cr2.setId(347706114686003l);
		cr2.setSequenceNumber(1);
		cr2.setName("Edisdasted1");
		contactRolesList.add(cr2);
		bodyWrapper.setContactRoles(contactRolesList);
		APIResponse<ActionHandler> response = contactRolesOperations.updateRoles(bodyWrapper);
		if (response != null)
		{
			System.out.println("Status Code: " + response.getStatusCode());
			if (response.isExpected())
			{
				ActionHandler actionHandler = response.getObject();
				if (actionHandler instanceof ActionWrapper)
				{
					ActionWrapper actionWrapper = (ActionWrapper) actionHandler;
					List<ActionResponse> actionResponses = actionWrapper.getContactRoles();
					for (ActionResponse actionResponse : actionResponses)
					{
						if (actionResponse instanceof SuccessResponse)
						{
							SuccessResponse successResponse = (SuccessResponse) actionResponse;
							System.out.println("Status: " + successResponse.getStatus().getValue());
							System.out.println("Code: " + successResponse.getCode().getValue());
							System.out.println("Details: ");
							for (Map.Entry<String, Object> entry : successResponse.getDetails().entrySet())
							{
								System.out.println(entry.getKey() + ": " + entry.getValue());
							}
							System.out.println("Message: " + successResponse.getMessage());
						}
						else if (actionResponse instanceof APIException)
						{
							APIException exception = (APIException) actionResponse;
							System.out.println("Status: " + exception.getStatus().getValue());
							System.out.println("Code: " + exception.getCode().getValue());
							System.out.println("Details: ");
							for (Map.Entry<String, Object> entry : exception.getDetails().entrySet())
							{
								System.out.println(entry.getKey() + ": " + entry.getValue());
							}
							System.out.println("Message: " + exception.getMessage());
						}
					}
				}
				else if (actionHandler instanceof APIException)
				{
					APIException exception = (APIException) actionHandler;
					System.out.println("Status: " + exception.getStatus().getValue());
					System.out.println("Code: " + exception.getCode().getValue());
					System.out.println("Details: ");
					for (Map.Entry<String, Object> entry : exception.getDetails().entrySet())
					{
						System.out.println(entry.getKey() + ": " + entry.getValue());
					}
					System.out.println("Message: " + exception.getMessage());
				}
			}
			else
			{
				Model responseObject = response.getModel();
				Class<? extends Model> clas = responseObject.getClass();
				Field[] fields = clas.getDeclaredFields();
				for (Field field : fields)
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
			updateContactRoles();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
