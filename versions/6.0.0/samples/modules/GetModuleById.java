package modules;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.customviews.SharedTo;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.modules.APIException;
import com.zoho.crm.api.modules.Argument;
import com.zoho.crm.api.modules.Criteria;
import com.zoho.crm.api.modules.CustomView;
import com.zoho.crm.api.modules.GroupCriteria;
import com.zoho.crm.api.modules.MinifiedModule;
import com.zoho.crm.api.modules.ModuleMeta;
import com.zoho.crm.api.modules.ModulesOperations;
import com.zoho.crm.api.modules.RelatedListProperties;
import com.zoho.crm.api.modules.SingleCriteria;
import com.zoho.crm.api.modules.SingleResponseHandler;
import com.zoho.crm.api.modules.SingleResponseWrapper;
import com.zoho.crm.api.profiles.Profile;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.Model;

public class GetModuleById
{
	public static void getModuleById(Long moduleId) throws Exception
	{
		ModulesOperations moduleOperations = new ModulesOperations();
		APIResponse<SingleResponseHandler> response = moduleOperations.getModule(moduleId);
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
				SingleResponseHandler responseHandler = response.getObject();
				if (responseHandler instanceof SingleResponseWrapper)
				{
					SingleResponseWrapper responseWrapper = (SingleResponseWrapper) responseHandler;
					List<ModuleMeta> modules = responseWrapper.getModules();
					for (ModuleMeta module : modules)
					{
						System.out.println("Module GlobalSearchSupported: " + module.getGlobalSearchSupported());
						if (module.getKanbanView() != null)
						{
							System.out.println("Module KanbanView: " + module.getKanbanView());
						}
						System.out.println("Module Deletable: " + module.getDeletable());
						System.out.println("Module Description: " + module.getDescription());
						System.out.println("Module Creatable: " + module.getCreatable());
						if (module.getFilterStatus() != null)
						{
							System.out.println("Module FilterStatus: " + module.getFilterStatus());
						}
						System.out.println("Module InventoryTemplateSupported: " + module.getInventoryTemplateSupported());
						if (module.getModifiedTime() != null)
						{
							System.out.println("Module ModifiedTime: " + module.getModifiedTime());
						}
						System.out.println("Module PluralLabel: " + module.getPluralLabel());
						System.out.println("Module PresenceSubMenu: " + module.getPresenceSubMenu());
						System.out.println("Module TriggersSupported: " + module.getTriggersSupported());
						System.out.println("Module Id: " + module.getId());
						// check if blueprint is supported for each Module
						System.out.println("Module IsBlueprintSupported : " + module.getIsblueprintsupported());
						RelatedListProperties relatedListProperties = module.getRelatedListProperties();
						if (relatedListProperties != null)
						{
							System.out.println("Module RelatedListProperties SortBy: " + relatedListProperties.getSortBy());
							List<String> fields = relatedListProperties.getFields();
							if (fields != null)
							{
								for (Object fieldName : fields)
								{
									System.out.println("Module RelatedListProperties Fields: " + fieldName);
								}
							}
							System.out.println("Module RelatedListProperties SortOrder: " + relatedListProperties.getSortOrder());
						}
						System.out.println("Module PerPage: " + module.getPerPage());
						List<String> properties = module.getProperties();
						if (properties != null)
						{
							for (Object fieldName : properties)
							{
								System.out.println("Module Properties Fields: " + fieldName);
							}
						}
						System.out.println("Module visible: " + module.getVisible());
						System.out.println("Module Visibility: " + module.getVisibility());
						System.out.println("Module Convertable: " + module.getConvertable());
						System.out.println("Module Editable: " + module.getEditable());
						System.out.println("Module EmailtemplateSupport: " + module.getEmailtemplateSupport());
						List<Profile> profiles = module.getProfiles();
						if (profiles != null)
						{
							for (Profile profile : profiles)
							{
								System.out.println("Module Profile Name: " + profile.getName());
								System.out.println("Module Profile Id: " + profile.getId());
							}
						}
						System.out.println("Module FilterSupported: " + module.getFilterSupported());
						List<String> onDemandProperties = module.getOnDemandProperties();
						if (onDemandProperties != null)
						{
							for (Object fieldName : onDemandProperties)
							{
								System.out.println("Module onDemandProperties Fields: " + fieldName);
							}
						}
						System.out.println("Module DisplayField: " + module.getDisplayField());
						List<String> searchLayoutFields = module.getSearchLayoutFields();
						if (searchLayoutFields != null)
						{
							for (Object fieldName : searchLayoutFields)
							{
								System.out.println("Module SearchLayoutFields Fields: " + fieldName);
							}
						}
						if (module.getKanbanViewSupported() != null)
						{
							System.out.println("Module KanbanViewSupported: " + module.getKanbanViewSupported());
						}
						System.out.println("Module ShowAsTab: " + module.getShowAsTab());
						System.out.println("Module WebLink: " + module.getWebLink());
						System.out.println("Module SequenceNumber: " + module.getSequenceNumber());
						System.out.println("Module SingularLabel: " + module.getSingularLabel());
						System.out.println("Module Viewable: " + module.getViewable());
						System.out.println("Module APISupported: " + module.getAPISupported());
						System.out.println("Module APIName: " + module.getAPIName());
						System.out.println("Module QuickCreate: " + module.getQuickCreate());
						com.zoho.crm.api.users.MinifiedUser modifiedBy = module.getModifiedBy();
						if (modifiedBy != null)
						{
							System.out.println("Module Modified By User-Name: " + modifiedBy.getName());
							System.out.println("Module Modified By User-ID: " + modifiedBy.getId());
						}
						System.out.println("Module GeneratedType: " + module.getGeneratedType().getValue());
						System.out.println("Module FeedsRequired: " + module.getFeedsRequired());
						System.out.println("Module ScoringSupported: " + module.getScoringSupported());
						System.out.println("Module WebformSupported: " + module.getWebformSupported());
						List<Argument> arguments = module.getArguments();
						if (arguments != null)
						{
							for (Argument argument : arguments)
							{
								System.out.println("Module Argument Name: " + argument.getName());
								System.out.println("Module Argument Value: " + argument.getValue());
							}
						}
						System.out.println("Module ModuleName: " + module.getModuleName());
						System.out.println("Module BusinessCardFieldLimit: " + module.getBusinessCardFieldLimit());
						CustomView customView = module.getCustomView();
						if (customView != null)
						{
							printCustomView(customView);
						}
						MinifiedModule parentModule = module.getParentModule();
						if (parentModule != null)
						{
							System.out.println("Module Parent Module Name: " + parentModule.getAPIName());
							System.out.println("Module Parent Module Id: " + parentModule.getId());
						}
					}
				}
				else if (responseHandler instanceof APIException)
				{
					APIException exception = (APIException) responseHandler;
					System.out.println("Status: " + exception.getStatus().getValue());
					System.out.println("Code: " + exception.getCode().getValue());
					System.out.println("Details: ");
					if (exception.getDetails() != null)
					{
						for (Map.Entry<String, Object> entry : exception.getDetails().entrySet())
						{
							System.out.println(entry.getKey() + ": " + entry.getValue());
						}
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

	private static void printCustomView(CustomView customView)
	{
		System.out.println("Module CustomView DisplayValue: " + customView.getDisplayValue());
		if (customView.getCreatedTime() != null)
		{
			System.out.println("Module CustomView CreatedTime: " + customView.getCreatedTime());
		}
		System.out.println("Module CustomView AccessType: " + customView.getAccessType());
		Criteria criteria = customView.getCriteria();
		if (criteria != null)
		{
			printCriteria(criteria);
		}
		System.out.println("Module CustomView SystemName: " + customView.getSystemName());
		System.out.println("Module CustomView SortBy: " + customView.getSortBy());
		com.zoho.crm.api.users.MinifiedUser createdBy = customView.getCreatedBy();
		if (createdBy != null)
		{
			System.out.println("Module Created By User-Name: " + createdBy.getName());
			System.out.println("Module Created By User-ID: " + createdBy.getId());
		}
		List<SharedTo> sharedToDetails = customView.getSharedTo();
		if (sharedToDetails != null)
		{
			for (SharedTo sharedTo : sharedToDetails)
			{
				System.out.println("SharedDetails Name: " + sharedTo.getName());
				System.out.println("SharedDetails ID: " + sharedTo.getId());
				System.out.println("SharedDetails Type: " + sharedTo.getType());
				System.out.println("SharedDetails Subordinates: " + sharedTo.getSubordinates());
			}
		}
		System.out.println("Module CustomView Default: " + customView.getDefault());
		if (customView.getModifiedTime() != null)
		{
			System.out.println("Module CustomView ModifiedTime: " + customView.getModifiedTime());
		}
		System.out.println("Module CustomView Name: " + customView.getName());
		System.out.println("Module CustomView SystemDefined: " + customView.getSystemDefined());
		com.zoho.crm.api.users.MinifiedUser modifiedBy = customView.getModifiedBy();
		if (modifiedBy != null)
		{
			System.out.println("Module Modified By User-Name: " + modifiedBy.getName());
			System.out.println("Module Modified By User-ID: " + modifiedBy.getId());
		}
		System.out.println("Module CustomView ID: " + customView.getId());
		List<com.zoho.crm.api.fields.MinifiedFields> fields = customView.getFields();
		if (fields != null)
		{
			for (com.zoho.crm.api.fields.MinifiedFields field : fields)
			{
				System.out.println("Module CustomView Field Id: " + field.getId());
				System.out.println("Module CustomView Field APIName: " + field.getAPIName());
			}
		}
		System.out.println("Module CustomView Category: " + customView.getCategory());
		if (customView.getLastAccessedTime() != null)
		{
			System.out.println("Module CustomView LastAccessedTime: " + customView.getLastAccessedTime());
		}
		if (customView.getFavorite() != null)
		{
			System.out.println("Module CustomView Favorite: " + customView.getFavorite());
		}
		if (customView.getSortOrder() != null)
		{
			System.out.println("Module CustomView SortOrder: " + customView.getSortOrder());
		}
	}

	private static void printCriteria(Criteria criteria)
	{
		if (criteria instanceof SingleCriteria)
		{
			SingleCriteria singlecriteria = (SingleCriteria) criteria;
			if (singlecriteria.getComparator() != null)
			{
				System.out.println("CustomView Criteria Comparator: " + singlecriteria.getComparator());
			}
			if (singlecriteria.getField() != null)
			{
				System.out.println("CustomView Criteria field name: " + singlecriteria.getField().getAPIName());
			}
			if (singlecriteria.getValue() != null)
			{
				System.out.println("CustomView Criteria Value: " + singlecriteria.getValue().toString());
			}
		}
		else if (criteria instanceof GroupCriteria)
		{
			GroupCriteria groupcriteria = (GroupCriteria) criteria;
			List<com.zoho.crm.api.modules.Criteria> criteriaGroup = groupcriteria.getGroup();
			if (criteriaGroup != null)
			{
				for (Criteria criteria1 : criteriaGroup)
				{
					printCriteria(criteria1);
				}
			}
			if (groupcriteria.getGroupOperator() != null)
			{
				System.out.println("CustomView Criteria Group Operator: " + groupcriteria.getGroupOperator());
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
			Long moduleId = 347706115237003l;
			getModuleById(moduleId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
