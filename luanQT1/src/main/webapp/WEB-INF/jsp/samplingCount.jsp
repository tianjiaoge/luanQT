<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/7
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title>采样统计</title>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
        $.ajax({
            url:'${ctx}/sampling/loadCountData.do',
            type:'post',
            dataType:'json',
            success:function (obj) {
                var xaxis = obj.xaxis;
                var samplings = obj.samplings;
                var merges  = obj.merges;
                myChart.setOption({
                    title: {
                        text: '样品分析'
                    },
                    tooltip: {},
                    legend: {
                        data:['采样量','合样量']
                    },
                    xAxis: {
                        data: xaxis
                    },
                    yAxis: {},
                    series: [{
                        name: '采样量',
                        type: 'bar',
                        data: samplings
                    },{
                        name: '合样量',
                        type: 'bar',
                        data: merges
                    }]
                });
            }
        })
</script>
</body>
</html>
