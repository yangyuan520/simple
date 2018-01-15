package test;

import java.lang.reflect.Method;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import servlet.HelloServlet;

/**
 * 使用DOM4J解析myServlet.xml,通过反射run方法执行
 * @author yangyuan
 *
 */


public class Demo {
    
	@Test
	public void run() throws Exception {
		
		//解析myServlet.xml
		//获取解析器
		SAXReader reader = new SAXReader();
		//解析
		Document document = reader.read("src/myServlet.xml");
		//获取根节点
		Element root = document.getRootElement();
		//获取servlet节点
	    Element servlet = root.element("servlet");
	    Element servletClass = servlet.element("servlet-class");
	    //获取包名+类名的全路径
	    String path = servletClass.getText();
	    
	    //获取class对象
	    Class clazz = Class.forName(path);
	    //获取实例
	    HelloServlet hello = (HelloServlet) clazz.newInstance();
	    
	    //获取方法
	    Method m = clazz.getDeclaredMethod("run");
	    m.setAccessible(true);
	    m.invoke(hello);
		
	}
}
