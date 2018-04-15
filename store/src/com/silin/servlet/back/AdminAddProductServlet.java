package com.silin.servlet.back;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.silin.domain.Category;
import com.silin.domain.Product;
import com.silin.service.AdminService;
import com.silin.utils.UUIDUtils;

public class AdminAddProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Ŀ�ģ��ռ��������� ��װһ��Productʵ�� ���ϴ�ͼƬ�浽������������
		
				Product product = new Product();
				
				//�ռ����ݵ�����
				Map<String,Object> map = new HashMap<String,Object>();
				
				try {
					//���������ļ����
					DiskFileItemFactory factory = new DiskFileItemFactory();
					//�����ļ��ϴ����Ķ���
					ServletFileUpload upload = new ServletFileUpload(factory);
					//����request����ļ�����󼯺�

					List<FileItem> parseRequest = upload.parseRequest(request);
					for(FileItem item : parseRequest){
						//�ж��Ƿ�����ͨ����
						boolean formField = item.isFormField();
						if(formField){
							//��ͨ���� ��ñ������� ��װ��Productʵ����
							String fieldName = item.getFieldName();
							String fieldValue = item.getString("UTF-8");
							
							map.put(fieldName, fieldValue);
							
						}else{
							//�ļ��ϴ��� ����ļ����� ����ļ�������
							String fileName = item.getName();
							String path = this.getServletContext().getRealPath("upload");
							InputStream in = item.getInputStream();
							OutputStream out = new FileOutputStream(path+"/"+fileName);//I:/xxx/xx/xxx/xxx.jpg
							IOUtils.copy(in, out);
							in.close();
							out.close();
							item.delete();
							
							map.put("pimage", "upload/"+fileName);
							
						}
						
					}
			BeanUtils.populate(product, map);
			//�Ƿ�product�����װ������ȫ
			//private String pid;
			product.setPid(UUIDUtils.getUUID());
			//private Date pdate;
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dateTime = time.parse(time.format(new Date()));
			product.setPdate(dateTime);
			//private int pflag;
			product.setPflag(0);
			//private Category category;
			Category category = new Category();
			category.setCid(map.get("cid").toString());
			product.setCategory(category);
			
			//��product���ݸ�service��
			AdminService service = new AdminService();
			service.saveProduct(product);
			
		} catch (FileUploadException | IllegalAccessException | InvocationTargetException | SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
