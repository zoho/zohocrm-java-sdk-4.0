package scoringrules;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.scoringrules.APIException;
import com.zoho.crm.api.scoringrules.EntityResponseWrapper;
import com.zoho.crm.api.scoringrules.EntityScore;
import com.zoho.crm.api.scoringrules.Info;
import com.zoho.crm.api.scoringrules.ResponseHandler;
import com.zoho.crm.api.scoringrules.ScoringRulesOperations;
import com.zoho.crm.api.scoringrules.ScoringRulesOperations.GetEntityScoreRecordsParam;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class GetEntityScoreRecords
{
	public static void getEntityScoreRecords() throws Exception
	{
		ScoringRulesOperations scoringRulesOperations = new ScoringRulesOperations();
		ParameterMap paramInstance = new ParameterMap();
		paramInstance.add(GetEntityScoreRecordsParam.FIELDS, "Positive_Score");
		APIResponse<ResponseHandler> response = scoringRulesOperations.getEntityScoreRecords(paramInstance);
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
				if (responseHandler instanceof EntityResponseWrapper)
				{
					EntityResponseWrapper responseWrapper = (EntityResponseWrapper) responseHandler;
					List<EntityScore> entityScores = responseWrapper.getData();
					for (EntityScore entityScore : entityScores)
					{
						System.out.println("EntityScore Id: " + entityScore.getId());
						System.out.println("EntityScore Score: " + entityScore.getScore());
					}
					Info info = responseWrapper.getInfo();
					if (info != null)
					{
						if (info.getPerPage() != null)
						{
							System.out.println("Info PerPage: " + info.getPerPage().toString());
						}
						if (info.getCount() != null)
						{
							System.out.println("Info Count: " + info.getCount().toString());
						}
						if (info.getPage() != null)
						{
							System.out.println("Info Page: " + info.getPage());
						}
						if (info.getMoreRecords() != null)
						{
							System.out.println("Info MoreRecords: " + info.getMoreRecords().toString());
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
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else if (response.getStatusCode() != 204)
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
			getEntityScoreRecords();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
