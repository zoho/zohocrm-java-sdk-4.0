package com.zoho.crm.api.sendmail;

import com.zoho.crm.api.util.Model;
import java.util.HashMap;

public class Attachments implements Model
{
	private Attachment attachment;

	private HashMap<String, Integer> keyModified = new HashMap<String, Integer>();


	/**
	 * The method to get the value of attachment
	 * @return An instance of Attachment
	 */
	public Attachment getAttachment()
	{
		return  this.attachment;

	}

	/**
	 * The method to set the value to attachment
	 * @param attachment An instance of Attachment
	 */
	public void setAttachment(Attachment attachment)
	{
		 this.attachment = attachment;

		 this.keyModified.put("attachment", 1);

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