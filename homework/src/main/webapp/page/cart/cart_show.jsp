<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="products" style="padding: 20px;">
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
                    <div class="product-price"><a href="#">￥${p.price}</a><button class="btn btn-sm btn-info product-cart" onclick="buy()"><span class="glyphicon glyphicon-shopping-cart">购买</span></button></div>
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

