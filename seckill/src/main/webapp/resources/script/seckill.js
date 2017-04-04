
//javascript模块化（客户端代码）利用json的表示对象的方式，jsp（服务器段代码，EL表达式）。
//seckill的json对象
var seckill={
    //封装秒杀相关ajax的url
    URL : {
        now : function(){
            return '/seckill/time/now';
        },
        exposer : function(seckillId){
            return '/seckill/'+ seckillId + '/exposer';
        },
        execution : function(seckillId,md5){
            return '/seckill/'+ seckillId + '/'+ md5 + '/execution';
        }
    },
    handleSeckillKill : function(seckillId,node){
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        var killExposer=seckill.URL.exposer(seckillId);
        console.log('killExposer:'+killExposer);
        $.post(killExposer,{},function(result){
            if(result && result['success'])
            {
                var exposer=result['data'];
                if(exposer['exposed'])
                {
                    var md5=exposer['md5'];
                    var killUrl=seckill.URL.execution(seckillId,md5);
                    console.log('killUrl:'+killUrl);
                    //绑定一次点击事件
                    $('#killBtn').one('click',function(){
                        //1.先禁用按钮
                        $(this).addClass('disabled');
                        //2.执行
                        $.post(killUrl,{},function(result){
                            if(result==null)
                            {
                                node.html('<span class="label label-success">未知错误</span>');
                            }
                            else
                            {
                                var killResult=result['data'];
                                var state=killResult['state'];
                                var stateInfo=killResult['stateInfo'];
                                //3.显示结果
                                node.html('<span class="label label-success">'+stateInfo+'</span>');
                            }
                        });
                    });
                    node.show();
                }else{
                    //自己电脑事件到了，服务器时间没到
                    var now=exposer['now'];
                    var start=exposer['start'];
                    var end=exposer['end'];
                    seckill.countdown(seckillId,now,start,end);
                }
            }else{
                console.log('result:'+result);
            }

        });

    },
    countdown : function(seckillId,nowTime,startTime,endTime)
    {
        var seckillBox=$('#seckill-box');
        if(nowTime>endTime){
            seckillBox.html('秒杀结束');
        }else if(nowTime<startTime){
            var killTime=new Date(startTime+1000);
            seckillBox.countdown(killTime,function(event){
                var format=event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                seckillBox.html(format);
            }).on('finish.countdown',function(){
                seckill.handleSeckillKill(seckillId,seckillBox);
            });
        }else{
            seckill.handleSeckillKill(seckillId,seckillBox);
        }
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
                    console.log('inputPhone'+inputPhone);
                    if(seckill.validatePhone(inputPhone)){
                        $.cookie('killPhone',inputPhone,{expires:7,path:'/seckill'});
                        location.reload();
                    }else{
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误！</label>').show(300);
                    }
                });
            }
            //已经登录
            var startTime=params['startTime'];
            var endTime=params['endTime'];
            var seckillId=params['seckillId'];
            //计时交互
            $.get(seckill.URL.now() ,{},function(result){
                if(result && result['success'])
                {
                    var nowTime=result['data'];
                    //时间判断,计时交互
                    seckill.countdown(seckillId,nowTime,startTime,endTime);

                }else{
                    console.log('result:'+result);
                }

            });
        }
    }
}
