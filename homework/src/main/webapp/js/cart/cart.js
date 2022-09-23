$(function () {
    var $good = $("#good");
    var $body = $(document.body);
    $(".cart-form").on("submit",function (e) {
        if($.trim($good.val()) === '')
        {
            e.preventDefault();
            $(document.body).appendChild("<div class=\"alert alert-warning\">\n" +
                "            <a href=\"#\" class=\"close\" data-dismiss=\"alert\">\n" +
                "                &times;\n" +
                "            </a>\n" +
                "            <strong>警告！</strong>请输入商品名称\n" +
                "        </div>");
        }
    });
});