<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/12
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="category">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-offset-1 col-lg-2 product_category_primary">
                <ul>
                    <c:forEach items="${categories}" var="c" varStatus="status">
                        <c:choose>
                            <c:when test="${c.id eq checkedPid}">
                                <c:set value="${c.children}" var="children" scope="page" />
                                <li class="checked" onclick="toggleChecked(this,${c.id})">${c.name}</li>
                            </c:when>
                            <c:otherwise>
                                <li onclick="toggleChecked(this,${c.id})">${c.name}</li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
            </div>
            <div class="col-lg-8 product_category_secondary">
                <c:choose>
                    <c:when test="${empty children}">
                        <div style="width: 100%;height: 100%;text-align: center"><span>当前暂无分类</span></div>
                    </c:when>
                    <c:otherwise>
                        <ul>
                            <c:forEach items="${children}" var="ch">
                                <li class="category_child"><a onclick="getProducts(${ch.id},${checkedPid})">${ch.name}</a></li>
                            </c:forEach>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<div class="products">
    <c:if test="${products.pages == 0}">
        <div class="text-center">
            <span>当前暂无任何商品</span>
        </div>
    </c:if>
    <c:forEach items="${products.objects }" var="p">
        <div class="product">
            <div class="product-img">
                <img src="${p.photo}" class="img-rounded">
            </div>
            <div class="product-content">
                <h3 class="product-name">${p.name}</h3>
                <div class="product-bottom">
                    <div class="product-des"><span style="display: inline-block;height: 30px">${p.des}</span></div>
                    <div class="product-price"><a href="#">￥${p.price}</a><button class="btn btn-sm btn-info product-cart" onclick="addToCart(${p.id})"><span class="glyphicon glyphicon-shopping-cart"> 添加购物车</span></button></div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<div class="text-center center-block" style="clear: both;margin-top: 20px">
    <ul class="pagination">
        <c:forEach items="${products.pageNumbers}" var="num" varStatus="status">
            <c:if test="${status.first and products.currentPage != 1}">
                <li onclick="getProducts(${checkedCid},${checkedPid},${products.currentPage-1})">
                    <a aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <c:choose>
                <c:when test="${products.currentPage eq num}">
                    <li class="active" onclick="getProducts(${checkedCid},${checkedPid},${num})"><a>${num}</a></li>
                </c:when>
                <c:otherwise>
                    <li onclick="getProducts(${checkedCid},${checkedPid},${num})"><a>${num}</a></li>
                </c:otherwise>
            </c:choose>
            <c:if test="${status.last && products.currentPage != products.pages}">
                <li onclick="getProducts(${checkedCid},${checkedPid},${products.currentPage+1})">
                    <a aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
        </c:forEach>
    </ul>
</div>
