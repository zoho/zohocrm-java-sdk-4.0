package com.zoho.crm.api.usertype;

import com.zoho.crm.api.util.Model;
import java.util.HashMap;
import java.util.List;

public class SuccessWrapper implements Model
{
	private List<SuccessResponse> userType;

	private HashMap<String, Integer> keyModified = new HashMap<String, Integer>();


	/**
	 * The method to get the value of userType
	 * @return An instance of List<SuccessResponse>
	 */
	public List<SuccessResponse> getUserType()
	{
		return  this.userType;

	}

	/**
	 * The method to set the value to userType
	 * @param userType An instance of List<SuccessResponse>
	 */
	public void setUserType(List<SuccessResponse> userType)
	{
		 this.userType = userType;

		 this.keyModified.put("user_type", 1);

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