package cn.mmdata.createcode;

import codeGenerate.def.FtlDef;
import codeGenerate.factory.CodeGenerateFactory;
/**
 * 
 * @author lintu5.com
 *
 */

public class CodeUtil {

	public static void main(String[] args) {
		config();
	}

	private static void config() {
		
		/** 此处修改成你的 表名 和 中文注释***/
		 String tableName="m_minute_data";//	
		 String codeName ="明细数据";//中文注释  当然你用英文也是可以的
		 String entityPackage ="m_minute_data";//实体包
		 String controllerEntityPackage = "m_minute_data";//controller包文件夹
		 String keyType = FtlDef.KEY_TYPE_02;//主键生成方式 01:UUID  02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName,entityPackage,controllerEntityPackage,keyType);
	}
	
	
}
