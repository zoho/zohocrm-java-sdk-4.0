package notification;

import java.lang.reflect.Field;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.notification.APIException;
import com.zoho.crm.api.notification.ActionHandler;
import com.zoho.crm.api.notification.ActionResponse;
import com.zoho.crm.api.notification.ActionWrapper;
import com.zoho.crm.api.notification.BodyWrapper;
import com.zoho.crm.api.notification.NotificationOperations;
import com.zoho.crm.api.notification.SuccessResponse;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class UpdateNotification
{
	public static void updateNotification() throws Exception
	{
		NotificationOperations notificationOperations = new NotificationOperations();
		BodyWrapper bodyWrapper = new BodyWrapper();
		List<com.zoho.crm.api.notification.Notification> notificationList = new ArrayList<com.zoho.crm.api.notification.Notification>();
		com.zoho.crm.api.notification.Notification notification = new com.zoho.crm.api.notification.Notification();
		notification.setChannelId(106800211l);
		notification.setNotifyOnRelatedAction(false);
		List<String> events = new ArrayList<String>();
		events.add("Deals.all");
		events.add("Accounts.all");
		notification.setEvents(events);
		notification.setChannelExpiry(OffsetDateTime.now());
		notification.setToken("TOKEN_FOR_VERIFICATION_OF_1068002");
		notification.setNotifyUrl("https://www.zohoapis.com");
		notificationList.add(notification);
		bodyWrapper.setWatch(notificationList);
		APIResponse<ActionHandler> response = notificationOperations.updateNotification(bodyWrapper);
		if (response != null)
		{
			System.out.println("Status Code: " + response.getStatusCode());
			if (response.isExpected())
			{
				ActionHandler actionHandler = response.getObject();
				if (actionHandler instanceof ActionWrapper)
				{
					ActionWrapper actionWrapper = (ActionWrapper) actionHandler;
					List<ActionResponse> actionResponses = actionWrapper.getWatch();
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
								if (entry.getValue() instanceof List)
								{
									List<?> dataList = (List<?>) entry.getValue();
									if (dataList.size() > 0 && dataList.get(0) instanceof com.zoho.crm.api.notification.Notification)
									{
										@SuppressWarnings("unchecked")
										List<com.zoho.crm.api.notification.Notification> eventList = (List<com.zoho.crm.api.notification.Notification>) dataList;
										for (com.zoho.crm.api.notification.Notification event : eventList)
										{
											System.out.println("Notification ChannelExpiry: " + event.getChannelExpiry());
											System.out.println("Notification ResourceUri: " + event.getResourceUri());
											System.out.println("Notification ResourceId: " + event.getResourceId());
											System.out.println("Notification ResourceName: " + event.getResourceName());
											System.out.println("Notification ChannelId: " + event.getChannelId());
										}
									}
								}
								else
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
								System.out.println(entry.getKey() + ": " + entry.getValue());
							}
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
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
			updateNotification();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
