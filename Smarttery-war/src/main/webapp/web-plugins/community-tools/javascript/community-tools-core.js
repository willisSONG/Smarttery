/**
 * 
 */
(function(obj,func){
	func(obj);
}(window,function(obj){
	console.log(typeof jQuery);
	var Community_Tool = {
			init : function(){
				community_initial();
			},
			HtmlBodyObject : function(){
				return getBodyObject();
			}
	};
	function getBodyObject(){
		var compatMode = document.compatMode;
		if(typeof compatMode != "undifined"){
			if(compatMode == "CSS1Compat"){//CSS1Compat
				return document.documentElement;
			}else if(compatMode == "BackCompat"){
				return document.body;
			}else{
				console.error("Can not recognize the compatMode!");
			}
		}else{
			console.error("Can not get the compatMode!");
		}
		return null;
	}
	function community_initial(){
		console.log("community init");
		if(typeof jQuery === "undefined"){
			console.error("There need the jquery lib support.");
		}
		var pagex = 0,pagey=0;
		var xStart,yStart = 0;
		var dragFlag = false;
		obj.$(document).mousemove(function(evt){
			pagex = evt.clientX;
			pagey = evt.clientY;
//			console.log("listen>"+pagex+","+pagey);
			var clientWidth = Community_Tool.HtmlBodyObject().clientWidth;
			var clientHeight = Community_Tool.HtmlBodyObject().clientHeight;
			console.log(dragFlag);
			if(dragFlag){
				obj.$(".communitiytool").css({
					"right":(clientWidth-pagex-parseInt(community_tools_config.TOOL_BAR_SIDE_LENGTH,10)/2)+"px",
					"bottom":(clientHeight-pagey-parseInt(community_tools_config.TOOL_BAR_SIDE_LENGTH,10)/2)+"px"
				});
			}
		});
		obj.$(".communityShowHide").mousedown(function(e){
			xStart = e.clientX;
			yStart = e.clientY;
			obj.$(".communityShowHide").unbind("click");
			this.setCapture && this.setCapture();
			console.log("mousedown");
			dragFlag = true;
		});
		obj.$(".communityShowHide").mouseup(function(e){
//			obj.$(".communityShowHide")[0].releaseCapture();
			this.releaseCapture && this.releaseCapture();
			dragFlag = false;
			console.log("mouseup");
			var communityToolLeft2 = $(".communitiytool").position().left;
			var communityToolLeft = $(".communitiytool").position().left;
			
//			var communityToolRight = $(".communitiytool").position().right;
			var communityToolRight = $(".communitiytool").css("right");
			var communityToolTop = $(".communitiytool").position().top;
//			var communityToolBottom = $(".communitiytool").position().bottom;
			var communityToolBottom = $(".communitiytool").css("bottom");
//			var communityBarToTop = $(".communitiytool").css("top");
			if(parseInt(communityToolTop) >= (parseInt(community_tools_config.QR_CODE_PIC_SIDE_LENGTH,10) - parseInt(community_tools_config.TOOL_BAR_SIDE_LENGTH))){//148
				if(parseInt(communityToolLeft,10) < parseInt(community_tools_config.QR_CODE_PIC_SIDE_LENGTH,10)){
					console.log("left bottom");
					obj.$(".communitiydetail").css({
						"left" : community_tools_config.TOOL_BAR_SIDE_LENGTH,
						"top":parseInt(community_tools_config.TOOL_BAR_SIDE_LENGTH) - parseInt(community_tools_config.QR_CODE_PIC_SIDE_LENGTH) + "px",
						"transform-origin" : "left bottom"
					});
					if(parseInt(communityToolLeft,10) < 0){
						$(".communitiytool").animate({
							"right" : "+=" +communityToolLeft
						});
					}
				}else{
					console.log("right bottom");
					obj.$(".communitiydetail").css({
						"left" : "-" + community_tools_config.QR_CODE_PIC_SIDE_LENGTH,
						"top": parseInt(community_tools_config.TOOL_BAR_SIDE_LENGTH) - parseInt(community_tools_config.QR_CODE_PIC_SIDE_LENGTH) + "px",
						"transform-origin" : "right bottom"
					});
					if(parseInt(communityToolRight,10) < 0){
						$(".communitiytool").animate({
							"right" : "-=" +communityToolRight
						});
					}
				}
			}else{//<148px
				console.log("communityToolLeft"+communityToolLeft);
				console.log("communityToolLeft2"+communityToolLeft2);
				console.log("communityToolRight"+communityToolRight);
				if(parseInt(communityToolLeft,10) < parseInt(community_tools_config.QR_CODE_PIC_SIDE_LENGTH,10)){//in chrome left is auto
					console.log("left top");
					obj.$(".communitiydetail").css({
						"left" : community_tools_config.TOOL_BAR_SIDE_LENGTH,
						"top":"0",
						"transform-origin" : "left top"
					});
					if(parseInt(communityToolLeft,10) < 0){
						$(".communitiytool").animate({
							"right" : "+=" +communityToolLeft
						});
					}
				}else{
					console.log("right top");
					obj.$(".communitiydetail").css({
						"left" : "-" + community_tools_config.QR_CODE_PIC_SIDE_LENGTH ,
						"top" : "0",
						"transform-origin" : "right top"
					});
					if(parseInt(communityToolRight,10) < 0){
						$(".communitiytool").animate({
							"right" : "-=" +communityToolRight
						});
					}
				}
			}
			
			if(parseInt(communityToolTop,10) < 0){//controll do not beyond the top boundary
				$(".communitiytool").animate({
					"bottom" : "+="+communityToolTop
				});
			}
			console.log("communityToolBottom = " + communityToolBottom);
			if(parseInt(communityToolBottom,10) < 0){//controll do not beyond the bottom boundary
				$(".communitiytool").animate({
					"bottom" : "-="+communityToolBottom
				});
			}
			console.log("endpoint==>"+e.clientX+","+e.clientY);
			if(xStart === e.clientX && yStart ===e.clientY){//some click function,showhideable
				CommunityShowHideImpl();
			}
		});
	}
	var CommunityShowHideImpl = function(){
		var classList = obj.$(".communityShowHide")[0].classList ;
		if(classList[1] == "communityShow"){
			console.log("slidedown");
			$(".communityBar").slideUp("0.5s",function(){
				$(".communityShowHide").removeClass("communityShow");
				$(".communityShowHide").addClass("communityHide");
//				var communityBarToTop = $(".communitiytool").css("top");
				var communityBarToTop = $(".communitiytool").position().top;
				if(parseInt(communityBarToTop,10) < 0){
					$(".communitiytool").animate({
						"bottom" : "+="+communityBarToTop
					});
				}
			});
		}else if(classList[1] == "communityHide"){
			$(".communityBar").slideDown("0.5s",function(){
				$(".communityShowHide").removeClass("communityHide");
				$(".communityShowHide").addClass("communityShow");
//				var communityBarToTop = $(".communitiytool").css("top");
				var communityBarToTop = $(".communitiytool").position().top;
				if(parseInt(communityBarToTop,10) < 0){
					$(".communitiytool").animate({
						"bottom" : "+="+communityBarToTop
					});
				}
			});
		}
	};
	if(typeof define === "function"){
		define("community_tools",["jquery"],function(){
			return Community_Tool;
		})
	}
	window.Community_Tool = Community_Tool ;
	return Community_Tool;
}))