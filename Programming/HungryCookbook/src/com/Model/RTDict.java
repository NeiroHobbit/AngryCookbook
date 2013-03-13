package com.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.res.AssetManager;

public class RTDict {

	private AssetManager am;
	private Map<String, String> recType;

	public RTDict(AssetManager amanager) {
		am = amanager;
		recType = new HashMap<String, String>();
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
				if (newLine.length() > 1) {
					String[] parts = newLine.split(" ");
					String key = parts[0];
					String value = "";
					for (int i = 1; i < parts.length; i++) {
						value += parts[i];
						if (i != parts.length - 1) {
							value += " ";
						}
					}
					recType.put(key, value);
				}
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getTypes() {
		ArrayList<String> types = new ArrayList<String>();
		for (String t : asSortedList(recType.keySet())) {
			if (t.length() == 1) {
				types.add(recType.get(t));
			}
		}
		return types;
	}

	private static <T extends Comparable<? super T>> List<T> asSortedList(
			Collection<T> c) {
		List<T> list = new ArrayList<T>(c);
		java.util.Collections.sort(list);
		return list;
	}

}
