package com.zoho.crm.api.fields;

import com.zoho.crm.api.util.Model;
import java.util.HashMap;

public class AllowedPermissionsToUpdate implements Model
{
	private Boolean readWrite;

	private Boolean hidden;

	private Boolean readOnly;

	private HashMap<String, Integer> keyModified = new HashMap<String, Integer>();


	/**
	 * The method to get the value of readWrite
	 * @return A Boolean representing the readWrite
	 */
	public Boolean getReadWrite()
	{
		return  this.readWrite;

	}

	/**
	 * The method to set the value to readWrite
	 * @param readWrite A Boolean representing the readWrite
	 */
	public void setReadWrite(Boolean readWrite)
	{
		 this.readWrite = readWrite;

		 this.keyModified.put("read_write", 1);

	}

	/**
	 * The method to get the value of hidden
	 * @return A Boolean representing the hidden
	 */
	public Boolean getHidden()
	{
		return  this.hidden;

	}

	/**
	 * The method to set the value to hidden
	 * @param hidden A Boolean representing the hidden
	 */
	public void setHidden(Boolean hidden)
	{
		 this.hidden = hidden;

		 this.keyModified.put("hidden", 1);

	}

	/**
	 * The method to get the value of readOnly
	 * @return A Boolean representing the readOnly
	 */
	public Boolean getReadOnly()
	{
		return  this.readOnly;

	}

	/**
	 * The method to set the value to readOnly
	 * @param readOnly A Boolean representing the readOnly
	 */
	public void setReadOnly(Boolean readOnly)
	{
		 this.readOnly = readOnly;

		 this.keyModified.put("read_only", 1);

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