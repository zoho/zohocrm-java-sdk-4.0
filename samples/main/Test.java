package com.zoho.crm.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.zoho.crm.sample.initializer.Initialize;

public class Test
{
	public static void main(String[] args) throws Exception
	{
		Initialize.initialize();
		AssignmentRule();
		Attachment();
		AvailableCurrencies();
		BluePrint();
		BulkRead();
		BulkWrite();
		ChangeOwner();
		ContactRoles();
		Currency();
		CustomView();
		DealContactRoles();
		EmailRelatedRecords();
		EmailTemplates();
		FieldAttachments();
		FieldMapDependency();
		Field();
		File();
		FromAddresses();
		InventoryTemplates();
		Layout();
		MassChangeOwner();
		MassConvert();
		MassDeleteCvid();
		Module();
		Note();
		Notification();
		Organization();
		Pipeline();
		Profile();
		Query();
		Record();
		RelatedList();
		RelatedRecords();
		Role();
		ScoringRules();
		SendMail();
		ShareRecords();
		Tags();
		Tax();
		Territory();
		UserGroups();
		User();
		UsersTerritories();
		UsersUnavailability();
		VariableGroup();
		Variable();
		Wizards();
	}

	public static void AssignmentRule()
	{
		try
		{
			String moduleAPIName = "Leads";
			
			com.zoho.crm.sample.assignmentrules.AssignmentRules.getAssignmentRule(34770614353013l, moduleAPIName);
			
			com.zoho.crm.sample.assignmentrules.AssignmentRules.getAssignmentRules(moduleAPIName);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void Attachment()
	{
		try
		{
			String moduleAPIName = "Leads";

			Long recordId = 347706111829018l;

			Long attachmentId = 3477061166635l;

			String absoluteFilePath = "/usr/file/download.png";

			String destinationFolder = "/usr/file";

			String attachmentURL = "https://5.imimg.com/data5/KJ/UP/MY-8655440/zoho-crm-500x500.png";

			List<Long> attachmentIds = new ArrayList<Long>(Arrays.asList(3477061166621l));

			com.zoho.crm.sample.attachments.Attachment.uploadAttachments(moduleAPIName, recordId, absoluteFilePath);

			com.zoho.crm.sample.attachments.Attachment.getAttachments(moduleAPIName, recordId);

			com.zoho.crm.sample.attachments.Attachment.deleteAttachments(moduleAPIName, recordId, attachmentIds);

			com.zoho.crm.sample.attachments.Attachment.downloadAttachment(moduleAPIName, recordId, attachmentId, destinationFolder);

			com.zoho.crm.sample.attachments.Attachment.deleteAttachment(moduleAPIName, recordId, attachmentId);

			com.zoho.crm.sample.attachments.Attachment.uploadLinkAttachments(moduleAPIName, recordId, attachmentURL);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void AvailableCurrencies()
	{
		try
		{
			com.zoho.crm.sample.availablecurrencies.AvailableCurrencies.getAvailableCurrencies();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void BluePrint()
	{
		try
		{
			String moduleAPIName = "Leads";

			Long recordId = 347706116634119l;

			Long transitionId = 34770610173093l;

			com.zoho.crm.sample.blueprint.BluePrint.getBlueprint(moduleAPIName, recordId);

			com.zoho.crm.sample.blueprint.BluePrint.updateBlueprint(moduleAPIName, recordId, transitionId);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void BulkRead()
	{
		try
		{
			Long jobId = 3477061166651l;

			String destinationFolder = "/usr/file";

			com.zoho.crm.sample.bulkread.BulkRead.createBulkReadJob("Leads");

			com.zoho.crm.sample.bulkread.BulkRead.getBulkReadJobDetails(jobId);

			com.zoho.crm.sample.bulkread.BulkRead.downloadResult(jobId, destinationFolder);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void BulkWrite()
	{
		try
		{
			String absoluteFilePath = "/usr/Leads.zip";

			String destinationFolder = "/usr/file";

			String orgID = "xxx";

			String moduleAPIName = "Leads";

			String fileId = "3477061166741";

			Long jobID = 3477061166761l;

			String downloadUrl = "https://download-accl.zoho.com/v2/crm/xxxx/bulk-write/347706116659035/347706116659035.zip";

			com.zoho.crm.sample.bulkwrite.BulkWrite.uploadFile(orgID, absoluteFilePath);

			com.zoho.crm.sample.bulkwrite.BulkWrite.createBulkWriteJob(moduleAPIName, fileId);

			com.zoho.crm.sample.bulkwrite.BulkWrite.getBulkWriteJobDetails(jobID);

			com.zoho.crm.sample.bulkwrite.BulkWrite.downloadBulkWriteResult(downloadUrl, destinationFolder);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void ChangeOwner()
	{
		try
		{
			com.zoho.crm.sample.changeowner.ChangeOwner.updateRecordsOwner("Leads");

			com.zoho.crm.sample.changeowner.ChangeOwner.updateRecordOwner("Leads", 3477061145631l);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void ContactRoles()
	{
		try
		{
			Long contactRoleId = 3477061113411l;

			ArrayList<Long> contactRoleIds = new ArrayList<Long>(Arrays.asList(347706110709051l, 347706152082l));

			com.zoho.crm.sample.contactroles.ContactRoles.getContactRoles();

			com.zoho.crm.sample.contactroles.ContactRoles.createContactRoles();

			com.zoho.crm.sample.contactroles.ContactRoles.updateContactRoles();

			com.zoho.crm.sample.contactroles.ContactRoles.deleteContactRoles(contactRoleIds);

			com.zoho.crm.sample.contactroles.ContactRoles.getContactRole(contactRoleId);

			com.zoho.crm.sample.contactroles.ContactRoles.updateContactRole(contactRoleId);

			com.zoho.crm.sample.contactroles.ContactRoles.deleteContactRole(contactRoleId);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void Currency()
	{
		try
		{
			Long currencyId = 34770617368016l;

			com.zoho.crm.sample.currencies.Currency.getCurrencies();

			com.zoho.crm.sample.currencies.Currency.addCurrencies();

			com.zoho.crm.sample.currencies.Currency.updateCurrencies();

			com.zoho.crm.sample.currencies.Currency.enableMultipleCurrencies();

			com.zoho.crm.sample.currencies.Currency.updateBaseCurrency();

			com.zoho.crm.sample.currencies.Currency.getCurrency(currencyId);

			com.zoho.crm.sample.currencies.Currency.updateCurrency(currencyId);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void CustomView()
	{
		try
		{
			String moduleAPIName = "Leads";

			Long customID = 347706149373l;

			List<String> names = new ArrayList<String>(Arrays.asList("Products", "Tasks", "Vendors", "Calls", "Leads", "Deals", "Campaigns", "Quotes", "Invoices", "Attachments", "Price_Books", "Sales_Orders", "Contacts", "Solutions", "Events", "Purchase_Orders", "Accounts", "Cases", "Notes"));

			for (String name : names)
			{
				com.zoho.crm.sample.customview.CustomView.getCustomViews(name);
			}

			com.zoho.crm.sample.customview.CustomView.getCustomViews(moduleAPIName);

			com.zoho.crm.sample.customview.CustomView.getCustomView(moduleAPIName, customID);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void DealContactRoles()
	{
		try
		{
			Long dealId = 3477061112997l;
			
			Long contactId = 347706114564040l;
			
			com.zoho.crm.sample.dealcontactroles.DealContactRoles.getAllContactRolesOfDeal(dealId);
			
			com.zoho.crm.sample.dealcontactroles.DealContactRoles.getContactRoleOfDeal(contactId, dealId);
			
			com.zoho.crm.sample.dealcontactroles.DealContactRoles.addContactRoleToDeal(contactId, dealId);
			
			com.zoho.crm.sample.dealcontactroles.DealContactRoles.removeContactRoleFromDeal(contactId, dealId);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void EmailRelatedRecords()
	{
		try
		{
			com.zoho.crm.sample.emailrelatedrecords.EmailRelatedRecords.getRelatedEmail();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void EmailTemplates()
	{
		try
		{
			com.zoho.crm.sample.emailtemplates.EmailTemplates.getEmailTemplates();
			com.zoho.crm.sample.emailtemplates.EmailTemplates.getEmailTemplateById(347706179l);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void FieldAttachments()
	{
		try
		{
			String destinationFolder = "/usr/file";

			com.zoho.crm.sample.fieldattachments.FieldAttachments.getFieldAttachments(destinationFolder, "Leads", 3477061116132l, 347706111613032l);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void FieldMapDependency()
	{
		try
		{
			String module = "Leads";
			
			Long layoutId = 347706191055l;
			
			Long dependencyId = 3477061167121l;
			
			com.zoho.crm.sample.fieldmapdependency.FieldMapDependency.getMapDependencies(layoutId, module);
			
			com.zoho.crm.sample.fieldmapdependency.FieldMapDependency.createMapDependency(layoutId, module);
			
			com.zoho.crm.sample.fieldmapdependency.FieldMapDependency.updateMapDependency(layoutId, module, dependencyId);
			
			com.zoho.crm.sample.fieldmapdependency.FieldMapDependency.getMapDependency(layoutId, module, dependencyId);
			
			com.zoho.crm.sample.fieldmapdependency.FieldMapDependency.deleteMapDependency(layoutId, module, dependencyId);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void Field()
	{
		try
		{
			String moduleAPIName = "Quotes";

			Long fieldId = 34770612565l;

			List<String> names = new ArrayList<String>(Arrays.asList("Products", "Tasks", "Vendors", "Calls", "Leads", "Deals", "Campaigns", "Quotes", "Invoices", "Attachments", "Price_Books", "Sales_Orders", "Contacts", "Solutions", "Events", "Purchase_Orders", "Accounts", "Cases", "Notes"));

			for (String name : names)
			{
				com.zoho.crm.sample.fields.Fields.getFields(name);
			}

			com.zoho.crm.sample.fields.Fields.getFields(moduleAPIName);

			com.zoho.crm.sample.fields.Fields.getField(moduleAPIName, fieldId);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void File()
	{
		try
		{
			String destinationFolder = "/usr/file";

			String id = "ae9c7cefa41823b5ac5a9761147c3d4d0660";

			com.zoho.crm.sample.file.File.uploadFiles();

			com.zoho.crm.sample.file.File.getFile(id, destinationFolder);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void FromAddresses()
	{
		try
		{
			com.zoho.crm.sample.fromaddresses.FromAddresses.getEmailAddresses();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void InventoryTemplates()
	{
		try
		{
			com.zoho.crm.sample.inventorytemplates.InventoryTemplates.getInventoryTemplates();
			com.zoho.crm.sample.inventorytemplates.InventoryTemplates.getInventoryTemplate(347706101743l);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void Layout()
	{
		try
		{
			String moduleAPIName = "Leads";

			Long layoutId = 347706191055l;

			List<String> names = new ArrayList<String>(Arrays.asList("Products", "Tasks", "Vendors", "Calls", "Leads", "Deals", "Campaigns", "Quotes", "Invoices", "Attachments", "Price_Books", "Sales_Orders", "Contacts", "Solutions", "Events", "Purchase_Orders", "Accounts", "Cases", "Notes", "Email_Analytics"));

			for (String name : names)
			{
				com.zoho.crm.sample.layouts.Layout.getLayouts(name);
			}

			com.zoho.crm.sample.layouts.Layout.getLayouts(moduleAPIName);

			com.zoho.crm.sample.layouts.Layout.getLayout(moduleAPIName, layoutId);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void Module()
	{
		try
		{
			String moduleAPIName = "DOT1";

			Long moduleId = 347706139053L;

			com.zoho.crm.sample.modules.Modules.getModules();

			com.zoho.crm.sample.modules.Modules.getModuleByAPIName(moduleAPIName);

			com.zoho.crm.sample.modules.Modules.updateModuleByAPIName(moduleAPIName);

			com.zoho.crm.sample.modules.Modules.getModuleById(moduleId);
			
			com.zoho.crm.sample.modules.Modules.updateModuleById(moduleId);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void MassChangeOwner()
	{
		try
		{
			String moduleAPIName = "DOT";
			
			Long jobId = 347706116712l;
			
			com.zoho.crm.sample.masschangeowner.MassChangeOwner.changeOwner(moduleAPIName);
			
			com.zoho.crm.sample.masschangeowner.MassChangeOwner.checkStatus(jobId, moduleAPIName);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void MassConvert()
	{
		try
		{
			Long jobId = 3477061167047l;
			
			com.zoho.crm.sample.massconvert.MassConvert.massConvert();
			
			com.zoho.crm.sample.massconvert.MassConvert.getJobStatus(jobId);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void MassDeleteCvid()
	{
		try
		{
			String moduleAPIName = "Leads";

			Long jobId = 347706116634118l;
			
			com.zoho.crm.sample.massdeletecvid.MassDeleteCvid.massDeleteByCvid(moduleAPIName);
			
			com.zoho.crm.sample.massdeletecvid.MassDeleteCvid.getMassDeleteStatus(jobId, moduleAPIName);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void Note()
	{
		try
		{
			ArrayList<Long> notesId = new ArrayList<Long>(Arrays.asList(347706116695010l, 347706116695011l, 347706116695012l));

			Long noteId = 347706192954l;

			com.zoho.crm.sample.notes.Note.getNotes();

			com.zoho.crm.sample.notes.Note.createNotes();

			com.zoho.crm.sample.notes.Note.updateNotes();

			com.zoho.crm.sample.notes.Note.deleteNotes(notesId);

			com.zoho.crm.sample.notes.Note.getNote(noteId);

			com.zoho.crm.sample.notes.Note.updateNote(noteId);

			com.zoho.crm.sample.notes.Note.deleteNote(noteId);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void Notification()
	{
		try
		{
			ArrayList<Long> channelIds = new ArrayList<Long>(Arrays.asList(168211l));

			com.zoho.crm.sample.notification.Notification.enableNotifications();

			com.zoho.crm.sample.notification.Notification.getNotificationDetails();

			com.zoho.crm.sample.notification.Notification.updateNotifications();

			com.zoho.crm.sample.notification.Notification.updateNotification();

			com.zoho.crm.sample.notification.Notification.disableNotifications(channelIds);

			com.zoho.crm.sample.notification.Notification.disableNotification();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void Organization()
	{
		try
		{
			String absoluteFilePath = "/usr/file/download.png";

			com.zoho.crm.sample.organization.Organization.getOrganization();

			com.zoho.crm.sample.organization.Organization.uploadOrganizationPhoto(absoluteFilePath);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void Pipeline()
	{
		try
		{
			Long layoutID = 347706191023l;

			Long pipelineID = 34770619599012l;

			com.zoho.crm.sample.pipeline.Pipeline.getPipelines(layoutID);

			com.zoho.crm.sample.pipeline.Pipeline.createPipelines(layoutID);

			com.zoho.crm.sample.pipeline.Pipeline.getPipeline(layoutID, pipelineID);

			com.zoho.crm.sample.pipeline.Pipeline.TransferAndDelete(layoutID);

			com.zoho.crm.sample.pipeline.Pipeline.updatePipeline(layoutID, pipelineID);

			com.zoho.crm.sample.pipeline.Pipeline.updatePipelines(layoutID);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void Profile()
	{
		try
		{
			Long profileId = 3477061167088l;
			
			Long existingprofileid = 3477061167158l;

			com.zoho.crm.sample.profile.Profile.getProfiles();

			com.zoho.crm.sample.profile.Profile.getProfile(profileId);
			
			com.zoho.crm.sample.profile.Profile.cloneProfiles(profileId);
			
			com.zoho.crm.sample.profile.Profile.updateProfile(profileId);
			
			com.zoho.crm.sample.profile.Profile.deleteProfile(profileId, existingprofileid);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void Query()
	{
		try
		{
			com.zoho.crm.sample.query.Query.getRecords();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void Record()
	{
		try
		{
			String moduleAPIName = "Leads";

			long recordId = 347706116634117l;

			String externalFieldValue = "TestExternalLead11";

			String destinationFolder = "/usr/file/";

			String absoluteFilePath = "/usr/file/download.png";

			List<String> recordIds = new ArrayList<String>(Arrays.asList("Value1234", "34770615908017l", "347706159081l"));

			String jobId = "347706116719015";

			List<String> names = new ArrayList<String>(Arrays.asList("Products", "Tasks", "Vendors", "Calls", "Leads", "Deals", "Campaigns", "Quotes", "Invoices", "Attachments", "Price_Books", "Sales_Orders", "Contacts", "Solutions", "Events", "Purchase_Orders", "Accounts", "Cases", "Notes"));

			for (String name : names)
			{
				com.zoho.crm.sample.record.Record.getRecords(name);
			}

			com.zoho.crm.sample.record.Record.getRecord(moduleAPIName, recordId, destinationFolder);

			com.zoho.crm.sample.record.Record.updateRecord(moduleAPIName, recordId);

			com.zoho.crm.sample.record.Record.deleteRecord(moduleAPIName, recordId);

			com.zoho.crm.sample.record.Record.getRecordUsingExternalId(moduleAPIName, externalFieldValue, destinationFolder);

			com.zoho.crm.sample.record.Record.updateRecordUsingExternalId(moduleAPIName, externalFieldValue);

			com.zoho.crm.sample.record.Record.deleteRecordUsingExternalId(moduleAPIName, externalFieldValue);

			com.zoho.crm.sample.record.Record.getRecords(moduleAPIName);

			com.zoho.crm.sample.record.Record.createRecords(moduleAPIName);

			com.zoho.crm.sample.record.Record.updateRecords(moduleAPIName);

			com.zoho.crm.sample.record.Record.deleteRecords(moduleAPIName, recordIds);

			com.zoho.crm.sample.record.Record.upsertRecords(moduleAPIName);

			com.zoho.crm.sample.record.Record.getDeletedRecords(moduleAPIName);

			com.zoho.crm.sample.record.Record.searchRecords(moduleAPIName);

			com.zoho.crm.sample.record.Record.convertLead(recordId);

			com.zoho.crm.sample.record.Record.getPhoto(moduleAPIName, recordId, destinationFolder);

			com.zoho.crm.sample.record.Record.uploadPhoto(moduleAPIName, recordId, absoluteFilePath);

			com.zoho.crm.sample.record.Record.deletePhoto(moduleAPIName, recordId);

			com.zoho.crm.sample.record.Record.massUpdateRecords(moduleAPIName);

			com.zoho.crm.sample.record.Record.getMassUpdateStatus(moduleAPIName, jobId);

			com.zoho.crm.sample.record.Record.getRecordCount();

			com.zoho.crm.sample.record.Record.assignTerritoriesToMultipleRecords(moduleAPIName);

			com.zoho.crm.sample.record.Record.assignTerritoryToRecord(moduleAPIName, recordId);

			com.zoho.crm.sample.record.Record.removeTerritoriesFromMultipleRecords(moduleAPIName);

			com.zoho.crm.sample.record.Record.removeTerritoriesFromRecord(moduleAPIName, recordId);
//
			com.zoho.crm.sample.record.Record.leadConversionOptions(recordId);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void RelatedList()
	{
		try
		{
			String moduleAPIName = "Leads";

			Long relatedListId = 34770616819126l;

			List<String> names = new ArrayList<String>(Arrays.asList("Products", "Tasks", "Vendors", "Calls", "Leads", "Deals", "Campaigns", "Quotes", "Invoices", "Attachments", "Price_Books", "Sales_Orders", "Contacts", "Solutions", "Events", "Purchase_Orders", "Accounts", "Cases"));

			for (String name : names)
			{
				com.zoho.crm.sample.relatedlist.RelatedList.getRelatedLists(name);
			}

			com.zoho.crm.sample.relatedlist.RelatedList.getRelatedLists(moduleAPIName);

			com.zoho.crm.sample.relatedlist.RelatedList.getRelatedList(moduleAPIName, relatedListId);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void RelatedRecords()
	{
		try
		{
			String moduleAPIName = "leads";

			Long recordId = 3477061121091l;

			String relatedListAPIName = "products";

			Long relatedRecordId = 347706111064027l;

			String destinationFolder = "/usr/file/";

			List<String> relatedListIds = new ArrayList<String>(Arrays.asList("AutomatedSDKExternal", "3477061106971"));

			String externalValue = "AutomatedSDKExternal";

			String externalFieldValue = "TestExternal121";

			com.zoho.crm.sample.relatedrecords.RelatedRecords.getRelatedRecords(moduleAPIName, recordId, relatedListAPIName);

			com.zoho.crm.sample.relatedrecords.RelatedRecords.updateRelatedRecords(moduleAPIName, recordId, relatedListAPIName);

			com.zoho.crm.sample.relatedrecords.RelatedRecords.delinkRecords(moduleAPIName, recordId, relatedListAPIName, relatedListIds);

			com.zoho.crm.sample.relatedrecords.RelatedRecords.getRelatedRecordsUsingExternalId(moduleAPIName, externalValue, relatedListAPIName);

			com.zoho.crm.sample.relatedrecords.RelatedRecords.updateRelatedRecordsUsingExternalId(moduleAPIName, externalValue, relatedListAPIName);

			com.zoho.crm.sample.relatedrecords.RelatedRecords.deleteRelatedRecordsUsingExternalId(moduleAPIName, externalValue, relatedListAPIName, relatedListIds);

			com.zoho.crm.sample.relatedrecords.RelatedRecords.getRelatedRecord(moduleAPIName, recordId, relatedListAPIName, relatedRecordId, destinationFolder);

			com.zoho.crm.sample.relatedrecords.RelatedRecords.updateRelatedRecord(moduleAPIName, recordId, relatedListAPIName, relatedRecordId);

			com.zoho.crm.sample.relatedrecords.RelatedRecords.delinkRecord(moduleAPIName, recordId, relatedListAPIName, relatedRecordId);

			com.zoho.crm.sample.relatedrecords.RelatedRecords.getRelatedRecordUsingExternalId(moduleAPIName, externalValue, relatedListAPIName, externalFieldValue, destinationFolder);

			com.zoho.crm.sample.relatedrecords.RelatedRecords.updateRelatedRecordUsingExternalId(moduleAPIName, externalValue, relatedListAPIName, externalFieldValue);

			com.zoho.crm.sample.relatedrecords.RelatedRecords.deleteRelatedRecordUsingExternalId(moduleAPIName, externalValue, relatedListAPIName, externalFieldValue);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void Role()
	{
		try
		{
			Long roleId = 3477061166894l;

			com.zoho.crm.sample.role.Role.getRoles();

			com.zoho.crm.sample.role.Role.createRoles();

			com.zoho.crm.sample.role.Role.updateRoles();

			com.zoho.crm.sample.role.Role.getRole(roleId);

			com.zoho.crm.sample.role.Role.updateRole(roleId);

			com.zoho.crm.sample.role.Role.deleteRole(roleId);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void ScoringRules()
	{
		try
		{
			Long id = 3477061149662l;

			com.zoho.crm.sample.scoringrules.ScoringRule.getScoringRules();
			
			com.zoho.crm.sample.scoringrules.ScoringRule.getScoringRule(id);
			
			com.zoho.crm.sample.scoringrules.ScoringRule.createScoringRules();
			
			com.zoho.crm.sample.scoringrules.ScoringRule.updateScoringRules(id);
			
			com.zoho.crm.sample.scoringrules.ScoringRule.updateScoringRule(id);
			
			com.zoho.crm.sample.scoringrules.ScoringRule.deleteScoringRules();
			
			com.zoho.crm.sample.scoringrules.ScoringRule.deleteScoringRule(id);
			
			com.zoho.crm.sample.scoringrules.ScoringRule.scoringRuleExecutionUsingLayoutId("Leads");
			
			com.zoho.crm.sample.scoringrules.ScoringRule.scoringRuleExecutionUsingRuleIds("Leads");
			
			com.zoho.crm.sample.scoringrules.ScoringRule.activateScoringRule(id);
			
			com.zoho.crm.sample.scoringrules.ScoringRule.deactivateScoringRule(id);
			
			com.zoho.crm.sample.scoringrules.ScoringRule.cloneScoringRule(id);
			
			com.zoho.crm.sample.scoringrules.ScoringRule.getEntityScoreRecords();
			
			com.zoho.crm.sample.scoringrules.ScoringRule.getEntityScoreRecord(3477061149632l, "Leads");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void SendMail()
	{
		try
		{
			com.zoho.crm.sample.sendmail.SendMail.sendMail(35240335495063L, "Leads");
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void ShareRecords()
	{
		try
		{
			String moduleAPIName = "Leads";

			long recordId = 34770615623115L;

			com.zoho.crm.sample.sharerecords.ShareRecords.getSharedRecordDetails(moduleAPIName, recordId);

			com.zoho.crm.sample.sharerecords.ShareRecords.shareRecord(moduleAPIName, recordId);

			com.zoho.crm.sample.sharerecords.ShareRecords.updateSharePermissions(moduleAPIName, recordId);

			com.zoho.crm.sample.sharerecords.ShareRecords.revokeSharedRecord(moduleAPIName, recordId);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void Tags()
	{
		try
		{
			String moduleAPIName = "Leads";

			Long tagId = 347706193375l;

			long recordId = 34770615623115L;

			ArrayList<String> tagNames = new ArrayList<String>(Arrays.asList("Variableasd33, addtag12"));

			ArrayList<Long> recordIds = new ArrayList<Long>(Arrays.asList(34770615623115l, 34770617421029l));

			String conflictId = "347706193375";

			com.zoho.crm.sample.tags.Tag.getTags(moduleAPIName);

			com.zoho.crm.sample.tags.Tag.createTags(moduleAPIName);

			com.zoho.crm.sample.tags.Tag.updateTags(moduleAPIName);

			com.zoho.crm.sample.tags.Tag.updateTag(moduleAPIName, tagId);

			com.zoho.crm.sample.tags.Tag.deleteTag(tagId);

			com.zoho.crm.sample.tags.Tag.mergeTags(tagId, conflictId);

			com.zoho.crm.sample.tags.Tag.addTagsToRecord(moduleAPIName, recordId, tagNames);

			com.zoho.crm.sample.tags.Tag.removeTagsFromRecord(moduleAPIName, recordId, tagNames);

			com.zoho.crm.sample.tags.Tag.addTagsToMultipleRecords(moduleAPIName, recordIds, tagNames);

			com.zoho.crm.sample.tags.Tag.removeTagsFromMultipleRecords(moduleAPIName, recordIds, tagNames);

			com.zoho.crm.sample.tags.Tag.getRecordCountForTag(moduleAPIName, tagId);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void Tax()
	{
		try
		{
			Long taxId = 3477061134681l;

			com.zoho.crm.sample.taxes.Tax.getTaxes();

			com.zoho.crm.sample.taxes.Tax.updateTaxes();

			com.zoho.crm.sample.taxes.Tax.getTax(taxId);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void Territory()
	{
		try
		{
			Long territoryId = 34770613051397l;

			com.zoho.crm.sample.territories.Territory.getTerritories();

			com.zoho.crm.sample.territories.Territory.getTerritory(territoryId);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void UserGroups()
	{
		try
		{
			Long groupId = 347706116712017l;
			
			Long jobId = 347706116712017l;
			
			com.zoho.crm.sample.usergroups.UserGroups.getGroups();
			
			com.zoho.crm.sample.usergroups.UserGroups.createGroup();
			
			com.zoho.crm.sample.usergroups.UserGroups.getGroup(groupId);
			
			com.zoho.crm.sample.usergroups.UserGroups.updateGroup(groupId);
			
			com.zoho.crm.sample.usergroups.UserGroups.deleteGroup(groupId);
			
			com.zoho.crm.sample.usergroups.UserGroups.getStatus(jobId);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void User()
	{
		try
		{
			Long userId = 347706116693019l;

			com.zoho.crm.sample.users.User.getUsers();

			com.zoho.crm.sample.users.User.createUser();

			com.zoho.crm.sample.users.User.updateUsers();

			com.zoho.crm.sample.users.User.getUser(userId);

			com.zoho.crm.sample.users.User.updateUser(userId);

			com.zoho.crm.sample.users.User.deleteUser(userId);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void UsersTerritories()
	{
		try
		{
			Long userId = 34770615791024l;
			
			Long territoryId = 34770613051397l;
			
			com.zoho.crm.sample.usersterritories.UsersTerritories.getTerritoriesOfUser(userId);
			
			com.zoho.crm.sample.usersterritories.UsersTerritories.associateTerritoriesToUser(userId);
			
			com.zoho.crm.sample.usersterritories.UsersTerritories.getSpecificTerritoryOfUser(userId, territoryId);
			
			com.zoho.crm.sample.usersterritories.UsersTerritories.validateBeforeTransferForAllTerritories(userId);
			
			com.zoho.crm.sample.usersterritories.UsersTerritories.validateBeforeTransfer(userId, territoryId);
			
			com.zoho.crm.sample.usersterritories.UsersTerritories.delinkAndTransferFromAllTerritories(userId);
			
			com.zoho.crm.sample.usersterritories.UsersTerritories.delinkAndTransferFromSpecificTerritory(userId, territoryId);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void UsersUnavailability()
	{
		try
		{
			Long userId = 347706114792029l;
			
			Long territoryId = 3477061149792l;

			com.zoho.crm.sample.usersunavailability.UsersUnavailability.getUsersUnavailabilityHours();
			
			com.zoho.crm.sample.usersunavailability.UsersUnavailability.getUserUnavailabilityHours(userId);
			
			com.zoho.crm.sample.usersunavailability.UsersUnavailability.usersUnavailabilityHours();
			
			com.zoho.crm.sample.usersunavailability.UsersUnavailability.updateUsersUnavailabilityHours();
			
			com.zoho.crm.sample.usersunavailability.UsersUnavailability.updateUsersUnavailability(territoryId);
			
			com.zoho.crm.sample.usersunavailability.UsersUnavailability.deleteUsersUnavailabilityHour(territoryId);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	
	public static void VariableGroup()
	{
		try
		{
			String variableGroupName = "General";

			Long variableGroupId = 347706130891l;

			com.zoho.crm.sample.variablegroups.VariableGroup.getVariableGroups();

			com.zoho.crm.sample.variablegroups.VariableGroup.getVariableGroupById(variableGroupId);

			com.zoho.crm.sample.variablegroups.VariableGroup.getVariableGroupByAPIName(variableGroupName);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	
	public static void Variable()
	{
		try
		{
			ArrayList<String> variableIds = new ArrayList<String>(Arrays.asList("347706116701021", "352403360751"));

			Long variableId = 347706116701019l;

			String variableName = "Variabladsde55";

			com.zoho.crm.sample.variables.Variable.getVariables();

			com.zoho.crm.sample.variables.Variable.createVariables();

			com.zoho.crm.sample.variables.Variable.updateVariables();

			com.zoho.crm.sample.variables.Variable.deleteVariables(variableIds);

			com.zoho.crm.sample.variables.Variable.getVariableById(variableId);

			com.zoho.crm.sample.variables.Variable.updateVariableById(variableId);

			com.zoho.crm.sample.variables.Variable.deleteVariable(variableId);

			com.zoho.crm.sample.variables.Variable.getVariableForAPIName(variableName);

			com.zoho.crm.sample.variables.Variable.updateVariableByAPIName(variableName);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	
	public static void Wizards()
	{
		try
		{
			com.zoho.crm.sample.wizards.Wizards.getWizards();
			com.zoho.crm.sample.wizards.Wizards.getWizard(347706194979l);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
