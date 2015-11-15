/**
 * 
 */
//alert(contextRoot);
require.config({
	baseUrl : (contextRoot + "/js"),
	paths : {
		"jquery" : "common/jquery-1.11.3",
		"homepage" :"app/homepage/homepage"
	}
});

require(["jquery","homepage"],function(jq,hp){
	
});