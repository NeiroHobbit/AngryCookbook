package com.hungrycookbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuFragment extends Fragment {

	private View rootView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_menu, container, false);

		Button menu_button = (Button) rootView.findViewById(R.id.button_cookbook);
		menu_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View button) {
				Intent i = new Intent(rootView.getContext(), CookbookActivity.class);
				rootView.getContext().startActivity(i);

			}
		});
		Button info_button = (Button) rootView.findViewById(R.id.button_info);
		info_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(rootView.getContext(), InfoActivity.class);
				startActivity(intent);
				
			}
		});
		
		return rootView;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	

	
}
