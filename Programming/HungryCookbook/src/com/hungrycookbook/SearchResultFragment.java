package com.hungrycookbook;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.Model.Recept;
import com.Model.ReceptWithPriority;

public class SearchResultFragment extends Fragment {
	private View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_search_result, container,
				false);
		
		SearchActivity searchActivity = (SearchActivity) getActivity();
		setRecepts(searchActivity.getRec());
		
		return rootView;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	public void setRecepts(ArrayList<ReceptWithPriority> recepts){
		
		LinearLayout linLay = (LinearLayout) rootView
				.findViewById(R.id.listResult);

		int padd = 5;
		LinearLayout ll = new LinearLayout(getActivity());
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setPadding(padd, padd, padd, padd);

		for (ReceptWithPriority recept : recepts) {
			TextView tv1 = new TextView(getActivity());
			tv1.setTypeface(null, Typeface.BOLD);
			tv1.setText(recept.getNameRecept());
			TextView tv2 = new TextView(getActivity());

			String[] receptTextArray = recept.getTextRecept()
					.replace("newline", " ").split(" ");
			String receptText = "";
			for (int i = 0; i < 30 && receptTextArray.length > i + 1; i++) {
				receptText += receptTextArray[i];
				if (i + 1 < 30) {
					receptText += " ";
				}
			}
			receptText += "...";

			tv2.setText(receptText);
			ll.addView(tv1);
			ll.addView(tv2);
			View v = new View(getActivity());
			v.setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.MATCH_PARENT, 1));
			v.setBackgroundColor(Color.rgb(51, 51, 51));
			ll.addView(v);
		}

		linLay.addView(ll);
		
	}
}
