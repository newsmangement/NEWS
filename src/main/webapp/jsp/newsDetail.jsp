<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/5/16
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>题目列表</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no" />
    <meta charset="utf-8">
    <link rel="stylesheet" href="../css/font-awesome.css" />
    <link rel="stylesheet" href="../css/bootstrap.css" />
    <link rel="stylesheet" href="../css/common.css" />
</head>
<body>
<div class="row">
    <div class="col-sm-12">
        <nav class="navbar navbar-default navbar-static-top admin-nav J_admin_nav">
            <a class="left-toggle pull-left" href="javascript:;">
                <i class="fa fa-bars fa-lg"></i>
            </a>
            <span class="pull-left logo-text menu-list-logo">&nbsp;新闻系统-后台</span>
            <ul class="nav navbar-nav hello-administrator pull-right">
                <li>
                    <span class="menu-list J_adminInfo">你好，小编！</span>
                </li>
                <li>
                    <a class="menu-list J_out" href="../login.html"><i class="fa fa-sign-out"></i>&nbsp;退出登录</a>
                </li>
            </ul>
        </nav>
    </div>
</div>
<!-- 侧边栏 -->
<ul class="nav nav-pills nav-stacked sidebar col-sm-3">
    <li id="firstMenu2" class="active">
        <a class="J_firstMenu" href="javascript:;"><i class="fa fa-bookmark-o"></i>&nbsp;&nbsp;&nbsp;&nbsp;<span >题目管理</span><i class="fa fa-angle-right angle-right"></i></a>
        <ul>
            <li><a class="J_menu" href="title-management-list.html">新闻列表</a></li>
            <li><a class="J_menu" href="title-management-addNews.html">新闻发布</a></li>
            <li><a class="J_menu" href="title-management-newsMangement.html">新闻管理</a></li>
            <li><a class="J_menu" href="title-management-search.html">新闻检索</a></li>
        </ul>
    </li>
</ul>
<!-- 内容 -->
<div class="page clearfix">
    <div class="holder">
        <div class="container">
            <div class="row">
                <div class="col-sm-12 margin-top--10">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h4>新闻列表</h4>
                        </div>
                        <div class="panel-body">
                            <form class="form-horizontal J_searchForm margin-left-per4">
                                <div id="newsType" class="col-sm-12 margin-bottom-10 margin-top--10 padding-left">

                                </div>
                            </form>
                            <div class="table-responsive col-sm-12">
                                <div class="panel panel-info box" questionNum="1" style="width: 92%;">
                                    <div class="top">
                                        <h4 class="top-left">News</h4>
                                        <!-- <span class="top-right top-right2 J_del">删除</span> -->
                                        <span class="top-right J_retract">收起</span>
                                    </div>
                                    <div id="newsDetail" class="panel-body panel-question">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 删除-->
<div class="modal fade" id="J_DEl" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <input type="hidden" class='hiddenDelete' value=''>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="  modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">删除</h4>
            </div>
            <div class="modal-body">
                <form action="" class="form-horizontal J_DelForm">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">  是否确认删除</label>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"   data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary J_delSure">确定</button>
            </div>
        </div>
    </div>
</div>


<input class="hidId" type="hidden"></input>
<input class="hidId1" type="hidden"></input>
<script src="../js/jquery-3.3.1.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/common.js"></script>
<script src="../js/title-management-list.js"></script>
</body>
</html>