$(document).ready(function () {
    let $start = $(".start");
    let $gameContent = $(".game_content");
    $(".btn_start").on("click",function () {
        $start.hide();
        $gameContent.removeClass("hide");
    });
});