package shifthours;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.shifthours.APIException;
import com.zoho.crm.api.shifthours.ActionWrapper;
import com.zoho.crm.api.shifthours.BreakHours;
import com.zoho.crm.api.shifthours.BreakHoursCustomTiming;
import com.zoho.crm.api.shifthours.ActionHandler;
import com.zoho.crm.api.shifthours.ActionResponse;
import com.zoho.crm.api.shifthours.CustomTiming;
import com.zoho.crm.api.shifthours.Holidays;
import com.zoho.crm.api.shifthours.RequestWrapper;
import com.zoho.crm.api.shifthours.ShiftHour;
import com.zoho.crm.api.shifthours.ShiftHoursOperations;
import com.zoho.crm.api.shifthours.SuccessResponse;
import com.zoho.crm.api.shifthours.Users;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Choice;
import com.zoho.crm.api.util.Model;

public class CreateShiftHours
{
	public static void createShiftHours() throws Exception
	{
		ShiftHoursOperations shifthoursoperations = new ShiftHoursOperations("440248000000020813");
		RequestWrapper request = new RequestWrapper();
		List<ShiftHour> shiftHours = new ArrayList<>();
		ShiftHour shifthours = new ShiftHour();
		shifthours.setTimezone(TimeZone.getTimeZone("Asia/Kolkata"));
		shifthours.setName("shift hours with holiday1");
		shifthours.setSameAsEveryday(false);
		List<Choice<String>> shiftDays = new ArrayList<>();
		shiftDays.add(new Choice<String>("Monday"));
		shiftDays.add(new Choice<String>("Tuesday"));
		shifthours.setShiftDays(shiftDays);
		//if same_as_everyday is true
		List<String> dailyTiming = new ArrayList<>();
		dailyTiming.add("10:00");
		dailyTiming.add("19:00");
		shifthours.setDailyTiming(dailyTiming);
		// if same_as_everyday is false
		List<CustomTiming> customTimings = new ArrayList<>();
		CustomTiming customtiming = new CustomTiming();
		List<String> shiftTiming = new ArrayList<>();
		shiftTiming.add("10:00");
		shiftTiming.add("19:00");
		customtiming.setShiftTiming(shiftTiming);
		customtiming.setDays(new Choice<String>("Monday"));
		customTimings.add(customtiming);
		CustomTiming customtiming1 = new CustomTiming();
		List<String> shiftTiming1 = new ArrayList<>();
		shiftTiming1.add("10:00");
		shiftTiming1.add("18:00");
		customtiming1.setShiftTiming(shiftTiming1);
		customtiming1.setDays(new Choice<String>("Tuesday"));
		customTimings.add(customtiming1);
		shifthours.setCustomTiming(customTimings);
		//
		List<BreakHours> breakHours = new ArrayList<>();
		BreakHours breakhour = new BreakHours();
		List<Choice<String>> breakDays = new ArrayList<>();
		breakDays.add(new Choice<String>("Monday"));
		breakDays.add(new Choice<String>("Tuesday"));
		breakhour.setBreakDays(breakDays);
		// if same_as_everday is True
		breakhour.setSameAsEveryday(true);
		List<String> dailytimingforBreakHours = new ArrayList<>();
		dailytimingforBreakHours.add("11:00");
		dailytimingforBreakHours.add("11:30");
		breakhour.setDailyTiming(dailytimingforBreakHours);
		breakHours.add(breakhour);
		shifthours.setBreakHours(breakHours);
		// if same_as_everyday is false
		breakhour.setSameAsEveryday(false);
		List<BreakHoursCustomTiming> customtimingsforBreakHours = new ArrayList<>();
		BreakHoursCustomTiming customTimingforBreakHour = new BreakHoursCustomTiming();
		List<String> breakTimings = new ArrayList<>();
		breakTimings.add("12:00");
		breakTimings.add("12:15");
		customTimingforBreakHour.setBreakTiming(breakTimings);
		customTimingforBreakHour.setDays(new Choice<String>("Monday"));
		customtimingsforBreakHours.add(customTimingforBreakHour);
		BreakHoursCustomTiming customTimingforBreakHour1 = new BreakHoursCustomTiming();
		List<String> breakTimings1 = new ArrayList<>();
		breakTimings1.add("11:00");
		breakTimings1.add("12:15");
		customTimingforBreakHour1.setBreakTiming(breakTimings1);
		customTimingforBreakHour1.setDays(new Choice<String>("Monday"));
		customtimingsforBreakHours.add(customTimingforBreakHour1);
		breakhour.setCustomTiming(customtimingsforBreakHours);
		breakHours.add(breakhour);
		shifthours.setBreakHours(breakHours);
		//
		List<Users> users = new ArrayList<>();
		Users user = new Users();
		user.setId(440248000000254001L);
//		user.setEffectiveFrom(LocalDate.of(2023, 10, 12));
		users.add(user);
		shifthours.setUsers(users);
		List<Holidays> holidays = new ArrayList<>();
		Holidays holiday = new Holidays();
		holiday.setDate(LocalDate.of(2023, 05, 01));
//		holiday.setId(23456L);
		holiday.setName("Holi10");
//		holiday.setYear(2023);
		holidays.add(holiday);
		shifthours.setHolidays(holidays);
		shiftHours.add(shifthours);
		request.setShiftHours(shiftHours);
		APIResponse<ActionHandler> response = shifthoursoperations.createShiftHours(request);
		if (response != null)
		{
			System.out.println("Status Code: " + response.getStatusCode());
			if (response.isExpected())
			{
				ActionHandler actionHandler = response.getObject();
				if (actionHandler instanceof ActionWrapper)
				{
					ActionWrapper actionWrapper = (ActionWrapper) actionHandler;
					List<ActionResponse> actionResponses = actionWrapper.getShiftHours();
					for (ActionResponse actionResponse : actionResponses)
					{
						if (actionResponse instanceof SuccessResponse)
						{
							SuccessResponse successresponse = (SuccessResponse) actionResponse;
							System.out.println("Status: " + successresponse.getStatus().getValue());
							System.out.println("Code: " + successresponse.getCode().getValue());
							System.out.println("Details: ");
							for (Map.Entry<String, Object> entry : successresponse.getDetails().entrySet())
							{
								System.out.println(entry.getKey() + ": " + entry.getValue());
							}
							System.out.println("Message: " + successresponse.getMessage());
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

	public static void main(String[] args)
	{
		try
		{
			Environment environment = USDataCenter.PRODUCTION;
			Token token = new OAuthToken.Builder().clientID("Client_Id").clientSecret("Client_Secret").refreshToken("Refresh_Token").redirectURL("Redirect_URL").build();
			new Initializer.Builder().environment(environment).token(token).initialize();
			createShiftHours();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
