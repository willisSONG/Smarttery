require.config({
	baseUrl : "./",
	paths:{
		"jquery" : "bower_components/jquery/dist/jquery.min",
		"a" : "a"
	}
});

require(["jquery"],function($){
	console.log($);
	// var slideLotteryPannel = function(event){
	// 	alert(event);
	// };
	$(".slidetool").bind({
		"click" : function(event){
			$(".lottery-scroll .lotterycup").removeClass("lotterycup_movex");	
			$(".lottery-scroll .lotterydata").removeClass("lotterydata_movex");
			event = window.event || event;
			var element = event.target||event.srcElement;
			var lottery_sort = element.id || element.name;
			if(lottery_sort && lottery_sort=="ssq_link"){
				// $(".lottery-scroll").removeClass("panel-init-slide");
				// $(".lottery-scroll").addClass("lottery_slide_1");
				// $(".lottery-scroll").removeClass("lottery_slide_2");
				// $(".lottery-scroll").removeClass("lottery_slide_3");
				$(".lottery-scroll").animate({
					"left" : "0"
				},300,function(){
					$(".lottery-scroll .lotterycup").addClass("lotterycup_movex");
					$(".lottery-scroll .lotterydata").addClass("lotterydata_movex");
				});
			}else if(lottery_sort && lottery_sort=="dlt_link"){
				$(".lottery-scroll").animate({
					"left" : "-100%"
				},300,function(){
					$(".lottery-scroll .lotterycup").addClass("lotterycup_movex");
					$(".lottery-scroll .lotterydata").addClass("lotterydata_movex");
				});

			}else if(lottery_sort && lottery_sort=="qxc_link"){
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
	
})