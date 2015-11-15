/**
 * smarttery tool js
 */
/*var SMARTTERY = {
		LockPage : function(element){
			var now = new Date().getTime();
			var lockpageId = now;
			var lockpageObj = $(element);
			var lockpageWidth = lockpageObj.css("width");
			var lockPageHeight = lockpageObj.css("height");
			lockpageObj.prepend("<div id = '"+lockpageId+"'></div>");
			$("#"+lockpageId).css({
				"width" : lockpageWidth,
				"height" : lockPageHeight,
				"background-color":"#000000",
				"opacity" : "0.5", 
				"filter" : "alpha(opacity=50)",
				"position" : "absolute",
				"top" : 0,
				"left" : 0,
				"z-index":1
			});
			return lockpageId;
		},
		UnlockPage : function(locker){
			var node = document.getElementById(locker);
			var prntNode = node.parentNode;
			prntNode.removeChild(node);
		}
};*/
(function(module,funsn){
	funsn(module);
}(window,function(obj){
	var SMARTTERY = {
			LockPage : function(element){
				var now = new Date().getTime();
				var lockpageId = now;
				var lockpageObj = $(element);
				var lockpageWidth = lockpageObj.css("width");
				var lockPageHeight = lockpageObj.css("height");
				lockpageObj.prepend("<div id = '"+lockpageId+"'></div>");
				$("#"+lockpageId).css({
					"width" : lockpageWidth,
					"height" : lockPageHeight,
					"background-color":"#000000",
					"opacity" : "0.5", 
					"filter" : "alpha(opacity=50)",
					"position" : "absolute",
					"top" : 0,
					"left" : 0,
					"z-index":1
				});
				return lockpageId;
			},
			UnLockPage : function(locker){
				var node = document.getElementById(locker);
				var prntNode = node.parentNode;
				prntNode.removeChild(node);
			},
			HtmlBodyObject:function(){
				return getBodyObject();
			},
			init:function(){
				init();
			}
	};
	function getBodyObject(){
		var compatMode = document.compatMode;
		if(typeof compatMode != "undifined"){
			if(compatMode == obj.SMARTTERY_CONSTANT.STARDARD_MODE){//CSS1Compat
				return document.documentElement;
			}else if(compatMode == obj.SMARTTERY_CONSTANT.QUICK_MODE){
				return document.body;
			}else{
				console.error("Can not recognize the compatMode!");
			}
		}else{
			console.error("Can not get the compatMode!");
		}
		return null;
	}
	/*init the webpage*/
	function init(){
		locatScroller();
		//init the slide menu
		initSlideMenu();
	}
	function locatScroller(){
		console.log("locate the scroller_" +SMARTTERY.HtmlBodyObject().scrollTop);
		
		if(SMARTTERY.HtmlBodyObject().scrollTop > 100){
			$(".toTop").css({"display":"inline-block"});
		}else{
			$(".toTop").css({"display":"none"});
		}
	}
	function initSlideMenu(){
		console.log("init the slide menu__");
		var widthlmt = (obj.SMARTTERY_CONSTANT.SLIDE_MENU_CONFIG.MENU_ITEM_WIDTH + 1) * (obj.SMARTTERY_CONSTANT.SLIDE_MENU_CONFIG.MENU_SHOW_COUNT_H - obj.SMARTTERY_CONSTANT.SLIDE_MENU_CONFIG.MENU_IN_BAR_H);
		if(widthlmt < 0){
			obj.$(".slidemenubodywrapper").css({
				"left": (-widthlmt-1)/2 + "px"
			});
		}else{
			obj.$(".slidemenubodywrapper").css({
				"left": -widthlmt + "px"
			});
		}
		$("#slideMenu").append("<div id='lotteryMsg'></div>");
		$("#lotteryMsg").css({
			"position" : "absolute",
			"bottom" : "0",
			"width" : "637px",
			"left" : "324px",
			"height" : "25px",
			"line-height" : "25px",
			"background-color" : "",
			"text-align" : "center"
		});
		$("#lotteryMsg").append("<span></span>");
		$("#lotteryMsg span").css({
			"display" : "none",
			"font-size" : "15px",
			"font-family" : "goldtime",
			"color" : "#699ED2",
			"letter-spacing" : "2px",
			"text-shadow" : "1px 1px 1px #AAAAAA"
		});
		$(".slidemenubodywrapper ul li div").mouseenter(function(){
			var className = $(this).attr("class");
			$("#lotteryMsg span").css({"display" : "block"});
			console.log(" className = " + className);
			if(className=="menuitem ssq"){
				$("#lotteryMsg span").html("双色球");
			}else if(className=="menuitem fc3d"){
				$("#lotteryMsg span").html("福彩3d");
			}else if(className=="menuitem qlc"){
				$("#lotteryMsg span").html("七乐彩");
			}else if(className=="menuitem dlt"){
				$("#lotteryMsg span").html("大乐透");
			}else if(className=="menuitem pl3"){
				$("#lotteryMsg span").html("排列3");
			}else if(className=="menuitem qxc"){
				$("#lotteryMsg span").html("七星彩");
			}
		});
		$(".slidemenubodywrapper ul").mouseleave(function(){
			$("#lotteryMsg span").fadeOut();
		});
	}
	
	if(typeof define ==="function"){
		define("smarttery",[],function(){
			return SMARTTERY;
		});
	}
	window.SMARTTERY = SMARTTERY;
	return SMARTTERY;
}));