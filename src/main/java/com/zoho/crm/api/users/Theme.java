package com.zoho.crm.api.users;

import com.zoho.crm.api.util.Choice;
import com.zoho.crm.api.util.Model;
import java.util.HashMap;

public class Theme implements Model
{
	private Tab normalTab;

	private Tab selectedTab;

	private String newBackground;

	private Choice<String> background;

	private Choice<String> screen;

	private String type;

	private HashMap<String, Integer> keyModified = new HashMap<String, Integer>();


	/**
	 * The method to get the value of normalTab
	 * @return An instance of Tab
	 */
	public Tab getNormalTab()
	{
		return  this.normalTab;

	}

	/**
	 * The method to set the value to normalTab
	 * @param normalTab An instance of Tab
	 */
	public void setNormalTab(Tab normalTab)
	{
		 this.normalTab = normalTab;

		 this.keyModified.put("normal_tab", 1);

	}

	/**
	 * The method to get the value of selectedTab
	 * @return An instance of Tab
	 */
	public Tab getSelectedTab()
	{
		return  this.selectedTab;

	}

	/**
	 * The method to set the value to selectedTab
	 * @param selectedTab An instance of Tab
	 */
	public void setSelectedTab(Tab selectedTab)
	{
		 this.selectedTab = selectedTab;

		 this.keyModified.put("selected_tab", 1);

	}

	/**
	 * The method to get the value of newBackground
	 * @return A String representing the newBackground
	 */
	public String getNewBackground()
	{
		return  this.newBackground;

	}

	/**
	 * The method to set the value to newBackground
	 * @param newBackground A String representing the newBackground
	 */
	public void setNewBackground(String newBackground)
	{
		 this.newBackground = newBackground;

		 this.keyModified.put("new_background", 1);

	}

	/**
	 * The method to get the value of background
	 * @return An instance of Choice<String>
	 */
	public Choice<String> getBackground()
	{
		return  this.background;

	}

	/**
	 * The method to set the value to background
	 * @param background An instance of Choice<String>
	 */
	public void setBackground(Choice<String> background)
	{
		 this.background = background;

		 this.keyModified.put("background", 1);

	}

	/**
	 * The method to get the value of screen
	 * @return An instance of Choice<String>
	 */
	public Choice<String> getScreen()
	{
		return  this.screen;

	}

	/**
	 * The method to set the value to screen
	 * @param screen An instance of Choice<String>
	 */
	public void setScreen(Choice<String> screen)
	{
		 this.screen = screen;

		 this.keyModified.put("screen", 1);

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