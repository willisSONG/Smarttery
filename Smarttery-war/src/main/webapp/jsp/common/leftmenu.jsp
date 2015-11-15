<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="leftmenuWrapper" class="leftmenuWrapper">
	<ul>
		<li>
			<a href="#">
				<span>双色球</span>
			</a>
		</li>
		<li>
			<a href="#">
				<span>时时彩</span>
			</a>
		</li>
		<li>
			<a href="#">
				<span>超级大乐透</span>
			</a>
		</li>
		<li>
			<a href="#">
				<span>福彩3D</span>
			</a>
		</li>
		<li>
			<a href="#">
				<span>排列3</span>
			</a>
		</li>
		<li>
			<a href="#">
				<span>七乐彩</span>
			</a>
		</li>
		<li>
			<a href="#">
				<span>七星彩</span>
			</a>
		</li>
		<li>
			<a href="#">
				<span>六场半全场</span>
			</a>
		</li>
		<li>
			<a href="#">
				<span>四场进球彩</span>
			</a>
		</li>
		<li>
			<a href="#">
				<span>十四场胜负彩</span>
			</a>
		</li>
	</ul>
</div>
<script type="text/javascript">
	$("#leftmenuWrapper ul li").mouseenter(function(){
		$(this).animate({
			width: '200px'
		},300,"linear");
	});
	$("#leftmenuWrapper ul li").mouseleave(function(){
		$(this).animate({
			width : '150px'
		},300,"linear");
	})
	
</script>