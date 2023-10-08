(function ($) {
  "use strict";

  new WOW().init();

  // background image
  function dataBackgroundImage() {
    $("[data-bgimg]").each(function () {
      var bgImgUrl = $(this).data("bgimg");
      $(this).css({
        "background-image": "url(" + bgImgUrl + ")", // concatenation
      });
    });
  }

  $(window).on("load", function () {
    dataBackgroundImage();
  });

  //for carousel slider of the slider section
  $(".slider_area").owlCarousel({
    animateOut: "fadeOut",
    autoplay: true,
    loop: true,
    nav: false,
    autoplayTimeout: 6000,
    items: 1,
    dots: true,
  });

  // blog
  $(document).ready(function(){
    $("#btn-blog").click(function(){
      $(".img-blog").toggle(500);
      $(".time-blog").toggle(700);
      $(".content-title-blog").toggle(990);
      $(".content-1").toggle(1200);
      $(".readmore").toggle(1400,function(){});
    });
  });

  // send mail
  $(document).ready(function(){
    var n = document.querySelector("#input-mail").value;
    n = parseFloat(n);

    $("#btn-mail").click(function(){
      if (n == 0) {
        $("#error").css("display", "block");
        $("#error").slideUp(3000);
      }
      else {
        $("#successful").css("display", "block");
        $("#successful").slideUp(3000);
      }
    });
  });

//Sản Phẩm chính
  $(document).ready(function(){
    $(window).scroll(function(event){
     var pos_body = $('html,body').scrollTop();
     var img_product_main_title = $("#img-product-main-title").offset().top;
     var img_product_main_1 = $(".img-product-main-1").offset().top;
     var img_product_main_2 = $(".img-product-main-2").offset().top;
     var img_product_main_3 = $(".img-product-main-3").offset().top;
     var img_product_main_4 = $(".img-product-main-4").offset().top;
     var img_product_main_5 = $(".img-product-main-5").offset().top;
     if(pos_body>150){
       $("#img-product-main-title").css("opacity","1");
       $("#img-product-main-title").addClass("animate__animated animate__backInLeft");
       if(pos_body>170){
         $(".img-product-main-1").css("opacity","1");
         $(".img-product-main-1").css("animation-duration","0.5s");
         $(".img-product-main-1").addClass("animate__animated animate__backInRight");

         $(".img-product-main-2").css("opacity","1");
         $(".img-product-main-2").css("animation-duration","0.7s");
         $(".img-product-main-2").addClass("animate__animated animate__backInRight");

         $(".img-product-main-3").css("opacity","1");
         $(".img-product-main-3").css("animation-duration","1s");
         $(".img-product-main-3").addClass("animate__animated animate__backInRight");

         $(".img-product-main-4").css("opacity","1");
         $(".img-product-main-4").css("animation-duration","1.2s");
         $(".img-product-main-4").addClass("animate__animated animate__backInRight");

         $(".img-product-main-5").css("opacity","1");
         $(".img-product-main-5").css("animation-duration","1.4s");
         $(".img-product-main-5").addClass("animate__animated animate__backInRight");
       }
     }
    });
});

//Sản Phẩm nỗi bật
$(document).ready(function(){
  $(window).scroll(function(event){
   var pos_body = $('html,body').scrollTop();
   var header_banner = $(".header-banner").offset().top;

   if(pos_body>500){
     $(".header-banner").css("opacity","1");
     $(".header-banner").css("animation-duration","2s");
     $(".header-banner").addClass("animate__animated animate__fadeIn");
     if(pos_body>700){
      $(".single_banner-1").css("opacity","1");
      $(".single_banner-1").css("animation-duration","0.5s");
      $(".single_banner-1").addClass("animate__animated animate__slideInLeft");

      $(".single_banner-2").css("opacity","1");
      $(".single_banner-2").css("animation-duration","0.7s");
      $(".single_banner-2").addClass("animate__animated animate__slideInDown");

      $(".single_banner-3").css("opacity","1");
      $(".single_banner-3").css("animation-duration","0.9s");
      $(".single_banner-3").addClass("animate__animated animate__slideInRight");
     }
   }
  });
});

})(jQuery);
