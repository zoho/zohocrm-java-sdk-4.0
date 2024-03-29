package usersterritories;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.usersterritories.APIException;
import com.zoho.crm.api.usersterritories.BulkValidation;
import com.zoho.crm.api.usersterritories.UsersTerritoriesOperations;
import com.zoho.crm.api.usersterritories.Validation;
import com.zoho.crm.api.usersterritories.ValidationGroup;
import com.zoho.crm.api.usersterritories.ValidationHandler;
import com.zoho.crm.api.usersterritories.ValidationWrapper;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class ValidateBeforeTransferForAllTerritories
{
	public static void validateBeforeTransferForAllTerritories(Long userId) throws Exception
	{
		UsersTerritoriesOperations usersTerritoriesOperations = new UsersTerritoriesOperations(userId);
		APIResponse<ValidationHandler> response = usersTerritoriesOperations.validateBeforeTransferForAllTerritories();
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
				ValidationHandler responseHandler = response.getObject();
				if (responseHandler instanceof ValidationWrapper)
				{
					ValidationWrapper responseWrapper = (ValidationWrapper) responseHandler;
					List<ValidationGroup> usersTerritory = responseWrapper.getValidateBeforeTransfer();
					for (ValidationGroup validationGroup : usersTerritory)
					{
						if (validationGroup instanceof BulkValidation)
						{
							BulkValidation validation = (BulkValidation) validationGroup;
							System.out.println("User Territory Validation Alert : " + validation.getAlert());
							System.out.println("User Territory Validation Assignment : " + validation.getAssignment());
							System.out.println("User Territory Validation Criteria : " + validation.getCriteria());

							System.out.println("User Territory Validation Name : " + validation.getName());

							System.out.println("User Territory Validation Id : " + validation.getId());
						}
						else if (validationGroup instanceof Validation)
						{
							Validation validation = (Validation) validationGroup;
							System.out.println("User Territory Validation Records : " + validation.getRecords());

							System.out.println("User Territory Validation Name : " + validation.getName());

							System.out.println("User Territory Validation Id : " + validation.getId());
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
			Long userId = 34770611709l;
			validateBeforeTransferForAllTerritories(userId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
