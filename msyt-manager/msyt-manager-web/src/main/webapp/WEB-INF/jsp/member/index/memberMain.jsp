<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>我的地盘 - 禅道</title>
    <script language="Javascript">
        var config = {
        "webRoot": "\/zentao\/",
        "appName": "",
        "cookieLife": 30,
        "requestType": "PATH_INFO",
        "requestFix": "-",
        "moduleVar": "m",
        "methodVar": "f",
        "viewVar": "t",
        "defaultView": "html",
        "themeRoot": "\/zentao\/theme\/",
        "currentModule": "my",
        "currentMethod": "index",
        "clientLang": "zh-cn",
        "requiredFields": "",
        "router": "\/zentao\/index.php",
        "save": "\u4fdd\u5b58",
        "runMode": "",
        "timeout": 30000,
        "pingInterval": ""
    };

    </script>
    <script src="${static$domain}/plugins/zentao/js/all.js" type="text/javascript"></script>
    <script src="${static$domain}/plugins/zentao/js/jquery/tablesorter/min.js" type="text/javascript"></script>
    <script src='${static$domain}/plugins/zentao/js/jquery/tablesorter/metadata.js' type='text/javascript'></script>
    <link rel="stylesheet" href="${static$domain}/plugins/zentao/theme/default/zh-cn.default.css"  type="text/css" media="screen">
    <link rel="stylesheet" href="${static$domain}/plugins/zentao/css/dropdown.css" type="text/css" media="screen">
    <link rel="stylesheet" href="${static$domain}/plugins/zentao/js/tablesort/css/tablesort.css" type="text/css" media="screen">
    <link rel='stylesheet' href='${static$domain}/plugins/zentao/js/dashboard/css/dashboard.css' type='text/css'  />
    <link rel='stylesheet' href='${static$domain}/plugins/zentao/js/tablesort/css/tablesort.css' type='text/css'  />
    <script src='${static$domain}/plugins/zentao/js/dashboard/dashboard.js' type='text/javascript'></script>
    <script src='${static$domain}/plugins/zentao/js/main/browserNotice.js' type='text/javascript'></script>
    <script src='${static$domain}/plugins/zentao/js/main/footer.js' type='text/javascript'></script>
    <script src='${static$domain}/plugins/zentao/js/tablesort/tablesort.js'type='text/javascript'></script>
    <!--[if lt IE 9]>
    <script src='${static$domain}/plugins/zentao/js/html5shiv/min.js' type='text/javascript'></script>
    <script src='${static$domain}/plugins/zentao/js/respond/min.js' type='text/javascript'></script>
    <![endif]-->
    <!--[if lt IE 10]>
    <script src='${static$domain}/plugins/zentao/js/jquery/placeholder/min.js' type='text/javascript'></script>
    <![endif]-->

    <!--[if lte IE 8]>
    <script src='${static$domain}/plugins/zentao/js/chartjs/excanvas.min.js' type='text/javascript'></script>
    <![endif]-->
    <script src="${static$domain}/plugins/zentao/js/chartjs/chart.min.js"
            type="text/javascript"></script>
</head>
<body class="m-my-index" style="">
<script>
    var noResultsMatch = '没有匹配结果';
    var chooseUsersToMail = '选择要发信通知的用户...';
    var defaultChosenOptions = {
        no_results_text: noResultsMatch,
        width: '100%',
        allow_single_deselect: true,
        disable_search_threshold: 1,
        placeholder_text_single: ' ',
        placeholder_text_multiple: ' ',
        search_contains: true
    };
