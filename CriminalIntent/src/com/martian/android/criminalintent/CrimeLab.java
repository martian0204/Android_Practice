package com.martian.android.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

/**
 * 
 * @author Maritan_He
 * 
 * 这是一个单利模式生成的对象
 * 目的是：应用能在内存里存多久，单利就能存多久，因此将对象列表保存在单利里可保持数据一直存在
 * 无论activity和fragment的生命周期怎么变化
 */
public class CrimeLab {

	private ArrayList<Crime> mCrimes;
	private static CrimeLab sCrimeLab;
	private Context mAppContext;
	
	//用Context参数，单利可完成启动activity、获取项目资源，查找应用的私有存储空间等任务
	private CrimeLab(Context appContext) {
		mAppContext = appContext;
		mCrimes = new ArrayList<Crime>();
		//模拟产生100个数据
		for(int i = 0;i<100;i++) {
			Crime c = new Crime();
			c.setTitle("crime # " + i);
			c.setSolved(i % 2 == 0);
			mCrimes.add(c);
		}
	}
	
	public static CrimeLab getInstace(Context c) {
		if(sCrimeLab == null) {
			//这里并未直接使用Context，因为我们不知道，这个Context就是我们需要的
			//所以为保证单利总是有Context可用，就调用getApplicationContext()方法，得到Application Context
			//任何时候只要是应用层的单利，就应该一直使用application context
			sCrimeLab = new CrimeLab(c.getApplicationContext());
		}
		return sCrimeLab;
	}
	
	public ArrayList<Crime> getCrimes() {
		return mCrimes;
	}
	
	public Crime getCrime(UUID id) {
		for(Crime c : mCrimes) {
			if(c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}

}
