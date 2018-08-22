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
	//�ַ�����GBK
	private String Encod="GBK";
	//����һ��map������Ŵ��������дʻ���
	@SuppressWarnings("rawtypes")
	public HashMap SensitiveMap;
	
	@SuppressWarnings("rawtypes")
	public Map init() throws IOException{
		//��ȡ���дʿ�
		Set<String> Sword = ReadSensitiveFile();
		//�����дʿ���뵽HashMap��
		GetSensitiveToHashMap(Sword);
		
		
		return SensitiveMap;
	}

	
    private Set<String> ReadSensitiveFile() throws IOException {
		
    	Set<String> content = null;
		// E:/codes2016/workspace/ansj/src/cc.txt
		File file = new File("E:/codes2016/workspace/SensitiveExtract/src/word.txt");    
		//��ȡ�ļ�
		InputStreamReader read = new InputStreamReader(new FileInputStream(file),Encod);
		// �ļ����Ƿ����
			if(file.isFile() && file.exists()){      
				content = new HashSet<String>();
				String line = null;
				@SuppressWarnings("resource")
				BufferedReader bufferedReader = new BufferedReader(read);
				while((line = bufferedReader.readLine()) != null){ 
					// ��ȡ�ļ������ļ����ݷ��뵽set��
					content.add(line);
			    }
			}
			else{         
				//�׳��쳣
				System.out.println("���дʿ�·�����ԣ�");
			}
		  read.close();
		return content;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void GetSensitiveToHashMap(Set<String> sword) {
		//��ʼ�����д��������������ݲ���
				SensitiveMap = new HashMap(sword.size());     
				//����������дʿ�Ŀ�ͷ���Ǹ��֣���Ϊmap��key
				String key = null;  
				Map GenerationMap = null;
				Map<String, String> NewMap = null;
				//����sword������
				Iterator<String> iterator = sword.iterator();
				while(iterator.hasNext()){
					//�ؼ���
					key = iterator.next();    
					//================================================
					//System.out.println("���key��"+key);
					GenerationMap = SensitiveMap;
					for(int i = 0 ; i < key.length() ; i++){
						//ת����char��
						char keyChar = key.charAt(i);  
						//==============================================
						//  System.out.println("���keychar��"+keyChar);
						//��ȡ
						Object WordMap = GenerationMap.get(keyChar);       
						//������ڸ�key��ֱ�Ӹ�ֵ
						if(WordMap != null){        
							GenerationMap = (Map) WordMap;
						}
						//���������򹹽�һ��map��ͬʱ��isEnd����Ϊ0����Ϊ���������һ��
						else{     
							NewMap = new HashMap<String,String>();
							//�������һ��
							NewMap.put("isEnd", "0");     
							GenerationMap.put(keyChar, NewMap);
							GenerationMap = NewMap;
						}
						
						if(i == key.length() - 1){
							GenerationMap.put("isEnd", "1");    //���һ��
						}
					}
					
				}
				//��ӡ�и���ַ�
//				Iterator<Map.Entry<Integer, Integer>> entries = GenerationMap.entrySet().iterator(); 
//				while (entries.hasNext()) { 
//				  Map.Entry<Integer, Integer> entry = entries.next(); 
//				  System.out.println("nowmap----->"+"Key = " + entry.getKey() + ", Value = " + entry.getValue());
//				}
		
	}

	



}
