package com.ctg.itrdc.account.balance.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.ctg.itrdc.account.balance.model.Menu;
import com.ctg.itrdc.account.balance.model.MenuParent;
import com.ctg.itrdc.account.balance.service.IMenuService;
import com.ctg.itrdc.account.balance.util.BaseUtil;

@Service("menuService")
public class MenuServiceImpl implements IMenuService {

	/*
	 * 后续改为从表中获取菜单
	 */
	public List<Menu> getMenuTree2() {
		List<Menu> menuList = new ArrayList<Menu>();

		Menu yecx = new Menu();
		yecx.setId("1");
		yecx.setText("余额查询");
		HashMap<String, Object> hm1 = new HashMap<String, Object>();
		hm1.put("url", "/test/queryBalance.action");
		yecx.setAttributes(hm1);
		menuList.add(yecx);

		Menu yelxpz = new Menu();
		yelxpz.setId("2");
		yelxpz.setText("余额存入");
		HashMap<String, Object> hm2 = new HashMap<String, Object>();
		hm2.put("url", "/acctBalance/balanceAddGo.action");
		yelxpz.setAttributes(hm2);
		menuList.add(yelxpz);

		Menu zkzypz = new Menu();
		zkzypz.setId("3");
		zkzypz.setText("余额类型配置");
		HashMap<String, Object> hm3 = new HashMap<String, Object>();
		hm3.put("url", "/special/specialQueryGo.action");
		zkzypz.setAttributes(hm3);
		menuList.add(zkzypz);
		return menuList;
	}

	/*public List<Menu> getMenuTree(String val) {
		List<Menu> menuList = new ArrayList<Menu>();
		Document doc;
		try {
			doc = Jsoup.parse(new File(BaseUtil.getMenuUrl()), "UTF-8");
			Elements elements = doc.getElementsByTag("element");
			for (Element element : elements) {
				Menu menuElement = new Menu();
				menuElement.setId(element.getElementsByTag("id").text());
				menuElement.setText(element.getElementsByTag("text").text());
				menuElement.setState(element.getElementsByTag("state").text());
				menuElement.setChecked(element.getElementsByTag("checked").text());
				HashMap<String, Object> attributes = new HashMap<String, Object>();
				attributes.put("url", element.getElementsByTag("attributes").first().getElementsByTag("url").text());
				menuElement.setAttributes(attributes);
				menuList.add(menuElement);
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
		return menuList;
	
	}*/

	public List<MenuParent> getMenuTree() {
		List<MenuParent> menuList = new ArrayList<MenuParent>();
		Document doc;
		try {
			doc = Jsoup.parse(new File(BaseUtil.getMenuUrl()), "UTF-8");
			Elements elements = doc.getElementsByTag("elements");
			for (Element element : elements) {
				MenuParent menuParent=new MenuParent();
				List<Menu> listMenu=new ArrayList<Menu>();
				HashMap<String, Object> attributesMenu = new HashMap<String, Object>();
				attributesMenu.put("itemId", element.getElementsByTag("attributesMenu").first().getElementsByTag("itemId").text());
				attributesMenu.put("itemName", element.getElementsByTag("attributesMenu").first().getElementsByTag("itemName").text());
				menuParent.setAttributesMenu(attributesMenu);
				Elements elementsChild = element.getElementsByTag("element");
				for (Element elementChild : elementsChild) {
					Menu menu=new Menu();
					menu.setId(elementChild.getElementsByTag("id").text());
					menu.setText(elementChild.getElementsByTag("text").text());
					menu.setState(elementChild.getElementsByTag("state").text());
					menu.setChecked(elementChild.getElementsByTag("checked").text());
					HashMap<String, Object> attributes = new HashMap<String, Object>();
					attributes.put("url", elementChild.getElementsByTag("attributes").first().getElementsByTag("url").text());
					menu.setAttributes(attributes);
					listMenu.add(menu);
				}
				menuParent.setListMenu(listMenu);
				menuList.add(menuParent);
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
		return menuList;
	
	}
}
