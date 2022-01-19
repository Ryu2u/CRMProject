<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
            request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <meta charset="UTF-8">

    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css"
          rel="stylesheet"/>

    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>

    <link rel="stylesheet" type="text/css" href="jquery/bs_pagination/jquery.bs_pagination.min.css">
    <script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
    <script type="text/javascript" src="jquery/bs_pagination/en.js"></script>

    <script type="text/javascript">

        $(function () {
            $.fn.datetimepicker.dates['zh-CN'] = {
                days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
                daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
                daysMin: ["日", "一", "二", "三", "四", "五", "六", "日"],
                months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
                monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
                today: "今天",
                suffix: [],
                meridiem: ["上午", "下午"]
            };
            //导入日历拾取器
            $(".time").datetimepicker({
                minView: "month",
                language: 'zh-CN',
                format: 'yyyy-mm-dd',
                autoclose: true,
                todayBtn: true,
                pickerPosition: "bottom-left"

            });


            $("#addBtn").click(function () {
                /*
                    操作模态窗口的方法:
                        需要操作的模态窗口的jquery对象,调用modal方法,为该方法传递参数 show打开模态窗口, hide: 关闭模态窗口
                 */
                $.ajax({
                    url: "workbench/activity/userList.do",
                    type: "get",
                    dataType: "json",
                    success: function (data) {
                        var html = "";
                        $.each(data, function (i, n) {
                            //每一个n就是一个User对象
                            html += "<option value='" + n.id + "'>" + n.name + "</option>"
                        })
                        $("#create-owner").html(html);
                        //将当前登录的用户,设置为下拉框默认的选项
                        $("#create-owner").val("${user.id}");
                    }
                });
                $("#createActivityModal").modal("show");
            })

            //为保存按钮绑定事件,执行添加操作
            $("#saveBtn").click(function () {
                //
                $.ajax({
                    url: "workbench/activity/save.do",
                    data: {
                        "owner": $.trim($("#create-owner").val()),
                        "name": $.trim($("#create-name").val()),
                        "startDate": $.trim($("#create-startDate").val()),
                        "endDate": $.trim($("#create-endDate").val()),
                        "cost": $.trim($("#create-cost").val()),
                        "description": $.trim($("#create-description").val()),
                    },
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        //返回添加信息
                        if (data.success) {
                            //添加成功
                            alert("保存成功");
                            //刷新界面
                            //清空模态窗口
                            $("#activityFrom")[0].reset();
                            //关闭模态窗口
                            $("#createActivityModal").modal("hide");

                            /*
                            *   ($("#activityPage").bs_pagination('getOption', 'currentPage')
                            *   操作后停留在当前页
                            *   $("#activityPage").bs_pagination('getOption', 'rowsPerPage'))
                            *   操作后停留在设置好的每页展现记录数
                            *
                            * */
                            pageList(1
                                ,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));


                        } else {
                            //添加失败
                            alert("保存失败!");
                        }
                    }
                });


            });


            //为查询按钮绑定事件
            $("#searchBtn").click(function () {
                //将数据保存到隐藏域中
                $("#hidden-name").val($.trim($("#search-name").val()));
                $("#hidden-owner").val($.trim($("#search-owner").val()));
                $("#hidden-startDate").val($.trim($("#search-startDate").val()));
                $("#hidden-endDate").val($.trim($("#search-endDate").val()));

                pageList(1, 4);

            });

            pageList(1, 4);

            //对于全选框执行全选操作
            $("#check").click(function () {
                $("input[name=checkActivity]").prop("checked", this.checked);
            });
            /*
                动态生成的元素需要以on方法的形式来触发事件
                    语法:
                        $(需要绑定元素的'有效的'外层元素).on(绑定事件的方式,需要绑定的元素的jquery对象,回调函数)
                        注意: 不能使用动态生成的元素
             */
            $("#activityBody").on("click", $("input[name=checkActivity]"), function () {
                $("#check").prop("checked", $("input[name=checkActivity]").length == $("input[name=checkActivity]:checked").length);

            });

            //为按钮绑定删除事件
            $("#deleteBtn").click(function () {
                /*
                    需要删除的是使用复选框选中的对象
                 */
                var $checkAct = $("input[name=checkActivity]:checked");
                if ($checkAct.length == 0) {
                    alert("请选择需要删除的记录");
                } else {
                    if (confirm("确定删除记录吗?")) {
                        var param = "";
                        $.each($checkAct, function (i, n) {
                            var id = $(n).val();
                            param += "id=" + id + "&";
                        })
                        param = param.substr(0, param.length - 1);

                        $.ajax({
                            url: "workbench/activity/delete.do",
                            data: param,
                            type: "get",
                            dataType: "json",
                            success: function (data) {
                                if (data.success) {
                                    alert("删除成功!");
                                    //刷新界面
                                    pageList(1
                                        ,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));
                                } else {
                                    alert("删除失败!");
                                }
                            }

                        });
                    }


                }

            });

            //绑定修改操作
            $("#editBtn").click(function () {
                //获取选中的id值
                var $check = $("input[name=checkActivity]:checked");
                if ($check.length == 0) {
                    alert("请选择要修改的对象");
                } else if ($check.length > 1) {
                    alert("一次只能修改一个对象");
                    $("input[type=checkbox]:checked").attr("checked", false);
                } else {
                    $("#editActivityModal").modal("show");
                }

                var id = $check.val();
                $.ajax({
                    url: "workbench/activity/findActivityById.do",
                    data: {
                        "id": id
                    },
                    type: "get",
                    dataType: "json",
                    success: function (data) {
                        //返回一个activity对象和user对象list集合
                        var html = "";
                        $.each(data.user, function (i, n) {
                            if (n.name == data.activity.owner) {
                                $("#owner").val(n.id);
                                html += "<option  selected value=" + n.id + " >" + n.name + "</option>";
                            } else {
                                html += "<option value=" + n.id + ">" + n.name + "</option>";
                            }
                        })
                        $("#edit-owner").html(html);
                        $("#edit-name").val(data.activity.name);
                        $("#actId").val(id);
                        $("#edit-startDate").val(data.activity.startDate);
                        $("#edit-endDate").val(data.activity.endDate);
                        $("#edit-cost").val(data.activity.cost);
                        $("#edit-description").val(data.activity.description);

                    }
                });
            });

            //绑定update更新按钮的操作
            $("#updateBtn").click(function(){

                //更新操作
                $.ajax({
                    url:"workbench/activity/updateActivity.do",
                    data:{
                        "id":$("#actId").val(),
                        "owner":$("#edit-owner").val(),
                        "name":$("#edit-name").val(),
                        "startDate":$("#edit-startDate").val(),
                        "endDate":$("#edit-endDate").val(),
                        "cost":$("#edit-cost").val(),
                        "description":$("#edit-description").val(),
                        "activityId": $("#owner").val()
                    },
                    type:"post",
                    dataType:"json",
                    success: function (data){
                        //返回成功还是失败
                        if(data.success){
                            alert("更新成功");
                            //关闭模态窗口
                            $("#editActivityModal").modal("hide");
                            //刷新页面
                            pageList($("#activityPage").bs_pagination('getOption', 'currentPage')
                                ,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));
                        }else{
                            alert("更新失败");

                        }
                        $("input[type=checkbox]:checked").attr("checked", false);
                    }
                });

            });

        });

        /*在哪些情况下,需要调用pageList方法(什么情况下需要刷新一下市场活动,列表)
            1)点击左侧菜单中的"市场活动"超链接,需要刷新市场活动列表,调用pageLIst方法
            2)添加,修改,删除后, 需要刷新市场活动列表,调用pageList方法
            3)点击查询的时候,需要刷新市场活动列表,调用pageList方法
            4)点击分页组件的时候,调用pageList方法*/
        function pageList(pageNo, pageSize) {
            //查询前将隐藏域中的信息重新复制到搜索框中
            $("#search-name").val($.trim($("#hidden-name").val()));
            $("#search-owner").val($.trim($("#hidden-owner").val()));
            $("#search-startDate").val($.trim($("#hidden-startDate").val()));
            $("#search-endDate").val($.trim($("#hidden-endDate").val()));
            $("#check").attr("checked", false);
            $.ajax({
                url: "workbench/activity/pageList.do",
                data: {
                    "pageNo": pageNo,
                    "pageSize": pageSize,
                    "name": $.trim($("#search-name").val()),
                    "owner": $.trim($("#search-owner").val()),
                    "startDate": $.trim($("#search-startDate").val()),
                    "endDate": $.trim($("#search-endDate").val())
                },
                type: "get",
                dataType: "json",
                success: function (data) {
                    if (data.total == 0) {
                        alert("未查询到数据,请重试!");
                    }
                    var html;
                    /*
                    data返回的值
                        {"total":100,"dataList":[{市场活动1},{市场活动12},{市场活动13}]}
                     */
                    $.each(data.dataList, function (i, n) {

                        html += '<tr class="activity">';
                        html += '<td><input type="checkbox" name="checkActivity" value="' + n.id + '"/></td>';
                        html
                            += '<td><a style=\"text-decoration: none; cursor: pointer;\" onclick=\"window.location.href=\'workbench/activity/detail.jsp?id=' + n.id + '\';\">' + n.name + '</a></td>';
                        html += '<td>' + n.owner + '</td>';
                        html += '<td>' + n.startDate + '</td>';
                        html += '<td>' + n.endDate + '</td>';
                        html += '</tr>';
                    });
                    $("#activityBody").html(html);

                    var totalPages = data.total % pageSize == 0 ? data.total / pageSize : parseInt(data.total / pageSize) + 1;

                    //数据处理完毕后,结合分页查询,对前端展现分页信息
                    $("#activityPage").bs_pagination({
                        currentPage: pageNo, // 页码
                        rowsPerPage: pageSize, // 每页显示的记录条数
                        maxRowsPerPage: 20, // 每页最多显示的记录条数
                        totalPages: totalPages, // 总页数
                        totalRows: data.total, // 总记录条数

                        visiblePageLinks: 3, // 显示几个卡片

                        showGoToPage: true,
                        showRowsPerPage: true,
                        showRowsInfo: true,
                        showRowsDefaultInfo: true,
                        //该回调函数是在点击分页组件的时候触发的
                        onChangePage: function (event, data) {
                            pageList(data.currentPage, data.rowsPerPage);
                        }
                    });

                }
            });

        }

    </script>
