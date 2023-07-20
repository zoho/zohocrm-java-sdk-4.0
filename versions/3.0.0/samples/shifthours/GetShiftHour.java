package shifthours;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.shifthours.APIException;
import com.zoho.crm.api.shifthours.BreakHours;
import com.zoho.crm.api.shifthours.BreakHoursCustomTiming;
import com.zoho.crm.api.shifthours.CustomTiming;
import com.zoho.crm.api.shifthours.Holidays;
import com.zoho.crm.api.shifthours.ResponseWrapper;
import com.zoho.crm.api.shifthours.ResponseHandler;
import com.zoho.crm.api.shifthours.ShiftCount;
import com.zoho.crm.api.shifthours.ShiftHour;
import com.zoho.crm.api.shifthours.ShiftHoursOperations;
import com.zoho.crm.api.shifthours.Users;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Choice;
import com.zoho.crm.api.util.Model;
import com.zoho.crm.api.dc.DataCenter.Environment;

public class GetShiftHour
{
	public static void getShiftHour(Long id) throws Exception
	{
		ShiftHoursOperations shifthoursoperations = new ShiftHoursOperations("440248000000020813");
		APIResponse<ResponseHandler> response = shifthoursoperations.getShiftHour(id);
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
				ResponseHandler responseObject = (ResponseHandler) response.getObject();
				if (responseObject instanceof ResponseWrapper)
				{
					ResponseWrapper responseWrapper = (ResponseWrapper) responseObject;
					ShiftCount shiftcount = responseWrapper.getShiftCount();
					System.out.println("Shift_count :");
					System.out.println("total_shift_with_user :" + shiftcount.getTotalShiftWithUser());
					System.out.println("total_shift :" + shiftcount.getTotalShift());
					List<ShiftHour> shifthours = responseWrapper.getShiftHours();
					if (shifthours != null)
					{
						for (ShiftHour shifthour : shifthours)
						{
							System.out.println("name : " + shifthour.getName());
							System.out.println("same_as_everyday : " + shifthour.getSameAsEveryday());
							System.out.println("users_count : " + shifthour.getUsersCount());
							System.out.println("timezone : " + shifthour.getTimezone().getID());
							List<Choice<String>> shiftDays = shifthour.getShiftDays();
							if (shiftDays != null)
							{
								System.out.println("ShiftDays : ");
								for (Choice<String> shiftDay : shiftDays)
								{
									System.out.println(shiftDay.getValue());
								}
							}
							List<String> dailyTiming = shifthour.getDailyTiming();
							if (dailyTiming != null)
							{
								System.out.println("Daily_timing : ");
								for (String dailytiming : dailyTiming)
								{
									System.out.println(dailytiming);
								}
							}
							List<CustomTiming> customTiming = shifthour.getCustomTiming();
							if (customTiming != null)
							{
								System.out.println("custom_timing : ");
								for (CustomTiming customtiming : customTiming)
								{
									List<String> shiftTiming = customtiming.getShiftTiming();
									if (shiftTiming != null)
									{
										System.out.println("shift_timing :");
										for (String shifttiming : shiftTiming)
										{
											System.out.println(shifttiming);
										}
									}
									System.out.println("days : " + customtiming.getDays().getValue());
								}
							}
							List<Holidays> holidays = shifthour.getHolidays();
							if (holidays != null)
							{
								System.out.println("holidays : ");
								for (Holidays holiday : holidays)
								{
									System.out.println("date : " + holiday.getDate());
									System.out.println("year : " + holiday.getYear());
									System.out.println("name : " + holiday.getName());
									System.out.println("id : " + holiday.getId());
								}
							}
							List<Users> users = shifthour.getUsers();
							if (users != null)
							{
								System.out.println("Users : ");
								for (Users user : users)
								{
									System.out.println("User_Id : " + user.getId());
									System.out.println("User_name : " + user.getName());
									System.out.println("User_email : " + user.getEmail());
									System.out.println("User_role : " + user.getRole());
									System.out.println("User_ZUID : " + user.getZuid());
									System.out.println("effective_from : " + user.getEffectiveFrom());
								}
							}
							List<BreakHours> breakHours = shifthour.getBreakHours();
							if (breakHours != null)
							{
								for (BreakHours breakhour : breakHours)
								{
									System.out.println("breakHour_ID : " + breakhour.getId());
									System.out.println("same_as_everyday : " + breakhour.getSameAsEveryday());
									List<Choice<String>> breakdays = breakhour.getBreakDays();
									if (breakdays != null)
									{
										for (Choice<String> breakday : breakdays)
										{
											System.out.println("breakdays : " + breakday.getValue());
										}
									}
									List<String> dailyTimings = breakhour.getDailyTiming();
									if (dailyTimings != null)
									{
										for (String dailytiming : dailyTimings)
										{
											System.out.println("dailyTiming : " + dailytiming);
										}
									}
									List<BreakHoursCustomTiming> breakHoursCustomTimings = breakhour.getCustomTiming();
									if (breakHoursCustomTimings != null)
									{
										for (BreakHoursCustomTiming breakHourCustomTiming : breakHoursCustomTimings)
										{
											System.out.println("CustomTiming : ");
											List<String> breakTimings = breakHourCustomTiming.getBreakTiming();
											if (breakTimings != null)
											{
												for (String breakTiming : breakTimings)
												{
													System.out.println("breakTiming : " + breakTiming);
												}
												System.out.println("days : " + breakHourCustomTiming.getDays().getValue());
											}
										}
									}
								}
							}
						}
					}
				}
				else if (responseObject instanceof APIException)
				{
					APIException exception = (APIException) responseObject;
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
			getShiftHour(4402481111074L);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
