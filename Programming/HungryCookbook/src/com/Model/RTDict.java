package com.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.res.AssetManager;

public class RTDict {
	
	private AssetManager am;
	private ArrayList<Map<String, String>> recType;

	public RTDict(AssetManager amanager) {
		am = amanager;
		recType = new ArrayList<Map<String,String>>();
		init();
	}

	private void init() {
		InputStream is;
		try {
			is = am.open("rec_txt");

			String newLine;
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			while ((newLine = br.readLine()) != null) {
				String[] parts = newLine.split(" ");
				Map<String, String> map = new HashMap<String, String>();
				String key = parts[0];
				String value = "";
				for(int i=1; i<parts.length; i++){
					value += parts[i];
                    if (i != parts.length-1)
                    {
                        value += " ";
                    }
				}
				map.put(key, value);
				recType.add(map);
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
