package com.zoho.crm.api.modules;

import com.zoho.crm.api.fields.Formula;
import com.zoho.crm.api.fields.Lookup;
import com.zoho.crm.api.fields.PickListValue;
import com.zoho.crm.api.util.Model;
import java.util.HashMap;
import java.util.List;

public class SectionField implements Model
{
	private String defaultValue;

	private Boolean required;

	private Integer sequenceNumber;

	private Integer sectionId;

	private String apiName;

	private Boolean businesscardSupported;

	private Long id;

	private Boolean massUpdate;

	private ConvertMapping convertMapping;

	private Lookup lookup;

	private Formula formula;

	private Subform subform;

	private List<PickListValue> pickListValues;

	private HashMap<String, Integer> keyModified = new HashMap<String, Integer>();


	/**
	 * The method to get the value of defaultValue
	 * @return A String representing the defaultValue
	 */
	public String getDefaultValue()
	{
		return  this.defaultValue;

	}

	/**
	 * The method to set the value to defaultValue
	 * @param defaultValue A String representing the defaultValue
	 */
	public void setDefaultValue(String defaultValue)
	{
		 this.defaultValue = defaultValue;

		 this.keyModified.put("default_value", 1);

	}

	/**
	 * The method to get the value of required
	 * @return A Boolean representing the required
	 */
	public Boolean getRequired()
	{
		return  this.required;

	}

	/**
	 * The method to set the value to required
	 * @param required A Boolean representing the required
	 */
	public void setRequired(Boolean required)
	{
		 this.required = required;

		 this.keyModified.put("required", 1);

	}

	/**
	 * The method to get the value of sequenceNumber
	 * @return An Integer representing the sequenceNumber
	 */
	public Integer getSequenceNumber()
	{
		return  this.sequenceNumber;

	}

	/**
	 * The method to set the value to sequenceNumber
	 * @param sequenceNumber An Integer representing the sequenceNumber
	 */
	public void setSequenceNumber(Integer sequenceNumber)
	{
		 this.sequenceNumber = sequenceNumber;

		 this.keyModified.put("sequence_number", 1);

	}

	/**
	 * The method to get the value of sectionId
	 * @return An Integer representing the sectionId
	 */
	public Integer getSectionId()
	{
		return  this.sectionId;

	}

	/**
	 * The method to set the value to sectionId
	 * @param sectionId An Integer representing the sectionId
	 */
	public void setSectionId(Integer sectionId)
	{
		 this.sectionId = sectionId;

		 this.keyModified.put("section_id", 1);

	}

	/**
	 * The method to get the value of apiName
	 * @return A String representing the apiName
	 */
	public String getAPIName()
	{
		return  this.apiName;

	}

	/**
	 * The method to set the value to apiName
	 * @param apiName A String representing the apiName
	 */
	public void setAPIName(String apiName)
	{
		 this.apiName = apiName;

		 this.keyModified.put("api_name", 1);

	}

	/**
	 * The method to get the value of businesscardSupported
	 * @return A Boolean representing the businesscardSupported
	 */
	public Boolean getBusinesscardSupported()
	{
		return  this.businesscardSupported;

	}

	/**
	 * The method to set the value to businesscardSupported
	 * @param businesscardSupported A Boolean representing the businesscardSupported
	 */
	public void setBusinesscardSupported(Boolean businesscardSupported)
	{
		 this.businesscardSupported = businesscardSupported;

		 this.keyModified.put("businesscard_supported", 1);

	}

	/**
	 * The method to get the value of id
	 * @return A Long representing the id
	 */
	public Long getId()
	{
		return  this.id;

	}

	/**
	 * The method to set the value to id
	 * @param id A Long representing the id
	 */
	public void setId(Long id)
	{
		 this.id = id;

		 this.keyModified.put("id", 1);

	}

	/**
	 * The method to get the value of massUpdate
	 * @return A Boolean representing the massUpdate
	 */
	public Boolean getMassUpdate()
	{
		return  this.massUpdate;

	}

	/**
	 * The method to set the value to massUpdate
	 * @param massUpdate A Boolean representing the massUpdate
	 */
	public void setMassUpdate(Boolean massUpdate)
	{
		 this.massUpdate = massUpdate;

		 this.keyModified.put("mass_update", 1);

	}

	/**
	 * The method to get the value of convertMapping
	 * @return An instance of ConvertMapping
	 */
	public ConvertMapping getConvertMapping()
	{
		return  this.convertMapping;

	}

	/**
	 * The method to set the value to convertMapping
	 * @param convertMapping An instance of ConvertMapping
	 */
	public void setConvertMapping(ConvertMapping convertMapping)
	{
		 this.convertMapping = convertMapping;

		 this.keyModified.put("convert_mapping", 1);

	}

	/**
	 * The method to get the value of lookup
	 * @return An instance of Lookup
	 */
	public Lookup getLookup()
	{
		return  this.lookup;

	}

	/**
	 * The method to set the value to lookup
	 * @param lookup An instance of Lookup
	 */
	public void setLookup(Lookup lookup)
	{
		 this.lookup = lookup;

		 this.keyModified.put("lookup", 1);

	}

	/**
	 * The method to get the value of formula
	 * @return An instance of Formula
	 */
	public Formula getFormula()
	{
		return  this.formula;

	}

	/**
	 * The method to set the value to formula
	 * @param formula An instance of Formula
	 */
	public void setFormula(Formula formula)
	{
		 this.formula = formula;

		 this.keyModified.put("formula", 1);

	}

	/**
	 * The method to get the value of subform
	 * @return An instance of Subform
	 */
	public Subform getSubform()
	{
		return  this.subform;

	}

	/**
	 * The method to set the value to subform
	 * @param subform An instance of Subform
	 */
	public void setSubform(Subform subform)
	{
		 this.subform = subform;

		 this.keyModified.put("subform", 1);

	}

	/**
	 * The method to get the value of pickListValues
	 * @return An instance of List<PickListValue>
	 */
	public List<PickListValue> getPickListValues()
	{
		return  this.pickListValues;

	}

	/**
	 * The method to set the value to pickListValues
	 * @param pickListValues An instance of List<PickListValue>
	 */
	public void setPickListValues(List<PickListValue> pickListValues)
	{
		 this.pickListValues = pickListValues;

		 this.keyModified.put("pick_list_values", 1);

	}

	/**
	 * The method to check if the user has modified the given key
	 * @param key A String representing the key
	 * @return An Integer representing the modification
	 */
	public Integer isKeyModified(String key)
	{
		if((( this.keyModified.containsKey(key))))
		{
			return  this.keyModified.get(key);

		}
		return null;

	}

	/**
	 * The method to mark the given key as modified
	 * @param key A String representing the key
	 * @param modification An Integer representing the modification
	 */
	public void setKeyModified(String key, Integer modification)
	{
		 this.keyModified.put(key, modification);

	}
}