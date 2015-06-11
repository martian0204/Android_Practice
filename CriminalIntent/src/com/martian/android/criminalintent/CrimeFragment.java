package com.martian.android.criminalintent;

import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class CrimeFragment extends Fragment{

	public static final String EXTRA_CRIME_ID = "com.martian.android.criminalintent.crime_id";
	
	private Crime mCrime;
	private EditText mTitleField;
	private Button mDateButton;
	private CheckBox mSolvedCheckBox;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
		mCrime = CrimeLab.getInstace(getActivity()).getCrime(crimeId);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//fragment视图是直接调用LayoutInflater.inflater(...)方法并传入布局的资源ID生成的
		View v = inflater.inflate(R.layout.fragment_crime, container,false);//第一个参数：布局的资源ID，第二个参数：视图的父视图，通常我们需要父视图来正确的配置组件
																			//第三个参数：告知布局生成器是否将生成的视图添加给父视图
		
		mTitleField = (EditText)v.findViewById(R.id.crime_title);
		mTitleField.setText(mCrime.getTitle());
		mTitleField.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//CharSequence 参数代表用户输入
				mCrime.setTitle(s.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO 
			}
		});
		
		mDateButton = (Button) v.findViewById(R.id.crime_date);
		mDateButton.setText(DateFormat.format("EEEE, MMM dd, yyyy", mCrime.getDate()));
		mDateButton.setEnabled(false);
		
		mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
		mSolvedCheckBox.setChecked(mCrime.isSolved());
		mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				mCrime.setSolved(isChecked);
			}
		});
		
		return v; 
	}
	
	public static CrimeFragment newInstance(UUID crimeId) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_CRIME_ID, crimeId);
		CrimeFragment fragment = new CrimeFragment();
		fragment.setArguments(args);//该任务必须在fragment创建后，添加给Activity之间完成
		return fragment;
	}
}
