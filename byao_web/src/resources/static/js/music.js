$(function () {
    window.location.hash = "#backline";
    //获取video
    var player = $("#play")[0];
    //定时器初始化
    var changeImage = null;
    var changeTime = null;
    //当前播放歌曲索引值
    var indexNumber = null;
    //当前播放列表长度
    var num = $(".clickmusic").length;
    //歌词存储
    var lrcObj = {};
    //设置ul距顶部距离
    var margin = 180;
    console.log(margin);

    //点击播放事件
    $(".clickmusic").dblclick(function () {
        //清除时间定时器
        clearInterval(changeTime);
        //清除图片轮播
        clearInterval(changeImage);
        //获取当前点击索引，为上一曲下一曲做准备
        indexNumber = $(this).index();
        //设置当前点击后样式
        $(this).css("backgroundColor", "rgba(255,255,255,0.5)");
        $(this).siblings().css("backgroundColor", "transparent");
        //获取点击所在数据库索引
        var id = $(this).children(".id").html();
        // console.log(id);
        //异步请求数据（图片地址，图片数量，播放地址，歌词）
        $.ajax({
                   type: "post",
                   dataType: "json",
                   url: "/play",
                   data: {
                       "id": id
                   },
                   success: function (data) {
                    player.src = data.musicPath;
                    //启动播放器
                    player.play();
                       //解析歌词
                       console.log(data.musicLrc);
                       showLrc(data.musicLrc);
                       //设置背景图片，并动态切换
                       showimage(data.musicImagesPath, data.musicImagesCount);
                       //设置播放源
                       player.src = data.musicPath;
                       //启动播放器
                       player.play();
                       player.volume = 0.5;
                       //结束时间，进度条位置
                       getTime();
                       //播放时间，设置歌词滚动
                       setTime();
                   }
               });
    });

    //上一曲
    $(".pre").click(function () {
        indexNumber = indexNumber - 1;
        if (indexNumber < 0) {
            indexNumber = 0;
        }
        $("body").find(".clickmusic").eq(indexNumber).dblclick();
    });
    //暂停
    $(".stop").click(function () {
        if (player.paused) {
            player.play();
            $(this).children().removeClass().addClass("icon_pause_alt");
        }
        else {
            player.pause();
            $(this).children().removeClass().addClass("arrow_triangle-right_alt");
        }

    });
    //下一曲
    $(".next").click(function () {
        indexNumber = indexNumber + 1;
        if (indexNumber == num) {
            indexNumber = num - 1;
        }
        $("body").find(".clickmusic").eq(indexNumber).dblclick();
    });
    //点击进度条事件
    $("#timeCss").click(function (event) {
        let divWidth = $(this).width();
        let x = event.offsetX / divWidth;
        $(this).children().css("width", x * 100 + "%");
        player.currentTime = player.duration * x;
    });
    //点击音量事件
    $("#voiceCss").click(function (event) {
        let divWidth = $(this).width();
        let x = event.offsetX / divWidth;
        $(this).children().css("width", x * 100 + "%");
        player.volume = x;
    });
    //静音事件
    $(".voice").click(function () {
        player.muted = !player.muted;
        // $(this).children().;
        if ($(this).children().hasClass("icon_volume-high")) {
            $(this).children().removeClass().addClass("icon_vol-mute");
        }
        else {
            $(this).children().removeClass().addClass("icon_volume-high");
        }
    });
    //收起播放列表
    let lrcPanelWidth = $(".animateLrc").css("width");
    $(".checkBox").click(function () {
        let leftMargin = $(".music-list").width();
        $(".music-list").stop(true, true).animate({marginLeft: -leftMargin}, function () {
            $(".icon_menu").css("display", "block")
        });
        $(".animateLrc").stop(true, true).animate({width: "100%"});
    });
    $(".icon_menu").click(function () {
        let leftMargin = $(".music-list").width();
        $(".icon_menu").css("display", "none")
        $(".music-list").stop(true, true).animate({marginLeft: 0});
        $(".animateLrc").stop(true, true).animate({width: lrcPanelWidth});
    })

    //当前歌曲总时长
    function getTime() {
        setTimeout(function () {
            var duration = player.duration;
            if (isNaN(duration)) {
                getTime();
            }
            else {
                var m = parseInt(player.duration / 60);
                var s = parseInt(player.duration % 60);
                $(".endTime").html(m + ":" + s);
            }
        }, 10);
    }


    //当前歌曲播放进度,歌词滚动
    function setTime() {
        //歌词行高及ul初始上边距
        var line = parseInt($(".lrc").children().css("lineHeight"));
        changeTime = setInterval(updateTime, 1000);

        function updateTime() {
            //当前歌词更新
            let LRCTIME = parseInt(player.currentTime);
            if (lrcObj[LRCTIME]) {
                //不好，需要改进歌词滚动监听
                let index = -1;
                let num = null;
                Object.keys(lrcObj).forEach(function (key) {
                    index++;
                    if (key == LRCTIME) num = index;
                });
                $(".lrc").children().eq(num).css("color", "#d17460");
                $(".lrc").children().eq(num).siblings().css("color", "#f9f9f9");
                let nowMargin = margin - line * num;
                $(".lrc").css("margin-top", nowMargin + "px");
            }

            //当前时间更新
            var bar = player.currentTime / player.duration * 100;
            $(".timeBar").css("width", bar + "%");
            var m = parseInt(player.currentTime / 60);
            var s = parseInt(player.currentTime % 60);
            if (s < 10) {
                s = "0" + s;
            }
            ;
            if (m == 0) {
                m = "00"
            }
            else if (m > 0 && m < 10) {
                m = "0" + m;
            }
            $(".starTime").html(m + ":" + s);

        }
    }

    //渲染歌词面板
    function showLrc(lrcs) {
        $(".lrc").empty();

        let str = "";
        lrcObj = {};
        // var htmlobj = $.ajax({url: lrcs, async: false});
        var htmlobj = $.ajax({
            url: lrcs,
            dataType: 'jsonp',
            success: function (json) {
                console.log(json)
            },
            error: function (err) {
                console.log(err)
            }

        });
        // var htmlobj = $.ajax({
        //     url: "http://47.104.142.179:8080/upload/music/lrc/ajaxlrc.js",
        //     dataType: 'jsonp',
        //     success: function (json) {
        //         console.log(json)
        //     },
        //     error: function (err) {
        //         console.log(err)
        //     }

        // });

        //htmlobj.responseText 歌词内容在这里
        var lrc = htmlobj.responseText;
        console.log(lrc);
        var lyrics = lrc.split("\n");
        for (var i = 0; i < lyrics.length; i++) {
            var lyric = decodeURIComponent(lyrics[i]);
            var timeReg = /\[\d*:\d*((\.|\:)\d*)*\]/g;
            var timeRegExpArr = lyric.match(timeReg);
            if (!timeRegExpArr) continue;
            var clause = lyric.replace(timeReg, '');
            for (var k = 0, h = timeRegExpArr.length; k < h; k++) {
                var t = timeRegExpArr[k];
                var min = Number(String(t.match(/\[\d*/i)).slice(1)),
                    sec = Number(String(t.match(/\:\d*/i)).slice(1));
                var time = min * 60 + sec;
                lrcObj[time] = clause;
            }
        }
        for (let i in lrcObj) {
            str += "<li>" + lrcObj[i] + "</li>"
        }
        $(".lrc").html(str);
        $(".lrc").css("marginTop", margin + "px")
    }

    //图片轮播事件
    function showimage(img, num) {
        var imgarr = [];
        var inum = 0;
        for (let i = 1; i <= num; i++) {
            imgarr.push(img + i);
        }
        $(".music").css("background-image", "url(" + imgarr[0] + ".jpg)");
        changeImage = setInterval(changeI, 10000);

        function changeI() {
            inum++;
            if (inum == num) {
                inum = 0;
            }
            $(".music").css("background-image", "url(" + imgarr[inum] + ".jpg)");
        }
    };
});