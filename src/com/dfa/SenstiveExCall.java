package com.dfa;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class SenstiveExCall {
	@SuppressWarnings("rawtypes")
	//初始化一个新的map，构造一个空的树形架构
	public static Map SensitiveMap = null;
	/**
	 * 初始化敏感词库
	 * @throws IOException 
	 */
	public SenstiveExCall() throws IOException{
		SensitiveMap = new SensitiveExtInit().init();
		@SuppressWarnings("unchecked")
		//遍历map,其实也是敏感词汇树结构，以map的形式表现
		Iterator<Map.Entry<Integer, Integer>> entries = SensitiveMap.entrySet().iterator(); 
		while (entries.hasNext()) { 
		  @SuppressWarnings("unused")
		Map.Entry<Integer, Integer> entry = entries.next(); 
		  //查看树形map，根据敏感词汇生成
		  System.out.println("SensitiveMap---->"+"Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
		}
	}
	
	/**
	 * 获取敏感词
	 * @param words
	 * @return
	 */
	public Set<String> GetSensitiveWords(String words ){
		Set<String> SensitivesSet = new HashSet<String>();
		
		for(int i = 0 ; i < words.length() ; i++){
			//判断是否包含敏感字符
			int length = CheckSensitiveWord(words, i);    
			// 存在,则添加到SensitivesSet中
			if(length > 0){    
				SensitivesSet.add(words.substring(i, i+length));
				// 因为for自增,所以此处-1
				i = i + length - 1;    
			}
		}
		
		return SensitivesSet;
	}
	
	/**
	 * 检查文字中是否包含敏感字符,如果存在，则返回敏感词字符的长度，不存在返回0
	 * @param words
	 * @param beginIndex
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public int CheckSensitiveWord(String words,int BeginIndex){
		// 敏感词结束标识位：用于敏感词只有1位的情况
		boolean  flag = false;    
		// 匹配标识数默认为0
		int MatchFlag = 0;     
		char word = 0;
		Map GenerationMap = SensitiveMap;
		
		for(int i = BeginIndex; i < words.length() ; i++){
				word = words.charAt(i);
				// 获取指定key
				 GenerationMap = (Map) GenerationMap.get(word);    
				// 存在，则判断是否为最后一个
				if(GenerationMap != null){   
						// 找到相应key，匹配标识+1 
							MatchFlag++;     
						if("1".equals(GenerationMap.get("isEnd"))){     
							// 如果为最后一个匹配规则,结束循环，返回匹配标识数
							// 结束标志位社为为true
							flag = true;          
						
						}
				}
				else{  
					//  不存在，直接返回
					break;
				}
				//长度必须大于等于1，才能为词 
				
		}
		if(!flag||MatchFlag < 2 ){        
			MatchFlag = 0;
		}
		return MatchFlag;
	}
}
