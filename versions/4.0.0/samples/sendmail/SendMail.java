package sendmail;

import java.lang.reflect.Field;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.emailtemplates.EmailTemplate;
import com.zoho.crm.api.sendmail.APIException;
import com.zoho.crm.api.sendmail.ActionHandler;
import com.zoho.crm.api.sendmail.ActionResponse;
import com.zoho.crm.api.sendmail.ActionWrapper;
import com.zoho.crm.api.sendmail.Attachment;
import com.zoho.crm.api.sendmail.BodyWrapper;
import com.zoho.crm.api.sendmail.Data;
import com.zoho.crm.api.sendmail.From;
import com.zoho.crm.api.sendmail.SendMailOperations;
import com.zoho.crm.api.sendmail.SuccessResponse;
import com.zoho.crm.api.sendmail.To;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Choice;
import com.zoho.crm.api.util.Model;

public class SendMail
{
	public static void sendMail(Long recordID, String moduleAPIName) throws Exception
	{
		SendMailOperations sendMailOperations = new SendMailOperations(recordID, moduleAPIName);
		BodyWrapper bodyWrapper = new BodyWrapper();
		List<Data> mails = new ArrayList<Data>();
		for (int i = 1; i <= 1; i++)
		{
			Data mail = new Data();
			From userAddressFrom = new From();
			To userAddressTo = new To();
			To userAddressCc = new To();
			To userAddressBcc = new To();
			To userAddressReplyTo = new To();
			Attachment attachment = new Attachment();
			attachment.setId("2cceafa194d2181dd81864b4812b1f8b0b4fe0949a982de89fa75a");
			mail.setAttachments(new ArrayList<Attachment>(Arrays.asList(attachment)));
			EmailTemplate template = new EmailTemplate();
			template.setId(44024810L);
			mail.setTemplate(template);
			userAddressFrom.setUserName("username");
			userAddressFrom.setEmail("abc@gmail.com");
			userAddressTo.setUserName("username1");
			userAddressTo.setEmail("abc1@gmail.com");
			userAddressCc.setUserName("username2");
			userAddressCc.setEmail("abc2@zoho.com");
			userAddressBcc.setUserName("username3");
			userAddressBcc.setEmail("abc3@zoho.com");
			userAddressReplyTo.setUserName("username4");
			userAddressReplyTo.setEmail("abc4@zoho.com");
			mail.setFrom(userAddressFrom);
			ArrayList<To> userAddressesTo = new ArrayList<To>();
			userAddressesTo.add(userAddressTo);
			mail.setTo(userAddressesTo);
			ArrayList<To> userAddressesBcc = new ArrayList<To>();
			userAddressesBcc.add(userAddressBcc);
			mail.setBcc(userAddressesBcc);
			ArrayList<To> userAddressesCc = new ArrayList<To>();
			userAddressesCc.add(userAddressCc);
			mail.setCc(userAddressesCc);
			mail.setReplyTo(userAddressReplyTo);
			mail.setOrgEmail(false);
			mail.setInReplyTo("2cceafa194d037b6dd8186486f1eb0360aee76d802b6d376dea97e7");
			OffsetDateTime scheduledTIme = OffsetDateTime.of(2021, 07, 02, 11, 00, 01, 00, ZoneOffset.of("+05:30"));
			mail.setScheduledTime(scheduledTIme);
			mail.setSubject("Testing Send Mail API");
			mail.setContent("<h3><span style=\"background-color: rgb(254, 255, 102)\">Mail is of rich text format</span></h3><h3><span style=\"background-color: rgb(254, 255, 102)\"><img src=\"https://www.zohoapis.com/crm/viewInLineImage?fileContent=2cceafa194d037b63f2181dd818646fd5e5167a274098b625c35654a20ed2\"></span></h3><h3><span style=\"background-color: rgb(254, 255, 102)\">REGARDS,</span></h3><div><span style=\"background-color: rgb(254, 255, 102)\">AZ</span></div><div><span style=\"background-color: rgb(254, 255, 102)\">ADMIN</span></div> <div></div>");
			mail.setMailFormat(new Choice<String>("html"));
			mails.add(mail);
		}
		bodyWrapper.setData(mails);
		APIResponse<ActionHandler> response = sendMailOperations.sendMail(bodyWrapper);
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
			Long recordId = 440248774074L;
			String moduleAPIName = "Leads";
			sendMail(recordId, moduleAPIName);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
