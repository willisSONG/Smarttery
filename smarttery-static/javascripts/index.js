require.config({
	baseUrl : "./",
	paths:{
		"jquery" : "bower_components/jquery/dist/jquery.min",
		"a" : "a"
	}
});

require(["jquery"],function($){
	console.log($);
	var current_lottery_display = 0 ;
	//网页中彩票信息配置
	var lottery_config ={
		_count : 3,//一共有几种彩票
		current_lottery_display : 0 ,
		setCheckStatus : function(){//设置radiobutton状态
			console.log("this.current_lottery_display = "+this.current_lottery_display);
			document.getElementsByName("lottery_sort")[this.current_lottery_display]["checked"] = "checked";
		}
	}
	Object.defineProperties(lottery_config,{
		"count" : {
			get : function(){
				return this._count;
			}
		}
	});
	var slideParams = {
		"originx" : 0,
		"originy" : 0
	};
	// var setCheckStatus = function(){
	// 	document.getElementsByName("lottery_sort")[current_lottery_display]["checked"] = "checked";
	// }
	$(".slidetool").bind({
		"click" : function(event){
			$(".lottery-scroll .lotterycup").removeClass("lotterycup_movex");	
			$(".lottery-scroll .lotterydata").removeClass("lotterydata_movex");
			event = window.event || event;
			var element = event.target||event.srcElement;
			var lottery_sort = element.id || element.name;
			if(lottery_sort && lottery_sort=="ssq_link"){
				lottery_config.current_lottery_display = 0 ;
				$(".lottery-scroll").animate({
					"left" : "0"
				},300,function(){
					$(".lottery-scroll .lotterycup").addClass("lotterycup_movex");
					$(".lottery-scroll .lotterydata").addClass("lotterydata_movex");
				});
			}else if(lottery_sort && lottery_sort=="dlt_link"){
				lottery_config.current_lottery_display = 1 ;
				$(".lottery-scroll").animate({
					"left" : "-100%"
				},300,function(){
					$(".lottery-scroll .lotterycup").addClass("lotterycup_movex");
					$(".lottery-scroll .lotterydata").addClass("lotterydata_movex");
				});

			}else if(lottery_sort && lottery_sort=="qxc_link"){
				lottery_config.current_lottery_display = 2 ;
				$(".lottery-scroll").animate({
					"left" : "-200%"
				},300,function(){
					$(".lottery-scroll .lotterycup").addClass("lotterycup_movex");
					$(".lottery-scroll .lotterydata").addClass("lotterydata_movex");
				});
			}
			else{
				return false;
			}
			// alert(event);
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
		// alert(this)
	}
	//触摸事件
	document.getElementsByClassName("lottery-scroll")[0].addEventListener("touchstart",function(event){
		var x = event.touches[0].clientX;
		var y = event.touches[0].clientY;
		initSlideParams(x,y);//设置起始坐标
		// console.log("x=" + x + "\ny=" +y)
		console.log(slideParams);
		// alert(event.touches[0]);
	},false);
	document.getElementsByClassName("lottery-scroll")[0].addEventListener("touchmove",function(event){
		// alert("touchmove");
	},false);
	document.getElementsByClassName("lottery-scroll")[0].addEventListener("touchend",function(event){
		// alert("touchmove");
		console.log(event.changedTouches);
		var x = event.changedTouches[0].clientX;
		var y = event.changedTouches[0].clientY;
		$(".lottery-scroll .lotterycup").removeClass("lotterycup_movex");	
			$(".lottery-scroll .lotterydata").removeClass("lotterydata_movex");
		if(parseInt(x)-parseInt(slideParams.originx) > 30){
			if(lottery_config.current_lottery_display> 0){
				lottery_config.current_lottery_display = lottery_config.current_lottery_display- 1 ;
				$(".lottery-scroll").animate({
					"left" : -lottery_config.current_lottery_display*100 + "%"
				},300,function(){
					$(".lottery-scroll .lotterycup").addClass("lotterycup_movex");
					$(".lottery-scroll .lotterydata").addClass("lotterydata_movex");
				});
				lottery_config.setCheckStatus();
			}else{
				//如果滑动到边界则不做滑动处理
			}
		}else if(parseInt(x)-parseInt(slideParams.originx)<=-30){
			// lottery_config.current_lottery_display = lottery_config.current_lottery_display<lottery_config.count? (lottery_config.current_lottery_display+ 1):lottery_config.current_lottery_display ;
			console.log("lottery_config.current_lottery_display = " +lottery_config.current_lottery_display);
			if(lottery_config.current_lottery_display<lottery_config.count-1){
				lottery_config.current_lottery_display = lottery_config.current_lottery_display+ 1 ;
				$(".lottery-scroll").animate({
					"left" : -lottery_config.current_lottery_display*100 + "%"
				},300,function(){
					$(".lottery-scroll .lotterycup").addClass("lotterycup_movex");
					$(".lottery-scroll .lotterydata").addClass("lotterydata_movex");
				});
				lottery_config.setCheckStatus();
			}else{
				//如果滑动到边界则不做滑动处理
			}
		}
	},false);
})