package com.zoho.crm.sample.record;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zoho.crm.api.HeaderMap;
import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.attachments.Attachment;
import com.zoho.crm.api.attachments.ParentId;
import com.zoho.crm.api.layouts.Layout;
import com.zoho.crm.api.record.APIException;
import com.zoho.crm.api.record.ActionHandler;
import com.zoho.crm.api.record.ActionResponse;
import com.zoho.crm.api.record.ActionWrapper;
import com.zoho.crm.api.record.BodyWrapper;
import com.zoho.crm.api.record.Comment;
import com.zoho.crm.api.record.Consent;
import com.zoho.crm.api.record.ConversionOption;
import com.zoho.crm.api.record.ConversionOptionsResponseWrapper;
import com.zoho.crm.api.record.ConvertBodyWrapper;
import com.zoho.crm.api.record.CountHandler;
import com.zoho.crm.api.record.DeletedRecord;
import com.zoho.crm.api.record.DeletedRecordsHandler;
import com.zoho.crm.api.record.DeletedRecordsWrapper;
import com.zoho.crm.api.record.DownloadHandler;
import com.zoho.crm.api.record.Field;
import com.zoho.crm.api.record.FileBodyWrapper;
import com.zoho.crm.api.record.FileDetails;
import com.zoho.crm.api.record.FileHandler;
import com.zoho.crm.api.record.ImageUpload;
import com.zoho.crm.api.record.Info;
import com.zoho.crm.api.record.LeadConverter;
import com.zoho.crm.api.record.LineItemProduct;
import com.zoho.crm.api.record.LineTax;
import com.zoho.crm.api.record.MassUpdate;
import com.zoho.crm.api.record.MassUpdateActionHandler;
import com.zoho.crm.api.record.MassUpdateActionResponse;
import com.zoho.crm.api.record.MassUpdateActionWrapper;
import com.zoho.crm.api.record.MassUpdateBodyWrapper;
import com.zoho.crm.api.record.MassUpdateResponse;
import com.zoho.crm.api.record.MassUpdateResponseHandler;
import com.zoho.crm.api.record.MassUpdateResponseWrapper;
import com.zoho.crm.api.record.MassUpdateSuccessResponse;
import com.zoho.crm.api.record.Participants;
import com.zoho.crm.api.record.PreferenceFieldMatchedValue;
import com.zoho.crm.api.record.PricingDetails;
import com.zoho.crm.api.record.RecordOperations;
import com.zoho.crm.api.record.RecurringActivity;
import com.zoho.crm.api.record.RemindAt;
import com.zoho.crm.api.record.Reminder;
import com.zoho.crm.api.record.ResponseHandler;
import com.zoho.crm.api.record.ResponseWrapper;
import com.zoho.crm.api.record.SuccessResponse;
import com.zoho.crm.api.record.Tax;
import com.zoho.crm.api.record.Territory;
import com.zoho.crm.api.record.CountWrapper;
import com.zoho.crm.api.tags.Tag;
import com.zoho.crm.api.record.RecordOperations.CreateRecordsHeader;
import com.zoho.crm.api.record.RecordOperations.DeleteRecordHeader;
import com.zoho.crm.api.record.RecordOperations.DeleteRecordParam;
import com.zoho.crm.api.record.RecordOperations.DeleteRecordsHeader;
import com.zoho.crm.api.record.RecordOperations.DeleteRecordsParam;
import com.zoho.crm.api.record.RecordOperations.GetDeletedRecordsHeader;
import com.zoho.crm.api.record.RecordOperations.GetDeletedRecordsParam;
import com.zoho.crm.api.record.RecordOperations.GetMassUpdateStatusParam;
import com.zoho.crm.api.record.RecordOperations.GetRecordHeader;
import com.zoho.crm.api.record.RecordOperations.GetRecordParam;
import com.zoho.crm.api.record.RecordOperations.GetRecordsHeader;
import com.zoho.crm.api.record.RecordOperations.GetRecordsParam;
import com.zoho.crm.api.record.RecordOperations.SearchRecordsHeader;
import com.zoho.crm.api.record.RecordOperations.SearchRecordsParam;
import com.zoho.crm.api.record.RecordOperations.UpdateRecordHeader;
import com.zoho.crm.api.record.RecordOperations.UpdateRecordsHeader;
import com.zoho.crm.api.record.RecordOperations.UpsertRecordsHeader;
import com.zoho.crm.api.users.MinifiedUser;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Choice;
import com.zoho.crm.api.util.Model;
import com.zoho.crm.api.util.StreamWrapper;

