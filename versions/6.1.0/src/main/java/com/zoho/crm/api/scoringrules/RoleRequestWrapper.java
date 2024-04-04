package com.zoho.crm.api.scoringrules;

import com.zoho.crm.api.util.Model;
import java.util.HashMap;
import java.util.List;

public class RoleRequestWrapper implements Model
{
	private List<String> scoringRules;

	private HashMap<String, Integer> keyModified = new HashMap<String, Integer>();


	/**
	 * The method to get the value of scoringRules
	 * @return An instance of List<String>
	 */
	public List<String> getScoringRules()
	{
		return  this.scoringRules;

	}

	/**
	 * The method to set the value to scoringRules
	 * @param scoringRules An instance of List<String>
	 */
	public void setScoringRules(List<String> scoringRules)
	{
		 this.scoringRules = scoringRules;

		 this.keyModified.put("scoring_rules", 1);

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