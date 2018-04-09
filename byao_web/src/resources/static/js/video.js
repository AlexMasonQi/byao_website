$(function () {

    $(".clickvideo").dblclick(function () {
        let src = null;
        let videoinfo = null;
        $(this).css("backgroundColor", "rgba(255,255,255,0.5)");
        $(this).siblings().css("backgroundColor", "transparent");

        var id = $(this).children(".id").html();
        $(".embed-responsive-item").attr("src", src);

        $(".videosoure").css("display", "none");

        $.ajax({
                   type: "post",
                   dataType: "json",
                   url: "/playVideo",
                   data: {
                       "id": id
                   },
                   success: function (data) {
                       src = data.videoPath;
                       $(".videoajaxinfo").empty().append(data.videoContent);
                       $(".videoinfo").css("display", "block");
                   }
               });
        $(".videobtn").click(function () {
            $(this).parent().css("display", "none");
            $(".embed-responsive-item").attr("src", src);
            $(".videosoure").css("display", "block");
        });
    });
});