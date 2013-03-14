package com.hungrycookbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.Model.Recept;
import com.Model.ReceptEngine;

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

	ArrayList<Map<String, String>> receptNameItem;
	ArrayList<ArrayList<Map<String, String>>> receptNames;

	// список аттрибутов группы или элемента
	Map<String, String> m;

	ExpandableListView elvMain;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cookbook);

		engine = ReceptEngine.getInstance(getAssets());
		receptNames = new ArrayList<ArrayList<Map<String, String>>>();

		groupData = new ArrayList<Map<String, String>>();
		for (String type : engine.getTypes()) {
			m = new HashMap<String, String>();
			m.put("groupName", type);
			groupData.add(m);
		}

		String groupFrom[] = new String[] { "groupName" };
		int groupTo[] = new int[] { android.R.id.text1 };

		fillType("A");
		fillType("B");
		fillType("C");
		fillType("D");
		fillType("E");
		fillType("F");
		fillType("G");

		String childFrom[] = new String[] { "receptName" };
		int childTo[] = new int[] { android.R.id.text1 };

		SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
				this, groupData,
				android.R.layout.simple_expandable_list_item_1, groupFrom,
				groupTo, receptNames, android.R.layout.simple_list_item_1,
				childFrom, childTo);

		elvMain = (ExpandableListView) findViewById(R.id.listView_types);
		elvMain.setAdapter(adapter);

	}

	private void fillType(String type) {
		receptNameItem = new ArrayList<Map<String, String>>();
		for (Recept recept : engine.getReceptsByType(type)) {
			m = new HashMap<String, String>();
			m.put("receptName", recept.getNameRecept());
			receptNameItem.add(m);
		}
		receptNames.add(receptNameItem);
	}
}
