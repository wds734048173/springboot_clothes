function Client() {
	this.typeInit = function (a) {
		var c,
		d,
		b = a.section,
		e = DIU.typeid.split("-");
		for (c = 0; c < e.length; c++)
			d = DIU.int(e[c]), b.find("[name='ctype'][type_id='" + d + "']").addClass("current")
	},
	this.uploadInput = "",
	this.uploadDialog = "",
	this.upload = function (a) {
		DIU.client.uploadInput = $("input[name='" + a.inputName + "']"),
		DIU.client.uploadDialog = DIUDialog({
				follow : b
			}).posting("正在上传，请稍后 ... ").position();
		var b = document.getElementById(a.iframeId),
		c = $(b.contentWindow.document.getElementById("upload_form"));
		c.submit()
	},
	this.uploadCallback = function (a, b) {
		a ? (DIU.client.uploadDialog.success("上传完毕").position().timeout(2), DIU.client.uploadInput.val(b)) : DIU.client.uploadDialog.error("上传失败", b).position().timeout(3)
	},
	this.uploadNotOpen = function (a) {
		$("button[name='" + a + "']").hide()
	},
	this.serviceOnline = function (a) {
		var b = "1" == a.open ? !0 : !1,
		c = a.data || "",
		d = "service-online",
		e = function () {
			var b,
			e,
			f,
			g,
			i,
			j,
			k,
			l,
			m,
			n,
			o,
			q,
			r,
			s,
			t,
			h = "";
			try {
				b = $.parseJSON(c)
			} catch (p) {
				b = $.parseJSON("[]")
			}
			for (e = 0; e < b.length; e++)
				i = unescape(b[e].name || ""), j = unescape(b[e].tel || ""), k = unescape(b[e].qq || ""), l = unescape(b[e].crmqq || ""), m = unescape(b[e].wangwang || ""), n = unescape(b[e].skype || ""), o = unescape(b[e].text || ""), "" != j && (j = '<dd class="tel">' + j + "</dd>"), "" != k && (k = '<dd class="qq"><a href="http://wpa.qq.com/msgrd?v=3&uin=' + k + '&site=qq&menu=yes" target="_blank"></a></dd>'), "" != l && (l = '<dd class="crmqq"><a href="' + l + '" target="_blank"></a></dd>'), "" != m && (m = '<dd class="wangwang"><a target="_blank" href="http://www.taobao.com/webww/ww.php?ver=3&touid=' + m + '&siteid=cntaobao&status=1&charset=utf-8"><img border="0" src="http://amos.alicdn.com/realonline.aw?v=2&uid=' + m + '&site=cntaobao&s=1&charset=utf-8" alt="点击这里给我发消息" /></a></dd>'), "" != n && (n = '<dd class="skype"><script type="text/javascript" src="http://skype.tom.com/script/skypeCheck40.js"></script><a href="skype:' + n + '?call" onclick="return skypeCheck();"><img src="http://mystatus.skype.com/bigclassic/' + n + '" style="border: none;" width="80" height="24" alt="My status" /></a></dd>'), "" != o && (o = '<dd class="skype">' + o + "</dd>"), g = "<dl><dt>" + i + "</dt>" + j + k + l + m + n + o + "</dl>", h = "" == g ? g : h + "" + g;
			f = '<div class="' + d + '"><a href="javascript:;" class="mini"></a><div class="listpanel">' + '<div class="heading"><a class="close" href="javascript:;"></a></div>' + '<div class="section">' + h + "</div>" + "</div></div>",
			$("body").append(f),
			q = $("div." + d).find(".mini"),
			r = $("div." + d).find(".listpanel"),
			s = $("div." + d).find("a.close"),
			"mini" == a.initType ? (q.show(), r.hide()) : (q.hide(), r.show()),
			q.click(function () {
				q.hide(),
				r.show("fast")
			}),
			s.click(function () {
				q.show("fast"),
				r.hide("fast")
			}),
			t = parseInt($("div." + d).css("top")),
			$(window).scroll(function () {
				$("div." + d).css("top", parseInt(document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop) + t)
			})
		};
		b && "" != c && e()
	}
}
function ADClass() {
	this.count = function () {},
	this.parse = function (a) {
		var b,
		c,
		e,
		d = "",
		f = "",
		g = "",
		h = $("#diu_ad_" + a),
		i = {},
		j = {},
		k = {},
		l = {},
		m = {},
		n = h.attr("data"),
		o = h.attr("type"),
		p = h.attr("ad_height"),
		q = h.attr("ad_width"),
		r = DIU.int(h.attr("full_screen")),
		s = DIU.int(h.attr("switch_time")),
		w = -DIU.int(q) / 2,
		x = "",
		y = "",
		z = "";
		if (1 > s && (s = 6), "" != n)
			switch (o) {
			case "image":
				if (e = DIU.JSON.parse(n), 1 == e.length)
					x = DIU.trim(e[0].link), y = 'target="_blank"', (DIU.isNull(x) || "#" == x) && (z = "cursor-default", x = "javascript:;", y = ""), h.html('<div class="diu-ad-pic"><a class="' + z + '" href="' + x + '" ' + y + '><img src="' + e[0].url + '"></a></div>').show(), j = h.find(".diu-ad-pic").css({
							height : p,
							width : q
						}), k = h.find("a").css({
							height : p,
							width : q
						}), l = h.find("img").css({
							height : p,
							width : q
						}), r && (h.css({
							width : "100%"
						}), j.css({
							overflow : "hidden",
							position : "absolute",
							width : "100%"
						}), k.css({
							left : "50%",
							"margin-left" : w,
							position : "absolute",
							width : q
						}));
				else if (e.length > 1) {
					for (b = '<div class="diu-pic-player">', c = 0; c < e.length; c++)
						DIU.isNull(e[c].link) || "#" == e[c].link ? (z = "cursor-default", x = "javascript:;", y = "") : (z = "", x = DIU.trim(e[c].link), y = 'target="_blank"'), d = d + '<li i="' + c + '"><a class="' + z + '" href="' + x + '" ' + y + '><img src="' + e[c].url + '"></a><div class="title"><a class="' + z + '" href="' + x + '" ' + y + ">" + unescape(e[c].name) + "</a></div></li>", f = f + '<a href="javascript:;" i="' + c + '">' + (c + 1) + "</a>";
					b = b + '<ul class="sliders" name="sliders">' + d + "</ul>",
					b = b + '<div class="pages" name="pages">' + f + "</div>",
					b += '<div class="turner" name="turner"><a class="prev" href="javascript:;"></a><a class="next" href="javascript:;"></a></div>',
					b += "</div>",
					h.html(b).show(),
					i = h.find(".diu-pic-player").css({
							height : p,
							width : q
						}),
					$sliders = h.find("ul[name='sliders']").css({
							height : p,
							width : q
						}),
					l = h.find("img").css({
							height : p,
							width : q
						}),
					m = h.find("div[name='pages']"),
					r && (h.css({
							width : "100%"
						}), i.css({
							width : "100%"
						}), $sliders.css({
							left : "50%",
							"margin-left" : w
						}), m.css({
							left : "50%",
							"margin-left" : -DIU.int(m.width()) / 2
						})),
					DIU.ad.picPlayer({
						obj : h,
						timer : 1e3 * s
					})
				}
				break;
			case "flash":
				e = DIU.JSON.parse(n),
				g = e.url,
				b = '<embed src="' + g + '" quality="high" wmode="opaque" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash">',
				h.html(b).show(),
				h.find("embed").css({
					height : p,
					width : h.css("width")
				})
			}
	},
	this.picPlayer = function (a) {
		var b,
		l,
		m,
		n,
		o,
		c = a.obj,
		d = a.timer,
		e = c.find("ul[name='sliders']"),
		f = c.find("div[name='pages']"),
		g = c.find("div[name='turner']").hide(),
		h = g.find("a.prev"),
		i = g.find("a.next"),
		j = f.find("a"),
		k = j.length;
		e.find("li").hide(),
		e.find("li:first").show(),
		f.find("a:first").addClass("current"),
		h.click(function () {
			o();
			var a,
			b = parseInt(f.find("a.current").attr("i"));
			a = 0 == b ? k - 1 : b - 1,
			m(b, a)
		}),
		i.click(function () {
			o();
			var a,
			b = parseInt(f.find("a.current").attr("i"));
			a = b == k - 1 ? 0 : b + 1,
			m(b, a),
			n()
		}),
		j.click(function () {
			var a,
			b;
			o(),
			a = parseInt(f.find("a.current").attr("i")),
			b = parseInt($(this).attr("i")),
			a != b && m(a, b),
			n()
		}),
		l = function () {
			var a,
			b = parseInt(f.find("a.current").attr("i"));
			a = b == k - 1 ? 0 : b + 1,
			m(b, a)
		},
		m = function (a, b) {
			e.find("li:eq(" + a + ")").hide(),
			e.find("li:eq(" + b + ")").fadeIn("fast"),
			f.find("a:eq(" + a + ")").removeClass("current"),
			f.find("a:eq(" + b + ")").addClass("current")
		},
		n = function () {
			d && (b = window.clearInterval(b), b = window.setInterval(function () {
						l()
					}, d))
		},
		o = function () {
			d && (b = window.clearInterval(b))
		},
		c.hover(function () {
			o(),
			g.show()
		}, function () {
			n(),
			g.hide()
		}).mouseout()
	}
}
function CMTClass() {
	var a = this;
	this.pagesize = 50,
	this.page = 1,
	this.countSection = {},
	this.listSection = {},
	this.pagerSection = {},
	this.getCallBack = function () {},
	this.init = function (b) {
		var f,
		c = b.cid,
		d = b.gid,
		e = DIU.cookie.getCookie("avatar");
		e = DIU.iif(DIU.isNull(e), DIU.siteUrl + "diu-content/images/avatar.jpg", e),
		f = '<div class="diu-diucmt-post"><div class="cmt-avatar"><img src="' + e + '"></div><div class="cmt-post"><textarea class="comment-text" id="diu_oscmt_comment_' + c + "_" + d + '_0" placeholder="说点什么吧" ></textarea></div><div class="cmt-btnline"><a href="javascript:;" onClick="DIU.cmt.post({cid:' + c + ",gid:" + d + ',cmtid:0})" class="btn-diucmt" name="diu_btn_diucmt">发布</a></div></div>',
		a.postSection.html(f)
	},
	this.get = function (b) {
		var c = b.cid,
		d = b.gid,
		e = b.page,
		f = a.countSection,
		g = a.listSection,
		h = a.pagerSection;
		a.page = e,
		g.html('<div class="diu-data-loading">成交记录加载中...</div>'),
		DIU.ajax({
			url : DIU.sitePath + "diu-includes/diu.ajax.main.asp?ctl=diucmt&act=get&cid=" + c + "&gid=" + d + "&page=" + e + "&pagesize=" + a.pagesize,
			data : "",
			success : function () {
				var b = DIU.int(DIU.ajaxData.count),
				c = '<div class="diu-diucmt-list">' + unescape(DIU.ajaxData.list) + "</div>",
				d = '<div class="diu-diucmt-list-pager">' + unescape(DIU.ajaxData.pager) + "</div>";
				f.html(b),
				g.hide().html(c).show(200),
				h.html(d),
				a.getCallBack()
			},
			failed : function (a) {
				g.html("亲，很抱歉，评论数据加载失败，请刷新页面再试试！<br>" + a)
			}
		})
	},
	this.post = function (b) {
		var h,
		c = b.cid,
		d = b.gid,
		e = b.cmtid,
		f = $("#diu_oscmt_comment_" + c + "_" + d + "_" + e),
		g = f.val();
		DIU.isNull(g) ? f.focus() : (h = DIUDialog().posting().position(), DIU.ajax({
				url : DIU.siteUrl + "diu-includes/diu.ajax.main.asp?ctl=diucmt&act=post&save=true",
				data : "cid=" + c + "&gid=" + d + "&cmtid=" + e + "&comment=" + escape(g),
				success : function () {
					h.success("已发布").position().timeout(2),
					DIU.delay(1e3, function () {
						f.val(""),
						DIU.cmt.get({
							cid : c,
							gid : d,
							page : a.page
						})
					})
				},
				failed : function (a) {
					h.error("亲，评论失败，请查看以下原因", a).position().timeout(4)
				}
			}))
	},
	this.toReply = function (a) {
		var b = a.cid,
		c = a.gid,
		d = a.cmtid,
		e = $("#diu_diucmt_reply_post_" + d),
		f = '<div class="reply-textarea"><input type="text" class="reply-text" id="diu_oscmt_comment_' + b + "_" + c + "_" + d + '" maxlength="250" placeholder="我有话说..." /></div><div class="reply-btnline"><a href="javascript:;" onClick="DIU.cmt.post({cid:' + b + ",gid:" + c + ",cmtid:" + d + '})" class="btn-reply">发布</a></div>';
		e.find("input").length > 0 ? e.slideUp().html("") : e.html(f).slideDown()
	}
}
function linkClass() {
	this.count = function (a) {
		var b = DIU.sitePath + "diu-includes/diu.ajax.main.asp?ctl=link&act=count";
		DIU.ajax({
			url : b,
			data : "link_id=" + a,
			success : function () {
				$dialog.close()
			},
			failed : function (a) {
				$dialog.error("亲，提交失败，请查看以下错误信息", a).timeout(5)
			}
		})
	}
}
function galleryClass() {
	var a,
	b,
	c,
	d,
	e,
	f;
	this.init = function (g) {
		var h,
		i,
		m,
		n,
		o,
		p,
		r,
		s,
		t;
		a = g.data || "{}",
		b = g.gallery,
		c = g.view || $(document.createElement("div")).addClass("gallery-view").attr("create", "true"),
		d = g.list || $(document.createElement("div")).addClass("gallery-list").attr("create", "true"),
		r = "",
		s = "",
		t = !1;
		try {
			a = DIU.rep(a, "&quot;", '"'),
			i = DIU.JSON.parse(a),
			i.length > 0 && (t = !0)
		} catch (u) {}
		if (t) {
			for (h = 0; h < i.length; h++)
				m = i[h].url, r = r + '<li><a class="cloud-zoom-gallery" name="cloud_zoom_gallery" bigimg="' + m + '" href="javascript:;" rel="useZoom:\'bigimg_view\',smallImage:\'' + m + '\'"><img src="' + m + '" alt="' + i[h].name + '"></a>';
			s = '<a class="turner prev" href="javascript:;" style="display:none;"></a><a class="turner next" href="javascript:;" style="display:none;"></a>',
			d.append("<ul>" + r + "</ul>"),
			d.append(s),
			c.append('<a class="cloud-zoom" name="cloud_zoom" id="bigimg_view" rel="adjustY:0,adjustX:5" bigimg="' + i[0].url + '" href="javascript:;"><img alt="' + i[0].name + '" class="view" src="' + i[0].url + '"></a>'),
			"true" == d.attr("create") && (b.html(""), b.append(c), b.append(d)),
			e = b.find(".prev"),
			f = b.find(".next"),
			n = parseInt(b.find(".gallery-list").css("width")),
			o = parseInt(b.find("li").css("width")) + parseInt(b.find("li").css("margin-right")),
			p = parseInt(n / o),
			p < i.length && b.find(".gallery-list").hover(function () {
				"0" != e.attr("valid") && e.show(),
				"0" != f.attr("valid") && f.show()
			}, function () {
				e.hide(),
				f.hide()
			}),
			e.click(function () {
				var a = parseInt(b.find("li[hide='1']").length),
				c = parseInt(b.find("li[hide!='1']").length);
				a > 0 && (a -= 1),
				b.find("li:eq(" + a + ")").attr("hide", 0).show(100),
				b.find("li:eq(" + (a - 1) + ")").attr("hide", 0).show(100),
				c + 2 > p && f.attr("valid", 1).show(100),
				2 > a ? e.attr("valid", 0).hide(100) : e.attr("valid", 1).show(100)
			}),
			f.click(function () {
				var a = parseInt(b.find("li[hide='1']").length),
				c = parseInt(b.find("li[hide!='1']").length);
				c > p && (b.find("li:eq(" + a + ")").attr("hide", 1).hide(100), b.find("li:eq(" + (a + 1) + ")").attr("hide", 1).hide(100)),
				2 >= c - p && (f.attr("valid", 0).hide(100), e.attr("valid", 1).show(100)),
				a > 0 && e.attr("valid", 1).show(100)
			})
		}
	},
	this.click = function () {
		b.find(".gallery-list").find("a").click(function () {
			b.find(".cloud-zoom").find("img").attr("src", $(this).attr("bigimg")),
			$(this).parents("ul").children("li").removeClass("current"),
			$(this).parent("li").addClass("current")
		})
	},
	this.cloudZoom = function (a) {
		$(a).CloudZoom()
	}
}
function memberClass() {
	var a = this;
	this.forgetPassword = function (a) {
		var b = a.tab,
		c = a.by,
		d = a.form,
		e = a.btn,
		f = d.find("input[name='mobile']"),
		g = d.find("input[name='mobile_code']"),
		h = d.find("button[name='get_mobile_code']"),
		w = d.find("input[name='weixin']"),
		i = d.find("input[name='email']"),
		j = d.find("span[name='forget_verifycode']"),
		k = d.find("input[name='verifycode_value']"),
		l = d.attr("vcode_open");
		DIU.verifyCode({
			boxer : j
		}),
		DIU.verifyCodeValueBlur(k),
		DIU.verifyCodeValueFocus(k, j),
		b.find("a").click(function () {
			c = $(this).attr("by"),
			$(this).parent().addClass("current"),
			b.find("a[by!='" + c + "']").parent().removeClass("current"),
			"mobile" == c ? (d.find("[by='mobile']").show(), d.find("[by='email']").hide(), d.find("[by='weixin']").hide()) : ("email" == c ? (d.find("[by='email']").show(), d.find("[by='mobile']").hide(), d.find("[by='weixin']").hide()):(d.find("[by='email']").hide(), d.find("[by='mobile']").hide(), d.find("[by='weixin']").show()))
		}),
		DIU.getMobileCode({
			mb : f,
			mc : g,
			getmc : h,
			url : DIU.sitePath + "diu-includes/diu.ajax.member.asp?ctl=member&act=get_forget_mobile_code",
			vcodeOpen : l,
			vc : j,
			vv : k,
			isHide : !1
		}),
		DIU.enterClick(function () {
			e.click()
		}),
		e.click(function () {
			var q,
			a = !0,
			b = j.find("input[name='verifycode_name']"),
			e = DIU.sitePath + "diu-includes/diu.ajax.member.asp?ctl=member&act=forget",
			h = DIU.trim(f.val()),
			m = DIU.trim(g.val()),
			n = DIU.trim(i.val()),
			v = DIU.trim(w.val()),
			o = DIU.parseVerifyCode(k.val()),
			p = DIU.trim(b.val());
			f.val(h),
			k.val(o),
			"weixin" == c ? (DIU.isNull(v) && (a = !1, w.focus()), DIU.isNull(w) && a && (a = !1, w.focus()), 1 == l && DIU.isNull(o) && a && (a = !1, k.focus())) : ( "mobile" == c ? (DIU.isNull(h) && (a = !1, f.focus()), DIU.isNull(m) && a && (a = !1, g.focus())) : (DIU.isNull(n) && (a = !1, i.focus()), 1 == l && DIU.isNull(o) && a && (a = !1, k.focus()))),
			a && (q = DIUDialog().posting().position(), DIU.ajax({
					url : e,
					data : "by=" + c + "&mobile=" + DIU.encrypt.encode(h) + "&mobile_code=" + DIU.encrypt.encode(m) + "&weixin=" + DIU.encrypt.encode(v) + "&account=" + DIU.encrypt.encode(n) + "&verifycode_name=" + p + "&verifycode_value=" + o,
					success : function () {
						var a,
						b,
						e;
						q.close(),
						(("email" == c) || ("weixin" == c)) ? (a = unescape(DIU.trim(DIU.ajaxData.html)), d.html('<div class="success-tip">' + a + "</div>")) : (b = DIU.int(DIU.ajaxData.uid), e = DIU.trim(DIU.ajaxData.hash), DIU.openPage(DIU.siteUrl + "?ctl=forget_password&act=reset&hash=" + e + "&uid=" + b))
					},
					failed : function (a) {
						DIU.verifyCode({
							boxer : j
						}),
						k.val("").focus(),
						q.error("亲，提交失败，请查看以下错误信息", a).position().timeout(5)
					}
				}))
		})
	},
	this.forgetPasswordReset = function (a) {
		var b = a.form,
		c = a.btn,
		d = b.attr("uid"),
		e = b.attr("hash"),
		f = b.find("input[name='password']"),
		g = b.find("input[name='repassword']"),
		h = b.find("span[name='forget_verifycode']"),
		i = b.find("input[name='verifycode_value']"),
		j = b.attr("vcode_open");
		DIU.verifyCodeValueBlur(i),
		DIU.verifyCodeValueFocus(i, h),
		DIU.enterClick(function () {
			c.click()
		}),
		c.click(function () {
			var a = !0,
			b = !0,
			c = DIUDialog().posting().position();
			$vcodeName = h.find("input[name='verifycode_name']"),
			url = DIU.sitePath + "diu-includes/diu.ajax.member.asp?ctl=member&act=forget_reset",
			pw = DIU.trim(f.val()),
			rp = DIU.trim(g.val()),
			vv = DIU.parseVerifyCode(i.val()),
			vn = DIU.trim($vcodeName.val()),
			f.val(pw),
			g.val(rp),
			i.val(vv),
			DIU.isNull(pw) && (a = !1, f.focus()),
			DIU.isNull(rp) && a && (a = !1, g.focus()),
			1 == j && DIU.isNull(vv) && a && (a = !1, i.focus()),
			pw != rp && a && (a = !1, b = !1, c.alert("两次输入的密码不一致，请重新输入！").position().timeout(2), g.addClass("text-err").val("").focus()),
			a ? DIU.ajax({
				url : url,
				data : "uid=" + DIU.encrypt.encode(d) + "&hash=" + escape(e) + "&password=" + DIU.encrypt.encode(pw) + "&verifycode_name=" + vn + "&verifycode_value=" + vv,
				success : function () {
					c.success("亲，您已成功设置新密码！").position().timeout(2),
					DIU.redirect(DIU.siteUrl, 2)
				},
				failed : function (a) {
					DIU.verifyCode({
						boxer : h
					}),
					i.val("").focus(),
					c.error("亲，提交失败，请查看以下错误信息", a).position().timeout(3)
				}
			}) : b && c.close()
		})
	},
	this.getMyPoint = function () {
		var a = 0;
		return DIU.ajax({
			url : DIU.sitePath + "diu-includes/diu.ajax.member.asp?ctl=member&act=get_my_point",
			async : !1,
			success : function () {
				a = DIU.ajaxData.my_point
			}
		}),
		a
	},
	this.isLogined = function () {
		return DIU.ajax({
			url : DIU.sitePath + "diu-includes/diu.ajax.member.asp?ctl=member&act=is_logined",
			data : "",
			async : !1,
			success : function () {
				"true" == DIU.ajaxData.logined && (DIU.logined = !0)
			}
		}),
		DIU.logined
	},
	this.loginInit = function (a) {
		var b = !0,
		c = a.username,
		d = a.password,
		e = a.vcodeValue,
		f = a.verifycode,
		g = DIU.cookie.getCookie("last_login_user") || "";
		"" != g && c.val(g),
		"" == DIU.trim(c.val()) && (b = !1, c.focus()),
		"" == DIU.trim(d.val()) && b && (b = !1, d.focus()),
		"" == DIU.trim(e.val()) && b && (b = !1, e.focus(), DIU.verifyCode({
				boxer : f
			})),
		DIU.verifyCodeValueBlur(e),
		DIU.verifyCodeValueFocus(e, f)
	},
	this.loginSuccessCallBack = {},
	this.login = function (b) {
		var d = DIU.sitePath + "diu-includes/diu.ajax.member.asp?ctl=member&act=login",
		e = !0,
		f = {},
		g = b.btnLogin,
		h = b.username,
		i = b.password,
		j = b.verifycode,
		k = b.vcodeValue,
		l = j.find("input[name='verifycode_name']"),
		m = b.remember,
		n = b.success ? b.success : "",
		o = b.vcode_open,
		p = DIU.trim(h.val()),
		q = DIU.trim(i.val()),
		r = DIU.parseVerifyCode(k.val()),
		s = DIU.trim(l.val());
		k.val(r);
		try {
			f = DIUDialog({
					id : "d_logining"
				}).posting("登录中...")
		} catch (t) {
			return !1
		}
		e && DIU.setDisabled(g, !0),
		"" == p && e ? (e = !1, h.addClass("text-err").focus()) : h.removeClass("text-err"),
		"" == q && e ? (e = !1, i.addClass("text-err").focus()) : i.removeClass("text-err"),
		1 == o && ("" == r && e ? (e = !1, k.addClass("text-err").focus()) : k.removeClass("text-err")),
		e ? DIU.ajax({
			url : d,
			data : "username=" + DIU.encrypt.encode(p) + "&password=" + DIU.encrypt.encode(q) + "&verifycode_name=" + s + "&verifycode_value=" + r,
			success : function () {
				var b = DIU.cookie.getCookie("login_return_url");
				if (b = DIU.iif(DIU.isNull(b), DIU.sitePath + "diu-ucenter/", b), DIU.logined = !0, 1 == m.attr("checked") && DIU.cookie.setCookie("last_login_user", p), DIU.isNull(n))
					f.success("登陆成功，正在跳转...").position(), DIU.openPage(b);
				else {
					f.close(),
					a.userStatus({});
					try {
						a.loginWin.close()
					} catch (c) {}
					try {
						n.call()
					} catch (c) {}
				}
			},
			failed : function (a) {
				DIU.verifyCode({
					boxer : j
				}),
				DIU.setDisabled(g, !1),
				k.val("").focus(),
				f.error("亲，登陆失败，请查看以下错误信息", a).position().timeout(3)
			}
		}) : (f.close(), DIU.setDisabled(g, !1))
	},
	this.loginWin,
	this.loginWindow = function () {
		return DIU.shoppingIsNeedLogin ? DIU.openPage(DIU.loginUrl) : DIU.cookie.setCookie("unlogin_buy", "1"),
		!1
	},
	this.loginWindowLogining = function (a) {
		DIU.member.login({
			username : $("#login_win_form").find("input[name='username']"),
			password : $("#login_win_form").find("input[name='password']"),
			verifycode : $("#login_win_form").find("span[name='verifycode']"),
			vcodeValue : $("#login_win_form").find("input[name='verifycode_value']"),
			remember : $("#login_win_form").find("input[name='remember']"),
			btnLogin : $("#login_win_form").find("button[name='btn_login']"),
			success : a.success
		})
	},
	this.regInit = function (a) {
		var b = a.form,
		c = b.find("input[name='mobile']"),
		d = b.find("input[name='mobile_code']"),
		e = b.find("button[name='get_mobile_code']"),
		f = b.find("span[name='reg_verifycode']"),
		g = b.find("input[name='verifycode_value']"),
		h = b.find("input[name='username']"),
		i = b.attr("vcode_open");
		DIU.objExist(h) && h.blur(function () {
			$(this).val(DIU.parseUsername($(this).val()))
		}),
		DIU.objExist(c) && DIU.getMobileCode({
			mb : c,
			mc : d,
			getmc : e,
			url : DIU.sitePath + "diu-includes/diu.ajax.member.asp?ctl=member&act=get_reg_mobile_code",
			vcodeOpen : i,
			vc : f,
			vv : g,
			isHide : !0
		})
	},
	this.reg = function (a) {
		var c = DIU.sitePath + "diu-includes/diu.ajax.member.asp?ctl=member&act=reg",
		d = !0,
		e = !0,
		f = DIUDialog().posting("提交注册中..."),
		g = a.form,
		h = g.find("input[name='mobile']"),
		i = g.find("input[name='mobile_code']"),
		j = g.find("input[name='username']"),
		k = g.find("input[name='email']"),
		l = g.find("input[name='password']"),
		m = g.find("input[name='repassword']"),
		n = g.find("input[name='recommend_user']"),
		o = a.btnReg,
		p = a.verifycode,
		q = a.vcodeValue,
		r = p.find("input[name='verifycode_name']"),
		s = a.vcode_open,
		t = "",
		u = "",
		v = DIU.trim(j.val()),
		w = DIU.trim(k.val()),
		x = DIU.trim(l.val()),
		y = DIU.trim(m.val()),
		z = DIU.trim(q.val()),
		A = DIU.trim(r.val()),
		B = DIU.iif(DIU.objExist(n), DIU.trim(n.val()), "");
		d && DIU.setDisabled(o, !0),
		DIU.objExist(h) && (t = DIU.parseMobile(DIU.trim(h.val())), u = DIU.trim(i.val()), !DIU.isValidMobile(t) && d ? (d = !1, h.addClass("text-err").focus()) : h.removeClass("text-err"), "" == u && d ? (d = !1, i.addClass("text-err").focus()) : i.removeClass("text-err")),
		DIU.objExist(j) && ("" == v && d ? (d = !1, j.addClass("text-err").focus()) : j.removeClass("text-err")),
		DIU.objExist(k) && ("" == w && d ? (d = !1, k.addClass("text-err").focus()) : k.removeClass("text-err")),
		"" == x && d ? (d = !1, l.addClass("text-err").focus()) : l.removeClass("text-err"),
		DIU.objExist(h) || ("" == y && d ? (d = !1, m.addClass("text-err").focus()) : m.removeClass("text-err"), x != y && d && (d = !1, e = !1, f.error("两次输入的密码不一致，请重新输入！").position().timeout(2), l.addClass("text-err").val("").focus(), m.addClass("text-err").val("")), 1 == s && ("" == z && d ? (d = !1, q.addClass("text-err").focus()) : q.removeClass("text-err"))),
		d ? DIU.ajax({
			url : c,
			data : "mobile=" + DIU.encrypt.encode(t) + "&mobile_code=" + DIU.encrypt.encode(u) + "&username=" + DIU.encrypt.encode(v) + "&password=" + DIU.encrypt.encode(x) + "&email=" + DIU.encrypt.encode(w) + "&verifycode_name=" + A + "&verifycode_value=" + z + "&recommend_user=" + DIU.encrypt.encode(B),
			success : function () {
				DIU.cookie.setCookie("last_login_user", v),
				f.success("亲，恭喜您注册成功！").position().timeout(3),
				window.location.href = DIU.sitePath + "diu-ucenter/regboot.asp?ctl=regsuccess"
			},
			failed : function (a) {
				DIU.verifyCode({
					boxer : p
				}),
				DIU.setDisabled(o, !1),
				q.val("").focus(),
				f.error("亲，注册失败，请查看以下错误信息", a).position().timeout(3)
			}
		}) : (e && f.close(), DIU.setDisabled(o, !1))
	},
	this.userStatusSection = "",
	this.userStatus = function (b) {
		var c = b.userStatusSection ? b.userStatusSection : a.userStatusSection;
		a.userStatusSection = c,
		DIU.uid = DIU.int(DIU.cookie.getCookie("uid")),
		DIU.username = DIU.trim(DIU.cookie.getCookie("username")),
		DIU.nickname = DIU.trim(DIU.cookie.getCookie("nickname")),
		DIU.uid > 0 && "" != DIU.username && (DIU.htmlCacheOpen && DIU.member.isLogined(), 1 == DIU.logined && c.html('<a href="' + DIU.sitePath + 'diu-ucenter/" class="username">' + DIU.iif(DIU.isNull(DIU.nickname), DIU.username, DIU.nickname) + '</a><a class="logout" href="' + DIU.logoutUrl + '">退出</a>'))
	},
	this.wxLogin = function () {
		DIU.openPage(DIU.siteUrl + "diu-includes/module/login/weixin/")
	},
	this.qqLogin = function (a) {
		var b = a.appid || "",
		c = DIU.urlEncode(DIU.siteUrl + "diu-includes/module/login/qq/index.asp");
		DIU.openPage("https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=" + b + "&redirect_uri=" + c + "&scope=get_user_info")
	}
}
function searchClass() {
	this.run = function (a) {
		var b = $("input[name='keywords']"),
		c = DIU.trim(b.val()),
		d = DIU.siteUrl + "?ctl=search&is_shop={$is_shop}&keyword={$keyword}&page={$page}";
		b.val(c),
		"" == c ? b.focus() : (d = d.replace("{$is_shop}", a).replace("{$keyword}", c).replace("{$page}", 1), DIU.redirect(d, 0))
	}
}
function shopClass() {
	var a = this;
	this.dialog = {},
	this.pid = 0,
	this.productSn = "",
	this.productStock = 0,
	this.productsData = "",
	this.goodsMyPrice = 0,
	this.MGPricesHtml = "",
	this.specCount = 0,
	this.cartAmountSection = {},
	this.priceSection = {},
	this.marketPriceSection = {},
	this.myPriceSection = {},
	this.pointSet = 0,
	this.pointPayAmount = 0,
	this.isBook = 0,
	this.bookFrontMoneyRate = 0,
	this.bookPriceDiscount = 0,
	this.bookPriceSection = {},
	this.bookTimeSection = {},
	this.bookMoneySection = {},
	this.amountInput = {},
	this.diypicInput = {},
	this.picidsInput = {},
	this.diynumInput = {},
	this.specSection = {},
	this.stockTipSection = {},
	this.cartGoods = {},
	this.cartPriceSum = {},
	this.addToCart = function (b, c, d, e, z1, z2,z3) {
		var f,
		g,
		h,
		i,
		j = DIU.shop.addToCart.arguments,
		k = !1,
		l = DIU.cookie.getCookie("cart");
		if (b = DIU.int(b), c = DIU.int(c), d = DIU.int(d), e = DIU.int(e), b > 0 && (e = 1), DIU.isNull(l))
			h = "[]", DIU.cookie.setCookie("cart", h, 10368e5), a.addToCart(b, c, d, e, z1, z2, z3);
		else {
			for (i = {
					suit_id : "" + b,
					gid : "" + c,
					pid : "" + d,
					m : "" + e,
					z1 : "" + z1,
					z2 : "" + z2,
					z3 : "" + z3
				}, g = DIU.JSON.parse(l), f = 0; f < g.length; f++)
				if (g[f].suit_id == b && g[f].gid == c && g[f].pid == d && g[f].z1 == z1) {
					g[f].m = DIU.toString(DIU.int(g[f].m) + e),
					g[f].z1 = z1,
					g[f].z2 = z2,
					g[f].z3 = z3,
					h = DIU.JSON.toString(g),
					k = !0;
					break
				}
			k ? DIUDialog({
				id : "dialog_add_to_cart"
			}).success("商品已在购物车中！").timeout(2) : (g.push(i), h = DIU.JSON.toString(g), DIU.cookie.setCookie("cart", h, 10368e5), DIUDialog({
					id : "dialog_add_to_cart"
				}).success("商品已成功加入购物车！").timeout(2).shadow(!1), j.length > 7 && j[7].call())
		}
	},
	this.addToFavorite = function (a) {
		if (a = DIU.int(a), !DIU.logined)
			return DIU.member.loginWindow({
				success : function () {
					DIU.shop.addToFavorite(a)
				}
			}), !1;
		var b = DIUDialog().posting();
		DIU.ajax({
			url : DIU.sitePath + "diu-includes/diu.ajax.shop.asp?ctl=goods&act=add_to_favorite",
			data : "gid=" + a,
			success : function () {
				b.success("成功添加至收藏夹！").timeout(2)
			},
			failed : function (a) {
				b.error("亲，很抱歉，添加至收藏夹失败，请查看以下原因！", a).timeout(4)
			}
		})
	},
	this.author = function () {
		var a = ">d>i>u>8>d>i>u>";
		return DIU.rep(a, ">", "")
	},
	this.cartInit = function (b) {
		var c,
		d,
		e = b.cartAmountSection || a.cartAmountSection,
		f = DIU.cookie.getCookie("cart");
		a.cartAmountSection = e,
		DIU.isNull(f) ? c = 0 : (d = DIU.JSON.parse(f), c = DIU.int(d.length)),
		e.html(c)
	},
	this.cartPageInit = function (b) {
		var c,
		d,
		e,
		f,
		g = b.cart,
		h = b.btn.addClass("disabled").attr("disabled", !0),
		i = DIUDialog().loading().position();
		DIU.ajax({
			url : DIU.sitePath + "diu-includes/diu.ajax.shop.asp?ctl=goods&act=get_cart_goods",
			success : function () {
				return i.close(),
				f = DIU.ajaxData.cart_json,
				e = DIU.ajaxData.cart_html,
				DIU.isNull(f) ? (DIU.cookie.remove("cart"), !1) : (g.html(unescape(e)), d = DIU.JSON.toString(f), c = g.find("input[name='check_all']"), a.cartGoods = g.find("div[name='cart_goods_list']"), a.cartPriceSum = b.cartPriceSum, a.cartPriceCount(), DIU.cookie.setCookie("cart", d, 10368e5), c.click(function () {
						var b = a.cartGoods.find("ul[name='item']");
						$checkbox = a.cartGoods.find("input[name='items[]']"),
						1 == $(this).attr("checked") ? (b.addClass("current"), $checkbox.attr("checked", !0)) : (b.removeClass("current"), $checkbox.attr("checked", !1)),
						a.cartPriceCount()
					}), a.cartGoods.find("input[name='items[]']").click(function () {
						var b = $(this).parent().parent().parent().parent();
						1 == $(this).attr("checked") ? b.addClass("current") : b.removeClass("current"),
						a.cartPriceCount()
					}), a.cartGoods.find("input[name='amount_input']").blur(function () {
						var b = DIU.int($(this).val()),
						c = DIU.int($(this).attr("suit_id"));
						1 > b && (b = 1),
						$(this).val(b),
						a.cartPriceCount(),
						a.gartGoodsAmountChange($(this).attr("gid"), $(this).attr("pid"), b),
						c > 0 && a.cartGoods.find("[name='suit_amount'][suit_id='" + c + "']").html(b)
					}), a.cartGoods.find("a[name='amount_decrease']").click(function () {
						var b = $(this).parent().find("input[name='amount_input']"),
						c = DIU.int(b.attr("suit_id")),
						d = DIU.int(b.val()) - 1;
						1 > d && (d = 1),
						b.val(d),
						a.cartPriceCount(),
						a.gartGoodsAmountChange(b.attr("gid"), b.attr("pid"), d),
						c > 0 && a.cartGoods.find("[name='suit_amount'][suit_id='" + c + "']").html(d)
					}), a.cartGoods.find("a[name='amount_increase']").click(function () {
						var b = $(this).parent().find("input[name='amount_input']"),
						c = DIU.int(b.attr("suit_id")),
						d = DIU.int(b.val()) + 1;
						b.val(d),
						a.cartPriceCount(),
						a.gartGoodsAmountChange(b.attr("gid"), b.attr("pid"), d),
						c > 0 && a.cartGoods.find("[name='suit_amount'][suit_id='" + c + "']").html(d)
					}), DIU.int(a.cartGoods.find("input[name='items[]']").length) > 0 && h.removeClass("disabled").attr("disabled", !1), void 0)
			},
			failed : function (a) {
				i.error("亲，很抱歉，加载购物车数据失败，请刷新页面再试试！", a).timeout(4)
			}
		}),
		h.click(function () {
			var b,
			c,
			d,
			e,
			z1,
			z2,
			z3,
			f;
			return DIU.logined || 1 == DIU.int(DIU.cookie.getCookie("unlogin_buy")) ? DIU.int(a.cartGoods.find("input[name='items[]']:checked").length) < 1 ? (DIUDialog().alert("请至少选中一件商品！"), !1) : (a.cartGoods.find("input[name='items[]']:checked").each(function () {
					suitId = DIU.int($(this).attr("suit_id")),
					b = DIU.int($(this).attr("gid")),
					c = DIU.int($(this).attr("pid")),
					z1 = $(this).attr("z1"),
					z2 = $(this).attr("z2"),
					z3 = $(this).attr("z3"),
					d = DIU.int($(this).parent().parent().parent().find("input[name='amount_input']").val()),
					f = '{"suit_id":"' + suitId + '","gid":"' + b + '","pid":"' + c + '","m":"' + d + '","z1":"' + z1 + '","z2":"' + z2 + '","z3":"' + z3 + '"}',
					e = DIU.isNull(e) ? f : e + "," + f
				}), e = "[" + e + "]", a.cartOrderSubmit({
					data : e
				}), void 0) : (DIU.member.loginWindow({
					success : function () {
						DIU.refresh()
					}
				}), !1)
		})
	},
	this.cartPriceCount = function () {
		var b,
		c,
		d,
		e = 0,
		f = a.cartGoods.find("input[name='items[]']:checked").parent().parent().parent().find("input[name='amount_input']");
		f.each(function () {
			b = DIU.int($(this).val()),
			c = DIU.parseFloat($(this).attr("price")),
			d = DIU.parseFloat(c * b),
			e += d,
			$(this).parent().parent().parent().parent().find("[name='item_sum']").html(DIU.parsePrice(d))
		}),
		a.cartPriceSum.html(DIU.parsePrice(e))
	},
	this.cartOrderSubmit = function (a) {
		var b,
		c,
		d = a.data,
		e = DIU.int(a.is_book),
		f = DIU.int(a.order_type),
		g = '<form name="order_data_form" method="post"><input type="hidden" name="order_type" value="' + f + '" /><input type="hidden" name="is_book" value="' + e + '" /><input type="hidden" name="order_goods_data" value="" /></form>';
		DIU.objExist($("form[name='order_data_form']")) || $("body").append(g),
		b = $("form[name='order_data_form']"),
		c = b.find("input[name='order_goods_data']").val(d),
		b.attr("action", DIU.siteUrl + "?ctl=order&act=init").submit()
	},
	this.invoiceEditWin,
	this.invoiceData,
	this.invoiceEdit = function (b) {
		var d, c = b.isInvoiceVatOpen || 0;
		DIU.appendToString = "",
		DIU.append('<div class="invoice-edit-html" id="invoice_edit_html">'),
		DIU.append('<div class="invoice-edit-header"><ul class="selected-style invoice-type"><li name="invoice_type" value="1"><span>普通发票</span></li><li name="invoice_type" value="2" '),
		0 == c && DIU.append('style="display:none;"'),
		DIU.append("><span>增值税专用发票</span></li></ul></div>"),
		DIU.append('<div class="invoice-edit-section">'),
		DIU.append('<div><dl><dt><i class="important">*</i>单位名称(发票抬头)</dt><dd><input type="text" class="text" datatype="*" errmsg="请填写发票抬头" name="invoice_title" maxlength="250" value="" /><span class="t-normal ml5" name="t_invoice_title"></span></dd></dl><dl><dt><i class="important">*</i>纳税人识别码</dt><dd><input type="text" class="text" datatype="*" errmsg="请填写纳税人识别码" name="invoice_code" maxlength="50" value="" /><span class="t-normal ml5" name="t_invoice_code"></span></dd></dl><dl><dt><i class="important">*</i>发票内容</dt><dd><ul class="selected-style invoice-content-type"><li name="invoice_content_type" value="1"><span>明细</span></li><li name="invoice_content_type" value="2" style="display:none"><span>自填</span></li></ul></dd><dd name="invoice_conent_type" style="display:none;"><input type="text" class="text" name="invoice_content" maxlength="250" value="" /><span class="t-normal ml5" name="t_invoice_content"></span></dd></dl></div>'),
		DIU.append('<div><dl><dt><i class="important">*</i>注册地址</dt><dd><input type="text" class="text" datatype="*" errmsg="请填写注册地址" name="invoice_address" maxlength="250" value="" /><span class="t-normal ml5" name="t_invoice_address"></span></dd></dl><dl><dt><i class="important">*</i>注册电话</dt><dd><input type="text" class="text" datatype="*" errmsg="请填写注册电话" name="invoice_phone" maxlength="100" value="" /><span class="t-normal ml5" name="t_invoice_phone"></span></dd></dl><dl><dt><i class="important">*</i>开户银行</dt><dd><input type="text" class="text" datatype="*" errmsg="请填写开户银行" name="invoice_bank_name" maxlength="100" value="" /><span class="t-normal ml5" name="t_invoice_bank_name"></span></dd></dl><dl><dt><i class="important">*</i>银行账户</dt><dd><input type="text" class="text" datatype="*" errmsg="请填写银行账号" name="invoice_bank_account" maxlength="30" value="" /><span class="t-normal ml5" name="t_invoice_bank_account"></span></dd></dl></div></div>'),
		DIU.append('<div class="invoice-edit-footer"><button type="button" class="btn btn-primary" name="btn_submit">确定</button></div>'),
		DIU.append("</div>"),
		d = DIU.appendToString,
		a.invoiceEditWin = DIUDialog({
				id : "d_login_window",
				title : "发票信息",
				content : d,
				padding : "0px",
				initialize : function () {
					var e,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,
					b = $("#order_invoice"),
					c = $("#invoice_edit_html"),
					d = b.find("input[name='invoice']"),
					f = d.val();
					DIU.isNull(f) && (f = '{"invoice_type":"1"}'),
					e = DIU.JSON.parse(f),
					g = e.invoice_type || 1,
					h = e.invoice_title || "",
					i = e.invoice_content_type || 1,
					j = e.invoice_content || "",
					k = e.invoice_code || "",
					l = e.invoice_address || "",
					m = e.invoice_phone || "",
					n = e.invoice_bank_name || "",
					o = e.invoice_bank_account || "",
					p = $("input[name='invoice_title']").val(unescape(h)),
					q = $("input[name='invoice_content']").val(unescape(j)),
					r = $("span[name='t_invoice_content']"),
					s = $("input[name='invoice_code']").val(unescape(k)),
					t = $("input[name='invoice_address']").val(unescape(l)),
					u = $("input[name='invoice_phone']").val(unescape(m)),
					v = $("input[name='invoice_bank_name']").val(unescape(n)),
					w = $("input[name='invoice_bank_account']").val(unescape(o)),
					x = c.find("button[name='btn_submit']"),
					c.find("li[name='invoice_type'][value='" + g + "']").addClass("current"),
					c.find("li[name='invoice_content_type'][value='" + i + "']").addClass("current"),
					2 == i && c.find("dd[name='invoice_conent_type']").show(),
					c.find("li[name='invoice_type']").click(function () {
						var b = $(this).attr("value");
						$(this).addClass("current"),
						$(this).siblings("li[name='invoice_type'][value!='" + b + "']").removeClass("current"),
						2 == b ? (a.taxrate = a.needInvoiceInput.attr("invoice_vat_taxrate")) : (a.taxrate = a.needInvoiceInput.attr("taxrate")),
						a.invoiceEditWin.position(),
						$("#taxrate_show").html("税率" + a.taxrate + "%"),
						a.costInvoiceCount(),
						a.totalAmountCount()
					}),
					c.find("li[name='invoice_content_type']").click(function () {
						var b = $(this).attr("value");
						$(this).addClass("current"),
						$(this).siblings("li[name='invoice_content_type'][value!='" + b + "']").removeClass("current"),
						2 == b ? c.find("dd[name='invoice_conent_type']").show() : c.find("dd[name='invoice_conent_type']").hide(),
						a.invoiceEditWin.position()
					}),
					x.click(function () {
						var h,
						i,
						j,
						e = c,
						f = c.find("li[name='invoice_type'][class='current']"),
						g = c.find("li[name='invoice_content_type'][class='current']");
						"1" == f.attr("value") && e,
						h = DIUValidForm({
								form : e
							}),
						h.verify(),
						h.result && 2 == c.find("li[name='invoice_content_type'][class='current']").attr("value") && ("" == DIU.trim(q.val()) ? (q.focus(), r.addClass("t-err").html("请填写发票内容"), h.result = !1) : r.removeClass("t-err").html("")),
						h.result && (i = '{"invoice_type":"' + escape(f.attr("value")) + '","invoice_title":"' + escape(p.val()) + '","invoice_content_type":"' + escape(g.attr("value")) + '","invoice_content":"' + escape(q.val()) + '","invoice_code":"' + escape(s.val()) + '","invoice_address":"' + escape(t.val()) + '","invoice_phone":"' + escape(u.val()) + '","invoice_bank_name":"' + escape(v.val()) + '","invoice_bank_account":"' + escape(w.val()) + '"}', j = DIUDialog().posting().position(), DIU.ajax({
								url : DIU.sitePath + "diu-includes/diu.ajax.shop.asp?ctl=invoice&act=edit",
								data : "invoice_data=" + i,
								success : function () {
									d.val(i),
									a.invoiceEditWin.close(),
									b.find("[name='invoice_type']").html(f.find("span").html()),
									b.find("[name='invoice_title']").html(p.val() + "<em>" + s.val() + "</em>"),
									2 == g.attr("value") ? b.find("[name='invoice_content']").html(q.val()) : b.find("[name='invoice_content']").html("明细"),
									j.success("修改成功").position().timeout(2)
								},
								failed : function (c) {
									d.val(i),
									a.invoiceEditWin.close(),
									b.find("[name='invoice_type']").html(f.find("span").html()),
									b.find("[name='invoice_title']").html(p.val() + "<em>" + s.val() + "</em>"),
									2 == g.attr("value") ? b.find("[name='invoice_content']").html(q.val()) : b.find("[name='invoice_content']").html("明细"),
									DIU.logined ? j.error("亲，很抱歉，修改失败，请查看以下信息！", c).position().timeout(4) : j.close()
								}
							}))
					})
				}
			}).position()
	},
	this.taxrate = 0,
	this.costItem = 0,
	this.costInvoice = 0,
	this.costCoupon = 0,
	this.costFreight = 0,
	this.freeFreightAmount = 0,
	this.totalAmount = 0,
	this.totalAmountSection = {},
	this.needInvoiceInput = {},
	this.costFreightSection = {},
	this.costInvoiceSection = {},
	this.totalAmountCount = function () {
		a.costItem < a.costCoupon && (a.costCoupon = a.costItem),
		a.totalAmount = DIU.parseMoney(a.costItem) - DIU.parseMoney(a.costCoupon) + DIU.parseMoney(a.costInvoice) + DIU.parseMoney(a.costFreight),
		a.totalAmount < 0 && (a.totalAmount = 0),
		a.totalAmountSection.html(DIU.parsePrice(a.totalAmount))
	},
	this.costInvoiceCount = function () {
		a.costItem < a.costCoupon && (a.costCoupon = a.costItem),
		1 == a.needInvoiceInput.attr("checked") && (a.costInvoice = DIU.parseMoney((DIU.parseMoney(a.costItem) - DIU.parseMoney(a.costCoupon) + DIU.parseMoney(a.costFreight)) * a.taxrate / 100), a.costInvoiceSection.attr("money", a.costInvoice).html(DIU.parsePrice(a.costInvoice)))
	},
	this.orderDlyTrigger = function () {
		var b = $("#order_dly"),
		c = b.find("input[name='order_dly']");
		c.length > 0 ? (1 == a.isOffline ? b.hide() : b.show(), c.click(function () {
				a.costFreight = DIU.parseMoney($(this).attr("dly_fee")),
				a.costFreightSection.html(DIU.parsePrice(a.costFreight)),
				a.costInvoiceCount(),
				a.totalAmountCount()
			})) : (b.hide(), $(".order-dly-header").hide())
	},
	this.isOffline = !1,
	this.orderOfflineTrigger = function () {
		var b = $("#order_offline_select");
		b.click(function () {
			var b = DIUDialog({
					id : "d_offline_select",
					title : "选择自提点",
					content : '<div class="diu-order-offline-diawin" id="diu_offline">loading ... </div>',
					padding : "0px",
					initialize : function () {},
					ok : function () {
						var b = $("#diu_offline").find("input:checked"),
						c = $("input[name='order_offline_store']"),
						d = $("#order_offline_info");
						return $("#order_dly"),
						DIU.objExist(b) ? (c.val(DIU.int(b.val())), d.html(b.attr("store_name") + "（" + b.attr("store_address") + "）" + '<a class="offline-cancel" href="javascript:;" id="offline_cancel">取消</a>'), a.isOffline = !0, $("#order_dly").hide(), a.costFreight = 0, a.costFreightSection.html(DIU.parsePrice(a.costFreight)), a.costInvoiceCount(), a.totalAmountCount(), $("#offline_cancel").click(function () {
								c.val(0),
								d.html(""),
								a.isOffline = !1,
								$("#order_dly").show(),
								$("#order_dly").find("input[name='order_dly']:eq(0)").click()
							}), void 0) : (alert("请选择自提点"), !1)
					},
					okValue : "确定选择",
					cancel : !0,
					cancelValue : "关闭"
				});
			DIU.ajax({
				url : DIU.sitePath + "diu-includes/diu.ajax.shop.asp?ctl=order&act=get_offline_html",
				data : "",
				async : !1,
				success : function () {
					$("#diu_offline").html(unescape(DIU.ajaxData.html)),
					b.position(),
					$("#diu_offline").find("input").click(function () {
						1 == $(this).attr("checked") && ($(this).parent().parent().addClass("current"), $(this).parent().parent().siblings().removeClass("current"))
					})
				}
			})
		})
	},
	this.formDataTrigger = function () {
		$(".diu-order-formdata-list").find("ul").hide(),
		$("a[name='select_formdata']").click(function () {
			var a = $(this),
			b = $(this).next("ul");
			DIU.shop.dialog = new DIUDialog({
					id : "d_data_select",
					title : "选择收货地址",
					content : '<div class="diu-order-formdata-list" id="diu_formdata_diawin">loading ... </div>',
					padding : "0px",
					initialize : function () {
						$("#diu_formdata_diawin").html(b.show())
					},
					beforeClose : function () {
						a.parent().append(b.hide())
					},
					cancel : !0,
					cancelValue : "关闭"
				}),
			DIU.shop.dialog.position()
		})
	},
	this.orderInit = function (b) {
		var c = b.order,
		d = b.btn,
		e = b.costItem,
		f = b.costCoupon,
		g = b.costFreight,
		h = b.costInvoiceGrid,
		i = "",
		j = $("#order_coupon").find("input[name='coupon']"),
		k = c.find("form"),
		l = $("div[name='order_dly_section']"),
		m = $("div[name='order_invoice_section']"),
		o = (DIU.iif(c.find("select[is_region='1']").length > 0, !0, !1), $("#order_region_select_grid").attr("region_data"));
		a.needInvoiceInput = $("input[name='need_invoice']"),
		a.taxrate = DIU.iif(1 == DIU.int(a.needInvoiceInput.attr("invoice_type")), a.needInvoiceInput.attr("taxrate"), a.needInvoiceInput.attr("invoice_vat_taxrate")),
		a.costItem = DIU.parseMoney(e.attr("money")),
		a.costFreight = DIU.parseMoney(g.attr("money")),
		a.costFreightSection = b.costFreight,
		a.costInvoiceSection = b.costInvoice,
		a.totalAmountSection = b.totalAmount,
		a.orderSubmitFun = b.orderSubmitFun || "",
		c.find("input[name='order_formdata_select']").click(function () {
			var b = ($(this).val(), $(this).attr("form_id")),
			c = $(this).attr("region_data"),
			d = $(this).parent(),
			e = $("form[form_id='" + b + "']");
			d.find("span").each(function () {
				var a = $(this).attr("field"),
				b = $(this).html();
				e.find("[name='" + a + "']").val(b)
			}),
			$("#order_region_select_grid").html(""),
			DIU.createRegionSelect($("#order_region_select_grid"), "order_region", c),
			DIU.shop.dialog.close()
		}),
		$("#order_goods_info").find("input[name='item[goods]']").length < 1 && d.addClass("disabled").attr("disabled", !0),
		DIU.regionSelectFinished = function (b) {
			var c = "goods_data=" + DIU.parseVal($("input[name='order_goods_data']").val()) + "&path=" + b,
			d = DIUDialog().loading();
			DIU.ajax({
				url : DIU.sitePath + "diu-includes/diu.ajax.shop.asp?ctl=order&act=get_order_dly",
				data : c,
				success : function () {
					d.close(),
					l.html(unescape(DIU.ajaxData.dly_html)),
					a.freeFreightAmount = DIU.parseMoney(DIU.ajaxData.shopping_free_freight_amount),
					a.freeFreightAmount > 0 && a.costItem >= a.freeFreightAmount ? (a.costFreight = 0, $("#order_dly").find("input[name='order_dly']").attr("dly_fee", "0"), $("#order_dly").find("em[name='freight']").html("")) : 0 == a.isOffline && (a.costFreight = DIU.parseMoney(DIU.ajaxData.dly_fee)),
					g.html(DIU.parsePrice(a.costFreight)),
					a.orderDlyTrigger(),
					a.costInvoiceCount(),
					a.totalAmountCount()
				},
				failed : function (a) {
					d.error("亲，很抱歉，获取运费失败，请重新试试！", a).position().timeout(4)
				}
			})
		},
		DIU.objExist($("#order_region_select_grid")) && DIU.createRegionSelect($("#order_region_select_grid"), "order_region", o),
		a.formDataTrigger(),
		a.orderDlyTrigger(),
		a.orderOfflineTrigger(),
		a.needInvoiceInput.attr("checked", !1),
		a.needInvoiceInput.click(function () {
			costCoupon = parseFloat(f.attr("money")),
			1 == $(this).attr("checked") ? (a.costInvoiceCount(), m.show(), h.show(50)) : (a.costInvoice = 0, m.hide(), h.hide(50), a.costInvoiceSection.attr("money", 0).html(0)),
			a.totalAmountCount()
		}),
		$("#order_coupon").find("li[name='coupon']").click(function () {
			var b = DIU.int($(this).attr("is_can_mix_use")),
			c = $(this).attr("selected");
			1 == c ? $(this).removeClass("current").attr("selected", 0) : (0 == b && $(this).siblings("li[is_can_mix_use='0']").removeClass("current").attr("selected", 0), $(this).addClass("current").attr("selected", 1)),
			i = "",
			a.costCoupon = 0,
			$("#order_coupon").find("li[name='coupon'][selected=1]").each(function () {
				i = DIU.isNull(i) ? $(this).attr("coupon_code") : i + "," + $(this).attr("coupon_code"),
				a.costCoupon = a.costCoupon + DIU.parseMoney($(this).attr("coupon_money"))
			}),
			j.val(i),
			a.costInvoiceCount(),
			a.totalAmountCount(),
			f.attr("money", a.costCoupon).html(DIU.parsePrice(a.costCoupon))
		}),
		d.click(function () {
			var b,
			d,
			e,
			f,
			g,
			h,
			i,
			j,
			l,
			m,
			n,
			o,
			p,
			q,
			r,
			t,
			u,
			v,
			w = '"region_data":""',
			x = !0,
			y = !0,
			z = c.find("select[is_region='1']"),
			A = $("span[name='t_order_region']");
			z.length > 0 && (A.removeClass("t-err").html(""), z.each(function () {
					!DIU.int($(this).val()) > 0 && (x = !1, $(this).focus(), A.addClass("t-err").html("请先选择配送地区！")),
					w = '"region_data":"' + $(this).find("option:selected").attr("path") + '"'
				})),
			x ? k.each(function () {
				t = DIUValidForm({
						form : $(this)
					}),
				t.verify(),
				t.getFormData(),
				t.result || (y = !1),
				e = DIU.isNull(e) ? t.formJsonData : e + "," + t.formJsonData
			}) : y = !1,
			"function" == typeof a.orderSubmitFun && y && (y = a.orderSubmitFun.call(this)),
			y && (v = (new DIUDialog).posting("亲，您的的订单正在提交，请稍后...").position(), u = c.find("input[name='item[goods]']"), u.each(function () {
					q = '{"suit_id":"' + DIU.int($(this).attr("suit_id")) + '","gid":"' + $(this).attr("gid") + '","pid":"' + $(this).attr("pid") + '","m":"' + $(this).attr("amount") + '","z1":"' + $(this).attr("z1") + '","z2":"' + $(this).attr("z2") + '","z3":"' + $(this).attr("z3") + '"}',
					r = DIU.isNull(r) ? q : r + "," + q
				}), e = DIU.isNull(e) ? "" : "," + e, h = DIU.trim($("#order_invoice").find("input[name='invoice']").val()), g = 1 == $("#order_invoice").find("input[name='need_invoice']").attr("checked") ? 1 : 0, j = '"offline_store_id":"' + DIU.iif(a.isOffline, DIU.int($("input[name='order_offline_store']").val()), "0") + '"', l = '"order_dly_id":"' + DIU.int($("#order_dly").find("input[name='order_dly']:checked").val()) + '"', h = DIU.isNull(h) ? "{}" : h, h = '"is_need_invoice":"' + g + '","invoice":' + h, i = DIU.trim($("#order_coupon").find("input[name='coupon']").val()), i = '"coupon":"' + i + '"', m = '"remark":"' + escape(DIU.trim(c.find("input[name='remark']").val())) + '"', n = DIU.trim(c.find("input[name='aux_data']").val()), n = '"aux_data":"' + n + '"', f = DIU.int(c.find("input[name='is_book']").val()), o = '{"is_book":"' + f + '","order_type":"0","goods":[' + r + "]" + e + "," + w + "," + j + "," + l + "," + h + "," + i + "," + m + "," + n + "}", d = "order_data=" + escape(o), b = "payment", DIU.ajax({
					url : DIU.sitePath + "diu-includes/diu.ajax.shop.asp?ctl=order&act=order",
					data : d,
					success : function () {
						p = DIU.ajaxData.order_id,
						DIU.redirect(DIU.siteUrl + "?ctl=order&act=" + b + "&order_id=" + p),
						DIU.cookie.remove("cart"),
						v.timeout(2)
					},
					failed : function (msg) {
						v.error("亲，很抱歉，订单提交失败，请查看以下信息！", msg).position().timeout(4)
					}
				}))
		})
	},
	this.orderPayment = function (a) {
		var b = a.orderId,
		d = (a.isBook, parseFloat(a.costPay)),
		e = parseFloat(a.totalAmount),
		f = parseFloat(a.bookFinalMoney),
		g = a.$payment,
		h = a.$costPay,
		i = a.$totalAmount,
		j = a.$btn,
		k = a.$bookFinalMoney;
		g.find("input[name='payment']").click(function () {
			var a = parseFloat($(this).attr("pay_fee")),
			b = e - d + a;
			h.html(DIU.parsePrice(a)),
			i.html(DIU.parsePrice(b)),
			k.html(DIU.parsePrice(f - d + a))
		}),
		g.find("dd[name='pay_bank_list'] li").hover(function () {
			$(this).find(".bank-name").show()
		}, function () {
			$(this).find(".bank-name").hide()
		}),
		g.find("dd[name='pay_bank_list'] input:radio").click(function () {
			var c,
			f,
			a = $(this).parent().parent().parent().parent().parent(),
			b = a.find("input[name='payment']");
			b.attr("checked", !0),
			a.find("li>label").removeClass("current"),
			$(this).parent().addClass("current"),
			c = parseFloat(b.attr("pay_fee")),
			f = e - d + c,
			h.html(DIU.parsePrice(c)),
			i.html(DIU.parsePrice(f))
		}),
		g.find("a[name='deposit_charge']").click(function () {
			DIUDialog({
				id : DIU.createDialogID(),
				close : !1,
				content : "是否已完成充值？",
				ok : function () {
					DIU.refresh()
				},
				okValue : "充值完毕",
				cancel : !0,
				cancelValue : "未充值"
			})
		}),
		j.click(function () {
			DIU.setDisabled(j, !0);
			var a,
			c,
			d,
			e;
			return $input = g.find("input[name='payment']:checked"),
			$dl = $input.parent().parent(),
			$dialog = DIUDialog().posting(),
			DIU.objExist($input) ? (c = DIU.int($input.val()), d = DIU.parseBankCode($input.attr("pay_code")), "alipay" == d && DIU.objExist($dl.find("dd[name='pay_bank_list']")) && (e = DIU.parseBankCode($dl.find("input[name='alipay_bank']:checked").val()), DIU.isNull(e)) ? ($dialog.error("请选择银行！").position().timeout(2), DIU.setDisabled(j, !1), !1) : (a = "order_id=" + b + "&pay_id=" + c + "&bank_code=" + e, DIU.ajax({
						url : DIU.sitePath + "diu-includes/diu.ajax.shop.asp?ctl=order&act=payment",
						data : a,
						success : function () {
							var a = DIU.ajaxData.is_need_pay_online;
							a ? DIU.redirect(DIU.siteUrl + "diu-includes/diu.order_pay.asp?order_id=" + b) : DIU.redirect(DIU.siteUrl + "?ctl=order&act=finish&order_id=" + b),
							$dialog.close()
						},
						failed : function (a) {
							$dialog.error("亲，很抱歉，支付方式提交失败，请查看以下信息！", a).position().timeout(4),
							DIU.setDisabled(j, !1)
						}
					}), void 0)) : ($dialog.error("请选择支付方式！").position().timeout(2), DIU.setDisabled(j, !1), !1)
		})
	},
	this.buyRecordInit = function (b) {
		a.recordTpl = b.tpl,
		a.recordCountSection = b.countSection,
		a.recordListSection = b.listSection,
		a.recordPagerSection = b.pagerSection,
		a.recordPageSize = b.pageSize ? b.pageSize : 20,
		a.recordPageTpl = b.pageTpl ? b.pageTpl : "",
		b.initGet === !1 ? !1 : !0,
		DIU.isStaticHtml() && DIU.shop.getBuyRecord(1)
	},
	this.commentInit = function (b) {
		a.cmtTpl = b.tpl,
		a.cmtCountSection = b.countSection,
		a.cmtListSection = b.listSection,
		a.cmtPagerSection = b.pagerSection,
		a.cmtPageSize = b.pageSize ? b.pageSize : 20,
		a.cmtPageTpl = b.pageTpl ? b.pageTpl : "",
		b.initGet === !1 ? !1 : !0,
		DIU.isStaticHtml() && DIU.shop.getComment(1)
	},
	this.consultationInit = function (b) {
		a.cstTpl = b.tpl,
		a.cstCountSection = b.countSection,
		a.cstListSection = b.listSection,
		a.cstPagerSection = b.pagerSection,
		a.cstPageSize = b.pageSize ? b.pageSize : 20,
		a.cstPageTpl = b.pageTpl ? b.pageTpl : "",
		b.initGet === !1 ? !1 : !0,
		$form = b.form,
		$vc = b.verifycode,
		$vv = b.vcodeValue,
		$submit = b.submit,
		$author = $form.find("input[name='consult_author']").val(DIU.nickname),
		$authorT = $form.find("span[name='t_consult_author']"),
		$content = $form.find("textarea[name='consult_content']"),
		$contentT = $form.find("span[name='t_consult_content']"),
		$email = $form.find("input[name='consult_email']"),
		$emailT = $form.find("span[name='t_consult_email']"),
		$isEmail = $form.find("input[name='is_answer_to_email']"),
		DIU.logined && ($author.val(DIU.cookie.getCookie("username")), $author.parent().parent().hide()),
		$content.blur(function () {
			$(this).val(DIU.left(DIU.trim($(this).val()), 250))
		}),
		$isEmail.click(function () {
			1 == $(this).attr("checked") ? $email.parent().parent("dl").show(200) : $email.parent().parent("dl").hide(200)
		}),
		DIU.verifyCodeValueBlur($vv),
		DIU.verifyCodeValueFocus($vv, $vc),
		DIU.enterClick(function () {
			$submit.click()
		}),
		DIU.isStaticHtml() && DIU.shop.getConsultation(1),
		$submit.click(function () {
			var h,
			i,
			a = !0,
			b = DIU.trim($author.val()),
			c = DIU.trim($content.val()),
			d = DIU.int($form.find("input[name='consult_type']:checked").val()),
			e = DIU.trim($email.val()),
			f = DIU.trim($vc.find("input[name='verifycode_name']").val()),
			g = DIU.trim($vv.val());
			DIU.isNull(c) ? (a = !1, $content.addClass("textarea-err").focus(), $contentT.addClass("t-err").html("请填写咨询内容")) : ($content.removeClass("textarea-err"), $contentT.removeClass("t-err").html("")),
			a && (DIU.isNull(b) ? (a = !1, $author.addClass("text-err").focus(), $authorT.addClass("t-err").html("请填写您的称呼")) : ($author.removeClass("text-err"), $authorT.removeClass("t-err").html(""))),
			a && 1 == $isEmail.attr("checked") && (DIU.isNull(e) ? (a = !1, $email.addClass("text-err").focus(), $emailT.addClass("t-err").html("请填写我的邮箱")) : DIU.isEmail(e) ? ($email.removeClass("text-err"), $emailT.removeClass("t-err").html("")) : (a = !1, $email.addClass("text-err").focus(), $emailT.addClass("t-err").html("邮箱格式不正确，请填写正确的邮箱！"))),
			a && (DIU.isNull(g) ? (a = !1, $vv.addClass("text-err").focus()) : $vv.removeClass("text-err")),
			$email.val(e),
			$content.val(c),
			$email.val(e),
			a && (h = DIU.sitePath + "diu-includes/diu.ajax.shop.asp?ctl=goods&act=consult_post", i = "cst_type=" + d + "&cst_author=" + escape(b) + "&cst_content=" + escape(c) + "&cst_author_email=" + escape(e) + "&gid=" + DIU.gid + "&verifycode_name=" + f + "&verifycode_value=" + g, $dialog = DIUDialog().posting().follow($content), DIU.ajax({
					url : h,
					data : i,
					success : function () {
						$dialog.success("亲，恭喜，咨询提交成功，请等待客服回复！").timeout(2).removeClose(!0),
						DIU.delay(2200, function () {
							$content.val("").focus(),
							$vc.html(""),
							$vv.val("")
						})
					},
					failed : function (a) {
						$dialog.error("亲，很抱歉，咨询提交失败，请刷新页面再试试！", a).timeout(4),
						$vv.val("").focus(),
						DIU.verifyCode({
							boxer : $vc
						})
					}
				}))
		}),
		$("img[name='avatar'][src='']").attr("src", DIU.siteUrl + "diu-content/images/avatar.jpg")
	},
	this.copyright = function () {
		var a = ">d>i>u>8>d>i>u>";
		return DIU.rep(a, ">", "")
	},
	this.priceDisplay = function () {
		if (DIU.logined && (a.goodsMyPrice < a.goodsPrice ? (a.myPriceSection.show(), $("#goods_price").addClass("my-price-show")) : a.myPriceSection.hide()), 1 == a.pointSet)
			a.goodsMyPrice <= 0 ? (a.pointplus.hide(), DIU.logined && a.myPriceSection.hide(), a.priceDefGrid.hide()) : (a.pointplus.css("display", "inline-block"), a.priceDefGrid.show());
		else {
			var b = $("#goods_point"),
			c = b.attr("point_get_type"),
			d = b.attr("shopping_point_rate"),
			e = b.attr("point_get_amount");
			e = DIU.iif(0 == c, DIU.int(a.goodsMyPrice * d), e),
			b.find("b[name='point']").html(e)
		}
	},
	this.getGoodsInitData = function () {
		DIU.ajax({
			async : !1,
			data : "gid=" + escape(DIU.gid) + "&pid=" + escape(a.pid),
			url : DIU.sitePath + "diu-includes/diu.ajax.shop.asp?ctl=goods&act=get_goods_init_data",
			success : function () {
				if (DIU.shop.productsData = DIU.ajaxData.products_data, DIU.logined) {
					var b = DIU.ajaxData.my_price;
					a.goodsMyPrice = DIU.parseMoney(b),
					a.myPriceSection.show(),
					a.myPriceSection.find("[name='my_price']").html(DIU.parsePrice(b)),
					DIU.shop.priceDisplay()
				}
				$("#suits").html(unescape(DIU.ajaxData.suits_html))
			},
			failed : function (a) {
				DIUDialog().error("亲，很抱歉，获取商品信息失败，请刷新页面再试试！", a).timeout(2)
			}
		})
	},
	this.goodsSuitInit = function () {
		var b = $("#goods_suit"),
		c = b.find("div[name='suit_header']"),
		d = b.find("div[name='suit_section']"),
		e = b.find("[name='suit_o_amount']");
		$suitAmount = b.find("[name='suit_amount']"),
		$suitSave = b.find("[name='suit_save']"),
		$suitBuy = b.find("button[name='btn_suit_buy']"),
		$ul = d.find("ul:eq(0)"),
		suitId = c.find("li:eq(0)").attr("suit_id"),
		suitOAmount = DIU.shop.goodsMyPrice + DIU.parseMoney($ul.attr("price_o_amount")),
		suitAmount = DIU.shop.goodsMyPrice + DIU.parseMoney($ul.attr("price_amount")),
		suitSave = DIU.parseMoney(d.find("ul:eq(0)").attr("price_save")),
		$suitBuy.attr("suit_id", suitId),
		e.html(DIU.parsePrice(suitOAmount)),
		$suitAmount.html(DIU.parsePrice(suitAmount)),
		$suitSave.html(DIU.parsePrice(suitSave)),
		c.find("li:eq(0)").addClass("current"),
		d.find("ul:eq(0)").show(),
		c.find("li").click(function () {
			suitId = DIU.int($(this).attr("suit_id")),
			$ul = d.find("ul[suit_id='" + suitId + "']"),
			suitOAmount = DIU.shop.goodsMyPrice + DIU.parseMoney($ul.attr("price_o_amount")),
			suitAmount = DIU.shop.goodsMyPrice + DIU.parseMoney($ul.attr("price_amount")),
			suitSave = DIU.parseMoney($ul.attr("price_save")),
			$suitBuy.attr("suit_id", suitId),
			e.html(DIU.parsePrice(suitOAmount)),
			$suitAmount.html(DIU.parsePrice(suitAmount)),
			$suitSave.html(DIU.parsePrice(suitSave)),
			$(this).addClass("current"),
			$(this).siblings().removeClass("current"),
			$ul.show(200),
			d.find("ul[suit_id!='" + suitId + "']").hide(200)
		}),
		d.find("ul").each(function () {
			var a = $(this).find("li"),
			b = a.length * (DIU.int(a.css("width")) + DIU.int(a.css("padding-left")) + DIU.int(a.css("padding-right")));
			$(this).css("width", b)
		}),
		$suitBuy.click(function () {
			var b = DIU.int($(this).attr("is_book"));
			return suitId = $(this).attr("suit_id"),
			a.productAmount = DIU.int(a.amountInput.val()),
			DIU.logined || 1 == DIU.int(DIU.cookie.getCookie("unlogin_buy")) ? (!a.pid > 0 || a.pid == DIU.pid && a.specCount > 0 ? a.specSection.parent().addClass("spec-un-selected") : 1 == b ? a.cartOrderSubmit({
					is_book : "1",
					data : '[{"suit_id":"' + suitId + '","gid":"' + DIU.gid + '","pid":"' + a.pid + '","m":"' + a.productAmount + '","z1":"","z2":"","z3":""}]'
				}) : (a.addToCart(suitId, DIU.gid, a.pid, a.productAmount,"","",""), DIU.redirect(DIU.cartUrl, 0)), void 0) : (DIU.member.loginWindow({
					success : function () {
						DIU.refresh()
					}
				}), !1)
		})
	},
	this.goodsSuitAmountCount = function () {
		var a = $("#goods_suit"),
		b = a.find("div[name='suit_header']"),
		c = a.find("div[name='suit_section']"),
		d = a.find("[name='suit_o_amount']");
		$suitAmount = a.find("[name='suit_amount']"),
		$suitSave = a.find("[name='suit_save']"),
		$li = b.find("li.current"),
		$ul = c.find("ul[suit_id='" + $li.attr("suit_id") + "']"),
		suitOAmount = DIU.shop.goodsMyPrice + DIU.parseMoney($ul.attr("price_o_amount")),
		suitAmount = DIU.shop.goodsMyPrice + DIU.parseMoney($ul.attr("price_amount")),
		suitSave = DIU.parseMoney($ul.attr("price_save")),
		d.html(DIU.parsePrice(suitOAmount)),
		$suitAmount.html(DIU.parsePrice(suitAmount)),
		$suitSave.html(DIU.parsePrice(suitSave))
	},
	this.goodsInit = function (b) {
		//大张 console.log("zhang:"+a.productsData);
		var h,
		i,
		j,
		k,
		l,
		m,
		n,
		o,
		d = b.btnBook,
		e = b.btnBuy,
		f = b.btnBuyCart,
		g = b.btnCart;
		a.pid = DIU.pid,
		a.priceSection = b.priceSection,
		a.marketPriceSection = b.marketPriceSection,
		a.myPriceSection = b.myPriceSection,
		a.bookPriceSection = b.bookPriceSection,
		a.bookMoneySection = $("[name='book_front_money']"),
		a.bookTimeSection = b.bookTimeSection,
		a.specSection = b.specSection,
		a.amountInput = b.amountInput,
		a.diypicInput = b.diypicInput,
		a.picidsInput = b.picidsInput,
		a.diynumInput = b.diynumInput,
		a.amountDecrease = b.amountDecrease,
		a.amountIncrease = b.amountIncrease,
		a.stockTipSection = b.stockTipSection,
		a.goodsPrice = DIU.parseMoney(a.priceSection.html()),
		a.goodsMyPrice = a.goodsPrice,
		a.priceDefGrid = a.priceSection.parent().parent().parent(),
		a.isBook = DIU.int(a.bookPriceSection.attr("is_book")),
		a.pointplus = $("#pointplus"),
		DIU.shop.goodsSpecImagesChangeFun = b.specImagesFun || function () {},
		DIU.shop.goodsSpecDisplay(),
		DIU.shop.getGoodsInitData(),
		DIU.shop.goodsSuitInit(),
		DIU.shop.priceDisplay(),
		a.productStock = a.productsData[0].stock,
		$("[name='stock_amount']").html(a.productStock),
		a.productStock <= 0 && (d.attr("disabled", !0).addClass("disabled").html(DIU.stocklang), f.attr("disabled", !0).addClass("disabled").html(DIU.stocklang), e.hide(), g.hide(), $("#goods_suit").find("button[name='btn_suit_buy']").attr("disabled", !0).addClass("disabled").html(DIU.stocklang)),
		1 == a.isBook && (h = 1, i = 1, j = 0, k = 0, l = DIU.int(a.bookTimeSection.attr("book_final_pay_day")), m = DIU.int(a.bookTimeSection.attr("is_book_timelimit")), n = a.bookTimeSection.attr("book_starttime"), o = a.bookTimeSection.attr("book_endtime"), a.bookFrontMoneyRate = a.bookPriceSection.attr("book_front_money_rate"), a.bookPriceDiscount = DIU.parseMoney(a.bookPriceSection.attr("book_price_discount")), k = a.bookFrontMoneyRate, j = a.goodsMyPrice - a.bookPriceDiscount, 0 > j && (j = 0), j = j * k / 100, 1 == m && (DIU.isValidDataime(n) && (h = DIU.dateDiff("s", n, DIU.now()), 0 > h && (d.addClass("disabled").attr("disabled", "disabled").html("预订时间未到"), $("#goods_suit").find("button[name='btn_suit_buy']").addClass("disabled").attr("disabled", "disabled").html("预订时间未到"))), DIU.isValidDataime(o) && (i = DIU.dateDiff("s", DIU.now(), o), 0 > i && (d.addClass("disabled").attr("disabled", "disabled").html("已结束预订"), $("#goods_suit").find("button[name='btn_suit_buy']").addClass("disabled").attr("disabled", "disabled").html("已结束预订")))), DIU.appendToString = "", DIU.append("<ul>"), DIU.append("<li " + DIU.iif(h > 0, 'class="current"', "") + '><span class="i">1</span><dl><dt>开始预订' + DIU.iif(k > 0, '<span class="money-rate">(预付款' + k + "%)</span>", "") + "</dt><dd>" + DIU.iif(1 == m && DIU.isValidDataime(n), n, "") + '</dd></dl><div class="tips"></div></li>'), 1 == m && DIU.isValidDataime(o) && DIU.append("<li " + DIU.iif(0 > i, 'class="current"', "") + '><span class="i"></span><dl><dt>结束预订</dt><dd>' + o + '</dd></dl><div class="tips"></div></li>'), DIU.append('<li class="last"><span class="i">2</span><dl><dt>支付尾款/发货</dt><dd></dd></dl><div class="tips">' + DIU.iif(l > 0, "预计下单" + l + "天后可发货", "") + "</div></li>"), DIU.append("</ul>"), a.bookTimeSection.append(DIU.appendToString).show(), a.bookMoneySection.html(DIU.parsePrice(j)), (j > 0 || a.bookPriceDiscount > 0) && a.bookPriceSection.show()),
		a.amountInput.blur(function () {
			var b = $(this).val();
			b = DIU.int(b.replace(/[^0-9]*/g, "")),
			b > a.productStock && a.productStock > 0 && (b = a.productStock),
			1 > b && (b = 1),
			$(this).val(b)
		}),
		a.amountDecrease.click(function () {
			var b = DIU.int(a.amountInput.val()) - 1;
			1 > b && (b = 1),
			a.amountInput.val(b)
		}),
		a.amountIncrease.click(function () {
			var b = DIU.int(a.amountInput.val()) + 1;
			b > a.productStock && a.productStock > 0 && (b = a.productStock),
			a.amountInput.val(b)
		}),
		d.click(function () {
			if (!DIU.int(a.amountInput.val()) > 0)
				return a.stockTipSection.html("亲，购买数量不能小于或为0！").show(100), !1;
			if (a.stockTipSection.html("").hide(100), !DIU.logined && 1 != DIU.int(DIU.cookie.getCookie("unlogin_buy")))
				return DIU.member.loginWindow({
					success : function () {
						DIU.refresh()
					}
				}), !1;
			if (!a.pid > 0 || a.pid == DIU.pid && a.specCount > 0)
				a.specSection.parent().addClass("spec-un-selected");
			else {
				if (DIU.int(a.amountInput.val()) > a.productStock)
					return a.stockTipSection.html("亲，您所填写的购买数量超过库存了！").show(100), !1;
				a.stockTipSection.html("").hide(100),
				a.specSection.parent().removeClass("spec-un-selected"),
				a.productAmount = DIU.int(a.amountInput.val()),
				a.cartOrderSubmit({
					is_book : "1",
					data : '[{"suit_id":"0","gid":"' + DIU.gid + '","pid":"' + a.pid + '","m":"' + a.productAmount + '","z1":"","z2":"","z3":""}]'
				})
			}
		}),
		e.click(function () {
			if (!DIU.int(a.amountInput.val()) > 0)
				return a.stockTipSection.html("亲，购买数量不能小于或为0！").show(100), !1;
			if (a.stockTipSection.html("").hide(100), !DIU.logined && 1 != DIU.int(DIU.cookie.getCookie("unlogin_buy")))
				return DIU.member.loginWindow({
					success : function () {
						DIU.refresh()
					}
				}), !1;
			if (!a.pid > 0 || a.pid == DIU.pid && a.specCount > 0)
				a.specSection.parent().addClass("spec-un-selected");
			else {
				if (DIU.int(a.amountInput.val()) > a.productStock)
					return a.stockTipSection.html("亲，您所填写的购买数量超过库存了！").show(100), !1;
				a.stockTipSection.html("").hide(100),
				a.specSection.parent().removeClass("spec-un-selected"),
				a.productAmount = DIU.int(a.amountInput.val()),
				a.cartOrderSubmit({
					data : '[{"suit_id":"0","gid":"' + DIU.gid + '","pid":"' + a.pid + '","m":"' + a.productAmount + '","z1":"","z2":"","z3":""}]'
				})
			}
		}),
		f.click(function () {
			if (!DIU.int(a.amountInput.val()) > 0)
				return a.stockTipSection.html("亲，购买数量不能小于或为0！").show(100), !1;
			if (a.stockTipSection.html("").hide(100), !DIU.logined && 1 != DIU.int(DIU.cookie.getCookie("unlogin_buy")))
				return DIU.member.loginWindow({
					success : function () {
						DIU.refresh()
					}
				}), !1;
			if (!a.pid > 0 || a.pid == DIU.pid && a.specCount > 0)
				a.specSection.parent().addClass("spec-un-selected");
			else {
				if (DIU.int(a.amountInput.val()) > a.productStock)
					return a.stockTipSection.html("亲，您所填写的购买数量超过库存了！").show(100), !1;
				a.stockTipSection.html("").hide(100),
				a.specSection.parent().removeClass("spec-un-selected"),
				a.productAmount = DIU.int(a.amountInput.val()),
				a.addToCart(0, DIU.gid, a.pid, a.productAmount,"","",""),
				DIU.redirect(DIU.cartUrl, 0)
			}
		}),
		g.click(function () {
			if (!DIU.int(a.amountInput.val()) > 0)
				return a.stockTipSection.html("亲，购买数量不能小于或为0！").show(100), !1;
			if (a.stockTipSection.html("").hide(100), !DIU.logined && 1 != DIU.int(DIU.cookie.getCookie("unlogin_buy")))
				return DIU.member.loginWindow({
					success : function () {
						DIU.refresh()
					}
				}), !1;
			if (!a.pid > 0 || a.pid == DIU.pid && a.specCount > 0)
				a.specSection.parent().addClass("spec-un-selected");
			else {
				if (DIU.int(a.amountInput.val()) > a.productStock)
					return a.stockTipSection.html("亲，您所填写的购买数量超过库存了！").show(100), !1;
				a.stockTipSection.html("").hide(100),
				a.specSection.parent().removeClass("spec-un-selected"),
				a.productAmount = DIU.int(a.amountInput.val()),
				a.addToCart(0, DIU.gid, a.pid, a.productAmount,"","",""),
				a.cartInit({})
			}
		})
	},
	this.removeCartGoods = function (a, b, c) {
		var d,
		e,
		f,
		g = DIU.cookie.getCookie("cart"),
		h = new DIUDialog({
				id : DIU.createDialogID(),
				content : "确定要删除吗？",
				ok : function () {
					if (!DIU.isNull(g))
						for (e = DIU.JSON.parse(g), d = 0; d < e.length; d++)
							if (e[d].suit_id == a && e[d].gid == b && e[d].pid == c) {
								e = DIU.JSON.del(d, e),
								f = DIU.JSON.toString(e),
								DIU.cookie.setCookie("cart", f, 10368e5);
								break
							}
					return h.success("亲，商品已从购物车删除！"),
					DIU.refresh(),
					!1
				},
				cancel : !0,
				close : !1
			}).position()
	},
	this.gartGoodsAmountChange = function (a, b, c) {
		var d,
		e,
		f,
		g = DIU.cookie.getCookie("cart");
		if (!DIU.isNull(g))
			for (e = DIU.JSON.parse(g), d = 0; d < e.length; d++)
				if (e[d].gid == a && e[d].pid == b) {
					e[d].m = DIU.toString(DIU.int(c)),
					f = DIU.JSON.toString(e),
					DIU.cookie.setCookie("cart", f, 10368e5);
					break
				}
	},
	this.goodsSpecImagesChangeFun,
	this.goodsSpecDisplay = function () {
		var b,
		c,
		d,
		e,
		f,
		g,
		h,
		i,
		j,
		k,
		l,
		m = a.specSection,
		n = DIU.trim(m.attr("data")),
		o = '<dl spec_id="{$spec_id}"><dt>{$spec_name}</dt><dd>{$spec_value}</dd></dl>',
		p = "";
		if ("" == n)
			a.specSection.parent().hide();
		else {
			for (n = DIU.JSON.parse(n), a.specCount = n.length, e = 0; e < n.length; e++) {
				for (g = n[e].spec_id, h = unescape(n[e].spec_name), d = o.replace("{$spec_name}", h).replace("{$spec_id}", g), b = n[e].spec_value, c = "", f = 0; f < b.length; f++)
					i = b[f].value_id, j = unescape(b[f].value_name), k = unescape(b[f].value_image), l = b[f].value_images, c = "" != k ? c + '<li spec_id="' + g + '" value_id="' + i + '"><a href="javascript:;" spec_id="' + g + '" value_id="' + i + '" value_image="' + k + '" value_images="' + l + '" class="value-img" title="' + j + '"><img src="' + k + '" /><span>' + j + "</span></a></li>" : c + '<li spec_id="' + g + '" value_id="' + i + '"><a href="javascript:;" spec_id="' + g + '" value_id="' + i + '" value_image="' + k + '" value_images="' + l + '" title="' + j + '"><span>' + j + "</span></a></li>";
				"" != c && (c = '<ul spec_id="' + g + '">' + c + "</ul>"),
				d = d.replace("{$spec_value}", c),
				p += d
			}
			m.html(p),
			m.find("a").click(function () {
				var b,
				e,
				f,
				i,
				j,
				k,
				l,
				n,
				o,
				p;
				if (a.pid = 0, "true" == $(this).parent().attr("disabled"))
					return !1;
				if (e = !1, f = $(this).attr("spec_id"), $(this).attr("value_id"), $(this).attr("value_image"), i = $(this).attr("value_images"), j = !0, k = $("div[name='selected_spec_display']"), l = k.find("[name='selected_spec_value']"), n = DIU.shop.productsData, $(this).parent().parent().find("li").removeClass("current").attr("selected", !1), $(this).parent().addClass("current").attr("selected", !0), m.find("dl[spec_id!='" + f + "']").find("li").removeClass("disabled").attr("disabled", !1), o = a.getSelectValueId(), o.split(",").length == a.specCount) {
					for (a.productStock = 0, b = 0; b < n.length; b++)
						if (o == n[b].spec_value_id) {
							e = !0,
							a.pid = n[b].pid,
							a.productSn = n[b].product_sn,
							a.productStock = n[b].stock,
							a.priceSection.html(DIU.parsePrice(n[b].price)),
							a.myPriceSection.find("[name='my_price']").html(DIU.parsePrice(n[b].my_price)),
							a.marketPriceSection.html(DIU.parsePrice(n[b].market_price)),
							1 == a.isBook && (p = n[b].my_price - a.bookPriceDiscount, 0 > p && (p = 0), a.bookMoneySection.html(DIU.parsePrice(p * a.bookFrontMoneyRate / 100))),
							a.goodsPrice = DIU.parseMoney(n[b].price),
							a.goodsMyPrice = DIU.parseMoney(n[b].my_price),
							a.MGPricesHtml = "",
							a.specSection.parent().removeClass("spec-un-selected"),
							DIU.shop.priceDisplay(),
							$("#goods_product_sn").html(a.productSn);
							break
						}
					a.productStock > 0 ? $("[name='stock_amount']").html(a.productStock) : (j = !1, $("[name='stock_amount']").html(a.productStock), $(this).parent().removeClass("current").attr("selected", !1).addClass("disabled").attr("disabled", !0))
				}
				e ? (DIU.shop.goodsSuitAmountCount(), $("#goods_suit").find("[name='suit_goods_spec']").html(unescape(n[b].spec_value)), l.html(unescape(n[b].spec_value)), k.show(200)) : (l.html(""), k.hide(200)),
				j && (DIU.isNull(i) || (i = unescape(i), DIU.shop.goodsSpecImagesChangeFun(i)))
			})
		}
	},
	this.getSelectValueId = function () {
		var b = "",
		c = a.specSection.find("li[selected='true']");
		return c.each(function () {
			b = DIU.isNull(b) ? $(this).attr("value_id") : b + "," + $(this).attr("value_id")
		}),
		b
	},
	this.getMGPrices = function () {
		var d,
		b = DIU.sitePath + "diu-includes/diu.ajax.shop.asp?ctl=goods&act=get_mg_prices",
		c = $("div[name='member_group_prices']");
		DIU.isNull(DIU.shop.MGPricesHtml) ? (d = DIUDialog().loading("正在获取价格...").follow(c).shadow(!1), DIU.ajax({
				url : b,
				data : "gid=" + escape(DIU.gid) + "&pid=" + escape(a.pid),
				success : function () {
					var a,
					f,
					g,
					b = '<tr><td class="group-name">{$group}</td><td class="price">' + DIU.moneySb + "{$price}</td></tr>",
					c = "",
					e = "",
					h = "";
					for (f = DIU.ajaxData.mgp, g = DIU.ajaxData.msgp, (void 0 == g || "undefined" == g) && (g = []), h = '<table border="0" cellpadding="0" cellspacing="0" class="table-price"><thead><tr><th>会员</th><th>价格</th></tr></thead>', a = 0; a < f.length; a++)
						c += b.replace("{$group}", unescape(f[a].group_name)).replace("{$price}", DIU.parsePrice(f[a].price));
					for (a = 0; a < g.length; a++)
						e += b.replace("{$group}", unescape(g[a].group_name)).replace("{$price}", DIU.parsePrice(g[a].price));
					"" != c && (c = '<tbody class="mg">' + c + "</tbody>"),
					"" != e && (e = '<tbody class="msg">' + e + "</tbody>"),
					h = h + c + e + "</table>",
					d.padding("0px").content(h),
					DIU.shop.MGPricesHtml = h
				},
				failed : function (a) {
					d.error("获取会员组价格数据失败！", a).timeout(2)
				}
			})) : d = DIUDialog().content(DIU.shop.MGPricesHtml).padding("0px").follow(c).shadow(!1)
	},
	this.recordTpl = "",
	this.recordCountSection = "",
	this.recordListSection = "",
	this.recordPagerSection = "",
	this.recordPageSize = 20,
	this.recordPageTpl = "",
	this.getBuyRecord = function (b) {
		var c = a.recordCountSection,
		d = a.recordListSection,
		e = a.recordPagerSection;
		b = DIU.int(b),
		d.html('<div class="diu-data-loading">成交记录加载中...</div>'),
		DIU.ajax({
			url : DIU.sitePath + "diu-includes/diu.ajax.shop.asp?ctl=goods&act=get_buy_record",
			data : "gid=" + escape(DIU.gid) + "&page=" + b + "&tpl=" + escape(a.recordTpl) + "&pagesize=" + a.recordPageSize + "&pagetpl=" + escape(a.recordPageTpl),
			success : function () {
				var a = DIU.int(DIU.ajaxData.count),
				b = DIU.ajaxData.list,
				f = DIU.ajaxData.pager;
				c.html(a),
				d.hide().html(unescape(b)).show(200),
				e.html(unescape(f))
			},
			failed : function (a) {
				d.html("亲，很抱歉，成交记录数据加载失败，请刷新页面再试试！<br>" + a)
			}
		})
	},
	this.cmtTpl = "",
	this.cmtCountSection = "",
	this.cmtListSection = "",
	this.cmtPagerSection = "",
	this.cmtPageSize = 20,
	this.cmtPageTpl = "",
	this.getComment = function (b) {
		var c = a.cmtCountSection,
		d = a.cmtListSection,
		e = a.cmtPagerSection;
		b = DIU.int(b),
		d.html('<div class="diu-data-loading">评论加载中...</div>'),
		DIU.ajax({
			url : DIU.sitePath + "diu-includes/diu.ajax.shop.asp?ctl=goods&act=get_comment",
			data : "gid=" + escape(DIU.gid) + "&page=" + b + "&tpl=" + escape(a.cmtTpl) + "&pagesize=" + a.cmtPageSize + "&pagetpl=" + escape(a.cmtPageTpl),
			success : function () {
				var a = DIU.int(DIU.ajaxData.count),
				b = DIU.ajaxData.list,
				f = DIU.ajaxData.pager;
				c.html(a),
				d.hide().html(unescape(b)).show(200),
				e.html(unescape(f)),
				$("img[name='avatar'][src='']").attr("src", DIU.siteUrl + "diu-content/images/avatar.jpg"),
				$("[name='comment_data_reply']").each(function () {
					DIU.isNull($(this).find("[name='reply_content']").html()) && $(this).remove()
				}),
				$("[name='cmt_type']").each(function () {
					$(this).html(commentType($(this).attr("cmt_type")))
				})
			},
			failed : function (a) {
				d.html("亲，很抱歉，评论数据加载失败，请刷新页面再试试！<br>" + a)
			}
		})
	},
	this.cstTpl = "",
	this.cstCountSection = "",
	this.cstListSection = "",
	this.cstPagerSection = "",
	this.cstPageSize = 20,
	this.cstPageTpl = "",
	this.getConsultation = function (b) {
		var c = a.cstCountSection,
		d = a.cstListSection,
		e = a.cstPagerSection;
		b = DIU.int(b),
		d.html('<div class="diu-data-loading">咨询加载中...</div>'),
		DIU.ajax({
			url : DIU.sitePath + "diu-includes/diu.ajax.shop.asp?ctl=goods&act=get_consultation",
			data : "gid=" + escape(DIU.gid) + "&page=" + b + "&tpl=" + escape(a.cstTpl) + "&pagesize=" + a.cstPageSize + "&pagetpl=" + escape(a.cstPageTpl),
			success : function () {
				var a = DIU.int(DIU.ajaxData.count),
				b = DIU.ajaxData.list,
				f = DIU.ajaxData.pager;
				c.html(a),
				d.hide(50).html(unescape(b)).show(200),
				e.html(unescape(f)),
				$("img[name='avatar'][src='']").attr("src", DIU.siteUrl + "diu-content/images/avatar.jpg"),
				$("[name='consult_data_reply']").each(function () {
					DIU.isNull($(this).find("[name='reply_content']").html()) && $(this).remove()
				})
			},
			failed : function (a) {
				d.html("亲，很抱歉，咨询数据加载失败，请刷新页面再试试！<br>" + a)
			}
		})
	}
}
function couponClass() {
	this.get = function (a) {
		var b = DIUDialog().loading("正在获取优惠券...");
		DIU.ajax({
			url : DIU.sitePath + "diu-includes/diu.ajax.shop.asp?ctl=coupon&act=get_coupon",
			data : "coupon_id=" + escape(a),
			success : function () {
				b.success("成功获取优惠券")
			},
			failed : function (a) {
				b.error("获取优惠券失败", a).position()
			}
		})
	}
}
function addwFavor(){
		var sURL=document.URL; 
		var sTitle=document.title; 
		try {window.external.addFavorite(sURL, sTitle);} 
		catch(e){ 
			try{window.sidebar.addPanel(sTitle, sURL, "");} 
			catch(e){alert("加入收藏操作被浏览器拒绝，请使用Ctrl+D进行添加");} 
		}
	};
DIU.client = new Client, DIU.ad = new ADClass, DIU.cmt = new CMTClass, DIU.link = new linkClass, DIU.gallery = new galleryClass, DIU.member = new memberClass, DIU.search = new searchClass, DIU.shop = new shopClass, DIU.coupon = new couponClass;
