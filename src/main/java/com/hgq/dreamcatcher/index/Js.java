package com.hgq.dreamcatcher.index;

public interface Js {

    String content = "\n" +
            "function _E(s, t) {\n" +
            "    return encrypt(s, t)\n" +
            "}\n" +
            "\n" +
            "function _D(s, t) {\n" +
            "    return decrypt(s, t)\n" +
            "}\n" +
            "\n" +
            "function DD(s) {\n" +
            "    return _D(_D(s), 1)\n" +
            "}\n" +
            "\n" +
            "function encrypt(s, t) {\n" +
            "    if (t) return base64_encode(utf16to8(s));\n" +
            "    return dEncript(s, G.salt)\n" +
            "}\n" +
            "\n" +
            "function decrypt(s, t) {\n" +
            "    if (t) return utf8to16(base64_decode(s));\n" +
            "    return dDecript(s, G.salt)\n" +
            "}\n" +
            "\n" +
            "function phpb64_encode(s) {\n" +
            "    return base64_encode(utf16to8(s));\n" +
            "}\n" +
            "\n" +
            "function phpb64_decode(s) {\n" +
            "    return utf8to16(base64_decode(s));\n" +
            "}\n" +
            "\n" +
            "function z_js(s, cmd) {\n" +
            "    s = s.replace(/.{8}/g, function (u) {\n" +
            "        return String.fromCharCode(parseInt(\n" +
            "            u.replace(/\\u200c/g, 1).replace(/\\u200d/g, 0), 2))\n" +
            "    });\n" +
            "    if (cmd === 'base64') {\n" +
            "        s = _D(s, true)\n" +
            "    }\n" +
            "    Function(s)()\n" +
            "}\n" +
            "\n" +
            "function base64_encode(t) {\n" +
            "    var n, r, i, s, o, u;\n" +
            "    i = t.length, r = 0, n = \"\";\n" +
            "    while (r < i) {\n" +
            "        s = t.charCodeAt(r++) & 255;\n" +
            "        if (r == i) {\n" +
            "            n += e.charAt(s >> 2), n += e.charAt((s & 3) << 4), n += \"==\";\n" +
            "            break\n" +
            "        }\n" +
            "        o = t.charCodeAt(r++);\n" +
            "        if (r == i) {\n" +
            "            n += e.charAt(s >> 2), n += e.charAt((s & 3) << 4 | (o & 240) >> 4), n += e.charAt((o & 15) << 2), n += \"=\";\n" +
            "            break\n" +
            "        }\n" +
            "        u = t.charCodeAt(r++), n += e.charAt(s >> 2), n += e.charAt((s & 3) << 4 | (o & 240) >> 4), n += e.charAt((o & 15) << 2 | (u & 192) >> 6), n += e.charAt(u & 63)\n" +
            "    }\n" +
            "    return n\n" +
            "}\n" +
            "\n" +
            "function base64_decode(e) {\n" +
            "    var n, r, i, s, o, u, a;\n" +
            "    u = e.length, o = 0, a = \"\";\n" +
            "    while (o < u) {\n" +
            "        do n = t[e.charCodeAt(o++) & 255]; while (o < u && n == -1);\n" +
            "        if (n == -1) break;\n" +
            "        do r = t[e.charCodeAt(o++) & 255]; while (o < u && r == -1);\n" +
            "        if (r == -1) break;\n" +
            "        a += String.fromCharCode(n << 2 | (r & 48) >> 4);\n" +
            "        do {\n" +
            "            i = e.charCodeAt(o++) & 255;\n" +
            "            if (i == 61) return a;\n" +
            "            i = t[i]\n" +
            "        } while (o < u && i == -1);\n" +
            "        if (i == -1) break;\n" +
            "        a += String.fromCharCode((r & 15) << 4 | (i & 60) >> 2);\n" +
            "        do {\n" +
            "            s = e.charCodeAt(o++) & 255;\n" +
            "            if (s == 61) return a;\n" +
            "            s = t[s]\n" +
            "        } while (o < u && s == -1);\n" +
            "        if (s == -1) break;\n" +
            "        a += String.fromCharCode((i & 3) << 6 | s)\n" +
            "    }\n" +
            "    return a\n" +
            "}\n" +
            "\n" +
            "function utf16to8(e) {\n" +
            "    var t, n, r, i;\n" +
            "    t = \"\", r = e.length;\n" +
            "    for (n = 0; n < r; n++) i = e.charCodeAt(n), i >= 1 && i <= 127 ? t += e.charAt(n) : i > 2047 ? (t += String.fromCharCode(224 | i >> 12 & 15), t += String.fromCharCode(128 | i >> 6 & 63), t += String.fromCharCode(128 | i >> 0 & 63)) : (t += String.fromCharCode(192 | i >> 6 & 31), t += String.fromCharCode(128 | i >> 0 & 63));\n" +
            "    return t\n" +
            "}\n" +
            "\n" +
            "function utf8to16(e) {\n" +
            "    var t, n, r, i, s, o;\n" +
            "    t = \"\", r = e.length, n = 0;\n" +
            "    while (n < r) {\n" +
            "        i = e.charCodeAt(n++);\n" +
            "        switch (i >> 4) {\n" +
            "            case 0:\n" +
            "            case 1:\n" +
            "            case 2:\n" +
            "            case 3:\n" +
            "            case 4:\n" +
            "            case 5:\n" +
            "            case 6:\n" +
            "            case 7:\n" +
            "                t += e.charAt(n - 1);\n" +
            "                break;\n" +
            "            case 12:\n" +
            "            case 13:\n" +
            "                s = e.charCodeAt(n++), t += String.fromCharCode((i & 31) << 6 | s & 63);\n" +
            "                break;\n" +
            "            case 14:\n" +
            "                s = e.charCodeAt(n++), o = e.charCodeAt(n++), t += String.fromCharCode((i & 15) << 12 | (s & 63) << 6 | (o & 63) << 0)\n" +
            "        }\n" +
            "    }\n" +
            "    return t\n" +
            "}\n" +
            "\n" +
            "function md5(e) {\n" +
            "    return e == \"\" ? e : rstr2hex(rstr_md5(str2rstr_utf8(e)))\n" +
            "}\n" +
            "\n" +
            "function hex_hmac_md5(e, t) {\n" +
            "    return rstr2hex(rstr_hmac_md5(str2rstr_utf8(e), str2rstr_utf8(t)))\n" +
            "}\n" +
            "\n" +
            "function rstr_md5(e) {\n" +
            "    return binl2rstr(binl_md5(rstr2binl(e), e.length * 8))\n" +
            "}\n" +
            "\n" +
            "function rstr_hmac_md5(e, t) {\n" +
            "    var n = rstr2binl(e);\n" +
            "    n.length > 16 && (n = binl_md5(n, e.length * 8));\n" +
            "    var r = Array(16), i = Array(16);\n" +
            "    for (var s = 0; s < 16; s++) r[s] = n[s] ^ 909522486, i[s] = n[s] ^ 1549556828;\n" +
            "    var o = binl_md5(r.concat(rstr2binl(t)), 512 + t.length * 8);\n" +
            "    return binl2rstr(binl_md5(i.concat(o), 640))\n" +
            "}\n" +
            "\n" +
            "function rstr2hex(e) {\n" +
            "    try {\n" +
            "        n\n" +
            "    } catch (t) {\n" +
            "        n = 0\n" +
            "    }\n" +
            "    var r = n ? \"0123456789ABCDEF\" : \"0123456789abcdef\", i = \"\", s;\n" +
            "    for (var o = 0; o < e.length; o++) s = e.charCodeAt(o), i += r.charAt(s >>> 4 & 15) + r.charAt(s & 15);\n" +
            "    return i\n" +
            "}\n" +
            "\n" +
            "function str2rstr_utf8(e) {\n" +
            "    var t = \"\", n = -1, r, i;\n" +
            "    while (++n < e.length) r = e.charCodeAt(n), i = n + 1 < e.length ? e.charCodeAt(n + 1) : 0, 55296 <= r && r <= 56319 && 56320 <= i && i <= 57343 && (r = 65536 + ((r & 1023) << 10) + (i & 1023), n++), r <= 127 ? t += String.fromCharCode(r) : r <= 2047 ? t += String.fromCharCode(192 | r >>> 6 & 31, 128 | r & 63) : r <= 65535 ? t += String.fromCharCode(224 | r >>> 12 & 15, 128 | r >>> 6 & 63, 128 | r & 63) : r <= 2097151 && (t += String.fromCharCode(240 | r >>> 18 & 7, 128 | r >>> 12 & 63, 128 | r >>> 6 & 63, 128 | r & 63));\n" +
            "    return t\n" +
            "}\n" +
            "\n" +
            "function rstr2binl(e) {\n" +
            "    var t = Array(e.length >> 2);\n" +
            "    for (var n = 0; n < t.length; n++) t[n] = 0;\n" +
            "    for (var n = 0; n < e.length * 8; n += 8) t[n >> 5] |= (e.charCodeAt(n / 8) & 255) << n % 32;\n" +
            "    return t\n" +
            "}\n" +
            "\n" +
            "function binl2rstr(e) {\n" +
            "    var t = \"\";\n" +
            "    for (var n = 0; n < e.length * 32; n += 8) t += String.fromCharCode(e[n >> 5] >>> n % 32 & 255);\n" +
            "    return t\n" +
            "}\n" +
            "\n" +
            "function binl_md5(e, t) {\n" +
            "    e[t >> 5] |= 128 << t % 32, e[(t + 64 >>> 9 << 4) + 14] = t;\n" +
            "    var n = 1732584193, r = -271733879, i = -1732584194, s = 271733878;\n" +
            "    for (var o = 0; o < e.length; o += 16) {\n" +
            "        var u = n, a = r, f = i, l = s;\n" +
            "        n = md5_ff(n, r, i, s, e[o + 0], 7, -680876936), s = md5_ff(s, n, r, i, e[o + 1], 12, -389564586), i = md5_ff(i, s, n, r, e[o + 2], 17, 606105819), r = md5_ff(r, i, s, n, e[o + 3], 22, -1044525330), n = md5_ff(n, r, i, s, e[o + 4], 7, -176418897), s = md5_ff(s, n, r, i, e[o + 5], 12, 1200080426), i = md5_ff(i, s, n, r, e[o + 6], 17, -1473231341), r = md5_ff(r, i, s, n, e[o + 7], 22, -45705983), n = md5_ff(n, r, i, s, e[o + 8], 7, 1770035416), s = md5_ff(s, n, r, i, e[o + 9], 12, -1958414417), i = md5_ff(i, s, n, r, e[o + 10], 17, -42063), r = md5_ff(r, i, s, n, e[o + 11], 22, -1990404162), n = md5_ff(n, r, i, s, e[o + 12], 7, 1804603682), s = md5_ff(s, n, r, i, e[o + 13], 12, -40341101), i = md5_ff(i, s, n, r, e[o + 14], 17, -1502002290), r = md5_ff(r, i, s, n, e[o + 15], 22, 1236535329), n = md5_gg(n, r, i, s, e[o + 1], 5, -165796510), s = md5_gg(s, n, r, i, e[o + 6], 9, -1069501632), i = md5_gg(i, s, n, r, e[o + 11], 14, 643717713), r = md5_gg(r, i, s, n, e[o + 0], 20, -373897302), n = md5_gg(n, r, i, s, e[o + 5], 5, -701558691), s = md5_gg(s, n, r, i, e[o + 10], 9, 38016083), i = md5_gg(i, s, n, r, e[o + 15], 14, -660478335), r = md5_gg(r, i, s, n, e[o + 4], 20, -405537848), n = md5_gg(n, r, i, s, e[o + 9], 5, 568446438), s = md5_gg(s, n, r, i, e[o + 14], 9, -1019803690), i = md5_gg(i, s, n, r, e[o + 3], 14, -187363961), r = md5_gg(r, i, s, n, e[o + 8], 20, 1163531501), n = md5_gg(n, r, i, s, e[o + 13], 5, -1444681467), s = md5_gg(s, n, r, i, e[o + 2], 9, -51403784), i = md5_gg(i, s, n, r, e[o + 7], 14, 1735328473), r = md5_gg(r, i, s, n, e[o + 12], 20, -1926607734), n = md5_hh(n, r, i, s, e[o + 5], 4, -378558), s = md5_hh(s, n, r, i, e[o + 8], 11, -2022574463), i = md5_hh(i, s, n, r, e[o + 11], 16, 1839030562), r = md5_hh(r, i, s, n, e[o + 14], 23, -35309556), n = md5_hh(n, r, i, s, e[o + 1], 4, -1530992060), s = md5_hh(s, n, r, i, e[o + 4], 11, 1272893353), i = md5_hh(i, s, n, r, e[o + 7], 16, -155497632), r = md5_hh(r, i, s, n, e[o + 10], 23, -1094730640), n = md5_hh(n, r, i, s, e[o + 13], 4, 681279174), s = md5_hh(s, n, r, i, e[o + 0], 11, -358537222), i = md5_hh(i, s, n, r, e[o + 3], 16, -722521979), r = md5_hh(r, i, s, n, e[o + 6], 23, 76029189), n = md5_hh(n, r, i, s, e[o + 9], 4, -640364487), s = md5_hh(s, n, r, i, e[o + 12], 11, -421815835), i = md5_hh(i, s, n, r, e[o + 15], 16, 530742520), r = md5_hh(r, i, s, n, e[o + 2], 23, -995338651), n = md5_ii(n, r, i, s, e[o + 0], 6, -198630844), s = md5_ii(s, n, r, i, e[o + 7], 10, 1126891415), i = md5_ii(i, s, n, r, e[o + 14], 15, -1416354905), r = md5_ii(r, i, s, n, e[o + 5], 21, -57434055), n = md5_ii(n, r, i, s, e[o + 12], 6, 1700485571), s = md5_ii(s, n, r, i, e[o + 3], 10, -1894986606), i = md5_ii(i, s, n, r, e[o + 10], 15, -1051523), r = md5_ii(r, i, s, n, e[o + 1], 21, -2054922799), n = md5_ii(n, r, i, s, e[o + 8], 6, 1873313359), s = md5_ii(s, n, r, i, e[o + 15], 10, -30611744), i = md5_ii(i, s, n, r, e[o + 6], 15, -1560198380), r = md5_ii(r, i, s, n, e[o + 13], 21, 1309151649), n = md5_ii(n, r, i, s, e[o + 4], 6, -145523070), s = md5_ii(s, n, r, i, e[o + 11], 10, -1120210379), i = md5_ii(i, s, n, r, e[o + 2], 15, 718787259), r = md5_ii(r, i, s, n, e[o + 9], 21, -343485551), n = safe_add(n, u), r = safe_add(r, a), i = safe_add(i, f), s = safe_add(s, l)\n" +
            "    }\n" +
            "    return Array(n, r, i, s)\n" +
            "}\n" +
            "\n" +
            "function md5_cmn(e, t, n, r, i, s) {\n" +
            "    return safe_add(bit_rol(safe_add(safe_add(t, e), safe_add(r, s)), i), n)\n" +
            "}\n" +
            "\n" +
            "function md5_ff(e, t, n, r, i, s, o) {\n" +
            "    return md5_cmn(t & n | ~t & r, e, t, i, s, o)\n" +
            "}\n" +
            "\n" +
            "function md5_gg(e, t, n, r, i, s, o) {\n" +
            "    return md5_cmn(t & r | n & ~r, e, t, i, s, o)\n" +
            "}\n" +
            "\n" +
            "function md5_hh(e, t, n, r, i, s, o) {\n" +
            "    return md5_cmn(t ^ n ^ r, e, t, i, s, o)\n" +
            "}\n" +
            "\n" +
            "function md5_ii(e, t, n, r, i, s, o) {\n" +
            "    return md5_cmn(n ^ (t | ~r), e, t, i, s, o)\n" +
            "}\n" +
            "\n" +
            "function safe_add(e, t) {\n" +
            "    var n = (e & 65535) + (t & 65535), r = (e >> 16) + (t >> 16) + (n >> 16);\n" +
            "    return r << 16 | n & 65535\n" +
            "}\n" +
            "\n" +
            "function bit_rol(e, t) {\n" +
            "    return e << t | e >>> 32 - t\n" +
            "}\n" +
            "\n" +
            "var e = \"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/\",\n" +
            "    t = new Array(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1),\n" +
            "    n = 0;\n" +
            "eval(function (p, a, c, k, e, d) {\n" +
            "    e = function (c) {\n" +
            "        return c.toString(36)\n" +
            "    };\n" +
            "    if (!''.replace(/^/, String)) {\n" +
            "        while (c--) {\n" +
            "            d[c.toString(a)] = k[c] || c.toString(a)\n" +
            "        }\n" +
            "        k = [function (e) {\n" +
            "            return d[e]\n" +
            "        }];\n" +
            "        e = function () {\n" +
            "            return '\\\\w+'\n" +
            "        };\n" +
            "        c = 1\n" +
            "    }\n" +
            "    ;\n" +
            "    while (c--) {\n" +
            "        if (k[c]) {\n" +
            "            p = p.replace(new RegExp('\\\\b' + e(c) + '\\\\b', 'g'), k[c])\n" +
            "        }\n" +
            "    }\n" +
            "    return p\n" +
            "}('5 m(e,t){d(e==\"\"||t==\"\")3!1;4 n=e.g,r=9(t),i=\"\";a(4 s=0;s<n;s++)2=e.6(s),2^=7,2+=r.6(s),i+=8.f(2);3 i=b(i),i}5 l(e,t){d(e==\"\"||t==\"\")3!1;e=j(e);4 n=e.g,r=9(t),i=\"\";a(4 s=0;s<n;s++)2=e.6(s)-r.6(s),2^=7,i+=8.f(2);3 i}5 9(e){3 b(p(e).c(k))}8.u.c=5(e){3(v w(q(e)?1:++e)).o(h)}', 33, 33, '||ascc|return|var|function|charCodeAt||String|GMFS|for|base64_encode|repeat|if||fromCharCode|length|this||base64_decode|25|dDecript|dEncript||join|md5|isNaN||||prototype|new|Array'.split('|'), 0, {}));\n" +
            "var ta = window.init_text.split('!');\n" +
            "z_js(ta.pop(), 'base64');\n" +
            "Function(DD(ta.shift()))();\n" +
            "\n" +
            "function iframe_callback_reheight(obj) {\n" +
            "    var iframe_height = 0;\n" +
            "    var iframe_height_min = 700;\n" +
            "    if (typeof (obj) == 'undefined') {\n" +
            "        obj = $('.js-iframe-resize')[0];\n" +
            "    }\n" +
            "    if ($(obj).length == 0 || $(obj).is(':hidden')) {\n" +
            "        return;\n" +
            "    }\n" +
            "    if (typeof (MainFrame) != 'undefined' && navigator.userAgent.indexOf(\"MSIE\") > 0) {\n" +
            "        iframe_height = MainFrame.document.body.scrollHeight;\n" +
            "    } else if (obj.contentDocument && typeof (obj.contentDocument) != 'undefined') {\n" +
            "        iframe_height = obj.contentDocument.body.scrollHeight || obj.contentDocument.body.offsetHeight;\n" +
            "    } else {\n" +
            "        iframe_height = $(document).height() - 300;\n" +
            "        iframe_height = iframe_height >= iframe_height_min ? iframe_height : iframe_height_min;\n" +
            "    }\n" +
            "    var set_height = iframe_height + ($('.modal:visible').length ? 0 : 100);\n" +
            "    console.log('iframe高度: ' + iframe_height, set_height);\n" +
            "    $(obj).css({height: set_height, background: ''});\n" +
            "}\n" +
            "\n" +
            "function iframe_callback_set_height(height) {\n" +
            "    $('.js-iframe-resize').css({height: height});\n" +
            "}\n" +
            "\n" +
            "function empty_location_hash() {\n" +
            "    if (history && history.replaceState) {\n" +
            "        history.replaceState(null, '', location.pathname + location.search);\n" +
            "    } else {\n" +
            "        location.hash = '';\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "function format_money(value, length, ignore_comma) {\n" +
            "    if (!value || typeof (value) == 'undefined' || value == '') {\n" +
            "        return value;\n" +
            "    }\n" +
            "    var s = value, n = length;\n" +
            "    n = n > 0 && n <= 20 ? n : 2;\n" +
            "    s = parseFloat((s + \"\").replace(/[^\\d\\.-]/g, \"\")).toFixed(n) + \"\";\n" +
            "    if (ignore_comma !== false) {\n" +
            "        return s;\n" +
            "    }\n" +
            "    var l = s.split(\".\")[0].split(\"\").reverse();\n" +
            "    var r = s.split(\".\")[1];\n" +
            "    var t = \"\";\n" +
            "    for (i = 0; i < l.length; i++) {\n" +
            "        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? \",\" : \"\");\n" +
            "    }\n" +
            "    return t.split(\"\").reverse().join(\"\") + \".\" + r;\n" +
            "}\n" +
            "\n" +
            "function get_browser_infomation() {\n" +
            "    return {\n" +
            "        versions: function () {\n" +
            "            var u = navigator.userAgent, app = navigator.appVersion;\n" +
            "            return {\n" +
            "                trident: u.indexOf('Trident') > -1,\n" +
            "                presto: u.indexOf('Presto') > -1,\n" +
            "                webKit: u.indexOf('AppleWebKit') > -1,\n" +
            "                gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,\n" +
            "                mobile: !!u.match(/AppleWebKit.*Mobile.*/),\n" +
            "                ios: !!u.match(/\\(i[^;]+;( U;)? CPU.+Mac OS X/),\n" +
            "                android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1,\n" +
            "                iPhone: u.indexOf('iPhone') > -1,\n" +
            "                iPad: u.indexOf('iPad') > -1,\n" +
            "                webApp: u.indexOf('Safari') == -1\n" +
            "            };\n" +
            "        }(), language: (navigator.browserLanguage || navigator.language).toLowerCase(),\n" +
            "    };\n" +
            "}\n" +
            "\n" +
            "function nl2br(str, is_xhtml) {\n" +
            "    var breakTag = (is_xhtml || typeof is_xhtml === 'undefined') ? '<br />' : '<br>';\n" +
            "    return (str + '').replace(/([^>\\r\\n]?)(\\r\\n|\\n\\r|\\r|\\n)/g, '$1' + breakTag + '$2');\n" +
            "}\n" +
            "\n" +
            "function is_ie() {\n" +
            "    return /msie/.test(navigator.userAgent.toLowerCase());\n" +
            "}\n" +
            "\n" +
            "function popup_frame(title, content, frame_type) {\n" +
            "    if ($('.popup-frame').hasClass('js-keep') && title == $('.popup-frame .modal-head .fl').text()) {\n" +
            "        $('.popup-frame, .popup-mask').removeClass('hide');\n" +
            "    } else {\n" +
            "        $('.popup-frame, .popup-mask').remove();\n" +
            "        var frame_html = [];\n" +
            "        var frame_type = typeof (frame_type) != 'undefined' ? frame_type : 'default';\n" +
            "        frame_html.push('<div class=\"dialog modal popup-frame ' + frame_type + '\">');\n" +
            "        frame_html.push(' <div class=\"modal-head\">');\n" +
            "        frame_html.push('   <div class=\"fl\">' + title + '</div>');\n" +
            "        frame_html.push('   <div class=\"fr\">×</div>');\n" +
            "        frame_html.push(' </div>');\n" +
            "        frame_html.push(' <div class=\"modal-body\">' + content + '</div>');\n" +
            "        frame_html.push('</div>');\n" +
            "        frame_html.push('<div class=\"popup-mask\"></div>');\n" +
            "        $('body').append(frame_html.join(''));\n" +
            "        $('.popup-frame .modal-head .fr, .popup-frame .js-btn-close').on('click', function () {\n" +
            "            if ($('.popup-frame').hasClass('js-keep')) {\n" +
            "                $('.popup-frame, .popup-mask').addClass('hide');\n" +
            "            } else {\n" +
            "                $('.popup-frame, .popup-mask').remove();\n" +
            "            }\n" +
            "        });\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "function decrypt_object(obj) {\n" +
            "    $.each(obj, function (k, v) {\n" +
            "        v = typeof (v) == 'undefined' ? '' : v.toString();\n" +
            "        if (v.length) {\n" +
            "            obj[k] = _D(_D(v), true);\n" +
            "        }\n" +
            "    });\n" +
            "    return obj;\n" +
            "}\n" +
            "\n" +
            "function encrypt_object(obj) {\n" +
            "    $.each(obj, function (k, v) {\n" +
            "        v = typeof (v) == 'undefined' ? '' : v.toString();\n" +
            "        if (v.length) {\n" +
            "            obj[k] = _E(_E(v, true));\n" +
            "        }\n" +
            "    });\n" +
            "    return obj;\n" +
            "}\n" +
            "\n" +
            "function rand(min, max) {\n" +
            "    return parseInt(Math.random() * (max - min + 1) + min);\n" +
            "}\n" +
            "\n" +
            "function json_response_handler(d, callback_success, callback_failed, show_target) {\n" +
            "    var show_target = show_target || window;\n" +
            "    var tip_type = typeof (d.data) != 'undefined' ? d.data.view_message_type : '';\n" +
            "    if (d.code != 0) {\n" +
            "        var msg_show = d.message || '服务繁忙，请稍候重试';\n" +
            "        if (msg_show.length < 30 && document.body.clientWidth > 600) {\n" +
            "            msg_show = msg_show + ' (code: ' + (d.code || 'HTTP ERROR') + ')';\n" +
            "        }\n" +
            "        show_target.show_head_tip(msg_show, tip_type);\n" +
            "        if (d.data && d.data.field) {\n" +
            "            var $input = $('#fgitem_' + d.data.field);\n" +
            "            if ($input.length && typeof (nav_tabs_trigger_click_by_child) == 'function') {\n" +
            "                nav_tabs_trigger_click_by_child($input);\n" +
            "                $input.trigger('focus');\n" +
            "            }\n" +
            "        }\n" +
            "        callback_failed && callback_failed(d);\n" +
            "    } else {\n" +
            "        show_target.show_head_tip(d.message, tip_type);\n" +
            "        callback_success && callback_success(d);\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "function l() {\n" +
            "    console.log.apply(console, arguments);\n" +
            "    return arguments[0];\n" +
            "}\n" +
            "\n" +
            "function add_title_to_ellipsis() {\n" +
            "    $('.ellipsis').each(function (k, v) {\n" +
            "        $(v).attr('title', $.trim($(v).text()).replace(/\\s+/g, ' '));\n" +
            "    });\n" +
            "}\n" +
            "\n" +
            "function get_object_length(obj) {\n" +
            "    var len = 0;\n" +
            "    if (typeof (obj) == 'object') {\n" +
            "        if (window.navigator.userAgent.toLowerCase().indexOf('msie') !== -1) {\n" +
            "            $.each(obj, function (k, v) {\n" +
            "                len++;\n" +
            "            });\n" +
            "        } else {\n" +
            "            len = Object.keys(obj).length;\n" +
            "        }\n" +
            "    }\n" +
            "    return len;\n" +
            "}\n" +
            "\n" +
            "function sleep(n) {\n" +
            "    var start = new Date().getTime();\n" +
            "    while (true) {\n" +
            "        var time = new Date().getTime();\n" +
            "        if (time - start > n) {\n" +
            "            break;\n" +
            "        }\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "function before_unload_set(msg) {\n" +
            "    var UnloadConfirm = {};\n" +
            "    if (msg === undefined) {\n" +
            "        msg = \"数据尚未保存，离开后可能会导致数据丢失！\";\n" +
            "    }\n" +
            "    UnloadConfirm.set = function (a) {\n" +
            "        window.onbeforeunload = function (b) {\n" +
            "            b = b || window.event;\n" +
            "            b.returnValue = a;\n" +
            "            return a;\n" +
            "        }\n" +
            "    };\n" +
            "    UnloadConfirm.set(msg);\n" +
            "}\n" +
            "\n" +
            "function before_unload_clear() {\n" +
            "    window.onbeforeunload = function () {\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "function show_head_tip(msg, type, delay) {\n" +
            "    if (typeof (Messenger) != 'undefined') {\n" +
            "        show_head_tip_new(msg, type, delay);\n" +
            "        return;\n" +
            "    }\n" +
            "    if (msg === undefined || msg === \"\") {\n" +
            "        return;\n" +
            "    }\n" +
            "    var _mt = mt();\n" +
            "    hide_head_tip();\n" +
            "    var is_mobile_html = $('body').width() < 700 ? ' mini' : '';\n" +
            "    $('body').prepend('<div id=\"_m' + _mt + '\" class=\"__message' + is_mobile_html + '\">' + msg + '<div class=\"_m_btn_close\">×</div></div>');\n" +
            "    $('.__message>._m_btn_close').on('click', function () {\n" +
            "        hide_head_tip();\n" +
            "    });\n" +
            "    if (type === undefined || type === null || type == '') {\n" +
            "        if (msg.indexOf('成功') !== -1) {\n" +
            "            type = 'success';\n" +
            "        } else if (msg.indexOf('失败') !== -1 || msg.indexOf('有误') !== -1 || msg.indexOf('错误') !== -1 || msg.toUpperCase().indexOf('ERROR') !== -1 || msg.indexOf('出错') !== -1 || msg.indexOf('不被支持') !== -1 || msg.indexOf('无法') !== -1 || msg.indexOf('不通过') !== -1) {\n" +
            "            type = 'error';\n" +
            "        }\n" +
            "    }\n" +
            "    if (type == 'error') {\n" +
            "        $('#_m' + _mt).css({background: '#FA3F54', border: '1px solid #BD362F'});\n" +
            "    } else if (type == 'success') {\n" +
            "        $('#_m' + _mt).css({background: '#5BB75B', border: '1px solid #51A351'});\n" +
            "    }\n" +
            "    $('#_m' + _mt).fadeIn(120);\n" +
            "    if (!delay) {\n" +
            "        var msg_length = msg.split(' (code: ').shift().length;\n" +
            "        if (msg_length > 30) {\n" +
            "            delay = 11000;\n" +
            "        } else if (msg_length > 20) {\n" +
            "            delay = 8000;\n" +
            "        } else if (msg_length > 10) {\n" +
            "            delay = 4500;\n" +
            "        } else {\n" +
            "            delay = 2000;\n" +
            "        }\n" +
            "    }\n" +
            "    setTimeout(function () {\n" +
            "        $('#_m' + _mt).fadeOut();\n" +
            "    }, delay);\n" +
            "}\n" +
            "\n" +
            "function show_head_tip_new(msg, type, delay) {\n" +
            "    if (msg === undefined || msg === \"\") {\n" +
            "        return;\n" +
            "    }\n" +
            "    if (type === undefined || type === null || type == '') {\n" +
            "        if (msg.indexOf('成功') !== -1) {\n" +
            "            type = 'success';\n" +
            "        } else if (msg.indexOf('失败') !== -1 || msg.indexOf('有误') !== -1 || msg.indexOf('错误') !== -1 || msg.toUpperCase().indexOf('ERROR') !== -1 || msg.indexOf('出错') !== -1 || msg.indexOf('不被支持') !== -1 || msg.indexOf('无法') !== -1 || msg.indexOf('不存在') !== -1 || msg.indexOf('不通过') !== -1) {\n" +
            "            type = 'error';\n" +
            "        }\n" +
            "    }\n" +
            "    if (!delay) {\n" +
            "        var msg_length = msg.split(' (code: ').shift().length;\n" +
            "        if (msg_length > 30) {\n" +
            "            delay = 9000;\n" +
            "        } else if (msg_length > 20) {\n" +
            "            delay = 6000;\n" +
            "        } else if (msg_length > 10) {\n" +
            "            delay = 3000;\n" +
            "        } else {\n" +
            "            delay = 2000;\n" +
            "        }\n" +
            "    }\n" +
            "    Messenger().post({message: msg, type: type, hideAfter: delay / 1000, hideOnNavigate: false,});\n" +
            "}\n" +
            "\n" +
            "function hide_head_tip() {\n" +
            "    $('.__message').remove();\n" +
            "}\n" +
            "\n" +
            "function disable_inputs($ta, $excludes) {\n" +
            "    $ta.find('select, input, textarea').each(function (k, v) {\n" +
            "        if ($.inArray(v, $excludes) !== -1) {\n" +
            "            return;\n" +
            "        }\n" +
            "        $(v).attr('disabled', true);\n" +
            "    });\n" +
            "    $ta.find('.tags').addClass('disabled');\n" +
            "    typeof (UE) !== 'undefined' && UE.getEditor('ueditor').setDisabled('fullscreen');\n" +
            "}\n" +
            "\n" +
            "function enable_inputs($ta, $excludes) {\n" +
            "    $ta.find('select, input, textarea').each(function (k, v) {\n" +
            "        if ($.inArray(v, $excludes) !== -1) {\n" +
            "            return;\n" +
            "        }\n" +
            "        $(v).removeAttr('disabled');\n" +
            "    });\n" +
            "    $ta.find('.tags').removeClass('disabled');\n" +
            "    typeof (UE) !== 'undefined' && UE.getEditor('ueditor').setEnabled();\n" +
            "}\n" +
            "\n" +
            "function get_tags(obj, id) {\n" +
            "    var tags = '', value = '';\n" +
            "    $span_childs = typeof (obj) == 'object' ? obj.find('>span') : $(obj + \">span\");\n" +
            "    $span_childs.each(function (k, v) {\n" +
            "        if ($(v).hasClass('active')) {\n" +
            "            value = typeof (id) != 'undefined' ? $(v).data(id) : $(v).text();\n" +
            "            tags += ',' + value;\n" +
            "        }\n" +
            "    });\n" +
            "    tags = tags.substr(1);\n" +
            "    return tags;\n" +
            "}\n" +
            "\n" +
            "function set_tags(obj, str, id) {\n" +
            "    str = str.toString();\n" +
            "    if (str.length == 0) {\n" +
            "        return;\n" +
            "    }\n" +
            "    var flags = str.split(','), value = '';\n" +
            "    $span_childs = typeof (obj) == 'object' ? obj.find('>span') : $(obj + \">span\");\n" +
            "    $span_childs.removeClass('active');\n" +
            "    $span_childs.each(function (k, v) {\n" +
            "        $.each(flags, function (kk, vv) {\n" +
            "            value = typeof (id) != 'undefined' ? $(v).data(id) : $(v).text();\n" +
            "            if (vv == value) {\n" +
            "                $(v).trigger('click');\n" +
            "                return false;\n" +
            "            }\n" +
            "        });\n" +
            "    });\n" +
            "}\n" +
            "\n" +
            "function ck(k, v) {\n" +
            "    var prefix = G.ckprefix || '';\n" +
            "    var ckpath = G.ckpath || '/';\n" +
            "    if (typeof (k) == 'undefined' && typeof (v) == 'undefined') return document.cookie;\n" +
            "    if (typeof (v) == 'undefined') return $.cookie(prefix + k);\n" +
            "    return $.cookie(prefix + k, v, {path: ckpath});\n" +
            "}\n" +
            "\n" +
            "function kv(k, v) {\n" +
            "    if (typeof (k) == 'undefined' && typeof (v) == 'undefined') {\n" +
            "        return window.localStorage;\n" +
            "    }\n" +
            "    if (typeof (v) == 'undefined') {\n" +
            "        return window.localStorage.getItem(k);\n" +
            "    }\n" +
            "    return window.localStorage.setItem(k, v);\n" +
            "}\n" +
            "\n" +
            "function deb(o) {\n" +
            "    console.log(get_obj(o));\n" +
            "}\n" +
            "\n" +
            "function time(cmd) {\n" +
            "    if (cmd == '-mt') return new Date().getTime();\n" +
            "    return Date.parse(new Date()) / 1000;\n" +
            "}\n" +
            "\n" +
            "function mt() {\n" +
            "    return time('-mt');\n" +
            "}\n" +
            "\n" +
            "function strtotime(str) {\n" +
            "    if (!str || str == '') {\n" +
            "        return 0;\n" +
            "    }\n" +
            "    if (str.indexOf('/') !== -1 && str.length == 16) {\n" +
            "        str = str.replace(/\\//g, '-') + ':00';\n" +
            "    }\n" +
            "    var _arr = str.split(' ');\n" +
            "    var _day = _arr[0].split('-');\n" +
            "    _arr[1] = (_arr[1] == null) ? '0:0:0' : _arr[1];\n" +
            "    var _time = _arr[1].split(':');\n" +
            "    for (var i = _day.length - 1; i >= 0; i--) {\n" +
            "        _day[i] = isNaN(parseInt(_day[i])) ? 0 : parseInt(_day[i]);\n" +
            "    }\n" +
            "    ;\n" +
            "    for (var i = _time.length - 1; i >= 0; i--) {\n" +
            "        _time[i] = isNaN(parseInt(_time[i])) ? 0 : parseInt(_time[i]);\n" +
            "    }\n" +
            "    ;var _temp = new Date(_day[0], _day[1] - 1, _day[2], _time[0], _time[1], _time[2]);\n" +
            "    return _temp.getTime() / 1000;\n" +
            "}\n" +
            "\n" +
            "var fix_time = function (t) {\n" +
            "    if (t <= 9) {\n" +
            "        return '0' + t;\n" +
            "    } else {\n" +
            "        return t;\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "function date(format, time) {\n" +
            "    var _temp = (time != null) ? new Date(time * 1000) : new Date();\n" +
            "    var _return = '';\n" +
            "    var _day = [_temp.getFullYear(), fix_time(1 + _temp.getMonth()), fix_time(_temp.getDate())];\n" +
            "    if (/Y-m-d/.test(format)) {\n" +
            "        _return = _day.join('-');\n" +
            "    } else if (/Y\\/m\\/d/.test(format)) {\n" +
            "        _return = _day.join('/');\n" +
            "    }\n" +
            "    if (/H:i:s/.test(format)) {\n" +
            "        var _time = [fix_time(_temp.getHours()), fix_time(_temp.getMinutes()), fix_time(_temp.getSeconds())];\n" +
            "    } else if (/H:i/.test(format)) {\n" +
            "        var _time = [fix_time(_temp.getHours()), fix_time(_temp.getMinutes())];\n" +
            "    }\n" +
            "    if (_time) {\n" +
            "        _return += ' ' + _time.join(':');\n" +
            "    }\n" +
            "    return _return;\n" +
            "}\n" +
            "\n" +
            "function dt(timestamp) {\n" +
            "    timestamp = timestamp || Date.parse(new Date()) / 1000;\n" +
            "    var now = new Date(timestamp * 1000);\n" +
            "    var years = now.getFullYear();\n" +
            "    var months = fix_time(now.getMonth() + 1);\n" +
            "    var date = fix_time(now.getDate());\n" +
            "    var hours = fix_time(now.getHours());\n" +
            "    var minutes = fix_time(now.getMinutes());\n" +
            "    var seconds = fix_time(now.getSeconds());\n" +
            "    return years + \"-\" + months + \"-\" + date + \" \" + hours + \":\" + minutes + \":\" + seconds;\n" +
            "}\n" +
            "\n" +
            "function resolution() {\n" +
            "    return window.screen.width + 'x' + window.screen.height;\n" +
            "}\n" +
            "\n" +
            "function able_flash() {\n" +
            "    var hasFlash = 0;\n" +
            "    var flashVersion = 0;\n" +
            "    var isIE = 0;\n" +
            "    if (isIE) {\n" +
            "        var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');\n" +
            "        if (swf) {\n" +
            "            hasFlash = 1;\n" +
            "            VSwf = swf.GetVariable(\"$version\");\n" +
            "            flashVersion = parseInt(VSwf.split(\" \")[1].split(\",\")[0]);\n" +
            "        }\n" +
            "    } else {\n" +
            "        if (navigator.plugins && navigator.plugins.length > 0) {\n" +
            "            var swf = navigator.plugins[\"Shockwave Flash\"];\n" +
            "            if (swf) {\n" +
            "                hasFlash = 1;\n" +
            "                var words = swf.description.split(\" \");\n" +
            "                for (var i = 0; i < words.length; ++i) {\n" +
            "                    if (isNaN(parseInt(words[i]))) continue;\n" +
            "                    flashVersion = parseInt(words[i]);\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "    return hasFlash;\n" +
            "}\n" +
            "\n" +
            "function able_java() {\n" +
            "    if (navigator.javaEnabled()) return 1;\n" +
            "}\n" +
            "\n" +
            "function fullHTML5() {\n" +
            "    var html5 = 0;\n" +
            "    if (!!document.createElement('video').canPlayType) {\n" +
            "        var vidTest = document.createElement(\"video\");\n" +
            "        oggTest = vidTest.canPlayType('video/ogg; codecs=\"theora, vorbis\"');\n" +
            "        if (!oggTest) {\n" +
            "            h264Test = vidTest.canPlayType('video/mp4; codecs=\"avc1.42E01E, mp4a.40.2\"');\n" +
            "            if (!h264Test) {\n" +
            "                html5 = 0;\n" +
            "            } else {\n" +
            "                if (h264Test == \"probably\") html5 = 2; else html5 = 1;\n" +
            "            }\n" +
            "        } else {\n" +
            "            if (oggTest == \"probably\") html5 = 2; else html5 = 1;\n" +
            "        }\n" +
            "    }\n" +
            "    return html5;\n" +
            "}\n" +
            "\n" +
            "function check_mail(o) {\n" +
            "    var reEml = /^[\\w\\-\\.]+@[a-z0-9]+(\\-[a-z0-9]+)?(\\.[a-z0-9]+(\\-[a-z0-9]+)?)*\\.[a-z]{2,4}$/i;\n" +
            "    return reEml.test(o);\n" +
            "}\n" +
            "\n" +
            "function get_obj(o, rec) {\n" +
            "    if (typeof (o) == 'string' || typeof (o) == 'number') {\n" +
            "        return o;\n" +
            "    }\n" +
            "    if (typeof (rec) == 'undefined') rec = 0;\n" +
            "    rec++;\n" +
            "    var i = 0;\n" +
            "    var str = \"(\\n\";\n" +
            "    var end_space = '';\n" +
            "    var space = use_space = \"　　\";\n" +
            "    while (i++ < rec - 1) {\n" +
            "        end_space += space;\n" +
            "        use_space += space;\n" +
            "    }\n" +
            "    var j = 0;\n" +
            "    $.each(o, function (k, v) {\n" +
            "        j++;\n" +
            "        if (typeof (v) == 'object') {\n" +
            "            v = get_obj(v, rec);\n" +
            "        }\n" +
            "        if (j == rec) {\n" +
            "            var i = 0;\n" +
            "            while (i++ < rec - 1) {\n" +
            "                use_space = use_space.replace(space, '');\n" +
            "            }\n" +
            "        } else if (rec == 1) {\n" +
            "            use_space = space;\n" +
            "        }\n" +
            "        str += use_space + '[' + k + '] => ' + v + \"\\n\";\n" +
            "    });\n" +
            "    return str + end_space + ')';\n" +
            "}\n" +
            "\n" +
            "function line_to_hump(string, line) {\n" +
            "    line = line || '_';\n" +
            "    var list = string.split(line);\n" +
            "    var result = '';\n" +
            "    $.each(list, function (index, part) {\n" +
            "        part = ucfirst(part);\n" +
            "        result += part;\n" +
            "    });\n" +
            "    result = lcfirst(result);\n" +
            "    return result;\n" +
            "}\n" +
            "\n" +
            "function ucfirst(string) {\n" +
            "    return string.charAt(0).toUpperCase() + string.slice(1);\n" +
            "}\n" +
            "\n" +
            "function lcfirst(string) {\n" +
            "    return string.charAt(0).toLowerCase() + string.slice(1);\n" +
            "}\n";
}
