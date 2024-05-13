package com.zoho.crm.api.org;

import com.zoho.crm.api.util.Model;
import java.util.HashMap;

public class HierarchyPreference implements Model
{
	private Boolean strictlyReporting;

	private String type;

	private HashMap<String, Integer> keyModified = new HashMap<String, Integer>();


	/**
	 * The method to get the value of strictlyReporting
	 * @return A Boolean representing the strictlyReporting
	 */
	public Boolean getStrictlyReporting()
	{
		return  this.strictlyReporting;

	}

	/**
	 * The method to set the value to strictlyReporting
	 * @param strictlyReporting A Boolean representing the strictlyReporting
	 */
	public void setStrictlyReporting(Boolean strictlyReporting)
	{
		 this.strictlyReporting = strictlyReporting;

		 this.keyModified.put("strictly_reporting", 1);

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

		 this.keyModified.put("type", 1);

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