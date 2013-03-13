package com.hungrycookbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuFragment extends Fragment {

	private View rootView;
	TextView info2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_menu, container, false);

		Button menu_button = (Button) rootView
				.findViewById(R.id.button_cookbook);
		menu_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View button) {
				Intent i = new Intent(rootView.getContext(), CookbookActivity.class);
				rootView.getContext().startActivity(i);

			}
		});

		return rootView;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	public void onClickInfo(View v)	{
		
		info2 = (TextView) v.findViewById(R.id.textInfo2);
		info2.setText("Нажата кнопка info, Шурик все поченил");
		
	    // выводим сообщение
	    Toast.makeText(v.getContext(), "Зачем вы нажали?", Toast.LENGTH_SHORT).show();
	    
	    
	    
	} 
	
}
