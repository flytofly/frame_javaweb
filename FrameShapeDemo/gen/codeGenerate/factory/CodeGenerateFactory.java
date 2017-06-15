package codeGenerate.factory;

import cn.mmdata.dao.DaoSupport;
import cn.mmdata.entity.system.Menu;
import cn.mmdata.service.system.menu.MenuService;
import cn.mmdata.util.DelAllFile;
import cn.mmdata.util.FileDownload;
import cn.mmdata.util.FileZip;
import cn.mmdata.util.Freemarker;
import cn.mmdata.util.PageData;
import cn.mmdata.util.PathUtil;
import codeGenerate.ColumnData;
import codeGenerate.CommonPageParser;
import codeGenerate.CreateBean;
import codeGenerate.def.CodeResourceUtil;
import codeGenerate.def.TableConvert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CodeGenerateFactory {

	private static final Log log = LogFactory.getLog(CodeGenerateFactory.class);
	private static String url = CodeResourceUtil.URL;
	private static String username = CodeResourceUtil.USERNAME;
	private static String passWord = CodeResourceUtil.PASSWORD;

	private static String buss_package = CodeResourceUtil.bussiPackage;
	private static String projectPath = getProjectPath();

	public static void codeGenerate(String tableName, String codeName,
			String entityPackage, String controllerEntityPackage, String keyType) {

		CreateBean createBean = new CreateBean();
		createBean.setMysqlInfo(url, username, passWord);

		String className = createBean.getTablesNameToClassName(tableName);
		String lowerName = className.substring(0, 1).toLowerCase()
				+ className.substring(1, className.length());

		String srcPath = projectPath + CodeResourceUtil.source_root_package
				+ "/";
		String resourcesPath = projectPath
				+ CodeResourceUtil.resources_root_package + "/";

		String webPath = projectPath + CodeResourceUtil.web_root_package
				+ "/WEB-INF/";
		String entityPath = (entityPackage == null || "".equals(entityPackage)) ? ""
				: entityPackage + "\\";
		webPath = webPath + entityPath;
		//
		String jspPath = lowerName + ".jsp";

		VelocityContext context = new VelocityContext();
		context.put("className", className);
		context.put("lowerName", lowerName);
		context.put("codeName", codeName);
		context.put("tableName", tableName);
		context.put("bussPackage", buss_package);
		context.put("entityPackage", entityPackage == "" ? null : entityPackage);
		context.put("controllerEntityPackage",
				controllerEntityPackage == "" ? null : controllerEntityPackage);
		context.put("keyType", keyType);

		Map<String, Object> root = new HashMap<String, Object>(); // 创建数据模型
		root.put("fieldList", createBean.getBeanFeildsList(tableName));
		root.put("packageName", entityPackage); // 包名,这个包名属于哪个包名?
		root.put("objectName", className); // 类名
		root.put("objectNameLower", className.toLowerCase()); // 类名(全小写)
		root.put("objectNameUpper", className.toUpperCase()); // 类名(全大写)
		root.put("tabletop", "m_"); // 表前缀
		root.put("nowDate", new Date()); // 当前日期
		root.put("tableName", tableName);

		// DelAllFile.delFolder(PathUtil.getClasspath()+"admin/ftl");
		// //生成代码前,先清空之前生成的代码
		/*
		 * ======================================================================
		 * =======================
		 */

		String filePath = srcPath + CodeResourceUtil.bussiPackageUrl + "/"; // 存放路径
		String mapFilePath = resourcesPath + "/mybatis" + "/"; // 存放路径
		String ftlPath = "createCode"; // ftl路径

		String packageName = entityPackage;
		String objectName = className;

		try {
			/* 生成controller */
			// Freemarker.printFile("controllerTemplate.ftl", root,
			// "controller/"+packageName+"/"+objectName.toLowerCase()+"/"+objectName+"Controller.java",
			// filePath, ftlPath);
			Freemarker.printFile("controllerTemplate.ftl", root, "controller/"
					+ packageName + "/" + objectName + "Controller.java",
					filePath, ftlPath);
			/* 生成service */
			Freemarker.printFile("serviceTemplate.ftl", root, "service/"
					+ packageName + "/" + objectName + "Service.java",
					filePath, ftlPath);

			Freemarker.printFile("mapperMysqlTemplate.ftl", root, packageName
					+ "/" + objectName + "Mapper.xml", mapFilePath, ftlPath);
			
			Freemarker.printFile("jsp_list_Template.ftl", root, "jsp/"
					+ packageName + "/" + objectName.toLowerCase()
					+ "_list.jsp", webPath, ftlPath);
			
			Freemarker.printFile("jsp_edit_Template.ftl", root, "jsp/"
					+ packageName + "/" + objectName.toLowerCase()
					+ "_edit.jsp", webPath, ftlPath);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/**
		Menu menu = new Menu();
		try {
			menu.setMENU_ID("100");
			menu.setMENU_NAME("test");
			menu.setMENU_URL("test");
			menu.setPARENT_ID("26");
			menu.setMENU_ORDER("1");
			menu.setMENU_ICON("icon-list");
			menu.setMENU_TYPE("2");
			createMenu(menu);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
**/
		// ************************************************************************************************//
		//
		// CommonPageParser.WriterPage(context, "EntityTemplate.ftl", pckPath,
		// beanPath);
		// CommonPageParser.WriterPage(context, "DaoTemplate.ftl", pckPath,
		// mapperPath);
		// CommonPageParser.WriterPage(context, "ServiceTemplate.ftl", pckPath,
		// servicePath);
		// CommonPageParser.WriterPage(context, "ServiceImplTemplate.ftl",
		// pckPath, serviceImplPath);
		// CommonPageParser.WriterPage(context, "MapperTemplate.xml", pckPath,
		// sqlMapperPath);
		// CommonPageParser.WriterPage(context, "ControllerTemplate.ftl",
		// pckPath, controllerPath);

		// CommonPageParser.WriterPage(context, "jspTemplate.ftl", webPath,
		// jspPath);

		log.info("----------------------------\u4EE3\u7801\u751F\u6210\u5B8C\u6BD5---------------------------");
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, passWord);
	}

	public static void createMenu(Menu menu) throws SQLException {
		String sql = "insert into SYS_MENU (MENU_NAME,MENU_URL,PARENT_ID,MENU_ORDER,MENU_TYPE) "
				+ "values ('"
				+ menu.getMENU_NAME()
				+ "','"
				+ menu.getMENU_URL()
				+ "',"
				+ menu.getPARENT_ID()
				+ ","
				+ menu.getMENU_ORDER() + "," + menu.getMENU_TYPE() + ")";
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.executeUpdate();
		ps.close();
		con.close();
	}

	public static String getProjectPath() {
		String path = System.getProperty("user.dir").replace("\\", "/") + "/";
		return path;
	}
}