public class Record
{
	/**
	 * <h3>Get Record</h3> This method is used to get a single record of a module with ID and print the response.
	 * 
	 * @param moduleAPIName     - The API Name of the record's module.
	 * @param recordId          - The ID of the record to be obtained.
	 * @param destinationFolder - The absolute path of the destination folder to store the file.
	 * @throws Exception
	 */
	public static void getRecord(String moduleAPIName, Long recordId, String destinationFolder) throws Exception
	{
		// example
		// String moduleAPIName = "Leads";
		// long recordId = 34770616603276L;

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		// Get instance of ParameterMap Class
		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(GetRecordParam.APPROVED, "both");

		paramInstance.add(GetRecordParam.CONVERTED, "false");

		List<String> fieldNames = new ArrayList<String>(Arrays.asList("Company", "Email"));

		paramInstance.add(GetRecordParam.FIELDS, String.join(",", fieldNames));

		OffsetDateTime startdatetime = OffsetDateTime.of(2021, 07, 02, 11, 00, 01, 00, ZoneOffset.of("+05:30"));

		paramInstance.add(GetRecordParam.STARTDATETIME, startdatetime);

		OffsetDateTime enddatetime = OffsetDateTime.of(2021, 8, 02, 12, 00, 01, 00, ZoneOffset.of("+05:30"));

		paramInstance.add(GetRecordParam.ENDDATETIME, enddatetime);

		paramInstance.add(GetRecordParam.TERRITORY_ID, "34770613051357");

		paramInstance.add(GetRecordParam.INCLUDE_CHILD, "true");

		HeaderMap headerInstance = new HeaderMap();

		OffsetDateTime ifmodifiedsince = OffsetDateTime.of(2019, 01, 02, 10, 00, 00, 00, ZoneOffset.of("+05:30"));

		headerInstance.add(GetRecordHeader.IF_MODIFIED_SINCE, ifmodifiedsince);

//		headerInstance.add(GetRecordHeader.X_EXTERNAL, "Leads.External");

		// Call getRecord method that takes recordID, moduleAPIName, paramInstance, and headerInstance as parameter
		APIResponse<ResponseHandler> response = recordOperations.getRecord(recordId, moduleAPIName, paramInstance, headerInstance);

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

					// Get the list of obtained Record instances
					List<com.zoho.crm.api.record.Record> records = responseWrapper.getData();

					for (com.zoho.crm.api.record.Record record : records)
					{
						// Get the ID of each Record
						System.out.println("Record ID: " + record.getId());

						// Get the createdBy User instance of each Record
						com.zoho.crm.api.users.MinifiedUser createdBy = record.getCreatedBy();

						// Check if createdBy is not null
						if (createdBy != null)
						{
							// Get the ID of the createdBy User
							System.out.println("Record Created By User-ID: " + createdBy.getId());

							// Get the name of the createdBy User
							System.out.println("Record Created By User-Name: " + createdBy.getName());

							// Get the Email of the createdBy User
							System.out.println("Record Created By User-Email: " + createdBy.getEmail());
						}

						// Get the CreatedTime of each Record
						System.out.println("Record CreatedTime: " + record.getCreatedTime());

						// Get the modifiedBy User instance of each Record
						com.zoho.crm.api.users.MinifiedUser modifiedBy = record.getModifiedBy();

						// Check if modifiedBy is not null
						if (modifiedBy != null)
						{
							// Get the ID of the modifiedBy User
							System.out.println("Record Modified By User-ID: " + modifiedBy.getId());

							// Get the name of the modifiedBy User
							System.out.println("Record Modified By User-Name: " + modifiedBy.getName());

							// Get the Email of the modifiedBy User
							System.out.println("Record Modified By User-Email: " + modifiedBy.getEmail());
						}

						// Get the ModifiedTime of each Record
						System.out.println("Record ModifiedTime: " + record.getModifiedTime());

						// Get the list of Tag instance each Record
						List<Tag> tags = record.getTag();

						// Check if tags is not null
						if (tags != null)
						{
							for (Tag tag : tags)
							{
								// Get the Name of each Tag
								System.out.println("Record Tag Name: " + tag.getName());

								// Get the Id of each Tag
								System.out.println("Record Tag ID: " + tag.getId());
							}
						}

						// To get particular field value
						System.out.println("Record Field Value: " + record.getKeyValue("Last_Name"));// FieldApiName

						System.out.println("Record KeyValues: ");

						// Get the KeyValue map
						for (Map.Entry<String, Object> entry : record.getKeyValues().entrySet())
						{
							String keyName = entry.getKey();

							Object value = entry.getValue();

							if (value instanceof List)
							{
								List<?> dataList = (List<?>) value;

								if (dataList.size() > 0)
								{
									if (dataList.get(0) instanceof FileDetails)
									{
										@SuppressWarnings("unchecked")
										List<FileDetails> fileDetails = (List<FileDetails>) value;

										for (FileDetails fileDetail : fileDetails)
										{
											// Get the Extn of each FileDetails
											System.out.println("Record FileDetails Extn: " + fileDetail.getExtn());

											// Get the IsPreviewAvailable of each FileDetails
											System.out.println("Record FileDetails IsPreviewAvailable: " + fileDetail.getIsPreviewAvailable());

											// Get the DownloadUrl of each FileDetails
											System.out.println("Record FileDetails DownloadUrl: " + fileDetail.getDownloadUrl());

											// Get the DeleteUrl of each FileDetails
											System.out.println("Record FileDetails DeleteUrl: " + fileDetail.getDeleteUrl());

											// Get the EntityId of each FileDetails
											System.out.println("Record FileDetails EntityId: " + fileDetail.getEntityId());

											// Get the Mode of each FileDetails
											System.out.println("Record FileDetails Mode: " + fileDetail.getMode());

											// Get the OriginalSizeByte of each FileDetails
											System.out.println("Record FileDetails OriginalSizeByte: " + fileDetail.getOriginalSizeByte());

											// Get the PreviewUrl of each FileDetails
											System.out.println("Record FileDetails PreviewUrl: " + fileDetail.getPreviewUrl());

											// Get the FileName of each FileDetails
											System.out.println("Record FileDetails FileName: " + fileDetail.getFileName());

											// Get the FileId of each FileDetails
											System.out.println("Record FileDetails FileId: " + fileDetail.getFileId());

											// Get the AttachmentId of each FileDetails
											System.out.println("Record FileDetails AttachmentId: " + fileDetail.getAttachmentId());

											// Get the FileSize of each FileDetails
											System.out.println("Record FileDetails FileSize: " + fileDetail.getFileSize());

											// Get the CreatorId of each FileDetails
											System.out.println("Record FileDetails CreatorId: " + fileDetail.getCreatorId());

											// Get the LinkDocs of each FileDetails
											System.out.println("Record FileDetails LinkDocs: " + fileDetail.getLinkDocs());
										}
									}
									else if (dataList.get(0) instanceof Choice<?>)
									{
										@SuppressWarnings("unchecked")
										List<Choice<?>> choiceList = (List<Choice<?>>) dataList;

										System.out.println(keyName);

										System.out.println("values");

										for (Choice<?> choice : choiceList)
										{
											System.out.println(choice.getValue());
										}
									}
									else if (dataList.get(0) instanceof ImageUpload)
									{
										@SuppressWarnings("unchecked")
										List<ImageUpload> imageUploads = (List<ImageUpload>) dataList;
										for (ImageUpload imageUpload : imageUploads)
										{
											System.out.println("Record  Description: " + imageUpload.getDescription());

											System.out.println("Record  PreviewId: " + imageUpload.getPreviewId());

											System.out.println("Record  File_Name: " + imageUpload.getFileName());

											System.out.println("Record  State: " + imageUpload.getState());

											System.out.println("Record  Size: " + imageUpload.getSize());

											System.out.println("Record  SequenceNumber: " + imageUpload.getSequenceNumber());

											System.out.println("Record  Id: " + imageUpload.getId());

											System.out.println("Record  FileId: " + imageUpload.getFileId());
										}
									}
									else if (dataList.get(0) instanceof Tax)
									{
										Tax tax = (Tax) dataList.get(0);
										// Get the id of Tax
										System.out.println("Record Tax id: " + tax.getId());

										// Get the value of Tax
										System.out.println("Record Tax value: " + tax.getValue());
									}
									else if (dataList.get(0) instanceof Tag)
									{
										@SuppressWarnings("unchecked")
										List<Tag> tagList = (List<Tag>) value;

										for (Tag tag : tagList)
										{
											// Get the Name of each Tag
											System.out.println("Record Tag Name: " + tag.getName());

											// Get the Id of each Tag
											System.out.println("Record Tag ID: " + tag.getId());
										}
									}
									else if (dataList.get(0) instanceof PricingDetails)
									{
										@SuppressWarnings("unchecked")
										List<PricingDetails> pricingDetails = (List<PricingDetails>) value;

										for (PricingDetails pricingDetail : pricingDetails)
										{
											System.out.println("Record PricingDetails ToRange: " + pricingDetail.getToRange().toString());

											System.out.println("Record PricingDetails Discount: " + pricingDetail.getDiscount().toString());

											System.out.println("Record PricingDetails ID: " + pricingDetail.getId());

											System.out.println("Record PricingDetails FromRange: " + pricingDetail.getFromRange().toString());
										}
									}
									else if (dataList.get(0) instanceof com.zoho.crm.api.record.Record)
									{
										@SuppressWarnings("unchecked")
										List<com.zoho.crm.api.record.Record> recordList = (List<com.zoho.crm.api.record.Record>) dataList;

										for (com.zoho.crm.api.record.Record record1 : recordList)
										{
											// Get the details map
											for (Map.Entry<String, Object> entry1 : record1.getKeyValues().entrySet())
											{
												// Get each value in the map
												System.out.println(entry1.getKey() + ": " + entry1.getValue());
											}
										}
									}
									else if (dataList.get(0) instanceof LineTax)
									{
										@SuppressWarnings("unchecked")
										List<LineTax> lineTaxes = (List<LineTax>) dataList;

										for (LineTax lineTax : lineTaxes)
										{
											System.out.println("Record ProductDetails LineTax Percentage: " + lineTax.getPercentage().toString());

											System.out.println("Record ProductDetails LineTax Name: " + lineTax.getName());

											System.out.println("Record ProductDetails LineTax Id: " + lineTax.getId());

											System.out.println("Record ProductDetails LineTax Value: " + lineTax.getValue().toString());
										}
									}
									else if (dataList.get(0) instanceof Comment)
									{
										@SuppressWarnings("unchecked")
										List<Comment> comments = (List<Comment>) dataList;

										for (Comment comment : comments)
										{
											System.out.println("Record Comment CommentedBy: " + comment.getCommentedBy());

											System.out.println("Record Comment CommentedTime: " + comment.getCommentedTime().toString());

											System.out.println("Record Comment CommentContent: " + comment.getCommentContent());

											System.out.println("Record Comment Id: " + comment.getId());
										}
									}
									else if (dataList.get(0) instanceof Attachment)
									{
										// Get the list of obtained Attachment instances
										@SuppressWarnings("unchecked")
										List<com.zoho.crm.api.attachments.Attachment> attachments = (List<Attachment>) dataList;

										for (com.zoho.crm.api.attachments.Attachment attachment : attachments)
										{
											// Get the owner User instance of each attachment
											com.zoho.crm.api.users.MinifiedUser owner = attachment.getOwner();

											// Check if owner is not null
											if (owner != null)
											{
												// Get the Name of the Owner
												System.out.println("Record Attachment Owner User-Name: " + owner.getName());

												// Get the ID of the Owner
												System.out.println("Record Attachment Owner User-ID: " + owner.getId());

												// Get the Email of the Owner
												System.out.println("Record Attachment Owner User-Email: " + owner.getEmail());
											}

											// Get the modified time of each attachment
											System.out.println("Record Attachment Modified Time: " + attachment.getModifiedTime().toString());

											// Get the name of the File
											System.out.println("Record Attachment File Name: " + attachment.getFileName());

											// Get the created time of each attachment
											System.out.println("Record Attachment Created Time: " + attachment.getCreatedTime().toString());

											// Get the Attachment file size
											System.out.println("Record Attachment File Size: " + attachment.getSize().toString());

											// Get the parentId Record instance of each attachment
											ParentId parentId = attachment.getParentId();

											// Check if parentId is not null
											if (parentId != null)
											{
												// Get the parent record Name of each attachment
												System.out.println("Record Attachment parent record Name: " + parentId.getName());

												// Get the parent record ID of each attachment
												System.out.println("Record Attachment parent record ID: " + parentId.getId());
											}

											// Get the attachment is Editable
											System.out.println("Record Attachment is Editable: " + attachment.getEditable().toString());

											// Get the file ID of each attachment
											System.out.println("Record Attachment File ID: " + attachment.getFileId());

											// Get the type of each attachment
											System.out.println("Record Attachment File Type: " + attachment.getType());

											// Get the seModule of each attachment
											System.out.println("Record Attachment seModule: " + attachment.getSeModule());

											// Get the modifiedBy User instance of each attachment
											modifiedBy = attachment.getModifiedBy();

											// Check if modifiedBy is not null
											if (modifiedBy != null)
											{
												// Get the Name of the modifiedBy User
												System.out.println("Record Attachment Modified By User-Name: " + modifiedBy.getName());

												// Get the ID of the modifiedBy User
												System.out.println("Record Attachment Modified By User-ID: " + modifiedBy.getId());

												// Get the Email of the modifiedBy User
												System.out.println("Record Attachment Modified By User-Email: " + modifiedBy.getEmail());
											}

											// Get the state of each attachment
											System.out.println("Record Attachment State: " + attachment.getState());

											// Get the ID of each attachment
											System.out.println("Record Attachment ID: " + attachment.getId());

											// Get the createdBy User instance of each attachment
											createdBy = attachment.getCreatedBy();

											// Check if createdBy is not null
											if (createdBy != null)
											{
												// Get the name of the createdBy User
												System.out.println("Record Attachment Created By User-Name: " + createdBy.getName());

												// Get the ID of the createdBy User
												System.out.println("Record Attachment Created By User-ID: " + createdBy.getId());

												// Get the Email of the createdBy User
												System.out.println("Record Attachment Created By User-Email: " + createdBy.getEmail());
											}

											// Get the linkUrl of each attachment
											System.out.println("Record Attachment LinkUrl: " + attachment.getLinkUrl());
										}
									}
									else
									{
										System.out.println(keyName + ": " + value);
									}
								}
							}
							else if (value instanceof com.zoho.crm.api.record.Record)
							{
								com.zoho.crm.api.record.Record recordValue = (com.zoho.crm.api.record.Record) value;

								System.out.println("Record " + keyName + " ID: " + recordValue.getId());

								System.out.println("Record " + keyName + " Name: " + recordValue.getKeyValue("name"));
							}
							else if (value instanceof Layout)
							{
								com.zoho.crm.api.layouts.Layout layout = (Layout) value;

								if (layout != null)
								{
									System.out.println("Record " + keyName + " ID: " + layout.getId());

									System.out.println("Record " + keyName + " Name: " + layout.getName());
								}
							}
							else if (value instanceof MinifiedUser)
							{
								com.zoho.crm.api.users.MinifiedUser user = (MinifiedUser) value;

								if (user != null)
								{
									System.out.println("Record " + keyName + " User-ID: " + user.getId());

									System.out.println("Record " + keyName + " User-Name: " + user.getName());

									System.out.println("Record " + keyName + " User-Email: " + user.getEmail());
								}
							}
							else if (value instanceof Choice<?>)
							{
								System.out.println(keyName + ": " + ((Choice<?>) value).getValue());
							}
							else if (value instanceof RemindAt)
							{
								System.out.println(keyName + ": " + ((RemindAt) value).getAlarm());
							}
							else if (value instanceof RecurringActivity)
							{
								System.out.println(keyName);

								System.out.println("RRULE" + ": " + ((RecurringActivity) value).getRrule());
							}
							else if (value instanceof Consent)
							{
								Consent consent = (Consent) value;

								System.out.println("Record Consent ID: " + consent.getId());

								// Get the Owner User instance of each attachment
								MinifiedUser owner = consent.getOwner();

								// Check if owner is not null
								if (owner != null)
								{
									// Get the name of the owner User
									System.out.println("Record Consent Owner Name: " + owner.getName());

									// Get the ID of the owner User
									System.out.println("Record Consent Owner ID: " + owner.getId());

									// Get the Email of the owner User
									System.out.println("Record Consent Owner Email: " + owner.getEmail());
								}

								MinifiedUser consentCreatedBy = consent.getCreatedBy();

								// Check if createdBy is not null
								if (consentCreatedBy != null)
								{
									// Get the name of the CreatedBy User
									System.out.println("Record Consent CreatedBy Name: " + consentCreatedBy.getName());

									// Get the ID of the CreatedBy User
									System.out.println("Record Consent CreatedBy ID: " + consentCreatedBy.getId());

									// Get the Email of the CreatedBy User
									System.out.println("Record Consent CreatedBy Email: " + consentCreatedBy.getEmail());
								}

								MinifiedUser consentModifiedBy = consent.getModifiedBy();

								// Check if createdBy is not null
								if (consentModifiedBy != null)
								{
									// Get the name of the ModifiedBy User
									System.out.println("Record Consent ModifiedBy Name: " + consentModifiedBy.getName());

									// Get the ID of the ModifiedBy User
									System.out.println("Record Consent ModifiedBy ID: " + consentModifiedBy.getId());

									// Get the Email of the ModifiedBy User
									System.out.println("Record Consent ModifiedBy Email: " + consentModifiedBy.getEmail());
								}

								System.out.println("Record Consent CreatedTime: " + consent.getCreatedTime());

								System.out.println("Record Consent ModifiedTime: " + consent.getModifiedTime());

								System.out.println("Record Consent ContactThroughEmail: " + consent.getContactThroughEmail());

								System.out.println("Record Consent ContactThroughSocial: " + consent.getContactThroughSocial());

								System.out.println("Record Consent ContactThroughSurvey: " + consent.getContactThroughSurvey());

								System.out.println("Record Consent ContactThroughPhone: " + consent.getContactThroughPhone());

								System.out.println("Record Consent MailSentTime: " + consent.getMailSentTime().toString());

								System.out.println("Record Consent ConsentDate: " + consent.getConsentDate().toString());

								System.out.println("Record Consent ConsentRemarks: " + consent.getConsentRemarks());

								System.out.println("Record Consent ConsentThrough: " + consent.getConsentThrough());

								System.out.println("Record Consent DataProcessingBasis: " + consent.getDataProcessingBasis());

								// To get custom values
								System.out.println("Record Consent Lawful Reason: " + consent.getKeyValue("Lawful_Reason"));
							}
							else
							{
								// Get each value in the map
								System.out.println(keyName + ": " + value);
							}
						}
					}
				}
				else if (responseHandler instanceof FileBodyWrapper)
				{
					// Get object from response
					FileBodyWrapper fileBodyWrapper = (FileBodyWrapper) responseHandler;

					// Get StreamWrapper instance from the returned FileBodyWrapper instance
					StreamWrapper streamWrapper = fileBodyWrapper.getFile();

					// Create a file instance with the absolute_file_path
					File file = new File(destinationFolder + File.separatorChar + streamWrapper.getName());

					// Get InputStream from the response
					InputStream is = streamWrapper.getStream();

					// Create an OutputStream for the destination file
					OutputStream os = new FileOutputStream(file);

					byte[] buffer = new byte[1024];

					int bytesRead;

					// read the InputStream till the end
					while ((bytesRead = is.read(buffer)) != -1)
					{
						// write data to OutputStream
						os.write(buffer, 0, bytesRead);
					}

					// Close the InputStream
					is.close();

					// Flush and close the OutputStream
					os.flush();

					os.close();
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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	/**
	 * <h3>Update Record</h3> This method is used to update a single record of a module with ID and print the response.
	 * 
	 * @param moduleAPIName - The API Name of the record's module.
	 * @param recordId      - The ID of the record to be updated.
	 * @throws Exception
	 */
	public static void updateRecord(String moduleAPIName, long recordId) throws Exception
	{
		// API Name of the module
		// String moduleAPIName = "Leads";
		// String recordId = "347706151772";

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper request = new BodyWrapper();

		// List of Record instances
		List<com.zoho.crm.api.record.Record> records = new ArrayList<com.zoho.crm.api.record.Record>();

		// Get instance of Record Class
		com.zoho.crm.api.record.Record record1 = new com.zoho.crm.api.record.Record();

		/*
		 * Call addFieldValue method that takes two arguments 1 -> Call Field "." and choose the module from the displayed list and press "." and choose the field name from the displayed list. 2 -> Value
		 */
		record1.addFieldValue(Field.Leads.CITY, "City");

		record1.addFieldValue(Field.Leads.LAST_NAME, "Last Name");

		record1.addFieldValue(Field.Leads.FIRST_NAME, "First Name");

		record1.addFieldValue(Field.Leads.LAST_NAME, "Last Name");

		record1.addFieldValue(Field.Leads.COMPANY, "KKRNP");

		/*
		 * Call addKeyValue method that takes two arguments 1 -> A string that is the Field's API Name 2 -> Value
		 */
		record1.addKeyValue("Custom_field", "Value");

		record1.addKeyValue("Custom_field_2", "value");

		record1.addKeyValue("Date_Time_2", OffsetDateTime.of(2019, 11, 20, 10, 00, 01, 00, ZoneOffset.of("+05:30")));

		record1.addKeyValue("Date_1", LocalDate.of(2017, 1, 13));

		List<FileDetails> fileDetails = new ArrayList<FileDetails>();

		FileDetails fileDetail1 = new FileDetails();

		fileDetail1.setAttachmentId("347706174116");

		fileDetail1.setDelete(null);

		fileDetails.add(fileDetail1);

		FileDetails fileDetail2 = new FileDetails();

		fileDetail2.setFileId("xxxx");

		fileDetails.add(fileDetail2);

		FileDetails fileDetail3 = new FileDetails();

		fileDetail3.setFileId("xxxxx");

		fileDetails.add(fileDetail3);

		record1.addKeyValue("File_Upload", fileDetails);

		com.zoho.crm.api.users.MinifiedUser recordOwner = new com.zoho.crm.api.users.MinifiedUser();

		recordOwner.setEmail("abc@zoho.com");

		record1.addKeyValue("Owner", recordOwner);

		// Used when GDPR is enabled
		Consent dataConsent = new Consent();

		dataConsent.setConsentRemarks("Approved.");

		dataConsent.setConsentThrough("Email");

		dataConsent.setContactThroughEmail(true);

		dataConsent.setContactThroughSocial(false);

		record1.addKeyValue("Data_Processing_Basis_Details", dataConsent);

		List<com.zoho.crm.api.record.Record> subformList = new ArrayList<com.zoho.crm.api.record.Record>();

		com.zoho.crm.api.record.Record subform = new com.zoho.crm.api.record.Record();

		subform.addKeyValue("Subform FieldAPIName", "FieldValue");

		subformList.add(subform);

		record1.addKeyValue("Subform Name", subformList);

		/** Following methods are being used only by Inventory modules */

		com.zoho.crm.api.record.Record dealName = new com.zoho.crm.api.record.Record();

		dealName.addFieldValue(Field.Deals.ID, 34770614995070l);

		record1.addFieldValue(Field.Sales_Orders.DEAL_NAME, dealName);

		com.zoho.crm.api.record.Record contactName = new com.zoho.crm.api.record.Record();

		contactName.addFieldValue(Field.Contacts.ID, 34770614977055l);

		record1.addFieldValue(Field.Purchase_Orders.CONTACT_NAME, contactName);

		com.zoho.crm.api.record.Record accountName = new com.zoho.crm.api.record.Record();

		accountName.addKeyValue("name", "automatedAccount");

		record1.addFieldValue(Field.Quotes.ACCOUNT_NAME, accountName);

		ArrayList<com.zoho.crm.api.record.Record> inventoryLineItemList = new ArrayList<com.zoho.crm.api.record.Record>();

		com.zoho.crm.api.record.Record inventoryLineItem = new com.zoho.crm.api.record.Record();

		LineItemProduct lineItemProduct = new LineItemProduct();

		lineItemProduct.setId(347706153569L);

		inventoryLineItem.addKeyValue("Description", "asd");
		inventoryLineItem.addKeyValue("Discount", "5");
		com.zoho.crm.api.record.Record parentId = new com.zoho.crm.api.record.Record();
		parentId.setId(35240337331017L);
//		inventoryLineItem.addKeyValue("Parent_Id", 5);
		inventoryLineItem.addKeyValue("Sequence_Number", 1L);
		LineItemProduct lineitemProduct = new LineItemProduct();
		lineitemProduct.setId(35240333659082L);
		inventoryLineItem.addKeyValue("Product_Name", lineItemProduct);
		inventoryLineItem.addKeyValue("Sequence_Number", 1l);
		inventoryLineItem.addKeyValue("Quantity", 123.2);
		inventoryLineItem.addKeyValue("Tax", 123.2);
		inventoryLineItemList.add(inventoryLineItem);

		List<LineTax> productLineTaxes = new ArrayList<LineTax>();

		LineTax productLineTax = new LineTax();

		productLineTax.setName("Sales Tax");

		productLineTax.setPercentage(20.0);

		productLineTaxes.add(productLineTax);

		inventoryLineItem.addKeyValue("line_tax", productLineTaxes);

		inventoryLineItemList.add(inventoryLineItem);

		record1.addKeyValue("Quoted_Items", inventoryLineItemList);

		List<LineTax> lineTaxes = new ArrayList<LineTax>();

		LineTax lineTax = new LineTax();

		lineTax.setName("Sales Tax");

		lineTax.setPercentage(20.0);

		lineTaxes.add(lineTax);

		record1.addKeyValue("$line_tax", lineTaxes);

		/** End Inventory **/

		List<Tag> tagList = new ArrayList<Tag>();

		Tag tag = new Tag();

		tag.setName("Testtask1");

		tagList.add(tag);

		record1.setTag(tagList);

		// Add Record instance to the list
		records.add(record1);

		// Set the list to Records in BodyWrapper instance
		request.setData(records);

		List<String> trigger = new ArrayList<String>();

		trigger.add("approval");

		trigger.add("workflow");

		trigger.add("blueprint");

		request.setTrigger(trigger);

		HeaderMap headerInstance = new HeaderMap();

//		headerInstance.add(UpdateRecordHeader.X_EXTERNAL, "Leads.External");

		// Call updateRecord method that takes recordId, ModuleAPIName, BodyWrapper instance and headerInstance as parameter.
		APIResponse<ActionHandler> response = recordOperations.updateRecord(recordId, moduleAPIName, request, headerInstance);

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
					// Get the received ResponseWrapper instance
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
							System.out.println("Message: " + successResponse.getMessage().getValue());
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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	/**
	 * <h3>Delete Record</h3> This method is used to delete a single record of a module with ID and print the response.
	 * 
	 * @param moduleAPIName - The API Name of the record's module.
	 * @param recordId      - The ID of the record to be deleted.
	 * @throws Exception
	 */
	public static void deleteRecord(String moduleAPIName, long recordId) throws Exception
	{
		// API Name of the module to delete record
		// String moduleAPIName = "Leads";
		// Long recordId = 347706151772l;

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		// Get instance of ParameterMap Class
		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(DeleteRecordParam.WF_TRIGGER, false);

		HeaderMap headerInstance = new HeaderMap();

//		headerInstance.add(DeleteRecordHeader.X_EXTERNAL, "Leads.External");

		// Call deleteRecord method that takes recordId, ModuleAPIName, paramInstance and headerInstance as parameter.
		APIResponse<ActionHandler> response = recordOperations.deleteRecord(recordId, moduleAPIName, paramInstance, headerInstance);

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
							System.out.println("Message: " + successResponse.getMessage().getValue());
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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	/**
	 * <h3>Get Record Using External Id</h3> This method is used to get a single record of a module with ID and print the response.
	 * 
	 * @param moduleAPIName      - The API Name of the record's module.
	 * @param externalFieldValue -
	 * @param destinationFolder  - The absolute path of the destination folder to store the file.
	 * @throws Exception
	 */
	public static void getRecordUsingExternalId(String moduleAPIName, String externalFieldValue, String destinationFolder) throws Exception
	{
		// example
		// String moduleAPIName = "Leads";
		// String externalFieldValue = "34770616603276";

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		// Get instance of ParameterMap Class
		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(GetRecordParam.APPROVED, "both");

		paramInstance.add(GetRecordParam.CONVERTED, "false");

		List<String> fieldNames = new ArrayList<String>(Arrays.asList("Company", "Email"));

		for (String fieldName : fieldNames)
		{
			paramInstance.add(GetRecordParam.FIELDS, fieldName);
		}

		OffsetDateTime startdatetime = OffsetDateTime.of(2021, 07, 02, 11, 00, 01, 00, ZoneOffset.of("+05:30"));

		paramInstance.add(GetRecordParam.STARTDATETIME, startdatetime);

		OffsetDateTime enddatetime = OffsetDateTime.of(2021, 8, 02, 12, 00, 01, 00, ZoneOffset.of("+05:30"));

		paramInstance.add(GetRecordParam.ENDDATETIME, enddatetime);

		paramInstance.add(GetRecordParam.TERRITORY_ID, "34770613051357");

		paramInstance.add(GetRecordParam.INCLUDE_CHILD, "true");

		HeaderMap headerInstance = new HeaderMap();

		OffsetDateTime ifmodifiedsince = OffsetDateTime.of(2019, 01, 02, 10, 00, 00, 00, ZoneOffset.of("+05:30"));

		headerInstance.add(GetRecordHeader.IF_MODIFIED_SINCE, ifmodifiedsince);

		headerInstance.add(GetRecordHeader.X_EXTERNAL, "Leads.External");

		// Call getRecordUsingExternalId method that takes externalFieldValue, moduleAPIName, paramInstance, and headerInstance as parameter
		APIResponse<ResponseHandler> response = recordOperations.getRecordUsingExternalId(externalFieldValue, moduleAPIName, paramInstance, headerInstance);

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

					// Get the list of obtained Record instances
					List<com.zoho.crm.api.record.Record> records = responseWrapper.getData();

					for (com.zoho.crm.api.record.Record record : records)
					{
						// Get the ID of each Record
						System.out.println("Record ID: " + record.getId());

						// Get the createdBy User instance of each Record
						com.zoho.crm.api.users.MinifiedUser createdBy = record.getCreatedBy();

						// Check if createdBy is not null
						if (createdBy != null)
						{
							// Get the ID of the createdBy User
							System.out.println("Record Created By User-ID: " + createdBy.getId());

							// Get the name of the createdBy User
							System.out.println("Record Created By User-Name: " + createdBy.getName());

							// Get the Email of the createdBy User
							System.out.println("Record Created By User-Email: " + createdBy.getEmail());
						}

						// Get the CreatedTime of each Record
						System.out.println("Record CreatedTime: " + record.getCreatedTime());

						// Get the modifiedBy User instance of each Record
						com.zoho.crm.api.users.MinifiedUser modifiedBy = record.getModifiedBy();

						// Check if modifiedBy is not null
						if (modifiedBy != null)
						{
							// Get the ID of the modifiedBy User
							System.out.println("Record Modified By User-ID: " + modifiedBy.getId());

							// Get the name of the modifiedBy User
							System.out.println("Record Modified By User-Name: " + modifiedBy.getName());

							// Get the Email of the modifiedBy User
							System.out.println("Record Modified By User-Email: " + modifiedBy.getEmail());
						}

						// Get the ModifiedTime of each Record
						System.out.println("Record ModifiedTime: " + record.getModifiedTime());

						// Get the list of Tag instance each Record
						List<Tag> tags = record.getTag();

						// Check if tags is not null
						if (tags != null)
						{
							for (Tag tag : tags)
							{
								// Get the Name of each Tag
								System.out.println("Record Tag Name: " + tag.getName());

								// Get the Id of each Tag
								System.out.println("Record Tag ID: " + tag.getId());
							}
						}

						// To get particular field value
						System.out.println("Record Field Value: " + record.getKeyValue("Last_Name"));// FieldApiName

						System.out.println("Record KeyValues: ");

						// Get the KeyValue map
						for (Map.Entry<String, Object> entry : record.getKeyValues().entrySet())
						{
							String keyName = entry.getKey();

							Object value = entry.getValue();

							if (value instanceof List)
							{
								List<?> dataList = (List<?>) value;

								if (dataList.size() > 0)
								{
									if (dataList.get(0) instanceof FileDetails)
									{
										@SuppressWarnings("unchecked")
										List<FileDetails> fileDetails = (List<FileDetails>) value;

										for (FileDetails fileDetail : fileDetails)
										{
											// Get the Extn of each FileDetails
											System.out.println("Record FileDetails Extn: " + fileDetail.getExtn());

											// Get the IsPreviewAvailable of each FileDetails
											System.out.println("Record FileDetails IsPreviewAvailable: " + fileDetail.getIsPreviewAvailable());

											// Get the DownloadUrl of each FileDetails
											System.out.println("Record FileDetails DownloadUrl: " + fileDetail.getDownloadUrl());

											// Get the DeleteUrl of each FileDetails
											System.out.println("Record FileDetails DeleteUrl: " + fileDetail.getDeleteUrl());

											// Get the EntityId of each FileDetails
											System.out.println("Record FileDetails EntityId: " + fileDetail.getEntityId());

											// Get the Mode of each FileDetails
											System.out.println("Record FileDetails Mode: " + fileDetail.getMode());

											// Get the OriginalSizeByte of each FileDetails
											System.out.println("Record FileDetails OriginalSizeByte: " + fileDetail.getOriginalSizeByte());

											// Get the PreviewUrl of each FileDetails
											System.out.println("Record FileDetails PreviewUrl: " + fileDetail.getPreviewUrl());

											// Get the FileName of each FileDetails
											System.out.println("Record FileDetails FileName: " + fileDetail.getFileName());

											// Get the FileId of each FileDetails
											System.out.println("Record FileDetails FileId: " + fileDetail.getFileId());

											// Get the AttachmentId of each FileDetails
											System.out.println("Record FileDetails AttachmentId: " + fileDetail.getAttachmentId());

											// Get the FileSize of each FileDetails
											System.out.println("Record FileDetails FileSize: " + fileDetail.getFileSize());

											// Get the CreatorId of each FileDetails
											System.out.println("Record FileDetails CreatorId: " + fileDetail.getCreatorId());

											// Get the LinkDocs of each FileDetails
											System.out.println("Record FileDetails LinkDocs: " + fileDetail.getLinkDocs());
										}
									}
									else if (dataList.get(0) instanceof Choice<?>)
									{
										@SuppressWarnings("unchecked")
										List<Choice<?>> choiceList = (List<Choice<?>>) dataList;

										System.out.println(keyName);

										System.out.println("values");

										for (Choice<?> choice : choiceList)
										{
											System.out.println(choice.getValue());
										}
									}
									else if (dataList.get(0) instanceof ImageUpload)
									{
										@SuppressWarnings("unchecked")
										List<ImageUpload> imageUploads = (List<ImageUpload>) dataList;
										for (ImageUpload imageUpload : imageUploads)
										{
											System.out.println("Record  Description: " + imageUpload.getDescription());

											System.out.println("Record  PreviewId: " + imageUpload.getPreviewId());

											System.out.println("Record  File_Name: " + imageUpload.getFileName());

											System.out.println("Record  State: " + imageUpload.getState());

											System.out.println("Record  Size: " + imageUpload.getSize());

											System.out.println("Record  SequenceNumber: " + imageUpload.getSequenceNumber());

											System.out.println("Record  Id: " + imageUpload.getId());

											System.out.println("Record  FileId: " + imageUpload.getFileId());
										}
									}
									else if (dataList.get(0) instanceof Tax)
									{
										Tax tax = (Tax) dataList.get(0);
										// Get the id of Tax
										System.out.println("Record Tax id: " + tax.getId());

										// Get the value of Tax
										System.out.println("Record Tax value: " + tax.getValue());
									}
									else if (dataList.get(0) instanceof Tag)
									{
										@SuppressWarnings("unchecked")
										List<Tag> tagList = (List<Tag>) value;

										for (Tag tag : tagList)
										{
											// Get the Name of each Tag
											System.out.println("Record Tag Name: " + tag.getName());

											// Get the Id of each Tag
											System.out.println("Record Tag ID: " + tag.getId());
										}
									}
									else if (dataList.get(0) instanceof PricingDetails)
									{
										@SuppressWarnings("unchecked")
										List<PricingDetails> pricingDetails = (List<PricingDetails>) value;

										for (PricingDetails pricingDetail : pricingDetails)
										{
											System.out.println("Record PricingDetails ToRange: " + pricingDetail.getToRange().toString());

											System.out.println("Record PricingDetails Discount: " + pricingDetail.getDiscount().toString());

											System.out.println("Record PricingDetails ID: " + pricingDetail.getId());

											System.out.println("Record PricingDetails FromRange: " + pricingDetail.getFromRange().toString());
										}
									}
									else if (dataList.get(0) instanceof com.zoho.crm.api.record.Record)
									{
										@SuppressWarnings("unchecked")
										List<com.zoho.crm.api.record.Record> recordList = (List<com.zoho.crm.api.record.Record>) dataList;

										for (com.zoho.crm.api.record.Record record1 : recordList)
										{
											// Get the details map
											for (Map.Entry<String, Object> entry1 : record1.getKeyValues().entrySet())
											{
												// Get each value in the map
												System.out.println(entry1.getKey() + ": " + entry1.getValue());
											}
										}
									}
									else if (dataList.get(0) instanceof LineTax)
									{
										@SuppressWarnings("unchecked")
										List<LineTax> lineTaxes = (List<LineTax>) dataList;

										for (LineTax lineTax : lineTaxes)
										{
											System.out.println("Record ProductDetails LineTax Percentage: " + lineTax.getPercentage().toString());

											System.out.println("Record ProductDetails LineTax Name: " + lineTax.getName());

											System.out.println("Record ProductDetails LineTax Id: " + lineTax.getId());

											System.out.println("Record ProductDetails LineTax Value: " + lineTax.getValue().toString());
										}
									}
									else if (dataList.get(0) instanceof Comment)
									{
										@SuppressWarnings("unchecked")
										List<Comment> comments = (List<Comment>) dataList;

										for (Comment comment : comments)
										{
											System.out.println("Record Comment CommentedBy: " + comment.getCommentedBy());

											System.out.println("Record Comment CommentedTime: " + comment.getCommentedTime().toString());

											System.out.println("Record Comment CommentContent: " + comment.getCommentContent());

											System.out.println("Record Comment Id: " + comment.getId());
										}
									}
									else if (dataList.get(0) instanceof Attachment)
									{
										// Get the list of obtained Attachment instances
										@SuppressWarnings("unchecked")
										List<com.zoho.crm.api.attachments.Attachment> attachments = (List<Attachment>) dataList;

										for (com.zoho.crm.api.attachments.Attachment attachment : attachments)
										{
											// Get the owner User instance of each attachment
											com.zoho.crm.api.users.MinifiedUser owner = attachment.getOwner();

											// Check if owner is not null
											if (owner != null)
											{
												// Get the Name of the Owner
												System.out.println("Record Attachment Owner User-Name: " + owner.getName());

												// Get the ID of the Owner
												System.out.println("Record Attachment Owner User-ID: " + owner.getId());

												// Get the Email of the Owner
												System.out.println("Record Attachment Owner User-Email: " + owner.getEmail());
											}

											// Get the modified time of each attachment
											System.out.println("Record Attachment Modified Time: " + attachment.getModifiedTime().toString());

											// Get the name of the File
											System.out.println("Record Attachment File Name: " + attachment.getFileName());

											// Get the created time of each attachment
											System.out.println("Record Attachment Created Time: " + attachment.getCreatedTime().toString());

											// Get the Attachment file size
											System.out.println("Record Attachment File Size: " + attachment.getSize().toString());

											// Get the parentId Record instance of each attachment
											ParentId parentId = attachment.getParentId();

											// Check if parentId is not null
											if (parentId != null)
											{
												// Get the parent record Name of each attachment
												System.out.println("Record Attachment parent record Name: " + parentId.getName());

												// Get the parent record ID of each attachment
												System.out.println("Record Attachment parent record ID: " + parentId.getId());
											}

											// Get the attachment is Editable
											System.out.println("Record Attachment is Editable: " + attachment.getEditable().toString());

											// Get the file ID of each attachment
											System.out.println("Record Attachment File ID: " + attachment.getFileId());

											// Get the type of each attachment
											System.out.println("Record Attachment File Type: " + attachment.getType());

											// Get the seModule of each attachment
											System.out.println("Record Attachment seModule: " + attachment.getSeModule());

											// Get the modifiedBy User instance of each attachment
											modifiedBy = attachment.getModifiedBy();

											// Check if modifiedBy is not null
											if (modifiedBy != null)
											{
												// Get the Name of the modifiedBy User
												System.out.println("Record Attachment Modified By User-Name: " + modifiedBy.getName());

												// Get the ID of the modifiedBy User
												System.out.println("Record Attachment Modified By User-ID: " + modifiedBy.getId());

												// Get the Email of the modifiedBy User
												System.out.println("Record Attachment Modified By User-Email: " + modifiedBy.getEmail());
											}

											// Get the state of each attachment
											System.out.println("Record Attachment State: " + attachment.getState());

											// Get the ID of each attachment
											System.out.println("Record Attachment ID: " + attachment.getId());

											// Get the createdBy User instance of each attachment
											createdBy = attachment.getCreatedBy();

											// Check if createdBy is not null
											if (createdBy != null)
											{
												// Get the name of the createdBy User
												System.out.println("Record Attachment Created By User-Name: " + createdBy.getName());

												// Get the ID of the createdBy User
												System.out.println("Record Attachment Created By User-ID: " + createdBy.getId());

												// Get the Email of the createdBy User
												System.out.println("Record Attachment Created By User-Email: " + createdBy.getEmail());
											}

											// Get the linkUrl of each attachment
											System.out.println("Record Attachment LinkUrl: " + attachment.getLinkUrl());
										}
									}
									else
									{
										System.out.println(keyName + ": " + value);
									}
								}
							}
							else if (value instanceof com.zoho.crm.api.record.Record)
							{
								com.zoho.crm.api.record.Record recordValue = (com.zoho.crm.api.record.Record) value;

								System.out.println("Record " + keyName + " ID: " + recordValue.getId());

								System.out.println("Record " + keyName + " Name: " + recordValue.getKeyValue("name"));
							}
							else if (value instanceof Layout)
							{
								com.zoho.crm.api.layouts.Layout layout = (Layout) value;

								if (layout != null)
								{
									System.out.println("Record " + keyName + " ID: " + layout.getId());

									System.out.println("Record " + keyName + " Name: " + layout.getName());
								}
							}
							else if (value instanceof MinifiedUser)
							{
								com.zoho.crm.api.users.MinifiedUser user = (MinifiedUser) value;

								if (user != null)
								{
									System.out.println("Record " + keyName + " User-ID: " + user.getId());

									System.out.println("Record " + keyName + " User-Name: " + user.getName());

									System.out.println("Record " + keyName + " User-Email: " + user.getEmail());
								}
							}
							else if (value instanceof Choice<?>)
							{
								System.out.println(keyName + ": " + ((Choice<?>) value).getValue());
							}
							else if (value instanceof RemindAt)
							{
								System.out.println(keyName + ": " + ((RemindAt) value).getAlarm());
							}
							else if (value instanceof RecurringActivity)
							{
								System.out.println(keyName);

								System.out.println("RRULE" + ": " + ((RecurringActivity) value).getRrule());
							}
							else if (value instanceof Consent)
							{
								Consent consent = (Consent) value;

								System.out.println("Record Consent ID: " + consent.getId());

								// Get the Owner User instance of each attachment
								MinifiedUser owner = consent.getOwner();

								// Check if owner is not null
								if (owner != null)
								{
									// Get the name of the owner User
									System.out.println("Record Consent Owner Name: " + owner.getName());

									// Get the ID of the owner User
									System.out.println("Record Consent Owner ID: " + owner.getId());

									// Get the Email of the owner User
									System.out.println("Record Consent Owner Email: " + owner.getEmail());
								}

								MinifiedUser consentCreatedBy = consent.getCreatedBy();

								// Check if createdBy is not null
								if (consentCreatedBy != null)
								{
									// Get the name of the CreatedBy User
									System.out.println("Record Consent CreatedBy Name: " + consentCreatedBy.getName());

									// Get the ID of the CreatedBy User
									System.out.println("Record Consent CreatedBy ID: " + consentCreatedBy.getId());

									// Get the Email of the CreatedBy User
									System.out.println("Record Consent CreatedBy Email: " + consentCreatedBy.getEmail());
								}

								MinifiedUser consentModifiedBy = consent.getModifiedBy();

								// Check if createdBy is not null
								if (consentModifiedBy != null)
								{
									// Get the name of the ModifiedBy User
									System.out.println("Record Consent ModifiedBy Name: " + consentModifiedBy.getName());

									// Get the ID of the ModifiedBy User
									System.out.println("Record Consent ModifiedBy ID: " + consentModifiedBy.getId());

									// Get the Email of the ModifiedBy User
									System.out.println("Record Consent ModifiedBy Email: " + consentModifiedBy.getEmail());
								}

								System.out.println("Record Consent CreatedTime: " + consent.getCreatedTime());

								System.out.println("Record Consent ModifiedTime: " + consent.getModifiedTime());

								System.out.println("Record Consent ContactThroughEmail: " + consent.getContactThroughEmail());

								System.out.println("Record Consent ContactThroughSocial: " + consent.getContactThroughSocial());

								System.out.println("Record Consent ContactThroughSurvey: " + consent.getContactThroughSurvey());

								System.out.println("Record Consent ContactThroughPhone: " + consent.getContactThroughPhone());

								System.out.println("Record Consent MailSentTime: " + consent.getMailSentTime().toString());

								System.out.println("Record Consent ConsentDate: " + consent.getConsentDate().toString());

								System.out.println("Record Consent ConsentRemarks: " + consent.getConsentRemarks());

								System.out.println("Record Consent ConsentThrough: " + consent.getConsentThrough());

								System.out.println("Record Consent DataProcessingBasis: " + consent.getDataProcessingBasis());

								// To get custom values
								System.out.println("Record Consent Lawful Reason: " + consent.getKeyValue("Lawful_Reason"));
							}
							else
							{
								// Get each value in the map
								System.out.println(keyName + ": " + value);
							}
						}
					}
				}
				else if (responseHandler instanceof FileBodyWrapper)
				{
					// Get object from response
					FileBodyWrapper fileBodyWrapper = (FileBodyWrapper) responseHandler;

					// Get StreamWrapper instance from the returned FileBodyWrapper instance
					StreamWrapper streamWrapper = fileBodyWrapper.getFile();

					// Create a file instance with the absolute_file_path
					File file = new File(destinationFolder + File.separatorChar + streamWrapper.getName());

					// Get InputStream from the response
					InputStream is = streamWrapper.getStream();

					// Create an OutputStream for the destination file
					OutputStream os = new FileOutputStream(file);

					byte[] buffer = new byte[1024];

					int bytesRead;

					// read the InputStream till the end
					while ((bytesRead = is.read(buffer)) != -1)
					{
						// write data to OutputStream
						os.write(buffer, 0, bytesRead);
					}

					// Close the InputStream
					is.close();

					// Flush and close the OutputStream
					os.flush();

					os.close();
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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	/**
	 * <h3>Update Record Using External Id</h3> This method is used to update a single record of a module with ID and print the response.
	 * 
	 * @param moduleAPIName - The API Name of the record's module.
	 * @param recordId      - The ID of the record to be updated.
	 * @throws Exception
	 */
	public static void updateRecordUsingExternalId(String moduleAPIName, String externalFieldValue) throws Exception
	{
		// API Name of the module
		// String moduleAPIName = "Leads";
		// String externalFieldValue = "347706151772";

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper request = new BodyWrapper();

		// List of Record instances
		List<com.zoho.crm.api.record.Record> records = new ArrayList<com.zoho.crm.api.record.Record>();

		// Get instance of Record Class
		com.zoho.crm.api.record.Record record1 = new com.zoho.crm.api.record.Record();

		/*
		 * Call addFieldValue method that takes two arguments 1 -> Call Field "." and choose the module from the displayed list and press "." and choose the field name from the displayed list. 2 -> Value
		 */
		record1.addFieldValue(Field.Leads.CITY, "City");

		record1.addFieldValue(Field.Leads.LAST_NAME, "Last Name");

		record1.addFieldValue(Field.Leads.FIRST_NAME, "First Name");

		record1.addFieldValue(Field.Leads.LAST_NAME, "Last Name");

		record1.addFieldValue(Field.Leads.COMPANY, "KKRNP");

		/*
		 * Call addKeyValue method that takes two arguments 1 -> A string that is the Field's API Name 2 -> Value
		 */
		record1.addKeyValue("Custom_field", "Value");

		record1.addKeyValue("Custom_field_2", "value");

		record1.addKeyValue("Date_Time_2", OffsetDateTime.of(2019, 11, 20, 10, 00, 01, 00, ZoneOffset.of("+05:30")));

		record1.addKeyValue("Date_1", LocalDate.of(2017, 1, 13));

		List<FileDetails> fileDetails = new ArrayList<FileDetails>();

		FileDetails fileDetail1 = new FileDetails();

		fileDetail1.setAttachmentId("347706174116");

		fileDetail1.setDelete(null);

		fileDetails.add(fileDetail1);

		FileDetails fileDetail2 = new FileDetails();

		fileDetail2.setFileId("xxxxxx");

		fileDetails.add(fileDetail2);

		FileDetails fileDetail3 = new FileDetails();

		fileDetail3.setFileId("xxxxxx");

		fileDetails.add(fileDetail3);

		record1.addKeyValue("File_Upload", fileDetails);

		com.zoho.crm.api.users.MinifiedUser recordOwner = new com.zoho.crm.api.users.MinifiedUser();

		recordOwner.setEmail("abc@zoho.com");

		record1.addKeyValue("Owner", recordOwner);

		// Used when GDPR is enabled
		Consent dataConsent = new Consent();

		dataConsent.setConsentRemarks("Approved.");

		dataConsent.setConsentThrough("Email");

		dataConsent.setContactThroughEmail(true);

		dataConsent.setContactThroughSocial(false);

		record1.addKeyValue("Data_Processing_Basis_Details", dataConsent);

		List<com.zoho.crm.api.record.Record> subformList = new ArrayList<com.zoho.crm.api.record.Record>();

		com.zoho.crm.api.record.Record subform = new com.zoho.crm.api.record.Record();

		subform.addKeyValue("Subform FieldAPIName", "FieldValue");

		subformList.add(subform);

		record1.addKeyValue("Subform Name", subformList);

		/** Following methods are being used only by Inventory modules */

		com.zoho.crm.api.record.Record dealName = new com.zoho.crm.api.record.Record();

		dealName.addFieldValue(Field.Deals.ID, 34770614995070l);

		record1.addFieldValue(Field.Sales_Orders.DEAL_NAME, dealName);

		com.zoho.crm.api.record.Record contactName = new com.zoho.crm.api.record.Record();

		contactName.addFieldValue(Field.Contacts.ID, 34770614977055l);

		record1.addFieldValue(Field.Purchase_Orders.CONTACT_NAME, contactName);

		com.zoho.crm.api.record.Record accountName = new com.zoho.crm.api.record.Record();

		accountName.addKeyValue("name", "automatedAccount");

		record1.addFieldValue(Field.Quotes.ACCOUNT_NAME, accountName);

		ArrayList<com.zoho.crm.api.record.Record> inventoryLineItemList = new ArrayList<com.zoho.crm.api.record.Record>();

		com.zoho.crm.api.record.Record inventoryLineItem = new com.zoho.crm.api.record.Record();

		LineItemProduct lineItemProduct = new LineItemProduct();

		lineItemProduct.setId(347706153569L);

		inventoryLineItem.addKeyValue("Description", "asd");
		inventoryLineItem.addKeyValue("Discount", "5");
		com.zoho.crm.api.record.Record parentId = new com.zoho.crm.api.record.Record();
		parentId.setId(35240337331017L);
//		inventoryLineItem.addKeyValue("Parent_Id", 5);
		inventoryLineItem.addKeyValue("Sequence_Number", 1L);
		LineItemProduct lineitemProduct = new LineItemProduct();
		lineitemProduct.setId(35240333659082L);
		inventoryLineItem.addKeyValue("Product_Name", lineItemProduct);
		inventoryLineItem.addKeyValue("Sequence_Number", 1l);
		inventoryLineItem.addKeyValue("Quantity", 123.2);
		inventoryLineItem.addKeyValue("Tax", 123.2);
		inventoryLineItemList.add(inventoryLineItem);

		List<LineTax> productLineTaxes = new ArrayList<LineTax>();

		LineTax productLineTax = new LineTax();

		productLineTax.setName("MyT2ax1134");

		productLineTax.setPercentage(20.0);

		productLineTaxes.add(productLineTax);

		inventoryLineItem.addKeyValue("line_tax", productLineTaxes);

		inventoryLineItemList.add(inventoryLineItem);

		record1.addKeyValue("Quoted_Items", inventoryLineItemList);

		List<LineTax> lineTaxes = new ArrayList<LineTax>();

		LineTax lineTax = new LineTax();

		lineTax.setName("MyT2ax1134");

		lineTax.setPercentage(20.0);

		lineTaxes.add(lineTax);

		record1.addKeyValue("$line_tax", lineTaxes);

		/** End Inventory **/

		List<Tag> tagList = new ArrayList<Tag>();

		Tag tag = new Tag();

		tag.setName("Testtask1");

		tagList.add(tag);

		record1.setTag(tagList);

		// Add Record instance to the list
		records.add(record1);

		// Set the list to Records in BodyWrapper instance
		request.setData(records);

		List<String> trigger = new ArrayList<String>();

		trigger.add("approval");

		trigger.add("workflow");

		trigger.add("blueprint");

		request.setTrigger(trigger);

		HeaderMap headerInstance = new HeaderMap();

		headerInstance.add(UpdateRecordHeader.X_EXTERNAL, "Leads.External");

		// Call updateRecordUsingExternalId method that takes externalFieldValue, ModuleAPIName, BodyWrapper instance and headerInstance as parameter.
		APIResponse<ActionHandler> response = recordOperations.updateRecordUsingExternalId(externalFieldValue, moduleAPIName, request, headerInstance);

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
					// Get the received ResponseWrapper instance
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
							System.out.println("Message: " + successResponse.getMessage().getValue());
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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	/**
	 * <h3>Delete Record Using External Id</h3> This method is used to delete a single record of a module with ID and print the response.
	 * 
	 * @param moduleAPIName      - The API Name of the record's module.
	 * @param externalFieldValue -
	 * @throws Exception
	 */
	public static void deleteRecordUsingExternalId(String moduleAPIName, String externalFieldValue) throws Exception
	{
		// API Name of the module to delete record
		// String moduleAPIName = "Leads";
		// String externalFieldValue = "347706151772";

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		// Get instance of ParameterMap Class
		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(DeleteRecordParam.WF_TRIGGER, false);

		HeaderMap headerInstance = new HeaderMap();

		headerInstance.add(DeleteRecordHeader.X_EXTERNAL, "Leads.External");

		// Call deleteRecord method that takes externalFieldValue, moduleAPIName, paramInstance and headerInstance as parameter.
		APIResponse<ActionHandler> response = recordOperations.deleteRecordUsingExternalId(externalFieldValue, moduleAPIName, paramInstance, headerInstance);

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
							System.out.println("Message: " + successResponse.getMessage().getValue());
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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	/**
	 * <h3>Get Records</h3> This method is used to get all the records of a module and print the response.
	 * 
	 * @param moduleAPIName - The API Name of the module to obtain records.
	 * @throws Exception
	 */
	public static void getRecords(String moduleAPIName) throws Exception
	{
		// example
		// String moduleAPIName = "Leads";

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(GetRecordsParam.APPROVED, "both");

		paramInstance.add(GetRecordsParam.CONVERTED, "both");

		paramInstance.add(GetRecordsParam.CVID, "347706187501");

		List<String> ids = new ArrayList<String>(Arrays.asList("TestExternalLead111", "347706143521"));

		paramInstance.add(GetRecordsParam.IDS, String.join(",", ids));

		paramInstance.add(GetRecordsParam.UID, "347706151818");

		List<String> fieldNames = new ArrayList<String>(Arrays.asList("Company", "Email"));

		paramInstance.add(GetRecordsParam.FIELDS, String.join(",", fieldNames));

		paramInstance.add(GetRecordsParam.SORT_BY, "Email");

		paramInstance.add(GetRecordsParam.SORT_ORDER, "desc");

		paramInstance.add(GetRecordsParam.PAGE, 1);

		paramInstance.add(GetRecordsParam.PER_PAGE, 1);

		OffsetDateTime startdatetime = OffsetDateTime.of(2019, 11, 20, 10, 00, 01, 00, ZoneOffset.of("+05:30"));

		paramInstance.add(GetRecordsParam.STARTDATETIME, startdatetime);

		OffsetDateTime enddatetime = OffsetDateTime.of(2019, 12, 20, 10, 00, 01, 00, ZoneOffset.of("+05:30"));

		paramInstance.add(GetRecordsParam.ENDDATETIME, enddatetime);

		paramInstance.add(GetRecordsParam.TERRITORY_ID, "34770613051357");

		paramInstance.add(GetRecordsParam.INCLUDE_CHILD, "true");

		HeaderMap headerInstance = new HeaderMap();

		OffsetDateTime ifmodifiedsince = OffsetDateTime.of(2019, 05, 20, 10, 00, 01, 00, ZoneOffset.of("+05:30"));

		headerInstance.add(GetRecordsHeader.IF_MODIFIED_SINCE, ifmodifiedsince);

//		headerInstance.add(GetRecordsHeader.X_EXTERNAL, "Leads.External");

		// Call getRecords method that takes moduleAPIName, paramInstance and headerInstance as parameter.
		APIResponse<ResponseHandler> response = recordOperations.getRecords(moduleAPIName, paramInstance, headerInstance);

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
				// Get the object from response
				ResponseHandler responseHandler = response.getObject();

				if (responseHandler instanceof ResponseWrapper)
				{
					// Get the received ResponseWrapper instance
					ResponseWrapper responseWrapper = (ResponseWrapper) responseHandler;

					// Get the obtained Record instances
					List<com.zoho.crm.api.record.Record> records = responseWrapper.getData();

					for (com.zoho.crm.api.record.Record record : records)
					{
						// Get the ID of each Record
						System.out.println("Record ID: " + record.getId());

						// Get the createdBy User instance of each Record
						com.zoho.crm.api.users.MinifiedUser createdBy = record.getCreatedBy();

						// Check if createdBy is not null
						if (createdBy != null)
						{
							// Get the ID of the createdBy User
							System.out.println("Record Created By User-ID: " + createdBy.getId());

							// Get the name of the createdBy User
							System.out.println("Record Created By User-Name: " + createdBy.getName());

							// Get the Email of the createdBy User
							System.out.println("Record Created By User-Email: " + createdBy.getEmail());
						}

						// Get the CreatedTime of each Record
						System.out.println("Record CreatedTime: " + record.getCreatedTime());

						// Get the modifiedBy User instance of each Record
						com.zoho.crm.api.users.MinifiedUser modifiedBy = record.getModifiedBy();

						// Check if modifiedBy is not null
						if (modifiedBy != null)
						{
							// Get the ID of the modifiedBy User
							System.out.println("Record Modified By User-ID: " + modifiedBy.getId());

							// Get the name of the modifiedBy User
							System.out.println("Record Modified By User-Name: " + modifiedBy.getName());

							// Get the Email of the modifiedBy User
							System.out.println("Record Modified By User-Email: " + modifiedBy.getEmail());
						}

						// Get the ModifiedTime of each Record
						System.out.println("Record ModifiedTime: " + record.getModifiedTime());

						// Get the list of Tag instance each Record
						List<Tag> tags = record.getTag();

						// Check if tags is not null
						if (tags != null)
						{
							for (Tag tag : tags)
							{
								// Get the Name of each Tag
								System.out.println("Record Tag Name: " + tag.getName());

								// Get the Id of each Tag
								System.out.println("Record Tag ID: " + tag.getId());
							}
						}

						// To get particular field value
						System.out.println("Record Field Value: " + record.getKeyValue("Last_Name"));// FieldApiName

						System.out.println("Record KeyValues: ");

						// Get the KeyValue map
						for (Map.Entry<String, Object> entry : record.getKeyValues().entrySet())
						{
							String keyName = entry.getKey();

							Object value = entry.getValue();

							if (value instanceof List)
							{
								List<?> dataList = (List<?>) value;

								if (dataList.size() > 0)
								{
									if (dataList.get(0) instanceof FileDetails)
									{
										@SuppressWarnings("unchecked")
										List<FileDetails> fileDetails = (List<FileDetails>) value;

										for (FileDetails fileDetail : fileDetails)
										{
											// Get the Extn of each FileDetails
											System.out.println("Record FileDetails Extn: " + fileDetail.getExtn());

											// Get the IsPreviewAvailable of each FileDetails
											System.out.println("Record FileDetails IsPreviewAvailable: " + fileDetail.getIsPreviewAvailable());

											// Get the DownloadUrl of each FileDetails
											System.out.println("Record FileDetails DownloadUrl: " + fileDetail.getDownloadUrl());

											// Get the DeleteUrl of each FileDetails
											System.out.println("Record FileDetails DeleteUrl: " + fileDetail.getDeleteUrl());

											// Get the EntityId of each FileDetails
											System.out.println("Record FileDetails EntityId: " + fileDetail.getEntityId());

											// Get the Mode of each FileDetails
											System.out.println("Record FileDetails Mode: " + fileDetail.getMode());

											// Get the OriginalSizeByte of each FileDetails
											System.out.println("Record FileDetails OriginalSizeByte: " + fileDetail.getOriginalSizeByte());

											// Get the PreviewUrl of each FileDetails
											System.out.println("Record FileDetails PreviewUrl: " + fileDetail.getPreviewUrl());

											// Get the FileName of each FileDetails
											System.out.println("Record FileDetails FileName: " + fileDetail.getFileName());

											// Get the FileId of each FileDetails
											System.out.println("Record FileDetails FileId: " + fileDetail.getFileId());

											// Get the AttachmentId of each FileDetails
											System.out.println("Record FileDetails AttachmentId: " + fileDetail.getAttachmentId());

											// Get the FileSize of each FileDetails
											System.out.println("Record FileDetails FileSize: " + fileDetail.getFileSize());

											// Get the CreatorId of each FileDetails
											System.out.println("Record FileDetails CreatorId: " + fileDetail.getCreatorId());

											// Get the LinkDocs of each FileDetails
											System.out.println("Record FileDetails LinkDocs: " + fileDetail.getLinkDocs());
										}
									}
									else if (dataList.get(0) instanceof ImageUpload)
									{
										@SuppressWarnings("unchecked")
										List<ImageUpload> imageUploads = (List<ImageUpload>) dataList;
										for (ImageUpload imageUpload : imageUploads)
										{
											System.out.println("Record  Description: " + imageUpload.getDescription());

											System.out.println("Record  PreviewId: " + imageUpload.getPreviewId());

											System.out.println("Record  File_Name: " + imageUpload.getFileName());

											System.out.println("Record  State: " + imageUpload.getState());

											System.out.println("Record  Size: " + imageUpload.getSize());

											System.out.println("Record  SequenceNumber: " + imageUpload.getSequenceNumber());

											System.out.println("Record  Id: " + imageUpload.getId());

											System.out.println("Record  FileId: " + imageUpload.getFileId());
										}
									}
									else if (dataList.get(0) instanceof Tax)
									{
										Tax tax = (Tax) dataList.get(0);
										// Get the id of Tax
										System.out.println("Record Tax id: " + tax.getId());

										// Get the value of Tax
										System.out.println("Record Tax value: " + tax.getValue());
									}

									else if (dataList.get(0) instanceof Tag)
									{
										@SuppressWarnings("unchecked")
										List<Tag> tagList = (List<Tag>) value;

										for (Tag tag : tagList)
										{
											// Get the Name of each Tag
											System.out.println("Record Tag Name: " + tag.getName());

											// Get the Id of each Tag
											System.out.println("Record Tag ID: " + tag.getId());
										}
									}
									else if (dataList.get(0) instanceof PricingDetails)
									{
										@SuppressWarnings("unchecked")
										List<PricingDetails> pricingDetails = (List<PricingDetails>) value;

										for (PricingDetails pricingDetail : pricingDetails)
										{
											System.out.println("Record PricingDetails ToRange: " + pricingDetail.getToRange().toString());

											System.out.println("Record PricingDetails Discount: " + pricingDetail.getDiscount().toString());

											System.out.println("Record PricingDetails ID: " + pricingDetail.getId());

											System.out.println("Record PricingDetails FromRange: " + pricingDetail.getFromRange().toString());
										}
									}
									else if (dataList.get(0) instanceof Participants)
									{
										@SuppressWarnings("unchecked")
										List<Participants> participants = (List<Participants>) value;

										for (Participants participant : participants)
										{
											System.out.println("Record Participants Name: " + participant.getName());

											System.out.println("Record Participants Invited: " + participant.getInvited().toString());

											System.out.println("Record Participants ID: " + participant.getId());

											System.out.println("Record Participants Type: " + participant.getType());

											System.out.println("Record Participants Participant: " + participant.getParticipant());

											System.out.println("Record Participants Status: " + participant.getStatus());
										}
									}
									else if (dataList.get(0) instanceof com.zoho.crm.api.record.Record)
									{
										@SuppressWarnings("unchecked")
										List<com.zoho.crm.api.record.Record> recordList = (List<com.zoho.crm.api.record.Record>) dataList;

										for (com.zoho.crm.api.record.Record record1 : recordList)
										{
											// Get the details map
											for (Map.Entry<String, Object> entry1 : record1.getKeyValues().entrySet())
											{
												// Get each value in the map
												System.out.println(entry1.getKey() + ": " + entry1.getValue());
											}
										}
									}
									else if (dataList.get(0) instanceof LineTax)
									{
										@SuppressWarnings("unchecked")
										List<LineTax> lineTaxes = (List<LineTax>) dataList;

										for (LineTax lineTax : lineTaxes)
										{
											System.out.println("Record ProductDetails LineTax Percentage: " + lineTax.getPercentage().toString());

											System.out.println("Record ProductDetails LineTax Name: " + lineTax.getName());

											System.out.println("Record ProductDetails LineTax Id: " + lineTax.getId());

											System.out.println("Record ProductDetails LineTax Value: " + lineTax.getValue().toString());
										}
									}
									else if (dataList.get(0) instanceof Choice<?>)
									{
										@SuppressWarnings("unchecked")
										List<Choice<?>> choiceList = (List<Choice<?>>) dataList;

										System.out.println(keyName);

										System.out.println("values");

										for (Choice<?> choice : choiceList)
										{
											System.out.println(choice.getValue());
										}
									}
									else if (dataList.get(0) instanceof Comment)
									{
										@SuppressWarnings("unchecked")
										List<Comment> comments = (List<Comment>) dataList;

										for (Comment comment : comments)
										{
											System.out.println("Record Comment CommentedBy: " + comment.getCommentedBy());

											System.out.println("Record Comment CommentedTime: " + comment.getCommentedTime().toString());

											System.out.println("Record Comment CommentContent: " + comment.getCommentContent());

											System.out.println("Record Comment Id: " + comment.getId());
										}
									}
									else if (dataList.get(0) instanceof Attachment)
									{
										// Get the list of obtained Attachment instances
										@SuppressWarnings("unchecked")
										List<com.zoho.crm.api.attachments.Attachment> attachments = (List<Attachment>) dataList;

										for (com.zoho.crm.api.attachments.Attachment attachment : attachments)
										{
											// Get the owner User instance of each attachment
											com.zoho.crm.api.users.MinifiedUser owner = attachment.getOwner();

											// Check if owner is not null
											if (owner != null)
											{
												// Get the Name of the Owner
												System.out.println("Record Attachment Owner User-Name: " + owner.getName());

												// Get the ID of the Owner
												System.out.println("Record Attachment Owner User-ID: " + owner.getId());

												// Get the Email of the Owner
												System.out.println("Record Attachment Owner User-Email: " + owner.getEmail());
											}

											// Get the modified time of each attachment
											System.out.println("Record Attachment Modified Time: " + attachment.getModifiedTime().toString());

											// Get the name of the File
											System.out.println("Record Attachment File Name: " + attachment.getFileName());

											// Get the created time of each attachment
											System.out.println("Record Attachment Created Time: " + attachment.getCreatedTime().toString());

											// Get the Attachment file size
											System.out.println("Record Attachment File Size: " + attachment.getSize().toString());

											// Get the parentId Record instance of each attachment
											ParentId parentId = attachment.getParentId();

											// Check if parentId is not null
											if (parentId != null)
											{
												// Get the parent record Name of each attachment
												System.out.println("Record Attachment parent record Name: " + parentId.getName());

												// Get the parent record ID of each attachment
												System.out.println("Record Attachment parent record ID: " + parentId.getId());
											}

											// Get the attachment is Editable
											System.out.println("Record Attachment is Editable: " + attachment.getEditable().toString());

											// Get the file ID of each attachment
											System.out.println("Record Attachment File ID: " + attachment.getFileId());

											// Get the type of each attachment
											System.out.println("Record Attachment File Type: " + attachment.getType());

											// Get the seModule of each attachment
											System.out.println("Record Attachment seModule: " + attachment.getSeModule());

											// Get the modifiedBy User instance of each attachment
											modifiedBy = attachment.getModifiedBy();

											// Check if modifiedBy is not null
											if (modifiedBy != null)
											{
												// Get the Name of the modifiedBy User
												System.out.println("Record Attachment Modified By User-Name: " + modifiedBy.getName());

												// Get the ID of the modifiedBy User
												System.out.println("Record Attachment Modified By User-ID: " + modifiedBy.getId());

												// Get the Email of the modifiedBy User
												System.out.println("Record Attachment Modified By User-Email: " + modifiedBy.getEmail());
											}

											// Get the state of each attachment
											System.out.println("Record Attachment State: " + attachment.getState());

											// Get the ID of each attachment
											System.out.println("Record Attachment ID: " + attachment.getId());

											// Get the createdBy User instance of each attachment
											createdBy = attachment.getCreatedBy();

											// Check if createdBy is not null
											if (createdBy != null)
											{
												// Get the name of the createdBy User
												System.out.println("Record Attachment Created By User-Name: " + createdBy.getName());

												// Get the ID of the createdBy User
												System.out.println("Record Attachment Created By User-ID: " + createdBy.getId());

												// Get the Email of the createdBy User
												System.out.println("Record Attachment Created By User-Email: " + createdBy.getEmail());
											}

											// Get the linkUrl of each attachment
											System.out.println("Record Attachment LinkUrl: " + attachment.getLinkUrl());
										}
									}
									else
									{
										System.out.println(keyName + ": " + value);
									}
								}
							}
							else if (value instanceof Layout)
							{
								com.zoho.crm.api.layouts.Layout layout = (Layout) value;

								if (layout != null)
								{
									System.out.println("Record " + keyName + " ID: " + layout.getId());

									System.out.println("Record " + keyName + " Name: " + layout.getName());
								}
							}
							else if (value instanceof MinifiedUser)
							{
								com.zoho.crm.api.users.MinifiedUser user = (MinifiedUser) value;

								if (user != null)
								{
									System.out.println("Record " + keyName + " User-ID: " + user.getId());

									System.out.println("Record " + keyName + " User-Name: " + user.getName());

									System.out.println("Record " + keyName + " User-Email: " + user.getEmail());
								}
							}
							else if (value instanceof com.zoho.crm.api.record.Record)
							{
								com.zoho.crm.api.record.Record recordValue = (com.zoho.crm.api.record.Record) value;

								System.out.println("Record " + keyName + " ID: " + recordValue.getId());

								System.out.println("Record " + keyName + " Name: " + recordValue.getKeyValue("name"));
							}
							else if (value instanceof Choice<?>)
							{
								System.out.println(keyName + ": " + ((Choice<?>) value).getValue());
							}
							else if (value instanceof RemindAt)
							{
								System.out.println(keyName + ": " + ((RemindAt) value).getAlarm());
							}
							else if (value instanceof RecurringActivity)
							{
								System.out.println(keyName);

								System.out.println("RRULE" + ": " + ((RecurringActivity) value).getRrule());
							}
							else if (value instanceof Consent)
							{
								Consent consent = (Consent) value;

								System.out.println("Record Consent ID: " + consent.getId());

								// Get the Owner User instance of each attachment
								MinifiedUser owner = consent.getOwner();

								// Check if owner is not null
								if (owner != null)
								{
									// Get the name of the owner User
									System.out.println("Record Consent Owner Name: " + owner.getName());

									// Get the ID of the owner User
									System.out.println("Record Consent Owner ID: " + owner.getId());

									// Get the Email of the owner User
									System.out.println("Record Consent Owner Email: " + owner.getEmail());
								}

								MinifiedUser consentCreatedBy = consent.getCreatedBy();

								// Check if createdBy is not null
								if (consentCreatedBy != null)
								{
									// Get the name of the CreatedBy User
									System.out.println("Record Consent CreatedBy Name: " + consentCreatedBy.getName());

									// Get the ID of the CreatedBy User
									System.out.println("Record Consent CreatedBy ID: " + consentCreatedBy.getId());

									// Get the Email of the CreatedBy User
									System.out.println("Record Consent CreatedBy Email: " + consentCreatedBy.getEmail());
								}

								MinifiedUser consentModifiedBy = consent.getModifiedBy();

								// Check if createdBy is not null
								if (consentModifiedBy != null)
								{
									// Get the name of the ModifiedBy User
									System.out.println("Record Consent ModifiedBy Name: " + consentModifiedBy.getName());

									// Get the ID of the ModifiedBy User
									System.out.println("Record Consent ModifiedBy ID: " + consentModifiedBy.getId());

									// Get the Email of the ModifiedBy User
									System.out.println("Record Consent ModifiedBy Email: " + consentModifiedBy.getEmail());
								}

								System.out.println("Record Consent CreatedTime: " + consent.getCreatedTime());

								System.out.println("Record Consent ModifiedTime: " + consent.getModifiedTime());

								System.out.println("Record Consent ContactThroughEmail: " + consent.getContactThroughEmail());

								System.out.println("Record Consent ContactThroughSocial: " + consent.getContactThroughSocial());

								System.out.println("Record Consent ContactThroughSurvey: " + consent.getContactThroughSurvey());

								System.out.println("Record Consent ContactThroughPhone: " + consent.getContactThroughPhone());

								System.out.println("Record Consent MailSentTime: " + consent.getMailSentTime().toString());

								System.out.println("Record Consent ConsentDate: " + consent.getConsentDate().toString());

								System.out.println("Record Consent ConsentRemarks: " + consent.getConsentRemarks());

								System.out.println("Record Consent ConsentThrough: " + consent.getConsentThrough());

								System.out.println("Record Consent DataProcessingBasis: " + consent.getDataProcessingBasis());

								// To get custom values
								System.out.println("Record Consent Lawful Reason: " + consent.getKeyValue("Lawful_Reason"));
							}
							else
							{
								// Get each value in the map
								System.out.println(keyName + ": " + value);
							}
						}
					}

					// Get the Object obtained Info instance
					Info info = responseWrapper.getInfo();

					// Check if info is not null
					if (info != null)
					{
						if (info.getPerPage() != null)
						{
							// Get the PerPage of the Info
							System.out.println("Record Info PerPage: " + info.getPerPage().toString());
						}

						if (info.getCount() != null)
						{
							// Get the Count of the Info
							System.out.println("Record Info Count: " + info.getCount().toString());
						}

						if (info.getPage() != null)
						{
							// Get the Page of the Info
							System.out.println("Record Info Page: " + info.getPage().toString());
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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	/**
	 * <h3>Create Records</h3> This method is used to create records of a module and print the response.
	 * 
	 * @param moduleAPIName - The API Name of the module to create records.
	 * @throws Exception
	 */
	public static void createRecords(String moduleAPIName) throws Exception
	{
		// API Name of the module to create records
		// String moduleAPIName = "Leads";

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper bodyWrapper = new BodyWrapper();

		// List of Record instances
		List<com.zoho.crm.api.record.Record> records = new ArrayList<com.zoho.crm.api.record.Record>();

		// Get instance of Record Class
		com.zoho.crm.api.record.Record record1 = new com.zoho.crm.api.record.Record();

		/*
		 * Call addFieldValue method that takes two arguments 1 -> Call Field "." and choose the module from the displayed list and press "." and choose the field name from the displayed list. 2 -> Value
		 */
		record1.addFieldValue(Field.Leads.CITY, "City");

		record1.addFieldValue(Field.Leads.LAST_NAME, "Last Name");

		record1.addFieldValue(Field.Leads.FIRST_NAME, "First Name");

		record1.addFieldValue(Field.Leads.COMPANY, "KKRNP");

		record1.addFieldValue(Field.Leads.LEAD_SOURCE, new Choice<String>("Advertisement"));

		/*
		 * Call addKeyValue method that takes two arguments 1 -> A string that is the Field's API Name 2 -> Value
		 */
		record1.addKeyValue("External", "Value12345");

		record1.addKeyValue("Custom_field", "Value");

		record1.addKeyValue("Date_Time_2", OffsetDateTime.of(2019, 11, 20, 10, 00, 01, 00, ZoneOffset.of("+05:30")));

		record1.addKeyValue("Date_1", LocalDate.of(2021, 1, 13));

		record1.addKeyValue("Subject", "AutomatedSDK");
		
		record1.addKeyValue("Product_Name", "AutomatedSDK");

		List<FileDetails> fileDetails = new ArrayList<FileDetails>();

		FileDetails fileDetail1 = new FileDetails();

		fileDetail1.setFileId("ae9c7cefxxxxxx44183d280");

		fileDetails.add(fileDetail1);

		FileDetails fileDetail2 = new FileDetails();

		fileDetail2.setFileId("ae9c7cxxxxxca878a934519e6cdb2");

		fileDetails.add(fileDetail2);

		FileDetails fileDetail3 = new FileDetails();

		fileDetail3.setFileId("ae9c7cefxxxxx6f155795981f");

		fileDetails.add(fileDetail3);

		record1.addKeyValue("File_Upload", fileDetails);

		List<com.zoho.crm.api.record.Record> subformList = new ArrayList<com.zoho.crm.api.record.Record>();

		com.zoho.crm.api.record.Record subform = new com.zoho.crm.api.record.Record();

		subform.addKeyValue("DOB", "sep");

		subform.addKeyValue("Name1", "test");

		subformList.add(subform);

		record1.addKeyValue("Subform_1", subformList);

		// Used when GDPR is enabled
		Consent dataConsent = new Consent();

		dataConsent.setConsentRemarks("Approved.");

		dataConsent.setConsentThrough("Email");

		dataConsent.setContactThroughEmail(true);

		dataConsent.setContactThroughSocial(false);

		record1.addKeyValue("Data_Processing_Basis_Details", dataConsent);

		/** Following methods are being used only by Inventory modules */

		com.zoho.crm.api.record.Record dealName = new com.zoho.crm.api.record.Record();

		dealName.addFieldValue(Field.Deals.ID, 3477061113837l);

		record1.addFieldValue(Field.Sales_Orders.DEAL_NAME, dealName);

		com.zoho.crm.api.record.Record contactName = new com.zoho.crm.api.record.Record();

		contactName.addFieldValue(Field.Contacts.ID, 3477061118531l);

		record1.addFieldValue(Field.Purchase_Orders.CONTACT_NAME, contactName);

		com.zoho.crm.api.record.Record accountName = new com.zoho.crm.api.record.Record();

		accountName.addKeyValue("name", "automatedAccount");

		record1.addFieldValue(Field.Quotes.ACCOUNT_NAME, accountName);

		ArrayList<com.zoho.crm.api.record.Record> inventoryLineItemList = new ArrayList<com.zoho.crm.api.record.Record>();

		com.zoho.crm.api.record.Record inventoryLineItem = new com.zoho.crm.api.record.Record();

		LineItemProduct lineItemProduct = new LineItemProduct();

		lineItemProduct.setId(3477061112174l);

		lineItemProduct.addKeyValue("Products_External", "ProductExternal");

		inventoryLineItem.addKeyValue("Description", "asd");

		inventoryLineItem.addKeyValue("Discount", "5");

		com.zoho.crm.api.record.Record parentId = new com.zoho.crm.api.record.Record();

		parentId.setId(35240337331017L);

		inventoryLineItem.addKeyValue("Parent_Id", parentId);

		LineItemProduct lineitemProduct = new LineItemProduct();

		lineitemProduct.setId(3477061136271l);

		inventoryLineItem.addKeyValue("Product_Name", lineItemProduct);

//		inventoryLineItem.addKeyValue("Sequence_Number", 2l);

		inventoryLineItem.addKeyValue("Quantity", 123.2);

		inventoryLineItem.addKeyValue("Tax", 123.2);

//		inventoryLineItemList.add(inventoryLineItem);

		List<LineTax> productLineTaxes = new ArrayList<LineTax>();

		LineTax productLineTax = new LineTax();
		
		productLineTax.setName("MyTax1134");
		
		productLineTax.setValue(0.0);
		
		productLineTax.setId(3477061107433l);

		productLineTax.setPercentage(15.0);

		productLineTaxes.add(productLineTax);

		inventoryLineItem.addKeyValue("Line_Tax", productLineTaxes);

		inventoryLineItemList.add(inventoryLineItem);

		record1.addKeyValue("Quoted_Items", inventoryLineItemList);

		List<LineTax> lineTaxes = new ArrayList<LineTax>();

		LineTax lineTax = new LineTax();

		lineTax.setName("MyTax1134");

		lineTax.setPercentage(20.0);

		lineTaxes.add(lineTax);

		record1.addKeyValue("$line_tax", lineTaxes);
		/** End Inventory **/

		/** Following methods are being used only by Activity modules */

//		Tasks
		record1.addFieldValue(Field.Tasks.DESCRIPTION, "Test Task");

		record1.addKeyValue("Currency", new Choice<String>("INR"));

		RemindAt remindAt = new RemindAt();

		remindAt.setAlarm("ACTION=EMAILANDPOPUP;TRIGGER=DATE-TIME:2020-07-03T12:30:00+05:30");

		record1.addFieldValue(Field.Tasks.REMIND_AT, remindAt);

		com.zoho.crm.api.record.Record whoId = new com.zoho.crm.api.record.Record();

		whoId.setId(34770614977055L);

		record1.addFieldValue(Field.Tasks.WHO_ID, whoId);

		record1.addFieldValue(Field.Tasks.STATUS, new Choice<String>("Waiting on someone else"));

		record1.addFieldValue(Field.Tasks.DUE_DATE, LocalDate.of(2021, 1, 13));

		record1.addFieldValue(Field.Tasks.PRIORITY, new Choice<String>("High"));

		record1.addFieldValue(Field.Tasks.SUBJECT, "Email1");

		record1.addKeyValue("$se_module", "Accounts");

		com.zoho.crm.api.record.Record whatId = new com.zoho.crm.api.record.Record();

		whatId.setId(3477061207118L);

		record1.addFieldValue(Field.Tasks.WHAT_ID, whatId);

		/** Recurring Activity can be provided in any activity module */

		RecurringActivity recurringActivity = new RecurringActivity();

		recurringActivity.setRrule("FREQ=DAILY;INTERVAL=10;UNTIL=2023-08-14;DTSTART=2020-07-03");

		record1.addFieldValue(Field.Events.RECURRING_ACTIVITY, recurringActivity);

//		Events
		record1.addFieldValue(Field.Events.DESCRIPTION, "Test Events");

		OffsetDateTime startDateTime = OffsetDateTime.of(2020, 01, 02, 10, 00, 00, 00, ZoneOffset.of("+05:30"));

		record1.addFieldValue(Field.Events.START_DATETIME, startDateTime);

		List<Participants> participants = new ArrayList<Participants>();

		Participants participant1 = new Participants();

		participant1.setEmail("abc@zoho.com");

		participant1.setType("email");

		participant1.setId(34770615902017L);

		participants.add(participant1);

		Participants participant2 = new Participants();

		participant2.addKeyValue("participant", "34770617425");

		participant2.addKeyValue("type", "lead");

		participants.add(participant2);

		record1.addFieldValue(Field.Events.PARTICIPANTS, participants);

		record1.addKeyValue("$send_notification", true);

		record1.addFieldValue(Field.Events.EVENT_TITLE, "New Automated Event");

		OffsetDateTime enddatetime = OffsetDateTime.of(2020, 05, 02, 10, 00, 00, 00, ZoneOffset.of("+05:30"));

		record1.addFieldValue(Field.Events.END_DATETIME, enddatetime);

		ArrayList<Reminder> reminderList = new ArrayList<Reminder>();

		Reminder remindAt1 = new Reminder();

		remindAt1.setPeriod("minutes");

		remindAt1.setUnit(15);

		reminderList.add(remindAt1);

		remindAt1 = new Reminder();

		remindAt1.setPeriod("days");

		remindAt1.setUnit(1);

		remindAt1.setTime("10.30");

		reminderList.add(remindAt1);

		record1.addFieldValue(Field.Events.REMIND_AT, reminderList);

		record1.addFieldValue(Field.Events.CHECK_IN_STATUS, "PLANNED");

		record1.addKeyValue("$se_module", "Leads");

		com.zoho.crm.api.record.Record whatId1 = new com.zoho.crm.api.record.Record();

		whatId1.setId(347706143812L);

		record1.addFieldValue(Field.Events.WHAT_ID, whatId1);

		record1.addKeyValue("Currency", new Choice<String>("USD"));

//		/** End Activity **/
//
//		/** Following methods are being used only by Price_Books modules */
//
		List<PricingDetails> pricingDetails = new ArrayList<PricingDetails>();

		PricingDetails pricingDetail1 = new PricingDetails();

		pricingDetail1.setFromRange(1.0);

		pricingDetail1.setToRange(5.0);

		pricingDetail1.setDiscount(2.0);

		pricingDetails.add(pricingDetail1);

		PricingDetails pricingDetail2 = new PricingDetails();

		pricingDetail2.addKeyValue("from_range", 6.0);

		pricingDetail2.addKeyValue("to_range", 11.0);

		pricingDetail2.addKeyValue("discount", 3.0);

		pricingDetails.add(pricingDetail2);

		record1.addFieldValue(Field.Price_Books.PRICING_DETAILS, pricingDetails);

		record1.addKeyValue("Email", "abc@zoho.com");

		record1.addFieldValue(Field.Price_Books.DESCRIPTION, "TEST");

		record1.addFieldValue(Field.Price_Books.PRICE_BOOK_NAME, "book_name");

		record1.addFieldValue(Field.Price_Books.PRICING_MODEL, new Choice<String>("Flat"));

		List<Tag> tagList = new ArrayList<Tag>();

		Tag tag = new Tag();

		tag.setName("Testtask");

		tagList.add(tag);

		record1.setTag(tagList);

		// Add Record instance to the list
		records.add(record1);

		// Set the list to Records in BodyWrapper instance
		bodyWrapper.setData(records);

		List<String> trigger = new ArrayList<String>();

		trigger.add("approval");

		trigger.add("workflow");

		trigger.add("blueprint");

		bodyWrapper.setTrigger(trigger);

		// bodyWrapper.setLarId("347706187515");

		HeaderMap headerInstance = new HeaderMap();

//		headerInstance.add(CreateRecordsHeader.X_EXTERNAL, "Quotes.Quoted_Items.Product_Name.Products_External");

		// Call createRecords method that takes moduleAPIName, BodyWrapper instance and headerInstance as parameter.
		APIResponse<ActionHandler> response = recordOperations.createRecords(moduleAPIName, bodyWrapper, headerInstance);

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
							System.out.println("Message: " + successResponse.getMessage().getValue());
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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	/**
	 * <h3>Update Records</h3> This method is used to update the records of a module with ID and print the response.
	 * 
	 * @param moduleAPIName - The API Name of the module to update records.
	 * @throws Exception
	 */
	public static void updateRecords(String moduleAPIName) throws Exception
	{
		// API Name of the module to create records
		// String moduleAPIName = "Leads";

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper request = new BodyWrapper();

		// List of Record instances
		List<com.zoho.crm.api.record.Record> records = new ArrayList<com.zoho.crm.api.record.Record>();

		// Get instance of Record Class
		com.zoho.crm.api.record.Record record1 = new com.zoho.crm.api.record.Record();

		record1.setId(3477061120811l);

		/*
		 * Call addFieldValue method that takes two arguments 1 -> Call Field "." and choose the module from the displayed list and press "." and choose the field name from the displayed list. 2 -> Value
		 */
		record1.addFieldValue(Field.Leads.CITY, "City");

		record1.addFieldValue(Field.Leads.LAST_NAME, "Last Name");

		record1.addFieldValue(Field.Leads.FIRST_NAME, "First Name");

		record1.addFieldValue(Field.Leads.COMPANY, "KKRNP");

		/*
		 * Call addKeyValue method that takes two arguments 1 -> A string that is the Field's API Name 2 -> Value
		 */
		record1.addKeyValue("Custom_field", "Value");

		record1.addKeyValue("Custom_field_2", "value");

		// Used when GDPR is enabled
		Consent dataConsent = new Consent();

		dataConsent.setConsentRemarks("Approved.");

		dataConsent.setConsentThrough("Email");

		dataConsent.setContactThroughEmail(true);

		dataConsent.setContactThroughSocial(false);

		record1.addKeyValue("Data_Processing_Basis_Details", dataConsent);

		/** Following methods are being used only by Inventory modules */

		com.zoho.crm.api.record.Record dealName = new com.zoho.crm.api.record.Record();

		dealName.addFieldValue(Field.Deals.ID, 3477061113837l);

		record1.addFieldValue(Field.Sales_Orders.DEAL_NAME, dealName);

		com.zoho.crm.api.record.Record contactName = new com.zoho.crm.api.record.Record();

		contactName.addFieldValue(Field.Contacts.ID, 3477061118531l);

		record1.addFieldValue(Field.Purchase_Orders.CONTACT_NAME, contactName);

		com.zoho.crm.api.record.Record accountName = new com.zoho.crm.api.record.Record();

		accountName.addKeyValue("name", "automatedAccount");

		record1.addFieldValue(Field.Quotes.ACCOUNT_NAME, accountName);

		ArrayList<com.zoho.crm.api.record.Record> inventoryLineItemList = new ArrayList<com.zoho.crm.api.record.Record>();

		com.zoho.crm.api.record.Record inventoryLineItem = new com.zoho.crm.api.record.Record();

		LineItemProduct lineItemProduct = new LineItemProduct();

		lineItemProduct.setId(3477061112174l);

//		lineItemProduct.addKeyValue("Products_External", "TestExternalLead121");

		inventoryLineItem.addKeyValue("Description", "asd");

		inventoryLineItem.addKeyValue("Discount", "5");

		com.zoho.crm.api.record.Record parentId = new com.zoho.crm.api.record.Record();

		parentId.setId(35240337331017L);

		inventoryLineItem.addKeyValue("Parent_Id", parentId);

		LineItemProduct lineitemProduct = new LineItemProduct();

		lineitemProduct.setId(35240333659082L);

		inventoryLineItem.addKeyValue("Product_Name", lineItemProduct);

		inventoryLineItem.addKeyValue("Sequence_Number", 1l);

		inventoryLineItem.addKeyValue("Quantity", 123.2);

		inventoryLineItem.addKeyValue("Tax", 123.2);

		inventoryLineItemList.add(inventoryLineItem);

		List<LineTax> productLineTaxes = new ArrayList<LineTax>();

		LineTax productLineTax = new LineTax();

		productLineTax.setName("MyTax1134");

		productLineTax.setPercentage(20.0);

		productLineTaxes.add(productLineTax);

		inventoryLineItem.addKeyValue("Line_Tax", productLineTaxes);

		inventoryLineItemList.add(inventoryLineItem);

		record1.addKeyValue("Quoted_Items", inventoryLineItemList);

		List<LineTax> lineTaxes = new ArrayList<LineTax>();

		LineTax lineTax = new LineTax();

		lineTax.setName("MyTax1134");

		lineTax.setPercentage(20.0);

		lineTaxes.add(lineTax);

		record1.addKeyValue("$line_tax", lineTaxes);
		/** End Inventory **/

		// Add Record instance to the list
		records.add(record1);

		// Get instance of Record Class
		com.zoho.crm.api.record.Record record2 = new com.zoho.crm.api.record.Record();

		record2.setId(347706158445l);

		/*
		 * Call addFieldValue method that takes two arguments 1 -> Call Field "." and choose the module from the displayed list and press "." and choose the field name from the displayed list. 2 -> Value
		 */
		record2.addFieldValue(Field.Leads.CITY, "City");

		record2.addFieldValue(Field.Leads.LAST_NAME, "Last Name");

		record2.addFieldValue(Field.Leads.FIRST_NAME, "First Name");

		record2.addFieldValue(Field.Leads.COMPANY, "KKRNP");

		/*
		 * Call addKeyValue method that takes two arguments 1 -> A string that is the Field's API Name 2 -> Value
		 */
		record2.addKeyValue("Custom_field", "Value");

		record2.addKeyValue("Custom_field_2", "value");

		// Add Record instance to the list
		records.add(record2);

		// Set the list to Records in BodyWrapper instance
		request.setData(records);

		List<String> trigger = new ArrayList<String>();

		trigger.add("approval");

		trigger.add("workflow");

		trigger.add("blueprint");

		request.setTrigger(trigger);

		HeaderMap headerInstance = new HeaderMap();

//		headerInstance.add(UpdateRecordsHeader.X_EXTERNAL, "Quotes.Quoted_Items.Product_Name.Products_External");

		// Call updateRecords method that takes moduleAPIName, BodyWrapper instance and headerInstance as parameter.
		APIResponse<ActionHandler> response = recordOperations.updateRecords(moduleAPIName, request, headerInstance);

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

					// Get the list of obtained Attachment instances
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
							System.out.println("Message: " + successResponse.getMessage().getValue());
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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	/**
	 * <h3>Delete Records</h3> This method is used to delete records of a module and print the response.
	 * 
	 * @param moduleAPIName - The API Name of the module to delete records.
	 * @param recordIds     - The List of the record IDs to be deleted.
	 * @throws Exception
	 */
	public static void deleteRecords(String moduleAPIName, List<String> recordIds) throws Exception
	{
		// API Name of the module
		// String moduleAPIName = "Leads";
		// List< String> recordIds = new ArrayList<String>(Arrays.asList("34770615908033","34770615908017"));

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		// Get instance of ParameterMap Class
		ParameterMap paramInstance = new ParameterMap();

		for (String id : recordIds)
		{
			paramInstance.add(DeleteRecordsParam.IDS, id);
		}

		paramInstance.add(DeleteRecordsParam.WF_TRIGGER, false);

		HeaderMap headerInstance = new HeaderMap();

//		headerInstance.add(DeleteRecordsHeader.X_EXTERNAL, "Leads.External");

		// Call deleteRecords method that takes moduleAPIName, paramInstance and headerInstance as parameter.
		APIResponse<ActionHandler> response = recordOperations.deleteRecords(moduleAPIName, paramInstance, headerInstance);

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
							System.out.println("Message: " + successResponse.getMessage().getValue());
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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	/**
	 * <h3>Upsert Records</h3> This method is used to Upsert records of a module and print the response.
	 * 
	 * @param moduleAPIName - The API Name of the module to upsert records.
	 * @throws Exception
	 */
	public static void upsertRecords(String moduleAPIName) throws Exception
	{
		// API Name of the module to create records
		// String moduleAPIName = "Leads";

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper request = new BodyWrapper();

		// List of Record instances
		List<com.zoho.crm.api.record.Record> records = new ArrayList<com.zoho.crm.api.record.Record>();

		// Get instance of Record Class
		com.zoho.crm.api.record.Record record1 = new com.zoho.crm.api.record.Record();

		/*
		 * Call addFieldValue method that takes two arguments 1 -> Call Field "." and choose the module from the displayed list and press "." and choose the field name from the displayed list. 2 -> Value
		 */
		record1.addFieldValue(Field.Leads.CITY, "City");

		record1.addFieldValue(Field.Leads.LAST_NAME, "Last Name");

		record1.addFieldValue(Field.Leads.FIRST_NAME, "First Name");

		record1.addFieldValue(Field.Leads.COMPANY, "Company1");

		/*
		 * Call addKeyValue method that takes two arguments 1 -> A string that is the Field's API Name 2 -> Value
		 */
		record1.addKeyValue("Custom_field", "Value");

		record1.addKeyValue("Custom_field_2", "value");

		// Add Record instance to the list
		records.add(record1);

		// Get instance of Record Class
		com.zoho.crm.api.record.Record record2 = new com.zoho.crm.api.record.Record();

		/*
		 * Call addFieldValue method that takes two arguments 1 -> Call Field "." and choose the module from the displayed list and press "." and choose the field name from the displayed list. 2 -> Value
		 */
		record2.addFieldValue(Field.Leads.CITY, "City");

		record2.addFieldValue(Field.Leads.LAST_NAME, "Last Name");

		record2.addFieldValue(Field.Leads.FIRST_NAME, "First Name");

		record2.addFieldValue(Field.Leads.COMPANY, "Company12");

		/*
		 * Call addKeyValue method that takes two arguments 1 -> A string that is the Field's API Name 2 -> Value
		 */
		record2.addKeyValue("Custom_field", "Value");

		record2.addKeyValue("Custom_field_2", "value");

		// Add Record instance to the list
		records.add(record2);

		List<String> duplicateCheckFields = new ArrayList<String>(Arrays.asList("City", "First_Name"));

		request.setDuplicateCheckFields(duplicateCheckFields);

		// Set the list to Records in BodyWrapper instance
		request.setData(records);

		HeaderMap headerInstance = new HeaderMap();

//		headerInstance.add(UpsertRecordsHeader.X_EXTERNAL, "Leads.External");

		// Call upsertRecords method that takes moduleAPIName, BodyWrapper and headerInstance instance as parameter.
		APIResponse<ActionHandler> response = recordOperations.upsertRecords(moduleAPIName, request, headerInstance);

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
							System.out.println("Message: " + successResponse.getMessage().getValue());
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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	/**
	 * <h3>Get Deleted Records</h3> This method is used to get the deleted records of a module and print the response.
	 * 
	 * @param moduleAPIName - The API Name of the module to get the deleted records.
	 * @throws Exception
	 */
	public static void getDeletedRecords(String moduleAPIName) throws Exception
	{
		// example
		// String moduleAPIName = "Leads";

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		// Get instance of ParameterMap Class
		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(GetDeletedRecordsParam.TYPE, "permanent");// all, recycle, permanent

		paramInstance.add(GetDeletedRecordsParam.PAGE, 1);

		paramInstance.add(GetDeletedRecordsParam.PER_PAGE, 2);

		// Get instance of HeaderMap Class
		HeaderMap headerInstance = new HeaderMap();

		OffsetDateTime ifModifiedSince = OffsetDateTime.of(2020, 05, 02, 12, 00, 30, 00, ZoneOffset.of("+05:30"));

		headerInstance.add(GetDeletedRecordsHeader.IF_MODIFIED_SINCE, ifModifiedSince);

		// Call getDeletedRecords method that takes moduleAPIName, headerInstance and paramInstance as parameter
		APIResponse<DeletedRecordsHandler> response = recordOperations.getDeletedRecords(moduleAPIName, paramInstance, headerInstance);

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
				// Get the object from response
				DeletedRecordsHandler deletedRecordsHandler = response.getObject();

				if (deletedRecordsHandler instanceof DeletedRecordsWrapper)
				{
					// Get the received DeletedRecordsWrapper instance
					DeletedRecordsWrapper deletedRecordsWrapper = (DeletedRecordsWrapper) deletedRecordsHandler;

					// Get the list of obtained DeletedRecord instances
					List<DeletedRecord> deletedRecords = deletedRecordsWrapper.getData();

					for (DeletedRecord deletedRecord : deletedRecords)
					{
						// Get the deletedBy User instance of each DeletedRecord
						com.zoho.crm.api.users.MinifiedUser deletedBy = deletedRecord.getDeletedBy();

						// Check if deletedBy is not null
						if (deletedBy != null)
						{
							// Get the name of the deletedBy User
							System.out.println("DeletedRecord Deleted By User-Name: " + deletedBy.getName());

							// Get the ID of the deletedBy User
							System.out.println("DeletedRecord Deleted By User-ID: " + deletedBy.getId());
						}

						// Get the ID of each DeletedRecord
						System.out.println("DeletedRecord ID: " + deletedRecord.getId());

						// Get the DisplayName of each DeletedRecord
						System.out.println("DeletedRecord DisplayName: " + deletedRecord.getDisplayName());

						// Get the Type of each DeletedRecord
						System.out.println("DeletedRecord Type: " + deletedRecord.getType());

						// Get the createdBy User instance of each DeletedRecord
						com.zoho.crm.api.users.MinifiedUser createdBy = deletedRecord.getCreatedBy();

						// Check if createdBy is not null
						if (createdBy != null)
						{
							// Get the name of the createdBy User
							System.out.println("DeletedRecord Created By User-Name: " + createdBy.getName());

							// Get the ID of the createdBy User
							System.out.println("DeletedRecord Created By User-ID: " + createdBy.getId());
						}

						// Get the DeletedTime of each DeletedRecord
						System.out.println("DeletedRecord DeletedTime: " + deletedRecord.getDeletedTime());
					}

					Info info = deletedRecordsWrapper.getInfo();

					// Check if info is not null
					if (info != null)
					{
						if (info.getPerPage() != null)
						{
							// Get the PerPage of the Info
							System.out.println("Record Info PerPage: " + info.getPerPage().toString());
						}

						if (info.getCount() != null)
						{
							// Get the Count of the Info
							System.out.println("Record Info Count: " + info.getCount().toString());
						}

						if (info.getPage() != null)
						{
							// Get the Page of the Info
							System.out.println("Record Info Page: " + info.getPage().toString());
						}

						if (info.getMoreRecords() != null)
						{
							// Get the MoreRecords of the Info
							System.out.println("Record Info MoreRecords: " + info.getMoreRecords().toString());
						}
					}
				}
				// Check if the request returned an exception
				else if (deletedRecordsHandler instanceof APIException)
				{
					// Get the received APIException instance
					APIException exception = (APIException) deletedRecordsHandler;

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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	/**
	 * <h3>Search Records</h3> This method is used to search records of a module and print the response.
	 * 
	 * @param moduleAPIName - The API Name of the module to obtain records.
	 * @throws Exception
	 */
	public static void searchRecords(String moduleAPIName) throws Exception
	{
		// example
		// String moduleAPIName = "Leads";

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(SearchRecordsParam.CRITERIA, "((Last_Name:starts_with:Last Name) and (Company:starts_with:fasf\\(123\\) K))");

//		paramInstance.add(SearchRecordsParam.CRITERIA, "(External:in:TestExternal221)");

		paramInstance.add(SearchRecordsParam.EMAIL, "abc@zoho.com");

		paramInstance.add(SearchRecordsParam.PHONE, "234567890");

		paramInstance.add(SearchRecordsParam.WORD, "First Name Last Name");

		paramInstance.add(SearchRecordsParam.CONVERTED, "both");

		paramInstance.add(SearchRecordsParam.APPROVED, "both");

		paramInstance.add(SearchRecordsParam.PAGE, 1);

		paramInstance.add(SearchRecordsParam.PER_PAGE, 2);

		HeaderMap headerInstance = new HeaderMap();

//		headerInstance.add(SearchRecordsHeader.X_EXTERNAL, "Leads.External");

		// Call searchRecords method that takes moduleAPIName and ParameterMap and headerInstance as parameter
		APIResponse<ResponseHandler> response = recordOperations.searchRecords(moduleAPIName, paramInstance, headerInstance);

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
				// Get the object from response
				ResponseHandler responseHandler = response.getObject();

				if (responseHandler instanceof ResponseWrapper)
				{
					ResponseWrapper responseWrapper = (ResponseWrapper) responseHandler;

					// Get the obtained Record instance
					List<com.zoho.crm.api.record.Record> records = responseWrapper.getData();

					for (com.zoho.crm.api.record.Record record : records)
					{
						// Get the ID of each Record
						System.out.println("Record ID: " + record.getId());

						// Get the createdBy User instance of each Record
						com.zoho.crm.api.users.MinifiedUser createdBy = record.getCreatedBy();

						// Check if createdBy is not null
						if (createdBy != null)
						{
							// Get the ID of the createdBy User
							System.out.println("Record Created By User-ID: " + createdBy.getId());

							// Get the name of the createdBy User
							System.out.println("Record Created By User-Name: " + createdBy.getName());

							// Get the Email of the createdBy User
							System.out.println("Record Created By User-Email: " + createdBy.getEmail());
						}

						// Get the CreatedTime of each Record
						System.out.println("Record CreatedTime: " + record.getCreatedTime());

						// Get the modifiedBy User instance of each Record
						com.zoho.crm.api.users.MinifiedUser modifiedBy = record.getModifiedBy();

						// Check if modifiedBy is not null
						if (modifiedBy != null)
						{
							// Get the ID of the modifiedBy User
							System.out.println("Record Modified By User-ID: " + modifiedBy.getId());

							// Get the name of the modifiedBy User
							System.out.println("Record Modified By User-Name: " + modifiedBy.getName());

							// Get the Email of the modifiedBy User
							System.out.println("Record Modified By User-Email: " + modifiedBy.getEmail());
						}

						// Get the ModifiedTime of each Record
						System.out.println("Record ModifiedTime: " + record.getModifiedTime());

						// Get the list of Tag instance each Record
						List<Tag> tags = record.getTag();

						// Check if tags is not null
						if (tags != null)
						{
							for (Tag tag : tags)
							{
								// Get the Name of each Tag
								System.out.println("Record Tag Name: " + tag.getName());

								// Get the Id of each Tag
								System.out.println("Record Tag ID: " + tag.getId());
							}
						}

						// To get particular field value
						System.out.println("Record Field Value: " + record.getKeyValue("Last_Name"));// FieldApiName

						System.out.println("Record KeyValues: ");

						// Get the KeyValue map
						for (Map.Entry<String, Object> entry : record.getKeyValues().entrySet())
						{
							String keyName = entry.getKey();

							Object value = entry.getValue();

							if (value instanceof List)
							{
								List<?> dataList = (List<?>) value;

								if (dataList.size() > 0)
								{
									if (dataList.get(0) instanceof FileDetails)
									{
										@SuppressWarnings("unchecked")
										List<FileDetails> fileDetails = (List<FileDetails>) value;

										for (FileDetails fileDetail : fileDetails)
										{
											// Get the Extn of each FileDetails
											System.out.println("Record FileDetails Extn: " + fileDetail.getExtn());

											// Get the IsPreviewAvailable of each FileDetails
											System.out.println("Record FileDetails IsPreviewAvailable: " + fileDetail.getIsPreviewAvailable());

											// Get the DownloadUrl of each FileDetails
											System.out.println("Record FileDetails DownloadUrl: " + fileDetail.getDownloadUrl());

											// Get the DeleteUrl of each FileDetails
											System.out.println("Record FileDetails DeleteUrl: " + fileDetail.getDeleteUrl());

											// Get the EntityId of each FileDetails
											System.out.println("Record FileDetails EntityId: " + fileDetail.getEntityId());

											// Get the Mode of each FileDetails
											System.out.println("Record FileDetails Mode: " + fileDetail.getMode());

											// Get the OriginalSizeByte of each FileDetails
											System.out.println("Record FileDetails OriginalSizeByte: " + fileDetail.getOriginalSizeByte());

											// Get the PreviewUrl of each FileDetails
											System.out.println("Record FileDetails PreviewUrl: " + fileDetail.getPreviewUrl());

											// Get the FileName of each FileDetails
											System.out.println("Record FileDetails FileName: " + fileDetail.getFileName());

											// Get the FileId of each FileDetails
											System.out.println("Record FileDetails FileId: " + fileDetail.getFileId());

											// Get the AttachmentId of each FileDetails
											System.out.println("Record FileDetails AttachmentId: " + fileDetail.getAttachmentId());

											// Get the FileSize of each FileDetails
											System.out.println("Record FileDetails FileSize: " + fileDetail.getFileSize());

											// Get the CreatorId of each FileDetails
											System.out.println("Record FileDetails CreatorId: " + fileDetail.getCreatorId());

											// Get the LinkDocs of each FileDetails
											System.out.println("Record FileDetails LinkDocs: " + fileDetail.getLinkDocs());
										}
									}
									else if (dataList.get(0) instanceof Tax)
									{
										Tax tax = (Tax) dataList.get(0);
										// Get the id of Tax
										System.out.println("Record Tax id: " + tax.getId());

										// Get the value of Tax
										System.out.println("Record Tax value: " + tax.getValue());
									}

									else if (dataList.get(0) instanceof Tag)
									{
										@SuppressWarnings("unchecked")
										List<Tag> tagList = (List<Tag>) value;

										for (Tag tag : tagList)
										{
											// Get the Name of each Tag
											System.out.println("Record Tag Name: " + tag.getName());

											// Get the Id of each Tag
											System.out.println("Record Tag ID: " + tag.getId());
										}
									}
									else if (dataList.get(0) instanceof PricingDetails)
									{
										@SuppressWarnings("unchecked")
										List<PricingDetails> pricingDetails = (List<PricingDetails>) value;

										for (PricingDetails pricingDetail : pricingDetails)
										{
											System.out.println("Record PricingDetails ToRange: " + pricingDetail.getToRange().toString());

											System.out.println("Record PricingDetails Discount: " + pricingDetail.getDiscount().toString());

											System.out.println("Record PricingDetails ID: " + pricingDetail.getId());

											System.out.println("Record PricingDetails FromRange: " + pricingDetail.getFromRange().toString());
										}
									}
									else if (dataList.get(0) instanceof com.zoho.crm.api.record.Record)
									{
										@SuppressWarnings("unchecked")
										List<com.zoho.crm.api.record.Record> recordList = (List<com.zoho.crm.api.record.Record>) dataList;

										for (com.zoho.crm.api.record.Record record1 : recordList)
										{
											// Get the details map
											for (Map.Entry<String, Object> entry1 : record1.getKeyValues().entrySet())
											{
												// Get each value in the map
												System.out.println(entry1.getKey() + ": " + entry1.getValue());
											}
										}
									}
									else if (dataList.get(0) instanceof LineTax)
									{
										@SuppressWarnings("unchecked")
										List<LineTax> lineTaxes = (List<LineTax>) dataList;

										for (LineTax lineTax : lineTaxes)
										{
											System.out.println("Record ProductDetails LineTax Percentage: " + lineTax.getPercentage().toString());

											System.out.println("Record ProductDetails LineTax Name: " + lineTax.getName());

											System.out.println("Record ProductDetails LineTax Id: " + lineTax.getId());

											System.out.println("Record ProductDetails LineTax Value: " + lineTax.getValue().toString());
										}
									}
									else if (dataList.get(0) instanceof Choice<?>)
									{
										@SuppressWarnings("unchecked")
										List<Choice<?>> choiceList = (List<Choice<?>>) dataList;

										System.out.println(keyName);

										System.out.println("values");

										for (Choice<?> choice : choiceList)
										{
											System.out.println(choice.getValue());
										}
									}
									else if (dataList.get(0) instanceof Comment)
									{
										@SuppressWarnings("unchecked")
										List<Comment> comments = (List<Comment>) dataList;

										for (Comment comment : comments)
										{
											System.out.println("Record Comment CommentedBy: " + comment.getCommentedBy());

											System.out.println("Record Comment CommentedTime: " + comment.getCommentedTime().toString());

											System.out.println("Record Comment CommentContent: " + comment.getCommentContent());

											System.out.println("Record Comment Id: " + comment.getId());
										}
									}
									else if (dataList.get(0) instanceof Attachment)
									{
										// Get the list of obtained Attachment instances
										@SuppressWarnings("unchecked")
										List<com.zoho.crm.api.attachments.Attachment> attachments = (List<Attachment>) dataList;

										for (com.zoho.crm.api.attachments.Attachment attachment : attachments)
										{
											// Get the owner User instance of each attachment
											com.zoho.crm.api.users.MinifiedUser owner = attachment.getOwner();

											// Check if owner is not null
											if (owner != null)
											{
												// Get the Name of the Owner
												System.out.println("Record Attachment Owner User-Name: " + owner.getName());

												// Get the ID of the Owner
												System.out.println("Record Attachment Owner User-ID: " + owner.getId());

												// Get the Email of the Owner
												System.out.println("Record Attachment Owner User-Email: " + owner.getEmail());
											}

											// Get the modified time of each attachment
											System.out.println("Record Attachment Modified Time: " + attachment.getModifiedTime().toString());

											// Get the name of the File
											System.out.println("Record Attachment File Name: " + attachment.getFileName());

											// Get the created time of each attachment
											System.out.println("Record Attachment Created Time: " + attachment.getCreatedTime().toString());

											// Get the Attachment file size
											System.out.println("Record Attachment File Size: " + attachment.getSize().toString());

											// Get the parentId Record instance of each attachment
											ParentId parentId = attachment.getParentId();

											// Check if parentId is not null
											if (parentId != null)
											{
												// Get the parent record Name of each attachment
												System.out.println("Record Attachment parent record Name: " + parentId.getName());

												// Get the parent record ID of each attachment
												System.out.println("Record Attachment parent record ID: " + parentId.getId());
											}

											// Get the attachment is Editable
											System.out.println("Record Attachment is Editable: " + attachment.getEditable().toString());

											// Get the file ID of each attachment
											System.out.println("Record Attachment File ID: " + attachment.getFileId());

											// Get the type of each attachment
											System.out.println("Record Attachment File Type: " + attachment.getType());

											// Get the seModule of each attachment
											System.out.println("Record Attachment seModule: " + attachment.getSeModule());

											// Get the modifiedBy User instance of each attachment
											modifiedBy = attachment.getModifiedBy();

											// Check if modifiedBy is not null
											if (modifiedBy != null)
											{
												// Get the Name of the modifiedBy User
												System.out.println("Record Attachment Modified By User-Name: " + modifiedBy.getName());

												// Get the ID of the modifiedBy User
												System.out.println("Record Attachment Modified By User-ID: " + modifiedBy.getId());

												// Get the Email of the modifiedBy User
												System.out.println("Record Attachment Modified By User-Email: " + modifiedBy.getEmail());
											}

											// Get the state of each attachment
											System.out.println("Record Attachment State: " + attachment.getState());

											// Get the ID of each attachment
											System.out.println("Record Attachment ID: " + attachment.getId());

											// Get the createdBy User instance of each attachment
											createdBy = attachment.getCreatedBy();

											// Check if createdBy is not null
											if (createdBy != null)
											{
												// Get the name of the createdBy User
												System.out.println("Record Attachment Created By User-Name: " + createdBy.getName());

												// Get the ID of the createdBy User
												System.out.println("Record Attachment Created By User-ID: " + createdBy.getId());

												// Get the Email of the createdBy User
												System.out.println("Record Attachment Created By User-Email: " + createdBy.getEmail());
											}

											// Get the linkUrl of each attachment
											System.out.println("Record Attachment LinkUrl: " + attachment.getLinkUrl());
										}
									}
									else
									{
										System.out.println(keyName + ": " + value);
									}
								}
							}
							else if (value instanceof Layout)
							{
								com.zoho.crm.api.layouts.Layout layout = (Layout) value;

								if (layout != null)
								{
									System.out.println("Record " + keyName + " ID: " + layout.getId());

									System.out.println("Record " + keyName + " Name: " + layout.getName());
								}
							}
							else if (value instanceof MinifiedUser)
							{
								com.zoho.crm.api.users.MinifiedUser user = (MinifiedUser) value;

								if (user != null)
								{
									System.out.println("Record " + keyName + " User-ID: " + user.getId());

									System.out.println("Record " + keyName + " User-Name: " + user.getName());

									System.out.println("Record " + keyName + " User-Email: " + user.getEmail());
								}
							}
							else if (value instanceof com.zoho.crm.api.record.Record)
							{
								com.zoho.crm.api.record.Record recordValue = (com.zoho.crm.api.record.Record) value;

								System.out.println("Record " + keyName + " ID: " + recordValue.getId());

								System.out.println("Record " + keyName + " Name: " + recordValue.getKeyValue("name"));
							}
							else if (value instanceof Choice<?>)
							{
								System.out.println(keyName + ": " + ((Choice<?>) value).getValue());
							}
							else if (value instanceof RemindAt)
							{
								System.out.println(keyName + ": " + ((RemindAt) value).getAlarm());
							}
							else if (value instanceof RecurringActivity)
							{
								System.out.println(keyName);

								System.out.println("RRULE" + ": " + ((RecurringActivity) value).getRrule());
							}
							else if (value instanceof Consent)
							{
								Consent consent = (Consent) value;

								System.out.println("Record Consent ID: " + consent.getId());

								// Get the Owner User instance of each attachment
								MinifiedUser owner = consent.getOwner();

								// Check if owner is not null
								if (owner != null)
								{
									// Get the name of the owner User
									System.out.println("Record Consent Owner Name: " + owner.getName());

									// Get the ID of the owner User
									System.out.println("Record Consent Owner ID: " + owner.getId());

									// Get the Email of the owner User
									System.out.println("Record Consent Owner Email: " + owner.getEmail());
								}

								MinifiedUser consentCreatedBy = consent.getCreatedBy();

								// Check if createdBy is not null
								if (consentCreatedBy != null)
								{
									// Get the name of the CreatedBy User
									System.out.println("Record Consent CreatedBy Name: " + consentCreatedBy.getName());

									// Get the ID of the CreatedBy User
									System.out.println("Record Consent CreatedBy ID: " + consentCreatedBy.getId());

									// Get the Email of the CreatedBy User
									System.out.println("Record Consent CreatedBy Email: " + consentCreatedBy.getEmail());
								}

								MinifiedUser consentModifiedBy = consent.getModifiedBy();

								// Check if createdBy is not null
								if (consentModifiedBy != null)
								{
									// Get the name of the ModifiedBy User
									System.out.println("Record Consent ModifiedBy Name: " + consentModifiedBy.getName());

									// Get the ID of the ModifiedBy User
									System.out.println("Record Consent ModifiedBy ID: " + consentModifiedBy.getId());

									// Get the Email of the ModifiedBy User
									System.out.println("Record Consent ModifiedBy Email: " + consentModifiedBy.getEmail());
								}

								System.out.println("Record Consent CreatedTime: " + consent.getCreatedTime());

								System.out.println("Record Consent ModifiedTime: " + consent.getModifiedTime());

								System.out.println("Record Consent ContactThroughEmail: " + consent.getContactThroughEmail());

								System.out.println("Record Consent ContactThroughSocial: " + consent.getContactThroughSocial());

								System.out.println("Record Consent ContactThroughSurvey: " + consent.getContactThroughSurvey());

								System.out.println("Record Consent ContactThroughPhone: " + consent.getContactThroughPhone());

								System.out.println("Record Consent MailSentTime: " + consent.getMailSentTime().toString());

								System.out.println("Record Consent ConsentDate: " + consent.getConsentDate().toString());

								System.out.println("Record Consent ConsentRemarks: " + consent.getConsentRemarks());

								System.out.println("Record Consent ConsentThrough: " + consent.getConsentThrough());

								System.out.println("Record Consent DataProcessingBasis: " + consent.getDataProcessingBasis());

								// To get custom values
								System.out.println("Record Consent Lawful Reason: " + consent.getKeyValue("Lawful_Reason"));
							}
							else
							{
								// Get each value in the map
								System.out.println(keyName + ": " + value);
							}
						}
					}

					// Get the Object obtained Info instance
					Info info = responseWrapper.getInfo();

					// Check if info is not null
					if (info != null)
					{
						if (info.getPerPage() != null)
						{
							// Get the PerPage of the Info
							System.out.println("Record Info PerPage: " + info.getPerPage().toString());
						}

						if (info.getCount() != null)
						{
							// Get the Count of the Info
							System.out.println("Record Info Count: " + info.getCount().toString());
						}

						if (info.getPage() != null)
						{
							// Get the Page of the Info
							System.out.println("Record Info Page: " + info.getPage().toString());
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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	/**
	 * <h3>Convert Lead</h3> This method is used to Convert Lead record and print the response.
	 * 
	 * @param leadId - The ID of the Lead to be converted.
	 * @throws Exception
	 */
	public static void convertLead(long leadId) throws Exception
	{
		// API Name of the module
		// long leadId = 34770616603276L;

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		// Get instance of ConvertBodyWrapper Class that will contain the request body
		ConvertBodyWrapper request = new ConvertBodyWrapper();

		// List of LeadConverter instances
		List<LeadConverter> data = new ArrayList<LeadConverter>();

		// Get instance of LeadConverter Class
		LeadConverter record1 = new LeadConverter();

		record1.setOverwrite(true);

		record1.setNotifyLeadOwner(true);

		record1.setNotifyNewEntityOwner(true);

		com.zoho.crm.api.record.Record accounts = new com.zoho.crm.api.record.Record();

		accounts.setId(3477061122632l);

		record1.setAccounts(accounts);

		com.zoho.crm.api.record.Record contacts = new com.zoho.crm.api.record.Record();

		contacts.setId(3477061122635l);

		record1.setContacts(contacts);

		MinifiedUser assignTo = new MinifiedUser();

		assignTo.setId(3477061173021l);

		record1.setAssignTo(assignTo);

		com.zoho.crm.api.record.Record deals = new com.zoho.crm.api.record.Record();

		/*
		 * Call addFieldValue method that takes two arguments 1 -> Call Field "." and choose the module from the displayed list and press "." and choose the field name from the displayed list. 2 -> Value
		 */
		deals.addFieldValue(Field.Deals.DEAL_NAME, "deal_name");

		deals.addFieldValue(Field.Deals.DESCRIPTION, "deals description");

		deals.addFieldValue(Field.Deals.CLOSING_DATE, LocalDate.of(2021, 1, 13));

		deals.addFieldValue(Field.Deals.STAGE, new Choice<String>("Closed Won"));

		deals.addFieldValue(Field.Deals.AMOUNT, 50.7);

		/*
		 * Call addKeyValue method that takes two arguments 1 -> A string that is the Field's API Name 2 -> Value
		 */
		deals.addKeyValue("Custom_field", "Value");

		deals.addKeyValue("Pipeline", new Choice<String>("Qualification"));

		record1.setDeals(deals);

		// Add Record instance to the list
		data.add(record1);

		// Set the list to Records in BodyWrapper instance
		request.setData(data);

		// Call convertLead method that takes leadId and ConvertBodyWrapper instance as parameter.
		APIResponse<ActionHandler> response = recordOperations.convertLead(leadId, request);

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

					// Get the list of obtained Attachment instances
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
							System.out.println("Message: " + successResponse.getMessage().getValue());
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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	/**
	 * This method is used to download a photo associated with a module.
	 * 
	 * @param moduleAPIName     - The API Name of the record's module
	 * @param recordId          - The ID of the record to be obtained.
	 * @param destinationFolder - The absolute path of the destination folder to store the photo.
	 * @throws Exception
	 */
	public static void getPhoto(String moduleAPIName, long recordId, String destinationFolder) throws Exception
	{
		// example
		// String moduleAPIName = "Leads";
		// Long recordId = 347706151772L;
		// String destinationFolder = "/Users/user_name/Desktop";

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		// Call getPhoto method that takes recordId and moduleAPIName as parameters
		APIResponse<DownloadHandler> response = recordOperations.getPhoto(recordId, moduleAPIName);

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
				DownloadHandler downloadHandler = response.getObject();

				if (downloadHandler instanceof FileBodyWrapper)
				{
					// Get object from response
					FileBodyWrapper fileBodyWrapper = (FileBodyWrapper) downloadHandler;

					// Get StreamWrapper instance from the returned FileBodyWrapper instance
					StreamWrapper streamWrapper = fileBodyWrapper.getFile();

					// Create a file instance with the absolute_file_path
					File file = new File(destinationFolder + File.separatorChar + streamWrapper.getName());

					// Get InputStream from the response
					InputStream is = streamWrapper.getStream();

					// Create an OutputStream for the destination file
					OutputStream os = new FileOutputStream(file);

					byte[] buffer = new byte[1024];

					int bytesRead;

					// read the InputStream till the end
					while ((bytesRead = is.read(buffer)) != -1)
					{
						// write data to OutputStream
						os.write(buffer, 0, bytesRead);
					}

					// Close the InputStream
					is.close();

					// Flush and close the OutputStream
					os.flush();

					os.close();
				}
				// Check if the request returned an exception
				else if (downloadHandler instanceof APIException)
				{
					// Get the received APIException instance
					APIException exception = (APIException) downloadHandler;

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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	/**
	 * This method is used to attach a photo to a record. You must include the photo in the request with content type as multipart/form data.
	 * 
	 * @param moduleAPIName    - The API Name of the record's module
	 * @param recordId         - The ID of the record to be obtained.
	 * @param absoluteFilePath - The absolute file path of the file to be uploads
	 * @throws Exception
	 */
	public static void uploadPhoto(String moduleAPIName, long recordId, String absoluteFilePath) throws Exception
	{
		// example
		// String moduleAPIName = "Leads";
		// Long recordId = 347706151772L;
		// String absoluteFilePath = "/Users/use_name/Desktop/image.png";

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		// Get instance of FileBodyWrapper class that will contain the request file
		FileBodyWrapper fileBodyWrapper = new FileBodyWrapper();

		// Get instance of StreamWrapper class that takes absolute path of the file to be attached as parameter
		StreamWrapper streamWrapper = new StreamWrapper(absoluteFilePath);

		// Set file to the FileBodyWrapper instance
		fileBodyWrapper.setFile(streamWrapper);

		// Call uploadPhoto method that takes recordId, moduleAPIName and FileBodyWrapper instance as parameter
		APIResponse<FileHandler> response = recordOperations.uploadPhoto(recordId, moduleAPIName, fileBodyWrapper);

		if (response != null)
		{
			// Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());

			// Check if expected response is received
			if (response.isExpected())
			{
				// Get object from response
				FileHandler fileHandler = response.getObject();

				// Check if the request is successful
				if (fileHandler instanceof SuccessResponse)
				{
					// Get the received SuccessResponse instance
					SuccessResponse successResponse = (SuccessResponse) fileHandler;

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
					System.out.println("Message: " + successResponse.getMessage().getValue());
				}
				// Check if the request returned an exception
				else if (fileHandler instanceof APIException)
				{
					// Get the received APIException instance
					APIException exception = (APIException) fileHandler;

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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	/**
	 * This method is used to delete a photo from a record in a module.
	 * 
	 * @param moduleAPIName - The API Name of the record's module
	 * @param recordId      - The ID of the record to be obtained.
	 * @throws Exception
	 */
	public static void deletePhoto(String moduleAPIName, long recordId) throws Exception
	{
		// example
		// String moduleAPIName = "Leads";
		// Long recordId = 347706151772L;

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		// Call deletePhoto method that takes recordId and moduleAPIName as parameter
		APIResponse<FileHandler> response = recordOperations.deletePhoto(recordId, moduleAPIName);

		if (response != null)
		{
			// Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());

			// Check if expected response is received
			if (response.isExpected())
			{
				// Get object from response
				FileHandler fileHandler = response.getObject();

				// Check if the request is successful
				if (fileHandler instanceof SuccessResponse)
				{
					// Get the received SuccessResponse instance
					SuccessResponse successResponse = (SuccessResponse) fileHandler;

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
					System.out.println("Message: " + successResponse.getMessage().getValue());
				}
				// Check if the request returned an exception
				else if (fileHandler instanceof APIException)
				{
					// Get the received APIException instance
					APIException exception = (APIException) fileHandler;

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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	/**
	 * This method is used to update the values of specific fields for multiple records and print the response.
	 * 
	 * @param moduleAPIName - The API Name of the module to obtain records.
	 * @throws Exception
	 */
	public static void massUpdateRecords(String moduleAPIName) throws Exception
	{
		// API Name of the module to massUpdateRecords
		// String moduleAPIName = "Leads";

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		// Get instance of MassUpdateBodyWrapper Class that will contain the request body
		MassUpdateBodyWrapper request = new MassUpdateBodyWrapper();

		// List of Record instances
		List<com.zoho.crm.api.record.Record> records = new ArrayList<com.zoho.crm.api.record.Record>();

		// Get instance of Record Class
		com.zoho.crm.api.record.Record record1 = new com.zoho.crm.api.record.Record();

		/*
		 * Call addKeyValue method that takes two arguments 1 -> A string that is the Field's API Name 2 -> Value
		 */
		record1.addKeyValue("City", "Value");

//		record1.addKeyValue("Company", "Value");

		// Add Record instance to the list
		records.add(record1);

		// Set the list to Records in BodyWrapper instance
		request.setData(records);

		request.setCvid("347706187501");

		List<String> ids = new ArrayList<String>(Arrays.asList("34770615922192"));

		request.setIds(ids);

//		Territory territory = new Territory();
//		
//		territory.setId("");
//		
//		territory.setIncludeChild(true);
//		
//		request.setTerritory(territory);

		request.setOverWrite(true);

		// Call massUpdateRecords method that takes ModuleAPIName and BodyWrapper instance as parameter.
		APIResponse<MassUpdateActionHandler> response = recordOperations.massUpdateRecords(moduleAPIName, request);

		if (response != null)
		{
			// Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());

			// Check if expected response is received
			if (response.isExpected())
			{
				// Get object from response
				MassUpdateActionHandler massUpdateActionHandler = response.getObject();

				if (massUpdateActionHandler instanceof MassUpdateActionWrapper)
				{
					// Get the received MassUpdateActionWrapper instance
					MassUpdateActionWrapper massUpdateActionWrapper = (MassUpdateActionWrapper) massUpdateActionHandler;

					// Get the list of obtained MassUpdateActionResponse instances
					List<MassUpdateActionResponse> massUpdateActionResponses = massUpdateActionWrapper.getData();

					for (MassUpdateActionResponse massUpdateActionResponse : massUpdateActionResponses)
					{
						// Check if the request is successful
						if (massUpdateActionResponse instanceof MassUpdateSuccessResponse)
						{
							// Get the received MassUpdateSuccessResponse instance
							MassUpdateSuccessResponse massUpdateSuccessResponse = (MassUpdateSuccessResponse) massUpdateActionResponse;

							// Get the Status
							System.out.println("Status: " + massUpdateSuccessResponse.getStatus().getValue());

							// Get the Code
							System.out.println("Code: " + massUpdateSuccessResponse.getCode().getValue());

							System.out.println("Details: ");

							// Get the details map
							for (Map.Entry<String, Object> entry : massUpdateSuccessResponse.getDetails().entrySet())
							{
								// Get each value in the map
								System.out.println(entry.getKey() + ": " + entry.getValue());
							}

							// Get the Message
							System.out.println("Message: " + massUpdateSuccessResponse.getMessage().getValue());
						}
						// Check if the request returned an exception
						else if (massUpdateActionResponse instanceof APIException)
						{
							// Get the received APIException instance
							APIException exception = (APIException) massUpdateActionResponse;

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
							System.out.println("Message: " + exception.getMessage().getValue());
						}
					}
				}
				// Check if the request returned an exception
				else if (massUpdateActionHandler instanceof APIException)
				{
					// Get the received APIException instance
					APIException exception = (APIException) massUpdateActionHandler;

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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	/**
	 * This method is used to get the status of the mass update job scheduled previously and print the response.
	 * 
	 * @param moduleAPIName- The API Name of the module to obtain records.
	 * @param jobId          - The ID of the job from the response of Mass Update Records.
	 * @throws Exception
	 */
	public static void getMassUpdateStatus(String moduleAPIName, String jobId) throws Exception
	{
		// example
		// String moduleAPIName = "Leads";
		// String jobId = "347706151772";

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		// Get instance of ParameterMap Class
		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(GetMassUpdateStatusParam.JOB_ID, jobId);

		// Call getMassUpdateStatus method that takes moduleAPIName and paramInstance as parameter
		APIResponse<MassUpdateResponseHandler> response = recordOperations.getMassUpdateStatus(moduleAPIName, paramInstance);

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
				MassUpdateResponseHandler massUpdateResponseHandler = response.getObject();

				if (massUpdateResponseHandler instanceof MassUpdateResponseWrapper)
				{
					// Get the received MassUpdateResponseWrapper instance
					MassUpdateResponseWrapper massUpdateResponseWrapper = (MassUpdateResponseWrapper) massUpdateResponseHandler;

					// Get the list of obtained MassUpdateResponse instances
					List<MassUpdateResponse> massUpdateResponses = massUpdateResponseWrapper.getData();

					for (MassUpdateResponse massUpdateResponse : massUpdateResponses)
					{
						// Check if the request is successful
						if (massUpdateResponse instanceof MassUpdate)
						{
							// Get the received MassUpdate instance
							MassUpdate massUpdate = (MassUpdate) massUpdateResponse;

							// Get the Status of each MassUpdate
							System.out.println("MassUpdate Status: " + massUpdate.getStatus().getValue());

							// Get the FailedCount of each MassUpdate
							System.out.println("MassUpdate FailedCount: " + massUpdate.getFailedCount().toString());

							// Get the UpdatedCount of each MassUpdate
							System.out.println("MassUpdate UpdatedCount: " + massUpdate.getUpdatedCount().toString());

							// Get the NotUpdatedCount of each MassUpdate
							System.out.println("MassUpdate NotUpdatedCount: " + massUpdate.getNotUpdatedCount());

							// Get the TotalCount of each MassUpdate
							System.out.println("MassUpdate TotalCount: " + massUpdate.getTotalCount().toString());

						}
						// Check if the request returned an exception
						else if (massUpdateResponse instanceof APIException)
						{
							// Get the received APIException instance
							APIException exception = (APIException) massUpdateResponse;

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
							System.out.println("Message: " + exception.getMessage().getValue());
						}
					}
				}
				// Check if the request returned an exception
				else if (massUpdateResponseHandler instanceof APIException)
				{
					// Get the received APIException instance
					APIException exception = (APIException) massUpdateResponseHandler;

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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	public static void getRecordCount() throws Exception
	{
		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		String moduleAPIName = "Leads";

		ParameterMap paramInstance = new ParameterMap();

		paramInstance.add(RecordOperations.RecordCountParam.PHONE, "(990) 000-0000");

		APIResponse<CountHandler> response = recordOperations.recordCount(moduleAPIName, paramInstance);

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
				CountHandler countHandler = response.getObject();

				if (countHandler instanceof CountWrapper)
				{
					// Get the received CountWrapper instance
					CountWrapper countWrapper = (CountWrapper) countHandler;

					// Get the Count of Tag
					System.out.println("Record Count: " + countWrapper.getCount());
				}
				// Check if the request returned an exception
				else if (countHandler instanceof APIException)
				{
					// Get the received APIException instance
					APIException exception = (APIException) countHandler;

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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	public static void assignTerritoriesToMultipleRecords(String moduleAPIName) throws Exception
	{
		// API Name of the module to assignTerritoriesToMultipleRecords
		// String moduleAPIName = "Leads";

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper request = new BodyWrapper();

		// List of Record instances
		List<com.zoho.crm.api.record.Record> records = new ArrayList<com.zoho.crm.api.record.Record>();

		// Get instance of Record Class
		com.zoho.crm.api.record.Record record1 = new com.zoho.crm.api.record.Record();

		record1.setId(3477061121072l);

		/*
		 * Call addKeyValue method that takes two arguments 1 -> A string that is the Field's API Name 2 -> Value
		 */

		Territory territory = new Territory();

		territory.setId(34770613051397l);

		record1.addKeyValue("Territories", new ArrayList<Territory>(Arrays.asList(territory)));

		// Add Record instance to the list
		records.add(record1);

		// Set the list to Records in BodyWrapper instance
		request.setData(records);

		// Call assignTerritoriesToMultipleRecords method that takes ModuleAPIName and BodyWrapper instance as parameter.
		APIResponse<ActionHandler> response = recordOperations.assignTerritoriesToMultipleRecords(moduleAPIName, request);

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
							System.out.println("Message: " + successResponse.getMessage().getValue());
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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	public static void assignTerritoryToRecord(String moduleAPIName, Long id) throws Exception
	{
		// API Name of the module to assignTerritoryToRecord
		// String moduleAPIName = "Leads";

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper request = new BodyWrapper();

		// List of Record instances
		List<com.zoho.crm.api.record.Record> records = new ArrayList<com.zoho.crm.api.record.Record>();

		// Get instance of Record Class
		com.zoho.crm.api.record.Record record1 = new com.zoho.crm.api.record.Record();

		/*
		 * Call addKeyValue method that takes two arguments 1 -> A string that is the Field's API Name 2 -> Value
		 */

		Territory territory = new Territory();

		territory.setId(34770613051397l);

		record1.addKeyValue("Territories", new ArrayList<Territory>(Arrays.asList(territory)));

		// Add Record instance to the list
		records.add(record1);

		// Set the list to Records in BodyWrapper instance
		request.setData(records);

		// Call assignTerritoryToRecord method that takes ModuleAPIName, id and BodyWrapper instance as parameter.
		APIResponse<ActionHandler> response = recordOperations.assignTerritoryToRecord(moduleAPIName, id, request);

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
							System.out.println("Message: " + successResponse.getMessage().getValue());
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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	public static void removeTerritoriesFromMultipleRecords(String moduleAPIName) throws Exception
	{
		// example
		// String moduleAPIName = "Leads";
		// Long recordId = 347706151772L;

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper request = new BodyWrapper();

		// List of Record instances
		List<com.zoho.crm.api.record.Record> records = new ArrayList<com.zoho.crm.api.record.Record>();

		// Get instance of Record Class
		com.zoho.crm.api.record.Record record1 = new com.zoho.crm.api.record.Record();

		record1.setId(3477061121072l);

		/*
		 * Call addKeyValue method that takes two arguments 1 -> A string that is the Field's API Name 2 -> Value
		 */

		Territory territory = new Territory();

		territory.setId(34770613051397l);

		record1.addKeyValue("Territories", new ArrayList<Territory>(Arrays.asList(territory)));

		// Add Record instance to the list
		records.add(record1);

		// Set the list to Records in BodyWrapper instance
		request.setData(records);

		// Call removeTerritoriesFromMultipleRecords method that takes moduleAPIName and BodyWrapper instance as parameter
		APIResponse<ActionHandler> response = recordOperations.removeTerritoriesFromMultipleRecords(moduleAPIName, request);

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
							System.out.println("Message: " + successResponse.getMessage().getValue());
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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	public static void removeTerritoriesFromRecord(String moduleAPIName, Long id) throws Exception
	{
		// example
		// String moduleAPIName = "Leads";
		// Long recordId = 347706151772L;

		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		// Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper request = new BodyWrapper();

		// List of Record instances
		List<com.zoho.crm.api.record.Record> records = new ArrayList<com.zoho.crm.api.record.Record>();

		// Get instance of Record Class
		com.zoho.crm.api.record.Record record1 = new com.zoho.crm.api.record.Record();

		/*
		 * Call addKeyValue method that takes two arguments 1 -> A string that is the Field's API Name 2 -> Value
		 */

		Territory territory = new Territory();

		territory.setId(34770613051397l);

		record1.addKeyValue("Territories", new ArrayList<Territory>(Arrays.asList(territory)));

		// Add Record instance to the list
		records.add(record1);

		// Set the list to Records in BodyWrapper instance
		request.setData(records);

		// Call removeTerritoriesFromRecord method that takes moduleAPIName, recordId and BodyWrapper instance as parameter
		APIResponse<ActionHandler> response = recordOperations.removeTerritoriesFromRecord(moduleAPIName, id, request);

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
							System.out.println("Message: " + successResponse.getMessage().getValue());
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
							System.out.println("Message: " + exception.getMessage().getValue());
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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void leadConversionOptions(Long recordId) throws Exception
	{
		// Get instance of RecordOperations Class
		RecordOperations recordOperations = new RecordOperations();

		String moduleAPIName = "Leads";

		APIResponse<ResponseHandler> response = recordOperations.leadConversionOptions(recordId, moduleAPIName);

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
				// Get the object from response
				ResponseHandler responseHandler = response.getObject();

				if (responseHandler instanceof ConversionOptionsResponseWrapper)
				{
					ConversionOptionsResponseWrapper conversionOptionResponseWrapper = (ConversionOptionsResponseWrapper) responseHandler;

					ConversionOption conversionOption = conversionOptionResponseWrapper.getConversionoptions();

					com.zoho.crm.api.modules.Module module = conversionOption.getModulePreference();

					if (module != null)
					{
						System.out.println("ConversionOptions ModulePreference API-Name: " + module.getAPIName());

						System.out.println("ConversionOptions ModulePreference ID: " + module.getId());
					}

					List<com.zoho.crm.api.record.Record> contacts = conversionOption.getContacts();

					if (contacts != null)
					{
						for (com.zoho.crm.api.record.Record contact : contacts)
						{
							// Get the ID of each Record
							System.out.println("Record ID: " + contact.getId());

							System.out.println("Record KeyValues: ");

							// Get the KeyValue map
							for (Map.Entry<String, Object> entry : contact.getKeyValues().entrySet())
							{
								String keyName = entry.getKey();

								Object value = entry.getValue();

								if (value instanceof List)
								{
									System.out.println("Record KeyName : " + keyName);

									List<?> dataList = (List<?>) value;

									for (Object data : dataList)
									{
										if (data instanceof Map)
										{
											System.out.println("Record KeyName : " + keyName + " - Value : ");

											for (Map.Entry<String, Object> mapValue : ((HashMap<String, Object>) data).entrySet())
											{
												System.out.println(mapValue.getKey() + " : " + mapValue.getValue());
											}
										}
										else
										{
											System.out.println(data);
										}
									}
								}
								else if (value instanceof Map)
								{
									System.out.println("Record KeyName : " + keyName + " - Value : ");

									for (Map.Entry<String, Object> mapValue : ((HashMap<String, Object>) value).entrySet())
									{
										System.out.println(mapValue.getKey() + " : " + mapValue.getValue());
									}
								}
								else
								{
									System.out.println("Record KeyName : " + keyName + " - Value : " + value);
								}
							}
						}
					}

					PreferenceFieldMatchedValue preferenceFieldMatchedValue = conversionOption.getPreferenceFieldMatchedValue();

					if (preferenceFieldMatchedValue != null)
					{
						List<com.zoho.crm.api.record.Record> contactsPreferenceField = preferenceFieldMatchedValue.getContacts();

						if (contactsPreferenceField != null)
						{
							for (com.zoho.crm.api.record.Record contact : contactsPreferenceField)
							{
								// Get the ID of each Record
								System.out.println("Record ID: " + contact.getId());

								System.out.println("Record KeyValues: ");

								// Get the KeyValue map
								for (Map.Entry<String, Object> entry : contact.getKeyValues().entrySet())
								{
									String keyName = entry.getKey();

									Object value = entry.getValue();

									if (value instanceof Map)
									{
										System.out.println("Record KeyName : " + keyName + " - Value : ");

										for (Map.Entry<String, Object> mapValue : ((HashMap<String, Object>) value).entrySet())
										{
											System.out.println(mapValue.getKey() + " : " + mapValue.getValue());
										}
									}
									else
									{
										System.out.println("Record KeyName : " + keyName + " - Value : " + value);
									}
								}
							}
						}

						List<com.zoho.crm.api.record.Record> accountsPreferenceField = preferenceFieldMatchedValue.getAccounts();

						if (accountsPreferenceField != null)
						{
							for (com.zoho.crm.api.record.Record account : accountsPreferenceField)
							{
								// Get the ID of each Record
								System.out.println("Record ID: " + account.getId());

								System.out.println("Record KeyValues: ");

								// Get the KeyValue map
								for (Map.Entry<String, Object> entry : account.getKeyValues().entrySet())
								{
									String keyName = entry.getKey();

									Object value = entry.getValue();

									if (value instanceof Map)
									{
										System.out.println("Record KeyName : " + keyName + " - Value : ");

										for (Map.Entry<String, Object> mapValue : ((HashMap<String, Object>) value).entrySet())
										{
											System.out.println(mapValue.getKey() + " : " + mapValue.getValue());
										}
									}
									else
									{
										System.out.println("Record KeyName : " + keyName + " - Value : " + value);
									}
								}
							}
						}

						List<com.zoho.crm.api.record.Record> dealsPreferenceField = preferenceFieldMatchedValue.getDeals();

						if (dealsPreferenceField != null)
						{
							for (com.zoho.crm.api.record.Record deal : dealsPreferenceField)
							{
								// Get the ID of each Record
								System.out.println("Record ID: " + deal.getId());

								System.out.println("Record KeyValues: ");

								// Get the KeyValue map
								for (Map.Entry<String, Object> entry : deal.getKeyValues().entrySet())
								{
									String keyName = entry.getKey();

									Object value = entry.getValue();

									if (value instanceof Map)
									{
										System.out.println("Record KeyName : " + keyName + " - Value : ");

										for (Map.Entry<String, Object> mapValue : ((HashMap<String, Object>) value).entrySet())
										{
											System.out.println(mapValue.getKey() + " : " + mapValue.getValue());
										}
									}
									else
									{
										System.out.println("Record KeyName : " + keyName + " - Value : " + value);
									}
								}
							}
						}
					}

					List<com.zoho.crm.api.record.Record> accoutns = conversionOption.getAccounts();

					if (accoutns != null)
					{
						for (com.zoho.crm.api.record.Record account : accoutns)
						{
							// Get the ID of each Record
							System.out.println("Record ID: " + account.getId());

							System.out.println("Record KeyValues: ");

							// Get the KeyValue map
							for (Map.Entry<String, Object> entry : account.getKeyValues().entrySet())
							{
								String keyName = entry.getKey();

								Object value = entry.getValue();

								if (value instanceof List)
								{
									System.out.println("Record KeyName : " + keyName);

									List<?> dataList = (List<?>) value;

									for (Object data : dataList)
									{
										if (data instanceof Map)
										{
											System.out.println("Record KeyName : " + keyName + " - Value : ");

											for (Map.Entry<String, Object> mapValue : ((HashMap<String, Object>) data).entrySet())
											{
												System.out.println(mapValue.getKey() + " : " + mapValue.getValue());
											}
										}
										else
										{
											System.out.println(data);
										}
									}
								}
								else if (value instanceof Map)
								{
									System.out.println("Record KeyName : " + keyName + " - Value : ");

									for (Map.Entry<String, Object> mapValue : ((HashMap<String, Object>) value).entrySet())
									{
										System.out.println(mapValue.getKey() + " : " + mapValue.getValue());
									}
								}
								else
								{
									System.out.println("Record KeyName : " + keyName + " - Value : " + value);
								}
							}
						}
					}

					List<com.zoho.crm.api.record.Record> deals = conversionOption.getDeals();

					if (deals != null)
					{
						for (com.zoho.crm.api.record.Record deal : deals)
						{
							// Get the ID of each Record
							System.out.println("Record ID: " + deal.getId());

							System.out.println("Record KeyValues: ");

							// Get the KeyValue map
							for (Map.Entry<String, Object> entry : deal.getKeyValues().entrySet())
							{
								String keyName = entry.getKey();

								Object value = entry.getValue();

								if (value instanceof List)
								{
									System.out.println("Record KeyName : " + keyName);

									List<?> dataList = (List<?>) value;

									for (Object data : dataList)
									{
										if (data instanceof Map)
										{
											System.out.println("Record KeyName : " + keyName + " - Value : ");

											for (Map.Entry<String, Object> mapValue : ((HashMap<String, Object>) data).entrySet())
											{
												System.out.println(mapValue.getKey() + " : " + mapValue.getValue());
											}
										}
										else
										{
											System.out.println(data);
										}
									}
								}
								else if (value instanceof Map)
								{
									System.out.println("Record KeyName : " + keyName + " - Value : ");

									for (Map.Entry<String, Object> mapValue : ((HashMap<String, Object>) value).entrySet())
									{
										System.out.println(mapValue.getKey() + " : " + mapValue.getValue());
									}
								}
								else
								{
									System.out.println("Record KeyName : " + keyName + " - Value : " + value);
								}
							}
						}
					}

					List<com.zoho.crm.api.modules.Module> modulesWithMultipleLayouts = conversionOption.getModulesWithMultipleLayouts();

					if (modulesWithMultipleLayouts != null)
					{
						for (com.zoho.crm.api.modules.Module module_1 : modulesWithMultipleLayouts)
						{
							System.out.println("ConversionOptions ModulesWithMultipleLayouts API-Name: " + module_1.getAPIName());

							System.out.println("ConversionOptions ModulesWithMultipleLayouts ID: " + module_1.getId());
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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();

				for (java.lang.reflect.Field field : fields)
				{
					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}
}
