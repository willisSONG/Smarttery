module.exports=function(grunt){
	require("load-grunt-tasks")(grunt);
	var config = {
		"htmls" : "htmls",
		"dist" : "dist",
		"scripts":"javascripts"
	};
	grunt.initConfig({
		config : config,//导入配置项
		uglify:{
			dist : {
				files : [{
					expand : true,
					cwd : "<%=config.scripts%>/",
					dest : "<%=config.dist%>/",
					ext : ".min.js",
					src:"*.js"
				}]
			}
		},
		browserSync: {
			// port:8080,
		    bsFiles: {
		        src : 'style/*.css',
		        
		    },
		    options: {
		        server: {
		            baseDir: "./",
		        },
		        port: 8081
		    }
		}
	});
	grunt.registerTask("default",["browserSync"]);
};