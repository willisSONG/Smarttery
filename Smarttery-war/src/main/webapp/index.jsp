<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Smarttery慧彩</title>
<script type="text/javascript">
	var contextRoot = "<%=request.getContextPath()%>";
	var scheme = "<%=request.getScheme()%>";
	var host = "<%=request.getServerName()%>";
	var port = "<%=request.getServerPort()%>";
	var community_tools_config = {
		TOOL_BAR_SIDE_LENGTH : "52px",
		QR_CODE_PIC_SIDE_LENGTH : "200px"
	};
</script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/homepage.css"/>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/img/title.ico"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/Constant.js"></script>
<script data-main="<%=request.getContextPath()%>/js/app/homepage/homepage.js" src="<%=request.getContextPath()%>/js/require.js"></script>
</head>
<body>
<!-- 	<div id="topbar" class="topbar"> -->
<!-- 		<div class="tips"> -->
<!-- 			welcome to smarttery -->
<!-- 		</div> -->
<!-- 		<ul> -->
<!-- 			<li><a>注册 </a> | <a>登陆</a></li> -->
<!-- 		</ul> -->
<!-- 	</div> -->
	<div id="pageWrapper" class="pageWrapper">
		<div class="underBarArea">
			<div id="header" class="header">
				<div id="logoWrapper" class="logoWrapper">
					<img alt="" src="<%=request.getContextPath() %>/img/logo.png">
				</div>
				<div id="headerLinks" class="headerLinks">
					<ul>
						<li>
							<a href="http://weibo.com/willisSong" target="blank_">
								<img alt="" src="img/community/sinalogo.png">
							</a>
						</li>
						<li>
							<a href="#">Rigest</a>
						</li>
						<li>
							<a href="#">History</a>
						</li>
					</ul>
				</div>
			</div>
			<div id="topTitle" class="topTitle">
				<h1>Lottery Recommendation</h1>
				<div id="description" class="discription">
					<p>
						Hello,guys,welcome to Smarttery.I'm very glad that you could access this website.
						<br/>I created it just for giving you some recommendation of lottery bought.
						And I can't make sure the result is accurate.
						<br/> Maybe it is not worth of choosing,just suggestion,
						and not for the purpose of benefit. 
					</p>
				</div>
				<div style="font: inherit;color: inherit;text-decoration: none">
					<a href="javascript:void(0)" id="Start">
						<span>Let's Begin</span>
					</a>
				</div>
			</div>
			<div id="displayOuterWrapper" class="displayOuterWrapper">
				<div class="dataSection">
					<%@include file="jsp/common/datagrid.jsp" %>
				</div>
				<div  class="HmenuInnerWrapper">
					<div id="lagan" class="lagan">
						
					</div>
					<div id="slideMenu" class="slideMenu">
						<div id="leftpointerwrapper" class="leftpointerwrapper" title="">
							<div class="newsarea">
								<div class="newsareatitle" >
									<span style=" ">彩票新闻</span>
									<div class="webmarker" style="">smarttery</div>
								</div>
								<div class="newsdetail" >
									<ul>
										<li>
											<span class="newstitle">查看最新中奖结果,哈哈哈哈哈哈哈</span>
											<span class="publishdate">2015/09/01</span>
										</li>
										<li>
											<span class="newstitle">查看最新中奖结果,呦吼吼吼吼吼</span>
											<span class="publishdate">2015/09/01</span>
										</li>
										<li>
											<span class="newstitle">查看最新中奖结果,</span>
											<span class="publishdate">2015/09/01</span>
										</li>
										<li>
											<span class="newstitle">查看最新中奖结果,</span>
											<span class="publishdate">2015/09/01</span>
										</li>
									</ul>
								</div>
							</div>
							<div class="leftpointer">
								<div class="leftArrow">
									<a href="javascript:void(0)" id="slideLeftA">
									</a>
								</div>
							</div>
						</div>
						<div class="slideMenuTips">
							<marquee behavior="scroll" direction="right" >Please choose a kind of lottery that you want to buy</marquee>
						</div>
						<div id="slidemenubodywrapper" class="slidemenubodywrapper">
							<ul id="slideMenuBody">
								<li>
									<div class="menuitem ssq">
										<a href="javascript:void(0)">
											<span></span>
										</a>
									</div>
									<div class="btnshadow">
									</div>
								</li>
								<li class="">
									<div class="menuitem fc3d">
										<a href="javascript:void(0)">
											<span></span>
										</a>
									</div>
									<div class="btnshadow">
									</div>
								</li>
								<li>
									<div class="menuitem qlc">
										<a href="javascript:void(0)">
											<span></span>
										</a>
									</div>
									<div class="btnshadow">
									</div>
								</li>
								<li class="">
									<div class="menuitem dlt">
										<a href="javascript:void(0)">
											<span></span>
										</a>
									</div>
									<div class="btnshadow">
									</div>
								</li>
								<li class="">
									<div class="menuitem pl3">
										<a href="javascript:void(0)">
											<span></span>
										</a>
									</div>
									<div class="btnshadow">
									</div>
								</li>
								<li class="">
									<div class="menuitem qxc">
										<a href="javascript:void(0)">
											<span></span>
										</a>
									</div>
									<div class="btnshadow">
									</div>
								</li>
