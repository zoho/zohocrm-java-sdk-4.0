package usergroups;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.usergroups.APIException;
import com.zoho.crm.api.usergroups.JobHandler;
import com.zoho.crm.api.usergroups.Jobs;
import com.zoho.crm.api.usergroups.JobsWrapper;
import com.zoho.crm.api.usergroups.UserGroupsOperations;
import com.zoho.crm.api.usergroups.UserGroupsOperations.GetStatusParam;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class GetStatus
{
	public static void getStatus(Long jobId) throws Exception
	{
		UserGroupsOperations userGroupsOperations = new UserGroupsOperations();
		ParameterMap paramInstance = new ParameterMap();
		paramInstance.add(GetStatusParam.JOB_ID, jobId);
		APIResponse<JobHandler> response = userGroupsOperations.getStatus(paramInstance);
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
				JobHandler jobHandler = response.getObject();
				if (jobHandler instanceof JobsWrapper)
				{
					JobsWrapper responseWrapper = (JobsWrapper) jobHandler;
					List<com.zoho.crm.api.usergroups.JobResponse> users = responseWrapper.getDeletionJobs();
					for (com.zoho.crm.api.usergroups.JobResponse user : users)
					{
						if (user instanceof Jobs)
						{
							Jobs jobs = (Jobs) user;
							System.out.println("Status: " + jobs.getStatus());
						}
					}
				}
				else if (jobHandler instanceof APIException)
				{
					APIException exception = (APIException) jobHandler;
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
			Long jobId = 347706116712017l;
			getStatus(jobId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
