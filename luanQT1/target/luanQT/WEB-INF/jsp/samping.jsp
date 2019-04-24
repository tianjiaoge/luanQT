<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <script type="text/javascript" src="${ctx}/extend/assets/js/jquery-1.7.2.min.js"></script>
  <script type="text/javascript" src="${ctx}/extend/assets/js/playVoice.js"></script>
    <link rel="stylesheet" href="${ctx}/extend/assets/css/samping.css">
    <title>采样合样</title>
</head>
<script type="text/javascript">
    var websocket = null;
    //判断浏览器是否支持websocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/websocket/socketServer.do");
    }
    else {
        alert("您的浏览器版本过低，将影响您的操作，请升级浏览器");
    }
    websocket.onopen = onOpen;
    websocket.onmessage = onMessage;
    websocket.onerror = onError;
    websocket.onclose = onClose;

    function onOpen(openEvt) {
        $.get('${ctx}/sampling/loadData.do');
    }

    function onMessage(evt) {
        var jsonObject =  JSON.parse(evt.data);
        var hintTone = jsonObject.hintTone;
        switch (hintTone){
            case 'EmptyCard':
                playVoice("${ctx}/voice/emptyCard.mp3");
                break;
            case 'ShuaKaSuccess':
               /* playVoice("${ctx}/voice/1.wav");*/
                loadData(jsonObject);
                break;
            case 'SaoMaError':
                playVoice("${ctx}/voice/codeError.mp3");
                break;
            case 'SaoMaOverdue':
                playVoice("${ctx}/voice/yiheyang.mp3");
                break;
            case 'SaoMaSuccess':
                loadData(jsonObject);
                break;
        }
    }
    function loadData(jsonObject){
        $("#tbody").empty();
        var voiceList =new Array();
        var vl=0;
        var datas = jsonObject.data;
        for (var i = 0; i < datas.length; i++) {
            var str = $('<li></li>');
            var data = datas[i];
            var sampleCodes = data.sampleCodes.split(",");
            var  states = data.states.split(",");
            if(data.count==data.mergeRule){
                str.addClass('hy');
                //播报语音
                voiceList.push(data.sampleCodes.replace(/,/g,""));
            }
            for (var j = 0; j < sampleCodes.length; j++) {
                if(states[j]=='0'){
                    str.append('<span >'+sampleCodes[j]+'</span>');
                }else{
                    str.append('<span class="on" >'+sampleCodes[j]+'</span>');
                }
            }
            var hyBtnDom=$('<b class="hide"><a class="heyanbtn" href="javascript:sample(\''+data.mergeCode+'\');">合样</a></b>');
            str.append(hyBtnDom);
            $("#tbody").append(str);
        }
        //拼装语音文件
        if (voiceList.length > 0) {
            voiceSynthesis(voiceList);
        }
    }
    var arr=new Array();; //分隔出来的字符数组
    var controlVoiceIndex;
    function voiceSynthesis(voiceList){
        clearInterval(controlVoiceIndex);
        if(voiceList.length !=0){
            for(var i=0;i<voiceList.length;i++){
                arr.push("start");
                var strChar= voiceList[i].split("");
                arr = arr.concat(strChar);
                arr.push("end");//在数组最后一位插入“请合样”的语音
            }
            controlVoicePlay(arr);
        }
        controlVoiceIndex =setInterval("controlVoicePlay(arr)", arr.length*700);
    }
    function onError() {
        window.location.reload();
    }
    function onClose() {
        window.location.reload();
    }

    window.close=function()
    {
        websocket.onclose();
    }


</script>
<body>
<div class="container-fluid">
    <div class="fixed-top">
        <div class="title"><img src="/images/luanIcon.png"><H1>潞安焦化质检系统采样平台</H1></div>
        <div class="operation">
            <ul>
                <li id="voiceSwitch">
                    <img src="/extend/assets/images/icon-play.png" title="关闭语音播报">
                </li>
                <li>
                    <img src="/extend/assets/images/icon-input.png" title="手工输入">
                </li>
                <li>
                    <img src="/extend/assets/images/icon-print.png" title="补打二维码">
                </li>
                <li>
                    <img src="/extend/assets/images/icon-query.png" title="查询数据">
                </li>
            </ul>
        </div>
        <div class="user">操作员：${sessionUser.user.realName}<a href="${ctx}/logout.do" >退出</a></div>
    </div>
    <div class="display">
        <ul id="tbody">
        </ul>
    </div>
</div>

</body>
</html>
</html>
