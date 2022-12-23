package com.zoho.crm.sample.availablecurrencies;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.crm.api.availablecurrencies.APIException;
import com.zoho.crm.api.availablecurrencies.AvailableCurrenciesOperations;
import com.zoho.crm.api.availablecurrencies.ResponseHandler;
import com.zoho.crm.api.availablecurrencies.ResponseWrapper;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class AvailableCurrencies
{
	/**
	 * <h3>Get AvailableCurrencies</h3> This method is used to get all the available currencies in your organization.
	 * 
	 * @throws Exception
	 */
	public static void getAvailableCurrencies() throws Exception
	{
		// Get instance of AvailableCurrenciesOperations Class
		AvailableCurrenciesOperations currenciesOperations = new AvailableCurrenciesOperations();

		// Call getCurrencies method
		APIResponse<ResponseHandler> response = currenciesOperations.getAvailableCurrencies();

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
					// Get the received BodyWrapper instance
					ResponseWrapper responseWrapper = (ResponseWrapper) responseHandler;

					// Get the list of obtained Currency instances
					List<com.zoho.crm.api.availablecurrencies.Currency> currenciesList = responseWrapper.getAvailableCurrencies();

					for (com.zoho.crm.api.availablecurrencies.Currency currency : currenciesList)
					{
						// Get the DisplayValue of each currency
						System.out.println("Currency DisplayValue: " + currency.getDisplayValue());

						// Get the currency is DecimalSeparator
						System.out.println("Currency DecimalSeparator: " + currency.getDecimalSeparator());

						// Get the Symbol of each currency
						System.out.println("Currency Symbol: " + currency.getSymbol());

						// Get the ThousandSeparator of each currency
						System.out.println("Currency ThousandSeparator: " + currency.getThousandSeparator());

						// Get the IsoCode of each currency
						System.out.println("Currency IsoCode: " + currency.getIsoCode());

						// Get the DisplayName of each currency
						System.out.println("Currency DisplayName: " + currency.getDisplayName());

						// Get the ModifiedTime of each currency
						System.out.println("Currency DecimalPlaces: " + currency.getDecimalPlaces());
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
