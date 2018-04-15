package com.silin.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpServletRequest = (HttpServletRequest)arg0;
		HttpServletResponse httpServletResponse = (HttpServletResponse)arg1;
		//编码设置
		httpServletRequest.setCharacterEncoding("utf-8");
		//创建自定义request
		MyRequest myRequest = new MyRequest(httpServletRequest); 
		//放行
		arg2.doFilter(myRequest, arg1);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
//自定义request对象
class MyRequest extends HttpServletRequestWrapper{
	private boolean encoded = false;
	private HttpServletRequest request;
	private boolean hasEncode;
	public MyRequest(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
		this.request =request;
	}
	public Map<String,String[]> getParameterMap(){
		try{
			//获取原始数据
			Map<String,String[]> map = super.getParameterMap();
			//入过是get请求，存放栏目
			
			if(!encoded){
				if("GET".equalsIgnoreCase(super.getMethod())){
					//遍历map,并遍历数组值
					for(Map.Entry<String,String[]> entry :map.entrySet()){
						String[] allValue = entry.getValue();
						for(int i = 0; i < allValue.length; i++){
							String encoding = super.getCharacterEncoding();
							if(encoding == null){
								encoding = "utf-8";
							}
							allValue[i] = new String(allValue[i].getBytes("ISO-8859-1"),encoding);
						}
					}
					encoded = true;
				}
			}
			return map;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
	}
	public String getParameter(String name){
		Map<String,String[]>parameterMap = getParameterMap();
		String[] values = parameterMap.get(name);
		if(values!= null){
			return null;
		}
		return values[0];
	}
	public String[] getParameterValues(String name){
		Map<String,String[]>parameterMap = getParameterMap();
		String[] values = parameterMap.get(name);
		return values;
	}
}
