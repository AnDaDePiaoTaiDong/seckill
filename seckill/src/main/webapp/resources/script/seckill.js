//javascript模块化（客户端代码）利用json的表示对象的方式，jsp（服务器段代码，EL表达式）。
//seckill的json对象
var seckill={
    //封装秒杀相关ajax的url
    URL : {

    },
    validatePhone: function(phone){
        if(phone && phone.length==11 && !isNaN(phone)){
            return true;
        } else{
            return false;
        }
    },
    //详情页秒杀逻辑
    detail:{
        //详情页初始化
        init : function(params){
            //手机验证和登录,计时交互
            //规划交互流程
            //在cookie中查找手机号
            var killPhone= $.cookie('killPhone');
            //javascript访问json的一种格式，
            // 客户端javascript向服务器端jsp拿数据（json来传递，
            // 服务器后端代码已经把数据封装成json格式了）
            var startTime=params['startTime'];
            var endTime=params['endTime'];
            var seckillId=params['seckillId'];
            //验证手机号
            if(!seckill.validatePhone(killPhone)){
                var killPhoneModal=$('#killPhoneModal');
                killPhoneModal.modal({
                    show:true,
                    backdrop:'static',
                    keyboard:false
                });
                $('#killPhoneBtn').click(function(){
                    var  inputPhone=$('#killPhoneKey').val();
                    if(seckill.validatePhone(inputPhone)){
                        $.cookie('killPhone',inputPhone,{expires:7,path:'/seckill'});
                        Window.location.reload();
                    }else{
                        $.('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误！</label>').show(300);
                    }
                });
            }
            //已经登录
        }
    }
}
