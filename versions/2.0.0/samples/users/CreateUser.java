package users;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.users.APIException;
import com.zoho.crm.api.users.ActionHandler;
import com.zoho.crm.api.users.ActionResponse;
import com.zoho.crm.api.users.ActionWrapper;
import com.zoho.crm.api.users.BodyWrapper;
import com.zoho.crm.api.users.Profile;
import com.zoho.crm.api.users.Role;
import com.zoho.crm.api.users.SuccessResponse;
import com.zoho.crm.api.users.UsersOperations;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Choice;
import com.zoho.crm.api.util.Model;

public class CreateUser
{
	public static void createUser() throws Exception
	{
		UsersOperations usersOperations = new UsersOperations();
		BodyWrapper request = new BodyWrapper();
		List<com.zoho.crm.api.users.User> userList = new ArrayList<com.zoho.crm.api.users.User>();
		com.zoho.crm.api.users.User user1 = new com.zoho.crm.api.users.User();
		Role role = new Role();
		role.setId(3477061026008l);
		user1.setRole(role);
		user1.setFirstName("TestUser");
		user1.setEmail("javasdk3@zoho.com");
		Profile profile = new Profile();
		profile.setId(3477061026014l);
		user1.setProfile(profile);
		user1.setLastName("TestUser LastName");
		user1.setTimeZone(TimeZone.getDefault());
		user1.setNameFormatS(new Choice<String>("Salutation,First Name,Last Name"));
		user1.setSortOrderPreferenceS("First Name,Last Name");
		com.zoho.crm.api.shifthours.ShiftHour shifthour = new com.zoho.crm.api.shifthours.ShiftHour();
		shifthour.setId(320012372713L);
		user1.addKeyValue("Shift_Hour", shifthour);
		userList.add(user1);
		request.setUsers(userList);
		APIResponse<ActionHandler> response = usersOperations.createUsers(request);
		if (response != null)
		{
			System.out.println("Status Code: " + response.getStatusCode());
			if (response.isExpected())
			{
				ActionHandler actionHandler = response.getObject();
				if (actionHandler instanceof ActionWrapper)
				{
					ActionWrapper responseWrapper = (ActionWrapper) actionHandler;
					List<ActionResponse> actionResponses = responseWrapper.getUsers();
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
			createUser();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
