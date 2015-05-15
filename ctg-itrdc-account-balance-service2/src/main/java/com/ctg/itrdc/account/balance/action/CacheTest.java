package com.ctg.itrdc.account.balance.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.ctg.itrdc.account.balance.dao.impl.MapCacheManager;
import com.ctg.itrdc.account.balance.model.BalanceTypeModel;

public class CacheTest {
	public static void main(String[] args) {
		MapCacheManager cache = MapCacheManager.getInstance();  
        Map<String, Object> cacheMap = new ConcurrentHashMap<String, Object>();  
          
        cacheMap = cache.getMapCache();  
        Set<String> set = cacheMap.keySet();  
        Iterator<String> it = set.iterator();  
          
        while(it.hasNext()){  
            String key = it.next();  
            List<BalanceTypeModel> list=(List<BalanceTypeModel>)cacheMap.get(key);
            System.out.println(key+"-"+list.size());
        }  
        
	}
}
