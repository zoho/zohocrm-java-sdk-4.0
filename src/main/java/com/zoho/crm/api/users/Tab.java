package com.zoho.crm.api.users;

import com.zoho.crm.api.util.Choice;
import com.zoho.crm.api.util.Model;
import java.util.HashMap;

public class Tab implements Model
{
	private Choice<String> fontColor;

	private Choice<String> background;

	private HashMap<String, Integer> keyModified = new HashMap<String, Integer>();


	/**
	 * The method to get the value of fontColor
	 * @return An instance of Choice<String>
	 */
	public Choice<String> getFontColor()
	{
		return  this.fontColor;

	}

	/**
	 * The method to set the value to fontColor
	 * @param fontColor An instance of Choice<String>
	 */
	public void setFontColor(Choice<String> fontColor)
	{
		 this.fontColor = fontColor;

		 this.keyModified.put("font_color", 1);

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