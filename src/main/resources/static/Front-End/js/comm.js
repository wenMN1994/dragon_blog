// $(document).ready(function() {
jQuery(document).ready(function($){
    //nav     
    var obj = null;
    var As = document.getElementById('starlist').getElementsByTagName('a');
    obj = As[0];
    for (i = 1; i < As.length; i++) {
        if (window.location.href.indexOf(As[i].href) >= 0) obj = As[i];
    }
    obj.id = 'selected';
    //nav
    $("#mnavh").click(function() {
        $("#starlist").toggle();
        $("#mnavh").toggleClass("open");
    });
    //search  
    $(".searchico").click(function() {
        $(".search").toggleClass("open");
    });
    //searchclose 
    $(".searchclose").click(function() {
        $(".search").removeClass("open");
    });
    //
    $(window).scroll(function() {
        var h = $("body").height() - window.innerHeight
        //console.log(h);
        if ($(window).scrollTop() > 28 && h > 120) {
            $(".topnav").addClass("is-fixed").find("").fadeOut(400);
            $(".searchico").hide()
        } else if ($(window).scrollTop() < 28) {
            $(".topnav").removeClass("is-fixed").find("").fadeIn(400);
            $(".searchico").show();

        }
    });
    //banner
    $('#banner').easyFader();
    //nav menu   
    $(".menu").click(function(event) {
        $(this).children('.sub').slideToggle();
    });
    //tab
    $('.tab_buttons li').click(function() {
        $(this).addClass('newscurrent').siblings().removeClass('newscurrent');
        $('.newstab>div:eq(' + $(this).index() + ')').show().siblings().hide();
    });

    //弹出分享层

    $(".fx-btn").click(function (e) {

        $(".arc-bdfx").show();

        $(document).one("click", function () {
            
            $(".arc-bdfx").hide();
            
        });

        e.stopPropagation();

    });

    $(".side-fx").click(function (e) {

        $(".side-bdfx").show();

        $(".mail-dy").hide();

        $(document).one("click", function () {
            
            $(".side-bdfx").hide();

            $(".mail-dy").hide();
            
        });

        e.stopPropagation();
        
    });

    $(".el-remove").click(function () {

        $(".arc-bdfx").hide();

        $(".mail-dy").hide();

        $(".side-bdfx").hide();
        
    });

    //toolbar
    $(".toolbar-open").click(function(){
        $(".toolbar-open").addClass("openviewd");
        $(".toolbar").addClass("closed");
    });

    $("#closed").click(function(){
        $(".toolbar-open").removeClass("openviewd");
        $(".toolbar").removeClass("closed");
        $("#toolbar-menu li").removeClass("current");
    });

    $('#toolbar-menu li').click(function(){
        var i = $(this).index();
        $(this).addClass('current').siblings().removeClass('current');
    });


    //返回顶部
    $(function () {

        $(window).scroll(function () {

            if ($(this).scrollTop() >= 500) {

                $('.top').fadeIn();

            } else {

                $('.top').fadeOut();

            }

        });
        
        $('.top').click(function () {

            $('body,html').animate({scrollTop: 0}, 800);

        });

    });
});