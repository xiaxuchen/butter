<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/12
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
    <head>
        <link rel="stylesheet" href="../../css/bootstrap.css">
        <link rel="stylesheet" href="../../css/bootstrapValidator.min.css">
        <link href="../../css/reset.css" rel="stylesheet">

        <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
        <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
        <!--[if lt IE 9]>
        <script src="../../js/html5shiv.min.js"></script>
        <script src="../../js/respond.min.js"></script>
        <![endif]-->
        <style>
            .form-container{
                width: 60%;
                border-radius: 5px;
                background-color: white;
                margin: 8% auto 0;
                padding: 20px 20px 40px;
            }
            .title{
                padding-bottom: 10px;
            }
            form label{
                text-align: center !important;
            }
        </style>
    </head>
    <body style="background-color: gainsboro">
        <div class="form-container">
            <div class="title"><h3 class="text-center">添加商品</h3></div>
            <form id="addForm" role="form" action="/product/add" method="post" class="form-horizontal" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="category-primary" class="control-label  col-lg-2">一级分类</label>
                    <div class="col-lg-2">
                        <select id="category-primary" class="form-control col-lg-4"></select>
                    </div>
                    <label for="category-secondary" class="control-label col-lg-2">二级分类</label>
                    <div class="col-lg-2">
                        <select id="category-secondary" name="category" class="form-control col-lg-4"></select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="product-name" class="control-label col-lg-2 ">商品名称</label>
                    <div class="col-lg-4">
                        <input id="product-name" type="text" name="name" class="form-control" placeholder="请输入商品名称">
                    </div>
                </div>
                <div class="form-group">
                    <label for="product-photo" class="control-label col-lg-2 ">商品图片</label>
                    <div class="col-lg-2">
                        <input id="product-photo" type="file" name="photo">
                    </div>
                </div>
                <div class="form-group">
                    <label for="product-price" class="control-label col-lg-2 ">商品价格</label>
                    <div class="col-lg-2">
                        <input id="product-price" type="number" name="price" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label for="product-count" class="control-label col-lg-2 ">商品库存</label>
                    <div class="col-lg-2">
                        <input id="product-count" type="number" name="count" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label for="product-des" class="control-label col-lg-2 ">描述信息</label>
                    <div class="col-lg-8">
                        <textarea id="product-des" name="des" class="form-control" placeholder="请输入描述信息，不超过14个字!"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-2 text-center">
                        <button class="btn btn-success" type="submit">添加</button>
                    </div>
                </div>
            </form>
        </div>
        <span class="glyphicon glyphicon-ok"></span>
        <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
        <script src="../../js/jquery.js"></script>
        <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
        <script src="../../js/bootstrap.js"></script>
        <script src="../../js/bootstrapValidator.min.js"></script>
        <script>
            let $primary =  $("#category-primary");
            let $secondary = $("#category-secondary");
            $primary.on("change",(e)=>{
                let ele = e.target;
                let value = ele.options[ele.selectedIndex].value;
                $.get("../../category/children?pid="+value,(data,status)=>{
                    if(status === "success")
                    {
                        if(data.length>0)
                            $secondary.html("");
                        else
                            $secondary.html("<option value='-1'>当前暂无选项</option>");
                        for(let i = 0;i<data.length;i++)
                        {
                            $("<option value='"+data[i].id+"'>"+data[i].name+"</option>").appendTo($secondary);
                        }
                    }
                });
            });
            $.get("../../category/first",(data,status)=>{
                if(status === "success")
                {
                    //当数据为空时重置选择器
                    if(data.length>0)
                        $primary.html("");
                    else
                    {
                        $primary.html("<option value='-1'>当前暂无选项</option>");
                        $secondary.html("<option value='-1'>当前暂无选项</option>");
                    }
                    //遍历数据装填到选择器
                    for(let i = 0;i<data.length;i++)
                    {
                        $("<option value='"+data[i].id+"'>"+data[i].name+"</option>").appendTo($primary);
                        if(i === 0)
                        {
                            if(data[0].children.length>0)
                                $secondary.html("");
                            else
                                $secondary.html("<option value='-1'>当前暂无选项</option>");
                            for(let j = 0;j<data[0].children.length;j++)
                            {
                                $("<option value='"+data[0].children[j].id+"'>"+data[0].children[j].name+"</option>").appendTo($secondary);
                            }
                        }
                    }
                }
            });
            //校验用户信息
            function ValidatorUserInfo(){
                $("#addForm").bootstrapValidator({
                    excluded: [':disabled', ':hidden'],
                    feedbackIcons: {
                        valid : 'glyphicon glyphicon-ok',
                        invalid : 'glyphicon glyphicon-remove',
                        validating : 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        name: {//字段名
                            validators: {
                                notEmpty: {//不能为空
                                    message: '请输入商品名称'
                                },
                                //长度校验
                                stringLength: {
                                    min: 2,
                                    max: 10,
                                    message: '字符长度必须在2位到10位之间'
                                }
                            }
                        },
                        category: {
                            validators: {
                                notEmpty: {
                                    message: '请选择商品分类'
                                },
                                callback: {//用于select的校验
                                    message: '请选择商品分类',
                                    callback: function (value, validator) {//这里可以自定义value的判断规则
                                        return value !== -1;
                                    }
                                }
                            }
                        },
                        des: {
                            validators: {
                                notEmpty: {
                                    message: '请输入商品描述'
                                },
                                //长度校验
                                stringLength: {
                                    min: 10,
                                    max: 20,
                                    message: '字符长度必须在10位以上,20位以下'
                                },
                            }
                        },
                        count: {
                            validators: {
                                notEmpty: {
                                    message: '请输入商品库存'
                                },
                                callback:{
                                    message:"库存必须为整数",
                                    callback:(value,validator)=>
                                    {
                                        return value%1 === 0
                                    }
                                }
                            }
                        },
                        price: {
                            validators: {
                                notEmpty: {
                                    message: '请输入商品价格'
                                },
                                callback: {
                                    message: "商品价格必须大于0",
                                    callback: (value, validator) => {
                                        return value > 0
                                    }
                                }
                            }
                        },
                        photo:{
                            validators: {
                                notEmpty: {
                                    message: '请选择商品图片'
                                }
                            }
                        }
                    }
                })
            }
            $(()=>{
                ValidatorUserInfo();
            });
        </script>
    </body>
</html>