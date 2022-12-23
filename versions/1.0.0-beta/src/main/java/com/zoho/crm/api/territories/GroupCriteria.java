package com.zoho.crm.api.territories;

import com.zoho.crm.api.util.Model;
import java.util.HashMap;
import java.util.List;

public class GroupCriteria implements Model, Criteria
{
	private String groupOperator;

	private List<Criteria> group;

	private HashMap<String, Integer> keyModified = new HashMap<String, Integer>();


	/**
	 * The method to get the value of groupOperator
	 * @return A String representing the groupOperator
	 */
	public String getGroupOperator()
	{
		return  this.groupOperator;

	}

	/**
	 * The method to set the value to groupOperator
	 * @param groupOperator A String representing the groupOperator
	 */
	public void setGroupOperator(String groupOperator)
	{
		 this.groupOperator = groupOperator;

		 this.keyModified.put("group_operator", 1);

	}

	/**
	 * The method to get the value of group
	 * @return An instance of List<Criteria>
	 */
	public List<Criteria> getGroup()
	{
		return  this.group;

	}

	/**
	 * The method to set the value to group
	 * @param group An instance of List<Criteria>
	 */
	public void setGroup(List<Criteria> group)
	{
		 this.group = group;

		 this.keyModified.put("group", 1);

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