<!-- 								<li> -->
<!-- 									<a href="#"> -->
<!-- 										<span>6场半全场</span> -->
<!-- 									</a> -->
<!-- 								</li> -->
<!-- 								<li> -->
<!-- 									<a href="#"> -->
<!-- 										<span>4场进球彩</span> -->
<!-- 									</a> -->
<!-- 								</li> -->
<!-- 								<li> -->
<!-- 									<a href="#"> -->
<!-- 										<span>胜平负</span> -->
<!-- 									</a> -->
<!-- 								</li> -->
							</ul>
						</div>
						<div id="rightpointerwrapper" class="rightpointerwrapper" title="动动鼠标就可以找到你想买的彩票哦">
							<div class="rightpointer">
								<div class="rightArrow">
									<a href="javascript:void(0)" id="slideRightA">
									</a>
								</div>
							</div>
							<div class="slideTool">
								<a href="javascript:void(0)">
									<span></span>
								</a>
							</div>
							<div class="tfimg" style="position: absolute;height: inherit;width: 285px;left: 50px;top: 0;padding: 0;z-index: 1">
								<img alt="" src="img/tf285x144.png">
							</div>
						</div>
					</div>
				</div>
<!-- 				<div style="z-index: 3;border: 1px solid;position: relative;">hello</div> -->
			</div>
<!-- 			<div id="pageContent" class="pageContentWrapper"> -->
<!-- 				<p>this area will give u some groups of numbers</p> -->
<!-- 			</div> -->
			<div class="footer">
				<hr/>
				<ul>
					<li>
						<span> ©2015 慧彩 @新浪云服务</span>
						|
						<a class="blance" href="http://weibo.com/willisSong" target="blank_">新浪微博</a>
						|
						<a class="blance" href="#">腾讯微博</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="communitiytool">
			<div class="communityBar">
				<ul>
					<li class="weixin">
						<div class="communitiydetail weixin2Code">
						</div>
					</li>
					<li class="weibo">
						<div class="communitiydetail weibo2Code">
						</div>
					</li>
					<li class="QQ">
						<div class="communitiydetail QQ2Code">
						</div>
					</li>
					<li class="toTop">
					</li>
				</ul>
			</div>
			<div class="communityShowHide communityShow">
				
			</div>
		</div>
	</div>
	<!-- <script type="text/javascript" src="js/common/jquery-1.11.3.js"></script>
	<script type="text/javascript">
		$.ajax({
			url : "http://localhost:8080/cp/test",
			beforeSend:function(xhr){
				alert("before");
			},
			success:function(a,b,c){
				alert("sucess	");
			}
		});
		$.ajax({
			url : "http://localhost:8080/cp/test",
			beforeSend:function(xhr){
				alert("before");
			},
			success:function(a,b,c){
				alert("sucess	");
			}
		});
	</script> -->
</body>
</html>