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
        url = "/manager/toGoodsClassList";
    } else if (text == "客户信息管理") {
        url = "/customer.do?method=getCustomerlist";
    } else if (text == "供应商管理") {
        url = "/manager/supplierList";
    } else if (text == "库存列表"){
        url = "/manager/stockList";
    }
    $(".content").load(url);
}