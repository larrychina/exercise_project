var seckill = {
    URL:{
        now:function(){
            return "/seckill/time/now";
        },
        exposer:function(seckillId){
            return "/seckill/"+seckillId+"/exposer" ;
        },
        execution:function(md5,seckillId){
            return "/seckill/"+seckillId+"/"+md5+"/execution" ;
        }
    },
    // 验证手机号
    validatePhone:function(killPhone){
        if(killPhone && killPhone.length == 11 && !isNaN(killPhone)){
            return true ;
        }else return false;
    },
    exposer:function (seckillId,node) {
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>')
        $.post(seckill.URL.exposer(seckillId),{},function(data){
           if(data && data['success']){
                var exposer = data['data'] ;
               if(exposer['exposed']){
                   // 秒杀开启
                   var md5 = exposer['md5'] ;
                   var killUrl = seckill.URL.execution(md5,seckillId) ;

                   $("#killBtn").one('click',function(){
                       $(this).addClass("disabled");
                       $.post(killUrl,{},function(killResult){
                           var stateInfo = killResult['data']['stateInfo'] ;
                          if(killResult && killResult.success){
                              // 秒杀成功
                              node.html('<span class="label label-success">' + stateInfo + '</span>').show() ;
                          } else{
                              // 秒杀失败
                              node.html('<span class="label label-danger">' + stateInfo + '</span>').show() ;
                          }
                       });
                   });
                   node.show();
               }else{
                   alert("fuck") ;
                   // 为开启秒杀
                   var now = exposer['now'] ;
                   var start = exposer['start'] ;
                   var end = exposer['end'] ;
                   seckill.countdown(seckillId,now,start,end) ;
               }
           }
        });
    },
    // 时间判断
    countdown:function(seckillId,nowTime,startTime,endTime){
        var seckillBox = $("#seckill-box") ;
        if(nowTime>endTime){
            // 秒杀结束
            seckillBox.html("秒杀活动已结束!");
        }else if(nowTime<startTime){
            // 秒杀未开始，进行倒计时
            var killTime = new Date(startTime)*1 + 1000 ;
            seckillBox.countdown(killTime,function (event) {
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒') ;
                seckillBox.html(format) ;
            }).on('finish.countdown',function(){
               // 获取秒杀地址，控制秒杀逻辑，实现秒杀
                seckill.exposer(seckillId,seckillBox) ;
            });
        }else{
            // 获取秒杀地址，控制秒杀逻辑，实现秒杀
            seckill.exposer(seckillId,seckillBox) ;
        }
    },
    detail:{
        init:function (params) {
            var killPhone = $.cookie('killPhone');
            var startTime = params['startTime'] ;
            var endTime = params['endTime'] ;
            var seckillId = params['seckillId'] ;
            if(!seckill.validatePhone(killPhone)){
                var killPhoneModal = $("#killPhoneModal") ;
                killPhoneModal.modal({
                    show:true, // 显示弹窗
                    backdrop:'static', // 静止位置关闭（点击空白处静止关闭）
                    keyboard:false // 关闭键盘事件
                });
                $("#killPhoneBtn").click(function(){
                    var inputPhone = $("#killPhoneKey").val() ;
                    console.log("inputPhone="+inputPhone+":length:"+inputPhone.length)
                    if(seckill.validatePhone(inputPhone)){
                        $.cookie('killPhone',inputPhone,{expires:7,path:'/seckill'}) ;
                        window.location.reload() ;
                    }else{
                        $("#killPhoneMessage").hide().html("<label class='label label-danger'>手机号码有误！</label>").show(300);
                    }
                }) ;
            }
            // 计时开始
            $.get(seckill.URL.now(),{},function(data){
                if(data && data['success']){
                    var nowTime = data['data'] ;
                    // 时间判断
                    seckill.countdown(seckillId,nowTime,startTime,endTime) ;
                }
            }) ;
        }
    }
}