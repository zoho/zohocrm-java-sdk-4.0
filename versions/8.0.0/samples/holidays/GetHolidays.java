package holidays;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.crm.api.HeaderMap;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.holidays.APIException;
import com.zoho.crm.api.holidays.Holiday;
import com.zoho.crm.api.holidays.Holidays;
import com.zoho.crm.api.holidays.HolidaysOperations;
import com.zoho.crm.api.holidays.HolidaysOperations.GetHolidaysHeader;
import com.zoho.crm.api.holidays.HolidaysOperations.GetHolidaysParam;
import com.zoho.crm.api.holidays.Info;
import com.zoho.crm.api.holidays.ResponseHandler;
import com.zoho.crm.api.holidays.ShiftHour;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class GetHolidays {
	public static void getHolidays() throws Exception {
		HolidaysOperations holidaysoperations = new HolidaysOperations();
		ParameterMap paraminstance = new ParameterMap();
		paraminstance.add(GetHolidaysParam.YEAR, 2023);
		paraminstance.add(GetHolidaysParam.SHIFT_ID, 4402481024802L);
		HeaderMap headerinstance = new HeaderMap();
		headerinstance.add(GetHolidaysHeader.X_CRM_ORG,"440248020813");
		APIResponse<ResponseHandler> response = holidaysoperations.getHolidays(paraminstance, headerinstance);
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
				if (responseHandler instanceof Holidays)
				{
					Holidays responseWrapper = (Holidays) responseHandler;
					List<Holiday> holidays = responseWrapper.getHolidays();
					if(holidays != null) {
						System.out.println("holidays : ");
						for (Holiday holiday : holidays)
						{
							System.out.println("Hoilday ID: " + holiday.getId());
							System.out.println("Name: " + holiday.getName());
							System.out.println("date: " + holiday.getDate());
							System.out.println("year: "+ holiday.getYear());
							System.out.println("type: " + holiday.getType().getValue());
							ShiftHour shifthour = holiday.getShiftHour();
							if(shifthour != null) {
								System.out.println("shifthour: ");
								System.out.println("name : "+ shifthour.getName());
								System.out.println("Shifthour id : " + shifthour.getId());
							}
						}
					}
					Info info = responseWrapper.getInfo();
					if(info != null) {
						System.out.println("info : ");
						System.out.println("perpage : "+info.getPerPage());
						System.out.println("count : "+ info.getCount());
						System.out.println("page : " + info.getPage());
						System.out.println("more_records : "+ info.getMoreRecords());
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
			else if (response.getStatusCode() != 204)
			{
				Model responseObject = response.getModel();
				Class<? extends Model> clas = responseObject.getClass();
				Field[] fields = clas.getDeclaredFields();
				for (Field field : fields)
				{
					field.setAccessible(true);
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
			getHolidays();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
