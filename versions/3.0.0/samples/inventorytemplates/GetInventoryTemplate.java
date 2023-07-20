package inventorytemplates;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.inventorytemplates.APIException;
import com.zoho.crm.api.inventorytemplates.Folder;
import com.zoho.crm.api.inventorytemplates.InventoryTemplate;
import com.zoho.crm.api.inventorytemplates.InventoryTemplatesOperations;
import com.zoho.crm.api.inventorytemplates.ResponseHandler;
import com.zoho.crm.api.inventorytemplates.ResponseWrapper;
import com.zoho.crm.api.modules.MinifiedModule;
import com.zoho.crm.api.users.MinifiedUser;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class GetInventoryTemplate
{
	public static void getInventoryTemplate(Long InventoryTemplateID) throws Exception
	{
		InventoryTemplatesOperations inventoryTemplatesOperations = new InventoryTemplatesOperations();
		APIResponse<ResponseHandler> response = inventoryTemplatesOperations.getInventoryTemplate(InventoryTemplateID);
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
					List<InventoryTemplate> inventoryTemplates = responseWrapper.getInventoryTemplates();
					for (InventoryTemplate inventoryTemplate : inventoryTemplates)
					{
						System.out.println("InventoryTemplate CreatedTime: " + inventoryTemplate.getCreatedTime());
						MinifiedModule module = inventoryTemplate.getModule();
						if (module != null)
						{
							System.out.println("InventoryTemplate Module ID: " + module.getId());
							System.out.println("InventoryTemplate Module apiName: " + module.getAPIName());
						}
						System.out.println("InventoryTemplate Type: " + inventoryTemplate.getType());
						MinifiedUser createdBy = inventoryTemplate.getCreatedBy();
						if (createdBy != null)
						{
							System.out.println("InventoryTemplate Created By Name : " + createdBy.getName());
							System.out.println("InventoryTemplate Created By id : " + createdBy.getId());
						}
						System.out.println("InventoryTemplate ModifiedTime: " + inventoryTemplate.getModifiedTime());
						Folder folder = inventoryTemplate.getFolder();
						if (folder != null)
						{
							System.out.println("InventoryTemplate Folder  id : " + folder.getId());
							System.out.println("InventoryTemplate Folder  Name : " + folder.getName());
						}
						System.out.println("InventoryTemplate Last Usage Time: " + inventoryTemplate.getLastUsageTime());
						System.out.println("InventoryTemplate Name: " + inventoryTemplate.getName());
						MinifiedUser modifiedBy = inventoryTemplate.getModifiedBy();
						if (modifiedBy != null)
						{
							System.out.println("InventoryTemplate Modified By Name : " + modifiedBy.getName());
							System.out.println("InventoryTemplate Modified By id : " + modifiedBy.getId());
						}
						System.out.println("InventoryTemplate ID: " + inventoryTemplate.getId());
						System.out.println("InventoryTemplate : " + inventoryTemplate.getEditorMode());
						System.out.println("InventoryTemplate Content: " + inventoryTemplate.getContent());
						System.out.println("InventoryTemplate EditorMode: " + inventoryTemplate.getEditorMode());
						System.out.println("InventoryTemplate Favourite: " + inventoryTemplate.getFavorite());
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
			Token token = new OAuthToken.Builder().clientID("Client_Id").clientSecret("Client_Secret").refreshToken("Refresh_Token").redirectURL("Redirect_URL").build();
			new Initializer.Builder().environment(environment).token(token).initialize();
			Long InventoryTemplateID = 440248258282L;
			getInventoryTemplate(InventoryTemplateID);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
