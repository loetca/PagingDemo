<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="cn.loetca.pagingdemo.bean.Page"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>书籍-进阶查询</title>
	<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
	<style type="text/css">
	    *{margin:0;padding:0;font-size:18px;}
	    .panel{margin:100px auto;width:600px;}
	    .home{width:75px;margin:0 auto 10px;}
	    form{width:260px;margin:10px auto 0;}
	    .tab{border:1px solid black;margin:20px 0;padding:10px;}
	    .panel table{width:100%;border-collapse:collapse;}
	    .panel td{text-align:center;border-bottom:1px solid #ccc;}
	    .panel thead td{font-weight:bold;}
	    .nav,.choose{text-align:center;margin:15px 0;}
	    .priceTxt,#goPageNo{width:30px;text-align:center;}
	    #queryBtn,#goBtn{padding:5px;text-align:center;cursor:pointer;}
	</style>
</head>
<body>
<div class="panel">
    <div class="home"><a href="${pageContext.request.contextPath}">返回首页</a></div><hr />
    <form action="queryBooks?method=queryPageBooksByMinpriceAndMaxprice" method="post">
           价格查询:
          <input class="priceTxt" type="text" name="min" value="${param.min}" />元 -
          <input class="priceTxt" type="text" name="max" value="${param.max}" />元
          <input id="queryBtn" type="submit" value="查询" />
    </form>
	<div class="tab">
	    <table>
	        <thead>
	            <tr>
	                <td>编号</td>
	                <td>书名</td>
	                <td>作者</td>
	                <td>价格</td>
	            </tr>
	        </thead>
	        <tbody>
		        <c:forEach items="${page.list}" var="book">
	            <tr>
	                <td>${book.id}</td>
	                <td>${book.name}</td>
	                <td>${book.author}</td>
	                <td>${book.price}</td>
	            </tr>
				</c:forEach>
	        </tbody>
	    </table>
	</div>
    <div class="nav">
        <%-- 指定显示的页码数量是5 --%>
        <c:set var="defaultShowPageNo" value="<%=Page.SHOW_ITEMS%>"></c:set>

        <c:choose>
            <%-- 【情况一】总的页码数量小于指定显示的页码数量 --%>
            <c:when test="${requestScope.page.totalPageNo < defaultShowPageNo}">
                <c:set var="beginPageNo" value="1"></c:set>
                <c:set var="endPageNo" value="${requestScope.page.totalPageNo}"></c:set>
            </c:when>

            <%-- 【情况二】当前页码小于等于指定显示的页码数量的中间位置 --%>
            <c:when test="${requestScope.page.pageNo <= ((defaultShowPageNo + 1) / 2)}">
                <c:set var="beginPageNo" value="1"></c:set>
                <c:set var="endPageNo" value="${defaultShowPageNo}"></c:set>
            </c:when>

            <%-- 【情况三】当前页码小于等于总的页码数 - 2（就是到最后的中间位置时） --%>
            <c:when test="${requestScope.page.pageNo <= (requestScope.page.totalPageNo - 2)}">
                <c:set var="beginPageNo" value="${requestScope.page.pageNo - 2}"></c:set>
                <c:set var="endPageNo" value="${requestScope.page.pageNo + 2}"></c:set>
            </c:when>

            <%-- 【情况四】当前页码大于总的页码数 - 2 --%>
            <c:when test="${requestScope.page.pageNo > (requestScope.page.totalPageNo - 2)}">
                <c:set var="beginPageNo" value="${requestScope.page.totalPageNo + 1 - defaultShowPageNo}"></c:set>
                <c:set var="endPageNo" value="${requestScope.page.totalPageNo}"></c:set>
            </c:when>
        </c:choose>


        <c:if test="${requestScope.page.pageNo > 1}">
            <a href="queryBooks?method=queryPageBooksByMinpriceAndMaxprice&min=${param.min}&max=${param.max}">首页</a>&nbsp;
            <a href="queryBooks?method=queryPageBooksByMinpriceAndMaxprice&pageNo=${requestScope.page.pageNo - 1}&min=${param.min}&max=${param.max}">前一页</a>
        </c:if>
        <c:if test="${requestScope.page.pageNo == 1}">
                  首页&nbsp;&nbsp;前一页
        </c:if>

        <%-- 从page域中取出开始和结束下标值 --%>
        <c:forEach begin="${beginPageNo}" end="${endPageNo}" var="index">
            <c:if test="${index == requestScope.page.pageNo}">
                <a style="color:red; text-decoration:none;" href="queryBooks?method=queryPageBooksByMinpriceAndMaxprice&pageNo=${index}&min=${param.min}&max=${param.max}">【${index}】</a>
            </c:if>

            <c:if test="${index != requestScope.page.pageNo}">
                <a href="queryBooks?method=queryPageBooksByMinpriceAndMaxprice&pageNo=${index}&min=${param.min}&max=${param.max}">${index}</a>
            </c:if>
        </c:forEach>

        <c:if test="${requestScope.page.pageNo < requestScope.page.totalPageNo}">
            <a href="queryBooks?method=queryPageBooksByMinpriceAndMaxprice&pageNo=${requestScope.page.pageNo + 1}&min=${param.min}&max=${param.max}">下一页</a>&nbsp;
            <a href="queryBooks?method=queryPageBooksByMinpriceAndMaxprice&pageNo=${requestScope.page.totalPageNo}&min=${param.min}&max=${param.max}">末页</a>
        </c:if>
        <c:if test="${requestScope.page.pageNo == requestScope.page.totalPageNo}">
                  下一页&nbsp;&nbsp;末页
        </c:if>
    </div>
    <div class="choose">
              一共有 ${requestScope.page.countNo} 条记录&nbsp;&copy;&nbsp;
              一共有 ${requestScope.page.totalPageNo} 页&nbsp;
              跳转到第 <input type="text" value="${requestScope.page.pageNo}" id="goPageNo" /> 页&nbsp;
        <input type="button" id="goBtn" value="跳转" />
    </div>
</div>

<script type="text/javascript">
    window.onload = function(){
        var goBtn = document.getElementById("goBtn");
        goBtn.onclick = function(){
            var goPageNo = document.getElementById("goPageNo").value;
            goPageNo = parseInt(goPageNo);
            if (isNaN(goPageNo)) {
            	alert('页码不是数值！');
            	document.getElementById("goPageNo").value = 1;
            	return;
            }
            window.location.href = "queryBooks?method=queryPageBooksByMinpriceAndMaxprice&pageNo=" + goPageNo + "&min=${param.min}&max=${param.max}";
        };
    };
</script>
</body>
</html>