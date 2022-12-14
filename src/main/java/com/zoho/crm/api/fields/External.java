package com.zoho.crm.api.fields;

import com.zoho.crm.api.util.Choice;
import com.zoho.crm.api.util.Model;
import java.util.HashMap;

public class External implements Model
{
	private Boolean show;

	private Choice<String> type;

	private Boolean allowMultipleConfig;

	private HashMap<String, Integer> keyModified = new HashMap<String, Integer>();


	/**
	 * The method to get the value of show
	 * @return A Boolean representing the show
	 */
	public Boolean getShow()
	{
		return  this.show;

	}

	/**
	 * The method to set the value to show
	 * @param show A Boolean representing the show
	 */
	public void setShow(Boolean show)
	{
		 this.show = show;

		 this.keyModified.put("show", 1);

	}

	/**
	 * The method to get the value of type
	 * @return An instance of Choice<String>
	 */
	public Choice<String> getType()
	{
		return  this.type;

	}

	/**
	 * The method to set the value to type
	 * @param type An instance of Choice<String>
	 */
	public void setType(Choice<String> type)
	{
		 this.type = type;

		 this.keyModified.put("type", 1);

	}

	/**
	 * The method to get the value of allowMultipleConfig
	 * @return A Boolean representing the allowMultipleConfig
	 */
	public Boolean getAllowMultipleConfig()
	{
		return  this.allowMultipleConfig;

	}

	/**
	 * The method to set the value to allowMultipleConfig
	 * @param allowMultipleConfig A Boolean representing the allowMultipleConfig
	 */
	public void setAllowMultipleConfig(Boolean allowMultipleConfig)
	{
		 this.allowMultipleConfig = allowMultipleConfig;

		 this.keyModified.put("allow_multiple_config", 1);

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