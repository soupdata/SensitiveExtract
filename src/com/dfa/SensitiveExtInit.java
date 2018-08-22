package com.dfa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SensitiveExtInit {
	//字符编码GBK
	private String Encod="GBK";
	//创建一个map树来存放处理后的敏感词汇树
	@SuppressWarnings("rawtypes")
	public HashMap SensitiveMap;
	
	@SuppressWarnings("rawtypes")
	public Map init() throws IOException{
		//读取敏感词库
		Set<String> Sword = ReadSensitiveFile();
		//将敏感词库加入到HashMap中
		GetSensitiveToHashMap(Sword);
		
		
		return SensitiveMap;
	}

	
    private Set<String> ReadSensitiveFile() throws IOException {
		
    	Set<String> content = null;
		// E:/codes2016/workspace/ansj/src/cc.txt
		File file = new File("E:/codes2016/workspace/SensitiveExtract/src/word.txt");    
		//读取文件
		InputStreamReader read = new InputStreamReader(new FileInputStream(file),Encod);
		// 文件流是否存在
			if(file.isFile() && file.exists()){      
				content = new HashSet<String>();
				String line = null;
				@SuppressWarnings("resource")
				BufferedReader bufferedReader = new BufferedReader(read);
				while((line = bufferedReader.readLine()) != null){ 
					// 读取文件，将文件内容放入到set中
					content.add(line);
			    }
			}
			else{         
				//抛出异常
				System.out.println("敏感词库路径不对！");
			}
		  read.close();
		return content;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void GetSensitiveToHashMap(Set<String> sword) {
		//初始化敏感词容器，减少扩容操作
				SensitiveMap = new HashMap(sword.size());     
				//用来存放敏感词库的开头的那个字，作为map的key
				String key = null;  
				Map GenerationMap = null;
				Map<String, String> NewMap = null;
				//迭代sword，遍历
				Iterator<String> iterator = sword.iterator();
				while(iterator.hasNext()){
					//关键字
					key = iterator.next();    
					//================================================
					//System.out.println("输出key："+key);
					GenerationMap = SensitiveMap;
					for(int i = 0 ; i < key.length() ; i++){
						//转换成char型
						char keyChar = key.charAt(i);  
						//==============================================
						//  System.out.println("输出keychar："+keyChar);
						//获取
						Object WordMap = GenerationMap.get(keyChar);       
						//如果存在该key，直接赋值
						if(WordMap != null){        
							GenerationMap = (Map) WordMap;
						}
						//不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
						else{     
							NewMap = new HashMap<String,String>();
							//不是最后一个
							NewMap.put("isEnd", "0");     
							GenerationMap.put(keyChar, NewMap);
							GenerationMap = NewMap;
						}
						
						if(i == key.length() - 1){
							GenerationMap.put("isEnd", "1");    //最后一个
						}
					}
					
				}
				//打印切割的字符
//				Iterator<Map.Entry<Integer, Integer>> entries = GenerationMap.entrySet().iterator(); 
//				while (entries.hasNext()) { 
//				  Map.Entry<Integer, Integer> entry = entries.next(); 
//				  System.out.println("nowmap----->"+"Key = " + entry.getKey() + ", Value = " + entry.getValue());
//				}
		
	}

	



}
