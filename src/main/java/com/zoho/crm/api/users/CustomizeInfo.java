package com.zoho.crm.api.users;

import com.zoho.crm.api.util.Model;
import java.util.HashMap;

public class CustomizeInfo implements Model
{
	private Object notesDesc;

	private Object showRightPanel;

	private Object bcView;

	private Object unpinRecentItem;

	private Boolean showHome;

	private Boolean showDetailView;

	private HashMap<String, Integer> keyModified = new HashMap<String, Integer>();


	/**
	 * The method to get the value of notesDesc
	 * @return An instance of Object
	 */
	public Object getNotesDesc()
	{
		return  this.notesDesc;

	}

	/**
	 * The method to set the value to notesDesc
	 * @param notesDesc An instance of Object
	 */
	public void setNotesDesc(Object notesDesc)
	{
		 this.notesDesc = notesDesc;

		 this.keyModified.put("notes_desc", 1);

	}

	/**
	 * The method to get the value of showRightPanel
	 * @return An instance of Object
	 */
	public Object getShowRightPanel()
	{
		return  this.showRightPanel;

	}

	/**
	 * The method to set the value to showRightPanel
	 * @param showRightPanel An instance of Object
	 */
	public void setShowRightPanel(Object showRightPanel)
	{
		 this.showRightPanel = showRightPanel;

		 this.keyModified.put("show_right_panel", 1);

	}

	/**
	 * The method to get the value of bcView
	 * @return An instance of Object
	 */
	public Object getBcView()
	{
		return  this.bcView;

	}

	/**
	 * The method to set the value to bcView
	 * @param bcView An instance of Object
	 */
	public void setBcView(Object bcView)
	{
		 this.bcView = bcView;

		 this.keyModified.put("bc_view", 1);

	}

	/**
	 * The method to get the value of unpinRecentItem
	 * @return An instance of Object
	 */
	public Object getUnpinRecentItem()
	{
		return  this.unpinRecentItem;

	}

	/**
	 * The method to set the value to unpinRecentItem
	 * @param unpinRecentItem An instance of Object
	 */
	public void setUnpinRecentItem(Object unpinRecentItem)
	{
		 this.unpinRecentItem = unpinRecentItem;

		 this.keyModified.put("unpin_recent_item", 1);

	}

	/**
	 * The method to get the value of showHome
	 * @return A Boolean representing the showHome
	 */
	public Boolean getShowHome()
	{
		return  this.showHome;

	}

	/**
	 * The method to set the value to showHome
	 * @param showHome A Boolean representing the showHome
	 */
	public void setShowHome(Boolean showHome)
	{
		 this.showHome = showHome;

		 this.keyModified.put("show_home", 1);

	}

	/**
	 * The method to get the value of showDetailView
	 * @return A Boolean representing the showDetailView
	 */
	public Boolean getShowDetailView()
	{
		return  this.showDetailView;

	}

	/**
	 * The method to set the value to showDetailView
	 * @param showDetailView A Boolean representing the showDetailView
	 */
	public void setShowDetailView(Boolean showDetailView)
	{
		 this.showDetailView = showDetailView;

		 this.keyModified.put("show_detail_view", 1);

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