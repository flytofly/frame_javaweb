<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/css/font-awesome.min.css" />
		<!-- 下拉框 -->
		<link rel="stylesheet" href="static/css/chosen.css" />
		
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 -->
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
<script type="text/javascript">
	
	
	//保存
	function save(){
			if($("#phone").val()==""){
			$("#phone").tips({
				side:3,
	            msg:'请输入手机号码',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#phone").focus();
			return false;
		}
		if($("#passwd").val()==""){
			$("#passwd").tips({
				side:3,
	            msg:'请输入密码',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#passwd").focus();
			return false;
		}
		if($("#username").val()==""){
			$("#username").tips({
				side:3,
	            msg:'请输入姓名',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#username").focus();
			return false;
		}
		if($("#company").val()==""){
			$("#company").tips({
				side:3,
	            msg:'请输入所属公司',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#company").focus();
			return false;
		}
		if($("#industry").val()==""){
			$("#industry").tips({
				side:3,
	            msg:'请输入所属行业',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#industry").focus();
			return false;
		}
		if($("#regDate").val()==""){
			$("#regDate").tips({
				side:3,
	            msg:'请输入注册时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#regDate").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="muser/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="id" id="id" value="${pd.id}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">手机号码:</td>
				<td><input type="text" name="phone" id="phone" value="${pd.phone}" maxlength="32" placeholder="这里输入手机号码" title="手机号码"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">密码:</td>
				<td><input type="text" name="passwd" id="passwd" value="${pd.passwd}" maxlength="32" placeholder="这里输入密码" title="密码"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">姓名:</td>
				<td><input type="text" name="username" id="username" value="${pd.username}" maxlength="32" placeholder="这里输入姓名" title="姓名"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">所属公司:</td>
				<td><input type="text" name="company" id="company" value="${pd.company}" maxlength="32" placeholder="这里输入所属公司" title="所属公司"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">所属行业:</td>
				<td><input type="text" name="industry" id="industry" value="${pd.industry}" maxlength="32" placeholder="这里输入所属行业" title="所属行业"/></td>
			</tr>
 
			<tr>
				<td style="text-align: center;" colspan="10">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		
	</form>
	
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});
		
		</script>
</body>
</html>