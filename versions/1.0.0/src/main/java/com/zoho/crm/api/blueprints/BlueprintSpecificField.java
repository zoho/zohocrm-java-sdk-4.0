package com.zoho.crm.api.blueprints;

import com.zoho.crm.api.util.Model;
import java.util.HashMap;

public class BlueprintSpecificField implements Model, FieldHandler
{
	private String displayLabel;

	private String type;

	private String dataType;

	private String columnName;

	private Long id;

	private Integer transitionSequence;

	private Boolean mandatory;

	private Boolean layouts;

	private Boolean content;

	private HashMap<String, Integer> keyModified = new HashMap<String, Integer>();


	/**
	 * The method to get the value of displayLabel
	 * @return A String representing the displayLabel
	 */
	public String getDisplayLabel()
	{
		return  this.displayLabel;

	}

	/**
	 * The method to set the value to displayLabel
	 * @param displayLabel A String representing the displayLabel
	 */
	public void setDisplayLabel(String displayLabel)
	{
		 this.displayLabel = displayLabel;

		 this.keyModified.put("display_label", 1);

	}

	/**
	 * The method to get the value of type
	 * @return A String representing the type
	 */
	public String getType()
	{
		return  this.type;

	}

	/**
	 * The method to set the value to type
	 * @param type A String representing the type
	 */
	public void setType(String type)
	{
		 this.type = type;

		 this.keyModified.put("_type", 1);

	}

	/**
	 * The method to get the value of dataType
	 * @return A String representing the dataType
	 */
	public String getDataType()
	{
		return  this.dataType;

	}

	/**
	 * The method to set the value to dataType
	 * @param dataType A String representing the dataType
	 */
	public void setDataType(String dataType)
	{
		 this.dataType = dataType;

		 this.keyModified.put("data_type", 1);

	}

	/**
	 * The method to get the value of columnName
	 * @return A String representing the columnName
	 */
	public String getColumnName()
	{
		return  this.columnName;

	}

	/**
	 * The method to set the value to columnName
	 * @param columnName A String representing the columnName
	 */
	public void setColumnName(String columnName)
	{
		 this.columnName = columnName;

		 this.keyModified.put("column_name", 1);

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
	 * The method to get the value of transitionSequence
	 * @return An Integer representing the transitionSequence
	 */
	public Integer getTransitionSequence()
	{
		return  this.transitionSequence;

	}

	/**
	 * The method to set the value to transitionSequence
	 * @param transitionSequence An Integer representing the transitionSequence
	 */
	public void setTransitionSequence(Integer transitionSequence)
	{
		 this.transitionSequence = transitionSequence;

		 this.keyModified.put("transition_sequence", 1);

	}

	/**
	 * The method to get the value of mandatory
	 * @return A Boolean representing the mandatory
	 */
	public Boolean getMandatory()
	{
		return  this.mandatory;

	}

	/**
	 * The method to set the value to mandatory
	 * @param mandatory A Boolean representing the mandatory
	 */
	public void setMandatory(Boolean mandatory)
	{
		 this.mandatory = mandatory;

		 this.keyModified.put("mandatory", 1);

	}

	/**
	 * The method to get the value of layouts
	 * @return A Boolean representing the layouts
	 */
	public Boolean getLayouts()
	{
		return  this.layouts;

	}

	/**
	 * The method to set the value to layouts
	 * @param layouts A Boolean representing the layouts
	 */
	public void setLayouts(Boolean layouts)
	{
		 this.layouts = layouts;

		 this.keyModified.put("layouts", 1);

	}

	/**
	 * The method to get the value of content
	 * @return A Boolean representing the content
	 */
	public Boolean getContent()
	{
		return  this.content;

	}

	/**
	 * The method to set the value to content
	 * @param content A Boolean representing the content
	 */
	public void setContent(Boolean content)
	{
		 this.content = content;

		 this.keyModified.put("content", 1);

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