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
    
//    private static SqlSession sqlSession = ((SqlSessionFactory)SpringUtil.getBean("sqlSessionFactory")).openSession();
//    private static IBalanceTypeMapper iBalanceTypeMapper=sqlSession.getMapper(IBalanceTypeMapper.class);//余额类型
//    private static ISpecialPaymentMapper iSpecialPaymentMapper=sqlSession.getMapper(ISpecialPaymentMapper.class);//专款专用
//    private static ISpecialPaymentDescMapper iSpecialPaymentDescMapper=sqlSession.getMapper(ISpecialPaymentDescMapper.class);//专款专用
	
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
      SqlSession sqlSession = ((SqlSessionFactory)SpringUtil.getBean("sqlSessionFactory")).openSession();
      IBalanceTypeMapper iBalanceTypeMapper=sqlSession.getMapper(IBalanceTypeMapper.class);//余额类型
      ISpecialPaymentMapper iSpecialPaymentMapper=sqlSession.getMapper(ISpecialPaymentMapper.class);//专款专用
      ISpecialPaymentDescMapper iSpecialPaymentDescMapper=sqlSession.getMapper(ISpecialPaymentDescMapper.class);//专款专用

        this.updateFlag = true;// 正在更新  
        
        List<BalanceTypeModel> typeList=iBalanceTypeMapper.selectAllBalanceType();
        List<SpecialPaymentModel> specialList=iSpecialPaymentMapper.selectAllSpecial();
        List<SpecialPaymentDescModel> specialDescList=iSpecialPaymentDescMapper.selectAll();
        for(SpecialPaymentModel special:specialList){
        	for(SpecialPaymentDescModel specialDesc:specialDescList){
        		if(special.getSpePaymentId()!=null&&special.getSpePaymentId().equals(specialDesc.getSpePaymentId())){
        			special.setSpecialPaymentDescModel(specialDesc);
        			break;
        		}
        	}
        	specialPaymentList.add(special);
        }
        
        for(BalanceTypeModel type:typeList){
        	for(SpecialPaymentModel specialmodel:specialPaymentList){
        		if(type.getSpePaymentId()!=null&&type.getSpePaymentId().equals(specialmodel.getSpePaymentId())){
        			type.setSpecialPaymentModel(specialmodel);
        			break;
        		}
        	}
        	balanceTypeList.add(type);
        }
        
        this.updateFlag = false;// 更新已完成  
  
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
    private List<BalanceTypeModel> ReLoadCache() {  
        this.balanceTypeList.clear();
        this.specialPaymentList.clear();
//        this.LoadCache();  
        return this.balanceTypeList;
    }  
    /**
     * 根据余额账本类型ID获取类型
     * @param typeId 余额账本类型ID
     * @return
     */
    public BalanceTypeModel getByTypeId(Long typeId) {
		BalanceTypeModel balanceTypeModel = null;
		List<BalanceTypeModel> balanceTypeList=this.balanceTypeList;
		for (BalanceTypeModel btm : balanceTypeList) {
			if (btm != null && btm.getBalanceTypeId()!=null&&btm.getBalanceTypeId() == typeId) {
				balanceTypeModel = btm;
				break;
			}
		}
		return balanceTypeModel;
	}
    /**
     * 新增余额类型
     * @param balanceTypeId
     */
    public void addBalanceType(long balanceTypeId){
      SqlSession sqlSession = ((SqlSessionFactory)SpringUtil.getBean("sqlSessionFactory")).openSession();
      IBalanceTypeMapper iBalanceTypeMapper=sqlSession.getMapper(IBalanceTypeMapper.class);//余额类型

    	BalanceTypeModel type=iBalanceTypeMapper.selectTypeById(balanceTypeId);
    	for(SpecialPaymentModel model:this.specialPaymentList){
    		if(type!= null && type.getSpePaymentId()!=null&&type.getSpePaymentId().equals(model.getSpePaymentId())){
    			type.setSpecialPaymentModel(model);
    		}
    	}
    	this.balanceTypeList.add(type);
    }
    /**
     * 新增专款专用
     * @param spePaymentId
     */
    public void addSpecial(long spePaymentId){
      SqlSession sqlSession = ((SqlSessionFactory)SpringUtil.getBean("sqlSessionFactory")).openSession();
      ISpecialPaymentMapper iSpecialPaymentMapper=sqlSession.getMapper(ISpecialPaymentMapper.class);//专款专用
      ISpecialPaymentDescMapper iSpecialPaymentDescMapper=sqlSession.getMapper(ISpecialPaymentDescMapper.class);//专款专用

    	SpecialPaymentModel model=iSpecialPaymentMapper.selectSprcialById(spePaymentId);
    	List<SpecialPaymentDescModel> specialDescList=iSpecialPaymentDescMapper.selectAll();
    	for(SpecialPaymentDescModel specialDesc:specialDescList){
    		if(model.getSpePaymentId()!=null&&model.getSpePaymentId().equals(specialDesc.getSpePaymentId())){
    			model.setSpecialPaymentDescModel(specialDesc);
    		}
    	}
    	this.specialPaymentList.add(model);
    }
    /**
     * 修改余额类型
     * @param balanceTypeId
     */
    public void  updateBalanceType(long balanceTypeId){
    	SqlSession sqlSession = ((SqlSessionFactory)SpringUtil.getBean("sqlSessionFactory")).openSession();
        IBalanceTypeMapper iBalanceTypeMapper=sqlSession.getMapper(IBalanceTypeMapper.class);//余额类型
        for(BalanceTypeModel balType:this.balanceTypeList){
        	if(balType.getBalanceTypeId()==balanceTypeId){
        		this.balanceTypeList.remove(balType);
        	}
        }
    	BalanceTypeModel type=iBalanceTypeMapper.selectTypeById(balanceTypeId);
    	for(SpecialPaymentModel model:this.specialPaymentList){
    		if(type.getSpePaymentId()!=null&&type.getSpePaymentId().equals(model.getSpePaymentId())){
    			type.setSpecialPaymentModel(model);
    		}
    	}
    	this.balanceTypeList.add(type);
    }
    /**
     * 修改专款专用
     * @param spePaymentId
     */
    public void updateSpecial(long spePaymentId){
      SqlSession sqlSession = ((SqlSessionFactory)SpringUtil.getBean("sqlSessionFactory")).openSession();
      ISpecialPaymentMapper iSpecialPaymentMapper=sqlSession.getMapper(ISpecialPaymentMapper.class);//专款专用
      ISpecialPaymentDescMapper iSpecialPaymentDescMapper=sqlSession.getMapper(ISpecialPaymentDescMapper.class);//专款专用
      for(SpecialPaymentModel speModel:this.specialPaymentList){
    	  if(speModel.getSpePaymentId()==spePaymentId){
    		  this.specialPaymentList.remove(speModel);
    	  }
      }
      
      SpecialPaymentModel model=iSpecialPaymentMapper.selectSprcialById(spePaymentId);
    	List<SpecialPaymentDescModel> specialDescList=iSpecialPaymentDescMapper.selectAll();
    	for(SpecialPaymentDescModel specialDesc:specialDescList){
    		if(model.getSpePaymentId()!=null&&model.getSpePaymentId().equals(specialDesc.getSpePaymentId())){
    			model.setSpecialPaymentDescModel(specialDesc);
    		}
    	}
    	this.specialPaymentList.add(model);
    }
    /**
     * 删除余额类型
     * @param balanceTypeId 类型标识
     */
    public void deleteBalanceType(long balanceTypeId){
        for(BalanceTypeModel balType:this.balanceTypeList){
        	if(balType.getBalanceTypeId()==balanceTypeId){
        		this.balanceTypeList.remove(balType);
        	}
        }
    }
    /**
     * 删除专款专用
     * @param spePaymentId 专款专用标识
     */
    public void deleteSpecial(long spePaymentId){
        for(SpecialPaymentModel speModel:this.specialPaymentList){
      	  if(speModel.getSpePaymentId()==spePaymentId){
      		  this.specialPaymentList.remove(speModel);
      	  }
        }
    }
    
    public List<BalanceTypeModel> getBalanceTypeList(){
    	return this.balanceTypeList;
    }
    
}
