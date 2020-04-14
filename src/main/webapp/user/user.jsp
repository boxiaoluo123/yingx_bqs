<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>

    $(function(){

        //初始化一个表格
        $("#userTable").jqGrid({
            url : "${path}/user/queryByPage",
            editurl : "${path}/user/edit",
            datatype : "json",  //返回 page:当前页  rows:数据（list）  total:总页数  records:总条数
            rowNum : 2,
            rowList : [ 2,10, 20, 30 ],
            pager : '#userPage', //分页工具栏
            viewrecords : true, //是否显示总条数
            styleUI: "Bootstrap",
            height: "auto",
            autowidth: true,
            colNames : [ 'ID', '用户名', '手机号', '头像', '签名','微信', '状态','创建日期' ],
            colModel : [


                {name : 'id',width : 30},
                {name : 'username',editable:true,width : 80},
                {name : 'phone',editable:true,width : 80},
                {name : 'headImg',edittype:"file",editable:true,width : 150,align:"center",
                    formatter:function(cellvalue, options, rowObject){

                        //return "<img src='${path}/upload/photo/"+rowObject.headImg+"' width='200px' height='100px' />";
                        return "<img src='"+rowObject.headImg+"' width='200px' height='100px' />";

                    }
                },
                {name : 'sign',editable:true,width : 80,align : "right"},
                {name : 'wechat',editable:true,width : 80,align : "right"},
                {name : 'status',width : 80,sortable : false,
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "<button onclick='updateStatus(\""+rowObject.id+"\",\""+cellvalue+"\")' class='btn btn-success' >冻结</button>";

                        } else {
                            return "<button onclick='updateStatus(\""+rowObject.id+"\",\""+cellvalue+"\")' class='btn btn-danger' >解除</button>";

                        }
                    }
                },
                {name : 'createDate',width : 80,sortable : false}
            ]

        });
        //操作表格
        $("#userTable").jqGrid('navGrid', '#userPage', {
            edit : true,add : true,del : true,addtext:"添加",edittext:"修改",deltext:"删除"},
            {closeAfterEdit: true,
                afterSubmit:function(response){
                $.ajaxFileUpload({
                    url:"${path}/user/edit",
                    type:"post",
                    dataType:"text",
                    fileElementId:"headImg",
                    data:{id:response.responseText},
                    success:function () {
                        //刷新表单
                        $("#userTable").trigger("reloadGrid");
                    }
                });
                //必须有返回值
                    return "hello";
                }

            },//修改
            {
                closeAfterAdd: true,
                //文件上传

                afterSubmit:function(response){
                    $.ajaxFileUpload({
                        url:"${path}/user/uploadUser",
                        type:"post",
                        dataType:"text",
                        fileElementId:"headImg",
                        data:{id:response.responseText},
                        success:function () {
                            //刷新表单
                            $("#userTable").trigger("reloadGrid");
                        }
                    });

                    //必须有返回值
                    return "hello";


                }
            },//添加----关闭对话框
            {closeAfterDel: true,
                afterSubmit:function(response){
                $.ajaxFileUpload({
                    url:"${path}/user/del",
                    type:"post",
                    dataType:"text",
                    fileElementId:"headImg",
                    data:{id:response.responseText},
                    success:function () {
                        //刷新表单
                        $("#userTable").trigger("reloadGrid");
                    }
                 });
                 //必须有返回值
                 return "hello";
                 }
            }
        );

            //发送验证码
            $("#basic-addon2").click(function(){

                var phone=$("#phoneInput").val();

                $.ajax({
                    url:"${path}/user/sendPhoneCode",
                    type:"post",
                    data:{"phone":phone},
                    success:function(message){
                        //刷新页面
                        alert(message)
                    }
                });

            });

        //导出所有用户数据
        $("#btnOut").click(function () {
            $.ajax({
                url:"${path}/user/outPutUser",
                type:"post",
                success:function () {
                    alert("已导出至桌面成功")
                }
            })
        });

    });
    //修改状态
    function updateStatus(id,status){
        //根据ID修改状态
        //id status

        if(status==1){
            //修改
            $.ajax({
                url:"${path}/user/edit",
                type:"post",
                data:{"id":id,"status":"0","oper":"edit"},
                success:function () {
                    //刷新页面
                    $("#userTable").trigger("reloadGrid")
                }
            });
        }else {
            $.ajax({
                url:"${path}/user/edit",
                type:"post",
                data:{"id":id,"status":"1","oper":"edit"},
                success:function () {
                    //刷新页面
                    $("#userTable").trigger("reloadGrid")
                }
            });
        }

    }

</script>
    <%--初始化一个面板--%>
    <div class="panel panel-info">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>用户信息</h2>
    </div>

    <%--发送验证码--%>
    <div class="input-group" style="width: 400px;height: 30px;float: right">
        <input id="phoneInput" type="text" class="form-control" placeholder="请输入验证码" aria-describedby="basic-addon2">
        <span class="input-group-addon" id="basic-addon2">点击发送验证码</span>
    </div>

   <%--标签页--%>
    <div class="nav nav-tabs">
        <li class="active"><a href="">用户信息</a></li>
    </div>

    <%--按钮--%>
        <div class="panel panel-body" id="btnOut">
            <button class="btn btn-info">导出用户信息</button>
        </div>

    <%--初始化表单--%>
    <table id="userTable" />


   <%--分页工具栏--%>
    <div id="userPage"></div>


</div>












