package com.ctg.itrdc.account.balance.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ctg.itrdc.account.balance.repository.IBalanceTypeMapper;
import com.ctg.itrdc.account.balance.repository.ISpecialPaymentDescMapper;
import com.ctg.itrdc.account.balance.repository.ISpecialPaymentMapper;
import com.ctg.itrdc.account.balance.util.SpringUtil;

public class BalanceConfig {
	  
    private volatile long updateTime = 0L;// 更新缓存时记录的时间  
  
    private volatile boolean updateFlag = true;// 正在更新时的阀门，为false时表示当前没有更新缓存，为true时表示当前正在更新缓存  
  
    private volatile static BalanceConfig balanceConfig;// 缓存实例对象  
  
    private static List<BalanceTypeModel> balanceTypeList=new ArrayList<BalanceTypeModel>();
    private static List<SpecialPaymentModel> specialPaymentList=new ArrayList<SpecialPaymentModel>();
    
    SqlSession sqlSession = ((SqlSessionFactory)SpringUtil.getBean("sqlSessionFactory")).openSession();
	IBalanceTypeMapper iBalanceTypeMapper=sqlSession.getMapper(IBalanceTypeMapper.class);//余额类型
	ISpecialPaymentMapper iSpecialPaymentMapper=sqlSession.getMapper(ISpecialPaymentMapper.class);//专款专用
	ISpecialPaymentDescMapper iSpecialPaymentDescMapper=sqlSession.getMapper(ISpecialPaymentDescMapper.class);//专款专用
	
    private BalanceConfig() {  
        this.LoadCache();// 加载缓存  
        updateTime = System.currentTimeMillis();// 缓存更新时间  
  
    }  
  
    /** 
     * 采用单例模式获取缓存对象实例 
     *  
     * @return 
     */  
    public static BalanceConfig getInstance() {  
        if (null == balanceConfig) {  
            synchronized (BalanceConfig.class) {  
                if (null == balanceConfig) {  
                	balanceConfig = new BalanceConfig();  
                }  
            }  
        }  
        return balanceConfig;  
    }  
  
    /** 
     * 装载缓存 
     */  
    private void LoadCache() {  
  
        this.updateFlag = true;// 正在更新  
        
        List<BalanceTypeModel> typeList=iBalanceTypeMapper.selectAllBalanceType();
        List<SpecialPaymentModel> specialList=iSpecialPaymentMapper.selectAllSpecial();
        List<SpecialPaymentDescModel> specialDescList=iSpecialPaymentDescMapper.selectAll();
        for(SpecialPaymentModel special:specialList){
        	for(SpecialPaymentDescModel specialDesc:specialDescList){
        		if(special.getSpePaymentId().equals(specialDesc.getSpePaymentId())){
        			special.setSpecialPaymentDescModel(specialDesc);
        			break;
        		}
        	}
        	specialPaymentList.add(special);
        }
        
        for(BalanceTypeModel type:typeList){
        	for(SpecialPaymentModel specialmodel:specialList){
        		if(type.getSpePaymentId() != null && type.getSpePaymentId().equals(specialmodel.getSpePaymentId())){
        			type.setSpecialPaymentModel(specialmodel);
        			break;
        		}
        	}
        	balanceTypeList.add(type);
        }
        
        this.updateFlag = false;// 更新已完成  
  
    }  
  
     
    
    /** 
     * 返回缓存 余额类型对象 
     *  
     * @return 
     */ 
    public List<BalanceTypeModel> getBalanceTypeList() {  
    	  
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
  
        return this.balanceTypeList;  
    }
    /** 
     * 返回缓存 专款专用对象 
     *  
     * @return 
     */
    public List<SpecialPaymentModel> getSpecialPaymentList() {  
  	  
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
  
        return this.specialPaymentList;  
    }
  
    private boolean IsTimeOut(long currentTime) {  
  
        return ((currentTime - this.updateTime) > 1000000);// 超过时限，超时  
    }  
  
    /** 
     * 获取缓存项大小 
     * @return 
     */  
    private int getBalTypeCacheSize() {  
        return balanceTypeList.size();  
    }  
    private int getSpecialPaymentCacheSize() {  
        return specialPaymentList.size();  
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
        this.balanceTypeList.clear();
        this.specialPaymentList.clear();
        this.LoadCache();  
    }  
    /**
     * 根据余额账本类型ID获取类型
     * @param typeId 余额账本类型ID
     * @return
     */
    public BalanceTypeModel getByTypeId(Long typeId) {
		BalanceTypeModel balanceTypeModel = null;
		List<BalanceTypeModel> balanceTypeList=balanceConfig.getBalanceTypeList();
		for (BalanceTypeModel btm : balanceTypeList) {
			if (btm.getBalanceTypeId() == typeId) {
				balanceTypeModel = btm;
				break;
			}
		}
		return balanceTypeModel;
	}
}
