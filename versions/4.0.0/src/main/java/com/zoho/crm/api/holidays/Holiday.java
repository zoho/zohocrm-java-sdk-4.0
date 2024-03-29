package com.zoho.crm.api.holidays;

import com.zoho.crm.api.util.Choice;
import com.zoho.crm.api.util.Model;
import java.time.LocalDate;
import java.util.HashMap;

public class Holiday implements Model
{
	private String name;

	private LocalDate date;

	private Choice<String> type;

	private ShiftHour shiftHour;

	private Integer year;

	private Long id;

	private HashMap<String, Integer> keyModified = new HashMap<String, Integer>();


	/**
	 * The method to get the value of name
	 * @return A String representing the name
	 */
	public String getName()
	{
		return  this.name;

	}

	/**
	 * The method to set the value to name
	 * @param name A String representing the name
	 */
	public void setName(String name)
	{
		 this.name = name;

		 this.keyModified.put("name", 1);

	}

	/**
	 * The method to get the value of date
	 * @return An instance of LocalDate
	 */
	public LocalDate getDate()
	{
		return  this.date;

	}

	/**
	 * The method to set the value to date
	 * @param date An instance of LocalDate
	 */
	public void setDate(LocalDate date)
	{
		 this.date = date;

		 this.keyModified.put("date", 1);

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
	 * The method to get the value of shiftHour
	 * @return An instance of ShiftHour
	 */
	public ShiftHour getShiftHour()
	{
		return  this.shiftHour;

	}

	/**
	 * The method to set the value to shiftHour
	 * @param shiftHour An instance of ShiftHour
	 */
	public void setShiftHour(ShiftHour shiftHour)
	{
		 this.shiftHour = shiftHour;

		 this.keyModified.put("shift_hour", 1);

	}

	/**
	 * The method to get the value of year
	 * @return An Integer representing the year
	 */
	public Integer getYear()
	{
		return  this.year;

	}

	/**
	 * The method to set the value to year
	 * @param year An Integer representing the year
	 */
	public void setYear(Integer year)
	{
		 this.year = year;

		 this.keyModified.put("year", 1);

	}

	/**
	 * The method to get the value of id
	 * @return A Long representing the id
	 */
	public Long getId()
	{
		return  this.id;

	}

	/**
	 * The method to set the value to id
	 * @param id A Long representing the id
	 */
	public void setId(Long id)
	{
		 this.id = id;

		 this.keyModified.put("id", 1);

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