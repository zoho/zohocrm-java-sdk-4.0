package com.zoho.crm.api.emailrelatedrecords;

import com.zoho.crm.api.util.Model;
import java.time.OffsetDateTime;
import java.util.HashMap;

public class Status implements Model
{
	private String type;

	private OffsetDateTime bouncedTime;

	private String bouncedReason;

	private HashMap<String, Integer> keyModified = new HashMap<String, Integer>();


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
	 * The method to get the value of bouncedTime
	 * @return An instance of OffsetDateTime
	 */
	public OffsetDateTime getBouncedTime()
	{
		return  this.bouncedTime;

	}

	/**
	 * The method to set the value to bouncedTime
	 * @param bouncedTime An instance of OffsetDateTime
	 */
	public void setBouncedTime(OffsetDateTime bouncedTime)
	{
		 this.bouncedTime = bouncedTime;

		 this.keyModified.put("bounced_time", 1);

	}

	/**
	 * The method to get the value of bouncedReason
	 * @return A String representing the bouncedReason
	 */
	public String getBouncedReason()
	{
		return  this.bouncedReason;

	}

	/**
	 * The method to set the value to bouncedReason
	 * @param bouncedReason A String representing the bouncedReason
	 */
	public void setBouncedReason(String bouncedReason)
	{
		 this.bouncedReason = bouncedReason;

		 this.keyModified.put("bounced_reason", 1);

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