package com.martian.android.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

/**
 * 
 * @author Maritan_He
 * 
 * ����һ������ģʽ���ɵĶ���
 * Ŀ���ǣ�Ӧ�������ڴ�����ã��������ܴ��ã���˽������б����ڵ�����ɱ�������һֱ����
 * ����activity��fragment������������ô�仯
 */
public class CrimeLab {

	private ArrayList<Crime> mCrimes;
	private static CrimeLab sCrimeLab;
	private Context mAppContext;
	
	//��Context�������������������activity����ȡ��Ŀ��Դ������Ӧ�õ�˽�д洢�ռ������
	private CrimeLab(Context appContext) {
		mAppContext = appContext;
		mCrimes = new ArrayList<Crime>();
		//ģ�����100������
		for(int i = 0;i<100;i++) {
			Crime c = new Crime();
			c.setTitle("crime # " + i);
			c.setSolved(i % 2 == 0);
			mCrimes.add(c);
		}
	}
	
	public static CrimeLab getInstace(Context c) {
		if(sCrimeLab == null) {
			//���ﲢδֱ��ʹ��Context����Ϊ���ǲ�֪�������Context����������Ҫ��
			//����Ϊ��֤����������Context���ã��͵���getApplicationContext()�������õ�Application Context
			//�κ�ʱ��ֻҪ��Ӧ�ò�ĵ�������Ӧ��һֱʹ��application context
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
