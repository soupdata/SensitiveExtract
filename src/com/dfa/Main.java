package com.dfa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;


public class Main {

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException {
		SenstiveExCall call = new SenstiveExCall();
		//=============================================================================
		File file = new File("E:/codes2016/workspace/SensitiveExtract/src/mytable.txt");    
		//��ȡ�ļ�
		InputStreamReader read = new InputStreamReader(new FileInputStream(file));
		// �ļ����Ƿ����
		String string="";
			if(file.isFile() && file.exists()){      
				String line = null;
				@SuppressWarnings("resource")
				BufferedReader bufferedReader = new BufferedReader(read);
				while((line = bufferedReader.readLine()) != null){ 
                   string=string+line;
			    }
			}
			else{         
				//�׳��쳣
				System.out.println("�ļ�·������");
			}
		  read.close();
		
		//=============================================================================
		
		
		 String string1="�������һ��ɵС��Ŷ������ë��";
		System.out.println("==============================================================Main=================================================");
		System.out.println("==============================================================");
		System.out.println("���дʻ�����Ϊ��" + call.SensitiveMap.size());
		System.out.println("==============================================================");
		//System.out.println("�������Ϊ��"+string);
		System.out.println("������������Ϊ��" + string1.length());
		System.out.println("==============================================================");
		long starttime = System.currentTimeMillis();
		Set<String> set = call.GetSensitiveWords(string1);
		long endtime = System.currentTimeMillis();
		long time=endtime-starttime;
		System.out.println("����а������дʵĸ���Ϊ��" + set.size());
		System.out.println("==============================================================");
		System.out.println( "����Ϊ��ȡ���������дʻ㣺" +"\n"+ set);
		System.out.println("==============================================================");
		System.out.println("��ʱ��" +time);
		System.out.println("==============================================================END=================================================");
	}

}
