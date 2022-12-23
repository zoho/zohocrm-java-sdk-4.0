package com.zoho.crm.sample.emailrelatedrecords;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.crm.api.emailrelatedrecords.APIException;
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

public class EmailRelatedRecords
{
	/*
	 * <h3> Get EmailTemplates</h3> This method is used to get Email Templates
	 * 
	 * @throws Exception
	 */
	public static void getRelatedEmail() throws Exception
	{
		// Get instance of EmailTemplatesOperations Class
		String moduleAPIName = "Leads";
		
		EmailRelatedRecordsOperations emailTemplatesOperations = new EmailRelatedRecordsOperations(347706116634119l, moduleAPIName, null, null);

		// Call getEmailTemplates method
		APIResponse<ResponseHandler> response = emailTemplatesOperations.getRelatedEmail();
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

					// Get the list of obtained EmailTemplate instances
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
						
						System.out.println("EmailRelatedRecords SentimentInfo: " + emailTemplate.getSentimentInfo());
						
						System.out.println("EmailRelatedRecords MessageId: " + emailTemplate.getMessageId());
						
						System.out.println("EmailRelatedRecords MessageId: " + emailTemplate.getSource());
						
						LinkedRecord linkedRecord = emailTemplate.getLinkedRecord();

						if (linkedRecord != null)
						{
							System.out.println("EmailRelatedRecords LinkedRecord id : " + linkedRecord.getId());
							
							com.zoho.crm.api.emailrelatedrecords.Module module = linkedRecord.getModule();
							
							if(module != null)
							{
								System.out.println("EmailRelatedRecords LinkedRecord Module APIName : " + module.getAPIName());
								
								System.out.println("EmailRelatedRecords LinkedRecord Module Id : " + module.getId());
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
								
								System.out.println("EmailRelatedRecords Status Name: " + status1.getBouncedTime());
								
								System.out.println("EmailRelatedRecords Status Name: " + status1.getBouncedReason());
							}
						}
					}

					// Get the Object obtained Info instance
					Info info = responseWrapper.getInfo();

					// Check if info is not null
					if (info != null)
					{
						if (info.getCount() != null)
						{
							// Get the Count of the Info
							System.out.println("Record Info Count: " + info.getCount().toString());
						}

						if (info.getNextIndex() != null)
						{
							System.out.println("Record Info NextIndex: " + info.getNextIndex().toString());
						}
						
						if (info.getPrevIndex() != null)
						{
							System.out.println("Record Info PrevIndex: " + info.getPrevIndex().toString());
						}
						
						if (info.getPerPage() != null)
						{
							// Get the PerPage of the Info
							System.out.println("Record Info PerPage: " + info.getPerPage().toString());
						}

						if (info.getMoreRecords() != null)
						{
							// Get the MoreRecords of the Info
							System.out.println("Record Info MoreRecords: " + info.getMoreRecords().toString());
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
					System.out.println("Message: " + exception.getMessage());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared EmailTemplates of the response class
				java.lang.reflect.Field[] Fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field Field : Fields)
				{
					// Get each value
					System.out.println(Field.getName() + ":" + Field.get(responseObject));
				}
			}
		}
	}

}
