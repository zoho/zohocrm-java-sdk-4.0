package attachments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.attachments.APIException;
import com.zoho.crm.api.attachments.ActionHandler;
import com.zoho.crm.api.attachments.ActionResponse;
import com.zoho.crm.api.attachments.ActionWrapper;
import com.zoho.crm.api.attachments.AttachmentsOperations;
import com.zoho.crm.api.attachments.SuccessResponse;
import com.zoho.crm.api.attachments.AttachmentsOperations.DeleteAttachmentsParam;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class DeleteAttachments
{
	public static void deleteAttachments(String moduleAPIName, Long recordId, List<Long> attachmentIds) throws Exception
	{
		AttachmentsOperations attachmentOperations = new AttachmentsOperations(moduleAPIName, recordId);
		ParameterMap paramInstance = new ParameterMap();
		for (Long attachmentId : attachmentIds)
		{
			paramInstance.add(DeleteAttachmentsParam.IDS, attachmentId);
		}
		APIResponse<ActionHandler> response = attachmentOperations.deleteAttachments(paramInstance);
		if (response != null)
		{
			System.out.println("Status Code: " + response.getStatusCode());
			if (response.isExpected())
			{
				ActionHandler actionHandler = response.getObject();
				if (actionHandler instanceof ActionWrapper)
				{
					ActionWrapper actionWrapper = (ActionWrapper) actionHandler;
					List<ActionResponse> actionResponses = actionWrapper.getData();
					for (ActionResponse actionResponse : actionResponses)
					{
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
			String moduleAPIName = "Leads";
			Long recordId = 34770615177002l;
			List<Long> attachmentIds = new ArrayList<Long>(Arrays.asList(34770615979001l, 34770615968003l, 34770615961010l));
			deleteAttachments(moduleAPIName, recordId, attachmentIds);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
