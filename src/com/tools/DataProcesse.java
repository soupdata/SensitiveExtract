package com.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataProcesse {

	public static void main(String[] args) throws IOException {
		File file = new File("E:/codes2016/workspace/SensitiveExtract/src/word.txt");    
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

	}

}
