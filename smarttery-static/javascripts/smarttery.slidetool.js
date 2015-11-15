(function(){
	var smartterytool = function(slidetoolconfig){
		var current_lottery =  0;
		var lottery_count = slidetoolconfig.lottery_count;
		// var smartterytoolbox = {
		// 	setCheckStatus : setCheckStatus,
		// };
		//设置checkbox状态
		var setCheckStatus = function(){
			// console.log("this = " + this);
			console.log("current_lottery = " + current_lottery);
			document.getElementsByName("lottery_sort")[current_lottery]["checked"] = "checked";
		};
		var setCurnt = function(num){
			current_lottery = num ;
		};
		var getCurntNum = function(){
			return current_lottery ;
		};
		var getLotteryCount = function(){
			return lottery_count;
		};
		return {
			"setCheckStatus" : setCheckStatus,
			"setCurnt" : setCurnt,
			"getCurntNum" : getCurntNum,
			"getLotteryCount" :getLotteryCount
		};		
	};

	window.smartterytool = smartterytool;
	if(typeof define ==="function"){
		define("smartterytool",[],function(){
			return smartterytool;
		});
	}
	return smartterytool;
})();