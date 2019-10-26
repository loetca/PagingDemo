<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>书籍-基础查询</title>
	<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
	<style type="text/css">
	    *{margin:0;padding:0;font-size:18px;}
	    .panel{margin:100px auto;width:600px;}
	    .home{width:75px;margin:0 auto 10px;}
	    .tab{border:1px solid black;padding:10px;}
	    .panel table{width:100%;border-collapse:collapse;}
	    .panel td{text-align:center;border-bottom:1px solid #ccc;}
	    .panel thead td{font-weight:bold;}
	    .nav,.choose{text-align:center;margin:15px 0;}
	    #goPageNo{width:30px;text-align:center;}
	    #goBtn{padding:5px;text-align:center;cursor:pointer;}
	</style>
</head>
<body>
<div class="panel">
    <div class="home"><a href="${pageContext.request.contextPath}">返回首页</a></div>
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
        <%-- 当前页码大于1 --%>
        <c:if test="${requestScope.page.pageNo > 1}">
           <a href="queryBooks?method=queryLimitedBooks">首页</a>&nbsp;
           <a href="queryBooks?method=queryLimitedBooks&pageNo=${requestScope.page.pageNo - 1}">前一页</a>
        </c:if>
        <%-- 当前页码等于1 --%>
        <c:if test="${requestScope.page.pageNo == 1}">
                    首页&nbsp;&nbsp;前一页
        </c:if>

        <a style="color:red; text-decoration:none;" href="queryBooks?method=queryLimitedBooks&pageNo=${requestScope.page.pageNo}">【${requestScope.page.pageNo}】</a>

        <%-- 当前页码小于总页码 --%>
        <c:if test="${requestScope.page.pageNo < requestScope.page.totalPageNo}">
           <a href="queryBooks?method=queryLimitedBooks&pageNo=${requestScope.page.pageNo + 1}">下一页</a>&nbsp;
           <a href="queryBooks?method=queryLimitedBooks&pageNo=${requestScope.page.totalPageNo}">末页</a>
        </c:if>
        <%-- 当前页码等于总页码 --%>
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
            window.location.href = "queryBooks?method=queryLimitedBooks&pageNo=" + goPageNo;
        };
    };
</script>
</body>
</html>