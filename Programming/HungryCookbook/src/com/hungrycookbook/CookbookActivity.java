package com.hungrycookbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.Model.ReceptEngine;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

public class CookbookActivity extends FragmentActivity {
	
	private ReceptEngine engine;

	// названия компаний (групп)
	String[] groups = new String[] { "HTC", "Samsung", "LG" };

	// названия телефонов (элементов)
	String[] phonesHTC = new String[] { "Sensation", "Desire", "Wildfire",
			"Hero" };
	String[] phonesSams = new String[] { "Galaxy S II", "Galaxy Nexus", "Wave" };
	String[] phonesLG = new String[] { "Optimus", "Optimus Link",
			"Optimus Black", "Optimus One" };

	// коллекция для групп
	ArrayList<Map<String, String>> groupData;

	// коллекция для элементов одной группы
	ArrayList<Map<String, String>> childDataItem;

	// общая коллекция для коллекций элементов
	ArrayList<ArrayList<Map<String, String>>> childData;
	// в итоге получится childData = ArrayList<childDataItem>

	// список аттрибутов группы или элемента
	Map<String, String> m;

	ExpandableListView elvMain;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cookbook);
		
		engine = ReceptEngine.getInstance(getAssets());
		

		groupData = new ArrayList<Map<String, String>>();
		for (String group : groups) {
			m = new HashMap<String, String>();
			m.put("groupName", group);
			groupData.add(m);
		}

		String groupFrom[] = new String[] { "groupName" };
		int groupTo[] = new int[] { android.R.id.text1 };

		childData = new ArrayList<ArrayList<Map<String, String>>>();

		childDataItem = new ArrayList<Map<String, String>>();
		for (String phone : phonesHTC) {
			m = new HashMap<String, String>();
			m.put("phoneName", phone);
			childDataItem.add(m);
		}
		childData.add(childDataItem);

		// создаем коллекцию элементов для второй группы
		childDataItem = new ArrayList<Map<String, String>>();
		for (String phone : phonesSams) {
			m = new HashMap<String, String>();
			m.put("phoneName", phone);
			childDataItem.add(m);
		}
		childData.add(childDataItem);

		// создаем коллекцию элементов для третьей группы
		childDataItem = new ArrayList<Map<String, String>>();
		for (String phone : phonesLG) {
			m = new HashMap<String, String>();
			m.put("phoneName", phone);
			childDataItem.add(m);
		}
		childData.add(childDataItem);

		// список аттрибутов элементов для чтения
		String childFrom[] = new String[] { "phoneName" };
		// список ID view-элементов, в которые будет помещены аттрибуты
		// элементов
		int childTo[] = new int[] { android.R.id.text1 };

		SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
				this, groupData,
				android.R.layout.simple_expandable_list_item_1, groupFrom,
				groupTo, childData, android.R.layout.simple_list_item_1,
				childFrom, childTo);

		elvMain = (ExpandableListView) findViewById(R.id.listView_types);
		elvMain.setAdapter(adapter);

	}
}
