package com.zoho.crm.api.fields;

import com.zoho.crm.api.util.Model;
import java.util.HashMap;

public class AssociationDetails implements Model
{
	private ModuleMap relatedField;

	private ModuleMap lookupField;

	private HashMap<String, Integer> keyModified = new HashMap<String, Integer>();


	/**
	 * The method to get the value of relatedField
	 * @return An instance of ModuleMap
	 */
	public ModuleMap getRelatedField()
	{
		return  this.relatedField;

	}

	/**
	 * The method to set the value to relatedField
	 * @param relatedField An instance of ModuleMap
	 */
	public void setRelatedField(ModuleMap relatedField)
	{
		 this.relatedField = relatedField;

		 this.keyModified.put("related_field", 1);

	}

	/**
	 * The method to get the value of lookupField
	 * @return An instance of ModuleMap
	 */
	public ModuleMap getLookupField()
	{
		return  this.lookupField;

	}

	/**
	 * The method to set the value to lookupField
	 * @param lookupField An instance of ModuleMap
	 */
	public void setLookupField(ModuleMap lookupField)
	{
		 this.lookupField = lookupField;

		 this.keyModified.put("lookup_field", 1);

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