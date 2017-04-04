<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
   <head>
      <title>秒杀详情页面</title>
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <!-- 引入 Bootstrap -->
       <link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
       <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
       <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
   </head>
   <body>
        <div class="container">
            <div class="panel panel-default text-center">
                <div class="panel-heading">
                    <h1>${seckill.name}</h1>
                </div>
            </div>
            <div class="panel-body">
                <h2 class="text-danger">
                    <!--显示time图标-->
                    <span class="glyphicon glyphicon-time"></span>

                    <!--展示倒计时-->
                    <span class="glyphicon" id="seckill-box"></span>
                    <%--<button type="button" class="btn btn-primary btn-lg btn-block"></button>--%>
                    <%--<a class="btn btn-primary btn-lg btn-block" href="/seckill/${seckill.seckillId}/expser" target="_blank">点我秒杀</a>--%>
                </h2>
            </div>
        </div>
        <div id="killPhoneModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3 class="modal-title text-center">
                            <span class="glyphicon glyphicon-phone"></span>秒杀电话
                        </h3>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-xs-8 col-xs-offset-2">
                                <input type="text" name="killPhone" id="killPhoneKey"
                                        placeholder="手机号" class="form-control" />
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <span id="killPhoneMessage" class="glyphicon"></span>
                        <button type="button" id="killPhoneBtn" class="btn btn-success">
                            <span class="glyphicon glyphicon-phone"></span>
                            Submit
                        </button>
                    </div>
                </div>
            </div>
        </div>

   </body>
   <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
       <script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>

       <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
       <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>

       <script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>

       <script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
       <script src="/resources/script/seckill.js" type="text/javascript"></script>

       <script type="text/javascript">
           $(function(){
               seckill.detail.init({
                   seckillId : ${seckill.seckillId},
                   startTime : ${seckill.startTime.time},
                   endTime : ${seckill.endTime.time}
               });
           });
       </script>

</html>
