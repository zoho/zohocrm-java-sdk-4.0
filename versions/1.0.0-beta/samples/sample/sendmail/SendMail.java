package com.zoho.crm.sample.sendmail;

import java.lang.reflect.Field;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zoho.crm.api.attachments.Attachment;
import com.zoho.crm.api.emailtemplates.EmailTemplate;
import com.zoho.crm.api.sendmail.APIException;
import com.zoho.crm.api.sendmail.ActionHandler;
import com.zoho.crm.api.sendmail.ActionResponse;
import com.zoho.crm.api.sendmail.ActionWrapper;
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
	/*
	 * <h3> send Mail</h3> This method is used to send Mail
	 * 
	 * @param recordID The id of the record
	 * 
	 * @param moduleAPIName The api name of the module
	 * 
	 * @throws Exception
	 */
	public static void sendMail(Long recordID, String moduleAPIName) throws Exception
	{
		// Get instance of SendMailOperations Class
		SendMailOperations sendMailOperations = new SendMailOperations(recordID, moduleAPIName);

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper bodyWrapper = new BodyWrapper();

		// List of SendMail instances
		List<Data> mails = new ArrayList<Data>();

		for (int i = 1; i <= 1; i++)
		{
			// Get instance of SendMail Class
			Data mail = new Data();
			From userAddressFrom = new From();
			To userAddressTo = new To();
			To userAddressCc = new To();
			To userAddressBcc = new To();
			To userAddressReplyTo = new To();
			Attachment attachment = new Attachment();
			attachment.setId("2cceafa194d037b63f2181dd81864b4812b1f8b0b4fe0949a982de89fa75a");
			EmailTemplate template = new EmailTemplate();
			template.setId(365239724971L);
			userAddressFrom.setUserName("patricia Boyle");
			userAddressFrom.setEmail("patricia.a@zoho.com");
			userAddressTo.setUserName("Jason Smith");
			userAddressTo.setEmail("patricia.a@zoho.com");
			userAddressCc.setUserName("Jasweon Smith");
			userAddressCc.setEmail("patricia.a@zoho.com");
			userAddressBcc.setUserName("Jassdon Smith");
			userAddressBcc.setEmail("patricia.a@zoho.com");
			userAddressReplyTo.setUserName("Jassdon Smith");
			userAddressReplyTo.setEmail("patricia.a@zoho.com");

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
			mail.setInReplyTo("2cceafa194d037b63f2181dd8186486f1eb0360aee76d802b6d376dea97e7");
			OffsetDateTime scheduledTIme = OffsetDateTime.of(2021, 07, 02, 11, 00, 01, 00, ZoneOffset.of("+05:30"));
			mail.setScheduledTime(scheduledTIme);
			mail.setSubject("Testing Send Mail API");
			mail.setContent("<h3><span style=\"background-color: rgb(254, 255, 102)\">Mail is of rich text format</span></h3><h3><span style=\"background-color: rgb(254, 255, 102)\"><img src=\"https://www.zohoapis.com/crm/viewInLineImage?fileContent=2cceafa194d037b63f2181dd818646fd5e5167a274098b625c35654a20ed2\"></span></h3><h3><span style=\"background-color: rgb(254, 255, 102)\">REGARDS,</span></h3><div><span style=\"background-color: rgb(254, 255, 102)\">AZ</span></div><div><span style=\"background-color: rgb(254, 255, 102)\">ADMIN</span></div> <div></div>");
			mail.setMailFormat(new Choice<String>("html"));
			mails.add(mail);
		}

		// Set the list to sendMail in BodyWrapper instance
		bodyWrapper.setData(mails);

		// Call createSendMail method that takes BodyWrapper instance as parameter
		APIResponse<ActionHandler> response = sendMailOperations.sendMail(bodyWrapper);

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
					List<ActionResponse> actionResponses = actionWrapper.getData();

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
}
