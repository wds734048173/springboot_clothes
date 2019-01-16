function pageClick(k) {
	$(k).parent().find("div").removeClass("active");
	$(k).addClass("active");
	var text = $(k).text();
	$("#flTitle").text($(k).text());
    var url = "";
    if (text == "品牌管理") {
        url = "/manager/brandList";
    } else if (text == "颜色管理") {
        url = "/manager/colorList";
    } else if (text == "尺码管理") {
        url = "/manager/sizeList";
    } else if (text == "分类管理") {
        url = "/manager/classList";
    } else if (text == "客户信息管理") {
        url = "/customer.do?method=getCustomerlist";
    } else if (text == "评价信息") {
        url = "/comment.do?method=getCommentlist";
    } else if (text == "信息反馈"){
        url = "/reply.do?method=getReplylist";
    }
    $(".content").load(url);
}