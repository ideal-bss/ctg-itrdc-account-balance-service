package com.ctg.itrdc.account.balance.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ctg.itrdc.account.balance.service.IMenuService;

@Service("menuAction")
public class MenuAction extends BaseAction {

	private IMenuService menuService;

	public IMenuService getMenuService() {
		return menuService;
	}

	@Autowired
	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public void getMenuTree() {
		writeJson(menuService.getMenuTree());
	}

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		MenuAction menuAction = (MenuAction) context.getBean("menuAction");
		System.out.println(JSON.toJSONString(menuAction.getMenuService().getMenuTree()));
	}
}
