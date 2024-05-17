package com.zoho.crm.api.shifthours;

import com.zoho.crm.api.util.Choice;
import com.zoho.crm.api.util.Model;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

public class ShiftHour implements Model
{
	private TimeZone timezone;

	private String name;

	private Boolean sameAsEveryday;

	private List<Choice<String>> shiftDays;

	private List<String> dailyTiming;

	private List<CustomTiming> customTiming;

	private List<Holidays> holidays;

	private List<BreakHours> breakHours;

	private List<Users> users;

	private Long id;

	private Integer usersCount;

	private HashMap<String, Integer> keyModified = new HashMap<String, Integer>();


	/**
	 * The method to get the value of timezone
	 * @return An instance of TimeZone
	 */
	public TimeZone getTimezone()
	{
		return  this.timezone;

	}

	/**
	 * The method to set the value to timezone
	 * @param timezone An instance of TimeZone
	 */
	public void setTimezone(TimeZone timezone)
	{
		 this.timezone = timezone;

		 this.keyModified.put("timezone", 1);

	}

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
	 * The method to get the value of sameAsEveryday
	 * @return A Boolean representing the sameAsEveryday
	 */
	public Boolean getSameAsEveryday()
	{
		return  this.sameAsEveryday;

	}

	/**
	 * The method to set the value to sameAsEveryday
	 * @param sameAsEveryday A Boolean representing the sameAsEveryday
	 */
	public void setSameAsEveryday(Boolean sameAsEveryday)
	{
		 this.sameAsEveryday = sameAsEveryday;

		 this.keyModified.put("same_as_everyday", 1);

	}

	/**
	 * The method to get the value of shiftDays
	 * @return An instance of List<Choice>
	 */
	public List<Choice<String>> getShiftDays()
	{
		return  this.shiftDays;

	}

	/**
	 * The method to set the value to shiftDays
	 * @param shiftDays An instance of List<Choice>
	 */
	public void setShiftDays(List<Choice<String>> shiftDays)
	{
		 this.shiftDays = shiftDays;

		 this.keyModified.put("shift_days", 1);

	}

	/**
	 * The method to get the value of dailyTiming
	 * @return An instance of List<String>
	 */
	public List<String> getDailyTiming()
	{
		return  this.dailyTiming;

	}

	/**
	 * The method to set the value to dailyTiming
	 * @param dailyTiming An instance of List<String>
	 */
	public void setDailyTiming(List<String> dailyTiming)
	{
		 this.dailyTiming = dailyTiming;

		 this.keyModified.put("daily_timing", 1);

	}

	/**
	 * The method to get the value of customTiming
	 * @return An instance of List<CustomTiming>
	 */
	public List<CustomTiming> getCustomTiming()
	{
		return  this.customTiming;

	}

	/**
	 * The method to set the value to customTiming
	 * @param customTiming An instance of List<CustomTiming>
	 */
	public void setCustomTiming(List<CustomTiming> customTiming)
	{
		 this.customTiming = customTiming;

		 this.keyModified.put("custom_timing", 1);

	}

	/**
	 * The method to get the value of holidays
	 * @return An instance of List<Holidays>
	 */
	public List<Holidays> getHolidays()
	{
		return  this.holidays;

	}

	/**
	 * The method to set the value to holidays
	 * @param holidays An instance of List<Holidays>
	 */
	public void setHolidays(List<Holidays> holidays)
	{
		 this.holidays = holidays;

		 this.keyModified.put("holidays", 1);

	}

	/**
	 * The method to get the value of breakHours
	 * @return An instance of List<BreakHours>
	 */
	public List<BreakHours> getBreakHours()
	{
		return  this.breakHours;

	}

	/**
	 * The method to set the value to breakHours
	 * @param breakHours An instance of List<BreakHours>
	 */
	public void setBreakHours(List<BreakHours> breakHours)
	{
		 this.breakHours = breakHours;

		 this.keyModified.put("break_hours", 1);

	}

	/**
	 * The method to get the value of users
	 * @return An instance of List<Users>
	 */
	public List<Users> getUsers()
	{
		return  this.users;

	}

	/**
	 * The method to set the value to users
	 * @param users An instance of List<Users>
	 */
	public void setUsers(List<Users> users)
	{
		 this.users = users;

		 this.keyModified.put("users", 1);

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
	 * The method to get the value of usersCount
	 * @return An Integer representing the usersCount
	 */
	public Integer getUsersCount()
	{
		return  this.usersCount;

	}

	/**
	 * The method to set the value to usersCount
	 * @param usersCount An Integer representing the usersCount
	 */
	public void setUsersCount(Integer usersCount)
	{
		 this.usersCount = usersCount;

		 this.keyModified.put("users_count", 1);

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