package emailrelatedrecords;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.emailrelatedrecords.APIException;
import com.zoho.crm.api.emailrelatedrecords.Attachments;
import com.zoho.crm.api.emailrelatedrecords.Email;
import com.zoho.crm.api.emailrelatedrecords.EmailRelatedRecordsOperations;
import com.zoho.crm.api.emailrelatedrecords.Info;
import com.zoho.crm.api.emailrelatedrecords.LinkedRecord;
import com.zoho.crm.api.emailrelatedrecords.ResponseHandler;
import com.zoho.crm.api.emailrelatedrecords.ResponseWrapper;
import com.zoho.crm.api.emailrelatedrecords.Status;
import com.zoho.crm.api.emailrelatedrecords.UserDetails;
import com.zoho.crm.api.users.MinifiedUser;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class GetRelatedEmail
{
	public static void getRelatedEmail(String moduleAPIName, Long id, String messageId) throws Exception
	{
		EmailRelatedRecordsOperations emailTemplatesOperations = new EmailRelatedRecordsOperations(id, moduleAPIName, null, null, null);
		APIResponse<ResponseHandler> response = emailTemplatesOperations.getRelatedEmail(messageId);
		if (response != null)
		{
			System.out.println("Status Code: " + response.getStatusCode());
			if (Arrays.asList(204, 304).contains(response.getStatusCode()))
			{
				System.out.println(response.getStatusCode() == 204 ? "No Content" : "Not Modified");
				return;
			}
			if (response.isExpected())
			{
				ResponseHandler responseHandler = response.getObject();
				if (responseHandler instanceof ResponseWrapper)
				{
					ResponseWrapper responseWrapper = (ResponseWrapper) responseHandler;
					List<Email> emailTemplates = responseWrapper.getEmails();
					for (Email emailTemplate : emailTemplates)
					{
						List<UserDetails> userDetails = emailTemplate.getCc();
						if (userDetails != null)
						{
							for (UserDetails userDetail : userDetails)
							{
								System.out.println("EmailRelatedRecords User Email: " + userDetail.getEmail());

								System.out.println("EmailRelatedRecords User Name: " + userDetail.getUserName());
							}
						}
						System.out.println("EmailRelatedRecords Summary: " + emailTemplate.getSummary());
						MinifiedUser owner = emailTemplate.getOwner();

						if (owner != null)
						{
							System.out.println("EmailRelatedRecords User ID: " + owner.getId());
							System.out.println("EmailRelatedRecords User Name: " + owner.getName());
						}
						System.out.println("EmailRelatedRecords Read: " + emailTemplate.getRead());

						System.out.println("EmailRelatedRecords Sent: " + emailTemplate.getSent());
						System.out.println("EmailRelatedRecords Subject: " + emailTemplate.getSubject());
						System.out.println("EmailRelatedRecords Intent: " + emailTemplate.getIntent());
						System.out.println("EmailRelatedRecords Content: "+ emailTemplate.getContent());

						System.out.println("EmailRelatedRecords SentimentInfo: " + emailTemplate.getSentimentInfo());

						System.out.println("EmailRelatedRecords MessageId: " + emailTemplate.getMessageId());

						System.out.println("EmailRelatedRecords MessageId: " + emailTemplate.getSource());

						LinkedRecord linkedRecord = emailTemplate.getLinkedRecord();
						if (linkedRecord != null)
						{
							System.out.println("EmailRelatedRecords LinkedRecord id : " + linkedRecord.getId());

							com.zoho.crm.api.emailrelatedrecords.Module module = linkedRecord.getModule();

							if (module != null)
							{
								System.out.println("EmailRelatedRecords LinkedRecord Module APIName : " + module.getAPIName());

								System.out.println("EmailRelatedRecords LinkedRecord Module Id : " + module.getId());
							}
						}
						List<Attachments> attachments = emailTemplate.getAttachments();
						if (attachments != null) 
						{
							for (Attachments attachment : attachments)
							{
								System.out.println("EmailRelatedRecords Attachmnet Size :"+ attachment.getSize());
								System.out.println("EmailRelatedRecords Attachmnet Name :"+ attachment.getName());
								System.out.println("EmailRelatedRecords Attachmnet Id :"+ attachment.getId());
							}
						}
						System.out.println("EmailRelatedRecords Emotion: " + emailTemplate.getEmotion());
						UserDetails from = emailTemplate.getFrom();
						if (from != null)
						{
							System.out.println("EmailRelatedRecords From User Email: " + from.getEmail());

							System.out.println("EmailRelatedRecords From User Name: " + from.getUserName());
						}

						List<UserDetails> toUserDetails = emailTemplate.getTo();
						if (toUserDetails != null)
						{
							for (UserDetails userDetail : toUserDetails)
							{
								System.out.println("EmailRelatedRecords User Email: " + userDetail.getEmail());

								System.out.println("EmailRelatedRecords User Name: " + userDetail.getUserName());
							}
						}

						System.out.println("EmailRelatedRecords Time: " + emailTemplate.getTime());

						List<Status> status = emailTemplate.getStatus();
						if (status != null)
						{
							for (Status status1 : status)
							{
								System.out.println("EmailRelatedRecords Status Type: " + status1.getType());

								System.out.println("EmailRelatedRecords Status BouncedTime: " + status1.getBouncedTime());

								System.out.println("EmailRelatedRecords Status BouncedReason: " + status1.getBouncedReason());
							}
						}
					}
				}
				else if (responseHandler instanceof APIException)
				{
					APIException exception = (APIException) responseHandler;
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
				java.lang.reflect.Field[] Fields = clas.getDeclaredFields();
				for (java.lang.reflect.Field Field : Fields)
				{
					System.out.println(Field.getName() + ":" + Field.get(responseObject));
				}
			}
		}
	}

	public static void main(String[] args)
	{
		try
		{
			Environment environment = USDataCenter.PRODUCTION;
			Token token = new OAuthToken.Builder().clientID("Client_Id").clientSecret("Client_Secret").refreshToken("Refresh_Token").build();
			new Initializer.Builder().environment(environment).token(token).initialize();
			String moduleAPIName = "Leads";
			Long Id = 4402481103140L;
			String messageId = "c6085f1f66ec2089a19d1a0149bbe5cff03fe";
			getRelatedEmail(moduleAPIName, Id, messageId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

