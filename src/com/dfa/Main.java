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
		//读取文件
		InputStreamReader read = new InputStreamReader(new FileInputStream(file));
		// 文件流是否存在
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
				//抛出异常
				System.out.println("文件路径有误！");
			}
		  read.close();
		
		//=============================================================================
		
		
		 String string1="你可能是一个傻小逼哦，我是毛泽东";
		System.out.println("==============================================================Main=================================================");
		System.out.println("==============================================================");
		System.out.println("敏感词汇总数为：" + call.SensitiveMap.size());
		System.out.println("==============================================================");
		//System.out.println("待测语句为："+string);
		System.out.println("需检测语句的字数为：" + string1.length());
		System.out.println("==============================================================");
		long starttime = System.currentTimeMillis();
		Set<String> set = call.GetSensitiveWords(string1);
		long endtime = System.currentTimeMillis();
		long time=endtime-starttime;
		System.out.println("语句中包含敏感词的个数为：" + set.size());
		System.out.println("==============================================================");
		System.out.println( "以下为提取出来的敏感词汇：" +"\n"+ set);
		System.out.println("==============================================================");
		System.out.println("耗时：" +time);
		System.out.println("==============================================================END=================================================");
	}

}
