package com.hungrycookbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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

		Button menu_button = (Button) rootView.findViewById(R.id.button_cookbook);
		menu_button.setOnClickListener(new OnClickListener() {
			public void onClick(View button) {
				Toast.makeText(rootView.getContext(), "You clicked the button",
						Toast.LENGTH_SHORT).show();
			}
		});
		
		//Button info_button = (Button) rootView.findViewById(R.id.button_info);
		//final TextView textInfo2 = (TextView) rootView.findViewById(R.id.textInfo2);
		//info_button.setOnClickListener(new OnClickListener() {
		
			//public void onClick(View button) {
					//textInfo2.setText("Нажата кнопка info");				
			//}
		//});
		
		return inflater.inflate(R.layout.fragment_menu, container, false);
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
