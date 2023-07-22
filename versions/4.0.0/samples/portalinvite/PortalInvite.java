package portalinvite;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.portalinvite.APIException;
import com.zoho.crm.api.portalinvite.ActionHandler;
import com.zoho.crm.api.portalinvite.ActionResponse;
import com.zoho.crm.api.portalinvite.ActionWrapper;
import com.zoho.crm.api.portalinvite.PortalInviteOperations;
import com.zoho.crm.api.portalinvite.PortalInviteOperations.InviteUsersParam;
import com.zoho.crm.api.portalinvite.Success;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class PortalInvite
{
	public static void portalInvite(Long record, String module, Long userTypeId, String type) throws Exception
	{
		PortalInviteOperations portalinviteoperations = new PortalInviteOperations();

		ParameterMap paramInstance = new ParameterMap();
		paramInstance.add(InviteUsersParam.USER_TYPE_ID, userTypeId);
		paramInstance.add(InviteUsersParam.TYPE, type);

		APIResponse<ActionHandler> response = portalinviteoperations.inviteUsers(record, module, paramInstance);
		if (response != null)
		{
			System.out.println("Status Code: " + response.getStatusCode());
			if (response.isExpected())
			{
				ActionHandler actionHandler = response.getObject();
				if (actionHandler instanceof ActionWrapper)
				{
					ActionWrapper actionWrapper = (ActionWrapper) actionHandler;
					List<ActionResponse> actionResponses = actionWrapper.getPortalInvite();
					for (ActionResponse actionResponse : actionResponses)
					{
						if (actionResponse instanceof Success)
						{
							Success successresponse = (Success) actionResponse;
							System.out.println("Status: " + successresponse.getStatus().getValue());
							System.out.println("Code: " + successresponse.getCode().getValue());
							System.out.println("Details: ");
							for (Map.Entry<String, Object> entry : successresponse.getDetails().entrySet())
							{
								System.out.println(entry.getKey() + ": " + entry.getValue());
							}
							System.out.println("Message: " + successresponse.getMessage());
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
			Long record = 3123232243321L;
			String module = "leads";
			Long userTypeId = 3210931303912L;
			String type = "invite";
			portalInvite(record, module, userTypeId, type);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
