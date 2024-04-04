package holidays;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.crm.api.HeaderMap;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.holidays.APIException;
import com.zoho.crm.api.holidays.ActionHandler;
import com.zoho.crm.api.holidays.ActionResponse;
import com.zoho.crm.api.holidays.ActionWrapper;
import com.zoho.crm.api.holidays.Holiday;
import com.zoho.crm.api.holidays.Holidays;
import com.zoho.crm.api.holidays.HolidaysOperations;
import com.zoho.crm.api.holidays.ShiftHour;
import com.zoho.crm.api.holidays.SuccessResponse;
import com.zoho.crm.api.holidays.HolidaysOperations.UpdateHolidaysHeader;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Choice;
import com.zoho.crm.api.util.Model;

public class UpdateHolidays {
	public static void updateHolidays() throws Exception {
		HolidaysOperations holidaysoperations = new HolidaysOperations();
		Holidays request = new Holidays();
		List<Holiday> holidays = new ArrayList<>();
		Holiday holiday = new Holiday();
		holiday.setId(4402481011100L);
		holiday.setName("holiday 1");
		holiday.setDate(LocalDate.of(2023, 8, 12));
		holiday.setType(new Choice<String>("shift_holiday"));
		//when type is Shift_holiday
		ShiftHour shifthour = new ShiftHour();
		shifthour.setName("shift hour for T");
		shifthour.setId(4402481024802L);
		holiday.setShiftHour(shifthour);
		//
		holiday.setYear(2023);
		holidays.add(holiday);
		request.setHolidays(holidays);
		HeaderMap headerinstance = new HeaderMap();
		headerinstance.add(UpdateHolidaysHeader.X_CRM_ORG, "440248020813");
		APIResponse<ActionHandler> response = holidaysoperations.updateHolidays(request, headerinstance);
		if (response != null)
		{
			System.out.println("Status Code: " + response.getStatusCode());
			if (response.isExpected())
			{
				ActionHandler actionHandler = response.getObject();
				if (actionHandler instanceof ActionWrapper)
				{
					ActionWrapper actionWrapper = (ActionWrapper) actionHandler;
					List<ActionResponse> actionResponses = actionWrapper.getHolidays();
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
	public static void main(String args[]) {
		try
		{
			Environment environment = USDataCenter.PRODUCTION;
			Token token = new OAuthToken.Builder().clientID("Client_Id").clientSecret("Client_Secret").refreshToken("Refresh_Token").redirectURL("Redirect_URL").build();
			new Initializer.Builder().environment(environment).token(token).initialize();
			updateHolidays();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
