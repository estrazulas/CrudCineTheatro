package com.rhcloud.pugmg.cinetheatrosys.controle.uteis.paginacao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenericDataTables<T> extends DataTables<T> 
{

	public GenericDataTables(List<String> atributosNomes){
		this.setAtributosNomes(atributosNomes);
	}

	public GenericDataTables(List<T> t, long iTotalRecords,
			long iTotalDisplayRecords, String sSearch, String sEcho, List<String> atributosNomes) 
	{
		super(t, iTotalRecords, iTotalDisplayRecords, sSearch, sEcho,atributosNomes);
	}
	
	@Override
	protected List<String> getAttributes(T objeto)
	{
		ArrayList<String> valores = new ArrayList<String>();
		
		for (Iterator<String> iterator = this.getAtributosNomes().iterator(); iterator.hasNext();) {
			String nomePropriedade = (String) iterator.next();
			Class<? extends Object> classeObj = objeto.getClass();
			
			Field[] declaredFields = classeObj.getDeclaredFields();
			
	        for(Field f:declaredFields){
	        	try {
	        		f.setAccessible(true);
	        		if(f.getName().equals(nomePropriedade)){
	        			Object obj = f.get(objeto);
	        			
	        			String value ="-";
	        			
	        			if(obj !=null)
	        				value = f.get(objeto).toString();
	        			
	        			valores.add(value);
	        		}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					f.setAccessible(false);
				}
	        	
	        }
			
		}
		
		
		return valores;
	}
	
	@Override
	public String getColumnNameById(int id)
	{
		return this.getAtributosNomes().get(id);
	}
}