/*
 FusionCharts JavaScript Library
 Copyright FusionCharts Technologies LLP
 License Information at <http://www.fusioncharts.com/license>

 @version 3.9.0
*/
FusionCharts.register("module", ["private", "modules.renderer.js-gradientlegend", function() {
    function gb(g, h, za) {
        var oa = g[0],
            J = g[1];
        g = g[2];
        oa += (h[0] - oa) * za;
        J += (h[1] - J) * za;
        h = g + (h[2] - g) * za;
        return {
            hex: (B + (oa << 16 | J << 8 | h).toString(16)).slice(-6),
            rgb: [oa, J, h]
        }
    }

    function jb(g, h) {
        return g.maxvalue - h.maxvalue
    }

    function tb(h) {
        var B, za, oa = h.colorRange || {},
            J = h.dataMin,
            u = h.dataMax,
            S = h.sortLegend || !1,
            x = h.mapByCategory || !1,
            F = h.defaultColor,
            ca = h.numberFormatter,
            G = oa.color;
        h = this.colorArr = [];
        var L, ba, la;
        this.mapByCategory =
            x;
        "1" === oa.mapbypercent && (this.mapbypercent = !0);
        if ("1" === oa.gradient && !x) {
            this.gradient = !0;
            za = Gb(Qa(oa.startcolor, oa.mincolor, oa.code));
            S = Lb(Gb(Qa(za, F, "CCCCCC")));
            x = this.scaleMin = Ua(oa.startvalue, oa.minvalue, this.mapbypercent ? 0 : J);
            h.push({
                code: za,
                maxvalue: x,
                label: O(oa.startlabel),
                codeRGB: Lb(za)
            });
            if (G && (B = G.length))
                for (J = 0; J < B; J += 1) F = G[J], za = Gb(Qa(F.color, F.code)), ba = Ua(F.value, F.maxvalue), la = Ua(F.minvalue), ba > x && h.push({
                    code: za,
                    maxvalue: ba,
                    userminvalue: la,
                    label: O(Qa(F.label, F.displayvalue)),
                    codeRGB: Lb(za)
                });
            h.sort(jb);
            B = h.length;
            for (J = 1; J < B; J += 1) F = h[J], za = F.maxvalue - x, 0 < za ? (F.minvalue = x, F.range = za, x = F.maxvalue) : (h.splice(J, 1), --J, --B);
            2 <= h.length && (this.scaleMax = x, h[J - 1].label = Qa(oa.endlabel, h[J - 1].label, h[J - 1].displayvalue));
            1 === h.length && (ba = Ua(oa.maxvalue, this.mapbypercent ? 100 : u), h.push({
                minvalue: x,
                maxvalue: ba,
                range: ba - x,
                label: oa.endlabel
            }), this.scaleMax = ba, delete h[0].code);
            oa = h[0];
            u = h[h.length - 1];
            oa.code && u.code || (za = g(S), B = A((za[2] = 0, za)), za = A((za[2] = 100, za)), oa.code || (oa.codeRGB = B, oa.code = ea(B)),
                u.code || (u.codeRGB = za, u.code = ea(za)));
            B = h.length;
            for (J = 1; J < B; J += 1)
                if (F = h[J], F.code) {
                    if (L)
                        for (u = F, la = oa.maxvalue, G = u.maxvalue - la; L < J; L += 1) S = h[L], za = gb(oa.codeRGB, u.codeRGB, (S.maxvalue - la) / G), S.code = za.hex, S.codeRGB = za.rgb;
                    L = null;
                    oa = F
                } else L = L || J;
            if (void 0 === this.scaleMin || void 0 === this.scaleMax) this.noValidRange = !0
        } else if (G && (B = G.length)) {
            for (J = 0; J < B; J += 1) F = G[J], za = Qa(F.color, F.code), ba = Ua(F.maxvalue), la = Ua(F.minvalue), L = Qa(F.label, F.displayvalue, x ? na : ca.dataLabels(la) + " - " + ca.dataLabels(ba)), (za &&
                ba > la || x && L) && h.push({
                code: za,
                maxvalue: ba,
                minvalue: la,
                label: O(L),
                labelId: L.toLowerCase()
            });
            h.length ? S && h.sort(jb) : this.noValidRange = !0
        }
    }

    function G(g, h) {
        return h ? X(100 * g) / 100 + "%" : cb(g, na).toString()
    }
    var u = this,
        ga = u.hcLib,
        Xa = u.window,
        Xa = /msie/i.test(Xa.navigator.userAgent) && !Xa.opera,
        Ua = ga.pluckNumber,
        B = ga.COLOR_BLACK,
        Zb = ga.COLOR_GLASS,
        ab = ga.FC_CONFIG_STRING,
        h = ga.graphics,
        A = h.HSBtoRGB,
        g = h.RGBtoHSB,
        ea = h.RGBtoHex,
        Lb = h.HEXtoRGB,
        F = ga.COMMASTRING,
        na = ga.BLANKSTRING,
        O = ga.parseUnsafeString,
        Ha = ga.graphics.convertColor,
        ca = ga.POSITION_TOP,
        nb = ga.POSITION_MIDDLE,
        fb = ga.POSITION_START,
        Ob = ga.POSITION_END,
        $b = ga.graphics.getDarkColor,
        ob = ga.graphics.getLightColor,
        Qa = ga.pluck,
        cb = ga.getValidValue,
        lb = ga.toRaphaelColor,
        pb = ga.hasTouch,
        X = Math.round,
        Na = Math.max,
        ba = Math.min,
        ua = Math.abs,
        ta, Sa, Ab, ac = "rgba(192,192,192," + (Xa ? .002 : 1E-6) + ")",
        Gb = function(g) {
            return g && g.replace(/^#?([a-f0-9]+)/ig, "$1")
        };
    tb.prototype = {
        getColorObj: function(g) {
            var h = this.colorArr,
                B = this.gradient ? 1 : 0,
                u = h[B],
                J;
            if (this.mapByCategory) {
                for (g = O(g).toLowerCase(); u;) {
                    if (u.labelId ===
                        g) return {
                        code: u.code,
                        seriesIndex: B
                    };
                    B += 1;
                    u = h[B]
                }
                return {
                    outOfRange: !0
                }
            }
            if (this.gradient) {
                if (this.scaleMin <= g && this.scaleMax >= g) {
                    for (; u && u.maxvalue < g;) B += 1, u = h[B];
                    g = (g - u.minvalue) / u.range;
                    return {
                        code: gb(h[B - 1].codeRGB, u.codeRGB, g).hex
                    }
                }
                return {
                    outOfRange: !0
                }
            }
            for (; u;) {
                if (u.maxvalue > g && u.minvalue <= g) return {
                    code: u.code,
                    seriesIndex: B
                };
                u.maxvalue === g && (J = B);
                B += 1;
                u = h[B]
            }
            return (u = h[J]) && u.maxvalue === g ? {
                code: u.code,
                seriesIndex: J
            } : {
                outOfRange: !0
            }
        }
    };
    tb.prototype.constructor = tb;
    ga.colorRange = tb;
    ta = ga.configureGradientLegendOptions =
        function(g, h) {
            var u = g.legend,
                F = h.chart;
            u.legendSliderBorderWidth = Ua(F.legendpointerborderthickness, 1);
            u.legendSliderBorderColor = Ha(Qa(F.legendpointerbordercolor, B), Ua(F.legendpointerborderalpha, 100));
            u.legendSliderWidth = Ua(F.legendpointerwidth, F.legendpointerswidth, 12);
            u.legendSliderHeight = Ua(F.legendpointerheight, F.legendpointersheight, 12);
            u.legendColorBoxBorderColor = u.borderColor;
            u.legendColorBoxBorderWidth = u.borderWidth;
            u.legendScaleColor = Ha(Qa(F.legendscalelinecolor, B), Ua(F.legendscalelinealpha,
                100));
            u.legendScalePadding = Ua(F.legendscalepadding, 4);
            u.legendScaleLineThickness = Ua(F.legendscalelinethickness, 1);
            u.legendScaleTickDistance = Ua(F.legendscaletickdistance, 6);
            u.itemStyle.cursor = "default";
            u.interActivity = Ua(F.interactivelegend, 1)
        };
    ga.placeGLegendBlockRight = function(g, h, B, u, J) {
        this.configureLegendOptions(g, h.chart, !0, J, B);
        ta(g, h);
        J = this.snapLiterals || (this.snapLiterals = {});
        var F = g[ab],
            S = this.smartLabel || F.smartLabel,
            x = g.legend,
            ca = g.chart.spacingRight,
            A, O, L = x.textPadding = 2,
            ua = 2 * L,
            la = x.title.padding,
            qa = 0,
            X = 0,
            ea = 2 * x.padding;
        h = Ua(h.chart.legendpadding, 7) + x.borderWidth / 2 + 1;
        var V = g.colorRange || {},
            Da = V.colorArr,
            Ha = V.mapbypercent,
            ga = V.scaleMin,
            Ea = V.scaleMax - ga,
            Qa = x.legendSliderWidth,
            La = x.legendSliderHeight / 2;
        O = x.legendScalePadding;
        var y = x.legendScaleTickDistance,
            ha = x.itemStyle || {};
        A = Ua(parseInt(ha.lineHeight, 10) || 12);
        var Va = .75 * A,
            Ma = B - ea,
            Ia, Aa, Ja = 0,
            Oa, s, da, Ba, Ca, Fa, Pa;
        u -= ea;
        if (!V.noValidRange && Da && 1 < (Aa = Da.length)) {
            --Aa;
            x.title.text !== na && (S.setStyle(x.title.style), A = S.getSmartText(x.title.text, Ma,
                Na(A, u / 4)), x.title.text = A.text, qa = A.width + ea, u -= X = A.height + la);
            S.setStyle(ha);
            A = S.lineHeight;
            Ma -= y + O + Qa;
            x.colorBoxX = Qa;
            la = Na(A, Ma / 2);
            Ma = ba(Ma - la - 4, A);
            Oa = Na(A, u / 2);
            Ia = u / 4;
            y = Da[0];
            y.scaleLabel = G(y.maxvalue, Ha);
            A = S.getSmartText(y.label, Ia, Ma);
            y.label = A.text;
            ha = A.height;
            y.labelY = Va - A.height / 2;
            O = S.getSmartText(y.scaleLabel, la, Oa);
            y.scaleLabel = O.text;
            V = O.height / 2;
            s = O.width;
            y.scaleLabelY = Va - O.height / 2;
            x.colorBoxY = Na(V, A.width + ua, La) + X;
            y = Pa = Da[Aa];
            y.scaleLabel = G(y.maxvalue, Ha);
            A = S.getSmartText(y.label, Ia,
                Ma);
            y.label = A.text;
            ha = Na(ha, A.height);
            y.labelY = Va - A.height / 2;
            O = S.getSmartText(y.scaleLabel, la, Oa);
            y.scaleLabel = O.text;
            s = Na(s, O.width);
            Ia = O.height / 2;
            A = Na(A.width + ua, Ia, La);
            y.scaleLabelY = Va - O.height / 2;
            x.colorBoxHeight = La = u - x.colorBoxY - A;
            Oa = La - Ia;
            da = La / Ea;
            Ca = ba(La - Ja, Oa - V) - 4;
            for (Ia = 1; Ia < Aa; Ia += 1) y = Da[Ia], Ba = (y.maxvalue - ga) * da, A = S.getSmartText(y.label, 2 * ba(Ba - Ja, La - Ba), Ma), y.label = A.text, ha = Na(ha, A.height), y.labelY = Va - A.height / 2, A = A.width / 2, y.scaleLabel = G(y.maxvalue, Ha), O = S.getSmartText(y.scaleLabel,
                la, 2 * ba(Ba - V, Oa - Ba)), y.scaleLabel = O.text, s = Na(s, O.width), Fa = O.height / 2, y.scaleLabelY = Va - O.height / 2, Ca = ba(Ca, (Ba - Na(Fa + V, A + Ja) - 4) * Ea / y.range), Ja = A + Ba, V = Fa + Ba;
            Ca = Na(ba(Ca, (ba(Oa - V, La - Ja) - 4) * Ea / Pa.range, .3 * u), 0);
            x.colorBoxHeight -= Ca;
            x.colorBoxWidth = ha && ha + ua || 15;
            x.height = x.totalHeight = u + X + ea - Ca;
            x.width = (s && s + L) + x.colorBoxWidth + Qa + x.legendScaleTickDistance + x.legendScalePadding + ea;
            x.width < qa && (x.colorBoxX += (qa - x.width) / 2, x.width = qa);
            x.width > B && (x.width = B);
            J.legendstartx = F.width - ca - x.width;
            J.legendwidth =
                x.width;
            J.legendendx = J.legendstartx + J.legendwidth;
            J.legendheight = x.height;
            h += x.width;
            g.chart.marginRight += h;
            return h
        }
        x.enabled = !1;
        return 0
    };
    ga.placeGLegendBlockBottom = function(g, h, B, u, F) {
        this.configureLegendOptions(g, h.chart, !1, F, B);
        ta(g, h);
        F = this.snapLiterals || (this.snapLiterals = {});
        var A = g[ab],
            S = this.smartLabel || A.smartLabel,
            x = g.legend,
            O = g.chart,
            ca = O.spacingBottom,
            ua = O.spacingLeft,
            O = O.spacingRight,
            L, X, la = x.textPadding = 2,
            qa = x.title.padding,
            ea = 0,
            Ha = 0,
            V = 2 * x.padding;
        h = Ua(h.chart.legendpadding, 7) + x.borderWidth /
            2 + 1;
        var Da = g.colorRange || {},
            ga = Da.colorArr,
            Ta = Da.mapbypercent,
            Ea = Da.scaleMin,
            Sa = Da.scaleMax - Ea,
            La = x.legendSliderWidth,
            y = x.legendSliderHeight,
            ha = x.legendScalePadding,
            Va = x.legendScaleTickDistance,
            Ma = x.itemStyle || {};
        L = Ua(parseInt(Ma.lineHeight, 10) || 12);
        var Ia = .75 * L,
            Aa = u - V,
            Ja, Oa, s, da, Ba = 0,
            Ca, Fa, Pa;
        B -= V;
        if (!Da.noValidRange && ga && 1 < (Oa = ga.length)) {
            --Oa;
            x.title.text !== na && (S.setStyle(x.title.style), L = S.getSmartText(x.title.text, B, Aa / 3), x.title.text = L.text, ea = L.width + V, Aa -= Ha = L.height + qa);
            S.setStyle(Ma);
            L =
                S.lineHeight;
            Aa -= Va + ha + y;
            qa = Na(L, Aa / 2);
            Ma = ba(Aa - qa - 4, L);
            Ja = B / 4;
            da = 2 * Ja;
            s = ga[0];
            s.scaleLabel = G(s.maxvalue, Ta);
            L = S.getSmartText(s.label, Ja, Ma);
            s.label = L.text;
            Aa = L.height;
            s.labelY = Ia - L.height / 2;
            X = S.getSmartText(s.scaleLabel, da, qa);
            s.scaleLabel = X.text;
            ha = X.width / 2;
            Va = X.height;
            s.code || (s.code = Qa(x.minColor, "CCCCCC"));
            x.colorBoxX = Na(ha, L.width + la, La);
            s = Da = ga[Oa];
            s.scaleLabel = G(s.maxvalue, Ta);
            L = S.getSmartText(s.label, Ja, Ma);
            s.label = L.text;
            Aa = Na(Aa, L.height);
            s.labelY = Ia - L.height / 2;
            X = S.getSmartText(s.scaleLabel,
                da, qa);
            s.scaleLabel = X.text;
            Va = Na(Va, X.height);
            s = X.width / 2;
            L = Na(L.width + la, s, La);
            x.colorBoxWidth = La = B - x.colorBoxX - L;
            da = La - s;
            Ca = La / Sa;
            Pa = ba(La - Ba, da - ha) - 4;
            for (Ja = 1; Ja < Oa; Ja += 1) s = ga[Ja], Fa = (s.maxvalue - Ea) * Ca, L = S.getSmartText(s.label, 2 * ba(Fa - Ba, La - Fa), Ma), s.label = L.text, Aa = Na(Aa, L.height), s.labelY = Ia - L.height / 2, L = L.width / 2, s.scaleLabel = G(s.maxvalue, Ta), X = S.getSmartText(s.scaleLabel, 2 * ba(Fa - ha, da - Fa), qa), s.scaleLabel = X.text, Va = Na(Va, X.height), X = X.width / 2, Pa = ba(Pa, (Fa - Na(X + ha, L + Ba) - 4) * Sa / s.range), Ba = L +
                Fa, ha = X + Fa;
            Pa = Na(ba(Pa, (ba(da - ha, La - Ba) - 4) * Sa / Da.range, .3 * B), 0);
            x.colorBoxWidth -= Pa;
            x.width = B + V - Pa;
            x.width < ea && (x.colorBoxX += (ea - x.width) / 2, x.width = ea);
            x.colorBoxY = Ha + y;
            x.colorBoxHeight = Aa && Aa + 2 * la || 15;
            x.height = x.totalHeight = (Va && Va + la) + x.colorBoxHeight + Ha + y + x.legendScaleTickDistance + x.legendScalePadding + V;
            x.height > u && (x.height = u);
            F.legendstartx = ua + .5 * (A.width - ua - O - x.width) + (x.x || 0);
            F.legendwidth = x.width;
            F.legendendx = F.legendstartx + F.legendwidth;
            F.legendstarty = A.height - ca - x.height;
            F.legendheight =
                x.height;
            F.legendendy = F.legendstarty + F.legendheight;
            h += x.height;
            g.chart.marginBottom += h;
            return h
        }
        x.enabled = !1;
        return 0
    };
    Sa = function() {
        return {
            point: this
        }
    };
    Ab = function(g) {
        return X(100 * g) / 100
    };
    ga.rendererRoot.drawGradientLegendItem = function(g) {
        var h = this,
            A = h.paper,
            O = h.options,
            J = h.canvasLeft,
            G = h.canvasTop,
            S = h.canvasWidth,
            x = h.canvasHeight,
            ba = O.colorRange,
            ea = O.chart.textDirection,
            ga, L, ta, la, qa = O.legend,
            Xa = Ua(qa.padding, 4),
            cb = qa.itemStyle,
            O = qa.symbolStyle,
            V = qa.interActivity,
            Da = g.elements;
        g = Da.elementGroup.trackTooltip(!0);
        var gb = "vertical" === qa.layout,
            Ta, Ea, ab, La, y, ha, Va = 0,
            Ma = qa.lighting3d,
            Ia = qa.colorBoxWidth,
            Aa = qa.colorBoxHeight,
            Ja = Ia,
            Oa = Aa,
            s = {
                FCcolor: {
                    color: na,
                    alpha: na,
                    angle: 0,
                    ratio: na
                }
            },
            da = s.FCcolor,
            Ba = qa.colorBoxX + Xa,
            Ca = qa.colorBoxY + Xa,
            Fa, Pa, tb = qa.legendColorBoxBorderColor,
            xa = qa.legendColorBoxBorderWidth,
            jb = ["M"],
            Lb = qa.legendScaleColor;
        ha = qa.legendScalePadding;
        var Mb = qa.legendScaleLineThickness,
            Bb = Mb % 2 / 2;
        L = qa.legendScaleTickDistance;
        var ub = qa.legendSliderWidth,
            Cb = qa.legendSliderHeight;
        y = Oa / 2;
        la = Ja / 2;
        var ib = ub /
            2,
            Za = Cb / 2,
            Pb, bb, Db;
        Pa = 0;
        var Eb = ob("ABABAB", 50),
            Xa = $b("ABABAB", 70),
            qa = Ha("ABABAB", 100),
            Xa = Ha(Xa, 100),
            Eb = Ha(Eb, 100),
            Fb, Ga = {
                isFirst: !0
            },
            va = {},
            db, qb, b, d;
        if (ba && (ga = ba.colorArr) && 1 < (ta = ga.length)) {
            Ga.toolText = db = ab = ba.scaleMin;
            va.toolText = qb = ba = ba.scaleMax;
            La = ba - ab;
            Ga.snapPX = va.snapPX = 0;
            Ga.tooltipConstraint = va.tooltipConstraint = "chart";
            Ga.getLabelConfig = va.getLabelConfig = Sa;
            Ga.tooltipPos = [0, 0];
            va.tooltipPos = [0, 0];
            va.tooltipOffsetReference = Ga.tooltipOffsetReference = {};
            va.tooltipOffsetReference.left = Ga.tooltipOffsetReference.left +=
                J - 20;
            va.tooltipOffsetReference.top = Ga.tooltipOffsetReference.top += G;
            b = Da.colorBox = A.group("colorBox", g);
            if (gb) {
                Ga.tooltipPos[0] = va.tooltipPos[0] = S + J;
                Pa = 270;
                da.angle = 90;
                J = Ba - ub;
                S = Ba + Ja;
                G = Ca - Za;
                Ta = Ca + Za;
                J = X(Ba - ub) + .5;
                S = X(Ba) + .5;
                G = X(Ca - Za) + .5;
                Ta = X(Ca + Za) + .5;
                Pb = X(Ba + Ja) + .5;
                Db = X(Ca - 2) + .5;
                Ea = X(Ca + 2) + .5;
                Fa = X(Ca) + .5;
                x = Ba - ib / 2;
                bb = X(x - Za) + .5;
                x = X(x) + .5;
                y = Ca - Za / 2;
                Za = X(y + Za) + .5;
                y = X(y) + .5;
                Ia /= 2;
                ib = ["M", J, G, "L", S, G, S, Db, Pb, Fa, S, Ea, S, Ta, J, Ta, "Z", "M", bb, y, "L", x, y, "M", bb, Fa, "L", x, Fa, "M", bb, Za, "L", x, Za];
                Za = ["M", J + 1,
                    G, "L", J + 1, Ta, "M", bb, y - 1, "L", x, y - 1, "M", bb, Fa - 1, "L", x, Fa - 1, "M", bb, Za - 1, "L", x, Za - 1
                ];
                y = Ba + Ja + ha;
                Ta = X(y + L) + Bb;
                y = X(y) + Bb;
                Fa = Ba + la;
                bb = ta - 1;
                for (L = 0; L < ta; L += 1) la = ga[L], S = (la.maxvalue - ab) / La, Ea = Oa * S + Ca, ha = X(Ea) + Bb, L ? (da.ratio += F, da.color += F, da.alpha += F, jb.push("L", y, ha, Ta, ha, "M", y, ha), L === bb ? (x = Ob, ha = Ea + 2) : (x = nb, ha = Ea)) : (jb.push(y, ha, "L", Ta, ha, "M", y, ha), x = fb, ha = Ea - 2), da.ratio += 100 * (S - Va), da.color += Qa(la.code, B), da.alpha += Qa(la.alpha, 100), Va = S, la.legendItem = A.text(g).attr({
                    text: la.label,
                    x: Fa,
                    y: ha,
                    "text-anchor": x,
                    direction: ea,
                    "vertical-align": nb
                }).rotate(Pa, Fa, ha).css(cb), la.legendSymbol = A.text(g).attr({
                    text: la.scaleLabel,
                    x: Ta,
                    y: Ea,
                    "text-anchor": fb,
                    direction: ea,
                    "vertical-align": nb
                }).css(cb);
                Ga.xMin = va.xMin = 0;
                Ga.xMax = va.xMax = 0;
                Ga.yMin = va.yMin = 0;
                Ga.yMax = va.yMax = Oa;
                Ga.x = va.x = 0;
                Ga.y = 0;
                va.y = Oa;
                ea = Cb + Ja;
                ga = ub
            } else {
                Ga.tooltipPos[1] = va.tooltipPos[1] = x + G;
                J = X(Ba - ib) + .5;
                S = X(Ba + ib) + .5;
                G = X(Ca - Cb) + .5;
                Ta = X(Ca + Oa) + .5;
                Pb = X(Ba - 2) + .5;
                x = X(Ba + 2) + .5;
                bb = X(Ba) + .5;
                Db = X(Ca) + .5;
                Ea = Ca - Za / 2;
                Fa = X(Ea - Za) + .5;
                Ea = X(Ea) + .5;
                Pa = Ba - ib / 2;
                la = X(Pa +
                    ib) + .5;
                Pa = X(Pa) + .5;
                Aa /= 2;
                ib = ["M", J, G, "L", S, G, S, Db, x, Db, bb, Ta, Pb, Db, J, Db, "Z", "M", Pa, Fa, "L", Pa, Ea, "M", bb, Fa, "L", bb, Ea, "M", la, Fa, "L", la, Ea];
                Za = ["M", J, G + 1, "L", S, G + 1, "M", Pa - 1, Fa, "L", Pa - 1, Ea, "M", bb - 1, Fa, "L", bb - 1, Ea, "M", la - 1, Fa, "L", la - 1, Ea];
                ha = Ca + Oa + ha;
                Pa = X(ha + L) + Bb;
                ha = X(ha) + Bb;
                Ea = Ca + y;
                bb = ta - 1;
                for (L = 0; L < ta; L += 1) la = ga[L], S = (la.maxvalue - ab) / La, Ta = Ja * S + Ba, y = X(Ta) + Bb, L ? (da.ratio += F, da.color += F, da.alpha += F, jb.push("L", y, ha, y, Pa, "M", y, ha), L === bb ? (x = fb, y = Ta + 2) : (x = nb, y = Ta)) : (jb.push(y, ha, "L", y, Pa, "M", y, ha), x = Ob,
                    y = Ta - 2), da.ratio += 100 * (S - Va), da.color += Qa(la.code, B), da.alpha += Qa(la.alpha, 100), Va = S, la.legendItem = A.text(g).attr({
                    text: la.label,
                    x: y,
                    y: Ea,
                    "text-anchor": x,
                    direction: ea,
                    "vertical-align": nb
                }).css(cb), la.legendSymbol = A.text(g).attr({
                    text: la.scaleLabel,
                    x: Ta,
                    y: Pa,
                    "text-anchor": nb,
                    direction: ea,
                    "vertical-align": ca
                }).css(cb);
                Ga.xMin = va.xMin = 0;
                Ga.xMax = va.xMax = Ja;
                Ga.yMin = va.yMin = 0;
                Ga.yMax = va.yMax = 0;
                Ga.y = va.y = 0;
                Ga.x = 0;
                va.x = Ja;
                ea = ub;
                ga = Cb + Oa
            }
            Da.colorBox = A.rect(b).attr({
                x: Ba,
                y: Ca,
                width: Ja,
                height: Oa,
                fill: lb(s),
                stroke: tb,
                strokeWidth: xa
            });
            Ma && (Da.colorBoxEffect = A.rect(b).attr({
                x: Ba,
                y: Ca,
                width: Ia,
                height: Aa,
                fill: Zb,
                "stroke-width": 0
            }));
            Da.scale = A.path(g).attr({
                path: jb,
                stroke: Lb,
                "stroke-width": Mb
            });
            Fb = function(a, b, e, f, q) {
                var m;
                gb ? (m = b * La / Oa + ab, f = 0 < b ? f : f + b + .01) : (m = a * La / Ja + ab, e = 0 < a ? e : e + a + .01);
                a = Ab(m);
                q ? (Da.slider1.translate(e, f), Da.slider1Effect.translate(e, f), Da.slider1Tracker.toFront().translate(e, f).tooltip(a, null, null, !0), db = m) : (Da.slider2.translate(e, f), Da.slider2Effect.translate(e, f), Da.slider2Tracker.toFront().translate(e,
                    f).tooltip(a, null, null, !0), qb = m);
                V && (d = clearTimeout(d), d = setTimeout(function() {
                    h.setScaleRange && h.setScaleRange(db, qb)
                }, 100))
            };
            ta = function(a, b) {
                var d = 0,
                    f = d,
                    q, m = this.isFirst,
                    g = m ? va : Ga;
                if (gb) {
                    f = this._startY + b;
                    0 >= f && (f = 0);
                    f > Oa && (f = Oa);
                    if (m ? f > g.y : f < g.y) f = g.y;
                    ua(f - this.y) >= (this.snapPX || 0) && (q = !0)
                } else {
                    d = this._startX + a;
                    0 >= d && (d = 0);
                    d > Ja && (d = Ja);
                    if (m ? d > g.x : d < g.x) d = g.x;
                    ua(d - this.x) >= (this.snapPX || 0) && (q = !0)
                }
                q && (Fb(d, f, d - this.x, f - this.y, m), this.x = d, this.y = f)
            };
            Ma = function() {
                var a = this.isFirst;
                this._startX =
                    this.x;
                this._startY = this.y;
                this._scaleStart = db;
                this._scaleEnd = qb;
                u.raiseEvent("LegendPointerDragStart", {
                    pointerIndex: a ? 0 : 1,
                    pointers: [{
                        value: db
                    }, {
                        value: qb
                    }],
                    legendPointerHeight: Cb,
                    legendPointerWidth: ub
                }, h.logic.chartInstance)
            };
            Ia = function() {
                var a = this._scaleStart,
                    b = this._scaleEnd;
                u.raiseEvent("LegendPointerDragStop", {
                    pointerIndex: this.isFirst ? 0 : 1,
                    pointers: [{
                        value: db
                    }, {
                        value: qb
                    }],
                    legendPointerHeight: Cb,
                    legendPointerWidth: ub
                }, h.logic.chartInstance);
                a === db && b === qb || u.raiseEvent("LegendRangeUpdated", {
                    previousMinValue: a,
                    previousMaxValue: b,
                    minValue: db,
                    maxValue: qb
                }, h.logic.chartInstance);
                delete this._scaleStart;
                delete this._scaleEnd
            };
            Aa = Ab(ab);
            Da.slider1 = A.path(g).attr({
                path: ib,
                fill: qa,
                strokeWidth: 1,
                stroke: Xa
            });
            Da.slider1Effect = A.path(g).attr({
                path: Za,
                fill: "none",
                strokeWidth: 1,
                stroke: Eb
            });
            pb && (J -= .5 * (Na(30, ea) - ea), G -= .5 * (Na(40, ga) - ga), ea = Na(30, ea), ga = Na(40, ga));
            Da.slider1Tracker = A.rect(g).attr({
                ishot: !0,
                width: ea,
                height: ga,
                x: J,
                y: G,
                fill: ac,
                stroke: "none"
            }).drag(ta, Ma, Ia, Ga, Ga, Ga).tooltip(Aa, null, null, !0).css(O);
            Aa = Ab(ba);
            Da.slider2 = A.path(g).attr({
                path: ib,
                fill: qa,
                strokeWidth: 1,
                stroke: Xa
            }).translate(va.x, va.y);
            Da.slider2Effect = A.path(g).attr({
                path: Za,
                fill: "none",
                strokeWidth: 1,
                stroke: Eb
            }).translate(va.x, va.y);
            Da.slider2Tracker = A.rect(g).attr({
                ishot: !0,
                width: ea,
                height: ga,
                x: J,
                y: G,
                fill: ac,
                stroke: "none"
            }).translate(va.x, va.y).css(O).drag(ta, Ma, Ia, va, va, va).tooltip(Aa, null, null, !0)
        }
    }
}]);
FusionCharts.register("module", ["private", "modules.renderer.js-powercharts", function() {
        function gb(b, d, a) {
            var c = b.fcObj;
            G.hcLib.createChart(c, b.container, b.type, void 0, void 0, !1, !0);
            u.raiseEvent("chartUpdated", O({
                sourceEvent: a
            }, d), c, [c.id])
        }

        function jb(b, d, a, c, e, f) {
            var q = Ra.atan((d - c) / (b - a)),
                m = [];
            0 > q && (q = 2 * Ra.PI + q);
            if (c > d) {
                if (a >= b && q > Ra.PI || a < b && q > Ra.PI) q -= Ra.PI
            } else if (a >= b && q < Ra.PI && 0 !== q || a < b && q < Ra.PI) q += Ra.PI;
            "undefined" == typeof f ? (a = b + e * za(q), e = d + e * $a(q)) : (e = S(e) / 2, f = S(f) / 2, a = b + (e = b < a ? e : -e),
                e = d + e * Ra.tan(q), S(d - e) > S(f) && (e = d + (f = d < c ? f : -f), a = b + f / Ra.tan(q)));
            m.push("L", a, e, a + 10 * za(q + .79), e + 10 * $a(q + .79), "M", a + 10 * za(q - .79), e + 10 * $a(q - .79), "L", a, e);
            return m
        }

        function tb(b, d) {
            var a;
            d._origAttr || (d._origAttr = {});
            for (a in b) ac.test(a) || (d._origAttr[a] = b[a]);
            return d._origAttr
        }
        var G = this,
            u = G.hcLib,
            ga = u.Raphael,
            Xa = G.window,
            Ua = Xa.document,
            B = u.BLANKSTRING,
            Zb = u.createTrendLine,
            ab = u.parseTooltext,
            h = u.pluck,
            A = u.getValidValue,
            g = u.pluckNumber,
            ea = u.getFirstValue,
            Lb = u.getDefinedColor,
            F = u.parseUnsafeString,
            na = u.FC_CONFIG_STRING,
            O = u.extend2,
            Ha = u.getDashStyle,
            ca = u.toRaphaelColor,
            nb = u.toPrecision,
            fb = u.hasSVG,
            Ob = u.createContextMenu,
            $b = u.isIE,
            ob = u.regex.dropHash,
            Qa = u.HASHSTRING,
            cb = function(b, d) {
                var a;
                b || (b = {});
                for (a in d) b[a] = d[a];
                return b
            },
            lb = u.each,
            pb = u.addEvent,
            X = u.removeEvent,
            Na = u.getTouchEvent,
            ba = function(b) {
                return void 0 !== b && null !== b
            },
            ua = "rgba(192,192,192," + ($b ? .002 : 1E-6) + ")",
            ta = u.TOUCH_THRESHOLD_PIXELS,
            Sa = u.CLICK_THRESHOLD_PIXELS,
            Ab = 8 === Xa.document.documentMode ? "visible" : "",
            ac = /^_/,
            Gb = u.BGRATIOSTRING,
            Ra = Math,
            $a = Ra.sin,
            za = Ra.cos,
            oa = Ra.round,
            J = Ra.min,
            Ya = Ra.max,
            S = Ra.abs,
            x = Ra.PI,
            hc = Ra.ceil,
            Yb = Ra.floor,
            uc = Ra.sqrt,
            L = Ra.pow,
            ic = x / 180,
            la = 2 * x,
            qa = u.hasTouch,
            bc = qa ? ta : Sa,
            cc = u.graphics.getColumnColor,
            V = u.getFirstColor,
            Da = u.setLineHeight,
            Ac = u.pluckFontSize,
            Ta = u.pluckColor,
            Ea = u.getFirstAlpha,
            jc = u.graphics.getDarkColor,
            La = u.graphics.getLightColor,
            y = u.graphics.convertColor,
            ha = u.COLOR_TRANSPARENT,
            Va = u.POSITION_CENTER,
            Ma = u.POSITION_TOP,
            Ia = u.POSITION_BOTTOM,
            Aa = u.POSITION_RIGHT,
            Ja = u.POSITION_LEFT,
            Oa = u.bindSelectionEvent,
            s = u.chartAPI,
            da = u.graphics.mapSymbolName,
            Sa = s.singleseries,
            Ba = u.COMMASTRING,
            Ca = u.ZEROSTRING,
            Fa = u.HUNDREDSTRING,
            Pa = u.COMMASPACE,
            dc = u.getMouseCoordinate,
            ta = !/fusioncharts\.com$/i.test(Xa.location.hostname),
            xa = u.plotEventHandler,
            Zc = G.xssEncode,
            kc = u.SHAPE_RECT,
            Mb = u.deltend,
            x = u.graphics,
            Bb = x.parseColor,
            ub = x.getValidColor,
            Cb = u.placeHorizontalAxis,
            ib = u.placeVerticalAxis,
            Za = u.stepYAxisValues,
            Pb = u.adjustHorizontalCanvasMargin,
            bb = u.adjustVerticalCanvasMargin,
            Db = u.getDataParser,
            Eb = {
                pageX: 0,
                pageY: 0
            },
            Fb, Ga, va,
            db = function() {
                this.data("move", !1);
                clearTimeout(this._longpressactive);
                delete this._longpressactive
            },
            qb = u.createElement;
        u.eventList.chartupdated = "FC_ChartUpdated";
        u.eventList.dataposted = "FC_DataPosted";
        u.eventList.dataposterror = "FC_DataPostError";
        u.eventList.datarestored = "FC_DataRestored";
        G.addEventListener("rendered", function(b) {
            b = b.sender;
            var d = b.__state,
                a = b.jsVars && b.jsVars.instanceAPI;
            !d.listenersAdded && a && "function" === typeof a.getCollatedData && (b.addEventListener(["chartupdated", "dataupdated",
                "rendered"
            ], function(a) {
                delete a.sender.__state.hasStaleData
            }), d.listenersAdded = !0)
        });
        s("spline", {
            friendlyName: "Spline Chart",
            standaloneInit: !0,
            creditLabel: ta,
            defaultSeriesType: "spline",
            rendererId: "spline"
        }, s.linebase);
        s("splinearea", {
            friendlyName: "Spline Area Chart",
            standaloneInit: !0,
            creditLabel: ta,
            defaultSeriesType: "areaspline",
            anchorAlpha: "100",
            rendererId: "spline"
        }, s.area2dbase);
        s("msspline", {
                friendlyName: "Multi-series Spline Chart",
                standaloneInit: !0,
                creditLabel: ta,
                defaultSeriesType: "spline",
                rendererId: "spline"
            },
            s.mslinebase);
        s("mssplinedy", {
            friendlyName: "Multi-series Dual Y-Axis Spline Chart",
            standaloneInit: !0,
            creditLabel: ta,
            isDual: !0,
            series: s.mscombibase.series,
            secondarySeriesType: "spline",
            secondarySeriesFilter: {
                spline: !0
            },
            defaultSeriesFilter: {
                spline: !0
            }
        }, s.msspline);
        s("mssplinearea", {
            friendlyName: "Multi-series Spline Area Chart",
            standaloneInit: !0,
            creditLabel: ta,
            defaultSeriesType: "areaspline",
            rendererId: "spline"
        }, s.msareabase);
        s("msstepline", {
            friendlyName: "Multi-series Step Line Chart",
            standaloneInit: !0,
            creditLabel: ta,
            defaultSeriesType: "line",
            rendererId: "cartesian",
            stepLine: !0
        }, s.mslinebase);
        s("inversemsline", {
            friendlyName: "Inverted Y-Axis Multi-series Line Chart",
            standaloneInit: !0,
            creditLabel: ta,
            inversed: !0,
            rendererId: "cartesian"
        }, s.mslinebase);
        s("inversemsarea", {
            friendlyName: "Inverted Y-Axis Multi-series Area Chart",
            standaloneInit: !0,
            creditLabel: ta,
            inversed: !0,
            rendererId: "cartesian"
        }, s.msareabase);
        s("inversemscolumn2d", {
            friendlyName: "Inverted Y-Axis Multi-series Column Chart",
            standaloneInit: !0,
            creditLabel: ta,
            inversed: !0,
            rendererId: "cartesian"
        }, s.mscolumn2dbase);
        s("logmsline", {
            friendlyName: "Multi-series Log Line Chart",
            standaloneInit: !0,
            isValueAbs: !0,
            isLog: !0,
            configureAxis: s.logbase.configureAxis,
            pointValueWatcher: s.logbase.pointValueWatcher,
            getLogAxisLimits: s.logbase.getLogAxisLimits,
            creditLabel: ta,
            rendererId: "cartesian"
        }, s.mslinebase);
        s("logmscolumn2d", {
            friendlyName: "Multi-series Log Column Chart",
            standaloneInit: !0,
            isLog: !0,
            isValueAbs: !0,
            configureAxis: s.logbase.configureAxis,
            pointValueWatcher: s.logbase.pointValueWatcher,
            getLogAxisLimits: s.logbase.getLogAxisLimits,
            creditLabel: ta,
            rendererId: "cartesian"
        }, s.mscolumn2dbase);
        s("logstackedcolumn2d", {
            friendlyName: "Stacked Log Column Chart",
            standaloneInit: !0,
            creditLabel: ta,
            isStacked: !0
        }, s.logmscolumn2d);
        s("errorbar2d", {
            friendlyName: "Error Bar Chart",
            standaloneInit: !0,
            creditLabel: ta,
            showValues: 0,
            rendererId: "cartesian",
            isErrorChart: !0,
            fireGroupEvent: !0,
            chart: function() {
                var b = this.base.chart.apply(this, arguments),
                    d = this.drawErrorValue;
                b.callbacks || (b.callbacks = []);
                b.callbacks.push(function() {
                    for (var a =
                            this.elements.plots, b = this.dataset || this.options.series, e = a && a.length; e--;) b[e] && d.call(this, a[e], b[e])
                });
                return b
            },
            point: function(b, d, a, c, e, f, q, m, v) {
                b = g(c.ignoreemptydatasets, 0);
                var l = !1,
                    p = !g(c.halferrorbar, 1),
                    k = e[na],
                    n = h(this.isValueAbs, k.isValueAbs, !1),
                    r = g(a.showvalues, k.showValues),
                    t = g(d.yAxis, 0),
                    z = g(c.use3dlighting, 1),
                    D = e[na].numberFormatter,
                    w = this.colorManager,
                    C = g(c.useplotgradientcolor, 1) ? Lb(c.plotgradientcolor, w.getColor("plotGradientColor")) : B,
                    H = h(a.alpha, c.plotfillalpha, "100"),
                    $ = Ea(h(a.errorbaralpha,
                        c.errorbaralpha, H)),
                    ja = g(a.dashed, c.plotborderdashed, 0),
                    ya = g(a.dashlen, c.plotborderdashlen, 5),
                    E = g(a.dashgap, c.plotborderdashgap, 4),
                    Ka = h(d.type, this.defaultSeriesType),
                    I = e.plotOptions[Ka] && e.plotOptions[Ka].stacking,
                    Ka = w.getPlotColor(),
                    ka, eb, ra, u, P, s, T, M, K, Z, U, N, Q, aa, Y, W;
                this.errorBarShadow = g(c.errorbarshadow);
                d.errorBar2D = !0;
                d.name = A(a.seriesname);
                I || (d.columnPosition = g(v, m, q));
                if (0 === g(a.includeinlegend) || 0 === H || void 0 === d.name) d.showInLegend = !1;
                d.errorBarWidthPercent = g(a.errorbarwidthpercent, c.errorbarwidthpercent,
                    70);
                d.errorBarColor = y(V(h(a.errorbarcolor, c.errorbarcolor, "AAAAAA")), $);
                d.errorBarThickness = g(a.errorbarthickness, c.errorbarthickness, 1);
                d.color = h(a.color, Ka).split(",")[0].replace(/^#?/g, "#");
                if (q = a.data)
                    for (N = h(c.plotborderthickness, "1"), I = e.chart.useRoundEdges, v = this.isBar, m = /3d$/.test(e.chart.defaultSeriesType), aa = h(c.plotbordercolor, w.getColor("plotBorderColor")).split(",")[0], Y = "0" == c.showplotborder ? "0" : h(c.plotborderalpha, "100"), Y = m ? c.showplotborder ? Y : "0" : Y, aa = m ? h(c.plotbordercolor, "#FFFFFF") :
                        aa, w = 0; w < f; w += 1)(eb = q[w]) ? (M = D.getCleanValue(eb.value, n), K = D.getCleanValue(eb.errorvalue, n), null === M ? d.data.push({
                        y: null
                    }) : (l = !0, T = k.oriCatTmp[w], u = h(eb.color, a.color, Ka), P = Ea(h(eb.alpha, H)) + B, ka = h(eb.ratio, a.ratio, c.plotfillratio), ra = h(360 - c.plotfillangle, 90), 0 > M && (ra = 360 - ra), Z = {
                            opacity: P / 100
                        }, Q = J(P, Ea(Y)) + B, s = cc(u + "," + C, P, ka, ra, I, aa, Q, v, m), U = {
                            opacity: $ / 250
                        }, W = this.getPointStub(eb, M, T, e, a, r, t, K), T = [], T.push({
                            errorValue: K,
                            toolText: W._errortoolText,
                            shadow: U
                        }), p && T.push({
                            errorValue: -K,
                            toolText: W._errortoolText,
                            shadow: U
                        }), ka = this.pointHoverOptions(eb, d, {
                            plotType: "column",
                            is3d: m,
                            isBar: v,
                            use3DLighting: z,
                            isRoundEdged: I,
                            color: u,
                            gradientColor: C,
                            alpha: P,
                            ratio: ka,
                            angle: ra,
                            borderWidth: N,
                            borderColor: aa,
                            borderAlpha: Q,
                            borderDashed: ja,
                            borderDashGap: E,
                            borderDashLen: ya,
                            shadow: Z
                        }), d.data.push(O(W, {
                            y: M,
                            shadow: Z,
                            errorValue: T,
                            color: s[0],
                            borderColor: s[1],
                            borderWidth: N,
                            use3DLighting: z,
                            dashStyle: g(eb.dashed, ja) ? Ha(h(eb.dashlen, ya), h(eb.dashgap, E), N) : "none",
                            hoverEffects: ka.enabled && ka.options,
                            rolloverProperties: ka.enabled && ka.rolloverOptions
                        })),
                        this.pointValueWatcher(e, M, K))) : d.data.push({
                        y: null
                    });
                b && !l && (d.showInLegend = !1);
                return d
            },
            pointValueWatcher: function(b, d, a) {
                var c = b[na];
                null !== d && (a ? (b = d + a, d -= a) : b = d, c[0] || (c[0] = {}), a = c[0], a.max = a.max > b ? a.max : b, a.min = a.min < b ? a.min : b, a.max = a.max > d ? a.max : d, a.min = a.min < d ? a.min : d)
            },
            drawErrorValue: function(b, d) {
                var a = this,
                    c = a.options,
                    e = c.plotOptions.series,
                    f = c[na],
                    f = a.smartLabel || f.smartLabel,
                    q = a.paper,
                    m = a.layers,
                    v = a.xAxis[0],
                    l = a.yAxis[0],
                    p = isNaN(+e.animation) && e.animation.duration || 1E3 * e.animation,
                    k =
                    m.dataset = m.dataset || q.group("dataset-orphan"),
                    n = b.errorGroup = q.group("errorBar").insertAfter(b.lineLayer || k.column || k),
                    h = m.errorTracker || (m.errorTracker = q.group("hot-error", m.tracker || k).toBack()),
                    t = k.errorValueGroup || (k.errorValueGroup = q.group("errorValues")),
                    z = d.errorBar2D,
                    D = d.data || [],
                    w = D.length,
                    C = b.items,
                    H = !1 !== (c.tooltip || {}).enabled,
                    $, ja, ya, E, Ka = b.graphics = b.graphics || [],
                    I = !1 === d.visible ? "hidden" : "visible",
                    ka = c.chart,
                    eb = ka.textDirection,
                    ra = ka.valuePadding || 0,
                    ka = 1 == ka.rotateValues ? 270 : void 0,
                    u = d.columnPosition || 0,
                    P = a.definition.chart,
                    s = v.getAxisPosition(0),
                    T = v.getAxisPosition(1) - s,
                    M = e.groupPadding,
                    K = e.maxColWidth,
                    s = d.numColumns || 1,
                    T = (1 - .01 * (P && P.plotspacepercent)) * T || J(T * (1 - 2 * M), K * s),
                    P = T / s,
                    u = u * P - T / 2,
                    s = a.logic,
                    T = !s.avoidCrispError,
                    M = a.canvasHeight + a.canvasTop,
                    y = m.shadows || (m.shadows = q.group("shadows", k).toBack()),
                    m = {},
                    K = c.plotOptions.series.dataLabels.style,
                    U = a.chartWidth,
                    N = a.chartHeight,
                    Q = {
                        fontFamily: K.fontFamily,
                        fontSize: K.fontSize,
                        lineHeight: K.lineHeight,
                        fontWeight: K.fontWeight,
                        fontStyle: K.fontStyle
                    },
                    aa, Y, W, x, F, A, fa, O, G, ia, ma, S, pa, L, wa, ca, V, vb, sa, mb, rb = function(b) {
                        xa.call(this, a, b)
                    },
                    X = function(b) {
                        xa.call(this, a, b, "DataPlotRollOver")
                    },
                    da = function(b) {
                        xa.call(this, a, b, "DataPlotRollOut")
                    },
                    ea = function(b) {
                        return function() {
                            void 0 !== b && a.linkClickFN.call({
                                link: b
                            }, a)
                        }
                    },
                    ha = function() {
                        n.show();
                        t.attr({
                            transform: "...t" + -U + "," + -N
                        });
                        y.show()
                    };
                if (0 < w) {
                    for (; w--;)
                        if ($ = D[w], c = g($.errorStartValue, $.y), S = $.errorValue, k = $.link, void 0 !== c && S && (wa = S.length)) {
                            $ = g($.x, w);
                            E = l.getAxisPosition(c);
                            ya = v.getAxisPosition($);
                            z && (u && (ya += u), P && (ya += P / 2));
                            ia = C[w] || (C[w] = {});
                            ia.errorBars = ia.errorBars || [];
                            ia.errorValues = ia.errorValues || [];
                            ia.trackerBars = ia.trackerBars || [];
                            for (Y = (aa = ia.tracker || ia.graphic) && aa.data("groupId"); wa--;) sa = ca = mb = null, pa = S[wa], G = pa.errorStartValue, ja = pa.tooltext || pa.toolText, F = isNaN(G) ? E : l.getAxisPosition(G), W = pa.displayValue, L = pa.errorValue, pa && ba(L) && (x = g(pa.isHorizontal, 0), vb = g(pa.errorBarThickness, d.errorBarThickness, 1), ca = g(P * d.errorBarWidthPercent / 100, pa.errorWidth, x ? d.hErrorBarWidth : d.vErrorBarWidth,
                                d.errorBarWidth), V = ca / 2, ca = pa.errorBarColor || d.errorBarColor, ba(W) && W !== B && (mb = q.text(t).attr({
                                text: W,
                                fill: K.color,
                                direction: eb,
                                "text-bound": [K.backgroundColor, K.borderColor, K.borderThickness, K.borderPadding, K.borderRadius, K.borderDash]
                            }).css(Q), f.setStyle(Q), m = f.getOriSize(W)), x ? (W = ma = G = v.getAxisPosition($ + L), x = ya, T && (W = oa(F) + vb % 2 / 2, x = oa(ma) + vb % 2 / 2), F = ["M", ya, W, "H", x, "M", x, W - V, "V", W + V]) : (W = ma = G = l.getAxisPosition((ba(G) ? G : c) + L), x = ya, T && (W = oa(ma) + vb % 2 / 2, x = oa(ya) + vb % 2 / 2), O = .5 * (ka ? m.width : m.height),
                                A = ma + .5 * vb + ra + O, fa = ma - .5 * vb - ra - O, F > ma ? (G = fa, fa - a.canvasTop < O && (G = A)) : (G = A, M - A < O && (G = fa)), F = ["M", x, F, "V", W, "M", x - V, W, "H", x + V]), ca = q.path(F, n).attr({
                                stroke: ca,
                                ishot: !H,
                                "stroke-width": vb,
                                cursor: k ? "pointer" : "",
                                "stroke-linecap": "round",
                                visibility: I
                            }).shadow(g(s.errorBarShadow, e.shadow) && 0 < vb && pa.shadow, y), (k || H) && vb < bc && (sa = q.path(F, h).attr({
                                stroke: ua,
                                "stroke-width": bc,
                                cursor: k ? "pointer" : "",
                                ishot: !!k,
                                visibility: I
                            })), sa = sa || ca, sa.data("eventArgs", aa && aa.data("eventArgs") || {
                                link: k,
                                toolText: ja,
                                displayValue: pa.displayValue,
                                value: L
                            }), sa.click(rb).data("groupId", Y).hover(X, da).tooltip(ja), (k || H) && sa.click(ea(k)), mb && (mb.attr({
                                x: ya,
                                y: G,
                                title: pa.originalText || "",
                                visibility: I
                            }).css(Q), ka && mb.attr("transform", "T0,0,R" + ka)), ca && (Ka.push(ca), ia.errorBars.push(ca)), mb && (Ka.push(mb), ia.errorValues.push(mb)), sa && sa !== ca && (Ka.push(sa), ia.trackerBars.push(sa)));
                            p && (n.hide(), t.attr({
                                transform: "...t" + U + "," + N
                            }), y.hide(), setTimeout(ha, p))
                        }
                    b.visible = !1 !== d.visible
                }
            }
        }, s.mscolumn2dbase);
        s("errorline", {
            friendlyName: "Error Line Chart",
            standaloneInit: !0,
            creditLabel: ta,
            chart: s.errorbar2d.chart,
            drawErrorValue: s.errorbar2d.drawErrorValue,
            useErrorGroup: !0,
            rendererId: "cartesian",
            isErrorChart: !0,
            fireGroupEvent: !0,
            canvasPaddingModifiers: ["anchor", "errorbar"],
            point: function(b, d, a, c, e, f) {
                b = g(c.ignoreemptydatasets, 0);
                var q = !1,
                    m = !g(c.halferrorbar, 1),
                    v = e[na],
                    l = h(this.isValueAbs, v.isValueAbs, !1),
                    p = g(a.showvalues, v.showValues),
                    k = g(d.yAxis, 0),
                    n = this.numberFormatter,
                    r = this.colorManager,
                    t = V(h(a.color, c.linecolor, r.getPlotColor())),
                    z = e.chart,
                    D = g(a.alpha, c.linealpha,
                        "100"),
                    w = g(a.errorbaralpha, c.errorbaralpha, D),
                    C = g(a.linethickness, c.linethickness, 2),
                    H = Boolean(g(a.dashed, c.linedashed, 0)),
                    $ = g(a.linedashlen, c.linedashlen, 5),
                    ja = g(a.linedashgap, c.linedashgap, 4),
                    ya, E, Ka, I, ka, eb, ra, u, P, x, T, M, K, Z, U, N, Q, F, Y, W, G, S, ca, fa, L, ba, ia, ma, X, pa;
                this.errorBarShadow = g(c.errorbarshadow);
                d.name = A(a.seriesname);
                d.color = {
                    FCcolor: {
                        color: t,
                        alpha: D
                    }
                };
                d.lineWidth = C;
                ra = g(a.drawanchors, a.showanchors, c.drawanchors, c.showanchors);
                Y = g(a.anchorsides, c.anchorsides, 0);
                W = g(a.anchorradius, c.anchorradius,
                    3);
                G = V(h(a.anchorbordercolor, c.anchorbordercolor, t));
                S = g(a.anchorborderthickness, c.anchorborderthickness, 1);
                ca = V(h(a.anchorbgcolor, c.anchorbgcolor, r.getColor("anchorBgColor")));
                fa = h(a.anchoralpha, c.anchoralpha, "100");
                L = h(a.anchorbgalpha, c.anchorbgalpha, fa);
                ba = g(a.anchorstartangle, c.anchorstartangle, 90);
                r = d.anchorShadow = g(c.anchorshadow, 0);
                d.errorBarWidth = g(c.errorbarwidth, a.errorbarwidth, 5);
                d.errorBarColor = y(V(h(a.errorbarcolor, c.errorbarcolor, "AAAAAA")), w);
                d.errorBarThickness = J(C, g(a.errorbarthickness,
                    c.errorbarthickness, 1));
                if (0 === g(a.includeinlegend) || void 0 === d.name || 0 === D && 1 !== ra) d.showInLegend = !1;
                d.marker = {
                    fillColor: {
                        FCcolor: {
                            color: ca,
                            alpha: L * fa / 100 + B
                        }
                    },
                    lineColor: {
                        FCcolor: {
                            color: G,
                            alpha: fa + B
                        }
                    },
                    lineWidth: S,
                    radius: W,
                    symbol: da(Y),
                    startAngle: ba
                };
                if (c = a.data)
                    for (I = 0; I < f; I += 1)(U = c[I]) ? (E = n.getCleanValue(U.value, l), Ka = n.getCleanValue(U.errorvalue, l), null === E ? d.data.push({
                        y: null
                    }) : (q = !0, Z = g(U.anchorsides, Y), K = g(U.anchorradius, W), T = V(h(U.anchorbordercolor, G)), M = g(U.anchorborderthickness, S), x = V(h(U.anchorbgcolor,
                        ca)), u = h(U.anchoralpha, fa), P = h(U.anchorbgalpha, L), ka = V(h(U.color, t)), eb = h(U.alpha, D), ma = g(U.dashed, H) ? Ha($, ja, C) : "none", N = {
                        opacity: eb / 100
                    }, ia = void 0 === ra ? 0 !== eb : !!ra, ya = v.oriCatTmp[I], pa = this.getPointStub(U, E, ya, e, a, p, k, Ka), X = [], X.push({
                        errorValue: Ka,
                        toolText: pa._errortoolText,
                        shadow: {
                            opacity: w / 250
                        }
                    }), m && X.push({
                        errorValue: null === Ka ? null : -Ka,
                        toolText: pa._errortoolText,
                        shadow: {
                            opacity: w / 250
                        }
                    }), Q = h(U.anchorstartangle, ba), F = Boolean(g(U.anchorshadow, r, 0)), ya = this.pointHoverOptions(U, d, {
                        plotType: "anchor",
                        anchorBgColor: x,
                        anchorAlpha: u,
                        anchorBgAlpha: P,
                        anchorAngle: Q,
                        anchorBorderThickness: M,
                        anchorBorderColor: T,
                        anchorBorderAlpha: u,
                        anchorSides: Z,
                        anchorRadius: K,
                        shadow: N
                    }), d.data.push(O(pa, {
                        y: E,
                        shadow: N,
                        dashStyle: ma,
                        errorValue: X,
                        valuePosition: h(U.valueposition, z.valuePosition),
                        color: {
                            FCcolor: {
                                color: ka,
                                alpha: eb
                            }
                        },
                        marker: {
                            enabled: ia,
                            shadow: F && {
                                opacity: u / 100
                            },
                            fillColor: {
                                FCcolor: {
                                    color: x,
                                    alpha: P * u / 100 + B
                                }
                            },
                            lineColor: {
                                FCcolor: {
                                    color: T,
                                    alpha: u
                                }
                            },
                            lineWidth: M,
                            radius: K,
                            symbol: da(Z),
                            startAngle: Q
                        },
                        hoverEffects: ya.enabled &&
                            ya.options,
                        rolloverProperties: ya.enabled && ya.rolloverOptions
                    })), s.errorbar2d.pointValueWatcher(e, E, Ka))) : d.data.push({
                        y: null
                    });
                b && !q && (d.showInLegend = !1);
                return d
            }
        }, s.mslinebase);
        s("errorscatter", {
            friendlyName: "Error Scatter Chart",
            isXY: !0,
            standaloneInit: !0,
            creditLabel: ta,
            chart: s.errorbar2d.chart,
            drawErrorValue: s.errorbar2d.drawErrorValue,
            defaultZeroPlaneHighlighted: !1,
            useErrorGroup: !0,
            rendererId: "cartesian",
            isErrorChart: !0,
            fireGroupEvent: !0,
            point: function(b, d, a, c, e, f, q) {
                b = g(c.ignoreemptydatasets,
                    0);
                f = !1;
                var m = g(a.drawline, 0),
                    v = g(a.drawprogressioncurve, 0),
                    l, p, k = g(a.showvalues, e[na].showValues),
                    n = this.numberFormatter,
                    r = g(a.showregressionline, c.showregressionline, 0),
                    t = h(c.errorbarcolor, "AAAAAA"),
                    z = h(c.errorbaralpha, "100"),
                    D = g(c.errorbarthickness, 1);
                p = g(c.errorbarwidth, 5);
                var w = g(c.halfverticalerrorbar, 1),
                    C = g(a.verticalerrorbaralpha, a.errorbaralpha, c.verticalerrorbaralpha, z),
                    H = y(h(a.verticalerrorbarcolor, a.errorbarcolor, c.verticalerrorbarcolor, t), C),
                    $ = g(a.verticalerrorbarthickness, a.errorbarthickness,
                        c.verticalerrorbarthickness, D),
                    ja = g(c.halfhorizontalerrorbar, 1),
                    z = h(a.horizontalerrorbaralpha, a.errorbaralpha, c.horizontalerrorbaralpha, z),
                    t = y(h(a.horizontalerrorbarcolor, a.errorbarcolor, c.horizontalerrorbarcolor, t), z),
                    D = g(a.horizontalerrorbarthickness, a.errorbarthickness, c.horizontalerrorbarthickness, D),
                    ya = g(a.usehorizontalerrorbar, c.usehorizontalerrorbar, 0),
                    E = g(a.useverticalerrorbar, c.useverticalerrorbar, 1),
                    Ka = {
                        sumX: 0,
                        sumY: 0,
                        sumXY: 0,
                        sumXsqure: 0,
                        sumYsqure: 0,
                        xValues: [],
                        yValues: []
                    };
                l = this.colorManager;
                var I = l.getPlotColor(),
                    ka, u, ra, s, P, x, T, M, K, F, U, N, Q, aa, Y, W, J, G, O, fa, S, ca, ia, ma;
                this.errorBarShadow = g(c.errorbarshadow);
                d.zIndex = 1;
                d.name = A(a.seriesname);
                if (0 === g(a.includeinlegend) || void 0 === d.name) d.showInLegend = !1;
                d.vErrorBarWidth = g(a.verticalerrorbarwidth, a.errorbarwidth, c.verticalerrorbarwidth, p);
                d.hErrorBarWidth = g(a.horizontalerrorbarwidth, a.errorbarwidth, c.horizontalerrorbarwidth, p);
                if (m || v) v && (d.type = "spline"), u = V(h(a.color, I)), m = h(a.alpha, Fa), v = g(a.linethickness, c.linethickness, 2), p = Boolean(g(a.linedashed,
                    a.dashed, c.linedashed, 0)), ra = g(a.linedashlen, c.linedashlen, 5), s = g(a.linedashgap, c.linedashgap, 4), d.color = y(h(a.linecolor, c.linecolor, u), g(a.linealpha, c.linealpha, m)), d.lineWidth = v, d.dashStyle = p ? Ha(ra, s, v) : "none";
                m = Boolean(g(a.drawanchors, a.showanchors, c.drawanchors, c.showanchors, 1));
                q = g(a.anchorsides, c.anchorsides, q + 3);
                v = g(a.anchorradius, c.anchorradius, 3);
                u = V(h(a.anchorbordercolor, a.color, c.anchorbordercolor, u, I));
                I = g(a.anchorborderthickness, c.anchorborderthickness, 1);
                s = V(h(a.anchorbgcolor, c.anchorbgcolor,
                    l.getColor("anchorBgColor")));
                x = h(a.anchoralpha, a.alpha, c.anchoralpha, "100");
                T = h(a.anchorbgalpha, c.anchorbgalpha, x);
                ra = h(a.anchorstartangle, c.anchorstartangle);
                d.anchorShadow = g(c.anchorshadow, 0);
                d.marker = {
                    fillColor: this.getPointColor(s, "100"),
                    lineColor: {
                        FCcolor: {
                            color: u,
                            alpha: x + B
                        }
                    },
                    lineWidth: I,
                    radius: v,
                    symbol: da(q)
                };
                if (l = a.data) {
                    p = l.length;
                    r && (d.events = {
                        hide: this.hideRLine,
                        show: this.showRLine
                    }, S = g(a.showyonx, c.showyonx, 1), ca = V(h(a.regressionlinecolor, c.regressionlinecolor, u)), ia = g(a.regressionlinethickness,
                        c.regressionlinethickness, I), c = Ea(g(a.regressionlinealpha, c.regressionlinealpha, x)), ca = y(ca, c));
                    for (ka = 0; ka < p; ka += 1)(P = l[ka]) ? (c = n.getCleanValue(P.y), Y = n.getCleanValue(P.x), n.getCleanValue(P.errorvalue), W = n.getCleanValue(h(P.horizontalerrorvalue, P.errorvalue)), J = n.getCleanValue(h(P.verticalerrorvalue, P.errorvalue)), null === c ? d.data.push({
                        y: null,
                        x: Y
                    }) : (f = !0, G = this.getPointStub(P, c, n.xAxis(Y), e, a, k, void 0, J, W, Y), M = g(P.anchorsides, q), K = g(P.anchorradius, v), F = V(h(P.anchorbordercolor, u)), U = g(P.anchorborderthickness,
                        I), N = V(h(P.anchorbgcolor, s)), Q = h(P.anchoralpha, P.alpha, x), aa = h(P.anchorbgalpha, T), O = Boolean(g(P.usehorizontalerrorbar, ya)), fa = Boolean(g(P.useverticalerrorbar, E)), ma = [], O && (O = G._hErrortoolText, ma.push({
                        errorValue: W,
                        toolText: O,
                        errorBarColor: t,
                        isHorizontal: 1,
                        errorBarThickness: D,
                        shadow: {
                            opacity: z / 250
                        }
                    }), ja || ma.push({
                        errorValue: -W,
                        toolText: O,
                        errorBarColor: t,
                        isHorizontal: 1,
                        errorBarThickness: D,
                        shadow: {
                            opacity: z / 250
                        }
                    })), fa && (fa = G._errortoolText, ma.push({
                        errorValue: J,
                        toolText: fa,
                        errorBarColor: H,
                        errorBarThickness: $,
                        shadow: {
                            opacity: C / 250
                        }
                    }), w || ma.push({
                        errorValue: -J,
                        toolText: fa,
                        errorBarColor: H,
                        errorBarThickness: $,
                        shadow: {
                            opacity: C / 250
                        }
                    })), fa = this.pointHoverOptions(P, d, {
                        plotType: "anchor",
                        anchorBgColor: N,
                        anchorAlpha: Q,
                        anchorBgAlpha: aa,
                        anchorAngle: ra,
                        anchorBorderThickness: U,
                        anchorBorderColor: F,
                        anchorBorderAlpha: Q,
                        anchorSides: M,
                        anchorRadius: K
                    }), d.data.push({
                        y: c,
                        x: Y,
                        errorValue: ma,
                        displayValue: G.displayValue,
                        toolText: G.toolText,
                        link: G.link,
                        marker: {
                            enabled: m,
                            shadow: void 0,
                            fillColor: {
                                FCcolor: {
                                    color: N,
                                    alpha: aa * Q / 100 +
                                        B
                                }
                            },
                            lineColor: {
                                FCcolor: {
                                    color: F,
                                    alpha: Q
                                }
                            },
                            lineWidth: U,
                            radius: K,
                            symbol: da(M),
                            startAngle: h(P.anchorstartangle, ra)
                        },
                        hoverEffects: fa.enabled && fa.options,
                        rolloverProperties: fa.enabled && fa.rolloverOptions
                    }), this.pointValueWatcher(e, w ? c : c - J, ja ? Y : Y - W, r && Ka), this.pointValueWatcher(e, c + J, Y + W, r && Ka))) : d.data.push({
                        y: null
                    });
                    r && (a = this.getRegressionLineSeries(Ka, S, p), this.pointValueWatcher(e, a[0].y, a[0].x), this.pointValueWatcher(e, a[1].y, a[1].x), e = {
                        type: "line",
                        color: ca,
                        showInLegend: !1,
                        lineWidth: ia,
                        enableMouseTracking: !1,
                        marker: {
                            enabled: !1
                        },
                        data: a,
                        zIndex: 0
                    }, d = [d, e])
                }
                b && !f && (d.showInLegend = !1);
                return d
            }
        }, s.scatterbase);
        s("waterfall2d", {
            friendlyName: "Waterfall Chart",
            standaloneInit: !0,
            isWaterfall: !0,
            creditLabel: ta,
            point: function(b, d, a, c, e) {
                var f, q, m, v, l, p, k, n, r, t, z, D, w, C, H;
                b = h(c.connectorthickness, 1);
                var $ = {
                        step: !0,
                        type: "line",
                        enableMouseTracking: !1,
                        data: [],
                        dataLabels: {
                            enabled: !1
                        },
                        marker: {
                            enabled: !1
                        },
                        dashStyle: "1" === c.connectordashed ? Ha(g(c.connectordashlen, 2), g(c.connectordashgap, 2), b) : "none",
                        drawVerticalJoins: !1,
                        useForwardSteps: !0,
                        color: y(h(c.connectorcolor, "000000"), h(c.connectoralpha, 100)),
                        lineWidth: b
                    },
                    ja = this.colorManager,
                    ya = a.length,
                    E = e[na],
                    Ka = E.axisGridManager,
                    I = e.xAxis,
                    u = E.x,
                    s = /3d$/.test(e.chart.defaultSeriesType),
                    ra = this.isBar,
                    x = "1" === h(c.showplotborder, s ? "0" : "1") ? s ? 1 : g(c.plotborderthickness, 1) : 0,
                    P = e.chart.useRoundEdges,
                    J = g(c.plotborderalpha, c.plotfillalpha, 100) + B,
                    T = h(c.plotbordercolor, ja.getColor("plotBorderColor").split(",")[0]),
                    M = g(c.useplotgradientcolor, 1) ? Lb(c.plotgradientcolor, ja.getColor("plotGradientColor")) :
                    B,
                    K = g(c.plotborderdashed, 0),
                    Z = g(c.plotborderdashlen, 6),
                    U = g(c.plotborderdashgap, 3),
                    N = 0,
                    Q = Boolean(g(c.use3dlighting, 1)),
                    aa = 0,
                    Y = 0,
                    W = e[na].numberFormatter,
                    G, O = 0,
                    S, fa = g(c.showsumatend, 1);
                for (f = 0; f < ya; f += 1) n = a[f], b = W.getCleanValue(n.value), m = g(n.issum, 0), n.vline || m || (O += b, n._value = b);
                G = W.dataLabels(O);
                fa && (fa = !0, ya += 1, S = {
                    label: ea(c.sumlabel, "Total"),
                    _value: O,
                    value: O,
                    issum: 1,
                    cumulative: 1
                });
                for (q = f = 0; f < ya; f += 1) n = a[f], !n && fa && (n = S), n.vline ? Ka.addVline(I, n, N, e) : (b = n._value, delete n._value, m = g(n.issum, 0), k =
                    g(n.cumulative, 1), m ? (b = k ? aa : aa === Y ? aa : aa - Y, Y = aa, $.data.push({
                        y: null,
                        x: q - .5
                    })) : aa += b, m = g(n.showlabel, c.showlabels, 1), m = F(m ? ea(n.label, n.name) : B), Ka.addXaxisCat(I, N, N, m, n, {}, c), N += 1, 0 < b ? (v = h(n.color, c.positivecolor, ja.getPlotColor()), d.hoverEffects && (d.hoverEffects.color = h(n.positivehovercolor, c.positivehovercolor, c.plotfillhovercolor))) : (v = h(n.color, c.negativecolor, ja.getPlotColor()), d.hoverEffects && (d.hoverEffects.color = h(n.negativehovercolor, c.negativehovercolor, c.plotfillhovercolor))), l = h(n.alpha,
                        c.plotfillalpha, "100"), p = h(n.ratio, c.plotfillratio), r = h(360 - c.plotfillangle, 90), 0 > b && (r = 360 - r), D = h(n.alpha, J), w = g(n.dashed, K), C = h(n.dashgap, U), H = h(n.dashlen, Z), t = {
                        opacity: l / 100,
                        inverted: ra
                    }, k = cc(v + Ba + M.replace(/,+?$/, ""), l, p, r, P, T, h(n.alpha, J), ra, s), z = w ? Ha(H, C, x) : "none", v = this.pointHoverOptions(n, d, {
                        plotType: "column",
                        is3d: s,
                        isBar: ra,
                        use3DLighting: Q,
                        isRoundEdged: P,
                        color: v,
                        gradientColor: M,
                        alpha: l,
                        ratio: p,
                        angle: r,
                        borderWidth: x,
                        borderColor: T,
                        borderAlpha: D,
                        borderDashed: w,
                        borderDashGap: C,
                        borderDashLen: H,
                        shadow: t
                    }), l = A(F(n.displayvalue)), p = null === b ? b : W.dataLabels(b), r = A(F(h(n.tooltext, E.tooltext))), r = E.showTooltip ? void 0 !== r ? ab(r, [1, 2, 3, 5, 6, 7, 20, 21, 24, 25], {
                        formattedValue: p,
                        label: m,
                        yaxisName: F(c.yaxisname),
                        xaxisName: F(c.xaxisname),
                        cumulativeValue: aa,
                        cumulativeDataValue: W.dataLabels(aa),
                        sum: G,
                        unformattedSum: O
                    }, n, c) : null === p ? !1 : m !== B ? m + E.tooltipSepChar + p : p : B, l = g(n.showvalue, E.showValues) ? void 0 !== l ? l : p : B, d.data.push({
                        y: b,
                        _FCY: 0 > b ? aa - b : aa,
                        previousY: 0 > b ? aa : 0 === aa - b ? void 0 : aa - b,
                        shadow: t,
                        color: k[0],
                        borderColor: k[1],
                        borderWidth: x,
                        dashStyle: z,
                        use3DLighting: Q,
                        hoverEffects: v.enabled && v.options,
                        rolloverProperties: v.enabled && v.rolloverOptions,
                        displayValue: l,
                        categoryLabel: m,
                        toolText: r,
                        link: h(n.link)
                    }), $.data.push({
                        y: b && aa,
                        x: q
                    }), this.pointValueWatcher(e, aa), q += 1);
                u.catCount = N;
                "0" != c.showconnectors && (d = [$, d]);
                return d
            },
            defaultSeriesType: "column",
            rendererId: "cartesian"
        }, Sa);
        s("multilevelpie", {
            friendlyName: "Multi-level Pie Chart",
            standaloneInit: !0,
            defaultSeriesType: "multilevelpie",
            rendererId: "multiLevelPie",
            defaultPlotShadow: 0,
            series: function() {
                var b = this.dataObj,
                    d = this.hcJSON,
                    a = b.chart,
                    c = d.series,
                    e = {},
                    f = Boolean(g(a.usehovercolor, 1)),
                    q = y(h(a.hoverfillcolor, "FF5904"), g(a.hoverfillalpha, 100)),
                    m = parseInt(a.pieradius, 10),
                    v = 0,
                    l = !0;
                d.chart.plotBorderColor = 0;
                d.chart.plotBackgroundColor = null;
                d.plotOptions.series.dataLabels.style = d.xAxis.labels.style;
                d.plotOptions.series.dataLabels.color = d.xAxis.labels.style.color;
                d.legend.enabled = !1;
                d.plotOptions.pie.allowPointSelect = !1;
                d.plotOptions.series.borderColor = y(h(a.plotbordercolor, a.piebordercolor,
                    "FFFFFF"), "0" != a.showplotborder ? h(a.plotborderalpha, a.pieborderalpha, 100) : 0);
                d.plotOptions.series.borderWidth = g(a.pieborderthickness, a.plotborderthickness, 1);
                d.plotOptions.pie.startingAngle = 0;
                d.plotOptions.pie.size = "100%";
                e.showLabels = g(a.showlabels, 1);
                e.showValues = g(a.showvalues, 0);
                e.showValuesInTooltip = g(a.showvaluesintooltip, a.showvalues, 0);
                e.showPercentValues = g(a.showpercentvalues, a.showpercentagevalues, 0);
                e.showPercentInTooltip = g(a.showpercentintooltip, 0);
                e.toolTipSepChar = h(a.tooltipsepchar,
                    a.hovercapsepchar, Pa);
                e.labelSepChar = h(a.labelsepchar, e.toolTipSepChar);
                e.tooltext = a.plottooltext;
                f && (d.plotOptions.series.point.events = {
                    mouseOver: function() {
                        for (var a = this, b = a.chart.plots, c, d; a;) a.graphic.attr({
                            fill: q
                        }), d = a.prevPointIndex, a = a.prevSeriesIndex, a = (c = b[a]) && c.items && c.items[d]
                    },
                    mouseOut: function() {
                        for (var a = this, b = a.chart.plots, c, d; a;) a.graphic.attr({
                            fill: a.color
                        }), d = a.prevPointIndex, a = a.prevSeriesIndex, a = (c = b[a]) && c.items && c.items[d]
                    }
                });
                d.chart.plotBorderWidth = 0;
                b.category && this.addMSPieCat(b.category,
                    0, 0, 100, h(a.plotfillalpha, a.piefillalpha, 100), e, null);
                m = parseInt(a.pieradius, 10);
                v = 0;
                l = !0;
                m ? (b = 2 * m / c.length, l = !1) : b = parseInt(100 / c.length, 10);
                d.plotOptions.series.dataLabels.distance = 0;
                d.plotOptions.series.dataLabels.placeLabelsInside = !0;
                for (d = 0; d < c.length; d += 1) c[d].innerSize = v + (l ? "%" : ""), c[d].size = (v += b) + (l ? "%" : ""), 0 === c[d].data[c[d].data.length - 1].y && c[d].data.pop()
            },
            spaceManager: function(b, d, a, c) {
                var e = b[na];
                this.titleSpaceManager(b, d, a - (e.marginLeftExtraSpace + e.marginRightExtraSpace + b.chart.marginRight +
                    b.chart.marginLeft), .4 * (c - (e.marginBottomExtraSpace + e.marginTopExtraSpace + b.chart.marginBottom + b.chart.marginTop)))
            },
            addMSPieCat: function(b, d, a, c, e, f, q) {
                var m = this.numberFormatter,
                    v = this.colorManager,
                    l, p, k = 0,
                    n = b.length - 1,
                    r, t, z;
                l = this.hcJSON.series;
                var D = f.labelSepChar,
                    w, C, H, $, ja, ya;
                void 0 === this.colorCount && (this.colorCount = 0);
                0 === d && (this.colorCount = 0);
                l[d] || (l[d] = {
                    data: [{
                        toolText: !1,
                        doNotSlice: !0,
                        y: 100,
                        visible: !1,
                        color: "rgba(255,255,255,0)"
                    }]
                });
                l = l[d];
                (p = a - 100 + l.data[l.data.length - 1].y) && l.data.splice(l.data.length -
                    1, 0, {
                        toolText: !1,
                        doNotSlice: !0,
                        y: p,
                        visible: !1,
                        color: "rgba(255,255,255,0)"
                    });
                l.data[l.data.length - 1].y = 100 - c;
                for (t = 0; t <= n; t += 1) r = b[t], r._userValue = m.getCleanValue(r.value, this.isValueAbs), r._value = g(r._userValue, 1), k += r._value;
                k = k || 1;
                p = (c - a) / k;
                for (t = n; 0 <= t; --t) r = b[t], n = p * r._value, z = F(h(r.label, r.name)), H = null !== r._userValue ? m.dataLabels(r._userValue) : B, $ = m.percentValue(r._value / k * 100), w = l.data.length - 1, C = g(r.alpha, e), ya = f.showLabels ? z : B, f.showValues && (f.showPercentValues ? ya += ya !== B ? D + $ : $ : void 0 !==
                    H && H !== B && (ya += ya !== B ? D + H : H)), ja = F(h(r.tooltext, r.hovertext, f.tooltext)), ja === B ? (ja = z, f.showValuesInTooltip && (f.showPercentInTooltip ? ja += ja !== B ? D + $ : $ : void 0 !== H && H !== B && (ja += ja !== B ? D + H : H))) : ja = ab(ja, [1, 2, 3, 14], {
                    percentValue: $,
                    label: z,
                    formattedValue: H
                }, r), l.data.splice(w, 0, {
                    prevPointIndex: q,
                    prevSeriesIndex: d - 1,
                    displayValue: ya,
                    toolText: ja,
                    y: n,
                    link: A(r.link),
                    doNotSlice: !0,
                    color: y(r.color || v.getPlotColor(), C),
                    shadow: {
                        opacity: .01 * oa(50 < C ? C * C * C * 1E-4 : C * C * .01)
                    }
                }), this.colorCount += 1, r.category && this.addMSPieCat(r.category,
                    d + 1, a, 0 === t ? c : a + n, e, f, w), a += n
            },
            isValueAbs: !0,
            creditLabel: ta
        }, Sa);
        s("radar", {
            friendlyName: "Radar Chart",
            standaloneInit: !0,
            creditLabel: ta,
            defaultSeriesType: "radar",
            areaAlpha: 50,
            spaceManager: function(b, d, a, c) {
                b.chart.plotBorderWidth = 0;
                b.chart.plotBackgroundColor = null;
                var e = b[na],
                    f = e.x,
                    q = b.xAxis,
                    m = b.yAxis[0],
                    v = d.chart,
                    m = g(v.labelpadding, v.labelxpadding, parseInt(m && m.labels && m.labels.style && m.labels.style.fontSize || 10, 10));
                a -= e.marginLeftExtraSpace + e.marginRightExtraSpace + b.chart.marginRight + b.chart.marginLeft;
                c -= e.marginBottomExtraSpace + e.marginTopExtraSpace + b.chart.marginBottom + b.chart.marginTop;
                e = this.colorManager;
                c -= this.titleSpaceManager(b, d, a, .4 * c);
                q.min = g(f.min, 0);
                q.max = g(f.max, f.catCount - 1);
                q.gridLineColor = y(h(v.radarspikecolor, e.getColor("divLineColor")), g(v.radarspikealpha, v.radarinlinealpha, e.getColor("divLineAlpha")));
                q.gridLineWidth = g(v.radarspikethickness, 1);
                q.showRadarBorder = g(v.showradarborder, 1);
                q.radarBorderThickness = g(v.radarborderthickness, 2);
                q.radarBorderColor = y(h(v.radarbordercolor,
                    e.getColor("divLineColor")), g(v.radarborderalpha, 100));
                q.radarFillColor = y(h(v.radarfillcolor, e.getColor("altHGridColor")), g(v.radarfillalpha, e.getColor("altHGridAlpha")));
                b.legend.enabled && (h(v.legendposition, Ia).toLowerCase() != Aa ? c -= this.placeLegendBlockBottom(b, d, a, c / 2) : a -= this.placeLegendBlockRight(b, d, a / 3, c));
                d = g(v.radarradius);
                f = 2 * g(parseInt(q.labels.style.lineHeight, 10), 12);
                v = 2 * m;
                f = J(a - (100 + v), c - (f + v));
                d = d || .5 * f;
                a = J(.3 * a, .3 * c);
                d < a && (d = a);
                b.chart.axisRadius = d;
                q.labels.labelPadding = m
            },
            anchorAlpha: "100",
            showValues: 0,
            isRadar: !0,
            rendererId: "radar"
        }, s.msareabase);
        Sa = {
            dragExtended: !0,
            defaultRestoreButtonVisible: 1,
            spaceManager: function(b, d, a, c) {
                var e = b[na],
                    f = b.chart,
                    q = d.chart,
                    m = e.outCanvasStyle,
                    v = c - .3 * (e.marginBottomExtraSpace + f.marginBottom + f.marginTop),
                    l = 0,
                    p = 0,
                    e = this.smartLabel || e.smartLabel,
                    k, n;
                f.formAction = A(q.formaction);
                f.formDataFormat = h(q.formdataformat, G.dataFormats.XML);
                f.formTarget = h(q.formtarget, "_self");
                f.formMethod = h(q.formmethod, "POST");
                f.submitFormAsAjax = g(q.submitformusingajax, 1);
                f.showFormBtn =
                    g(q.showformbtn, 1) && f.formAction;
                f.formBtnTitle = h(q.formbtntitle, "Submit");
                f.formBtnBorderColor = h(q.formbtnbordercolor, "CBCBCB");
                f.formBtnBgColor = h(q.formbtnbgcolor, "FFFFFF");
                f.btnPadding = g(q.btnpadding, 7);
                f.btnSpacing = g(q.btnspacing, 5);
                f.formBtnStyle = {
                    fontSize: m.fontSize,
                    fontFamily: m.fontFamily,
                    fontWeight: "bold"
                };
                f.formBtnLabelFill = m.color;
                q.btntextcolor && (f.formBtnLabelFill = q.btntextcolor.replace(ob, Qa));
                0 <= (m = g(q.btnfontsize)) && (f.formBtnStyle.fontSize = m + "px");
                Da(f.formBtnStyle);
                f.showRestoreBtn =
                    g(q.showrestorebtn, this.defaultRestoreButtonVisible, 1);
                f.showRestoreBtn && (f.restoreBtnTitle = h(q.restorebtntitle, "Restore"), f.restoreBtnBorderColor = h(q.restorebtnbordercolor, f.formBtnBorderColor), f.restoreBtnBgColor = h(q.restorebtnbgcolor, f.formBtnBgColor), f.restoreBtnStyle = {
                        fontSize: f.formBtnStyle.fontSize,
                        fontFamily: f.formBtnStyle.fontFamily,
                        fontWeight: "bold"
                    }, f.restoreBtnLabelFill = f.formBtnLabelFill, q.restorebtntextcolor && (f.restoreBtnLabelFill = q.restorebtntextcolor.replace(ob, Qa)), 0 <= (m = g(q.restorebtnfontsize)) &&
                    (f.restoreBtnStyle.fontSize = m + "px"), Da(f.restoreBtnStyle));
                f.showLimitUpdateMenu = g(q.showlimitupdatemenu, 1);
                f.showFormBtn && (e.setStyle(f.formBtnStyle), k = e.getOriSize(f.formBtnTitle), l = k.height || 0);
                f.showRestoreBtn && (e.setStyle(f.restoreBtnStyle), n = e.getOriSize(f.restoreBtnTitle), l = Ya(n.height, l) || 0);
                0 < l && (l += f.btnPadding + 4, l > v && (f.btnPadding = Ya(f.btnPadding - l + v, 0) / 2, l = v));
                f.btnHeight = l;
                f.showFormBtn && (p = k.width + l, f.formBtnWidth = g(q.formbtnwidth, p), f.formBtnWidth < k.width && (f.formBtnWidth = p));
                f.showRestoreBtn &&
                    (p = n.width + l, f.restoreBtnWidth = g(q.restorebtnwidth, p), f.restoreBtnWidth < n.width && (f.restoreBtnWidth = p));
                f.marginBottom += l + f.btnPadding;
                f.spacingBottom += l + f.btnPadding;
                (b.callbacks || (b.callbacks = [])).push(this.drawButtons);
                return this.placeVerticalXYSpaceManager.apply(this, arguments)
            },
            drawButtons: function() {
                var b = this.logic,
                    d = this.paper,
                    a = this.options.chart,
                    c = a.btnSpacing,
                    e = this.chartHeight - a.spacingBottom + a.btnPadding,
                    f = this.chartWidth - a.spacingRight,
                    q = this.layers.layerAboveDataset,
                    m = 0;
                a.showFormBtn &&
                    (this.submitBtn = d.button(f - a.formBtnWidth, e, a.formBtnTitle, void 0, {
                        width: a.formBtnWidth,
                        height: a.btnHeight,
                        verticalPadding: 1,
                        horizontalPadding: 15
                    }, q).labelcss(a.formBtnStyle).attr({
                        fill: [V(a.formBtnBgColor), a.formBtnLabelFill],
                        stroke: V(a.formBtnBorderColor)
                    }).buttonclick(function() {
                        b.chartInstance.submitData()
                    }), m = a.formBtnWidth + c);
                a.showRestoreBtn && (this.restoreBtn = d.button(f - a.restoreBtnWidth - m, e, a.restoreBtnTitle, void 0, {
                        width: a.restoreBtnWidth,
                        height: a.btnHeight,
                        verticalPadding: 1,
                        horizontalPadding: 15
                    },
                    q).labelcss(a.restoreBtnStyle).attr({
                    fill: [V(a.restoreBtnBgColor), a.restoreBtnLabelFill],
                    stroke: V(a.restoreBtnBorderColor)
                }).buttonclick(function() {
                    b.chartInstance.restoreData()
                }))
            },
            drawAxisUpdateUI: function() {
                var b = this,
                    d = b.logic,
                    a = b.elements,
                    c = b.options,
                    e = c.chart,
                    f = c[na],
                    q = d.chartInstance,
                    d = d.renderer,
                    m = b.yAxis[0],
                    g = m.axisData,
                    l = m.poi,
                    p = g.plotLines,
                    k = b.container,
                    n = c.chart.showRangeError,
                    h = f.inCanvasStyle,
                    c = b.toolbar || (b.toolbar = []),
                    m = b.menus || (b.menus = []),
                    t = cb({
                        outline: "none",
                        "-webkit-appearance": "none",
                        filter: "alpha(opacity=0)",
                        position: "absolute",
                        background: "transparent",
                        border: "1px solid #cccccc",
                        textAlign: "right",
                        top: 0,
                        left: 0,
                        width: 50,
                        zIndex: 20,
                        opacity: 0,
                        borderRadius: 0
                    }, h),
                    z, D;
                d && !d.forExport && (D = function(a, c, d) {
                    if (a === c + "") return null;
                    c = d ? q.setUpperLimit(a, !0) : q.setLowerLimit(a, !0);
                    !c && n && b.showMessage("Sorry! Not enough range gap to modify axis limit to " + (Number(a) || "0") + ".<br />Please modify the data values to be within range.<br />&nbsp;<br />(click anywhere on the chart to close this message)", !0);
                    return c
                }, lb(["max", "min"], function(a) {
                    var c = l[a],
                        d = c.label,
                        f = p[c.index],
                        c = d && d.getBBox(),
                        q, m, g, n, v, z, s;
                    if (c && d) {
                        m = c.x + c.width - e.spacingLeft;
                        g = e.marginLeft - m - (fb ? 4 : 5);
                        q = qb("input", {
                            type: "text",
                            value: f.value,
                            name: a || ""
                        }, k, !0);
                        cb(t, {
                            top: c.y + (fb ? -1 : 0) + "px",
                            left: g + "px",
                            width: m + "px"
                        });
                        for (n in t) q.style[n] = t[n];
                        u.dem.listen(q, ["focus", "mouseup", "blur", "keyup"], [function() {
                                var a = {
                                        opacity: 1,
                                        filter: "alpha(opacity=100)",
                                        color: h.color
                                    },
                                    b;
                                this.value = f.value;
                                for (b in a) this.style[b] = a[b];
                                v = s = !0;
                                d.hide()
                            },
                            function() {
                                var a = this;
                                s && (s = !1, qa || setTimeout(function() {
                                    a.select()
                                }, 0))
                            },
                            function() {
                                !0 !== D(this.value, f.value, f.isMaxLabel) && (this.style.opacity = 0, this.style.filter = "alpha(opacity=0)", d.show());
                                $b && Ua.getElementsByTagName("body")[0].focus && Ua.getElementsByTagName("body")[0].focus();
                                v = s = !1
                            },
                            function(a) {
                                var b = a.originalEvent.keyCode,
                                    c = this.value;
                                13 === b ? (a = D(c, f.value, f.isMaxLabel), !1 === a && (this.style.color = "#dd0000")) : 27 === b && (this.value = f.value, u.dem.fire(this, "blur", a))
                            }
                        ]);
                        q.setAttribute("isOverlay",
                            "true");
                        fb ? (pb(b.container, "defaultprevented", z = function(a) {
                            q.parentNode && u.dem.fire(q, "blur", a)
                        }), pb(b.container, "destroy", function() {
                            X(b, "defaultprevented", z);
                            q.parentNode.removeChild(q)
                        })) : (pb(b.container, "mousedown", z = function(a) {
                            a.srcElement !== q && v && u.dem.fire(q, "blur", a)
                        }), pb(b.container, "destroy", function() {
                            X(b.container, "mousedown", z);
                            q.parentNode.removeChild(q)
                        }))
                    }
                }), e.showLimitUpdateMenu && (m.push(z = Ob({
                    chart: b,
                    basicStyle: f.outCanvasStyle,
                    items: [{
                        text: "Increase Upper Limit",
                        onclick: function() {
                            q.setUpperLimit(g.max +
                                g.tickInterval, !0)
                        }
                    }, {
                        text: "Increase Lower Limit",
                        onclick: function() {
                            q.setLowerLimit(g.min + g.tickInterval, !0)
                        }
                    }, {
                        text: "Decrease Upper Limit",
                        onclick: function() {
                            q.setUpperLimit(g.max - g.tickInterval, !0)
                        }
                    }, {
                        text: "Decrease Lower Limit",
                        onclick: function() {
                            q.setLowerLimit(g.min - g.tickInterval, !0)
                        }
                    }],
                    position: {
                        x: e.spacingLeft,
                        y: q.height - e.spacingBottom + (e.showFormBtn || e.showRestoreBtn ? 10 : -15)
                    }
                })), a.configureButton = c.add("configureIcon", function(a, b) {
                    return function() {
                        z.visible ? z.hide() : z.show({
                            x: a,
                            y: b + 1
                        })
                    }
                }(), {
                    x: e.spacingLeft,
                    y: q.height - e.spacingBottom + (e.showFormBtn || e.showRestoreBtn ? 10 : -15),
                    tooltip: "Change Y-Axis Limits"
                })))
            },
            getCollatedData: function() {
                var b = this.chartInstance,
                    d = b.__state,
                    a = b.jsVars,
                    b = this.updatedDataObj || O({}, b.getChartData(G.dataFormats.JSON)),
                    c = a._reflowData,
                    a = b.dataset,
                    e = (c = c && c.hcJSON && c.hcJSON.series) && c.length,
                    f, q, m, g;
                if (void 0 !== d.hasStaleData && !d.hasStaleData && this.updatedDataObj) return this.updatedDataObj;
                if (a && c)
                    for (; e--;)
                        if (q = a[e] && a[e].data, (f = (m = c[e] && c[e].data) && m.length) &&
                            q)
                            for (; f--;)
                                if (g = m[f]) q[f].value = g.y;
                d.hasStaleData = !1;
                return this.updatedDataObj = b
            },
            eiMethods: {
                restoreData: function() {
                    var b = this.jsVars,
                        d = b.fcObj;
                    b._reflowData = {};
                    delete b._reflowClean;
                    G.hcLib.createChart(d, b.container, b.type, void 0, void 0, !1, !0);
                    u.raiseEvent("dataRestored", {}, d, [d.id]);
                    return !0
                },
                submitData: function() {
                    var b = this.jsVars,
                        d = b.fcObj,
                        a = d.__state,
                        c = a._submitAjaxObj || (a._submitAjaxObj = new G.ajax),
                        a = G.dataFormats.JSON,
                        e = G.dataFormats.CSV,
                        f = G.dataFormats.XML,
                        b = b.instanceAPI,
                        q = b.hcJSON.chart,
                        m = q.formAction,
                        g = q.submitFormAsAjax,
                        l, p, k, n, h;
                    q.formDataFormat === a ? (l = a, p = JSON.stringify(b.getCollatedData())) : q.formDataFormat === e ? (l = e, p = b.getCSVString && b.getCSVString(), void 0 === p && (p = G.core.transcodeData(b.getCollatedData(), a, e))) : (l = f, p = G.core.transcodeData(b.getCollatedData(), a, f));
                    G.raiseEvent("beforeDataSubmit", {
                        data: p
                    }, d, void 0, function() {
                        g ? (c.onError = function(a, b, c, e) {
                            u.raiseEvent("dataSubmitError", {
                                    xhrObject: b.xhr,
                                    url: e,
                                    statusText: a,
                                    httpStatus: b.xhr && b.xhr.status ? b.xhr.status : -1,
                                    data: p
                                },
                                d, [d.id, a, b.xhr && b.xhr.status])
                        }, c.onSuccess = function(a, b, e, f) {
                            u.raiseEvent("dataSubmitted", {
                                xhrObject: c,
                                response: a,
                                url: f,
                                data: p
                            }, d, [d.id, a])
                        }, k = {}, k["str" + l.toUpperCase()] = p, c.open && c.abort(), c.post(m, k)) : (n = Xa.document.createElement("span"), n.innerHTML = '<form style="display:none" action="' + m + '" method="' + q.formMethod + '" target="' + q.formTarget + '"> <input type="hidden" name="strXML" value="' + Zc(p) + '"><input type="hidden" name="dataFormat" value="' + l.toUpperCase() + '" /></form>', h = n.removeChild(n.firstChild),
                            Xa.document.body.appendChild(h), h.submit && h.submit(), h.parentNode.removeChild(h), n = h = null)
                    }, function() {
                        G.raiseEvent("dataSubmitCancelled", {
                            data: p
                        }, d)
                    })
                },
                getDataWithId: function() {
                    for (var b = this.jsVars.instanceAPI.getCollatedData(), d = [
                            [B]
                        ], a = b.dataset, b = b.categories && b.categories[0] && b.categories[0].category, c = a && a.length || 0, e = 0, f, q, m, g, l, p; c--;)
                        if (q = a[c])
                            for (d[0][c + 1] = q.id || q.seriesname, g = q.id || c + 1, p = (q = q.data) && q.length || 0, l = 0; l < p; l += 1) {
                                m = l + 1;
                                if (!d[m]) {
                                    for (f = b && b[l + e] || {}; f.vline;) e += 1, f = b[l + e] || {};
                                    f = f.label || f.name || B;
                                    d[m] = [f]
                                }
                                f = d[m];
                                m = q[l].id || m + "_" + g;
                                f[c + 1] = [m, Number(q[l].value)]
                            }
                        return d
                },
                getData: function(b) {
                    var d = this.jsVars.instanceAPI.getCollatedData(),
                        a = [
                            [B]
                        ],
                        c = d.dataset,
                        e = d.categories && d.categories[0] && d.categories[0].category,
                        f = c && c.length || 0,
                        q = 0,
                        m, g, l;
                    if (b) a = /^json$/ig.test(b) ? d : G.core.transcodeData(d, "json", b);
                    else
                        for (; f--;)
                            if (b = c[f])
                                for (a[0][f + 1] = c[f].seriesname, d = (b = c[f] && c[f].data) && b.length || 0, l = 0; l < d; l += 1) {
                                    g = l + 1;
                                    if (!a[g]) {
                                        for (m = e && e[l + q] || {}; m.vline;) q += 1, m = e[l + q] || {};
                                        m =
                                            m.label || m.name || B;
                                        a[g] = [m]
                                    }
                                    g = a[g];
                                    g[f + 1] = Number(b[l].value)
                                }
                            return a
                },
                setYAxisLimits: function(b, d) {
                    var a = this.jsVars.instanceAPI,
                        c = a.hcJSON,
                        e = a.dataObj,
                        f = e && e.chart || {},
                        c = c && c.yAxis && c.yAxis[0] || !1,
                        q = !1;
                    f.animation = !1;
                    if (!c) return !1;
                    void 0 !== b && b > a.highValue && b !== c.max ? (f.yaxismaxvalue = b, q = !0) : (b = a.highValue > c.max ? a.highValue : c.max, f.yaxismaxvalue = b);
                    void 0 !== d && d < a.lowValue && d !== c.min ? (f.yaxisminvalue = d, q = !0) : (d = a.lowValue < c.min ? a.lowValue : c.min, f.yaxisminvalue = d);
                    q && a.updateChartWithData(e);
                    return q
                },
                getUpperLimit: function() {
                    var b = this.jsVars.instanceAPI.hcJSON;
                    return (b = b.yAxis && b.yAxis[0]) ? b.max : void 0
                },
                setUpperLimit: function(b) {
                    return this.jsVars.fcObj.setYAxisLimits(b, void 0)
                },
                getLowerLimit: function() {
                    var b = this.jsVars.instanceAPI.hcJSON;
                    return (b = b.yAxis && b.yAxis[0]) ? b.min : void 0
                },
                setLowerLimit: function(b) {
                    return this.jsVars.fcObj.setYAxisLimits(void 0, b)
                }
            },
            updateChartWithData: function(b) {
                var d = this.chartInstance,
                    a = d.jsVars,
                    c = b && b.chart;
                b = a._reflowData || (a._reflowData = {});
                c = {
                    dataObj: {
                        chart: {
                            yaxisminvalue: g(c.yaxisminvalue),
                            yaxismaxvalue: g(c.yaxismaxvalue),
                            animation: c.animation
                        }
                    }
                };
                O(b, c, !0);
                G.hcLib.createChart(d, a.container, a.type)
            },
            preSeriesAddition: function() {
                var b = this,
                    d = b.hcJSON,
                    a = b.dataObj.chart,
                    c = d.chart;
                b.tooltipSepChar = d[na].tooltipSepChar;
                c.allowAxisChange = g(a.allowaxischange, 1);
                c.changeDivWithAxis = 1;
                c.snapToDivOnly = g(a.snaptodivonly, 0);
                c.snapToDiv = c.snapToDivOnly ? 1 : g(a.snaptodiv, 1);
                c.snapToDivRelaxation = g(a.snaptodivrelaxation, 10);
                c.doNotSnap = g(a.donotsnap, 0);
                c.doNotSnap && (c.snapToDiv = c.snapToDivOnly = 0);
                c.showRangeError =
                    g(a.showrangeerror, 0);
                g(a.allowaxischange, 1) && (d.callbacks || (d.callbacks = [])).push(function(a) {
                    var c = this,
                        d = arguments,
                        m;
                    pb(a.renderer.container, "destroy", function() {
                        m && (m = clearTimeout(m))
                    });
                    m = setTimeout(function() {
                        b.drawAxisUpdateUI.apply(c, d);
                        m = null
                    }, 1)
                })
            },
            getTooltextCreator: function() {
                var b = arguments;
                return function() {
                    var d = arguments,
                        a = d.length,
                        c, e, f;
                    for (f = 0; f < a; f += 1) void 0 !== (e = d[f]) && void 0 !== (c = b[f]) && (b[f] = "object" === typeof c ? O(c, e) : e);
                    return ab.apply(this, b)
                }
            },
            getPointStub: function(b, d, a, c,
                e, f, q) {
                var m = this.isDual,
                    v = this.dataObj.chart;
                c = c[na];
                var l = null === d ? d : c.numberFormatter.dataLabels(d, 1 === q ? !0 : !1),
                    p = A(F(h(b.tooltext, e.plottooltext, c.tooltext))),
                    k = c.tooltipSepChar,
                    n = e._sourceDataset;
                d = g(b.allowdrag, n.allowdrag, 1);
                var n = g(b.allownegativedrag, n.allownegativedrag, e.allownegativedrag, 1),
                    r, t, z, D, w = 0,
                    C = 0,
                    H, $;
                c.showTooltip ? void 0 !== p ? ($ = this.getTooltextCreator(p, [1, 2, 3, 4, 5, 6, 7], {
                        yaxisName: F(m ? q ? v.syaxisname : v.pyaxisname : v.yaxisname),
                        xaxisName: F(v.xaxisname),
                        formattedValue: l,
                        label: a
                    }, b,
                    v, e), e = $(), e === p && ($ = void 0, w = 1)) : null === l ? e = !1 : (c.seriesNameInToolTip && (D = ea(e && e.seriesname)), e = D ? D + k : B, H = e += a ? a + k : B, c.showPercentInToolTip ? r = !0 : e += l) : e = !1;
                g(b.showvalue, f) ? void 0 !== A(b.displayvalue) ? (z = F(b.displayvalue), C = 1) : c.showPercentValues ? t = !0 : z = l : z = B;
                b = h(b.link);
                return {
                    displayValue: z,
                    categoryLabel: a,
                    toolText: e,
                    link: b,
                    showPercentValues: t,
                    showPercentInToolTip: r,
                    allowDrag: d,
                    allowNegDrag: n,
                    _toolTextStr: H,
                    _isUserValue: C,
                    _isUserTooltip: w,
                    _getTooltext: $
                }
            }
        };
        s("dragnode", {
            friendlyName: "Dragable Node Chart",
            standaloneInit: !0,
            decimals: 2,
            numdivlines: 0,
            numVDivLines: 0,
            defaultZeroPlaneHighlighted: !1,
            defaultZeroPlaneHidden: !0,
            spaceManager: Sa.spaceManager,
            drawButtons: Sa.drawButtons,
            updateChartWithData: Sa.updateChartWithData,
            creditLabel: ta,
            canvasPaddingModifiers: null,
            defaultSeriesType: "dragnode",
            rendererId: "dragnode",
            tooltipsepchar: " - ",
            showAxisLimitGridLines: 0,
            cleanedData: function(b, d) {
                var a = b && b.hcJSON,
                    c = d && d.hcJSON,
                    e, f, q, m, g, l, p, k, n;
                if (a && c) {
                    if (a.series && c.series && (g = c.series.length))
                        for (k = 0; k < g; k += 1)
                            if (f =
                                c.series[k], e = a.series[k], f.data && (l = f.data.length))
                                for (n = 0; n < l; n += 1) !0 === f.data[n] && e && e.data && e.data[n] && (delete e.data[n], e.data[n] = {
                                    y: null
                                });
                    if (a.connectors && c.connectors && (q = c.connectors.length))
                        for (k = 0; k < q; k += 1)
                            if (f = c.connectors[k], e = a.connectors[k], f.connector && (p = f.connector.length))
                                for (n = 0; n < p; n += 1) !0 === f.connector[n] && e && e.connector && e.connector[n] && (delete e.connector[n], e.connector[n] = {});
                    if (a.dragableLabels && c.dragableLabels && (m = c.dragableLabels.length))
                        for (k = 0; k < m; k += 1) !0 === c.dragableLabels[k] &&
                            a.dragableLabels[k] && (delete a.dragableLabels[k], a.dragableLabels[k] = {})
                }
            },
            eiMethods: O(cb(s.scatterbase.eiMethods, Sa.eiMethods), {
                addNode: function(b) {
                    var d = this.jsVars,
                        a = d.instanceAPI,
                        c = d._reflowData || (d._reflowData = {}),
                        e = a.hcJSON,
                        f = a.numberFormatter,
                        a = h(b.datasetId),
                        q = f.getCleanValue(b.y),
                        f = f.getCleanValue(b.x),
                        m = !1,
                        g = e.series,
                        l = g.length,
                        p = e.xAxis.min,
                        k = e.xAxis.max,
                        n = e.yAxis[0].min,
                        r = e.yAxis[0].max,
                        e = {
                            hcJSON: {
                                series: []
                            }
                        },
                        t = e.hcJSON.series,
                        z;
                    if (void 0 !== a && null !== q && q >= n && q <= r && null !== f && f >= p && f <=
                        k) {
                        for (p = 0; p < l && !m; p += 1) a == g[p].id && (t[p] = {
                            data: []
                        }, m = !0, z = g[p], n = z.data, k = n.length, n.push(n = z._dataParser(b, k, f, q)), t[p].data[k] = n, O(c, e, !0), z = {
                            index: k,
                            dataIndex: k,
                            link: b.link,
                            y: b.y,
                            x: b.x,
                            shape: b.shape,
                            width: b.width,
                            height: b.height,
                            radius: b.radius,
                            sides: b.sides,
                            label: b.name,
                            toolText: b.tooltext,
                            id: b.id,
                            datasetIndex: p,
                            datasetName: z.name,
                            sourceType: "dataplot"
                        });
                        if (m) return gb(d, z, "nodeadded"), G.raiseEvent("nodeadded", z, d.fcObj), !0
                    }
                    return !1
                },
                getNodeAttribute: function(b) {
                    var d = this.jsVars,
                        a = d.instanceAPI,
                        d = d._reflowData || (d._reflowData = {}),
                        d = d.hcJSON && d.hcJSON.series || [],
                        a = a.hcJSON.series,
                        c = a.length,
                        e, f, q, m;
                    if (void 0 !== b)
                        for (e = 0; e < c; e += 1)
                            for (f = a[e], m = f.data, q = m.length, f = 0; f < q; f += 1)
                                if (m[f].id === b) return d[e] && d[e].data && d[e].data[f] ? O(m[f]._options, d[e].data[f]._options, !0) : m[f]._options;
                    return !1
                },
                setNodeAttribute: function(b, d, a) {
                    var c = this.jsVars,
                        e = c.instanceAPI,
                        f = c._reflowData || (c._reflowData = {}),
                        q = e.hcJSON,
                        m = e.numberFormatter,
                        g = q.series,
                        l = g.length,
                        p = q.xAxis.min,
                        k = q.xAxis.max,
                        n = q.yAxis[0].min,
                        h =
                        q.yAxis[0].max,
                        q = {
                            hcJSON: {
                                series: []
                            }
                        },
                        e = q.hcJSON.series,
                        t = f.hcJSON && f.hcJSON.series || [],
                        z, D, w, C;
                    "object" === typeof d && void 0 === a ? C = d : (C = {}, C[d] = a);
                    if (void 0 !== b)
                        for (d = 0; d < l; d += 1)
                            for (z = g[d], D = z.data, w = D.length, a = 0; a < w; a += 1)
                                if (b === D[a].id) return b = D[a], delete C.id, t[d] && t[d].data && t[d].data[a] && t[d].data[a]._options && (C = O(t[d].data[a]._options, C, !0)), C = O(b._options, C, !0), b = m.getCleanValue(C.y), m = m.getCleanValue(C.x), null !== b && b >= n && b <= h && null !== m && m >= p && m <= k ? (e[d] = {
                                        data: []
                                    }, p = z._dataParser(C, a, m, b),
                                    k = {
                                        index: a,
                                        dataIndex: a,
                                        link: C.link,
                                        y: C.y,
                                        x: C.x,
                                        shape: C.shape,
                                        width: C.width,
                                        height: C.height,
                                        radius: C.radius,
                                        sides: C.sides,
                                        label: C.name,
                                        toolText: C.tooltext,
                                        id: C.id,
                                        datasetIndex: d,
                                        datasetName: z.name,
                                        sourceType: "dataplot"
                                    }, e[d].data[a] = p, O(f, q, !0), gb(c, k, "nodeupdated"), G.raiseEvent("nodeupdated", k, c.fcObj), !0) : !1;
                    return !1
                },
                deleteNode: function(b) {
                    if (void 0 !== b) {
                        var d = this.jsVars,
                            a = d.instanceAPI,
                            c = d._reflowClean || (d._reflowClean = {}),
                            e = a.hcJSON.series,
                            f = {
                                hcJSON: {
                                    series: []
                                }
                            },
                            q, m, g, l, p;
                        if (e && (g = e.length))
                            for (l =
                                0; l < g; l += 1)
                                if ((a = e[l]) && (m = a.data) && (q = m.length))
                                    for (p = 0; p < q; p += 1)
                                        if (b === m[p].id) return f.hcJSON.series[l] = {
                                            data: []
                                        }, f.hcJSON.series[l].data[p] = !0, O(c, f, !0), b = m[p], b = {
                                            index: p,
                                            dataIndex: p,
                                            link: b.link,
                                            y: b.y,
                                            x: b.x,
                                            shape: b._options.shape,
                                            width: b._options.width,
                                            height: b._options.height,
                                            radius: b._options.radius,
                                            sides: b._options.sides,
                                            label: b.displayValue,
                                            toolText: b.toolText,
                                            id: b.id,
                                            datasetIndex: l,
                                            datasetName: a.name,
                                            sourceType: "dataplot"
                                        }, gb(d, b, "nodedeleted"), G.raiseEvent("nodedeleted", b, d.fcObj), !0
                    }
                    return !1
                },
                addConnector: function(b) {
                    if ("object" === typeof b) {
                        var d = this.jsVars,
                            a = d.instanceAPI,
                            c = d._reflowData || (d._reflowData = {}),
                            a = a.hcJSON,
                            e = a.connectors && a.connectors[0] || {
                                connector: []
                            },
                            a = e.connector.length,
                            f = {
                                hcJSON: {
                                    connectors: [{
                                        connector: []
                                    }]
                                }
                            };
                        b = e._connectorParser && e._connectorParser(b, a);
                        e = {
                            arrowAtEnd: b.arrowAtEnd,
                            arrowAtStart: b.arrowAtStart,
                            fromNodeId: b.from,
                            id: b.id,
                            label: b.label,
                            link: b.connectorLink,
                            sourceType: "connector",
                            toNodeId: b.to
                        };
                        f.hcJSON.connectors[0].connector[a] = b;
                        O(c, f, !0);
                        gb(d, e, "connectoradded");
                        G.raiseEvent("connectoradded", e, d.fcObj);
                        return !0
                    }
                    return !1
                },
                editConnector: function(b, d, a) {
                    var c = this.jsVars,
                        e = c.instanceAPI,
                        f = c._reflowData || (c._reflowData = {}),
                        e = e.hcJSON,
                        q = e.connectors || (e.connectors = []),
                        m = q.length,
                        e = {
                            hcJSON: {
                                connectors: []
                            }
                        },
                        g = e.hcJSON.connectors,
                        l, p, k, n;
                    "object" === typeof d && void 0 === a ? n = d : (n = {}, n[d] = a);
                    if (void 0 !== b)
                        for (d = 0; d < m; d += 1)
                            if ((p = q[d]) && (l = p.connector))
                                for (k = l.length, a = 0; a < k; a += 1)
                                    if (b === l[a].id) return l = l[a], delete n.id, f.hcJSON && f.hcJSON.connectors && f.hcJSON.connectors[d] &&
                                        f.hcJSON.connectors[d].connector && f.hcJSON.connectors[d].connector[a] && f.hcJSON.connectors[d].connector[a]._options && (n = O(f.hcJSON.connectors[d].connector[a]._options, n, !0)), n = O(l._options, n, !0), b = {
                                            arrowAtEnd: Boolean(n.arrowatend),
                                            arrowAtStart: Boolean(n.arrowatstart),
                                            fromNodeId: n.from,
                                            id: b,
                                            label: n.label,
                                            link: n.link,
                                            sourceType: "connector",
                                            toNodeId: n.to
                                        }, g[d] = {
                                            connector: []
                                        }, l = p._connectorParser(n, a), g[d].connector[a] = l, O(f, e, !0), gb(c, b, "connectorupdated"), G.raiseEvent("connectorupdated", b, c.fcObj), !0;
                    return !1
                },
                deleteConnector: function(b) {
                    if (void 0 !== b) {
                        var d = this.jsVars,
                            a = d.instanceAPI,
                            c = d._reflowClean || (d._reflowClean = {}),
                            e = a.hcJSON.connectors,
                            a = {
                                hcJSON: {
                                    connectors: []
                                }
                            },
                            f, q, m, g, l, p = {};
                        if (e && (g = e.length))
                            for (l = 0; l < g; l += 1)
                                if ((f = e[l]) && (m = f.connector) && (q = m.length))
                                    for (f = 0; f < q; f += 1)
                                        if (b === m[f].id) return b = m[f], p = {
                                                arrowAtEnd: b.arrowAtEnd,
                                                arrowAtStart: b.arrowAtStart,
                                                fromNodeId: b.from,
                                                id: b.id,
                                                label: b.label,
                                                link: b.connectorLink,
                                                sourceType: "connector",
                                                toNodeId: b.to
                                            }, a.hcJSON.connectors[l] = {
                                                connector: []
                                            },
                                            a.hcJSON.connectors[l].connector[f] = !0, O(c, a, !0), gb(d, p, "connectordeleted"), G.raiseEvent("connectordeleted", p, d.fcObj), !0
                    }
                    return !1
                },
                addLabel: function(b) {
                    if (b) {
                        var d = this.jsVars,
                            a = d.instanceAPI,
                            c = d._reflowData || (d._reflowData = {}),
                            e = {
                                hcJSON: {
                                    dragableLabels: []
                                }
                            };
                        e.hcJSON.dragableLabels[(a.hcJSON.dragableLabels || []).length] = b;
                        O(c, e, !0);
                        b = {
                            text: b.text,
                            x: b.x,
                            y: b.y,
                            allowdrag: b.allowdrag,
                            sourceType: "labelnode",
                            link: b.link
                        };
                        gb(d, b, "labeladded");
                        G.raiseEvent("labeladded", b, d.fcObj);
                        return !0
                    }
                    return !1
                },
                deleteLabel: function(b,
                    d) {
                    var a = this.jsVars,
                        c = a.instanceAPI,
                        e = a._reflowClean || (a._reflowClean = {}),
                        f = {
                            hcJSON: {
                                dragableLabels: []
                            }
                        };
                    return b < (c.hcJSON.dragableLabels || []).length ? (f.hcJSON.dragableLabels[b] = !0, O(e, f, !0), gb(a, d, "labeldeleted"), G.raiseEvent("labeldeleted", d, a.fcObj), !0) : !1
                },
                setThreshold: function(b) {
                    var d = this.jsVars.hcObj.connectorsStore || [],
                        a = d.length,
                        c, e;
                    for (e = 0; e < a; e += 1)(c = d[e]) && c.options && (c.options.conStrength < b ? (c.graphic && c.graphic.hide(), c.text && (c.text.hide(), c.text.textBoundWrapper && c.text.textBoundWrapper.hide())) :
                        (c.graphic && c.graphic.show(), c.text && (c.text.show(), c.text.textBoundWrapper && c.text.textBoundWrapper.show())))
                }
            }),
            getCollatedData: function() {
                var b = this.chartInstance,
                    d = b.__state,
                    a = b.jsVars,
                    b = this.updatedDataObj || O({}, b.getChartData(G.dataFormats.JSON)),
                    c = a._reflowData,
                    e = a._reflowClean,
                    a = (b.labels || (b.labels = {
                        label: []
                    }), b.labels.label || (b.labels.label = [])),
                    f = c && c.hcJSON && c.hcJSON.dragableLabels,
                    q = e && e.hcJSON && e.hcJSON.dragableLabels,
                    m = b.connectors,
                    g = c && c.hcJSON && c.hcJSON.connectors,
                    l = e && e.hcJSON &&
                    e.hcJSON.connectors,
                    p = b.dataset,
                    k = c && c.hcJSON && c.hcJSON.series,
                    c = e && e.hcJSON && e.hcJSON.series,
                    e = k && k.length,
                    n, h, t, z;
                if (void 0 !== d.hasStaleData && !d.hasStaleData && this.updatedDataObj) return this.updatedDataObj;
                if (p && k)
                    for (; e--;)
                        if (h = p[e] && p[e].data, (n = (t = k[e] && k[e].data) && t.length) && h)
                            for (; n--;)
                                if (z = t[n]) h[n] ? O(h[n], z._options) : h[n] = z._options;
                if (e = g && g.length)
                    for (b.connectors || (m = b.connectors = [{
                            connector: []
                        }]); e--;)
                        if (k = m[e] && m[e].connector, (n = (h = g[e] && g[e].connector) && h.length) && k)
                            for (; n--;)
                                if (t =
                                    h[n]) k[n] ? O(k[n], t._options) : k[n] = t._options;
                if ((e = f && f.length) && f)
                    for (; e--;) f[e] && (a[e] = f[e]);
                Mb(p, c);
                Mb(m, l);
                Mb(a, q);
                d.hasStaleData = !1;
                return this.updatedDataObj = b
            },
            createHtmlDialog: function(b, d, a, c, e, f) {
                var q = b.paper,
                    m = this.hcJSON[na].inCanvasStyle,
                    g = b.chartWidth,
                    l = b.chartHeight,
                    h = {
                        color: m.color,
                        textAlign: "center",
                        paddingTop: "1px",
                        border: "1px solid #cccccc",
                        borderRadius: "4px",
                        cursor: "pointer",
                        _cursor: "hand",
                        backgroundColor: "#ffffff",
                        zIndex: 21,
                        "-webkit-border-radius": "4px"
                    },
                    k;
                k = q.html("div", {
                    fill: "transparent",
                    width: g,
                    height: l
                }, {
                    fontSize: "10px",
                    lineHeight: "15px",
                    fontFamily: m.fontFamily
                }, b.container);
                k.veil = q.html("div", {
                    fill: "000000",
                    width: g,
                    height: l,
                    opacity: .3
                }, void 0, k);
                k.dialog = q.html("div", {
                    x: (g - d) / 2,
                    y: (l - a) / 2,
                    fill: "efefef",
                    strokeWidth: 1,
                    stroke: "000000",
                    width: d,
                    height: a
                }, {
                    borderRadius: "5px",
                    boxShadow: "1px 1px 3px #000000",
                    "-webkit-border-radius": "5px",
                    "-webkit-box-shadow": "1px 1px 3px #000000",
                    filter: 'progid:DXImageTransform.Microsoft.Shadow(Strength=4, Direction=135, Color="#000000")'
                }, k);
                k.ok = q.html("div", {
                    x: d - 70 - 5,
                    y: a - 23 - 5,
                    width: 65,
                    height: 17,
                    text: "Submit",
                    tabIndex: 1
                }, h, k.dialog).on("click", c);
                k.cancel = q.html("div", {
                    x: d - 140 - 5,
                    y: a - 23 - 5,
                    width: 65,
                    height: 17,
                    text: "Cancel",
                    tabIndex: 2
                }, h, k.dialog).on("click", e);
                k.remove = q.html("div", {
                    x: d - 210 - 5,
                    y: a - 23 - 5,
                    width: 65,
                    height: 17,
                    text: "Delete",
                    tabIndex: 3,
                    visibility: "hidden"
                }, h, k.dialog).on("click", f);
                k.handleKeyPress = function(a) {
                    13 === a.keyCode ? k.ok.trigger(qa ? "touchStart" : "click", a) : 27 === a.keyCode && k.cancel.trigger(qa ? "touchStart" : "click", a)
                };
                k.hide();
                return k
            },
            nodeUpdateUIDefinition: [{
                key: "id",
                text: "Id",
                inputWidth: 60,
                x: 10,
                y: 15
            }, {
                key: "dataset",
                text: "Dataset",
                inputType: "select",
                inputWidth: 110,
                innerHTML: void 0,
                x: 170,
                y: 15
            }, {
                key: "x",
                text: "Value",
                x: 10,
                y: 40,
                inputWidth: 21
            }, {
                key: "y",
                text: ",",
                x: 88,
                y: 40,
                inputWidth: 21,
                labelWidth: 5
            }, {
                text: "(x, y)",
                x: 125,
                y: 40,
                labelWidth: 33,
                noInput: !0
            }, {
                key: "tooltip",
                text: "Tooltip",
                inputWidth: 105,
                x: 170,
                y: 40
            }, {
                key: "label",
                text: "Label",
                inputWidth: 92,
                x: 10,
                y: 65
            }, {
                key: "labelalign",
                text: "Align",
                labelWidth: 70,
                inputWidth: 110,
                inputType: "select",
                innerHTML: '<option></option><option value="top">Top</option><option value="middle">Middle</option><option value="bottom">Bottom</option>',
                x: 145,
                y: 63
            }, {
                key: "color",
                text: "Color",
                x: 10,
                y: 90,
                inputWidth: 60
            }, {
                key: "colorOut",
                innerHTML: "&nbsp;",
                x: 85,
                y: 90,
                inputWidth: 15,
                inputType: "span"
            }, {
                key: "alpha",
                text: "Alpha",
                x: 170,
                y: 90,
                inputWidth: 20
            }, {
                key: "draggable",
                text: "Allow Drag",
                value: !0,
                inputWidth: 20,
                x: 250,
                y: 90,
                labelWidth: 58,
                inputPaddingTop: 3,
                type: "checkbox"
            }, {
                key: "shape",
                text: "Shape",
                inputType: "select",
                inputWidth: 97,
                innerHTML: '<option value="rect">Rectangle</option><option value="circ">Circle</option><option value="poly">Polygon</option>',
                x: 10,
                y: 115
            }, {
                key: "rectHeight",
                text: "Height",
                x: 170,
                y: 115,
                inputWidth: 20
            }, {
                key: "rectWidth",
                text: "Width",
                x: 255,
                y: 115,
                inputWidth: 20
            }, {
                key: "circPolyRadius",
                text: "Radius",
                x: 170,
                y: 115,
                inputWidth: 20
            }, {
                key: "polySides",
                text: "Sides",
                x: 255,
                y: 115,
                inputWidth: 20
            }, {
                key: "link",
                text: "Link",
                x: 10,
                y: 140,
                inputWidth: 92
            }, {
                key: "image",
                text: "Image",
                type: "checkbox",
                inputPaddingTop: 4,
                inputWidth: 20,
                x: 10,
                y: 170
            }, {
                key: "imgUrl",
                text: "URL",
                inputWidth: 105,
                x: 170,
                y: 170
            }, {
                key: "imgWidth",
                text: "Width",
                inputWidth: 20,
                x: 10,
                y: 195
            }, {
                key: "imgHeight",
                text: "Height",
                inputWidth: 20,
                x: 82,
                y: 195
            }, {
                key: "imgAlign",
                text: "Align",
                inputType: "select",
                inputWidth: 75,
                innerHTML: '<option value="top">Top</option><option value="middle">Middle</option><option value="bottom">Bottom</option>',
                x: 170,
                y: 195
            }],
            showNodeUpdateUI: function() {
                var b = function(a) {
                        a = a.cacheUpdateUI;
                        for (var b = a.fields.shape, d = ["rectWidth", "rectHeight", "circPolyRadius", "polySides"], q = d.length, m; q--;) m = d[q], /rect|poly|circ/ig.test(m) && (a.labels[m].hide(), a.fields[m].hide()), (new RegExp(h(b.val(), "rect"), "ig")).test(m) &&
                            (a.labels[m].show(), a.fields[m].show())
                    },
                    d = function(a) {
                        a = a.cacheUpdateUI.fields;
                        var b = ub(a.color.val());
                        b && a.colorOut.css({
                            background: Bb(b)
                        })
                    },
                    a = function(a, b) {
                        var d = a.cacheUpdateUI,
                            q = a.chartHeight,
                            m = d.fields.image.val(),
                            g = b ? 300 : 0,
                            l = ["imgWidth", "imgHeight", "imgAlign", "imgUrl"],
                            h, k, n;
                        h = m ? 250 : 215;
                        d.ok.hide();
                        d.cancel.hide();
                        d.remove.hide();
                        d.error.hide();
                        for (k = l.length; !m && k--;) n = l[k], d.labels[n].hide(), d.fields[n].hide();
                        u.danimate.animate(d.dialog.element, {
                            top: (q - h) / 2,
                            height: h
                        }, g, "linear", function() {
                            for (k =
                                l.length; k-- && m;) n = l[k], d.labels[n].show(), d.fields[n].show();
                            d.ok.attr({
                                y: h - 23 - 5
                            }).show();
                            d.cancel.attr({
                                y: h - 23 - 5
                            }).show();
                            d.remove.attr({
                                y: h - 23 - 5
                            });
                            d.error.attr({
                                y: h - 23 - 5 + 4
                            }).show();
                            d.edit ? d.remove.show() : d.remove.hide()
                        })
                    };
                return function(c, e, f) {
                    var q = this,
                        m = c.cacheUpdateUI,
                        g = c.paper,
                        l = {
                            width: "80px",
                            border: "1px solid #cccccc",
                            fontSize: "10px",
                            lineHeight: "15px",
                            padding: "2px",
                            fontFamily: q.hcJSON[na].inCanvasStyle.fontFamily
                        },
                        h = 0,
                        k = {
                            textAlign: "right"
                        },
                        n = m && m.fields,
                        r = m && m.labels,
                        t;
                    m || (m = c.cacheUpdateUI =
                        q.createHtmlDialog(c, 350, 215, function() {
                            var a = m && m.fields,
                                b = m.edit,
                                c = q.chartInstance,
                                d = q.hcJSON,
                                e, f, k, g, l, n, v;
                            if (!d) return !1;
                            e = d.xAxis.min;
                            f = d.yAxis[0].min;
                            d = d.series;
                            k = d.length;
                            if (a) {
                                switch (a.shape.val()) {
                                    case "circ":
                                        l = "circle";
                                        break;
                                    case "poly":
                                        l = "polygon";
                                        break;
                                    default:
                                        l = "rectangle"
                                }
                                v = {
                                    x: ea(a.x.val(), e),
                                    y: ea(a.y.val(), f),
                                    id: e = a.id.val(),
                                    datasetId: a.dataset.val(),
                                    name: a.label.val(),
                                    tooltext: a.tooltip.val(),
                                    color: a.color.val(),
                                    alpha: a.alpha.val(),
                                    labelalign: a.labelalign.val(),
                                    allowdrag: a.draggable.val(),
                                    shape: l,
                                    width: a.rectWidth.val(),
                                    height: a.rectHeight.val(),
                                    radius: a.circPolyRadius.val(),
                                    numsides: a.polySides.val(),
                                    imagenode: a.image.val(),
                                    imagewidth: a.imgWidth.val(),
                                    imageheight: a.imgHeight.val(),
                                    imagealign: a.imgAlign.val(),
                                    imageurl: a.imgUrl.val(),
                                    link: a.link.val()
                                };
                                if (void 0 !== e && !b)
                                    for (h = 0; h < k && !g; h += 1)
                                        for (l = d[h].data, n = l.length, f = 0; f < n; f += 1) e === l[f].id && (g = !0);
                                if (g) m.error.attr({
                                    text: "ID already exist."
                                }), a.label.focus();
                                else {
                                    b ? c && c.setNodeAttribute && c.setNodeAttribute(v.id, v) : c && c.addNode && c.addNode(v);
                                    return
                                }
                            }
                            m.enableFields()
                        }, function() {
                            m.hide();
                            m.enableFields();
                            m.error.attr({
                                text: B
                            })
                        }, function() {
                            q.chartInstance.deleteNode && q.chartInstance.deleteNode(m.fields.id.val())
                        }), t = m.dialog, r = m.labels = {}, n = m.fields = {});
                    m.config = e;
                    m.edit = f;
                    m.error || (m.error = g.html("span", {
                        color: "ff0000",
                        x: 30,
                        y: 228
                    }, void 0, t));
                    m.enableFields || (m.enableFields = function() {
                        for (var a in e) e[a] && e[a].disabled && n[a] && n[a].element.removeAttribute("disabled")
                    });
                    lb(this.nodeUpdateUIDefinition, function(f) {
                        var q, h = f.key,
                            p = {},
                            H = e[h] || {},
                            $, ja;
                        !r[h] && (r[h] = g.html("label", {
                            x: f.x,
                            y: f.y,
                            width: f.labelWidth || 45,
                            text: f.text
                        }, k, t));
                        if (!f.noInput) {
                            q = n[h];
                            if (!q) {
                                l.border = "checkbox" == f.type ? B : "1px solid #cccccc";
                                q = n[h] = g.html(f.inputType || "input", {
                                    x: f.labelWidth && f.labelWidth + 5 || 50,
                                    y: -2 + (f.inputPaddingTop || 0),
                                    width: f.inputWidth || 50,
                                    name: h || ""
                                }, l);
                                if ("select" !== f.inputType) q.attr({
                                    type: f.type || "text"
                                }).on("keyup", m.handleKeyPress);
                                q.add(r[h])
                            }
                            ba($ = ea(H.innerHTML, f.innerHTML)) && (p.innerHTML = $);
                            H.disabled && (p.disabled = "disabled");
                            q.attr(p);
                            ba(ja = ea(H.value, f.value)) && q.val(ja);
                            "shape" == h && q.on("change", function() {
                                b(c)
                            });
                            "image" == h && q.on("click", function() {
                                a(c, !0)
                            });
                            "color" == h && q.on("keyup", function() {
                                d(c)
                            })
                        }
                    });
                    d(c);
                    a(c);
                    b(c);
                    c.options.chart.animation ? m.fadeIn("fast") : m.show();
                    m.fields[f ? "label" : "id"].focus()
                }
            }(),
            labelUpdateUIDefinition: [{
                key: "label",
                text: "Label*",
                x: 10,
                y: 15,
                inputWidth: 235
            }, {
                key: "size",
                text: "Size",
                x: 10,
                y: 40
            }, {
                key: "padding",
                text: "Padding",
                x: 10,
                y: 65
            }, {
                key: "x",
                text: "Position",
                x: 120,
                y: 65,
                labelWidth: 70,
                inputWidth: 25
            }, {
                key: "y",
                text: ",",
                x: 225,
                y: 65,
                labelWidth: 10,
                inputWidth: 25
            }, {
                key: "xy",
                text: "(x, y)",
                x: 260,
                y: 65,
                noInput: !0
            }, {
                key: "allowdrag",
                text: "Allow Drag",
                x: 120,
                y: 40,
                inputType: "checkbox",
                inputPaddingTop: 3,
                inputWidth: 15,
                labelWidth: 70,
                val: 1
            }, {
                key: "color",
                text: "Color",
                x: 10,
                y: 90
            }, {
                key: "alpha",
                text: "Alpha",
                x: 145,
                y: 90,
                inputWidth: 30,
                val: "100"
            }, {
                key: "bordercolor",
                text: "Border Color",
                x: 10,
                y: 125,
                labelWidth: 100
            }, {
                key: "bgcolor",
                text: "Background Color",
                x: 10,
                y: 150,
                labelWidth: 100
            }],
            showLabelUpdateUI: function(b, d) {
                var a = this,
                    c = b.paper,
                    e = b.cacheLabelUpdateUI,
                    f = {
                        border: "1px solid #cccccc",
                        fontSize: "10px",
                        lineHeight: "15px",
                        fontFamily: a.hcJSON[na].inCanvasStyle.fontFamily,
                        padding: "2px"
                    },
                    q = {
                        textAlign: "right"
                    },
                    m = e && e.fields,
                    g = e && e.labels,
                    l, p, k;
                e || (e = b.cacheLabelUpdateUI = a.createHtmlDialog(b, 315, 205, function() {
                    var b = e && e.fields,
                        c;
                    b && (c = {
                            text: b.label.val(),
                            x: b.x.val(),
                            y: b.y.val(),
                            color: b.color.val(),
                            alpha: b.alpha.val(),
                            bgcolor: b.bgcolor.val(),
                            bordercolor: b.bordercolor.val(),
                            fontsize: b.size.val(),
                            allowdrag: b.allowdrag.val(),
                            padding: b.padding.val()
                        },
                        c.text ? a.chartInstance && a.chartInstance.addLabel && a.chartInstance.addLabel(c) : (e.error.attr({
                            text: "Label cannot be blank."
                        }), b.label.focus()))
                }, function() {
                    e.error.attr({
                        text: ""
                    });
                    e.hide()
                }), k = e.dialog, g = e.labels = {}, m = e.fields = {});
                lb(a.labelUpdateUIDefinition, function(a) {
                    var b = a.key;
                    g[b] || (g[b] = c.html("label", {
                        x: a.x,
                        y: a.y,
                        width: a.labelWidth || 45,
                        text: a.text
                    }, q, k));
                    a.noInput || ((l = m[b]) || (l = m[b] = c.html("input", {
                        y: -2 + (a.inputPaddingTop || 0),
                        x: a.labelWidth && a.labelWidth + 5 || 50,
                        width: a.inputWidth || 50,
                        type: a.inputType ||
                            "text",
                        name: b || ""
                    }, f, g[b]).on("keyup", e.handleKeyPress)), void 0 !== (p = h(d[b], a.val)) && l.val(p))
                });
                e.error || (e.error = c.html("span", {
                    color: "ff0000",
                    x: 10,
                    y: 180
                }, void 0, k));
                b.animation ? e.fadeIn("fast") : e.show();
                e.fields.label.focus()
            },
            showLabelDeleteUI: function(b, d) {
                var a = this,
                    c = b.paper,
                    e = b["cache-label-delete-ui"],
                    f = d.data && d.data("data") || {},
                    q = d.data && d.data("eventArgs"),
                    f = f && f.labelNode;
                e || (e = b["cache-label-delete-ui"] = a.createHtmlDialog(b, 250, 100, void 0, function() {
                    e.hide()
                }, function() {
                    a.chartInstance.deleteLabel(f.index,
                        q)
                }), e.message = c.html("span", {
                    x: 10,
                    y: 10,
                    width: 230,
                    height: 80
                }).add(e.dialog), e.ok.hide(), e.remove.translate(175).show());
                e.message.attr({
                    text: 'Would you really like to delete the label: "' + f.text + '"?'
                });
                b.animation ? e.fadeIn("fast") : e.show()
            },
            connectorUpdateUIDefinition: [{
                key: "fromid",
                text: "Connect From",
                inputType: "select",
                x: 10,
                y: 15,
                labelWidth: 80,
                inputWidth: 100
            }, {
                key: "toid",
                text: "Connect To",
                inputType: "select",
                x: 10,
                y: 40,
                labelWidth: 80,
                inputWidth: 100
            }, {
                key: "arratstart",
                text: "Arrow At Start",
                x: 200,
                y: 15,
                type: "checkbox",
                inputPaddingTop: 3,
                labelWidth: 80,
                inputWidth: 15
            }, {
                key: "arratend",
                text: "Arrow At End",
                x: 200,
                y: 40,
                type: "checkbox",
                inputPaddingTop: 3,
                labelWidth: 80,
                inputWidth: 15
            }, {
                key: "label",
                text: "Label",
                x: 10,
                y: 75,
                labelWidth: 40,
                inputWidth: 120
            }, {
                key: "id",
                text: "Node ID",
                x: 190,
                y: 75,
                inputWidth: 55
            }, {
                key: "color",
                text: "Color",
                x: 10,
                y: 100,
                labelWidth: 40,
                inputWidth: 35
            }, {
                key: "alpha",
                text: "Alpha",
                x: 110,
                y: 100,
                inputWidth: 25,
                labelWidth: 35
            }, {
                key: "strength",
                text: "Strength",
                x: 190,
                y: 100,
                inputWidth: 55,
                val: "0.1"
            }, {
                key: "url",
                text: "Link",
                x: 10,
                y: 125,
                labelWidth: 40,
                inputWidth: 120
            }, {
                key: "tooltext",
                text: "Tooltip",
                x: 190,
                y: 125,
                labelWidth: 40,
                inputWidth: 60
            }, {
                key: "dashed",
                text: "Dashed",
                x: 10,
                y: 150,
                type: "checkbox",
                inputPaddingTop: 3,
                inputWidth: 15,
                labelWidth: 40
            }, {
                key: "dashgap",
                text: "Dash Gap",
                x: 85,
                y: 150,
                labelWidth: 60,
                inputWidth: 25
            }, {
                key: "dashlen",
                text: "Dash Length",
                x: 190,
                y: 150,
                labelWidth: 70,
                inputWidth: 30
            }],
            showConnectorUpdateUI: function(b, d, a) {
                var c = this.chartInstance,
                    e = b.paper,
                    f = b.cacheConnectorUpdateUI,
                    q = {
                        border: "1px solid #cccccc",
                        fontSize: "10px",
                        lineHeight: "15px",
                        fontFamily: this.hcJSON[na].inCanvasStyle.fontFamily,
                        padding: "2px"
                    },
                    m = {
                        textAlign: "right"
                    },
                    g = f && f.fields,
                    l = f && f.labels,
                    p, k, n, r;
                f || (f = b.cacheConnectorUpdateUI = this.createHtmlDialog(b, 315, 215, function() {
                        var b = f && f.fields,
                            d;
                        b && (d = {
                            from: b.fromid.val(),
                            to: b.toid.val(),
                            id: b.id.val(),
                            label: b.label.val(),
                            color: b.color.val(),
                            alpha: b.alpha.val(),
                            link: b.url.val(),
                            tooltext: b.tooltext.val(),
                            strength: b.strength.val(),
                            arrowatstart: b.arratstart.val(),
                            arrowatend: b.arratend.val(),
                            dashed: b.dashed.val(),
                            dashlen: b.dashlen.val(),
                            dashgap: b.dashgap.val()
                        }, d.from ? d.to ? d.from != d.to ? (a ? c.editConnector(d.id, d) : c.addConnector(d), f.enableFields()) : (f.error.attr({
                            text: "Connector cannot start and end at the same node!"
                        }), b.fromid.focus()) : (f.error.attr({
                            text: "Please select a valid connector end."
                        }), b.toid.focus()) : (f.error.attr({
                            text: "Please select a valid connector start."
                        }), b.fromid.focus()))
                    }, function() {
                        f.error.attr({
                            text: ""
                        });
                        f.enableFields();
                        f.hide()
                    }, function() {
                        c.deleteConnector(f.fields.id.val())
                    }), r =
                    f.dialog, l = f.labels = {}, g = f.fields = {});
                f.config = d;
                f.enableFields = function() {
                    for (var a in d) d[a] && d[a].disabled && g[a] && g[a].element.removeAttribute("disabled")
                };
                lb(this.connectorUpdateUIDefinition, function(a) {
                    var b = a.key,
                        c = d[b] || {};
                    l[b] || (l[b] = e.html("label", {
                        x: a.x,
                        y: a.y,
                        width: a.labelWidth || 45,
                        text: a.text
                    }, m, r));
                    if (!a.noInput) {
                        if (!(k = g[b])) {
                            k = g[b] = e.html(a.inputType || "input", {
                                y: -2 + (a.inputPaddingTop || 0),
                                x: a.labelWidth && a.labelWidth + 5 || 50,
                                width: a.inputWidth || 50,
                                name: b || ""
                            }, q);
                            if ("select" !== a.inputType) k.attr({
                                type: a.type ||
                                    "text"
                            }).on("keyup", f.handleKeyPress);
                            k.add(l[b])
                        }(p = h(c.innerHTML, a.innerHTML)) && k.attr({
                            innerHTML: p
                        });
                        void 0 !== (n = h(c.val, a.val)) && k.val(n);
                        c.disabled && k.attr({
                            disabled: "disabled"
                        })
                    }
                });
                f.checkDash = function() {
                    var a = g.dashed && g.dashed.val() ? "show" : "hide";
                    l.dashgap && l.dashgap[a]();
                    g.dashgap && g.dashgap[a]();
                    l.dashlen && l.dashlen[a]();
                    g.dashlen && g.dashlen[a]()
                };
                f.checkDash();
                g.dashed.on("click", f.checkDash);
                f.error || (f.error = e.html("span", {
                    color: "ff0000",
                    x: 10,
                    y: 170
                }, void 0, r));
                f.remove[a ? "show" : "hide"]();
                b.animation ? f.fadeIn("fast") : f.show()
            },
            drawNodeUpdateButtons: function() {
                var b = this,
                    d = b.logic,
                    a = b.options,
                    c = a.chart,
                    e = a.pointStore || {},
                    f = a.series,
                    a = (a = a[na]) && a.outCanvasStyle || b.outCanvasStyle || {},
                    q = b.menu || (b.menu = []),
                    m = b.toolbar,
                    g = f.length,
                    l = "",
                    h = "",
                    k, n;
                for (n in e) l += '<option value="' + n + '">' + n + "</option>";
                for (n = 0; n < g; n += 1) e = f[n], h += '<option value="' + e.id + '">' + (e.name !== B && void 0 !== e.name && e.name + Ba + " " || B) + e.id + "</option>";
                q.push(k = Ob({
                    chart: b,
                    basicStyle: a,
                    items: [{
                        text: "Add a Node",
                        onclick: function() {
                            d.showNodeUpdateUI(b, {
                                dataset: {
                                    innerHTML: h
                                }
                            })
                        }
                    }, {
                        text: "Add a Label",
                        onclick: function() {
                            d.showLabelUpdateUI(b, {})
                        }
                    }, {
                        text: "Add a Connector",
                        onclick: function() {
                            d.showConnectorUpdateUI(b, {
                                fromid: {
                                    innerHTML: l
                                },
                                toid: {
                                    innerHTML: l
                                }
                            })
                        }
                    }],
                    position: {
                        x: c.spacingLeft,
                        y: b.chartHeight - c.spacingBottom + (c.showFormBtn || c.showRestoreBtn ? 10 : -15)
                    }
                }));
                b.elements.configureButton = m.add("configureIcon", function(a, b) {
                    return function() {
                        k.visible ? k.hide() : k.show({
                            x: a,
                            y: b + 1
                        })
                    }
                }(), {
                    x: c.spacingLeft,
                    y: b.chartHeight - c.spacingBottom + (c.showFormBtn ||
                        c.showRestoreBtn ? 10 : -15),
                    tooltip: "Add or edit items"
                })
            },
            postSeriesAddition: function() {
                var b = this.hcJSON,
                    d = this.dataObj.chart,
                    a = this.base.postSeriesAddition && this.base.postSeriesAddition.apply(this, arguments);
                b.legend.enabled = "1" == d.showlegend ? !0 : !1;
                (b.chart.viewMode = g(d.viewmode, 0)) || (b.callbacks || (b.callbacks = [])).push(this.drawNodeUpdateButtons);
                return a
            },
            pointHoverOptions: function(b, d, a, c) {
                var e = g(b.showhovereffect, d.showhovereffect, a.plothovereffect, a.showhovereffect),
                    f = {},
                    q = !!h(b.hovercolor,
                        d.hovercolor, a.plotfillhovercolor, b.hoveralpha, d.hoveralpha, a.plotfillhoveralpha, b.borderhovercolor, d.borderhovercolor, a.plotborderhovercolor, b.borderhoveralpha, d.borderhoveralpha, a.plotborderhoveralpha, b.borderhoverthickness, d.borderhoverthickness, a.plotborderhoverthickness, b.hoverheight, d.hoverheight, a.plothoverheight, b.hoverwidth, d.hoverwidth, a.plothoverwidth, b.hoverradius, d.hoverradius, a.plothoverradius, e),
                    m = !1;
                if (void 0 === e && q || e) m = !0, e = h(b.hovercolor, d.hovercolor, a.plotfillhovercolor, La(c.color,
                    70)), q = h(b.hoveralpha, d.hoveralpha, a.plotfillhoveralpha, c.alpha), f = {
                    stroke: y(h(b.borderhovercolor, d.borderhovercolor, a.plotborderhovercolor, c.borderColor), g(b.borderhoveralpha, d.borderhoveralpha, a.plotborderhoveralpha, q, c.borderAlpha)),
                    "stroke-width": g(b.borderhoverthickness, d.borderhoverthickness, a.plotborderhoverthickness, c.borderThickness),
                    height: g(b.hoverheight, d.hoverheight, a.plothoverheight, c.height),
                    width: g(b.hoverwidth, d.hoverwidth, a.plothoverwidth, c.width),
                    r: g(b.hoverradius, d.hoverradius,
                        a.plothoverradius, c.radius)
                }, b = c.use3D ? this.getPointColor(V(h(b.hovercolor, d.hovercolor, a.plotfillhovercolor, La(c.color, 70))), h(b.hoveralpha, d.hoveralpha, a.plotfillhoveralpha, c.alpha), c.shapeType) : y(e, q), f.fill = ca(b);
                return {
                    enabled: m,
                    rolloverProperties: f
                }
            },
            point: function(b, d, a, c, e, f, q) {
                var m = this;
                b = g(c.ignoreemptydatasets, 0);
                var v = m.numberFormatter,
                    l = (f = a.data) && f.length,
                    p = g(a.showvalues, e[na].showValues),
                    k = g(c.useroundedges),
                    n = !1,
                    r = m.colorManager,
                    t, z, D, w, C, H, $, ja, u, E, Ka, s, ka, x;
                d.zIndex = 1;
                d.name =
                    A(a.seriesname);
                H = d.id = h(a.id, q);
                if (b && !a.data) return d.showInLegend = !1, d;
                if (0 === g(a.includeinlegend) || void 0 === d.name) d.showInLegend = !1;
                z = h(c.plotfillalpha, "100");
                D = g(c.showplotborder, 1);
                q = V(h(c.plotbordercolor, "666666"));
                t = g(c.plotborderthickness, k ? 2 : 1);
                w = h(c.plotborderalpha, c.plotfillalpha, k ? "35" : "95");
                C = Boolean(g(c.use3dlighting, c.is3d, k ? 1 : 0));
                $ = V(h(a.color, r.getPlotColor()));
                ja = h(a.plotfillalpha, a.nodeFillAlpha, a.alpha, z);
                k = Boolean(g(a.showplotborder, D));
                u = V(h(a.plotbordercolor, a.nodebordercolor,
                    q));
                E = g(a.plotborderthickness, a.nodeborderthickness, t);
                Ka = k ? h(a.plotborderalpha, a.nodeborderalpha, a.alpha, w) : "0";
                s = Boolean(g(a.allowdrag, 1));
                d.marker = {
                    enabled: !0,
                    fillColor: y($, ja),
                    lineColor: {
                        FCcolor: {
                            color: u,
                            alpha: Ka
                        }
                    },
                    lineWidth: E,
                    symbol: "poly_4"
                };
                w = d._dataParser = function(b, d, f, q) {
                    d = h(b.id, H + "_" + d);
                    var k = Boolean(g(b.allowdrag, s)),
                        l = A(b.shape, "rectangle").toLowerCase(),
                        n = A(b.height, 10),
                        r = A(b.width, 10),
                        t = A(b.radius, 10),
                        z = A(b.numsides, 4),
                        w = V(h(b.color, $)),
                        D = h(b.alpha, ja),
                        F = A(b.imageurl),
                        W = Boolean(g(b.imagenode));
                    switch (l) {
                        case "circle":
                            x = 0;
                            break;
                        case "polygon":
                            x = 2;
                            l = da(z);
                            break;
                        default:
                            x = 1
                    }
                    ka = C ? m.getPointColor(w, D, x) : y(w, D);
                    z = m.pointHoverOptions(b, a, c, {
                        plotType: "funnel",
                        shapeType: x,
                        use3D: C,
                        height: n,
                        width: r,
                        radius: t,
                        color: w,
                        alpha: D,
                        borderColor: u,
                        borderAlpha: Ka,
                        borderThickness: E
                    });
                    return O(m.getPointStub(b, q, v.xAxis(f), e, a, p), {
                        hoverEffects: z,
                        _options: b,
                        y: q,
                        x: f,
                        id: d,
                        imageNode: W,
                        imageURL: F,
                        imageAlign: A(b.imagealign, B).toLowerCase(),
                        imageWidth: A(b.imagewidth),
                        imageHeight: A(b.imageheight),
                        labelAlign: h(b.labelalign,
                            W && ba(F) ? Ma : "middle"),
                        allowDrag: k,
                        marker: {
                            enabled: !0,
                            fillColor: ka,
                            lineColor: {
                                FCcolor: {
                                    color: u,
                                    alpha: Ka
                                }
                            },
                            lineWidth: E,
                            radius: t,
                            height: n,
                            width: r,
                            symbol: l
                        },
                        tooltipConstraint: m.tooltipConstraint
                    })
                };
                for (q = 0; q < l; q += 1)
                    if (t = f[q]) k = v.getCleanValue(t.y), r = v.getCleanValue(t.x), null === k ? d.data.push({
                        _options: t,
                        y: null
                    }) : (n = !0, d.data.push(w(t, q, r, k)), this.pointValueWatcher(e, k, r));
                b && !n && (d.showInLegend = !1);
                return d
            },
            getPointColor: function(b, d, a) {
                var c;
                b = V(b);
                d = Ea(d);
                c = La(b, 80);
                b = jc(b, 65);
                d = {
                    FCcolor: {
                        gradientUnits: "objectBoundingBox",
                        color: c + "," + b,
                        alpha: d + "," + d,
                        ratio: Gb
                    }
                };
                a ? d.FCcolor.angle = 1 === a ? 0 : 180 : (d.FCcolor.cx = .4, d.FCcolor.cy = .4, d.FCcolor.r = "50%", d.FCcolor.radialGradient = !0);
                return d
            },
            getPointStub: function(b, d, a, c, e) {
                var f = this.dataObj.chart,
                    q = c[na],
                    m = null === d ? d : q.numberFormatter.dataLabels(d),
                    g = A(F(h(b.tooltext, e.plottooltext, q.tooltext))),
                    l = this.tooltipSepChar = q.tooltipSepChar,
                    p = h(b.label, b.name);
                d = F(p);
                var k;
                c = B;
                var n = !1;
                q.showTooltip ? void 0 !== g ? (n = !0, e = ab(g, [3, 4, 5, 6, 8, 9, 10, 11], {
                    yaxisName: F(f.yaxisname),
                    xaxisName: F(f.xaxisname),
                    yDataValue: m,
                    xDataValue: a,
                    label: d
                }, b, f, e)) : void 0 !== p ? (e = d, n = !0) : null === m ? e = !1 : (q.seriesNameInToolTip && (k = ea(e && e.seriesname)), e = c = k ? k + l : B, e += a ? a + l : B, e += m) : e = !1;
                b = h(b.link);
                return {
                    displayValue: d,
                    toolText: e,
                    link: b,
                    _toolTextStr: c,
                    _isUserTooltip: n
                }
            },
            connector: function(b, d, a, c, e) {
                var f = e[na],
                    q = f.smartLabel;
                e = (b = a.connector) && b.length;
                var m, v, l, p, k, n, r, t, z, D, w, C = A(F(h(a.connectortooltext, c.connectortooltext))),
                    H = "$fromLabel" + f.tooltipSepChar + "$toLabel";
                m = g(a.stdthickness, 1);
                v = V(h(a.color, "FF5904"));
                l =
                    h(a.alpha, "100");
                p = g(a.dashgap, 5);
                k = g(a.dashlen, 5);
                n = Boolean(g(a.dashed, 0));
                r = Boolean(g(a.arrowatstart, 1));
                t = Boolean(g(a.arrowatend, 1));
                z = g(a.strength, 1);
                c = d.connector;
                D = d._connectorParser = function(a, b) {
                    var c = F(h(a.label, a.name)),
                        d = h(a.alpha, l),
                        d = {
                            FCcolor: {
                                color: V(h(a.color, v)),
                                alpha: d
                            }
                        },
                        e = q.getOriSize(c),
                        D = A(F(h(a.tooltext, C)));
                    w = f.showTooltip ? h(D, c ? "$label" : H) : !1;
                    return {
                        _options: a,
                        id: h(a.id, b).toString(),
                        from: h(a.from, B),
                        to: h(a.to, B),
                        label: c,
                        toolText: w,
                        customToolText: D,
                        color: d,
                        dashStyle: Boolean(g(a.dashed,
                            n)) ? Ha(g(a.dashlen, k), g(a.dashgap, p), m) : "none",
                        arrowAtStart: Boolean(g(a.arrowatstart, r)),
                        arrowAtEnd: Boolean(g(a.arrowatend, t)),
                        conStrength: g(a.strength, z),
                        connectorLink: A(a.link),
                        stdThickness: m,
                        labelWidth: e.widht,
                        labelHeight: e.height
                    }
                };
                for (a = 0; a < e; a += 1) c.push(D(b[a], a));
                return d
            },
            series: function(b, d, a) {
                var c = d[na],
                    e = [],
                    f, q, m, v;
                d.legend.enabled = Boolean(g(b.chart.showlegend, 1));
                if (b.dataset && 0 < (q = b.dataset.length)) {
                    this.categoryAdder(b, d);
                    c.x.requiredAutoNumericLabels = !1;
                    if (b.connectors && (f = b.connectors.length))
                        for (v =
                            0, m = f; v < m; v += 1) f = {
                            connector: []
                        }, e.push(this.connector(a, f, b.connectors[v], b.chart, d, c.oriCatTmp.length, v));
                    else f = {
                        connector: []
                    }, e.push(this.connector(a, f, {}, b.chart, d, c.oriCatTmp.length, v));
                    for (v = 0; v < q; v += 1) f = {
                        hoverEffects: this.parseSeriesHoverOptions(b, d, b.dataset[v], a),
                        data: []
                    }, f = this.point(a, f, b.dataset[v], b.chart, d, c.oriCatTmp.length, v), f instanceof Array ? d.series = d.series.concat(f) : d.series.push(f);
                    d.connectors = e;
                    b.labels && b.labels.label && 0 < b.labels.label.length && (d.dragableLabels = b.labels.label);
                    b.chart.showyaxisvalue = h(b.chart.showyaxisvalue, 0);
                    this.configureAxis(d, b);
                    b.trendlines && Zb(b.trendlines, d.yAxis, c, !1, this.isBar)
                }
            }
        }, s.scatterbase);
        Ga = function(b, d, a, c, e, f) {
            var q = f.logic,
                m = f.options.chart,
                v, l, p = d[b.from],
                k = d[b.to],
                n = {
                    sourceType: "connector"
                },
                r = b && b._options,
                t = q.numberFormatter,
                z, D, w, C, H;
            this.renderer = c;
            this.connectorsGroup = e;
            this.pointStore = d;
            this.options = b;
            this.style = a || {};
            p && k && (this.fromPointObj = p, this.toPointObj = k, this.fromX = z = p._xPos, this.fromY = D = p._yPos, this.toX = w = k._xPos, this.toY =
                C = k._yPos, this.arrowAtStart = n.arrowAtStart = b.arrowAtStart, this.arrowAtEnd = n.arrowAtEnd = b.arrowAtEnd, this.strokeWidth = d = b.conStrength * b.stdThickness, this.textBgColor = l = (this.color = v = b.color) && v.FCcolor && v.FCcolor.color, this.label = n.label = H = b.label, t = ab(b.toolText, [3, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92], {
                    label: b.label,
                    fromXValue: t.dataLabels(p.x),
                    fromYValue: t.dataLabels(p.y),
                    fromXDataValue: p.x,
                    fromYDataValue: p.y,
                    fromLabel: h(p.displayValue, p.id),
                    toXValue: t.dataLabels(k.x),
                    toYValue: t.dataLabels(k.y),
                    toXDataValue: k.x,
                    toYDataValue: k.y,
                    toLabel: h(k.displayValue, k.id)
                }), this.link = n.link = r && r.link, n.id = b.id, n.fromNodeId = p.id, n.toNodeId = k.id, p._config && p._config.startConnectors && p._config.startConnectors.push(this), k._config && k._config.endConnectors && k._config.endConnectors.push(this), p = function() {
                    var a = this,
                        c = b._options || {};
                    a._longpressactive = clearTimeout(a._longpressactive);
                    a.data("fire_click_event", 1);
                    a._longpressactive = setTimeout(function() {
                        a.data("fire_click_event", 0);
                        a.data("viewMode") || q.showConnectorUpdateUI(f, {
                            fromid: {
                                val: c.from,
                                innerHTML: "<option>" + c.from + "</option>",
                                disabled: !0
                            },
                            toid: {
                                val: c.to,
                                innerHTML: "<option>" + c.to + "</option>",
                                disabled: !0
                            },
                            arratstart: {
                                val: Boolean(g(c.arrowatstart, 1))
                            },
                            arratend: {
                                val: Boolean(g(c.arrowatend, 1))
                            },
                            dashed: {
                                val: g(c.dashed)
                            },
                            dashgap: {
                                val: c.dashgap
                            },
                            dashlen: {
                                val: c.dashlen
                            },
                            label: {
                                val: c.label
                            },
                            tooltext: {
                                val: c.tooltext
                            },
                            id: {
                                val: b.id,
                                disabled: !0
                            },
                            strength: {
                                val: c.strength
                            },
                            alpha: {
                                val: c.alpha
                            },
                            color: {
                                val: c.color
                            }
                        }, !0)
                    }, 1E3)
                }, this.graphic = c.path(this.getlinePath(), e).attr({
                    "stroke-width": d,
                    ishot: !0,
                    "stroke-dasharray": b.dashStyle,
                    cursor: this.link ? "pointer" : "",
                    stroke: ca(v)
                }).mousedown(p).mousemove(function() {
                    this.data("fire_click_event", 0);
                    db.call(this)
                }).mouseup(function(a) {
                    db.call(this);
                    xa.call(this, f, a, "ConnectorClick")
                }).hover(function(a) {
                    xa.call(this, f, a, "ConnectorRollover")
                }, function(a) {
                    xa.call(this, f, a, "ConnectorRollout")
                }).tooltip(t).data("eventArgs", n).data("viewMode", m.viewMode), H && (this.text = c.text(), e.appendChild(this.text), this.text.css(a.style).attr({
                    text: H,
                    x: (z + w) / 2,
                    y: (D +
                        C) / 2,
                    fill: a.color,
                    ishot: !0,
                    direction: m.textDirection,
                    cursor: this.link ? "pointer" : "",
                    "text-bound": [h(a.backgroundColor, l), h(a.borderColor, l), 1, "2"]
                }).tooltip(t).mousedown(p).mousemove(function() {
                    this.data("fire_click_event", 0);
                    db.call(this)
                }).hover(function(a) {
                    xa.call(this, f, a, "ConnectorRollover")
                }, function(a) {
                    xa.call(this, f, a, "ConnectorRollout")
                }).mouseup(function(a) {
                    db.call(this);
                    xa.call(this, f, a, "ConnectorClick")
                }).tooltip(t).data("eventArgs", n).data("viewMode", f.options.chart.viewMode)))
        };
        Ga.prototype = {
            updateFromPos: function(b, d) {
                this.fromX = b;
                this.fromY = d;
                this.graphic && this.graphic.animate({
                    path: this.getlinePath()
                });
                this.text && this.text.animate({
                    x: (this.fromX + this.toX) / 2,
                    y: (this.fromY + this.toY) / 2
                })
            },
            updateToPos: function(b, d) {
                this.toX = b;
                this.toY = d;
                this.graphic && this.graphic.animate({
                    path: this.getlinePath()
                });
                this.text && this.text.animate({
                    x: (this.fromX + this.toX) / 2,
                    y: (this.fromY + this.toY) / 2
                })
            },
            getlinePath: function() {
                var b = this.fromPointObj,
                    d = this.toPointObj,
                    a = this.fromX,
                    c = this.fromY,
                    e = this.toX,
                    f =
                    this.toY,
                    q = ["M", a, c];
                this.arrowAtStart && (b = b._config, q = b.shapeType === kc ? q.concat(jb(a, c, e, f, b.shapeArg.width, b.shapeArg.height)) : q.concat(jb(a, c, e, f, b.shapeArg.radius)));
                this.arrowAtEnd && (b = d._config, q = b.shapeType === kc ? q.concat(jb(e, f, a, c, b.shapeArg.width, b.shapeArg.height)) : q.concat(jb(e, f, a, c, b.shapeArg.radius)));
                q.push("L", e, f);
                return q
            }
        };
        Ga.prototype.constructor = Ga;
        va = {
            mouseDown: function(b) {
                delete b.data.point.dragActive
            },
            click: function(b) {
                return !b.data.point.dragActive
            },
            dragHandler: function(b) {
                var d =
                    b.data,
                    a = b.type,
                    c = d.point,
                    e = d.series,
                    f = e.chart || e,
                    q = f.tooltip,
                    g = qa && Na(b) || Eb,
                    f = f.options.instanceAPI;
                switch (a) {
                    case "dragstart":
                        q.block(!0);
                        d.dragStartY = b.pageY || g.pageY || 0;
                        d.dragStartX = b.pageX || g.pageX || 0;
                        d.startValue = c.y;
                        d.startXValue = c.x;
                        c.dragActive = !0;
                        e.dragStartHandler && e.dragStartHandler(d);
                        break;
                    case "dragend":
                        q.block(!1);
                        e.repositionItems(d, d.changeX ? (b.pageX || g.pageX || 0) - d.dragStartX : 0, d.changeY ? (b.pageY || g.pageY || 0) - d.dragStartY : 0, !0);
                        a = {
                            dataIndex: c.index + 1,
                            datasetIndex: e.index + 1,
                            startValue: d.startValue,
                            endValue: c.y,
                            datasetName: e.name
                        };
                        b = [f.chartInstance.id, a.dataIndex, a.datasetIndex, a.datasetName, a.startValue, a.endValue];
                        d.changeX && (a.startYValue = d.startValue, a.endYValue = c.y, a.startXValue = d.startXValue, a.endXValue = c.x, b.push(d.startXValue, c.x), delete a.startValue, delete a.endValue);
                        u.raiseEvent("chartupdated", a, f.chartInstance, b);
                        delete d.dragStartY;
                        delete d.dragStartX;
                        delete d.startValue;
                        delete d.startXValue;
                        break;
                    default:
                        e.repositionItems(d, d.changeX ? (b.pageX || g.pageX || 0) - d.dragStartX : 0, d.changeY ?
                            (b.pageY || g.pageY || 0) - d.dragStartY : 0)
                }
            },
            dragLabelHandler: function(b) {
                var d = b.data,
                    a = b.type,
                    c = d.element,
                    e = d.tracker,
                    f = d.toolTip,
                    q = qa && Na(b) || Eb,
                    g = d.series,
                    h, l, p;
                "dragstart" === a ? (f.block(!0), d.dragStartY = b.pageY || q.pageY || 0, d.dragStartX = b.pageX || q.pageX || 0) : (h = d.x + (b.pageX || q.pageX || 0) - d.dragStartX, p = h - d.leftDistance, p + d.width > d.plotWidth && (p = d.plotWidth - d.width), 0 > p && (p = 0), h = p + d.leftDistance, l = d.y + (b.pageY || q.pageY || 0) - d.dragStartY, b = l - d.topDistance, b + d.height > d.plotHeight && (b = d.plotHeight - d.height),
                    0 > b && (b = 0), l = b + d.topDistance, "dragend" === a ? (f.block(!1), d.x = h, d.y = l, delete d.dragStartY, delete d.dragStartX) : (c.attr({
                        x: h,
                        y: l
                    }).textBound(), e.attr({
                        x: p,
                        y: b
                    })));
                "dragend" == a && (a = {
                    hcJSON: {
                        dragableLabels: []
                    }
                }, a.hcJSON.dragableLabels[d.index] = {
                    y: g.yAxis.translate(g.chart.plotHeight - l + d.yAdjustment, 1),
                    x: g.xAxis.translate(h, 1)
                }, O(g.chart.options.instanceAPI.chartInstance.jsVars._reflowData, a, !0))
            },
            pointUpdate: function(b, d, a) {
                b._isUserTooltip || b.toolText === B || b._getTooltext ? b._getTooltext && (b.toolText = b._getTooltext(void 0,
                    void 0, {
                        formattedValue: d
                    }, {
                        value: a
                    })) : b.toolText = b._toolTextStr + d;
                b._isUserValue || b.displayValue === B || (b.displayValue = d)
            },
            snapPoint: function(b, d, a) {
                var c = b.options.chart,
                    e = c.snapToDiv,
                    f = c.snapToDivOnly;
                b = b._yAxisPlotLines;
                for (var q = S(b[1] - b[0]), c = f ? .5 * q : c.snapToDivRelaxation, q = d.lastSnap, g = 1, h = b.length, l; h--;)
                    if (l = S(b[h] - a), e && l < c) {
                        q !== h && (d.lastSnap = f ? void 0 : h, a = b[h]);
                        g = 0;
                        break
                    }
                g && (d.lastSnap = void 0);
                return a
            },
            setMinMaxValue: function(b) {
                var d = b.options.series;
                b = b.logic;
                var a = 0,
                    c = Infinity,
                    e = -Infinity,
                    f = b.chartInstance.jsVars._reflowData,
                    q, g, h, l, p;
                p = 0;
                for (q = d.length; p < q; p += 1)
                    for (h = d[p] && d[p].data, a = 0, l = h.length; a < l; a += 1) g = h[a].y, null !== g && (e = e > g ? e : g, c = c < g ? c : g);
                b.highValue = e;
                b.lowValue = c;
                f.postHCJSONCreation = function() {
                    var a = this.hcJSON[na][0];
                    a.min = c;
                    a.max = e
                }
            },
            setSelectBoxValues: function(b, d) {
                var a = d.xAxis[0],
                    c = d.yAxis[0],
                    e = d.plotHeight;
                b.startX = a.translate(b.left, 1);
                b.endX = a.translate(b.left + b.width, 1);
                b.startY = c.translate(e - b.top, 1);
                b.endY = c.translate(e - (b.top + b.height), 1)
            }
        };
        s("dragarea", cb({
            friendlyName: "Dragable Area Chart",
            standaloneInit: !0,
            creditLabel: ta,
            rendererId: "dragarea",
            defaultSeriesType: "area",
            decimals: 2,
            anchorAlpha: "100",
            eiMethods: s.msareabase.eiMethods
        }, Sa), s.msareabase);
        s("dragline", cb({
            friendlyName: "Dragable Line Chart",
            standaloneInit: !0,
            creditLabel: ta,
            decimals: 2,
            defaultSeriesType: "line",
            rendererId: "dragline",
            eiMethods: s.mslinebase.eiMethods
        }, Sa), s.mslinebase);
        s("dragcolumn2d", cb({
            friendlyName: "Dragable Column Chart",
            standaloneInit: !0,
            creditLabel: ta,
            decimals: 2,
            defaultSeriesType: "column",
            rendererId: "dragcolumn2d",
            eiMethods: s.mscolumn2dbase.eiMethods
        }, Sa), s.mscolumn2dbase);
        s("selectscatter", {
            friendlyName: "Dragable Scatter Chart",
            isXY: !0,
            standaloneInit: !0,
            creditLabel: ta,
            defaultSeriesType: "scatter",
            defaultZeroPlaneHighlighted: !1,
            spaceManager: Sa.spaceManager,
            drawButtons: Sa.drawButtons,
            updateChartWithData: Sa.updateChartWithData,
            eiMethods: cb(cb(cb({}, s.scatterbase.eiMethods), Sa.eiMethods), {
                getData: function(b) {
                    var d = this.jsVars.instanceAPI,
                        a = d.getCollatedData(),
                        c = [],
                        e = a.dataset,
                        f = e && e.length || 0,
                        q = 0,
                        g = 0;
                    if (b) c = /^json$/ig.test(b) ?
                        a : /^csv$/ig.test(b) ? d.getCSVString() : G.core.transcodeData(a, "json", b);
                    else
                        for (; q < f; q += 1)
                            if (d = e[q]) {
                                for ((a = b = (d = e[q] && e[q].data) && d.length || 0) && (c[g] || (c[g] = [A(e[q].id, "null")])); a--;) c[g][a + 1] = A(d[a].id, "null");
                                b && (g += 1)
                            } return c
                }
            }),
            getCSVString: function() {
                for (var b = this.chartInstance.getData(), d = b.length; d--;) b[d] = b[d].join(",");
                return b.join("|")
            },
            getCollatedData: function() {
                for (var b = this.chartInstance, d = b.jsVars.hcObj._selectEleArr, a = d && d.length, b = O({}, b.getChartData(G.dataFormats.JSON)), c = b.dataset,
                        e, f, q, g, h, l, p, k, n, r = []; a--;)
                    if (e = d[a])
                        for (l = e.startX, p = e.endX, k = e.startY, n = e.endY, g = c.length; g--;)
                            for (r[g] || (r[g] = {
                                    data: []
                                }), h = (q = c[g].data) && q.length; h--;) f = q[h], e = f.x, f = f.y, e > l && e < p && f < k && f > n && (r[g].data[h] = !0);
                for (g = c.length; g--;)
                    for (h = (q = c[g].data) && q.length; h--;) r[g] && r[g].data[h] || q.splice(h, 1);
                return this.updatedDataObj = b
            },
            createSelectionBox: function(b) {
                var d = b.chart,
                    a = d.paper,
                    c = d.options.chart,
                    e = d.yAxis && d.yAxis[0],
                    f = d.xAxis && d.xAxis[0],
                    g = b.selectionLeft,
                    m = b.selectionTop,
                    h = b.selectionWidth;
                b = b.selectionHeight;
                var l = g + h,
                    p = m + b,
                    k = 15 < h && 15 < b,
                    n = {
                        resizeEleRadius: 15,
                        canvasTop: d.canvasTop,
                        canvasRight: d.canvasLeft + d.canvasWidth,
                        canvasLeft: d.canvasLeft,
                        canvasBottom: d.canvasTop + d.canvasHeight
                    },
                    r = d.layers.tracker,
                    t = d._selectEleArr || (d._selectEleArr = []);
                n.index = t.length;
                n.id = "SELECT_" + n.index;
                n.selectBoxG = r = a.group("selection-box", r).toFront();
                n.selectBoxTracker = a.rect(g, m, h, b, r).attr({
                    "stroke-width": 1,
                    stroke: ca(c.selectBorderColor),
                    ishot: !0,
                    fill: c.selectFillColor
                }).css({
                    cursor: "move"
                });
                n.selectBoxTracker.data("config", {
                    position: 6,
                    selectEleObj: n,
                    xChange: !0,
                    yChange: !0
                });
                n.topTracker = a.rect(g, m - 6, h, 12, r).attr({
                    "stroke-width": 0,
                    ishot: !0,
                    fill: ua
                }).css("cursor", fb && "ns-resize" || "n-resize");
                n.topTracker.data("config", {
                    position: 1,
                    selectEleObj: n,
                    yChange: !0
                });
                n.rightTracker = a.rect(g + h - 6, m, 12, b, r).attr({
                    "stroke-width": 0,
                    ishot: !0,
                    fill: ua
                }).css("cursor", fb && "ew-resize" || "w-resize");
                n.rightTracker.data("config", {
                    position: 2,
                    selectEleObj: n,
                    xChange: !0
                });
                n.bottomTracker = a.rect(g, m + b - 6, h, 12, r).attr({
                    "stroke-width": 0,
                    ishot: !0,
                    fill: ua
                }).css("cursor",
                    fb && "ns-resize" || "n-resize");
                n.bottomTracker.data("config", {
                    position: 3,
                    selectEleObj: n,
                    yChange: !0
                });
                n.leftTracker = a.rect(g - 6, m, 12, b, r).attr({
                    "stroke-width": 0,
                    ishot: !0,
                    fill: ua
                }).css("cursor", fb && "ew-resize" || "e-resize");
                n.leftTracker.data("config", {
                    position: 4,
                    selectEleObj: n,
                    xChange: !0
                });
                n.cornerInnerSymbol = a.symbol("resizeIcon", 0, 0, 15, r).attr({
                    transform: "t" + l + "," + p,
                    "stroke-width": 1,
                    visibility: k ? Ab : "hidden",
                    ishot: !0,
                    stroke: "#999999"
                });
                n.cornerOuterSymbol = a.symbol("resizeIcon", 0, 0, -12, r).attr({
                    transform: "t" +
                        l + "," + p,
                    strokeWidth: 1,
                    visibility: k ? "hidden" : Ab,
                    ishot: !0,
                    stroke: "#777777"
                });
                n.resizeTracker = a.circle(l, p, 12, r).attr({
                    "stroke-width": 1,
                    stroke: ua,
                    ishot: !0,
                    fill: ua
                }).css("cursor", fb && "nwse-resize" || "nw-resize");
                n.resizeTracker.data("config", {
                    position: 5,
                    selectEleObj: n,
                    yChange: !0,
                    xChange: !0
                });
                n.closeButton = a.symbol("closeIcon", 0, 0, 6, r).attr({
                    transform: "t" + l + "," + m,
                    "stroke-width": 2,
                    stroke: c.selectionCancelButtonBorderColor,
                    fill: c.selectionCancelButtonFillColor,
                    "stroke-linecap": "round",
                    ishot: !0,
                    "stroke-linejoin": "round"
                }).css({
                    cursor: "pointer",
                    _cursor: "hand"
                }).click(function() {
                    d.logic.deleteSelection(this, d)
                });
                n.closeButton.data("config", {
                    index: n.index
                });
                n.chart = d;
                n.startX = f.getAxisPosition(g, 1);
                n.startY = e.getAxisPosition(m, 1);
                n.endX = f.getAxisPosition(l, 1);
                n.endY = e.getAxisPosition(p, 1);
                n.isVisible = !0;
                t.push(n);
                d.logic.bindDragEvent(n)
            },
            deleteSelection: function(b, d) {
                var a = b.data("config").index,
                    c = d._selectEleArr,
                    e = c[a],
                    f, g, m;
                f = e.selectBoxTracker.getBBox();
                m = {
                    selectionLeft: f.x,
                    selectionTop: f.y,
                    selectionWidth: f.width,
                    selectionHeight: f.height,
                    startXValue: d.xAxis[0].getAxisPosition(f.x, 1),
                    startYValue: d.yAxis[0].getAxisPosition(f.y, 1),
                    endXValue: d.xAxis[0].getAxisPosition(f.x + f.width, 1),
                    endYValue: d.yAxis[0].getAxisPosition(f.y + f.height, 1),
                    data: d.logic.getCollatedData(),
                    id: e.id
                };
                for (g in e) f = e[g], f.remove && f.remove(), delete e[g];
                delete c[a];
                G.raiseEvent("selectionRemoved", m, d.logic.chartInstance)
            },
            bindDragEvent: function(b) {
                for (var d in b) /Tracker/.test(d) && b[d].drag(this.move, this.start, this.up)
            },
            start: function() {
                var b = this.data("config").selectEleObj,
                    d = b.topTracker,
                    a = b.rightTracker,
                    c = b.bottomTracker,
                    e = b.leftTracker,
                    f = b.resizeTracker,
                    g = d.data("config"),
                    m = a.data("config"),
                    h = c.data("config"),
                    l = e.data("config"),
                    p = f.data("config"),
                    k = b.selectBoxTracker.data("config"),
                    n = b.selectBoxTracker.getBBox();
                g.ox = n.x;
                g.oy = n.y;
                m.ox = n.x2;
                m.oy = n.y;
                h.ox = n.x;
                h.oy = n.y2;
                l.ox = n.x;
                l.oy = n.y;
                g.ox = n.x;
                g.oy = n.y;
                p.ox = n.x2;
                p.oy = n.y2;
                k.ox = n.x;
                k.oy = n.y;
                k.ow = n.width;
                k.oh = n.height;
                k.ox2 = n.x2;
                k.oy2 = n.y2;
                b.selectBoxG.toFront();
                d.hide();
                a.hide();
                c.hide();
                e.hide();
                f.hide();
                this.show()
            },
            move: function(b, d) {
                var a = this.data("config"),
                    c = a.selectEleObj,
                    e = c.chart,
                    f = c.topTracker,
                    g = c.rightTracker,
                    m = c.bottomTracker,
                    h = c.leftTracker,
                    l = c.resizeTracker,
                    p = c.selectBoxTracker,
                    k = c.canvasLeft,
                    n = c.canvasRight,
                    r = c.canvasTop,
                    t = c.canvasBottom,
                    z = p.data("config"),
                    D = {},
                    w, C;
                b = a.xChange ? b : 0;
                d = a.yChange ? d : 0;
                w = b + a.ox;
                C = d + a.oy;
                w = J(n - (a.ow || 0), Ya(w, k));
                C = J(t - (a.oh || 0), Ya(C, r));
                switch (a.position) {
                    case 1:
                        D.y = J(z.oy2, C);
                        D.height = S(z.oy2 - C) || 1;
                        f.attr({
                            y: C + -6
                        });
                        break;
                    case 2:
                        D.x = J(z.ox, w);
                        D.width = S(z.ox - w) || 1;
                        g.attr({
                            x: w +
                                -6
                        });
                        break;
                    case 3:
                        D.y = J(z.oy, C);
                        D.height = S(z.oy - C) || 1;
                        m.attr({
                            y: C + -6
                        });
                        break;
                    case 4:
                        D.x = J(z.ox2, w);
                        D.width = S(z.ox2 - w) || 1;
                        h.attr({
                            x: w + -6
                        });
                        break;
                    case 5:
                        D.x = J(z.ox, w);
                        D.width = S(z.ox - w) || 1;
                        D.y = J(z.oy, C);
                        D.height = S(z.oy - C) || 1;
                        l.attr({
                            cx: w,
                            cy: C
                        });
                        break;
                    default:
                        D.x = w, D.y = C
                }
                this.data("dragStarted") || (a = p.getBBox(), a = {
                    selectionLeft: a.x,
                    selectionTop: a.y,
                    selectionWidth: a.width,
                    selectionHeight: a.height,
                    startXValue: e.xAxis[0].getAxisPosition(a.x, 1),
                    startYValue: e.yAxis[0].getAxisPosition(a.y, 1),
                    endXValue: e.xAxis[0].getAxisPosition(a.x +
                        a.width, 1),
                    endYValue: e.yAxis[0].getAxisPosition(a.y + a.height, 1),
                    id: c.id
                }, G.raiseEvent("BeforeSelectionUpdate", a, e.logic.chartInstance), this.data("dragStarted", 1));
                p.animate(D);
                c.isVisible && (c.closeButton.hide(), c.cornerInnerSymbol.hide(), c.cornerOuterSymbol.hide(), c.isVisible = !1)
            },
            up: function() {
                var b = this,
                    d = b.data("config").selectEleObj,
                    a = d.chart,
                    c = a.xAxis && a.xAxis[0],
                    e = a.yAxis && a.yAxis[0],
                    f = d.topTracker,
                    g = d.rightTracker,
                    m = d.bottomTracker,
                    h = d.leftTracker,
                    l = d.resizeTracker,
                    p = d.selectBoxTracker,
                    k, n;
                setTimeout(function() {
                    k = p.getBBox();
                    d.startX = c.getAxisPosition(k.x, 1);
                    d.startY = e.getAxisPosition(k.y, 1);
                    d.endX = c.getAxisPosition(k.x2, 1);
                    d.endY = e.getAxisPosition(k.y2, 1);
                    f.attr({
                        x: k.x,
                        y: k.y + -6,
                        width: k.width
                    });
                    g.attr({
                        x: k.x2 + -6,
                        y: k.y,
                        height: k.height
                    });
                    m.attr({
                        x: k.x,
                        y: k.y2 + -6,
                        width: k.width
                    });
                    h.attr({
                        x: k.x + -6,
                        y: k.y,
                        height: k.height
                    });
                    l.attr({
                        cx: k.x2,
                        cy: k.y2
                    });
                    d.closeButton.transform("t" + k.x2 + "," + k.y);
                    d.cornerInnerSymbol.transform("t" + k.x2 + "," + k.y2);
                    d.cornerOuterSymbol.transform("t" + k.x2 + "," + k.y2);
                    d.closeButton.show();
                    15 > k.width || 15 > k.height ? (d.cornerInnerSymbol.hide(), d.cornerOuterSymbol.show()) : (d.cornerInnerSymbol.show(), d.cornerOuterSymbol.hide());
                    d.isVisible = !0;
                    f.show();
                    g.show();
                    m.show();
                    h.show();
                    l.show();
                    b.data("dragStarted") && (n = {
                        selectionLeft: k.x,
                        selectionTop: k.y,
                        selectionWidth: k.width,
                        selectionHeight: k.height,
                        startXValue: a.xAxis[0].getAxisPosition(k.x, 1),
                        startYValue: a.yAxis[0].getAxisPosition(k.y, 1),
                        endXValue: a.xAxis[0].getAxisPosition(k.x + k.width, 1),
                        endYValue: a.yAxis[0].getAxisPosition(k.y + k.height, 1),
                        data: a.logic.getCollatedData(),
                        id: d.id
                    }, G.raiseEvent("SelectionUpdated", n, a.logic.chartInstance), b.data("dragStarted", 0))
                }, 100)
            },
            postSeriesAddition: function(b, d) {
                var a = s.scatter && s.scatter.postSeriesAddition && s.scatter.postSeriesAddition.apply(this, arguments),
                    c = b.chart,
                    e = d.chart,
                    f = this.colorManager,
                    q = h(e.selectbordercolor, f.getColor("canvasBorderColor")),
                    m = g(e.selectborderalpha, f.getColor("canvasBorderAlpha"));
                c.selectBorderColor = {
                    FCcolor: {
                        color: q,
                        alpha: m
                    }
                };
                c.selectFillColor = y(h(e.selectfillcolor,
                    f.getColor("altHGridColor")), g(e.selectfillalpha, f.getColor("altHGridAlpha")));
                c.selectionCancelButtonBorderColor = y(h(e.selectioncancelbuttonbordercolor, q), g(e.selectioncancelbuttonborderalpha, m));
                c.selectionCancelButtonFillColor = y(h(e.selectioncancelbuttonfillcolor, "FFFFFF"), g(e.selectioncancelbuttonfillalpha, 100));
                b.chart.nativeZoom = !1;
                c.formAction = A(e.formaction);
                "0" !== e.submitdataasxml || e.formdataformat || (e.formdataformat = G.dataFormats.CSV);
                c.formDataFormat = h(e.formdataformat, G.dataFormats.XML);
                c.formTarget = h(e.formtarget, "_self");
                c.formMethod = h(e.formmethod, "POST");
                c.submitFormAsAjax = g(e.submitformusingajax, 1);
                (b.callbacks || (b.callbacks = [])).push(function() {
                    var a = this.logic;
                    Oa(this, {
                        selectionStart: function(a) {
                            var b = dc(a.chart.container, a.originalEvent),
                                b = O({
                                    selectionLeft: a.selectionLeft,
                                    selectionTop: a.selectionTop,
                                    selectionWidth: a.selectionWidth,
                                    selectionHeight: a.selectionHeight,
                                    startXValue: a.chart.xAxis[0].getAxisPosition(a.selectionLeft, 1),
                                    startYValue: a.chart.yAxis[0].getAxisPosition(a.selectionTop,
                                        1)
                                }, b);
                            G.raiseEvent("selectionStart", b, a.chart.logic.chartInstance)
                        },
                        selectionEnd: function(b) {
                            var c = dc(b.chart.container, b.originalEvent),
                                d = b.chart.xAxis[0],
                                e = b.chart.yAxis[0],
                                c = O({
                                    selectionLeft: b.selectionLeft,
                                    selectionTop: b.selectionTop,
                                    selectionWidth: b.selectionWidth,
                                    selectionHeight: b.selectionHeight,
                                    startXValue: d.getAxisPosition(b.selectionLeft, 1),
                                    startYValue: e.getAxisPosition(b.selectionTop, 1),
                                    endXValue: d.getAxisPosition(b.selectionLeft + b.selectionWidth, 1),
                                    endYValue: e.getAxisPosition(b.selectionTop +
                                        b.selectionHeight, 1)
                                }, c);
                            G.raiseEvent("selectionEnd", c, b.chart.logic.chartInstance);
                            a.createSelectionBox(b)
                        }
                    })
                });
                b.chart.zoomType = "xy";
                return a
            }
        }, s.scatterbase);
        s("multiaxisline", {
            friendlyName: "Multi-axis Line Chart",
            standaloneInit: !0,
            creditLabel: ta,
            defaultSeriesType: "line",
            rendererId: "multiaxisline",
            isMLAxis: !0,
            canvasPaddingModifiers: ["anchor", "anchorlabel"],
            drawAxisTrackerAndCheckBox: function() {
                for (var b = this, d = b.canvasLeft, a = b.canvasTop, c = b.canvasWidth, e = b.canvasHeight, f = b.paper, q = b.yAxis, m = q.length,
                        h = b.logic, l = 0, p = 0, k = {
                            cursor: "col-resize",
                            _cursor: "e-resize",
                            "*cursor": "e-resize"
                        }, n = h.chartInstance, r = n.jsVars, t = h.dataObj, z = r._reflowData, D = z.hcJSON || {}, w = t.axis, C = t.chart, t = g(C.allowaxisshift, 1), H = (C = g(C.allowselection, 1)) && f.html("div", {
                            fill: "transparent",
                            width: b.chartWidth
                        }, {
                            top: "",
                            left: "",
                            fontSize: "10px",
                            lineHeight: "15px",
                            marginTop: -b.chartHeight + "px"
                        }, b.container), D = D.yAxis || (D.yAxis = []), $, ja, u, E, s, I, B = function(a) {
                            b.series && b.series[a] && b.series[a].setVisible(!1, !1)
                        }, x = function(a) {
                            var c = a.data;
                            a = c.axis[c.index].axisData;
                            var d = a._relatedSeries,
                                e = !c.checkBox.checked(),
                                c = w[a._axisposition];
                            d && lb(d, function(a) {
                                b.options.series[a].legendClick(e, !0)
                            });
                            c.hidedataplots = !e;
                            O(z, {
                                preReflowAdjustments: function() {
                                    this.dataObj.axis = w
                                }
                            });
                            G.raiseEvent("AxisSelected", {
                                selected: e,
                                AxisId: c._index,
                                AxisConfiguration: a._origAttr || tb(c, a)
                            }, b.logic.chartInstance)
                        }, ra = function(a) {
                            var c = a.data;
                            a = c.axis;
                            var c = a[c.index].axisData,
                                d = c.opposite,
                                e = c._axisposition,
                                f = w.length,
                                q, k, m, l = {},
                                p = w[e],
                                t = {};
                            for (q = 0; q < f; q += 1) k =
                                w[q], k = !g(k.axisonleft, 1), k === d && (m = q, d && (q = f));
                            m !== e && (l = a[m], t = w[m], a = w.splice(m, 1, w[e]), w.splice(e, 1, a[0]));
                            if (m !== e || d !== h.dataObj.chart._lastClickedOpp) O(z, {
                                preReflowAdjustments: function() {
                                    this.dataObj.chart._lastClickedOpp = d;
                                    this.dataObj.axis = w
                                }
                            }), G.raiseEvent("AxisShifted", {
                                previousDefaultAxisId: t._index,
                                newDefaultAxisId: p._index,
                                previousDefaultAxisConfiguration: l._origAttr || tb(t, l),
                                newDefaultAxisConfiguration: c._origAttr || tb(p, c)
                            }, b.logic.chartInstance), G.hcLib.createChart(n, r.container, r.type,
                                void 0, void 0, !1, !0)
                        }; m--;) $ = q[m], ja = $.axisData, u = ja._axisWidth, (E = ja.opposite) || (l += u), D[m] || (D[m] = {}), C && ja.showAxis && (s = d + (E ? c + p + g(ja.title.margin, u - 10) + 5 : -l), I = a + e + 10, $.checkBox = f.html("input", {}, {
                    left: s + "px",
                    top: I + "px"
                }).attr({
                    type: "checkbox",
                    name: "axis[]",
                    value: ja.title.text || ""
                }).add(H), $.checkBox.val(ja.hidedataplots), ja.hidedataplots || ja._relatedSeries && lb(ja._relatedSeries, B), pb($.checkBox.element, qa ? "touchstart" : "mousedown", x, {
                    axis: q,
                    index: m,
                    checkBox: $.checkBox
                })), t && ($.tracker = f.rect(d +
                    (E ? c + p : -l), a, u, e, 0).attr({
                    "stroke-width": 0,
                    fill: ua,
                    isTracker: +new Date,
                    zIndex: 7
                }).css(k), E && (p += u), pb($.tracker[0], qa ? "touchstart" : "mousedown", ra, {
                    axis: q,
                    index: m
                }))
            },
            series: function(b) {
                var d = this,
                    a = d.numberFormatter,
                    c = d.name,
                    e = d.dataObj,
                    f = e.chart,
                    q = e.axis,
                    m = d.hcJSON,
                    v = m[na],
                    l = m.yAxis[0],
                    p = g(e.chart.allowselection, 1),
                    k = [],
                    n = g(f.showaxisnamesinlegend, 0),
                    r = g(f.yaxisvaluesstep, f.yaxisvaluestep, 1),
                    t = this.colorManager,
                    z, D, w, C, H, $, ja, u, E, s, I, ka, x, ra, A;
                m.callbacks || (m.callbacks = []);
                m.callbacks.push(function() {
                    d.drawAxisTrackerAndCheckBox.call(this)
                });
                m.legend.enabled = Boolean(g(e.chart.showlegend, 1));
                if (q && 0 < q.length) {
                    this.categoryAdder(e, m);
                    m.yAxis.splice(0, 2);
                    $ = v.noHiddenAxis = 0;
                    for (u = q.length; $ < u; $ += 1) E = q[$], void 0 === E._index && (E._index = $), E._axisposition = $, (C = !g(E.axisonleft, 1)) ? (E._isSY = !0, k.unshift(E)) : (E._isSY = !1, k.push(E));
                    $ = 0;
                    for (u = k.length; $ < u; $ += 1)
                        if (E = k[$], H = g(E.showaxis, 1), q = E._index || 0, a.parseMLAxisConf(E, q), z = t.getPlotColor(q), E.id = q, x = h(E.color, f.axiscolor, z), s = y(x, 100), C = !g(E.axisonleft, 1), I = g(E.divlinethickness, f.divlinethickness,
                                1), D = H ? g(E.tickwidth, f.axistickwidth, 2) : 0, w = H ? g(E.axislinethickness, f.axislinethickness, 2) : 0, ka = v[$] = {}, ka.showAxis = H, v.noHiddenAxis += 1 - H, H && (C ? A = $ : ra = $), ja = [], m.yAxis.push({
                                startOnTick: !1,
                                endOnTick: !1,
                                _axisposition: E._axisposition,
                                _isSY: E._isSY,
                                _index: q,
                                hidedataplots: !g(E.hidedataplots, 0),
                                title: {
                                    enabled: H,
                                    style: l.title.style,
                                    text: H ? F(E.title) : B,
                                    align: p ? "low" : "middle",
                                    textAlign: p && C ? "right" : void 0
                                },
                                labels: {
                                    x: 0,
                                    style: l.labels.style
                                },
                                plotBands: [],
                                plotLines: [],
                                gridLineColor: y(h(E.divlinecolor, x), g(E.divlinealpha,
                                    f.divlinealpha, t.getColor("divLineAlpha"), 100)),
                                gridLineWidth: I,
                                gridLineDashStyle: g(E.divlinedashed, E.divlineisdashed, f.divlinedashed, f.divlineisdashed, 0) ? Ha(g(E.divlinedashlen, f.divlinedashlen, 4), g(E.divlinedashgap, f.divlinedashgap, 2), I) : "none",
                                alternateGridColor: ha,
                                lineColor: s,
                                lineWidth: w,
                                tickLength: D,
                                tickColor: s,
                                tickWidth: w,
                                opposite: C,
                                _relatedSeries: ja,
                                showAxis: H
                            }), ka.yAxisValuesStep = g(E.yaxisvaluesstep, E.yaxisvaluestep, r), ka.maxValue = E.maxvalue, ka.tickWidth = D, ka.minValue = E.minvalue, ka.setadaptiveymin =
                            g(E.setadaptiveymin, f.setadaptiveymin), ka.numDivLines = g(E.numdivlines, f.numdivlines, 4), ka.adjustdiv = g(E.adjustdiv, f.adjustdiv), ka.showYAxisValues = H ? g(E.showyaxisvalues, E.showyaxisvalue, f.showyaxisvalues, f.showyaxisvalue, 1) : 0, ka.showLimits = H ? g(E.showlimits, f.showyaxislimits, f.showlimits, ka.showYAxisValues) : 0, ka.showDivLineValues = H ? g(E.showdivlinevalue, f.showdivlinevalues, E.showdivlinevalues, ka.showYAxisValues) : 0, ka.showzeroplane = E.showzeroplane, ka.showzeroplanevalue = g(E.showzeroplanevalue), ka.zeroplanecolor =
                            E.zeroplanecolor, ka.zeroplanethickness = E.zeroplanethickness, ka.zeroplanealpha = E.zeroplanealpha, ka.linecolor = h(E.linecolor, f.linecolor || E.color, z), ka.linealpha = E.linealpha, ka.linedashed = E.linedashed, ka.linethickness = E.linethickness, ka.linedashlen = E.linedashlen, ka.linedashgap = E.linedashgap, ka.anchorShadow = E.anchorshadow, ka.plottooltext = E.plottooltext, E.dataset && 0 < E.dataset.length) {
                            I = E.dataset.length;
                            z = g(E.includeinlegend, 1);
                            C = !1;
                            H = {
                                data: [],
                                relatedSeries: ja,
                                name: F(E.title),
                                type: "line",
                                marker: {
                                    symbol: "axisIcon",
                                    fillColor: ua,
                                    lineColor: jc(x, 80).replace(ob, Qa)
                                },
                                lineWidth: 0,
                                legendFillColor: 0 !== n ? y(x, 25) : void 0,
                                legendFillOpacity: 0,
                                legendIndex: E._index,
                                showInLegend: Boolean(g(n, z))
                            };
                            m.series.push(H);
                            for (s = 0; s < I; s += 1) {
                                w = E.dataset[s];
                                w._yAxisName = E.title;
                                void 0 === w.color && (w.color = h(ka.linecolor, x));
                                D = {
                                    visible: !g(w.initiallyhidden, 0),
                                    yAxis: $,
                                    data: [],
                                    hoverEffects: this.parseSeriesHoverOptions(b, m, w, c)
                                };
                                D = this.point(c, D, w, e.chart, m, v.oriCatTmp.length, $, q);
                                D.legendFillColor = H.legendFillColor;
                                D.legendIndex = E._index;
                                if (void 0 ===
                                    D.showInLegend || D.showInLegend) C = !0;
                                !1 !== D.showInLegend && (D.showInLegend = Boolean(z));
                                ja.push(m.series.length);
                                m.series.push(D)
                            }
                            0 !== ja.length && C || (H.showInLegend = !1)
                        }
                    b = f._lastClickedOpp ? g(A, ra) : g(ra, A);
                    $ = 0;
                    for (u = m.yAxis.length; $ < u; $ += 1) $ != b && (m.yAxis[$].gridLineWidth = 0, v[$].zeroplanethickness = 0);
                    this.configureAxis(m, e)
                }
            },
            point: function(b, d, a, c, e, f, q, m) {
                b = !1;
                q = g(c.ignoreemptydatasets, 0);
                var v;
                v = e.chart;
                var l = a.data || [],
                    p = e[na],
                    k = p[d.yAxis || 0],
                    n = h(d.type, this.defaultSeriesType),
                    r = e.plotOptions[n] && e.plotOptions[n].stacking,
                    t = h(this.isValueAbs, p.isValueAbs, !1),
                    z = g(d.yAxis, 0),
                    D = this.numberFormatter,
                    w = this.colorManager,
                    C = V(h(a.color, k.linecolor, c.linecolor, w.getPlotColor())),
                    H = g(a.alpha, k.linealpha, c.linealpha, Fa),
                    $ = g(c.showshadow, this.defaultPlotShadow, 1),
                    ja = g(a.drawanchors, a.showanchors, c.drawanchors, c.showanchors),
                    u = g(a.anchorsides, c.anchorsides, 0),
                    E = g(a.anchorstartangle, c.anchorstartangle, 90),
                    s = g(a.anchorradius, c.anchorradius, 3),
                    I = V(h(a.anchorbordercolor, c.anchorbordercolor, C)),
                    x = g(a.anchorborderthickness, c.anchorborderthickness,
                        1),
                    w = V(h(a.anchorbgcolor, c.anchorbgcolor, w.getColor("anchorBgColor"))),
                    y = h(a.anchoralpha, c.anchoralpha, Fa),
                    ra = h(a.anchorbgalpha, c.anchorbgalpha, y);
                d.anchorShadow = y && h(a.anchorshadow, k.anchorShadow, c.anchorshadow, 0);
                d.name = A(a.seriesname);
                if (0 === g(a.includeinlegend) || void 0 === d.name || 0 === H && 1 !== ja) d.showInLegend = !1;
                d.marker = {
                    fillColor: {
                        FCcolor: {
                            color: w,
                            alpha: ra * y / 100 + B
                        }
                    },
                    lineColor: {
                        FCcolor: {
                            color: I,
                            alpha: y + B
                        }
                    },
                    lineWidth: x,
                    radius: s,
                    symbol: da(u),
                    startAngle: E
                };
                d.color = {
                    FCcolor: {
                        color: C,
                        alpha: H
                    }
                };
                d.shadow =
                    $ ? {
                        opacity: $ ? H / 100 : 0
                    } : !1;
                d.step = this.stepLine;
                d.drawVerticalJoins = Boolean(g(c.drawverticaljoins, 1));
                d.useForwardSteps = Boolean(g(c.useforwardsteps, 1));
                d.lineWidth = g(a.linethickness, k.linethickness, c.linethickness, 2);
                c = d._dataParser = Db.line(e, {
                    plottooltext: h(a.plottooltext, k.plottooltext),
                    seriesname: d.name,
                    lineAlpha: H,
                    anchorAlpha: y,
                    showValues: g(a.showvalues, p.showValues),
                    yAxis: m,
                    lineDashed: Boolean(g(a.dashed, k.linedashed, c.linedashed, 0)),
                    lineDashLen: g(a.linedashlen, k.linedashlen, c.linedashlen, 5),
                    lineDashGap: g(a.linedashgap,
                        k.linedashgap, c.linedashgap, 4),
                    lineThickness: d.lineWidth,
                    lineColor: C,
                    valuePosition: h(a.valueposition, v.valuePosition),
                    drawAnchors: ja,
                    anchorShadow: d.anchorShadow,
                    anchorBgColor: w,
                    anchorBgAlpha: ra,
                    anchorBorderColor: I,
                    anchorBorderThickness: x,
                    anchorRadius: s,
                    anchorSides: u,
                    anchorAngle: E,
                    _sourceDataset: a,
                    _yAxisName: a._yAxisName,
                    hoverEffects: d.hoverEffects
                }, this);
                delete a._yAxisName;
                for (m = 0; m < f; m += 1)(v = l[m]) ? (a = D.getCleanValue(v.value, t), null === a ? d.data.push({
                    y: null
                }) : (b = !0, d.data.push(c(v, m, a)), this.pointValueWatcher(e,
                    a, z, r, m, 0, n))) : d.data.push({
                    y: null
                });
                !q || b || this.realtimeEnabled || (d.showInLegend = !1);
                return d
            },
            configureAxis: function(b, d) {
                var a = b[na],
                    c = d.chart,
                    e, f, q, m, h, l, p, k, n, r, t, z, D;
                b.xAxis.title.text = F(c.xaxisname);
                D = 0;
                for (f = b.yAxis.length; D < f; D += 1) e = b.yAxis[D], q = a[D], z = g(q.yAxisValuesStep, 1), z = 1 > z ? 1 : z, m = q.maxValue, h = q.minValue, l = g(q.setadaptiveymin, 0), p = l = !l, k = q.numDivLines, n = 0 !== q.adjustdiv, r = q.showLimits, t = q.showDivLineValues, this.axisMinMaxSetter(e, q, m, h, l, p, k, n), this.configurePlotLines(c, b, e, q, r, t, z,
                    this.numberFormatter, e._isSY, void 0, e._index), e.reversed && 0 <= e.min && (b.plotOptions.series.threshold = e.max)
            },
            spaceManager: function(b, d, a, c) {
                var e = b[na],
                    f, q, m = d.chart,
                    v, l, p, k, n, r, t, z, D, w, C, H, $, ja;
                ja = b.chart.marginLeft;
                var u = b.chart.marginRight,
                    E = e.marginLeftExtraSpace,
                    s = e.marginTopExtraSpace,
                    I = e.marginBottomExtraSpace,
                    B = e.marginRightExtraSpace;
                n = a - (E + B + b.chart.marginRight + b.chart.marginLeft);
                var x = c - (I + b.chart.marginBottom + b.chart.marginTop),
                    y = .3 * n;
                c = .3 * x;
                var F = n - y,
                    P = x - c,
                    A = h(m.legendposition, Ia).toLowerCase();
                b.legend.enabled && A === Aa && (F -= this.placeLegendBlockRight(b, d, F / 2, x));
                q = b.yAxis;
                k = q.length;
                f = k - e.noHiddenAxis;
                t = 0;
                if (f)
                    for (w = z = 0, C = 10, $ = F / f, r = k - 1; 0 <= r; --r) D = q[r], D.showAxis && (f = e[r], p = D.opposite, H = (p ? w : z) + C, v = f.tickWidth, l = h(m.rotateyaxisname, p ? "cw" : "ccw"), f.verticalAxisNamePadding = 4, f.fixedValuesPadding = v, f.verticalAxisValuesPadding = v, f.rotateVerticalAxisName = p && "ccw" !== l ? "cw" : l, f.verticalAxisNameWidth = 50, D.offset = H, t = $ + t - C, f = ib(D, f, b, d, x, t, p, 0, 0), f += C, p ? (w += f, b.chart.marginRight += C) : (z += f, b.chart.marginLeft +=
                        C), t -= f, F -= f, F < C && (C = 0), D._axisWidth = f);
                F -= Pb(b, d, F);
                q = F + y;
                b.legend.enabled && A !== Aa && (P -= this.placeLegendBlockBottom(b, d, n, P / 2), b.legend.width > q && (b.legend.x = 0));
                P -= this.titleSpaceManager(b, d, q, P / 2);
                f = e.x;
                f.horizontalAxisNamePadding = g(m.xaxisnamepadding, 5);
                f.horizontalLabelPadding = g(m.labelpadding, 2);
                f.labelDisplay = "1" == m.rotatelabels ? "rotate" : h(m.labeldisplay, "auto").toLowerCase();
                f.staggerLines = g(m.staggerlines, 2);
                f.slantLabels = g(m.slantlabels, m.slantlabel, 0);
                n = {
                    left: 0,
                    right: 0
                };
                n = b.chart.managePlotOverflow &&
                    this.canvasPaddingModifiers && this.calculateCanvasOverflow(b, !0) || n;
                r = n.left + n.right;
                t = .6 * q;
                r > t && (z = n.left / r, n.left -= z * (r - t), n.right -= (1 - z) * (r - t));
                this.xAxisMinMaxSetter(b, d, q, n.left, n.right);
                P -= Cb(b.xAxis, f, b, d, q, P, y);
                P -= bb(b, d, P, b.xAxis);
                d = c + P;
                for (r = 0; r < k; r += 1) Za(d, b, m, b.yAxis[r], e[r].lYLblIdx);
                b.legend.enabled && A === Aa && (e = b.legend, m = c + P, e.height > m && (e.height = m, e.scroll.enabled = !0, t = (e.scroll.scrollBarWidth = 10) + (e.scroll.scrollBarPadding = 2), e.width += t, b.chart.marginRight += t), e.y = 20);
                m = (e = b.title.alignWithCanvas) ?
                    b.chart.marginLeft + q / 2 : a / 2;
                ja = e ? b.chart.marginLeft : ja;
                a = e ? a - b.chart.marginRight : a - u;
                switch (b.title.align) {
                    case Ja:
                        b.title.x = ja;
                        b.title.align = "start";
                        break;
                    case Aa:
                        b.title.x = a;
                        b.title.align = "end";
                        break;
                    default:
                        b.title.x = m, b.title.align = "middle"
                }
                switch (b.subtitle.align) {
                    case Ja:
                        b.subtitle.x = ja;
                        break;
                    case Aa:
                        b.subtitle.x = a;
                        break;
                    default:
                        b.subtitle.x = m
                }
                b.chart.marginLeft += E;
                b.chart.marginTop += s;
                b.chart.marginBottom += I;
                b.chart.marginRight += B
            }
        }, s.mslinebase);
        s("candlestick", {
            friendlyName: "Candlestick Chart",
            standaloneInit: !0,
            creditLabel: ta,
            paletteIndex: 3,
            defaultSeriesType: "candlestick",
            canvasborderthickness: 1,
            rendererId: "candlestick",
            chart: s.errorbar2d.chart,
            drawErrorValue: s.errorbar2d.drawErrorValue,
            series: function(b, d, a) {
                var c, e, f = d[na],
                    q, m, v, l, p, k;
                c = b.chart;
                q = d.chart;
                var n = g(c.showvolumechart, 1);
                m = this.colorManager;
                var r;
                d.legend.enabled = Boolean(g(c.showlegend, 1));
                q.rollOverBandColor = y(h(c.rolloverbandcolor, m.getColor("altHGridColor")), h(c.rolloverbandalpha, m.getColor("altHGridAlpha")));
                if (b.dataset &&
                    0 < b.dataset.length) {
                    this.categoryAdder(b, d);
                    d.yAxis[0].opposite = !0;
                    f.numdivlines = A(b.chart.numpdivlines);
                    n && (r = d._FCconf.numberFormatter, q = d.labels, d._FCconf.numberFormatter = {}, d._FCconf.smartLabel && (e = d._FCconf.smartLabel, d._FCconf.smartLabel = void 0), d.labels = {}, v = O({}, d), d._FCconf.numberFormatter = r, d._FCconf.smartLabel = e, d.labels = q, e && (v._FCconf.smartLabel = e), v._FCconf.numberFormatter = new u.NumberFormatter(O(O({}, c), {
                            forcedecimals: ea(c.forcevdecimals, c.forcedecimals),
                            forceyaxisvaluedecimals: ea(c.forcevyaxisvaluedecimals,
                                c.forceyaxisvaluedecimals),
                            yaxisvaluedecimals: ea(c.vyaxisvaluedecimals, c.yaxisvaluedecimals),
                            formatnumber: ea(c.vformatnumber, c.formatnumber),
                            formatnumberscale: ea(c.vformatnumberscale, c.formatnumberscale),
                            defaultnumberscale: ea(c.vdefaultnumberscale, c.defaultnumberscale),
                            numberscaleunit: ea(c.vnumberscaleunit, c.numberscaleunit),
                            vnumberscalevalue: ea(c.vnumberscalevalue, c.numberscalevalue),
                            scalerecursively: ea(c.vscalerecursively, c.scalerecursively),
                            maxscalerecursion: ea(c.vmaxscalerecursion, c.maxscalerecursion),
                            scaleseparator: ea(c.vscaleseparator, c.scaleseparator),
                            numberprefix: ea(c.vnumberprefix, c.numberprefix),
                            numbersuffix: ea(c.vnumbersuffix, c.numbersuffix),
                            decimals: ea(c.vdecimals, c.decimals)
                        }), this), O(v, {
                            chart: {
                                backgroundColor: "rgba(255,255,255,0)",
                                borderColor: "rgba(255,255,255,0)",
                                animation: !1
                            },
                            title: {
                                text: null
                            },
                            subtitle: {
                                text: null
                            },
                            legend: {
                                enabled: !1
                            },
                            credits: {
                                enabled: !1
                            },
                            xAxis: {
                                opposite: !0,
                                labels: {
                                    enabled: !1
                                }
                            },
                            yAxis: [{
                                opposite: !0,
                                title: {},
                                plotBands: [],
                                plotLines: []
                            }, {
                                opposite: !1,
                                title: {
                                    text: b.chart.vyaxisname
                                }
                            }]
                        }),
                        r = d.subCharts = [v]);
                    c = 0;
                    for (e = b.dataset.length; c < e; c += 1) q = {
                        numColumns: e,
                        data: []
                    }, m = b.dataset[c], q = this.point(a, q, m, b.chart, d, f.oriCatTmp.length, c), q instanceof Array ? (n && (v.series.push({
                            type: "column",
                            data: q[1]
                        }), v.showVolume = !0, m = g(b.chart.volumeheightpercent, 40), m = 20 > m ? 20 : 80 < m ? 80 : m, l = f.height - (d.chart.marginBottom + d.chart.marginTop), p = l * m / 100, k = d.chart.marginBottom + p, v[na].marginTop = k + 40, v.yAxis[0].plotBands = [], v.yAxis[0].plotLines = [], v.exporting.enabled = !1, v.yAxis[0].title.text = F(A(b.chart.vyaxisname)),
                        v.yAxis[0].title.align = "low", v.chart.height = p + 20, v.chart.width = f.width, v.chart.top = l - p, v.chart.left = 0, v.chart.volumeHeightPercent = m), d.series.push(q[0])) : (d.series.push(q), r = d.subCharts = void 0);
                    if (b.trendset && 0 < b.trendset.length)
                        for (c = 0, e = b.trendset.length; c < e; c += 1) q = {
                            type: "line",
                            marker: {
                                enabled: !1
                            },
                            connectNullData: 1,
                            data: []
                        }, v = b.trendset[c], v.data && 0 < v.data.length && (q = this.getTrendsetPoint(a, q, v, b.chart, d, f.oriCatTmp.length, c), d.series.push(q));
                    b.chart.showdivlinesecondaryvalue = 0;
                    b.chart.showsecondarylimits =
                        0;
                    this.configureAxis(d, b);
                    d.yAxis[1].opposite = !1;
                    d.yAxis[1].min = d.yAxis[0].min;
                    d.yAxis[1].max = d.yAxis[0].max;
                    d.yAxis[1].title.text = d.yAxis[0].title.text;
                    d.yAxis[0].title.text = B;
                    n && r && (r = r[0], a = r[na], a.numdivlines = A(b.chart.numvdivlines), a[0].min = f.volume && f.volume.min, a[0].max = f.volume && f.volume.max, r.series && r.series[0] && (r.series[0].showInLegend = !1), this.configureAxis(r, b), r.yAxis[0].title.text = F(A(b.chart.vyaxisname)), r.yAxis[1].min = r.yAxis[0].min, r.yAxis[1].max = r.yAxis[0].max, r.yAxis[1].title.text =
                        r.yAxis[0].title.text, r.yAxis[0].title.text = B);
                    if ((a = b.trendlines && b.trendlines[0] && b.trendlines[0].line) && a.length) {
                        for (n = 0; n < a.length; n += 1) a[n].parentyaxis = "s", a[n].valueonleft = "1";
                        Zb(b.trendlines, d.yAxis, f, !0, this.isBar)
                    }
                }
            },
            getTrendsetPoint: function(b, d, a, c, e) {
                if (a.data) {
                    b = a.data;
                    var f = b.length,
                        q = 0,
                        m, v, l, p, k, n = e[na],
                        r = this.numberFormatter,
                        t = g(d.yAxis, 0),
                        n = n.toolTextStore,
                        f = V(h(a.color, c.trendsetcolor, "666666")),
                        q = h(a.alpha, c.trendsetalpha, "100");
                    m = g(a.thickness, c.trendsetthickness, 2);
                    v = Boolean(g(a.dashed,
                        c.trendsetdashed, 0));
                    l = g(a.dashlen, c.trendsetdashlen, 4);
                    p = g(a.dashgap, c.trendsetdashgap, 4);
                    k = h(a.includeinlegend, 1);
                    d.color = y(f, q);
                    d.lineWidth = m;
                    d.dashStyle = v ? Ha(l, p) : "none";
                    d.includeInLegend = k;
                    d.name = A(a.name);
                    d.doNotUseBand = !0;
                    if (0 === g(a.includeinlegend) || void 0 === d.name) d.showInLegend = !1;
                    d.tooltip = {
                        enabled: !1
                    };
                    q = c.interactivelegend = 0;
                    for (f = b.length; q < f; q += 1)(c = b[q]) && !c.vline && (a = r.getCleanValue(c.value), c = r.getCleanValue(c.x), c = null !== c ? c : q + 1, m = n && n[c], d.data.push({
                        x: c,
                        y: a,
                        toolText: m
                    }), this.pointValueWatchers(e,
                        null, a, a, null, t))
                }
                return d
            },
            point: function(b, d, a, c, e) {
                if (a.data) {
                    b = s[b];
                    var f = e[na],
                        q = A(c.plotpriceas, B).toLowerCase(),
                        m = a.data,
                        v = m && m.length,
                        l = this.numberFormatter,
                        p = [],
                        k = [],
                        n = {},
                        r, t, z, D = !1,
                        w = g(d.yAxis, 0),
                        C = V(h(c.bearbordercolor, "B90000")),
                        H = V(h(c.bearfillcolor, "B90000")),
                        $ = this.colorManager,
                        ja = V(h(c.bullbordercolor, $.getColor("canvasBorderColor"))),
                        u = V(h(c.bullfillcolor, "FFFFFF")),
                        E = d.lineWidth = g(c.plotlinethickness, "line" == q || "bar" == q ? 2 : 1),
                        x = h(c.plotlinealpha, "100"),
                        I = g(c.plotlinedashlen, 5),
                        ka =
                        g(c.plotlinedashgap, 4),
                        eb = g(c.vplotborderthickness, 1),
                        ra = !!g(c.drawanchors, 1),
                        G = g(c.anchorsides, 0),
                        P = g(c.anchorstartangle, 90),
                        O = g(c.anchorradius, this.anchorRadius, 3),
                        T = V(h(c.anchorbordercolor, ja)),
                        M = g(c.anchorborderthickness, this.anchorBorderThickness, 1),
                        $ = V(h(c.anchorbgcolor, $.getColor("anchorBgColor"))),
                        K = h(c.anchoralpha, "0"),
                        Z = h(c.anchorbgalpha, K),
                        U, N, Q, aa, Y, W, ca, L, ba, fa, X, ea, ia, ma, ha, pa = !1;
                    d.name = A(a.seriesname);
                    d.showInLegend = !1;
                    d.marker = {};
                    switch (q) {
                        case "line":
                            d.plotType = "line";
                            break;
                        case "bar":
                            d.plotType =
                                "candlestickbar";
                            break;
                        default:
                            d.plotType = "column", d.errorBarWidthPercent = 0, pa = !0
                    }
                    for (N = 0; N < v; N += 1)(Q = m[N]) && !Q.vline && (ca = l.getCleanValue(Q.open), L = l.getCleanValue(Q.close), ba = l.getCleanValue(Q.high), fa = l.getCleanValue(Q.low), X = l.getCleanValue(Q.volume, !0), ma = l.getCleanValue(Q.x), pa && S(L - ca), J(ca, L), Ya(ca, L), null !== X && (D = !0), ea = J(ca, L, ba, fa), ia = Ya(ca, L, ba, fa), F(A(Q.valuetext, B)), r = V(h(Q.bordercolor, L < ca ? C : ja)), t = h(Q.alpha, "100"), q = y(V(h(Q.color, L < ca ? H : u)), t), z = Boolean(g(Q.dashed)) ? Ha(I, ka) : "none",
                        U = {
                            opacity: t / 100
                        }, Y = f.oriCatTmp[N], ha = y(r, x), aa = b.getPointStub(e, c, Q, ca, L, ba, fa, X, ha, E, d.plotType, Y), ma = ma ? ma : N + 1, n[ma] = aa.toolText, d.data.push({
                            high: Ya(ca, L, ba, fa),
                            low: J(ca, L, ba, fa),
                            color: pa ? q : {
                                FCcolor: {
                                    color: r,
                                    alpha: t
                                }
                            },
                            displayValue: F(h(Q.displayvalue, Q.valuetext, B)),
                            borderColor: ha,
                            shadow: U,
                            dashStyle: z,
                            borderWidth: E,
                            x: ma,
                            y: aa.y,
                            categoryLabel: Y,
                            errorValue: aa.errorValue,
                            previousY: aa.previousY,
                            toolText: aa.toolText,
                            link: aa.link,
                            marker: {
                                enabled: ra,
                                fillColor: {
                                    FCcolor: {
                                        color: $,
                                        alpha: Z * K / 100 + B
                                    }
                                },
                                lineColor: {
                                    FCcolor: {
                                        color: T,
                                        alpha: K
                                    }
                                },
                                lineWidth: M,
                                radius: O,
                                startAngle: P,
                                symbol: da(G)
                            }
                        }), W = A(F(h(Q.volumetooltext, a.volumetooltext, c.volumetooltext))), W = void 0 !== W ? b.getPointStub(e, c, Q, ca, L, ba, fa, X, ha, E, d.plotType, Y, W).toolText : aa.toolText, k.push({
                            y: X,
                            categoryLabel: Y,
                            color: y(q, t),
                            toolText: W,
                            borderWidth: eb,
                            borderColor: y(r, h(c.plotlinealpha, Q.alpha)),
                            dashStyle: z,
                            shadow: U,
                            x: ma,
                            link: Q.link
                        }), this.pointValueWatchers(e, ma, ea, ia, X, w));
                    f.toolTextStore = n;
                    (d.drawVolume = D) ? p.push(d, k): p = d;
                    return p
                }
                return []
            },
            getPointStub: function(b, d, a,
                c, e, f, g, m, v, l, p, k, n) {
                var r = B,
                    r = b[na],
                    t = r.numberFormatter,
                    z = "line" === p,
                    D = J(c, e),
                    w = Ya(c, e),
                    C = {};
                b = b.subCharts && b.subCharts[0] && b.subCharts[0][na].numberFormatter || t;
                switch (p) {
                    case "line":
                        C.y = e;
                        C.link = h(a.link);
                        break;
                    case "column":
                        C.y = S(e - c);
                        C.previousY = D;
                        C.link = h(a.link);
                        C.errorValue = [];
                        0 < f - w && C.errorValue.push({
                            errorValue: f - w,
                            errorStartValue: w,
                            errorBarColor: v,
                            errorBarThickness: l,
                            opacity: 1
                        });
                        0 > g - D && C.errorValue.push({
                            errorValue: g - D,
                            errorStartValue: D,
                            errorBarColor: v,
                            errorBarThickness: l,
                            opacity: 1
                        });
                        break;
                    default:
                        C.y = c, C.previousY = e, C.link = h(a.link)
                }
                r.showTooltip ? (r = A(F(h(n, a.tooltext, r.tooltext))), void 0 !== r ? r = ab(r, [3, 5, 6, 10, 54, 55, 56, 57, 58, 59, 60, 61, 81, 82], {
                    label: k,
                    yaxisName: F(d.yaxisname),
                    xaxisName: F(d.xaxisname),
                    openValue: a.open,
                    openDataValue: t.dataLabels(c),
                    closeValue: a.close,
                    closeDataValue: t.dataLabels(e),
                    highValue: a.high,
                    highDataValue: t.dataLabels(f),
                    lowValue: a.low,
                    lowDataValue: t.dataLabels(g),
                    volumeValue: a.volume,
                    volumeDataValue: t.dataLabels(m)
                }, a, d) : (r = null === c || z ? B : "<b>Open:</b> " + t.dataLabels(c) +
                    "<br/>", r += null !== e ? "<b>Close:</b> " + t.dataLabels(e) + "<br/>" : B, r += null === f || z ? B : "<b>High:</b> " + t.dataLabels(f) + "<br/>", r += null === g || z ? B : "<b>Low:</b> " + t.dataLabels(g) + "<br/>", r += null !== m ? "<b>Volume:</b> " + b.dataLabels(m) : B)) : r = B;
                C.toolText = r;
                return C
            },
            pointValueWatchers: function(b, d, a, c, e, f) {
                var q = b[na];
                f = g(f, 0);
                null !== e && (b = q.volume, b || (b = q.volume = {}), b.max = b.max > e ? b.max : e, b.min = b.min < e ? b.min : e);
                null !== a && (b = q[f], !b.max && 0 !== b.max && (b.max = a), !b.min && 0 !== b.min && (b.min = a), b.max = Ya(b.max, a), b.min =
                    J(b.min, a));
                null !== c && (b = q[f], !b.max && 0 !== b.max && (b.max = c), !b.min && 0 !== b.min && (b.min = c), b.max = Ya(b.max, c), b.min = J(b.min, c));
                null !== d && (a = q.x, a.max = a.max > d ? a.max : d, a.min = a.min < d ? a.min : d)
            },
            spaceManager: function(b, d, a, c) {
                var e = b[na],
                    f, q = d.chart,
                    m = b.chart,
                    v, l, p = this.smartLabel || e.smartLabel,
                    k = e.x.min,
                    n = e.x.max,
                    r, t, z = c - (e.marginBottomExtraSpace + 0 + m.marginTop),
                    D = b.yAxis,
                    w;
                l = D.length;
                var C, H, $ = 0,
                    ja = 0,
                    u = 8,
                    E, s = Ya(g(m.plotBorderWidth, 1), 0),
                    I;
                this.base.spaceManager.apply(this, arguments);
                b.xAxis.min = k - .5;
                b.xAxis.max =
                    n + .5;
                b.yAxis[0].title.centerYAxis = b.yAxis[1].title.centerYAxis = !0;
                if (b.subCharts) {
                    k = b.subCharts[0];
                    $ = b.xAxis.showLine ? b.xAxis.lineThickness : s;
                    I = c - (m.marginTop + m.marginBottom + $ + s);
                    ja = k.chart.volumeHeightPercent;
                    n = (e.horizontalAxisHeight || 15) + s;
                    I = I * ja / 100;
                    m.marginBottom += I + $ + s;
                    l = O({}, b.xAxis);
                    ja = 0;
                    for (u = b.xAxis.plotBands.length; ja < u; ja += 1)(f = b.xAxis.plotBands[ja]) && f.label && f.label.text && (f.label.text = " "), (f = l.plotBands[ja]) && f.label && f.label.y && (f.label.y = Ac(q.basefontsize, 10) + 4 + $);
                    ja = 0;
                    for (u = l.plotLines.length; ja <
                        u; ja += 1)(f = l.plotLines[ja]) && f.label && f.label.text && (f.label.text = B);
                    k.yAxis && k.yAxis[0] && k.yAxis[0].title && k.yAxis[0].title.text && (k.yAxis[0].title.text = B);
                    k.xAxis = l;
                    l = h(d.chart.rotateyaxisname, "ccw");
                    l = l === Ca ? "none" : l;
                    p = D[1].title.rotation ? p.getSmartText(k.yAxis[1].title.text, "none" === l ? m.marginLeft - 10 : I, void 0, !0).text : p.getSmartText(k.yAxis[1].title.text, p.getOriSize(D[1].title.text).width, void 0, !0).text;
                    D = k.yAxis;
                    l = D.length;
                    u = ja = $ = 0;
                    for (C = l - 1; 0 <= C; --C) H = D[C], f = e[C], w = H.opposite, E = (w ? ja : $) + u,
                        l = h(d.chart.rotateyaxisname, w ? "cw" : "ccw"), l = l === Ca ? "none" : l, v = g(q.yaxisvaluespadding, q.labelypadding, 4), v < s && (v = s), f.verticalAxisNamePadding = 10, f.verticalAxisValuesPadding = v + (H.showLine ? H.lineThickness : 0), f.rotateVerticalAxisName = l, H.offset = E, w ? r = ib(H, f, k, d, z, m.marginRight, !!w, 0, 0, ja) : t = ib(H, f, k, d, z, m.marginLeft, !!w, 0, 0, $);
                    D = b.yAxis;
                    k.yAxis[1].title = O({}, b.yAxis[1].title);
                    k.yAxis[1].title.style = b.orphanStyles.vyaxisname.style;
                    k.yAxis[1].title.text = p;
                    k.chart.left = 0;
                    k.chart.width = a;
                    k.chart.top = c - m.marginBottom +
                        n;
                    k.chart.height = m.marginBottom - n;
                    r = Math.max(m.marginRight, r + m.spacingRight);
                    t = Math.max(m.marginLeft, t + m.spacingLeft);
                    k.chart.marginLeft = m.marginLeft = t;
                    k.chart.marginRight = m.marginRight = r;
                    k.chart.marginTop = 5;
                    k.chart.marginBottom = m.marginBottom - (n + I);
                    b.yAxis.push(k.yAxis[0], k.yAxis[1]);
                    k.xAxis.startY = D[2].startY = D[3].startY = k.chart.top + k.chart.marginTop;
                    k.xAxis.endY = D[2].endY = D[3].endY = k.yAxis[0].startY + k.chart.height - k.chart.marginBottom;
                    k.series[0] && (k.series[0].yAxis = 3, b.series.push(k.series[0]));
                    b.xAxis = [b.xAxis, k.xAxis];
                    b.yAxis[2].title.centerYAxis = b.yAxis[3].title.centerYAxis = !0
                }
            },
            isDual: !0,
            numVDivLines: 0,
            defSetAdaptiveYMin: !0,
            divLineIsDashed: 1,
            isCandleStick: !0,
            defaultPlotShadow: 1,
            requiredAutoNumericLabels: 1
        }, s.scatterbase);
        s("kagi", {
            friendlyName: "Kagi Chart",
            standaloneInit: !0,
            stepLine: !0,
            creditLabel: ta,
            defaultSeriesType: "kagi",
            defaultZeroPlaneHighlighted: !1,
            setAdaptiveYMin: 1,
            canvasPadding: 15,
            isKagi: 1,
            rendererId: "kagi",
            pointValueWatcher: function(b, d, a) {
                null !== d && (b = b[na], a = g(a, 0), b[a] ||
                    (b[a] = {}), a = b[a], this.maxValue = a.max = a.max > d ? a.max : d, this.minValue = a.min = a.min < d ? a.min : d)
            },
            point: function(b, d, a, c, e) {
                b = e.chart;
                var f = a.length,
                    q = 0,
                    m = e[na].x,
                    v = e[na].numberFormatter,
                    l = this.colorManager,
                    p, k, n, r, t, z, D, w, C, H, $, u, s, E, x, I, ka, y, ra, A, P, G, T;
                w = V(h(c.linecolor, c.palettecolors, l.getColor("plotFillColor")));
                C = g(c.linealpha, 100);
                p = g(c.linethickness, 2);
                d.color = {
                    FCcolor: {
                        color: w,
                        alpha: C
                    }
                };
                ra = d.anchorShadow = g(c.anchorshadow, 0);
                d.lineWidth = p;
                d.step = this.stepLine;
                d.drawVerticalJoins = Boolean(g(c.drawverticaljoins,
                    1));
                D = g(c.drawanchors, c.showanchors);
                for (k = 0; k < f; k += 1) r = a[k], r.vline || (p = v.getCleanValue(r.value), null != p && (n = g(r.showlabel, c.showlabels, 1), n = F(n ? ea(r.label, r.name) : B), q += 1, z = g(r.linealpha, C), t = {
                    opacity: z / 100
                }, H = g(r.anchorsides, c.anchorsides, 0), y = g(r.anchorstartangle, c.anchorstartangle, 90), s = g(r.anchorradius, c.anchorradius, this.anchorRadius, 3), u = V(h(r.anchorbordercolor, c.anchorbordercolor, w)), $ = g(r.anchorborderthickness, c.anchorborderthickness, this.anchorBorderThickness, 1), E = V(h(r.anchorbgcolor, c.anchorbgcolor,
                    l.getColor("anchorBgColor"))), x = h(r.anchoralpha, c.anchoralpha, "100"), I = h(r.anchorbgalpha, c.anchorbgalpha, x), z = void 0 === D ? 0 !== z : !!D, A = Boolean(g(r.anchorshadow, ra, 0)), P = h(r.anchorimageurl, c.anchorimageurl), G = h(r.anchorimagescale, c.anchorimagescale, 100), T = h(r.anchorimagealpha, c.anchorimagealpha, 100), ka = this.pointHoverOptions(r, d, {
                    plotType: "anchor",
                    anchorBgColor: E,
                    anchorAlpha: x,
                    anchorBgAlpha: I,
                    anchorAngle: y,
                    anchorBorderThickness: $,
                    anchorBorderColor: u,
                    anchorBorderAlpha: x,
                    anchorSides: H,
                    anchorRadius: s,
                    imageUrl: P,
                    imageScale: G,
                    imageAlpha: T,
                    shadow: t
                }), d.data.push(O(this.getPointStub(r, p, n, e), {
                    y: p,
                    color: w,
                    shadow: t,
                    dashStyle: r.dashed,
                    valuePosition: h(r.valueposition, b.valuePosition),
                    isDefined: !0,
                    marker: {
                        enabled: !!z,
                        shadow: A && {
                            opacity: x / 100
                        },
                        fillColor: {
                            FCcolor: {
                                color: E,
                                alpha: I * x / 100 + B
                            }
                        },
                        lineColor: {
                            FCcolor: {
                                color: u,
                                alpha: x
                            }
                        },
                        lineWidth: $,
                        radius: s,
                        startAngle: y,
                        symbol: da(H),
                        imageUrl: P,
                        imageScale: G,
                        imageAlpha: T
                    },
                    hoverEffects: ka.enabled && ka.options,
                    rolloverProperties: ka.enabled && ka.rolloverOptions
                })), this.pointValueWatcher(e,
                    p)));
                m.catCount = q;
                return d
            },
            postSeriesAddition: function(b, d) {
                var a = b.series[0],
                    c = d.chart,
                    e = d.data,
                    f = a && a.data,
                    q = f && f.length,
                    m = b[na],
                    v = m.x,
                    m = m.axisGridManager,
                    l = b.xAxis,
                    p = !1,
                    k = 0,
                    n = .5,
                    r = g(c.reversalvalue, -1),
                    q = g(c.reversalpercentage, 5),
                    t = this.maxValue,
                    z = this.minValue,
                    D, w, C, H, $, u, s, E, x, I, ka, y, ra, A, P, G, T = {};
                if (f && f.length) {
                    a.rallyColor = h(c.rallycolor, "FF0000");
                    a.rallyAlpha = g(c.rallyalpha, c.linealpha, 100);
                    a.declineColor = h(c.declinecolor, "0000FF");
                    a.declineAlpha = g(c.declinealpha, c.linealpha, 100);
                    a.rallyThickness =
                        g(c.rallythickness, c.linethickness, 2);
                    $ = g(c.rallydashlen, c.linedashlen, 5);
                    y = g(c.rallydashgap, c.linedashgap, 4);
                    a.declineThickness = g(c.declinethickness, c.linethickness, 2);
                    ra = g(c.declinedashlen, c.linedashlen, 5);
                    A = g(c.declinedashgap, c.linedashgap, 4);
                    a.lineDashed = {
                        "true": g(c.rallydashed, c.linedashed, 0),
                        "false": g(c.declinedashed, c.linedashed, 0)
                    };
                    a.rallyDashed = g(c.rallydashed, c.linedashed, 0) ? Ha($, y, a.rallyThickness) : "none";
                    a.declineDashed = g(c.declinedashed, c.linedashed, 0) ? Ha(ra, A, a.declineThickness) : "none";
                    a.canvasPadding = g(c.canvaspadding, this.canvasPadding, 15);
                    r = 0 < r ? r : q * (t - z) / 100;
                    t = f[0].y;
                    z = function(a, b) {
                        for (var c, d = 1, e = f[0].y; d < a;) c = f[d].y, b ? c <= e && (f[d].isDefined = !1) : c >= e && (f[d].isDefined = !1), d += 1;
                        f[0].vAlign = b ? Ia : Ma;
                        f[0].align = "center"
                    };
                    q = e && e.length;
                    for (A = ra = 0; A < q; A += 1)
                        if ((y = e[A]) && y.vline) ra && m.addVline(l, y, n, b);
                        else {
                            T = e[A];
                            G && (G = !1, n += .5);
                            if (ra && (x = f[ra])) {
                                I = f[ra - 1];
                                x.vAlign = "middle";
                                x.align = Aa;
                                x.showLabel = !1;
                                $ = null;
                                H = x.y;
                                C = f[ra + 1] && f[ra + 1].y;
                                ka = S(t - H);
                                p ? H < u && D ? D = !1 : H > s && !D && (D = !0) : (H > t && ka >
                                    r ? (D = !0, u = t, s = null, p = w = !0, z(ra, D)) : H < t && ka > r ? (D = !1, u = null, s = t, w = !1, p = !0, z(ra, D)) : (w = D = null, p = !1), ba(I) && (I.isRally = D), null != D && (f[0].isRally = D));
                                x.isRally = D;
                                if (w && H < t || !w && H > t) $ = t;
                                P = $ ? $ : H;
                                ka = S(P - C);
                                C = null == w ? null : w ? P > C && ka >= r : P < C && ka >= r;
                                if (I && I.isShift)
                                    for (w ? (u = t, E = Ia) : w || (s = t, E = Ma), I = ra; 1 < I; --I)
                                        if (f[I].y == t) {
                                            f[I].vAlign = E;
                                            f[I].align = "center";
                                            f[I].showLabel = !0;
                                            break
                                        }
                                C ? (k += 1, n += .5, G = !0, w = !w, x.isShift = !0, t = P, H = g(y.showlabel, c.showlabels, 1), H = F(H ? ea(y.label, y.name) : B), m.addXaxisCat(l, k - 1, k - 1, H, y, {},
                                    c)) : w && H > t || !w && H < t ? t = H : $ = t;
                                x.plotValue = $;
                                x.objParams = {
                                    isRally: D,
                                    lastHigh: s,
                                    lastLow: u,
                                    isRallyInitialised: p
                                }
                            }
                            ra += 1
                        }
                    H = g(T.showlabel, c.showlabels, 1);
                    H = F(H ? ea(T.label, T.name) : B);
                    m.addXaxisCat(l, k, k, H, T, {}, c);
                    a.shiftCount = v.catCount = k + 1
                }
            },
            xAxisMinMaxSetter: function(b, d, a) {
                var c = b[na].x,
                    e = d.chart;
                d = c.min = g(c.min, 0);
                var c = c.max = g(c.max, c.catCount - 1),
                    f = b.xAxis,
                    q = J(g(e.canvaspadding, 0), a / 2 - 10),
                    m = q,
                    h = g(e.maxhshiftpercent, 10),
                    l = b.series[0];
                b = l && l.shiftCount;
                var e = g(e.canvaspadding, this.canvasPadding, 15),
                    p = a -
                    2 * e;
                l && (m = l.xShiftLength = J(p / b, (0 >= h ? 10 : h) * p / 100), q = e + m / 2, m = a - (m * Ya(b - 1, 1) + q), c = Ya(c, 1));
                f.labels.enabled = !1;
                f.gridLineWidth = 0;
                f.alternateGridColor = ha;
                a = (a - (q + m)) / (c - d + 0);
                f.min = d - (0 + q / a);
                f.max = c + (0 + m / a)
            }
        }, s.linebase);
        Fb = function(b, d, a) {
            this.nf = d;
            this.dataSeparator = a;
            this.method = (b || B).toLowerCase().replace(/\s/g, "")
        };
        Fb.prototype = {
            setArray: function(b) {
                var d = this.nf,
                    a = this.dataSeparator,
                    c = 0;
                !b && (b = B);
                for (b = this.dataLength = (a = b.replace(/\s/g, B).split(a)) && a.length; b--;) c += a[b] = d.getCleanValue(a[b]);
                a && a.sort(function(a, b) {
                    return a - b
                });
                this.values = a;
                this.mean = c / this.dataLength;
                this.getFrequencies()
            },
            getQuartiles: function() {
                var b = this.values,
                    d = this.dataLength,
                    a = d % 2,
                    c, e;
                switch (this.method) {
                    case "tukey":
                        a ? (a = (d + 3) / 4, d = (3 * d + 1) / 4) : (a = (d + 2) / 4, d = (3 * d + 2) / 4);
                        break;
                    case "mooremccabe":
                        a ? (a = (d + 1) / 4, d = 3 * a) : (a = (d + 2) / 4, d = (3 * d + 2) / 4);
                        break;
                    case "freundperles":
                        a = (d + 3) / 4;
                        d = (3 * d + 1) / 4;
                        break;
                    case "mendenhallsincich":
                        a = oa((d + 1) / 4);
                        d = oa(3 * a);
                        break;
                    default:
                        a = (d + 1) / 4, d = 3 * a
                }--a;
                --d;
                c = Yb(a);
                e = Yb(d);
                a = a - c ? b[c] + (b[hc(a)] -
                    b[c]) * (a - c) : b[a];
                b = d - e ? b[e] + (b[hc(d)] - b[e]) * (d - e) : b[d];
                return this.quartiles = {
                    q1: a,
                    q3: b
                }
            },
            getMinMax: function() {
                var b = this.values;
                return {
                    min: b[0],
                    max: b[this.dataLength - 1]
                }
            },
            getMean: function() {
                return this.mean
            },
            getMD: function() {
                for (var b = this.mean, d = this.frequencies, a = d.length, c, e = 0; a--;) c = d[a], e += c.frequency * S(c.value - b);
                return e / this.dataLength
            },
            getSD: function() {
                for (var b = this.mean, d = this.values, a = this.dataLength, c = a, e = 0; a--;) e += L(d[a] - b, 2);
                return uc(e) / c
            },
            getQD: function() {
                return .5 * (this.quartiles.q3 -
                    this.quartiles.q1)
            },
            getFrequencies: function() {
                var b = [],
                    d = this.dataLength,
                    a = this.values,
                    c = 0,
                    e, f, g;
                for (g = 0; g < d; g += 1) c += e = a[g], ba(b[g]) ? b[g].frequency += 1 : (f = {}, f.value = e, f.frequency = 1, b[g] = f);
                this.sum = c;
                this.frequencies = b
            },
            getMedian: function() {
                var b = this.dataLength,
                    d = .5 * b,
                    a = this.values;
                return 0 === b % 2 ? (a[d] + a[d - 1]) / 2 : a[Yb(d)]
            }
        };
        Fb.prototype.constructor = Fb;
        s("boxandwhisker2d", {
            friendlyName: "Box and Whisker Chart",
            standaloneInit: !0,
            creditLabel: ta,
            defaultSeriesType: "boxandwhisker2d",
            chart: s.errorbar2d.chart,
            drawErrorValue: s.errorbar2d.drawErrorValue,
            decimals: 2,
            maxColWidth: 9E3,
            useErrorAnimation: 1,
            avoidCrispError: 0,
            tooltipsepchar: ": ",
            rendererId: "boxandwhisker",
            fireGroupEvent: !0,
            point: function(b, d, a, c, e, f, q, m, v) {
                var l = e[na],
                    p = g(c.ignoreemptydatasets, 0),
                    k = l.numberFormatter,
                    n = e.chart.useRoundEdges,
                    r = g(c.showshadow, 1),
                    t = this.colorManager,
                    z = "," + (g(c.useplotgradientcolor, 0) ? Lb(c.plotgradientcolor, t.getColor("plotGradientColor")) : B),
                    D = 2 * q,
                    w = g(c.plotborderthickness, 1),
                    C = h(c.plotbordercolor, t.getColor("plotBorderColor")).split(",")[0],
                    H = h(c.plotborderalpha, "100"),
                    $ = "0" == c.showplotborder ? "0" : H,
                    u = g(a.dashed, c.plotborderdashed, 0),
                    s = g(a.dashlen, c.plotborderdashlen, 5),
                    E = g(a.dashgap, c.plotborderdashgap, 4),
                    x = h(a.upperboxcolor, c.upperboxcolor, t.getPlotColor(D)),
                    I = h(a.lowerboxcolor, c.lowerboxcolor, t.getPlotColor(D + 1)),
                    ka = g(a.upperboxalpha, c.upperboxalpha),
                    G = g(a.lowerboxalpha, c.lowerboxalpha),
                    ra = h(a.upperwhiskercolor, c.upperwhiskercolor, C),
                    S = h(a.lowerwhiskercolor, c.lowerwhiskercolor, C),
                    P = g(a.upperwhiskeralpha, c.upperwhiskeralpha, c.plotborderalpha,
                        "100"),
                    L = g(a.lowerwhiskeralpha, c.lowerwhiskeralpha, c.plotborderalpha, "100"),
                    T = g(a.upperwhiskerthickness, c.upperwhiskerthickness, w),
                    M = g(a.lowerwhiskerthickness, c.lowerwhiskerthickness, w),
                    K = h(a.upperwhiskerdashed, c.upperwhiskerdashed, 0),
                    Z = h(a.lowerwhiskerdashed, c.lowerwhiskerdashed, 0),
                    U = h(a.upperwhiskerdashlen, c.upperwhiskerdashlen, 5),
                    N = h(a.lowerwhiskerdashlen, c.lowerwhiskerdashlen, 5),
                    Q = h(a.upperwhiskerdashgap, c.upperwhiskerdashgap, 4),
                    aa = h(a.lowerwhiskerdashgap, c.lowerwhiskerdashgap, 4),
                    Y = h(a.upperquartilecolor,
                        c.upperquartilecolor, C),
                    W = h(a.lowerquartilecolor, c.lowerquartilecolor, C),
                    X = h(a.upperboxbordercolor, c.upperboxbordercolor, C),
                    V = h(a.lowerboxbordercolor, c.lowerboxbordercolor, C),
                    ea = h(a.mediancolor, c.mediancolor, C),
                    fa = h(a.upperquartilealpha, c.upperquartilealpha, n ? 0 : H),
                    oa = h(a.lowerquartilealpha, c.lowerquartilealpha, n ? 0 : H),
                    Mc = h(a.upperboxborderalpha, c.upperboxborderalpha, n ? 0 : $),
                    ia = h(a.lowerboxborderalpha, c.lowerboxborderalpha, n ? 0 : $),
                    ma = h(a.medianalpha, c.medianalpha, H),
                    ga = h(a.upperquartilethickness, c.upperquartilethickness,
                        w),
                    pa = h(a.lowerquartilethickness, c.lowerquartilethickness, w),
                    la = h(a.upperboxborderthickness, c.upperboxborderthickness, w),
                    wa = h(a.lowerboxborderthickness, c.lowerboxborderthickness, w),
                    Bc = h(a.medianthickness, c.medianthickness, w),
                    qa = h(a.upperquartiledashed, c.upperquartiledashed, u),
                    vb = h(a.lowerquartiledashed, c.lowerquartiledashed, u),
                    sa = h(a.upperboxborderdashed, c.upperboxborderdashed, u),
                    mb = h(a.lowerboxborderdashed, c.lowerboxborderdashed, u),
                    rb = h(a.mediandashed, c.mediandashed, u),
                    ua = h(a.upperquartiledashlen,
                        c.upperquartiledashlen, s),
                    $c = h(a.lowerquartiledashlen, c.lowerquartiledashlen, s),
                    ta = h(a.upperboxborderdashlen, c.upperboxborderdashlen, s),
                    xa = h(a.lowerboxborderdashlen, c.lowerboxborderdashlen, s),
                    va = h(a.mediandashlen, c.mediandashlen, s),
                    za = h(a.upperquartiledashgap, c.upperquartiledashgap, E),
                    Aa = h(a.lowerquartiledashgap, c.lowerquartiledashgap, E),
                    Ba = h(a.upperboxborderdashgap, c.upperboxborderdashgap, E),
                    Da = h(a.lowerboxborderdashgap, c.lowerboxborderdashgap, E),
                    Ga = h(a.mediandashgap, c.mediandashgap, E),
                    Ea = {},
                    Fa = {},
                    Ia = {},
                    Ja = {},
                    Ca = {},
                    La = [],
                    Ma = [],
                    Na = [],
                    Pa = [],
                    Qa = [],
                    Oa = {
                        polygon: "polygon",
                        spoke: "spoke"
                    },
                    Ra = Oa[h(a.meaniconshape, c.meaniconshape, "polygon").toLowerCase()] || "polygon",
                    Ua = g(a.meaniconradius, c.meaniconradius, 5),
                    Sa = g(a.meaniconsides, c.meaniconsides, 3),
                    Ta = h(a.meaniconcolor, c.meaniconcolor, "000000"),
                    Va = h(a.meaniconbordercolor, c.meaniconbordercolor, "000000"),
                    Xa = g(a.meaniconalpha, c.meaniconalpha, 100),
                    Za = Oa[h(a.sdiconshape, c.sdiconshape, "polygon").toLowerCase()] || "polygon",
                    ab = g(a.sdiconradius, c.sdiconradius,
                        5),
                    bb = g(a.sdiconsides, c.sdiconsides, 3),
                    $a = h(a.sdiconcolor, c.sdiconcolor, "000000"),
                    cb = h(a.sdiconbordercolor, c.sdiconbordercolor, "000000"),
                    fb = g(a.sdiconalpha, c.sdiconalpha, 100),
                    db = Oa[h(a.mdiconshape, c.mdiconshape, "polygon").toLowerCase()] || "polygon",
                    gb = g(a.mdiconradius, c.mdiconradius, 5),
                    jb = g(a.mdiconsides, c.mdiconsides, 3),
                    ib = h(a.mdiconcolor, c.mdiconcolor, "000000"),
                    lb = h(a.mdiconbordercolor, c.mdiconbordercolor, "000000"),
                    tb = g(a.mdiconalpha, c.mdiconalpha, 100),
                    nb = Oa[h(a.qdiconshape, c.qdiconshape, "polygon").toLowerCase()] ||
                    "polygon",
                    ob = g(a.qdiconradius, c.qdiconradius, 5),
                    qb = g(a.qdiconsides, c.qdiconsides, 3),
                    pb = h(a.qdiconcolor, c.qdiconcolor, "000000"),
                    ub = h(a.qdiconbordercolor, c.qdiconbordercolor, "000000"),
                    Db = g(a.qdiconalpha, c.qdiconalpha, 100),
                    Ab = Oa[h(a.outliericonshape, c.outliericonshape, "polygon").toLowerCase()] || "polygon",
                    Cb = g(a.outliericonradius, c.outliericonradius, 5),
                    Eb = g(a.outliericonsides, c.outliericonsides, 3),
                    Fb = h(a.outliericoncolor, c.outliericoncolor, "000000"),
                    Mb = h(a.outliericonbordercolor, c.outliericonbordercolor,
                        "000000"),
                    Pb = g(a.outliericonalpha, c.outliericonalpha, 100),
                    Gb = (1 - 2 * l.plotSpacePercent) / 2 * (-.5 + q),
                    Ob = g(c.reverselegend, 0),
                    lc = Ob ? -1 : 1,
                    mc = d.legendIndex = 6 * q + (Ob ? 5 : 0),
                    Zb = g(a.showmean, c.showmean, 0),
                    $b = g(a.showmd, c.showmd, 0),
                    ac = g(a.showsd, c.showsd, 0),
                    dc = g(a.showqd, c.showqd, 0),
                    Yb = g(a.showalloutliers, c.showalloutliers, 0),
                    vc = g(c.outliersupperrangeratio, 0),
                    wc = g(c.outlierslowerrangeratio, 0),
                    bc = !1,
                    nc = Boolean(g(c.showdetailedlegend, 1)),
                    oc = l.tooltipSepChar,
                    Sb = !0,
                    kc = l.dataSeparator,
                    Qb = l.bwCalc,
                    hc = h(d.type, this.defaultSeriesType),
                    uc = e.plotOptions[hc] && e.plotOptions[hc].stacking,
                    sb, Tb, Cc, Dc, ic, jc, Nc, Oc, Pc, Qc, Rc, kb, Hb, Ib, Jb, Kb, xc, Ec, Nb, ec, pc, Rb, fc, Ub, Vb, Wb, Fc, Gc, qc, Hc, R, rc, Xb, sc, gc, Sc, Ic, hb, Wa, Tc, tc, Jc, wb, xb, yc, Kc, yb, zb, Lc, Ac = function(a, b) {
                        return a - b
                    },
                    zc, Uc, Vc, Wc, Xc, Yc;
                d.errorBarWidthPercent = g(a.whiskerslimitswidthratio, c.whiskerslimitswidthratio, 40);
                Jc = a.data;
                d.name = A(a.seriesname);
                uc || (d.columnPosition = g(v, m, q));
                d.errorBar2D = !0;
                if (0 === g(a.includeinlegend) || void 0 === d.name) Sb = d.showInLegend = !1;
                3 > Sa && (Sa = 3);
                xc = Bb(x.split(",")[0]);
                Ec = Bb(I.split(",")[0]);
                d.color = {
                    FCcolor: {
                        color: xc + "," + xc + "," + Ec + "," + Ec,
                        alpha: "100,100,100,100",
                        angle: 90,
                        ratio: "0,50,0,50"
                    }
                };
                sc = this.isBar;
                $ = (gc = /3d$/.test(e.chart.defaultSeriesType)) ? c.showplotborder ? $ : "0" : $;
                C = gc ? h(c.plotbordercolor, "#FFFFFF") : C;
                vc = 0 > vc ? 0 : vc;
                wc = 0 > wc ? 0 : wc;
                for (Wb = 0; Wb < f; Wb += 1) {
                    if (R = Jc && Jc[Wb]) R.value ? (Qb.setArray(R.value), Kc = Qb.getQuartiles(), yb = Kc.q1, zb = Kc.q3, Lc = Qb.getMinMax(), xb = Vb = Lc.min, wb = Lc.max, yc = Qb.getMedian(), Hb = Qb.getMean(), Jb = Qb.getMD(), Ib = Qb.getSD(), Kb = Qb.getQD(), Ub = fc =
                        wb) : (yb = k.getCleanValue(R.q1), zb = k.getCleanValue(R.q3), xb = Vb = k.getCleanValue(R.min), wb = k.getCleanValue(R.max), yc = k.getCleanValue(R.median), Ub = fc = wb, Hb = Dc = k.getCleanValue(R.mean), Jb = k.getCleanValue(R.md), Ib = k.getCleanValue(R.sd), Kb = k.getCleanValue(R.qd));
                    if (R && null != yb && null != zb && null !== fc) {
                        bc = !0;
                        Dc = g(R.showmean, Zb);
                        ic = g(R.showmd, $b);
                        jc = g(R.showsd, ac);
                        Nc = g(R.showqd, dc);
                        Tb = l.oriCatTmp[Wb];
                        hb = this.getPointStub(e, c, a, R, wb, zb, yc, yb, xb, Hb, Jb, Ib, Kb, Tb);
                        Wa = hb.toolText;
                        Dc ? (Oc = 1, kb = g(R.meaniconalpha, Xa), Wa =
                            A(F(h(R.meantooltext, a.meantooltext, c.meantooltext))), Wa = void 0 !== Wa ? this.getTooltext(Wa, e, c, a, R, wb, xb, yb, zb, Ca, Ib, Kb, Jb, Hb, Tb) : "<b>Mean" + oc + "</b>" + k.dataLabels(Hb), La.push({
                                y: Hb,
                                toolText: Wa,
                                link: hb.link,
                                marker: {
                                    enabled: !0,
                                    fillColor: y(h(R.meaniconcolor, Ta), kb),
                                    lineColor: y(h(R.meaniconbordercolor, Va), kb),
                                    radius: g(R.meaniconradius, Ua),
                                    symbol: da(g(R.meaniconsides, Sa), "spoke" == h(R.meaniconshape, Ra))
                                }
                            })) : La.push({
                            y: null
                        });
                        ic ? (Pc = 1, kb = g(R.mdiconalpha, tb), Wa = A(F(h(R.mdtooltext, a.mdtooltext, c.mdtooltext))),
                            Wa = void 0 !== Wa ? this.getTooltext(Wa, e, c, a, R, wb, xb, yb, zb, Ca, Ib, Kb, Jb, Hb, Tb) : "<b>MD" + oc + "</b>" + k.dataLabels(Jb), Ma.push({
                                y: Jb,
                                toolText: Wa,
                                link: hb.link,
                                marker: {
                                    enabled: !0,
                                    fillColor: y(h(R.mdiconcolor, ib), kb),
                                    lineColor: y(h(R.mdiconbordercolor, cb), kb),
                                    radius: g(R.mdiconradius, gb),
                                    symbol: da(g(R.mdiconsides, jb), "spoke" == h(R.mdiconshape, db))
                                }
                            })) : Ma.push({
                            y: null
                        });
                        jc ? (Qc = 1, kb = g(R.sdiconalpha, fb), Wa = A(F(h(R.sdtooltext, a.sdtooltext, c.sdtooltext))), Wa = void 0 !== Wa ? this.getTooltext(Wa, e, c, a, R, wb, xb, yb, zb, Ca, Ib, Kb,
                            Jb, Hb, Tb) : "<b>SD" + oc + "</b>" + k.dataLabels(Ib), Na.push({
                            y: Ib,
                            toolText: Wa,
                            link: hb.link,
                            marker: {
                                enabled: !0,
                                fillColor: y(h(R.sdiconcolor, $a), kb),
                                lineColor: y(h(R.sdiconbordercolor, cb), kb),
                                radius: g(R.sdiconradius, ab),
                                symbol: da(g(R.sdiconsides, bb), "spoke" == h(R.sdiconshape, Za))
                            }
                        })) : Na.push({
                            y: null
                        });
                        Nc ? (Rc = 1, kb = g(R.qdiconalpha, Db), Wa = A(F(h(R.qdtooltext, a.qdtooltext, c.qdtooltext))), Wa = void 0 !== Wa ? this.getTooltext(Wa, e, c, a, R, wb, xb, yb, zb, Ca, Ib, Kb, Jb, Hb, Tb) : "<b>QD" + oc + "</b>" + k.dataLabels(Kb), Pa.push({
                            y: Kb,
                            toolText: Wa,
                            link: hb.link,
                            marker: {
                                enabled: !0,
                                fillColor: y(h(R.qdiconcolor, pb), kb),
                                lineColor: y(h(R.qdiconbordercolor, ub), kb),
                                radius: g(R.qdiconradius, ob),
                                symbol: da(g(R.qdiconsides, qb), "spoke" == h(R.qdiconshape, nb))
                            }
                        })) : Pa.push({
                            y: null
                        });
                        if (Nb = R.outliers) {
                            Nb = Nb.replace(/\s/g, B).split(kc);
                            for (ec = Nb.length; ec--;) Nb[ec] = k.getCleanValue(Nb[ec]);
                            Nb.sort(Ac);
                            ec = Nb.length;
                            for (pc = 0; pc < ec; pc += 1)
                                if (Rb = Nb[pc], Yb && (Ub = Ya(fc, Rb), Vb = J(xb, Rb)), kb = g(R.outliericonalpha, Pb), Rb > fc || Rb < xb) Wa = A(F(h(R.outlierstooltext, a.outlierstooltext,
                                    c.outlierstooltext))), Wa = void 0 !== Wa ? this.getTooltext(Wa, e, c, a, R, wb, xb, yb, zb, Ca, Ib, Kb, Jb, Hb, Tb, Rb) : "<b>Outlier" + oc + "</b>" + k.dataLabels(Rb), Qa.push({
                                    y: Rb,
                                    toolText: Wa,
                                    x: Wb,
                                    link: hb.link,
                                    marker: {
                                        enabled: !0,
                                        fillColor: y(h(R.outliericoncolor, Fb), kb),
                                        lineColor: y(h(R.outliericonbordercolor, Mb), kb),
                                        radius: g(R.outliericonradius, Cb),
                                        symbol: da(g(R.outliericonsides, Eb), "spoke" == h(R.outliericonshape, Ab))
                                    }
                                })
                        }
                        Yb || (Cc = Ub - Vb, Ub += Cc * vc, Vb -= Cc * wc);
                        Fc = h(R.upperboxcolor, x) + z;
                        Gc = h(R.lowerboxcolor, I) + z;
                        qc = h(R.upperboxalpha,
                            ka, c.upperboxalpha, c.plotfillalpha, "100") + B;
                        Hc = h(R.lowerboxalpha, G, c.lowerboxalpha, c.plotfillalpha, "100") + B;
                        rc = h(R.ratio, a.ratio, c.plotfillratio);
                        Xb = h(360 - c.plotfillangle, 90);
                        0 > fc && (Xb = 360 - Xb);
                        Tc = {
                            opacity: qc / 100
                        };
                        tc = J(qc, $) + B;
                        Sc = cc(Fc, qc, rc, Xb, n, C, tc, sc, gc);
                        Ic = cc(Gc, Hc, rc, Xb, n, C, tc, sc, gc);
                        Ea = {
                            value: zb,
                            color: y(h(R.upperquartilecolor, Y), g(R.upperquartilealpha, fa)),
                            borderWidth: g(R.upperquartilethickness, ga),
                            dashStyle: g(R.upperquartiledashed, qa) ? Ha(h(R.upperquartiledashlen, ua), h(R.upperquartiledashgap,
                                za), g(R.upperquartilethickness, ga)) : "none",
                            displayValue: hb.displayValueQ3
                        };
                        Fa = {
                            value: yb,
                            color: y(h(R.lowerquartilecolor, W), g(R.lowerquartilealpha, oa)),
                            borderWidth: g(R.lowerquartilethickness, pa),
                            dashStyle: g(R.lowerquartiledashed, vb) ? Ha(h(R.lowerquartiledashlen, $c), h(R.lowerquartiledashgap, Aa), g(R.lowerquartilethickness, pa)) : "none",
                            displayValue: hb.displayValueQ1
                        };
                        Ia = {
                            color: y(h(R.upperboxbordercolor, X), g(R.upperboxborderalpha, Mc)),
                            borderWidth: g(R.upperboxborderthickness, la),
                            dashStyle: g(R.upperboxborderdashed,
                                sa) ? Ha(h(R.upperboxborderdashlen, ta), h(R.upperboxborderdashgap, Ba), g(R.upperboxborderthickness, la)) : "none"
                        };
                        Ja = {
                            color: y(h(R.lowerboxbordercolor, V), g(R.lowerboxborderalpha, ia)),
                            borderWidth: g(R.lowerboxborderthickness, wa),
                            dashStyle: g(R.lowerboxborderdashed, mb) ? Ha(h(R.lowerboxborderdashlen, xa), h(R.lowerboxborderdashgap, Da), g(R.lowerboxborderthickness, wa)) : "none"
                        };
                        Ca = {
                            value: yc,
                            color: y(h(R.mediancolor, ea), g(R.medianalpha, ma)),
                            borderWidth: g(R.medianthickness, Bc),
                            dashStyle: g(R.mediandashed, rb) ? Ha(h(R.mediandashlen,
                                va), h(R.mediandashgap, Ga), g(R.medianthickness, Bc)) : "none",
                            displayValue: hb.displayValueMid
                        };
                        zc = [];
                        ba(wb) && zc.push({
                            errorValue: wb - zb,
                            toolText: hb.toolText,
                            link: hb.link,
                            errorBarColor: y(h(R.upperwhiskercolor, ra), g(R.upperwhiskeralpha, P)),
                            errorBarThickness: g(R.upperwhiskerthickness, T),
                            dashStyle: g(R.upperwhiskerdashed, K) ? Ha(h(R.upperwhiskerdashlen, U), h(R.upperwhiskerdashgap, Q), g(R.upperwhiskerthickness, T)) : "none",
                            displayValue: hb.displayValueMax,
                            shadow: {
                                opacity: r ? g(R.upperwhiskeralpha, P) / 250 : 0
                            }
                        });
                        ba(xb) &&
                            zc.push({
                                errorValue: -(yb - xb),
                                errorStartValue: yb,
                                toolText: hb.toolText,
                                link: hb.link,
                                errorBarColor: y(h(R.lowerwhiskercolor, S), g(R.lowerwhiskeralpha, L)),
                                errorBarThickness: g(R.lowerwhiskerthickness, M),
                                dashStyle: g(R.lowerwhiskerdashed, Z) ? Ha(h(R.lowerwhiskerdashlen, N), h(R.lowerwhiskerdashgap, aa), g(R.lowerwhiskerthickness, M)) : "none",
                                displayValue: hb.displayValueMin,
                                shadow: {
                                    opacity: r ? g(R.lowerwhiskeralpha, L) / 250 : 0
                                }
                            });
                        sb = this.pointHoverOptions(R, a, c, {
                            upperBoxColor: Fc,
                            upperBoxAlpha: qc,
                            upperBoxBorderColor: h(R.upperboxbordercolor,
                                X),
                            upperBoxBorderAlpha: g(R.upperboxborderalpha, Mc),
                            upperBoxBorderThickness: Ia.borderWidth,
                            lowerBoxColor: Gc,
                            lowerBoxAlpha: Hc,
                            lowerBoxBorderColor: h(R.lowerboxbordercolor, V),
                            lowerBoxBorderAlpha: g(R.lowerboxborderalpha, ia),
                            lowerBoxBorderThickness: Ja.borderWidth,
                            upperQuartileColor: h(R.upperquartilecolor, Y),
                            upperQuartileAlpha: g(R.upperquartilealpha, fa),
                            upperQuartileThickness: Ea.borderWidth,
                            lowerQuartileColor: h(R.lowerquartilecolor, W),
                            lowerQuartileAlpha: g(R.lowerquartilealpha, oa),
                            lowerQuartileThickness: Fa.borderWidth,
                            upperWhiskerColor: h(R.upperwhiskercolor, ra),
                            upperWhiskerThickness: g(R.upperwhiskerthickness, T),
                            upperWhiskerAlpha: g(R.upperwhiskeralpha, P),
                            lowerWhiskerColor: h(R.lowerwhiskercolor, S),
                            lowerWhiskerAlpha: g(R.lowerwhiskeralpha, L),
                            lowerWhiskerThickness: g(R.lowerwhiskerthickness, M),
                            medianColor: h(R.mediancolor, ea),
                            medianAlpha: g(R.medianalpha, ma),
                            medianThickness: g(R.medianthickness, Bc)
                        });
                        sb.enabled && (sb.upperBox.fill = ca(cc(sb.upperBox.color, sb.upperBox.alpha, rc, Xb, n, C, tc, sc, gc)[0].FCcolor), delete sb.upperBox.color,
                            delete sb.upperBox.alpha, sb.lowerBox.fill = ca(cc(sb.lowerBox.color, sb.lowerBox.alpha, rc, Xb, n, C, tc, sc, gc)[0].FCcolor), delete sb.lowerBox.color, delete sb.lowerBox.alpha);
                        d.data.push(O(hb, {
                            y: zb,
                            errorValue: zc,
                            shadow: Tc,
                            color: Sc[0],
                            toolText: hb.toolText,
                            lowerboxColor: Ic[0],
                            lowerboxBorderColor: Ic[1],
                            borderWidth: 0,
                            upperQuartile: Ea,
                            lowerQuartile: Fa,
                            upperBoxBorder: Ia,
                            lowerBoxBorder: Ja,
                            median: Ca,
                            hoverEffects: sb
                        }));
                        this.pointValueWatcher(e, Ub);
                        this.pointValueWatcher(e, Vb)
                    } else d.data.push({
                            y: null
                        }), Ma.push({
                            y: null
                        }),
                        Na.push({
                            y: null
                        }), Pa.push({
                            y: null
                        }), La.push({
                            y: null
                        })
                }
                d.showInLegend = Sb && (bc || !p);
                d.legendFillColor = y(xc, 20);
                Uc = {
                    type: "line",
                    name: "Mean",
                    relatedSeries: "boxandwhisker",
                    data: La,
                    legendIndex: mc + lc,
                    showInLegend: !!Oc && Sb && nc,
                    marker: {
                        fillColor: y(Ta, 100),
                        lineColor: y(Va, 100),
                        radius: Ua,
                        symbol: da(Sa, "spoke" == Ra)
                    },
                    color: g(c.drawmeanconnector, a.drawmeanconnector, 0) ? y(h(a.meanconnectorcolor, c.meanconnectorcolor, Ta), g(a.meanconnectoralpha, c.meanconnectoralpha, 100)) : ha,
                    lineWidth: g(c.drawmeanconnector, a.drawmeanconnector,
                        0) ? g(a.meanconnectorthickness, c.meanconnectorthickness, 1) : 0,
                    shadow: 0,
                    legendFillColor: d.legendFillColor
                };
                Vc = {
                    type: "line",
                    name: "SD",
                    relatedSeries: "boxandwhisker",
                    data: Na,
                    legendIndex: mc + 2 * lc,
                    showInLegend: !!Qc && Sb && nc,
                    marker: {
                        fillColor: y($a, 100),
                        lineColor: y(cb, 100),
                        radius: ab,
                        symbol: da(bb, "spoke" == Za)
                    },
                    color: g(c.drawsdconnector, a.drawsdconnector, 0) ? y(h(a.sdconnectorcolor, c.sdconnectorcolor, $a), g(a.sdconnectoralpha, c.sdconnectoralpha, 100)) : ha,
                    lineWidth: g(c.drawsdconnector, a.drawsdconnector, 0) ? g(a.sdconnectorthickness,
                        c.sdconnectorthickness, 1) : 0,
                    shadow: 0,
                    pointStart: Gb,
                    legendFillColor: d.legendFillColor
                };
                Wc = {
                    type: "line",
                    name: "MD",
                    relatedSeries: "boxandwhisker",
                    data: Ma,
                    legendIndex: mc + 3 * lc,
                    showInLegend: !!Pc && Sb && nc,
                    marker: {
                        fillColor: y(ib, 100),
                        lineColor: y(lb, 100),
                        radius: gb,
                        symbol: da(jb, "spoke" == db)
                    },
                    color: g(c.drawmdconnector, a.drawmdconnector, 0) ? y(h(a.mdconnectorcolor, c.mdconnectorcolor, ib), g(a.mdconnectoralpha, c.mdconnectoralpha, 100)) : ha,
                    lineWidth: g(c.drawmdconnector, a.drawmdconnector, 0) ? g(a.mdconnectorthickness, c.mdconnectorthickness,
                        1) : 0,
                    shadow: 0,
                    pointStart: Gb,
                    legendFillColor: d.legendFillColor
                };
                Xc = {
                    type: "line",
                    name: "QD",
                    relatedSeries: "boxandwhisker",
                    data: Pa,
                    legendIndex: mc + 4 * lc,
                    showInLegend: !!Rc && Sb && nc,
                    marker: {
                        fillColor: y(pb, 100),
                        lineColor: y(ub, 100),
                        radius: ob,
                        symbol: da(qb, "spoke" == nb)
                    },
                    color: g(c.drawqdconnector, a.drawqdconnector, 0) ? y(h(a.qdconnectorcolor, c.qdconnectorcolor, pb), g(a.qdconnectoralpha, c.qdconnectoralpha, 100)) : ha,
                    lineWidth: g(c.drawqdconnector, a.drawqdconnector, 0) ? g(a.qdconnectorthickness, c.qdconnectorthickness, 1) : 0,
                    shadow: 0,
                    pointStart: Gb,
                    legendFillColor: d.legendFillColor
                };
                Yc = {
                    type: "line",
                    name: "Outlier",
                    relatedSeries: "boxandwhisker",
                    showInLegend: !(!Qa || !Qa.length) && Sb && nc,
                    data: Qa,
                    legendIndex: mc + 5 * lc,
                    marker: {
                        fillColor: y(Fb, 100),
                        lineColor: y(Mb, 100),
                        radius: Cb,
                        symbol: da(Eb, "spoke" == Ab)
                    },
                    color: ha,
                    lineWidth: 0,
                    shadow: 0,
                    pointStart: Gb,
                    legendFillColor: d.legendFillColor
                };
                e._meanDataArr.push(Uc);
                e._sdDataArr.push(Vc);
                e._mdDataArr.push(Wc);
                e._qdDataArr.push(Xc);
                e._outliers.push(Yc);
                return d
            },
            series: function(b, d, a) {
                var c =
                    d.series,
                    e = d._meanDataArr = [],
                    f = d._sdDataArr = [],
                    g = d._mdDataArr = [],
                    m = d._qdDataArr = [],
                    v = d._outliers = [],
                    l = d[na],
                    p = d.yAxis[0],
                    k = 2 * l.plotSpacePercent,
                    n, r, t, z, D;
                l.dataSeparator = h(d.chart.dataseparator, Ba);
                l.bwCalc = new Fb(b.chart.calculationmethod, l.numberFormatter, l.dataSeparator);
                s.multiseries.series.call(this, b, d, a);
                a = c && c.length;
                b = Ya(e.length, f.length, g.length, m.length, v.length, a);
                k = (1 - k) / a;
                l = p.min;
                D = p.max;
                d.series = c.concat(e, f, g, m, v);
                for (p = 0; p < a; p += 1)
                    for (r = c[p], n = p, !r.relatedSeries && (r.relatedSeries = []), t = 0; 5 > t; t += 1) n += a, r.relatedSeries.push(n);
                for (t = p = 0; t < b; t += 1, p += 1)
                    if (c = (-.5 * (a - 1) + p) * k, e[p] && (e[p].pointStart = c), f[p] && (f[p].pointStart = c), m[p] && (m[p].pointStart = c), g[p] && (g[p].pointStart = c), v[p] && (v[p].pointStart = c), n = (c = v[p]) && c.data)
                        for (c = 0; c < n.length; c += 1) r = n[c], z = r.y, r.y = z > D || z < l ? null : z;
                delete d._meanDataArr;
                delete d._sdDataArr;
                delete d._mdDataArr;
                delete d._qdDataArr;
                delete d._outliers
            },
            getTooltext: function(b, d, a, c, e, f, g, m, h, l, p, k, n, r, t, z) {
                d = this.numberFormatter;
                return ab(b, [1, 2, 3, 4, 5, 6,
                        62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80
                    ], {
                        maxValue: f,
                        maxDataValue: d.dataLabels(f),
                        minValue: g,
                        minDataValue: d.dataLabels(g),
                        Q1: d.dataLabels(m),
                        unformattedQ1: m,
                        Q3: d.dataLabels(h),
                        unformattedQ3: h,
                        median: d.dataLabels(l),
                        unformattedMedian: l,
                        SD: d.dataLabels(p),
                        unformattedSD: p,
                        QD: d.dataLabels(k),
                        unformattedQD: k,
                        MD: d.dataLabels(n),
                        unformattedMD: n,
                        mean: d.dataLabels(r),
                        unformattedMean: r,
                        label: F(t),
                        yaxisName: F(a.yaxisname),
                        xaxisName: F(a.xaxisname),
                        formattedValue: d.dataLabels(z),
                        value: z
                    }, {
                        value: z
                    },
                    a, c)
            },
            pointHoverOptions: function(b, d, a, c) {
                var e = g(b.showhovereffect, d.showhovereffect, a.plothovereffect, a.showhovereffect),
                    f = g(b.highlightonhover, d.highlightonhover, d.highlightplotonhover, a.highlightonhover, a.highlightplotonhover, e),
                    q = {},
                    m = {},
                    v = {},
                    l = {},
                    p = {},
                    k = {},
                    n = {},
                    r = {},
                    t = {},
                    z;
                q.color = h(b.upperboxhovercolor, d.upperboxhovercolor, a.plotfillhovercolor, a.upperboxhovercolor);
                q.alpha = h(b.upperboxhoveralpha, d.upperboxhoveralpha, a.upperboxhoveralpha);
                n.color = h(b.upperboxborderhovercolor, d.upperboxborderhovercolor,
                    a.upperboxborderhovercolor);
                n.alpha = h(b.upperboxborderhoveralpha, d.upperboxborderhoveralpha, a.upperboxborderhoveralpha);
                n.thickness = g(b.upperboxborderhoverthickness, d.upperboxborderhoverthickness, a.upperboxborderhoverthickness);
                m.color = h(b.lowerboxhovercolor, d.lowerboxhovercolor, a.plotfillhovercolor, a.lowerboxhovercolor);
                m.alpha = h(b.lowerboxhoveralpha, d.lowerboxhoveralpha, a.lowerboxhoveralpha);
                r.color = h(b.lowerboxborderhovercolor, d.lowerboxborderhovercolor, a.lowerboxborderhovercolor);
                r.alpha = h(b.lowerboxborderhoveralpha,
                    d.lowerboxborderhoveralpha, a.lowerboxborderhoveralpha);
                r.thickness = g(b.lowerboxborderhoverthickness, d.lowerboxborderhoverthickness, a.lowerboxborderhoverthickness);
                p.color = h(b.upperwhiskerhovercolor, d.upperwhiskerhovercolor, a.upperwhiskerhovercolor);
                p.alpha = h(b.upperwhiskerhoveralpha, d.upperwhiskerhoveralpha, a.upperwhiskerhoveralpha);
                p.thickness = h(b.upperwhiskerhoverthickness, d.upperwhiskerhoverthickness, a.upperwhiskerhoverthickness);
                k.color = h(b.lowerwhiskerhovercolor, d.lowerwhiskerhovercolor, a.lowerwhiskerhovercolor);
                k.alpha = h(b.lowerwhiskerhoveralpha, d.lowerwhiskerhoveralpha, a.lowerwhiskerhoveralpha);
                k.thickness = h(b.lowerwhiskerhoverthickness, d.lowerwhiskerhoverthickness, a.lowerwhiskerhoverthickness);
                v.color = h(b.upperquartilehovercolor, d.upperquartilehovercolor, a.upperquartilehovercolor);
                v.alpha = h(b.upperquartilehoveralpha, d.upperquartilehoveralpha, a.upperquartilehoveralpha);
                v.thickness = h(b.upperquartilehoverthickness, d.upperquartilehoverthickness, a.upperquartilehoverthickness);
                l.color = h(b.lowerquartilehovercolor,
                    d.lowerquartilehovercolor, a.lowerquartilehovercolor);
                l.alpha = h(b.lowerquartilehoveralpha, d.lowerquartilehoveralpha, a.lowerquartilehoveralpha);
                l.thickness = h(b.lowerquartilehoverthickness, d.lowerquartilehoverthickness, a.lowerquartilehoverthickness);
                t.color = h(b.medianhovercolor, d.medianhovercolor, a.medianhovercolor);
                t.alpha = h(b.medianhoveralpha, d.medianhoveralpha, a.medianhoveralpha);
                t.thickness = h(b.medianhoverthickness, d.medianhoverthickness, a.medianhoverthickness);
                b = !!h(q.color, q.alpha, n.color, n.alpha,
                    n.thickness, m.color, m.alpha, r.color, r.thickness, r.alpha, p.color, p.alpha, p.thickness, k.color, k.alpha, k.thickness, v.color, v.alpha, v.thickness, l.color, l.alpha, l.thickness, t.color, t.alpha, t.thickness, f);
                void 0 === e && void 0 === f && b && (f = 0);
                if (void 0 === e && b || e) z = !0, q.color = h(q.color, f ? La(c.upperBoxColor, 70) : c.upperBoxColor), q.alpha = h(q.alpha, c.upperBoxAlpha), m.color = h(m.color, f ? La(c.lowerBoxColor, 70) : c.lowerBoxColor), m.alpha = h(m.alpha, c.lowerBoxAlpha), n.color = h(n.color, c.upperBoxBorderColor), n.alpha = g(n.alpha,
                        c.upperBoxBorderAlpha), n.stroke = y(n.color, n.alpha), n["stroke-width"] = g(n.thickness, c.upperBoxBorderThickness), delete n.color, delete n.alpha, delete n.thickness, r.color = h(r.color, c.lowerBoxBorderColor), r.alpha = h(r.alpha, c.lowerBoxBorderAlpha), r.stroke = y(r.color, r.alpha), r["stroke-width"] = g(r.thickness, c.lowerBoxBorderThickness), delete r.color, delete r.alpha, delete r.thickness, p.color = h(p.color, c.upperWhiskerColor, 70), p.alpha = h(p.alpha, c.upperWhiskerAlpha), p.stroke = y(p.color, p.alpha), p["stroke-width"] =
                    h(p.thickness, c.upperWhiskerThickness), delete p.color, delete p.alpha, delete p.thickness, k.color = h(k.color, c.lowerWhiskerColor, 70), k.alpha = h(k.alpha, c.lowerWhiskerAlpha), k.stroke = y(k.color, k.alpha), k["stroke-width"] = h(k.thickness, c.lowerWhiskerThickness), delete k.color, delete k.alpha, delete k.thickness, v.color = h(v.color, c.upperQuartileColor, 70), v.alpha = h(v.alpha, c.upperQuartileAlpha), v.stroke = y(v.color, v.alpha), v["stroke-width"] = h(v.thickness, c.upperQuartileThickness), delete v.color, delete v.alpha,
                    delete v.thickness, l.color = h(l.color, c.lowerQuartileColor, 70), l.alpha = h(l.alpha, c.lowerQuartileAlpha), l.stroke = y(l.color, l.alpha), l["stroke-width"] = h(l.thickness, c.lowerQuartileThickness), delete l.color, delete l.alpha, delete l.thickness, t.color = h(t.color, c.medianColor, 70), t.alpha = h(t.alpha, c.medianAlpha), t.stroke = y(t.color, t.alpha), t["stroke-width"] = h(t.thickness, c.medianThickness), delete t.color, delete t.alpha, delete t.thickness;
                return {
                    enabled: z,
                    upperBox: q,
                    upperBoxBorder: n,
                    lowerBox: m,
                    lowerBoxBorder: r,
                    upperQuartile: v,
                    lowerQuartile: l,
                    upperWhisker: p,
                    lowerWhisker: k,
                    median: t
                }
            },
            getPointStub: function(b, d, a, c, e, f, q, m, v, l, p, k, n, r) {
                var t = B,
                    t = b[na],
                    z = t.tooltipSepChar,
                    D = this.numberFormatter,
                    w = g(c.showvalue, a.showvalues, d.showvalues, 1),
                    C = {
                        "true": D.dataLabels(e),
                        "false": B
                    },
                    H = {
                        "true": D.dataLabels(f),
                        "false": B
                    },
                    u = {
                        "true": D.dataLabels(q),
                        "false": B
                    },
                    s = {
                        "true": D.dataLabels(m),
                        "false": B
                    },
                    D = {
                        "true": D.dataLabels(v),
                        "false": B
                    };
                t.showTooltip ? (t = A(F(h(c.tooltext, a.plottooltext, t.tooltext))), t = void 0 !== t ? this.getTooltext(t,
                    b, d, a, c, e, v, m, f, q, k, n, p, l, r) : "<b>Maximum" + z + "</b>" + C[!0] + "<br/><b>Q3" + z + "</b>" + H[!0] + "<br/><b>Median" + z + "</b>" + u[!0] + "<br/><b>Q1" + z + "</b>" + s[!0] + "<br/><b>Minimum" + z + "</b>" + D[!0]) : t = B;
                return {
                    toolText: t,
                    link: h(c.link),
                    categoryLabel: r,
                    displayValueMax: C[!(!w || !g(c.showmaxvalue, a.showmaxvalues, d.showmaxvalues, 1))],
                    displayValueMid: u[!(!w || !g(c.showmedianvalue, a.showmedianvalues, d.showmedianvalues, 1))],
                    displayValueMin: D[!(!w || !g(c.showminvalue, a.showminvalues, d.showminvalues, 1))],
                    displayValueQ3: H[!(!w ||
                        !g(c.showq3value, a.showq3values, d.showq3values, 0))],
                    displayValueQ1: s[!(!w || !g(c.showq1value, a.showq1values, d.showq1values, 0))]
                }
            }
        }, s.multiseries);
        s("heatmap", {
            friendlyName: "Heatmap Chart",
            standaloneInit: !0,
            creditLabel: ta,
            defaultSeriesType: "heatmap",
            tooltipsepchar: ": ",
            tooltipConstraint: "chart",
            rendererId: "heatmap",
            series: function(b, d, a) {
                var c = b.chart,
                    e = d.chart,
                    f = d[na],
                    q = this.colorManager,
                    m = d.series,
                    v = this.numberFormatter,
                    l = b.rows && b.rows.row,
                    p = l && l.length,
                    k = b.columns && b.columns.column,
                    n = k && k.length,
                    r = b.dataset,
                    t = r && r.data,
                    z = b.colorrange || {},
                    D = f.mapByPercent = g(z.mapbypercent, 0),
                    w = f.mapByCategory = g(c.mapbycategory, 0),
                    z = !w && g(z.gradient, 0),
                    C = h(c.plotfillalpha, 100),
                    H = g(c.showlabels, c.showlabel, 1),
                    s = g(c.showplotborder, 1),
                    x = s ? g(c.plotborderthickness, 1) : 0,
                    q = h(c.plotbordercolor, q.getColor("plotBorderColor")),
                    s = h(c.plotborderalpha, s ? 95 : 0).toString(),
                    q = y(q, s),
                    s = g(c.plotborderdashed, 0),
                    ya = g(c.plotborderdashlen, 5),
                    t = g(c.plotborderdashgap, 4),
                    ya = s ? Ha(ya, t, x) : "none",
                    E = u.colorRange,
                    Ka = 0,
                    I = 0,
                    ka = 0,
                    G = 0,
                    ra = f.rowIdObj = {},
                    J = f.columnIdObj = {},
                    P = [],
                    s = [],
                    ca = 0,
                    T = [],
                    M, K, Z, U, N, Q, aa, Y, W;
                e.showHoverEffect = g(c.showhovereffect, 1);
                z && (d.legend.type = "gradient");
                d.legend.enabled = Boolean(g(c.showlegend, 1));
                for (e = 0; e < p; e += 1) K = l[e], M = K.id, ba(M) && M !== B && (Ka += 1, ra[M.toLowerCase()] = {
                    index: Ka,
                    label: g(K.showlabel, c.showyaxislabels, c.showyaxisnames, H) ? h(K.label, K.name, M) : B
                });
                for (e = 0; e < n; e += 1) Z = k[e], l = Z.id, ba(l) && l !== B && (J[l.toLowerCase()] = {
                    index: I,
                    label: g(Z.showlabel, c.showxaxislabels, c.showxaxisnames, H) ? h(Z.label, Z.name, l) : B
                }, I += 1);
                aa = 0;
                for (Y = r && r.length; aa < Y; aa += 1)
                    for (t = r[aa] && r[aa].data, e = 0, W = t && t.length; e < W; e += 1)
                        if (k = t[e], Q = v.getCleanValue(k.value), null !== Q || w) M = A(k.rowid, k.rowids), K = A(M, B).toLowerCase(), l = A(k.columnid, k.columnids), Z = A(l, B).toLowerCase(), P.push(Q), ba(N) || ba(U) || !ba(Q) || (U = N = Q), N > Q && (N = Q), U < Q && (U = Q), !ba(K) || ba(ra[K]) || p || (ka += 1, ra[K] = {
                            index: ka,
                            label: M
                        }), !ba(Z) || ba(J[Z]) || n || (J[Z] = {
                            index: G,
                            label: l
                        }, G += 1), K = ra[K], Z = J[Z], K && Z && (ba(T[K.index]) || (T[K.index] = []), T[K.index][Z.index] ? s[T[K.index][Z.index] - 1] = {
                            rowId: M,
                            columnId: l,
                            categoryId: h(k.colorrangelabel, k.categoryid, k.categoryname, k.category),
                            tlLabel: F(h(k.tllabel, k.ltlabel)),
                            trLabel: F(h(k.trlabel, k.rtlabel)),
                            blLabel: F(h(k.bllabel, k.lblabel)),
                            brLabel: F(h(k.brlabel, k.rblabel)),
                            rowLabel: K.label,
                            columnLabel: Z.label,
                            setColor: k.color,
                            setAlpha: h(k.alpha, C),
                            setShowLabel: g(k.showlabel, k.showname, H),
                            colorRangeLabel: k.colorrangelabel,
                            displayValue: k.displayvalue,
                            tooltext: k.tooltext,
                            showvalue: k.showvalue,
                            link: k.link,
                            hoverColor: h(k.hovercolor, c.hovercolor, c.plotfillhovercolor),
                            hoverAlpha: g(k.hoveralpha, c.hoveralpha, c.plotfillhoveralpha),
                            index: ca,
                            value: Q,
                            y: K.index,
                            x: Z.index,
                            _value: k.value,
                            _cleanValue: Q
                        } : (ca += 1, s.push({
                            rowId: M,
                            columnId: l,
                            categoryId: h(k.colorrangelabel, k.categoryid, k.categoryname, k.category),
                            tlLabel: F(h(k.tllabel, k.ltlabel)),
                            trLabel: F(h(k.trlabel, k.rtlabel)),
                            blLabel: F(h(k.bllabel, k.lblabel)),
                            brLabel: F(h(k.brlabel, k.rblabel)),
                            rowLabel: K.label,
                            columnLabel: Z.label,
                            setColor: k.color && k.color.replace(ob, Qa),
                            setAlpha: h(k.alpha, C),
                            setShowLabel: g(k.showlabel, k.showname,
                                H),
                            colorRangeLabel: k.colorrangelabel,
                            displayValue: k.displayvalue,
                            tooltext: k.tooltext,
                            showvalue: k.showvalue,
                            link: k.link,
                            hoverColor: h(k.hovercolor, c.hovercolor, c.plotfillhovercolor),
                            hoverAlpha: g(k.hoveralpha, c.hoveralpha, c.plotfillhoveralpha),
                            index: ca,
                            value: Q,
                            y: K.index,
                            x: Z.index,
                            _value: k.value,
                            _cleanValue: Q
                        }), T[K.index][Z.index] = ca));
                if (s.length) {
                    f.rowCount = Ka = Ya(Ka, ka);
                    f.columnCount = Ya(I, G);
                    for (e in ra) ra[e].index = Ka - ra[e].index + 1;
                    f.minHeatValue = N;
                    f.maxHeatValue = U;
                    p = U - N;
                    D = D && !w;
                    d.colorRange = new E({
                        colorRange: b.colorrange,
                        dataMin: N,
                        dataMax: U,
                        sortLegend: g(c.autoorderlegendicon, c.autoorderlegendicon, 0),
                        mapByCategory: w,
                        defaultColor: "cccccc",
                        numberFormatter: v
                    });
                    if (z) m.push({
                        data: [],
                        hoverEffects: this.parseSeriesHoverOptions(b, d, r, a),
                        borderWidth: x,
                        borderColor: q,
                        dashStyle: ya
                    });
                    else
                        for (v = (c = d.colorRange.colorArr) && c.length, e = 0; e < v; e += 1) U = c[e], ba(U.code) && m.push({
                            data: [],
                            hoverEffects: this.parseSeriesHoverOptions(b, d, r, a),
                            name: h(U.label, U.name),
                            borderWidth: x,
                            borderColor: q,
                            color: Bb(U.code),
                            dashStyle: ya
                        });
                    m.length || m.push({
                        data: [],
                        showInLegend: !1
                    });
                    for (e = 0; e < s.length; e += 1) k = s[e], D && (k.value = oa((k.value - N) / p * 1E4) / 100), a = d.colorRange.getColorObj(w ? k.categoryId : k.value), a.outOfRange || (k.y = f.rowCount - k.y + 1, k.color = y(h(k.setColor, a.code), h(k.setAlpha, C)), k.hoverColor = y(h(k.hoverColor, k.setColor, a.code), g(k.hoverAlpha, 25)), k = O(k, this.getPointStub(k, k.value, B, d, b)), z ? m[0].data.push(k) : m[a.seriesIndex] && m[a.seriesIndex].data.push(k))
                } else d.series = [];
                this.configureAxis(d, b)
            },
            getPointStub: function(b, d, a, c, e) {
                a = c[na];
                var f = e.chart,
                    q =
                    a.tooltipSepChar,
                    m = a.mapByCategory;
                e = a.mapByPercent && !m;
                var v = this.numberFormatter,
                    l = b._cleanValue;
                c = v.percentValue(d);
                d = null === l ? d : v.dataLabels(l);
                var p = A(F(h(b.tooltext, a.tooltext))),
                    v = A(F(b.displayValue)),
                    k = m ? v : h(v, d),
                    n = g(b.showvalue, a.showValues),
                    r = A(f.tltype, B),
                    t = A(f.trtype, B),
                    z = A(f.bltype, B),
                    D = A(f.brtype, B),
                    m = b.tlLabel,
                    l = b.trLabel,
                    w = b.blLabel,
                    C = b.brLabel,
                    H;
                r !== B && (r = "<b>" + r + q + "</b>");
                t !== B && (t = "<b>" + t + q + "</b>");
                z !== B && (z = "<b>" + z + q + "</b>");
                D !== B && (D = "<b>" + D + q + "</b>");
                a = a.showTooltip ? void 0 !==
                    p ? ab(p, [1, 2, 5, 6, 7, 14, 93, 94, 95, 96, 97, 98, 112, 113, 114, 115, 116, 117], {
                        formattedValue: d,
                        percentValue: e ? c : B,
                        yaxisName: F(f.yaxisname),
                        xaxisName: F(f.xaxisname)
                    }, {
                        value: b._value,
                        displayvalue: b.displayValue
                    }, f, b) : k === B ? !1 : (e ? "<b>Value" + q + "</b>" + d + "<br/><b>Percentage" + q + "</b>" + c : k) + (b.tlLabel !== B ? "<br/>" + (r + b.tlLabel) : B) + (b.trLabel !== B ? "<br/>" + t + b.trLabel : B) + (b.blLabel !== B ? "<br/>" + z + b.blLabel : B) + (b.brLabel !== B ? "<br/>" + D + b.brLabel : B) : B;
                n ? H = void 0 !== v ? v : e ? c : d : m = l = w = C = H = B;
                b = h(b.link);
                return {
                    displayValue: H,
                    toolText: a,
                    link: b,
                    tlLabel: m,
                    trLabel: l,
                    blLabel: w,
                    brLabel: C
                }
            },
            configureAxis: function(b, d) {
                var a = b[na],
                    c = d.chart,
                    e = b.yAxis[0],
                    f = b.xAxis,
                    q = a.rowCount,
                    m = a.columnCount,
                    v = a.axisGridManager,
                    l = a.rowIdObj,
                    p = a.columnIdObj,
                    k = this.colorManager,
                    n = y(h(c.vdivlinecolor, c.divlinecolor, k.getColor("divLineColor")), g(c.vdivlinealpha, c.divlinealpha, k.getColor("divLineAlpha"))),
                    r = g(c.vdivlinethickness, c.divlinethickness, 1),
                    t = g(c.vdivlinedashed, c.vdivlineisdashed, c.divlinedashed, c.divlineisdashed, 0) ? Ha(g(c.vdivlinedashlen, c.divlinedashlen,
                        4), g(c.vdivlinedashgap, c.divlinedashgap, 2), r) : "none",
                    z = y(h(c.hdivlinecolor, c.divlinecolor, k.getColor("divLineColor")), g(c.hdivlinealpha, c.divlinealpha, k.getColor("divLineAlpha"))),
                    D = g(c.hdivlinethickness, c.divlinethickness, 1),
                    w = g(c.hdivlinedashed, c.hdivlineisdashed, c.divlinedashed, c.divlineisdashed, 0) ? Ha(g(c.hdivlinedashlen, c.divlinedashlen, 4), g(c.hdivlinedashgap, c.divlinedashgap, 2), r) : "none",
                    C, H;
                e.min = 0;
                e.max = q;
                for (H in l) C = l[H], k = C.index, C = C.label, v.addAxisGridLine(e, k + -.5, C, .1, void 0, ha, 1), k < q &&
                    e.plotBands.push({
                        isTrend: !0,
                        color: z,
                        value: k,
                        width: D,
                        dashStyle: w,
                        zIndex: 3
                    });
                e.labels.enabled = !1;
                e.gridLineWidth = 0;
                e.alternateGridColor = ha;
                e.title.text = F(c.yaxisname);
                f.min = -.5;
                f.max = e = m + -.5;
                f.opposite = g(c.placexaxislabelsontop, 0);
                a.x.catCount = m;
                for (H in p) a = p[H], k = a.index, C = a.label, v.addXaxisCat(f, k, 1, C, a, {}, c), k -= -.5, k < e && f.plotBands.push({
                    isTrend: !0,
                    color: n,
                    value: k,
                    width: r,
                    dashStyle: t,
                    zIndex: 3
                });
                f.labels.enabled = !1;
                f.gridLineWidth = 0;
                f.alternateGridColor = ha;
                f.title.text = F(c.xaxisname)
            },
            xAxisMinMaxSetter: function() {},
            placeLegendBlockRight: function() {
                return "gradient" === arguments[0].legend.type ? u.placeGLegendBlockRight ? u.placeGLegendBlockRight.apply(this, arguments) : 0 : u.placeLegendBlockRight.apply(this, arguments)
            },
            placeLegendBlockBottom: function() {
                return "gradient" === arguments[0].legend.type ? u.placeGLegendBlockBottom ? u.placeGLegendBlockBottom.apply(this, arguments) : 0 : u.placeLegendBlockBottom.apply(this, arguments)
            }
        }, s.column2dbase);
        s("renderer.multiaxisline", {
            legendClick: function(b, d, a) {
                var c = this.options.series,
                    e =
                    this.yAxis[c[b.index].yAxis],
                    f = e.axisData._relatedSeries,
                    g = f.length,
                    m = !1;
                s["renderer.cartesian"].legendClick.call(this, b, d, a);
                if (!a) {
                    for (; g-- && !(m = c[f[g]].visible););
                    e.checkBox.element.checked = m
                }
            }
        }, s["renderer.cartesian"]);
        s("renderer.candlestick", {
            drawPlotCandlestickbar: function(b, d) {
                var a = b.data,
                    c = a.length,
                    e = b.items,
                    f = b.graphics = [],
                    q = this.paper,
                    m = this.layers,
                    h = this.definition.chart,
                    l = this.options.plotOptions.series,
                    p = this.xAxis[d.xAxis || 0],
                    k = this.yAxis[d.yAxis || 0],
                    n = d.numColumns || 1,
                    r = d.columnPosition ||
                    0,
                    t = !1 === d.visible ? "hidden" : "visible",
                    z = p.getAxisPosition(0),
                    z = p.getAxisPosition(1) - z,
                    D = l.groupPadding,
                    w = l.maxColWidth,
                    h = (1 - .01 * (h && h.plotspacepercent)) * z || J(z * (1 - 2 * D), w * n),
                    n = h / n * r - h / 2,
                    C, H, s, u, x, D = m.dataset = m.dataset || q.group("dataset-orphan");
                D.column = D.column || q.group("columns", D);
                for (m = 0; m < c; m += 1) {
                    r = a[m];
                    z = r.y;
                    C = null;
                    if (null === z) {
                        if (w = e[m]) C = w.graphic, C.attr({
                            height: 0
                        })
                    } else w = g(r.x, m), h = r.link, w = p.getAxisPosition(w), C = r.previousY, H = k.getAxisPosition(C), C = k.getAxisPosition(z), s = k.getAxisPosition(r.high),
                        u = k.getAxisPosition(r.low), S(C - H), x = n, H = ["M", w, u, "L", w, s, "M", w, C, "L", w + x, C, "M", w, H, "L", w - x, H], (w = e[m]) || (w = e[m] = {
                            index: m,
                            value: z,
                            graphic: q.path(H, D),
                            dataLabel: null,
                            tracker: null
                        }), C = w.graphic, C.attr({
                            path: H,
                            fill: ca(r.color),
                            stroke: ca(r.borderColor),
                            "stroke-width": r.borderWidth,
                            "stroke-dasharray": r.dashStyle,
                            "stroke-linecap": "round",
                            "stroke-linejoin": "round",
                            "shape-rendering": "crisp",
                            cursor: h ? "pointer" : "",
                            visibility: t
                        }).shadow(l.shadow || r.shadow), this.drawTracker && this.drawTracker.call(this, b, d, m);
                    C && f.push(C);
                    this.drawTracker && this.drawTracker.call(this, b, d, m)
                }
                b.visible = !1 !== d.visible;
                return b
            },
            drawCanvas: function() {
                s["renderer.cartesian"].drawCanvas.call(this, arguments);
                if (this.options.subCharts && this.options.subCharts[0]) {
                    var b = this.options,
                        b = (b.subCharts && b.subCharts[0]).chart || {},
                        d = this.paper,
                        a = this.elements,
                        c = a.volumeCanvas,
                        e = b.marginTop + b.top,
                        f = b.left = b.marginLeft,
                        q = b.width - b.marginLeft - b.marginRight,
                        m = b.height - b.marginBottom,
                        h = g(b.plotBorderRadius, 0),
                        l = b.plotBorderWidth,
                        p = b.plotBackgroundColor,
                        k = .5 * l,
                        n = b.plotBorderColor,
                        r = this.layers.canvas;
                    c || (a.volumeCanvas = d.rect(f - k, e - k - 1, q + l, m + l, h, r).attr({
                        fill: ca(p),
                        "stroke-width": l,
                        stroke: n,
                        "stroke-linejoin": 2 < l ? "round" : "miter",
                        "shape-rendering": "crisp"
                    }).shadow(b.plotShadow).crisp())
                }
            },
            drawTracker: function(b, d, a) {
                var c = this,
                    e = c.paper,
                    f = c.xAxis[0],
                    q = b.data[a],
                    m = c.yAxis[0].getAxisPosition(q.y),
                    h = f.getAxisPosition(g(q.x, a));
                b = b.items[a];
                a = qa ? 40 : 20;
                var l = c.layers.tracker,
                    p = c.definition.chart,
                    k = c.options.plotOptions.series,
                    n = f.getAxisPosition(0),
                    f = f.getAxisPosition(1) -
                    n,
                    n = k.groupPadding,
                    k = k.maxColWidth,
                    r = ((1 - .01 * (p && p.plotspacepercent)) * f || J(f * (1 - 2 * n), 1 * k)) / 1,
                    t = .5 * -r,
                    p = c.elements,
                    f = p.canvas.getBBox(),
                    k = p.volumeCanvas && p.volumeCanvas.getBBox(),
                    n = p.rollOverBand,
                    z = b && b.tracker,
                    r = {
                        "stroke-width": r,
                        ishot: !0,
                        stroke: ca(c.options.chart.rollOverBandColor),
                        fill: ca(c.options.chart.rollOverBandColor),
                        visibility: "hidden"
                    };
                k && z && !d.doNotUseBand && (z || (z = b.tracker = e.circle(h, m, a, l).attr({
                    "stroke-width": 0,
                    fill: ua
                })), z.data("x", h), q.toolText && z.tooltip(q.toolText), n || (n = p.rollOverBand =
                    e.path(["M", 0, f.y, "L", 0, f.y2, "M", 0, k.y, "L", 0, k.y2]).attr(r), c.layers.dataset.appendChild(n), n.toBack()), z.mouseover(function() {
                    c.rollOver(c, this, t)
                }).mouseout(function() {
                    c.rollOut(c)
                }))
            },
            rollOver: function(b, d) {
                b.elements.rollOverBand.transform("t" + d.data("x") + ",0").show()
            },
            rollOut: function(b) {
                b.elements.rollOverBand.hide()
            }
        }, s["renderer.cartesian"]);
        s("renderer.spline", {
            getSplinePath: function(b, d, a) {
                var c = function(a, b, c, d) {
                        b = (d - b) / uc((c - a) * (c - a) + (d - b) * (d - b));
                        b = S(.5 * b);
                        return a * b + c * (1 - b)
                    },
                    e = function(a,
                        b, c, d) {
                        var e = a.length,
                            f = a[e - 1],
                            g = f.length,
                            k = f[0],
                            f = f[g - 2];
                        3 > g || ("R" !== k && "C" !== k || 3 !== g || (a[e - 1][0] = "L"), b && a.push(["L", f, d, c, d, "Z"]))
                    },
                    f = /area/ig.test(this.logic.defaultSeriesType),
                    g = this.options.chart.minimizeTendency,
                    m = [null],
                    h = [],
                    l = [],
                    p = d.max,
                    k = d.min;
                d = d.getAxisPosition(0 < p && 0 < k ? k : 0 > p && 0 > k ? p : 0);
                var n, r, t, z, D, w, C, H, s, u, x, E, B, I, y;
                I = 0;
                for (y = b.length; I < y; I += 1)
                    if (H = b[I], t = b[I - 1] || {}, z = b[I + 1] || {}, k = H.x, p = H.y, n = t.x, t = t.y, D = z.x, z = z.y, w = H.lastYPos, C = H.lastXPos, u = h.length, H = l.length, g)
                        if (null !== w)
                            if (r =
                                E, I === b.length - 1) {
                                w = m[I - x - 1];
                                H = D = (k + n) / 2;
                                u = (H - n) * w + t;
                                if (t > p && u < p || t < p && u > p) u = p, H = (u - t) / w + n;
                                E.push(H, u, D, (p + t) / 2, k, p);
                                h.push(E);
                                l.push(E);
                                f && e(h, !0, B, d);
                                f && e(l, !1)
                            } else {
                                w = m[I - x - 1];
                                if (t > p && z >= p || t < p && z <= p)
                                    if (s = 0, D = c(n, t, k, p), z = p, 1 !== I - x) {
                                        H = D;
                                        u = (H - n) * w + t;
                                        if (t > p && u < p || t < p && u > p) u = p, H = (u - t) / w + n;
                                        E.push(H, u, D, z, k, p)
                                    } else E.push((k + n) / 2, (p + t) / 2, D, z, k, p);
                                else if (t === p) s = 0, E.push(n, t, k, p, k, p);
                                else if (t > p && p > z || t < p && p < z) {
                                    s = (z - t) / (D - n);
                                    D = c(n, t, k, p);
                                    z = (D - k) * s + p;
                                    if (t > p && z > t || t < p && z < t) z = t, D = (z - p) / s + k;
                                    if (1 !== I - x) {
                                        H =
                                            D;
                                        u = (H - n) * w + t;
                                        if (t > p && u < p || t < p && u > p) u = p, H = (u - t) / w + n;
                                        E.push(H, u, D, z, k, p)
                                    } else E.push((k + n) / 2, (p + t) / 2, D, z, k, p)
                                }
                                m.push(s)
                            } else null === w && 0 !== I ? (r || (r = []), "C" === r[0] && (h.push(E), l.push(E), f && e(h, !0, B, d), f && e(l, !1)), h.push(["M", k, p]), l.push(["M", k, p]), B = k, E = ["C"], x = I, m = [null]) : (h.push(["M", k, p]), l.push(["M", k, p]), B = k, E = ["C"], x = I);
                else null !== w ? 2 <= u ? ("M" === h[u - 1][0] && h.push(["R"]), "M" === l[H - 1][0] && l.push(["R"]), u = h.length, H = l.length, r = h[u - 1], n = r.length, h[u - 1].push(k), h[u - 1].push(p), l[H - 1].push(k), l[H - 1].push(p),
                    I === a - 1 && "R" === r[0] && (e(h, !0, B, d), e(l, !1))) : (h.push(["M", C, w]), h.push(["R", k, p]), l.push(["M", C, w]), l.push(["R", k, p]), B = C) : null === w && 2 <= u && (r = h[u - 1], "R" === r[0] && (e(h, !0, B, d), e(l, !1)), h.push(["M", k, p]), l.push(["M", k, p]), B = k);
                r = h[h.length - 1];
                f && r && (n = r.length, "Z" === r[n - 1] || "R" !== r[0] && "C" !== r[0] || (e(h, !0, B, d), e(l, !1)));
                f || (h = g ? h : l, 2 <= h.length && e(h, !1));
                return {
                    closedPath: h,
                    openPath: l
                }
            },
            drawPlotSpline: function(b, d) {
                var a = this,
                    c = [],
                    c = [],
                    e = a.paper,
                    f = a.elements,
                    h = a.options,
                    m = h.chart,
                    v = h.plotOptions.series,
                    l = v.dataLabels && v.dataLabels.style || {},
                    p = {
                        fontFamily: l.fontFamily,
                        fontSize: l.fontSize,
                        lineHeight: l.lineHeight,
                        fontWeight: l.fontWeight,
                        fontStyle: l.fontStyle
                    },
                    k = b.items,
                    n = b.graphics = b.graphics || [],
                    r = a.xAxis[d.xAxis || 0],
                    t = a.yAxis[d.yAxis || 0],
                    z = b.data,
                    l = [],
                    D = [],
                    w = !1 === d.visible,
                    C = w ? "hidden" : "visible",
                    H = isNaN(+v.animation) && v.animation.duration || 1E3 * v.animation,
                    u = !1 !== (h.tooltip || {}).enabled,
                    s = a.chartWidth,
                    x = a.chartHeight,
                    h = function() {
                        ia.attr({
                            "clip-rect": null
                        });
                        ma.show();
                        ba.show();
                        X.show();
                        L.attr({
                            transform: "...t" +
                                -s + "," + -x
                        })
                    },
                    E, B, I = v.connectNullData,
                    y, F, A, G, P, J, T = null,
                    M, K = d.lineWidth,
                    Z, U, N, Q, aa, Y, W = a.layers,
                    O = W.dataset = W.dataset || e.group("dataset-orphan"),
                    L = W.datalabels = W.datalabels || e.group("datalables"),
                    S = W.tracker,
                    m = m.anchorTrackingRadius,
                    fa, ba, X, ia, ma, V, pa, ea, W = function(c, f, g, k, h, q, m, l) {
                        return function() {
                            var p = g.imageUrl,
                                r = g.imageScale,
                                t = g.imageAlpha,
                                v = m.imageHoverAlpha,
                                z = m.imageHoverScale,
                                w = this.width * r * .01,
                                D = this.width * z * .01;
                            aa = {
                                x: c - this.width * r * .005,
                                y: f - this.height * r * .005,
                                width: w,
                                height: this.height *
                                    r * .01,
                                alpha: t
                            };
                            Y = {
                                x: c - this.width * z * .005,
                                y: f - this.height * z * .005,
                                width: D,
                                height: this.height * z * .01,
                                alpha: v
                            };
                            v = D > w ? Y : aa;
                            k.graphic = N = e.image(p, ma).attr(aa).css({
                                opacity: .01 * t
                            }).data("alwaysInvisible", 0 === r).data("setRolloverProperties", m).data("setRolloverAttr", Y).data("setRolloutAttr", aa).data("anchorRadius", r).data("anchorHoverRadius", z);
                            n.push(N);
                            if (G || u || m) pa = k.tracker = e.rect(S).attr(v).attr({
                                cursor: G ? "pointer" : "",
                                stroke: ua,
                                "stroke-width": g.lineWidth,
                                fill: ua,
                                ishot: !0,
                                visibility: C
                            }).data("eventArgs",
                                h).click(function(b) {
                                xa.call(this, a, b)
                            }).hover(function(b) {
                                return function(c) {
                                    a.hoverPlotAnchor(this, c, "DataPlotRollOver", b, a)
                                }
                            }(k), function(b) {
                                return function(c) {
                                    a.hoverPlotAnchor(this, c, "DataPlotRollOut", b, a)
                                }
                            }(k)).tooltip(q);
                            (V = a.drawPlotLineLabel(b, d, l, c, f)) && n.push(V)
                        }
                    },
                    wa = function(c, e, f, g, k, h, q) {
                        return function() {
                            (V = f.dataLabel = a.drawPlotLineLabel(b, d, q, c, e)) && n.push(V)
                        }
                    },
                    ha = function(b) {
                        xa.call(this, a, b)
                    },
                    da = function(b, c) {
                        return function(d) {
                            a.hoverPlotAnchor(this, d, c, b, a)
                        }
                    };
                a.addCSSDefinition(".fusioncharts-datalabels .fusioncharts-label",
                    p);
                L.insertAfter(O);
                L.attr({
                    "class": "fusioncharts-datalabels",
                    transform: "...t" + s + "," + x
                });
                H && a.animationCompleteQueue.push({
                    fn: h,
                    scope: a
                });
                p = O.line || (O.line = e.group("line-connector", O));
                ba = e.group("connector-shadow", p);
                X = e.group("anchor-shadow", p);
                ia = e.group("connector", p);
                ma = e.group("anchors", p);
                ma.hide();
                ba.hide();
                X.hide();
                E = 0;
                for (B = z.length; E < B; E += 1)
                    if (y = z[E], A = y.y, N = pa = V = null, null === A) 0 === I && (T = null);
                    else {
                        p = k[E] = {
                            chart: a,
                            index: E,
                            value: A
                        };
                        F = g(y.x, E);
                        G = y.link;
                        P = y.tooltext || y.toolText;
                        M = t.getAxisPosition(A);
                        F = r.getAxisPosition(F);
                        c.push({
                            x: F,
                            y: M,
                            lastXPos: J,
                            lastYPos: T
                        });
                        if ((U = y.marker) && U.enabled)
                            if (J = U.radius, T = U.shadow, Z = U.symbol.split("_"), fa = {
                                    index: E,
                                    link: G,
                                    value: A,
                                    displayValue: y.displayValue,
                                    categoryLabel: y.categoryLabel,
                                    toolText: y.toolText,
                                    id: b.userID,
                                    datasetIndex: b.index,
                                    datasetName: b.name,
                                    visible: b.visible
                                }, aa = Y = {}, A = y.rolloverProperties, U.imageUrl) J = new Xa.Image, J.onload = W(F, M, U, p, fa, P, A, E), J.onerror = wa(F, M, p, E), J.src = U.imageUrl;
                            else {
                                A && (aa = {
                                    polypath: [Z[1] || 2, F, M, U.radius, U.startAngle, 0],
                                    fill: ca(U.fillColor),
                                    "stroke-width": U.lineWidth,
                                    stroke: ca(U.lineColor)
                                }, Y = {
                                    polypath: [A.sides || 2, F, M, A.radius, A.startAngle, A.dip],
                                    fill: ca(A.fillColor),
                                    "stroke-width": A.lineWidth,
                                    stroke: ca(A.lineColor)
                                });
                                N = p.graphic = e.polypath(Z[1] || 2, F, M, U.radius, U.startAngle, 0, ma).attr({
                                    fill: ca(U.fillColor),
                                    "stroke-width": U.lineWidth,
                                    stroke: ca(U.lineColor),
                                    cursor: G ? "pointer" : "",
                                    "stroke-linecap": "round",
                                    "stroke-linejoin": "round",
                                    ishot: !0,
                                    visibility: 0 === J ? "hidden" : C
                                }).data("alwaysInvisible", 0 === J).data("setRolloverProperties", A).data("setRolloverAttr",
                                    Y).data("setRolloutAttr", aa).data("anchorRadius", J).data("anchorHoverRadius", A && A.radius).shadow(T || !1, X);
                                if (G || u || A) J = Ya(J, A && A.radius || 0, m), pa = e.polypath(Z[1] || 2, F, M, J, U.startAngle, 0, S).attr({
                                    cursor: G ? "pointer" : "",
                                    stroke: ua,
                                    "stroke-width": 0,
                                    ishot: !0,
                                    fill: ua,
                                    visibility: C
                                });
                                ea = pa || N;
                                ea.click(ha);
                                (pa || N).data("eventArgs", fa).hover(da(p, "DataPlotRollOver"), da(p, "DataPlotRollOut")).tooltip(P)
                            }
                        N && n.push(N);
                        ea && n.push(ea);
                        J = F;
                        T = M;
                        P = y.color;
                        Z = y.dashStyle;
                        D.push(N);
                        p.dataLabel = V;
                        p.tracker = ea;
                        U && U.imageUrl ||
                            (V = a.drawPlotLineLabel(b, d, E, F, M));
                        V && n.push(V);
                        a.drawTracker && a.drawTracker.call(a, b, d, E)
                    }
                c = a.getSplinePath(c, t).closedPath;
                2 <= c.length && (Q = b.graphic = e.path(c, ia).attr({
                    "stroke-dasharray": Z,
                    "stroke-width": K,
                    stroke: ca(P),
                    "stroke-linecap": "round",
                    "stroke-linejoin": "round",
                    visibility: C
                }).shadow(v.shadow && y.shadow, ba), l.push(Q), O.shadow(v.shadow || y.shadow));
                H ? ia.attr({
                    "clip-rect": f["clip-canvas-init"]
                }).animate({
                    "clip-rect": f["clip-canvas"]
                }, H, "normal", a.getAnimationCompleteFn()) : (h && h(), h = void 0);
                Q &&
                    n.push(Q);
                b.visible = !w;
                return b
            },
            drawPlotAreaspline: function(b, d) {
                var a = this,
                    c = [],
                    c = [],
                    e = a.paper,
                    f = a.layers,
                    h = a.options,
                    m = h.chart,
                    v = a.elements,
                    l = h.plotOptions.series,
                    p = l.dataLabels && l.dataLabels.style || {},
                    k = {
                        fontFamily: p.fontFamily,
                        fontSize: p.fontSize,
                        lineHeight: p.lineHeight,
                        fontWeight: p.fontWeight,
                        fontStyle: p.fontStyle
                    },
                    n = a.xAxis[d.xAxis || 0],
                    r = a.yAxis[d.yAxis || 0],
                    t = b.data,
                    z = (p = !1 === d.visible) ? "hidden" : "visible",
                    D = isNaN(+l.animation) && l.animation.duration || 1E3 * l.animation,
                    w = "0" === a.definition.chart.drawfullareaborder,
                    C = !1 !== (h.tooltip || {}).enabled,
                    u, s, x, B, h = b.items,
                    E = b.graphics = b.graphics || [],
                    y = null,
                    I, F = f.tracker,
                    A = f.dataset = f.dataset || e.group("dataset-orphan"),
                    G = f.datalabels = f.datalabels || e.group("datalabels").insertAfter(A),
                    J = m.anchorTrackingRadius,
                    P = a.chartWidth,
                    O = a.chartHeight,
                    f = function() {
                        Z.attr({
                            "clip-rect": null
                        });
                        K.show();
                        M.show();
                        G.attr({
                            transform: "...t" + -P + "," + -O
                        })
                    },
                    T, M, K, Z, U, N, Q = [],
                    aa, Y, W, L, S, ba, fa, V, X, ia, Q = function(c, f, g, k, h, q, m, l) {
                        return function() {
                            var n = g.imageUrl,
                                p = g.imageScale,
                                r = g.imageAlpha,
                                t =
                                m.imageHoverAlpha,
                                v = m.imageHoverScale,
                                w = this.width * p * .01,
                                D = this.width * v * .01;
                            ba = {
                                x: c - this.width * p * .005,
                                y: f - this.height * p * .005,
                                width: w,
                                height: this.height * p * .01,
                                alpha: r
                            };
                            fa = {
                                x: c - this.width * v * .005,
                                y: f - this.height * v * .005,
                                width: D,
                                height: this.height * v * .01,
                                alpha: t
                            };
                            t = D > w ? fa : ba;
                            k.graphic = Y = e.image(n, K).attr(ba).css({
                                opacity: .01 * r
                            }).data("alwaysInvisible", 0 === p).data("setRolloverProperties", m).data("setRolloverAttr", fa).data("setRolloutAttr", ba).data("anchorRadius", p).data("anchorHoverRadius", v);
                            E.push(Y);
                            if (B || C || m) W = k.tracker = e.rect(F).attr(t).attr({
                                cursor: B ? "pointer" : "",
                                stroke: ua,
                                "stroke-width": g.lineWidth,
                                fill: ua,
                                ishot: !0,
                                visibility: z
                            }).data("eventArgs", h).click(function(b) {
                                xa.call(this, a, b)
                            }).hover(function(b) {
                                return function(c) {
                                    a.hoverPlotAnchor(this, c, "DataPlotRollOver", b, a)
                                }
                            }(k), function(b) {
                                return function(c) {
                                    a.hoverPlotAnchor(this, c, "DataPlotRollOut", b, a)
                                }
                            }(k)).tooltip(q);
                            (L = k.dataLabel = a.drawPlotLineLabel(b, d, l, c, f)) && E.push(L)
                        }
                    },
                    ma = function(c, e, f, g) {
                        return function() {
                            (L = f.dataLabel = a.drawPlotLineLabel(b,
                                d, g, c, e)) && E.push(L)
                        }
                    },
                    ea = function(b) {
                        xa.call(this, a, b)
                    },
                    pa = function(b, c) {
                        return function(d) {
                            a.hoverPlotAnchor(this, d, c, b, a)
                        }
                    };
                Z = A.area = A.area || e.group("area", A);
                T = A.line || (A.line = e.group("line-connector", A));
                e.group("connector-shadow", T);
                M = e.group("anchor-shadow", T);
                m = e.group("area-connector", T);
                K = e.group("area-anchors", T);
                K.hide();
                M.hide();
                a.addCSSDefinition(".fusioncharts-datalabels .fusioncharts-label", k);
                G.insertAfter(A);
                G.attr({
                    "class": "fusioncharts-datalabels",
                    transform: "...t" + P + "," + O
                });
                D &&
                    a.animationCompleteQueue.push({
                        fn: f,
                        scope: a
                    });
                A = 0;
                for (T = t.length; A < T; A += 1)
                    if (u = t[A], x = u.y, Y = W = L = null, null === x) 0 === l.connectNullData && (y = null);
                    else {
                        X = h[A] = {
                            chart: a,
                            index: A,
                            value: x
                        };
                        s = g(u.x, A);
                        B = u.link;
                        k = u.tooltext || u.toolText;
                        s = n.getAxisPosition(s);
                        x = r.getAxisPosition(x);
                        c.push({
                            x: s,
                            y: x,
                            lastXPos: I,
                            lastYPos: y
                        });
                        if ((I = u.marker) && I.enabled)
                            if (ia = {
                                    index: A,
                                    link: B,
                                    value: u.y,
                                    displayValue: u.displayValue,
                                    categoryLabel: u.categoryLabel,
                                    toolText: k,
                                    id: b.userID,
                                    datasetIndex: b.index,
                                    datasetName: b.name,
                                    visible: b.visible
                                },
                                N = I.radius, V = I.shadow, aa = I.symbol.split("_"), ba = fa = {}, y = u.rolloverProperties, I.imageUrl) N = new Xa.Image, N.onload = Q(s, x, I, X, ia, k, y, A), N.onerror = ma(s, x, X, A), N.src = I.imageUrl;
                            else {
                                if (y = u.rolloverProperties) ba = {
                                    polypath: [aa[1] || 2, s, x, N, I.startAngle, 0],
                                    fill: ca(I.fillColor),
                                    "stroke-width": I.lineWidth,
                                    stroke: ca(I.lineColor)
                                }, fa = {
                                    polypath: [y.sides || 2, s, x, y.radius, y.startAngle, y.dip],
                                    fill: ca(y.fillColor),
                                    "stroke-width": y.lineWidth,
                                    stroke: ca(y.lineColor)
                                };
                                Y = X.graphic = e.polypath(aa[1] || 2, s, x, N, I.startAngle, 0,
                                    K).attr({
                                    fill: ca(I.fillColor),
                                    "stroke-width": I.lineWidth,
                                    stroke: ca(I.lineColor),
                                    "stroke-linecap": "round",
                                    cursor: B ? "pointer" : "",
                                    ishot: !0,
                                    visibility: 0 === N ? "hidden" : z
                                }).data("alwaysInvisible", 0 === N).data("setRolloverProperties", y).data("setRolloverAttr", fa).data("setRolloutAttr", ba).data("anchorRadius", N).data("anchorHoverRadius", y && y.radius).shadow(V || !1, M);
                                if (B || C || y) N = Ya(N, y && y.radius || 0, J), W = e.polypath(aa[1] || 2, s, x, N, I.startAngle, 0, F).attr({
                                    cursor: B ? "pointer" : "",
                                    stroke: ua,
                                    "stroke-width": 0,
                                    ishot: !0,
                                    fill: ua,
                                    visibility: z
                                });
                                (W || Y).data("eventArgs", ia).click(ea).hover(pa(X, "DataPlotRollOver"), pa(X, "DataPlotRollOut")).tooltip(k)
                            }
                        Y && E.push(Y);
                        a.drawTracker && a.drawTracker.call(a, b, d, A);
                        X.graphic = Y;
                        X.dataLabel = L;
                        X.tracker = void 0;
                        I && I.imageUrl || (L = a.drawPlotLineLabel(b, d, A, s, x));
                        L && E.push(L);
                        a.drawTracker && a.drawTracker.call(a, b, d, A);
                        I = s;
                        y = x
                    }
                n = a.getSplinePath(c, r, T);
                c = n.closedPath;
                Q = n.openPath;
                2 <= c.length && (c = e.path(c, Z).attr({
                    fill: ca(d.fillColor),
                    "stroke-dasharray": d.dashStyle,
                    "stroke-width": w ? 0 : d.lineWidth,
                    stroke: ca(d.lineColor),
                    "stroke-linecap": "round",
                    visibility: z
                }).shadow(l.shadow && u.shadow), b.graphic = c, E.push(c));
                D ? S = Z.attr({
                    "clip-rect": v["clip-canvas-init"]
                }).animate({
                    "clip-rect": v["clip-canvas"]
                }, D, "normal", a.getAnimationCompleteFn()) : (f && f(), f = void 0);
                w && (2 <= Q.length && (U = e.path(Q, m).attr({
                    stroke: ca(d.lineColor),
                    "stroke-width": d.lineWidth,
                    "stroke-dasharray": u.dashStyle || d.dashStyle,
                    "stroke-linecap": "round",
                    visibility: z
                }).shadow(l.shadow || u.shadow)), E.push(U), D && m.attr({
                    "clip-rect": v["clip-canvas-init"]
                }).animateWith(Z,
                    S, {
                        "clip-rect": v["clip-canvas"]
                    }, D, "normal"));
                b.visible = !p;
                return b
            }
        }, s["renderer.cartesian"]);
        s("renderer.kagi", {
            drawPlotKagi: function(b, d) {
                var a = this,
                    c = a.paper,
                    e = a.options,
                    f = a.elements,
                    g = b.data,
                    m = e.plotOptions.series,
                    v = a.xAxis[d.xAxis || 0],
                    l = a.yAxis[d.yAxis || 0],
                    p = d.canvasPadding,
                    k = d.xShiftLength,
                    n = b.items,
                    r = a.logic,
                    t = !1 === d.visible ? "hidden" : "visible",
                    z = !1 !== (e.tooltip || {}).enabled,
                    D = {
                        stroke: ca({
                            color: d.rallyColor,
                            alpha: d.rallyAlpha
                        }),
                        "stroke-linecap": "round",
                        "stroke-linejoin": "round",
                        "stroke-width": d.rallyThickness ||
                            d.lineWidth,
                        "stroke-dasharray": d.rallyDashed
                    },
                    w = {
                        stroke: ca({
                            color: d.declineColor,
                            alpha: d.declineAlpha
                        }),
                        "stroke-linecap": "round",
                        "stroke-linejoin": "round",
                        "stroke-width": d.declineThickness || d.lineWidth,
                        "stroke-dasharray": d.declineDashed
                    },
                    u = {
                        "true": D["stroke-width"],
                        "false": w["stroke-width"]
                    },
                    s = a.layers,
                    x = s.dataset = s.dataset || c.group("dataset-orphan"),
                    y = s.datalabels = s.datalabels || c.group("datalabels").insertAfter(x),
                    B = s.tracker,
                    s = isNaN(+m.animation) && m.animation.duration || 1E3 * m.animation,
                    E = f["clip-canvas-init"].slice(0),
                    f = f["clip-canvas"].slice(0),
                    A = 0,
                    I = v.getAxisPosition(A),
                    F = a.chartWidth,
                    G = a.chartHeight,
                    J = function() {
                        da.attr({
                            "clip-rect": null
                        });
                        pa.show();
                        wa.show();
                        y.attr({
                            transform: "...t" + -F + "," + -G
                        })
                    },
                    O = [],
                    P = [],
                    L, T, M, K, Z, U, N, Q, aa, Y, W, S, ba, X, fa, V, ea, ia, ma = e.chart.anchorTrackingRadius,
                    ha, pa, da, wa, na, ga, la, sa, mb, rb, qa;
                if (g.length) {
                    e = x.line || (x.line = c.group("line-connector", x));
                    c.group("connector-shadow", e);
                    pa = c.group("anchor-shadow", e);
                    da = c.group("connector", e);
                    wa = c.group("anchors", e);
                    wa.hide();
                    pa.hide();
                    y.attr({
                        transform: "...t" +
                            F + "," + G
                    });
                    s && a.animationCompleteQueue.push({
                        fn: J,
                        scope: this
                    });
                    N = !!g[0].isRally;
                    e = 0;
                    for (x = g.length; e < x; e += 1) n[e] = {
                        chart: a,
                        index: e,
                        graphic: null,
                        line: [],
                        dataLabel: null,
                        tracker: null
                    }, Q = g[e], aa = Q.y, Q.isDefined || (aa = Q.plotValue), aa = h(Q.plotValue, aa), Q.plotY = nb(l.getAxisPosition(Q.y), 2), Q.graphY = nb(l.getAxisPosition(aa), 2), Q.plotX = I, Q.isShift && (A += 1, I = v.getAxisPosition(A)), e && (aa = g[e - 1], N = Q && Q.objParams && Q.objParams.isRally, ba = Q && Q.objParams && Q.objParams.lastHigh, X = Q && Q.objParams && Q.objParams.lastLow, fa =
                        Q && Q.objParams && Q.objParams.isRallyInitialised, aa && fa && aa.isRally !== Q.isRally ? (Q.isChanged = !0, Q.ty = nb(l.getAxisPosition(N ? ba : X), 2)) : Q.isChanged = !1);
                    v = a.canvasLeft + p;
                    I = v + k / 2;
                    Y = g[0].plotY;
                    N = !!g[0].isRally;
                    l = oa(Y) + u[N] % 2 / 2;
                    N ? O.push("M", v, l, "H", I) : P.push("M", v, l, "H", I);
                    lb(g, function(e, f) {
                        if (V = g[f + 1]) ia = ["M", I, Y], N = e.isRally, e.isShift && (I += k, Y = e.graphY, ia.push("H", I), ia[2] = oa(ia[2]) + u[N] % 2 / 2, ia = ia.toString(), N ? O.push(ia) : P.push(ia), ia = ["M", I, Y]), V.isChanged && (Y = V.ty, ia.push("V", Y), ia[1] = oa(ia[1]) + u[N] %
                            2 / 2, ia = ia.toString(), N ? O.push(ia) : P.push(ia), ia = ["M", I, Y]), ea = V.isRally, V.graphY !== ia[2] && (ia.push("V", V.graphY), ia[1] = oa(ia[1]) + u[ea] % 2 / 2, ia = ia.toString(), ea ? O.push(ia) : P.push(ia)), Y = V.graphY;
                        W = e.plotX;
                        S = e.plotY;
                        K = e.marker;
                        L = e && e.link;
                        T = e && e.toolText;
                        if (void 0 !== S && !isNaN(S) && e.isDefined)
                            if (Z = K.symbol.split("_"), mb = "spoke" === Z[0] ? 1 : 0, rb = K.radius, na = K.shadow, ha = {
                                    index: f,
                                    link: L,
                                    value: e.y,
                                    displayValue: e.displayValue,
                                    categoryLabel: e.categoryLabel,
                                    toolText: T,
                                    id: b.userID,
                                    datasetIndex: b.index,
                                    datasetName: b.name,
                                    visible: b.visible
                                }, ga = la = {}, sa = e.rolloverProperties, K.imageUrl) qa = new Xa.Image, qa.onload = function(e, f, g, k, h, m, q, l) {
                                return function() {
                                    var n = g.imageUrl,
                                        p = g.imageScale,
                                        r = g.imageAlpha,
                                        v = q.imageHoverAlpha,
                                        w = q.imageHoverScale,
                                        D = this.width * p * .01,
                                        u = this.width * w * .01;
                                    ga = {
                                        x: e - this.width * p * .005,
                                        y: f - this.height * p * .005,
                                        width: D,
                                        height: this.height * p * .01,
                                        alpha: r
                                    };
                                    la = {
                                        x: e - this.width * w * .005,
                                        y: f - this.height * w * .005,
                                        width: u,
                                        height: this.height * w * .01,
                                        alpha: v
                                    };
                                    v = u > D ? la : ga;
                                    k.graphic = U = c.image(n, wa).attr(ga).css({
                                        opacity: .01 *
                                            r
                                    }).data("alwaysInvisible", 0 === p).data("setRolloverProperties", q).data("setRolloverAttr", la).data("setRolloutAttr", ga).data("anchorRadius", p).data("anchorHoverRadius", w);
                                    if (L || z || q) M = k.tracker = c.rect(B).attr(v).attr({
                                        cursor: L ? "pointer" : "",
                                        stroke: ua,
                                        "stroke-width": g.lineWidth,
                                        fill: ua,
                                        ishot: !0,
                                        visibility: t
                                    }).data("eventArgs", h).click(function(b) {
                                        xa.call(this, a, b)
                                    }).hover(function(b) {
                                        return function(c) {
                                            a.hoverPlotAnchor(this, c, "DataPlotRollOver", b, a)
                                        }
                                    }(k), function(b) {
                                        return function(c) {
                                            a.hoverPlotAnchor(this,
                                                c, "DataPlotRollOut", b, a)
                                        }
                                    }(k)).tooltip(m);
                                    k.dataLabel = a.drawPlotKagiLabel(b, d, l, e, f)
                                }
                            }(W, S, K, n[f], ha, T, sa, f), qa.onerror = function(c, e, f, g, k, h, m, q) {
                                return function() {
                                    g.dataLabel = a.drawPlotKagiLabel(b, d, q, c, e)
                                }
                            }(W, S, K, n[f], ha, T, sa, f), qa.src = K.imageUrl;
                            else {
                                !r.multisetRealtime && sa && (ga = {
                                    polypath: [Z[1] || 2, W, S, rb, K.startAngle, mb],
                                    fill: ca(K.fillColor),
                                    "stroke-width": K.lineWidth,
                                    stroke: ca(K.lineColor)
                                }, la = {
                                    polypath: [sa.sides || 2, W, S, sa.radius, sa.startAngle, sa.dip],
                                    fill: ca(sa.fillColor),
                                    "stroke-width": sa.lineWidth,
                                    stroke: ca(sa.lineColor)
                                });
                                U = n[f].graphic = c.polypath(Z[1] || 2, W, S, rb, K.startAngle, mb, wa).attr({
                                    fill: ca(K.fillColor),
                                    "stroke-width": K.lineWidth,
                                    stroke: ca(K.lineColor),
                                    "stroke-linecap": "round",
                                    cursor: L ? "pointer" : "",
                                    ishot: !0,
                                    visibility: 0 === rb ? "hidden" : t
                                }).data("alwaysInvisible", 0 === rb).data("setRolloverProperties", sa).data("setRolloverAttr", la).data("setRolloutAttr", ga).data("anchorRadius", rb).data("anchorHoverRadius", sa && sa.radius).shadow(na || !1, pa);
                                if (L || z) rb = Ya(rb, sa && sa.radius || 0, ma), M = c.circle(W,
                                    S, rb, B).attr({
                                    cursor: L ? "pointer" : "",
                                    stroke: ua,
                                    ishot: !0,
                                    fill: ua,
                                    "stroke-width": K.lineWidth,
                                    visibility: t
                                }).data("eventArgs", ha).click(function(b) {
                                    xa.call(this, a, b)
                                }).hover(function(b) {
                                    return function(c) {
                                        a.hoverPlotAnchor(this, c, "DataPlotRollOver", b, a)
                                    }
                                }(n[f]), function(b) {
                                    return function(c) {
                                        a.hoverPlotAnchor(this, c, "DataPlotRollOut", b, a)
                                    }
                                }(n[f])).tooltip(T);
                                n[f].tracker = M || U;
                                K && K.imageUrl || (n[f].dataLabel = a.drawPlotKagiLabel(b, d, f, W, S))
                            }
                    });
                    D = c.path(O, da).attr(D).shadow(m.shadow);
                    n[0].line.push(D);
                    D =
                        c.path(P, da).attr(w).shadow(m.shadow);
                    n[0].line.push(D);
                    s ? da.attr({
                        "clip-rect": E
                    }).animate({
                        "clip-rect": f
                    }, s, "normal", a.getAnimationCompleteFn()) : (J && J(), J = void 0)
                }
            },
            drawPlotKagiLabel: function(b, d, a, c, e, f) {
                var g = this.options,
                    h = g.chart,
                    v = this.paper,
                    l = this.layers,
                    p = g.plotOptions.series.dataLabels.style,
                    g = 1 === h.rotateValues ? 270 : 0,
                    k = this.canvasHeight,
                    n = this.canvasTop,
                    r = this.canvasLeft,
                    t = b.data[a],
                    z = b.items[a];
                b = (b = z.graphic) && "image" == b.type && .5 * b.attr("height") || t.marker && t.marker.radius - 3;
                b = h.valuePadding +
                    2 + b;
                d = !1 === d.visible ? "hidden" : "visible";
                a = z.dataLabel;
                var D = {
                        fontFamily: p.fontFamily,
                        fontSize: p.fontSize,
                        lineHeight: p.lineHeight,
                        fontWeight: p.fontWeight,
                        fontStyle: p.fontStyle
                    },
                    w, u, s;
                f = f || l.datalabels;
                l = t.displayValue;
                ba(l) && l !== B ? (a ? g && a.rotate(360 - g) : a = z.dataLabel = v.text(f).attr({
                        text: l,
                        fill: p.color,
                        direction: h.textDirection,
                        "text-bound": [p.backgroundColor, p.borderColor, p.borderThickness, p.borderPadding, p.borderRadius, p.borderDash]
                    }).css(D), a.attr({
                        title: t.originalText || "",
                        fill: p.color
                    }), v = a.getBBox(),
                    f = l = g ? v.width : v.height, h = e, f = f + b + 4, l = .5 * l + b, g ? (r = !0, t.vAlign === Ma ? (h -= l, r = e - n < f) : t.vAlign === Ia && (h += l - 2, w = 1, r = e + f > n + k), r && (u = 1, c -= b + 3 + .5 * v.height, h = e)) : t.vAlign === Ma ? h -= l : t.vAlign === Ia ? (h += l, w = 1) : v.width > c - r ? h -= l : (u = 1, c -= b + 3, s = "end"), a.attr({
                        x: c,
                        y: h,
                        "text-anchor": s,
                        visibility: d
                    }).data("isBelow", w).data("isMiddle", u), g && a.attr("transform", "T0,0,R" + g)) : a && a.attr({
                    text: B
                });
                return a
            }
        }, s["renderer.cartesian"]);
        s("renderer.boxandwhisker", {
            drawPlotBoxandwhisker2d: function(b, d) {
                var a = this,
                    c = a.paper,
                    e = a.options,
                    f = e.plotOptions.series,
                    h = a.xAxis[d.xAxis || 0],
                    m = a.yAxis[d.xAxis || 0],
                    v = isNaN(+f.animation) && f.animation.duration || 1E3 * f.animation,
                    l = a.layers,
                    p = l.dataset = l.dataset || c.group("dataset-orphan"),
                    k = l.datalabels = l.datalabels || c.group("datalabels"),
                    n = d.data,
                    r = b.items || (b.items = []),
                    t = !1 === d.visible ? "hidden" : "visible",
                    z = !1 !== (e.tooltip || {}).enabled,
                    u = d.columnPosition || 0,
                    w = a.definition.chart,
                    s = h.getAxisPosition(0),
                    H = h.getAxisPosition(1) - s,
                    x = f.groupPadding,
                    y = f.maxColWidth,
                    s = d.numColumns || 1,
                    H = (1 - .01 * (w && w.plotspacepercent)) *
                    H || J(H * (1 - 2 * x), y * s),
                    w = H / s,
                    u = u * w - H / 2,
                    s = e.chart,
                    H = 1 === s.rotateValues ? 270 : void 0,
                    x = g(s.valuePadding, 0),
                    y = p.upperBoxGroup = p.upperBoxGroup || c.group("upperBox", p),
                    A = p.lowerBoxGroup = p.lowerBoxGroup || c.group("lowerBox", p),
                    E = p.medianGroup = p.medianGroup || c.group("median", p),
                    F = b.graphics = b.graphics || [],
                    I = r.displayValues = {},
                    G = I.upperQuartileValues = [],
                    O = I.lowerQuartileValues = [],
                    I = I.medianValues = [],
                    L = function(b) {
                        xa.call(this, a, b)
                    },
                    l = l.shadows || (l.shadows = c.group("shadows", p).toBack()),
                    e = e.plotOptions.series.dataLabels.style,
                    S = {
                        fontFamily: e.fontFamily,
                        fontSize: e.fontSize,
                        lineHeight: e.lineHeight,
                        fontWeight: e.fontWeight,
                        fontStyle: e.fontStyle
                    },
                    P = function(b, c) {
                        return function(d) {
                            b.upperBox.attr(c.upperBox);
                            b.lowerBox.attr(c.lowerBox);
                            b.upperBoxBorder.attr(c.upperBoxBorder);
                            b.lowerBoxBorder.attr(c.lowerBoxBorder);
                            b.upperQuartile.attr(c.upperQuartile);
                            b.lowerQuartile.attr(c.lowerQuartile);
                            b.medianBorder.attr(c.median);
                            xa.call(this, a, d, "DataPlotRollOver")
                        }
                    },
                    V = function(b, c) {
                        return function(d) {
                            b.upperBox.attr(c.upperBox);
                            b.lowerBox.attr(c.lowerBox);
                            b.upperBoxBorder.attr(c.upperBoxBorder);
                            b.lowerBoxBorder.attr(c.lowerBoxBorder);
                            b.upperQuartile.attr(c.upperQuartile);
                            b.lowerQuartile.attr(c.lowerQuartile);
                            b.medianBorder.attr(c.median);
                            xa.call(this, a, d, "DataPlotRollOut")
                        }
                    },
                    T, M, K, Z, U, N, Q, aa, Y, W, X, ea, ha, fa, da, ga, ia, ma, na, pa, la, wa, qa, ua, ta, sa;
                ma = 0;
                for (na = n.length; ma < na; ma += 1) M = n[ma], K = M.y, U = M.link, N = M.tooltext || M.toolText, (T = r[ma]) || (T = r[ma] = {
                    index: ma,
                    value: K,
                    upperBox: null,
                    lowerBox: null,
                    upperBoxBorder: null,
                    lowerBoxBorder: null,
                    upperQuartileBorder: null,
                    lowerQuartileBorder: null,
                    medianBorder: null,
                    upperQuartileValues: null,
                    lowerQuartileValues: null,
                    medianValues: null,
                    tracker: null,
                    hot: null
                }), null !== K && (m.getAxisPosition(K), K = g(M.x, ma), K = h.getAxisPosition(K), u && (K += u), Z = f.borderRadius || 0, aa = ((aa = (ha = M.upperQuartile || {}, ha.value)) || 0 === aa) && m.getAxisPosition(aa), Q = ((Q = (fa = M.lowerQuartile || {}, fa.value)) || 0 === Q) && m.getAxisPosition(Q), W = ((Y = (ia = M.median) && ia.value) || 0 === Y) && m.getAxisPosition(Y), X = W - aa, ea = Q - W, da = M.upperBoxBorder || {}, ga = M.lowerBoxBorder || {},
                    Y = {
                        index: ma,
                        link: U,
                        maximum: M.displayValueMax,
                        minimum: M.displayValueMin,
                        median: Y,
                        q3: ha.value,
                        q1: fa.value,
                        maxDisplayValue: M.displayValueMax,
                        minDisplayValue: M.displayValueMin,
                        medianDisplayValue: M.displayValueMid,
                        q1DisplayValue: M.displayValueQ1,
                        q3DisplayValue: M.displayValueQ3,
                        categoryLabel: M.categoryLabel,
                        toolText: M.toolText,
                        id: b.userID,
                        datasetIndex: b.index,
                        datasetName: b.name,
                        visible: b.visible
                    }, pa = oa(K) + da.borderWidth % 2 * .5, la = oa(K + w) + da.borderWidth % 2 * .5, wa = oa(aa) + ha.borderWidth % 2 * .5, w = la - pa, sa = M.hoverEffects.rollOut = {
                        upperBox: {
                            fill: ca(M.color.FCcolor),
                            "stroke-width": 0,
                            "stroke-dasharray": da.dashStyle,
                            cursor: U ? "pointer" : "",
                            ishot: !0,
                            visibility: t
                        },
                        lowerBox: {
                            fill: ca(M.lowerboxColor.FCcolor),
                            "stroke-width": 0,
                            "stroke-dasharray": ga.dashStyle,
                            cursor: U ? "pointer" : B,
                            ishot: !0,
                            visibility: t
                        },
                        upperBoxBorder: {
                            stroke: da.color,
                            "stroke-width": da.borderWidth,
                            "stroke-linecap": "round",
                            dashstyle: da.dashStyle,
                            ishot: !0,
                            visibility: t
                        },
                        lowerBoxBorder: {
                            stroke: ga.color,
                            "stroke-width": ga.borderWidth,
                            dashstyle: ga.dashStyle,
                            "stroke-linecap": "round",
                            ishot: !0,
                            visibility: t
                        },
                        upperQuartile: {
                            stroke: ca(ha.color),
                            "stroke-width": ha.borderWidth,
                            "stroke-dasharray": ha.dashSyle,
                            "stroke-linecap": "round",
                            cursor: U ? "pointer" : B,
                            ishot: !0,
                            visibility: t
                        },
                        lowerQuartile: {
                            stroke: ca(fa.color),
                            "stroke-width": fa.borderWidth,
                            "stroke-dasharray": fa.dashSyle,
                            cursor: U ? "pointer" : "",
                            "stroke-linecap": "round",
                            ishot: !0,
                            visibility: t
                        },
                        median: {
                            stroke: ca(ia.color),
                            "stroke-width": ia.borderWidth,
                            "stroke-dasharray": ia.dashSyle,
                            cursor: U ? "pointer" : "",
                            "stroke-linecap": "round",
                            ishot: !0,
                            visibility: t
                        }
                    },
                    U = T.graphic = T.upperBox = c.rect(pa, wa, w, X, Z, y).attr(sa.upperBox).shadow(f.shadow && M.shadow, l), X = T.upperBoxBorder = c.path(["M", pa, wa, "V", wa + X, "M", la, wa, "V", wa + X], y).attr(sa.upperBoxBorder).shadow(f.shadow && da.shadow, l), da = T.upperQuartile = c.path(["M", pa, wa, "H", pa + w], E).attr(sa.upperQuartile).shadow(f.shadow && ha.shadow, l), pa = oa(K) + ga.borderWidth % 2 * .5, la = oa(K + w) + ga.borderWidth % 2 * .5, wa = oa(W + ea) + fa.borderWidth % 2 * .5, Z = T.lowerBox = c.rect(pa, W, w, wa - W, Z, A).attr(sa.lowerBox).shadow(f.shadow && M.shadow, l), ga = T.lowerBoxBorder =
                    c.path(["M", pa, W, "V", W + ea, "M", la, W, "V", W + ea], A).attr(sa.lowerBoxBorder).shadow(f.shadow && ga.shadow, l), wa = oa(W + ea) + fa.borderWidth % 2 * .5, ea = T.lowerQuartile = c.path(["M", pa, wa, "H", pa + w], E).attr(sa.lowerQuartile).shadow(f.shadow && ha.shadow, l), wa = oa(W) + ia.borderWidth % 2 * .5, pa = T.medianBorder = c.path(["M", pa, wa, "H", pa + w], E).attr(sa.median), wa = b.index + "_" + ma, U.click(L).hover(P(T, M.hoverEffects), V(T, sa)).data("groupId", wa).data("eventArgs", Y), Z.click(L).hover(P(T, M.hoverEffects), V(T, sa)).data("groupId", wa).data("eventArgs",
                        Y), X.click(L).hover(P(T, M.hoverEffects), V(T, sa)).data("groupId", wa).data("eventArgs", Y), ga.click(L).hover(P(T, M.hoverEffects), V(T, sa)).data("groupId", wa).data("eventArgs", Y), da.click(L).hover(P(T, M.hoverEffects), V(T, sa)).data("groupId", wa).data("eventArgs", Y), ea.click(L).hover(P(T, M.hoverEffects), V(T, sa)).data("groupId", wa).data("eventArgs", Y), pa.click(L).hover(P(T, M.hoverEffects), V(T, sa)).data("groupId", wa).data("eventArgs", Y), Y = H ? Ja : Va, ba(ha.displayValue) && ha.displayValue !== B && (qa = G[ma] = c.text(k).attr({
                        text: ha.displayValue,
                        x: K + w / 2,
                        title: ha.originalText || "",
                        y: aa - x,
                        "text-anchor": H ? "start" : Y,
                        "vertical-align": H ? "middle" : "bottom",
                        visibility: t,
                        direction: s.textDirection,
                        fill: e.color,
                        "text-bound": [e.backgroundColor, e.borderColor, e.borderThickness, e.borderPadding, e.borderRadius, e.borderDash]
                    }).hover(P(T, M.hoverEffects), V(T, sa)).data("groupId", wa).css(S), H && qa.rotate(H, K + w / 2, aa - x)), ba(ia.displayValue) && ia.displayValue !== B && (ta = I[ma] = c.text(k).attr({
                        text: ia.displayValue,
                        x: K + w / 2,
                        y: W - x,
                        title: ia.originalText || "",
                        "text-anchor": H ?
                            "start" : Y,
                        "vertical-align": H ? "middle" : "bottom",
                        visibility: t,
                        direction: s.textDirection,
                        fill: e.color,
                        "text-bound": [e.backgroundColor, e.borderColor, e.borderThickness, e.borderPadding, e.borderRadius, e.borderDash]
                    }).hover(P(T, M.hoverEffects), V(T, sa)).data("groupId", wa).css(S), H && ta.rotate(H, K + w / 2, W - x)), ba(fa.displayValue) && fa.displayValue !== B && (ua = O[ma] = c.text(k).attr({
                        text: fa.displayValue,
                        x: K + w / 2,
                        y: Q + x,
                        title: fa.originalText || "",
                        "text-anchor": H ? "start" : Y,
                        "vertical-align": H ? "middle" : "top",
                        visibility: t,
                        direction: s.textDirection,
                        fill: e.color,
                        "text-bound": [e.backgroundColor, e.borderColor, e.borderThickness, e.borderPadding, e.borderRadius, e.borderDash]
                    }).hover(P(T, M.hoverEffects), V(T, sa)).data("groupId", wa).css(S), H && ua.rotate(H, K + w / 2, Q + x)), z && (U.tooltip(N), Z.tooltip(N), X.tooltip(N), ga.tooltip(N), da.tooltip(N), ea.tooltip(N), pa.tooltip(N), qa && qa.tooltip(N), ta && ta.tooltip(N), ua && ua.tooltip(N)), U && F.push(U), Z && F.push(Z), pa && F.push(pa), X && F.push(X), ga && F.push(ga), da && F.push(da), ea && F.push(ea), qa && F.push(qa), ta && F.push(ta), ua && F.push(ua));
                p.attr({
                    "clip-rect": [a.canvasLeft, a.canvasTop, v ? 0 : a.canvasWidth, a.canvasHeight]
                });
                v && p.animate({
                    "clip-rect": [a.canvasLeft, a.canvasTop, a.canvasWidth, a.canvasHeight]
                }, v, "normal");
                b.visible = !1 !== d.visible
            }
        }, s["renderer.cartesian"]);
        s("renderer.dragnode", {
            drawPlotDragnode: function(b, d) {
                var a = this,
                    c = b.graphics = [],
                    e = {},
                    f = a.options,
                    q = f.tooltip,
                    m = f._FCconf.inCanvasStyle,
                    v = a.paper,
                    l = a.layers,
                    p = b.items,
                    k = l.dataset,
                    n = l.connector,
                    r = e.xAxis = a.xAxis[d.xAxis || 0],
                    t = e.yAxis = a.yAxis[d.yAxis || 0],
                    z = d.data,
                    s = e.elements = {
                        data: []
                    },
                    w = a.smartLabel,
                    C = f.plotOptions.series.dataLabels.style,
                    x = f.orphanStyles.connectorlabels.style,
                    y = f.connectors,
                    A = f.connectorsStore,
                    G = f.pointStore || (f.pointStore = []),
                    E = f.invalConnectStore,
                    J = {
                        fontFamily: C.fontFamily,
                        fontSize: C.fontSize,
                        lineHeight: C.lineHeight,
                        fontWeight: C.fontWeight,
                        fontStyle: C.fontStyle
                    },
                    I = function(b) {
                        G[b.from] && G[b.to] ? A.push(new Ga(b, G, x, v, n, a)) : E.push(b)
                    },
                    ka = function() {
                        var b = this;
                        b.data("fire_click_event", 1);
                        clearTimeout(b._longpressactive);
                        b._longpressactive = setTimeout(function() {
                            b.data("fire_click_event",
                                0);
                            b.data("viewMode") || a.logic.showLabelDeleteUI(a, b)
                        }, 1E3)
                    },
                    L = function() {
                        this.data("fire_click_event") && (this.data("fire_click_event", 0), db.call(this))
                    },
                    S = function(b) {
                        var c = this.data("fire_click_event");
                        db.call(this);
                        c && xa.call(this, a, b, "LabelClick")
                    },
                    V = function(b) {
                        xa.call(this, a, b, "LabelRollover")
                    },
                    P = function(b) {
                        xa.call(this, a, b, "LabelRollout")
                    },
                    X = function(b, c, d, e, f) {
                        d = this.data("data");
                        e = d.bBox;
                        var g = a.canvasTop + a.canvasHeight,
                            h = a.canvasLeft + a.canvasWidth;
                        this.data("fire_dragend") || (xa.call(this,
                            a, f, "LabelDragStart"), this.data("fire_dragend", 1));
                        e.x + b < a.canvasLeft && (b = a.canvasLeft - e.x);
                        e.x2 + b > h && (b = h - e.x2);
                        e.y + c < a.canvasTop && (c = a.canvasTop - e.y);
                        e.y2 + c > g && (c = g - e.y2);
                        this.attr({
                            x: e.x + b,
                            y: e.y + c
                        });
                        d.label.attr({
                            x: d.ox + b,
                            y: d.oy + c
                        })
                    },
                    T = function() {
                        var a = this.data("data"),
                            b = this.getBBox();
                        a.ox = a.label.attr("x");
                        a.oy = a.label.attr("y");
                        a.bBox = b;
                        this.data("fire_dragend", 0)
                    },
                    M = function(b) {
                        var c = this.data("data"),
                            d = c.label,
                            e = {
                                hcJSON: {
                                    dragableLabels: []
                                }
                            },
                            f = this.data("eventArgs"),
                            g = f.x = a.xAxis[0].getAxisPosition(d.attr("x"),
                                1),
                            d = f.y = a.yAxis[0].getAxisPosition(d.attr("y"), 1);
                        e.hcJSON.dragableLabels[c.labelNode.index] = {
                            y: d,
                            x: g
                        };
                        O(a.logic.chartInstance.jsVars._reflowData, e, !0);
                        this.data("fire_dragend") && (c = dc(a.container, b), c.sourceEvent = "labeldragend", u.raiseEvent("chartupdated", O(c, f), a.logic.chartInstance), xa.call(this, a, b, "labeldragend"))
                    },
                    K, Z, U, N, Q, aa, Y, W, ea, ha, da, fa, ga, la, ia, ma, na, pa, qa, wa, oa, ta, va, sa;
                n || (n = l.connector = v.group("connectors").insertBefore(k));
                q && !1 !== q.enabled && n.trackTooltip(!0);
                l = s.group = v.group(k);
                q = s.dragLabelGroup = v.group(k);
                w.setStyle(C);
                k = 0;
                for (s = z.length; k < s; k += 1) {
                    K = z[k];
                    N = K.marker;
                    K._yPos = U = t.getAxisPosition(K.y);
                    K._xPos = Z = r.getAxisPosition(K.x);
                    aa = p[k] || (p[k] = {});
                    sa = K.hoverEffects && K.hoverEffects.rolloverProperties;
                    N = aa.graphic;
                    Y = aa.image;
                    W = aa.label;
                    N = K.marker;
                    if (void 0 !== U && !isNaN(U) && N) {
                        da = K._config = K._config || {
                            shapeArg: {},
                            startConnectors: [],
                            endConnectors: []
                        };
                        fa = da.shapeArg;
                        ga = g(N && N.height);
                        la = g(N && N.width);
                        ia = g(N && N.radius);
                        Q = h(N && N.symbol);
                        ma = "rectangle" === Q;
                        ea = K.id;
                        na = K.imageNode;
                        pa = K.imageURL;
                        qa = K.imageAlign;
                        wa = K.labelAlign;
                        oa = ma ? la : 1.4 * ia;
                        ta = g(K.imageWidth, oa);
                        ma = ma ? ga : 1.4 * ia;
                        va = g(K.imageHeight, ma);
                        ia = {
                            fill: ca(N.fillColor),
                            "stroke-width": N.lineWidth,
                            r: N.radius,
                            stroke: ca(N.lineColor)
                        };
                        Q = fa.symbol = h(N && N.symbol, e.symbol);
                        Q = Q.split("_");
                        fa.x = Z;
                        fa.y = U;
                        fa.radius = N.radius;
                        fa.width = la;
                        fa.height = ga;
                        fa.sides = Q[1];
                        "poly" === Q[0] || "circle" === Q[0] ? N = v.polypath(Q[1], Z, U, N.radius, N.startAngle, 0, l).attr(ia) : (da.shapeType = kc, fa.x = Z - la / 2, fa.y = U - ga / 2, fa.r = 0, ia.width = la, ia.height = ga, ia.x = Z -
                            la / 2, ia.y = U - ga / 2, sa && K.hoverEffects.enabled && (sa.x = Z - sa.width / 2, sa.y = U - sa.height / 2, delete sa.r), delete ia.r, N = v.rect(fa.x, fa.y, la, ga, 0, l).attr(ia));
                        if (na && pa) {
                            va > ma && (va = ma);
                            ta > oa && (ta = oa);
                            switch (qa) {
                                case "middle":
                                    sa = U - va / 2;
                                    break;
                                case "bottom":
                                    sa = ma > va ? U + ma / 2 - va : U - va / 2;
                                    break;
                                default:
                                    sa = ma > va ? U - .5 * ma : U - va / 2
                            }
                            da.imageX = Z - ta / 2;
                            da.imageY = sa;
                            Y || (Y = v.image(l));
                            Y.attr({
                                src: pa,
                                x: da.imageX,
                                y: sa,
                                width: ta,
                                height: va
                            })
                        }
                        da = K.displayValue;
                        if (ba(da) || da !== B) {
                            oa = w.getSmartText(da, oa, ma);
                            da = .5 * ma - .5 * oa.height;
                            switch (wa) {
                                case "top":
                                    da = -da;
                                    break;
                                case "bottom":
                                    break;
                                default:
                                    da = 0
                            }
                            K._yAdjustment = wa = da;
                            U += wa;
                            W ? W.attr({
                                text: oa.text,
                                title: oa.tooltext || "",
                                fill: C.color,
                                x: Z,
                                y: U
                            }) : (W = v.text(l), W.attr({
                                text: oa.text,
                                fill: C.color,
                                x: Z,
                                y: U,
                                direction: f.chart.textDirection,
                                "text-bound": [C.backgroundColor, C.borderColor, C.borderThickness, C.borderPadding, C.borderRadius, C.borderDash]
                            }).css(J))
                        }
                        G[ea] = K;
                        aa.index = k;
                        aa.graphic = N;
                        aa.label = W;
                        aa.image = Y;
                        ea = a.drawTracker && a.drawTracker.call(a, b, d, k, ia);
                        N && c.push(N);
                        W && c.push(W);
                        Y && c.push(Y);
                        ea && c.push(ea)
                    }
                    aa.index =
                        k;
                    aa.tracker = ea
                }
                if (A)
                    for (k = E.length - 1; 0 <= k; --k) c = E[k], G[c.from] && G[c.to] && (E.splice(k, 1), A.push(new Ga(c, G, x, v, n, a)));
                else
                    for (A = f.connectorsStore = [], E = f.invalConnectStore = [], k = 0; k < y.length; k += 1) lb(y[k].connector, I);
                if (!a.dragLabelsDrawn && (ha = f.dragableLabels) && 0 < (s = ha.length)) {
                    oa = a.plotSizeX;
                    ma = a.plotSizeY;
                    c = parseInt(m.fontSize, 10);
                    p = m.backgroundColor;
                    z = m.borderColor;
                    for (k = 0; k < s; k += 1)
                        if (w = ha[k], w.index = k, J = F(h(w.text, w.label))) J = F(J), I = r.getAxisPosition(w.x || 0), U = t.getAxisPosition(w.y || 0, 0, 1, 0, 1),
                            K = g(w.fontsize, c), Z = Ta(h(w.color, m.color)), l = g(w.alpha, 100) / 100, C = g(w.allowdrag, 1), wa = .8 * K, y = g(w.padding, 5), Y = {
                                fontSize: K + "px",
                                fontFamily: m.fontFamily,
                                fill: Z,
                                color: Z,
                                opacity: l
                            }, Da(Y), Z = h(w.bgcolor, p), aa = h(w.bordercolor, z), K = {
                                link: w.link,
                                text: J,
                                x: I,
                                y: U,
                                allowdrag: C,
                                sourceType: "labelnode"
                            }, Z && (Y.backgroundColor = Z.replace(ob, Qa), Y.backgroundOpacity = l), aa && (Y.borderColor = aa.replace(ob, Qa), Y.borderOpacity = l), J = v.text(q).css(Y).attr({
                                text: J,
                                x: I,
                                y: U,
                                align: Va,
                                direction: f.chart.textDirection,
                                "text-bound": [(w.bgcolor ||
                                    "").replace(ob, Qa), (w.bordercolor || "").replace(ob, Qa), g(w.borderthickness, 1), y, g(w.radius, 0), g(w.dashed, 0) ? Ha(g(w.dashlen, 5), g(w.dashgap, 4), g(w.borderthickness, 1)) : "none"]
                            }), I = J.getBBox(), y = v.rect(I.x - y, I.y - y, I.width + 2 * y, I.height + 2 * y, 0).attr({
                                fill: ua,
                                ishot: !0,
                                "stroke-width": 0
                            }).css({
                                cursor: C ? "move" : ""
                            }).mousedown(ka).mousemove(L).mouseup(S).data("viewMode", f.chart.viewMode).hover(V, P), q.appendChild(y), y.data("data", {
                                label: J,
                                labelNode: w,
                                chart: a
                            }).data("eventArgs", K).data("link", w.link), C && y.drag(X,
                                T, M);
                    a.dragLabelsDrawn = !0
                }
                return e
            },
            drawTracker: function(b, d, a, c) {
                var e = this,
                    f = e.paper,
                    g = b.data[a],
                    m = b.items[a],
                    v = g._config,
                    l = e.layers.tracker,
                    p = cb({}, v.pointAttr),
                    k = v.shapeArg,
                    n = k.x,
                    r = k.y,
                    t = k.width,
                    z = k.height,
                    u = k.radius,
                    w = e.dragStart,
                    s = e.dragUp,
                    x = e.dragMove,
                    y = g.link ? "pointer" : g.allowDrag ? "move" : "",
                    B = m.tracker;
                p.fill = ua;
                p.stroke = ua;
                p.cursor = y;
                p.ishot = !0;
                B = "rect" === v.shapeType ? f.rect(n, r, t, z, 0).attr(p) : f.polypath(k.sides, n, r, u, k.startAngle).attr(p);
                f = {
                    index: a,
                    link: g.link,
                    y: g.y,
                    x: g.x,
                    shape: h(g._options.shape,
                        "rect"),
                    width: t,
                    height: z,
                    radius: u,
                    sides: k.sides,
                    label: g.displayValue,
                    toolText: g.toolText,
                    id: g.id,
                    datasetIndex: b.index,
                    datasetName: b.name,
                    sourceType: "dataplot"
                };
                m.tracker = B.hover(function(a, b) {
                    return function(c) {
                        a.graphic.attr(b);
                        xa.call(this, e, c, "DataPlotRollOver")
                    }
                }(m, g.hoverEffects.rolloverProperties), function(a, b) {
                    return function(c) {
                        a.graphic.attr(b);
                        xa.call(this, e, c, "DataPlotRollOut")
                    }
                }(m, c)).data("eventArgs", f).data("drag-options", {
                    plotItems: m,
                    dataObj: g,
                    endConnectors: v.endConnectors,
                    startConnectors: v.startConnectors,
                    boundaryTop: e.canvasTop,
                    boundaryBottom: e.canvasTop + e.canvasHeight,
                    boundaryLeft: e.canvasLeft,
                    boundaryRight: e.canvasLeft + e.canvasWidth,
                    cloneGroup: e.layers.dataset,
                    datasetIndex: b.index,
                    pointIndex: a,
                    dataOptions: d,
                    cursor: y,
                    chart: e,
                    link: g.link
                }).tooltip(g.toolText);
                l.appendChild(B);
                B.drag(function(a, b, c, d, f) {
                    x.call(this, a, b, c, d, f, e)
                }, function(a, b, c) {
                    w.call(this, a, b, c, e)
                }, function(a) {
                    s.call(this, a, e)
                });
                return B
            },
            dragStart: function(b, d, a, c) {
                var e = this;
                b = e.paper;
                d = e.data("drag-options") || {};
                var f = d.dataObj,
                    g = d.plotItems,
                    h = g.cloneGroup,
                    v = g.cloneGraphic,
                    l = g.cloneImage,
                    p = g.cloneLabel,
                    k = e.getBBox(),
                    n = qa && Na(a) || Eb,
                    r = a.layerX || n.layerX,
                    t = a.layerY || n.layerY,
                    z = u.getPosition(c.container),
                    s = c.elements,
                    w = s.waitElement,
                    C = d.dataOptions,
                    x = c.layers.tracker,
                    y = {
                        opacity: .3
                    };
                e.data("fire_click_event", 1);
                e.data("mousedown", 1);
                void 0 === r && (r = (a.pageX || n.pageX) - z.left, t = (a.pageY || n.pageY) - z.top);
                clearTimeout(e._longpressactive);
                e.data("move", !0);
                c.options.chart.viewMode || (w || (w = s.waitElement = b.ringpath(r, t, 8, 11, 0, 0, x).attr({
                    fill: ca({
                        alpha: "100,100",
                        angle: 120,
                        color: "CCCCCC,FFFFFF",
                        ratio: "30,50"
                    }),
                    "stroke-width": 0
                })), r += 11, t -= 21, w.attr({
                    ringpath: [r, t, 8, 11, 0, 0]
                }).show().animate({
                    ringpath: [r, t, 8, 11, 0, 6.28]
                }, 1E3), e._longpressactive = setTimeout(function() {
                    var a = C.name !== B && void 0 !== C.name ? C.name + Ba + " " : B,
                        b = C.id,
                        d = f._options,
                        g = {
                            circle: "circ",
                            polygon: "poly",
                            undefined: "rect"
                        }[d.shape];
                    s.waitElement && s.waitElement.hide();
                    e.data("fire_click_event", 0);
                    c.logic.showNodeUpdateUI(c, {
                        x: {
                            value: f.x
                        },
                        y: {
                            value: f.y
                        },
                        draggable: {
                            value: ea(d.allowdrag, 1)
                        },
                        color: {
                            value: d.color
                        },
                        alpha: {
                            value: d.alpha
                        },
                        label: {
                            value: ea(d.label, d.name)
                        },
                        tooltip: {
                            value: d.tooltext
                        },
                        shape: {
                            value: g
                        },
                        rectWidth: {
                            value: d.width
                        },
                        rectHeight: {
                            value: d.height
                        },
                        circPolyRadius: {
                            value: d.radius
                        },
                        polySides: {
                            value: d.numsides
                        },
                        image: {
                            value: d.imagenode
                        },
                        imgWidth: {
                            value: d.imagewidth
                        },
                        imgHeight: {
                            value: d.imageheight
                        },
                        imgAlign: {
                            value: d.imagealign
                        },
                        imgUrl: {
                            value: d.imageurl
                        },
                        id: {
                            value: f.id,
                            disabled: !0
                        },
                        link: {
                            value: d.link
                        },
                        dataset: {
                            innerHTML: '<option value="' + b + '">' + a + b + "</option>",
                            disabled: !0
                        }
                    }, !0)
                }, 1E3));
                d.bBoxX = k.x;
                d.bBoxX2 = k.x2 || k.x + k.width;
                d.bBoxY = k.y;
                d.bBoxY2 = k.y2 || k.y + k.height;
                d.origX = d.lastX || (d.lastX = 0);
                d.origY = d.lastY || (d.lastY = 0);
                d.draged = !1;
                d.startYValue = f.y;
                d.startXValue = f.x;
                h || (h = g.cloneGroup = b.group(d.cloneGroup).attr(y));
                g.graphic && !v && (v = g.cloneGraphic = g.graphic.clone(), h.appendChild(v), v.attr(y));
                g.image && !l && (l = g.cloneImage = g.image.clone(), h.appendChild(l).attr(y));
                g.label && !p && (p = g.cloneLabel = g.label.clone(), h.appendChild(p).attr(y));
                h.show()
            },
            dragMove: function(b, d, a, c, e, f) {
                a = this.data("drag-options");
                c = a.plotItems;
                var g = a.bBoxX2 + b,
                    h = a.bBoxY + d,
                    v = a.bBoxY2 + d,
                    l = f.elements;
                a.bBoxX + b < a.boundaryLeft && (b = a.boundaryLeft - a.bBoxX);
                g > a.boundaryRight && (b = a.boundaryRight - a.bBoxX2);
                h < a.boundaryTop && (d = a.boundaryTop - a.bBoxY);
                v > a.boundaryBottom && (d = a.boundaryBottom - a.bBoxY2);
                if (b || d) l.waitElement && l.waitElement.hide(), this.data("fire_click_event", 0), db.call(this);
                a.dataObj.allowDrag && (g = a._transformObj = {
                        transform: "t" + (a.origX + b) + "," + (a.origY + d)
                    }, this.attr(g), c.cloneGraphic && c.cloneGraphic.attr(g), c.cloneImage &&
                    c.cloneImage.attr(g), c.cloneLabel && c.cloneLabel.attr(g), a.draged || xa.call(this, f, e, "DataplotDragStart"), a.draged = !0, a.lastX = b, a.lastY = d)
            },
            dragUp: function(b) {
                var d = this.data("drag-options"),
                    a = d.plotItems,
                    c = d.chart,
                    e = c.xAxis[0],
                    f = c.yAxis[0],
                    g = c.logic,
                    h = g.tooltipSepChar,
                    v = g.numberFormatter,
                    l = d.dataObj,
                    p = c.elements,
                    k = this.data("fire_click_event"),
                    n, r, t;
                p.waitElement && p.waitElement.hide();
                db.call(this);
                this.data("mousedown", 0);
                k && xa.call(this, c, b);
                if (d.draged) {
                    d.lastX += d.origX;
                    d.lastY += d.origY;
                    k = l._xPos +
                        d.lastX;
                    p = l._yPos + d.lastY;
                    n = d.startConnectors;
                    r = n.length;
                    for (t = 0; t < r; t += 1) n[t].updateFromPos(k, p);
                    n = d.endConnectors;
                    r = n.length;
                    for (t = 0; t < r; t += 1) n[t].updateToPos(k, p);
                    a.label && a.label.attr(d._transformObj);
                    a.image && a.image.attr(d._transformObj);
                    a.graphic && a.graphic.attr(d._transformObj);
                    e = e.getAxisPosition(k, 1);
                    f = f.getAxisPosition(p, 1);
                    l._isUserTooltip || l.toolText === B || (l.toolText = l._toolTextStr + v.dataLabels(e) + h + v.dataLabels(f));
                    v = this.data("eventArgs");
                    l.x = v.x = e;
                    l.y = v.y = f;
                    h = dc(c.container, b);
                    h.sourceEvent =
                        "dataplotdragend";
                    u.raiseEvent("chartupdated", O(h, v), c.logic.chartInstance);
                    xa.call(this, c, b, "dataplotdragend");
                    b = {
                        hcJSON: {
                            series: []
                        }
                    };
                    b.hcJSON.series[d.datasetIndex] = {
                        data: []
                    };
                    b.hcJSON.series[d.datasetIndex].data[d.pointIndex] = {
                        _options: {
                            x: e,
                            y: f
                        },
                        x: e,
                        y: f,
                        toolText: l.toolText,
                        displayValue: l.displayValue
                    };
                    O(g.chartInstance.jsVars._reflowData, b, !0)
                }
                a.cloneGroup && a.cloneGroup.hide()
            }
        }, s["renderer.cartesian"]);
        s("renderer.dragcolumn2d", {
            drawTracker: function(b, d, a) {
                var c = this.paper,
                    e = this.yAxis[0],
                    f = b.data[a],
                    g = e.getAxisPosition(f.y),
                    h = b.items[a],
                    v = this.layers.tracker,
                    l = h && h.dragTracker || null,
                    p = this.dragStart,
                    k = this.dragUp,
                    n = this.dragMove,
                    r = {
                        stroke: ua,
                        "stroke-width": qa ? 40 : 10,
                        ishot: !0,
                        cursor: fb && "ns-resize" || "n-resize"
                    },
                    t = e && e.axisData && e.axisData.plotLines,
                    z = this._yAxisPlotLines || (this._yAxisPlotLines = []),
                    s = 0,
                    w, u;
                if (!z.length)
                    for (w = t.length; s < w; s += 1) u = t[s], u.isGrid && z.push(e.getAxisPosition(u.value));
                null !== f.y && f.allowDrag && (e = h.graphic.getBBox(), e = ["M", e.x, g, "L", e.x + e.width, g, "Z"], l ? l.animate({
                        d: e
                    }).attr(r) :
                    l = h.dragTracker = c.path(e, v).attr(r), l.drag(n, p, k).data("drag-options", {
                        items: h,
                        yPos: g,
                        chart: this,
                        datasetIndex: b.index,
                        pointIndex: a,
                        dataOptions: d,
                        dataObj: f
                    }), h.dragTracker = l)
            },
            dragStart: function() {
                var b = this.data("drag-options"),
                    d = b.chart,
                    a = d.yAxis[0],
                    c = a.max,
                    a = a.min,
                    e = this.getBBox();
                b.barH = b.items.graphic.getBBox().height;
                b.isAllPositive = 0 < c && 0 < a;
                b.isAllPositiveZero = 0 < c && 0 <= a;
                b.isAllNegative = 0 > c && 0 > a;
                b.isAllNegativeZero = 0 >= c && 0 > a;
                b.isPositiveNegative = 0 < c && 0 > a;
                b.boundaryTop = d.canvasTop;
                b.boundaryBottom =
                    d.canvasTop + d.canvasHeight;
                b.bBoxY = e.y;
                b.bBoxY2 = e.y2 || e.y + e.height;
                b.startValue = b.dataObj.y;
                b.origX = b.lastX || (b.lastX = 0);
                b.origY = b.lastY || (b.lastY = 0);
                b.draged = !1
            },
            dragMove: function(b, d) {
                var a = this.data("drag-options"),
                    c = a.items,
                    e = a.dataObj,
                    f = a.chart,
                    g = f.options.chart,
                    h = f.yAxis[0],
                    v = f.logic.numberFormatter,
                    l = h.yBasePos,
                    p = c.dataLabel,
                    k = {},
                    n = a.bBoxY2 + d,
                    r = a.bBoxY + d,
                    t = f.canvasBottom,
                    s = e.allowNegDrag ? t : l,
                    u = f.canvasTop,
                    w = parseFloat(e.borderWidth) || 0,
                    g = g.isCanvasBorder,
                    C = a.isAllNegativeZero,
                    x = a.isPositiveNegative,
                    y = a.dataOptions;
                r < a.boundaryTop && (d = a.boundaryTop - a.bBoxY);
                n > s && (d = s - a.bBoxY2);
                r = a._transformObj = {
                    transform: "t0," + (a.origY + d)
                };
                a.draged || (n = {
                    dataIndex: a.pointIndex + 1,
                    datasetIndex: y.__i + 1,
                    startValue: a.startValue,
                    datasetName: y.name
                }, G.raiseEvent("dataplotDragStart", n, f.logic.chartInstance));
                n = a.yPos + d;
                n <= l ? (k.y = n, k.height = l - n) : (k.y = l, k.height = n - l);
                g && !x && (C ? k.y -= k.y - (u - w / 2) : k.height = t - k.y + w / 2);
                this.attr(r);
                c.graphic.animate(k);
                a.shapeAttr = k;
                c = a.value = oa(1E8 * h.getAxisPosition(n, 1)) / 1E8;
                v = v.dataLabels(c);
                va.pointUpdate(e, v, c);
                p && f.drawPlotColumnLabel(f.plots[a.datasetIndex], a.dataOptions, a.pointIndex, void 0, n).attr("text", a.dataObj.displayValue);
                a.draged = !0;
                a.lastX = b;
                a.lastY = d
            },
            dragUp: function() {
                var b = this.data("drag-options"),
                    d = b.chart,
                    a = d.logic,
                    c = !d.options.chart.doNotSnap,
                    e = b.dataObj,
                    f = b.dataOptions,
                    g, h;
                b.draged && (g = b.yPos + b.lastY, c && (h = va.snapPoint(d, e, g), h - g && d.dragMove.call(this, 0, h - b.yPos)), b.yPos = h, b.lastX += b.origX, b.lastY += b.origY, c = {
                        dataIndex: b.pointIndex + 1,
                        datasetIndex: f.__i + 1,
                        startValue: b.startValue,
                        endValue: b.dataObj.y = b.value,
                        datasetName: f.name
                    }, f = [d.logic.chartInstance.id, c.dataIndex, c.datasetIndex, c.datsetName, c.startValue, c.endValue], G.raiseEvent("dataplotDragEnd", c, d.logic.chartInstance), u.raiseEvent("chartupdated", c, d.logic.chartInstance, f), c = {
                        hcJSON: {
                            series: []
                        }
                    }, c.hcJSON.series[b.datasetIndex] = {
                        data: []
                    }, b.items.tracker.attr(b.shapeAttr).tooltip(e.toolText), c.hcJSON.series[b.datasetIndex].data[b.pointIndex] = {
                        y: b.value,
                        toolText: e.toolText,
                        displayValue: e.displayValue
                    }, va.setMinMaxValue(d),
                    O(a.chartInstance.jsVars._reflowData, c, !0))
            }
        }, s["renderer.cartesian"]);
        s("renderer.dragline", {
            drawTracker: function(b, d, a) {
                var c = this.paper,
                    e = this.yAxis[0],
                    f = this.xAxis[0],
                    g = b.data[a],
                    h = b.items[a],
                    v = qa ? 20 : Ya(g.marker && g.marker.radius || 0, 5),
                    l = this.layers.tracker,
                    p = h.tracker || null,
                    k = this.dragStart,
                    n = this.dragUp,
                    r = this.dragMove,
                    t = {
                        fill: ua,
                        "stroke-width": 0,
                        cursor: fb && "ns-resize" || "n-resize"
                    },
                    s = e && e.axisData && e.axisData.plotLines,
                    u = this._yAxisPlotLines || (this._yAxisPlotLines = []),
                    w = 0,
                    C, x;
                if (!u.length)
                    for (C =
                        s.length; w < C; w += 1) x = s[w], x.isGrid && u.push(e.getAxisPosition(x.value));
                null !== g.y && g.allowDrag && (f = f.getAxisPosition(a), e = e.getAxisPosition(g.y), p || (p = h.tracker = c.circle(f, e, v, l).attr(t)), p.attr({
                    cursor: fb && "ns-resize" || "n-resize",
                    ishot: !0
                }).drag(r, k, n).data("drag-options", {
                    items: b.items,
                    yPos: e,
                    chart: this,
                    datasetIndex: b.index,
                    pointIndex: a,
                    dataOptions: d,
                    dataObj: g
                }))
            },
            dragStart: function() {
                var b = this.data("drag-options"),
                    d = b.items,
                    a = b.pointIndex,
                    c = d[a + 1],
                    d = d[a],
                    c = b.nextGraph = c && c.connector,
                    d = b.currGraph =
                    d && d.connector,
                    a = b.chart;
                b._origY = b._lastY || (b._lastY = 0);
                b.boundaryTop = a.canvasTop;
                b.boundaryBottom = a.canvasTop + a.canvasHeight;
                b.currPath = d && d.attr("path");
                b.nextPath = c && c.attr("path");
                b.startValue = b.dataObj.y;
                b.origY = this.attr("cy");
                b.origX = this.attr("cx");
                b.draged = !1
            },
            dragMove: function(b, d) {
                var a = this.data("drag-options"),
                    c = a.items[a.pointIndex],
                    e = a.nextPath,
                    f = a.currPath,
                    g = a.dataObj,
                    h = a.chart,
                    v = h.elements.plots[a.datasetIndex],
                    l = h.yAxis[0],
                    p = h.logic.numberFormatter,
                    k = l.yBasePos,
                    n = c.dataLabel,
                    r =
                    g.allowNegDrag ? a.boundaryBottom : k,
                    t = a.dataOptions,
                    k = a.origY + d;
                a.draged || (t = {
                    dataIndex: a.pointIndex + 1,
                    datasetIndex: t.__i + 1,
                    startValue: a.startValue,
                    datasetName: t.name
                }, G.raiseEvent("dataplotDragStart", t, h.logic.chartInstance));
                k < a.boundaryTop && (d = a.boundaryTop - a.origY);
                k > r && (d = r - a.origY);
                k = a.origY + d;
                this.animate({
                    cy: k
                });
                c.graphic && c.graphic.attr("transform", "t0," + (a._origY + d));
                e && e[0] && a.nextGraph && (fb ? e[0][2] = k : e[2] = k, a.nextGraph.animate({
                    path: e
                }));
                f && f[1] && a.currGraph && (fb ? f[1][2] = k : f[5] = k, a.currGraph.animate({
                    path: f
                }));
                c = g.y = a.value = oa(1E8 * l.getAxisPosition(k, 1)) / 1E8;
                p = p.dataLabels(c);
                va.pointUpdate(g, p, c);
                n && h.drawPlotLineLabel(h.plots[a.datasetIndex], a.dataOptions, a.pointIndex, a.origX, k).attr("text", g.displayValue);
                a.draged = !0;
                a.lastY = d;
                h.getAreaPath && v.graphic && v.graphic.attr({
                    path: h.getAreaPath(v.data)
                })
            },
            dragUp: function() {
                var b = this.data("drag-options"),
                    d = b.chart,
                    a = d.logic,
                    c = !d.options.chart.doNotSnap,
                    e = b.dataObj,
                    f = b.dataOptions,
                    g, h;
                b.draged && (g = b.yPos + b.lastY, c && (h = va.snapPoint(d, e, g), h - g && d.dragMove.call(this,
                    0, h - b.yPos)), b.yPos = h, b._lastY = b.lastY + b._origY, b.lastY += b.origY, f = {
                    dataIndex: b.pointIndex + 1,
                    datasetIndex: f.__i + 1,
                    startValue: b.startValue,
                    endValue: b.dataObj.y = b.value,
                    datasetName: f.name
                }, c = [d.logic.chartInstance.id, f.dataIndex, f.datasetIndex, f.datasetName, f.startValue, f.endValue], G.raiseEvent("dataplotDragEnd", f, d.logic.chartInstance), u.raiseEvent("chartupdated", f, d.logic.chartInstance, c), c = {
                    hcJSON: {
                        series: []
                    }
                }, c.hcJSON.series[b.datasetIndex] = {
                    data: []
                }, c.hcJSON.series[b.datasetIndex].data[b.pointIndex] = {
                    y: b.value,
                    toolText: e.toolText,
                    displayValue: e.displayValue
                }, b.items[b.pointIndex].tracker.tooltip(e.toolText), va.setMinMaxValue(d), O(a.chartInstance.jsVars._reflowData, c, !0))
            }
        }, s["renderer.cartesian"]);
        s("renderer.dragarea", {
            getAreaPath: function(b) {
                for (var d = this.xAxis[0], a = this.yAxis[0], c = a.yBasePos, e = b.length, f = 0, g = [], h = [], v = [], l = !0, p, k, n; f < e; f += 1) k = b[f], v[f] = d.getAxisPosition(f), h[f] = null, null !== k.y && (h[f] = a.getAxisPosition(k.y), n = b[f - 1] ? b[f - 1].y : null, k = b[f + 1] ? b[f + 1].y : null, null !== n ? (l ? (g.push("M",
                    v[f - 1], c, "L", v[f - 1], h[f - 1], "L", v[f], h[f]), p = f - 1) : g.push("L", v[f], h[f]), null === k && g.push("L", v[f], c, "L", v[p], c), l = !1) : l = !0);
                return g
            }
        }, s["renderer.dragline"]);
        s("renderer.heatmap", {
            drawPlotHeatmap: function(b, d) {
                var a = this,
                    c = b.data,
                    e = b.items,
                    f = b.graphics = b.graphics || [],
                    h = a.paper,
                    m = a.layers,
                    v = a.options,
                    l = v.chart,
                    p = l.showHoverEffect,
                    k = !1 !== (v.tooltip || {}).enabled,
                    n = v.plotOptions.series,
                    v = a.xAxis[d.xAxis || 0],
                    r = a.yAxis[d.yAxis || 0],
                    n = isNaN(+n.animation) && n.animation.duration || 1E3 * n.animation,
                    t = !1 === d.visible ?
                    "hidden" : "visible",
                    s, u, w = v.getAxisPosition(0),
                    x = v.getAxisPosition(1),
                    y = r.getAxisPosition(0),
                    B = r.getAxisPosition(1),
                    w = x - w,
                    y = y - B,
                    l = g(l.useRoundEdges, 0),
                    B = d.borderColor,
                    x = d.borderWidth,
                    A = d.dashStyle,
                    F = w / 2,
                    E = y / 2,
                    G = m.dataset = m.dataset || h.group("dataset-orphan"),
                    I = m.datalabels = m.datalabels || h.group("datalables").insertAfter(G),
                    m = m.tracker,
                    J = a.chartWidth,
                    L = a.chartHeight,
                    O = function(b) {
                        xa.call(this, a, b)
                    },
                    S = function(b) {
                        xa.call(this, a, b, "DataPlotRollOver")
                    },
                    P = function(b) {
                        xa.call(this, a, b, "DataPlotRollOut")
                    },
                    V = function(a, b) {
                        return function() {
                            a.attr({
                                fill: ca(b)
                            })
                        }
                    },
                    T, M, K, Z, U, N, Q, aa, Y;
                n && (I.attr({
                    transform: "t" + J + "," + L
                }), a.animationCompleteQueue.push({
                    fn: function() {
                        I.attr({
                            transform: "t0,0"
                        })
                    },
                    scope: this
                }));
                J = 0;
                for (L = c.length; J < L; J++) {
                    Z = c[J];
                    M = Z.y;
                    T = null;
                    if (null !== M) {
                        U = Z.link;
                        N = Z.toolText || Z.tooltext;
                        T = ca(Z.setColor || Z.color);
                        u = (s = Z.visible) && !1 === s ? "hiddden" : t;
                        Q = g(Z.x, J);
                        Q = v.getAxisPosition(Q) - F;
                        Y = r.getAxisPosition(M);
                        aa = Y + E;
                        M = {
                            link: U,
                            value: Z.value,
                            columnId: Z.columnId,
                            rowId: Z.rowId,
                            displayValue: Z.displayValue,
                            tlLabel: Z.tlLabel,
                            trLabel: Z.trLabel,
                            blLabel: Z.blLabel,
                            brLabel: Z.brLabel,
                            toolText: N,
                            id: b.userID,
                            datasetIndex: b.index,
                            datasetName: b.name,
                            visible: b.visible
                        };
                        T = h.rect(Q, Y, w, y, l, G).attr({
                            fill: T,
                            stroke: B,
                            "stroke-width": x,
                            "stroke-dasharray": A,
                            "stroke-linejoin": "miter",
                            "shape-rendering": 0 === l ? "crisp" : "",
                            cursor: U ? "pointer" : "",
                            opacity: n ? 0 : Z.setAlpha && +Z.setAlpha / 100 || 1
                        }).crisp().attr({
                            visibility: u
                        });
                        n && T.animate({
                            opacity: Z.setAlpha && +Z.setAlpha / 100 || 1
                        }, n, "normal", a.getAnimationCompleteFn());
                        if (p || k || U) K = h.rect(Q,
                            Y, w, y, l, m).attr({
                            cursor: U ? "pointer" : "",
                            stroke: ua,
                            "stroke-width": x,
                            fill: ua,
                            ishot: !0
                        }).data("eventArgs", M);
                        (K || T).click(O).hover(S, P).tooltip(N);
                        1 === p && T && K && K.hover(V(T, Z.hoverColor), V(T, Z.setColor || Z.color));
                        e[J] = {
                            index: J,
                            value: Z.value,
                            graphic: T,
                            tracker: K,
                            dataLabel: null,
                            dataLabels: [],
                            visible: s || "hidden" !== u
                        };
                        s = a.drawLabelHeatmap.call(a, b, d, J);
                        T && f.push(T);
                        K && f.push(K);
                        u = 0;
                        for (Z = s.length; u < Z; u++) !e[J].dataLabels && (e[J].dataLabels = []), s[u] && f.push(s[u]), e[J].dataLabels.push(s[u])
                    }
                    a.drawTracker && a.drawTracker.call(a,
                        b, J, Q, aa)
                }
                b.visible = !1 !== d.visible;
                return b
            },
            drawLabelHeatmap: function(b, d, a) {
                var c = b.items[a],
                    e = b.data[a],
                    f = this.options;
                b = this.paper;
                a = this.layers.datalabels;
                var g = f.plotOptions.series.dataLabels,
                    h = g.style;
                d = !1 === d.visible ? "hidden" : Ab;
                var v = e.displayValue,
                    f = f.chart.textDirection,
                    l = e.tlLabel,
                    p = e.trLabel,
                    k = e.blLabel,
                    e = e.brLabel,
                    n = g.tlLabelStyle,
                    r = g.trLabelStyle,
                    t = g.blLabelStyle,
                    g = g.brLabelStyle,
                    s = {
                        fontFamily: n.fontFamily,
                        fontSize: n.fontSize,
                        lineHeight: n.lineHeight,
                        fontWeight: n.fontWeight,
                        fontStyle: n.fontStyle
                    },
                    u = {
                        fontFamily: r.fontFamily,
                        fontSize: r.fontSize,
                        lineHeight: r.lineHeight,
                        fontWeight: r.fontWeight,
                        fontStyle: r.fontStyle
                    },
                    w = {
                        fontFamily: t.fontFamily,
                        fontSize: t.fontSize,
                        lineHeight: t.lineHeight,
                        fontWeight: t.fontWeight,
                        fontStyle: t.fontStyle
                    },
                    x = {
                        fontFamily: g.fontFamily,
                        fontSize: g.fontSize,
                        lineHeight: g.lineHeight,
                        fontWeight: g.fontWeight,
                        fontStyle: g.fontStyle
                    },
                    y = c.tlLabel,
                    A = c.trLabel,
                    F = c.blLabel,
                    J = c.brLabel,
                    E = this.smartLabel,
                    G = c.dataLabel,
                    I = [],
                    L = {
                        fontFamily: h.fontFamily,
                        fontSize: h.fontSize,
                        lineHeight: h.lineHeight,
                        fontWeight: h.fontWeight,
                        fontStyle: h.fontStyle
                    },
                    O, S, V, P, X, T, M, K;
                P = c.graphic.getBBox();
                O = P.width;
                S = P.height;
                V = P.x;
                P = P.y;
                E.setStyle(h);
                ba(v) && v !== B && (M = E.getSmartText(v, O, S, !1), v = M.text, G || (G = c.dataLabel = b.text(a)), G.attr({
                    text: v,
                    title: M.tooltext || "",
                    visibility: d,
                    fill: h.color,
                    direction: f,
                    x: V + .5 * O,
                    y: P + .5 * S,
                    "text-bound": [h.backgroundColor, h.borderColor, h.borderThickness, h.borderPadding, h.borderRadius, h.borderDash]
                }).css(L), I.push(G));
                v = ba(l) && l !== B;
                L = ba(p) && p !== B;
                X = ba(k) && k !== B;
                T = ba(e) && e !== B;
                h = O * (v &&
                    L ? .5 : .9);
                G = .5 * (S - (M && M.height || 0));
                K = P + 4;
                v && (E.setStyle(n), M = E.getSmartText(l, h, G, !1), v = M.text, l = V, y || (y = c.tlLabel = b.text(a)), y.attr({
                    text: v,
                    title: M.tooltext || "",
                    visibility: d,
                    fill: n.color,
                    "text-anchor": "start",
                    "vertical-align": Ma,
                    direction: f,
                    x: l + 4,
                    y: K,
                    "text-bound": [n.backgroundColor, n.borderColor, n.borderThickness, n.borderPadding, n.borderRadius, n.borderDash]
                }).css(s), a.appendChild(y), I.push(y));
                L && (E.setStyle(r), M = E.getSmartText(p, h, G, !1), v = M.text, l = V + O, A || (A = c.trLabel = b.text(a)), A.attr({
                    text: v,
                    title: M.tooltext ||
                        "",
                    visibility: d,
                    fill: r.color,
                    "text-anchor": "end",
                    "vertical-align": Ma,
                    direction: f,
                    x: l - 4,
                    y: K,
                    "text-bound": [r.backgroundColor, r.borderColor, r.borderThickness, r.borderPadding, r.borderRadius, r.borderDash]
                }).css(u), a.appendChild(A), I.push(A));
                K = P + S - 4;
                X && (E.setStyle(t), M = E.getSmartText(k, h, G, !1), v = M.text, l = V, F || (F = c.blLabel = b.text(a)), F.attr({
                    text: v,
                    title: M.tooltext || "",
                    visibility: d,
                    fill: t.color,
                    "text-anchor": "start",
                    "vertical-align": Ia,
                    direction: f,
                    x: l + 4,
                    y: K,
                    "text-bound": [t.backgroundColor, t.borderColor,
                        t.borderThickness, t.borderPadding, t.borderRadius, t.borderDash
                    ]
                }).css(w), I.push(F));
                T && (E.setStyle(t), M = E.getSmartText(e, h, G, !1), v = M.text, l = V + O - 4, J || (J = c.brLabel = b.text(a)), J.attr({
                    text: v,
                    title: M.tooltext || "",
                    visibility: d,
                    fill: g.color,
                    "text-anchor": "end",
                    "vertical-align": Ia,
                    direction: f,
                    x: l,
                    y: K,
                    "text-bound": [g.backgroundColor, g.borderColor, g.borderThickness, g.borderPadding, g.borderRadius, g.borderDash]
                }).css(x), a.appendChild(J), I.push(J));
                return I
            },
            setScaleRange: function(b, d) {
                var a = this.logic,
                    c = this.plots[0],
                    e = {
                        visibility: "visible"
                    },
                    f = {
                        visibility: "hidden"
                    },
                    g = {
                        hcJSON: {
                            series: [{}]
                        }
                    },
                    h = g.hcJSON.series[0],
                    v = h.data || (h.data = []),
                    l = a.chartInstance.jsVars._reflowData,
                    p = c.items,
                    k, n, r, t, s, u, w, x = function(a) {
                        a.attr(u)
                    };
                setTimeout(function() {
                    var a, c;
                    a = 0;
                    for (c = p.length; a < c; a++) k = p[a], n = k.value, r = k.graphic, s = v[a] || (v[a] = {}), t = k.dataLabels, u = (w = n >= b && n <= d) ? e : f, r.attr(u), lb(t, x), s.visible = w;
                    O(l, g, !0)
                }, 100)
            }
        }, s["renderer.cartesian"]);
        s("renderer.radar", {
            createRadarAxis: function() {
                var b = this.options,
                    d = this.canvasLeft + this.canvasWidth /
                    2,
                    a = this.canvasTop + this.canvasHeight / 2,
                    c = b.xAxis,
                    e = b.yAxis instanceof Array ? b.yAxis[0] : b.yAxis,
                    f = c.max - c.min + 1,
                    g = S(e.max - e.min),
                    b = ba(b.chart.axisRadius) ? b.chart.axisRadius : J(d, a),
                    h, v = Ra.PI / 2,
                    l = {};
                0 > b && (b = J(d, a));
                h = 2 * Ra.PI / f;
                l.yTrans = b / g;
                l.xTrans = h;
                l.yRange = g;
                l.startAngle = v;
                l.yMin = e.min;
                l.centerX = d;
                l.centerY = a;
                l.radius = b;
                l.categories = [];
                l.catLength = f;
                l.yAxis = e;
                l.xAxis = c;
                return this.radarAxis = l
            },
            drawRadarAxis: function() {
                var b = this.radarAxis,
                    d = b.catLength,
                    a = b.xAxis,
                    c = b.yAxis,
                    e = c.min,
                    f = c.plotLines,
                    q =
                    f.length,
                    m = a.plotLines,
                    v = b.xTrans,
                    l = b.yTrans,
                    p = b.radius,
                    k = b.startAngle,
                    n = this.canvasLeft + this.canvasWidth / 2,
                    r = this.canvasTop + this.canvasHeight / 2,
                    t = this.paper,
                    s = this.layers,
                    u = s.dataset = s.dataset || t.group("orphan-dataset").trackTooltip(!0),
                    w = s.layerBelowDataset = s.layerBelowDataset || t.group("axisbottom").trackTooltip(!0),
                    x = s.layerAboveDataset = s.layerAboveDataset || t.group("axistop").trackTooltip(!0),
                    y = s.axisLines = s.axisLines || t.group("axis-lines", w),
                    B = s.axisLabels = s.axisLabels || t.group("axis-labels",
                        w),
                    A = c.labels,
                    c = 2 * Ra.PI,
                    F = Ra.PI / 2,
                    E = Ra.PI + F,
                    J = !1 !== (this.options.tooltip || {}).enabled,
                    I = ["right", "center", "left"],
                    G = a.labels,
                    L = g(.9 * parseInt(G.style && G.style.fontSize, 10), 9) / 2,
                    G = p + G.labelPadding,
                    O = [],
                    V = ["M"],
                    P = [],
                    X = this.logic.smartLabel,
                    T = this.options.chart.textDirection,
                    M, K, Z, U, N, Q, aa, Y, W, ca, da, ba = function(a) {
                        if (1 <= a) da = .5;
                        else return da = a || .5, a = (K - M) * da + (M - Z) * da, U > a && ba.call(this, da + .1), da
                    };
                w.insertBefore(u);
                x.insertAfter(u);
                b.divline = [];
                for (W = 0; W < q; W += 1) {
                    P[W] = ["M"];
                    w = !0;
                    u = d;
                    N = f[W];
                    ca = N.tooltext;
                    for (Q = N.value; u--;) A = S(Q - e) * l, aa = n + A * za(-(k + u * v)), Y = r + A * $a(-(k + u * v)), P[W].splice(P[W].length, 0, aa, Y), w && (P[W].push("L"), w = !1), 0 === u && N.label && (A = N.label, ((x = A.text) || 0 === x) && t.text(B).attr({
                        text: x,
                        x: aa,
                        y: Y,
                        "text-anchor": "right" === A.textAlign ? "end" : "left" === A.textAlign ? "start" : "middle",
                        "vertical-align": A.verticalAlign,
                        direction: T,
                        rotation: A.rotation
                    }).css(A.style));
                    P[W].push("Z");
                    b.divline[W] = t.path(P[W], y).attr({
                        stroke: N.color,
                        "stroke-width": N.width
                    });
                    J && ca && t.path({
                        stroke: ua,
                        "stroke-width": Ya(N.width,
                            bc),
                        ishot: !0,
                        path: P[W]
                    }, s.tracker).toBack().tooltip(ca)
                }
                w = !0;
                for (u = m.length; u--;)
                    if (N = m[u], Q = N.value, s = k + Q * v, d = s % c, aa = n + p * za(-s), Y = r + p * $a(-s), O.splice(O.length, 0, "M", n, r, "L", aa, Y), V.splice(V.length, 0, aa, Y), w && (V.push("L"), w = !1), N.label && (A = N.label, (x = A.text) || 0 === x)) {
                        f = d > F && d < E ? 0 : d == F || d == E ? 1 : 2;
                        d = A.style;
                        e = {
                            fontFamily: d.fontFamily,
                            fontSize: d.fontSize,
                            lineHeight: d.lineHeight,
                            fontWeight: d.fontWeight,
                            fontStyle: d.fontStyle,
                            color: d.color
                        };
                        X.setStyle(e);
                        f = "right" === I[f] ? "end" : "left" === I[f] ? "start" : "middle";
                        q = n + G * za(-s);
                        M = r + G * $a(-s);
                        U = parseInt(e.lineHeight, 10);
                        P = A.verticalAlign;
                        switch (f) {
                            case "start":
                                l = this.canvasLeft + this.canvasWidth - q;
                                s = m[u - 1];
                                s = k + s.value * v;
                                K = r + G * $a(-s) + L * $a(-s) + L;
                                s = m[u + 1 === m.length ? 0 : u + 1];
                                s = k + s.value * v;
                                Z = r + G * $a(-s) + L * $a(-s) + L;
                                s = ba();
                                J = (K - M) * s + (M - Z) * s;
                                P = "middle";
                                break;
                            case "end":
                                l = q - this.canvasLeft;
                                s = m[u + 1];
                                s = k + s.value * v;
                                K = r + G * $a(-s) + L * $a(-s) + L;
                                s = m[u - 1];
                                s = k + s.value * v;
                                Z = r + G * $a(-s) + L * $a(-s) + L;
                                s = ba();
                                J = (K - M) * s + (M - Z) * s;
                                P = "middle";
                                break;
                            default:
                                l = this.canvasWidth, J = U, M += L * $a(-s) + L
                        }
                        x = X.getSmartText(x,
                            l, J).text;
                        t.text(B).attr({
                            text: x,
                            x: q,
                            y: M,
                            "text-anchor": f,
                            "vertical-align": P,
                            rotation: A.rotation,
                            direction: T,
                            "text-bound": [d.backgroundColor, d.borderColor, d.borderThickness, d.borderPadding, d.borderRadius, d.borderDash]
                        }).css(e)
                    }
                V.push("Z");
                b.spikeGraph = t.path(O, y).attr({
                    stroke: a.gridLineColor,
                    "stroke-width": h(a.gridLineWidth, 1)
                });
                a.showRadarBorder && (b.borderGraph = t.path(V, y).toBack().attr({
                    stroke: a.radarBorderColor,
                    "stroke-width": h(a.radarBorderThickness, 2),
                    fill: a.radarFillColor
                }))
            },
            drawPlotRadar: function(b,
                d) {
                var a = this,
                    c = a.paper,
                    e = a.layers,
                    f = e.dataset = e.dataset || c.group("orphan-dataset"),
                    h = e.datalabels = e.datalabels || c.group("datalabels").insertAfter(f),
                    m = e.tracker = e.tracker || c.group("hot").insertAfter(f),
                    v = a.options,
                    l = v.chart.anchorTrackingRadius,
                    p = v.plotOptions.series,
                    k = [],
                    n = b.items || {},
                    r = b.graphics = b.graphics || [],
                    e = a.radarAxis,
                    t = d.data || [],
                    u = t.length,
                    x, w, y = !1 === d.visible,
                    A = y ? "hidden" : "visible",
                    p = isNaN(+p.animation) && p.animation.duration || 1E3 * p.animation,
                    B, F, G = !1 !== (v.tooltip || {}).enabled,
                    E, J, I, L,
                    O = f.radarGroup = f.radarGroup || c.group("connectors", f),
                    V = f.marker = f.marker || c.group("anchors", f),
                    X = m.trackers = m.trackers || c.group("trackers", m),
                    P = a.chartWidth,
                    da = a.chartHeight,
                    f = [],
                    T, M, K, Z, U, N, Q, aa, Y, W, ba, ea, ha, fa, ga, la, ia = (v = v.cursor) && {
                        cursor: v
                    },
                    ma = s["renderer.cartesian"],
                    na, pa, oa;
                void 0 === a.radarAxis && (e = a.radarAxis = a.createRadarAxis(d), a.drawRadarAxis(d));
                B = e.yTrans;
                E = e.yMin;
                J = e.startAngle;
                F = e.xTrans;
                x = e.centerX;
                w = e.centerY;
                1 <= u && (N = [], lb(t, function(e, f) {
                    Y = null;
                    f ? 2 > f && N.push("L") : N.push("M");
                    n[f] =
                        na = k[f] = {
                            chart: a,
                            index: f,
                            value: e.y
                        };
                    if (null === e.y) N.push(x, w);
                    else {
                        Q = W = null;
                        ba = e.link;
                        ea = e.tooltext || e.toolText;
                        I = x + B * S(e.y - E) * za(-(J + f * F));
                        L = w + B * S(e.y - E) * $a(-(J + f * F));
                        if (Q = e.anchorElem) U = g(Q.attr("r"), M.radius), Q.attr({
                            x: I,
                            y: L,
                            r: U
                        });
                        else if (M = e.marker, oa = {
                                index: f,
                                link: ba,
                                value: e.y,
                                displayValue: e.displayValue,
                                categoryLabel: e.categoryLabel,
                                toolText: ea,
                                id: b.userID,
                                datasetIndex: b.index,
                                datasetName: b.name,
                                visible: b.visible
                            }, M && M.enabled)
                            if (K = M.radius, ha = M.shadow, T = M.symbol.split("_"), Z = "spoke" === T[0] ?
                                1 : 0, ga = la = {}, fa = e.rolloverProperties, M.imageUrl) pa = new Xa.Image, pa.onload = function(e, f, g, h, k, l, n, q) {
                                return function() {
                                    var p = g.imageUrl,
                                        t = g.imageScale,
                                        s = g.imageAlpha,
                                        v = n.imageHoverAlpha,
                                        u = n.imageHoverScale,
                                        w = this.width * t * .01,
                                        x = this.width * u * .01;
                                    ga = {
                                        x: e - this.width * t * .005,
                                        y: f - this.height * t * .005,
                                        width: w,
                                        height: this.height * t * .01,
                                        alpha: s
                                    };
                                    la = {
                                        x: e - this.width * u * .005,
                                        y: f - this.height * u * .005,
                                        width: x,
                                        height: this.height * u * .01,
                                        alpha: v
                                    };
                                    v = x > w ? la : ga;
                                    (h.graphic = Q = c.image(p, V).attr(ga).css({
                                        opacity: .01 * s
                                    }).data("alwaysInvisible",
                                        0 === t).data("setRolloverProperties", n).data("setRolloverAttr", la).data("setRolloutAttr", ga).data("anchorRadius", t).data("anchorHoverRadius", u)) && r.push(Q);
                                    if (ba || G || n) W = h.tracker = c.rect(m).attr(v).attr({
                                        cursor: ba ? "pointer" : "",
                                        stroke: ua,
                                        "stroke-width": g.lineWidth,
                                        fill: ua,
                                        ishot: !0,
                                        visibility: A
                                    }).data("eventArgs", k).click(function(b) {
                                        xa.call(this, a, b)
                                    }).hover(function(b) {
                                        return function(c) {
                                            ma.hoverPlotAnchor(this, c, "DataPlotRollOver", b, a)
                                        }
                                    }(h), function(b) {
                                        return function(c) {
                                            ma.hoverPlotAnchor(this, c,
                                                "DataPlotRollOut", b, a)
                                        }
                                    }(h)).tooltip(l);
                                    (Y = h.dataLabel = ma.drawPlotLineLabel.call(a, b, d, q, e, f)) && r.push(Y)
                                }
                            }(I, L, M, na, oa, ea, fa, f), pa.onerror = function(c, e, f, g, h, k, l, m) {
                                return function() {
                                    (Y = g.dataLabel = ma.drawPlotLineLabel.call(a, b, d, m, c, e)) && r.push(Y)
                                }
                            }(I, L, M, na, oa, ea, fa, f), pa.src = M.imageUrl;
                            else {
                                fa && (ga = {
                                    polypath: [T[1] || 2, I, L, K, M.startAngle, Z],
                                    fill: ca(M.fillColor),
                                    "stroke-width": M.lineWidth,
                                    stroke: ca(M.lineColor)
                                }, la = {
                                    polypath: [fa.sides || 2, I, L, fa.radius, fa.startAngle, fa.dip],
                                    fill: ca(fa.fillColor),
                                    "stroke-width": fa.lineWidth,
                                    stroke: ca(fa.lineColor)
                                });
                                Q = na.graphic = c.polypath(T[1] || 2, I, L, K, M.startAngle, null, V).attr({
                                    fill: ca(M.fillColor),
                                    "stroke-width": M.lineWidth,
                                    stroke: ca(M.lineColor),
                                    cursor: ba ? "pointer" : "",
                                    "stroke-linecap": "round",
                                    "stroke-linejoin": "round",
                                    ishot: !0,
                                    visibility: 0 === K ? "hidden" : A
                                }).data("alwaysInvisible", 0 === K).data("setRolloverProperties", fa).data("setRolloverAttr", la).data("setRolloutAttr", ga).data("anchorRadius", K).data("anchorHoverRadius", fa && fa.radius).shadow(ha);
                                e.anchorElem =
                                    Q;
                                if (ba || G || fa)(W = e.trackerElem) ? (U = g(W.attr("r"), M.radius + 1), W.attr({
                                    x: I,
                                    y: L,
                                    r: U
                                })) : (T || (T = M.symbol.split("_")), K = Ya(K, l, fa && fa.radius || 0), W = c.circle(I, L, K, X).attr({
                                    cursor: e.link ? "pointer" : "",
                                    stroke: ua,
                                    "stroke-width": 1,
                                    fill: ua,
                                    ishot: !0,
                                    visibility: A
                                }).css(ia)), e.trackerElem = W;
                                (W = W || Q) && W.data("eventArgs", oa).click(function(b) {
                                    xa.call(this, a, b)
                                }).hover(function(b) {
                                    return function(c) {
                                        ma.hoverPlotAnchor(this, c, "DataPlotRollOver", b, a)
                                    }
                                }(na), function(b) {
                                    return function(c) {
                                        ma.hoverPlotAnchor(this, c, "DataPlotRollOut",
                                            b, a)
                                    }
                                }(na)).tooltip(ea)
                            }
                        N.push(I, L);
                        na.dataLabel = Y;
                        na.tracker = W;
                        M && M.imageUrl || (Y = ma.drawPlotLineLabel.call(a, b, d, f, I, L));
                        Q && r.push(Q);
                        Y && r.push(Y);
                        W && r.push(W)
                    }
                }), N.push("Z"), f = f.concat(N));
                f && 0 < f.length && (aa = b.graphic = c.path(f, O).attr({
                    stroke: ca(d.lineColor.FCcolor),
                    fill: ca(d.fillColor.FCcolor),
                    "stroke-width": d.lineWidth,
                    visibility: A
                }));
                p && (a.animationCompleteQueue.push({
                    fn: function() {
                        V.show();
                        h.attr({
                            transform: "...t" + -P + "," + -da
                        })
                    },
                    scope: a
                }), V.hide(), h.attr({
                    transform: "...t" + P + "," + da
                }), O.scale(.01,
                    .01, x, w).animate({
                    transform: "s1,1"
                }, p, "normal", a.getAnimationCompleteFn()));
                aa && r.push(aa);
                b.visible = !y
            },
            legendClick: function(b) {
                s["renderer.cartesian"].legendClick.call(this, b)
            },
            getEventArgs: function(b) {
                return s["renderer.cartesian"].getEventArgs.call(this, b)
            }
        }, s["renderer.root"]);
        s("renderer.multiLevelPie", {
            drawPlotMultilevelpie: function(b, d) {
                var a = this,
                    c = b.items,
                    e = b.data,
                    f = a.options,
                    h = f.plotOptions.series,
                    m = a.layers,
                    s = h.animation,
                    l = h.dataLabels.style,
                    p = h.shadow,
                    k = g(b.moveDuration, s.duration, 0),
                    n = h.borderWidth,
                    r = h.borderColor,
                    t = a.paper,
                    u = f.chart.textDirection,
                    f = (f = f.tooltip || {}, !1 !== f.enabled),
                    x = (d.startAngle || 0) % la,
                    w = la / (d.valueTotal || 100),
                    y = a.canvasLeft + .5 * a.canvasWidth,
                    A = a.canvasTop + .5 * a.canvasHeight,
                    B, F, G, E, L, I, O, V, S, X, P;
                F = J(a.canvasWidth, a.canvasHeight);
                var ba, T = m.dataset,
                    M = s.mainItem,
                    K = s.animObj,
                    Z = function(b) {
                        xa.call(this.graphic, a, b, "DataPlotRollOver");
                        h.point.events.mouseOver.call(this)
                    },
                    U = function(b) {
                        xa.call(this.graphic, a, b, "DataPlotRollOut");
                        h.point.events.mouseOut.call(this)
                    },
                    N = function(b) {
                        xa.call(this.graphic, a, b, "DataPlotRollOver");
                        h.point.events.mouseOver.call(this)
                    },
                    Q = function(b) {
                        xa.call(this.graphic, a, b, "DataPlotRollOut");
                        h.point.events.mouseOut.call(this)
                    },
                    aa = function() {
                        a.placeDataLabels(!1, c, b, d)
                    };
                B = .5 * (/%$/.test(d.size) ? F * parseInt(d.size, 10) / 100 : d.size);
                F = .5 * (/%$/.test(d.innerSize) ? F * parseInt(d.innerSize, 10) / 100 : d.innerSize);
                d.metrics = [y, A, 2 * B, 2 * F];
                e && e.length || (e = []);
                ba = m.datalabels || (m.datalabels = t.group("datalabels").insertAfter(T));
                X = S = x;
                for (P = e.length; P--;) E =
                    e[P], L = E.y, I = E.displayValue, m = E.toolText, O = !!E.link, null !== L && void 0 !== L && (X = S, S -= L * w, V = .5 * (S + X), (G = c[P]) || (G = c[P] = {
                                chart: a,
                                link: E.link,
                                value: L,
                                angle: V,
                                color: E.color,
                                prevPointIndex: E.prevPointIndex,
                                prevSeriesIndex: E.prevSeriesIndex,
                                labelText: I,
                                graphic: t.ringpath(y, A, B, F, x, x, T).attr({
                                    "stroke-width": E.borderWidth || n,
                                    stroke: E.borderColor || r,
                                    fill: ca(E.color),
                                    "stroke-dasharray": E.dashStyle,
                                    ishot: !f,
                                    cursor: O ? "pointer" : ""
                                }).shadow(p && !!E.shadow)
                            }, E = {
                                link: E.link,
                                label: E.displayValue,
                                toolText: E.toolText
                            },
                            G.graphic.mouseover(Z, G), G.graphic.mouseout(U, G), G.graphic.mouseup(a.plotMouseUp), G.graphic.data("plotItem", G), G.graphic.data("eventArgs", E), f && G.graphic.tooltip(m), void 0 !== I && (G.dataLabel = t.text(ba).css(l).attr({
                                text: I,
                                fill: l.color || "#000000",
                                visibility: "hidden",
                                direction: u,
                                ishot: O,
                                cursor: O ? "pointer" : ""
                            }).mouseover(N, G).mouseout(Q, G).mouseup(a.plotMouseUp).data("plotItem", G).data("eventArgs", E), f && G.dataLabel.tooltip(m))), k ? M ? G.graphic.animateWith(M, K, {
                            ringpath: [y, A, B, F, S, X]
                        }, k, "easeIn", !P && aa) :
                        (K = s.animObj = ga.animation({
                            ringpath: [y, A, B, F, S, X]
                        }, k, "easeIn", !P && aa), M = s.mainItem = G.graphic.animate(K)) : (G.graphic.attr({
                            ringpath: [y, A, B, F, S, X]
                        }), !P && aa && aa()))
            },
            plotMouseUp: function(b) {
                var d = this.data("plotItem");
                xa.call(this, d.chart, b)
            }
        }, s["renderer.piebase"]);
        ga.addSymbol({
            resizeIcon: function(b, d, a) {
                var c = g(a, 15) / 3,
                    e = [];
                0 > c && (c = -c, a = -a, b += a - c / 2, d += a - c / 2);
                for (a = 3; 0 < a; --a) e.push("M", b - c * a, d - 3, "L", b - 3, d - c * a);
                return e
            },
            closeIcon: function(b, d, a) {
                var c = 1.3 * a,
                    e = 43 * ic,
                    f = 48 * ic,
                    g = b + c * za(e),
                    e = d + c * $a(e),
                    h = b + c * za(f),
                    s = d + c * $a(f),
                    f = .71 * (a - 2);
                a = .71 * (a - 2);
                c = ["A", c, c, 0, 1, 0, h, s];
                g = ["M", g, e];
                g = g.concat(c);
                return g = g.concat(["M", b + f, d - a, "L", b - f, d + a, "M", b - f, d - a, "L", b + f, d + a])
            },
            configureIcon: function(b, d, a) {
                --a;
                var c = .71 * a,
                    e = .71 * (a + 2),
                    f = b - a,
                    g = d - a,
                    h = b + a;
                a = d + a;
                var s = b + .5,
                    l = d + .5,
                    p = b - .5,
                    k = d - .5,
                    n = f - 2,
                    r = g - 2,
                    t = h + 2,
                    u = a + 2,
                    x = b + c,
                    w = d + c,
                    y = b - c,
                    c = d - c,
                    A = b + e,
                    B = d + e;
                b -= e;
                d -= e;
                return ["M", f, l, "L", n, l, n, k, f, k, y - .25, c + .25, b - .25, d + .25, b + .25, d - .25, y + .25, c - .25, p, g, p, r, s, r, s, g, x - .25, c - .25, A - .25, d - .25, A + .25, d + .25, x + .25, c + .25, h, k, t, k,
                    t, l, h, l, x + .25, w - .25, A + .25, B - .25, A - .25, B + .25, x - .25, w + .25, s, a, s, u, p, u, p, a, y + .25, w + .25, b + .25, B + .25, b - .25, B - .25, y - .25, w - .25, "Z"
                ]
            },
            axisIcon: function(b, d, a) {
                --a;
                var c = .33 * a,
                    e = a / 2,
                    f = b - a,
                    g = d - a,
                    h = b + e;
                a = d + a;
                b -= e;
                e = d + c;
                d -= c;
                return ["M", f, g, "L", h, g, h, a, f, a, "M", b, e, "L", h, e, "M", b, d, "L", h, d]
            },
            loggerIcon: function(b, d, a) {
                --a;
                b -= a;
                d -= a;
                var c = b + 2 * a,
                    e = b + 2,
                    f = c - 2,
                    g = d + 2;
                a = g + a;
                var h = a + 2;
                return ["M", b, d, "L", c, d, c, g, f, g, f, a, c, a, c, h, b, h, b, a, e, a, e, g, b, g, b, d]
            }
        })
    },
    [3, 2, 1, "release"]
]);