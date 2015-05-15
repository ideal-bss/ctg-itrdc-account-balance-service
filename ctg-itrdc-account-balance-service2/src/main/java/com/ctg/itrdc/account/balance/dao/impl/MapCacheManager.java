package com.ctg.itrdc.account.balance.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ctg.itrdc.account.balance.model.BalanceTypeModel;
import com.ctg.itrdc.account.balance.model.SpecialPaymentModel;
import com.ctg.itrdc.account.balance.repository.IBalanceTypeMapper;
import com.ctg.itrdc.account.balance.repository.ISpecialPaymentMapper;
import com.ctg.itrdc.account.balance.util.SpringUtil;

public class MapCacheManager {
	  
    private volatile long updateTime = 0L;// 更新缓存时记录的时间  
  
    private volatile boolean updateFlag = true;// 正在更新时的阀门，为false时表示当前没有更新缓存，为true时表示当前正在更新缓存  
  
    private volatile static MapCacheManager mapCacheObject;// 缓存实例对象  
  
    private static Map<String, Object> cacheMap = new ConcurrentHashMap<String, Object>();// 缓存容器  
    
    SqlSession sqlSession = ((SqlSessionFactory)SpringUtil.getBean("sqlSessionFactory")).openSession();
	IBalanceTypeMapper iBalanceTypeMapper=sqlSession.getMapper(IBalanceTypeMapper.class);//余额类型
	ISpecialPaymentMapper iSpecialPaymentMapper=sqlSession.getMapper(ISpecialPaymentMapper.class);//专款专用
	
    private MapCacheManager() {  
        this.LoadCache();// 加载缓存  
        updateTime = System.currentTimeMillis();// 缓存更新时间  
  
    }  
  
    /** 
     * 采用单例模式获取缓存对象实例 
     *  
     * @return 
     */  
    public static MapCacheManager getInstance() {  
        if (null == mapCacheObject) {  
            synchronized (MapCacheManager.class) {  
                if (null == mapCacheObject) {  
                    mapCacheObject = new MapCacheManager();  
                }  
            }  
        }  
        return mapCacheObject;  
    }  
  
    /** 
     * 装载缓存 
     */  
    private void LoadCache() {  
  
        this.updateFlag = true;// 正在更新  
  
        /********** 数据处理，将数据放入cacheMap缓存中 **begin ******/  
        
        
        List<BalanceTypeModel> listBalanceType=iBalanceTypeMapper.selectAllBalanceType();
        List<SpecialPaymentModel> listSpecial=iSpecialPaymentMapper.selectAllSpecial();
        cacheMap.put("balanceType", listBalanceType);
        cacheMap.put("special", listSpecial);
        
        /********** 数据处理，将数据放入cacheMap缓存中 ***end *******/  
  
        this.updateFlag = false;// 更新已完成  
  
    }  
  
    /** 
     * 返回缓存对象 
     *  
     * @return 
     */  
    public Map<String, Object> getMapCache() {  
  
        long currentTime = System.currentTimeMillis();  
  
        if (this.updateFlag) {// 前缓存正在更新  
            return null;  
  
        }  
  
        if (this.IsTimeOut(currentTime)) {// 如果当前缓存正在更新或者缓存超出时限，需重新加载  
            synchronized (this) {  
                this.ReLoadCache();  
                this.updateTime = currentTime;  
            }  
        }  
  
        return this.cacheMap;  
    }  
  
    private boolean IsTimeOut(long currentTime) {  
  
        return ((currentTime - this.updateTime) > 1000000);// 超过时限，超时  
    }  
  
    /** 
     * 获取缓存项大小 
     * @return 
     */  
    private int getCacheSize() {  
        return cacheMap.size();  
    }  
  
    /** 
     * 获取更新时间 
     * @return 
     */  
    private long getUpdateTime() {  
        return this.updateTime;  
    }  
  
    /** 
     * 获取更新标志 
     * @return 
     */  
    private boolean getUpdateFlag() {  
        return this.updateFlag;  
    }  
  
    /** 
     * 重新装载 
     */  
    private void ReLoadCache() {  
        this.cacheMap.clear();  
        this.LoadCache();  
    }  
}
