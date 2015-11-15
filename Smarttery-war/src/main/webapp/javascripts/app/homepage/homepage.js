/**
 * 
 */
require.config({
	baseUrl : contextRoot,
	paths : {
		"jquery" : "js/common/jquery-1.11.3",
		"homepage" :"js/app/homepage/homepage",
		"smarttery":"js/common/smarttery_TOOLS",
		"community_tools" : "web-plugins/community-tools/javascript/community-tools-core"
	}
});
require(["jquery","smarttery","community_tools"],function(jquery,smarttery,community){
	$(document).ready(function(){
		//init the webPage
		smarttery.init();
		community.init();
		var vMenuShowFlag =false;
		var lockerId = "";
		var left =  parseInt($("#slidemenubodywrapper").css("left"));
		$("#Start").click(function(){
			if(vMenuShowFlag == false){
				$(".HmenuInnerWrapper").animate({
					"top" : "+=330px"
				},"normal","linear",function(){
					$(".HmenuInnerWrapper").animate({
						"top" : "-=20px"
					},300,"linear");
				});
				vMenuShowFlag = true ;
				lockerId = smarttery.LockPage(".dataGridWrapper");
			}
		});
		
		$("#slideRightA").click(function(){
			/*console.log("left="+$("#slidemenubodywrapper").css("left"));
			console.log("right="+$("#slidemenubodywrapper").css("right"));*/
			if(vMenuShowFlag){
				if(parseInt(left, 10)<0){
					left += (SMARTTERY.SLIDE_MENU_CONFIG.MENU_ITEM_WIDTH + 1);
					$("#slidemenubodywrapper").animate({
						"left" : "+=" + (SMARTTERY.SLIDE_MENU_CONFIG.MENU_ITEM_WIDTH + 1) + "px"
					},500,"linear");
				}
			}
		});
		$("#slideLeftA").click(function(){
			if(vMenuShowFlag){
				var widthlmt = (SMARTTERY.SLIDE_MENU_CONFIG.MENU_ITEM_WIDTH + 1)*(SMARTTERY.SLIDE_MENU_CONFIG.MENU_SHOW_COUNT_H-SMARTTERY.SLIDE_MENU_CONFIG.MENU_IN_BAR_H);
				if(parseInt(left, 10)>(-1)*widthlmt){
					$("#slidemenubodywrapper").animate({
						"left" : "-="+ (SMARTTERY.SLIDE_MENU_CONFIG.MENU_ITEM_WIDTH + 1) +"px"
					},500,"linear");
					left -= ((SMARTTERY.SLIDE_MENU_CONFIG.MENU_ITEM_WIDTH + 1));
				}
			}
		});
		//bind a click event to this <a>
		$(".slideTool a").click(function(){
			$(".HmenuInnerWrapper").animate({
				"top" : "-330px"
			},500,"linear",function(){
				vMenuShowFlag = false;
				smarttery.UnLockPage(lockerId);
			});
		});
		
		$(window).scroll(function(e){//same as window.onscroll = function(){};
			var bodyObj = smarttery.HtmlBodyObject();
			var scrollTop = bodyObj.scrollTop;
			if(parseInt(scrollTop,10) >= 100){
				/*console.log("scroll is big enough,then show the [to top ]button");*/
				$(".toTop").fadeIn();
			}else{
				$(".toTop").fadeOut("1s");
			}
		});
		
		$(".toTop").click(function(){
//			console.log(smarttery.HtmlBodyObject());
			var bodyObj = smarttery.HtmlBodyObject();
			bodyObj.scrollTop = 0 ;
		});
	});
	
});