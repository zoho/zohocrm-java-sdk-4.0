package com.zoho.crm.api.usersunavailability;

import com.zoho.crm.api.util.Model;
import java.time.OffsetDateTime;
import java.util.HashMap;

public class UsersUnavailability implements Model
{
	private String comments;

	private OffsetDateTime from;

	private OffsetDateTime to;

	private User user;

	private Long id;

	private HashMap<String, Integer> keyModified = new HashMap<String, Integer>();


	/**
	 * The method to get the value of comments
	 * @return A String representing the comments
	 */
	public String getComments()
	{
		return  this.comments;

	}

	/**
	 * The method to set the value to comments
	 * @param comments A String representing the comments
	 */
	public void setComments(String comments)
	{
		 this.comments = comments;

		 this.keyModified.put("comments", 1);

	}

	/**
	 * The method to get the value of from
	 * @return An instance of OffsetDateTime
	 */
	public OffsetDateTime getFrom()
	{
		return  this.from;

	}

	/**
	 * The method to set the value to from
	 * @param from An instance of OffsetDateTime
	 */
	public void setFrom(OffsetDateTime from)
	{
		 this.from = from;

		 this.keyModified.put("from", 1);

	}

	/**
	 * The method to get the value of to
	 * @return An instance of OffsetDateTime
	 */
	public OffsetDateTime getTo()
	{
		return  this.to;

	}

	/**
	 * The method to set the value to to
	 * @param to An instance of OffsetDateTime
	 */
	public void setTo(OffsetDateTime to)
	{
		 this.to = to;

		 this.keyModified.put("to", 1);

	}

	/**
	 * The method to get the value of user
	 * @return An instance of User
	 */
	public User getUser()
	{
		return  this.user;

	}

	/**
	 * The method to set the value to user
	 * @param user An instance of User
	 */
	public void setUser(User user)
	{
		 this.user = user;

		 this.keyModified.put("user", 1);

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