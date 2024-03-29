package currencies;

import java.lang.reflect.Field;
import java.util.Map;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.currencies.APIException;
import com.zoho.crm.api.currencies.ActionHandler;
import com.zoho.crm.api.currencies.BaseCurrencyActionResponse;
import com.zoho.crm.api.currencies.BaseCurrencyActionWrapper;
import com.zoho.crm.api.currencies.BaseCurrencyWrapper;
import com.zoho.crm.api.currencies.CurrenciesOperations;
import com.zoho.crm.api.currencies.Format;
import com.zoho.crm.api.currencies.SuccessResponse;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Choice;
import com.zoho.crm.api.util.Model;

public class UpdateBaseCurrency
{
	public static void updateBaseCurrency() throws Exception
	{
		CurrenciesOperations currenciesOperations = new CurrenciesOperations();
		BaseCurrencyWrapper bodyWrapper = new BaseCurrencyWrapper();
		com.zoho.crm.api.currencies.BaseCurrency currency = new com.zoho.crm.api.currencies.BaseCurrency();
		currency.setPrefixSymbol(true);
		currency.setSymbol("DA");
		currency.setExchangeRate("5.0");
		currency.setId(34770617368016l);
		currency.setIsActive(true);
		Format format = new Format();
		format.setDecimalSeparator(new Choice<String>("Period"));
		format.setThousandSeparator(new Choice<String>("Comma"));
		format.setDecimalPlaces(new Choice<String>("2"));
		currency.setFormat(format);
		bodyWrapper.setBaseCurrency(currency);
		APIResponse<ActionHandler> response = currenciesOperations.updateBaseCurrency(bodyWrapper);
		if (response != null)
		{
			System.out.println("Status Code: " + response.getStatusCode());
			if (response.isExpected())
			{
				ActionHandler baseCurrencyActionHandler = response.getObject();
				if (baseCurrencyActionHandler instanceof BaseCurrencyActionWrapper)
				{
					BaseCurrencyActionWrapper baseCurrencyActionWrapper = (BaseCurrencyActionWrapper) baseCurrencyActionHandler;
					BaseCurrencyActionResponse actionResponse = baseCurrencyActionWrapper.getBaseCurrency();
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
				else if (baseCurrencyActionHandler instanceof APIException)
				{
					APIException exception = (APIException) baseCurrencyActionHandler;
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
			updateBaseCurrency();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
