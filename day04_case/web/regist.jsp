
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DoctType html>
<html>
    <head>
        <meta charset="utf-8">
        <title>welcome to Joy's company</title>
        <link rel = "stylesheet" href="./css/Joystyle.css">

    </head>
	
	<script type="text/javascript">
        window.onload = function () {
            var t = document.getElementById("time");
            function time() {
                var date = new Date();
                var hours = date.getHours();
                var minutes = date.getMinutes();
                var seconds = date.getSeconds();
                if (seconds<10){
                    t.innerHTML = "当前时间："+hours+":"+minutes+":0"+seconds;
                }else {
                    t.innerHTML = "当前时间："+hours+":"+minutes+":"+seconds;
                }

            }
            setInterval(time,1000);
        }
        function vilidate_username(uname){
            // var uname=document.getElementById("username").value;
           
            //定义正则表达式：邮箱正则
            var emailReg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
            //console.log(username)
            if(uname!=""&&uname.search(emailReg)!=-1){
           
                document.getElementById("test_user").innerHTML = 
                "<font color = 'blue' size='5px'>✔</font>";
            }else{
                document.getElementById("test_user").innerHTML= "<font color='red' size='2px'>邮箱格式错误</font>";
    
                     }
        }
        function vilidate_password(password){
            //六位密码由数字和字母组成
            var passwordReg =/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6}$/;
            if(password != ""&& password.search(passwordReg)!=-1){
                document.getElementById("test_pw").innerHTML="<font color='yellow' size='5px'>√</font>";
    
            }else{
                  document.getElementById("test_pw").innerHTML = "<font color='red' size='2px'>密码格式错误</font>";
                  alert("密码有6位，由数字和字母组成!");
              }
        }
        function vilidate_password2(password2){
	
            var password=document.getElementById("password").value;
            //测试
            if(password == ""){
                document.getElementById("is_test_pw").innerHTML ="<font color='red' size='3px'>密码不为空</font>";
    
            }else if(password==password2)
            {
                document.getElementById("is_test_pw").innerHTML="<font color='green' size='5px'>√</font>";
    
            }else{
                  document.getElementById("is_test_pw").innerHTML = "<font color='red' size='1px'>两次输入的密码不相同</font>";
                  console.log("密码有6位，由数字和字母组成!");
              }
    
        }
        //函数：验证表单是否已经填好
        function validate_form(){
              var username = document.getElementById("username").value;
              var password = document.getElementById("password").value;
              var password2 = document.getElementById("repassword").value;
              //console.log("表单填写正确，可以正常提交！");
          
              //这三个，如果任何一个有问题，都返回false
              //18128@qq.com		12345y
              var emailReg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
              var passwordReg=/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6}$/;
              
              if(username != "" && emailReg.test(username)){
                  if(password !="" && passwordReg.test(password)){
                      if(password2==password){
                          alert("信息填写正确，可以正常提交！");
                          console.log("信息填写正确，可以正常提交！");
                          return true;
                      }else{
                          alert("密码不一致，提交失败，请重新填写！");
                          console.log("密码不一致，提交失败，请重新填写！");
                          return false;
                      }
                  }else{
                      alert("密码格式错误，提交失败，请重新填写！");
                      console.log("密码格式错误，提交失败，请重新填写！");
                      return false;
                  }
              }else{
                  alert("注册的账号不符合要求，提交失败，请重新填写！");
                  console.log("注册的账号不符合要求，提交失败，请重新填写！");
                  return false;
              }
          }
    
        </script>
    <body>
        <div class="box">
            <h2>注册</h2>
            <form action="${pageContext.request.contextPath}/addManagerServlet" method="post">
                <div class="InputBox" >
                    <input type="text" id="showName" name="showName" required=""
                           placeholder="" />

                    <span id="test_manager"></span>
                    <label for="">MANAGER</label>

                </div>
                <div class="InputBox" >
                        <input type="text" id="username" name="username" required=""
                         placeholder="只能用邮箱注册" onblur="vilidate_username(this.value)"/>
                         
                        <span id="test_user"></span>
                    <label for="">USERNAME</label>
                    
                    
                </div>
                <div class="InputBox" >
                        
                        <input type="password" id="password" name="password" required=""
                        placeholder="6位密码由数字和字母组成" onblur="vilidate_password(this.value)"/>
                        <span id="test_pw"></span>
                        <label for="">PASSWORD</label>
                </div>
				<div class="InputBox" >
                        
                        <input type="password" id="repassword" name="repassword" required=""
                        placeholder="请确认密码" onblur="vilidate_password2(this.value)"/>
                        <span id="is_test_pw"></span>
                        <label for="">REPASSWORD</label>
                </div>
                
            <input type="submit" name=""id="submit_form" value="注册" οnclick="return validate_form()"/>

            
            <div class="regist">
        <a href="login.jsp" >登录</a>
        </div>

            </form>
        </div>
    <div style="position: absolute;right: 30px;top: 850px">
        <span id="time" style="color: #2b669a">当前时间：</span>
    </div>

    </body>
</html>