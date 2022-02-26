<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!-- 将  HTML 页面转换为 jsp 页面-->
<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>云R记</title>
    <link href="statics/css/login.css" rel="stylesheet" type="text/css" />
    <script src="statics/js/jquery-1.11.3.js" type=text/javascript></script>
    <!-- 引入判断字符串是否为空的方法 以便在 config.js 中直接调用进行判断用户名和密码是否为空-->
    <script src="statics/js/util.js" type=text/javascript></script>
    <!-- config.js 作用： 切换首页轮播图 -->
    <script src="statics/js/config.js" type=text/javascript></script>
</head>
<body>
<!--head-->
<div id="head">
    <div class="top">
        <div class="fl yahei18">开启移动办公新时代！</div>
    </div>
</div>

<!--login box-->
<div class="wrapper">
    <div id="box">
        <div class="loginbar">用户登录</div>
        <div id="tabcon">
            <div class="box show">
                <!-- 添加表单 form,记录登录信息 ,并进行检验 :若不为空 则登录成功  -->
                <%-- actionName表示用户行为，通过这个参数可以在UserServlet中判断用户当前想要操作的功能 --%>
                <form action="user" method="post" id = "loginForm">
                    <!-- 传入参数actionName 区分用户行为是登录/退出/显示头像... -->
                    <input type = "hidden" name="actionName" value="login"/>
                    <!-- 传入 name 属性 -->
                    <input type="text" class="user yahei16" id="userName" name="userName" value="${resultInfo.result.uname}" /><br /><br />
                    <!-- 传入 name 属性 -->
                    <input type="password" class="pwd yahei16" id="userPwd" name="userPwd" value="${resultInfo.result.upwd}" /><br /><br />
                    <!-- 复选框 设置 value 值为 1, 勾选即可获得这个value值 -->
                    <input name="rem" type="checkbox" value="1"  class="inputcheckbox"/> <label>记住我</label>&nbsp; &nbsp;
                    <!-- 登录按钮点击之后，登录信息未提交成功，出现提示信息-->
                    <span id="msg" style="color: red;font-size: 12px;">${resultInfo.msg}</span><br /><br />
                    <!-- 登录按钮调用checkLogin()方法 进行检查-->
                    <input type="button" class="log jc yahei16" value="登 录" onclick="checkLogin()" />&nbsp; &nbsp; &nbsp; <input type="reset" value="取 消" class="reg jc yahei18" />
                </form>
            </div>
        </div>
    </div>
</div>

<div id="flash">
    <div class="pos">
        <a bgUrl="statics/images/banner-bg1.jpg" id="flash1" style="display:block;"><img src="statics/images/banner_pic1.png"></a>
        <a bgUrl="statics/images/banner-bg2.jpg" id="flash2"                       ><img src="statics/images/banner-pic2.jpg"></a>
    </div>
    <div class="flash_bar">
        <div class="dq" id="f1" onclick="changeflash(1)"></div>
        <div class="no" id="f2" onclick="changeflash(2)"></div>
    </div>
</div>

<!--bottom-->
<div id="bottom">
    <div id="copyright">
        <div class="quick">
            <ul>
                <li><input type="button" class="quickbd iphone" onclick="location.href='https://leetcode-cn.com/'" /></li>
                <li><input type="button" class="quickbd android" onclick="location.href='https://leetcode-cn.com/'" /></li>
                <li><input type="button" class="quickbd pc" onclick="location.href='https://leetcode-cn.com/'" /></li>
                <div class="clr"></div>
            </ul>
            <div class="clr"></div>
        </div>
        <div class="text">
            Copyright © 2021-2024  <a href="https://leetcode-cn.com/">「fosu_20bigdata_lbj」</a>  All Rights Reserved
        </div>
    </div>
</div>
</body>

</html>

