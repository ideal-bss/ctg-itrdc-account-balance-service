package com.ctg.itrdc.account.balance.model;

import java.util.HashMap;
import java.util.List;

public class MenuParent {
	private List<Menu> listMenu;
	private HashMap<String,Object > attributesMenu;
	
	public List<Menu> getListMenu() {
		return listMenu;
	}
	public void setListMenu(List<Menu> listMenu) {
		this.listMenu = listMenu;
	}
	public HashMap<String, Object> getAttributesMenu() {
		return attributesMenu;
	}
	public void setAttributesMenu(HashMap<String, Object> attributesMenu) {
		this.attributesMenu = attributesMenu;
	}
	
}
