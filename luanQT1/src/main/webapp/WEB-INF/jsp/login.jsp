<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<div class="login-main">
    <header class="layui-elip">登录</header>
    <form id="formLogin" class="layui-form">
        <div class="layui-input-inline">
            <input type="text" name="username" required lay-verify="required" placeholder=用户名" autocomplete="off"
                   class="layui-input">
        </div>
        <div class="layui-input-inline">
            <input type="password" name="password" required lay-verify="required" placeholder="密码" autocomplete="off"
                   class="layui-input">
        </div>
        <div class="layui-input-inline login-btn">
            <!-- <button type="submit" class="layui-btn">登录</button> -->
            <button lay-submit class="layui-btn" lay-filter="loginButton">登录</button>

        </div>
        <hr/>
        <p><a href="javascript:;" class="fl">立即注册</a><a href="javascript:;" class="fr">忘记密码？</a></p>
    </form>
</div>
</body>
<script type="text/javascript">
	layui.use(['form','layer'],function(){
		var form = layui.form;
		var layer = layui.layer;
		var $ = layui.$;
		
		form.on('submit(loginButton)', function(data){
			$.ajax({
				url:'${ctx}/login.do',
				data:$('#formLogin').serialize(), //username=1111&password=123   {username:1111,password:123}
				type:'post',
				dataType:'json',
				success:function(obj){
					if(obj.success){
                            window.location.href="${ctx}/"+obj.url;
					}else{
					layer.msg(obj.msg);						
					}
				}
			})
			return false;
		});

	});

</script>
</html>
