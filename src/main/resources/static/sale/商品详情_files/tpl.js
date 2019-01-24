var createShopCategory = function(opt){
	var isShop = DIU.int(opt.is_shop),
	cateId     = DIU.int(opt.cate_id);
	DIU.ajax({
		url:DIU.sitePath +"diu-includes/diu.cate.data.asp?is_shop="+isShop+"&cate_id="+cateId,data:"",
		success:function(){
			var jsonData = DIU.ajaxData.category;
		},
		failed:function(msg){
			DIUDialog().error('亲，很抱歉，分类数据获取失败，请刷新页面试试！<br>'+msg);
		}
	});
};