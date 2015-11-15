require.config({
	baseUrl : "./",
	paths:{
		"jquery" : "javascripts/common/jquery-1.11.3",
		"smartterytool" : "javascripts/smarttery.slidetool"
	}
});

require(["jquery","smartterytool"],function($,tool){
	var lottery_config ={
		"lottery_count" : 3
	}
	//init toolbox,初始化
	var toolbox = tool(lottery_config);
	toolbox.setCheckStatus();
	// console.log(setCheckStatus);
	var current_lottery_display = 0 ;
	/*//网页中彩票信息配置
	var lottery_config ={
		c : 3,//一共有几种彩票
		current_lottery_display : 0 ,
		setCheckStatus : function(){//设置radiobutton状态
			console.log("this.current_lottery_display = "+this.current_lottery_display);
			document.getElementsByName("lottery_sort")[current_lottery_display]["checked"] = "checked";
		}
	}
	Object.defineProperties(lottery_config,{
		"count" : {
			get : function(){
				return this._count;
			}
		}
	});*/
	//滑动时用来记录横纵坐标
	var slideParams = {
		"originx" : 0,
		"originy" : 0
	};
	$(".slidetool").bind({
		"click" : function(event){
			$(".lottery-scroll .lotterycup").removeClass("lotterycup_movex");	
			$(".lottery-scroll .lotterydata").removeClass("lotterydata_movex");
			event = window.event || event;
			var element = event.target||event.srcElement;
			var lottery_sort = element.id || element.name;
			/*跳转到双色球*/
			if(lottery_sort && lottery_sort=="ssq_link"){
				if(toolbox.getCurntNum() != 0){
					toolbox.setCurnt(0);
					$(".lottery-scroll").animate({
						"left" : "0"
					},300,function(){
						$(".lottery-scroll .lotterycup").addClass("lotterycup_movex");
						$(".lottery-scroll .lotterydata").addClass("lotterydata_movex");
					});
				}
			}else if(lottery_sort && lottery_sort=="dlt_link"){/*跳转到大乐透*/
				// lottery_config.current_lottery_display = 1 ;
				if(toolbox.getCurntNum() != 1){
					toolbox.setCurnt(1);
					$(".lottery-scroll").animate({
						"left" : "-100%"
					},300,function(){
						$(".lottery-scroll .lotterycup").addClass("lotterycup_movex");
						$(".lottery-scroll .lotterydata").addClass("lotterydata_movex");
					});
				}

			}else if(lottery_sort && lottery_sort=="qxc_link"){/*跳转到七星彩*/
				// lottery_config.current_lottery_display = 2 ;
				if(toolbox.getCurntNum() != 2){
					toolbox.setCurnt(2);
					$(".lottery-scroll").animate({
						"left" : "-200%"
					},300,function(){
						$(".lottery-scroll .lotterycup").addClass("lotterycup_movex");
						$(".lottery-scroll .lotterydata").addClass("lotterydata_movex");
					});
				}
			}
			else{
				return false;
			}
		}
	});
	var initSlideParams = function(originx,originy,endx,endy){
		Object.defineProperties(slideParams,{
			"originx" : {
				value : originx || 0
			},
			"originy" : {
				value : originy || 0
			}
		});
		return slideParams;
	}
	//触摸事件
	var flag = false;
	document.getElementsByClassName("lottery-scroll")[0].addEventListener("touchstart",function(event){
		//alert(event.touches.length);
		flag = false;
		var length  = event.touches.length ;
		if(length ==1 ){
			var x = event.touches[0].clientX;
			var y = event.touches[0].clientY;
			initSlideParams(x,y);//设置起始坐标
		}
		console.log(slideParams);
	},false);
	document.getElementsByClassName("lottery-scroll")[0].addEventListener("touchmove",function(event){
		// alert("touchmove");
		var length = event.touches.length;//找出有几根手指在滑动
		if(length != 1)
			flag = false ;
		else
			flag = true;
		// alert(flag);
	},false);
	document.getElementsByClassName("lottery-scroll")[0].addEventListener("touchend",function(event){
		if(flag){
			var x = event.changedTouches[0].clientX;
			var y = event.changedTouches[0].clientY;
			$(".lottery-scroll .lotterycup").removeClass("lotterycup_movex");	
				$(".lottery-scroll .lotterydata").removeClass("lotterydata_movex");
			var curnt_num = toolbox.getCurntNum();
			if(parseInt(x)-parseInt(slideParams.originx) > 50){
				if(curnt_num > 0){
					toolbox.setCurnt(curnt_num - 1);
					$(".lottery-scroll").animate({
						"left" : -toolbox.getCurntNum()*100 + "%"
					},300,function(){
						$(".lottery-scroll .lotterycup").addClass("lotterycup_movex");
						$(".lottery-scroll .lotterydata").addClass("lotterydata_movex");
					});
					toolbox.setCheckStatus();
				}else{
					//如果滑动到边界则不做滑动处理
				}
			}else if(parseInt(x)-parseInt(slideParams.originx)<=-50){
				// lottery_config.current_lottery_display = lottery_config.current_lottery_display<lottery_config.count? (lottery_config.current_lottery_display+ 1):lottery_config.current_lottery_display ;
				if(curnt_num < toolbox.getLotteryCount()-1){
					// lottery_config.current_lottery_display = lottery_config.current_lottery_display+ 1 ;
					toolbox.setCurnt(curnt_num + 1);
					$(".lottery-scroll").animate({
						// "left" : -lottery_config.current_lottery_display*100 + "%"
						"left" : -toolbox.getCurntNum()*100 + "%"
					},300,function(){
						$(".lottery-scroll .lotterycup").addClass("lotterycup_movex");
						$(".lottery-scroll .lotterydata").addClass("lotterydata_movex");
					});
					// lottery_config.setCheckStatus();
					toolbox.setCheckStatus();
				}else{
					//如果滑动到边界则不做滑动处理
				}
			}
		}

	},false);
//下期预估事件
	$(".mid_leng_button").bind({
		"click" : function(event){
			console.log(this.parentNode.children[1]);
			console.log($(this).attr("lotterySort"));
			$("#" + $(this).attr("lotterySort") + " .curnt_lotry_info").animate({
				"top" : "-100%",
				"opacity" : "0"
			},500,function(){
				$(this).css({
					"display" : "none"
				});
			});
			this.parentNode.children[2].style.display = "block";
		}
	},false);
})