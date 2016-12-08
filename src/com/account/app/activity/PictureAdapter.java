package com.account.app.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.account.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PictureAdapter extends BaseAdapter{

	private Context context ;
	private List<Picture> pictures = new ArrayList<Picture>();
	
	public PictureAdapter(String[] titles,int[] images,Context context){
		super() ;
		this.context = context ;
		
		for(int i=0;i<images.length;i++){
			Picture picture = new Picture(titles[i],images[i]);
			pictures.add(picture);
		}
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(null != pictures){
			return pictures.size() ;
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return pictures.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null ;
		if(convertView== null){
			holder = new ViewHolder() ;
			convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
			holder.image = (ImageView)convertView.findViewById(R.id.imageItem);
			holder.title = (TextView)convertView.findViewById(R.id.textItem);
			// 给converHolder附加一个对象
			convertView.setTag(holder);
		}else{
		    // 取得converHolder附加的对象
			holder = (ViewHolder)convertView.getTag();
		}
		
		// 给组件设置资源
		Picture picture = pictures.get(position);
		holder.image.setImageResource(picture.getImageId());
		holder.title.setText(picture.getTitle());
		
		return convertView ;
	}
	
	class Picture{
		private int imageId ;
		private String title ;
		
		public Picture(String title ,int imageId){
			this.imageId = imageId ;
			this.title = title ;
		}
		
		public void setTitle(String title){
			this.title = title ;
		}
		
		public String getTitle(){
			return title ;
		}
		
		public void setImageId(int imageId){
			this.imageId = imageId ;
		}
		
		public int getImageId(){
			return imageId ;
		}
	}
	
	//对控件实例进行缓存，不用每次 创建新的控件，提高效率
	class ViewHolder{
		public TextView title ;
		public ImageView image ;
	}

}
