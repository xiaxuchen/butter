$(document).ready(function () {
    let $content = $(".content");
    let $look_more = $("#look-more");
    let $lis = $content.children();
    let $msg = $(".msg");
    $msg.each((i,e)=>{
        if($(e).hasClass("writable"))
        {
            let $cur = $(e).find(".content");
            let form = $(e).find("form").get(0);
            let $textarea = $("<textarea class=\"form-control\" name=\"content\">"+$cur.text()+"</textarea>");
            $cur.dblclick(()=>{
                $textarea.on("blur",()=>{
                    form.submit();
                });
                $cur.html($textarea);
                $textarea.get(0).focus();
            });
        }
    });
    // if($lis.length>6)
    // {
    //     for(let i = 0;i<$lis.length;i++)
    //     {
    //         if(i>2&&i!==$lis.length-1)
    //             $($lis[i]).hide();
    //     }
    // }else if($lis.length <= 6)
    // {
    //     $($lis[$lis.length-1]).remove();
    // }
    // $look_more.click(function () {
    //     $look_more.hide();
    //     for(let i = 0;i<$lis.length;i++)
    //     {
    //         $($lis[i]).show();
    //     }
    // });
});