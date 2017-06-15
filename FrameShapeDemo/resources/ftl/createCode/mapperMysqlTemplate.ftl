<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${objectName}Mapper">
	
	
	<!-- 新增-->
	<insert id="save" parameterType="pd">
		insert into ${tableName}(
	<#list fieldList as var>
	<#if var[0]!="id">
			${var[0]}<#if var_has_next>,</#if>
	</#if>
	</#list>			
		) values (
	<#list fieldList as var>
	<#if var[0]!="id">
			${r"#{"}${var[0]}${r"}"}
		<#if var_has_next>
		,
		</#if>	
	</#if>	
	</#list>
			 
		)
	</insert>
	
	
	<!-- 删除-->
	<delete id="delete" parameterType="pd">
		delete from ${tableName}
		where 
			id = ${r"#{"}id${r"}"}
	</delete>
	
	
	<!-- 修改 -->
	<update id="edit" parameterType="pd">
		update  ${tableName}
			set 
	<#list fieldList as var>
	<#if var[0]!="id">
		<#if var[3] == "是">
				${var[0]} = ${r"#{"}${var[0]}${r"}"}
		</#if>
		<#if var_has_next>
		,
		</#if>
	</#if>	
	</#list>
			
			where 
				id = ${r"#{"}id${r"}"}
	</update>
	
	
	<!-- 通过ID获取数据 -->
	<select id="findById" parameterType="pd" resultType="pd">
		select 
	<#list fieldList as var>
			${var[0]}
		<#if var_has_next>
		,
		</#if>	
	</#list>			 
		from 
			${tableName}
		where 
			id = ${r"#{"}id${r"}"}
	</select>
	
	
	<!-- 列表 -->
	<select id="datalistPage" parameterType="page" resultType="pd">
		select
		<#list fieldList as var>
				a.${var[0]}
	   <#if var_has_next>
		,
		</#if>
		</#list>
				 
		from 
				${tableName} a
	</select>
	
	<!-- 列表(全部) -->
	<select id="listAll" parameterType="pd" resultType="pd">
		select
		<#list fieldList as var>
				a.${var[0]}
		<#if var_has_next>
		,
		</#if>	
		</#list>
				 
		from 
				${tableName} a
	</select>
	
	<!-- 批量删除 -->
	<delete id="deleteAll" parameterType="String">
		delete from ${tableName}
		where 
			id in
		<foreach item="item" index="index" collection="array" open="(" separator="," close=")">
                 ${r"#{item}"}
		</foreach>
	</delete>
	
</mapper>