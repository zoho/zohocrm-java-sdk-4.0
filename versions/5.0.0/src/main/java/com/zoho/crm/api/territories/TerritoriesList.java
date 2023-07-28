package com.zoho.crm.api.territories;

import com.zoho.crm.api.util.Model;
import java.util.HashMap;

public class TerritoriesList implements Model
{
	private TerritoryMap territorymap;

	private HashMap<String, Integer> keyModified = new HashMap<String, Integer>();


	/**
	 * The method to get the value of territorymap
	 * @return An instance of TerritoryMap
	 */
	public TerritoryMap getTerritorymap()
	{
		return  this.territorymap;

	}

	/**
	 * The method to set the value to territorymap
	 * @param territorymap An instance of TerritoryMap
	 */
	public void setTerritorymap(TerritoryMap territorymap)
	{
		 this.territorymap = territorymap;

		 this.keyModified.put("TerritoryMap", 1);

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