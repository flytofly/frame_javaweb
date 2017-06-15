package cn.mmdata.service.invitation_code;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mmdata.dao.DaoSupport;
import cn.mmdata.entity.Page;
import cn.mmdata.util.PageData;


@Service("invitation_codeService")
public class Invitation_codeService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("Invitation_codeMapper.save", pd);
	}
	/*
	* 批量新增
	*/
	public void saveBatch(List list)throws Exception{
		dao.save("Invitation_codeMapper.saveBatch",list);
	}
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("Invitation_codeMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("Invitation_codeMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("Invitation_codeMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("Invitation_codeMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("Invitation_codeMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("Invitation_codeMapper.deleteAll", ArrayDATA_IDS);
	}
	
	private static char randomChar(){
		Random r = new Random();
		String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		return s.charAt(r.nextInt(s.length()));
	}
	public static String getCode(int len){	
		String code="";
		for(int i=0;i<len;i++){
			code+=randomChar();
		}
		return code;
	}
	
	public static void main(String[] args) {
		System.out.println(getCode(6));
	}
	
}

