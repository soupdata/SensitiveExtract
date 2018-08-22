package com.dfa;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class SenstiveExCall {
	@SuppressWarnings("rawtypes")
	//��ʼ��һ���µ�map������һ���յ����μܹ�
	public static Map SensitiveMap = null;
	/**
	 * ��ʼ�����дʿ�
	 * @throws IOException 
	 */
	public SenstiveExCall() throws IOException{
		SensitiveMap = new SensitiveExtInit().init();
		@SuppressWarnings("unchecked")
		//����map,��ʵҲ�����дʻ����ṹ����map����ʽ����
		Iterator<Map.Entry<Integer, Integer>> entries = SensitiveMap.entrySet().iterator(); 
		while (entries.hasNext()) { 
		  @SuppressWarnings("unused")
		Map.Entry<Integer, Integer> entry = entries.next(); 
		  //�鿴����map���������дʻ�����
		  System.out.println("SensitiveMap---->"+"Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
		}
	}
	
	/**
	 * ��ȡ���д�
	 * @param words
	 * @return
	 */
	public Set<String> GetSensitiveWords(String words ){
		Set<String> SensitivesSet = new HashSet<String>();
		
		for(int i = 0 ; i < words.length() ; i++){
			//�ж��Ƿ���������ַ�
			int length = CheckSensitiveWord(words, i);    
			// ����,����ӵ�SensitivesSet��
			if(length > 0){    
				SensitivesSet.add(words.substring(i, i+length));
				// ��Ϊfor����,���Դ˴�-1
				i = i + length - 1;    
			}
		}
		
		return SensitivesSet;
	}
	
	/**
	 * ����������Ƿ���������ַ�,������ڣ��򷵻����д��ַ��ĳ��ȣ������ڷ���0
	 * @param words
	 * @param beginIndex
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public int CheckSensitiveWord(String words,int BeginIndex){
		// ���дʽ�����ʶλ���������д�ֻ��1λ�����
		boolean  flag = false;    
		// ƥ���ʶ��Ĭ��Ϊ0
		int MatchFlag = 0;     
		char word = 0;
		Map GenerationMap = SensitiveMap;
		
		for(int i = BeginIndex; i < words.length() ; i++){
				word = words.charAt(i);
				// ��ȡָ��key
				 GenerationMap = (Map) GenerationMap.get(word);    
				// ���ڣ����ж��Ƿ�Ϊ���һ��
				if(GenerationMap != null){   
						// �ҵ���Ӧkey��ƥ���ʶ+1 
							MatchFlag++;     
						if("1".equals(GenerationMap.get("isEnd"))){     
							// ���Ϊ���һ��ƥ�����,����ѭ��������ƥ���ʶ��
							// ������־λ��ΪΪtrue
							flag = true;          
						
						}
				}
				else{  
					//  �����ڣ�ֱ�ӷ���
					break;
				}
				//���ȱ�����ڵ���1������Ϊ�� 
				
		}
		if(!flag||MatchFlag < 2 ){        
			MatchFlag = 0;
		}
		return MatchFlag;
	}
}