</head>
<body>
<input type="hidden" id="hidden-name"/>
<input type="hidden" id="hidden-owner"/>
<input type="hidden" id="hidden-startDate"/>
<input type="hidden" id="hidden-endDate"/>

<!-- 创建市场活动的模态窗口 -->
<div class="modal fade" id="createActivityModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 85%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
            </div>
            <div class="modal-body">

                <form id="activityFrom" class="form-horizontal" role="form">

                    <div class="form-group">
                        <label for="create-Owner" class="col-sm-2 control-label">所有者<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <select class="form-control" id="create-owner">

                            </select>
                        </div>
                        <label for="create-Name" class="col-sm-2 control-label">名称<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="create-startDate" class="col-sm-2 control-label">开始日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control time" id="create-startDate" readonly>
                        </div>
                        <label for="create-endDate" class="col-sm-2 control-label">结束日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control time" id="create-endDate" readonly>
                        </div>
                    </div>
                    <div class="form-group">

                        <label for="create-cost" class="col-sm-2 control-label">成本</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-cost">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="create-description" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10" style="width: 81%;">
                            <textarea class="form-control" rows="3" id="create-description"></textarea>
                        </div>
                    </div>

                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="saveBtn">保存</button>
            </div>
        </div>
    </div>
</div>

<!-- 修改市场活动的模态窗口 -->
<div class="modal fade" id="editActivityModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 85%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" role="form">

                    <div class="form-group">
                        <input type="hidden" id="actId"/>
                        <input type="hidden" id="owner"/>
                        <label for="edit-owner" class="col-sm-2 control-label">所有者<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <select class="form-control" id="edit-owner">

                            </select>
                        </div>
                        <label for="edit-name" class="col-sm-2 control-label">名称<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-name" value="">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-startDate" class="col-sm-2 control-label">开始日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control time" id="edit-startDate" value="2020-10-10">
                        </div>
                        <label for="edit-endDate" class="col-sm-2 control-label">结束日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control time" id="edit-endDate" value="2020-10-20">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-cost" class="col-sm-2 control-label">成本</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-cost" value="5,000">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-description" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10" style="width: 81%;">
                            <textarea class="form-control" rows="3" id="edit-description"></textarea>
                        </div>
                    </div>

                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="updateBtn" class="btn btn-primary" data-dismiss="modal">更新</button>
            </div>
        </div>
    </div>
