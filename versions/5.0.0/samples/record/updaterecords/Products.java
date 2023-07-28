package record.updaterecords;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.crm.api.HeaderMap;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.record.APIException;
import com.zoho.crm.api.record.ActionHandler;
import com.zoho.crm.api.record.ActionResponse;
import com.zoho.crm.api.record.ActionWrapper;
import com.zoho.crm.api.record.BodyWrapper;
import com.zoho.crm.api.record.Consent;
import com.zoho.crm.api.record.Field;
import com.zoho.crm.api.record.FileDetails;
import com.zoho.crm.api.record.RecordOperations;
import com.zoho.crm.api.record.SuccessResponse;
import com.zoho.crm.api.tags.Tag;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Choice;
import com.zoho.crm.api.util.Model;

public class Products {
	public static void updateProducts(String module) throws Exception 
	{
		RecordOperations recordOperations = new RecordOperations();
		BodyWrapper bodyWrapper = new BodyWrapper();
		List<com.zoho.crm.api.record.Record> records = new ArrayList<com.zoho.crm.api.record.Record>();
		com.zoho.crm.api.record.Record record1 = new com.zoho.crm.api.record.Record();
		record1.setId(3034553467895L);
		record1.addFieldValue(Field.Products.PRODUCT_NAME, "product_name");
		record1.addFieldValue(Field.Products.PRODUCT_CODE, "product_code");
		record1.addFieldValue(Field.Products.PRODUCT_ACTIVE, true);
		record1.addFieldValue(Field.Products.PRODUCT_CATEGORY, new Choice<String>("Software"));
		record1.addFieldValue(Field.Products.SALES_END_DATE, LocalDate.of(2023, 12, 25));
		record1.addFieldValue(Field.Products.SUPPORT_EXPIRY_DATE, LocalDate.of(2025, 12, 12));
		record1.addFieldValue(Field.Products.SALES_START_DATE, LocalDate.of(2023, 06, 12));
		record1.addFieldValue(Field.Products.SUPPORT_START_DATE, LocalDate.of(2023, 06, 20));
		record1.addFieldValue(Field.Products.MANUFACTURER, new Choice<String>("MetBeat Corp."));
		com.zoho.crm.api.record.Record vendor = new com.zoho.crm.api.record.Record();
		vendor.addFieldValue(Field.Vendors.ID, 4402481054948L);
		record1.addFieldValue(Field.Products.VENDOR_NAME, vendor);
		record1.addFieldValue(Field.Products.DESCRIPTION, "description of product");
		// price info of Product
		record1.addFieldValue(Field.Products.UNIT_PRICE, 33.22);
		record1.addFieldValue(Field.Products.COMMISSION_RATE, 12.2);
		record1.addFieldValue(Field.Products.TAXABLE, true);
		List<com.zoho.crm.api.record.Tax> taxes = new ArrayList<com.zoho.crm.api.record.Tax>();
		com.zoho.crm.api.record.Tax tax = new com.zoho.crm.api.record.Tax();
		tax.setId(440248020807L);
		tax.setValue("15.0");
		record1.addKeyValue("Tax", taxes);
		// stock info of Product
		record1.addFieldValue(Field.Products.USAGE_UNIT, new Choice<String>("Box"));
		record1.addFieldValue(Field.Products.QTY_IN_STOCK, 10.0);
		record1.addFieldValue(Field.Products.QTY_IN_DEMAND, 5.0);
		record1.addFieldValue(Field.Products.QTY_ORDERED, 5.0);
		record1.addFieldValue(Field.Products.REORDER_LEVEL, 0.0);
		com.zoho.crm.api.users.MinifiedUser user = new com.zoho.crm.api.users.MinifiedUser();
		user.setId(440248254001L);
		record1.addKeyValue("Handler", user);
		// Used when GDPR is enabled
		Consent dataConsent = new Consent();
		dataConsent.setConsentRemarks("Approved.");
		dataConsent.setConsentThrough("Email");
		dataConsent.setContactThroughEmail(true);
		dataConsent.setContactThroughSocial(false);
		dataConsent.setContactThroughPhone(true);
		dataConsent.setContactThroughSurvey(true);
		dataConsent.setConsentDate(LocalDate.of(2023, 10, 11));
		dataConsent.setDataProcessingBasis("Obtained");
		record1.addKeyValue("Data_Processing_Basis_Details", dataConsent);
		// for MultiSelectLookUp/custom MultiSelectLookUp
		List<com.zoho.crm.api.record.Record> Multirecords = new ArrayList<>();
		com.zoho.crm.api.record.Record record = new com.zoho.crm.api.record.Record();
		com.zoho.crm.api.record.Record linkingRecord = new com.zoho.crm.api.record.Record();
		record.addKeyValue("id", 440248884001L);
		linkingRecord.addKeyValue("Msl",record);
		Multirecords.add(linkingRecord);
		record1.addKeyValue("Msl", Multirecords);
		// for Custom Fields
		record1.addKeyValue("External", "Value12345");
		record1.addKeyValue("Custom_field", "Value");
		record1.addKeyValue("Date_Time_2", OffsetDateTime.of(2023, 11, 20, 10, 00, 01, 00, ZoneOffset.of("+05:30")));
		record1.addKeyValue("Date_1", LocalDate.of(2021, 1, 13));
		record1.addKeyValue("Subject", "AutomatedSDK");
		record1.addKeyValue("Product_Name", "AutomatedSDK");
		List<FileDetails> fileDetails = new ArrayList<FileDetails>();
		FileDetails fileDetail1 = new FileDetails();
		fileDetail1.setFileId("ae9c7cefa418aa5cc2d9ab35c32a6ae23d729ad87c6d90b0bd44183d280");
		fileDetails.add(fileDetail1);
		FileDetails fileDetail2 = new FileDetails();
		fileDetail2.setFileId("ae9c7cefa418aec1d6a5cc2d9ab35c32e0063e7321b5b4ca34519e6cdb2");
		fileDetails.add(fileDetail2);
		record1.addKeyValue("File_Upload", fileDetails);
		// for Custom User LookUp
		com.zoho.crm.api.users.MinifiedUser user_1 = new com.zoho.crm.api.users.MinifiedUser();
		user_1.setId(440248254001L);
		record1.addKeyValue("User_1", user_1);
		// for Custom LookUp
		com.zoho.crm.api.record.Record data = new com.zoho.crm.api.record.Record();
		data.setId(440248774074L);
		record1.addKeyValue("Lookup_2",data);
		// for Custom pickList
		record1.addKeyValue("pick", new Choice<String>("true"));
		// for Custom MultiSelect 
		record1.addKeyValue("Multiselect", new ArrayList<>(Arrays.asList(new Choice<String>("Option 1"),new Choice<String>("Option 2"))));
		// Subform sample code
		List<com.zoho.crm.api.record.Record> subformList = new ArrayList<com.zoho.crm.api.record.Record>();
		com.zoho.crm.api.record.Record subform = new com.zoho.crm.api.record.Record();
		subform.addKeyValue("customfield", "customvalue");
		com.zoho.crm.api.users.MinifiedUser user1 = new com.zoho.crm.api.users.MinifiedUser();
		user1.setId(440248254001L);
		subform.addKeyValue("Userfield", user1);
		subformList.add(subform);
		record1.addKeyValue("Subform_2", subformList);
		
		// can update another record with same above mention fields
		com.zoho.crm.api.record.Record record2 = new com.zoho.crm.api.record.Record();
		record2.setId(35002303403L);
		
		List<Tag> tagList = new ArrayList<Tag>();
		Tag tag = new Tag();
		tag.setName("Testtask");
		tagList.add(tag);
		record1.setTag(tagList);
	
		// Add Record instance to the list
		records.add(record1);
		bodyWrapper.setData(records);
	
		List<String> trigger = new ArrayList<String>();
		trigger.add("approval");
		trigger.add("workflow");
		trigger.add("blueprint");
		bodyWrapper.setTrigger(trigger);
	
//		bodyWrapper.setLarId("3477061087515");
		HeaderMap headerInstance = new HeaderMap();
	//			headerInstance.add(CreateRecordsHeader.X_EXTERNAL, "Quotes.Quoted_Items.Product_Name.Products_External");
		APIResponse<ActionHandler> response = recordOperations.updateRecords(module, bodyWrapper, headerInstance);
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
				java.lang.reflect.Field[] fields = clas.getDeclaredFields();
				for (java.lang.reflect.Field field : fields)
				{
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}	
	}
	public static void main(String[] args) {
		try
		{
			Environment environment = USDataCenter.PRODUCTION;
			Token token = new OAuthToken.Builder().clientID("Client_Id").clientSecret("Client_Secret").refreshToken("Refresh_Token").redirectURL("Redirect_URL").build();
			new Initializer.Builder().environment(environment).token(token).initialize();
			String moduleAPIName = "Products";
			updateProducts(moduleAPIName);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
