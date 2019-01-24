﻿function DIU_Class() {
	var THIS = this;
	this.isClient = !0,
	this.debug = !1,
	this.runMode = 0,
	this.cacheOpen = !1,
	this.htmlCacheOpen = !1,
	this.isStaticHtml = function () {
		return 2 == THIS.runMode ? !0 : THIS.htmlCacheOpen ? !0 : !1
	},
	this.logined = !1,
	this.sitePath = "",
	this.siteUrl = "",
	this.siteHurl = "",
	this.siteHtmlFileSuffix = "",
	this.ucenterHurl = "",
	this.searchUrl = "",
	this.loginUrl = "",
	this.logoutUrl = "",
	this.regUrl = "",
	this.forgetPasswordUrl = "",
	this.moneySb = "",
	this.ctl = "",
	this.rootpath = "",
	this.urlpath = "",
	this.page = "",
	this.typeid = "",
	this.orderby = "",
	this.time = "",
	this.timer = "",
	this.encryptKey = "",
	this.uid = 0,
	this.username = "",
	this.nickname = "",
	this.ussKey = "",
	this.gid = 0,
	this.pid = 0,
	this.shoppingIsNeedLogin = 1,
	this.cartUrl = "",
	this.orderUrl = "",
	this.now = function () {
		return new Date
	},
	this.c1t = "",
	this.c2t = "",
	this.c3t = "",
	this.c4t = "",
	this.currentURL = function () {
		return window.location.href
	},
	this.client = "",
	this.link = "",
	this.ad = "",
	this.gallery = "",
	this.member = "",
	this.search = "",
	this.shop = "",
	this.cookie = new Cookie_Class,
	this.JSON = new JSON_Class,
	this.encrypt = new Encrypt_Class,
	this.ajaxData = "",
	this.ajax = function (a) {
		var c = (a.follow, a.async === !1 ? !1 : !0),
		d = a.url,
		e = a.data,
		f = a.success || function () {},
		g = a.failed || function () {};
		$.ajax({
			type : "post",
			dataType : "html",
			async : c,
			url : d + "&return_type=json&r=" + DIU.random(),
			data : e,
			error : function (a, b, c) {
				var d = "";
				DIU.isNotNull(a.responseText) && (DIU.debug ? (d = d + "<b>textStatus</b> : " + b + "<br>", d = d + "<b>errorThrown</b> : " + c + "<br>", d = d + "<b>responseText</b> : " + a.responseText + "<br>", DIUDialog({
							id : DIU.createDialogID(),
							content : "哎啊，服务器刚开了一下小差！<div class='server-data'>" + d + "</div>"
						}).timeout(4)) : DIUDialog({
						id : DIU.createDialogID(),
						content : "哎啊，服务器刚开了一下小差！"
					}).timeout(4)),
				g.call()
			},
			success : function (a) {
				var b,
				c,
				d,
				e = '{"result":"failed","message":"' + escape("系统返回错误，操作失败！") + '"}';
				d = a,
				DIU.isNull(d) && (d = e);
				try {
					d = $.parseJSON(d)
				} catch (h) {
					d = $.parseJSON(e)
				}
				if (DIU.ajaxData = d, "success" == d.result)
					f.call(this);
				else {
					if (c = unescape(d.message), DIU.isNull(c))
						for (c = "", b = 0; b < d.messages.length; b++)
							c = c + "" + unescape(d.messages[b].message) + "<br>";
					DIU.debug && (c = c + "<div class='server-data'>" + a + "</div>");
					try {
						g(c).call(this)
					} catch (h) {}
				}
			}
		})
	},
	this.appendToString = "",
	this.append = function (a) {
		DIU.appendToString = DIU.appendToString + a
	},
	this.arrayUnique = function (a) {
		var b,
		c,
		e,
		d = 0,
		f = [];
		for (b = 0; b < a.length; b++)
			if ("" != $.trim(a[b])) {
				for (e = !1, c = 0; c < f.length; c++)
					($.trim(a[b]) == $.trim(f[c]) || "" == $.trim(f[c])) && (e = !0);
				e || (f[d] = a[b], d += 1)
			}
		return f
	},
	this.bodyScrollTime = 3600,
	this.bodyScrollClose = !1,
	this.bodyScroll = function () {
		DIU.bodyScrollTime = DIU.bodyScrollTime - 1,
		document.documentElement.scrollTop = document.body.scrollHeight,
		!DIU.bodyScrollClose && DIU.bodyScrollTime > 0 && window.setTimeout(function () {
			DIU.bodyScroll()
		}, 200)
	},
	this.closeParentDialog = function (a) {
		parent.closeDialog(a)
	},
	this.confirm = function (a) {
		return confirm(a) ? !0 : !1
	},
	this.echo = function (a) {
		document.write(a)
	},
	this.formatDateTime = function (a, b) {
		var c,
		d,
		e,
		f,
		g,
		h,
		i,
		j;
		try {
			c = DIU.rep(a, "-", "/"),
			d = c.split(" "),
			e = DIU.int(d[0].split("/")[0]),
			f = DIU.int(d[0].split("/")[1]),
			g = DIU.int(d[0].split("/")[2]),
			h = DIU.int(d[1].split(":")[0]),
			i = DIU.int(d[1].split(":")[1]),
			j = DIU.int(d[1].split(":")[2]),
			"0" == b ? c = e + "-" + f + "-" + g + " " + h + ":" + i + ":" + j : "1" == b ? c = e + "." + f + "." + g + " " + h + ":" + i + ":" + j : "2" == b ? c = e + "-" + f + "-" + g : "3" == b ? c = h + ":" + i + ":" + j : "4" == b && (c = e + "." + f + "." + g)
		} catch (k) {
			c = ""
		}
		return c
	},
	this.toDate = function (a) {
		var b,
		c,
		d,
		e,
		f,
		g,
		h,
		i,
		j;
		return "string" == typeof a ? (b = DIU.rep(a, "-", "/"), c = b.split(" "), d = parseInt(DIU.int(c[0].split("/")[0])), e = parseInt(DIU.int(c[0].split("/")[1])) - 1, f = parseInt(DIU.int(c[0].split("/")[2])), g = parseInt(DIU.int(c[1].split(":")[0])), h = parseInt(DIU.int(c[1].split(":")[1])), i = parseInt(DIU.int(c[1].split(":")[2])), j = new Date(d, e, f, g, h, i), j) : a
	},
	this.dateAdd = function (a, b, c) {
		switch ("string" == typeof c && (c = DIU.toDate(c)), a) {
		case "y":
			return new Date(c.setFullYear(c.getFullYear() + b));
		case "m":
			return new Date(c.setMonth(c.getMonth() + b));
		case "d":
			return new Date(c.setDate(c.getDate() + b));
		case "w":
			return new Date(c.setDate(c.getDate() + 7 * b));
		case "h":
			return new Date(c.setHours(c.getHours() + b));
		case "n":
			return new Date(c.setMinutes(c.getMinutes() + b));
		case "s":
			return new Date(c.setSeconds(c.getSeconds() + b));
		case "l":
			return new Date(c.setMilliseconds(c.getMilliseconds() + b))
		}
	},
	this.dateDiff = function (a, b, c) {
		try {
			switch ("string" == typeof b && (b = DIU.toDate(b)), "string" == typeof c && (c = DIU.toDate(c)), a) {
			case "s":
				return parseInt((c - b) / 1e3);
			case "n":
				return parseInt((c - b) / 6e4);
			case "h":
				return parseInt((c - b) / 36e5);
			case "d":
				return parseInt((c - b) / 864e5);
			case "w":
				return parseInt((c - b) / 6048e5);
			case "m":
				return c.getMonth() + 1 + 12 * (c.getFullYear() - b.getFullYear()) - (b.getMonth() + 1);
			case "y":
				return c.getFullYear() - b.getFullYear()
			}
		} catch (d) {}
	},
	this.timeOutTip = function (a) {
		var b,
		c,
		d,
		e,
		f,
		g = "",
		h = a.time,
		i = a.now,
		j = a.section,
		k = a.d,
		l = a.h,
		m = a.n,
		n = a.s;
		DIU.isNotNull(h) && (h = DIU.toDate(h), timeDiff = DIU.dateDiff("s", i, h), timeDiff > 0 && (60 >= timeDiff ? g = timeDiff + n : timeDiff > 60 && 5400 >= timeDiff ? (d = DIU.int(timeDiff / 60), e = timeDiff - 60 * d, g = e > 0 ? d + m + e + n : d + m) : timeDiff > 5400 && 86400 >= timeDiff ? (c = DIU.int(timeDiff / 3600), f = timeDiff - 3600 * c, d = DIU.int(f / 60), e = f - 60 * d, g = e > 0 ? c + l + d + m + e + n : c + l + d + m) : (b = DIU.int(timeDiff / 86400), f = timeDiff - 86400 * b, c = DIU.int(f / 3600), f -= 3600 * c, d = DIU.int(f / 60), e = f - 60 * d, g = e > 0 ? b + k + c + l + d + m + e + n : b + k + c + l + d + m), j.html(g), i = DIU.dateAdd("s", 1, i), a = {
					d : k,
					h : l,
					n : m,
					s : n,
					time : h,
					now : i,
					section : j
				}, window.setTimeout(function () {
					DIU.timeOutTip(a)
				}, 1e3)))
	},
	this.timeAgoTip = function (a, b, c) {
		var d,
		e,
		f,
		g,
		h,
		i,
		j,
		k = "",
		l = a.y,
		m = a.m,
		n = a.w,
		o = a.d,
		p = a.h,
		q = a.n,
		r = a.s,
		s = a.z || "";
		return b = DIU.toDate(b),
		c = DIU.toDate(c),
		d = DIU.dateDiff("y", b, c),
		e = DIU.dateDiff("m", b, c),
		f = DIU.dateDiff("w", b, c),
		g = DIU.dateDiff("d", b, c),
		h = DIU.dateDiff("h", b, c),
		i = DIU.dateDiff("n", b, c),
		j = DIU.dateDiff("s", b, c),
		0 >= j && (k = s),
		j > 0 && (k = j + r),
		i > 0 && (k = i + q),
		h > 0 && (k = h + p),
		g > 0 && (k = g + o),
		f > 0 && g > 7 && (k = f + n),
		e > 0 && f > 4 && (k = e + m),
		d > 0 && e > 12 && (k = d + l),
		k
	},
	this.delay = function (a, b) {
		window.setTimeout(function () {
			b.call()
		}, a)
	},
	this.echoAdData = function (a, b) {
		var c = $("#diu_ad_" + a).attr("data");
		if (!DIU.isNull(c))
			for (json = DIU.JSON.parse(c), i = 0; i < json.length; i++)
				s = b, s = DIU.rep(s, "{$i}", i + 1), s = DIU.rep(s, "{$link}", json[i].link), s = DIU.rep(s, "{$name}", json[i].name), s = DIU.rep(s, "{$url}", json[i].url), document.write(s);
		$("script[id='" + a + "']").remove()
	},
	this.echoImagesData = function (a, b) {
		if (!DIU.isNull(a))
			for (a = DIU.rep(a, "&quot;", '"'), json = DIU.JSON.parse(a), i = 0; i < json.length; i++)
				s = b, s = DIU.rep(s, "{$i}", i + 1), s = DIU.rep(s, "{$name}", json[i].name), s = DIU.rep(s, "{$url}", json[i].url), document.write(s)
	},
	this.enterClick = function (a) {
		$(document).keydown(function (b) {
			var c;
			b = b ? b : window.event ? window.event : null,
			c = b.keyCode ? b.keyCode : b.which,
			13 == c && a.call(this)
		})
	},
	this.filename = function (a) {
		var b,
		c,
		d,
		e,
		f = a.split("/");
		return e = f[f.length - 1],
		b = DIU.filename.arguments,
		b.length > 1 && (c = b[1], 0 == c.exName && (d = e.lastIndexOf("."), d > 0 && (e = DIU.left(e, d)))),
		e
	},
	this.fileExName = function (a) {
		if (a = $.trim(a), "" != a) {
			var b = a.split(".");
			return b[b.length - 1]
		}
		return ""
	},
	this.getSubDomain = function (a) {
		if ("" == a || null == a)
			a = "";
		else {
			var b = a.split(".");
			a = "www" == b[0] ? b[1] : b[0]
		}
		return a
	},
	this.goBack = function () {
		history.go(-1)
	},
	this.iif = function (a, b, c) {
		return a ? b : c
	},
	this.int = function (a) {
		return "" == a || null == a ? 0 : (a += "", "px" == DIU.right(a, 2) ? (a = a.replace(/[^0-9.-]*/g, ""), "" == a && (a = "0"), a = parseInt(a)) : (a = a.replace(/[^0-9.-]*/g, ""), "" == a && (a = 0), a = parseInt(a)), a)
	},
	this.indexOf = function (a, b) {
		return DIU.isNull(a) ? -1 : a.indexOf(b)
	},
	this.isIE = function () {
		return window.navigator.userAgent.indexOf("IE") >= 0 ? !0 : !1
	},
	this.isFirefox = function () {
		return window.navigator.userAgent.indexOf("Firefox") >= 0 ? !0 : !1
	},
	this.isWebKit = function () {
		return window.navigator.userAgent.indexOf("WebKit") >= 0 ? !0 : !1
	},
	this.isChrome = function () {
		return window.navigator.userAgent.indexOf("Chrome") >= 0 ? !0 : !1
	},
	this.isOpera = function () {
		return window.navigator.userAgent.indexOf("Opera") >= 0 ? !0 : !1
	},
	this.isWeixinAgent = function () {
		return window.navigator.userAgent.indexOf("MicroMessenger") >= 0 ? !0 : !1
	},
	this.isEmail = function (a) {
		return -1 != a.search(DIU.rule("email")) ? !0 : !1
	},
	this.isFlash = function (a) {
		var b = DIU.fileExName(a);
		return "swf" == b ? !0 : !1
	},
	this.isImage = function (a) {
		var b = DIU.fileExName(a);
		return "jpg" == b || "jpeg" == b || "gif" == b || "png" == b || "bmp" == b || "ico" == b ? !0 : !1
	},
	this.isInArray = function (a, b) {
		var c,
		d = !1;
		for (c = 0; c < a.length; c++)
			if (a[c] == b) {
				d = !0;
				break
			}
		return d
	},
	this.isNull = function (a) {
		var b = $.trim(a);
		return null == b ? !0 : "undefined" == b ? !0 : "" == b ? b === !1 || b === !0 ? !1 : !0 : !1
	},
	this.isNotNull = function (a) {
		return !DIU.isNull(a)
	},
	this.objExist = function (a) {
		return DIU.isObjExist(a)
	},
	this.isObjExist = function (a) {
		return null == a || "undefined" == a || void 0 == a || null == a.html() ? !1 : !0
	},
	this.isInArray = function (a, b) {
		var d,
		c = !1;
		if (a.length > 0)
			for (d = 0; d < a.length; d++)
				if (b == a[d]) {
					c = !0;
					break
				}
		return c
	},
	this.isValidDataime = function (a) {
		var b = !1;
		return DIU.isNull(a) || "1900" != DIU.left(a, 4) && (b = !0),
		b
	},
	this.isValidMobile = function (a) {
		var b = !1;
		return a = DIU.parseMobile(a),
		DIU.isNull(a) || 11 == a.length && "1" == DIU.left(a, 1) && (b = !0),
		b
	},
	this.leftString = function (a, b) {
		var c = a.length,
		d = DIU.left(a, b);
		return c > b && (d += " ..."),
		d
	},
	this.left = function (a, b) {
		return "" == a || void 0 == a || 1 > b ? "" : a.substring(0, b)
	},
	this.right = function (a) {
		if (DIU.isNull(a))
			return "";
		a += "";
		var c = a.length;
		return a = a.substring(c - 2, c)
	},
	this.listOrderBy = function (a) {
		var b = a.obj;
		b.each(function () {
			var a = $(this).attr("orderby"),
			b = DIU.listOrderByURL(a);
			DIU.orderby == a ? $(this).addClass("current") : $(this).removeClass("current"),
			$(this).click(function () {
				DIU.redirect(b)
			})
		})
	},
	this.listOrderByURL = function (a) {
		var b = "";
		return b = DIU.isNull(DIU.rootpath) ? DIU.page > 0 ? DIU.c2t : DIU.c1t : DIU.page > 0 ? DIU.c3t : DIU.c3t,
		b.replace("{$rootpath}", DIU.rootpath).replace("{$urlpath}", DIU.urlpath).replace("{$page}", DIU.page).replace("{$typeid}", DIU.typeid).replace("{$orderby}", a)
	},
	this.onblur = function (me, opt) {
		var v = $(me).val(),
		rep = opt.rep,
		length = opt.length;
		rep && (v = v.replace(eval(rep), "")),
		length && (v = v.substring(0, length)),
		$(me).val($.trim(v))
	},
	this.openPage = function (a) {
		window.location.href = a
	},
	this.openNewPage = function (a) {
		window.open(a)
	},
	this.parseBankCode = function (a) {
		var b = "";
		return DIU.isNull(a) || (b = a.replace(/[^0-9a-zA-Z\-\_]*/g, "")),
		b
	},
	this.parseFloat = function (a) {
		return DIU.isNull(a) ? 0 : parseFloat(a)
	},
	this.parseFormInputValue = function (a) {
		var b,
		c = a.form;
		c.find("input").each(function () {
			b = $.trim($(this).val()),
			$(this).val(b)
		})
	},
	this.parseMobile = function (a) {
		return a.replace(/[^0-9]*/g, "")
	},
	this.parseMobileCode = function (a) {
		return DIU.left(a.replace(/[^0-9]*/g, ""), 6)
	},
	this.parseUsername = function (a) {
		return a.replace(/[^0-9a-zA-Z\.\-\_]*/g, "")
	},
	this.parseMoney = function (a) {
		return a = DIU.parseFloat(a),
		a = a.toFixed(2),
		a = DIU.parseFloat(a)
	},
	this.parsePrice = function (a) {
		return DIU.isNull(a) || "0" == a ? "0.00" : (a = DIU.parseMoney(a), a = a.toFixed(2))
	},
	this.parseVal = function (a) {
		return ("undefined" == a || void 0 == a) && (a = ""),
		a
	},
	this.random = function () {
		return parseInt(1e5 * Math.random())
	},
	this.redirect = function (a, b) {
		b = DIU.int(b),
		b = parseInt(b - 1),
		b > 0 ? ($("#redirect_timeout").html(b), window.setTimeout(function () {
				DIU.redirect(a, b)
			}, 1e3)) : window.location.href = a
	},
	this.refresh = function () {
		DIUDialog({
			id : "refresh_" + DIU.createDialogID(),
			content : "页面刷新中...",
			shadow : !1
		}).padding("13px 20px").position(),
		window.location.reload()
	},
	this.pageLoading = function () {
		DIUDialog({
			id : "loading_" + DIU.createDialogID(),
			content : "页面加载中...",
			shadow : !1
		}).padding("13px 20px").position()
	},
	this.rep = function (str, rule, s) {
		return DIU.isNull(str) ? "" : (rule = rule.replace(/\$/g, "\\$"), eval("str.replace(/" + rule + "/g,s)"))
	},
	this.reps = function (a, b, c) {
		return DIU.isNull(a) ? "" : a.replace(b, c)
	},
	this.rule = function (a) {
		switch (a) {
		case "email":
			return /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		case "domain":
			return /[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\.?/;
		case "site_url":
			return "";
		case "img":
			return "img";
		default:
			return !1
		}
	},
	this.scrollTop = function (a) {
		var b = 1;
		a.click(function () {
			for (var a = document.body.scrollHeight - 1; a && (window.scrollTo(0, a), a > 200 ? (a = a - 2 - b, b += 5) : a -= 2, !(0 > a)); );
		})
	},
	this.documentScrollTop = function () {
		return parseInt(document.body.scrollTop || document.documentElement.scrollTop)
	},
	this.setDisabled = function (a, b) {
		1 == b ? a.addClass("disabled").attr("disabled", !0) : a.removeClass("disabled").attr("disabled", !1)
	},
	this.tabSwitch = function (a) {
		var b = a.current || 1,
		c = a.tab || "tab",
		d = a.tabbar,
		e = a.container;
		d.find("[name='" + c + "'][" + c + "='" + b + "']").addClass("current"),
		e.find("[is_" + c + "='true'][" + c + "!='" + b + "']").hide(),
		d.find("[name='" + c + "']").click(function () {
			var a = DIU.int($(this).attr(c));
			$(this).addClass("current"),
			d.find("[name='" + c + "'][" + c + "!='" + a + "']").removeClass("current"),
			e.find("[is_" + c + "='true'][" + c + "!='" + a + "']").hide(0),
			e.find("[is_" + c + "='true'][" + c + "='" + a + "']").show(50)
		})
	},
	this.timeoutTime = 0,
	this.timeoutFinish = {},
	this.timeoutDisplay = function (a) {
		DIU.timeoutTime > 0 ? (DIU.timeoutTime = DIU.timeoutTime - 1, a.html(DIU.timeoutTime), window.setTimeout(function () {
				DIU.timeoutDisplay(a)
			}, 1e3)) : DIU.timeoutFinish.call()
	},
	this.toString = function (a) {
		return a.toString()
	},
	this.trim = function (a) {
		return $.trim(a)
	},
	this.urlEncode = function (a) {
		return a = DIU.isNotNull(a) ? encodeURIComponent(a) : ""
	},
	this.verifyCode = function (a) {
		var b = a.boxer || $("#verifycode"),
		c = a.path || THIS.sitePath,
		d = DIU.random();
		b.append('<i class="code-loading"></i>'),
		b.html('<img src="' + c + "diu-includes/diu.verifycode.asp?key=" + d + '" style="border:1px solid #888; vertical-align:top;" alt="点击刷新" /><input type="hidden" name="verifycode_name" value="' + d + '" /><a class="fresh" href="javascript:;" name="fresh">刷新</a>'),
		b.find("img,a[name='fresh']").click(function () {
			DIU.verifyCode(a)
		})
	},
	this.author = function () {
		var a = ">d>i>u>8>d>i>u>";
		return DIU.rep(a, ">", "")
	},
	this.verifyCodeValueBlur = function (a) {
		DIU.isObjExist(a) && a.blur(function () {
			$(this).val(DIU.parseVerifyCode($(this).val()))
		})
	},
	this.verifyCodeValueFocus = function (a, b) {
		DIU.isObjExist(a) && a.focus(function () {
			DIU.isNull(b.html()) && DIU.verifyCode({
				boxer : b
			})
		})
	},
	this.parseVerifyCode = function (a) {
		return DIU.left(DIU.trim(a).replace(/[^0-9]*/g, ""), 5)
	},
	this.regionJson = [],
	this.subRegion = [],
	this.currentPath = "",
	this.regionSelectFinished = {},
	this.isRegionSelected = function (a, b) {
		var c = THIS.currentPath.split(",");
		return a == c[b] ? !0 : !1
	},
	this.createRegionSelect = function (a, b, c) {
		THIS.regionJson = DIURegion || [],
		"" != c && (THIS.currentPath = c);
		var d,
		e = THIS.regionJson,
		f = !1;
		for (s = '<select class="select-region" is_region="1" name="' + b + '" depth="1">', s += '<option value="0"> = 请选择 = </option>', i = 0; i < e.length; i++)
			s = s + '<option value="' + e[i].i + '" path=",' + e[i].i + '," ', THIS.isRegionSelected(e[i].i, 1) && (s += " selected ", f = !0), s = s + ">" + e[i].n + "</option>";
		s += "</select>",
		a.append(s),
		d = a.find("select[name='" + b + "']"),
		f && THIS.createSubRegion(a, b + "_2", d.find("option:selected").attr("path"), THIS.regionJson),
		d.change(function () {
			DIU.int($(this).val()) > 0 ? THIS.createSubRegion(a, b + "_2", $(this).find("option:selected").attr("path"), THIS.regionJson) : a.find("select[depth!='1']").remove()
		})
	},
	this.createSubRegion = function (a, b, c, d) {
		var e,
		f,
		g,
		h;
		if (THIS.getRegionJson(c, THIS.regionJson), d = THIS.subRegion, f = !1, h = c.split(",").length - 1, d.length > 0) {
			for (a.find("select").each(function () {
					DIU.int($(this).attr("depth")) >= h && $(this).remove()
				}), g = '<select class="select-region" is_region="1" name="' + b + '" depth="' + h + '">', g += '<option value="0"> = 请选择 = </option>', i = 0; i < d.length; i++)
				g = g + '<option value="' + d[i].i + '" path="' + c + d[i].i + '," ', THIS.isRegionSelected(d[i].i, h) && (g += " selected ", f = !0), g = g + ">" + d[i].n + "</option>";
			g += "</select>",
			a.append(g),
			e = a.find("select[name='" + b + "']"),
			f && THIS.createSubRegion(a, b + "_" + (h + 1), e.find("option:selected").attr("path"), THIS.regionJson),
			e.change(function () {
				DIU.int($(this).val()) > 0 ? THIS.createSubRegion(a, b + "_" + (h + 1), $(this).find("option:selected").attr("path")) : a.find("select").each(function () {
					DIU.int($(this).attr("depth")) > h && $(this).remove()
				})
			})
		} else {
			a.find("select").each(function () {
				DIU.int($(this).attr("depth")) >= h && $(this).remove()
			});
			try {
				THIS.regionSelectFinished(c).call()
			} catch (j) {}
			$("span[name='t_order_region']").removeClass("t-err").html("")
		}
	},
	this.getRegionJson = function (a, b) {
		var c,
		d,
		e = a.split(",");
		for (THIS.subRegion = [], c = 0; c < b.length; c++)
			for (d = 0; d < e.length; d++)
				if (DIU.int(e[d]) > 0 && b[c].i == e[d]) {
					if (d == e.length - 2) {
						THIS.subRegion = b[c].c;
						break
					}
					THIS.getRegionJson(a, b[c].c);
					break
				}
	},
	this.getMobileCode = function (a) {
		var b = a.mb,
		c = a.mc,
		d = a.getmc,
		e = DIU.int(d.attr("timeout")),
		f = a.url,
		g = a.vv || {},
		h = a.vc || {},
		i = a.isHide,
		j = a.vcodeOpen;
		b.blur(function () {
			$(this).val(DIU.parseMobile($(this).val()))
		}),
		c.blur(function () {
			$(this).val(DIU.parseMobileCode($(this).val()))
		}),
		b.focus(),
		d.click(function () {
			var a,
			c,
			k,
			o,
			l = !0,
			m = b.val(),
			n = b.attr("scode_name");
			DIU.isValidMobile(m) || (DIUDialog().alert("请填写正确的手机号码").position().timeout(2), l = !1, b.focus()),
			DIU.objExist(h) && 1 == j && (DIU.objExist(h) && (a = h.find("input[name='verifycode_name']")), 1 == DIU.int(g.attr("need_refresh")) && (DIU.verifyCode({
						boxer : h
					}), g.attr("need_refresh", "0").val("").focus().parent().parent().show()), c = DIU.trim(a.val()), k = DIU.trim(g.val()), DIU.isNull(k) && l && (DIUDialog().alert("请填写验证码！").position().timeout(2), l = !1, g.focus())),
			l && (o = DIUDialog().posting("正在发送短信验证码..."), DIU.ajax({
					url : f,
					data : "mobile=" + DIU.encrypt.encode(m) + "&scode_name=" + n + "&verifycode_name=" + c + "&verifycode_value=" + k,
					success : function () {
						d.addClass("disabled").attr("disabled", "disabled").html("已发送短信(<i>" + e + "</i>)"),
						DIU.timeoutTime = d.attr("timeout"),
						DIU.timeoutFinish = function () {
							d.removeClass("disabled").attr("disabled", "").html("重新获取验证码")
						},
						DIU.timeoutDisplay(d.find("i")),
						o.success("验证码已发送，请查看手机短信！").position().timeout(3),
						g.attr("need_refresh", "1"),
						i && g.parent().parent().hide()
					},
					failed : function (a) {
						o.error("验证码发送失败，请查看以下错误提示信息", a).position().timeout(4),
						DIU.indexOf(a, "手机号码") >= 0 && b.focus(),
						DIU.indexOf(a, "验证码") >= 0 && (DIU.verifyCode({
								boxer : h
							}), g.val("").focus())
					}
				}))
		})
	},
	this.webScreenHeight = function () {
		return parseInt(document.documentElement.clientHeight)
	},
	this.webScreenWidth = function () {
		return DIU.isFirefox() || DIU.isIE() || DIU.isWebKit() ? parseInt(document.documentElement.clientWidth) - 1 : parseInt(document.documentElement.clientWidth)
	},
	this.webHeight = function () {
		return parseInt($("body").height())
	},
	this.webWidth = function () {
		return parseInt(document.body.scrollWidth)
	},
	this.createDialogID = function () {
		var a = new Date,
		b = "d" + a.getHours().toString() + a.getMinutes().toString() + a.getSeconds().toString() + a.getMilliseconds().toString() + parseInt(1e4 * Math.random()).toString();
		return b
	},
	this.dialogBigHeight = function () {
		return DIU.webScreenHeight() - 48
	},
	this.dialogBigWidth = function () {
		return DIU.webScreenWidth() - 52
	},
	this.DIALOG_Class = function (a, b) {
		var c = "DIUDialog" + +new Date,
		d = function (a) {
			var f,
			e = d.defaults;
			a = a || {},
			("string" == typeof a || 1 === a.nodeType) && (a = {
					content : a
				});
			for (f in e)
				void 0 === a[f] && (a[f] = e[f]);
			return document.getElementById(a.id) ? void 0 : (a.button && a.button.push || (a.button = []), a.ok && a.button.push({
					id : "ok",
					value : a.okValue,
					callback : a.ok,
					important : !0,
					focus : !0
				}), a.cancel && a.button.push({
					id : "cancel",
					value : a.cancelValue,
					callback : a.cancel
				}), new d.fn.constructor(a))
		};
		d.fn = d.prototype = {
			constructor : function (a) {
				var b;
				return this.config = a,
				this.dom = b = this.dom || this.createHTML(a),
				this.button.apply(this, a.button),
				this.title(a.title).content(a.content).iframe(a.iframe).padding(a.padding).size(a.height, a.width).timeout(a.timeout),
				this.reset(),
				this.zIndex(),
				this.addEvent(),
				this.dom.dialog.show(),
				a.initialize && a.initialize.call(this),
				this
			},
			createHTML : function (b) {
				a("body").append(d.template.replace(/{tpl:([^}]+)}/g, function (a, c) {
						var d = b[c];
						return "string" == typeof d ? d : ""
					}));
				var c = {};
				return c.dialog = a("#" + b.id),
				c.shadow = a("#" + b.id + "_shadow"),
				c.title = a("#" + b.id + "_title"),
				c.content = a("#" + b.id + "_content"),
				c.close = a("#" + b.id + "_close"),
				c.buttons = a("#" + b.id + "_buttons"),
				c.timeout = a("#" + b.id + "_timeout"),
				b.shadow && a("#" + b.id + "_shadow").show(),
				b.close || a("#" + b.id + "_close").hide(),
				DIU.move(document.getElementById(b.id + "_titleBar"), document.getElementById(b.id)),
				c
			},
			button : function () {
				var g,
				h,
				i,
				j,
				k,
				d = this.dom.buttons,
				e = this.Listeners = this.Listeners || {},
				f = [].slice.call(arguments);
				for (g = 0; g < f.length; g++)
					val = f[g], h = val.value, i = val.id || h, j = !e[i], k = j ? document.createElement("button") : e[i].button, k.className = "btn", e[i] || (e[i] = {}), h && a(k).html(h), val.important && a(k).addClass("btn-primary"), val.remove && a(k).remove(), val.callback && (e[i].callback = val.callback), k[c + "callback"] = i, val.disabled ? (a(k).attr("disabled", !0), a(k).addClass("disabled")) : (a(k).attr("disabled", !1), a(k).removeClass("disabled")), j && (e[i].button = k, d.append(k)), val.focus && a(k).focus();
				return d.find("button").length < 1 ? d.hide() : d.show(),
				this
			},
			title : function (a) {
				var b = this.dom.title;
				return a ? (b.html(a), b.show()) : b.hide(),
				this
			},
			content : function (a) {
				return this.dom.content.html(a),
				this
			},
			fontSize : function (a) {
				return this.dom.content.css("font-size", a),
				this
			},
			alert : function (a) {
				return a = DIU.isNull(a) ? "" : a,
				this.dom.content.addClass("diu-dialog-content-alert").html(a),
				this.timeout(4),
				this
			},
			success : function (a) {
				return a = DIU.isNull(a) ? "" : a,
				this.dom.content.addClass("diu-dialog-content-success").html(a),
				this.timeout(4),
				this
			},
			error : function (a, b) {
				var c = DIU.isNull(a) ? "" : '<h5 class="error-text">' + a + "</h5>",
				d = DIU.isNull(b) ? "" : '<dl class="error-msg"><dt>错误信息</dt><dd>' + b + "</dd></dl>",
				e = c + d;
				return this.dom.content.addClass("diu-dialog-content-error").html(e),
				this.timeout(4),
				this
			},
			loading : function (a) {
				return a = DIU.isNull(a) ? "亲，我正在努力加载数据，请稍候..." : a,
				this.dom.content.html('<div class="diu-dialog-loading">' + a + "</div>"),
				this
			},
			posting : function (a) {
				return a = DIU.isNull(a) ? "亲，我正在提交保存数据，请稍候..." : a,
				this.dom.content.html('<div class="diu-dialog-posting">' + a + "</div>"),
				this
			},
			top : function () {
				var a = b,
				c = function (a) {
					try {
						var c = b[a].document;
						c.getElementsByTagName
					} catch (d) {
						return !1
					}
					return b[a].artDialog && 0 === c.getElementsByTagName("frameset").length
				};
				return c("top") ? a = b.top : c("parent") && (a = b.parent),
				a
			},
			footerFix : function (a) {
				return this.dom.dialog,
				height = a.height || "",
				this.dom.dialog.css({
					position : "fixed",
					left : "0px",
					top : "auto",
					bottom : "0px",
					width : "100%"
				}),
				this.dom.content.css({
					width : "100%"
				}),
				DIU.isNotNull(height) && (height = DIU.int(height) / 100 * DIU.webScreenHeight(), this.dom.content.css("height", height)),
				this
			},
			iframe : function (b) {
				var e,
				c = this,
				d = this.config.id;
				return b && (this.dom.content.css("overflow", "hidden"), e = c.top().document.createElement("iframe"), e.src = b + "&dialog_id=" + d, e.name = c.config.id + "_iframe", e.setAttribute("frameborder", 0, 0), e.setAttribute("frameborder", 0, 0), e.setAttribute("allowTransparency", !0), e.setAttribute("dialog", !0), $iframe = a(e), $iframe.addClass("diu-dialog-iframe").attr("id", "" + d + "_iframe"), c.dom.content.html(e), this.dom.iframe = e),
				this
			},
			padding : function (a) {
				return this.dom.content.css("padding", a),
				this
			},
			size : function (a, b) {
				var c = this.dom.title,
				d = this.dom.buttons;
				return a = DIU.int(a),
				b = DIU.int(b),
				"none" != c.css("display") && (a -= DIU.int(c.height())),
				"none" != d.css("display") && (a -= 40),
				a > 0 && (this.dom.content.css({
						height : a + "px"
					}), this.dom.content.find("iframe[name='dialog-iframe']").css({
						height : a + "px"
					})),
				b > 0 ? (this.dom.content.css({
						width : b + "px"
					}), this.dom.content.find("iframe[name='dialog-iframe']").css({
						width : b + "px"
					})) : (b = DIU.int(DIU.webScreenWidth()) - 80, this.dom.content.css("max-width", b + "px"), this.dom.content.find("iframe[name='dialog-iframe']").css("max-width", b + "px")),
				this
			},
			follow : function (b) {
				var c = this.dom.dialog,
				d = a(b),
				e = d.attr("offsetWidth"),
				f = d.attr("offsetHeight"),
				g = d.offset(),
				h = g.left,
				i = g.top,
				j = parseInt(c.width()),
				k = DIU.webScreenWidth();
				return h + j - 20 > k && (h = k - j - 100),
				c.css({
					position : "absolute",
					left : h + e / 2,
					top : i + f
				}),
				this
			},
			position : function (a) {
				var b,
				d,
				e,
				f,
				g,
				h;
				return a = a || "",
				b = this.dom.dialog,
				this.dom.title,
				d = "none" != this.dom.close.css("display") ? parseInt(this.dom.close.width()) : 0,
				e = parseInt(a.left || (DIU.webScreenWidth() - parseInt(b.width()) - d) / 2),
				f = parseInt(DIU.documentScrollTop()),
				g = (DIU.webScreenHeight() - parseInt(b.height())) / 2,
				h = parseInt(a.top || f + g) + 2,
				0 > e && (e = 2 * e),
				10 > h && (h = 10),
				b.css({
					position : "absolute",
					left : e,
					top : h
				}),
				this
			},
			fixTop : function (a) {
				a = DIU.int(a);
				var b = this.dom.dialog,
				c = "none" != this.dom.close.css("display") ? parseInt(this.dom.close.width()) : 0;
				left = parseInt((DIU.webScreenWidth() - parseInt(b.width()) - c) / 2),
				b.css({
					position : "fixed",
					left : left + "px",
					top : a + "px"
				})
			},
			reset : function () {
				return this.config.follow ? this.follow(this.config.follow) : this.position(this.config.position),
				this
			},
			shadow : function (a) {
				return a ? this.dom.shadow.show() : this.dom.shadow.hide(),
				this
			},
			shadowBackground : function (a) {
				return this.dom.shadow.css("background", a),
				this
			},
			close : function () {
				var a = this.dom.dialog,
				b = this.config.beforeClose,
				c = this.config.afterClose;
				return this.removeEvent(),
				b && b.call(this) === !1 ? this : (this.dom.shadow.remove(), a.remove(), c && c.call(this) === !1 ? this : void 0)
			},
			removeClose : function (a) {
				var b = a ? a : !0;
				b && this.dom.close.hide()
			},
			click : function (a) {
				var b = this.Listeners[a] && this.Listeners[a].callback;
				return "function" != typeof b || b.call(this) !== !1 ? this.close() : this
			},
			timeout : function (a) {
				var c = this,
				d = this.TIMER;
				return d && clearTimeout(d),
				null !== a && (this.dom.timeout.html(this.config.timeTip.replace("{tpl:time}", a)), a = parseInt(a - 1), a >= 0 ? this.TIMER = b.setTimeout(function () {
							c.timeout(a)
						}, 1e3) : c.close()),
				this
			},
			addEvent : function () {
				var a = this,
				b = this.dom,
				d = this.dom.dialog;
				d.bind("click", function (d) {
					var e,
					f = d.target;
					return f.disabled ? !1 : f === b.close[0] ? (a.click("cancel"), !1) : (e = f[c + "callback"], e && a.click(e), void 0)
				}).bind("mousedown", function () {})
			},
			removeEvent : function () {
				this.dom.dialog.unbind()
			},
			zIndex : function () {
				var a = this.dom,
				b = DIUDialog.defaults.zIndex++;
				return a.shadow.css("zIndex", b + 1),
				a.dialog.css("zIndex", b + 2),
				this
			}
		},
		d.fn.constructor.prototype = d.fn,
		d.defaults = {
			id : "dialog",
			title : null,
			content : '<div class="diu-dialog-loading">loading ...</div>',
			iframe : null,
			close : !0,
			beforeClose : null,
			afterClose : null,
			shadow : !0,
			button : null,
			ok : null,
			okValue : "确定",
			cancel : null,
			cancelValue : "取消",
			initialize : null,
			width : "auto",
			height : "auto",
			padding : "15px 20px",
			timeout : null,
			timeTip : "<b>{tpl:time}</b> 秒后自动关闭",
			follow : null,
			position : null,
			zIndex : 1900
		},
		d.template = '<div class="diu-dialog" role="dialog" id="{tpl:id}" style="display:none;"><div class="diu-dialog-wrapper"><table border="0" cellpadding="0" cellspacing="0" class="diu-dialog-inner"><tbody><tr><td class="diu-dialog-header"><div class="diu-dialog-titleBar" id="{tpl:id}_titleBar"><div class="diu-dialog-title" id="{tpl:id}_title"></div><a class="diu-dialog-close" id="{tpl:id}_close" href="javascript:;"></a></div></td></tr><tr><td class="diu-dialog-main"><div class="diu-dialog-content" id="{tpl:id}_content"></div></td></tr><tr><td colspan="2" class="diu-dialog-footer"><div class="diu-dialog-buttons" id="{tpl:id}_buttons"></div></td></tr></tbody></table></div><div class="diu-dialog-timeout" id="{tpl:id}_timeout"></div></div><div class="diu-dialog-shadow" id="{tpl:id}_shadow" style="display:none;"></div>',
		DIUDialog = d
	},
	this.copyright = function () {
		var a = ">d>i>u>8>d>i>u>";
		return DIU.rep(a, ">", "")
	},
	this.move = function (a, b) {
		var c,
		d,
		e,
		f;
		e = b.style,
		f = document,
		a.onselectstart = function () {
			return !1
		},
		a.onmousedown = function (a) {
			a = a || event,
			c = a.clientX - b.offsetLeft,
			d = a.clientY - b.offsetTop,
			f.onmousemove = function (a) {
				a = a || event,
				e.left = a.clientX - c + "px",
				e.top = a.clientY - d + "px"
			},
			f.onmouseup = function () {
				f.onmouseup = f.onmousemove = ""
			}
		}
	},
	this.VALIDFORM_Class = function (a) {
		var c = function (a) {
			var f,
			e = c.defaults;
			a = a || {},
			("string" == typeof a || 1 === a.nodeType) && (a = {
					content : a
				});
			for (f in e)
				void 0 === a[f] && (a[f] = e[f]);
			return new c.fn.constructor(a)
		};
		c.fn = c.prototype = {
			constructor : function (a) {
				return this.config = a,
				this.result = !1,
				this.formData = "",
				this.formJsonData = "",
				a.initialize && a.initialize.call(this),
				this
			},
			getFormData : function () {
				var c,
				d,
				e,
				f,
				g,
				h,
				i = this.config.form.attr("form_id"),
				j = this.config.form.find("input:text,input:password,input:hidden,input:radio:checked,input:checkbox:checked,textarea,select");
				j.each(function () {
					e = a(this).attr("name"),
					f = escape(a(this).val()),
					g = e + "=" + f,
					h = DIU.isNull(h) ? g : h + "&" + g,
					c = '"' + e + '":"' + f + '"',
					d = DIU.isNull(d) ? c : d + "," + c
				}),
				d = DIU.isNull(d) ? "" : '"form_' + i + '":{' + d + "}",
				this.formData = h,
				this.formJsonData = d
			},
			getVerifyCodeData : function () {
				this.getVerifyCodeData = "verifycode_name=" + this.config.form.find("input[name='verifycode_name']") + "&verifycode_value=" + this.config.form.find("input[name='verifycode_value']")
			},
			verify : function () {
				var c,
				d,
				e,
				f,
				g,
				h,
				i,
				b = !0,
				j = this.config.form;
				$elems = j.find("input:not(:checkbox,:radio),textarea,select"),
				$radios = j.find("input:checkbox,input:radio"),
				$elems.each(function () {
					c = a(this).attr("name"),
					d = a(this).attr("datatype") || "",
					e = a(this).attr("errmsg") || "",
					g = a(this).attr("tips") || "",
					f = a(this).val(),
					h = j.find("span[name='t_" + c + "']"),
					"*" == d && (DIU.isNull(f) ? (DIU.isObjExist(i) || (i = a(this)), a(this).focus(), h.addClass("t-err").html(e + DIU.iif(DIU.isNotNull(g), "(" + g + ")", "")), b = !1) : (a(this).attr("valid_focus", "false"), h.removeClass("t-err").html(g)))
				}),
				$radios.each(function () {
					c = a(this).attr("name"),
					d = a(this).attr("datatype") || "",
					e = a(this).attr("errmsg") || "",
					g = a(this).attr("tips") || "",
					f = a(this).val(),
					h = j.find("span[name='t_" + c + "']"),
					"*" == d && (j.find("input[name='" + c + "']:checked").length >= 1 ? h.removeClass("t-err").html(g) : (h.addClass("t-err").html(e), a(this).focus(), b = !1))
				}),
				0 == b && i.focus(),
				this.result = b
			}
		},
		c.fn.constructor.prototype = c.fn,
		c.defaults = {
			form : null,
			initialize : null
		},
		DIUValidForm = c
	}
}
function Cookie_Class() {
	var a = this;
	this.cookiePre = "",
	this.cookieDomain = "",
	this.cookiePath = "",
	this.cookieSecure = !1,
	this.setCookie = function () {
		var g,
		h,
		i,
		b = a.setCookie.arguments,
		c = a.setCookie.arguments.length,
		d = DIU.isNull(b[4]) ? a.cookiePath : b[4],
		e = DIU.isNull(b[5]) ? a.cookieDomain : b[5];
		return DIU.isNull(b[6]) ? a.cookieSecure : b[6],
		2 > c ? !1 : (g = a.cookiePre + b[0], h = g + "=" + a.encode(b[1]), c >= 3 && (i = new Date, i.setTime(i.getTime() + b[2]), h += "; expires=" + i.toGMTString()), h += DIU.isNull(d) ? "" : "; path=" + d, h += DIU.isNull(e) ? "" : "; domain=" + e, h += 1 == b[5] ? "; secure" : "", h.length <= 4e3 ? (document.cookie = h, !0) : confirm("Cookie exceeds 4KB and will be cut!") ? (document.cookie = h, !0) : void 0)
	},
	this.getCookie = function (b) {
		var d,
		e,
		f,
		g,
		h,
		i,
		j,
		c = "";
		for (b.indexOf(":") > 0 && (c = b.split(":")[1], b = b.split(":")[0]), d = a.cookiePre + b + "=", e = document.cookie.split(";"), i = 0; i < e.length; i++) {
			for (f = unescape(e[i]); " " == f.charAt(0); )
				f = f.substring(1, f.length);
			if (0 == f.indexOf(d)) {
				if (g = a.decode(f.substring(d.length, f.length)), "" != c)
					for (h = g.split("&"), g = "", j = 0; j < h.length; j++)
						c == h[j].split("=")[0] && (g = h[j].split("=")[1]);
				return g
			}
		}
		return ""
	},
	this.remove = function (b) {
		a.setCookie(b, "")
	},
	this.encode = function (a) {
		return unescape(a)
	},
	this.decode = function (a) {
		return unescape(unescape(a))
	}
}
function JSON_Class() {
	this.parse = function (a) {
		return $.parseJSON(a)
	},
	this.toString = function (a) {
		return JSON.stringify(a)
	},
	this.toJSONString = function (a) {
		return JSON.stringify(a)
	},
	this.del = function (a, b) {
		var c,
		d,
		e;
		for (a = parseInt(a), c = b.length, d = 0; c > d; d++)
			if (d == a) {
				for (e = d + 1; c > e; e++)
					b[e - 1] = b[e];
				b.length--
			}
		return b
	}
}
function closeDialog(a) {
	var b = a || {},
	c = b.refresh === !0 ? !0 : !1,
	d = b.callBack || {},
	e = $("iframe[dialog='true']"),
	f = e.attr("id").replace("_iframe", "");
	if ($("#" + f + ",#" + f + "_shadow").delayRemove(100), c)
		DIU.refresh();
	else
		try {
			d.call()
		} catch (g) {}
}
function Encrypt_Class() {
	this.aes5 = function (a) {
		a = DIU.encrypt.getByteArray(a);
		for (var d, e, g, h, i, b = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "/", "="], c = "", f = "", j = "", k = 0, l = a.length, m = l - l % 3; m > k; )
			d = a[k++], e = a[k++], f = a[k++], g = d >> 2, h = (3 & d) << 4 | e >> 4, i = (15 & e) << 2 | f >> 6, j = 63 & f, c += b[g] + b[h] + b[i] + b[j];
		return 2 == l - m ? (d = a[k++], e = a[k++], c += b[d >> 2] + b[(3 & d) << 4 | e >> 4] + b[(15 & e) << 2] + "=") : 1 == l - m && (d = a[k++], c += b[d >> 2] + b[(3 & d) << 4] + "=="),
		c
	},
	this.encode = function (a) {
		return a = DIU.encrypt.aes5(a),
		a = "DIU" + a.replace(/=/g, ".").replace("a", "1L1LWA").replace("b", "2Z2ZWB").replace("c", "3V3VWC").replace("d", "4X4XWD").replace("e", "5Y5YWD")
	},
	this.getByteArray = function (a) {
		var d,
		f,
		b = a.length,
		c = 0,
		e = [];
		if (0 >= b)
			return [];
		for (; b > c; )
			d = a.charCodeAt(c), 127 > d ? e.push(d) : (f = DIU.encrypt.utoutf8(d), f > 16777215 ? e.push(a >>> 24, 255 & a >> 16, 255 & a >> 8, 255 & a) : f > 65535 ? e.push(f >> 16, 255 & f >> 8, 255 & f) : f > 255 && e.push(f >> 8, 255 & f)), c++;
		return e
	},
	this.utoutf8 = function (a) {
		var b,
		c,
		d,
		e,
		f;
		return 127 >= a ? a : 2047 >= a ? (b = 192 | a >> 6, c = 128 | 63 & a, b << 8 | c) : 65535 >= a ? (b = 224 | a >> 12, c = 128 | 63 & a >> 6, d = 128 | 63 & a, b << 16 | c << 8 | d) : 1114111 >= a ? (b = 240 | a >> 18, c = 128 | 63 & a >> 12, d = 128 | 63 & a >> 6, e = 128 | 63 & a, f = b << 24 | c << 16 | d << 8 | e, 0 > f && (f += 4294967296), f) : 0
	}
}
var DIUDialog, DIUValidForm, DIU = new DIU_Class;
DIU.DIALOG_Class(this.jQuery, this), DIU.VALIDFORM_Class(this.jQuery, this), jQuery.fn.extend({
	delayRemove : function (a) {
		var b = this;
		b.hide(a),
		window.setTimeout(function () {
			b.remove()
		}, a)
	}
}), this.JSON = {}, function () {
	"use strict";
	function f(a) {
		return 10 > a ? "0" + a : a
	}
	function quote(a) {
		return escapable.lastIndex = 0,
		escapable.test(a) ? '"' + a.replace(escapable, function (a) {
			var b = meta[a];
			return "string" == typeof b ? b : "\\u" + ("0000" + a.charCodeAt(0).toString(16)).slice(-4)
		}) + '"' : '"' + a + '"'
	}
	function str(a, b) {
		var c,
		d,
		e,
		f,
		h,
		g = gap,
		i = b[a];
		switch (i && "object" == typeof i && "function" == typeof i.toJSON && (i = i.toJSON(a)), "function" == typeof rep && (i = rep.call(b, a, i)), typeof i) {
		case "string":
			return quote(i);
		case "number":
			return isFinite(i) ? String(i) : "null";
		case "boolean":
		case "null":
			return String(i);
		case "object":
			if (!i)
				return "null";
			if (gap += indent, h = [], "[object Array]" === Object.prototype.toString.apply(i)) {
				for (f = i.length, c = 0; f > c; c += 1)
					h[c] = str(c, i) || "null";
				return e = 0 === h.length ? "[]" : gap ? "[\n" + gap + h.join(",\n" + gap) + "\n" + g + "]" : "[" + h.join(",") + "]",
				gap = g,
				e
			}
			if (rep && "object" == typeof rep)
				for (f = rep.length, c = 0; f > c; c += 1)
					d = rep[c], "string" == typeof d && (e = str(d, i), e && h.push(quote(d) + (gap ? ": " : ":") + e));
			else
				for (d in i)
					Object.hasOwnProperty.call(i, d) && (e = str(d, i), e && h.push(quote(d) + (gap ? ": " : ":") + e));
			return e = 0 === h.length ? "{}" : gap ? "{\n" + gap + h.join(",\n" + gap) + "\n" + g + "}" : "{" + h.join(",") + "}",
			gap = g,
			e
		}
	}
	"function" != typeof Date.prototype.toJSON && (Date.prototype.toJSON = function () {
		return isFinite(this.valueOf()) ? this.getUTCFullYear() + "-" + f(this.getUTCMonth() + 1) + "-" + f(this.getUTCDate()) + "T" + f(this.getUTCHours()) + ":" + f(this.getUTCMinutes()) + ":" + f(this.getUTCSeconds()) + "Z" : null
	}, String.prototype.toJSON = Number.prototype.toJSON = Boolean.prototype.toJSON = function () {
		return this.valueOf()
	});
	var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
	escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
	gap,
	indent,
	meta = {
		"\b" : "\\b",
		"	" : "\\t",
		"\n" : "\\n",
		"\f" : "\\f",
		"\r" : "\\r",
		'"' : '\\"',
		"\\" : "\\\\"
	},
	rep;
	"function" != typeof JSON.stringify && (JSON.stringify = function (a, b, c) {
		var d;
		if (gap = "", indent = "", "number" == typeof c)
			for (d = 0; c > d; d += 1)
				indent += " ";
		else
			"string" == typeof c && (indent = c);
		if (rep = b, b && "function" != typeof b && ("object" != typeof b || "number" != typeof b.length))
			throw new Error("JSON.stringify");
		return str("", {
			"" : a
		})
	}),
	"function" != typeof JSON.parse && (JSON.parse = function (text, reviver) {
		function walk(a, b) {
			var c,
			d,
			e = a[b];
			if (e && "object" == typeof e)
				for (c in e)
					Object.hasOwnProperty.call(e, c) && (d = walk(e, c), void 0 !== d ? e[c] = d : delete e[c]);
			return reviver.call(a, b, e)
		}
		var j;
		if (text = String(text), cx.lastIndex = 0, cx.test(text) && (text = text.replace(cx, function (a) {
						return "\\u" + ("0000" + a.charCodeAt(0).toString(16)).slice(-4)
					})), /^[\],:{}\s]*$/.test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, "@").replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, "]").replace(/(?:^|:|,)(?:\s*\[)+/g, "")))
			return j = eval("(" + text + ")"), "function" == typeof reviver ? walk({
				"" : j
			}, "") : j;
		throw new SyntaxError("JSON.parse")
	})
}
();
