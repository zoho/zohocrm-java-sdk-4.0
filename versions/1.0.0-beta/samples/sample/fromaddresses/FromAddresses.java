package com.zoho.crm.sample.fromaddresses;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.crm.api.fromaddresses.APIException;
import com.zoho.crm.api.fromaddresses.Address;
import com.zoho.crm.api.fromaddresses.FromAddressesOperations;
import com.zoho.crm.api.fromaddresses.ResponseHandler;
import com.zoho.crm.api.fromaddresses.ResponseWrapper;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class FromAddresses
{
	public static void getEmailAddresses() throws Exception
	{
		FromAddressesOperations sendMailsOperations = new FromAddressesOperations();

		APIResponse<ResponseHandler> response = sendMailsOperations.getAddresses();

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

					// Get the list of obtained UserAdress instances
					List<Address> userAddresses = responseWrapper.getFromAddresses();

					for (Address userAddress : userAddresses)
					{
						// Get the Email of each UserAdress
						System.out.println("UserAdress Email: " + userAddress.getEmail());

						// Get the Type of each UserAdress
						System.out.println("UserAdress Type: " + userAddress.getType());

						// Get the UserName of each UserAdress
						System.out.println("UserAdress UserName: " + userAddress.getUserName());

						// Get the Default of each UserAdress
						System.out.println("UserAdress Default: " + userAddress.getDefault());

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
			else if (response.getStatusCode() != 204)
			{// If response is not as expected

				// Get model object from response
				Model responseObject = response.getModel();

				// Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();

				// Get all declared fields of the response class
				Field[] fields = clas.getDeclaredFields();

				for (Field field : fields)
				{
					field.setAccessible(true);

					// Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}

	}

}
