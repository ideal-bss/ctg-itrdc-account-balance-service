package com.ctg.itrdc.account.balance.service;

import java.util.List;
import com.ctg.itrdc.account.balance.model.Menu;
import com.ctg.itrdc.account.balance.model.MenuParent;

public interface  IMenuService {
	public List<MenuParent> getMenuTree();
}
