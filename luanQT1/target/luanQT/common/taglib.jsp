<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<link rel="stylesheet" href="${ctx}/extend/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/extend/layui/style.css">
<script src="${ctx}/extend/layui/layui.js"></script>
<script type="text/javascript">
    ctx = '${ctx}';
</script>
<script type="text/javascript" src="${ctx}/extend/assets/js/jquery-1.7.2.min.js"></script>
<script src="${ctx}/js/echarts.js"></script>