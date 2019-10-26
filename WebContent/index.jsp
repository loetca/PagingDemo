<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>JavaWeb分页查询示例</title>
        <style type="text/css">
            h1{color:red;}
            div{width:500px;margin:120px auto;text-align:center;}
            div a{font-size:20px;}
        </style>
    </head>
    <body>
        <div>
            <h1>欢迎使用 JavaWeb 分页查询示例</h1>
            <h1><a href="https://blog.csdn.net/adsl624153/article/details/72512459">可以查看CSDN博主「loetca」的原创文章</a></h1><hr /><br />
            <a href="${pageContext.request.contextPath}/queryBooks?method=queryLimitedBooks">基础查询</a>&nbsp;
            <a href="${pageContext.request.contextPath}/queryBooks?method=queryPageBooksByMinpriceAndMaxprice">进阶查询</a>
        </div>
    </body>
</html>