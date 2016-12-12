package com.account.app.util;

import com.account.app.activity.AddInaccount;
import com.account.app.activity.AddOutaccount;
import com.account.app.activity.InfoManage;
import com.example.account.R;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MyDialogFragment extends DialogFragment{

	public static final int DATE_PICKER_DIALOG = 1 ;
	public static final int ALERT_DIALOG = 2 ;
	public static final int TIME_PICKER_DIALOG = 3 ;
	public static final int ALERT_TIME_DIALOG = 4 ;
	public static final int OUTDATE_PICKER_DIALOG = 5 ;
	
	public static MyDialogFragment newInstance(int title){
		MyDialogFragment myDialogFragment = new MyDialogFragment();
		Bundle bundle = new Bundle() ;
		bundle.putInt("title", title);
		myDialogFragment.setArguments(bundle);
		return myDialogFragment ;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		int args = getArguments().getInt("title");
		switch(args){
		
		//为AddInaccount弹出时间选择对话框
		case DATE_PICKER_DIALOG:
			return new DatePickerDialog(getActivity(), new OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
					// TODO Auto-generated method stub
					//System.out.println("year-->"+year + " month-->"+monthOfYear+" y-->"+dayOfMonth);
					//为AddInaccount中年月日赋值
					AddInaccount.inYear = year ;
					AddInaccount.inMonth = monthOfYear; 
					AddInaccount.inDay = dayOfMonth ;
					AddInaccount.updateDisplay();
					
				}
			},AddInaccount.inYear,AddInaccount.inMonth,AddInaccount.inDay);
			
		//为InfoManage弹出时间选择对话框
		case OUTDATE_PICKER_DIALOG:
			return new DatePickerDialog(getActivity(), new OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
					// TODO Auto-generated method stub
					//System.out.println("year-->"+year + " month-->"+monthOfYear+" y-->"+dayOfMonth);
					//为InfoManage中年月日赋值
					AddOutaccount.outYear = year ;
					AddOutaccount.outMonth = monthOfYear; 
					AddOutaccount.outDay = dayOfMonth ;
					AddOutaccount.updateDisplay();
					
				}
			},AddOutaccount.outYear,AddOutaccount.outMonth,AddOutaccount.outDay);
			
		//为OutInfoManage弹出时间选择对话框
		case ALERT_TIME_DIALOG:
			return new DatePickerDialog(getActivity(), new OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
					// TODO Auto-generated method stub
					//System.out.println("year-->"+year + " month-->"+monthOfYear+" y-->"+dayOfMonth);
					//为InfoManage中年月日赋值
					InfoManage.mYear = year ;
					InfoManage.mMonth = monthOfYear; 
					InfoManage.mDay = dayOfMonth ;
					InfoManage.updateDisplay();
					
				}
			},InfoManage.mYear,InfoManage.mMonth,InfoManage.mDay);			
			
		case ALERT_DIALOG:
			return new AlertDialog.Builder(getActivity()).setIcon(R.drawable.ic_launcher)
														 .setTitle(getTag())
														 .setPositiveButton("OK", new OnClickListener() {
															
															@Override
															public void onClick(DialogInterface dialog, int which) {
																// TODO Auto-generated method stub
																Toast.makeText(getActivity(), "you click the ok", Toast.LENGTH_SHORT).show();
															}
														})
														 .setNegativeButton("CANCAEL", new OnClickListener() {
															
															@Override
															public void onClick(DialogInterface dialog, int which) {
																// TODO Auto-generated method stub
																Toast.makeText(getActivity(), "you click the cancel", Toast.LENGTH_SHORT).show();
															}
														})
														 .create();
		case TIME_PICKER_DIALOG:
			return new TimePickerDialog(getActivity(), new OnTimeSetListener() {
				
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					// TODO Auto-generated method stub
					System.out.println("hour-->"+hourOfDay+" minute-->"+minute);
				}
			}, 13, 23, true);
			
		}
		return null ;
	}
}

























