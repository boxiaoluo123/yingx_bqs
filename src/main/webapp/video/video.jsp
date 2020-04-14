<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>

    $(function(){
        //初始化一个表格
        $("#videoTable").jqGrid({
            url : "${path}/video/queryByVideoPage",
            editurl : "${path}/video/edit",
            datatype : "json",  //返回 page:当前页  rows:数据（list）  total:总页数  records:总条数
            rowNum : 5,
            rowList : [ 5,10, 20, 30 ],
            pager : '#videoPager', //分页工具栏
            viewrecords : true, //是否显示总条数
            styleUI: "Bootstrap",
            height: "auto",
            autowidth: true,
            colNames : ['ID', '标题', '描述', '视频', '上传时间','用户Id', '类别id','分组id'],
            colModel : [


                {name : 'id',width : 55},
                {name : 'title',editable:true,width : 90},
                {name : 'brief',editable:true,width : 100},
                {name : 'path',editable:true,width : 400,align : "center",edittype:"file",
                    formatter:function(cellvalue, options, rowObject){
                        return "<video id='media' src='"+cellvalue+"' controls width='400px' heigt='800px' poster='"+rowObject.cover+"'/>";
                    }
                },
                {name : 'uploadTime',width : 80,align : "right"},
                {name : 'userId',width : 80,align : "right"},
                {name : 'categoryId',width : 80,align : "center"},
                {name : 'groupId',width : 150,sortable : false}
            ]

        });
        //操作表格
        $("#videoTable").jqGrid('navGrid', '#videoPager', {
                edit : true,add : true,del : true,addtext:"添加",edittext:"修改",deltext:"删除"},
            {
                closeAfterEdit:true,
                beforeShowForm :function(obj){
                    obj.find("#path").attr("disabled",true);//禁用input
                }
            }, //执行修改之后的额外操作
            {
                closeAfterAdd:true, //关闭添加的对话框
                afterSubmit:function(data){
                    $.ajaxFileUpload({
                        fileElementId: "path",    //需要上传的文件域的ID，即<input type="file">的ID。
                        url: "${path}/video/uploadVdieo", //后台方法的路径
                        type: 'post',   //当要提交自定义参数时，这个参数要设置成post
                        data:{id:data.responseText},  //responseText: "74141b4c-f337-4ab2-ada2-c1b07364adee"
                        success: function() {   //提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
                            //刷新页面
                            $("#videoTable").trigger("reloadGrid");
                        }
                    });
                    //必须要有返回值
                    return "hello";
                }
            }, //执行添加之后的额外操作
            {closeAfterDel: true,
                afterSubmit:function(data){

                    $.ajaxFileUpload({
                        fileElementId: "path",    //需要上传的文件域的ID，即<input type="file">的ID。
                        url: "${path}/video/uploadVdieo", //后台方法的路径
                        type: 'post',   //当要提交自定义参数时，这个参数要设置成post
                        data:{id:data.responseText},  //responseText: "74141b4c-f337-4ab2-ada2-c1b07364adee"
                        success: function() {   //提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
                            //刷新页面
                            $("#videoTable").trigger("reloadGrid");


                            //向警告框中追加错误信息
                            $("#showMsg").html(data.responseJSON.message);
                            //展示警告框
                            $("#deleteMsg").show();

                            //自动关闭
                            setTimeout(function () {
                                $("#deleteMsg").hide();
                            }, 3000);
                        }
                });
                    return "hello";

                }
            } //执行删除之后的额外操作

        );
    });

</script>
<%--初始化一个面板--%>
<div class="panel panel-warning">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>视频信息</h2>
    </div>

    <%--标签页--%>
    <div class="nav nav-tabs">
        <li class="active"><a href="">视频信息</a></li>
    </div>


    <%--初始化表单--%>
    <table id="videoTable" />


    <%--分页工具栏--%>
    <div id="videoPager"></div>


</div>