</div>


<div>
    <div style="position: relative; left: 10px; top: -10px;">
        <div class="page-header">
            <h3>市场活动列表</h3>
        </div>
    </div>
</div>
<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
    <div style="width: 100%; position: absolute;top: 5px; left: 10px;">

        <div class="btn-toolbar" role="toolbar" style="height: 80px;">
            <form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">名称</div>
                        <input class="form-control" type="text" id="search-name">
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">所有者</div>
                        <input class="form-control" type="text" id="search-owner">
                    </div>
                </div>


                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">开始日期</div>
                        <input class="form-control" type="text" id="startTime" id="search-startTime"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">结束日期</div>
                        <input class="form-control" type="text" id="endTime" id="search-endTime">
                    </div>
                </div>

                <button type="button" id="searchBtn" class="btn btn-default">查询</button>

            </form>
        </div>
        <div class="btn-toolbar" role="toolbar"
             style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
            <div class="btn-group" style="position: relative; top: 18%;">
                <%--
                    点击该按钮,观察两个属性和属性值
                    data-toggle="modal"
                        表示触发该按钮,将要打开一个模态窗口
                    data-target="#createActivityModal
                        表示要打开哪个模态窗口,通过"#id"的形式定位到模态窗口
                --%>
                <button type="button" class="btn btn-primary" id="addBtn"><span class="glyphicon glyphicon-plus"></span>
                    创建
                </button>
                <button id="editBtn" type="button" class="btn btn-default" data-toggle="modal"
                ><span
                        class="glyphicon glyphicon-pencil"></span> 修改
                </button>
                <button type="button" class="btn btn-danger" id="deleteBtn"><span
                        class="glyphicon glyphicon-minus"></span> 删除123
                </button>
            </div>

        </div>
        <div style="position: relative;top: 10px;">
            <table class="table table-hover">
                <thead>
                <tr style="color: #B3B3B3;">
                    <td><input type="checkbox" id="check"/></td>
                    <td>名称</td>
                    <td>所有者</td>
                    <td>开始日期</td>
                    <td>结束日期</td>
                </tr>
                </thead>
                <tbody id="activityBody">

                </tbody>
            </table>
        </div>

        <div style="height: 50px; position: relative;top: 30px;">
            <div id="activityPage">分页插件</div>
        </div>

    </div>

</div>
</body>
</html>