package test;

import java.lang.reflect.Method;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import servlet.HelloServlet;

/**
 * ʹ��DOM4J����myServlet.xml,ͨ������run����ִ��
 * @author yangyuan
 *
 */


public class Demo {
    
	@Test
	public void run() throws Exception {
		
		//����myServlet.xml
		//��ȡ������
		SAXReader reader = new SAXReader();
		//����
		Document document = reader.read("src/myServlet.xml");
		//��ȡ���ڵ�
		Element root = document.getRootElement();
		//��ȡservlet�ڵ�
	    Element servlet = root.element("servlet");
	    Element servletClass = servlet.element("servlet-class");
	    //��ȡ����+������ȫ·��
	    String path = servletClass.getText();
	    
	    //��ȡclass����
	    Class clazz = Class.forName(path);
	    //��ȡʵ��
	    HelloServlet hello = (HelloServlet) clazz.newInstance();
	    
	    //��ȡ����
	    Method m = clazz.getDeclaredMethod("run");
	    m.setAccessible(true);
	    m.invoke(hello);
		
	}
}