</script>
<!--/resources/plugins/zentao/theme/default/images/main/green.png-->
<div id="wrap">
    <div class="outer" style="min-height: 562px;">


        <div class="dashboard dashboard-draggable" id="dashboard" data-confirm-remove-block="确定移除区块【{0}】吗？">
            <div class="dashboard-actions"><a href="#"
                                              data-toggle="modal" data-type="ajax" data-width="700" data-title="添加区块"><i
                    class="icon icon-plus" title="" data-toggle="tooltip" data-placement="left"
                    data-original-title="添加区块"></i></a></div>
            <div class="dashboard-empty-message hide">
                <a href="#" data-toggle="modal" data-type="ajax"
                   data-width="700" class="btn btn-primary"><i class="icon icon-plus"></i> 添加区块</a>
            </div>
            <div class="row">
                <!-- 产品列表 begin-->
                <div class="col-sm-6 col-md-4">
                    <!--data-url="/zentao/block-printBlock-164-my.html"-->
                    <div class="panel panel-block panel-default" id="block164" data-id="164" data-name="产品列表"  style="height: 240px;">
                        <div class="panel-heading">
                            <div class="panel-actions">
                                <a href="#" class="panel-action drag-disabled panel-action-more"> <i class="icon icon-more"></i></a>
                                <div class="dropdown">
                                    <a href="javascript:;" data-toggle="dropdown" class="panel-action"><i class="icon icon-ellipsis-v"></i></a>
                                    <ul class="dropdown-menu pull-right">
                                        <li><a href="javascript:;" class="refresh-panel"><i class="icon-repeat"></i> 刷新</a></li>
                                        <li><a data-toggle="modal" href="#" class="edit-block" data-title="产品列表" data-icon="icon-pencil"><i class="icon-pencil"></i> 编辑</a></li>
                                        <li><a href="javascript:;" class="remove-panel"><i class="icon-remove"></i> 移除</a></li>
                                    </ul>
                                </div>
                            </div>
                            <span class="panel-title">产品列表</span>
                        </div>
                        <div class="panel-body no-padding"><table class="table tablesorter table-data table-hover table-fixed table-striped block-product tablesorter-default hasSaveSort" role="grid">
                            <thead style="visibility: visible;">
                            <tr class="text-center tablesorter-headerRow" role="row">
                                <th class="text-left header tablesorter-headerUnSorted" data-column="0" tabindex="0" scope="col" role="columnheader" aria-disabled="false" unselectable="on" style="-moz-user-select: none;" aria-sort="none" aria-label="产品名称: No sort applied, activate to apply an ascending sort"><div class="tablesorter-header-inner">产品名称</div></th>
                                <th title="需求" data-column="1" class="header tablesorter-headerUnSorted" tabindex="0" scope="col" role="columnheader" aria-disabled="false" unselectable="on" style="-moz-user-select: none;" aria-sort="none" aria-label="激活: No sort applied, activate to apply an ascending sort" width="65"><div class="tablesorter-header-inner">激活</div></th>
                                <th title="需求" data-column="2" class="header tablesorter-headerUnSorted" tabindex="0" scope="col" role="columnheader" aria-disabled="false" unselectable="on" style="-moz-user-select: none;" aria-sort="none" aria-label="已变更: No sort applied, activate to apply an ascending sort" width="65"><div class="tablesorter-header-inner">已变更</div></th>
                                <th title="需求" data-column="3" class="header tablesorter-headerUnSorted" tabindex="0" scope="col" role="columnheader" aria-disabled="false" unselectable="on" style="-moz-user-select: none;" aria-sort="none" aria-label="草稿: No sort applied, activate to apply an ascending sort" width="65"><div class="tablesorter-header-inner">草稿</div></th>
                                <th data-column="4" class="header tablesorter-headerUnSorted" tabindex="0" scope="col" role="columnheader" aria-disabled="false" unselectable="on" style="-moz-user-select: none;" aria-sort="none" aria-label="计划数: No sort applied, activate to apply an ascending sort" width="65"><div class="tablesorter-header-inner">计划数</div></th>
                                <th data-column="5" class="header headerSortUp" tabindex="0" scope="col" role="columnheader" aria-disabled="false" unselectable="on" style="-moz-user-select: none;" aria-sort="ascending" aria-label="发布数: Ascending sort applied, activate to apply a descending sort" width="65"><div class="tablesorter-header-inner">发布数</div></th>
                                <th title="Bug" data-column="6" class="header tablesorter-headerUnSorted" tabindex="0" scope="col" role="columnheader" aria-disabled="false" unselectable="on" style="-moz-user-select: none;" aria-sort="none" aria-label="相关BUG: No sort applied, activate to apply an ascending sort" width="65"><div class="tablesorter-header-inner">相关BUG</div></th>
                                <th title="Bug" data-column="7" class="header tablesorter-headerUnSorted" tabindex="0" scope="col" role="columnheader" aria-disabled="false" unselectable="on" style="-moz-user-select: none;" aria-sort="none" aria-label="未解决: No sort applied, activate to apply an ascending sort" width="65"><div class="tablesorter-header-inner">未解决</div></th>
                            </tr>
                            </thead>
                            <tbody aria-live="polite" aria-relevant="all">

                            <tr data-url="/zentao/product-browse-1.html" class="text-center">
                                <td class="text-left" title="洋桃平台V2.0.0需求">洋桃平台V2.0.0需求</td>
                                <td>3</td>
                                <td>0</td>
                                <td>0</td>
                                <td>2</td>
                                <td>0</td>
                                <td>195</td>
                                <td>41</td>
                            </tr></tbody>
                        </table>
                            <script>
                                if(typeof(dataTable) == 'function')$('.block-product').dataTable();
                            </script>
                        </div>
                    </div>
                    <div class="resize-handle resize-horizontal"><i class="icon icon-resize-h"></i></div><div class="resize-handle resize-vertical"><i class="icon icon-resize-v"></i></div></div>
                <!-- 产品列表 end-->
                <!-- 流程图 begin-->
                <div class="col-sm-6 col-md-8">
                    <div class="panel panel-block panel-default" id="block158" data-id="158" data-name="流程图"
                       style="height: 240px;"> <!--  data-url="#" -->
                        <div class="panel-heading">
                            <div class="panel-actions">
                                <a href="#"
                                   class="panel-action drag-disabled panel-action-more"> <i class="icon icon-more"></i></a>
                                <div class="dropdown">
                                    <a href="javascript:;" data-toggle="dropdown" class="panel-action"><i
                                            class="icon icon-ellipsis-v"></i></a>
                                    <ul class="dropdown-menu pull-right">
                                        <li><a href="javascript:;" class="refresh-panel"><i class="icon-repeat"></i> 刷新</a>
                                        </li>
                                        <li><a data-toggle="modal"
                                               href="#"
                                               class="edit-block" data-title="流程图" data-icon="icon-pencil"><i
                                                class="icon-pencil"></i> 编辑</a></li>
                                        <li><a href="javascript:;" class="remove-panel"><i class="icon-remove"></i>
                                            移除</a></li>
                                    </ul>
                                </div>
                            </div>
                            <span class="panel-title">流程图</span>
                        </div>
                        <div class="panel-body no-padding">
                            <table class="table tablesorter table-data table-hover block-todo table-fixed">
                                <thead style="visibility: visible;">
                                <tr>
                                    <th width="90">日期</th>
                                    <th width="40">P</th>
                                    <th> 名称</th>
                                    <th width="50">开始</th>
                                    <th width="50">结束</th>
                                </tr>
                                </thead>
                            </table>
                            <script>
                                if (typeof(dataTable) == 'function') $('.block-todo').dataTable();
                            </script>
                        </div>
                    </div>
                    <div class="resize-handle resize-horizontal"><i class="icon icon-resize-h"></i></div>
                    <div class="resize-handle resize-vertical"><i class="icon icon-resize-v"></i></div>
                </div>
                <!-- 流程图 end-->

                <!-- 项目列表 begin -->
                <div class="col-sm-6 col-md-4">
                    <div class="panel panel-block panel-default" id="block159" data-id="159" data-name="项目列表"
                         style="height: 240px;">
                        <div class="panel-heading">
                            <div class="panel-actions">
                                <a href="#"
                                   class="panel-action drag-disabled panel-action-more"> <i class="icon icon-more"></i></a>
                                <div class="dropdown">
                                    <a href="javascript:;" data-toggle="dropdown" class="panel-action"><i
                                            class="icon icon-ellipsis-v"></i></a>
                                    <ul class="dropdown-menu pull-right">
                                        <li><a href="javascript:;" class="refresh-panel"><i class="icon-repeat"></i> 刷新</a>
                                        </li>
                                        <li><a data-toggle="modal"
                                               href="#"
                                               class="edit-block" data-title="项目列表" data-icon="icon-pencil"><i
                                                class="icon-pencil"></i> 编辑</a></li>
                                        <li><a href="javascript:;" class="remove-panel"><i class="icon-remove"></i>
                                            移除</a></li>
                                    </ul>
                                </div>
                            </div>
                            <span class="panel-title">项目列表</span>
                        </div>
                        <div class="panel-body no-padding">
                            <style>
                                .projectline {
                                    padding: 2px !important
                                }
                            </style>

                            <script>
                                var isIE = $.zui.browser.isIE();
                                jQuery.fn.projectLine = function (setting) {
                                    var $lines = $(this);
                                    if (isIE && $.zui.browser.ie < 9 && $lines.length > 10) return;

                                    $lines.each(function () {
                                        var $e = $(this);
                                        var options = $.extend({values: $e.attr('values')}, $e.data(), setting),
                                            height = $e.height() - 4,
                                            values = [],
                                            maxWidth = $e.width() - 4;
                                        var strValues = options.values.split(','), maxValue = 0;
                                        for (var i in strValues) {
                                            var v = parseFloat(strValues[i]);
                                            if (v != NaN) {
                                                values.push(v);
                                                maxValue = Math.max(v, maxValue);
                                            }
                                        }

                                        var scaleSteps = Math.min(maxValue, 30);
                                        var scaleStepWidth = Math.ceil(maxValue / scaleSteps);

                                        var width = Math.min(maxWidth, Math.max(10, values.length * maxWidth / 30));
                                        var canvas = $e.children('canvas');
                                        if (!canvas.length) {
                                            $e.append('<canvas class="projectline-canvas"></canvas>');
                                            canvas = $e.children('canvas');
                                            if ($.zui.browser.ie == 8) G_vmlCanvasManager.initElement(canvas[0]);
                                        }
                                        canvas.attr('width', width).attr('height', height);
                                       /* $e.data('projectLineChart', new Chart(canvas[0].getContext("2d")).Line(
                                            {
                                                labels: values,
                                                datasets: [{
                                                    fillColor: "rgba(0,0,255,0.25)",
                                                    strokeColor: "rgba(0,0,255,1)",
                                                    pointColor: "rgba(255,136,0,1)",
                                                    pointStrokeColor: "#fff",
                                                    data: values
                                                }]
                                            },
                                            {
                                                animation: !isIE,
                                                scaleOverride: true,
                                                scaleStepWidth: Math.ceil(maxValue / 10),
                                                scaleSteps: 10,
                                                scaleStartValue: 0
                                            }));*/
                                    });
                                }

                                $(function () {
                                    $('.projectline').projectLine();
                                });
                            </script>
                            <div id="projectbox-1251581554">
                                <table class="table tablesorter table-data table-hover table-striped table-fixed block-project tablesorter-default hasSaveSort"
                                       role="grid">
                                    <thead style="visibility: visible;">
                                    <tr class="text-center tablesorter-headerRow" role="row">
                                        <th class="text-left header tablesorter-headerUnSorted" data-column="0"
                                            tabindex="0" scope="col" role="columnheader" aria-disabled="false"
                                            unselectable="on" style="-moz-user-select: none;" aria-sort="none"
                                            aria-label="项目名称: No sort applied, activate to apply an ascending sort">
                                            <div class="tablesorter-header-inner">项目名称</div>
                                        </th>
                                        <th data-column="1" class="header tablesorter-headerUnSorted" tabindex="0"
                                            scope="col" role="columnheader" aria-disabled="false" unselectable="on"
                                            style="-moz-user-select: none;" aria-sort="none"
                                            aria-label="结束日期: No sort applied, activate to apply an ascending sort"
                                            width="80">
                                            <div class="tablesorter-header-inner">结束日期</div>
                                        </th>
                                        <th data-column="2" class="header tablesorter-headerUnSorted" tabindex="0"
                                            scope="col" role="columnheader" aria-disabled="false" unselectable="on"
                                            style="-moz-user-select: none;" aria-sort="none"
                                            aria-label="状态: No sort applied, activate to apply an ascending sort"
                                            width="50">
                                            <div class="tablesorter-header-inner">状态</div>
                                        </th>
                                        <th data-column="3" class="header tablesorter-headerUnSorted" tabindex="0"
                                            scope="col" role="columnheader" aria-disabled="false" unselectable="on"
                                            style="-moz-user-select: none;" aria-sort="none"
                                            aria-label="总预计: No sort applied, activate to apply an ascending sort"
                                            width="45">
                                            <div class="tablesorter-header-inner">总预计</div>
                                        </th>
                                        <th data-column="4" class="header tablesorter-headerUnSorted" tabindex="0"
                                            scope="col" role="columnheader" aria-disabled="false" unselectable="on"
                                            style="-moz-user-select: none;" aria-sort="none"
                                            aria-label="总消耗: No sort applied, activate to apply an ascending sort"
                                            width="45">
                                            <div class="tablesorter-header-inner">总消耗</div>
                                        </th>
                                        <th data-column="5" class="header tablesorter-headerUnSorted" tabindex="0"
                                            scope="col" role="columnheader" aria-disabled="false" unselectable="on"
                                            style="-moz-user-select: none;" aria-sort="none"
                                            aria-label="总剩余: No sort applied, activate to apply an ascending sort"
                                            width="45">
                                            <div class="tablesorter-header-inner">总剩余</div>
                                        </th>
                                        <th data-column="6" class="header tablesorter-headerUnSorted" tabindex="0"
                                            scope="col" role="columnheader" aria-disabled="false" unselectable="on"
                                            style="-moz-user-select: none;" aria-sort="none"
                                            aria-label="进度: No sort applied, activate to apply an ascending sort"
                                            width="115">
                                            <div class="tablesorter-header-inner">进度</div>
                                        </th>
                                        <th class="{sorter: false} header sorter-false tablesorter-headerUnSorted"
                                            data-column="7" tabindex="0" scope="col" role="columnheader"
                                            aria-disabled="true" unselectable="on" style="-moz-user-select: none;"
                                            aria-sort="none" width="100">
                                            <div class="tablesorter-header-inner">燃尽图</div>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody aria-live="polite" aria-relevant="all">
                                    <tr data-url="#" class="text-center odd">
                                        <td class="text-left" title="商品中心、分布式架构需求"><a
                                                href="#"
                                                title="商品中心、分布式架构需求">商品中心、分布式架构需求</a>
                                        </td>
                                        <td>2017-06-16</td>
                                        <td class="status-doing">进行中</td>
                                        <td>0</td>
                                        <td>0</td>
                                        <td>0</td>
                                        <td class="text-left">
                                            <img class="progressbar"
                                                 src="${static$domain}/plugins/zentao/theme/default/images/main/green.png"
                                                 alt="" width="1" height="16">
                                            <small>0%</small>
                                        </td>
                                        <td id="spark-0" class="spark text-left pd-0 sparked" values="">
                                            <canvas class="projectline-canvas" width="10" height="26"
                                                    style="width: 10px; height: 26px;"></canvas>
                                        </td>
                                    </tr>
                                    <tr data-url="#" class="text-center even">
                                        <td class="text-left" title="洋桃平台V2.0.0"><a
                                                href="#"
                                                title="洋桃平台V2.0.0">洋桃平台V2.0.0</a>
                                        </td>
                                        <td>2017-05-09</td>
                                        <td>已延期</td>
                                        <td>0</td>
                                        <td>0</td>
                                        <td>0</td>
                                        <td class="text-left">
                                            <img class="progressbar"
                                                 src="${static$domain}/plugins/zentao/theme/default/images/main/green.png"
                                                 alt="" width="1" height="16">
                                            <small>0%</small>
                                        </td>
                                        <td id="spark-1" class="spark text-left pd-0 sparked" values="">
                                            <canvas class="projectline-canvas" width="10" height="26"
                                                    style="width: 10px; height: 26px;"></canvas>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <script>
                                $(function () {
                                    var $projectbox = $('#projectbox-1251581554');
                                    var $sparks = $projectbox.find('.spark');
                                    $sparks.filter(':lt(6)').addClass('sparked').projectLine();
                                    $sparks = $sparks.not('.sparked');
                                    var rowHeight = $sparks.first().closest('tr').outerHeight() - ($.zui.browser.ie === 8 ? 0.3 : 0);

                                    var scrollFn = false, scrollStart = 6, i, id, $spark;
                                    $projectbox.parent().on('scroll.spark', function (e) {
                                        if (!$sparks.length) {
                                            $projectbox.off('scroll.spark');
                                            return;
                                        }
                                        if (scrollFn) clearTimeout(scrollFn);

                                        scrollFn = setTimeout(function () {
                                            for (i = scrollStart; i <= scrollStart + 10; i++) {
                                                id = '#spark-' + i;
                                                $spark = $(id);
                                                if ($spark.hasClass('sparked')) continue;
                                                $spark.addClass('sparked').projectLine();
                                                $sparks = $sparks.not(id);
                                            }
                                            scrollStart += 10;
                                        }, 100);
                                    });
                                });
                            </script>
                        </div>
                    </div>
                    <div class="resize-handle resize-horizontal"><i class="icon icon-resize-h"></i></div>
                    <div class="resize-handle resize-vertical"><i class="icon icon-resize-v"></i></div>
                </div>
                <!-- 项目列表 end -->

                <!-- 需求列表 begin  -->
                <div class="col-sm-6 col-md-4">
                    <!--data-url="/zentao/block-printBlock-163-my.html"-->
                    <div class="panel panel-block panel-default" id="block163" data-id="163" data-name="需求列表"  style="height: 240px;">
                        <div class="panel-heading">
                            <div class="panel-actions">
                                <a href="#" class="panel-action drag-disabled panel-action-more"> <i class="icon icon-more"></i></a>
                                <div class="dropdown">
                                    <a href="javascript:;" data-toggle="dropdown" class="panel-action"><i class="icon icon-ellipsis-v"></i></a>
                                    <ul class="dropdown-menu pull-right">
                                        <li><a href="javascript:;" class="refresh-panel"><i class="icon-repeat"></i> 刷新</a></li>
                                        <li><a data-toggle="modal" href="/zentao/block-admin-163-my.html" class="edit-block" data-title="需求列表" data-icon="icon-pencil"><i class="icon-pencil"></i> 编辑</a></li>
                                        <li><a href="javascript:;" class="remove-panel"><i class="icon-remove"></i> 移除</a></li>
                                    </ul>
                                </div>
                            </div>
                            <span class="panel-title">需求列表</span>
                        </div>
                        <div class="panel-body no-padding"><table class="table tablesorter table-data table-hover block-story table-fixed">
                            <thead style="visibility: visible;">
                            <tr>
                                <th width="50">ID</th>
                                <th width="30">P</th>
                                <th>           需求名称</th>
                                <th width="50">预计</th>
                                <th width="70">状态</th>
                                <th width="70">阶段</th>
                            </tr>
                            </thead>
                        </table>
                            <script>
                                if(typeof(dataTable) == 'function')$('.block-story').dataTable();
                            </script>
                        </div>
                    </div>
                    <div class="resize-handle resize-horizontal"><i class="icon icon-resize-h"></i></div><div class="resize-handle resize-vertical"><i class="icon icon-resize-v"></i></div></div>
                <!-- 需求列表  end-->
                <!-- 待办begin-->
                <div class="col-sm-6 col-md-4">
                    <!--data-url="/zentao/block-printBlock-165-my.html"-->
                    <div class="panel panel-block panel-default" id="block165" data-id="165" data-name="待办列表"  style="height: 240px;">
                        <div class="panel-heading">
                            <div class="panel-actions">
                                <a href="#" class="panel-action drag-disabled panel-action-more"> <i class="icon icon-more"></i></a>
                                <div class="dropdown">
                                    <a href="javascript:;" data-toggle="dropdown" class="panel-action"><i class="icon icon-ellipsis-v"></i></a>
                                    <ul class="dropdown-menu pull-right">
                                        <li><a href="javascript:;" class="refresh-panel"><i class="icon-repeat"></i> 刷新</a></li>
                                        <li><a data-toggle="modal" href="#" class="edit-block" data-title="待办列表" data-icon="icon-pencil"><i class="icon-pencil"></i> 编辑</a></li>
                                        <li><a href="javascript:;" class="remove-panel"><i class="icon-remove"></i> 移除</a></li>
                                    </ul>
                                </div>
                            </div>
                            <span class="panel-title">待办列表</span>
                        </div>
                        <div class="panel-body no-padding"><table class="table tablesorter table-data table-hover block-todo table-fixed">
                            <thead style="visibility: visible;">
                            <tr>
                                <th width="90">日期</th>
                                <th width="40">P</th>
                                <th>           名称</th>
                                <th width="50">开始</th>
                                <th width="50">结束</th>
                            </tr>
                            </thead>
                        </table>
                            <script>
                                if(typeof(dataTable) == 'function')$('.block-todo').dataTable();
                            </script>
                        </div>
                    </div>
                    <div class="resize-handle resize-horizontal"><i class="icon icon-resize-h"></i></div><div class="resize-handle resize-vertical"><i class="icon icon-resize-v"></i></div></div>
                <!-- 待办 end -->
            </div>
        </div>
        <script>
            config.ordersSaved = '排序已保存';
            config.confirmRemoveBlock = '确定移除区块【{0}】吗？';
            var module = 'my';
            var useGuest = false;
        </script>

    </div>
    <script>setTreeBox()</script>

    <div id="divider"></div>
    <iframe name="hiddenwin" id="hiddenwin" scrolling="no" class="debugwin hidden" frameborder="0"></iframe>
</div>

<script>config.onlybody = 'no';</script>




</body>
</html>

